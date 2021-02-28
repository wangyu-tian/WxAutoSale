package com.retrofit;

import com.github.lianjiatech.retrofit.spring.boot.annotation.RetrofitClient;
import com.retrofit.dto.req.WxSubscribeReq;
import com.retrofit.dto.resp.BaseHttpResp;
import com.retrofit.dto.resp.PlaceOrderResp;
import com.retrofit.dto.resp.WxTokenResp;
import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.http.*;

import java.util.Map;

import static com.github.lianjiatech.retrofit.spring.boot.interceptor.LogStrategy.BODY;


/**
 * 微信官方服务交互
 *
 * @author wangyu
 */
@RetrofitClient(baseUrl = "https://api.weixin.qq.com", logStrategy = BODY)
public interface WxApi {

    /**
     * 微信小程序获取用户token
     *
     * @param grantType
     * @param appId
     * @param secret
     * @return
     */
    @GET("/cgi-bin/token")
    Response<WxTokenResp> getToken(@Query("grant_type") String grantType, @Query("appid") String appId, @Query("secret") String secret);

    /**
     * 微信小程序用户下单通知（微信自带通知系统）
     * @param accessToken
     * @param wxSubscribeReq
     * @return
     */
    @POST("/cgi-bin/message/subscribe/send")
    Response<WxTokenResp> subscribeSend(@Query("access_token") String accessToken, @Body WxSubscribeReq wxSubscribeReq);

}
