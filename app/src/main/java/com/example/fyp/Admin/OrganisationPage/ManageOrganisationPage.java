package com.example.fyp.Admin.OrganisationPage;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fyp.Admin.MainAdminPage;
import com.example.fyp.Database.DataBaseHelper;
import com.example.fyp.R;

public class ManageOrganisationPage extends AppCompatActivity {

    public ImageView returnButton;
    public TextView updateOrg;
    public TextView viewOrg;
    public TextView approveOrg;
    public DataBaseHelper db;
    public int orgCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_organisation_page);
        db = new DataBaseHelper(this);
        String uniqueString = getIntent().getStringExtra("username");

        returnButton = (ImageView) findViewById(R.id.adminArrowBack3);
        returnButton.setOnClickListener(v ->{
            Intent returnpage = new Intent(this, MainAdminPage.class);
            returnpage.putExtra("username", uniqueString);
            startActivity(returnpage);
        });

        viewOrg = (TextView) findViewById(R.id.ViewOrganisationButton);
        viewOrg.setOnClickListener(v ->{

                if (db.checkViewOrganisation()) {
                    Intent vieworg = new Intent(this, ViewOrganisation.class);
                    vieworg.putExtra("username", uniqueString);
                    startActivity(vieworg);
                }

            else {
                showWarningNoOrganisationCreated();
            }
        });


        updateOrg = (TextView) findViewById(R.id.UpdateOrganisationButton);
        updateOrg.setOnClickListener(v ->{

            if (db.checkUpdateOrganisation()) {
                Intent updateorg = new Intent(this, UpdateOrganisation.class);
                updateorg.putExtra("username", uniqueString);
                startActivity(updateorg);
            }

            else {
                showWarningNoOrganisationForUpdate();
            }
        });

        approveOrg = (TextView) findViewById(R.id.ApproveOrganisationButton);
        approveOrg.setOnClickListener(v ->{
            if (db.checkApproveOrg()) {
                Intent approveorg = new Intent(this, ShowUnapprovedOrg.class);
                approveorg.putExtra("username", uniqueString);
                startActivity(approveorg);
            }
            else {
                showWarningOrganisationApproved();
            }
        });
    }

    private void showWarningNoOrganisationCreated() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setMessage("There is no organisation available for viewing.");
        dialog.setTitle("Message");
        dialog.setPositiveButton("OK", null);
        dialog.show();
    }

    private void showWarningNoOrganisationForUpdate() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setMessage("There is no organisation available for update.");
        dialog.setTitle("Message");
        dialog.setPositiveButton("OK", null);
        dialog.show();
    }

    private void showWarningOrganisationApproved() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setMessage("There is no organisation available for approve.");
        dialog.setTitle("Message");
        dialog.setPositiveButton("OK", null);
        dialog.show();
    }


    }
