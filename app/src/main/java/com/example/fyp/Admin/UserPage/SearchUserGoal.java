package com.example.fyp.Admin.UserPage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.fyp.Admin.MainAdminPage;
import com.example.fyp.Database.DataBaseHelper;
import com.example.fyp.ObjectClass.FilteredUsersGoal;
import com.example.fyp.R;

import java.util.ArrayList;

public class SearchUserGoal extends AppCompatActivity {

    public ImageView returnarrow;
    public RecyclerView recyclerView;
    public EditText searchText;
    public DataBaseHelper db;
    public ArrayList<FilteredUsersGoal> GoalsArray;
    SearchGoalAdapter searchGoalAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_user_goal);

        String uniqueString = getIntent().getStringExtra("username");

        returnarrow = findViewById(R.id.orgArrowBack);
        returnarrow.setOnClickListener(v -> {
            Intent returnPrev = new Intent(this, MainAdminPage.class);
            returnPrev.putExtra("username", uniqueString);
            startActivity(returnPrev);
        });

        recyclerView = findViewById(R.id.GoalRecyclerView);
        searchText = findViewById(R.id.viewGoalsSearch);
        db = new DataBaseHelper(this);
        putDataInArray();
        // make it so that accomplished returns YES if == 1 or NO if ==
        searchGoalAdapter = new SearchGoalAdapter(SearchUserGoal.this, GoalsArray);
        recyclerView.setAdapter(searchGoalAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(SearchUserGoal.this));

        // test
        //System.out.println("size: " + GoalsArray.size());

        // do search
        searchText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                boolean haveCat = false;
                filter(s.toString());




            }
        });


    }

    public void putDataInArray() {
        Cursor c = db.getUsersGoals();
        GoalsArray = new ArrayList<>();
        String acc = "";
        if (c.getCount() != 0) {
            while (c.moveToNext()) {
                if (c.getString(3).equals("0")) {
                    acc = "NO";
                } else acc = "YES";
                GoalsArray.add(new FilteredUsersGoal(c.getString(0), c.getString(1), c.getString(2), acc));
            }
        }
    }

    private void filter(String text) {
        //new array list that will hold the filtered data
        ArrayList<FilteredUsersGoal> filteredArray = new ArrayList<>();
        text.toLowerCase();
        for (FilteredUsersGoal g : GoalsArray) {
            if(g.getName().contains(text) || g.getGoalName().contains(text) || g.getCategory().contains(text) || g.getAccomplished().equalsIgnoreCase(text)) {
                filteredArray.add(g);
            }

        }

        searchGoalAdapter.filterList(filteredArray);
    }




}