
package com.example.fyp.Admin.AdminPage;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;


import com.example.fyp.Admin.MainAdminPage;
import com.example.fyp.Database.DataBaseHelper;
import com.example.fyp.ObjectClass.Users;
import com.example.fyp.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UpdateAdmin extends AppCompatActivity {

    public EditText UpdateAdminUsername;
    public EditText UpdateAdminPassword;
    public EditText UpdateAdminFullName;
    public EditText DateOfBirth;
    public EditText UpdateAdminPhoneNo;
    public Button UpdateAdmin;
    public ImageView returnArrow;
    public DatePickerDialog picker;
    public DataBaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_update_admin);
            String uniqueString = getIntent().getStringExtra("username");

        // return to prev page
        returnArrow = (ImageView) findViewById(R.id.adminArrowBack4);
        returnArrow.setOnClickListener(v -> {
            Intent returnprev = new Intent(this, ManageAdminPage.class);
            returnprev.putExtra("username", uniqueString);
            startActivity(returnprev);
        });

        // dob date picker
   /*     DateOfBirth = (EditText) findViewById(R.id.UpdateDateOfBirth);
        DateOfBirth.setInputType(InputType.TYPE_NULL);
        DateOfBirth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                picker = new DatePickerDialog(UpdateAdmin.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                DateOfBirth.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, year, month, day);
                picker.show();
            }
        });*/

// updating of admin
     //   UpdateAdminUsername = findViewById(R.id.UpdateAdminUsername);
     //   UpdateAdminFullName = findViewById(R.id.UpdateAdminFullName);
      //  DateOfBirth = findViewById(R.id.UpdateDateOfBirth);
        UpdateAdminPassword = findViewById(R.id.UpdateAdminPassword);
        UpdateAdminPhoneNo = findViewById(R.id.updateAdminPhoneNo);
        UpdateAdmin = (Button) findViewById(R.id.adminUpdateButton);
        UpdateAdmin.setOnClickListener(v -> {
            if (checkIfEmpty()) {
                Toast.makeText(this, "You did not enter all fields.", Toast.LENGTH_LONG).show();
                return;
            }
            else {
                Users admin;
                databaseHelper = new DataBaseHelper(this);
               // String username = UpdateAdminUsername.getText().toString();
               // String fullname = UpdateAdminFullName.getText().toString();
                //String bday = DateOfBirth.getText().toString();
                String password = UpdateAdminPassword.getText().toString();
                String phoneNo = UpdateAdminPhoneNo.getText().toString();
                if (checkAll(password, phoneNo)) {
                   int updated = databaseHelper.updateAdmin(uniqueString, password, Integer.parseInt(phoneNo));
                    if (updated == 0) {
                        Toast.makeText(this, "Failed to update.", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this, "Updated Successfully.", Toast.LENGTH_SHORT).show();
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
        if (UpdateAdminPassword.getText().toString().matches("") || UpdateAdminPhoneNo.getText().toString().matches("")) {
            return true;
        }

        else return false;
    }


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
     /*   if (databaseHelper.checkUsernameDuplicate(UpdateAdminUsername.getText().toString()) != 0) {
            word+= "Username is taken.\n";
        }
        if(!UpdateAdminUsername.getText().toString().matches("\\S+")) {
            word+= "Username cannot have spaces.\n";
        }*/
        if (!checkValidPassword(UpdateAdminPassword.getText().toString())) {
            word += "Password does not meet requirements.\n";
        }
        if (!checkValidPhoneNumber(UpdateAdminPhoneNo.getText().toString())) {
            word += "Invalid phone number.";
        }
        return word;
    }

    // checks all at once
    private boolean checkAll(String password, String phoneNo) {
        if (checkValidPassword(password) && checkValidPhoneNumber(phoneNo)) {
            return true;
        }
        else return false;
    }
}