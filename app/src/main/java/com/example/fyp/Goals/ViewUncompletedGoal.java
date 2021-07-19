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
import com.example.fyp.ObjectClass.UserAchievement;
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
                boolean b = db.checkHelpReqAlr();
                if (b) {
                    Toast.makeText(getApplicationContext(), "Pls contact Admin for more details", Toast.LENGTH_SHORT).show();
                    }
                else {
                    db.deleteGoal(id, goal_name);
                    Toast.makeText(getApplicationContext(), "Goal deleted", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(getApplicationContext(), TrackingGoal.class);
                    i.putExtra("username", uniqueString);
                    startActivity(i);
                }
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
                // puts accomplished to 1
                // its incremented at this point
                int finished = db.updateGoal(id, goal_name);
                if (finished > 0) {
                    // assume users start with 0 accomplished
                    // gives achievement after completing x amt of goals
                    // can only give ONE achievement at a time
                    int u = db.getUserID(uniqueString);
                    int a = db.getMatchingAchievement(u);
                    if (a != 0) {
                        UserAchievement ua = new UserAchievement(a, u);
                        if (db.addUserAchievement(ua)) {
                            Toast.makeText(getApplicationContext(), "You have earned a new achievement! Check it out!", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(getApplicationContext(), TrackingGoal.class);
                            i.putExtra("username", uniqueString);
                            startActivity(i);
                        }
                        else Toast.makeText(getApplicationContext(), "error in giving achievements, check", Toast.LENGTH_LONG).show();
                    }
                    Toast.makeText(getApplicationContext(), "Goal Finished!", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(getApplicationContext(), TrackingGoal.class);
                    i.putExtra("username", uniqueString);
                    startActivity(i);
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