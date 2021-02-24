package com.wx_auto_sale.wx.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wrapper.constants.SqlWrapperConfig;
import com.wrapper.util.JpaUtil;
import com.wrapper.util.StringUtils;
import com.wrapper.wrapper.SqlWrapper;
import com.wx_auto_sale.config.WxAutoException;
import com.wx_auto_sale.constants.DataEnum;
import com.wx_auto_sale.utils.*;
import com.wx_auto_sale.wx.model.dto.AgentThreadLocal;
import com.wx_auto_sale.wx.model.dto.PageDto;
import com.wx_auto_sale.wx.model.dto.request.OrderInDto;
import com.wx_auto_sale.wx.model.dto.response.*;
import com.wx_auto_sale.wx.model.entity.MerchantEntity;
import com.wx_auto_sale.wx.model.entity.OrderEntity;
import com.wx_auto_sale.wx.model.entity.OrderUserEntity;
import com.wx_auto_sale.wx.model.entity.UserEntity;
import com.wx_auto_sale.wx.repository.OrderRepository;
import com.wx_auto_sale.wx.repository.OrderUserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

import static com.wx_auto_sale.constants.DataEnum.DiscountEnum.*;
import static com.wx_auto_sale.constants.DataEnum.OrderEnum.*;
import static com.wx_auto_sale.constants.ErrorCode.OrderEnum.*;

/**
 * @Author wangyu
 * @Create: 2020/4/10 12:50 下午
 * @Description:
 */
@Service
@Slf4j
public class OrderService {

    @Autowired
    private JpaUtil jpaUtil;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private MerchantService merchantService;

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private OrderUserRepository orderUserRepository;

    @Autowired
    private UserService userService;

    private static final int MAX_ORDER_COUNT = 10;

    //不开启事务，对账不通过的订单也保存。
    public OrderOutDto uAdd(String mId, String uId, OrderInDto orderInDto) {
        //查询今天已提交订单数
        int orderCount = getOrderCountByDate(mId, uId, DateUtil.date2string(DateUtil.now(), "yyyyMMdd"));
        PermissionUtil.isTrue(MAX_ORDER_COUNT < orderCount, SUBMIT_MORE_LIMIT);
        //新增订单
        OrderEntity orderEntityReqNo = getOrderByRequestNo(orderInDto.getReqNo(), mId);
        PermissionUtil.isTrue(orderEntityReqNo != null, REQUEST_NO_IS_REPEAT);
        MerchantOutDto merchantOutDto = merchantService.getMerchantDtoById(mId);
        OrderEntity orderEntity = createOrderModel(merchantOutDto, uId, orderInDto);
        boolean isLegal = checkOrder(merchantOutDto, orderEntity);
        if (!isLegal) {//不合法保存非法订单
            orderEntity.setValid("0");
            orderEntity.setMsg(DataUtil.transOrderMsg(null,"金额校验不合法，已自动终止"));
            orderEntity.setStatus(STATUS_7.getCode());
        }
        //保存订单
        orderRepository.save(orderEntity);
        //保存订单用户信息数据
        orderUserRepository.save(createOrderUser(orderEntity));
        PermissionUtil.isTrue(!isLegal, AMOUNT_IS_NOT_LEGAL);
        UserEntity userEntity = userService.findById(uId);
        userEntity.setName(orderInDto.getName());
        userEntity.setPhone(orderInDto.getPhone());
        userEntity.setAddress(orderEntity.getAddress());
        userService.save(userEntity);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("character_string1", orderEntity.getCode());
        jsonObject.put("thing4", orderEntity.getListName().length() > 20 ? orderEntity.getListName().substring(0, 17) + "..." : orderEntity.getListName());
        jsonObject.put("number5", JSONObject.parseObject(orderEntity.getDetail()).size());
        jsonObject.put("amount2", orderEntity.getDiscountAmount());
        jsonObject.put("thing3", orderEntity.getUMemo());
        JSONObject jsonData = null;
        try {
            //推送数据到订单用户
            jsonData = WxUtil.pushWxMessage(
                    WxUtil.getAccessToken(merchantOutDto.getAppid(), merchantOutDto.getSecret()).getString("access_token"),
                    userEntity.getWId(),
                    jsonObject,
                    transformPushPageParams(mId, uId));
            //推送数据到商户
            WxUtil.pushWXNotify(orderEntity.getName(),
                    orderEntity.getCode(),
                    orderEntity.getListName(),
                    orderEntity.getOrderAmount()+"元",
                    orderEntity.getUMemo(),
                    DateUtil.date2string(orderEntity.getCreateDate(),DateUtil.FORMAT_19),
                    "https://www.tx.wtianyu.com:7899/view/order/" + orderEntity.getUId() + "/" + orderEntity.getCode()
            );
        } catch (Exception e) {
            log.error("推送信息出错:", e);
        } finally {
            log.info("推送消息结果：{}", JSON.toJSONString(jsonData));
        }


        return BeanUtils.copyProperties(orderEntity, OrderOutDto.class);
    }

