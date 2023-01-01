package com.polytope.testpro.utils;

import android.text.TextUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author: 雪豹高科
 * @time: 2021-2-8
 */
public class DateUtil {

    public static String getPicTime() {
        String seconds = System.currentTimeMillis() / 1000 + "";
        String name = "app/img/" + seconds.substring(3);
        return name;
    }

    public static String getAudioTime() {
        String seconds = System.currentTimeMillis() / 1000 + "";
        String name = "app/audio/" + seconds.substring(3);
        return name;
    }

    public static String getSimpleDate(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.format(date);
    }

    public static String getSimpleDate(long time) {
        Date date = new Date(time);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.format(date);
    }

    public static String getSimpleSSDate(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return simpleDateFormat.format(date);
    }

    public static String getDate() {
        //设置日期格式
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // new Date()为获取当前系统时间
        return df.format(new Date());
    }

    public static String getDateSS() {
        //设置日期格式
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        // new Date()为获取当前系统时间
        return df.format(new Date());
    }

    public static String getSimpleDate() {
        //设置日期格式
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        // new Date()为获取当前系统时间
        return df.format(new Date());
    }

    public static String getSimpleDateForMeasure(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:00");
        return simpleDateFormat.format(date);
    }

    public static String getSimpleDateMeasure(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.format(date);
    }

    public static Date parseString(String stringDte) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date date = simpleDateFormat.parse(stringDte);
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取2个日期之间相隔几天
     *
     * @param fDateStr
     * @param oDateStr
     * @return
     */
    public static int getDayInterval(String fDateStr, String oDateStr) {
        Date fDate = parseString(fDateStr);
        Date oDate = parseString(oDateStr);

        return getDayInterval(fDate, oDate);
    }

    public static String getDateStr(long time, String format) {
        Date date = new Date(time);
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    /**
     * 获取2个日期之间相隔几天
     *
     * @param fDate
     * @param oDate
     * @return
     */
    public static int getDayInterval(Date fDate, Date oDate) {
        int dayInterval = 0;
        try {
            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
            Calendar cal1 = Calendar.getInstance();
            SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
            Calendar cal2 = Calendar.getInstance();

            fDate = sdf1.parse(sdf1.format(fDate));

            oDate = sdf2.parse(sdf2.format(oDate));


            cal1.setTime(fDate);
            long fTime = cal1.getTimeInMillis();

            cal2.setTime(oDate);
            long oTime = cal2.getTimeInMillis();

            float temp = (oTime - fTime) / (1000 * 3600 * 24f);
            dayInterval = Math.round(temp);
        } catch (Exception e) {

        }
        return dayInterval;
    }

    /**
     * 将字符串转为时间戳
     *
     * @param time
     * @return
     */
    public static long getStringToDate(String time) {

        SimpleDateFormat sf = null;
        if (!TextUtils.isEmpty(time)) {
            if (time.contains("-")) {
                sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            } else if (time.contains(".")) {
                sf = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
            }
        } else {
            time = getDate();
            sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }

        Date date = new Date();
        try {
            date = sf.parse(time);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return date.getTime() / 1000;
    }

    /**
     * @param date
     * @return 毫秒
     */
    public static long stringDateToLong(String date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date parse = simpleDateFormat.parse(date);
            return parse.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static String getTimeStr(long seconds) {
        Date date = new Date(seconds * 1000);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.format(date);
    }

    public static String getMinDate(String date1, String date2) {
        if (getStringToDate(date1) <= getStringToDate(date2)) {
            return date1;
        } else {
            return date2;
        }
    }

    public static String getMaxDate(String date1, String date2) {
        if (getStringToDate(date1) >= getStringToDate(date2)) {
            return date1;
        } else {
            return date2;
        }
    }

//    public static String plusOneWeek(String dateTime) {
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:00");
//        try {
//            Date date = sdf.parse(dateTime);
//            LocalDateTime localDateTime = LocalDateTime.fromDateFields(date);
//            Date date1 = localDateTime.plusWeeks(1).toDate();
//            return sdf2.format(date1);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        return "";
//    }
//
//    public static String plusOneMonth(String dateTime) {
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:00");
//        try {
//            Date date = sdf.parse(dateTime);
//            LocalDateTime localDateTime = LocalDateTime.fromDateFields(date);
//            Date date1 = localDateTime.plusMonths(1).toDate();
//            return sdf2.format(date1);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        return "";
//    }
//
//    public static String plusDays(String date, int days) {
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        try {
//            Date parse = sdf.parse(date);
//            Date date1 = LocalDateTime.fromDateFields(parse).plusDays(days).toDate();
//            String format = sdf.format(date1);
//            return format;
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        return date;
//    }
//
//    public static String minusDays(String date, int days) {
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        try {
//            Date parse = sdf.parse(date);
//            Date date1 = LocalDateTime.fromDateFields(parse).minusDays(days).toDate();
//            String format = sdf.format(date1);
//            return format;
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        return date;
//    }

    /**
     * 递增天数 1天
     */
    public static String addDateDay(String date) {
        String getDateDay = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date getDate;
        try {
            getDate = sdf.parse(date);
            getDate = addDate(getDate, 1);
            getDateDay = sdf.format(getDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return getDateDay;
    }

    public static Date addDate(Date d, long day) {
        long time = d.getTime();
        day = day * 24 * 60 * 60 * 1000;
        time += day;
        return new Date(time);
    }
}
