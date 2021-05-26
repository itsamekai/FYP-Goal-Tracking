package com.example.fyp.Admin.CategoryPage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.fyp.Admin.MainAdminPage;
import com.example.fyp.R;

public class ManageCategoryPage extends AppCompatActivity {

    public ImageView returnButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_category_page);
        String uniqueString = getIntent().getStringExtra("username");

        returnButton = (ImageView) findViewById(R.id.adminArrowBack2);
        returnButton.setOnClickListener(v ->{
            Intent returnprev = new Intent(this, MainAdminPage.class);
            returnprev.putExtra("username", uniqueString);
            startActivity(returnprev);
        });
    }
}