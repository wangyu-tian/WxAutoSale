package com.wx_auto_sale.wx.model.api;

import lombok.Data;

/**
 * @Author wangyu
 * @Create: 2020/4/10 8:50 下午
 * @Description: 获取微信用户WID
 */
@Data
public class WxUser {

    private String appid;

    private String secret;

    private String js_code;

    private String grant_type;
}
