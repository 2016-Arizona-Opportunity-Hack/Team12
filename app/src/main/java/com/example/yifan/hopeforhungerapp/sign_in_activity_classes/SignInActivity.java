package com.example.yifan.hopeforhungerapp.sign_in_activity_classes;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.yifan.hopeforhungerapp.ApplicationConstants;
import com.example.yifan.hopeforhungerapp.R;
import com.example.yifan.hopeforhungerapp.SignInFragment;
import com.example.yifan.hopeforhungerapp.volunteer_classes.BenevolentVolunteer;
import com.example.yifan.hopeforhungerapp.volunteer_classes.ToServerVolunteerData;
import com.example.yifan.hopeforhungerapp.volunteer_classes.Volunteer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

public class SignInActivity extends AppCompatActivity implements SignInCommunicator{

    private ListView mVolunteers;
    private ArrayAdapter<Volunteer> volunteerArrayAdapter;
    private ArrayList<Volunteer> volunteers;
    private DrawerLayout drawer;

    private static final String LOG_TAG = SignInActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        Toolbar toolbar = (Toolbar) findViewById(R.id.sign_in_toolbar);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        setSupportActionBar(toolbar);
        if(toolbar != null){
            toolbar.setTitle("Sign In");
            toolbar.setTitleTextColor(0xFFFFFFFF);
        }
        volunteers = new ArrayList<>();
        //createDummyData();
        volunteerArrayAdapter = new VolunteerAdapter(getApplicationContext(), R.layout.single_volunteer_layout, volunteers);
        mVolunteers = (ListView) findViewById(R.id.volunteer_listview);
        mVolunteers.setAdapter(volunteerArrayAdapter);
        mVolunteers.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d(LOG_TAG, "item cliked position: " + position);
                Volunteer selected = volunteers.get(position);
                Fragment f = getSupportFragmentManager().findFragmentByTag("sign_in_fragment");
                if(f != null && f instanceof SignInFragment){
                    SignInFragment fragment = SignInFragment.newInstance(selected);
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.sign_in_fragment_container, fragment, "sign_in_fragment")
                            .commit();
                    drawer.openDrawer(Gravity.RIGHT);
                }
                else{
                    SignInFragment fragment = SignInFragment.newInstance(selected);
                    getSupportFragmentManager().beginTransaction()
                            .add(R.id.sign_in_fragment_container, fragment, "sign_in_fragment")
                            .commit();
                    drawer.openDrawer(Gravity.RIGHT);
                }


            }
        });
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
            case R.id.upload_data:
                Log.i(LOG_TAG, "upload");
                ArrayList<ToServerVolunteerData> data = new ArrayList<>();
                for(Volunteer vol: volunteers){
                    data.add(vol.getToServerVolunteerData());
                }
                GsonBuilder builder = new GsonBuilder();
                builder.setPrettyPrinting();
                Gson gson = builder.create();
                String json = gson.toJson(data);
                Log.i(LOG_TAG, json);



            default:
                return false;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1 && resultCode == Activity.RESULT_OK){
            String addressStr = data.getStringExtra(ApplicationConstants.ADDRESS);
            String guardianStr = data.getStringExtra(ApplicationConstants.GUARDIAN);
            String nameStr = data.getStringExtra(ApplicationConstants.NAME);
            String dateStr = data.getStringExtra(ApplicationConstants.DATE);
            String phoneStr = data.getStringExtra(ApplicationConstants.PHONE);
            volunteers.add(new BenevolentVolunteer(nameStr, addressStr, phoneStr, guardianStr, dateStr));
            volunteerArrayAdapter.notifyDataSetChanged();
        }
    }

    private void createDummyData(){
        for(int i = 0; i < 5; i++){
            volunteers.add(new BenevolentVolunteer("Person" + i, "address", "12345", "asdf", "sadfadsf"));
        }
    }

    @Override
    public void signIn() {
        volunteerArrayAdapter.notifyDataSetChanged();
        drawer.closeDrawer(Gravity.RIGHT);
    }
}
