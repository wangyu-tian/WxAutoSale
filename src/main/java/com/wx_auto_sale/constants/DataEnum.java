package com.wx_auto_sale.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author wangyu
 * @Create: 2020/5/4 1:04 下午
 * @Description:
 */
public interface DataEnum {
    //1待付款，2已支付，3待派送，4派送中，5已结束，6已取消（客户取消），7已取消（商家取消）
    @Getter
    @AllArgsConstructor
    enum OrderEnum implements IMessage {
        STATUS_1("1", "已确认"),
        STATUS_2("2", "已支付"),
        STATUS_3("3", "待派送"),
        STATUS_4("4", "派送中"),
        STATUS_5("5", "已完成"),
        STATUS_6("6", "已取消（客户取消）"),
        STATUS_7("7", "已取消（商家取消）"),
        ;

        private String code;
        private String msg;

        public static String getMsgByCode(String code){
            for (OrderEnum orderEnum: OrderEnum.values()) {
                if(orderEnum.code.equals(code)){
                    return orderEnum.msg;
                }
            }
            return null;
        }

    }
    @Getter
    @AllArgsConstructor
    enum DiscountEnum implements IMessage {
        DISCOUNT_TYPE_1("1", "满m元减n元"),//商户折扣，已使用
        DISCOUNT_TYPE_2("2", "满m个送n个"),//商品折扣，未使用
        DISCOUNT_TYPE_3("3", "折扣m折限购n个"),//商品折扣，已使用
        DISCOUNT_TYPE_4("4", "满m元送n元优惠券"),//商户折扣，未使用
        DISCOUNT_TYPE_5("5", "满m元送n折优惠券"),//商户折扣，未使用
        ;
        private String code;
        private String msg;
    }

    interface IMessage {
        String getCode();

        String getMsg();
    }
}
