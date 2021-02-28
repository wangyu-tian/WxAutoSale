package com.retrofit.dto.req;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Author wangyu
 * @Create: 2021/2/25 12:21 下午
 * @Description: 下订单通知req
 */
@Data
@Accessors(chain = true)
@Component
public class PlaceOrderReq {

    @Value("${feige.app_key}")
    private String app_key;

    private Data data;

    @Value("${feige.template_id}")
    private String template_id;

    @Value("${feige.secret}")
    private String secret;

    private String url = "https://www.tx.wtianyu.com:7899/view/order/{uid}/{code}";

    @lombok.Data
    @Accessors(chain = true)
    public static class Data {
        /**
         * 标题
         */
        private Detail first;
        /**
         * 订单编号
         */
        private Detail keyword1;
        /**
         * 订单详情
         */
        private Detail keyword2;
        /**
         * 订单金额
         */
        private Detail keyword3;
        /**
         * 订单时间
         */
        private Detail keyword4;
        /**
         * 备注
         */
        private Detail remark;
    }

    @lombok.Data
    @Accessors(chain = true)
    public static class Detail {
        private String value;
        private String color;
    }
}
