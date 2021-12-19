package com.example.organizeupv3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.organizeupv3.SQL.DBHelper;
import com.example.organizeupv3.helpers.InputValidation;
import com.example.organizeupv3.models.Place;
import com.example.organizeupv3.models.User;

public class CreatePlaceActivity extends AppCompatActivity implements View.OnClickListener {
    private final AppCompatActivity activity = CreatePlaceActivity.this;

    //Declaration of components
    EditText etPlacename;
    EditText etCapacity;
    EditText etAddress;
    EditText etOpenTime;
    EditText etCloseTime;
    EditText etPhone;
    CheckBox chkBar;
    CheckBox chkCafe;
    CheckBox chkRestaurant;
    Button btnCreatePlace;

    public InputValidation inputValidation;
    public DBHelper dbHelper;
    public User user;
    public Place place;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_place);
        Intent in = getIntent();
        user = (User) in.getSerializableExtra("userDetails");

        initializeViews();
        initializeListeners();
        initializeObjects();
    }

    //Initialize Views
    private void initializeViews() {
        etPlacename = findViewById(R.id.etPlaceName);
        etCapacity = findViewById(R.id.etCapacity);
        etAddress = findViewById(R.id.etPlaceAddress);
        etOpenTime = findViewById(R.id.etOpenTime);
        etCloseTime = findViewById(R.id.etCloseTime);
        etPhone = findViewById(R.id.etPhone);
        chkBar = findViewById(R.id.chkBar);
        chkCafe = findViewById(R.id.chkCafe);
        chkRestaurant=findViewById(R.id.chkRestaurant);
        btnCreatePlace = findViewById(R.id.btnCreatePlace);
    }
    //Initialize Listeners
    private void initializeListeners() {
        btnCreatePlace.setOnClickListener((View.OnClickListener) this);
    }

    //Initialize Objects
    private void initializeObjects() {
        inputValidation = new InputValidation(activity);
        dbHelper = new DBHelper(activity);
        place = new Place();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnCreatePlace:
                postDataToSQLite();
                break;
        }

    }

    //Validate inputs and post data to SQLite
    private void postDataToSQLite() {
        String type="";
        //Check if there are any empty edit texts
        if(!inputValidation.isInputEditTextFilled(etPlacename)
        ||!inputValidation.isInputEditTextFilled(etCapacity)
        ||!inputValidation.isInputEditTextFilled(etAddress)
        ||!inputValidation.isInputEditTextFilled(etOpenTime)
        ||!inputValidation.isInputEditTextFilled(etCloseTime)
        ||!inputValidation.isInputEditTextFilled(etPhone)){
            Toast.makeText(getApplicationContext(), "Please fill all the required fields!", Toast.LENGTH_LONG).show();
            return;
        }
        place.setOwnerID(user.getUserID());
        place.setAddress(etAddress.getText().toString().trim());
        place.setCapacity(Integer.parseInt(etCapacity.getText().toString().trim()));
        place.setAddress(etAddress.getText().toString().trim());
        place.setOpenTime(etOpenTime.getText().toString().trim());
        place.setCloseTime(etCloseTime.getText().toString().trim());
        place.setPhone(Integer.parseInt(etPhone.getText().toString().trim()));

        if(chkBar.isChecked()){
            type +=" Bar ";
        }
        if(chkCafe.isChecked());{
            type+=" Cafe ";
        }
        if(chkRestaurant.isChecked()){
            type+=" Restaurant ";

        }

        place.setType(type);

        if(!dbHelper.addPlace(place)){
            Toast.makeText(getApplicationContext(), "Something Went Wrong!Please Try again.", Toast.LENGTH_LONG).show();

        }else{
            Toast.makeText(getApplicationContext(), "Place Created Successfully!", Toast.LENGTH_LONG).show();
            emptyInput();

        }



    }

    private void emptyInput() {
         etPlacename.setText(null);
         etCapacity.setText(null);;
         etAddress.setText(null);;
         etOpenTime.setText(null);;
         etCloseTime.setText(null);;
         etPhone.setText(null);;
         chkBar.setChecked(false);
         chkCafe.setChecked(false);
         chkRestaurant.setChecked(false);
    }
}