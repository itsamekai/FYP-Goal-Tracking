package com.example.fyp.Goals;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fyp.Database.DataBaseHelper;
import com.example.fyp.GeneralMainPage.MainHomePage;
import com.example.fyp.ObjectClass.UsersGoal;
import com.example.fyp.R;

public class SetGoal extends AppCompatActivity {

    public ImageView arrow;
    public EditText GoalName, GoalDesc;
    public TextView createGoal;
    public DataBaseHelper db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_goal);
        String uniqueString = getIntent().getStringExtra("username");
        String selectedName = getIntent().getStringExtra("selected_category_name");

        GoalName = findViewById(R.id.userGoalName);
        GoalDesc = findViewById(R.id.userGoalDesc);
        arrow = findViewById(R.id.SetGoalArrowBack);
        arrow.setOnClickListener(v -> {
            Intent returnback = new Intent(this, ChoosingGoal.class);
            returnback.putExtra("username", uniqueString);
            startActivity(returnback);
        });

        createGoal = findViewById(R.id.createUserGoal);
        createGoal.setOnClickListener(v -> {
            System.out.println(uniqueString);
            System.out.println(GoalName.getText().toString());
            db = new DataBaseHelper(this);
            if (!checkIfEmpty()) {
                if (db.CheckDuplicateGoal(uniqueString, GoalName.getText().toString()) == 0) {
                    UsersGoal goal = new UsersGoal(db.getCategoryID(selectedName), db.getUserID(uniqueString), GoalName.getText().toString(), GoalDesc.getText().toString());
                    if(db.addUserGoal(goal)) {
                        Intent i = new Intent(this, MainHomePage.class);
                        i.putExtra("username", uniqueString);
                        startActivity(i);
                        Toast.makeText(this, "Goal Created! Good luck!", Toast.LENGTH_SHORT).show();
                    }
                    else Toast.makeText(this, "error error error why", Toast.LENGTH_LONG).show();
                }
                else Toast.makeText(this, "Goal already exists!", Toast.LENGTH_LONG).show();

                }
            else Toast.makeText(this, validateMessage(), Toast.LENGTH_LONG).show();

        });


    }

    private boolean checkIfEmpty() {
        if (GoalName.getText().toString().matches("") && GoalDesc.getText().toString().matches("")) {
            return true;
        }
        else return false;
    }



    private String validateMessage() {
        String message = "";
        if (GoalName.getText().toString().matches("")) {
            message += "Goal Name is empty!\n";
        }
        if (GoalDesc.getText().toString().matches("")){
            message += "Goal Description is empty!";
        }
        return message;
    }

}