package com.wx_auto_sale.utils;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author wangyu
 * @Create: 2020/4/10 11:32 下午
 * @Description:
 */
@Slf4j
public class BeanUtils {

    public static void copyProperties(Object source, Object target) {
        org.springframework.beans.BeanUtils.copyProperties(source, target);
    }

    public static <T> T copyProperties(Object source, Class<T> targetClass) {
        if(source == null) {
            return null;
        }
        try {
            T t = targetClass.newInstance();
            org.springframework.beans.BeanUtils.copyProperties(source, t);
            return t;
        } catch (Exception e) {
            log.error("实例化失败", e);
        }
        return null;
    }

    public static <T> List<T> batchModel(Object source, Class<T> targetClass) {
        List<T> resList = new ArrayList<>();
        List data = (List) source;
        data.forEach(o->{
            resList.add(copyProperties(o,targetClass));
        });
        return resList;
    }
}
