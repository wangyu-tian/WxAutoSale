package com.wx_auto_sale.wx.service;

import com.wrapper.model.OrderByModel;
import com.wrapper.util.JpaUtil;
import com.wrapper.util.StringUtils;
import com.wrapper.wrapper.SqlWrapper;
import com.wx_auto_sale.utils.BeanUtils;
import com.wx_auto_sale.wx.model.dto.response.CategoryOutDto;
import com.wx_auto_sale.wx.model.dto.response.CommodityOutDto;
import com.wx_auto_sale.wx.model.dto.response.DiscountOutDto;
import com.wx_auto_sale.wx.model.dto.response.GoodsOutDto;
import com.wx_auto_sale.wx.model.entity.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Transient;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


/**
 * @Author wangyu
 * @Create: 2020/4/10 12:50 下午
 * @Description:
 */
@Service
@Slf4j
public class GoodsService {

    @Autowired
    private JpaUtil jpaUtil;

    @Autowired
    private MerchantService merchantService;

    @Autowired
    private UserService userService;

    /**
     * 查询用户下所有商品和分类信息
     *
     * @param uId
     * @param mId
     * @return
     */
    @Transient
    public GoodsOutDto allInfo(String uId, String mId) {
        GoodsOutDto goodsOutDto = new GoodsOutDto();
        MerchantEntity merchantEntity = merchantService.getMerchantById(mId);
        //商品列表暂不根据用户区分
//        UserEntity userEntity = userService.getUserById(uId,null);

        //获取分类信息
        SqlWrapper<CategoryEntity> categoryWrapper = new SqlWrapper<>(CategoryEntity.class);
        List<CategoryEntity> categoryEntityList = jpaUtil.wrapper(categoryWrapper.eq(CategoryEntity::getMId, mId)
                .eq(CategoryEntity::getValid, "1")
                .orderBy(categoryWrapper.newOrderByModel(CategoryEntity::getSort)));
        //查询商品信息
        SqlWrapper<CommodityEntity> commodityWrapper = new SqlWrapper<>(CommodityEntity.class);
        List<CommodityEntity> commodityEntityList = jpaUtil.wrapper(commodityWrapper.eq(CommodityEntity::getMId, mId)
                .eq(CommodityEntity::getValid, "1")
                .orderBy(commodityWrapper.newOrderByModel(CommodityEntity::getSort)));

        List<CommodityOutDto> commodityOutDtoList = transFormDataEntityToDTO(commodityEntityList);

        goodsOutDto.setCategoryOutDtoList(BeanUtils.batchModel(categoryEntityList, CategoryOutDto.class));
        goodsOutDto.setCommodityOutDtoList(commodityOutDtoList);
        return goodsOutDto;
    }

    //商品实体信息转换成dto信息
    private List<CommodityOutDto> transFormDataEntityToDTO(List<CommodityEntity> commodityEntityList) {
        //查询商品折扣信息
        List<String> dIdList = commodityEntityList.stream().filter(m -> {
            if (StringUtils.isNotEmpty(m.getDId())) {
                return true;
            }
            return false;
        }).map(CommodityEntity::getDId).collect(Collectors.toList());
        List<DiscountEntity> discountEntityList = jpaUtil.wrapper(new SqlWrapper<>(DiscountEntity.class)
                .in(DiscountEntity::getId, CollectionUtils.isEmpty(dIdList)?null:dIdList,true)
                .eq(DiscountEntity::getValid, "1")).stream().distinct().collect(Collectors.toList());
        //将商品列表中加入折扣信息数据
        List<CommodityOutDto> commodityOutDtoList = BeanUtils.batchModel(commodityEntityList, CommodityOutDto.class);
        commodityOutDtoList.stream().forEach(m -> {
            discountEntityList.forEach(d -> {
                if (StringUtils.isNotEmpty(m.getDId()) && m.getDId().equals(d.getId())) {
                    DiscountOutDto discountOutDto = BeanUtils.copyProperties(d, DiscountOutDto.class);
                    transData(m.getPrice(), discountOutDto);
                    m.setDiscountOutDto(discountOutDto);
                }
            });
        });
        return commodityOutDtoList;
    }

    /**
     * 折扣转换，计算折扣价
     * @param price 原价
     * @param discountOutDto
     */
    private void transData(String price, DiscountOutDto discountOutDto) {
        if ("1".equals(discountOutDto.getShowDiscount())) {
            switch (discountOutDto.getType()) {
                case "3"://折扣m折限购n个
                    String discountPrice = new BigDecimal(price).divide(new BigDecimal(10)).multiply(new BigDecimal(discountOutDto.getM()))
                            .setScale(2, BigDecimal.ROUND_HALF_UP).toString();
                    discountOutDto.setDiscountPrice(discountPrice);
                    break;
                default:
                    log.error("非折扣价商品不能显示折扣价");
            }
        }
    }

    public List<CommodityOutDto> getByIds(List<String> ids) {
        List<CommodityEntity> commodityEntityList = jpaUtil.wrapper(new SqlWrapper<>(CommodityEntity.class)
                .in(CommodityEntity::getId,ids)
                .eq(CommodityEntity::getValid,"1"));

        return transFormDataEntityToDTO(commodityEntityList);
    }
}

