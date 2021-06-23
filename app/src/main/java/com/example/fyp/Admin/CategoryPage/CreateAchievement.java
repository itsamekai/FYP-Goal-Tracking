package com.example.fyp.Admin.CategoryPage;

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

import com.example.fyp.Database.DataBaseHelper;
import com.example.fyp.ObjectClass.Category;
import com.example.fyp.ObjectClass.Services;
import com.example.fyp.R;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;


public class CreateAchievement extends AppCompatActivity {
    public ImageView setimagebutton;
    public ImageView medalimage;
    public ImageView arrowBack;
    public DataBaseHelper db;
    private static int GET_FROM_GALLERY = 1;
    public byte[] image;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_achievement);
        String uniqueString = getIntent().getStringExtra("username");

        arrowBack = findViewById(R.id.adminArrowBack9);
        medalimage = findViewById(R.id.categoryUploadImageUpdate);
        setimagebutton = findViewById(R.id.seticon);

        // return previous
        arrowBack.setOnClickListener(v -> {
            Intent i = new Intent(this, ManageCategoryPage.class);
            i.putExtra("username", uniqueString);
            startActivity(i);
        });

        // uploads image
        setimagebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                startActivityForResult(new Intent(Intent.ACTION_GET_CONTENT, MediaStore.Images.Media.INTERNAL_CONTENT_URI), GET_FROM_GALLERY);
            }
        });

    }
    // open photo gallery
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //Detects request codes
        if (requestCode == GET_FROM_GALLERY && resultCode == Activity.RESULT_OK) {
            Uri selectedImage = data.getData();
            Bitmap bitmap = null;
            try {
                bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImage);
                db = new DataBaseHelper(this);
                setimagebutton.setImageBitmap(bitmap);
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
