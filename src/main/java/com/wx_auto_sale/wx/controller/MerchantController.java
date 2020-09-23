package com.wx_auto_sale.wx.controller;

import com.wx_auto_sale.wx.model.BaseIn;
import com.wx_auto_sale.wx.model.BaseOut;
import com.wx_auto_sale.wx.model.dto.response.MerchantOutDto;
import com.wx_auto_sale.wx.service.MerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author wangyu
 * @Create: 2020/4/18 11:21 上午
 * @Description: 商户信息
 */
@RestController
@RequestMapping("merchant")
public class MerchantController {

    @Autowired
    private MerchantService merchantService;
    /**
     * 查询商户信息
     * @param
     * @return
     */
    @PostMapping(value = "/info")
    public BaseOut allInfo(@RequestBody BaseIn baseIn) {
        MerchantOutDto merchantOutDto = merchantService.getMerchantDtoById(baseIn.getMId());
        return new BaseOut(merchantOutDto);
    }
}
