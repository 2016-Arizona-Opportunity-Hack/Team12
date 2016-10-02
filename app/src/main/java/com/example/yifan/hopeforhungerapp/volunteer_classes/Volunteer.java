package com.example.yifan.hopeforhungerapp.volunteer_classes;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Yifan on 10/1/2016.
 */

public abstract class Volunteer {
    private String name;
    private String address;
    private String phoneNum;
    private ArrayList<String> physicalLimitations;
    private String signUpDate;
    private boolean signedIn;

    private int currentHoursWorked;
    private int weeklyHoursWorked;

    transient private Date startTime;
    transient private Date endTime;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public int getCurrentHoursWorked() {return currentHoursWorked; }

    public int getWeeklyHoursWorked() { return weeklyHoursWorked; }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public boolean isSignedIn() {
        return signedIn;
    }

    public void setSignedIn(boolean signedIn) {
        this.signedIn = signedIn;
    }

    public ArrayList<String> getPhysicalLimitations() {
        return physicalLimitations;
    }

    public void setPhysicalLimitations(ArrayList<String> physicalLimitations) {
        this.physicalLimitations = physicalLimitations;
    }

    public void setSignUpDate(String signUpDate) {
        this.signUpDate = signUpDate;
    }

    public String getSignUpDate() {
        return signUpDate;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }
}
