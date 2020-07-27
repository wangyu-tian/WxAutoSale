package com.wx_auto_sale.wx.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Author wangyu
 * @Create: 2020/7/27 10:22 下午
 * @Description: 后台管理系统
 */

@Controller
@RequestMapping("back")
@Slf4j
public class BackController {

    /**
     * 登陆
     * @param userName
     * @param password
     * @return
     */
    @RequestMapping(value = "/login")
    public ModelAndView viewOrderInfo(@RequestParam(value = "userName",required = false) String userName,
                                      @RequestParam(value = "password",required = false) String password) {

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("login");

        return modelAndView;
    }
}
