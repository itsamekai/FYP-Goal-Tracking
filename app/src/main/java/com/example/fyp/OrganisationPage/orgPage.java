package com.example.fyp.OrganisationPage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fyp.Database.DataBaseHelper;
import com.example.fyp.Login.LoginPage;
import com.example.fyp.R;

public class orgPage extends AppCompatActivity {

    public Button requested, current;
    public ImageView logOut, settings;
    public TextView name;
    public String uniqueString;
    public DataBaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_org_page);
        uniqueString = getIntent().getStringExtra("username");

        name = findViewById(R.id.logged_in_org_name);

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

        db = new DataBaseHelper(this);
        name.setText(db.getOrgName(uniqueString));




    }
}