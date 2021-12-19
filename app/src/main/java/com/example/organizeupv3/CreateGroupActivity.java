package com.example.organizeupv3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.example.organizeupv3.SQL.DBHelper;
import com.example.organizeupv3.helpers.InputValidation;
import com.example.organizeupv3.models.Group;
import com.example.organizeupv3.models.User;

public class CreateGroupActivity extends AppCompatActivity implements View.OnClickListener {
    private final AppCompatActivity activity = CreateGroupActivity.this;

    //Declaration of components
    private EditText etGroupName;
    private Button btnCreateGroup;

    //Object declaration
    private InputValidation inputValidation;
    private DBHelper dbHelper;
    private Group group;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_group);

        //Get userDetails from previous activity
        Intent in = getIntent();
        user = (User) in.getSerializableExtra("userDetails");


        initializeViews();
        initializeListeners();
        initializeObjects();
    }

    private void initializeViews() {
        etGroupName = findViewById(R.id.etGroupName);
        btnCreateGroup = findViewById(R.id.btnCreateGroup);
    }

    private void initializeListeners() {
        btnCreateGroup.setOnClickListener((View.OnClickListener)this);
    }

    private void initializeObjects() {
        inputValidation = new InputValidation(activity);
        dbHelper = new DBHelper(activity);
        group = new Group();
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnCreateGroup:
                postDataToSQLite();
                break;
        }
    }

    private void postDataToSQLite() {
        //Check if edit text is empty
        if(!inputValidation.isInputEditTextFilled(etGroupName)){
            Toast.makeText(getApplicationContext(), "Please fill all the required fields!", Toast.LENGTH_LONG).show();
            return;

        }
        //Set group attributes and send them for insert to database
        group.setCreatorID(user.getUserID());
        group.setGroupName(etGroupName.getText().toString().trim());
        dbHelper.addGroup(group);
    }
}