package com.example.fyp.Admin.AdminPage;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.fyp.R;

public class ConfirmDeleteAdmin extends AppCompatActivity {

    public ImageView returnArrow;
    public Button confirmDeleteAdmin;

    //test
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_delete_admin);
        String uniqueString = getIntent().getStringExtra("username");

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

    }
}