package com.example.fyp.OrganisationPage;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fyp.R;

public class CurrentlyHelpingGoal extends AppCompatActivity {

    public TextView category, fullname, phoneno, goalname, goaldesc, goalcreated;
    public ImageView arrow;
    public String categoryString, fullnameString, phoneString, goalnameString, goaldescString, goalcreatedString, uniqueString;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currently_helping_goal);

        category = findViewById(R.id.selected_category_name);
        fullname = findViewById(R.id.selected_user_fullname);
        phoneno = findViewById(R.id.selected_user_phoneno);
        goalname = findViewById(R.id.selected_goal_name);
        goaldesc = findViewById(R.id.selected_goal_description);
        goalcreated = findViewById(R.id.selected_goal_datecreated);
        arrow = findViewById(R.id.returnArrow15);

        categoryString = getIntent().getStringExtra("category_name");
        uniqueString = getIntent().getStringExtra("username");
        fullnameString = getIntent().getStringExtra("full_name");
        phoneString = getIntent().getStringExtra("phone_no");
        goalnameString = getIntent().getStringExtra("goal_name");
        goaldescString = getIntent().getStringExtra("goal_description");
        goalcreatedString = getIntent().getStringExtra("goal_created");

        arrow.setOnClickListener(v -> {
            Intent i = new Intent(this, CurrentlyHelpingList.class);
            i.putExtra("username", uniqueString);
            startActivity(i);
        });

        category.setText(categoryString + " Category");
        fullname.setText("User:\n" + fullnameString);
        phoneno.setText("Phone No:\n" + phoneString);
        goalname.setText("Goal Name:\n" + goalnameString);
        goaldesc.setText("Description:\n" + goaldescString);
        goalcreated.setText("Date Created:\n" + goalcreatedString);



    }
}