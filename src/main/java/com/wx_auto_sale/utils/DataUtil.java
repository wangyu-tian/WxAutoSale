package com.wx_auto_sale.utils;

import lombok.extern.slf4j.Slf4j;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * @Author wangyu
 * @Create: 2020/4/13 11:17 上午
 * @Description:
 */
@Slf4j
public class DataUtil {

    /**
     * 将异常转为字符串
     *
     * @param e
     * @return
     */
    public static String getErrorInfoFromException(Exception e) {
        try {
            if (e == null) return null;
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            return sw.toString();
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }
}