package com.wx_auto_sale.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wx_auto_sale.config.ConstantConfig;
import com.wx_auto_sale.wx.model.api.WxUser;
import com.wx_auto_sale.wx.model.dto.response.OrderOutDto;
import com.wx_auto_sale.wx.model.entity.MerchantEntity;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author wangyu
 * @Create: 2020/5/24 9:28 下午
 * @Description:
 */
@Slf4j
public class WxUtil {

    public static JSONObject pushWxMessage(String accessToken, String openId,JSONObject jsonData) {
        //这里简单起见我们每次都获取最新的access_token（时间开发中，应该在access_token快过期时再重新获取）
        String url = "https://api.weixin.qq.com/cgi-bin/message/subscribe/send?access_token=" + accessToken;
        //拼接推送的模版
        WxUser wxUser = new WxUser();
        wxUser.setTouser(openId);//用户的openid（要发送给那个用户，通常这里应该动态传进来的）
        wxUser.setTemplate_id("PKV0Gk3Sa90lphIa2MtcqAzJJv8I7dNP0LjBJ5Nx3G4");//订阅消息模板id
        wxUser.setPage("pages/index/index");

        Map<String, JSONObject> params = new HashMap<>(3);
        //订单号
        params.put("character_string1", new JSONObject().fluentPut("value",jsonData.get("character_string1")));
        //商品名称
        params.put("thing4", new JSONObject().fluentPut("value",jsonData.get("thing4")));
        //数量
        params.put("number5", new JSONObject().fluentPut("value",jsonData.get("number5")));
        //金额
        params.put("amount2", new JSONObject().fluentPut("value",jsonData.get("amount2")));
        //备注
        params.put("thing3", new JSONObject().fluentPut("value",jsonData.get("thing3")));
        wxUser.setData(params);
        String responseData = HttpUtils.post(url,wxUser,String.class);
        return JSONObject.parseObject(responseData);
    }

    /**
     * 获取用户基本信息
     *
     * @param accessTokenJson
     * @param openId
     * @return
     */
    public static JSONObject getUserInfoByAccessToken(String accessTokenJson, String openId) {
        WxUser wxUser = new WxUser();
        wxUser.setAccess_token(accessTokenJson);
        wxUser.setOpenid(openId);
        log.info("请求微信用户ID：{}", JSON.toJSONString(wxUser));
        String responseData = HttpUtils.get(ConstantConfig.wxUserUrl, JSON.parseObject(JSON.toJSONString(wxUser)));
        log.info("微信鉴权接口响应:{}", responseData);
        return JSON.parseObject(responseData);
    }

    /**
     * 获取用户鉴权token
     * @param appid
     * @param secret
     * @return
     */
    public static JSONObject getAccessToken(String appid,String secret) {
        WxUser wxUser = new WxUser();
        wxUser.setAppid(appid);
        wxUser.setGrant_type(ConstantConfig.wxTokenGrantType);
        wxUser.setSecret(secret);
        log.info("请求微信用户ID：{}", JSON.toJSONString(wxUser));
        String responseData = HttpUtils.get(ConstantConfig.wxTokenUrl, JSON.parseObject(JSON.toJSONString(wxUser)));
        log.info("微信鉴权接口响应:{}", responseData);
        return JSON.parseObject(responseData);
    }

    /**
     * 获取用户openId
     * @param wxCode
     * @param appid
     * @param secret
     * @return
     */
    public static JSONObject getOpenIdByWxData(String wxCode, String appid, String secret) {
        WxUser wxUser = new WxUser();
        wxUser.setAppid(appid);
        wxUser.setGrant_type(ConstantConfig.wxGrantType);
        wxUser.setSecret(secret);
        wxUser.setJs_code(wxCode);
        log.info("请求微信用户ID：{}", JSON.toJSONString(wxUser));
        String responseData = HttpUtils.post(ConstantConfig.wxApiUrl, JSON.parseObject(JSON.toJSONString(wxUser)));
        log.info("微信鉴权接口响应:{}", responseData);
        return JSON.parseObject(responseData);
    }

    /**
     * 消息推送
     * @param orderOutDto
     */
    public static void pushWxMessage(OrderOutDto orderOutDto) {

    }
}
