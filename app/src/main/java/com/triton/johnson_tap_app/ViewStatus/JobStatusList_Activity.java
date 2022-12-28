package com.triton.johnson_tap_app.ViewStatus;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.triton.johnson_tap_app.R;

import java.util.ArrayList;
import java.util.Arrays;

public class JobStatusList_Activity extends AppCompatActivity {

    TextView txt_Title,txt_NoRecords;
    RelativeLayout rel_Job;
    RecyclerView recyclerView;
    Context context;
    ImageView img_Back;
    SharedPreferences sharedPreferences;
    JobStatusListAdapter completedJobListAdapter;

    Integer Count;
    String str_JobID,str_StartTime,str_EndTime;
    ArrayList<String> Ar_JobID = new ArrayList<>();
    ArrayList<String> Ar_StartTime = new ArrayList<>();
    ArrayList<String> Ar_EndTime = new ArrayList<>();
    String[] strList_JobID,strList_StartTime,strList_EndTime;

    @SuppressLint("MissingInflatedId")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
//        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_job_details);
        context = this;

        txt_Title = findViewById(R.id.txt_title);
        rel_Job = findViewById(R.id.rel_job);
        recyclerView = findViewById(R.id.recyclerView);
        img_Back = findViewById(R.id.iv_back);
        rel_Job.setVisibility(View.GONE);
        txt_NoRecords = findViewById(R.id.txt_no_records);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        str_JobID = sharedPreferences.getString("Job_ID","");
        str_JobID = str_JobID.replaceAll("\\[", "").replaceAll("\\]","");
        str_StartTime = sharedPreferences.getString("StartTime","");
        str_StartTime = str_StartTime.replaceAll("\\[", "").replaceAll("\\]","");
        str_EndTime = sharedPreferences.getString("EndTime","");
        str_EndTime = str_EndTime.replaceAll("\\[", "").replaceAll("\\]","");
        Log.e("Job ID",""+ str_JobID);
        Count = sharedPreferences.getInt("Count", Integer.parseInt("0"));

        if (Count == 0){

            recyclerView.setVisibility(View.GONE);
            txt_NoRecords.setVisibility(View.VISIBLE);
            txt_NoRecords.setText("No Records Found");
        }

        strList_JobID = str_JobID.split(",");
        Ar_JobID = new ArrayList<String>(Arrays.asList(strList_JobID));
        Log.e("Job List",""+ Ar_JobID);

        strList_StartTime = str_StartTime.split(",");
        Ar_StartTime = new ArrayList<String>(Arrays.asList(strList_StartTime));

        strList_EndTime = str_EndTime.split(",");
        Ar_EndTime = new ArrayList<String>(Arrays.asList(strList_EndTime));


        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        completedJobListAdapter = new JobStatusListAdapter(this,Ar_JobID,Ar_StartTime,Ar_EndTime);
        recyclerView.setAdapter(completedJobListAdapter);

        img_Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onBackPressed();
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
