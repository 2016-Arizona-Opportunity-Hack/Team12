package com.example.yifan.hopeforhungerapp.volunteer_classes;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.yifan.hopeforhungerapp.Day;
import com.example.yifan.hopeforhungerapp.TimeWorked;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by Yifan on 10/1/2016.
 */

public abstract class Volunteer implements Parcelable, Serializable {
    String name;
    String address;
    String phoneNum;
    String guardian;
    ArrayList<String> physicalLimitations;
    String signUpDate;
    boolean signedIn;

    HashMap<String, Double> map = new HashMap<>();

    int currentHoursWorked;
    int weeklyHoursWorked;

    transient Date startTime;
    transient Date endTime;

    public ToServerVolunteerData getToServerVolunteerData(){
        return new ToServerVolunteerData(name, map);
    }

    public void addNewVolunteerEntry(TimeWorked timeWorked){
        Day key = Day.getInstance();
        double hoursDecimal = timeWorked.hours + (timeWorked.minutes/60);
        if(map.containsKey(key.toString()))
        map.put(key.toString(), hoursDecimal);
    }

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(address);
        dest.writeString(phoneNum);
        if (physicalLimitations == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(physicalLimitations);
        }
        dest.writeString(signUpDate);
        dest.writeByte((byte) (signedIn ? 0x01 : 0x00));
        dest.writeInt(currentHoursWorked);
        dest.writeInt(weeklyHoursWorked);
        dest.writeLong(startTime != null ? startTime.getTime() : -1L);
        dest.writeLong(endTime != null ? endTime.getTime() : -1L);
        dest.writeString(guardian);
    }


    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Volunteer> CREATOR = new Parcelable.Creator<Volunteer>() {
        @Override
        public Volunteer createFromParcel(Parcel in) {
            return new BenevolentVolunteer(in);
        }

        @Override
        public Volunteer[] newArray(int size) {
            return new Volunteer[size];
        }
    };
}