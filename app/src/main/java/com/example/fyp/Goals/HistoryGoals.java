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

public class HistoryGoals extends AppCompatActivity {

    public ImageView returnprev;
    public TextView totalGoals, completedGoals;
    public static String uniqueString;
    public RecyclerView recyclerView;
    public ArrayList<String> HistoryGoalName, HistoryGoalDesc, HistoryGoalDate;
    public HistoryGoalAdapter HistoryGoalAdapter;
    public DataBaseHelper db;
    int counter = 0;
    int total = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_goals);
        uniqueString = getIntent().getStringExtra("username");

        returnprev = findViewById(R.id.returnArrow6);
        returnprev.setOnClickListener(v -> {
            Intent i = new Intent(this, MainHomePage.class);
            i.putExtra("username", uniqueString);
            startActivity(i);
        });

        recyclerView = findViewById(R.id.HistoryGoalRecyclerView);
        db = new DataBaseHelper(this);
        HistoryGoalName = new ArrayList<>();
        HistoryGoalDesc = new ArrayList<>();
        HistoryGoalDate = new ArrayList<>();
        putDataInArray();
        setAllGoalCount();
        HistoryGoalAdapter = new HistoryGoalAdapter(HistoryGoals.this, this, HistoryGoalName, HistoryGoalDesc, HistoryGoalDate);
        recyclerView.setAdapter(HistoryGoalAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(HistoryGoals.this));

    }

    private void setAllGoalCount() {
        total = db.TotalGoals(uniqueString);
        completedGoals = findViewById(R.id.HistoryCompleteCounter);
        totalGoals = findViewById(R.id.HistoryGoalCounter);
        totalGoals.setText(String.valueOf(total) + " Goals Created");
        completedGoals.setText(String.valueOf(counter));
    }

    private void putDataInArray() {
        Cursor c = db.HistoryGoals(uniqueString);
        if (c.getCount() != 0) {
            while (c.moveToNext()) {
                counter++;
                HistoryGoalName.add(c.getString(0));
                HistoryGoalDesc.add(c.getString(1));
                HistoryGoalDate.add(c.getString(2));
            }
        }
    }
}