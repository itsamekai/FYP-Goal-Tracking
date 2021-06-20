package com.example.fyp.GeneralMainPage.OrgHelp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fyp.Database.DataBaseHelper;
import com.example.fyp.GeneralMainPage.MainHomePage;
import com.example.fyp.Goals.TrackingGoal;
import com.example.fyp.ObjectClass.UserHelp;
import com.example.fyp.R;

public class ConfirmHelpGoal extends AppCompatActivity {

    public String uniqueString, goalName;
    public TextView name, description, timestarted;
    public Button getHelpB;
    public ImageView returnArrow;
    public DataBaseHelper db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_help_goal);

        uniqueString = getIntent().getStringExtra("username");
        goalName = getIntent().getStringExtra("goal_name");

        name = findViewById(R.id.selectedHelpGoalName);
        description = findViewById(R.id.selectedHelpGoalDesc);
        timestarted = findViewById(R.id.selectedHelpGoalDate);
        getHelpB = findViewById(R.id.getSelectedHelpButton);
        returnArrow = findViewById(R.id.returnArrow10);
        returnArrow.setOnClickListener(v -> {
            Intent returnPrev = new Intent(this, SelectHelpGoal.class);
            returnPrev.putExtra("username", uniqueString);
            startActivity(returnPrev);

        });
        putText();

        getHelpB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GetHelpAlert();
            }
        });


    }

    private void putText() {
        db = new DataBaseHelper(this);
        Cursor c = db.getGoals(uniqueString, goalName);
        if (c.getCount() != 0) {
            while (c.moveToNext()) {
                description.setText(c.getString(0));
                timestarted.setText(c.getString(1));
            }
        }
        name.setText(goalName);
    }

    private void GetHelpAlert() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setMessage("Would you like to get help from a partner organisation with this goal?");
        dialog.setTitle("Confirmation");
        dialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                db = new DataBaseHelper(getApplicationContext());
                UserHelp help = new UserHelp(db.getServiceID(db.getCategoryName(goalName)), db.getUserID(uniqueString), db.getGoalID(db.getUserID(uniqueString), goalName));
                if (db.addUserHelp(help)) {
                    if (db.updateRequested(String.valueOf(db.getUserID(uniqueString)), goalName) > 0) {
                        Toast.makeText(getApplicationContext(), "Pending Help. Please be patient.", Toast.LENGTH_LONG).show();
                        Intent i = new Intent(getApplicationContext(), MainHomePage.class);
                        i.putExtra("username", uniqueString);
                        startActivity(i);
                    }

                } else
                    Toast.makeText(getApplicationContext(), "can't update?", Toast.LENGTH_SHORT).show();

            }


        });

        dialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "You can do it!", Toast.LENGTH_SHORT).show();
            }
        });
        AlertDialog alertDialog = dialog.create();
        alertDialog.show();
    }

}