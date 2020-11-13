package com.wx_auto_sale.config;

import com.wx_auto_sale.utils.DataUtil;
import com.wx_auto_sale.wx.model.BaseOut;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;

/**
 * @Author wangyu
 * @Create: 2020/4/11 9:12 上午
 * @Description: 全局异常处理
 */
@ControllerAdvice
@Slf4j
@ResponseBody
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public BaseOut methodArgumentNotValidException(MethodArgumentNotValidException e,HttpServletRequest request) {
        log.error("MethodArgumentNotValidException:{}", DataUtil.getErrorInfoFromException(e));
        BindingResult bindingResult = e.getBindingResult();
        String errorMesssage = "校验失败:";
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            errorMesssage += fieldError.getDefaultMessage() + ", ";
        }
        return new BaseOut("9000002",errorMesssage);
    }

    @ExceptionHandler(WxAutoException.class)
    public BaseOut wxAutoException(WxAutoException e,HttpServletRequest request) {
        log.error("WxAutoException:{}", DataUtil.getErrorInfoFromException(e));
        return new BaseOut(e.getCode(),e.getMsg());
    }

    @ExceptionHandler(Exception.class)
    public BaseOut exception(Exception e,HttpServletRequest request) {
       log.error("Exception:{}",DataUtil.getErrorInfoFromException(e));
        return new BaseOut().error();
    }
}
