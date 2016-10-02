package com.example.yifan.hopeforhungerapp.sign_in_activity_classes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
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

    private static final String LOG_TAG = SignInActivity.class.getSimpleName();

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_new_volunteer, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.add_new_volunteer:
                Log.i(LOG_TAG, "going to waiver activity");
                Intent waiverIntent = new Intent(getApplicationContext(), WaiverActivity.class);
                startActivityForResult(waiverIntent, 1);
                return true;

            default:
                return false;
        }
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
