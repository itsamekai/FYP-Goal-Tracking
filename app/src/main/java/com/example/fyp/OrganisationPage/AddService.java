package com.example.fyp.OrganisationPage;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fyp.Database.DataBaseHelper;
import com.example.fyp.ObjectClass.OrgServices;
import com.example.fyp.R;

import java.util.ArrayList;

public class AddService extends AppCompatActivity {

    public ImageView arrow;
    public TextView description;
    public Button addServiceB;
    public Spinner dropDownBox;
    public DataBaseHelper db;
    public String uniqueString, service_name;
    public ArrayList<String> serviceName, serviceDescription;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_service);
        uniqueString = getIntent().getStringExtra("username");

        dropDownBox = findViewById(R.id.select_service_spinner);
        arrow = findViewById(R.id.returnArrow17);
        addServiceB = findViewById(R.id.add_service_b);
        description = findViewById(R.id.service_description);

        arrow.setOnClickListener(v -> {
            Intent i = new Intent(this, orgPage.class);
            i.putExtra("username", uniqueString);
            startActivity(i);
        });

        addServiceB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmAddAlertBox();
            }
        });

        // fill the array for dropdown box
        fillArrays();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, serviceName);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dropDownBox.setAdapter(adapter);

        dropDownBox.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // for creating object of OrgService
                service_name = serviceName.get(position);
                description.setText(serviceDescription.get(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



    }

    private void fillArrays() {
        db = new DataBaseHelper(this);
        serviceName = new ArrayList<>();
        serviceDescription = new ArrayList<>();
        Cursor c = db.getService(db.getOrgID(uniqueString));
        if (c.getCount() != 0) {
            while (c.moveToNext()) {
                serviceName.add(c.getString(0));
                serviceDescription.add(c.getString(1));
            }
        }
    }

    private void confirmAddAlertBox() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setMessage("Is the service you have chosen the correct one?");
        dialog.setTitle("Confirm Service Selection");
        dialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                db = new DataBaseHelper(getApplicationContext());
                OrgServices os = new OrgServices(db.getOrgID(uniqueString), db.getServiceID(service_name));
                if (db.addDedicatedServicesForOrg(os)) {
                    Toast.makeText(getApplicationContext(), "Your organisation is now helping in the '" + service_name + "' service.", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(getApplicationContext(), orgPage.class);
                    i.putExtra("username", uniqueString);
                    startActivity(i);
                }
                else Toast.makeText(getApplicationContext(), "error adding to db", Toast.LENGTH_SHORT).show();
            }
        });

        dialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "Be sure to select the correct service that your organisation can help in!", Toast.LENGTH_LONG).show();
            }
        });
        AlertDialog alertDialog = dialog.create();
        alertDialog.show();
    }
}