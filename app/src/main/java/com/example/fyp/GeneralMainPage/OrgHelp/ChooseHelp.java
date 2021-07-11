package com.example.fyp.GeneralMainPage.OrgHelp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import com.example.fyp.Database.DataBaseHelper;
import com.example.fyp.GeneralMainPage.MainHomePage;
import com.example.fyp.R;

public class ChooseHelp extends AppCompatActivity {
    // choose to view existing help or select new help
    public ImageView returnarrow;
    public Button existingHelp, getHelp, pendingHelp;
    public String uniqueString;
    public int userid;
    public DataBaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_help);

        uniqueString = getIntent().getStringExtra("username");
        db = new DataBaseHelper(this);
        userid = db.getUserID(uniqueString);

        returnarrow = findViewById(R.id.returnArrow8);
        existingHelp = findViewById(R.id.existingHelp);
        getHelp = findViewById(R.id.getHelpChoice);
        pendingHelp = findViewById(R.id.showPendingHelp);

        returnarrow.setOnClickListener(v -> {
            Intent ret = new Intent(this, MainHomePage.class);
            ret.putExtra("username", uniqueString);
            startActivity(ret);
        });

        existingHelp.setOnClickListener(v -> {
            if (db.checkExistingHelpGoalCount(userid)) {
                Intent existing = new Intent(this, ExistingHelp.class);
                existing.putExtra("username", uniqueString);
                startActivity(existing);
            }
            else showWarningNoExistingHelp();

        });

        getHelp.setOnClickListener(v -> {
            if (db.checkExistingOnGoingGoalsHelp(userid)) {
                Intent newHelp = new Intent(this, SelectHelpGoal.class);
                newHelp.putExtra("username", uniqueString);
                startActivity(newHelp);
            }
            else showWarningNoGoalRequest();

        });

        pendingHelp.setOnClickListener(v -> {
            if (db.checkPendingGoalCount(userid)) {
                Intent pending = new Intent(this, PendingHelp.class);
                pending.putExtra("username", uniqueString);
                startActivity(pending);
            }
            else showWarningNoGoalsPending();

        });

    }

    private void showWarningNoGoalRequest() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setMessage("You do not have any goals that an organisation can help you with.");
        dialog.setTitle("Error");
        dialog.setPositiveButton("OK", null);
        dialog.show();
    }

    private void showWarningNoGoalsPending() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setMessage("You do not have any goals that are pending help.");
        dialog.setTitle("Error");
        dialog.setPositiveButton("OK", null);
        dialog.show();
    }

    private void showWarningNoExistingHelp() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setMessage("You do not have an organisation helping you with any of your goals.");
        dialog.setTitle("Error");
        dialog.setPositiveButton("OK", null);
        dialog.show();
    }

}