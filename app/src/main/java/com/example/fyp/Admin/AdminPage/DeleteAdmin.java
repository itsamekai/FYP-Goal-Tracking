package com.example.fyp.Admin.AdminPage;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.example.fyp.Database.DataBaseHelper;
import com.example.fyp.ObjectClass.Users;
import com.example.fyp.R;

public class DeleteAdmin extends AppCompatActivity {

    public ImageView returnArrow;
    public EditText adminDeleteUsername;
    public Button deleteAdmin;
    public DataBaseHelper databaseHelper;

    //test
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_admin);
        String uniqueString = getIntent().getStringExtra("username");

        // return to prev page
        returnArrow = (ImageView) findViewById(R.id.adminArrowBack4);
        returnArrow.setOnClickListener(v -> {
            Intent returnprev = new Intent(this, ManageAdminPage.class);
            returnprev.putExtra("username", uniqueString);
            startActivity(returnprev);
        });

        adminDeleteUsername = findViewById(R.id.adminUserInput);
        deleteAdmin = findViewById(R.id.adminDeleteButton);
        deleteAdmin.setOnClickListener(v -> {
            if (!checkIfEmpty()) {
                Toast.makeText(this, "You did not enter a username.", Toast.LENGTH_LONG).show();
                return;
            } else {
                databaseHelper = new DataBaseHelper(this);
                String username = adminDeleteUsername.getText().toString();

                if (databaseHelper.checkAdminExists(username) == 0) {
                    Toast.makeText(this, "Please enter a valid username.", Toast.LENGTH_SHORT).show();
                } else {
                    Intent goNext = new Intent(this, ConfirmDeleteAdmin.class);
                    goNext.putExtra("username", username);
                    startActivity(goNext);
                    System.out.println("it works");
                }
            }

        });
    }

    private boolean checkIfEmpty() {
        if (adminDeleteUsername.getText().toString().isEmpty()) {
            return false;
        } else {
            return true;
        }
    }
}

