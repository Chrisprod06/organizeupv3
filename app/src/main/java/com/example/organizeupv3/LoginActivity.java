package com.example.organizeupv3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.DatabaseErrorHandler;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.organizeupv3.SQL.DBHelper;
import com.example.organizeupv3.helpers.InputValidation;
import com.example.organizeupv3.models.User;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private final AppCompatActivity activity = LoginActivity.this;

    private EditText etEmailLogin;
    private EditText etPasswordLogin;

    private Button btnLogin;

    private TextView tvLinkRegister;

    private InputValidation inputValidation;
    private DBHelper dbHelper;

    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();

        initializeViews();
        initializeListeners();
        initializeObjects();


    }

    private void initializeObjects() {
        inputValidation = new InputValidation(activity);
        dbHelper = new DBHelper(activity);
        user = new User();
    }

    private void initializeListeners() {
        btnLogin.setOnClickListener(this);
        tvLinkRegister.setOnClickListener(this);
    }

    private void initializeViews() {

        etEmailLogin = findViewById(R.id.etEmailLogin);
        etPasswordLogin = findViewById(R.id.etPasswordLogin);
        btnLogin = findViewById(R.id.btnLogin);
        tvLinkRegister = findViewById(R.id.tvLinkRegister);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnLogin:
                verifyFromSQLite();
                break;
            case R.id.tvLinkRegister:
                Intent inRegister = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(inRegister);
                break;
        }

    }

    private void verifyFromSQLite() {

        //Check if there are any empty edit texts
        if (!inputValidation.isInputEditTextFilled(etEmailLogin)
                || !inputValidation.isInputEditTextFilled(etPasswordLogin)){
            Toast.makeText(getApplicationContext(),"Please fill all the required fields!",Toast.LENGTH_LONG).show();
            return;
        }

        //Check if email is correct type
        if(!inputValidation.isInputEditTextEmail(etEmailLogin)){
            return;
        }

        //Check if credentials match
        if(dbHelper.checkUserLogin(etEmailLogin.getText().toString().trim(),etPasswordLogin.getText().toString().trim())){
            //Get user details and place them into a bundle
            //Send email and retrieve all the other details

            Intent inNavigation = new Intent(getApplicationContext(),NavigationActivity.class);
            User user = dbHelper.getUserDetails(etEmailLogin.getText().toString().trim());

            inNavigation.putExtra("UserDetails", user);
            startActivity(inNavigation);
        }else{
            Toast.makeText(getApplicationContext(), "Wrong login credentials!", Toast.LENGTH_SHORT).show();
        }
    }
    private void emptyInputEditText() {
        etEmailLogin.setText(null);
        etPasswordLogin.setText(null);
    }
}