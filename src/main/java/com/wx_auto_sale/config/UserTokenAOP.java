package com.wx_auto_sale.config;

import com.alibaba.fastjson.JSON;
import com.wx_auto_sale.constants.WConstants;
import com.wx_auto_sale.utils.PermissionUtil;
import com.wx_auto_sale.wx.model.dto.AgentThreadLocal;
import com.wx_auto_sale.wx.model.entity.SysUserEntity;
import com.wx_auto_sale.wx.service.SysUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.ExceptionUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Arrays;

import static com.wx_auto_sale.constants.ErrorCode.SysEnum.TOKEN_CHECK_FAIL;

/**
 * @Author wangyu
 * @Create: 2020/7/29 10:42 下午
 * @Description:
 */
@Component
@Aspect
@Slf4j
public class UserTokenAOP {

    @Autowired
    private SysUserService sysUserService;
    /**
     * 客户信息
     */
    @Pointcut("execution(* com.wx_auto_sale.wx.controller.view..*.*(..))" +
            "&&!execution(* com.wx_auto_sale.wx.controller.view.BackController.redirect(..)) " +
            "&&!execution(* com.wx_auto_sale.wx.controller.view.BackController.login(..)) ")
    public void pointCut(){}

    @AfterThrowing(value = "pointCut()",throwing = "object")
    public void exception(Exception object) {
        log.info("UserTokenAOP_exception_args:", JSON.toJSONString(object));
    }

    @Around(value = "pointCut()")
    public ModelAndView around(ProceedingJoinPoint pjp){
        log.info("UserTokenAOP_before_args:{}", JSON.toJSONString(pjp.getArgs()));
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String sessionId = request.getSession().getId();
        sysUserService.findByToken(sessionId);
        if(AgentThreadLocal.get() != null){
            try {
                ModelAndView modelAndView = (ModelAndView) pjp.proceed();
                modelAndView.addObject("menuPermission", WConstants.permissionMap);
                modelAndView.addObject("userName", AgentThreadLocal.get().getUserName());
                return modelAndView;
            } catch (Throwable e) {
                log.error("UserTokenAOP_exception_error:e",e);
            }
        }
        AgentThreadLocal.remove();
        log.info("UserTokenAOP_after_args:{}", JSON.toJSONString(pjp.getArgs()));
        log.info("UserTokenAOP_exception_error_Session已超时，正在跳转回登录页面");
        return new ModelAndView("/back/login");
    }

}
