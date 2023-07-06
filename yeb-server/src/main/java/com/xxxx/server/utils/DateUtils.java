package com.xxxx.server.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author Sunmile_Cheung 张亮
 * @Date 2023/7/6 11:10
 * @Description
 * @Modified by Sunmile
 */
public class DateUtils {

    /**
     * 将格林尼治时间转换为Date类型
     *
     * @param strDate
     * @return
     */
    public static Date parseStringToDate(String strDate) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = format.parse(strDate);
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 将时间进行格式转换
     * @param date
     * @return
     */
    public static Date parseFormatDate(Date date){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        String strDate = format.format(date);

        try {
            return format.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }

    }

}
