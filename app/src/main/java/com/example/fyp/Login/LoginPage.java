package com.example.fyp.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fyp.Admin.MainAdminPage;
import com.example.fyp.OrganisationPage.orgPage;
import com.example.fyp.Register.ChooseRegister;
import com.example.fyp.Database.DataBaseHelper;
import com.example.fyp.GeneralMainPage.MainHomePage;
import com.example.fyp.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginPage extends AppCompatActivity {
    public EditText usernameInput;
    public EditText passwordInput;
    public EditText loginInput;
    public TextView createAccount;
    public Button removeButton;
    public Button loginButton;
    public DataBaseHelper db;
    public Button addAdmin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        createAccount = (TextView) findViewById(R.id.CreateAccount);
        createAccount.setOnClickListener(v -> {
            Intent intent = new Intent(this, ChooseRegister.class);
            startActivity(intent);
        });

//        addAdmin = findViewById(R.id.removeButton);
//        addAdmin.setOnClickListener(v -> {
//            db = new DataBaseHelper(this);
//            if(db.addTemporaryAdmin()) {
//                Toast.makeText(this, "added test admin: user is testadmin", Toast.LENGTH_SHORT).show();
//            }
//            else {
//                Toast.makeText(this, "test", Toast.LENGTH_SHORT).show();
//            }
//        });

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

    private boolean checkUserorOrg(String loginInput) {
        String regex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(loginInput);
        return m.matches();
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

        else if (checkUserorOrg(usernameInput.getText().toString()) == true) {
            int a = 0;
            db = new DataBaseHelper(LoginPage.this);

            if(db.checkOrgLogin(usernameInput.getText().toString(), passwordInput.getText().toString()) == 1) {
                a = db.checkOrgLogin(usernameInput.getText().toString(), passwordInput.getText().toString());
                System.out.println(a);
                Intent orgPage;
                orgPage = new Intent(this, orgPage.class);
                orgPage.putExtra("username", usernameInput.getText().toString());
                Toast.makeText(this, "Login Success! Welcome.", Toast.LENGTH_SHORT).show();
                startActivity(orgPage);

            } else {
                System.out.println("test" + db.checkOrgLogin(usernameInput.getText().toString(), passwordInput.getText().toString()));
                Toast.makeText(this, "Login Failed! Please try again." ,Toast.LENGTH_SHORT).show();
            }
        }

        else Toast.makeText(this, validateMessage(), Toast.LENGTH_SHORT).show();

    }
}

