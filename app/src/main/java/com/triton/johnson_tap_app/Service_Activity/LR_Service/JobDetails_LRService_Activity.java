package com.triton.johnson_tap_app.Service_Activity.LR_Service;

import static android.content.ContentValues.TAG;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.triton.johnson_tap_app.R;
import com.triton.johnson_tap_app.RestUtils;
import com.triton.johnson_tap_app.api.APIInterface;
import com.triton.johnson_tap_app.api.RetrofitClient;
import com.triton.johnson_tap_app.requestpojo.JobListRequest;
import com.triton.johnson_tap_app.requestpojo.Pasused_ListRequest;
import com.triton.johnson_tap_app.responsepojo.JobListResponse;
import com.triton.johnson_tap_app.responsepojo.Pasused_ListResponse;
import com.triton.johnson_tap_app.utils.ConnectionDetector;

import java.util.ArrayList;
import java.util.List;

import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class JobDetails_LRService_Activity extends AppCompatActivity {

    ImageView img_back,img_clearsearch;
    RecyclerView recyclerView;
    EditText edtsearch;
    TextView txt_no_records;
    JobListAdapter_LRService petBreedTypesListAdapter;
    PausedJobListAdapter_LRService pausedlistAdapter;
    RelativeLayout Job;
    Context context;
    String status,se_user_mobile_no, se_user_name, se_id,check_id, service_title,message,networkStatus="";
    SharedPreferences sharedPreferences;
    List<JobListResponse.DataBean> breedTypedataBeanList;
    List<Pasused_ListResponse.DataBean> databeanList;
    private String PetBreedType = "";
    //ArrayList<String> arli_jobid = new ArrayList<String>();

   // List<arli_jobid> jobid = new ArrayList<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_jobdetails_lrservice);
        context = this;


        img_back = (ImageView) findViewById(R.id.img_back);
        edtsearch = (EditText) findViewById(R.id.edt_search);
        img_clearsearch = (ImageView) findViewById(R.id.img_clearsearch);
        txt_no_records = findViewById(R.id.txt_no_records);
        recyclerView = findViewById(R.id.recyclerView);
        Job = findViewById(R.id.rel_job);

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

        networkStatus = ConnectionDetector.getConnectivityStatusString(getApplicationContext());

        Log.e("Network",""+networkStatus);
        if (networkStatus.equalsIgnoreCase("Not connected to Internet")) {

            NoInternetDialog();

        }
        else{

            if (status.equals("new")){

                jobFindResponseCall(se_user_mobile_no,service_title);
            }
            else{

                Job.setVisibility(View.GONE);
                edtsearch.setVisibility(View.GONE);
                pausedjobResponseCall();
            }
        }



        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent send = new Intent(context, LR_Service_Activity.class);
                //send.putExtra("service_title",service_title);
                send.putExtra("status", status);
                startActivity(send);

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

    public void NoInternetDialog() {

        AlertDialog.Builder mBuilder = new AlertDialog.Builder(context);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View mView = inflater.inflate(R.layout.dialog_nointernet, null);
        Button btn_Retry = mView.findViewById(R.id.btn_retry);


        mBuilder.setView(mView);
        final Dialog dialog= mBuilder.create();
        dialog.show();
        dialog.setCanceledOnTouchOutside(false);

        btn_Retry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();
                finish();
                startActivity(getIntent());

            }
        });
    }

    private void pausedjobResponseCall() {

        APIInterface apiInterface = RetrofitClient.getClient().create(APIInterface.class);
        Call<Pasused_ListResponse> call = apiInterface.Pasused_listResponseLRCall(RestUtils.getContentType(), serviceRequest());
        Log.w(TAG, "Jobno Find Response url  :%s" + " " + call.request().url().toString());

        call.enqueue(new Callback<Pasused_ListResponse>() {
            @Override
            public void onResponse(Call<Pasused_ListResponse> call, Response<Pasused_ListResponse> response) {

                Log.w(TAG, "Paused Job Response" + new Gson().toJson(response.body()));

                if (response.body() != null) {

                    message = response.body().getMessage();
                    Log.d("message", message);

                    if (200 == response.body().getCode()) {
                        if (response.body().getData() != null) {
                            databeanList = response.body().getData();

                            if (databeanList.size() == 0){

                                recyclerView.setVisibility(View.GONE);
                                txt_no_records.setVisibility(View.VISIBLE);
                                txt_no_records.setText("No Records Found");
                                edtsearch.setEnabled(false);

                            }

                            setPausedTypeView(databeanList);
                            Log.d("dataaaaa", String.valueOf(databeanList));

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
                      //  Toasty.warning(getApplicationContext(), "" + response.body().getMessage(), Toasty.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<Pasused_ListResponse> call, Throwable t) {

                Log.e("Jobno Find ", "--->" + t.getMessage());
                recyclerView.setVisibility(View.GONE);
                txt_no_records.setVisibility(View.VISIBLE);
                txt_no_records.setText("Something Went Wrong.. Try Again Later");
                edtsearch.setEnabled(false);
               // Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setPausedTypeView(List<Pasused_ListResponse.DataBean> databeanList) {

        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        pausedlistAdapter= new PausedJobListAdapter_LRService(getApplicationContext(), databeanList,status);
        recyclerView.setAdapter(pausedlistAdapter);
    }

    private Pasused_ListRequest serviceRequest() {
        Pasused_ListRequest service = new Pasused_ListRequest();
        service.setUser_mobile_no(se_user_mobile_no);
        //service.setService_name(title);
        Log.w(TAG, "Paused Request " + new Gson().toJson(service));
        return service;
    }

    private void filter(String search) {

        List<JobListResponse.DataBean> filterlist = new ArrayList<>();

        try{

            for (JobListResponse.DataBean item :breedTypedataBeanList) {
                if(item.getJob_id().toLowerCase().contains(search.toLowerCase())) {
                    Log.w(TAG,"filter----"+item.getJob_id().toLowerCase().contains(search.toLowerCase()));
                    filterlist.add(item);

                }
            }

        }catch (NullPointerException e){
            e.printStackTrace();
            Log.e("Hi ",""+e.toString());
        }



        if(filterlist.isEmpty()) {
            //Toast.makeText(this,"No Data Found ... ",Toast.LENGTH_SHORT).show();
            recyclerView.setVisibility(View.GONE);
            txt_no_records.setVisibility(View.VISIBLE);
            txt_no_records.setText("No Data Found");
        }else
        {
            petBreedTypesListAdapter.filterrList(filterlist);
        }
    }


     //   arli_jobid.add("L-1456");
      //  arli_jobid.add("L-4578");
    //    arli_jobid.add("L-6745");

    private void jobFindResponseCall(String se_user_mobile_no, String service_title) {

        APIInterface apiInterface = RetrofitClient.getClient().create(APIInterface.class);
        Call<JobListResponse> call = apiInterface.NewJobListLRCall(RestUtils.getContentType(),joblistlrrequest(se_user_mobile_no,service_title));
        Log.w(TAG, "JobList Response url  :%s" + " " + call.request().url().toString());
        call.enqueue(new Callback<JobListResponse>() {
            @Override
            public void onResponse(Call<JobListResponse> call, Response<JobListResponse> response) {
                Log.w(TAG, "JobList Response" + new Gson().toJson(response.body()));

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
                       // Toasty.warning(getApplicationContext(), "" + response.body().getMessage(), Toasty.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<JobListResponse> call, Throwable t) {

                Log.e("JobList On Failure ", "--->" + t.getMessage());
               // Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                recyclerView.setVisibility(View.GONE);
                txt_no_records.setVisibility(View.VISIBLE);
                txt_no_records.setText("Something Went Wrong.. Try Again Later");
                edtsearch.setEnabled(false);

            }
        });
    }


    private void setBreedTypeView(List<JobListResponse.DataBean> breedTypedataBeanList) {

        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        petBreedTypesListAdapter = new JobListAdapter_LRService(getApplicationContext(), breedTypedataBeanList,status);
        recyclerView.setAdapter(petBreedTypesListAdapter);

    }

    private JobListRequest joblistlrrequest(String se_user_mobile_no, String service_title) {

        JobListRequest joblist = new JobListRequest();
        joblist.setUser_mobile_no(se_user_mobile_no);
        joblist.setService_name(service_title);
        Log.w(TAG, "JobList Request " + new Gson().toJson(joblist));
        return joblist;
    }

    public void petBreedTypeSelectListener(String petbreedtitle, String petbreedid) {
        PetBreedType = petbreedtitle;
    }

    public void onBackPressed() {
        Intent send = new Intent(context, LR_Service_Activity.class);
        // send.putExtra("service_title",service_title);
        startActivity(send);
    }
}
