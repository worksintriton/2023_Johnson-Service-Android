package com.triton.johnson_tap_app.activity;

import static android.content.ContentValues.TAG;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.VolleyLog;
import com.google.gson.Gson;
import com.triton.johnson_tap_app.R;
import com.triton.johnson_tap_app.RestUtils;
import com.triton.johnson_tap_app.Service_Activity.LR_Service.JobListAdapter_LRService;
import com.triton.johnson_tap_app.Service_Activity.LR_Service.StartJob_LRService_Activity;
import com.triton.johnson_tap_app.api.APIInterface;
import com.triton.johnson_tap_app.api.RetrofitClient;
import com.triton.johnson_tap_app.requestpojo.JobListRequest;
import com.triton.johnson_tap_app.requestpojo.NotificationListRequest;
import com.triton.johnson_tap_app.responsepojo.JobListResponse;
import com.triton.johnson_tap_app.responsepojo.NotificationListResponse;
import com.triton.johnson_tap_app.responsepojo.SuccessResponse;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Notification_Activity extends AppCompatActivity {

    TextView txt_Title,txt_NoRecords;
    RelativeLayout rel_Job;
    RecyclerView recyclerView;
    Context context;
    ImageView img_Back;
    SharedPreferences sharedPreferences;
    String se_user_mobile_no,message;
    List<NotificationListResponse.NotificationData> notificationDataList;
    AlertDialog alertDialog;
    Dialog dialog;
    NotificationListAdapter notificationListAdapter;

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

        txt_Title.setText("Notifications");

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        se_user_mobile_no = sharedPreferences.getString("user_mobile_no", "default value");

        notificationCall();

        notificationUpdate();

        notificationDelete();

        img_Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onBackPressed();
            }
        });


    }

    private void notificationDelete() {

        APIInterface apiInterface = RetrofitClient.getClient().create(APIInterface.class);
        Call<SuccessResponse> call = apiInterface.NotificationdeleteCall(RestUtils.getContentType(),notificationUpdatelist());
        Log.w(TAG, "Notification Delete Response url  :%s" + " " + call.request().url().toString());

        call.enqueue(new Callback<SuccessResponse>() {
            @Override
            public void onResponse(Call<SuccessResponse> call, Response<SuccessResponse> response) {

                Log.w(VolleyLog.TAG," Notification Delete Response" + new Gson().toJson(response.body()));
            }

            @Override
            public void onFailure(Call<SuccessResponse> call, Throwable t) {

                Log.e("OTP", "--->" + t.getMessage());
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void notificationUpdate() {

        APIInterface apiInterface = RetrofitClient.getClient().create(APIInterface.class);
        Call<SuccessResponse> call = apiInterface.NotificationupdateCall(RestUtils.getContentType(),notificationUpdatelist());
        Log.w(TAG, "Notification Update Response url  :%s" + " " + call.request().url().toString());

        call.enqueue(new Callback<SuccessResponse>() {
            @Override
            public void onResponse(Call<SuccessResponse> call, Response<SuccessResponse> response) {

                Log.w(VolleyLog.TAG," Notification Update Response" + new Gson().toJson(response.body()));
            }

            @Override
            public void onFailure(Call<SuccessResponse> call, Throwable t) {
                Log.e("OTP", "--->" + t.getMessage());
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private NotificationListRequest notificationUpdatelist() {
        NotificationListRequest notificationlist = new NotificationListRequest();
        notificationlist.setUser_monbile_no(se_user_mobile_no);
        Log.w(TAG, "Notification Update Request " + new Gson().toJson(notificationlist));
        return notificationlist;
    }

    private void notificationCall() {

        dialog = new Dialog(context, R.style.NewProgressDialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.progroess_popup);
        dialog.show();

        APIInterface apiInterface = RetrofitClient.getClient().create(APIInterface.class);
        Call<NotificationListResponse> call = apiInterface.NotificationListCall(RestUtils.getContentType(),notificationlist());
        Log.w(TAG, "Notification List Response url  :%s" + " " + call.request().url().toString());

        call.enqueue(new Callback<NotificationListResponse>() {
            @Override
            public void onResponse(Call<NotificationListResponse> call, Response<NotificationListResponse> response) {

                Log.w(VolleyLog.TAG," Notification List Response" + new Gson().toJson(response.body()));

                dialog.dismiss();

                if (response.body() != null){

                    if (response.body().getCode() == 200){

                        message = response.body().getMessage();

                        Log.e("Message",""+message);

                        notificationDataList = response.body().getData();

                        if (notificationDataList.size() == 0){

                            recyclerView.setVisibility(View.GONE);
                            txt_NoRecords.setVisibility(View.VISIBLE);
                            txt_NoRecords.setText("No Records Found");

                        }

                        setNotification(notificationDataList);
                        Log.d("dataaaaa", String.valueOf(notificationDataList));

                    }
                    else{

                        dialog.dismiss();
                        recyclerView.setVisibility(View.GONE);
                        txt_NoRecords.setVisibility(View.VISIBLE);
                        txt_NoRecords.setText("Error 404 Found");
                    }

                }
                else{

                    dialog.dismiss();
                    recyclerView.setVisibility(View.GONE);
                    txt_NoRecords.setVisibility(View.VISIBLE);
                    txt_NoRecords.setText("Error 404 Found");
                }
            }

            @Override
            public void onFailure(Call<NotificationListResponse> call, Throwable t) {
                dialog.dismiss();
                recyclerView.setVisibility(View.GONE);
                txt_NoRecords.setVisibility(View.VISIBLE);
                txt_NoRecords.setText("Something Went Wrong.. Try Again Later");
                Log.e("OTP", "--->" + t.getMessage());
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void setNotification(List<NotificationListResponse.NotificationData> notificationDataList) {

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        notificationListAdapter = new NotificationListAdapter(this, notificationDataList);
        recyclerView.setAdapter(notificationListAdapter);

    }

    private NotificationListRequest notificationlist() {

        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd ");
        String Date = sdf.format(new Date());

        NotificationListRequest notificationlist = new NotificationListRequest();
        notificationlist.setUser_monbile_no(se_user_mobile_no);
        notificationlist.setDate(Date);
        Log.w(TAG, "Notification List Request " + new Gson().toJson(notificationlist));
        return notificationlist;
    }

    public void onBackPressed() {
        super.onBackPressed();
//        finish();
    }
}
