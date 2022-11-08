package com.triton.johnson_tap_app.Service_Activity.Breakdown_Services;

import static android.content.ContentValues.TAG;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.triton.johnson_tap_app.PetBreedTypeSelectListener;
import com.triton.johnson_tap_app.R;
import com.triton.johnson_tap_app.RestUtils;
import com.triton.johnson_tap_app.Service_Adapter.JobListAdapter;
import com.triton.johnson_tap_app.api.APIInterface;
import com.triton.johnson_tap_app.api.RetrofitClient;
import com.triton.johnson_tap_app.requestpojo.JobListRequest;
import com.triton.johnson_tap_app.responsepojo.JobListResponse;

import java.util.ArrayList;
import java.util.List;

import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Job_DetailsActivity extends AppCompatActivity implements PetBreedTypeSelectListener {

    ImageView iv_back,img_clearsearch;
    String se_user_mobile_no, se_user_name, se_id,check_id, service_title;
    private List<JobListResponse.DataBean> breedTypedataBeanList;
    RecyclerView recyclerView;
    String message,status;
    JobListAdapter petBreedTypesListAdapter;
    EditText edtsearch;
    TextView txt_no_records;
    private String PetBreedType = "";
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_job_details);

        iv_back = (ImageView) findViewById(R.id.iv_back);
        edtsearch = (EditText) findViewById(R.id.edt_search);
        img_clearsearch = (ImageView) findViewById(R.id.img_clearsearch);
        txt_no_records = findViewById(R.id.txt_no_records);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        se_id = sharedPreferences.getString("_id", "default value");
        se_user_mobile_no = sharedPreferences.getString("user_mobile_no", "default value");
        se_user_name = sharedPreferences.getString("user_name", "default value");
        service_title = sharedPreferences.getString("service_title","Breakdown Service");

        Log.e("Name",service_title);


        Bundle extras = getIntent().getExtras();
        if (extras != null) {
          //  service_title = extras.getString("service_title");
           // Log.d("title",service_title);
            status = extras.getString("status");
            Log.e("Status",status);
        }

//        SharedPreferences.Editor editor = sharedPreferences.edit();
//        editor.putString("service_title", service_title);
//        editor.apply();

        iv_back = (ImageView) findViewById(R.id.iv_back);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        jobFindResponseCall(se_user_mobile_no,service_title);

        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                onBackPressed();

//                Intent send = new Intent(Job_DetailsActivity.this, Breakdown_ServiceActivity.class);
//                send.putExtra("service_title",service_title);
//                startActivity(send);

            }
        });

        edtsearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                String Search = edtsearch.getText().toString();

                if(Search.equals("")){
                    recyclerView.setVisibility(View.VISIBLE);
                    img_clearsearch.setVisibility(View.INVISIBLE);
                } else {

                    filter(Search);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

                String Search = edtsearch.getText().toString();

                recyclerView.setVisibility(View.VISIBLE);
                txt_no_records.setVisibility(View.GONE);

                filter(Search);
            }
        });

    }

    private void filter(String search) {

        List<JobListResponse.DataBean> filterlist = new ArrayList<>();
        for (JobListResponse.DataBean item :breedTypedataBeanList){
            if(item.getJob_id().toLowerCase().contains(search.toLowerCase()) ||
                    item.getCustomer_name().toLowerCase().contains(search.toLowerCase()))
            {
                Log.w(TAG,"filter----"+item.getJob_id().toLowerCase().contains(search.toLowerCase()));
                filterlist.add(item);

            }
        }


        if(filterlist.isEmpty())
        {
           // Toast.makeText(this,"No Data Found ... ",Toast.LENGTH_SHORT).show();
            recyclerView.setVisibility(View.GONE);
            txt_no_records.setVisibility(View.VISIBLE);
            txt_no_records.setText("No Data Found");
        }else
        {
            petBreedTypesListAdapter.filterjobList(filterlist);
        }
    }

    private void jobFindResponseCall(String job_no, String title) {
        APIInterface apiInterface = RetrofitClient.getClient().create(APIInterface.class);
        Call<JobListResponse> call = apiInterface.JobListResponseCall(RestUtils.getContentType(), joblistRequest(job_no,title));
        Log.w(TAG, "Jobno Find Response url  :%s" + " " + call.request().url().toString());
        call.enqueue(new Callback<JobListResponse>() {
            @SuppressLint("LogNotTimber")
            @Override
            public void onResponse(@NonNull Call<JobListResponse> call, @NonNull Response<JobListResponse> response) {
                Log.w(TAG, "Jobno Find Response" + new Gson().toJson(response.body()));

                if (response.body() != null) {

                    message = response.body().getMessage();
                    Log.d("message", message);

                    if (200 == response.body().getCode()) {
                        if (response.body().getData() != null) {
                            breedTypedataBeanList = response.body().getData();

                            if (breedTypedataBeanList.size() == 0){

                                recyclerView.setVisibility(View.GONE);
                                txt_no_records.setVisibility(View.VISIBLE);
                                txt_no_records.setText("No Records Found");
                                edtsearch.setEnabled(false);

                            }

                            setBreedTypeView(breedTypedataBeanList);
                            Log.d("dataaaaa", String.valueOf(breedTypedataBeanList));

                        }

                    } else if (400 == response.body().getCode()) {
                        if (response.body().getMessage() != null && response.body().getMessage().equalsIgnoreCase("There is already a user registered with this email id. Please add new email id")) {

                        }
                    } else {

                        Toasty.warning(getApplicationContext(), "" + response.body().getMessage(), Toasty.LENGTH_LONG).show();
                    }
                }


            }

            @Override
            public void onFailure(@NonNull Call<JobListResponse> call, @NonNull Throwable t) {
                Log.e("Jobno Find ", "--->" + t.getMessage());
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private JobListRequest joblistRequest(String job_no, String title) {
        JobListRequest job = new JobListRequest();
        job.setUser_mobile_no(job_no);
        job.setService_name(title);
        Log.w(TAG, "Jobno Find Request " + new Gson().toJson(job));
        return job;
    }

    private void setBreedTypeView(List<JobListResponse.DataBean> breedTypedataBeanList) {
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        petBreedTypesListAdapter = new JobListAdapter(getApplicationContext(), breedTypedataBeanList,this,status);
        recyclerView.setAdapter(petBreedTypesListAdapter);
    }

    public void petBreedTypeSelectListener(String petbreedtitle, String petbreedid) {
        PetBreedType = petbreedtitle;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
//        Intent send = new Intent(Job_DetailsActivity.this, Breakdown_ServiceActivity.class);
//        send.putExtra("service_title",service_title);
//        startActivity(send);
    }
}