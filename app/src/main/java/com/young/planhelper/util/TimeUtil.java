package com.young.planhelper.util;

import android.text.format.Time;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

/**
 * @author: young
 * email:1160415122@qq.com
 * date:16/11/7  20:50
 */


public class TimeUtil {

    public static DateFormat dateFormat = null;
    private static final String[] WEEK = { "天", "一", "二", "三", "四", "五", "六" };
    public static final String XING_QI = "星期";
    public static final String ZHOU = "周";

    /**
     * 获取日期
     * @param num
     * @param format
     * @return
     */
    public static String getWeek(int num, String format) {
        final Calendar c = Calendar.getInstance();
        c.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        int weekNum = c.get(Calendar.DAY_OF_WEEK) + num;
        if (weekNum > 7)
            weekNum = weekNum - 7;
        return format + WEEK[weekNum - 1];
    }

    /**
     * 获取日期
     * @return
     */
    public static String getDay() {
        final Calendar c = Calendar.getInstance();
        c.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        int dayNum = c.get(Calendar.DAY_OF_MONTH);
        return String.valueOf(dayNum);
    }


    /**
     * 判断当前为第几周
     * @return
     */
    public static String getZhouWeek() {
        SimpleDateFormat format = new SimpleDateFormat("MM/dd");
        return format.format(new Date(System.currentTimeMillis())) + " "
                + getWeek(0, ZHOU);
    }

