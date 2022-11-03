package com.triton.johnson_tap_app.Service_Activity.Preventive_Services;

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

public class Material_Request_PreventiveActivity extends AppCompatActivity {

    TextView text;
    CardView yes,no;
    String value;
    ImageView iv_back;
    String job_id,Form1_value,Form1_name,Form1_comments,Form1_cat_id,Form1_group_id,service_title,status;
    SharedPreferences sharedPreferences;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_material_request_preventive);

        text = findViewById(R.id.text);
        yes = findViewById(R.id.card_yes);
        no = findViewById(R.id.card_no);
        iv_back = (ImageView) findViewById(R.id.iv_back);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
         //   job_id = extras.getString("job_id");
            status = extras.getString("status");
            Log.e("Status",""+status);
        }

        if (extras != null) {
           // value = extras.getString("value");
        }

        if (extras != null) {
          //  service_title = extras.getString("service_title");
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

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        service_title = sharedPreferences.getString("service_title", "default value");
        job_id = sharedPreferences.getString("job_id","L1234");
        Log.e("Name",service_title);
        Log.e("JobID",job_id);

        Spannable name_Upload = new SpannableString("Raise MR ");
        name_Upload.setSpan(new ForegroundColorSpan(Material_Request_PreventiveActivity.this.getResources().getColor(R.color.colorAccent)), 0, name_Upload.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        text.setText(name_Upload);
        Spannable name_Upload1 = new SpannableString("*");
        name_Upload1.setSpan(new ForegroundColorSpan(Color.RED), 0, name_Upload1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        text.append(name_Upload1);

        yes.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent send = new Intent( Material_Request_PreventiveActivity.this, Material_Request_MR_Screen_PreventiveActivity.class);
              //  send.putExtra("valuess","yes");
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("value", "yes");
                editor.apply();
//                send.putExtra("job_id",job_id);
//                send.putExtra("value",value);
//                send.putExtra("service_title",service_title);
//                send.putExtra("Form1_value", Form1_value);
//                send.putExtra("Form1_name",Form1_name);
//                send.putExtra("Form1_comments",Form1_comments);
//                send.putExtra("Form1_cat_id",Form1_cat_id);
//                send.putExtra("Form1_group_id",Form1_group_id);
                send.putExtra("status",status);
                startActivity(send);
            }
        });

        no.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent send = new Intent( Material_Request_PreventiveActivity.this, Preventive_checklistActivity.class);
               // send.putExtra("valuess","no");

                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("value", "no");
                editor.apply();

              //  send.putExtra("job_id",job_id);
//                send.putExtra("value",value);
//                send.putExtra("service_title",service_title);
//                send.putExtra("Form1_value", Form1_value);
//                send.putExtra("Form1_name",Form1_name);
//                send.putExtra("Form1_comments",Form1_comments);
//                send.putExtra("Form1_cat_id",Form1_cat_id);
//                send.putExtra("Form1_group_id",Form1_group_id);
                send.putExtra("status",status);
                startActivity(send);
            }
        });

        iv_back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
//
//                Intent send = new Intent(Material_Request_PreventiveActivity.this, Recycler_SpinnerActivity.class);
//                send.putExtra("valuess","no");
//                send.putExtra("job_id",job_id);
//                send.putExtra("value",value);
//                send.putExtra("service_title",service_title);
//                startActivity(send);

                onBackPressed();
            }
        });
    }

    @Override
    public void onBackPressed() {
      //  super.onBackPressed();

        Intent send = new Intent(Material_Request_PreventiveActivity.this, Recycler_SpinnerActivity.class);
        send.putExtra("status",status);
        startActivity(send);
    }
}