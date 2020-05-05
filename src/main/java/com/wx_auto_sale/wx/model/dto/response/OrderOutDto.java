package com.wx_auto_sale.wx.model.dto.response;

import com.alibaba.fastjson.JSONObject;
import com.wx_auto_sale.constants.DataEnum;
import lombok.Data;
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
@Data
public class OrderOutDto  extends BaseOutDto {

    private String id;

    private String mId;

    //请求流水号
    private String reqNo;

    private String uId;
    //json数据，订单数据细节
    private String detail;

    //1已确认，2已支付，3待派送，4派送中，5已结束，6已取消（客户取消），7已取消（商家取消）
    private String status;

    private String statusName;

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

    private Date createDate;

    //商品列表展示名称
    private String listName;

    //商品编号
    private String code;

    private String count;

    //商家折扣信息
    private String discountMemo;

    public void setDetail(String detail) {
        this.detail = detail;
        count = "共"+ JSONObject.parseObject(detail).size()+"个商品";
    }

    public void setStatus(String status) {
        this.status = status;
        this.statusName = DataEnum.OrderEnum.getMsgByCode(status);
    }

    public void setId(String id){
        super._id = id;
        this.id = null;
    }
}
