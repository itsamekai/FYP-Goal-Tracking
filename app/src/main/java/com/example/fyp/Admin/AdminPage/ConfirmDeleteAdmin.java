    package com.example.fyp.Admin.AdminPage;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fyp.Admin.MainAdminPage;
import com.example.fyp.Database.DataBaseHelper;
import com.example.fyp.R;

public class ConfirmDeleteAdmin extends AppCompatActivity {

    public ImageView returnArrow;
    public Button confirmDeleteAdmin;
    public TextView username, fullname, dob, phoneNo;
    public DataBaseHelper db;
    public String uniqueString, deletingUsername;

    //test
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_delete_admin);
         deletingUsername = getIntent().getStringExtra("deletingusername");
         uniqueString = getIntent().getStringExtra("currentUsername");

        // return to prev page
        returnArrow = (ImageView) findViewById(R.id.adminArrowBack4);
        returnArrow.setOnClickListener(v -> {
            Intent returnprev = new Intent(this, DeleteAdmin.class);
            returnprev.putExtra("username", uniqueString);
            startActivity(returnprev);
        });

        confirmDeleteAdmin = (Button) findViewById(R.id.adminDeleteButton);
        confirmDeleteAdmin.setOnClickListener(v -> {
            Intent returnprev = new Intent(this, ManageAdminPage.class);
            returnprev.putExtra("username", uniqueString);
            startActivity(returnprev);
        });


        username = findViewById(R.id.selectedUsername);
        fullname = findViewById(R.id.selectedFullName);
        dob = findViewById(R.id.selectedDOB);
        phoneNo = findViewById(R.id.selectedPhoneNo);
        putDataInTextView();

        confirmDeleteAdmin.setOnClickListener(v -> {
            db = new DataBaseHelper(this);
            if (db.deleteAdmin(deletingUsername)) {
                Intent i = new Intent(this, MainAdminPage.class);
                i.putExtra("username", uniqueString);
                startActivity(i);
                Toast.makeText(this, "Deleted admin.", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(this, "failed to delete.", Toast.LENGTH_SHORT).show();
            }
        });







    }

    private void putDataInTextView(){
        db = new DataBaseHelper(this);
        Cursor c = db.retrieveCurrentAdmin(deletingUsername);
        if (c.getCount() != 0) {
            while (c.moveToNext()) {
                fullname.setText(c.getString(0));
                dob.setText(c.getString(1));
                phoneNo.setText(c.getString(2));
            }
        }
        username.setText(deletingUsername);
    }
}