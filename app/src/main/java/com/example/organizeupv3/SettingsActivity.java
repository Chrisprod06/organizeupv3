package com.example.organizeupv3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.organizeupv3.SQL.DBHelper;
import com.example.organizeupv3.helpers.InputValidation;
import com.example.organizeupv3.models.User;

public class SettingsActivity extends AppCompatActivity implements View.OnClickListener {
    private final AppCompatActivity activity = SettingsActivity.this;
    private EditText etEmail;
    private EditText etFirstname;
    private EditText etLastname;
    private EditText etPassword;
    private EditText etRepeatPassword;
    private Button btnSave;

    private InputValidation inputValidation;
    private DBHelper databaseHelper;
    private User user;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Intent in = getIntent();
        user = (User) in.getSerializableExtra("UserDetails");

        initializeViews();
        initializeListeners();
        initializeObjects();

        //etEmail.setText(user.getEmail());
        //etFirstname.setText(user.getFirstname());
        //etLastname.setText(user.getLastname());
        //etPassword.setText(user.getPassword());
        //etRepeatPassword.setText(user.getPassword());


    }
    //Initialize Views
    private void initializeViews() {
        etEmail = findViewById(R.id.etEmailChange);
        etFirstname = findViewById(R.id.etFirstnameChange);
        etLastname = findViewById(R.id.etLastnameChange);
        etPassword = findViewById(R.id.etPasswordChange);
        etRepeatPassword = findViewById(R.id.etRepeatPasswordChange);
        btnSave = findViewById(R.id.btnSaveChangesUser);
    }

    //Initialize Listeners
    private void initializeListeners() {
        btnSave.setOnClickListener((View.OnClickListener) this);
    }

    //Initialize Objects
    private void initializeObjects() {
        inputValidation = new InputValidation(activity);
        databaseHelper = new DBHelper(activity);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnSaveChangesUser:
                postDataToSQLite();
                break;
        }
    }
    private void postDataToSQLite(){
        //Check if there are any empty edit texts
        if (!inputValidation.isInputEditTextFilled(etEmail)
                || !inputValidation.isInputEditTextFilled(etFirstname)
                || !inputValidation.isInputEditTextFilled(etLastname)
                || !inputValidation.isInputEditTextFilled(etPassword)
                || !inputValidation.isInputEditTextFilled(etRepeatPassword)) {
            Toast.makeText(getApplicationContext(), "Please fill all the required fields!", Toast.LENGTH_LONG).show();
            return;
        }


        //Check if email is correct type
        if (!inputValidation.isInputEditTextEmail(etEmail)) {
            return;
        }

        //Check if passwords match
        if (!inputValidation.isInputEditTextMatches(etPassword, etRepeatPassword)) {
            return;
        }

        if (!databaseHelper.checkUserEmail(etEmail.getText().toString().trim())) {
            user.setEmail(etEmail.getText().toString().trim());
            user.setFirstname(etFirstname.getText().toString().trim());
            user.setLastname(etLastname.getText().toString().trim());
            user.setPassword(etPassword.getText().toString().trim());

            if(!databaseHelper.updateUser(user)){
                Toast.makeText(getApplicationContext(), "Something Went Wrong!Please Try again.", Toast.LENGTH_LONG).show();

            }else{
                Toast.makeText(getApplicationContext(), "Account Created Successfully!", Toast.LENGTH_LONG).show();

            }
        } else {
            Toast.makeText(getApplicationContext(), "Account Already Exists!", Toast.LENGTH_LONG).show();

        }
    }
}