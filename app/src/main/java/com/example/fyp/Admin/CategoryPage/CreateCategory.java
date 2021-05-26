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
import com.example.fyp.R;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class CreateCategory extends AppCompatActivity {

    public ImageView arrow, uploadImage;
    public TextView uploadButton;
    public Button create;
    public EditText CategoryName, CategoryDesc;
    public DataBaseHelper db;
    private static int GET_FROM_GALLERY = 1;
    public byte[] image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_category);
        String uniqueString = getIntent().getStringExtra("username");

        arrow = findViewById(R.id.adminArrowBack8);
        uploadImage = findViewById(R.id.categoryUploadImage);
        uploadButton = findViewById(R.id.createCategoryUpload);
        create = findViewById(R.id.UploadCategoryButton);
        CategoryName = findViewById(R.id.createCategoryName);
        CategoryDesc = findViewById(R.id.createCategoryDesc);

        // return previous
        arrow.setOnClickListener(v ->{
            Intent i = new Intent(this, ManageCategoryPage.class);
            i.putExtra("username", uniqueString);
            startActivity(i);
        });


        // creates category with object
        create.setOnClickListener(v -> {
            if (CheckIfEmpty()) {
                Toast.makeText(this, "Please fill in all the fields.", Toast.LENGTH_SHORT).show();
            }
            else {
                db = new DataBaseHelper(CreateCategory.this);
                if (db.checkCategoryDuplicate(CategoryName.getText().toString()) != 0) {
                    Toast.makeText(this, "Category already exists.", Toast.LENGTH_SHORT).show();
                }
                else {
                    Category c = new Category(CategoryName.getText().toString(), CategoryDesc.getText().toString(), image);
                    db = new DataBaseHelper(CreateCategory.this);
                    boolean added = db.addCategory(c);
                    if (added) {
                        Toast.makeText(this, "Successfully added!", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(this, ManageCategoryPage.class);
                        i.putExtra("username", uniqueString);
                        startActivity(i);
                    }
                    else {
                        Toast.makeText(this, "Failed to create category.", Toast.LENGTH_SHORT).show();
                    }
                }


            }
        });


        // uploads image
        uploadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                startActivityForResult(new Intent(Intent.ACTION_GET_CONTENT, android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI), GET_FROM_GALLERY);
            }
        });
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
                uploadImage.setImageBitmap(bitmap);
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


    // checks if empty, if empty return true, else false
    public boolean CheckIfEmpty() {
        if (CategoryName.getText().toString().matches("") || CategoryDesc.getText().toString().matches("") || image == null) {
            return true;
        }
        else return false;
    }


}
