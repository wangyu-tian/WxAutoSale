package com.wx_auto_sale.wx.model.api;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @Author wangyu
 * @Create: 2020/4/10 8:50 下午
 * @Description: http://u.ifeige.cn/
 */
@Data
@Component
public class WxOrderNotify {

    @Value("${feige.secret}")
    private String secret;

    @Value("${feige.app_key}")
    private String app_key;

    @Value("${feige.template_id}")
    private String template_id;

    @Value("${feige.http_url}")
    private String httpUrl;

    private String url;

    private Map<String, JSONObject> data;//推送文字

}

