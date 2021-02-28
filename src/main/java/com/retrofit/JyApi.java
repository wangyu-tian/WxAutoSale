package com.retrofit;

import com.alibaba.fastjson.JSONObject;
import com.github.lianjiatech.retrofit.spring.boot.annotation.RetrofitClient;
import com.retrofit.dto.req.PlaceOrderReq;
import com.retrofit.dto.resp.PlaceOrderResp;
import com.wx_auto_sale.wx.model.BaseOut;
import retrofit2.Response;
import retrofit2.http.*;

import static com.github.lianjiatech.retrofit.spring.boot.interceptor.LogStrategy.BODY;
import static com.github.lianjiatech.retrofit.spring.boot.interceptor.LogStrategy.HEADERS;

/**
 * @Author wangyu
 * @Create: 2021/2/24 6:20 下午
 * @Description: 捷易快信第三方推送服务
 */
@RetrofitClient(baseUrl = "http://jy.erpit.cn/",logStrategy = BODY)
public interface JyApi {

    @POST("/api/message/send")
    Response<PlaceOrderResp> groupSend(@Body PlaceOrderReq placeOrderReq);
}
