package com.wx_auto_sale.wx.controller.view;

import com.wrapper.util.StringUtils;
import com.wx_auto_sale.constants.DataEnum;
import com.wx_auto_sale.constants.WConstants;
import com.wx_auto_sale.utils.DateUtil;
import com.wx_auto_sale.wx.model.dto.AgentThreadLocal;
import com.wx_auto_sale.wx.model.entity.SysUserEntity;
import com.wx_auto_sale.wx.service.OrderService;
import com.wx_auto_sale.wx.service.SysUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Calendar;
import java.util.Date;

/**
 * @Author wangyu
 * @Create: 2020/7/27 10:22 下午
 * @Description: 后台管理系统
 */

@Controller
@RequestMapping("back")
@Slf4j
public class BackController {

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private OrderService orderService;
    /**
     * 首页
     * @return
     */
    @RequestMapping(value = "/index")
    public ModelAndView index(@RequestParam(value = "page",required = false) String page) {
        ModelAndView modelAndView = new ModelAndView();
        page = StringUtils.isEmpty(page)? DataEnum.BackPageEnum.INDEX.getCode():page;
        modelAndView.addObject("page",page);
        if(DataEnum.BackPageEnum.ORDER.getCode().equals(page)
            ||DataEnum.BackPageEnum.INDEX.getCode().equals(page)){
            String dateRange = DateUtil.date2string(DateUtil.addDate(new Date(), Calendar.DATE,-7),DateUtil.FORMAT_10)
                    +" - "+DateUtil.date2string(DateUtil.now(),DateUtil.FORMAT_10);
            modelAndView.addObject("dateRange",dateRange);
            modelAndView.setViewName("redirect:/back/order/search");
        }else {
            modelAndView.setViewName("back/index");
        }
        return modelAndView;
    }

    /**
     * 重定向跳转
     * @return
     */
    @RequestMapping(value = "/redirect")
    public ModelAndView redirect(@RequestParam(value = "loginErrorMsg",required = false) String loginErrorMsg,
                                 @RequestParam(value = "url",required = false) String url) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("loginErrorMsg",loginErrorMsg);
        modelAndView.setViewName("redirect:".concat(url));

        return modelAndView;
    }

    /**
     * 登陆
     * @param userName
     * @param password
     * @return
     */
    @RequestMapping(value = "/login")
    public ModelAndView login(@RequestParam(value = "userName",required = false) String userName,
                              @RequestParam(value = "password",required = false) String password,
                              HttpServletRequest request, HttpServletResponse response) {
        log.info("BackController_login_userName:{},password:{}",userName,password);
        boolean loginFirst = StringUtils.isEmpty(userName)&&StringUtils.isEmpty(password);
        String url = "";
        ModelAndView modelAndView = new ModelAndView();

        SysUserEntity loginUser = sysUserService.login(userName,password,request.getSession().getId());
        if(loginUser != null){
            modelAndView.addObject("userName",loginUser.getUserName());
            url = "/back/redirect?url=/back/index";
            modelAndView.setViewName(loginFirst ? url : "redirect:".concat(url));
            return modelAndView;
        }else if(StringUtils.isNotEmpty(userName)&&StringUtils.isNotEmpty(password)){
            loginFirst = true;
            modelAndView.addObject("loginErrorMsg","用户名或密码错误");
            url = "back/login";
        }else {
            url = "back/login";
        }
        modelAndView.setViewName(loginFirst ? url : "redirect:".concat(url));

        return modelAndView;
    }
}