    /**
     * 根据日期获取订单
     *
     * @param mId
     * @param uId
     * @param date
     * @return
     */
    private int getOrderCountByDate(String mId, String uId, String date) {
        SqlWrapper<OrderEntity> sqlWrapper = new SqlWrapper<>(OrderEntity.class)
                .eq(OrderEntity::getMId, mId)
                .eq(OrderEntity::getUId, uId)
                .ge(OrderEntity::getCreateDate, DateUtil.string2date(date.concat(DateUtil.DATE_START), DateUtil.FORMAT_14))
                .le(OrderEntity::getCreateDate, DateUtil.string2date(date.concat(DateUtil.DATE_END), DateUtil.FORMAT_14));
        return jpaUtil.count(sqlWrapper.getHql(), sqlWrapper.getParamsMap());
    }


    /**
     * 组合推送跳转地址参数
     *
     * @param mId
     * @param uId
     * @return
     */
    private String transformPushPageParams(String mId, String uId) {

        PageDto<List<OrderOutDto>> pageDto = pageList(mId, uId, 0, 1);
        OrderOutDto orderOutDto = pageDto.getData().get(0);
        StringBuilder sb = new StringBuilder("type=2");
        sb.append("&orderInfo=").append(JSON.toJSONString(orderOutDto));
        return sb.toString();
    }

    /**
     * 订单用户信息
     *
     * @param orderEntity
     * @return
     */
    private OrderUserEntity createOrderUser(OrderEntity orderEntity) {
        OrderUserEntity orderUserEntity = new OrderUserEntity();
        orderUserEntity.setAddress(orderEntity.getAddress());
        orderUserEntity.setCreateDate(new Date());
        orderUserEntity.setMemo(orderEntity.getUMemo());
        orderUserEntity.setMId(orderEntity.getMId());
        orderUserEntity.setName(orderEntity.getName());
        orderUserEntity.setOrderId(orderEntity.getId());
        orderUserEntity.setUId(orderEntity.getUId());
        orderUserEntity.setValid("1");
        orderUserEntity.setPhone(orderEntity.getPhone());
        return orderUserEntity;
    }

