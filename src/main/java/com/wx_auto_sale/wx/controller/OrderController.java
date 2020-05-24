package com.wx_auto_sale.wx.controller;

import com.wx_auto_sale.utils.WxUtil;
import com.wx_auto_sale.wx.model.BaseIn;
import com.wx_auto_sale.wx.model.BaseOut;
import com.wx_auto_sale.wx.model.api.TemplateData;
import com.wx_auto_sale.wx.model.dto.PageDto;
import com.wx_auto_sale.wx.model.dto.request.OrderInDto;
import com.wx_auto_sale.wx.model.dto.response.OrderOutDto;
import com.wx_auto_sale.wx.model.dto.response.OrderUserDto;
import com.wx_auto_sale.wx.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author wangyu
 * @Create: 2020/4/18 10:28 上午
 * @Description:
 */
@RestController
@RequestMapping("order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    /**
     * 用户确认添加订单
     * @param
     * @return
     */
    @PostMapping(value = "/uAdd")
    public BaseOut uAdd(@RequestBody BaseIn<OrderInDto> orderInDto) {
        OrderOutDto orderOutDto = orderService.uAdd(orderInDto.getMId(),orderInDto.getUId(),orderInDto.getData());
        return new BaseOut(orderOutDto);
    }

    @PostMapping(value = "/pageList")
    public BaseOut pageList(@RequestBody BaseIn pageIn) {
        PageDto<List<OrderOutDto>> pageList = orderService.pageList(pageIn.getMId(),pageIn.getUId(),pageIn.getCurrentPage(),pageIn.getPageSize());
        return new BaseOut(pageList);
    }

    /**
     * 查询订单用户基本信息：姓名，地址，电话，备注等
     * @param baseIn
     * @return
     */
    @PostMapping(value = "/orderUserInfo")
    public BaseOut orderUserInfo(@RequestBody BaseIn baseIn) {
        OrderUserDto orderUserDto = orderService.findOrderUserInfoBest(baseIn.getUId(),baseIn.getMId());
        return new BaseOut(orderUserDto);
    }
}
