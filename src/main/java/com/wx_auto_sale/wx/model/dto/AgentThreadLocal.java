package com.wx_auto_sale.wx.model.dto;

import com.wx_auto_sale.wx.model.entity.SysUserEntity;

public class AgentThreadLocal {

    private AgentThreadLocal() {
    }

    private static final ThreadLocal<SysUserEntity> LOCAL = new ThreadLocal<SysUserEntity>();

    public static void set(SysUserEntity user) {
        LOCAL.set(user);
    }

    public static SysUserEntity get() {
        return LOCAL.get();
    }

    public static void remove() {
        LOCAL.remove();
    }
}