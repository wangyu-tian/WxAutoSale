package com.wx_auto_sale.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.retrofit.dto.resp.BaseHttpResp;
import com.wx_auto_sale.config.ApplicationContextUtil;
import com.wx_auto_sale.config.ConstantConfig;
import com.wx_auto_sale.wx.model.api.WxUser;
import lombok.extern.slf4j.Slf4j;
import retrofit2.Response;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;


/**
 * @Author wangyu
 * @Create: 2020/5/24 9:28 下午
 * @Description:
 */
@Slf4j
public class RequestHttpUtils {


    /**
     * 通用http请求
     *
     * @param classT     内部方法的类class
     * @param methodName 执行的方法
     * @param reqs       参数
     * @return 返回数据实例
     */
    public static BaseHttpResp requestHttp(Class classT, String methodName, Object... reqs) {
        Response<BaseHttpResp> tResponse = null;
        try {
            Method method = Arrays.stream(classT.getMethods()).filter(m -> m.getName().equals(methodName) ? true : false).findFirst().get();
            tResponse = (Response<BaseHttpResp>) method.invoke(ApplicationContextUtil.getBean(classT), reqs);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return tResponse.body();
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
     * 获取用户openId
     *
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

}
