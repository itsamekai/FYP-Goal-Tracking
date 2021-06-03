package com.example.fyp.GeneralMainPage;

import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fyp.Admin.AdminPage.ManageAdminPage;
import com.example.fyp.Admin.CategoryPage.CreateCategory;
import com.example.fyp.Admin.CategoryPage.ManageCategoryPage;
import com.example.fyp.Database.DataBaseHelper;
import com.example.fyp.Login.LoginPage;
import com.example.fyp.ObjectClass.Category;
import com.example.fyp.ObjectClass.Users;
import com.example.fyp.R;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProfilePageEdit extends AppCompatActivity {

    public ImageView profilePic;
    public ImageView uploadButton;
    public Button updateb;
    public TextView user_fullname1;
    public TextView user_fullname2;
    public TextView user_fullname3;
    public TextView DOB1;
    public EditText phonenumber2;
    public EditText address1;
    public EditText aboutD;
    public DataBaseHelper db;
    public DataBaseHelper databaseHelper;
    private static int GET_FROM_GALLERY = 1;
    public byte[] image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page_edit);

        profilePic = findViewById(R.id.ProfilePic);
        uploadButton = findViewById(R.id.upload1);
        user_fullname3 = findViewById(R.id.user_fullname1);
        aboutD = findViewById(R.id.aboutdetails);


        // sets image
        db = new DataBaseHelper(this);
        Bitmap image = db.retrieveImage(getIntent().getStringExtra("username"));
        System.out.println(image);
        if (image != null) {
            profilePic = (ImageView) findViewById(R.id.ProfilePic);
            profilePic.setImageBitmap(db.retrieveImage(getIntent().getStringExtra("username")));
        }

        // gets the name of the user to display


        user_fullname2 = (TextView) findViewById(R.id.user_fullname2);
        String uniqueString2 = getIntent().getStringExtra("username");
        db = new DataBaseHelper(this);
        user_fullname2.setText(db.getUserFullName(uniqueString2));

        user_fullname1 = (TextView) findViewById(R.id.user_fullname1);
        String uniqueString1 = getIntent().getStringExtra("username");
        db = new DataBaseHelper(this);
        user_fullname1.setText(db.getUserName(uniqueString1));

        // gets the DOB of the user to display
        DOB1 = (TextView) findViewById(R.id.DOB1);
        String uniqueString3 = getIntent().getStringExtra("username");
        db = new DataBaseHelper(this);
        DOB1.setText(db.getDateOfBirth(uniqueString3));

        // gets the PhoneNumber of the user to display
        phonenumber2 = (EditText) findViewById(R.id.phonenumber1);
        String uniqueString4 = getIntent().getStringExtra("username");
        db = new DataBaseHelper(this);
        phonenumber2.setText(db.getPhoneNumber(uniqueString4));

        // gets the Address of the user to display
        address1 = (EditText) findViewById(R.id.address1);
        String uniqueString5 = getIntent().getStringExtra("username");
        db = new DataBaseHelper(this);
        address1.setText(db.getUserAddress(uniqueString5));

        // gets the about of the user to display
        aboutD = (EditText) findViewById(R.id.aboutdetails);
        String uniqueString6 = getIntent().getStringExtra("username");
        db = new DataBaseHelper(this);
        aboutD.setText(db.getUserAbout(uniqueString6));

        // update button
        phonenumber2 = findViewById(R.id.phonenumber1);
        updateb = (Button) findViewById(R.id.updatebutton);
        updateb.setOnClickListener(v -> {
            if (checkIfEmpty()) {
                Toast.makeText(this, "You did not enter all fields.", Toast.LENGTH_LONG).show();
                return;
            } else {
                Users senior;
                db = new DataBaseHelper(this);
                String phoneNo = phonenumber2.getText().toString();
                String address = address1.getText().toString();
                String userName = user_fullname1.getText().toString();
                String about = aboutD.getText().toString();

                if (checkValidPhoneNumber(phoneNo)) {
                    int updated = db.updateSenior(userName,address,Integer.parseInt(phoneNo),about);
                    if (updated == 0) {
                        Toast.makeText(this, "Failed to update.", Toast.LENGTH_SHORT).show();
                    } else {
                        String uniqueString = getIntent().getStringExtra("username");
                        Intent returnPage = new Intent(this, ProfilePage.class);
                        returnPage.putExtra("username", uniqueString);
                        startActivity(returnPage);
                        Toast.makeText(this, "Update Successful", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(this, validateMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });



        // uploads image
        uploadButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View arg0) {
            startActivityForResult(new Intent(Intent.ACTION_GET_CONTENT, MediaStore.Images.Media.INTERNAL_CONTENT_URI), GET_FROM_GALLERY);
        }
    });
}

    //starts with 8 or 9 and at least 8 digits
    private boolean checkValidPhoneNumber(String phoneNumber) {
        String regex = "^[89]\\d{7}$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(phoneNumber);
        return m.matches();
    }

    private String validateMessage() {
        String word = "";
        if (!checkValidPhoneNumber(phonenumber2.getText().toString())) {
            word += "Invalid phone number.";
        }
        return word;
    }
    // checkifempty(phone number)
    private boolean checkIfEmpty() {
        if (phonenumber2.getText().toString().matches("") || address1.getText().toString().matches("") || aboutD.getText().toString().matches("")) {
            return true;
        }

        else return false;
    }

    // checks all at once
    private boolean checkAll(String phoneNo) {
        if (checkValidPhoneNumber(phoneNo)) {
            return true;
        }
        else return false;
    }

    // open photo gallery
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //Detects request codes
        if(requestCode==GET_FROM_GALLERY && resultCode == Activity.RESULT_OK) {
            Uri selectedImage = data.getData();
            Bitmap bitmap = null;
            try {
                bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImage);
                db = new DataBaseHelper(this);
                db.addImage(getBytes(bitmap), getIntent().getStringExtra("username"));
                profilePic.setImageBitmap(bitmap);
                image = getBytes(bitmap);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

    public static byte[] getBytes(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, stream);
        return stream.toByteArray();
    }


}
