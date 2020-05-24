package com.wx_auto_sale.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Author wangyu
 * @Create: 2020/4/10 8:37 下午
 * @Description:
 */
@Component
@Data
public class ConstantConfig {

    //微信用户校验url
    public static String wxApiUrl;
    //微信用户校验type
    public static String wxGrantType;

    //微信鉴权token url
    public static String wxTokenUrl;
    //微信用户校验type
    public static String wxTokenGrantType;

    //微信用户获取url
    public static String wxUserUrl;

    @Value("${wx.user-url}")
    public  void setWxUserUrl(String wxUserUrl) {
        ConstantConfig.wxUserUrl = wxUserUrl;
    }

    @Value("${wx.token-url}")
    public  void setWxTokenUrl(String wxTokenUrl) {
        ConstantConfig.wxTokenUrl = wxTokenUrl;
    }

    @Value("${wx.token-grant_type}")
    public  void setWxTokenGrantType(String wxTokenGrantType) {
        ConstantConfig.wxTokenGrantType = wxTokenGrantType;
    }

    @Value("${wx.api-url}")
    public  void setWxApiUrl(String wxApiUrl) {
        ConstantConfig.wxApiUrl = wxApiUrl;
    }

    @Value("${wx.grant_type}")
    public  void setWxGrantType(String wxGrantType) {
        ConstantConfig.wxGrantType = wxGrantType;
    }
}
