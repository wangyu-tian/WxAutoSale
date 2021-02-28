package com.retrofit.dto.resp;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.beans.factory.annotation.Value;

/**
 * @Author wangyu
 * @Create: 2021/2/25 12:21 下午
 * @Description: 下订单通知resp
 */
@Data
@Accessors(chain = true)
public class PlaceOrderResp {

   private String code;

   private String msg;

//   private JSONObject data;
}
