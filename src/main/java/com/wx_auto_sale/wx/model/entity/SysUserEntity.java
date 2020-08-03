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
 * @Create: 2020/7/28 09:56 下午
 * @Description:
 */

@Table(name = "sys_user")
@Entity
@Data
public class SysUserEntity {

    @Id
    @GeneratedValue(generator = "idGenerator")
    @GenericGenerator(name="idGenerator", strategy="uuid")
    private String id;
    //'微信用户ID'
    private String userName;
    //'密码'
    private String password;
    //'1为正常，2为异常'
    private String status;

    private String sysToken;

    private String tokenEndTime;

    //'创建时间'
    @CreatedDate
    private Date createDate;

    private Date updateDate;

}