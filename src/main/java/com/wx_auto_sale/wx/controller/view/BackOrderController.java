package com.wx_auto_sale.wx.controller.view;

import com.alibaba.fastjson.JSONObject;
import com.wx_auto_sale.constants.DataEnum;
import com.wx_auto_sale.wx.model.dto.request.OrderInDto;
import com.wx_auto_sale.wx.model.dto.response.OrderOutDto;
import com.wx_auto_sale.wx.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import static com.wx_auto_sale.constants.DataEnum.BackPageEnum.ORDER_DETAIL;

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
    private OrderService orderService;

    /**
     * 订单页面
     * @return
     */
    @RequestMapping(value = "/search")
    public ModelAndView search(@RequestParam(value = "page") String page,
                              @RequestParam(value = "dateRange",required = false) String dateRange,
                              @RequestParam(value = "orderNum",required = false) String orderNum) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("page",page);
        modelAndView.addObject("dateRange",dateRange);
        modelAndView.addObject("orderNum",orderNum);
        modelAndView.addObject("orderStatusMap", DataEnum.OrderEnum.toMap());
        modelAndView.addObject("orderList",orderService.search(dateRange,orderNum));
        modelAndView.setViewName("back/index");
        return modelAndView;
    }

    /**
     * 更新订单
     * @return
     */
    @RequestMapping(value = "/update")
    public ModelAndView update(@RequestParam(value = "page") String page,
                               @RequestParam(value = "id") String orderId,
                               @RequestParam(value = "dateRange") String dateRange,
                               @RequestParam(value = "orderNum") String orderNum,
                               @RequestParam(value = "oldStatus") String oldStatus,
                               @RequestParam(value = "newStatus") String newStatus) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("page",page);
        modelAndView.addObject("dateRange",dateRange);
        modelAndView.addObject("orderNum",orderNum);
        orderService.update(new OrderInDto().setOrderId(orderId).setOldStatus(oldStatus).setNewStatus(newStatus));
        modelAndView.setViewName("redirect:/back/order/search");
        return modelAndView;
    }

    /**
     * 订单详情
     * @return
     */
    @RequestMapping(value = "/detail")
    public ModelAndView detail(@RequestParam(value = "id") String orderId) {


        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("page",ORDER_DETAIL.getCode());
        OrderOutDto orderOutDto = orderService.findById(orderId);
        modelAndView.addObject("orderInfo",orderOutDto);
        modelAndView.setViewName("back/index");
        return modelAndView;
    }

}
