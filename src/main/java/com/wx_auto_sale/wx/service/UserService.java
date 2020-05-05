package com.wx_auto_sale.wx.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wrapper.util.JpaUtil;
import com.wrapper.wrapper.SqlWrapper;
import com.wx_auto_sale.constants.ErrorCode;
import com.wx_auto_sale.config.ConstantConfig;
import com.wx_auto_sale.utils.BeanUtils;
import com.wx_auto_sale.utils.PermissionUtil;
import com.wx_auto_sale.wx.model.api.WxUser;
import com.wx_auto_sale.wx.model.dto.response.UserOutDto;
import com.wx_auto_sale.wx.model.entity.MerchantEntity;
import com.wx_auto_sale.wx.model.entity.UserEntity;
import com.wx_auto_sale.wx.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Transient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Author wangyu
 * @Create: 2020/4/10 12:50 下午
 * @Description:
 */
@Service
@Slf4j
public class UserService {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private JpaUtil jpaUtil;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MerchantService merchantService;

    /**
     * 小程序用户校验：通过微信code获取用户信息
     * @param wxCode
     * @param mId 商户ID
     * @return
     */
    @Transient
    public UserOutDto getUserInfoByWxCode(String wxCode, String mId) {
        //通过mId获取商户信息
        MerchantEntity merchantEntity = merchantService.getMerchantById(mId);

        //通过商户信息获取微信用户ID
        UserEntity user = null;
        JSONObject responseData = getOpenIdByWxData(wxCode,merchantEntity);
        String openId = responseData.getString("openid");
        PermissionUtil.isNull(openId, ErrorCode.UserEnum.WX_CODE_ERROR);
        //通过微信用户ID查询用户信息
        SqlWrapper<UserEntity> sqlWrapper = new SqlWrapper<>(UserEntity.class);
        sqlWrapper.eq(UserEntity::getWId, openId);
        List<UserEntity> userEntityList = jpaUtil.wrapper(sqlWrapper);
        if (userEntityList.size() == 0) {//用户不存在，新建用户
            UserEntity userEntity = new UserEntity();
            userEntity.setWId(openId);
            userEntity.setMId(mId);
            userEntity.setStatus("1");
            userEntity.setStatusMemo("正常");
            userEntity.setCreateDate(new Date());
            userRepository.save(userEntity);
            user = userEntity;
        } else {
            user = userEntityList.get(0);
            PermissionUtil.isTrue(!"1".equals(user.getStatus()),ErrorCode.UserEnum.WX_STATUS_ERROR);
        }
        UserOutDto userOutDto = BeanUtils.copyProperties(user, UserOutDto.class);
        return userOutDto;
    }

    private JSONObject getOpenIdByWxData(String wxCode, MerchantEntity merchantEntity) {
        WxUser wxUser = new WxUser();
        wxUser.setAppid(merchantEntity.getAppid());
        wxUser.setGrant_type(ConstantConfig.wxgrantType);
        wxUser.setSecret(merchantEntity.getSecret());
        wxUser.setJs_code(wxCode);
        log.info("请求微信用户ID：{}", JSON.toJSONString(wxUser));
        MultiValueMap<String, Object> postParameters = new LinkedMultiValueMap<>();
        postParameters.setAll(JSONObject.toJavaObject(JSON.parseObject(JSON.toJSONString(wxUser)), Map.class));
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/x-www-form-urlencoded");
        HttpEntity<MultiValueMap<String, Object>> r = new HttpEntity<>(postParameters, headers);
        String responseData = restTemplate.postForObject(ConstantConfig.wxUserUrl, r, String.class);
        log.info("微信鉴权接口响应:{}", responseData);
        return JSON.parseObject(responseData);
    }

    /**
     * 获取用户信息
     * @param uId
     * @return
     */
    public UserEntity getUserById(String uId,String wId) {
        PermissionUtil.paramCheckFail(StringUtils.isBlank(uId)&&StringUtils.isBlank(wId), ErrorCode.UserEnum.WX_MERCHANT_NOT_EXIST,
                uId,wId);
        List<UserEntity> userEntityList = jpaUtil.wrapper(new SqlWrapper<>(UserEntity.class)
                .eq(UserEntity::getId,uId,true)
                .eq(UserEntity::getWId,wId,true)
                .eq(UserEntity::getStatus,"1"));
        PermissionUtil.isTrue(userEntityList.size() == 0, ErrorCode.UserEnum.WX_USER_IS_NOT_EXIST);
        PermissionUtil.isTrue(!userEntityList.get(0).getStatus().equals("1"),ErrorCode.UserEnum.WX_STATUS_ERROR);
        return userEntityList.get(0);
    }
}
