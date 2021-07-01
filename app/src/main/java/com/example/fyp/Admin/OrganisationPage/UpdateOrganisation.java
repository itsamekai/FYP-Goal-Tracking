package com.example.fyp.Admin.OrganisationPage;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fyp.Admin.AdminPage.ManageAdminPage;
import com.example.fyp.Admin.CategoryPage.ManageCategoryPage;
import com.example.fyp.Database.DataBaseHelper;
import com.example.fyp.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class UpdateOrganisation extends AppCompatActivity {

    public TextView OrgEmail1;
    public EditText UpdateOrgPassword;
    public EditText UpdateOrgPhoneNo;
    public EditText UpdateOrgPIC;
    public EditText UpdateOrgAddress;
    public Button UpdateOrganisation;
    public ArrayList<String> OrgName,OrgEmail,UpdateOrgPassword1,UpdateOrgPhoneNo1,UpdateOrgPIC1,UpdateOrgAddress1;
    public String passToNext;
    public Spinner dropDownBox;
    public ImageView returnArrow;
    public DataBaseHelper db;
    public String uniqueString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_organisation);
        String uniqueString = getIntent().getStringExtra("username");

        dropDownBox = (Spinner) findViewById(R.id.dropdownBox2);

        OrgEmail1 = findViewById(R.id.UpdateOrgEmail);
        UpdateOrgPassword = findViewById(R.id.UpdateOrgPassword);
        UpdateOrgPhoneNo = findViewById(R.id.UpdateOrgPhoneNo);
        UpdateOrgPIC = findViewById(R.id.updateOrgPIC);
        UpdateOrgAddress = findViewById(R.id.updateOrgAddress);

        // return to prev page
        returnArrow = (ImageView) findViewById(R.id.adminArrowBack4);
        returnArrow.setOnClickListener(v -> {
            startActivity(new Intent(this, ManageOrganisationPage.class));
        });

        // get list of org name (Dropdown)

        fillArrays1();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, OrgName);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dropDownBox.setAdapter(adapter);

        dropDownBox.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                OrgEmail1.setText(OrgEmail.get(i));
                UpdateOrgPassword.setText(UpdateOrgPassword1.get(i));
                UpdateOrgPhoneNo.setText(UpdateOrgPhoneNo1.get(i));
                UpdateOrgPIC.setText(UpdateOrgPIC1.get(i));
                UpdateOrgAddress.setText(UpdateOrgAddress1.get(i));
                passToNext = OrgName.get(i);

                System.out.println("test " + passToNext);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, OrgName);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dropDownBox.setAdapter(adapter1);

        dropDownBox.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                OrgEmail1.setText(OrgEmail.get(i));
                UpdateOrgPassword.setText(UpdateOrgPassword1.get(i));
                UpdateOrgPhoneNo.setText(UpdateOrgPhoneNo1.get(i));
                UpdateOrgPIC.setText(UpdateOrgPIC1.get(i));
                UpdateOrgAddress.setText(UpdateOrgAddress1.get(i));
                passToNext = OrgName.get(i);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });
    }
    private void fillArrays1() {
        db = new DataBaseHelper(this);
        OrgName = new ArrayList<>();
        OrgEmail = new ArrayList<>();
        UpdateOrgPassword1 = new ArrayList<>();
        UpdateOrgPhoneNo1 = new ArrayList<>();
        UpdateOrgPIC1 = new ArrayList<>();
        UpdateOrgAddress1 = new ArrayList<>();
        Cursor c = db.retrieveOrgDetails(db.getOrgID(uniqueString));
        if (c.getCount() != 0) {
            while (c.moveToNext()) {
                OrgName.add(c.getString(0));
                OrgEmail.add(c.getString(1));
                UpdateOrgPassword1.add(c.getString(2));
                UpdateOrgPhoneNo1.add(c.getString(3));
                UpdateOrgPIC1.add(c.getString(4));
                UpdateOrgAddress1.add(c.getString(5));
            }
        }
        System.out.println("UpdateOrgName size: " + OrgName.size());
        System.out.println("OrgEmail size: " + OrgEmail.size());
        System.out.println("UpdateOrgPassword size: " + UpdateOrgPassword1.size());
        System.out.println("UpdateOrgPhoneNo size: " + UpdateOrgPhoneNo1.size());
        System.out.println("UpdateOrgPIC size: " + UpdateOrgPIC1.size());
        System.out.println("UpdateOrgPIC size: " + UpdateOrgAddress1.size());


        // update change button
        UpdateOrganisation = (Button) findViewById(R.id.orgUpdateButton);
        UpdateOrganisation.setOnClickListener(v -> {
            if (checkIfEmpty()) {
                Toast.makeText(this, "You did not enter all fields.", Toast.LENGTH_LONG).show();
                return;
            } else {

                db = new DataBaseHelper(this);

                String password = UpdateOrgPassword.getText().toString();
                String contactNumber = UpdateOrgPhoneNo.getText().toString();
                String contactperson = UpdateOrgPIC.getText().toString();
                String  address = UpdateOrgAddress.getText().toString();


                int updated = db.updateOrgAdmin(passToNext,password,Integer.parseInt(contactNumber),contactperson,address);
                if (updated == 0) {
                    Toast.makeText(this, "Failed to update.", Toast.LENGTH_SHORT).show();
                } else {
                    int updateService = db.updateOrgAdmin(passToNext,password,Integer.parseInt(contactNumber),contactperson,address);
                    if (updateService != 0) {
                        Intent returnPage = new Intent(this, ManageOrganisationPage.class);
                        returnPage.putExtra("username", uniqueString);
                        startActivity(returnPage);
                        Toast.makeText(this, "Update Successful", Toast.LENGTH_SHORT).show();
                    }
                    else Toast.makeText(this, "Failed to update service.", Toast.LENGTH_SHORT).show();

                }

            }
        });
    }
    // checkifempty(Desc)
    private boolean checkIfEmpty() {
        if ( UpdateOrgPassword.getText().toString().matches("")) {
            return true;
        }

        else return false;
    }

}