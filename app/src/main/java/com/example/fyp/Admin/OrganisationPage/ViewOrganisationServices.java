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
    public TextView svcdetail;
    public DataBaseHelper db;
    public ArrayList<String> orgsvc;
    public String uniqueString;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_organisation_services);
        db = new DataBaseHelper(this);
        String uniqueString = getIntent().getStringExtra("username");


        returnButton1 = (ImageView) findViewById(R.id.adminArrowBack89);
        returnButton1.setOnClickListener(v -> {
            Intent returnpage = new Intent(this, ViewOrganisation.class);
            returnpage.putExtra("username", uniqueString);
            startActivity(returnpage);
        });

        svcdetail = findViewById(R.id.textView18);
        fillArrays();

    }

    private void fillArrays() {
        db = new DataBaseHelper(this);
        orgsvc = new ArrayList<>();
        Cursor c = db.readSvcData();
        if (c.getCount() != 0) {
            while (c.moveToNext()) {
                orgsvc.add(c.getString(0));
            }
        }
        System.out.println("UpdateOrgSvc size: " + orgsvc.size());

    }
}