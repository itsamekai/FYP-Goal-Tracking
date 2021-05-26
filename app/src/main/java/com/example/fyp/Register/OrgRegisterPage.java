package com.example.fyp.Register;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.content.Intent;
import android.widget.Button;
import android.widget.Toast;

import com.example.fyp.Database.DataBaseHelper;
import com.example.fyp.Login.LoginPage;
import com.example.fyp.ObjectClass.OrgUsers;
import com.example.fyp.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OrgRegisterPage extends AppCompatActivity {

    public ImageView returnArrow3;
    public EditText orgName;
    public EditText orgEmail;
    public EditText phoneNumb;
    public EditText PIC;
    public EditText orgAddress;
    public EditText orgPassword;
    public Button orgRegisterButton;
    public DataBaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_org_register_page);

        returnArrow3 = (ImageView) findViewById(R.id.ReturnArrow3);
        orgName = (EditText) findViewById(R.id.orgName);
        orgEmail = (EditText) findViewById(R.id.orgEmailAddress);
        phoneNumb = (EditText) findViewById(R.id.orgPhoneNo);
        PIC = (EditText) findViewById(R.id.orgInCharge);
        orgAddress = (EditText) findViewById(R.id.orgAdd);
        orgPassword = (EditText) findViewById(R.id.orgPassword);
        orgRegisterButton = (Button) findViewById(R.id.orgRegisterButton);

        returnArrow3.setOnClickListener(v -> {
            Intent returnPage = new Intent(this, LoginPage.class);
            startActivity(returnPage);
        });

        orgRegisterButton.setOnClickListener(v -> {

            boolean emailAddress = false;
            boolean password = false;
            boolean phoneNo = false;

            if (checkIfEmpty() == true) {
                Toast.makeText(this, "You did not enter all fields.", Toast.LENGTH_LONG).show();
                return;
            }
                else {
                db = new DataBaseHelper(this);
                String email = orgEmail.getText().toString();
                if (checkAll(email, orgPassword.getText().toString(), phoneNumb.getText().toString(), email)) {
                    db = new DataBaseHelper(this);
                    OrgUsers org = new OrgUsers(orgEmail.getText().toString(), Integer.parseInt(phoneNumb.getText().toString()), PIC.getText().toString(),
                            orgAddress.getText().toString(), orgName.getText().toString(), orgPassword.getText().toString());
                    boolean succeed = db.allowRegister(org);
                    if (succeed) {
                        Intent successful = new Intent(this, LoginPage.class);
                        startActivity(successful);
                        Toast.makeText(this, "Registration as Organisation success! Welcome.", Toast.LENGTH_SHORT).show();
                    }
                    else Toast.makeText(this, "u failed noob.", Toast.LENGTH_SHORT).show();
                }
                else Toast.makeText(this, validateMessage(), Toast.LENGTH_SHORT).show();
                }

            });
    }



    private boolean checkIfEmpty() {
        if (orgName.getText().toString().matches("") || orgEmail.getText().toString().matches("") || phoneNumb.getText().toString().matches("") || PIC.getText().toString().matches("") ||
                orgAddress.getText().toString().matches("") || orgPassword.getText().toString().matches("")) {
            return true;
        } else {
            return false;
        }
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

    private boolean checkValidEmail(String emailAddress) {
        String regex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(emailAddress);
        return m.matches();
    }


    private String validateMessage() {
        String word = "";
        if (db.checkEmailDuplicate(orgEmail.getText().toString()) != 0) {
            word+= "Email not available.\n";
        }
        if (!checkValidEmail(orgEmail.getText().toString())) {
            word += "Email does not meet the requirements.\n";
        }
        if (!checkValidPassword(orgPassword.getText().toString())) {
            word += "Password does not meet the requirements.\n";
        }
        if (!checkValidPhoneNumber(phoneNumb.getText().toString())) {
            word += "Invalid phone number.\n";
        }


        return word;
    }

    // checks all at once
    private boolean checkAll(String emailAddress, String password, String phoneNumber, String email) {
        if (checkValidEmail(emailAddress) && checkValidPassword(password) && checkValidPhoneNumber(phoneNumber) && db.checkEmailDuplicate(email) == 0) {
            return true;
        } else return false;
    }
}