package com.baymax.utilslib;

import android.content.Context;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author oukanggui
 * @date 2018/10/11
 * 描述 时间Time操作工具类
 */

public class TimeUtil {

    /**
     * 默认统一使用的日期转换格式
     */
    private static SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 获取系统当前时间
     *
     * @return 当前系统时间, 形式为“yyyy-MM-dd HH:mm:ss”
     */
    public static String getSystemCurrentTime() {

        Date curDate = new Date(System.currentTimeMillis());
        return mSimpleDateFormat.format(curDate);
    }

    /**
     * 判断两个日期是否是同一天
     *
     * @param lastTime    上次时间（比较时间1）
     * @param currentTime 当前时间（比较时间2）
     * @return true 如果两个日期为同一天，否则为false
     */
    public static boolean isSameDay(String lastTime, String currentTime) {
        if (TextUtil.isEmpty(lastTime) || TextUtil.isEmpty(currentTime)) {
            return false;
        }
        Date lastDate;
        Date currentDate;
        try {
            lastDate = mSimpleDateFormat.parse(lastTime);
            currentDate = mSimpleDateFormat.parse(currentTime);
            if (lastDate.getYear() == currentDate.getYear() && lastDate.getMonth() == currentDate.getMonth() && lastDate.getDate() == currentDate.getDate()) {
                return true;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 计算两个日期的时间相差多少秒
     *
     * @param startTime
     * @param endTime
     * @return
     * @throws ParseException
     */
    public static long twoDateGapSeconds(String startTime, String endTime) {
        if (TextUtil.isEmpty(startTime) || TextUtil.isEmpty(endTime)) {
            return 0;
        }
        long timeLong = 0;
        try {
            Date startDate = mSimpleDateFormat.parse(startTime);
            Date endDate = mSimpleDateFormat.parse(endTime);
            timeLong = endDate.getTime() - startDate.getTime();
            timeLong = timeLong / 1000;
        } catch (ParseException e) {
            LogUtil.e("TimeUtil", "ParseException : " + e.toString());
        }
        return timeLong;
    }

    /**
     * 计算两个日期的时间相差多少分钟
     *
     * @param startTime
     * @param endTime
     * @return
     * @throws ParseException
     */
    public static long twoDateGapMinutes(String startTime, String endTime) {
        return twoDateGapSeconds(startTime, endTime) / 60;
    }


    /**
     * 计算两个日期的时间相差多少小时
     *
     * @param startTime
     * @param endTime
     * @return
     * @throws ParseException
     */
    public static long twoDateGapHours(String startTime, String endTime) {
        return twoDateGapMinutes(startTime, endTime) / 60;
    }

    /**
     * 计算两个日期的时间相差多少天
     *
     * @param startTime
     * @param endTime
     * @return
     * @throws ParseException
     */
    public static long twoDateGapDays(String startTime, String endTime) {
        if (TextUtil.isEmpty(startTime) || TextUtil.isEmpty(endTime)) {
            return 0;
        }
        long dayGap = 0;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date startDate = sdf.parse(startTime);
            Date endDate = sdf.parse(endTime);
            dayGap = endDate.getTime() - startDate.getTime();
            dayGap = dayGap / 1000 / 60 / 60 / 24;
        } catch (ParseException e) {
            LogUtil.e("TimeUtil", "ParseException : " + e.toString());
        }
        return dayGap;
    }
}
