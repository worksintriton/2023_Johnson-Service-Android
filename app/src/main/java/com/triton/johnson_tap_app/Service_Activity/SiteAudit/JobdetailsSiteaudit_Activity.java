package com.triton.johnson_tap_app.Service_Activity.SiteAudit;

import static android.content.ContentValues.TAG;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
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
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.triton.johnson_tap_app.PetBreedTypeSelectListener;
import com.triton.johnson_tap_app.R;
import com.triton.johnson_tap_app.RestUtils;
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

public class JobdetailsSiteaudit_Activity extends AppCompatActivity implements PetBreedTypeSelectListener {

    ImageView img_back,img_clearsearch;
    RecyclerView recyclerView;
    EditText edtsearch;
    TextView txt_no_records;
    JobListAdapter_SiteAudit petBreedTypesListAdapter;
    Context context;
    String status,se_user_mobile_no, se_user_name, se_id,check_id, service_title,message;
    SharedPreferences sharedPreferences;
    List<JobListResponse.DataBean> breedTypedataBeanList;
    private String PetBreedType = "";

//    ArrayList<String> arli_jobid = new ArrayList<String>();
//    ArrayList<String> arli_custname = new ArrayList<String>();
//    ArrayList<String> arli_auditdate = new ArrayList<String>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_job_details_siteaudit);
        context = this;

        img_back = (ImageView) findViewById(R.id.img_back);
        edtsearch = (EditText) findViewById(R.id.edt_search);
        img_clearsearch = (ImageView) findViewById(R.id.img_clearsearch);
        txt_no_records = findViewById(R.id.txt_no_records);
        recyclerView = findViewById(R.id.recyclerView);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        se_id = sharedPreferences.getString("_id", "default value");
        se_user_mobile_no = sharedPreferences.getString("user_mobile_no", "default value");
        se_user_name = sharedPreferences.getString("user_name", "default value");
        service_title = sharedPreferences.getString("service_title", "Services");

        Log.e("Name", "" + service_title);
        Log.e("Mobile", ""+ se_user_mobile_no);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            //  service_title = extras.getString("service_title");
            status = extras.getString("status");
            //   Log.e("Name",":" + service_title);
            Log.e("Status", "" + status);
        }

        newJoblist();

//        arli_jobid.add("L-44291");
//        arli_jobid.add("L-44292");
//        arli_jobid.add("L-44293");
//        arli_jobid.add("L-44294");
//        arli_jobid.add("L-44295");
//
//        arli_custname.add("John");
//        arli_custname.add("Sam");
//        arli_custname.add("Devi");
//        arli_custname.add("Anisha");
//        arli_custname.add("Nishanth");
//
//        arli_auditdate.add("12-09-2022");
//        arli_auditdate.add("15-09-2022");
//        arli_auditdate.add("17-09-2022");
//        arli_auditdate.add("17-09-2022");
//        arli_auditdate.add("10-09-2022");


        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onBackPressed();
//                Intent send = new Intent(context, SiteAudit_Activity.class);
//                //send.putExtra("service_title",service_title);
//                send.putExtra("status", status);
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
                    item.getName().toLowerCase().contains(search.toLowerCase()))
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
            petBreedTypesListAdapter.filterrList(filterlist);
        }
    }

    private void newJoblist() {

        APIInterface apiInterface = RetrofitClient.getClient().create(APIInterface.class);
        Call<JobListResponse> call = apiInterface.NewJobAuditListCall(RestUtils.getContentType(), joblistRequest());
        Log.w(TAG, "Jobno Find Response url  :%s" + " " + call.request().url().toString());
        call.enqueue(new Callback<JobListResponse>() {
            @SuppressLint("LogNotTimber")
            @Override
            public void onResponse(@NonNull Call<JobListResponse> call, @NonNull Response<JobListResponse> response) {
                Log.w(TAG, "Job List Response" + new Gson().toJson(response.body()));

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

                            recyclerView.setVisibility(View.GONE);
                            txt_no_records.setVisibility(View.VISIBLE);
                            txt_no_records.setText("Error 404 Found");
                            edtsearch.setEnabled(false);
                        }
                    } else {

                        recyclerView.setVisibility(View.GONE);
                        txt_no_records.setVisibility(View.VISIBLE);
                        txt_no_records.setText("Error 404 Found");
                        edtsearch.setEnabled(false);
                        Toasty.warning(getApplicationContext(), "" + response.body().getMessage(), Toasty.LENGTH_LONG).show();
                    }
                }


            }

            @Override
            public void onFailure(@NonNull Call<JobListResponse> call, @NonNull Throwable t) {
                Log.e("Job List on Failure", "--->" + t.getMessage());
                recyclerView.setVisibility(View.GONE);
                txt_no_records.setVisibility(View.VISIBLE);
                txt_no_records.setText("Something Went Wrong.. Try Again Later");
                edtsearch.setEnabled(false);
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void setBreedTypeView(List<JobListResponse.DataBean> breedTypedataBeanList) {

        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        petBreedTypesListAdapter = new JobListAdapter_SiteAudit(getApplicationContext(), breedTypedataBeanList,status);
        recyclerView.setAdapter(petBreedTypesListAdapter);
    }

    private JobListRequest joblistRequest() {

        JobListRequest job = new JobListRequest();
        job.setUser_mobile_no(se_user_mobile_no);
        job.setService_name(service_title);
        Log.w(TAG, "Job List Request " + new Gson().toJson(job));
        return job;
    }

    public void petBreedTypeSelectListener(String petbreedtitle, String petbreedid) {
        PetBreedType = petbreedtitle;
    }

    @Override
    public void onBackPressed() {

        super.onBackPressed();
//        Intent send = new Intent(context, SiteAudit_Activity.class);
//        //send.putExtra("service_title",service_title);
//        send.putExtra("status", status);
//        startActivity(send);
    }
}
