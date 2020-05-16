package com.wx_auto_sale.config;

import com.wrapper.util.StringUtils;
import com.wx_auto_sale.wx.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

import java.util.Date;

import static com.wx_auto_sale.constants.ErrorCode.SysEnum.TOKEN_CHECK_FAIL;

@Slf4j
@Component
public class BaseInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler) throws Exception {
        UserService userService = ApplicationContextUtil.getBean(UserService.class);
        String token = httpServletRequest.getHeader("token");
        log.info("header token:{}",token);
        //校验规则为：前13位为时间戳，后32位为用户id。时间戳与当前时间差不能超过一分钟
        if(StringUtils.isEmpty(token) || token.length() != 45
                    || (new Date().getTime() - Long.valueOf(token.substring(0,13))) > 60000
                    || userService.findById(token.substring(13)) == null){
            throw new WxAutoException(TOKEN_CHECK_FAIL);
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        super.afterCompletion(request, response, handler, ex);
    }
}
