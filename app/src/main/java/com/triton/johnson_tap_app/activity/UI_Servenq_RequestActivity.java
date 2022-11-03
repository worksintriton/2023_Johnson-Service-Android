package com.triton.johnson_tap_app.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

import com.triton.johnson_tap_app.R;

public class UI_Servenq_RequestActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

   Spinner spinner,spinner1,spinner2,spinner3,spinner_zoon,spinner_state,spinner4,spinner5,spinner6,spinner_door;
    String[] courses = { "001", "002", "003", "004", "005"};
    String[] zone = { "North", "South", "West", "East"};
    String[] state = { "Chennai", "Trichy", "Coimbatore", "Thanjavur"};
    String[] door = { "COPD", "SOPD", "Manual"};
    ImageView iv_back;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_ui_servenq_request);

        spinner = (Spinner) findViewById(R.id.spinner);
        spinner1 = (Spinner) findViewById(R.id.spinner1);
        spinner2 = (Spinner) findViewById(R.id.spinner2);
        spinner3 = (Spinner) findViewById(R.id.spinner3);
        spinner4 = (Spinner) findViewById(R.id.spinner4);
        spinner5 = (Spinner) findViewById(R.id.spinner5);
        spinner6 = (Spinner) findViewById(R.id.spinner6);
        spinner_zoon = (Spinner) findViewById(R.id.spinner_zoon);
        spinner_state = (Spinner) findViewById(R.id.spinner_state);
        spinner_door = (Spinner) findViewById(R.id.spinner_door);
        iv_back = (ImageView) findViewById(R.id.iv_back);

        spinner.setOnItemSelectedListener(this);
        ArrayAdapter ad = new ArrayAdapter(this, android.R.layout.simple_spinner_item, courses);
        ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(ad);

        spinner1.setOnItemSelectedListener(this);
        ArrayAdapter ad1 = new ArrayAdapter(this, android.R.layout.simple_spinner_item, courses);
        ad1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(ad1);

        spinner2.setOnItemSelectedListener(this);
        ArrayAdapter ad2 = new ArrayAdapter(this, android.R.layout.simple_spinner_item, courses);
        ad2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(ad2);

        spinner3.setOnItemSelectedListener(this);
        ArrayAdapter ad3 = new ArrayAdapter(this, android.R.layout.simple_spinner_item, courses);
        ad3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner3.setAdapter(ad3);

        spinner_zoon.setOnItemSelectedListener(this);
        ArrayAdapter ad4 = new ArrayAdapter(this, android.R.layout.simple_spinner_item, zone);
        ad4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_zoon.setAdapter(ad4);

        spinner_state.setOnItemSelectedListener(this);
        ArrayAdapter ad5 = new ArrayAdapter(this, android.R.layout.simple_spinner_item, state);
        ad5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_state.setAdapter(ad5);

        spinner4.setOnItemSelectedListener(this);
        ArrayAdapter ad6 = new ArrayAdapter(this, android.R.layout.simple_spinner_item, courses);
        ad6.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner4.setAdapter(ad6);

        spinner5.setOnItemSelectedListener(this);
        ArrayAdapter ad7 = new ArrayAdapter(this, android.R.layout.simple_spinner_item, courses);
        ad7.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner5.setAdapter(ad7);

        spinner6.setOnItemSelectedListener(this);
        ArrayAdapter ad8 = new ArrayAdapter(this, android.R.layout.simple_spinner_item, courses);
        ad8.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner6.setAdapter(ad8);

        spinner_door.setOnItemSelectedListener(this);
        ArrayAdapter ad9 = new ArrayAdapter(this, android.R.layout.simple_spinner_item, door);
        ad9.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_door.setAdapter(ad9);

        iv_back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent send = new Intent(UI_Servenq_RequestActivity.this, MainActivity.class);
                startActivity(send);
            }
        });

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
       // Toast.makeText(getApplicationContext(), courses[i], Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}