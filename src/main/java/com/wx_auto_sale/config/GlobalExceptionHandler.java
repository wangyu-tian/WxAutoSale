package com.wx_auto_sale.config;

import com.wx_auto_sale.utils.DataUtil;
import com.wx_auto_sale.wx.model.BaseOut;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author wangyu
 * @Create: 2020/4/11 9:12 上午
 * @Description:
 */
@ControllerAdvice
@Slf4j
@ResponseBody
public class GlobalExceptionHandler {


    @ExceptionHandler(WxAutoException.class)
    public BaseOut WxAutoException(WxAutoException e,HttpServletRequest request) {
        log.error("WxAutoException:{}", DataUtil.getErrorInfoFromException(e));
        return new BaseOut(e.getCode(),e.getMsg());
    }

    @ExceptionHandler(Exception.class)
    public BaseOut Exception(Exception e,HttpServletRequest request) {
       log.error("Exception:{}",DataUtil.getErrorInfoFromException(e));
        return new BaseOut().error();
    }
}
