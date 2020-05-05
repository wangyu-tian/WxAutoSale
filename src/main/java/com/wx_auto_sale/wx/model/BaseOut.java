package com.wx_auto_sale.wx.model;

import lombok.Data;

/**
 * @Author wangyu
 * @Create: 2020/4/10 12:51 下午
 * @Description:出参数据
 */
@Data
public class BaseOut<T> {
    private String code = "000000";
    private String msg = "处理成功";
    private T data;

    public BaseOut() {

    }

    public BaseOut(T t) {
        data = t;
    }

    public BaseOut(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public BaseOut<T> error() {
        code = "999999";
        msg = "系统异常";
        return this;
    }
}
