package com.retrofit.dto.req;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @Author wangyu
 * @Create: 2020/4/10 8:50 下午
 * @Description: 获取微信用户WID
 */
@Data
@Accessors(chain = true)
@Component
public class WxSubscribeReq {

    private String access_token;

    /**
     * 用户openid
     */
    private String touser;
    /**
     * 订阅消息模版id
     */
    private String template_id = "PKV0Gk3Sa90lphIa2MtcqAzJJv8I7dNP0LjBJ5Nx3G4";
    /**
     * 默认跳到小程序首页
     */
    private String page = "pages/order/index?";
    /**
     * 跳转页面适用于开发者小程序
     */
    private String miniprogram_state = "developer";
    /**
     * 推送文字
     */
    private Map<String, JSONObject> data;

}

