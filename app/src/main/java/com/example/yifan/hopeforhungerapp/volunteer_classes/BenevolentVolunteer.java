package com.example.yifan.hopeforhungerapp.volunteer_classes;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Yifan on 10/1/2016.
 */

public class BenevolentVolunteer extends Volunteer {

    public BenevolentVolunteer(String name, String address, String phoneNum, String guardian, String signUpDate){
        this.setName(name);
        this.setAddress(address);
        this.setPhoneNum(phoneNum);
        this.guardian = guardian;
        this.setSignUpDate(signUpDate);
    }

    public BenevolentVolunteer(Parcel in) {
        name = in.readString();
        address = in.readString();
        phoneNum = in.readString();
        if (in.readByte() == 0x01) {
            physicalLimitations = new ArrayList<String>();
            in.readList(physicalLimitations, String.class.getClassLoader());
        } else {
            physicalLimitations = null;
        }
        signUpDate = in.readString();
        signedIn = in.readByte() != 0x00;
        currentHoursWorked = in.readInt();
        weeklyHoursWorked = in.readInt();
        long tmpStartTime = in.readLong();
        startTime = tmpStartTime != -1 ? new Date(tmpStartTime) : null;
        long tmpEndTime = in.readLong();
        endTime = tmpEndTime != -1 ? new Date(tmpEndTime) : null;
        this.guardian = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
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
