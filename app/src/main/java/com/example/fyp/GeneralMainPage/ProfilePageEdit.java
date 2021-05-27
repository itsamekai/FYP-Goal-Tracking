package com.example.fyp.GeneralMainPage;

import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fyp.Database.DataBaseHelper;
import com.example.fyp.Login.LoginPage;
import com.example.fyp.R;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ProfilePageEdit extends AppCompatActivity {

    public ImageView ProfilePic;
    public ImageView Done;
    public TextView user_fullname;
    public TextView user_fullname1;
    public TextView user_fullname2;
    public TextView DOB1;
    public TextView phonenumber1;
    public TextView address1;
    public DataBaseHelper db;
    private static int GET_FROM_GALLERY = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page_edit);

        // sets image
        db = new DataBaseHelper(this);
        Bitmap image = db.retrieveImage(getIntent().getStringExtra("username"));
        System.out.println(image);
        if (image != null) {
            ProfilePic = (ImageView) findViewById(R.id.ProfilePic);
            ProfilePic.setImageBitmap(db.retrieveImage(getIntent().getStringExtra("username")));
        }

        // gets the name of the user to display
        user_fullname = (TextView) findViewById(R.id.user_fullname);
        String uniqueString = getIntent().getStringExtra("username");
        db = new DataBaseHelper(this);
        user_fullname.setText(db.getUserFullName(uniqueString));

        user_fullname2 = (TextView) findViewById(R.id.user_fullname2);
        String uniqueString2 = getIntent().getStringExtra("username");
        db = new DataBaseHelper(this);
        user_fullname2.setText(db.getUserFullName(uniqueString2));

        user_fullname1 = (TextView) findViewById(R.id.user_fullname1);
        String uniqueString1 = getIntent().getStringExtra("username");
        db = new DataBaseHelper(this);
        user_fullname1.setText(db.getUserName(uniqueString1));

        DOB1 = (TextView) findViewById(R.id.DOB1);
        String uniqueString3 = getIntent().getStringExtra("username");
        db = new DataBaseHelper(this);
        DOB1.setText(db.getDateOfBirth(uniqueString3));

        // return to previous page with done
        Done = (ImageView) findViewById(R.id.Done);
        Done.setOnClickListener(v -> {
            Intent profilePage = new Intent(this, ProfilePage.class);
            profilePage.putExtra("username", uniqueString);
            startActivity(profilePage);
        });

    }
}
