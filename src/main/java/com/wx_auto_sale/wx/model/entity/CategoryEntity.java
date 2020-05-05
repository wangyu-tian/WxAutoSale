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
 * @Create: 2020/4/18 10:19 上午
 * @Description:
 */
@Table(name = "category")
@Entity
@Data
public class CategoryEntity {

    @Id
    @GeneratedValue(generator = "idGenerator")
    @GenericGenerator(name="idGenerator", strategy="uuid")
    private String id;

    private String code;

    private String mId;

    private String name;

    private int sort;

    private String valid;

    @CreatedDate
    private Date createDate;
}