    /**
     * 校验订单的合法性
     *
     * @param merchantOutDto
     * @param orderEntity
     * @return
     */
    private boolean checkOrder(MerchantOutDto merchantOutDto, OrderEntity orderEntity) {
        //查看已购买商品
        String detail = orderEntity.getDetail();
        JSONObject jsonGoods = JSONObject.parseObject(detail);
        JSONObject jsonGoodsNew = JSONObject.parseObject(detail);
        List<CommodityOutDto> commodityOutDtoList = goodsService.getByIds(new ArrayList<>(jsonGoods.keySet()));
        PermissionUtil.isTrue(commodityOutDtoList.size() != jsonGoods.size(), GOOD_IS_NOT_LEGAL);
        //订单金额
        BigDecimal orderAmount = new BigDecimal("0.00");
        //订单折扣金额
        BigDecimal discountAmount = new BigDecimal("0.00");
        String goodNames = "";
        //商品折扣金额
        for (String key : jsonGoods.keySet()) {
            for (CommodityOutDto dto : commodityOutDtoList) {
                if (key.equals(dto._id)) {
                    //商品原价金额计算
                    BigDecimal orderAmountTemp = new BigDecimal("0.00");
                    BigDecimal discountAmountTemp = new BigDecimal("0.00");
                    orderAmountTemp = orderAmountTemp.add(new BigDecimal(dto.getPrice()).multiply(new BigDecimal(String.valueOf(jsonGoods.get(key)))));
                    orderAmount = orderAmount.add(orderAmountTemp);
                    //商品折扣金额计算
                    discountAmountTemp = discountAmount(dto.getDiscountOutDto(), dto.getPrice(), discountAmountTemp, String.valueOf(jsonGoods.get(key)));
                    discountAmount = discountAmount.add(discountAmountTemp);
                    //拼接订单信息
                    //count数量;price单价;orderAmount商品原价;discountAmount商品折扣价;name商品名称;imageUrl商品地址:discountMemo商品折扣描述
                    jsonGoodsNew.put(key, jsonGoods.get(key) + ";" + dto.getPrice() + ";" + orderAmountTemp + ";"
                            + discountAmountTemp + ";" + dto.getName() + ";" + dto.getImageUrl() + ";" + (dto.getDiscountOutDto() == null ? "" : dto.getDiscountOutDto().getMemo()));
                    goodNames += dto.getName() + ",";
                }
            }
        }
        //商户折扣
        if (merchantOutDto.getDiscountOutDto() != null) {
            discountAmount = discountAmount(merchantOutDto.getDiscountOutDto(), null, discountAmount, "1");
        }
        orderEntity.setDetail(jsonGoodsNew.toJSONString());
        orderEntity.setDiscountMemo(merchantOutDto.getDiscountOutDto() == null ? null : merchantOutDto.getDiscountOutDto().getMemo());
        orderEntity.setListName(goodNames.substring(0, goodNames.length() - 2));
        return discountAmount.compareTo(new BigDecimal(orderEntity.getDiscountAmount())) == 0
                && orderAmount.compareTo(new BigDecimal(orderEntity.getOrderAmount())) == 0;
    }

    /**
     * 商品折扣金额计算
     *
     * @param discountOutDto 折扣dto
     * @param price          单价
     * @param discountAmount 累计金额
     * @param count          数量
     */
    private BigDecimal discountAmount(DiscountOutDto discountOutDto, String price, BigDecimal discountAmount, String count) {
        //不存在折扣信息
        if (discountOutDto == null) {
            return discountAmount.add(new BigDecimal(price).multiply(new BigDecimal(count)));
        }

        //折扣计算规则
        if (DISCOUNT_TYPE_3.getCode().equals(discountOutDto.getType())) {
            //限购个数
            long nL = Long.valueOf(discountOutDto.getN());
            //已购个数
            long countL = Long.valueOf(count);
            if (nL >= countL) {//全部折扣
                discountAmount = discountAmount.add(new BigDecimal(discountOutDto.getDiscountPrice()).multiply(new BigDecimal(count)));
            } else {//部分折扣
                discountAmount = discountAmount.add(new BigDecimal(discountOutDto.getDiscountPrice()).multiply(new BigDecimal(nL)));
                discountAmount = discountAmount.add(new BigDecimal(price).multiply(new BigDecimal(countL - nL)));
            }
        } else if (DISCOUNT_TYPE_1.getCode().equals(discountOutDto.getType())) {
            //满足折扣条件
            if (discountAmount.compareTo(new BigDecimal(discountOutDto.getM())) > 0) {
                discountAmount = discountAmount.subtract(new BigDecimal(discountOutDto.getN()));
            }
            return discountAmount;
        } else {
            throw new WxAutoException(GOOD_DISCOUNT_IS_ERROR);
        }
        return discountAmount;
    }

