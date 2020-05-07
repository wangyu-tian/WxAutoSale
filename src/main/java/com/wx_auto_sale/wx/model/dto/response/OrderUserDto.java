package com.wx_auto_sale.wx.model.dto.response;

import lombok.Data;

/**
 * @Author wangyu
 * @Create: 2020/5/7 21:25 下午
 * @Description:
 */
@Data
public class OrderUserDto  extends BaseOutDto {

    private String id;

    private String address;

    private String phone;

    private String name;

    private String memo;


    public void setId(String id){
        super._id = id;
        this.id = null;
    }

}