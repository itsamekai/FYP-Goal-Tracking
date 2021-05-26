package com.example.fyp.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fyp.Admin.MainAdminPage;
import com.example.fyp.Register.ChooseRegister;
import com.example.fyp.Database.DataBaseHelper;
import com.example.fyp.GeneralMainPage.MainHomePage;
import com.example.fyp.R;

public class LoginPage extends AppCompatActivity {
    public EditText usernameInput;
    public EditText passwordInput;
    public TextView createAccount;
    public Button removeButton;
    public Button loginButton;
    public DataBaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        createAccount = (TextView) findViewById(R.id.CreateAccount);
        createAccount.setOnClickListener(v -> {
            Intent intent = new Intent(this, ChooseRegister.class);
            startActivity(intent);
        });


        usernameInput = (EditText) findViewById(R.id.inputUser);
        passwordInput = (EditText) findViewById(R.id.inputPassword);
        loginButton = (Button) findViewById(R.id.loginButton);
        loginButton.setInputType(InputType.TYPE_NULL);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginAndRetrieve();
            }
        });


        // temporary button to remove all fields for check
        // lazy to write code l o l
        removeButton = findViewById(R.id.removeButton);
        removeButton.setOnClickListener(v -> {
            String add = "";
        });


    }

    private String validateMessage() {
        String word = "";
        if (usernameInput.getText().toString().matches("")) {
            word += "Please enter your username.\n";
        }
        if (passwordInput.getText().toString().matches("")) {
            word += "Please enter your password.";
        }
        else if (!validation()) {
            word += "Incorrect username or password.";
        }
        return word;
    }

    private boolean validation() {
        boolean validated = false;
        db = new DataBaseHelper(LoginPage.this);
        if (db.validateLogin(usernameInput.getText().toString(), passwordInput.getText().toString()) > 0) {
            validated = true;
        }
        return validated;
    }


    private void loginAndRetrieve() {

        if (validation()) {
            Intent loginUser;
            db = new DataBaseHelper(LoginPage.this);
            if (db.checkAccountType(usernameInput.getText().toString()).equals("User")) {
                loginUser = new Intent(this, MainHomePage.class);
                Toast.makeText(this, "Login success! Welcome.", Toast.LENGTH_SHORT).show();
                // unique username, use this username to get queries from the database etc
                loginUser.putExtra("username", usernameInput.getText().toString());
                startActivity(loginUser);
            }
            // do this once admin page is done
            else if (db.checkAccountType(usernameInput.getText().toString()).equals("Admin")) {
                Intent adminPage;
                 adminPage = new Intent(this, MainAdminPage.class);
                 adminPage.putExtra("username", usernameInput.getText().toString());
                Toast.makeText(this, "Login success! Welcome.", Toast.LENGTH_SHORT).show();
                 startActivity(adminPage);
            }
        }
        else Toast.makeText(this, validateMessage(), Toast.LENGTH_SHORT).show();

    }


}