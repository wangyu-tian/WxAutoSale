package com.wx_auto_sale.wx.model.dto.response;

import lombok.Data;

/**
 * @Author wangyu
 * @Create: 2020/4/18 10:34 上午
 * @Description:
 */
@Data
public class CommodityOutDto extends BaseOutDto {

    private String id;

    private String mId;

    private String cCode;

    private String name;

    private String price;

    private int count;

    private String desc;

    private String dId;

    private String imageUrl;

    private DiscountOutDto discountOutDto;


    public void setId(String id){
        super._id = id;
        this.id = null;
    }

}

