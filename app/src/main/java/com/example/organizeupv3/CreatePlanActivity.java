package com.example.organizeupv3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.organizeupv3.SQL.DBHelper;
import com.example.organizeupv3.databinding.ActivityCreatePlanBinding;
import com.example.organizeupv3.helpers.InputValidation;
import com.example.organizeupv3.models.Place;
import com.example.organizeupv3.models.Plan;
import com.example.organizeupv3.models.User;

import java.util.List;

public class CreatePlanActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {
    private final AppCompatActivity activity = CreatePlanActivity.this;

    //Declaration of components
    Spinner spGroup;
    Spinner spPlace;
    EditText etPlanTime;
    EditText etPlanDate;
    EditText etMessage;
    Button btnCreatePlan;

    public InputValidation inputValidation;
    public DBHelper dbHelper;
    public User user;
    public Plan plan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_plan);
        Intent in = getIntent();
        user = (User) in.getSerializableExtra("userDetails");


        initializeObjects();
        initializeViews();
        initializeListeners();


    }

    //Initialize Views
    private void initializeViews() {
        spGroup = findViewById(R.id.spGroup);
        spPlace = findViewById(R.id.spPlace);
        etPlanTime = findViewById(R.id.etPlanTIme);
        etPlanDate = findViewById(R.id.etPlanDate);
        etMessage = findViewById(R.id.etMessage);
        btnCreatePlan = findViewById(R.id.btnCreatePlan);

        //load data to spinners
        loadSpinnerGroupsData();
        loadSpinnerPlacesData();
    }

    //Initialize Listeners
    private void initializeListeners() {
        btnCreatePlan.setOnClickListener((View.OnClickListener) this);
    }

    //Initialize Objects
    private void initializeObjects() {
        inputValidation = new InputValidation(activity);
        dbHelper = new DBHelper(activity);
        plan = new Plan();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnCreatePlan:
                postDataToSQLite();
                break;
        }

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        String label = parent.getItemAtPosition(position).toString();

        // Showing selected spinner item
        Toast.makeText(parent.getContext(), "You selected: " + label,
                Toast.LENGTH_LONG).show();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // TODO Auto-generated method stub
    }

    //functions to load spinner data from database

    private void loadSpinnerGroupsData() {
        List<String> labels = dbHelper.getAllLabelsGroups();
        //Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, labels);
        //Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Attaching data adapter to spinner
        spGroup.setAdapter(dataAdapter);
    }

    private void loadSpinnerPlacesData() {
        List<String> labels = dbHelper.getAllLabelsPlaces();
        //Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, labels);
        //Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Attaching data adapter to spinner
        spPlace.setAdapter(dataAdapter);
    }

    //Validate inputs and post data to SQLite
    private void postDataToSQLite() {
        //Check if empty input
        if (!inputValidation.isInputEditTextFilled(etPlanTime)
                || !inputValidation.isInputEditTextFilled(etPlanDate)
                || !inputValidation.isInputEditTextFilled(etMessage)) {
            Toast.makeText(getApplicationContext(), "Please fill all the required fields!", Toast.LENGTH_LONG).show();
            return;
        }

        plan.setGroupID(Integer.parseInt(spGroup.getSelectedItem().toString()));
        plan.setPlaceID(Integer.parseInt(spPlace.getSelectedItem().toString()));
        plan.setTime(etPlanTime.getText().toString().trim());
        plan.setDate(etPlanDate.getText().toString().trim());
        plan.setMessage(etMessage.getText().toString().trim());

        if (!dbHelper.addPlan(plan)) {
            Toast.makeText(getApplicationContext(), "Something Went Wrong!Please Try again.", Toast.LENGTH_LONG).show();

        } else {
            Toast.makeText(getApplicationContext(), "Plan Created Successfully!", Toast.LENGTH_LONG).show();
            emptyInput();

        }
    }

    private void emptyInput() {
        etPlanTime.setText(null);
        etPlanDate.setText(null);
        etMessage.setText(null);
    }
}