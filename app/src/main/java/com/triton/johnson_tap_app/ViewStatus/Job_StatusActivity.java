package com.triton.johnson_tap_app.ViewStatus;

import static android.service.controls.ControlsProviderService.TAG;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.triton.johnson_tap_app.Adapter.ViewStatusAdapter;
import com.triton.johnson_tap_app.PetBreedTypeSelectListener;
import com.triton.johnson_tap_app.R;
import com.triton.johnson_tap_app.api.APIInterface;
import com.triton.johnson_tap_app.api.RetrofitClient;
import com.triton.johnson_tap_app.requestpojo.ViewStatusRequest;
import com.triton.johnson_tap_app.responsepojo.ViewStatusResponse;
import com.triton.johnson_tap_app.utils.RestUtils;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Job_StatusActivity extends AppCompatActivity implements PetBreedTypeSelectListener {

    ImageView iv_back;
    LinearLayout lin_layout,lin_layout1,lin_layout2,layout,layout1,layout2;
    TextView txt_Pending,txt_Completed,text2, txt_PendingCount, txt_COmpletedCount;
    String se_user_mobile_no, se_user_name, se_id,check_id,service_title;
    RecyclerView recyclerView_Pending,recyclerView_Completed;
    private List<ViewStatusResponse.Data> breedTypedataBeanList;
    List<ViewStatusResponse.Data.CompletedData> completedDataList;
    List<ViewStatusResponse.Data.PendingData> pendingDataList;
    List<ViewStatusResponse.Data.CompletedData.CompletedList> completedListList;
    String message;
    ViewStatusAdapter petBreedTypesListAdapter;
    PendingJobStatusAdapter pendingJobsAdapter;
    CompletedJobsStatusAdapter completedJobsAdapter;

    private String PetBreedType = "";
    ProgressDialog progressDialog;
    Context context;

    boolean PendingJob = true;
    boolean CompletedJob= true;

    @SuppressLint("MissingInflatedId")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_job_status);
        context = this;

        iv_back = (ImageView) findViewById(R.id.iv_back);
        lin_layout = (LinearLayout) findViewById(R.id.lin_layout);
        lin_layout1 = (LinearLayout) findViewById(R.id.lin_layout1);
        lin_layout2 = (LinearLayout) findViewById(R.id.lin_layout2);
        layout = (LinearLayout) findViewById(R.id.rel_job);
        layout1 = (LinearLayout) findViewById(R.id.layout1);
        layout2 = (LinearLayout) findViewById(R.id.layout2);
        txt_Pending = (TextView) findViewById(R.id.txt_pending);
        txt_PendingCount = findViewById(R.id.txt_pendingcount);
        txt_Completed = (TextView) findViewById(R.id.txt_completed);
        txt_COmpletedCount = findViewById(R.id.txt_completedcount);
        text2 = (TextView) findViewById(R.id.text2);
        recyclerView_Pending = findViewById(R.id.recyclerView_pendingjob);
        recyclerView_Completed = findViewById(R.id.recyclerView_completedjob);

       // recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        se_id = sharedPreferences.getString("_id", "default value");
        se_user_mobile_no = sharedPreferences.getString("user_mobile_no", "default value");
        se_user_name = sharedPreferences.getString("user_name", "default value");

        jobFindResponseCall(se_user_mobile_no);

        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                Intent send = new Intent(Job_StatusActivity.this, Main_Menu_ServicesActivity.class);
