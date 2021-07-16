package com.example.fyp.Admin;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fyp.Admin.AdminPage.ManageAdminPage;
import com.example.fyp.Admin.OrganisationPage.ManageOrganisationPage;
import com.example.fyp.Admin.CategoryPage.ManageCategoryPage;
import com.example.fyp.Admin.UserPage.SearchUserGoal;
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
    public int rowCount = 0;

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
           UsersPage = (ImageView) findViewById(R.id.ViewUsersButton);
           UsersPage.setOnClickListener(v -> {

               if (rowCount >= 1) {
                   if (db.checkViewUserGoals()) {
                       Intent userPage = new Intent(this, SearchUserGoal.class);
                       userPage.putExtra("username", uniqueString);
                       startActivity(userPage);
                   }
               }

               else {
                   showWarningUnableToViewGoals();
               }
           });


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

    private void showWarningUnableToViewGoals() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setMessage("There is no goals available to view");
        dialog.setTitle("Message");
        dialog.setPositiveButton("OK", null);
        dialog.show();
    }
}