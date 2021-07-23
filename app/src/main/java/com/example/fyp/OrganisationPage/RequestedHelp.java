package com.example.fyp.OrganisationPage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fyp.Database.DataBaseHelper;
import com.example.fyp.R;

import java.util.ArrayList;

public class RequestedHelp extends AppCompatActivity {

    public ImageView arrow;
    public DataBaseHelper db;
    public RequestedHelpAdapter requestedHelpAdapter;
    public TextView count;
    public RecyclerView recyclerView;
    public static String uniqueString;
    public ArrayList category, full_name, phone_no, goal_name, goal_desc, goal_created_time;
    public ArrayList<Integer> categoryArray, UserArray, GoalArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_requested_help);
        uniqueString = getIntent().getStringExtra("username");
        recyclerView = findViewById(R.id.requestedRecyclerView);
        count = findViewById(R.id.requestedCount);
        arrow = findViewById(R.id.returnArrow12);
        db = new DataBaseHelper(this);

        arrow.setOnClickListener(v -> {
            Intent i = new Intent(this, orgPage.class);
            i.putExtra("username", uniqueString);
            startActivity(i);
        });

        category = new ArrayList<>();
        full_name = new ArrayList<>();
        phone_no = new ArrayList<>();
        goal_name = new ArrayList<>();
        goal_desc = new ArrayList<>();
        goal_created_time = new ArrayList<>();
        categoryArray = new ArrayList<>();
        UserArray = new ArrayList<>();
        GoalArray = new ArrayList<>();

        fillIDArrays();
        getGoalsFromArray();
        requestedHelpAdapter = new RequestedHelpAdapter(RequestedHelp.this, this, category, full_name, phone_no, goal_name, goal_desc, goal_created_time);
        recyclerView.setAdapter(requestedHelpAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(RequestedHelp.this));





    }

    private void fillIDArrays() {
        db = new DataBaseHelper(this);
        Cursor c = db.getRequestedGoalsID(db.getOrgID(uniqueString));
        if (c.getCount() != 0) {
            while (c.moveToNext()) {
                categoryArray.add(c.getInt(0));
                UserArray.add(c.getInt(1));
                GoalArray.add(c.getInt(2));
            }
        }
    }

    private void getGoalsFromArray() {
        for (int i = 0; categoryArray.size() > i; i++) {
            db = new DataBaseHelper(this);
            Cursor c = db.getGoalFromUser(categoryArray.get(i), UserArray.get(i), GoalArray.get(i));
            if (c.getCount() != 0) {
                while (c.moveToNext()) {
                    category.add(c.getString(0));
                    full_name.add(c.getString(1));
                    phone_no.add(c.getString(2));
                    goal_name.add(c.getString(3));
                    goal_desc.add(c.getString(4));
                    goal_created_time.add(c.getString(5));
                }
            }
        }
        count.setText(String.valueOf(categoryArray.size()));
    }
}