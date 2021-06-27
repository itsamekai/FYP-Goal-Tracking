package com.example.fyp.Admin.CategoryPage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fyp.Admin.MainAdminPage;
import com.example.fyp.R;

public class ManageCategoryPage extends AppCompatActivity {

    public ImageView returnButton;
    public TextView createButton;
    public TextView updateButton;
    public TextView createAchievementButton1;

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

        createButton = (TextView) findViewById(R.id.createCategoryButton);
        createButton.setOnClickListener(v ->{
            Intent create = new Intent(this, CreateCategory.class);
            create.putExtra("username", uniqueString);
            startActivity(create);
        });

        updateButton = (TextView) findViewById(R.id.updateCategoryButton);
        updateButton.setOnClickListener(v ->{
            Intent update = new Intent(this, UpdateCategory.class);
            update.putExtra("username", uniqueString);
            startActivity(update);
        });

        createAchievementButton1 = (TextView) findViewById(R.id.createAchievementButton);
        createAchievementButton1.setOnClickListener(v ->{
            Intent create1 = new Intent(this, CreateAchievement.class);
            create1.putExtra("username", uniqueString);
            startActivity(create1);
        });
    }
}