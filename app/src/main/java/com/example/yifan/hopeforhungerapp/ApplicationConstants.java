package com.example.yifan.hopeforhungerapp;

import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Yifan on 10/1/2016.
 */

public class ApplicationConstants {
    public final static String LOG_TAG = ApplicationConstants.class.getSimpleName();
    public final static String GUARDIAN = "Guardian";
    public final static String ADDRESS = "Address";
    public final static String FIRST_NAME = "FirstName";
    public final static String LAST_NAME = "LastName";
    public final static String DATE = "Date";
    public final static String PHONE = "Phone";
    public final static String INTERNAL_STORAGE = "InternalStorage";

    public static String convertCalanderObjToMMDDYYYY(Calendar calendar){ //converts to MM/DD/YYYY format
        return String.valueOf(calendar.get(Calendar.MONTH) + 1) + "/" + calendar.get(Calendar.DAY_OF_MONTH) + "/" + calendar.get(Calendar.YEAR);
    }

    public static String getTimeStampForCalanderObj(Calendar calendar){ //gets timestamp ex)5:00PM
        String minutesString;
        if(calendar.get(Calendar.MINUTE) < 10){
            minutesString = "0" + calendar.get(Calendar.MINUTE);
        }
        else{
            minutesString = String.valueOf(calendar.get(Calendar.MINUTE));
        }
        if(calendar.get(Calendar.AM_PM) == Calendar.AM){ //this is fine, don't know why A.S flags this
            return String.valueOf(calendar.get(Calendar.HOUR) + ":" + minutesString + "AM");
        }
        else{
            return String.valueOf(calendar.get(Calendar.HOUR) + ":" + minutesString + "PM");
        }

    }

    //Calculating the difference in hours between the two calendar objects
    public static double getTimeDifference(Calendar signInTime, Calendar signOutTime){
        if(signInTime == null || signOutTime == null){
            Log.e(LOG_TAG, "sign in or out time is null");
            return 0.0;
        }
        else{
            Date start = signInTime.getTime();  //set start date to signIn current time stamp
            Date end = signOutTime.getTime();   //set end date to signOut current time stamp
            double timeDifference = end.getTime() - start.getTime();    //Get the difference in time
            double hoursDifference = timeDifference / (1000*60*60) % 24;    //Get the difference in hours in double format
            //Create decimal format
            double formattedHours = Math.floor(hoursDifference * 100)/100;
            return formattedHours;
        }
    }



}
