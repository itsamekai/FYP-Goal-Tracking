package com.example.fyp.GeneralMainPage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fyp.Database.DataBaseHelper;
import com.example.fyp.GeneralMainPage.OrgHelp.ChooseHelp;
import com.example.fyp.GeneralMainPage.Profile.ProfilePage;
import com.example.fyp.Goals.CheckAccomplishedGoal;
import com.example.fyp.Goals.HistoryGoals;
import com.example.fyp.Goals.TrackingGoal;
import com.example.fyp.Login.LoginPage;
import com.example.fyp.R;
import com.example.fyp.Goals.ChoosingGoal;

public class MainHomePage extends AppCompatActivity {

    public TextView full_name;
    public ImageView logout;
    public DataBaseHelper db;
    public ImageView profileB;
    public ImageView profilePic;
    public ImageView setGoalsB;
    public ImageView trackGoalsB;
    public ImageView viewHistoryGoals;
    public ImageView getHelpB;
    public ImageView rewardView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_home_page);
        String uniqueString = getIntent().getStringExtra("username");

        db = new DataBaseHelper(this);
        Bitmap image = db.retrieveImage(getIntent().getStringExtra("username"));
        System.out.println(image);
        if (image != null) {
            profilePic = (ImageView) findViewById(R.id.profilePicture);
            profilePic.setImageBitmap(db.retrieveImage(getIntent().getStringExtra("username")));
        }

        // gets the name of the user to display
        full_name = (TextView) findViewById(R.id.user_fullname);
        db = new DataBaseHelper(this);
        full_name.setText(db.getUserFullName(uniqueString));

        // logout button
        logout = (ImageView) findViewById(R.id.logout_button);
        logout.setOnClickListener(v -> {
            Intent log = new Intent(this, LoginPage.class);
            startActivity(log);
            Toast.makeText(this, "Logged out successfully.", Toast.LENGTH_SHORT).show();
        });

        // profile button
        profileB = (ImageView) findViewById(R.id.profile_button);
        profileB.setOnClickListener(v ->{
            Intent profilePage = new Intent(this, ProfilePage.class);
            profilePage.putExtra("username", uniqueString);
            startActivity(profilePage);
        });


        // Button redirects - set goals
        setGoalsB = (ImageView) findViewById(R.id.setgoal_button);
        setGoalsB.setOnClickListener(v -> {
            Intent setGoalsPage = new Intent(this, ChoosingGoal.class);
            setGoalsPage.putExtra("username", uniqueString);
            startActivity(setGoalsPage);
        });

        // Tracking of Goals
        trackGoalsB = (ImageView) findViewById(R.id.monitor_button);
        trackGoalsB.setOnClickListener(v -> {
            Intent trackGoalsPage = new Intent(this, TrackingGoal.class);
            trackGoalsPage.putExtra("username", uniqueString);
            startActivity(trackGoalsPage);
        });

        //History Goals
        viewHistoryGoals = (ImageView) findViewById(R.id.history_button);
        viewHistoryGoals.setOnClickListener(v -> {
            Intent HistoryGoalPage = new Intent(this, HistoryGoals.class);
            HistoryGoalPage.putExtra("username", uniqueString);
            startActivity(HistoryGoalPage);
        });

        getHelpB = (ImageView) findViewById(R.id.help_button);
        getHelpB.setOnClickListener(v -> {
            Intent HelpPage = new Intent(this, ChooseHelp.class);
            HelpPage.putExtra("username", uniqueString);
            startActivity(HelpPage);
        });

        rewardView = (ImageView) findViewById(R.id.view_medal);
        rewardView.setOnClickListener(v -> {
            Intent ViewRewardPage = new Intent(this, CheckAccomplishedGoal.class);
            ViewRewardPage.putExtra("username", uniqueString);
            startActivity(ViewRewardPage);
        });
    }
}