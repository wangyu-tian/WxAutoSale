package com.wx_auto_sale.wx.model.entity;

import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @Author wangyu
 * @Create: 2020/4/18 10:19 上午
 * @Description:
 */
@Table(name = "order_info")
@Entity
@Data
@Accessors(chain = true)
@DynamicUpdate(true)
public class OrderEntity {

    @Id
    @GeneratedValue(generator = "idGenerator")
    @GenericGenerator(name="idGenerator", strategy="uuid")
    private String id;

    //请求流水号
    private String reqNo;

    //商品编号
    private String code;

    //商品列表展示名称
    private String listName;

    private String mId;

    private String uId;
    //json数据，订单数据细节。key是商品id,value是商品数量
    private String detail;

    //1待付款，2已支付，3待派送，4派送中，5已结束，6已取消（客户取消），7已取消（商家取消）
    private String status;

    //用户备注
    private String uMemo;

    //商家备注
    private String mMemo;

    //订单折扣之前的金额
    private String orderAmount;

    //应付金额，折扣之后的金额
    private String discountAmount;

    //实际已付金额
    private String payAmount;

    //派送费
    private String deliveryFee;

    //商家电话
    private String mPhone;

    //派送地址
    private String address;

    //用户联系电话
    private String phone;

    //期望配送时间
    private String expectDate;

    //系统消息，订单金额校验不通过等非正常情况导致订单一场
    private String msg;

    //用户姓名
    private String name;

    private String valid;

    //商家折扣信息
    private String discountMemo;

    @CreatedDate
    private Date createDate;
}