//                startActivity(send);

                onBackPressed();

            }
        });

        lin_layout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                layout.setVisibility(View.VISIBLE);
                layout1.setVisibility(View.GONE);
                layout2.setVisibility(View.GONE);

                lin_layout2.setBackgroundResource(R.drawable.background1);
                lin_layout1.setBackgroundResource(R.drawable.background1);
                lin_layout.setBackgroundColor(Color.parseColor("#B00303"));
                txt_Pending.setTextColor(Color.WHITE);
                txt_PendingCount.setTextColor(Color.WHITE);
                txt_Completed.setTextColor(Color.BLACK);
                txt_COmpletedCount.setTextColor(Color.BLACK);
                text2.setTextColor(Color.BLACK);

            }
        });

        lin_layout1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                layout.setVisibility(View.GONE);
                layout1.setVisibility(View.VISIBLE);
                layout2.setVisibility(View.GONE);
                lin_layout.setBackgroundResource(R.drawable.background1);
                lin_layout2.setBackgroundResource(R.drawable.background1);
                lin_layout1.setBackgroundColor(Color.parseColor("#B00303"));

                txt_Completed.setTextColor(Color.WHITE);
                txt_COmpletedCount.setTextColor(Color.WHITE);
                txt_Pending.setTextColor(Color.BLACK);
                txt_PendingCount.setTextColor(Color.BLACK);
                text2.setTextColor(Color.BLACK);

            }
        });

        lin_layout2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                layout.setVisibility(View.GONE);
                layout1.setVisibility(View.GONE);
                layout2.setVisibility(View.VISIBLE);

                lin_layout.setBackgroundResource(R.drawable.background1);
                lin_layout1.setBackgroundResource(R.drawable.background1);
                lin_layout2.setBackgroundColor(Color.parseColor("#B00303"));


                text2.setTextColor(Color.WHITE);
                txt_Pending.setTextColor(Color.BLACK);
                txt_Completed.setTextColor(Color.BLACK);

            }
        });
    }

    private void jobFindResponseCall(String job_no) {
        progressDialog = new ProgressDialog(Job_StatusActivity.this);
        progressDialog.setMessage("Please Wait..");
        progressDialog.setCancelable(false);
        progressDialog.show();

        APIInterface apiInterface = RetrofitClient.getClient().create(APIInterface.class);
        Call<ViewStatusResponse> call = apiInterface.View_statusResponseCall(RestUtils.getContentType(), serviceRequest(job_no));
        Log.w(TAG, "Jobno Find Response url  :%s" + " " + call.request().url().toString());
        call.enqueue(new Callback<ViewStatusResponse>() {
            @SuppressLint({"LogNotTimber", "SetTextI18n"})

            @Override
            public void onResponse(Call<ViewStatusResponse> call, Response<ViewStatusResponse> response) {
                Log.w(TAG, "Jobno Find Response" + new Gson().toJson(response.body()));

                progressDialog.dismiss();

                if (response.body() != null){

                    message = response.body().getMessage();

                    if (response.body().getCode() == 200){

                        if (response.body().getData() != null) {

                            String pendingjob = String.valueOf(response.body().getData().getPending_total());
                            Log.e("Pending Job",""+pendingjob);
                            txt_PendingCount.setText("(" + pendingjob + ")");

                            String completedjob = String.valueOf(response.body().getData().getCompleted_total());
                            Log.e("Completed Job",""+completedjob);
                            txt_COmpletedCount.setText("(" + completedjob + ")");

                            pendingDataList = response.body().getData().getPending_data();

                            setPendingjob(pendingDataList);

                            completedDataList = response.body().getData().getCompleted_data();

                            setCompletedjob(completedDataList);


                        }

                    }
                    else {

                        long delayInMillis = 10000;
                        Timer timer = new Timer();
                        timer.schedule(new TimerTask() {
                            @Override
                            public void run() {
                                progressDialog.dismiss();
                            }
                        }, delayInMillis);
                    }
                }
                else{
                    long delayInMillis = 10000;
                    Timer timer = new Timer();
                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            progressDialog.dismiss();
                        }
                    }, delayInMillis);
                }
            }

            @Override
            public void onFailure(Call<ViewStatusResponse> call,Throwable t) {
                long delayInMillis = 10000;
                Timer timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        progressDialog.dismiss();
                    }
                }, delayInMillis);
                Log.e("Jobno Find ", "--->" + t.getMessage());
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void setCompletedjob(List<ViewStatusResponse.Data.CompletedData> completedDataList) {

      //  Log.e("Total Completed","" + pendingDataList.size());
        recyclerView_Completed.setLayoutManager(new LinearLayoutManager(this));
        recyclerView_Completed.setItemAnimator(new DefaultItemAnimator());
        completedJobsAdapter = new CompletedJobsStatusAdapter(this,completedDataList);
        recyclerView_Completed.setAdapter(completedJobsAdapter);
    }

    private void setPendingjob(List<ViewStatusResponse.Data.PendingData> pendingDataList) {

      //  Log.e("Total Pending","" + pendingDataList.size());
        recyclerView_Pending.setLayoutManager(new LinearLayoutManager(this));
        recyclerView_Pending.setItemAnimator(new DefaultItemAnimator());
        pendingJobsAdapter = new PendingJobStatusAdapter(this,pendingDataList);
        recyclerView_Pending.setAdapter(pendingJobsAdapter);
    }

    private ViewStatusRequest serviceRequest(String job_no) {
        ViewStatusRequest service = new ViewStatusRequest();
        service.setUser_mobile_no(job_no);
        Log.w(TAG, "Jobno Find Request " + new Gson().toJson(service));
        return service;
    }

//    private void setBreedTypeView(List<ViewStatusResponse.Data> breedTypedataBeanList) {
//        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
//        recyclerView.setItemAnimator(new DefaultItemAnimator());
//        petBreedTypesListAdapter = new ViewStatusAdapter(getApplicationContext(), breedTypedataBeanList,this);
//        recyclerView.setAdapter(petBreedTypesListAdapter);
//    }

    public void petBreedTypeSelectListener(String petbreedtitle, String petbreedid) {
        PetBreedType = petbreedtitle;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}