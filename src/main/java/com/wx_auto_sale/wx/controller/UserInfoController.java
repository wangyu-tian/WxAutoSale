package com.wx_auto_sale.wx.controller;

import com.wx_auto_sale.utils.BeanUtils;
import com.wx_auto_sale.wx.model.BaseIn;
import com.wx_auto_sale.wx.model.BaseOut;
import com.wx_auto_sale.wx.model.dto.request.UserInDto;
import com.wx_auto_sale.wx.model.dto.response.UserOutDto;
import com.wx_auto_sale.wx.model.entity.UserEntity;
import com.wx_auto_sale.wx.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author wangyu
 * @Create: 2020/4/10 12:49 上午
 * @Description:
 */
@RestController
@RequestMapping("userInfo")
public class UserInfoController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/getInfoByCode")
    public BaseOut getInfoByCode(@RequestBody BaseIn<UserInDto> userInfo) {
        UserOutDto userOutDto = userService.getUserInfoByWxCode(userInfo.getData().getWxCode(),userInfo.getMId());
        return new BaseOut(userOutDto);
    }

    @PostMapping(value = "/getInfo")
    public BaseOut getInfo(@RequestBody BaseIn<UserInDto> userInfo) {
        UserEntity userEntity = userService.getUserById(userInfo.getUId(),userInfo.getWId());
        return new BaseOut(BeanUtils.copyProperties(userEntity, UserOutDto.class));
    }


}
