package com.example.yifan.hopeforhungerapp.volunteer_classes;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;


public class Volunteer implements Serializable, Parcelable{

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
    //Calculating the difference in hours between the two date objects
    private double getTimeDifference(){
        Date start = signInTime.getTime();  //set start date to signIn current time stamp
        Date end = signOutTime.getTime();   //set end date to signOut current time stamp
        double timeDifference = end.getTime() - start.getTime();    //Get the difference in time
        double hoursDifference = timeDifference / (1000*60*60) % 24;    //Get the difference in hours in double format
        //Create decimal format
        double formattedHours = Math.floor(hoursDifference * 100)/100;
        return formattedHours;






    }

    protected Volunteer(Parcel in) {
        name = in.readString();
        address = in.readString();
        phoneNum = in.readString();
        guardian = in.readString();
        signedIn = in.readByte() != 0x00;
        signInTime = (Calendar) in.readValue(Calendar.class.getClassLoader());
        signOutTime = (Calendar) in.readValue(Calendar.class.getClassLoader());
        hoursForCalanderDayMap = (HashMap) in.readValue(HashMap.class.getClassLoader());
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
        dest.writeString(guardian);
        dest.writeByte((byte) (signedIn ? 0x01 : 0x00));
        dest.writeValue(signInTime);
        dest.writeValue(signOutTime);
        dest.writeValue(hoursForCalanderDayMap);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Volunteer> CREATOR = new Parcelable.Creator<Volunteer>() {
        @Override
        public Volunteer createFromParcel(Parcel in) {
            return new Volunteer(in);
        }

        @Override
        public Volunteer[] newArray(int size) {
            return new Volunteer[size];
        }
    };




}