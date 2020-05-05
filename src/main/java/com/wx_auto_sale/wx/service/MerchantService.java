package com.wx_auto_sale.wx.service;

import com.wrapper.util.JpaUtil;
import com.wrapper.util.StringUtils;
import com.wrapper.wrapper.SqlWrapper;
import com.wx_auto_sale.constants.ErrorCode;
import com.wx_auto_sale.utils.BeanUtils;
import com.wx_auto_sale.utils.PermissionUtil;
import com.wx_auto_sale.wx.model.dto.response.DiscountOutDto;
import com.wx_auto_sale.wx.model.dto.response.MerchantOutDto;
import com.wx_auto_sale.wx.model.entity.DiscountEntity;
import com.wx_auto_sale.wx.model.entity.MerchantEntity;
import com.wx_auto_sale.wx.repository.MerchantRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @Author wangyu
 * @Create: 2020/4/10 12:50 下午
 * @Description:
 */
@Service
@Slf4j
public class MerchantService {

    @Autowired
    private JpaUtil jpaUtil;
    @Autowired
    private MerchantRepository merchantRepository;

    /**
     * 通过ID获取商户对象
     *
     * @param id
     * @return
     */
    public MerchantEntity getMerchantById(String id) {
        List<MerchantEntity> merchantEntityList = jpaUtil.wrapper(new SqlWrapper<>(MerchantEntity.class)
                .eq(MerchantEntity::getId,id)
                .eq(MerchantEntity::getValid,"1"));
        PermissionUtil.isTrue(merchantEntityList.size() == 0, ErrorCode.UserEnum.WX_MERCHANT_NOT_EXIST);
        return merchantEntityList.get(0);
    }

    /**
     * 通过ID获取商户对象以及折扣信息
     * @param id
     * @return
     */
    public MerchantOutDto getMerchantDtoById(String id) {
        List<MerchantEntity> merchantEntityList = jpaUtil.wrapper(new SqlWrapper<>(MerchantEntity.class)
                .eq(MerchantEntity::getId,id)
                .eq(MerchantEntity::getValid,"1"));
        PermissionUtil.isTrue(merchantEntityList.size() == 0, ErrorCode.UserEnum.WX_MERCHANT_NOT_EXIST);
        MerchantOutDto m = BeanUtils.copyProperties(merchantEntityList.get(0),MerchantOutDto.class);
        if(StringUtils.isNotEmpty(m.getDId())
                && m.getDDateStart() != null
                && m.getDDateEnd() != null
                && DateUtils.truncatedCompareTo(new Date(),m.getDDateStart(), Calendar.SECOND) > 0
                && DateUtils.truncatedCompareTo(new Date(),m.getDDateEnd(), Calendar.SECOND) < 0){
            //在折扣时间范围内
            List<DiscountEntity> discountEntityList = jpaUtil.wrapper(new SqlWrapper<>(DiscountEntity.class)
                    .eq(DiscountEntity::getId,m.getDId())
                    .eq(DiscountEntity::getValid,"1"));
            if(discountEntityList.size() > 0){
                m.setDiscountOutDto(BeanUtils.copyProperties(discountEntityList.get(0),DiscountOutDto.class));
            }

        }
        return m;
    }
}
