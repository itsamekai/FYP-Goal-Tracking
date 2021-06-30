package com.example.fyp.OrganisationProfile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import com.example.fyp.GeneralMainPage.MainHomePage;
import com.example.fyp.OrganisationPage.orgPage;
import com.example.fyp.R;

public class OrganisationSettingsPage extends AppCompatActivity {

    public ImageView ArrowBack;
    public Button EditDetailsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_organisation_settings_page);
        String uniqueString = getIntent().getStringExtra("username");

        //Return Arrow
        ArrowBack = (ImageView) findViewById(R.id.orgArrowBack);
        ArrowBack.setOnClickListener(v -> {
            Intent returnPage = new Intent(this, orgPage.class);
            returnPage.putExtra("email", uniqueString);
            startActivity(returnPage);
        });


        //Edit Details button to go into the edit details page
        EditDetailsButton = findViewById(R.id.OrgUpdateDetails);
        EditDetailsButton.setOnClickListener(v -> {
            Intent i = new Intent(this, OrganisationEditDetails.class);
            i.putExtra("email", uniqueString);
            startActivity(i);
        });

    }
}