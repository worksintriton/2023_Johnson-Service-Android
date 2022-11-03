package com.triton.johnson_tap_app.Service_Activity.Breakdown_Services;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.triton.johnson_tap_app.Adapter.ViewStatusAdapter;
import com.triton.johnson_tap_app.PetBreedTypeSelectListener;
import com.triton.johnson_tap_app.R;
import com.triton.johnson_tap_app.activity.Main_Menu_ServicesActivity;
import com.triton.johnson_tap_app.responsepojo.ViewStatusResponse;

import java.util.List;

public class Job_StatusActivity extends AppCompatActivity implements PetBreedTypeSelectListener {

    ImageView iv_back;
    LinearLayout lin_layout,lin_layout1,lin_layout2,layout,layout1,layout2;
    TextView text,text1,text2;
    String se_user_mobile_no, se_user_name, se_id,check_id,service_title;
    RecyclerView recyclerView;
    private List<ViewStatusResponse.DataBean> breedTypedataBeanList;
    private List<ViewStatusResponse.DataBean> breedTypedataBeanList1;
    String message;
    ViewStatusAdapter petBreedTypesListAdapter;
    private String PetBreedType = "";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_job_status);

        iv_back = (ImageView) findViewById(R.id.iv_back);
        lin_layout = (LinearLayout) findViewById(R.id.lin_layout);
        lin_layout1 = (LinearLayout) findViewById(R.id.lin_layout1);
        lin_layout2 = (LinearLayout) findViewById(R.id.lin_layout2);
        layout = (LinearLayout) findViewById(R.id.layout);
        layout1 = (LinearLayout) findViewById(R.id.layout1);
        layout2 = (LinearLayout) findViewById(R.id.layout2);
        text = (TextView) findViewById(R.id.text);
        text1 = (TextView) findViewById(R.id.text1);
        text2 = (TextView) findViewById(R.id.text2);
       // recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        se_id = sharedPreferences.getString("_id", "default value");
        se_user_mobile_no = sharedPreferences.getString("user_mobile_no", "default value");
        se_user_name = sharedPreferences.getString("user_name", "default value");

   //     jobFindResponseCall(se_user_mobile_no);

        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent send = new Intent(Job_StatusActivity.this, Main_Menu_ServicesActivity.class);
                startActivity(send);

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
                text.setTextColor(Color.WHITE);
                text1.setTextColor(Color.BLACK);
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

                text1.setTextColor(Color.WHITE);
                text.setTextColor(Color.BLACK);
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
                text.setTextColor(Color.BLACK);
                text1.setTextColor(Color.BLACK);

            }
        });
    }

//    private void jobFindResponseCall(String job_no) {
//        APIInterface apiInterface = RetrofitClient.getClient().create(APIInterface.class);
//        Call<ViewStatusResponse> call = apiInterface.View_statusResponseCall(RestUtils.getContentType(), serviceRequest(job_no));
//        Log.w(TAG, "Jobno Find Response url  :%s" + " " + call.request().url().toString());
//        call.enqueue(new Callback<ViewStatusResponse>() {
//            @SuppressLint("LogNotTimber")
//            @Override
//            public void onResponse(@NonNull Call<ViewStatusResponse> call, @NonNull Response<ViewStatusResponse> response) {
//                Log.w(TAG, "Jobno Find Response" + new Gson().toJson(response.body()));
//
//                if (response.body() != null) {
//
//                    message = response.body().getMessage();
//                    Log.d("message", message);
//
//                    if (200 == response.body().getCode()) {
//                        if (response.body().getData() != null) {
//                            breedTypedataBeanList = response.body().getData();
//
//                            setBreedTypeView(breedTypedataBeanList);
//
//                            Log.d("dataaaaa", String.valueOf(breedTypedataBeanList));
//
//                        }
//
//                    } else if (400 == response.body().getCode()) {
//                        if (response.body().getMessage() != null && response.body().getMessage().equalsIgnoreCase("There is already a user registered with this email id. Please add new email id")) {
//
//                        }
//                    } else {
//
//                        Toasty.warning(getApplicationContext(), "" + response.body().getMessage(), Toasty.LENGTH_LONG).show();
//                    }
//                }
//
//
//            }
//
//            @Override
//            public void onFailure(@NonNull Call<ViewStatusResponse> call, @NonNull Throwable t) {
//                Log.e("Jobno Find ", "--->" + t.getMessage());
//                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        });
//
//    }
//
//    private ViewStatusRequest serviceRequest(String job_no) {
//        ViewStatusRequest service = new ViewStatusRequest();
//        service.setUser_mobile_no(job_no);
//        Log.w(TAG, "Jobno Find Request " + new Gson().toJson(service));
//        return service;
//    }
//
//    private void setBreedTypeView(List<ViewStatusResponse.DataBean> breedTypedataBeanList) {
//        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
//        recyclerView.setItemAnimator(new DefaultItemAnimator());
//        petBreedTypesListAdapter = new ViewStatusAdapter(getApplicationContext(), breedTypedataBeanList,this);
//        recyclerView.setAdapter(petBreedTypesListAdapter);
//    }

    public void petBreedTypeSelectListener(String petbreedtitle, String petbreedid) {
        PetBreedType = petbreedtitle;
    }
}