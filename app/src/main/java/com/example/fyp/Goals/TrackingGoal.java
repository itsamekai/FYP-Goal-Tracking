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

public class TrackingGoal extends AppCompatActivity {

    public ImageView addButton, returnButton;
    public TextView goalCounter, goalCompleted, goalUncompleted;
    public RecyclerView recyclerView;
    public ArrayList<String> goalName, goalDesc;
    public DataBaseHelper db;
    public TrackingGoalAdapter trackingGoalAdapter;
    int counter = 0;
    int total = 0;
    public static String uniqueString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tracking_goal);
        uniqueString = getIntent().getStringExtra("username");

        addButton = findViewById(R.id.addButton);
        addButton.setOnClickListener(v -> {
            Intent addGoal = new Intent(this, ChoosingGoal.class);
            addGoal.putExtra("username", uniqueString);
            startActivity(addGoal);
        });

        returnButton = findViewById(R.id.returnArrow4);
        returnButton.setOnClickListener(v -> {
            Intent returnback = new Intent(this, MainHomePage.class);
            returnback.putExtra("username", uniqueString);
            startActivity(returnback);
        });



        recyclerView = findViewById(R.id.GoalsAccomplishedRecyclerView);
        db = new DataBaseHelper(this);
        goalName = new ArrayList<>();
        goalDesc = new ArrayList<>();
        putDataInArray();
        setAllGoalCount();
        trackingGoalAdapter = new TrackingGoalAdapter(TrackingGoal.this, this, goalName, goalDesc);
        recyclerView.setAdapter(trackingGoalAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(TrackingGoal.this));





    }

    private void putDataInArray() {
        Cursor c = db.trackingGoals(uniqueString);
        if (c.getCount() != 0) {
            System.out.println("succeed!");
            while (c.moveToNext()) {
                counter++;
                goalName.add(c.getString(0));
                goalDesc.add(c.getString(1));
            }
        }
    }

    private void setAllGoalCount() {
        total = db.TotalGoals(uniqueString);
        goalCounter = findViewById(R.id.accomplishedTotal);
        goalCompleted = findViewById(R.id.completeCounter);
        goalUncompleted = findViewById(R.id.uncompleteCounter);
        goalCounter.setText(String.valueOf(total) + " Goals");
        goalUncompleted.setText(String.valueOf(counter));
        goalCompleted.setText(String.valueOf(total - counter));

    }
}