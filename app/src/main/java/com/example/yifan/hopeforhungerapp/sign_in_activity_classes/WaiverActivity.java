package com.example.yifan.hopeforhungerapp.sign_in_activity_classes;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.yifan.hopeforhungerapp.ApplicationConstants;
import com.example.yifan.hopeforhungerapp.R;
import com.example.yifan.hopeforhungerapp.volunteer_classes.Volunteer;


public class WaiverActivity extends AppCompatActivity {

    private static final String LOG_TAG = WaiverActivity.class.getSimpleName();
    private EditText firstNameField;
    private EditText lastNameField;
    private EditText groupNameField;
    private CheckBox signitureCheckBox;
    private Spinner volunteerTypeSpinner;
    Volunteer.VolunteerTypes selectedType;

    private final static String[] volunteerChoices = {"BENEVOLENT", "SNAP", "MAXIMUS", "GROUP"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waiver);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Waiver");
        toolbar.setTitleTextColor(0xFFFFFFFF);

        signitureCheckBox = (CheckBox) findViewById(R.id.agreement_checkbox);
        firstNameField = (EditText) findViewById(R.id.first_name_field);
        lastNameField = (EditText) findViewById(R.id.last_name_field);
        groupNameField = (EditText) findViewById(R.id.group_name_field);
        spinnerSetUp();
    }

    private void spinnerSetUp(){
        volunteerTypeSpinner = (Spinner) findViewById(R.id.volunteer_type_spinner);
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, volunteerChoices);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        volunteerTypeSpinner.setAdapter(spinnerAdapter);
        volunteerTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int spinnerPosition = volunteerTypeSpinner.getSelectedItemPosition();

                switch (spinnerPosition) {
                    case 0:
                        selectedType = Volunteer.VolunteerTypes.BENEVOLENT;
                        Log.d(LOG_TAG, selectedType.toString());
                        groupNameField.setVisibility(View.INVISIBLE);
                        break;
                    case 1:
                        selectedType = Volunteer.VolunteerTypes.SNAP;
                        Log.d(LOG_TAG, selectedType.toString());
                        groupNameField.setVisibility(View.INVISIBLE);
                        break;
                    case 2:
                        selectedType = Volunteer.VolunteerTypes.MAXIMUS;
                        Log.d(LOG_TAG, selectedType.toString());
                        groupNameField.setVisibility(View.INVISIBLE);
                        break;
                    case 3:
                        selectedType = Volunteer.VolunteerTypes.GROUP;
                        Log.d(LOG_TAG, selectedType.toString());
                        groupNameField.setVisibility(View.VISIBLE);
                        break;
                    default:
                        throw new UnsupportedOperationException("Invalid spinner selection");
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void onSubmitBtnClicked(View view){
        //first and last name fields need to be filled
        //spinner must be filled
        //is spinner is GROUP then group name must be filled
        if(signitureCheckBox.isChecked()){

            String firstNameStr = firstNameField.getText().toString();
            String lastNameStr = lastNameField.getText().toString();
            String groupNameStr = groupNameField.getText().toString();

            boolean firstNameFilled = false;
            boolean lastNameFilled = false;
            boolean spinnerFilled = false;
            //true if not signing a group.
            //true if signing a group, if group is not empty
            boolean groupFilled = selectedType != Volunteer.VolunteerTypes.GROUP;

            final String error = "field cannot be empty";
            if(TextUtils.isEmpty(firstNameStr)){
                firstNameField.setError(error);
            }
            else{
                firstNameFilled = true;
            }
            if(TextUtils.isEmpty(lastNameStr)){
                lastNameField.setError(error);
            }
            else{
                lastNameFilled = true;
            }
            if(volunteerTypeSpinner.getSelectedItemPosition() < 0){
                Toast.makeText(this, "Volunteer Type cannot be empty", Toast.LENGTH_LONG);
            }
            else{
                spinnerFilled = true;
            }
            if(selectedType == Volunteer.VolunteerTypes.GROUP && TextUtils.isEmpty(groupNameStr)){
                groupNameField.setError(error);
            }
            else{
                groupFilled = true;
            }

            if(firstNameFilled && lastNameFilled && spinnerFilled && groupFilled){
                //all fields filled, pass back to sign in
                Intent resultIntent = new Intent("intent");
                resultIntent.putExtra(ApplicationConstants.FIRST_NAME, firstNameStr);
                resultIntent.putExtra(ApplicationConstants.LAST_NAME, lastNameStr);
                resultIntent.putExtra(ApplicationConstants.VOLUNTEER_TYPE, selectedType);
                if(selectedType == Volunteer.VolunteerTypes.GROUP){
                    resultIntent.putExtra(ApplicationConstants.GROUP_NAME, groupNameStr);
                }
                setResult(Activity.RESULT_OK, resultIntent);
                finish();
            }


        }
        else{
            Toast.makeText(getApplicationContext(), "Need to agree to waiver (check the mark at the bottom)", Toast.LENGTH_SHORT).show();
        }
    }
}
