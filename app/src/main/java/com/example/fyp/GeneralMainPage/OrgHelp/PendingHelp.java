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

public class PendingHelp extends AppCompatActivity {

    public ImageView returnArrow;
    public RecyclerView recyclerView;
    public static String uniqueString;
    public ArrayList<String> goalName, goalDesc, goalStartTime;
    public DataBaseHelper db;
    public PendingHelpAdapter pendingHelpAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pending_help);
        uniqueString = getIntent().getStringExtra("username");

        returnArrow = findViewById(R.id.returnArrow11);
        returnArrow.setOnClickListener(v -> {
            Intent prev = new Intent(this, ChooseHelp.class);
            prev.putExtra("username", uniqueString);
            startActivity(prev);
        });

        recyclerView = findViewById(R.id.PendingRecyclerView);
        db = new DataBaseHelper(this);
        goalName = new ArrayList<>();
        goalDesc = new ArrayList<>();
        goalStartTime = new ArrayList<>();
        retrieveData();
        pendingHelpAdapter = new PendingHelpAdapter(PendingHelp.this, this, goalName, goalDesc, goalStartTime);
        recyclerView.setAdapter(pendingHelpAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(PendingHelp.this));


    }

    private void retrieveData() {
        Cursor c = db.getPendingGoals(uniqueString);
        if (c.getCount() != 0) {
            while (c.moveToNext()) {
                goalName.add(c.getString(0));
                goalDesc.add(c.getString(1));
                String time = "Started on: " + c.getString(2);
                goalStartTime.add(time);
            }
        }
    }
}