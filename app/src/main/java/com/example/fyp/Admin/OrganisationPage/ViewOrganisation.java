package com.example.fyp.Admin.OrganisationPage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.fyp.Database.DataBaseHelper;
import com.example.fyp.R;

import java.util.ArrayList;

public class ViewOrganisation extends AppCompatActivity {

    public ImageView returnarrow;
    public RecyclerView recyclerView;
    public DataBaseHelper db;
    public ArrayList<String> orgname, contact_name, contact_no, orgservices;
    ViewOrgCustomAdapter viewOrgCustomAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_organisation);
        String uniqueString = getIntent().getStringExtra("username");


        returnarrow = findViewById(R.id.adminArrowBack6);
        returnarrow.setOnClickListener(v ->{
            Intent returnPrev = new Intent(this, ManageOrganisationPage.class);
            returnPrev.putExtra("username", uniqueString);
            startActivity(returnPrev);
        });

        recyclerView = findViewById(R.id.OrgRecyclerView);
        db = new DataBaseHelper(this);
        orgname = new ArrayList<>();
        contact_name = new ArrayList<>();
        contact_no = new ArrayList<>();
       // orgservices = new ArrayList<>();
        putDataInArray();
        viewOrgCustomAdapter = new ViewOrgCustomAdapter(ViewOrganisation.this, orgname, contact_name, contact_no);
        recyclerView.setAdapter(viewOrgCustomAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(ViewOrganisation.this));


    }

    public void putDataInArray() {
        Cursor c = db.readOrgData();
        if (c.getCount() != 0) {
            System.out.println("succeed!");
            while (c.moveToNext()) {
                orgname.add(c.getString(0));
                contact_name.add(c.getString(1));
                contact_no.add(c.getString(2));
              //  orgservices.add(c.getString(3));
            }
        }
    }


}