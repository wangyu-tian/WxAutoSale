package com.wx_auto_sale.wx.model.dto.response;

import lombok.Data;

/**
 * @Author wangyu
 * @Create: 2020/4/18 10:34 上午
 * @Description:
 */
@Data
public class CategoryOutDto extends BaseOutDto {

    private String id;

    private String code;

    private String mId;

    private String name;

    public void setId(String id){
        super._id = id;
        this.id = null;
    }

}

