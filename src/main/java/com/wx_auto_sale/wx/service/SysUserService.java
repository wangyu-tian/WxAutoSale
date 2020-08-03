package com.wx_auto_sale.wx.service;

import com.alibaba.fastjson.JSONObject;
import com.wrapper.util.JpaUtil;
import com.wrapper.wrapper.SqlWrapper;
import com.wx_auto_sale.constants.ErrorCode;
import com.wx_auto_sale.utils.BeanUtils;
import com.wx_auto_sale.utils.DateUtil;
import com.wx_auto_sale.utils.PermissionUtil;
import com.wx_auto_sale.utils.WxUtil;
import com.wx_auto_sale.wx.model.dto.AgentThreadLocal;
import com.wx_auto_sale.wx.model.dto.response.UserOutDto;
import com.wx_auto_sale.wx.model.entity.MerchantEntity;
import com.wx_auto_sale.wx.model.entity.SysUserEntity;
import com.wx_auto_sale.wx.model.entity.UserEntity;
import com.wx_auto_sale.wx.repository.SysUserRepository;
import com.wx_auto_sale.wx.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.http.client.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Transient;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.web.client.RestTemplate;
import sun.security.provider.MD5;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

/**
 * @Author wangyu
 * @Create: 2020/4/10 12:50 下午
 * @Description:
 */
@Service
@Slf4j
public class SysUserService {

    @Autowired
    private JpaUtil jpaUtil;

    @Autowired
    private SysUserRepository sysUserRepository;

    ThreadLocal<HashMap<String,SysUserEntity>> threadLocal = new ThreadLocal<>();


    public void save(SysUserEntity userEntity) {
        sysUserRepository.save(userEntity);
    }

    public SysUserEntity login( String userName, String password,String sessionId) {
        if(StringUtils.isBlank(userName)||StringUtils.isBlank(password))return null;
        SqlWrapper<SysUserEntity> sqlWrapper = new SqlWrapper<>(SysUserEntity.class)
                .eq(SysUserEntity::getUserName,userName)
                .eq(SysUserEntity::getPassword,password)
                .eq(SysUserEntity::getStatus,"1");
        SysUserEntity sysUserEntity = jpaUtil.one(sqlWrapper.getHql(),sqlWrapper.getParamsMap());
        if(sysUserEntity != null){
            sysUserEntity.setSysToken(sessionId);
            sysUserEntity.setTokenEndTime(DateUtil.date2string(DateUtil.addDate(DateUtil.now(),12,60*12),"yyyyMMddHHmmss"));
            sysUserEntity.setUpdateDate(new Date());
            save(sysUserEntity);
        }
        return sysUserEntity;
    }

    public synchronized SysUserEntity findByToken(String sessionId) {
        if(StringUtils.isBlank(sessionId))return null;
        SqlWrapper<SysUserEntity> sqlWrapper = new SqlWrapper<>(SysUserEntity.class)
                .eq(SysUserEntity::getSysToken,sessionId)
                .ge(SysUserEntity::getTokenEndTime,DateUtil.date2string(DateUtil.now(),"yyyyMMddHHmmss"))
                .eq(SysUserEntity::getStatus,"1");
        SysUserEntity sysUserEntity = jpaUtil.one(sqlWrapper.getHql(),sqlWrapper.getParamsMap());
        if(sysUserEntity != null){
            AgentThreadLocal.set(sysUserEntity);
        }
        return sysUserEntity;
    }
}
