package com.wx_auto_sale.wx.model.dto.response;

import com.wx_auto_sale.wx.model.BaseOut;
import lombok.Data;

import java.util.Date;

/**
 * @Author wangyu
 * @Create: 2020/4/10 12:56 下午
 * @Description:
 */
@Data
public class UserOutDto extends BaseOutDto {

    private String id;
    private String wId;
    //'头像'
    private String image;
    //'姓名'
    private String name;
    //'电话'
    private String phone;
    //'收货地址，多个用；分隔'
    private String address;

    public void setId(String id){
        super._id = id;
        this.id = null;
    }
}
