package com.example.fyp.OrganisationPage;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fyp.Database.DataBaseHelper;
import com.example.fyp.R;

public class OrgConfirmUserHelp extends AppCompatActivity {

    public ImageView arrow;
    public Button confirmB;
    public TextView categoryText, fullnameText, phonenoText, goalnameText, goaldescText, goalcreatedText;
    public DataBaseHelper db;
    public String categoryString, fullnameString, phoneString, goalnameString, goaldescString, goalcreatedString, uniqueString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_org_confirm_user_help);
        categoryString = getIntent().getStringExtra("category_name");
        fullnameString = getIntent().getStringExtra("full_name");
        phoneString = getIntent().getStringExtra("phone_no");
        goalnameString = getIntent().getStringExtra("goal_name");
        goaldescString = getIntent().getStringExtra("goal_description");
        goalcreatedString = getIntent().getStringExtra("goal_created");
        uniqueString = getIntent().getStringExtra("username");

        arrow = findViewById(R.id.returnArrow13);
        categoryText = findViewById(R.id.confirm_category_name);
        fullnameText = findViewById(R.id.confirm_user_fullname);
        phonenoText = findViewById(R.id.confirm_user_phoneno);
        goalnameText = findViewById(R.id.confirm_goal_name);
        goaldescText = findViewById(R.id.confirm_goal_description);
        goalcreatedText = findViewById(R.id.confirm_goal_datecreated);
        confirmB = findViewById(R.id.confirmOrgHelpB);

        arrow.setOnClickListener(v -> {
            Intent i = new Intent(this, RequestedHelp.class);
            i.putExtra("username", uniqueString);
            startActivity(i);
        });

        categoryText.setText(categoryString + " Category");
        fullnameText.setText("User:\n" + fullnameString);
        phonenoText.setText("Phone No:\n" + phoneString);
        goalnameText.setText("Goal Name:\n" + goalnameString);
        goaldescText.setText("Description:\n" + goaldescString);
        goalcreatedText.setText("Date Created:\n" + goalcreatedString);

        confirmB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmAlert();
            }
        });


    }

    private void confirmAlert() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setMessage("Would you like to help this user with his/her goal?");
        dialog.setTitle("Confirmation");
        dialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                db = new DataBaseHelper(getApplicationContext());
                int updated = db.updateUserHelped(String.valueOf(db.getUserIDWithFullName(fullnameString))
                        , String.valueOf(db.getGoalID(db.getUserIDWithFullName(fullnameString), goalnameString))
                        , String.valueOf(db.getOrgID(uniqueString)));
                if (updated > 0) {
                    Toast.makeText(getApplicationContext(), "The user has been informed. Please contact them as well.", Toast.LENGTH_LONG).show();
                    Intent i = new Intent(getApplicationContext(), orgPage.class);
                    i.putExtra("username", uniqueString);
                    startActivity(i);
                }
                else {
                    // to test
                    System.out.println("User ID: " + db.getUserIDWithFullName(fullnameString));
                    System.out.println("Goal ID: " + db.getGoalID(db.getUserIDWithFullName(fullnameString), goalnameString));
                    System.out.println("Org ID: " + db.getOrgID(uniqueString));
                    Toast.makeText(getApplicationContext(), "can't update? check database query", Toast.LENGTH_SHORT).show();
                }
            }
        });

        dialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "Please be sure before you choose to help a user!", Toast.LENGTH_SHORT).show();
            }
        });
        AlertDialog alertDialog = dialog.create();
        alertDialog.show();
    }


}