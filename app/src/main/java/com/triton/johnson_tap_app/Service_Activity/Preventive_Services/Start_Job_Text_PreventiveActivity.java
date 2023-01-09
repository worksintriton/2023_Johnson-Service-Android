package com.triton.johnson_tap_app.Service_Activity.Preventive_Services;

import static android.view.View.GONE;
import static com.android.volley.VolleyLog.TAG;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.location.LocationManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.triton.johnson_tap_app.Location.GpsTracker;
import com.triton.johnson_tap_app.R;
import com.triton.johnson_tap_app.Service_Activity.Breakdown_Services.BD_DetailsActivity;
import com.triton.johnson_tap_app.Service_Activity.Breakdown_Services.Start_Job_TextActivity;
import com.triton.johnson_tap_app.Service_Activity.PreventiveMRApproval.MRForms_PreventiveMRActivity;
import com.triton.johnson_tap_app.Service_Activity.PreventiveMRApproval.StartJob_PreventiveMR_Activity;
import com.triton.johnson_tap_app.api.APIInterface;
import com.triton.johnson_tap_app.api.RetrofitClient;
import com.triton.johnson_tap_app.requestpojo.Check_Pod_StatusRequest;
import com.triton.johnson_tap_app.requestpojo.Job_statusRequest;
import com.triton.johnson_tap_app.requestpojo.Job_status_updateRequest;
import com.triton.johnson_tap_app.responsepojo.Check_Pod_StatusResponse;
import com.triton.johnson_tap_app.responsepojo.Job_statusResponse;
import com.triton.johnson_tap_app.responsepojo.Job_status_updateResponse;
import com.triton.johnson_tap_app.responsepojo.RetriveResponsePR;
import com.triton.johnson_tap_app.utils.ConnectionDetector;
import com.triton.johnson_tap_app.utils.RestUtils;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Start_Job_Text_PreventiveActivity extends AppCompatActivity {

    FloatingActionButton send;
    TextView text,txt_Title;
    ImageView iv_back;
    String se_user_mobile_no, se_user_name, se_id,check_id, service_title,str_job_id="",message,str_job_status ="",str_status;
    AlertDialog alertDialog1;
    CharSequence[] values = {"POD","SEMPOD","MOD","ESC/TRV"};
    String compno, sertype,status,str_StartTime,currentDateandTime;
    SharedPreferences sharedPreferences;
    Context context;
    TextView txt_DateandTime;
    GpsTracker gpsTracker;
    Geocoder geocoder;
    double Latitude ,Logitude;
    String address = "",networkStatus="";
    List<Address> myAddress =  new ArrayList<>();
    int PageNumber = 0;
    int SubPageNumber = 0;
    AlertDialog mDialog;

    @SuppressLint("MissingInflatedId")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_start_job_text_preventive);
        context = this;

