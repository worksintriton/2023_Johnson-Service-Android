package com.triton.johnson_tap_app.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.triton.johnson_tap_app.R;
import com.triton.johnson_tap_app.session.SessionManager;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    private String TAG ="MainActivity";

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.btn_job)
    Button btn_job;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.btn_enquiry)
    Button btn_enquird;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.btn_pending)
    Button btn_pending;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.btn_activity)
    Button btn_activity;

    @SuppressLint("NonConstatntResourceId")
    @BindView(R.id.btn_general)
    Button btn_general;
    ImageView iv_back;
    private SessionManager session;
    private String url = "http://smart.johnsonliftsltd.com/";
    private ProgressDialog progDailog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
     //   getSupportActionBar().hide();
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        Log.w(TAG,"Oncreate -->");
        session = new SessionManager(getApplicationContext());
        iv_back = (ImageView) findViewById(R.id.iv_back);

        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent send = new Intent(MainActivity.this, Dashbaord_MainActivity.class);
                startActivity(send);

            }
        });

        btn_activity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent send = new Intent(MainActivity.this, UI_Servenq_RequestActivity.class);
                startActivity(send);

            }
        });


        btn_job.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent send = new Intent(MainActivity.this, UI_Serv_LeadsActivity.class);
                startActivity(send);
            }
        });

        btn_general.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent send = new Intent(MainActivity.this, Daily_Collection_DetailsActivity.class);
                startActivity(send);
            }
        });

        btn_enquird.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent send = new Intent(MainActivity.this, Enquiry_Status_ReviewActivity.class);
                startActivity(send);
            }
        });

        btn_pending.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent send = new Intent(MainActivity.this, Pending_Collection_ReviewActivity.class);
                startActivity(send);
            }
        });

    }


    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        showWarningLogout();

    }


    private void showWarningLogout() {
        new AlertDialog.Builder(this)
                .setMessage("Are you sure you want to exit?")
                .setCancelable(false)
                .setPositiveButton("Yes", (dialog, id) -> {
                    session.logoutUser();
                })
                .setNegativeButton("No", null)
                .show();
    }




}