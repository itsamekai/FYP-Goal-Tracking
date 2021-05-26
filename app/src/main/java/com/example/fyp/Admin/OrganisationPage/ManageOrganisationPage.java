package com.example.fyp.Admin.OrganisationPage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fyp.Admin.MainAdminPage;
import com.example.fyp.R;

public class ManageOrganisationPage extends AppCompatActivity {

    public ImageView returnButton;
    public TextView updateOrg;
    public TextView viewOrg;
    public TextView approveOrg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_organisation_page);
        String uniqueString = getIntent().getStringExtra("username");

        returnButton = (ImageView) findViewById(R.id.adminArrowBack3);
        returnButton.setOnClickListener(v ->{
            Intent returnpage = new Intent(this, MainAdminPage.class);
            returnpage.putExtra("username", uniqueString);
            startActivity(returnpage);
        });

        viewOrg = (TextView) findViewById(R.id.ViewOrganisationButton);
        viewOrg.setOnClickListener(v ->{
            Intent vieworg = new Intent(this, ViewOrganisation.class);
            vieworg.putExtra("username", uniqueString);
            startActivity(vieworg);
        });


        updateOrg = (TextView) findViewById(R.id.UpdateOrganisationButton);
        updateOrg.setOnClickListener(v ->{
            Intent updateorg = new Intent(this, UpdateOrganisation.class);
            updateorg.putExtra("username", uniqueString);
            startActivity(updateorg);
        });

        approveOrg = (TextView) findViewById(R.id.ApproveOrganisationButton);
        approveOrg.setOnClickListener(v ->{
            Intent approveorg = new Intent(this, ShowUnapprovedOrg.class);
            approveorg.putExtra("username", uniqueString);
            startActivity(approveorg);
        });
    }
    }
