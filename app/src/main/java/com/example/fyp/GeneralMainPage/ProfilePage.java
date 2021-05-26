package com.example.fyp.GeneralMainPage;

import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fyp.Database.DataBaseHelper;
import com.example.fyp.Login.LoginPage;
import com.example.fyp.R;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ProfilePage extends AppCompatActivity {

    public ImageView ProfilePic;
    public ImageView ProfileArrowBack;
    public ImageView settingB;
    public ImageView awardicon1;
    public ImageView awardicon2;
    public ImageView homeB1;
    public ImageView friends_button;
    public ImageView addfriends_button;
    public ImageView profile_button;
    public TextView aboutdetails;
    public TextView user_fullname;
    public DataBaseHelper db;
    private static int GET_FROM_GALLERY = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page);

        // sets image
        db = new DataBaseHelper(this);
        Bitmap image = db.retrieveImage(getIntent().getStringExtra("username"));
       // System.out.println(image);
        if (image != null) {
            ProfilePic = (ImageView) findViewById(R.id.ProfilePic);
            ProfilePic.setImageBitmap(db.retrieveImage(getIntent().getStringExtra("username")));
        }

        // gets the name of the user to display
        user_fullname = (TextView) findViewById(R.id.user_fullname);
        String uniqueString = getIntent().getStringExtra("username");
        db = new DataBaseHelper(this);
        user_fullname.setText(db.getUserFullName(uniqueString));

        // return to previous page with arrow
        ProfileArrowBack = (ImageView) findViewById(R.id.ProfileArrowBack);
        ProfileArrowBack.setOnClickListener(v -> {
            Intent returnPage = new Intent(this, MainHomePage.class);
            startActivity(returnPage);
        });

        // home button
        homeB1 = (ImageView) findViewById(R.id.homeB);
        homeB1.setOnClickListener(v -> {
            Intent mainHomePage = new Intent(this, MainHomePage.class);
            mainHomePage.putExtra("username", uniqueString);
            startActivity(mainHomePage);
        });

        // setting button
        settingB = (ImageView) findViewById(R.id.setting_button);
        settingB.setOnClickListener(v -> {
            Intent profilePageEdit = new Intent(this, ProfilePageEdit.class);
            profilePageEdit.putExtra("username", uniqueString);
            startActivity(profilePageEdit);
        });


    }

    }
