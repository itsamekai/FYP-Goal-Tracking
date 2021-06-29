package com.example.fyp.OrganisationProfile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fyp.Database.DataBaseHelper;
import com.example.fyp.ObjectClass.OrgUsers;
import com.example.fyp.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OrganisationEditDetails extends AppCompatActivity {

    public TextView organisation_name;
    public TextView organisation_name2;
    public TextView email_address;
    public TextView phone_no;
    public TextView pic;
    public TextView address;
    public Button updateButton;
    public String uniqueString;
    public DataBaseHelper databaseHelper;
    public DataBaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_organisation_edit_details);
        uniqueString = getIntent().getStringExtra("username");

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

        email_address = (EditText) findViewById(R.id.editEmail);
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

        //update button
      //  updateButton = (Button) findViewById(R.id.orgUpdateDetailsButton);
      //  uodateButton.setOnClickListener(v -> {
       //     if (checkIfEmpty()) {
         //       Toast.makeText(this, "You did not enter all fields.", Toast.LENGTH_LONG).show();
         //       return;
         //   } else {
        //        OrgUsers orgnisation;
         //       db = new DataBaseHelper(this);
         //       String OrgName1 = organisation_name.getText().toString();
          //      String OrgName2 = organisation_name2.getText().toString();
          //      String emailAddress = email_address.getText().toString();
          //      String phoneNo = phone_no.getText().toString();
           //     String PIC = pic.getText().toString();
           //     String Orgaddress = address.getText().toString();

             //   if (checkValidPhoneNumber(phoneNo)) {
            //        int updated = db.updateOrg(OrgName1, OrgName2, emailAddress, Integer.parseInt(phoneNo), PIC, Orgaddress);
             //       if (updated == 0) {
               //         Toast.makeText(this, "Failed to update.", Toast.LENGTH_SHORT).show();

//                    } else {
  //                      String uniqueString = getIntent().getStringExtra("username");
    //                    Intent returnPage = onNewIntent(this, OrganisationSettingsPage.class);
      //                  returnPage.putExtra("username", uniqueString);
        //                startActivity(returnPage);
          //              Toast.makeText(this, "Update Successful", Toast.LENGTH_SHORT).show();
            //        }

//                } else {
  //                  Toast.makeText(this, validateMessage(), Toast.LENGTH_LONG).show();
    //            }

       //     }
     //   });

        //starts with 8 or 9 and at least 8 digits
        //private boolean checkValidPhoneNumber (String contactNo){
            //String regex = "^[89]\\d{7}$";
           // Pattern p = Pattern.compile(regex);
           // Matcher m = p.matcher(contactNo);
           // return m.matches();
        //}

       // private String validateMessage () {
        //    String word = "";
         //   if (!checkValidPhoneNumber(phone_no.getText().toString())) {
        //        word += "Invalid phone number.";
         //   }
        //    return word;
      //  }
        // checkifempty(phone number)
       // private boolean checkIfEmpty () {
      //      if (phone_no.getText().toString().matches("") || address.getText().toString().matches("") || email_address.getText().toString().matches("")
       //             || pic.getText().toString().matches("") || organisation_name2.getText().toString().matches("")) {
      //          return true;
        //    } else return false;
     //   }

        // checks all at once
       // private boolean checkAll (String phoneNo){
         //   if (checkValidPhoneNumber(phoneNo)) {
        //        return true;
        //    } else return false;
      //  }

    }
}
