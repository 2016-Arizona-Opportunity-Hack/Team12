package com.example.yifan.hopeforhungerapp;

import android.util.Log;

import java.util.Calendar;

import static java.util.Calendar.*;

/**
 * Created by Yifan on 10/2/2016.
 */

public class Day {
    private int month;
    private int day;
    private int year;

    static int instnaces = 0;
    private Day(int month, int day, int year){
        this.month = month;
        this.day = day;
        this.year = year;
    }

    public static Day getInstance(){
        Calendar cal = Calendar.getInstance();
        instnaces++;
        return new Day(cal.get(MONTH)+1, cal.get(DATE), cal.get(YEAR));

    }

    public String toString(){
        String a = month+"/"+(day+instnaces)+"/"+year;
        Log.i("testing day",a);
        return a;
    }

    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

}
