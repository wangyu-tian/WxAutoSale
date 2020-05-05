package com.wx_auto_sale.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author wangyu
 * @Create: 2020/4/12 1:04 下午
 * @Description:
 */
public interface ErrorCode {

    @Getter
    @AllArgsConstructor
    enum OrderEnum implements IMessage{
        REQUEST_NO_IS_BLANK("300001", "请求流水号不能为空"),
        REQUEST_NO_IS_REPEAT("300002", "重复提交"),
        AMOUNT_IS_NOT_LEGAL("300003", "订单金额校验不合法"),
        GOOD_IS_NOT_LEGAL("300004", "商品信息不合法"),
        GOOD_DISCOUNT_IS_ERROR("300005", "折扣商品异常"),
        ;

        private String code;
        private String msg;

    }

    @Getter
    @AllArgsConstructor
    enum UserEnum implements IMessage{
        WX_CODE_ERROR("100001", "用户微信授权失败"),
        WX_MERCHANT_NOT_EXIST("100011", "商户不存在"),
        WX_STATUS_ERROR("100002", "用户状态异常"),
        WX_USER_IS_NOT_EXIST("100003", "用户不存在"),
        ;

        private String code;
        private String msg;

    }

    @Getter
    @AllArgsConstructor
    enum ParamEnum implements IMessage{
        PARAM_CHECK_ERROR("200001", "参数校验失败"),
        ;

        private String code;
        private String msg;

    }

    interface IMessage{
         String getCode();
         String getMsg();
    }
}
