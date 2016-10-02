package com.example.yifan.hopeforhungerapp.volunteer_classes;

/**
 * Created by Yifan on 10/1/2016.
 */

public class BenevolentVolunteer extends Volunteer {
    private String guardian;

    public BenevolentVolunteer(String name, String address, String phoneNum, String guardian, String signUpDate){
        this.setName(name);
        this.setAddress(address);
        this.setPhoneNum(phoneNum);
        this.guardian = guardian;
        this.setSignUpDate(signUpDate);
    }
}
