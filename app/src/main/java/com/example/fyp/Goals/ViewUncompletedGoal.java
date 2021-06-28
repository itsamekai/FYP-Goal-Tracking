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
    public Button deleteGoal, finishGoal;
    public ImageView arrowback;
    public String uniqueString, goal_name;
    public int u;
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
        finishGoal = findViewById(R.id.finishGoalButton);
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
                DeleteAlertDialog();
            }

        });

        finishGoal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FinishAlertDialog();
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

    private void DeleteAlertDialog() {
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

    private void FinishAlertDialog() {
        AlertDialog.Builder dialog=new AlertDialog.Builder(this);
        dialog.setMessage("Have you fully completed this goal?");
        dialog.setTitle("Complete Goal");
        dialog.setPositiveButton("Finish!", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                db = new DataBaseHelper(getApplicationContext());
                String id = String.valueOf(db.getUserID(uniqueString));
                int finished = db.updateGoal(id, goal_name);
                if (finished > 0) {
                    Toast.makeText(getApplicationContext(), "Goal Finished!", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(getApplicationContext(), TrackingGoal.class);
                    i.putExtra("username", uniqueString);
                    startActivity(i);
                }


                int count = db.checkGoalsAccomplished(u); //retrieves goal count of how many goals they have completed/accomplished
                if (count == 1) {

                    //if the accomplished goal is 1, make sure it matches with required fields in the database
                    //if they match with the database fields, then medals will be given to them
                    //have to make sure to check for no duplicated rewards/achievements

                    //if (db.checkCategoryDuplicate())
                }

                else Toast.makeText(getApplicationContext(), "can't update?", Toast.LENGTH_SHORT).show();
            }
        });

        dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "You can do it!", Toast.LENGTH_SHORT).show();
            }
        });
        AlertDialog alertDialog = dialog.create();
        alertDialog.show();
    }

}