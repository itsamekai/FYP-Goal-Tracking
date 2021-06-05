package com.example.fyp.Goals;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fyp.Database.DataBaseHelper;
import com.example.fyp.GeneralMainPage.MainHomePage;
import com.example.fyp.R;

public class ViewCompletedGoal extends AppCompatActivity {

    public ImageView arrow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_completed_goal);
    }
}