    private OrderEntity getOrderByRequestNo(String reqNo, String mId) {
        PermissionUtil.isTrue(StringUtils.isEmpty(reqNo), REQUEST_NO_IS_BLANK);
        List<OrderEntity> orderEntityList = jpaUtil.wrapper(new SqlWrapper<>(OrderEntity.class)
                .eq(OrderEntity::getReqNo, reqNo)
                .eq(OrderEntity::getMId, mId));
        //无论是否失效，都不允许出现重复的流水号
        //.eq(OrderEntity::getValid, "1"))
        if (orderEntityList.size() > 0) {
            return orderEntityList.get(0);
        }
        return null;
    }

    //创建订单对象
    private OrderEntity createOrderModel(MerchantOutDto merchantOutDto, String uId, OrderInDto orderInDto) {
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setUId(uId);
        orderEntity.setAddress(orderInDto.getAddress());
        orderEntity.setDeliveryFee(orderEntity.getDeliveryFee());
        orderEntity.setDetail(orderInDto.getDetail());
        orderEntity.setDiscountAmount(orderInDto.getDiscountAmount());
        orderEntity.setExpectDate(orderInDto.getExpectDate());
        orderEntity.setMId(merchantOutDto._id);
        orderEntity.setMMemo(null);
        orderEntity.setMPhone(merchantOutDto.getPhone());
        orderEntity.setMsg(null);
        orderEntity.setOrderAmount(orderInDto.getOrderAmount());
        orderEntity.setPayAmount(null);
        orderEntity.setCode(new SimpleDateFormat("HHmmssSSS").format(new Date()));
        orderEntity.setPhone(orderInDto.getPhone());
        orderEntity.setName(orderInDto.getName());
        orderEntity.setReqNo(orderInDto.getReqNo());
        orderEntity.setUMemo(orderInDto.getUMemo());
        orderEntity.setValid("1");
        orderEntity.setStatus(DataEnum.OrderEnum.STATUS_1.getCode());
        orderEntity.setCreateDate(new Date());
        return orderEntity;
    }

    /**
     * 查询订单列表（时间倒序）
     *
     * @param mId
     * @param uId
     * @param currentPage
     * @param pageSize
     * @return
     */
    public PageDto pageList(String mId, String uId, Integer currentPage, Integer pageSize) {
        SqlWrapper<OrderEntity> sqlWrapper = new SqlWrapper<>(OrderEntity.class);
        sqlWrapper.eq(OrderEntity::getValid, "1")
                .eq(OrderEntity::getMId, mId)
                .eq(OrderEntity::getUId, uId)
                .orderBy(sqlWrapper.newOrderByModel(OrderEntity::getCreateDate, SqlWrapperConfig.Order.DESC));
        Pageable pageable = PageRequest.of(currentPage, pageSize);
        //分页查询
        Page<OrderEntity> page = jpaUtil.pageWrapper(sqlWrapper, pageable);
        List<OrderOutDto> orderOutDtoList = BeanUtils.batchModel(page.getContent(), OrderOutDto.class);
        PageDto<List<OrderOutDto>> pageDto = new PageDto<>(page.getTotalElements()
                , page.getTotalPages()
                , orderOutDtoList);
        return pageDto;
    }

    /**
     * 查询订单用户信息
     *
     * @param uId
     * @param mId
     * @return
     */
    public OrderUserDto findOrderUserInfoBest(String uId, String mId) {
        SqlWrapper<OrderUserEntity> sqlWrapper = new SqlWrapper<>(OrderUserEntity.class);
        sqlWrapper.eq(OrderUserEntity::getUId, uId)
                .eq(OrderUserEntity::getMId, mId)
                .eq(OrderUserEntity::getValid, "1")
                .orderBy(sqlWrapper.newOrderByModel(OrderUserEntity::getCreateDate, SqlWrapperConfig.Order.DESC));
        OrderUserEntity orderUserEntity = jpaUtil.one(sqlWrapper.getHql(), sqlWrapper.getParamsMap());
        return BeanUtils.copyProperties(orderUserEntity, OrderUserDto.class);
    }

    /**
     * 查询订单
     *
     * @param uId
     * @param orderCode
     * @return
     */
    public OrderEntity findOrderByCode(String uId, String orderCode) {
        SqlWrapper<OrderEntity> sqlWrapper = new SqlWrapper<>(OrderEntity.class);
        sqlWrapper.eq(OrderEntity::getCode, orderCode)
                .eq(OrderEntity::getUId, uId);
        return jpaUtil.one(sqlWrapper.getHql(), sqlWrapper.getParamsMap());
    }

