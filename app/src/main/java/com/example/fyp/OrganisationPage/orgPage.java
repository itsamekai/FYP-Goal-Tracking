package com.example.fyp.OrganisationPage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.fyp.Login.LoginPage;
import com.example.fyp.R;

public class orgPage extends AppCompatActivity {

    public Button requested, current;
    public ImageView logOut, settings;
    public String uniqueString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_org_page);
        uniqueString = getIntent().getStringExtra("username");

        logOut = findViewById(R.id.orgLogOut);
        logOut.setOnClickListener(v -> {
            startActivity(new Intent(this, LoginPage.class));
            Toast.makeText(this, "Successfully logged out.", Toast.LENGTH_SHORT).show();
        });

        requested = findViewById(R.id.requestedHelp);
        requested.setOnClickListener(v -> {
            Intent i = new Intent(this, RequestedHelp.class);
            i.putExtra("username", uniqueString);
            startActivity(i);
        });

        current = findViewById(R.id.currentlyHelping);
        current.setOnClickListener(v -> {
            Intent i = new Intent(this, CurrentlyHelpingList.class);
            i.putExtra("username", uniqueString);
            startActivity(i);
        });

    }
}