    /**
     * 根据开始时间和结束时间获取差值秒
     * @param end
     * @param begin
     * @return
     */
    public static long getSec(long end,long begin){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date nowDate = new Date(begin);
        Date lastUpdateDate = new Date(end);
        Long diff = nowDate.getTime() - lastUpdateDate.getTime();
        Long day = diff / (  60 * 60 * 24);
        Long hour = (diff / (60 * 60 ) - day * 24);
        Long min = ((diff / (60)) - day * 24 * 60 - hour * 60); // 以分钟为单位取整
        Long second = (diff / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
        return second;
    }


    public static String formatDuring(long mss) {
        long days = mss / ( 1000* 60 * 60 * 24);
        long hours = (mss % (1000* 60 * 60 * 24)) / (1000 * 60 * 60);
        long minutes = (mss % (1000 * 60 * 60)) / (1000 * 60);
        long seconds = (mss % (1000 * 60)) / 1000;
        StringBuffer sb = new StringBuffer();
        if (days > 0) {
            sb.append(days + "天 ");
        }
        if (hours > 0) {
            sb.append(hours + "小时 ");
        }
        if (minutes > 0) {
            if (String.valueOf(minutes).length()==1) {
                sb.append("0"+minutes+"分钟");
            }else{
                sb.append(minutes + "分钟");
            }
        }else{
            sb.append("");
        }
        if (seconds > 0) {
            if (String.valueOf(seconds).length()==1) {
                sb.append("0"+seconds+"秒");
            }else{
                sb.append(seconds+"秒");
            }
        }else{
            sb.append("00"+"秒");
        }
        return sb.toString();
    }

    public static long formatSecDuring(long mss) {
        long sec=0;
        long days = mss / ( 1000* 60 * 60 * 24);
        long hours = (mss % (1000* 60 * 60 * 24)) / (1000 * 60 * 60);
        long minutes = (mss % (1000 * 60 * 60)) / (1000 * 60);
        long seconds = (mss % (1000 * 60)) / 1000;
        StringBuffer sb = new StringBuffer();
        if (days > 0) {
            sb.append(days + "天 ");
        }
        if (hours > 0) {
            sb.append(hours + "小时 ");
        }
        if (minutes > 0) {
            sec=sec+minutes*60;
            if (String.valueOf(minutes).length()==1) {
                sb.append("0"+minutes+"分钟");
            }else{
                sb.append(minutes + "分钟");
            }
        }else{
            sb.append("");
        }
        if (seconds > 0) {
            sec=sec+seconds;
            if (String.valueOf(seconds).length()==1) {
                sb.append("0"+seconds+"''");
            }else{
                sb.append(seconds+"''");
            }

        }else{
            sb.append("00"+"''");
        }
        LogUtil.eLog("formatSecDuring:"+sb.toString());
        return sec;
    }

    /**
     * 判断时间戳是什么时候
     * @param timesamp
     * @return
     */
    public static String getDay(long timesamp) {
        String result = "";
        SimpleDateFormat sdf = new SimpleDateFormat("dd");
        Date today = new Date(System.currentTimeMillis());
        Date otherDay = new Date(timesamp/1000);
        int temp = Integer.parseInt(sdf.format(today))
                - Integer.parseInt(sdf.format(otherDay));

        switch (temp) {
            case 0:
                result = "今天";
                break;
            case 1:
                result = "昨天";
                break;
            case 2:
                result = "前天";
                break;

            default:
                result = temp + "天前";
                break;
        }

        return result;
    }

    /**
     * 获取当前日期
     * @param date
     * @return
     */
    public static String getDateTime(Date date) {
        return format(date, "yyyy-MM-dd HH:mm:ss");
    }

    public static String format(Date date, String format) {
        String result = "";
        try {
            if (date != null) {
                dateFormat = new SimpleDateFormat(format);
                result = dateFormat.format(date);
            }
        } catch (Exception e) {
        }
        return result;
    }

    public static String getTimeStampNow() {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        return  format.format(new Date());
    }

    public static String getYMDNow() {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        return  format.format(new Date());
    }

    public static String getMonthByTime(Time t) {
        return String.valueOf(t.month) + "月";
    }

    public static int getDayByTime(Time t) {
        return t.monthDay;
    }

    public static String getWeekOfDate(Time t) {
        String[] weekDays = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
        return weekDays[t.weekDay];
    }

    public static String getWeekOfDate2(Time t) {
        String[] weekDays = { "周日", "周一", "周二", "周三", "周四", "周五", "周六" };
        return weekDays[t.weekDay];
    }

    public static String getHMSbyMs(long mss) {
        long hours = (mss % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
        long minutes = (mss % (1000 * 60 * 60)) / (1000 * 60);
        long seconds = (mss % (1000 * 60)) / 1000;

        String pattern="00";
        java.text.DecimalFormat df = new java.text.DecimalFormat(pattern);
        return df.format(hours) + ":" + df.format(minutes) + ":"
                + df.format(seconds);
    }

    public static final SimpleDateFormat DEFAULT_DATE_FORMAT1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static final SimpleDateFormat DATE_FORMAT_DATE    = new SimpleDateFormat("MM月dd日");
    public static final SimpleDateFormat DATE_FORMAT_DATE_01    = new SimpleDateFormat("yyyy年MM月dd日");
    public static final SimpleDateFormat DATE_FORMAT_DATE_TIME    = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");
    public static final SimpleDateFormat DATE_FORMAT_DATE_TIME_01    = new SimpleDateFormat("yyyy-MM-dd HH:mm");


    private TimeUtil() {
        throw new AssertionError();
    }

    /**
     * long time to string
     *
     * @param timeInMillis
     * @param dateFormat
     * @return
     */
    public static String getTime(long timeInMillis, SimpleDateFormat dateFormat) {
        return dateFormat.format(new Date(timeInMillis));
    }

    /**
     * long time to string, format is {@link #DEFAULT_DATE_FORMAT}
     *
     * @param timeInMillis
     * @return
     */
    public static String getTime(long timeInMillis) {
        return getTime(timeInMillis, DATE_FORMAT_DATE);
    }

    public static String getTime1(long timeInMillis) {
        return getTime(timeInMillis, DEFAULT_DATE_FORMAT1);
    }

    public static String getTime2(long timeInMillis){
        return getTime(timeInMillis, DATE_FORMAT_DATE_TIME);
    }

    public static String getTime3(long timeInMillis){
        return getTime(timeInMillis, DATE_FORMAT_DATE_TIME_01);
    }

    public static String getTime4(long timeInMillis){
        return getTime(timeInMillis, DATE_FORMAT_DATE_01);
    }

    /**
     * get current time in milliseconds
     *
     * @return
     */
    public static long getCurrentTimeInLong() {
        return System.currentTimeMillis();
    }

    /**
     * get current time in milliseconds, format is {@link #DEFAULT_DATE_FORMAT}
     *
     * @return
     */
    public static String getCurrentTimeInString() {
        return getTime(getCurrentTimeInLong());
    }

    public static String getCurrentDateTimeInString(){
        return getTime2(getCurrentTimeInLong());
    }

    public static String getCurrentDateTimeInString1(){
        return getTime3(getCurrentTimeInLong());
    }

    public static String getCurrentDateTimeInString1(long time){
        return getTime3(getCurrentTimeInLong() +time );
    }

    public static String getCurrentDateInString() {
        return getTime4(getCurrentTimeInLong() );
    }

    /**
     * get current time in milliseconds
     *
     * @return
     */
    public static String getCurrentTimeInString(SimpleDateFormat dateFormat) {
        return getTime(getCurrentTimeInLong(), dateFormat);
    }


    /**
     * 获取当前开始时间
     * @return
     */
    public static Long getTodayStartTime(){
        Calendar todayStart = Calendar.getInstance();
        todayStart.set(Calendar.HOUR, 0);
        todayStart.set(Calendar.MINUTE, 0);
        todayStart.set(Calendar.SECOND, 0);
        todayStart.set(Calendar.MILLISECOND, 0);
        return todayStart.getTime().getTime();
    }

    /**
     * 获取当前结束时间
     * @return
     */
    public static Long getTodayEndTime(){
        Calendar todayEnd = Calendar.getInstance();
        todayEnd.set(Calendar.HOUR, 23);
        todayEnd.set(Calendar.MINUTE, 59);
        todayEnd.set(Calendar.SECOND, 59);
        todayEnd.set(Calendar.MILLISECOND, 999);
        return todayEnd.getTime().getTime();
    }

    /**
     * 将时间转换为时间戳
     * @param s
     * @return
     * @throws ParseException
     */
    public static long dateToStamp(String s) throws ParseException{
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");
        Date date = simpleDateFormat.parse(s);
        long ts = date.getTime();
        return ts;
    }

    /**
     * 将时间转换为时间戳
     * @param s
     * @return
     * @throws ParseException
     */
    public static long dateToStamp1(String s) throws ParseException{
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date = simpleDateFormat.parse(s);
        long ts = date.getTime();
        return ts;
    }

}
