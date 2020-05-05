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
public class DiscountOutDto {

    private String id;
    //折扣类型1：满m元减n元；2：满m个送n个；3：折扣m折限购n个；4：满m元送n元优惠券;5:满m元送n折优惠券
    private String type;
    //未知变量m
    private String m;
    //未知变量n
    private String n;
    private String valid;
    //优惠券使用变量c_m
    private String cM;
    //优惠券使用折扣比例p
    private String cP;
    //优惠券类型1:满c_m可减c_n;2:无门槛减c_n;3:无门槛c_p折优惠券
    private String cType;

    private String memo;

    private String showDiscount;

    //折扣价格
    private String discountPrice;

}