    /**
     * 搜索订单
     * @param dateRange
     * @param orderNum
     * @return
     */
    public List<OrderOutDto> search(String dateRange, String orderNum) {
        SqlWrapper<OrderEntity> sqlWrapper = new SqlWrapper<>(OrderEntity.class);
        if (StringUtils.isNotEmpty(dateRange)) {
            Date startDate = DateUtil.string2date(dateRange.split(DateUtil.DATE_SPLIT_EMPTY)[0].concat(DateUtil.DATE_START_EMPTY), DateUtil.FORMAT_19);
            Date endDate = DateUtil.string2date(dateRange.split(DateUtil.DATE_SPLIT_EMPTY)[1].concat(DateUtil.DATE_END_EMPTY), DateUtil.FORMAT_19);
            sqlWrapper.ge(OrderEntity::getCreateDate, startDate)
                    .le(OrderEntity::getCreateDate, endDate);
        }else {
            sqlWrapper.ge(OrderEntity::getCreateDate, DateUtil.string2date(DateUtil.date2string(new Date(),DateUtil.FORMAT_8).concat(DateUtil.DATE_START_EMPTY),DateUtil.FORMAT_14))
                    .le(OrderEntity::getCreateDate, DateUtil.string2date(DateUtil.date2string(new Date(),DateUtil.FORMAT_8).concat(DateUtil.DATE_END_EMPTY),DateUtil.FORMAT_14));
        }
        sqlWrapper.eq(OrderEntity::getCode,orderNum,true);
        sqlWrapper.orderBy(sqlWrapper.newOrderByModel(OrderEntity::getCreateDate,SqlWrapperConfig.Order.DESC));
        List<OrderOutDto> orderOutDtoList = BeanUtils.batchModel(jpaUtil.wrapper(sqlWrapper),OrderOutDto.class);
        if(CollectionUtils.isEmpty(orderOutDtoList)) {
            return orderOutDtoList;
        }
        List<MerchantEntity> merchantEntityList = merchantService.getMerchantByIdList(orderOutDtoList.stream().map(OrderOutDto::getMId).collect(Collectors.toList()));
        Map<String,MerchantEntity> merchantEntityMap = merchantEntityList.stream().collect(Collectors.toMap(MerchantEntity::getId,m->m));
        orderOutDtoList.stream().forEach(o->{
            o.setMName(merchantEntityMap.get(o.getMId()).getName());
        });
        return orderOutDtoList;
    }

    /**
     * 更新订单
     * @param orderInDto
     */
    public void update(OrderInDto orderInDto) {

        DataEnum.OrderEnum orderEnum = DataEnum.OrderEnum.getOrderByCode(orderInDto.getNewStatus());
        if(orderEnum == null) {
            return;
        }
        Optional<OrderEntity> optional = orderRepository.findById(orderInDto.getOrderId());
        OrderEntity orderEntity = optional.get();
        orderRepository.save(orderEntity
                .setId(orderInDto.getOrderId())
                .setMsg(DataUtil.transOrderMsg(orderEntity.getMsg()
                        , AgentThreadLocal.get().getUserName().concat("将订单状态由")
                                .concat(DataEnum.OrderEnum.getMsgByCode(orderEntity.getStatus())).concat("修改为")
                                .concat(DataEnum.OrderEnum.getMsgByCode(orderInDto.getNewStatus()))))
                .setStatus(orderEnum.getCode())
                .setValid(orderEnum.getValid())
        );

    }

    public OrderOutDto findById(String orderId) {
        OrderOutDto orderOutDto = BeanUtils.copyProperties(orderRepository.findById(orderId).get(),OrderOutDto.class);
        MerchantEntity merchantEntity = merchantService.getMerchantById(orderOutDto.getMId());
        orderOutDto.setMName(merchantEntity.getName());
        return orderOutDto;
    }
}
