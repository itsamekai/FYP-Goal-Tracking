package com.example.fyp.OrganisationPage;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fyp.Database.DataBaseHelper;
import com.example.fyp.Login.LoginPage;
import com.example.fyp.OrganisationProfile.OrganisationSettingsPage;
import com.example.fyp.R;

public class orgPage extends AppCompatActivity {

    //public Button  ;
    public ImageView logOut, current, settings,requested,addServices;
    public TextView name;
    public String uniqueString;
    public DataBaseHelper db;
    public boolean verified = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_org_page);
        uniqueString = getIntent().getStringExtra("username");
        db = new DataBaseHelper(this);
        verified = db.checkOrgVerified(uniqueString);

        name = findViewById(R.id.logged_in_org_name);

        logOut = findViewById(R.id.orgLogOut);
        logOut.setOnClickListener(v -> {
            startActivity(new Intent(this, LoginPage.class));
            Toast.makeText(this, "Successfully logged out.", Toast.LENGTH_SHORT).show();
        });

        // add verification to see if org is verified (verified = 1)
        requested = findViewById(R.id.OrgUpdateDetails);
        requested.setOnClickListener(v -> {
            if (verified) {
                if (db.checkIfRequestedHelpExists()) {
                    Intent i = new Intent(this, RequestedHelp.class);
                    i.putExtra("username", uniqueString);
                    startActivity(i);
                }
                else showWarningNotNeeded();

            } else {
                showWarningNotVerified();
            }

        });

        current = findViewById(R.id.currentlyHelping);
        current.setOnClickListener(v -> {
            if (verified) {
                if (db.checkIfHelping(db.getOrgID(uniqueString))) {
                    Intent i = new Intent(this, CurrentlyHelpingList.class);
                    i.putExtra("username", uniqueString);
                    startActivity(i);
                }
                else showWarningNotHelping();

            } else {
                showWarningNotVerified();
            }

        });

        addServices = findViewById(R.id.orgUpdateDetailsButton);
        addServices.setOnClickListener(v -> {
            if (db.checkServicesAvailableForOrg(db.getOrgID(uniqueString))) {
                Intent i = new Intent(this, AddService.class);
                i.putExtra("username", uniqueString);
                startActivity(i);
            } else {
                showWarningNoServices();
            }

        });

        settings = findViewById(R.id.orgPageSettings);
        settings.setOnClickListener(v -> {
            Intent i = new Intent(this, OrganisationSettingsPage.class);
            i.putExtra("username", uniqueString);
            startActivity(i);
        });


        name.setText(db.getOrganisationName(uniqueString));


    }

    // pop up window to show no permission.
    private void showWarningNotVerified() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setMessage("Your organisation has to be verified first by an admin.");
        dialog.setTitle("Error");
        dialog.setPositiveButton("OK", null);
        dialog.show();

    }

    // pop up window to show no more services left to add.
    private void showWarningNoServices() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setMessage("There is no more services that your organisation can help in.");
        dialog.setTitle("Message");
        dialog.setPositiveButton("OK", null);
        dialog.show();
    }

    // pop up window to show that there is currently no users that are requesting help.
    private void showWarningNotNeeded() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setMessage("There are currently no users requesting for help. Check back later.");
        dialog.setTitle("Message");
        dialog.setPositiveButton("OK", null);
        dialog.show();
    }

    // pop up window to show that the org is currently not helping any users with their goals.
    private void showWarningNotHelping() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setMessage("Your organisation is currently not helping any users. Check the requested help page.");
        dialog.setTitle("Message");
        dialog.setPositiveButton("OK", null);
        dialog.show();
    }
}