package com.example.yifan.hopeforhungerapp.sign_in_activity_classes;


import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.yifan.hopeforhungerapp.ApplicationConstants;
import com.example.yifan.hopeforhungerapp.R;
import com.example.yifan.hopeforhungerapp.volunteer_classes.Volunteer;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;


/**
 * Fragment used to sign in/out Volunteers
 */
public class SignInFragment extends Fragment {

    private static final String VOLUNTEER = "VOLUNTEER";
    private static final String LOG_TAG = SignInFragment.class.getSimpleName();
    private View fragmentView;
    private Calendar currentTime;
    private TextView firstNameTV;
    private TextView lastNameTV;
    private TextView signInOutDirections;
    private FloatingActionButton signInOrOutFAB;
    private TextView dateTV;
    private TextView checkInTV;
    private TextView currentTimeTV;
    private TextView elapsedTimeTV;
    private SignInCommunicator hostActivity;
    private Volunteer selectedVolunteer;

    public SignInFragment() {
        // Required empty public constructor
    }

    public static SignInFragment newInstance(Volunteer volunteer){
        SignInFragment instance = new SignInFragment();
        Bundle args = new Bundle();
        args.putParcelable(VOLUNTEER, volunteer);
        instance.setArguments(args);
        return instance;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentView = inflater.inflate(R.layout.fragment_sign_in, container, false);
        hostActivity = (SignInCommunicator) getActivity();
        this.selectedVolunteer = getArguments().getParcelable(VOLUNTEER);
        currentTime = GregorianCalendar.getInstance();
        if(selectedVolunteer == null){
            Log.e(LOG_TAG, "volunteer was null");
            getActivity().getSupportFragmentManager().beginTransaction().remove(this).commit();
        }
        firstNameTV = (TextView) fragmentView.findViewById(R.id.first_name_tv);
        lastNameTV = (TextView) fragmentView.findViewById(R.id.last_name_tv);
        signInOutDirections = (TextView) fragmentView.findViewById(R.id.sign_in_out_directions);
        signInOrOutFAB = (FloatingActionButton) fragmentView.findViewById(R.id.sign_in_fab);
        dateTV = (TextView) fragmentView.findViewById(R.id.date_tv);
        checkInTV = (TextView) fragmentView.findViewById(R.id.check_in_tv);
        currentTimeTV = (TextView) fragmentView.findViewById(R.id.check_out_tv);
        elapsedTimeTV = (TextView) fragmentView.findViewById(R.id.elapsed_time_tv);


        firstNameTV.setText(selectedVolunteer.getFirstName());
        lastNameTV.setText(selectedVolunteer.getLastName());

        dateTV.setText(ApplicationConstants.convertCalanderObjToMMDDYYYY(GregorianCalendar.getInstance()));

        setUpContextuals();
        currentTimeTV.setText(ApplicationConstants.getTimeStampForCalanderObj(currentTime));

        return fragmentView;
    }

    /**
     * sets up everything that depends on whether the volunteer is signing in or out.
     */
    private void setUpContextuals(){
        final boolean isSignedIn = selectedVolunteer.isSignedIn();
        signInOrOutFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isSignedIn){
                    selectedVolunteer.signOut(currentTime);
                }
                else{
                    selectedVolunteer.signIn();
                }
                hostActivity.signInStatusChangedCallback();
            }
        });
        if(isSignedIn){
            signInOutDirections.setText(getString(R.string.sign_out));
            signInOutDirections.setTextColor(Color.RED);
            signInOrOutFAB.setImageResource(R.mipmap.x_mark);
            String formatedElapsedTime = ApplicationConstants.getTimeDifference(selectedVolunteer.getSignInTime(), currentTime) + "Hrs";
            Calendar rawSignInData = selectedVolunteer.getSignInTime();
            String signInTime = ApplicationConstants.getTimeStampForCalanderObj(rawSignInData);
            checkInTV.setText(signInTime);
            elapsedTimeTV.setText(formatedElapsedTime);
        }
        else{
            signInOutDirections.setText(getString(R.string.sign_in));
            signInOutDirections.setTextColor(Color.GREEN);
            signInOrOutFAB.setImageResource(R.mipmap.check_mark);
            checkInTV.setText("Not signed in");
            elapsedTimeTV.setText("N/A");
        }
    }





}
