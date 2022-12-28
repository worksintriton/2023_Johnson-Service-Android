package com.triton.johnson_tap_app.Service_Activity.Preventive_Services;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.triton.johnson_tap_app.R;

public class Preventive_StatusActivity extends AppCompatActivity {

    TextView text;
    CardView full,material,lift;
    String value="",job_id,mr1,mr2,mr3,mr4,mr5,mr6,mr7,mr8,mr9,mr10,value_s,service_title,preventive_check,action_req_customer,Form1_value,Form1_name,Form1_comments,tech_signature="";
    String Form1_cat_id,Form1_group_id,status;
    ImageView iv_back;
    SharedPreferences sharedPreferences;
    TextView txt_Jobid,txt_Starttime,txt_Lift;
    String str_StartTime;

    @SuppressLint("MissingInflatedId")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_preventive_status);

        text = findViewById(R.id.text);
        full = findViewById(R.id.card_full);
        material = findViewById(R.id.card_material);
        lift = findViewById(R.id.card_lift);
        iv_back = (ImageView) findViewById(R.id.iv_back);
        txt_Starttime = findViewById(R.id.txt_starttime);
        txt_Jobid = findViewById(R.id.txt_jobid);
        txt_Lift = findViewById(R.id.txt_lift);

        Spannable name_Upload = new SpannableString("PM Status ");
        name_Upload.setSpan(new ForegroundColorSpan(Preventive_StatusActivity.this.getResources().getColor(R.color.colorAccent)), 0, name_Upload.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        text.setText(name_Upload);
        Spannable name_Upload1 = new SpannableString("*");
        name_Upload1.setSpan(new ForegroundColorSpan(Color.RED), 0, name_Upload1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        text.append(name_Upload1);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        value_s = sharedPreferences.getString("value","default value");
        job_id = sharedPreferences.getString("job_id","L1234");
        Log.e("Value",""+value_s);

        str_StartTime = sharedPreferences.getString("starttime","");
        str_StartTime = str_StartTime.replaceAll("[^0-9-:]", " ");
        Log.e("Start Time",str_StartTime);
        txt_Jobid.setText("Job ID : " + job_id);
        txt_Starttime.setText("Start Time : " + str_StartTime);

        String LiftType = job_id.substring(0,1);
        Log.e("Hi","Lift Type "+ LiftType);

        if (LiftType.equals("E")){

            txt_Lift.setText("Escalator Shutdown");
        }



        Bundle extras = getIntent().getExtras();
        if (extras != null) {
           // value_s = extras.getString("valuess");
            status = extras.getString("status");
            Log.e("Status",""+status);
        }
        if (extras != null) {
          //  job_id = extras.getString("job_id");
        }

        if (extras != null) {
          //  value = extras.getString("value");
        }

        if (extras != null) {
          //  service_title = extras.getString("service_title");
        }
        if (extras != null) {
            mr1 = extras.getString("mr1");
        }
        if (extras != null) {
            mr2 = extras.getString("mr2");
        }
        if (extras != null) {
            mr3 = extras.getString("mr3");
        }
        if (extras != null) {
            mr4 = extras.getString("mr4");
        }
        if (extras != null) {
            mr5 = extras.getString("mr5");
        }
        if (extras != null) {
            mr6 = extras.getString("mr6");
        }
        if (extras != null) {
            mr7 = extras.getString("mr7");
        }
        if (extras != null) {
            mr8 = extras.getString("mr8");
        }
        if (extras != null) {
            mr9 = extras.getString("mr9");
        }
        if (extras != null) {
            mr10 = extras.getString("mr10");
        }
        if (extras != null) {
           // preventive_check = extras.getString("preventive_check");
        }
        if (extras != null) {
          //  action_req_customer = extras.getString("action_req_customer");
        }

        if (extras != null) {
            Form1_value = extras.getString("Form1_value");
        }

        if (extras != null) {
            Form1_name = extras.getString("Form1_name");
        }
        if (extras != null) {
            Form1_comments = extras.getString("Form1_comments");
        }
        if (extras != null) {
            Form1_cat_id = extras.getString("Form1_cat_id");
        }

        if (extras != null) {
            Form1_group_id = extras.getString("Form1_group_id");
        }

        if (extras != null) {
            tech_signature = extras.getString("tech_signature");
        }

        if(value_s.equals("yes")){
            full.setVisibility(View.GONE);
            material.setVisibility(View.VISIBLE);
            lift.setVisibility(View.VISIBLE);
        }
        else {
            full.setVisibility(View.VISIBLE);
            material.setVisibility(View.VISIBLE);
            lift.setVisibility(View.VISIBLE);
        }


        full.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent send = new Intent( Preventive_StatusActivity.this, Technician_signature_preventiveActivity.class);
                send.putExtra("valuess",value_s);
                send.putExtra("job_id",job_id);
                send.putExtra("value",value);
                send.putExtra("service_title",service_title);
                send.putExtra("mr1", mr1);
                send.putExtra("mr2", mr2);
                send.putExtra("mr3", mr3);
                send.putExtra("mr4", mr4);
                send.putExtra("mr5", mr5);
                send.putExtra("mr6", mr6);
                send.putExtra("mr7", mr7);
                send.putExtra("mr8", mr8);
                send.putExtra("mr9", mr9);
                send.putExtra("mr10", mr10);
                send.putExtra("preventive_check",preventive_check);

                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("pmstatus", "Completed in full");
                editor.apply();

              //  send.putExtra("pm_status","Completed in full");
                send.putExtra("action_req_customer",action_req_customer);
                send.putExtra("Form1_value", Form1_value);
                send.putExtra("Form1_name",Form1_name);
                send.putExtra("Form1_comments",Form1_comments);
                send.putExtra("Form1_cat_id",Form1_cat_id);
                send.putExtra("Form1_group_id",Form1_group_id);
                send.putExtra("tech_signature",tech_signature);
                send.putExtra("status",status);
                startActivity(send);
            }
        });

        material.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent send = new Intent( Preventive_StatusActivity.this, Technician_signature_preventiveActivity.class);
                send.putExtra("valuess",value_s);
                send.putExtra("job_id",job_id);
                send.putExtra("value",value);
                send.putExtra("service_title",service_title);
                send.putExtra("mr1", mr1);
                send.putExtra("mr2", mr2);
                send.putExtra("mr3", mr3);
                send.putExtra("mr4", mr4);
                send.putExtra("mr5", mr5);
                send.putExtra("mr6", mr6);
                send.putExtra("mr7", mr7);
                send.putExtra("mr8", mr8);
                send.putExtra("mr9", mr9);
                send.putExtra("mr10", mr10);
                send.putExtra("preventive_check",preventive_check);

                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("pmstatus", "Completed but material to be replaced");
                editor.apply();

            //    send.putExtra("pm_status","Completed but material to be replaced");
                send.putExtra("action_req_customer",action_req_customer);
                send.putExtra("Form1_value", Form1_value);
                send.putExtra("Form1_name",Form1_name);
                send.putExtra("Form1_comments",Form1_comments);
                send.putExtra("Form1_cat_id",Form1_cat_id);
                send.putExtra("Form1_group_id",Form1_group_id);
                send.putExtra("tech_signature",tech_signature);
                send.putExtra("status",status);
                startActivity(send);
            }
        });

        lift.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent send = new Intent( Preventive_StatusActivity.this,  Technician_signature_preventiveActivity.class);
                send.putExtra("valuess",value_s);
                send.putExtra("job_id",job_id);
                send.putExtra("value",value);
                send.putExtra("service_title",service_title);
                send.putExtra("mr1", mr1);
                send.putExtra("mr2", mr2);
                send.putExtra("mr3", mr3);
                send.putExtra("mr4", mr4);
                send.putExtra("mr5", mr5);
                send.putExtra("mr6", mr6);
                send.putExtra("mr7", mr7);
                send.putExtra("mr8", mr8);
                send.putExtra("mr9", mr9);
                send.putExtra("mr10", mr10);
                send.putExtra("preventive_check",preventive_check);

               // send.putExtra("pm_status","Lift Shutdown");
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("pmstatus", "Lift Shutdown");
                editor.apply();

                send.putExtra("action_req_customer",action_req_customer);
                send.putExtra("Form1_value", Form1_value);
                send.putExtra("Form1_name",Form1_name);
                send.putExtra("Form1_comments",Form1_comments);
                send.putExtra("Form1_cat_id",Form1_cat_id);
                send.putExtra("Form1_group_id",Form1_group_id);
                send.putExtra("tech_signature",tech_signature);
                send.putExtra("status",status);
                startActivity(send);
            }
        });

        iv_back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

