package com.triton.johnson_tap_app.Service_Activity.SiteAudit;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.triton.johnson_tap_app.R;
import com.triton.johnson_tap_app.Service_Activity.ServicesActivity;


public class Checklist_AuditActivity extends AppCompatActivity {

    Context context;
    ImageView img_Back;
    CardView cv_Lift, cv_Escalator;
    String se_user_mobile_no, se_user_name,status,se_id,check_id, service_title,jobid,message,str_job_status,osacompno;
    SharedPreferences sharedPreferences;
    AlertDialog alertDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_checklist_audit);
        context = this;

        img_Back = findViewById(R.id.img_back);
        cv_Lift = findViewById(R.id.cv_lift);
        cv_Escalator = findViewById(R.id.cv_escalator);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        se_id = sharedPreferences.getString("_id", "default value");
        se_user_mobile_no = sharedPreferences.getString("user_mobile_no", "default value");
        se_user_name = sharedPreferences.getString("user_name", "default value");
        service_title = sharedPreferences.getString("service_title", "Services");
        jobid =sharedPreferences.getString("jobid","L-1234");
        osacompno = sharedPreferences.getString("osacompno","ADT2020202020");

        Log.e("Name", "" + service_title);
        Log.e("Mobile", ""+ se_user_mobile_no);
        Log.e("Jobid",""+ jobid);
        Log.e("osocompno",""+ osacompno);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            status = extras.getString("status");
            Log.e("Status", "" + status);
        }

        img_Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                onBackPressed();

//                Intent send = new Intent(context, StartJob_AuditActivity.class);
//                send.putExtra("status", status);
//                startActivity(send);
            }
        });

        cv_Lift.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent send = new Intent(context, AuditChecklist.class);

                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("service_type","L");
                Log.e("Type 1","L");
                editor.apply();
                send.putExtra("status", status);
                Log.e("Type","L");
//                send.putExtra("service_type","L");
                startActivity(send);
            }
        });

        cv_Escalator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent send = new Intent(context, AuditChecklist.class);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("service_type","E");
                editor.apply();
                send.putExtra("status", status);
               // send.putExtra("service_type","E");
                startActivity(send);
            }
        });

    }

    @Override
    public void onBackPressed() {
      //  super.onBackPressed();
//        Intent send = new Intent(context, StartJob_AuditActivity.class);
//        send.putExtra("status", status);
//        startActivity(send);

        alertDialog = new AlertDialog.Builder(context)
                .setTitle("Are you sure to close this job ?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent send = new Intent(context, ServicesActivity.class);
                        startActivity(send);
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        alertDialog.dismiss();
                    }
                })
                .show();
    }
}
