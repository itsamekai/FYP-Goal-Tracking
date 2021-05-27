package com.example.fyp.SettingGoals;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.fyp.Database.DataBaseHelper;
import com.example.fyp.GeneralMainPage.MainHomePage;
import com.example.fyp.R;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ChoosingGoal extends AppCompatActivity {

    public Spinner dropDownBox;
    public ImageView arrow;
    public ImageView icon;
    public TextView desc;
    public TextView button;
    public DataBaseHelper db;
    public ArrayList<String> category_name, category_desc;
    public ArrayList<byte[]> pre_image;
    public ArrayList<Bitmap> image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choosing_goal);
        String uniqueString = getIntent().getStringExtra("username");

        dropDownBox = (Spinner) findViewById(R.id.dropdownBox);
        arrow = (ImageView) findViewById(R.id.arrowBack);
        icon = (ImageView) findViewById(R.id.imageIcon);
        desc = (TextView) findViewById(R.id.desc);

        arrow.setOnClickListener(v -> {
            Intent returnprev = new Intent(this, MainHomePage.class);
            returnprev.putExtra("username", uniqueString);
            startActivity(returnprev);
        });

        fillArrays();





        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, category_name);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dropDownBox.setAdapter(adapter);

        dropDownBox.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    icon.setImageBitmap(image.get(i));
                    desc.setText(category_desc.get(i));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });

        button = (TextView) findViewById(R.id.nextB);
        button.setOnClickListener(v -> {
            Intent setGoal = new Intent(this, SetGoal.class);
            setGoal.putExtra("username", uniqueString);
            startActivity(setGoal);
        });
    }

    private void fillArrays() {
        db = new DataBaseHelper(this);
        category_name = new ArrayList<>();
        category_desc = new ArrayList<>();
        pre_image = new ArrayList<>();
        image = new ArrayList<>();
        Cursor c = db.retrieveCategory();
        if (c.getCount() != 0) {
            while (c.moveToNext()) {
                category_name.add(c.getString(0));
                category_desc.add(c.getString(1));
                pre_image.add(c.getBlob(2));
            }
        }

        for (int i = 0; pre_image.size() > i; i++) {
            image.add(BitmapFactory.decodeByteArray(pre_image.get(i), 0, pre_image.get(i).length));
        }
        System.out.println("Image size: " + image.size());
        System.out.println("category_name size: " + category_name.size());
        System.out.println("category_desc size: " + category_desc.size());

    }







}