//                Intent send = new Intent(Preventive_StatusActivity.this, Preventive_action_requiredActivity.class);
//                send.putExtra("valuess",value_s);
//                send.putExtra("job_id",job_id);
//                send.putExtra("value",value);
//                send.putExtra("service_title",service_title);
//                send.putExtra("mr1", mr1);
//                send.putExtra("mr2", mr2);
//                send.putExtra("mr3", mr3);
//                send.putExtra("mr4", mr4);
//                send.putExtra("mr5", mr5);
//                send.putExtra("mr6", mr6);
//                send.putExtra("mr7", mr7);
//                send.putExtra("mr8", mr8);
//                send.putExtra("mr9", mr9);
//                send.putExtra("mr10", mr10);
//                send.putExtra("preventive_check",preventive_check);
//                send.putExtra("action_req_customer",action_req_customer);
//                send.putExtra("Form1_value", Form1_value);
//                send.putExtra("Form1_name",Form1_name);
//                send.putExtra("Form1_comments",Form1_comments);
//                send.putExtra("Form1_cat_id",Form1_cat_id);
//                send.putExtra("Form1_group_id",Form1_group_id);
//                startActivity(send);

                onBackPressed();
                }
        });
    }

    @Override
    public void onBackPressed() {
       // super.onBackPressed();

        Intent send = new Intent( Preventive_StatusActivity.this, Preventive_action_requiredActivity.class);
        send.putExtra("status",status);
        startActivity(send);
    }
}