//        Latitude = Double.parseDouble("0.0");
//        Logitude = Double.parseDouble("0.0");

        send = findViewById(R.id.add_fab);
        text = findViewById(R.id.text);
        iv_back = (ImageView) findViewById(R.id.iv_back);
        txt_Title = findViewById(R.id.txt_title);
        txt_DateandTime = findViewById(R.id.txt_datetime);

        try {
            if (ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED ) {
                ActivityCompat.requestPermissions(Start_Job_Text_PreventiveActivity.this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, 101);
            }
        } catch (Exception e){
            e.printStackTrace();
        }

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
         //   str_job_id = extras.getString("job_id");
            status = extras.getString("status");
            Log.e("Status",""+status);
        }

        if (extras != null) {
           // service_title = extras.getString("service_title");
        }

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        se_id = sharedPreferences.getString("_id", "default value");
        se_user_mobile_no = sharedPreferences.getString("user_mobile_no", "default value");
        se_user_name = sharedPreferences.getString("user_name", "default value");
        service_title = sharedPreferences.getString("service_title", "default value");
        str_job_id = sharedPreferences.getString("job_id","L1234");
        Log.e("Name",service_title);
        Log.e("JobID",str_job_id);
        txt_Title.setText(service_title);

        compno = sharedPreferences.getString("compno","123");
        sertype = sharedPreferences.getString("sertype","123");

        Spannable name_Upload = new SpannableString("Start Job ");
        name_Upload.setSpan(new ForegroundColorSpan(Start_Job_Text_PreventiveActivity.this.getResources().getColor(R.color.colorAccent)), 0, name_Upload.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        text.setText(name_Upload);
        Spannable name_Upload1 = new SpannableString("*");
        name_Upload1.setSpan(new ForegroundColorSpan(Color.RED), 0, name_Upload1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        text.append(name_Upload1);

        getMYLocation();
        Toast.makeText(context,"Lat : " + Latitude + "Long : " + Logitude + "Add : " + address,Toast.LENGTH_LONG).show();


        networkStatus = ConnectionDetector.getConnectivityStatusString(getApplicationContext());

        Log.e("Network",""+networkStatus);
        if (networkStatus.equalsIgnoreCase("Not connected to Internet")) {

           NoInternetDialog();

        }else{

            Job_status();
        }


        if (status.equals("new")){

            send.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Log.e("Status",""+ message);

//                    getSampleLoc();

                    if (Objects.equals(message, "Not Started")){

                        Log.e("Hi","inside");

                        DateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                        currentDateandTime = df.format(Calendar.getInstance().getTime());
                        Log.e ("Start Time",""+ currentDateandTime);
                        str_StartTime = currentDateandTime;

                        alert();

                    }
                    else{

                        Log.e("Hi","outside");

                        networkStatus = ConnectionDetector.getConnectivityStatusString(getApplicationContext());

                        Log.e("Network",""+networkStatus);
                        if (networkStatus.equalsIgnoreCase("Not connected to Internet")) {

                            NoInternetDialog();

                        }else{

                            Check_Pod_Status();
                        }



//                        Intent send = new Intent(Start_Job_Text_PreventiveActivity.this, MRForms_PreventiveMRActivity.class);
//                        send.putExtra("job_id",str_job_id);
//                        send.putExtra("status", status);
//                        startActivity(send);

                    }


                }
            });

        }

        else{

            send.setImageResource(R.drawable.ic_resume);
            //  send.getImageTintList() = ColorStateList.valueOf(Color.rgb(255, 50, 50));

            txt_DateandTime.setVisibility(View.VISIBLE);

            Spannable name_Upload2 = new SpannableString("Resume Job ");
            name_Upload2.setSpan(new ForegroundColorSpan(context.getResources().getColor(R.color.colorAccent)), 0, name_Upload.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            text.setText(name_Upload2);
            Spannable name_Upload3 = new SpannableString("*");
            name_Upload3.setSpan(new ForegroundColorSpan(Color.RED), 0, name_Upload3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            text.append(name_Upload3);

            DateFormat df = new SimpleDateFormat("EEE, d MMM yyyy, HH:mm");
            String date = df.format(Calendar.getInstance().getTime());
            txt_DateandTime.setText(date);

            send.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    str_job_status = "Job Resume";

//                    getSampleLoc();

                    networkStatus = ConnectionDetector.getConnectivityStatusString(getApplicationContext());

                    Log.e("Network",""+networkStatus);
                    if (networkStatus.equalsIgnoreCase("Not connected to Internet")) {

                        NoInternetDialog();
                    }
                    else{

                        Toast.makeText(context,"Lat : " + Latitude + "Long : " + Logitude + "Add : " + address,Toast.LENGTH_LONG).show();

                        if (Latitude > 0.0 && Logitude > 0.0 && !Objects.equals(address, "")){

                            Job_status_update();
                            Check_Pod_Status();


                        }
                        else{

                            ErrorAlert();
                        }

                    }

                  //  retriveLocalvalue();
                }
            });

        }

        iv_back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

