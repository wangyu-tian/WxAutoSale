package com.wx_auto_sale.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @Author wangyu
 * @Create: 2020/7/28 10:23 下午
 * @Description:
 */
public class DateUtil {

    public static Date now(){
        return new Date();
    }

    public static Date addDate(Date date , int field , int minute){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(field,minute);
        return calendar.getTime();
    }

    public static String date2string(Date date,String format){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        return simpleDateFormat.format(date);
    }
}
