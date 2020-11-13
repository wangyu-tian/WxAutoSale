package com.wx_auto_sale.utils;

import com.wx_auto_sale.constants.ErrorCode;
import com.wx_auto_sale.config.WxAutoException;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Author wangyu
 * @Create: 2020/4/12 1:02 下午
 * @Description:
 */
public class PermissionUtil {

    public static void isTrue(boolean isTrue,ErrorCode.IMessage iMessage){
        if(isTrue){
            throw new WxAutoException(iMessage.getCode(),iMessage.getMsg());
        }
    }

    public static void isNull(Object o,ErrorCode.IMessage iMessage){
        if(o == null || "".equals(o.toString())){
            throw new WxAutoException(iMessage.getCode(),iMessage.getMsg());
        }
    }

    /**
     * 参数校验失败
     * @param isFail
     * @param iMessage
     * @param params
     */
    public static void paramCheckFail(boolean isFail,ErrorCode.IMessage iMessage,String... params){
        if(isFail){
            StringBuilder messageSb = new StringBuilder(iMessage.getMsg());
            Stream.of(params).forEach(m->{messageSb.append(m).append(",");});
            throw new WxAutoException(iMessage.getCode(),messageSb.toString());
        }
    }
}
