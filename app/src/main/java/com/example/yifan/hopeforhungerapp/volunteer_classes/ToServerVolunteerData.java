package com.example.yifan.hopeforhungerapp.volunteer_classes;

import com.google.gson.Gson;

import java.util.HashMap;

/**
 * Created by Yifan on 10/2/2016.
 */

public class ToServerVolunteerData {
    private String name;
    private HashMap<String, Double> workedHoursPerDay;

    public ToServerVolunteerData(String name, HashMap<String, Double> map){
        this.name = name;
        this.workedHoursPerDay = new HashMap<>(map);
    }

    public String toJson(){
        Gson gson = new Gson();
        String gsonStr = gson.toJson(this);
        return gsonStr;
    }
}
