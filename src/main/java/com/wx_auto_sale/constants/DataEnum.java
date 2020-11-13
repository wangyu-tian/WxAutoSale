package com.wx_auto_sale.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author wangyu
 * @Create: 2020/5/4 1:04 下午
 * @Description:
 */
public interface DataEnum {


    @Getter
    @AllArgsConstructor
    enum BackPageEnum implements IMessage {
        /**
         * 首页子页面
         */
        INDEX("shouye","首页子页面"),
        /**
         * 订单列表子页面
         */
        ORDER("order","订单列表子页面"),
        /**
         * 订单详情子页面
         */
        ORDER_DETAIL("orderDetail","订单详情子页面"),
        ;

        private String code;
        private String msg;
    }

    /**
     * 1待付款，2已支付，3待派送，4派送中，5已结束，6已取消（客户取消），7已取消（商家取消）
     */
    @Getter
    @AllArgsConstructor
    enum OrderEnum implements IMessage {
        /**
         *已确认(待支付)
         */
        STATUS_1("1", "已确认(待支付)","1"),
        /**
         * 已支付
         */
        STATUS_2("2", "已支付","1"),
        /**
         * 待派送
         */
        STATUS_3("3", "待派送","1"),
        /**
         * 派送中
         */
        STATUS_4("4", "派送中","1"),
        /**
         * 已完成
         */
        STATUS_5("5", "已完成","0"),
        /**
         * 已取消（客户取消）
         */
        STATUS_6("6", "已取消（客户取消）","0"),
        /**
         * 已取消（商家取消）
         */
        STATUS_7("7", "已取消（商家取消）","0"),
        ;

        private String code;
        private String msg;
        private String valid;

        public static String getMsgByCode(String code){
            for (OrderEnum orderEnum: OrderEnum.values()) {
                if(orderEnum.code.equals(code)){
                    return orderEnum.msg;
                }
            }
            return null;
        }

        public static OrderEnum getOrderByCode(String code){
            for (OrderEnum orderEnum: OrderEnum.values()) {
                if(orderEnum.code.equals(code)){
                    return orderEnum;
                }
            }
            return null;
        }

        public static Map<String,String> toMap(){
            Map<String,String> dataMap = new HashMap<>();
            for (OrderEnum orderEnum: OrderEnum.values()) {
                dataMap.put(orderEnum.getCode(),orderEnum.getMsg());
            }
            return dataMap;
        }
    }
    @Getter
    @AllArgsConstructor
    enum DiscountEnum implements IMessage {
        /**
         * 满m元减n元
         * 商品折扣，已使用
         */
        DISCOUNT_TYPE_1("1", "满m元减n元"),
        /**
         * 满m个送n个
         * 商户折扣，未使用
         */
        DISCOUNT_TYPE_2("2", "满m个送n个"),
        /**
         * 折扣m折限购n个
         * 商品折扣，已使用
         */
        DISCOUNT_TYPE_3("3", "折扣m折限购n个"),
        /**
         * 满m元送n元优惠券
         * 商户折扣，未使用
         */
        DISCOUNT_TYPE_4("4", "满m元送n元优惠券"),
        /**
         * 满m元送n折优惠券
         * 商户折扣，未使用
         */
        DISCOUNT_TYPE_5("5", "满m元送n折优惠券"),
        ;
        private String code;
        private String msg;
    }

    interface IMessage {
        String getCode();

        String getMsg();
    }
}
