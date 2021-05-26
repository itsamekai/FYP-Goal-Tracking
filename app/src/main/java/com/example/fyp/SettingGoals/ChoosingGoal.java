package com.example.fyp.SettingGoals;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.fyp.GeneralMainPage.MainHomePage;
import com.example.fyp.R;

public class ChoosingGoal extends AppCompatActivity {

    public Spinner dropDownBox;
    public ImageView arrow;
    public ImageView icon;
    public TextView desc;
    public TextView button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choosing_goal);

        dropDownBox = (Spinner) findViewById(R.id.dropdownBox);
        arrow = (ImageView) findViewById(R.id.arrowBack);
        icon = (ImageView) findViewById(R.id.imageIcon);
        desc = (TextView) findViewById(R.id.desc);

        arrow.setOnClickListener(v ->{
            startActivity(new Intent(this, MainHomePage.class));
        });


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.GoalType));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dropDownBox.setAdapter(adapter);

        dropDownBox.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                // fitness
                if (i == 0) {
                    icon.setImageResource(R.drawable.exercise);
                    desc.setText(getString(R.string.fitness_desc));
                }
                // health
                else if (i == 1){
                    icon.setImageResource(R.drawable.healthcare);
                    desc.setText(getString(R.string.health_desc));
                }
                // finance
                else if (i == 2) {
                    icon.setImageResource(R.drawable.budget);
                    desc.setText(getString(R.string.finance_desc));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });

        button = (TextView) findViewById(R.id.nextB);
        button.setOnClickListener(v -> {
            Intent setGoal = new Intent(this, SetGoal.class);
            String uniqueString = getIntent().getStringExtra("username");
            setGoal.putExtra("username", uniqueString);
            startActivity(setGoal);
        });
}
}