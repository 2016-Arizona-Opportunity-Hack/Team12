package com.example.yifan.hopeforhungerapp.volunteer_classes;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;


public class Volunteer implements Serializable, Parcelable{

    private static ArrayList<Volunteer> volunteers;
    private String firstName;
    private String lastName;
    private String address;
    private String phoneNum;
    private String guardian;

    private transient boolean signedIn;
    private transient Calendar signInTime;
    private transient Calendar signOutTime;

    private HashMap<String, Double> hoursForCalanderDayMap;

    private Volunteer(String firstName, String lastName, String address, String phoneNum, String guardian) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phoneNum = phoneNum;
        this.guardian = guardian;
        this.hoursForCalanderDayMap = new HashMap<>();
    }

    public void addNewVolunteer(String firstName, String lastName, String address, String phoneNum, String guardian){
        volunteers.add(new Volunteer(firstName, lastName, address, phoneNum, guardian));
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



    public static ArrayList<Volunteer> getVolunteers() {
        return volunteers;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public String getGuardian() {
        return guardian;
    }

    public boolean isSignedIn() {
        return signedIn;
    }

    public Calendar getSignInTime() {
        return signInTime;
    }

    public Calendar getSignOutTime() {
        return signOutTime;
    }
}