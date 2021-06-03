package com.example.fyp.Admin.AdminPage;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;


import com.example.fyp.Database.DataBaseHelper;
import com.example.fyp.ObjectClass.Users;
import com.example.fyp.R;

import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CreateAdmin extends AppCompatActivity {

    public EditText adminUsername;
    public EditText adminPassword;
    public EditText adminFullName;
    public EditText dob;
    public EditText adminPhoneNo;
    public Button CreateAdmin;
    public ImageView returnArrow;
    public DatePickerDialog picker;
    public DataBaseHelper databaseHelper;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_admin);
        String uniqueString = getIntent().getStringExtra("username");

        // return to prev page
        returnArrow = (ImageView) findViewById(R.id.adminArrowBack4);
        returnArrow.setOnClickListener(v -> {
            Intent returnprev = new Intent(this, ManageAdminPage.class);
            returnprev.putExtra("username", uniqueString);
            startActivity(returnprev);

        });

        // dob date picker
        dob = (EditText) findViewById(R.id.adminDOBinput);
        dob.setInputType(InputType.TYPE_NULL);
        dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                picker = new DatePickerDialog(CreateAdmin.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                dob.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, year, month, day);
                picker.show();
            }
        });

        // registering admin
        adminUsername = findViewById(R.id.adminUserInput);
        adminPassword = findViewById(R.id.adminPassInput);
        adminFullName = findViewById(R.id.adminNameInput);
        dob = findViewById(R.id.adminDOBinput);
        adminPhoneNo = findViewById(R.id.adminPhoneInput);
        CreateAdmin = (Button) findViewById(R.id.adminCreateButton);
        CreateAdmin.setOnClickListener(v -> {
            if (checkIfEmpty()) {
                Toast.makeText(this, "You did not enter all fields.", Toast.LENGTH_LONG).show();
                return;
            }
            else {
                Users admin;
                databaseHelper = new DataBaseHelper(this);
                String user = adminUsername.getText().toString();
                String pass = adminPassword.getText().toString();
                String fullname = adminFullName.getText().toString();
                String birthday = dob.getText().toString();
                String phoneNo = adminPhoneNo.getText().toString();
                if (checkAll(user, pass, phoneNo)) {
                    admin = new Users(user, pass, fullname, birthday, Integer.parseInt(phoneNo));
                    boolean added = databaseHelper.addUser(admin);
                    if (added == false) {
                        Toast.makeText(this, "Failed to register.", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this, "Registered Successfully.", Toast.LENGTH_SHORT).show();
                        Intent returnPage = new Intent(this, ManageAdminPage.class);
                        returnPage.putExtra("username", uniqueString);
                        startActivity(returnPage);
                    }
                }
                else {
                    Toast.makeText(this, validateMessage(), Toast.LENGTH_LONG).show();
                }

            }
        });


    }

    private boolean checkIfEmpty() {
        if (adminPhoneNo.getText().toString().matches("") || adminPassword.getText().toString().matches("") || adminUsername.getText().toString().matches("")
                || dob.getText().toString().matches("") || adminFullName.getText().toString().matches("")) {
            return true;
        }
        else return false;
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
        if (databaseHelper.checkUsernameDuplicate(adminUsername.getText().toString()) != 0) {
            word+= "Username not available.\n";
        }
        if(!adminUsername.getText().toString().matches("\\S+")) {
            word+= "Username cannot have spaces.\n";
        }
        if (!checkValidPassword(adminPassword.getText().toString())) {
            word += "Password does not meet requirement.\n";
        }
        if (!checkValidPhoneNumber(adminPhoneNo.getText().toString())) {
            word += "Invalid phone number.";
        }
        return word;
    }

    // checks all at once
    private boolean checkAll(String u, String pass, String phoneNumber) {
        if (databaseHelper.checkUsernameDuplicate(u) == 0 && checkValidPassword(pass) && checkValidPhoneNumber(phoneNumber)) {
            return true;
        }
        else return false;
    }
}