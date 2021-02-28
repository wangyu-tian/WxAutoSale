package com.retrofit;

import com.alibaba.fastjson.JSONObject;
import com.github.lianjiatech.retrofit.spring.boot.annotation.OkHttpClientBuilder;
import com.github.lianjiatech.retrofit.spring.boot.annotation.RetrofitClient;

import com.wx_auto_sale.wx.model.BaseOut;
import kotlin.Result;
import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

import java.util.concurrent.TimeUnit;

/**
 * @Author wangyu
 * @Create: 2021/2/24 6:20 下午
 * @Description:
 * 捷易快信第三方推送服务
 */
@RetrofitClient(baseUrl = "http://jy.erpit.cn/",poolName = "pool1")
public interface JyApiTest {

    @OkHttpClientBuilder
    static OkHttpClient.Builder okhttpClientBuilder() {
        return new OkHttpClient.Builder()
                .connectTimeout(3, TimeUnit.SECONDS)
                .readTimeout(50, TimeUnit.SECONDS)
                .writeTimeout(50, TimeUnit.SECONDS)
                .connectionPool(new ConnectionPool())
                ;

    }


    @POST("/api/message/send")
    Response<ResponseBody> groupSend(@Body JSONObject jsonObject);
}
