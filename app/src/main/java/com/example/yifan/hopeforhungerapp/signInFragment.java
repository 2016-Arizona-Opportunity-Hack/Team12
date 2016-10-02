package com.example.yifan.hopeforhungerapp;

/**
 * Created by TonysPC on 10/1/2016.
 */
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.yifan.hopeforhungerapp.sign_in_activity_classes.SignInActivity;
import com.example.yifan.hopeforhungerapp.volunteer_classes.Volunteer;

import java.util.Date;

/*
When the user clicks on one of the cells in the ListView it will inflate this fragment
 */
//inherit from fragment class
public class SignInFragment extends Fragment {

    private static final String VOLUNTEER = "Volunteer";

    private SignInActivity hostActivity;
    private View view;
    private Volunteer volunteer;

    //pass a Volunteer object to the fragment, along with String 'name' and boolean 'signedIn'
    public static SignInFragment newInstance(Volunteer volunteer){
        SignInFragment sIF = new SignInFragment();
        Bundle bundle = new Bundle();   //Use bundle to pass data to fragment
        bundle.putParcelable(VOLUNTEER, volunteer);
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
        volunteer = bundle.getParcelable(VOLUNTEER);

        String name = volunteer.getName();
        boolean vSignStatus = volunteer.isSignedIn();
        int currentHours = volunteer.getCurrentHoursWorked();
        int weeklyHours = volunteer.getWeeklyHoursWorked();

        //Display everything in the layout

        setButtonText(vSignStatus);
        hourLabelsSet(currentHours, weeklyHours);
        textViewSetUp(name);
        setupButton(vSignStatus);
        return view;



    }

    //Display the current hours and weekly hours worked
    private void hourLabelsSet(int currentHours, int weeklyHours)
    {
        TextView curHours = (TextView)view.findViewById(R.id.curHoursField);
        TextView weekHours = (TextView)view.findViewById(R.id.weeklyHoursField);
        String temp;
        if(volunteer.isSignedIn()){
            TimeWorked diff = ApplicationConstants.getTimeDifference(volunteer.getStartTime(), volunteer.getEndTime());
            temp = "volunteer time today: " + String.valueOf(diff.hours) + "hrs " + String.valueOf(diff.minutes) + "mins " + String.valueOf(diff.seconds) + "seconds";
            curHours.setText(temp);
            volunteer.addNewVolunteerEntry(diff);
        }

        temp = "accumulated weekly hours: " + String.valueOf(weeklyHours);
        weekHours.setText(temp);
    }
    //Set the button's text based on volunteer sign-in status
    private void setButtonText(boolean signStatus)
    {
        Button button = (Button)view.findViewById(R.id.signButton);
        if(signStatus){
            volunteer.setEndTime(new Date());
            button.setText("Sign Out");
        }else{
            volunteer.setStartTime(new Date());
            button.setText("Sign In");
        }
    }

    //Display the volunteer's name in the textView
    private void textViewSetUp(String volunteerName){
        TextView vName = (TextView)view.findViewById(R.id.nameField);
        vName.setText(volunteerName);
    }

    /*Create a button listener that returns a boolean value which will be set to true if the user 'Signs In'
    or false if the button is 'Signs Out'.
    Also, if the user wishes to sign out, a textView will appear asking the user if they want to sign out
     */
    private void setupButton(final boolean signedInStatus){
        Button signInButton = (Button)view.findViewById(R.id.signButton);
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //set the signed in status to false if current value of signed in is true
                if(signedInStatus){

                    volunteer.setSignedIn(false);
                }else{  //Make toast for this later
                    volunteer.setSignedIn(true);
                }
                hostActivity.signIn();
            }
        });


    }
}
