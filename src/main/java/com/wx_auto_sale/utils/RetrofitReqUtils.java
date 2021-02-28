package com.wx_auto_sale.utils;

import com.alibaba.fastjson.JSONObject;
import com.retrofit.dto.req.WxSubscribeReq;
import com.wx_auto_sale.wx.model.entity.OrderEntity;

import java.util.HashMap;
import java.util.Map;

/**
 * request数据封装工具类
 *
 * @author wangyu
 */
public class RetrofitReqUtils {

    /**
     * 数据封装
     * WxApi.subscribeSend 微信通知
     *
     * @param openId      用户openId,要发送给那个用户
     * @param orderEntity 推送数据
     * @param url         跳转地址
     * @return
     */
    public static WxSubscribeReq transferWxSubscribeReq(String openId, OrderEntity orderEntity, String url) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("character_string1", orderEntity.getCode());
        jsonObject.put("thing4", DataUtil.textTransfer(orderEntity.getListName()));
        jsonObject.put("number5", JSONObject.parseObject(orderEntity.getDetail()).size());
        jsonObject.put("amount2", orderEntity.getDiscountAmount());
        jsonObject.put("thing3", orderEntity.getUMemo());

        //拼接推送的模版
        WxSubscribeReq wxSubscribeReq = new WxSubscribeReq();
        wxSubscribeReq.setTouser(openId);
        wxSubscribeReq.setPage(wxSubscribeReq.getPage() + url);

        Map<String, JSONObject> params = new HashMap<>();
        //订单号
        params.put("character_string1", new JSONObject().fluentPut("value", jsonObject.get("character_string1")));
        //商品名称
        params.put("thing4", new JSONObject().fluentPut("value", jsonObject.get("thing4")));
        //数量
        params.put("number5", new JSONObject().fluentPut("value", jsonObject.get("number5")));
        //金额
        params.put("amount2", new JSONObject().fluentPut("value", jsonObject.get("amount2")));
        //备注
        params.put("thing3", new JSONObject().fluentPut("value", jsonObject.get("thing3")));
        wxSubscribeReq.setData(params);
        return wxSubscribeReq;
    }
}
