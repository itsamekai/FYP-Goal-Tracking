package com.example.fyp.GeneralMainPage.OrgHelp;

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

public class ExistingHelp extends AppCompatActivity {

    public ImageView arrow;
    public DataBaseHelper db;
    public RecyclerView recyclerView;
    public ArrayList<String> goalName, goalDesc, goalStartTime, orgName, orgContact;
    public ExistingHelpAdapter existingHelpAdapter;
    public static String uniqueString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_existing_help);
        uniqueString = getIntent().getStringExtra("username");

        recyclerView = findViewById(R.id.ExistingHelpRecyclerView);
        arrow = findViewById(R.id.returnArrow16);
        arrow.setOnClickListener(v -> {
            Intent i = new Intent(this, ChooseHelp.class);
            i.putExtra("username", uniqueString);
            startActivity(i);
        });

        goalName = new ArrayList<>();
        goalDesc = new ArrayList<>();
        goalStartTime = new ArrayList<>();
        orgName = new ArrayList<>();
        orgContact = new ArrayList<>();
        fillArray();
        existingHelpAdapter = new ExistingHelpAdapter(ExistingHelp.this, this, goalName, goalDesc, goalStartTime, orgName, orgContact);
        recyclerView.setAdapter(existingHelpAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(ExistingHelp.this));


    }

    private void fillArray() {
        db = new DataBaseHelper(this);
        Cursor c = db.showExistingHelpGoals(uniqueString);
        if (c.getCount() != 0) {
            while (c.moveToNext()) {
                goalName.add(c.getString(0));
                goalDesc.add(c.getString(1));
                goalStartTime.add(c.getString(2));
                orgName.add(c.getString(3));
                orgContact.add(c.getString(4));
            }
        }
    }

}