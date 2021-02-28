package com.wx_auto_sale.utils;

import com.alibaba.fastjson.JSONObject;
import com.wx_auto_sale.config.ApplicationContextUtil;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * @Author wangyu
 * @Create: 2020/5/24 10:56 上午
 * @Description:
 */
public class HttpUtils {

    public static final String FORM_URLENCODED = "application/x-www-form-urlencoded";

    //post请求
    public static String post(String url, JSONObject jsonData){
        RestTemplate restTemplate = ApplicationContextUtil.getBean(RestTemplate.class);
        if(jsonData == null) {
            jsonData = new JSONObject();
        }
        MultiValueMap<String, Object> postParameters = new LinkedMultiValueMap<>();
        postParameters.setAll(JSONObject.toJavaObject(jsonData, Map.class));
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", FORM_URLENCODED);
        HttpEntity<MultiValueMap<String, Object>> r = new HttpEntity(postParameters, headers);
        String responseData = restTemplate.postForObject(url, r, String.class);
        return responseData;
    }

    //get请求
    public static String get(String url, JSONObject jsonData){
        RestTemplate restTemplate = ApplicationContextUtil.getBean(RestTemplate.class);
        String responseData = restTemplate.getForObject(url,String.class,JSONObject.toJavaObject(jsonData, Map.class));
        return responseData;
    }

    public static String post(String url, Object request, Class<String> stringClass) {
        RestTemplate restTemplate = ApplicationContextUtil.getBean(RestTemplate.class);
        ResponseEntity<String> responseEntity =
                restTemplate.postForEntity(url, request, String.class);
        return responseEntity.getBody();
    }

    public  static <T> String postJson(String url, T t){
        RestTemplate restTemplate = ApplicationContextUtil.getBean(RestTemplate.class);
        //创建请求头
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<T> entity = new HttpEntity<T>(t, headers);
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, entity, String.class);
        return responseEntity.getBody();//{"msg":"调用成功！","code":1}
    }
}
