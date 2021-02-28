package com.retrofit;


import com.fasterxml.jackson.databind.type.MapType;
import com.github.lianjiatech.retrofit.spring.boot.annotation.RetrofitClient;
import com.retrofit.dto.req.PlaceOrderReq;
import com.retrofit.dto.resp.PlaceOrderResp;
import retrofit2.Response;
import retrofit2.http.*;

import java.util.Map;

import static com.github.lianjiatech.retrofit.spring.boot.interceptor.LogStrategy.BODY;

/**
 * @Author wangyu
 * @Create: 2021/2/24 6:20 下午
 * @Description: 捷易快信第三方推送服务 http://www.erpit.cn/
 */
@RetrofitClient(baseUrl = "http://jy.erpit.cn/",logStrategy = BODY)
public interface JyApi {

    /**
     * 捷易服务发送群组消息
     * PlaceOrderReq
     * @param placeOrderReqMap
     * @return
     */
    @FormUrlEncoded
    @POST("/api/message/send")
    Response<PlaceOrderResp> groupSend(@FieldMap Map<String,Object> placeOrderReqMap);
}
