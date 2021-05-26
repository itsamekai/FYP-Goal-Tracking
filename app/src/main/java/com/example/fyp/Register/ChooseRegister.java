package com.example.fyp.Register;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fyp.Login.LoginPage;
import com.example.fyp.R;
import com.example.fyp.Register.OrgRegisterPage;
import com.example.fyp.Register.SeniorRegisterPage;


public class ChooseRegister extends AppCompatActivity {

    public TextView chooseSenior;
    public TextView chooseOrg;
    public ImageView returnArrow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_register);

        chooseSenior = (TextView) findViewById(R.id.chooseSenior);
        chooseOrg = (TextView) findViewById(R.id.chooseOrgansiation);
        returnArrow = (ImageView) findViewById(R.id.returnArrow2);


        // return to previous page with arrow
        returnArrow.setOnClickListener(v -> {
            Intent returnPage = new Intent(this, LoginPage.class);
            startActivity(returnPage);
        });

        // redirects to senior registration page
        chooseSenior.setOnClickListener(v -> {
            Intent seniorPage = new Intent(this, SeniorRegisterPage.class);
            startActivity(seniorPage);
        });

        //redirects to organisation registration page
        // need to change the class still
        chooseOrg.setOnClickListener(v -> {
            Intent OrgPage = new Intent(this, OrgRegisterPage.class);
            startActivity(OrgPage);
        });


    }
}