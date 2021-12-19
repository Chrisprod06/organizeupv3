package com.example.organizeupv3.helpers;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;


public class InputValidation {
    private Context Context;

    //Constructor
    public InputValidation(Context context) {
        this.Context = context;
    }

    //Check if input edit text is filled
    public boolean isInputEditTextFilled(EditText EditText) {
        String value = EditText.getText().toString().trim();
        if (value.isEmpty()) {
            hideKeyboardFrom(EditText);
            return false;
        } else {
            return true;
        }
    }

    public boolean isInputEditTextEmail(EditText EditText) {
        String value = EditText.getText().toString().trim();
        if (value.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(value).matches()) {
            hideKeyboardFrom(EditText);
            return false;
        } else {
        }
        return true;
    }

    public boolean isInputEditTextMatches(EditText EditText1,EditText EditText2) {
        String value1 = EditText1.getText().toString().trim();
        String value2 = EditText2.getText().toString().trim();
        if (!value1.contentEquals(value2)) {
            hideKeyboardFrom(EditText2);
            return false;
        } else {
        }
        return true;
    }
    //method to Hide keyboard

    private void hideKeyboardFrom(View view) {
        InputMethodManager imm = (InputMethodManager) Context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

    private void hideKeyboardFrom(EditText EditText) {
    }
}
