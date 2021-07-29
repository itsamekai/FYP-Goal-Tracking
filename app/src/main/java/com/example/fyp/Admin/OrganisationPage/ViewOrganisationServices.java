package com.example.fyp.Admin.OrganisationPage;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fyp.Admin.MainAdminPage;
import com.example.fyp.Database.DataBaseHelper;
import com.example.fyp.R;

import java.util.ArrayList;

public class ViewOrganisationServices extends AppCompatActivity {

    public ImageView returnButton1;
    public TextView org_field, name_field, contact_field, email_field, address_field, service_field;
    public DataBaseHelper db;
    public ArrayList<String> orgsvc;
    public String org_name, uniqueString;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_organisation_services);
        db = new DataBaseHelper(this);
        // org name
        org_name = getIntent().getStringExtra("name");
        uniqueString = getIntent().getStringExtra("username");

        org_field = findViewById(R.id.view_org_name);
        name_field = findViewById(R.id.view_contact_name);
        contact_field = findViewById(R.id.view_contact_number);
        email_field = findViewById(R.id.view_email_address);
        address_field = findViewById(R.id.view_org_address);
        service_field = findViewById(R.id.view_org_services);
        orgsvc = new ArrayList<>();

        returnButton1 = (ImageView) findViewById(R.id.adminArrowBack89);
        returnButton1.setOnClickListener(v -> {
            Intent returnpage = new Intent(this, ViewOrganisation.class);
            returnpage.putExtra("username", uniqueString);
            startActivity(returnpage);
        });

        getInformation();


    }

    private void getInformation() {
        db = new DataBaseHelper(this);
        Cursor c = db.readOrgData(org_name);
        while (c.moveToNext()) {
            org_field.setText(c.getString(0));
            name_field.setText(c.getString(1));
            contact_field.setText(c.getString(2));
            email_field.setText(c.getString(3));
            address_field.setText(c.getString(4));
        }

        String word = db.getServicesForOrg(db.getOrgIDWithName(org_name));
        System.out.println(word);
        if (word.equals("")) {
            service_field.setText("No services.");
        }
        else {
            if (word.charAt(word.length() - 2) == ',') {
                word = word.substring(0, word.length() - 2);
                System.out.println("test here");
            }
            service_field.setText(word);
        }


        }

    }
