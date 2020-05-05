package com.wx_auto_sale.config;

import com.wx_auto_sale.constants.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Author wangyu
 * @Create: 2020/4/12 1:25 下午
 * @Description: 项目自定义异常类
 */
@AllArgsConstructor
@Data
public class WxAutoException extends RuntimeException{
    private String code;
    private String msg;

    public WxAutoException(ErrorCode.IMessage iMessage){
        this.code = iMessage.getCode();
        this.msg = iMessage.getMsg();
    }
}
