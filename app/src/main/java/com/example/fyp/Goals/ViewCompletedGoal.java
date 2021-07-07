package com.example.fyp.Goals;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fyp.Database.DataBaseHelper;
import com.example.fyp.R;

public class ViewCompletedGoal extends AppCompatActivity {

    public ImageView returnarrow;
    public TextView cGoalName, cGoalStart, cGoalEnd, cGoalDesc;
    public String uniqueString, goal_name;
    public DataBaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_completed_goal);

        goal_name = getIntent().getStringExtra("goal_name");
        uniqueString = getIntent().getStringExtra("username");
        returnarrow = findViewById(R.id.viewAccomplishedAchArrow);
        cGoalName = findViewById(R.id.createdGoalName);
        cGoalStart = findViewById(R.id.achDesc);
        cGoalEnd = findViewById(R.id.completedGoalEnd);
        cGoalDesc = findViewById(R.id.goalDescriptions);

        fillTextViews();

        returnarrow.setOnClickListener(v -> {
            Intent i = new Intent(this, HistoryGoals.class);
            i.putExtra("username", uniqueString);
            startActivity(i);
        });


    }

    public void fillTextViews() {
        db = new DataBaseHelper(this);
        Cursor c = db.getCompletedGoal(uniqueString, goal_name);
        if (c.getCount() != 0) {
            while (c.moveToNext()) {
                cGoalDesc.setText(c.getString(0));
                cGoalStart.setText(c.getString(1));
                cGoalEnd.setText(c.getString(2));
            }
        }
        cGoalName.setText(goal_name);
    }


}