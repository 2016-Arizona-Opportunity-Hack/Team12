package com.example.yifan.hopeforhungerapp;

import java.util.Date;

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


    public static TimeWorked getTimeDifference(Date start, Date end){
        long timeDiff = end.getTime() - start.getTime();
        if(start.after(end)){
            return diff;
        }
        diff.hours = (int)timeDiff / (1000 * 60 * 60) % 24;
        diff.minutes = (int)timeDiff / (60 * 1000) % 60;
        diff.seconds = (int)timeDiff /1000 % 60;
        return diff;
    }



}
