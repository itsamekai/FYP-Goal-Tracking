package com.example.fyp.GeneralMainPage.OrgHelp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.fyp.Database.DataBaseHelper;
import com.example.fyp.Goals.TrackingGoal;
import com.example.fyp.Goals.TrackingGoalAdapter;
import com.example.fyp.R;

import java.util.ArrayList;

public class SelectHelpGoal extends AppCompatActivity {

    public ImageView returnArrow;
    public RecyclerView recyclerView;
    public static String uniqueString;
    public ArrayList<String> goalName, goalDesc, goalStartTime;
    public DataBaseHelper db;
    public HelpAdapter helpAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_help_goal);
        uniqueString = getIntent().getStringExtra("username");

        returnArrow = findViewById(R.id.returnArrow9);
        returnArrow.setOnClickListener(v -> {
            Intent prev = new Intent(this, ChooseHelp.class);
            prev.putExtra("username", uniqueString);
            startActivity(prev);
        });

        recyclerView = findViewById(R.id.helpRecyclerView);
        db = new DataBaseHelper(this);
        goalName = new ArrayList<>();
        goalDesc = new ArrayList<>();
        goalStartTime = new ArrayList<>();
        RetrieveData();
        helpAdapter = new HelpAdapter(SelectHelpGoal.this, this, goalName, goalDesc, goalStartTime);
        recyclerView.setAdapter(helpAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(SelectHelpGoal.this));

    }

    private void RetrieveData() {
        Cursor c = db.getGoalsHelpPage(uniqueString);
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