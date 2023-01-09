package com.triton.johnson_tap_app.Service_Activity.Breakdown_Services;

import static android.content.ContentValues.TAG;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.VolleyLog;
import com.google.gson.Gson;
import com.triton.johnson_tap_app.Adapter.CardViewDataAdapter;
import com.triton.johnson_tap_app.Db.CommonUtil;
import com.triton.johnson_tap_app.Db.DbHelper;
import com.triton.johnson_tap_app.Db.DbUtil;
import com.triton.johnson_tap_app.Location.GpsTracker;
import com.triton.johnson_tap_app.R;
import com.triton.johnson_tap_app.RestUtils;
import com.triton.johnson_tap_app.Service_Activity.ServicesActivity;
import com.triton.johnson_tap_app.Service_Adapter.BD_DetailsAdapter;
import com.triton.johnson_tap_app.UserTypeSelectListener1;
import com.triton.johnson_tap_app.api.APIInterface;
import com.triton.johnson_tap_app.api.RetrofitClient;
import com.triton.johnson_tap_app.requestpojo.BD_DetailsRequest;
import com.triton.johnson_tap_app.requestpojo.Breakdowm_Submit_Request;
import com.triton.johnson_tap_app.requestpojo.Job_status_updateRequest;
import com.triton.johnson_tap_app.responsepojo.BD_DetailsResponse;
import com.triton.johnson_tap_app.responsepojo.Job_status_updateResponse;
import com.triton.johnson_tap_app.responsepojo.RetriveLocalValueBRResponse;
import com.triton.johnson_tap_app.responsepojo.SuccessResponse;
import com.triton.johnson_tap_app.utils.ConnectionDetector;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

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
    String message,se_user_mobile_no,status, se_user_name, se_id,check_id, service_title,str_job_id,data="",str_BDDetails="";
    private String Title,petimage,str_StartTime,str_job_status,compno,sertype;
    AlertDialog alertDialog;
    ProgressDialog progressDialog;
    Context context;
    ArrayList<String> mydata = new ArrayList<>();
    TextView txt_Jobid,txt_Starttime;
    String networkStatus="";
    SharedPreferences sharedPreferences;
    GpsTracker gpsTracker;
    double Latitude ,Logitude;
    String address = "";
    Geocoder geocoder;
    List<Address> myAddress =  new ArrayList<>();
    android.app.AlertDialog mdialog;
    int PageNumber = 1;

    @SuppressLint("SetTextI18n")
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
        txt_Starttime = findViewById(R.id.txt_starttime);
        txt_Jobid = findViewById(R.id.txt_jobid);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
           // str_job_id = extras.getString("job_id");
            status = extras.getString("status");
            Log.e("Status",""+status);
        }

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        se_id = sharedPreferences.getString("_id", "default value");
        se_user_mobile_no = sharedPreferences.getString("user_mobile_no", "default value");
        se_user_name = sharedPreferences.getString("user_name", "default value");
        service_title = sharedPreferences.getString("service_title", "default value");
        str_job_id = sharedPreferences.getString("job_id","123");
        str_StartTime = sharedPreferences.getString("starttime","");
        str_StartTime = str_StartTime.replaceAll("[^0-9-:]", " ");
        Log.e("Name",service_title);
        Log.e("JobID",str_job_id);
        Log.e("Start Time",str_StartTime);
        compno = sharedPreferences.getString("compno","123");
        sertype = sharedPreferences.getString("sertype","123");

//        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm aa", Locale.getDefault());
//        String currentDateandTime = sdf.format(new Date());
        txt_Jobid.setText("Job ID : " + str_job_id);
        txt_Starttime.setText("Start Time : " + str_StartTime);

        Latitude = Double.parseDouble(sharedPreferences.getString("lati","0.00000"));
        Logitude = Double.parseDouble(sharedPreferences.getString("long","0.00000"));
        address =sharedPreferences.getString("add","Chennai");
        Log.e("Location",""+Latitude+""+Logitude+""+address);

