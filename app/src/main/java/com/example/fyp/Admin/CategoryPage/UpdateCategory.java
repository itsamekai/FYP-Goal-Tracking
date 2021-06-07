package com.example.fyp.Admin.CategoryPage;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fyp.Admin.MainAdminPage;
import com.example.fyp.Database.DataBaseHelper;
import com.example.fyp.GeneralMainPage.ProfilePage;
import com.example.fyp.ObjectClass.Category;
import com.example.fyp.ObjectClass.Users;
import com.example.fyp.R;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class UpdateCategory extends AppCompatActivity {

    public ImageView returnButton;
    public ImageView iconPicture;
    public ImageView changeiconbutton;
    public Button updatechangesb;
    public Spinner dropDownBox;
    public EditText category_name1, category_desc1;
    public ArrayList<String> category_name, category_desc;
    public String passToNext;
    public ArrayList<Bitmap> image1;
    public ArrayList<byte[]> pre_image;
    public DataBaseHelper db;
    public byte[] image;
    private static int GET_FROM_GALLERY = 1;
    public String uniqueString;
    public Bitmap image2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_category);
        uniqueString = getIntent().getStringExtra("username");


        iconPicture = findViewById(R.id.categoryUploadImageUpdate);
        changeiconbutton = findViewById(R.id.updateicon);

        dropDownBox = (Spinner) findViewById(R.id.dropdownBox1);
        iconPicture = (ImageView) findViewById(R.id.categoryUploadImageUpdate);

        category_desc1 = (EditText) findViewById(R.id.categoryDesc);


        // sets image
        db = new DataBaseHelper(this);
        Bitmap image = db.retrieveImage(getIntent().getStringExtra("username"));
        System.out.println(image);
        if (image != null) {
            iconPicture = (ImageView) findViewById(R.id.categoryUploadImageUpdate);
            iconPicture.setImageBitmap(db.retrieveImage(getIntent().getStringExtra("username")));
        }

        // uploads image
        changeiconbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                startActivityForResult(new Intent(Intent.ACTION_GET_CONTENT, MediaStore.Images.Media.INTERNAL_CONTENT_URI), GET_FROM_GALLERY);
            }
        });

        returnButton = (ImageView) findViewById(R.id.adminArrowBack3);
        returnButton.setOnClickListener(v -> {
            Intent returnprev = new Intent(this, ManageCategoryPage.class);
            returnprev.putExtra("username", uniqueString);
            startActivity(returnprev);
        });

        // get goals created by admin (Dropdown)

        fillArrays();

        iconPicture = (ImageView) findViewById(R.id.categoryUploadImageUpdate);
        category_desc1 = (EditText) findViewById(R.id.categoryDesc);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, category_name);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dropDownBox.setAdapter(adapter);

        dropDownBox.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                iconPicture.setImageBitmap(image1.get(i));
                category_desc1.setText(category_desc.get(i));
                passToNext = category_name.get(i);
                System.out.println("test " + passToNext);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });

        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, category_name);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dropDownBox.setAdapter(adapter1);

        dropDownBox.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                iconPicture.setImageBitmap(image1.get(i));
                category_desc1.setText(category_desc.get(i));
                passToNext = category_name.get(i);
                image2 = image1.get(i);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });
    }

    private void fillArrays() {
        db = new DataBaseHelper(this);
        category_name = new ArrayList<>();
        category_desc = new ArrayList<>();
        pre_image = new ArrayList<>();
        image1 = new ArrayList<>();
        Cursor c = db.retrieveCategory();
        if (c.getCount() != 0) {
            while (c.moveToNext()) {
                category_name.add(c.getString(0));
                category_desc.add(c.getString(1));
                pre_image.add(c.getBlob(2));
            }
        }

        for (int i = 0; pre_image.size() > i; i++) {
            image1.add(BitmapFactory.decodeByteArray(pre_image.get(i), 0, pre_image.get(i).length));
        }
        System.out.println("Image size: " + image1.size());
        System.out.println("category_name size: " + category_name.size());
        System.out.println("category_desc size: " + category_desc.size());

        // update change button
        updatechangesb = (Button) findViewById(R.id.updatechangesbutton);

        updatechangesb.setOnClickListener(v -> {
            System.out.println("image" + image2);
            if (checkIfEmpty()) {
                Toast.makeText(this, "You did not enter all fields.", Toast.LENGTH_LONG).show();
                return;
            } else {

                db = new DataBaseHelper(this);

                String catdesc = category_desc1.getText().toString();

                int updated = db.updateCategory(passToNext,catdesc,getBytes(image2));
                if (updated == 0) {
                    Toast.makeText(this, "Failed to update.", Toast.LENGTH_SHORT).show();
                } else {

                    Intent returnPage = new Intent(this, ManageCategoryPage.class);
                    returnPage.putExtra("username", uniqueString);
                    startActivity(returnPage);
                    Toast.makeText(this, "Update Successful", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    // checkifempty(Desc)
    private boolean checkIfEmpty() {
        if ( category_desc1.getText().toString().matches("")) {
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
                image2 = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImage);
                db = new DataBaseHelper(this);
                iconPicture.setImageBitmap(image2);
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