package com.example.organizeupv3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.organizeupv3.SQL.DBHelper;
import com.example.organizeupv3.helpers.InputValidation;
import com.example.organizeupv3.models.User;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    private final AppCompatActivity activity = RegisterActivity.this;

    //Declaration of components
    private EditText etEmail;
    private EditText etFirstname;
    private EditText etLastname;
    private EditText etPassword;
    private EditText etRepeatPassword;
    private Button btnRegister;

    private InputValidation inputValidation;
    private DBHelper databaseHelper;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().hide();

        initializeViews();
        initializeListeners();
        initializeObjects();
    }

    //Initialize Views
    private void initializeViews() {
        etEmail = findViewById(R.id.etEmailRegister);
        etFirstname = findViewById(R.id.etFirstname);
        etLastname = findViewById(R.id.etLastname);
        etPassword = findViewById(R.id.etPasswordRegister);
        etRepeatPassword = findViewById(R.id.etRepeatPassword);
        btnRegister = findViewById(R.id.btnRegister);
        TextView tvLinkLogin = findViewById(R.id.tvLinkLogin);
    }

    //Initialize Listeners
    private void initializeListeners() {
        btnRegister.setOnClickListener((View.OnClickListener) this);
    }

    //Initialize Objects
    private void initializeObjects() {
        inputValidation = new InputValidation(activity);
        databaseHelper = new DBHelper(activity);
        user = new User();
    }

    //When view is clicked action goes here
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnRegister:
                postDataToSQLite();
                break;
            case R.id.tvLinkLogin:
                Intent inLogin = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(inLogin);
                break;
        }
    }

    //Validate inputs and post data to SQLite
    private void postDataToSQLite() {

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

            if(!databaseHelper.addUser(user)){
                Toast.makeText(getApplicationContext(), "Something Went Wrong!Please Try again.", Toast.LENGTH_LONG).show();

            }else{
                Toast.makeText(getApplicationContext(), "Account Created Successfully!", Toast.LENGTH_LONG).show();

            }
            emptyInput();
        } else {
            Toast.makeText(getApplicationContext(), "Account Already Exists!", Toast.LENGTH_LONG).show();

        }

    }

    private void emptyInput() {
        etEmail.setText(null);
        etFirstname.setText(null);
        etLastname.setText(null);
        etPassword.setText(null);
        etRepeatPassword.setText(null);
    }

}