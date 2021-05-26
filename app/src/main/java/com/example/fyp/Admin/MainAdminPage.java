package com.example.fyp.Admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fyp.Admin.AdminPage.ManageAdminPage;
import com.example.fyp.Admin.OrganisationPage.ManageOrganisationPage;
import com.example.fyp.Admin.CategoryPage.ManageCategoryPage;
import com.example.fyp.Database.DataBaseHelper;
import com.example.fyp.Login.LoginPage;
import com.example.fyp.R;

public class MainAdminPage extends AppCompatActivity {

    public ImageView adminLog;
    public ImageView categoryPage;
    public ImageView UsersPage;
    public ImageView manageAdminPage;
    public ImageView approveOrgPage;
    public DataBaseHelper db;
    public TextView adminName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_main_page);
        String uniqueString = getIntent().getStringExtra("username");

        // get admin name
        adminName = (TextView) findViewById(R.id.AdminName);
        db = new DataBaseHelper(this);
        adminName.setText(db.getUserFullName(uniqueString));


        // logs out
        adminLog = (ImageView) findViewById(R.id.adminLogOut);
        adminLog.setOnClickListener(v -> {
            startActivity(new Intent(this, LoginPage.class));
            Toast.makeText(this, "Successfully logged out.", Toast.LENGTH_SHORT).show();
        });

        // goes to category page
        categoryPage = (ImageView) findViewById(R.id.manageGoalCatButton);
        categoryPage.setOnClickListener(v -> {
            Intent catPage = new Intent(this, ManageCategoryPage.class);
            catPage.putExtra("username", uniqueString);
            startActivity(catPage);
        });


        // goes to view users page
        // UsersPage = (ImageView) findViewById(R.id.


        // goes to manage admin page
        manageAdminPage = (ImageView) findViewById(R.id.manageAdminButton);
        manageAdminPage.setOnClickListener(v -> {
            Intent adminPage = new Intent(this, ManageAdminPage.class);
            adminPage.putExtra("username", uniqueString);
            startActivity(adminPage);
        });


        // goes to approve org page
        approveOrgPage = (ImageView) findViewById(R.id.ApproveOrgButton);
        approveOrgPage.setOnClickListener(v -> {
            Intent orgPage = new Intent(this, ManageOrganisationPage.class);
            orgPage.putExtra("username", uniqueString);
            startActivity(orgPage);
        });


    }
}