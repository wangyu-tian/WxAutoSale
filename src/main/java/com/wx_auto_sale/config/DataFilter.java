package com.wx_auto_sale.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.connector.RequestFacade;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter(filterName = "dataFilter", urlPatterns = {"/*"})
@Slf4j
public class DataFilter extends OncePerRequestFilter {

    private final int maxLength = 2048;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) {
        try {
            RequestWrapper requestWrapper = new RequestWrapper(httpServletRequest);
            String body = requestWrapper.getBody();
            body = body.length() > maxLength ? body.substring(0,maxLength):body;
            String url = httpServletRequest.getRequestURI();
            log.info("请求地址：{},请求参数：{}",url,body);
            filterChain.doFilter(requestWrapper, httpServletResponse);
        } catch (Exception e) {
            log.error("doFilter is error:", e);
        }
    }
}
