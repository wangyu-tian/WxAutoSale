package com.wx_auto_sale.wx.model.dto.request;

import lombok.Data;


/**
 * @Author wangyu
 * @Create: 2020/4/18 10:19 上午
 * @Description:
 */
@Data
public class OrderInDto{

    //请求流水号
    private String reqNo;

    //json数据，订单数据细节
    private String detail;

    //用户备注
    private String uMemo;

    //订单折扣之前的金额
    private String orderAmount;

    //应付金额，折扣之后的金额
    private String discountAmount;

    //派送费
    private String deliveryFee;

    //派送地址
    private String address;

    //用户联系电话
    private String phone;

    //期望配送时间
    private String expectDate;

    //用户姓名
    private String name;


}
