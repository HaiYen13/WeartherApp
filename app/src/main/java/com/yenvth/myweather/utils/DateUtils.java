package com.yenvth.myweather.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {

    public static final String FORMAT_1 = "HH:mm:ss";
    public static final String FORMAT_2 = "dd/MM/yyyy";
    public static final String FORMAT_3 = "HH:mm";
    public static final String FORMAT_4 = "dd-MM-yyyy";
    public static final String FORMAT_5 = "EEE dd-MM-yyyy HH:mm";

    public static final String FORMAT_6 = "dd/MM";
    public static final String FORMAT_7 = "EEE";

    public static String getDateFromTimestamp(Long timestamp, String format){
        if (timestamp == null) return "";
        Date date = new Date(timestamp);
        DateFormat dateFormat = new SimpleDateFormat(format);
        return dateFormat.format(date);
    }

    public static int getYear(long timestamp) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timestamp);
        return calendar.get(Calendar.YEAR);
    }

    public static int getMonth(long timestamp) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timestamp);
        return (calendar.get(Calendar.MONTH) + 1);
    }

    public static int getDayOfMonth(long timestamp) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timestamp);
        return calendar.get(Calendar.DAY_OF_MONTH);
    }
    //Lấy thứ trong tuần
    public static int getDayOfWeek(long timestamp) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timestamp);
        return calendar.get(Calendar.DAY_OF_WEEK);
    }
    public static String getDayOfWeek(int dayOfWeek) {
        String res = "";
        switch (dayOfWeek) {
            case 1:
                return "Mon";
            case 2:
                return "Tue";
            case 3:
                return "Wed";
            case 4:
                return "Thu";
            case 5:
                return "Fri";
            case 6:
                return "Sat";
            case 7:
                return "Sun";
            default :
                return "";
        }
    }
}
