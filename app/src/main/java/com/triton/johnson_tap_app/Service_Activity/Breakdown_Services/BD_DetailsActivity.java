package com.triton.johnson_tap_app.Service_Activity.Breakdown_Services;

import static android.content.ContentValues.TAG;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.triton.johnson_tap_app.Adapter.CardViewDataAdapter;
import com.triton.johnson_tap_app.Db.CommonUtil;
import com.triton.johnson_tap_app.Db.DbHelper;
import com.triton.johnson_tap_app.Db.DbUtil;
import com.triton.johnson_tap_app.R;
import com.triton.johnson_tap_app.RestUtils;
import com.triton.johnson_tap_app.Service_Activity.ServicesActivity;
import com.triton.johnson_tap_app.Service_Adapter.BD_DetailsAdapter;
import com.triton.johnson_tap_app.UserTypeSelectListener1;
import com.triton.johnson_tap_app.api.APIInterface;
import com.triton.johnson_tap_app.api.RetrofitClient;
import com.triton.johnson_tap_app.requestpojo.BD_DetailsRequest;
import com.triton.johnson_tap_app.responsepojo.BD_DetailsResponse;

import java.util.ArrayList;
import java.util.List;

import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BD_DetailsActivity extends AppCompatActivity implements UserTypeSelectListener1 {

    private Button btnSelection;
    private RecyclerView recyclerView;
    private CardViewDataAdapter adapter;
    ImageView iv_back,ic_paused;
    List<BD_DetailsResponse.DataBean> breedTypedataBeanList;
    BD_DetailsAdapter activityBasedListAdapter;
    private String PetBreedType = "";
    String message,se_user_mobile_no,status, se_user_name, se_id,check_id, service_title,str_job_id,data="";
    private String Title,petimage;
    AlertDialog alertDialog;
    ProgressDialog progressDialog;
    Context context;
    ArrayList<String> mydata = new ArrayList<>();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_bd_details);
        context = this;

        CommonUtil.dbUtil = new DbUtil(context);
        CommonUtil.dbUtil.open();
        CommonUtil.dbHelper = new DbHelper(context);

        btnSelection = (Button) findViewById(R.id.btn_next);
        iv_back = (ImageView) findViewById(R.id.iv_back);
        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        ic_paused = (ImageView) findViewById(R.id.ic_paused);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
           // str_job_id = extras.getString("job_id");
            status = extras.getString("status");
            Log.e("Status",""+status);
        }

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        se_id = sharedPreferences.getString("_id", "default value");
        se_user_mobile_no = sharedPreferences.getString("user_mobile_no", "default value");
        se_user_name = sharedPreferences.getString("user_name", "default value");
        service_title = sharedPreferences.getString("service_title", "default value");
        str_job_id = sharedPreferences.getString("job_id","123");

        Log.e("Name",service_title);
        Log.e("JobID",str_job_id);


      //  CommonUtil.dbUtil.reportDeletePreventiveListDelete(str_job_id,service_title);

       // jobFindResponseCall("L-F3183");

        if (status.equals("new")){


        }
        else{


        }
        jobFindResponseCall(str_job_id);

        btnSelection.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Log.e("BD SIZE",""+breedTypedataBeanList.size());

                Cursor curs = CommonUtil.dbUtil.getBDdetails(str_job_id,service_title, "1");
                Log.e("BD",""+curs.getCount());

                for (int i = 0; i < breedTypedataBeanList.size(); i++) {
                    BD_DetailsResponse.DataBean singleStudent = breedTypedataBeanList.get(i);
                    if (singleStudent.isSelected() == true || curs.getCount() > 0) {
                        data = breedTypedataBeanList.get(i).getTitle().toString();
                        Log.e("My BD DATA",""+data);
                        CommonUtil.dbUtil.addBDDetails(str_job_id,service_title,data,"1");
                            Intent send = new Intent(BD_DetailsActivity.this, Feedback_GroupActivity.class);
                            send.putExtra("bd_details",data);
                            send.putExtra("job_id",str_job_id);
                            send.putExtra("status",status);
                            startActivity(send);
//                        }

                    } else {
//                        if(curs.getCount() > 0){
//                            Intent send = new Intent(BD_DetailsActivity.this, Feedback_GroupActivity.class);
//                            send.putExtra("bd_details",data);
//                            send.putExtra("job_id",str_job_id);
//                            startActivity(send);
//                        }
                        //else{

//                        alertDialog = new AlertDialog.Builder(BD_DetailsActivity.this)
//                                .setTitle("Please Selected Value")
//                                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
//                                    public void onClick(DialogInterface dialogInterface, int i) {
//                                        alertDialog.dismiss();
//                                    }
//                                })
//                                .show();
                    //    }

                    }
                }
            }
        });


        iv_back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                alertDialog = new AlertDialog.Builder(BD_DetailsActivity.this)
                        .setTitle("Are you sure close job ?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent send = new Intent(BD_DetailsActivity.this, ServicesActivity.class);
                                startActivity(send);
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialogInterface, int i) {
                                alertDialog.dismiss();
                            }
                        })
                        .show();
            }
        });

        ic_paused.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

