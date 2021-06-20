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

public class CurrentlyHelpingList extends AppCompatActivity {

    public ImageView arrow;
    public DataBaseHelper db;
    public TextView count;
    public CurrentlyHelpingListAdapter currentlyHelpingListAdapter;
    public RecyclerView recyclerView;
    public static String uniqueString;
    public ArrayList category, full_name, phone_no, goal_name, goal_desc, goal_created_time;
    public ArrayList<Integer> goal_ids, category_ids, user_ids;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currently_helping_list);
        uniqueString = getIntent().getStringExtra("username");
        arrow = findViewById(R.id.returnArrow14);
        count = findViewById(R.id.currentCount);
        recyclerView = findViewById(R.id.currentHelpRecyclerView);
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
        goal_ids = new ArrayList<>();
        category_ids = new ArrayList<>();
        user_ids = new ArrayList<>();
        getAllGoalsID();
        fillGoalArray();

        currentlyHelpingListAdapter = new CurrentlyHelpingListAdapter(CurrentlyHelpingList.this, this, category, full_name, phone_no, goal_name, goal_desc, goal_created_time);
        recyclerView.setAdapter(currentlyHelpingListAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(CurrentlyHelpingList.this));


    }

    private void getAllGoalsID() {
        db = new DataBaseHelper(this);
        Cursor c = db.getGoalIDsForHelpedGoals(db.getOrgID(uniqueString));
        if (c.getCount() != 0) {
            while (c.moveToNext()) {
                goal_ids.add(c.getInt(0));
                category_ids.add(c.getInt(1));
                user_ids.add(c.getInt(2));
            }
        }

    }

    private void fillGoalArray() {
        db = new DataBaseHelper(this);
        for (int i = 0; goal_ids.size() > i; i++) {
            Cursor c = db.getGoalFromUser(category_ids.get(i), user_ids.get(i), goal_ids.get(i));
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
        count.setText(String.valueOf(goal_ids.size()));
    }
}