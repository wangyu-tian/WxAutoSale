package com.wx_auto_sale.wx.controller;

import com.alibaba.fastjson.JSONObject;
import com.wx_auto_sale.utils.BeanUtils;
import com.wx_auto_sale.wx.model.dto.response.OrderOutDto;
import com.wx_auto_sale.wx.model.entity.OrderEntity;
import com.wx_auto_sale.wx.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author wangyu
 * @Create: 2020/7/26 3:34 下午
 * @Description: 外部页面查看控制器
 */
@Controller
@RequestMapping("view")
@Slf4j
public class ViewController {

    @Autowired
    private OrderService orderService;

    /**
     * 外部查看订单页面详情
     * @param uId
     * @param orderCode
     * @return
     */
    @RequestMapping(value = "/order/{uId}/{orderCode}")
    public ModelAndView viewOrderInfo( @PathVariable("uId") String uId,
                             @PathVariable("orderCode") String orderCode) {
        log.info("ViewController_viewOrderInfo_uId:{}_orderCode:{}",uId,orderCode);
        ModelAndView mv = new ModelAndView();
        OrderEntity orderEntity = orderService.findOrderByCode(uId,orderCode);
        mv.setViewName("orderInfo");
        OrderOutDto orderOutDto = BeanUtils.copyProperties(orderEntity, OrderOutDto.class);
        List<JSONObject> goods = JSONObject.parseObject(orderOutDto.getDetail()).values().stream()
                .map(d-> {
                    String[] data = d.toString().split(";");
                    JSONObject jsonObject = new JSONObject();
                    //数量
                    jsonObject.put("count",data[0]);
                    //单价
                    jsonObject.put("price",data[1]);
                    //原总价
                    jsonObject.put("total",data[2]);
                    //折扣总价
                    jsonObject.put("discountTotal",data[3]);
                    //商品名称
                    jsonObject.put("name",data[4]);
                    //商品图片
                    jsonObject.put("url",data[5]);
                    //商品折扣
                    jsonObject.put("discount",data.length>6?data[6]:"");
                    return jsonObject;
                }).collect(Collectors.toList());
        mv.addObject("orderInfo", orderOutDto);
        mv.addObject("goods",goods);
        BigDecimal disAmount = new BigDecimal(orderOutDto.getOrderAmount())
                .subtract(new BigDecimal(orderOutDto.getDiscountAmount())).setScale(2);
        mv.addObject("disAmount", disAmount);
        return mv;
    }
}
