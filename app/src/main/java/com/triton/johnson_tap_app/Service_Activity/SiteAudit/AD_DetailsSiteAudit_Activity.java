package com.triton.johnson_tap_app.Service_Activity.SiteAudit;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.triton.johnson_tap_app.R;

public class AD_DetailsSiteAudit_Activity extends AppCompatActivity {


    ImageView img_Back;
    TextView txt_AdNumber;
    SharedPreferences sharedPreferences;
    Button btn_Next,btn_Prev;
    Context context;
    String status,se_user_mobile_no, se_user_name, se_id,check_id, service_title,message,jobid,osacompno;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_addetailssiteaudit);
        context = this;

        img_Back = findViewById(R.id.img_back);
        txt_AdNumber = findViewById(R.id.txt_adnumber);
        btn_Prev = findViewById(R.id.btn_prev);
        btn_Next = findViewById(R.id.btn_next);

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

        txt_AdNumber.setText(osacompno);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            //  service_title = extras.getString("service_title");
            status = extras.getString("status");
            //   Log.e("Name",":" + service_title);
            Log.e("Status", "" + status);
        }

        img_Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onBackPressed();
//                Intent send = new Intent(context, JobdetailsSiteaudit_Activity.class);
//                //send.putExtra("service_title",service_title);
//                send.putExtra("status", status);
//                startActivity(send);
            }
        });

        btn_Prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
//                Intent send = new Intent(context, JobdetailsSiteaudit_Activity.class);
//                send.putExtra("status", status);
//                startActivity(send);
            }
        });

        btn_Next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

         //       onBackPressed();
                Intent send = new Intent(context, StartJob_AuditActivity.class);
                send.putExtra("status", status);
                startActivity(send);
            }
        });

    }

    @Override
    public void onBackPressed() {

        super.onBackPressed();
//        Intent send = new Intent(context, JobdetailsSiteaudit_Activity.class);
//        //send.putExtra("service_title",service_title);
//        send.putExtra("status", status);
//        startActivity(send);
    }
}
