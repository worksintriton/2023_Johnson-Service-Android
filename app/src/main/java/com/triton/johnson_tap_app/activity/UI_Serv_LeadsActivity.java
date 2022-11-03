package com.triton.johnson_tap_app.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.triton.johnson_tap_app.R;

import java.util.Calendar;

public class UI_Serv_LeadsActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    Spinner spinner,spinner1,spinner2,spinner3,spinner_status,spinner_state,spinner_won;
    String[] courses = { "001", "002", "003", "004", "005"};
    String[] status = { "LBQ", "In Progress", " Not Feasible"};
    String[] state = { "Chennai", "Trichy", "Coimbatore", "Thanjavur"};
    EditText Date;
    DatePickerDialog datepicker;
    ImageView iv_back;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_ui_serv_leads);

        spinner = (Spinner) findViewById(R.id.spinner);
        spinner1 = (Spinner) findViewById(R.id.spinner1);
        spinner2 = (Spinner) findViewById(R.id.spinner2);
        spinner3 = (Spinner) findViewById(R.id.spinner3);
        spinner_won = (Spinner) findViewById(R.id.spinner_won);
        spinner_status = (Spinner) findViewById(R.id.spinner_status);
        spinner_state = (Spinner) findViewById(R.id.spinner_state);
        Date = (EditText) findViewById(R.id.calender);
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

        spinner_status.setOnItemSelectedListener(this);
        ArrayAdapter ad4 = new ArrayAdapter(this, android.R.layout.simple_spinner_item, status);
        ad4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_status.setAdapter(ad4);

        spinner_state.setOnItemSelectedListener(this);
        ArrayAdapter ad5 = new ArrayAdapter(this, android.R.layout.simple_spinner_item, state);
        ad5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_state.setAdapter(ad5);

        spinner_won.setOnItemSelectedListener(this);
        ArrayAdapter ad6 = new ArrayAdapter(this, android.R.layout.simple_spinner_item, courses);
        ad6.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_won.setAdapter(ad6);

        Date.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                datepicker = new DatePickerDialog(UI_Serv_LeadsActivity.this,
                        new DatePickerDialog.OnDateSetListener() {

                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                Date.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, year, month, day);
                datepicker.show();
            }
        });

        iv_back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent send = new Intent(UI_Serv_LeadsActivity.this, MainActivity.class);
                startActivity(send);
            }
        });

    }

    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
      //  Toast.makeText(getApplicationContext(), courses[i], Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}