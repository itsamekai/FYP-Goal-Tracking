package com.example.fyp.Goals;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fyp.Database.DataBaseHelper;
import com.example.fyp.R;

public class ViewUncompletedGoal extends AppCompatActivity {

    public TextView selectedGoalName, goalDesc, selectedGoalStart;
    public Button deleteGoal;
    public ImageView arrowback;
    public String uniqueString, goal_name;
    public DataBaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_uncompleted_goal);

        uniqueString = getIntent().getStringExtra("username");
        goal_name = getIntent().getStringExtra("goal_name");

        selectedGoalName = findViewById(R.id.goalChoosenName);
        goalDesc = findViewById(R.id.goalChosenDesc);
        selectedGoalStart = findViewById(R.id.goalChosenDateCreated);
        deleteGoal = findViewById(R.id.deleteGoalButton);
        arrowback = findViewById(R.id.returnArrow5);

        fillTextViews();

        arrowback.setOnClickListener(v -> {
            Intent i = new Intent(this, TrackingGoal.class);
            i.putExtra("username", uniqueString);
            startActivity(i);
        });

        deleteGoal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog();
            }

        });

    }

    private void fillTextViews() {
        db = new DataBaseHelper(this);
        Cursor c = db.getGoals(uniqueString, goal_name);
        if (c.getCount() != 0) {
            while (c.moveToNext()) {
                goalDesc.setText(c.getString(0));
                selectedGoalStart.setText(c.getString(1));
            }
        }
        selectedGoalName.setText(goal_name);
    }

    private void alertDialog() {
        AlertDialog.Builder dialog=new AlertDialog.Builder(this);
        dialog.setMessage("Are you sure you want to delete this goal?");
        dialog.setTitle("Confirmation");
        dialog.setPositiveButton("DELETE", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                db = new DataBaseHelper(getApplicationContext());
                int id = db.getUserID(uniqueString);
                if (db.deleteGoal(id, goal_name)) {
                    Toast.makeText(getApplicationContext(), "Goal deleted.", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(getApplicationContext(), TrackingGoal.class);
                    i.putExtra("username", uniqueString);
                    startActivity(i);
                }
                else Toast.makeText(getApplicationContext(), "Error in deletion.", Toast.LENGTH_SHORT).show();


            }
        });

        dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "Be extra careful when deleting!", Toast.LENGTH_SHORT).show();
            }
        });
        AlertDialog alertDialog = dialog.create();
        alertDialog.show();
    }

}