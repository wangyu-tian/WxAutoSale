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
 * @Create: 2020/4/18 10:21 上午
 * @Description:
 */
@Table(name = "commodity")
@Entity
@Data
public class CommodityEntity {

    @Id
    @GeneratedValue(generator = "idGenerator")
    @GenericGenerator(name="idGenerator", strategy="uuid")
    private String id;

    private String mId;

    private String cCode;

    private String name;

    private String price;

    private int count;

    private String desc;

    private String dId;

    private String imageUrl;

    private int sort;

    private String valid;

    @CreatedDate
    private Date createDate;
}
