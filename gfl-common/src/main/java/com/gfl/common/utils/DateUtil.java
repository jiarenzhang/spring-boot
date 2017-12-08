package com.gfl.common.utils;

import org.apache.commons.lang3.StringUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    //private static final String Format_Date = "yyyy-MM-dd";
    // private static final String Format_Time = "HH:mm:ss";
    private static final String Format_DateTime = "yyyy-MM-dd HH:mm:ss";
    // private static final String Format_Date_TIME = "yyyy-MM-dd-HH-mm-ss";

    public static String getCurrentDate() {
        return new SimpleDateFormat("yyyyMMdd").format(new Date());
    }

    public static String getCurrentDate(Date date) {
        return new SimpleDateFormat("yyyy-MM-dd").format(date);
    }

    public static String getCurrentDatetime(Date date) {
        return new SimpleDateFormat(Format_DateTime).format(date);
    }

    public static String getCurrentDatetime(Date date, String format) {
        return new SimpleDateFormat(format).format(date);
    }

    /**
     * 将时间戳转换日期对象
     * @param times 时间戳
     * @return 日期对象Date
     * @throws Exception 异常信息对象
     */
    public static Date parseLongToDate(Long times) {
        try {
            SimpleDateFormat format = new SimpleDateFormat(Format_DateTime);
            String str = format.format(times);
            Date temp = format.parse(str);
            return temp;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将时间戳转换为时间
     * @param timeStamp
     * @return
     */
    public static String stampToDate(String timeStamp) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(Format_DateTime);
        long lt = new Long(timeStamp);
        Date date = new Date(lt);
        return simpleDateFormat.format(date);
    }

    public static Date parseStrToDate(String str) {
        if (StringUtils.isEmpty(str)) {
            return null;
        }
        try {
            return new SimpleDateFormat(Format_DateTime).parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 时间比较
     * @param date1
     * @param date2
     * @param formatStr
     * @return
     */
    public static int compareDate(String date1, String date2, String formatStr) {
        int result = -2;
        try {
            DateFormat format = new SimpleDateFormat(formatStr);
            Date dt1 = format.parse(date1);
            Date dt2 = format.parse(date2);
            if (dt1.getTime() > dt2.getTime()) {
                result = 1;
            } else if (dt1.getTime() < dt2.getTime()) {
                result = -1;
            } else {
                result = 0;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return result;
    }

    public static void main(String[] args) {
        //Date data = com.cc.platform.customer.util.DateUtils.parseStrToDate(com.cc.platform.customer.util.DateUtils.stampToDate(String.valueOf(-0L)));
        // System.out.println(parseLongToDate(0L));
        int i = compareDate("9:50", "9:50", DateFormatUtils.HH_MM);
        System.out.println("i==" + i);
    }
}