//                Intent send = new Intent(Start_Job_Text_PreventiveActivity.this, Customer_Details_preActivity.class);
//                startActivity(send);
                onBackPressed();
            }
        });
    }

    private Job_status_updateRequest getSampleLoc() {

        Job_status_updateRequest custom = new Job_status_updateRequest();
        custom.setUser_mobile_no(se_user_mobile_no);
        custom.setService_name(service_title);
        custom.setJob_id(str_job_id);
        custom.setStatus(str_job_status);
        custom.setSMU_SCH_COMPNO(compno);
        custom.setSMU_SCH_SERTYPE(sertype);
        custom.setJOB_LOCATION(address);
        custom.setJOB_START_LAT(Latitude);
        custom.setJOB_START_LONG((Logitude));
        Log.e("CompNo",""+compno);
        Log.e("SertYpe", ""+sertype);
        Log.w(TAG,"loginRequest "+ new Gson().toJson(custom));
        return custom;
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

    private void alert() {

        AlertDialog.Builder mBuilder = new AlertDialog.Builder(Start_Job_Text_PreventiveActivity.this);
        View mView = getLayoutInflater().inflate(R.layout.startjob_popup_layout, null);

        DateFormat df = new SimpleDateFormat("EEE, d MMM yyyy, HH:mm");
        String date = df.format(Calendar.getInstance().getTime());

        TextView txt_jobstatus = mView.findViewById(R.id.txt_jobstatus);
        TextView txt_job_content = mView.findViewById(R.id.txt_job_content);
        LinearLayout ll_start = mView.findViewById(R.id.ll_start);
        LinearLayout ll_pause = mView.findViewById(R.id.ll_pause);
        LinearLayout ll_stop = mView.findViewById(R.id.ll_stop);
        LinearLayout ll_resume = mView.findViewById(R.id.ll_resume);
        ImageView img_close = mView.findViewById(R.id.img_close);
        Button btn_back = mView.findViewById(R.id.btn_back);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        TextView txt_DateTime = mView.findViewById(R.id.txt_datetime);
        btn_back.setVisibility(GONE);
        txt_jobstatus.setVisibility(GONE);
        ll_resume.setVisibility(GONE);
        ll_pause.setVisibility(GONE);
        ll_stop.setVisibility(GONE);
        txt_DateTime.setText(date);

        mBuilder.setView(mView);
        mDialog= mBuilder.create();
        mDialog.show();

        ll_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                str_job_status = "Job Started";

                networkStatus = ConnectionDetector.getConnectivityStatusString(getApplicationContext());

                Log.e("Network",""+networkStatus);
                if (networkStatus.equalsIgnoreCase("Not connected to Internet")) {

                  NoInternetDialog();
                }
                else{

                    Toast.makeText(context,"Lat : " + Latitude + "Long : " + Logitude + "Add : " + address,Toast.LENGTH_LONG).show();

                    if (Latitude > 0.0 && Logitude > 0.0 && !Objects.equals(address, "")){
                        Job_status_update();
                        Check_Pod_Status();

                    }
                    else{

                        mDialog.dismiss();

                        ErrorAlert();
                    }
                }

//                Intent send = new Intent(Start_Job_Text_PreventiveActivity.this, MRForms_PreventiveMRActivity.class);
//                send.putExtra("job_id",str_job_id);
//                send.putExtra("status", status);
//                startActivity(send);

            }
        });
    }

    @SuppressLint("MissingInflatedId")
    private void ErrorAlert() {

        AlertDialog.Builder mBuilder = new AlertDialog.Builder(context);
        View mView = getLayoutInflater().inflate(R.layout.popup_tryagain, null);

        TextView txt_Message = mView.findViewById(R.id.txt_message);
        Button btn_Ok = mView.findViewById(R.id.btn_ok);


        mBuilder.setView(mView);
        mDialog = mBuilder.create();
        mDialog.setCanceledOnTouchOutside(false);
        mDialog.show();

        btn_Ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog.dismiss();
                finish();
                startActivity(getIntent());
            }
        });
    }

    @SuppressLint("LongLogTag")
    private void retriveLocalvalue() {

        APIInterface apiInterface =  RetrofitClient.getClient().create((APIInterface.class));
        Call<RetriveResponsePR> call = apiInterface.retriveLocalValuePRCall(com.triton.johnson_tap_app.RestUtils.getContentType(),localRequest());
        Log.e("Retrive Local Value url  :%s"," "+ call.request().url().toString());

        call.enqueue(new Callback<RetriveResponsePR>() {
            @Override
            public void onResponse(Call<RetriveResponsePR> call, Response<RetriveResponsePR> response) {

                Log.e("Retrive Response","" + new Gson().toJson(response.body()));

                if (response.body() != null) {
                    message = response.body().getMessage();

                    if (200 == response.body().getCode()) {
                        if(response.body().getData() != null){

                            Log.d("msg",message);

                            str_job_status = response.body().getData().getJob_status_type();
                            PageNumber = response.body().getData().getPage_number();
//                            Log.e("Status Type",str_job_status);
//                            Log.e("PAGE NUMBER", String.valueOf(PageNumber));

                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString("statustype", str_job_status);
                            editor.putString("starttime", str_StartTime);
                            editor.putInt("page",PageNumber);
                            editor.putInt("currentpage",SubPageNumber);
                            editor.apply();

                            if (PageNumber ==0){

                                AlertDialog.Builder mBuilder = new AlertDialog.Builder(context);
                                View mView = getLayoutInflater().inflate(R.layout.popup_tryagain, null);

                                TextView txt_Message = mView.findViewById(R.id.txt_message);
                                Button btn_Ok = mView.findViewById(R.id.btn_ok);

                                txt_Message.setText("Please Try Again");


                                mBuilder.setView(mView);
                                mDialog = mBuilder.create();
                                mDialog.setCanceledOnTouchOutside(false);
                                mDialog.show();

                                btn_Ok.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        mDialog.dismiss();
                                        finish();
                                        startActivity(getIntent());
                                    }
                                });
                            }
                            else if (str_job_status.equals("ESC/TRV") && PageNumber == 1){

                                Intent send3 = new Intent(Start_Job_Text_PreventiveActivity.this, ESCTRV.class);
                                send3.putExtra("job_id",str_job_id);
                                //  send3.putExtra("value","ESC/TRV");

                                send3.putExtra("service_title",service_title);
                                send3.putExtra("status",status);
                                startActivity(send3);

                            }
                            else if(PageNumber == 1){

                                Intent send = new Intent(Start_Job_Text_PreventiveActivity.this, Monthlist_Preventive_Activity.class);
                                send.putExtra("job_id",str_job_id);
                                // send.putExtra("value","POD");

                                send.putExtra("service_title",service_title);
                                send.putExtra("status",status);
                                startActivity(send);

                            }
                            else if(PageNumber == 2){

                                SubPageNumber = response.body().getData().getSubPage_number();
                                Log.e("Page ", String.valueOf(SubPageNumber));

                                Intent send = new Intent(Start_Job_Text_PreventiveActivity.this, Recycler_SpinnerActivity.class);
                                send.putExtra("job_id",str_job_id);

                                send.putExtra("service_title",service_title);
                                send.putExtra("status",status);
                                startActivity(send);
//                                Toast.makeText(context,"Page Not Found",Toast.LENGTH_SHORT).show();
                            }
                            else if(PageNumber == 4){

                                SubPageNumber = response.body().getData().getSubPage_number();
                                Log.e("Page ", String.valueOf(SubPageNumber));

                                Intent send = new Intent(Start_Job_Text_PreventiveActivity.this, Material_Request_MR_Screen_PreventiveActivity.class);
                                send.putExtra("job_id",str_job_id);

                                send.putExtra("service_title",service_title);
                                send.putExtra("status",status);
                                startActivity(send);
//                                Toast.makeText(context,"Page Not Found",Toast.LENGTH_SHORT).show();
                            }
                            else if(PageNumber == 5){

                                SubPageNumber = response.body().getData().getSubPage_number();
                                Log.e("Page ", String.valueOf(SubPageNumber));

                                Intent send = new Intent(Start_Job_Text_PreventiveActivity.this, Preventive_checklistActivity.class);
                                send.putExtra("job_id",str_job_id);

                                send.putExtra("service_title",service_title);
                                send.putExtra("status",status);
                                startActivity(send);
//                                Toast.makeText(context,"Page Not Found",Toast.LENGTH_SHORT).show();
                            }
                            else if(PageNumber == 6){

                                SubPageNumber = response.body().getData().getSubPage_number();
                                Log.e("Page ", String.valueOf(SubPageNumber));

                                Intent send = new Intent(Start_Job_Text_PreventiveActivity.this, Preventive_action_requiredActivity.class);
                                send.putExtra("job_id",str_job_id);

                                send.putExtra("service_title",service_title);
                                send.putExtra("status",status);
                                startActivity(send);
//                                Toast.makeText(context,"Page Not Found",Toast.LENGTH_SHORT).show();
                            }
                            else if(PageNumber == 8){

                                SubPageNumber = response.body().getData().getSubPage_number();
                                Log.e("Page ", String.valueOf(SubPageNumber));

                                Intent send = new Intent(Start_Job_Text_PreventiveActivity.this, Technician_signature_preventiveActivity.class);
                                send.putExtra("job_id",str_job_id);

                                send.putExtra("service_title",service_title);
                                send.putExtra("status",status);
                                startActivity(send);
//                                Toast.makeText(context,"Page Not Found",Toast.LENGTH_SHORT).show();
                            }
                            else if(PageNumber == 9){

                                SubPageNumber = response.body().getData().getSubPage_number();
                                Log.e("Page ", String.valueOf(SubPageNumber));

                                Intent send = new Intent(Start_Job_Text_PreventiveActivity.this, Customer_Details_PreventiveActivity.class);
                                send.putExtra("job_id",str_job_id);

                                send.putExtra("service_title",service_title);
                                send.putExtra("status",status);
                                startActivity(send);
//                                Toast.makeText(context,"Page Not Found",Toast.LENGTH_SHORT).show();
                            }
                            else if(PageNumber == 10){

                                SubPageNumber = response.body().getData().getSubPage_number();
                                Log.e("Page ", String.valueOf(SubPageNumber));

                                Intent send = new Intent(Start_Job_Text_PreventiveActivity.this, Customer_Acknowledgement_PreventiveActivity.class);
                                send.putExtra("job_id",str_job_id);

                                send.putExtra("service_title",service_title);
                                send.putExtra("status",status);
                                startActivity(send);
//                                Toast.makeText(context,"Page Not Found",Toast.LENGTH_SHORT).show();
                            }
                        }


                    } else {
                        Toasty.warning(getApplicationContext(),""+message,Toasty.LENGTH_LONG).show();

                    }
                }
            }

            @Override
            public void onFailure(Call<RetriveResponsePR> call, Throwable t) {

                Log.e("On Failure", "--->" + t.getMessage());
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private Job_status_updateRequest localRequest() {
        Job_status_updateRequest createlocaldata = new Job_status_updateRequest();
        createlocaldata.setUser_mobile_no(se_user_mobile_no);
        createlocaldata.setJob_id(str_job_id);
        createlocaldata.setSMU_SCH_COMPNO(compno);
        //  custom.setSMU_SCH_SERTYPE(sertype);
        Log.e("Request Data ",""+ new Gson().toJson(createlocaldata));
        return createlocaldata;
    }

    private void Job_status() {
        APIInterface apiInterface = RetrofitClient.getClient().create(APIInterface.class);
        Call<Job_statusResponse> call = apiInterface.job_status_PreventiveResponseCall(RestUtils.getContentType(), job_statusRequest());
        Log.w(TAG,"SignupResponse url  :%s"+" "+ call.request().url().toString());

        call.enqueue(new Callback<Job_statusResponse>() {
            @SuppressLint("LogNotTimber")
            @Override
            public void onResponse(@NonNull Call<Job_statusResponse> call, @NonNull retrofit2.Response<Job_statusResponse> response) {

                Log.w(TAG,"SignupResponse" + new Gson().toJson(response.body()));
                if (response.body() != null) {
                    message = response.body().getMessage();

                    if (200 == response.body().getCode()) {
                        if(response.body().getData() != null){

                            Log.d("msg",message);

                            if (!Objects.equals(message, "Not Started")){

                                str_StartTime = response.body().getTime();

                                Log.e (" Start Time  API",""+str_StartTime);

                            }

//                            if (Objects.equals(message, "Job Paused")){
//
//                                retriveLocalvalue();
//                            }
                        }


                    } else {
                        Toasty.warning(getApplicationContext(),""+message,Toasty.LENGTH_LONG).show();

                    }
                }


            }

            @Override
            public void onFailure(@NonNull Call<Job_statusResponse> call, @NonNull Throwable t) {
                Log.e("OTP", "--->" + t.getMessage());
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private Job_statusRequest job_statusRequest() {

        Job_statusRequest custom = new Job_statusRequest();
        custom.setUser_mobile_no(se_user_mobile_no);
        custom.setService_name(service_title);
        custom.setJob_id(str_job_id);
        custom.setSMU_SCH_COMPNO(compno);
        custom.setSMU_SCH_SERTYPE(sertype);
        Log.e("CompNo",""+compno);
        Log.e("SertYpe", ""+sertype);
        Log.w(TAG,"loginRequest "+ new Gson().toJson(custom));
        return custom;
    }

    private void Job_status_update() {

        APIInterface apiInterface = RetrofitClient.getClient().create(APIInterface.class);
        Call<Job_status_updateResponse> call = apiInterface.job_status_update_PreventiveResponseCall(RestUtils.getContentType(), job_status_updateRequest());
        Log.w(TAG,"SignupResponse url  :%s"+" "+ call.request().url().toString());

        call.enqueue(new Callback<Job_status_updateResponse>() {
            @SuppressLint("LogNotTimber")
            @Override
            public void onResponse(@NonNull Call<Job_status_updateResponse> call, @NonNull retrofit2.Response<Job_status_updateResponse> response) {

                Log.w(TAG,"SignupResponse" + new Gson().toJson(response.body()));
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
        custom.setJOB_LOCATION(address);
        custom.setJOB_START_LAT((Latitude));
        custom.setJOB_START_LONG((Logitude));
        Log.e("CompNo",""+compno);
        Log.e("SertYpe", ""+sertype);
        Log.w(TAG,"loginRequest "+ new Gson().toJson(custom));
        return custom;
    }

    private void Check_Pod_Status() {

        APIInterface apiInterface = RetrofitClient.getClient().create(APIInterface.class);
        Call<Check_Pod_StatusResponse> call = apiInterface.Check_Pod_StatusResponseCall(RestUtils.getContentType(), count_pasuedRequest());
        Log.w(TAG,"SignupResponse url  :%s"+" "+ call.request().url().toString());

        call.enqueue(new Callback<Check_Pod_StatusResponse>() {
            @SuppressLint("LogNotTimber")
            @Override
            public void onResponse(@NonNull Call<Check_Pod_StatusResponse> call, @NonNull retrofit2.Response<Check_Pod_StatusResponse> response) {

                Log.w(TAG,"SignupResponse" + new Gson().toJson(response.body()));
                if (response.body() != null) {
                    message = response.body().getMessage();

                    if (200 == response.body().getCode()) {
                        if(response.body().getData() != null){

                            str_job_status = response.body().getData().getStatus();

                            if (Objects.equals(message, "Not Started")){

                                mDialog.dismiss();

                            }
                            Log.e("Status on Send ", ""+status);

//                            Intent send3 = new Intent(Start_Job_Text_PreventiveActivity.this, Esc_TrvActivity.class);
//                            send3.putExtra("job_id",str_job_id);
//                            send3.putExtra("value","ESC/TRV");
//                            send3.putExtra("service_title",service_title);
//                            startActivity(send3);
                            if (Objects.equals(status, "new")){


                                if(str_job_status.equals("POD")){
                                    Intent send = new Intent(Start_Job_Text_PreventiveActivity.this, Monthlist_Preventive_Activity.class);
                                    send.putExtra("job_id",str_job_id);
                                    // send.putExtra("value","POD");

                                    SharedPreferences.Editor editor = sharedPreferences.edit();
                                    editor.putString("statustype", "POD");
                                    editor.putString("starttime", str_StartTime);
                                    editor.putString("lati", String.valueOf(Latitude));
                                    editor.putString("long", String.valueOf(Logitude));
                                    editor.putString("add",address);
                                    editor.apply();

                                    send.putExtra("service_title",service_title);
                                    send.putExtra("status",status);
                                    startActivity(send);
                                }
                                else if(str_job_status.equals("SEMPOD")){
                                    Intent send1 = new Intent(Start_Job_Text_PreventiveActivity.this, Monthlist_Preventive_Activity.class);
                                    send1.putExtra("job_id",str_job_id);
                                    //  send1.putExtra("value","SEMPOD");

                                    SharedPreferences.Editor editor = sharedPreferences.edit();
                                    editor.putString("statustype", "SEMPOD");
                                    editor.putString("starttime", str_StartTime);
                                    editor.putString("lati", String.valueOf(Latitude));
                                    editor.putString("long", String.valueOf(Logitude));
                                    editor.putString("add",address);
                                    editor.apply();

                                    send1.putExtra("service_title",service_title);
                                    send1.putExtra("status",status);
                                    startActivity(send1);
                                }
                                else if(str_job_status.equals("MOD")){
                                    Intent send2 = new Intent(Start_Job_Text_PreventiveActivity.this, Monthlist_Preventive_Activity.class);
                                    send2.putExtra("job_id",str_job_id);
                                    // send2.putExtra("value","MOD");

                                    SharedPreferences.Editor editor = sharedPreferences.edit();
                                    editor.putString("statustype", "MOD");
                                    editor.putString("starttime", str_StartTime);
                                    editor.putString("lati", String.valueOf(Latitude));
                                    editor.putString("long", String.valueOf(Logitude));
                                    editor.putString("add",address);
                                    editor.apply();

                                    send2.putExtra("service_title",service_title);
                                    send2.putExtra("status",status);
                                    startActivity(send2);
                                }
                                else {
                                    Intent send3 = new Intent(Start_Job_Text_PreventiveActivity.this, ESCTRV.class);
                                    send3.putExtra("job_id",str_job_id);
                                    //  send3.putExtra("value","ESC/TRV");

                                    SharedPreferences.Editor editor = sharedPreferences.edit();
                                    editor.putString("statustype", "ESC/TRV");
                                    editor.putString("starttime", str_StartTime);
                                    editor.putString("lati", String.valueOf(Latitude));
                                    editor.putString("long", String.valueOf(Logitude));
                                    editor.putString("add",address);
                                    editor.apply();

                                    send3.putExtra("service_title",service_title);
                                    send3.putExtra("status",status);
                                    startActivity(send3);
                                }
                            }
                            else{

                                retriveLocalvalue();
                            }


                        }


                    } else {
                        Toasty.warning(getApplicationContext(),""+message,Toasty.LENGTH_LONG).show();

                    }
                }


            }

            @Override
            public void onFailure(@NonNull Call<Check_Pod_StatusResponse> call, @NonNull Throwable t) {
                Log.e("OTP", "--->" + t.getMessage());
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private Check_Pod_StatusRequest count_pasuedRequest() {

        Check_Pod_StatusRequest count = new Check_Pod_StatusRequest();
        count.setUser_mobile_no(se_user_mobile_no);
        count.setJob_id(str_job_id);
        Log.w(TAG,"loginRequest "+ new Gson().toJson(count));
        return count;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private void getMYLocation() {

        Log.e("Hi","Getting Your Location");
        gpsTracker = new GpsTracker(Start_Job_Text_PreventiveActivity.this);
        if(gpsTracker.canGetLocation()){
            Latitude = gpsTracker.getLatitude();
            Logitude = gpsTracker.getLongitude();
            Log.e("Lat ",Latitude + " Long: " + Logitude);

            if (Latitude > 0.0 && Logitude > 0.0){
                geocoder = new Geocoder(context, Locale.getDefault());

                try {
                    myAddress = geocoder.getFromLocation(gpsTracker.getLatitude(),gpsTracker.getLongitude(),1);
                    address = myAddress.get(0).getAddressLine(0);
                } catch (IOException e) {
                    e.printStackTrace();
                }



                Log.e("Address",address);
            }
        }else{
            gpsTracker.showSettingsAlert();
        }
    }
}