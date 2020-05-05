package com.wx_auto_sale.wx.model.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @Author wangyu
 * @Create: 2020/4/10 12:25 下午
 * @Description:
 */

@Table(name = "user")
@Entity
@Data
public class UserEntity {

    @Id
    @GeneratedValue(generator = "idGenerator")
    @GenericGenerator(name="idGenerator", strategy="uuid")
    private String id;
    //'微信用户ID'
    private String wId;
    //'商户ID'
    private String mId;
    //'头像'
    private String image;
    //'姓名'
    private String name;
    //'电话'
    private String phone;
    //'收货地址，多个用；分隔'
    private String address;
    //'1为正常，2为异常'
    private String status;
    //'状态备注'
    private String statusMemo;
    //'创建时间'
    @CreatedDate
    private Date createDate;

}