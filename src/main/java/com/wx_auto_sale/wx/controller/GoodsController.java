package com.wx_auto_sale.wx.controller;

import com.wx_auto_sale.wx.model.BaseIn;
import com.wx_auto_sale.wx.model.BaseOut;
import com.wx_auto_sale.wx.model.dto.request.UserInDto;
import com.wx_auto_sale.wx.model.dto.response.GoodsOutDto;
import com.wx_auto_sale.wx.model.dto.response.UserOutDto;
import com.wx_auto_sale.wx.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author wangyu
 * @Create: 2020/4/18 10:28 上午
 * @Description: 商品信息
 */
@RestController
@RequestMapping("goods")
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    /**
     * 查询用户下所有商品和分类信息
     * @param
     * @return
     */
    @PostMapping(value = "/allInfo")
    public BaseOut allInfo(@RequestBody BaseIn baseIn) {

        GoodsOutDto goodsOutDto = goodsService.allInfo(baseIn.getUId(),baseIn.getMId());
        return new BaseOut(goodsOutDto);
    }
}
