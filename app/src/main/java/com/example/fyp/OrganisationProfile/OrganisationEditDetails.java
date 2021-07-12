
package com.example.fyp.OrganisationProfile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fyp.Admin.OrganisationPage.ManageOrganisationPage;
import com.example.fyp.Database.DataBaseHelper;
import com.example.fyp.ObjectClass.OrgUsers;
import com.example.fyp.OrganisationPage.orgPage;
import com.example.fyp.R;

import org.w3c.dom.Text;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OrganisationEditDetails extends AppCompatActivity {

    public TextView organisation_name;
    public TextView organisation_name2;
    public TextView organisation_name3;
    public ImageView ArrowBack;
    public TextView email_address;
    public EditText phone_no;
    public EditText pic;
    public EditText address;
    public Button updateButton;
    public String uniqueString;
    public DataBaseHelper databaseHelper;
    public DataBaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_organisation_edit_details);
        uniqueString = getIntent().getStringExtra("username");


        organisation_name3 = findViewById(R.id.viewOrgName2);
        email_address = findViewById(R.id.viewEmail);
        phone_no = findViewById(R.id.editPhoneNo);
        pic = findViewById(R.id.editPIC);
        address = findViewById(R.id.editAddress);

        //back arrow
        ArrowBack = (ImageView) findViewById(R.id.orgEditArrow);
        ArrowBack.setOnClickListener(v -> {
            Intent returnPage = new Intent(this, OrganisationSettingsPage.class);
            returnPage.putExtra("username", uniqueString);
            startActivity(returnPage);
        });

        //get organisation name to display

        organisation_name = (TextView) findViewById(R.id.viewOrgName1);
        String uniqueString1 = getIntent().getStringExtra("username");
        db = new DataBaseHelper(this);
        organisation_name.setText(db.getOrganisationName(uniqueString1));

        organisation_name2 = (TextView) findViewById(R.id.viewOrgName2);
        String uniqueString2 = getIntent().getStringExtra("username");
        db = new DataBaseHelper(this);
        organisation_name2.setText(db.getOrganisationName(uniqueString2));

        //get email address of organisation to display

        email_address = (TextView) findViewById(R.id.viewEmail);
        String uniqueString3 = getIntent().getStringExtra("username");
        db = new DataBaseHelper(this);
        email_address.setText(db.getOrgName(uniqueString3));

        //get phone number of orgnisation to display

        phone_no = (EditText) findViewById(R.id.editPhoneNo);
        String uniqueString4 = getIntent().getStringExtra("username");
        db = new DataBaseHelper(this);
        phone_no.setText(db.getOrgPhoneNumber(uniqueString4));

        //get person-in-charge of organisation to display

        pic = (EditText) findViewById(R.id.editPIC);
        String uniqueString5 = getIntent().getStringExtra("username");
        db = new DataBaseHelper(this);
        pic.setText(db.getOrgPIC(uniqueString5));

        //get address of organisation to display
        address = findViewById(R.id.editAddress);
        String uniqueString6 = getIntent().getStringExtra("username");
        db = new DataBaseHelper(this);
        address.setText(db.getOrgAddress(uniqueString6));


        updateButton = findViewById(R.id.orgUpdateDetailsButton);
        updateButton.setOnClickListener(v -> {
            if (checkIfEmpty()) {
                Toast.makeText(this, "You did not enter all fields.", Toast.LENGTH_LONG).show();
                return;
            } else {
                OrgUsers organisation;
                db = new DataBaseHelper(this);
                String emailAddress = email_address.getText().toString();
                String contactNo = phone_no.getText().toString();
                String PIC = pic.getText().toString();
                String OrgAddress = address.getText().toString();

                if (checkAll(emailAddress, contactNo)) {
                    int updated = db.updateOrg(emailAddress, Integer.parseInt(contactNo), PIC, OrgAddress);
                    if (updated == 0) {
                        Toast.makeText(this, "Failed to update.", Toast.LENGTH_SHORT).show();

                    } else {
                        String uniqueString = getIntent().getStringExtra("username");
                        Intent returnPage = new Intent(this, orgPage.class);
                        returnPage.putExtra("username", uniqueString);
                        startActivity(returnPage);
                        Toast.makeText(this, "Update Successful", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(this, validateMessage(), Toast.LENGTH_LONG).show();
                }

            }
        });
    }

    private boolean checkIfEmpty() {
        if (email_address.getText().toString().matches("") || phone_no.getText().toString().matches("")
                || pic.getText().toString().matches("") || address.getText().toString().matches("")) {
            return true;
        } else {
            return false;
        }
    }

    private boolean checkValidEmail(String email) {
        String regex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(email);
        return m.matches();
    }

    //starts with 8 or 9 and at least 8 digits
    private boolean checkValidPhoneNumber(String contactNum) {
        String regex = "^[689]\\d{7}$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(contactNum);
        return m.matches();
    }

    private String validateMessage() {
        String word = "";
        if (!checkValidEmail(email_address.getText().toString())) {
            word += "Email does not meet requirements.\n";
        }

        if (!checkValidPhoneNumber(phone_no.getText().toString())) {
            word += "Invalid phone number.";
        }
        return word;
    }

    private boolean checkAll(String email, String contactNum){
        if (checkValidEmail(email) && checkValidPhoneNumber(contactNum)) {
            return true;
        }
        else {
            return false;
        }
    }

}