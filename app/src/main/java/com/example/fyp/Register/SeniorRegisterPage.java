package com.example.fyp.Register;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.app.DatePickerDialog;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.fyp.Database.DataBaseHelper;
import com.example.fyp.Login.LoginPage;
import com.example.fyp.ObjectClass.Users;
import com.example.fyp.R;

import java.util.Calendar;
import java.util.regex.*;

public class SeniorRegisterPage extends AppCompatActivity {

    public DatePickerDialog picker;
    public EditText dob;
    public ImageView arrowBack;

    public Button registerButton;
    public EditText username;
    public EditText password;
    public EditText fullname;
    public EditText phoneNo;
    public EditText address;
    public DataBaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_senior_register_page);

        // get date of birth pop up
        dob = (EditText) findViewById(R.id.register_dob);
        dob.setInputType(InputType.TYPE_NULL);
        dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                picker = new DatePickerDialog(SeniorRegisterPage.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                dob.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, year, month, day);
                picker.show();
            }
        });

        // returns to the previous page
        arrowBack = (ImageView) findViewById(R.id.returnArrow);
        arrowBack.setOnClickListener(v -> {

            Intent returnPage = new Intent(this, LoginPage.class); // new Intent (current, to the page you want to go)
            startActivity(returnPage);
        });


        // for registering, getting IDs first then database or creating a new object after onclick

        registerButton = findViewById(R.id.registerElderButton);
        username = findViewById(R.id.registerUsername);
        password = findViewById(R.id.registerPassword);
        fullname = findViewById(R.id.registerFullName);
        phoneNo = findViewById(R.id.register_phoneNo);
        address = findViewById(R.id.register_address);

        registerButton.setOnClickListener(v -> {
            Users User;
            boolean usernamePass = false;
            boolean passwordPass = false;
            boolean phonePass = false;
            // if this is empty, you show the error message
            if (checkIfEmpty() == true) {
                Toast.makeText(this, "You did not enter all fields.", Toast.LENGTH_LONG).show();
                return;
            } else {
                // check for validation here
                // creates a new User object
                databaseHelper = new DataBaseHelper(SeniorRegisterPage.this);
                String u = username.getText().toString();
                if (checkAll(u, password.getText().toString(), phoneNo.getText().toString())) {
                    User = new Users(username.getText().toString(), password.getText().toString(), fullname.getText().toString(), dob.getText().toString(),
                            Integer.parseInt(phoneNo.getText().toString()), "User", address.getText().toString());
                    boolean added = databaseHelper.addUser(User);
                    if (added == false) {
                        Toast.makeText(SeniorRegisterPage.this, "Failed to register.", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(SeniorRegisterPage.this, "Registered Successfully.", Toast.LENGTH_SHORT).show();
                        Intent returnToLogin = new Intent(this, LoginPage.class);
                        startActivity(returnToLogin);
                    }
                }
                else {
                    Toast.makeText(this, validateMessage(), Toast.LENGTH_LONG).show();
                }
            }

        });

    }

    private boolean checkIfEmpty() {
        if (username.getText().toString().matches("") || password.getText().toString().matches("") || fullname.getText().toString().matches("") ||
                phoneNo.getText().toString().matches("") || address.getText().toString().matches("") || dob.getText().toString().matches("")) {
            return true;
        } else return false;
    }

    //^                                   # start of line
    //  (?=.*[0-9])                       # positive lookahead, digit [0-9]
    //  (?=.*[a-z])                       # positive lookahead, one lowercase character [a-z]
    //  (?=.*[A-Z])                       # positive lookahead, one uppercase character [A-Z]
    //  (?=.*[!@#&()–[{}]:;',?/*~$^+=<>]) # positive lookahead, one of the special character in this [..]
    //  .                                 # matches anything
    //  {8,20}                            # length at least 8 characters and maximum of 20 characters
    //$                                   # end of line
    private boolean checkValidPassword(String password) {
        String regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(password);
        return m.matches();
    }

    //starts with 8 or 9 and at least 8 digits
    private boolean checkValidPhoneNumber(String phoneNumber) {
        String regex = "^[89]\\d{7}$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(phoneNumber);
        return m.matches();
    }


    private String validateMessage() {
        String word = "";
        if (databaseHelper.checkUsernameDuplicate(username.getText().toString()) != 0) {
            word+= "Username not available.\n";
        }
        if(!username.getText().toString().matches("[\\s]")) {
            word+= "Username cannot have spaces.";
        }
        if ((!checkValidPassword(password.getText().toString()))) {
            word += "Password does not meet requirement.\n";
        }
        if ((!checkValidPhoneNumber(phoneNo.getText().toString())) || (databaseHelper.checkUserPhoneNo(phoneNo.getText().toString()) != 0) ) {
            word += "Invalid phone number/phone number registered before";
        }
        return word;
    }

    // checks all at once
    private boolean checkAll(String u, String pass, String phoneNumber) {
        if (u.matches("[\\s]") && databaseHelper.checkUsernameDuplicate(u) == 0 && checkValidPassword(pass) && checkValidPhoneNumber(phoneNumber) && databaseHelper.checkUserPhoneNo(phoneNumber) == 0) {
            return true;
        }
        else return false;
    }

}