//                progressDialog = new ProgressDialog(BD_DetailsActivity.this);
//                progressDialog.setMessage("Please Wait ...");
//                progressDialog.setCancelable(false);
//                progressDialog.show();
//
//                for (int i = 0; i < breedTypedataBeanList.size(); i++) {
//                    BD_DetailsResponse.DataBean singleStudent = breedTypedataBeanList.get(i);
//                    if (singleStudent.isSelected() == true) {
//                        data = breedTypedataBeanList.get(i).getTitle().toString();
//                    }
//                }
//
//                locationAddResponseCall();
            }
        });

    }

    private void jobFindResponseCall(String job_no) {
        APIInterface apiInterface = RetrofitClient.getClient().create(APIInterface.class);
        Call<BD_DetailsResponse> call = apiInterface.BD_DetailsResponseCall(RestUtils.getContentType(), serviceRequest(job_no));
        Log.w(TAG, "Jobno Find Response url  :%s" + " " + call.request().url().toString());
        call.enqueue(new Callback<BD_DetailsResponse>() {
            @SuppressLint("LogNotTimber")
            @Override
            public void onResponse(@NonNull Call<BD_DetailsResponse> call, @NonNull Response<BD_DetailsResponse> response) {
                Log.w(TAG, "Jobno Find Response" + new Gson().toJson(response.body()));

                if (response.body() != null) {

                    message = response.body().getMessage();
                    Log.d("message", message);

                    if (200 == response.body().getCode()) {
                        if (response.body().getData() != null) {
                            breedTypedataBeanList = response.body().getData();

                            setView(breedTypedataBeanList);
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
            public void onFailure(@NonNull Call<BD_DetailsResponse> call, @NonNull Throwable t) {
                Log.e("Jobno Find ", "--->" + t.getMessage());
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private BD_DetailsRequest serviceRequest(String job_no) {
        BD_DetailsRequest service = new BD_DetailsRequest();
        service.setJob_id(job_no);
        Log.w(TAG, "Jobno Find Request " + new Gson().toJson(service));
        return service;
    }

    private void setView(List<BD_DetailsResponse.DataBean> dataBeanList) {

//        Cursor cur = CommonUtil.dbUtil.getBDdetails(str_job_id,service_title, "1");
//        Log.e("BD Details Get",""+cur.getCount());
//        if(cur.getCount() >0 && cur.moveToFirst()){
//
//            do{
//                @SuppressLint("Range")
//                String abc = cur.getString(cur.getColumnIndex(DbHelper.BD_DETAILS));
//                Log.e("Datas Get",""+abc);
//                mydata.add(abc);
//            }while (cur.moveToNext());
//
//        }

//        if (cur.moveToLast()){
//            @SuppressLint("Range")
//            String abc = cur.getString(cur.getColumnIndex(DbHelper.BD_DETAILS));
//            Log.e("BD Data Get",""+abc);
//            mydata.add(abc);
//        }


        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        activityBasedListAdapter = new BD_DetailsAdapter(getApplicationContext(), dataBeanList,this,status);
        recyclerView.setAdapter(activityBasedListAdapter);
    }

    public void userTypeSelectListener1(String usertype, String usertypevalue) {
        Title = usertype;

        Log.w(TAG,"myPetsSelectListener : "+ "petList" +new Gson().toJson(breedTypedataBeanList));

        if(breedTypedataBeanList != null && breedTypedataBeanList.size()>0) {
            for (int i = 0; i < breedTypedataBeanList.size(); i++) {
                if (breedTypedataBeanList.get(i).getTitle().equalsIgnoreCase(breedTypedataBeanList.get(i).getTitle())) {
                    petimage = breedTypedataBeanList.get(i).getTitle();
                    
                }
                Log.w(TAG, "myPetsSelectListener : " + "petimage" + petimage);

            }
        }
    }

//    public void locationAddResponseCall(){
//        APIInterface apiInterface = RetrofitClient.getClient().create(APIInterface.class);
//        Call<SubmitBreakdownResponseee> call = apiInterface.submitAddResponseCall(RestUtils.getContentType(),submitDailyRequest());
//        Log.w(TAG,"url  :%s"+" "+ call.request().url().toString());
//
//        call.enqueue(new Callback<SubmitBreakdownResponseee>() {
//            @SuppressLint("LogNotTimber")
//            @Override
//            public void onResponse(@NotNull Call<SubmitBreakdownResponseee> call, @NotNull Response<SubmitBreakdownResponseee> response) {
//
//                Log.w(TAG, "AddLocationResponse" + new Gson().toJson(response.body()));
//                Log.w(TAG,"url  :%s"+" "+ call.request().url().toString());
//
//                if (response.body() != null) {
//
//                    if(response.body().getCode() == 200){
//                        Log.w(TAG,"url  :%s"+" "+ call.request().url().toString());
//
//                        Log.w(TAG,"dddd %s"+" "+ response.body().getData().toString());
//
//                        Toasty.success(BD_DetailsActivity.this, "Added Successfully", Toast.LENGTH_SHORT).show();
//                        progressDialog.dismiss();
////                        Intent send = new Intent( Customer_AcknowledgementActivity.this,ServicesActivity.class);
////                        startActivity(send);
//
//                    }else{
//                        //  showErrorLoading(response.body().getMessage());
//
//                    }
//
//                }
//            }
//
//            @Override
//            public void onFailure(@NotNull Call<SubmitBreakdownResponseee> call, @NotNull Throwable t) {
//                Log.w(TAG,"AddLocationResponseflr"+t.getMessage());
//            }
//        });
//
//    }
//    private Breakdowm_Submit_Request submitDailyRequest() {
//
//        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm aa", Locale.getDefault());
//        String currentDateandTime = sdf.format(new Date());
//
//        Breakdowm_Submit_Request submitDailyRequest = new Breakdowm_Submit_Request();
//        submitDailyRequest.setBd_details(data);
//        submitDailyRequest.setFeedback_details("");
//        submitDailyRequest.setFeedback_remark_text("");
//        submitDailyRequest.setMr_status("");
//        submitDailyRequest.setMr_1("");
//        submitDailyRequest.setMr_2("");
//        submitDailyRequest.setMr_3("");
//        submitDailyRequest.setMr_4("");
//        submitDailyRequest.setMr_5("");
//        submitDailyRequest.setMr_6("");
//        submitDailyRequest.setMr_7("");
//        submitDailyRequest.setMr_8("");
//        submitDailyRequest.setMr_9("");
//        submitDailyRequest.setMr_10("");
//        submitDailyRequest.setBreakdown_service("");
//        submitDailyRequest.setTech_signature("");
//        submitDailyRequest.setCustomer_name("");
//        submitDailyRequest.setCustomer_number("");
//        submitDailyRequest.setCustomer_acknowledgemnet("");
//        submitDailyRequest.setDate_of_submission(currentDateandTime);
//        submitDailyRequest.setUser_mobile_no(se_user_mobile_no);
//        submitDailyRequest.setJob_id(str_job_id);
//
//        Log.w(TAG," locationAddRequest"+ new Gson().toJson(submitDailyRequest));
//        return submitDailyRequest;
//    }


    @Override
    public void onBackPressed() {
        //super.onBackPressed();

        alertDialog = new AlertDialog.Builder(context)
                .setTitle("Are you sure to Close this job ?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent send = new Intent(context, ServicesActivity.class);
                        startActivity(send);
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        alertDialog.dismiss();
                    }
                })
                .show();
    }
}