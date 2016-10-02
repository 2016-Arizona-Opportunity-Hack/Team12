package com.example.yifan.hopeforhungerapp.sign_in_activity_classes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.yifan.hopeforhungerapp.R;
import com.example.yifan.hopeforhungerapp.volunteer_classes.BenevolentVolunteer;
import com.example.yifan.hopeforhungerapp.volunteer_classes.Volunteer;

import java.util.ArrayList;

public class SignInActivity extends AppCompatActivity implements SignInCommunicator{

    private ListView mVolunteers;
    private ArrayAdapter<Volunteer> volunteerArrayAdapter;
    private ArrayList<Volunteer> volunteers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        Toolbar toolbar = (Toolbar) findViewById(R.id.sign_in_toolbar);
        setSupportActionBar(toolbar);
        if(toolbar != null){
            toolbar.setTitle("Sign In");
            toolbar.setTitleTextColor(0xFFFFFFFF);
        }
        volunteers = new ArrayList<>();
        createDummyData();
        volunteerArrayAdapter = new VolunteerAdapter(getApplicationContext(), R.layout.single_volunteer_layout, volunteers);
        mVolunteers = (ListView) findViewById(R.id.volunteer_listview);
        mVolunteers.setAdapter(volunteerArrayAdapter);
    }



    private void createDummyData(){
        for(int i = 0; i < 5; i++){
            volunteers.add(new BenevolentVolunteer("Person" + i, "address", "12345"));
        }
    }

    @Override
    public void signIn(Volunteer newVolunteer) {

    }
}
