package com.wx_auto_sale.wx.model.entity;

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
 * @Create: 2020/5/7 21:25 下午
 * @Description:
 */

@Table(name = "order_user_info")
@Entity
@Data
public class OrderUserEntity {

    @Id
    @GeneratedValue(generator = "idGenerator")
    @GenericGenerator(name = "idGenerator", strategy = "uuid")
    private String id;
    //'商户ID'
    private String mId;
    //'用户ID'
    private String uId;
    //订单ID
    private String orderId;

    private String address;

    private String phone;

    private String name;

    private String memo;

    private String valid;
    //'创建时间'
    @CreatedDate
    private Date createDate;

}