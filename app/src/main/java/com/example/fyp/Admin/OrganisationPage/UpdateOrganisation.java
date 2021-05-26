package com.example.fyp.Admin.OrganisationPage;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fyp.Admin.AdminPage.ManageAdminPage;
import com.example.fyp.R;

public class UpdateOrganisation extends AppCompatActivity {

    public EditText UpdateOrgName;
    public EditText UpdateOrgEmail;
    public EditText UpdateOrgPassword;
    public EditText UpdateOrgPhoneNo;
    public EditText UpdateOrgPIC;
    public EditText UpdateOrgAddress;
    public Button UpdateOrganisation;
    public ImageView returnArrow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_organisation);
        String uniqueString = getIntent().getStringExtra("username");

        // return to prev page
        returnArrow = (ImageView) findViewById(R.id.adminArrowBack4);
        returnArrow.setOnClickListener(v -> {
            startActivity(new Intent(this, ManageOrganisationPage.class));
        });
    }
}