package com.example.yifan.hopeforhungerapp.sign_in_activity_classes;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.yifan.hopeforhungerapp.ApplicationConstants;
import com.example.yifan.hopeforhungerapp.R;



public class WaiverActivity extends AppCompatActivity {

    private EditText dateField;
    private EditText firstNameField;
    private EditText lastNameField;
    private EditText guardianField;
    private EditText addressField;
    private EditText phoneField;
    private CheckBox signitureCheckBox;
    private Button submitBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waiver);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Waiver");
        toolbar.setTitleTextColor(0xFFFFFFFF);
        submitBtn = (Button) findViewById(R.id.submit_btn);
        signitureCheckBox = (CheckBox) findViewById(R.id.agreement_checkbox);
        addressField = (EditText) findViewById(R.id.address_field);
        guardianField = (EditText) findViewById(R.id.guardian_field);
        firstNameField = (EditText) findViewById(R.id.first_name_field);
        lastNameField = (EditText) findViewById(R.id.last_name_field);
        dateField = (EditText) findViewById(R.id.date_field);
        phoneField = (EditText) findViewById(R.id.phone_field);

    }

    public void onSubmitBtnClicked(View view){
        if(signitureCheckBox.isChecked()){
            String addressStr = addressField.getText().toString();
            String guardianStr = guardianField.getText().toString();
            String firstNameStr = firstNameField.getText().toString();
            String lastNameStr = lastNameField.getText().toString();
            String dateStr = dateField.getText().toString();
            String phoneStr = phoneField.getText().toString();
            final String error = "cannot be empty";

            if(TextUtils.isEmpty(addressStr)){
                addressField.setError(error);
            }
            if(TextUtils.isEmpty(firstNameStr)){
                firstNameField.setError(error);
            }
            if(TextUtils.isEmpty(lastNameStr)){
                lastNameField.setError(error);
            }
            if(TextUtils.isEmpty(dateStr)){
                dateField.setError(error);
            }
            if(TextUtils.isEmpty(phoneStr)){
                phoneField.setError(error);
            }
            else{ //all fields filled, pass back to sign in
                if(TextUtils.isEmpty(guardianStr)){
                    guardianStr = "no guardian";
                }
                Intent resultIntent = new Intent("intent");
                resultIntent.putExtra(ApplicationConstants.FIRST_NAME, firstNameStr);
                resultIntent.putExtra(ApplicationConstants.LAST_NAME, lastNameStr);
                resultIntent.putExtra(ApplicationConstants.ADDRESS, addressStr);
                resultIntent.putExtra(ApplicationConstants.DATE, dateStr);
                resultIntent.putExtra(ApplicationConstants.GUARDIAN, guardianStr);
                resultIntent.putExtra(ApplicationConstants.PHONE, phoneStr);
                setResult(Activity.RESULT_OK, resultIntent);
                finish();
            }
        }
        else{
            Toast.makeText(getApplicationContext(), "Need to agree to waiver (check the mark at the bottom)", Toast.LENGTH_SHORT).show();
        }
    }
}
