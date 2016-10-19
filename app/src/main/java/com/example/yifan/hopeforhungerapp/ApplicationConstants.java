package com.example.yifan.hopeforhungerapp;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Yifan on 10/1/2016.
 */

public class ApplicationConstants {
    public final static String GUARDIAN = "Guardian";
    public final static String ADDRESS = "Address";
    public final static String NAME = "Names";
    public final static String DATE = "Date";
    public final static String PHONE = "Phone";
    public final static String INTERNAL_STORAGE = "InternalStorage";

    public static String convertCalanderObjToMMDDYYYY(Calendar calendar){
        return String.valueOf(calendar.get(Calendar.MONTH) + 1) + "/" + calendar.get(Calendar.DAY_OF_MONTH) + "/" + calendar.get(Calendar.YEAR);
    }

    public static String getTimeStampForCalanderObj(Calendar calendar){
        if(calendar.get(Calendar.AM_PM) == 0){
            return String.valueOf(calendar.get(Calendar.HOUR) + ":" + calendar.get(Calendar.MINUTE) + "AM");
        }
        else{
            return String.valueOf(calendar.get(Calendar.HOUR) + ":" + calendar.get(Calendar.MINUTE) + "PM");
        }

    }



}
