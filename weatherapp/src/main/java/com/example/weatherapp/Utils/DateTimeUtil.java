package com.example.weatherapp.Utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeUtil {

    public static final String DATE_FORMAT_APP="dd-MM-yyyy HH:mm:ss";
    public static final String DATE_FORMAT_SERVER="dd-MM-yyyy HH:mm:ss";
    public static String convert_MiSecToTimeString(long millisecond){
        return new SimpleDateFormat(DATE_FORMAT_APP).format(convert_MiSecToTimeObj(millisecond));
    }
    public static Date convert_MiSecToTimeObj(long millisecond){
        Date date = new Date();
        date.setTime(millisecond);
        return date;
    }

    public static long convert_SecToMilli(long sec){
        return sec*1000;
    }
}
