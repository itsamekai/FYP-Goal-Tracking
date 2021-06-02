package com.example.fyp.Admin.AdminPage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fyp.Admin.MainAdminPage;
import com.example.fyp.R;

public class ManageAdminPage extends AppCompatActivity {

    public ImageView returnButton;
    public TextView createAdmin;
    public TextView updateAdmin;
    public TextView deleteAdmin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_admin_page);
        String uniqueString = getIntent().getStringExtra("username");

        returnButton = (ImageView) findViewById(R.id.adminArrowBack);
        returnButton.setOnClickListener(v ->{
            Intent returnprev = new Intent(this, MainAdminPage.class);
            returnprev.putExtra("username", uniqueString);
            startActivity(returnprev);
        });

        createAdmin = (TextView) findViewById(R.id.createAdminButton);
        createAdmin.setOnClickListener(v ->{
            Intent create = new Intent(this, CreateAdmin.class);
            create.putExtra("username", uniqueString);
            startActivity(create);

        });

        updateAdmin = (TextView) findViewById(R.id.UpdateAdminButton);
        updateAdmin.setOnClickListener(v -> {
            Intent update = new Intent(this, UpdateAdmin.class);
            update.putExtra("username", uniqueString);
            startActivity(update);
        });

        deleteAdmin = (TextView) findViewById(R.id.DeleteAdminButton);
        deleteAdmin.setOnClickListener(v -> {
            Intent delete = new Intent(this, DeleteAdmin.class);
            delete.putExtra("username", uniqueString);
            startActivity(delete);
        });




    }
}