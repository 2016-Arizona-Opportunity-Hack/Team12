package com.example.yifan.hopeforhungerapp.sign_in_activity_classes;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yifan.hopeforhungerapp.R;
import com.example.yifan.hopeforhungerapp.volunteer_classes.Volunteer;

/**
 * A simple {@link Fragment} subclass.
 */
public class SignInFragment extends Fragment {

    private static final String INDEX = "INDEX";

    public SignInFragment() {
        // Required empty public constructor
    }

    public static SignInFragment newInstance(int volunteerIndex){
        SignInFragment instance = new SignInFragment();
        Bundle args = new Bundle();
        args.putInt(INDEX, volunteerIndex);
        instance.setArguments(args);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_in, container, false);
    }

}
