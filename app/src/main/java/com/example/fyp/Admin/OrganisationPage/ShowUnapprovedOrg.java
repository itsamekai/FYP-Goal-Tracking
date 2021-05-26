package com.example.fyp.Admin.OrganisationPage;

import androidx.annotation.Nullable;
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

public class ShowUnapprovedOrg extends AppCompatActivity {

    public ImageView returnarrow;
    public RecyclerView rView;
    public DataBaseHelper db;
    public ArrayList<String> orgname;
    public ApproveOrgCustomAdapter ApproveOrgCustomAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_unapproved_orgs);
        String uniqueString = getIntent().getStringExtra("username");

        returnarrow = findViewById(R.id.adminArrowBack5);
        returnarrow.setOnClickListener(v ->{
            Intent returnprev = new Intent(this, ManageOrganisationPage.class);
            returnprev.putExtra("username", uniqueString);
            startActivity(returnprev);
        });

        rView = findViewById(R.id.ApproveOrgRecyclerView);
        db = new DataBaseHelper(this);
        orgname = new ArrayList<>();
        putDataInArray();
        ApproveOrgCustomAdapter = new ApproveOrgCustomAdapter(ShowUnapprovedOrg.this, this, orgname);
        rView.setAdapter(ApproveOrgCustomAdapter);
        rView.setLayoutManager(new LinearLayoutManager(ShowUnapprovedOrg.this));


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            recreate();
        }
    }

    public void putDataInArray(){
        Cursor c = db.readUnapprovedOrgData();
        if (c.getCount() != 0){
            System.out.println("succeed!");
            while (c.moveToNext()) {
                orgname.add(c.getString(0));
            }
        }
    }
}