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
    public static String wxUserUrl;
    //微信用户校验type
    public static String wxgrantType;

    @Value("${wx.api-url}")
    public void setWxUserUrl(String wxUserUrl) {
        ConstantConfig.wxUserUrl = wxUserUrl;
    }

    @Value("${wx.grant_type}")
    public  void setWxgrantType(String wxgrantType) {
        ConstantConfig.wxgrantType = wxgrantType;
    }
}
