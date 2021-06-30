package com.example.fyp.Goals;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fyp.Database.DataBaseHelper;
import com.example.fyp.GeneralMainPage.MainHomePage;
import com.example.fyp.R;

import java.util.ArrayList;

public class CheckAccomplishedAchievement extends AppCompatActivity {

    public ImageView returnButton;
    public TextView accomplishedTotal;
    public RecyclerView recyclerView;
    public ArrayList<String> accomplishedAchievement,accomplishedDesc, accomplishedDateTime;
    public DataBaseHelper db;
    public CheckAccomplishedAchievementAdapter checkAccomplishedGoalAdapter;
    int total = 0;
    int counter = 0;
    public static String uniqueString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_accomplished_achievement);
        uniqueString = getIntent().getStringExtra("username");

        returnButton = findViewById(R.id.returnArrow4);
        returnButton.setOnClickListener(v -> {
            Intent returnback = new Intent(this, MainHomePage.class);
            returnback.putExtra("username", uniqueString);
            startActivity(returnback);
        });

        recyclerView = findViewById(R.id.GoalsAccomplishedRecyclerView);
        db = new DataBaseHelper(this);
        accomplishedAchievement = new ArrayList<>();
        accomplishedDesc = new ArrayList<>();
        accomplishedDateTime = new ArrayList<>();
        putDataInArray();
        SetAllGoalCount();
        checkAccomplishedGoalAdapter = new CheckAccomplishedAchievementAdapter(CheckAccomplishedAchievement.this, this, accomplishedAchievement, accomplishedDesc, accomplishedDateTime);
        recyclerView.setAdapter(checkAccomplishedGoalAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(CheckAccomplishedAchievement.this));
    }

    private void SetAllGoalCount(){

         accomplishedTotal = findViewById(R.id.RewardCount);
         accomplishedTotal.setText(String.valueOf(counter));
    }

    private void putDataInArray() {
        Cursor c = db.retrieveAchievements(db.getUserID(uniqueString));
        if (c.getCount() != 0) {
            while (c.moveToNext()) {
                //to see how many total accomplishments there are in total
                counter++;
                accomplishedAchievement.add(c.getString(0));
                accomplishedDesc.add(c.getString(1));
                accomplishedDateTime.add(c.getString(2));
            }
        }

    }

}