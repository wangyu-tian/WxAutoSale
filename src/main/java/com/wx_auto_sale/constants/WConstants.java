package com.wx_auto_sale.constants;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author wangyu
 * @Create: 2020/8/3 8:35 下午
 * @Description:
 */
public class WConstants {

    public static Map<String,Boolean> permissionMap = new HashMap<>();

    //初始化权限分配,默认启动所有菜单
    static{
        permissionMap.put("1000",true);
        permissionMap.put("1001",true);
        permissionMap.put("2000",true);
        permissionMap.put("2001",true);
        permissionMap.put("2002",true);
        permissionMap.put("2003",true);
        permissionMap.put("3000",true);
        permissionMap.put("3001",false);
        permissionMap.put("4000",true);
        permissionMap.put("4001",true);
    }
}
