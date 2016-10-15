package com.example.yifan.hopeforhungerapp.volunteer_classes;

import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;

/**
 * Created by Yifan on 10/1/2016.
 */

public class Volunteer implements Serializable {

    private static ArrayList<Volunteer> volunteers;
    private String name;
    private String address;
    private String phoneNum;
    private String guardian;

    private transient boolean signedIn;
    private transient Calendar signInTime;
    private transient Calendar signOutTime;

    HashMap<Calendar, Double> hoursForCalanderDayMap;

    private Volunteer(String name, String address, String phoneNum, String guardian) {
        this.name = name;
        this.address = address;
        this.phoneNum = phoneNum;
        this.guardian = guardian;
        this.hoursForCalanderDayMap = new HashMap<>();
    }

    public void addNewVolunteer(String name, String address, String phoneNum, String guardian){
        volunteers.add(new Volunteer(name, address, phoneNum, guardian));
    }

    public void signIn(){
        signedIn = true;
        signInTime = GregorianCalendar.getInstance();
    }

    public double signOutAndGetHours(){
        signedIn = false;
        signOutTime = GregorianCalendar.getInstance();
        return getTimeDifference();

    }

    private double getTimeDifference(){

    }




}