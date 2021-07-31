package com.example.fyp.Admin.OrganisationPage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fyp.Database.DataBaseHelper;
import com.example.fyp.R;

public class ApproveOrganisation extends AppCompatActivity {

    public ImageView returnprev;
    public TextView orgName, orgContactName, orgContactNumber, orgEmailAddress, orgAddress;
    public Button approveOrgB;
    public DataBaseHelper db;
    public String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_approve_organisation);
        String uniqueString = getIntent().getStringExtra("username");

        // name used to retrieve from sql (where org_name = name;)
        if (getIntent().hasExtra("name")) {
            name = getIntent().getStringExtra("name");
        }

        orgName = findViewById(R.id.selectedOrgName);
        orgContactName = findViewById(R.id.selectedContactName);
        orgContactNumber = findViewById(R.id.selectedContactNumber);
        orgEmailAddress = findViewById(R.id.selectedEmailAddress);
        orgAddress = findViewById(R.id.selectedOrgAddress);
        returnprev = findViewById(R.id.adminArrowBack7);
        approveOrgB = findViewById(R.id.approveOrgButton);
        db = new DataBaseHelper(this);
        putDataInText();


        returnprev.setOnClickListener(v -> {
            Intent i = new Intent(this, ShowUnapprovedOrg.class);
            i.putExtra("username", uniqueString);
            startActivity(i);
        });

        approveOrgB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db = new DataBaseHelper(ApproveOrganisation.this);
                boolean approveUpdated = db.approveOrg(name);
                if (!approveUpdated) {
                    Toast.makeText(ApproveOrganisation.this, "Approve failed.", Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent i = new Intent(ApproveOrganisation.this, ManageOrganisationPage.class);
                    i.putExtra("username", uniqueString);
                    System.out.println(uniqueString + " at approve");
                    Toast.makeText(ApproveOrganisation.this, "Approve succeed.", Toast.LENGTH_SHORT).show();
                    startActivity(i);
                }
            }
        });




    }

    public void putDataInText(){
        if (getIntent().hasExtra("name")) {
            name = getIntent().getStringExtra("name");
        }
        Cursor c = db.readOrgData(name);
        if (c.getCount() != 0){
            System.out.println("succeed!");
            while (c.moveToNext()) {
                orgName.setText(c.getString(0));
                orgContactName.setText(c.getString(1));
                orgContactNumber.setText(c.getString(2));
                orgEmailAddress.setText(c.getString(3));
                orgAddress.setText(c.getString(4));
            }
        }
    }
}