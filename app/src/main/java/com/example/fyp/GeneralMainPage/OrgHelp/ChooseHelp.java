package com.example.fyp.GeneralMainPage.OrgHelp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import com.example.fyp.GeneralMainPage.MainHomePage;
import com.example.fyp.R;

public class ChooseHelp extends AppCompatActivity {
    // choose to view existing help or select new help
    public ImageView returnarrow;
    public Button existingHelp, getHelp;
    public String uniqueString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_help);
        uniqueString = getIntent().getStringExtra("username");

        returnarrow = findViewById(R.id.returnArrow8);
        existingHelp = findViewById(R.id.existingHelp);
        getHelp = findViewById(R.id.getHelpChoice);

        returnarrow.setOnClickListener(v -> {
            Intent ret = new Intent(this, MainHomePage.class);
            ret.putExtra("username", uniqueString);
            startActivity(ret);
        });

//        existingHelp.setOnClickListener(v -> {
//            Intent existing = new Intent(this, )
//        });

        getHelp.setOnClickListener(v -> {
            Intent newHelp = new Intent(this, SelectHelpGoal.class);
            newHelp.putExtra("username", uniqueString);
            startActivity(newHelp);
        });

    }
}