//        CommonUtil.dbUtil.reportDeletePreventiveListDelete(str_job_id,service_title);

       // jobFindResponseCall("L-F3183");

        networkStatus = ConnectionDetector.getConnectivityStatusString(getApplicationContext());

        Log.e("Network",""+networkStatus);
        if (networkStatus.equalsIgnoreCase("Not connected to Internet")) {

            NoInternetDialog();

        }
        else{

            jobFindResponseCall(str_job_id);
        }
        getBDDetails();

        if (status.equals("new")){


        }
        else{

            if (!Objects.equals(networkStatus, "Not connected to Internet")){


                retrive_LocalValue();
            }
            else{
                NoInternetDialog();
            }

        }


        btnSelection.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Log.e("BD SIZE",""+breedTypedataBeanList.size());
                Cursor curs;

                for (int i = 0; i < breedTypedataBeanList.size(); i++) {
                    BD_DetailsResponse.DataBean singleStudent = breedTypedataBeanList.get(i);
                    if (singleStudent.isSelected()) {
                        data = breedTypedataBeanList.get(i).getTitle().toString();
                        Log.e("My BD DATA",""+data);
//                        CommonUtil.dbUtil.addBDDetails(str_job_id,service_title,data,"1");
//                            Intent send = new Intent(BD_DetailsActivity.this, Feedback_GroupActivity.class);
//                            send.putExtra("bd_details",data);
//                            send.putExtra("job_id",str_job_id);
//                            send.putExtra("status",status);
//                            startActivity(send);
                    }
                }

                curs = CommonUtil.dbUtil.getBDdetails(str_job_id,service_title, "1");
                Log.e("BD",""+curs.getCount());

                if(curs.getCount() >0){
                    Intent send = new Intent(BD_DetailsActivity.this, Feedback_GroupActivity.class);
                    send.putExtra("bd_details",data);
                    send.putExtra("job_id",str_job_id);
                    send.putExtra("status",status);
                    startActivity(send);
                }
                else{

                    alertDialog = new AlertDialog.Builder(BD_DetailsActivity.this)
                            .setTitle("Please Selected Value")
                            .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialogInterface, int i) {
                                        alertDialog.dismiss();
                                    }
                                })
                                .show();
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
            @Override
            public void onClick(View view) {

                DateFormat df = new SimpleDateFormat("EEE, d MMM yyyy, HH:mm");
                String date = df.format(Calendar.getInstance().getTime());

                alertDialog = new AlertDialog.Builder(context)
                        .setTitle("Are you sure to pause this job ?")
                        .setMessage(date)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(context,"Lat : " + Latitude + "Long : " + Logitude + "Add : " + address,Toast.LENGTH_LONG).show();
                                str_job_status = "Job Paused";
                                Job_status_update();
                                createLocalvalue();

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

    }

    @SuppressLint("LongLogTag")
    private void retrive_LocalValue() {

        APIInterface apiInterface =  RetrofitClient.getClient().create((APIInterface.class));
        Call<RetriveLocalValueBRResponse> call = apiInterface.retriveLocalValueBRCall(com.triton.johnson_tap_app.utils.RestUtils.getContentType(),localRequest());
        Log.e("Retrive Local Value url  :%s"," "+ call.request().url().toString());

        call.enqueue(new Callback<RetriveLocalValueBRResponse>() {
            @Override
            public void onResponse(Call<RetriveLocalValueBRResponse> call, Response<RetriveLocalValueBRResponse> response) {
                Log.e("Retrive Response","" + new Gson().toJson(response.body()));
                if (response.body() != null){

                    message = response.body().getMessage();

                    if (response.body().getCode() == 200){

                        if (response.body().getData() != null){
                            Log.d("msg",message);

                            str_BDDetails = response.body().getData().getBd_details();
                            Log.e("Retive BD",""+str_BDDetails);

                            CommonUtil.dbUtil.addBDDetails(str_job_id,service_title,str_BDDetails,"1");
                            Cursor curs = CommonUtil.dbUtil.getBDdetails(str_job_id,service_title, "1");
                            Log.e("BD",""+curs.getCount());

                            setView(breedTypedataBeanList);
                        }
                    }else{
                        Toasty.warning(getApplicationContext(),""+message,Toasty.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<RetriveLocalValueBRResponse> call, Throwable t) {

                Log.e("On Failure", "--->" + t.getMessage());
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private Job_status_updateRequest localRequest() {

        Job_status_updateRequest custom = new Job_status_updateRequest();
        custom.setUser_mobile_no(se_user_mobile_no);
        custom.setJob_id(str_job_id);
        custom.setSMU_SCH_COMPNO(compno);
        //  custom.setSMU_SCH_SERTYPE(sertype);
        Log.e("Request Data ",""+ new Gson().toJson(custom));
        return custom;

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

        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        activityBasedListAdapter = new BD_DetailsAdapter(getApplicationContext(), dataBeanList,this,status,str_BDDetails);
        recyclerView.setAdapter(activityBasedListAdapter);
    }

    @SuppressLint("Range")
    private void getBDDetails() {

        Cursor curs = CommonUtil.dbUtil.getBDdetails(str_job_id,service_title, "1");
        Log.e("BD Count",""+curs.getCount());

        if (curs.getCount()>0 && curs.moveToLast()){

            str_BDDetails = curs.getString(curs.getColumnIndex(DbHelper.BD_DETAILS));
            Log.e("BD Data Get",""+str_BDDetails);
        }


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

    public void NoInternetDialog() {

        android.app.AlertDialog.Builder mBuilder = new android.app.AlertDialog.Builder(context);
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

    private void Job_status_update() {

        APIInterface apiInterface = RetrofitClient.getClient().create(APIInterface.class);
        Call<Job_status_updateResponse> call = apiInterface.job_status_updateResponseCall(com.triton.johnson_tap_app.utils.RestUtils.getContentType(), job_status_updateRequest());
        Log.w(VolleyLog.TAG,"SignupResponse url  :%s"+" "+ call.request().url().toString());

        call.enqueue(new Callback<Job_status_updateResponse>() {
            @SuppressLint("LogNotTimber")
            @Override
            public void onResponse(@NonNull Call<Job_status_updateResponse> call, @NonNull Response<Job_status_updateResponse> response) {

                Log.w(VolleyLog.TAG,"SignupResponse" + new Gson().toJson(response.body()));
                if (response.body() != null) {

                    message = response.body().getMessage();

                    if (200 == response.body().getCode()) {
                        if(response.body().getData() != null){

                            Log.d("msg",message);
                        }


                    } else {
                        Toasty.warning(getApplicationContext(),""+message,Toasty.LENGTH_LONG).show();

                    }
                }


            }

            @Override
            public void onFailure(@NonNull Call<Job_status_updateResponse> call, @NonNull Throwable t) {
                Log.e("OTP", "--->" + t.getMessage());
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private Job_status_updateRequest job_status_updateRequest() {

        Job_status_updateRequest custom = new Job_status_updateRequest();
        custom.setUser_mobile_no(se_user_mobile_no);
        custom.setService_name(service_title);
        custom.setJob_id(str_job_id);
        custom.setStatus(str_job_status);
        custom.setSMU_SCH_COMPNO(compno);
        custom.setSMU_SCH_SERTYPE(sertype);
        custom.setJOB_START_LONG(Logitude);
        custom.setJOB_START_LAT(Latitude);
        custom.setJOB_LOCATION(address);
        Log.e("CompNo",""+compno);
        Log.e("SertYpe", ""+sertype);
        Log.w(VolleyLog.TAG,"loginRequest "+ new Gson().toJson(custom));
        return custom;
    }

    private void createLocalvalue() {

        APIInterface apiInterface = RetrofitClient.getClient().create(APIInterface.class);
        Call<SuccessResponse> call = apiInterface.createLocalvalueBD(com.triton.johnson_tap_app.utils.RestUtils.getContentType(), createLocalRequest());
        Log.w(VolleyLog.TAG,"Create Local Value Response url  :%s"+" "+ call.request().url().toString());

        call.enqueue(new Callback<SuccessResponse>() {
            @Override
            public void onResponse(Call<SuccessResponse> call, Response<SuccessResponse> response) {

                Log.w(VolleyLog.TAG,"Create Local Value Response" + "" + new Gson().toJson(response.body()));

                if (response.body() != null) {
                    message = response.body().getMessage();

                    if (response.body().getCode() == 200){

                        if(response.body().getData() != null){

                            Log.d("msg",message);

                            Intent send = new Intent(context, ServicesActivity.class);
                            startActivity(send);
                        }

                    } else{
                        Toasty.warning(getApplicationContext(),""+message,Toasty.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<SuccessResponse> call, Throwable t) {

                Log.e("On Failure", "--->" + t.getMessage());
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private Breakdowm_Submit_Request createLocalRequest() {

        getBDDetails();

        String codelist="", feedbackdetails="";

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm aa", Locale.getDefault());
        String currentDateandTime = sdf.format(new Date());

        Breakdowm_Submit_Request submitDailyRequest = new Breakdowm_Submit_Request();
        submitDailyRequest.setBd_details(str_BDDetails);
        //submitDailyRequest.setFeedback_details(sstring);
        submitDailyRequest.setCode_list(String.valueOf(codelist));
        submitDailyRequest.setFeedback_details(String.valueOf(feedbackdetails));
        submitDailyRequest.setFeedback_remark_text("");
        submitDailyRequest.setMr_status("-");
        submitDailyRequest.setMr_1("");
        submitDailyRequest.setMr_2("");
        submitDailyRequest.setMr_3("");
        submitDailyRequest.setMr_4("");
        submitDailyRequest.setMr_5("");
        submitDailyRequest.setMr_6("");
        submitDailyRequest.setMr_7("");
        submitDailyRequest.setMr_8("");
        submitDailyRequest.setMr_9("");
        submitDailyRequest.setMr_10("");
        submitDailyRequest.setBreakdown_service("");
        submitDailyRequest.setTech_signature("");
        submitDailyRequest.setCustomer_name("");
        submitDailyRequest.setCustomer_number("");
        submitDailyRequest.setCustomer_acknowledgemnet("");
        submitDailyRequest.setDate_of_submission(currentDateandTime);
        submitDailyRequest.setUser_mobile_no(se_user_mobile_no);
        submitDailyRequest.setJob_id(str_job_id);
        submitDailyRequest.setSMU_SCH_COMPNO(compno);
        submitDailyRequest.setSMU_SCH_SERTYPE(sertype);
        submitDailyRequest.setPage_number(PageNumber);
        Log.e("CompNo",""+compno);
        Log.e("SertYpe", ""+sertype);
        Log.e("Pause Page Number",""+PageNumber);
        Log.w(TAG," Create Local Value Request"+ new Gson().toJson(submitDailyRequest));
        return submitDailyRequest;
    }


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