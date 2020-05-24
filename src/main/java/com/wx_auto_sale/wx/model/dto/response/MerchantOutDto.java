package com.wx_auto_sale.wx.model.dto.response;

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
 * @Create: 2020/4/10 12:25 下午
 * @Description:
 */

@Data
public class MerchantOutDto extends BaseOutDto{

    private String id;

    //商户头像
    private String image;

    //商户名称
    private String name;

    //商铺联系电话
    private String phone;

    //商铺地址
    private String address;

    //维度
    private String addressLatitude;

    //经度
    private String addressLongitude;

    private String dId;

    private Date dDateStart;

    private Date dDateEnd;

    private String appid;

    private String secret;

    private DiscountOutDto discountOutDto;

    public void setId(String id){
        super._id = id;
        this.id = null;
    }

}