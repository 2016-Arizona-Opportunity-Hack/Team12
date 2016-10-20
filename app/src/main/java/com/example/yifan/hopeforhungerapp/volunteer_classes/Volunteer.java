package com.example.yifan.hopeforhungerapp.volunteer_classes;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import com.example.yifan.hopeforhungerapp.ApplicationConstants;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;


public class Volunteer implements Serializable, Parcelable{

    private static final String LOG_TAG = Volunteer.class.getSimpleName();
    private static ArrayList<Volunteer> volunteers = new ArrayList<>();
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

    public static void addNewVolunteer(String firstName, String lastName, String address, String phoneNum, String guardian){
        volunteers.add(new Volunteer(firstName, lastName, address, phoneNum, guardian));
    }

    public void signIn(){
        signedIn = true;
        signInTime = GregorianCalendar.getInstance();
    }

    public void signOut(Calendar signOutTimeStamp){
        signedIn = false;
        if(signInTime == null){

            Log.e(LOG_TAG, "signInTime Null");

        }
        else{
            this.signOutTime = signOutTimeStamp;
            String monthDayYear = ApplicationConstants.convertCalanderObjToMMDDYYYY(signInTime);
            double hoursWorked = ApplicationConstants.getTimeDifference(signInTime, signOutTime);
            if(hoursForCalanderDayMap.containsKey(monthDayYear)){
                hoursForCalanderDayMap.put(monthDayYear, hoursForCalanderDayMap.get(monthDayYear) + hoursWorked); //in case of multiple sign in in one day.
            }
            else{
                hoursForCalanderDayMap.put(monthDayYear, hoursWorked);
            }

        }

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

    protected Volunteer(Parcel in) {
        firstName = in.readString();
        lastName = in.readString();
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
        dest.writeString(firstName);
        dest.writeString(lastName);
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