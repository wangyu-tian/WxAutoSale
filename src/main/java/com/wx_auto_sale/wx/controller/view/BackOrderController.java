package com.wx_auto_sale.wx.controller.view;

import com.wrapper.util.StringUtils;
import com.wx_auto_sale.constants.WConstants;
import com.wx_auto_sale.wx.model.dto.AgentThreadLocal;
import com.wx_auto_sale.wx.model.entity.SysUserEntity;
import com.wx_auto_sale.wx.service.SysUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author wangyu
 * @Create: 2020/7/27 10:22 下午
 * @Description: 后台管理系统
 */

@Controller
@RequestMapping("back/order")
@Slf4j
public class BackOrderController {

    @Autowired
    private SysUserService sysUserService;


    /**
     * 首页
     * @return
     */
    @RequestMapping(value = "/search")
    public ModelAndView search(@RequestParam(value = "page") String page,
                              @RequestParam(value = "dateRange") String dateRange,
                              @RequestParam(value = "orderNum") String orderNum) {
        ModelAndView modelAndView = new ModelAndView();
        log.info(orderNum,dateRange);
        modelAndView.addObject("userName", AgentThreadLocal.get().getUserName());
        modelAndView.addObject("menuPermission", WConstants.permissionMap);
        modelAndView.addObject("page",page);
        modelAndView.setViewName("back/index");
        return modelAndView;
    }


}
