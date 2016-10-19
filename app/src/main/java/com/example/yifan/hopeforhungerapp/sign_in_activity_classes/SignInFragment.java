package com.example.yifan.hopeforhungerapp.sign_in_activity_classes;


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
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Fragment used to sign in/out Volunteers
 */
public class SignInFragment extends Fragment {

    private static final String VOLUNTEER = "VOLUNTEER";
    private static final String LOG_TAG = SignInFragment.class.getSimpleName();
    private View fragmentView;
    @BindView(R.id.first_name_tv) private TextView firstNameTV;
    @BindView(R.id.last_name_tv) private TextView lastNameTV;
    @BindView(R.id.sign_in_fab) private FloatingActionButton signInFAB;
    @BindView(R.id.date_tv) private TextView dateTV;
    @BindView(R.id.check_in_tv) private TextView checkInTV;
    @BindView(R.id.check_out_tv) private TextView currentTimeTV;
    @BindView(R.id.elapsed_time_tv) private TextView elapsedTimeTV;

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
        ButterKnife.bind(this, fragmentView);
        this.selectedVolunteer = getArguments().getParcelable(VOLUNTEER);
        if(selectedVolunteer == null){
            Log.e(LOG_TAG, "volunteer was null");
            getActivity().getSupportFragmentManager().beginTransaction().remove(this).commit();
        }

        firstNameTV.setText(selectedVolunteer.getFirstName());
        lastNameTV.setText(selectedVolunteer.getLastName());
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy", Locale.US);
        dateTV.setText(sdf.format(new Date()));

        Calendar rawSignInData = selectedVolunteer.getSignInTime();
        String signInTime = String.valueOf(rawSignInData.get(Calendar.HOUR)) + String.valueOf(rawSignInData.get(Calendar.MINUTE));
        checkInTV.setText(signInTime);


        return fragmentView;
    }



}
