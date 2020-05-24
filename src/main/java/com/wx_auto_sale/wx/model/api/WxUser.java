package com.wx_auto_sale.wx.model.api;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

import java.util.Map;

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

    private String access_token;

    private String openid;

    //微信消息推送
    private String touser;//用户openid
    private String template_id;//订阅消息模版id
    private String page = "pages/index/index";//默认跳到小程序首页
    private String miniprogram_state = "developer";//跳转页面适用于开发者小程序
    private Map<String, JSONObject> data;//推送文字

}

