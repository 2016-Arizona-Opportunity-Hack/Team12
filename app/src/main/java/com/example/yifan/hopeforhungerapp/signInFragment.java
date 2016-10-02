package com.example.yifan.hopeforhungerapp;

/**
 * Created by TonysPC on 10/1/2016.
 */
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.TextViewCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.EditText;

import com.example.yifan.hopeforhungerapp.sign_in_activity_classes.SignInActivity;
import com.example.yifan.hopeforhungerapp.volunteer_classes.Volunteer;

import static android.R.attr.button;

/*
When the user clicks on one of the cells in the ListView it will inflate this fragment
 */
//inherit from fragment class
public class signInFragment extends Fragment {

    private static final String VOLUNTEER_NAME = "Name";
    private static final String SIGN_IN_BOOL = "signedIn";
    private static final String CURRENT_HOURS = "0";
    private static final String WEEKLY_HOURS = "0";

    private SignInActivity hostActivity;

    private View view;

    //pass a Volunteer object to the fragment, along with String 'name' and boolean 'signedIn'
    public static signInFragment newInstance(Volunteer volunteer){
        signInFragment sIF = new signInFragment();
        Bundle bundle = new Bundle();   //Use bundle to pass data to fragment
        bundle.putString(VOLUNTEER_NAME, volunteer.getName());      //gets the name from the volunteer
        bundle.putBoolean(SIGN_IN_BOOL, volunteer.isSignedIn());    //gets boolean signedIn value
        bundle.putInt(CURRENT_HOURS, volunteer.getCurrentHoursWorked());    //gets the current hours worked by volunteer
        bundle.putInt(WEEKLY_HOURS, volunteer.getWeeklyHoursWorked());  //gets weekly hours of volunteer
        sIF.setArguments(bundle);
        return sIF;

    }
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //Layout we are using for this fragment
        hostActivity = (SignInActivity) getActivity();
        view = inflater.inflate(R.layout.sign_in_fragment, container, false);
        Bundle bundle = getArguments();
        //Set the values that the bundle is going to return
        String name = bundle.getString(VOLUNTEER_NAME);
        boolean vSignStatus = bundle.getBoolean(SIGN_IN_BOOL);
        int currentHours = bundle.getInt(CURRENT_HOURS);
        int weeklyHours = bundle.getInt(WEEKLY_HOURS);
        //Display everything in the layout
        hourLabelsSet(currentHours, weeklyHours);
        setButtonText(vSignStatus);
        textViewSetUp(name);
        return view;
    }

    //Display the current hours and weekly hours worked
    private void hourLabelsSet(int currentHours, int weeklyHours)
    {
        TextView curHours = (TextView)view.findViewById(R.id.curHoursField);
        TextView weekHours = (TextView)view.findViewById(R.id.weeklyHoursField);
        curHours.setText(String.valueOf(currentHours));
        weekHours.setText(String.valueOf(weeklyHours));
    }
    //Set the button's text based on volunteer sign-in status
    private void setButtonText(boolean signStatus)
    {
        Button button = (Button)view.findViewById(R.id.signButton);
        if(signStatus){
            button.setText("Sign Out");
        }else{
            button.setText("Sign In");
        }
    }

    //Display the volunteer's name in the textView
    private void textViewSetUp(String volunteerName){
        TextView vName = (TextView)view.findViewById(R.id.nameField);
        vName.setText(volunteerName);
    }

    //Create a button listener
}
