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

public class CheckAccomplishedGoal extends AppCompatActivity {

    public ImageView returnButton;
    public TextView accomplishedTotal;
    public RecyclerView recyclerView;
    public ArrayList<String> accomplishedAchievement, accomplishedDateTime;
    public DataBaseHelper db;
    public CheckAccomplishedGoalAdapter checkAccomplishedGoalAdapter;
    int total = 0;
    int counter = 0;
    public static String uniqueString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_accomplished_goal);
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
        accomplishedDateTime = new ArrayList<>();
        putDataInArray();
        SetAllGoalCount();
        checkAccomplishedGoalAdapter = new CheckAccomplishedGoalAdapter(CheckAccomplishedGoal.this, this, accomplishedAchievement, accomplishedDateTime);
        recyclerView.setAdapter(checkAccomplishedGoalAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(CheckAccomplishedGoal.this));
    }

    private void SetAllGoalCount(){

         accomplishedTotal = findViewById(R.id.RewardCount);
         accomplishedTotal.setText(String.valueOf(counter));
    }

    private void putDataInArray() {
        Cursor c = db.HistoryGoals(uniqueString);
        if (c.getCount() != 0) {
            while (c.moveToNext()) {
                //to see how many total accomplishments there are in total
                counter++;
                accomplishedAchievement.add(c.getString(0));
                accomplishedDateTime.add(c.getString(1));
            }
        }

    }

}