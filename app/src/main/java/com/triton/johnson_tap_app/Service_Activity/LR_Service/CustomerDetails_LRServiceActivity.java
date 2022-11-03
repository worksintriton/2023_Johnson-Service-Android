package com.triton.johnson_tap_app.Service_Activity.LR_Service;

import static android.content.ContentValues.TAG;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.VolleyLog;
import com.google.gson.Gson;
import com.triton.johnson_tap_app.Db.CommonUtil;
import com.triton.johnson_tap_app.Db.DbHelper;
import com.triton.johnson_tap_app.Db.DbUtil;
import com.triton.johnson_tap_app.R;
import com.triton.johnson_tap_app.Service_Activity.BreakdownMRApprovel.BreakdownMRListOne_Activity;
import com.triton.johnson_tap_app.Service_Activity.BreakdownMRApprovel.BreakdownMR_Activity;
import com.triton.johnson_tap_app.Service_Activity.BreakdownMRApprovel.MRForms_BreakdownMRActivity;
import com.triton.johnson_tap_app.Service_Activity.ServicesActivity;
import com.triton.johnson_tap_app.api.APIInterface;
import com.triton.johnson_tap_app.api.RetrofitClient;
import com.triton.johnson_tap_app.requestpojo.Job_status_updateRequest;
import com.triton.johnson_tap_app.requestpojo.LRService_SubmitRequest;
import com.triton.johnson_tap_app.requestpojo.ServiceUserdetailsRequestResponse;
import com.triton.johnson_tap_app.responsepojo.Job_status_updateResponse;
import com.triton.johnson_tap_app.responsepojo.Retrive_LocalValueResponse;
import com.triton.johnson_tap_app.responsepojo.SuccessResponse;
import com.triton.johnson_tap_app.utils.RestUtils;

import java.util.List;

import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CustomerDetails_LRServiceActivity extends AppCompatActivity {

    Context context;
    EditText edt_Custname, edt_Custno, edt_Custremarks;
    TextView txt_Custname, txt_Custno;
    Button btn_Prev,btn_Next;
    String status,job_id,se_user_mobile_no, se_user_name, se_id,service_title,str_Techsign,str_CustAck,str_job_status,message,str_Quoteno;
    ImageView img_Back,img_Pause;
    String str_Custname="", str_Custno="", str_Custremarks="",service_type;
    SharedPreferences sharedPreferences;
    AlertDialog alertDialog;
    private String TAG ="LR SERVICE";
    List<Retrive_LocalValueResponse.Data> databeanList;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_customerdetails_lrservice);
        context = this;


        CommonUtil.dbUtil = new DbUtil(context);
        CommonUtil.dbUtil.open();
        CommonUtil.dbHelper = new DbHelper(context);


        edt_Custname = findViewById(R.id.edt_cust_name);
        edt_Custno = findViewById(R.id.edt_cust_no);
        edt_Custremarks = findViewById(R.id.edt_cust_remarks);
        btn_Prev = findViewById(R.id.btn_prev);
        btn_Next = findViewById(R.id.btn_next);
        img_Back = findViewById(R.id.img_back);
        img_Pause = findViewById(R.id.img_paused);
        txt_Custname = findViewById(R.id.txt_cust_name);
        txt_Custno = findViewById(R.id.txt_cust_no);

        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            //  service_title = extras.getString("service_title");
            status = extras.getString("status");
            //   Log.e("Name",":" + service_title);
            Log.e("Status", "" + status);
            job_id = extras.getString("job_id");
            Log.e("jobID", "" + job_id);
            str_Custname = extras.getString("C_name");
            str_Custno = extras.getString("C_no");
            str_Custremarks = extras.getString("C_remarks");
            str_Techsign = extras.getString("tech_signature");
            str_CustAck = extras.getString("cust_ack");
            Log.e("A", "" + str_Custname);
            Log.e("A", "" + str_Custno);
            Log.e("A", "" + str_Custremarks);
            edt_Custname.setText(str_Custname);
            edt_Custno.setText(str_Custno);
            edt_Custremarks.setText(str_Custremarks);
        }
        sharedPreferences =  PreferenceManager.getDefaultSharedPreferences(this);
        se_id = sharedPreferences.getString("_id", "default value");
        se_user_mobile_no = sharedPreferences.getString("user_mobile_no", "default value");
        se_user_name = sharedPreferences.getString("user_name", "default value");
        service_title = sharedPreferences.getString("service_title", "Services");
        str_Quoteno = sharedPreferences.getString("quoteno","123");
        service_type = sharedPreferences.getString("service_type","PSM");
        Log.e("Name", "" + service_title);
        Log.e("Mobile", ""+ se_user_mobile_no);
        Log.e("QuoteNO", ""+ str_Quoteno);

        Spannable name_Upload = new SpannableString("Customer Name ");
        name_Upload.setSpan(new ForegroundColorSpan(context.getResources().getColor(R.color.colorAccent)), 0, name_Upload.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        txt_Custname.setText(name_Upload);
        Spannable name_Upload1 = new SpannableString("*");
        name_Upload1.setSpan(new ForegroundColorSpan(Color.RED), 0, name_Upload1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        txt_Custname.append(name_Upload1);

        Spannable no = new SpannableString("Customer Number ");
        no.setSpan(new ForegroundColorSpan(context.getResources().getColor(R.color.colorAccent)), 0, name_Upload.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        txt_Custno.setText(no);
        Spannable no1 = new SpannableString("*");
        no1.setSpan(new ForegroundColorSpan(Color.RED), 0, name_Upload1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        txt_Custno.append(no1);




        if (status.equals("pause")){

           retrive_LocalValue();
        }else{
            getCustomer(job_id,service_title);
        }

        btn_Prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                Intent send = new Intent(context, LR_Details_Activity.class);
//                // send.putExtra("service_title",service_title);
//                send.putExtra("job_id",job_id);
//                send.putExtra("status" , status);
//                send.putExtra("C_name" , str_Custname);
//                send.putExtra("C_no" , str_Custno);
//                send.putExtra("C_remarks" , str_Custremarks);
//                send.putExtra("tech_signature", str_Techsign);
//                send.putExtra("cust_ack",str_CustAck);
//                startActivity(send);

                onBackPressed();
            }
        });

        img_Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent send = new Intent(context, LR_Details_Activity.class);
//                // send.putExtra("service_title",service_title);
//                send.putExtra("job_id",job_id);
//                send.putExtra("status", status);
//                send.putExtra("C_name", str_Custname);
//                send.putExtra("C_no", str_Custno);
//                send.putExtra("C_remarks", str_Custremarks);
//                send.putExtra("tech_signature", str_Techsign);
//                send.putExtra("cust_ack", str_CustAck);
//                startActivity(send);
                onBackPressed();
            }
        });

        btn_Next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addCutomerData();

                str_Custname = edt_Custname.getText().toString();
                str_Custno = edt_Custno.getText().toString();
                str_Custremarks = edt_Custremarks.getText().toString();

                if (str_Custname.equals("")){
                    edt_Custname.setError("Enter Customer Name");
                }else if(str_Custno.equals("")){
                    edt_Custno.setError("Enter Customer Number");
                }else{

                    Intent send = new Intent(context, TechnicianSignature_LRServiceActivity.class);
                    // send.putExtra("service_title",service_title);
                    send.putExtra("job_id",job_id);
                    send.putExtra("status" , status);
                    send.putExtra("C_name" , str_Custname);
                    send.putExtra("C_no" , str_Custno);
                    send.putExtra("C_remarks" , str_Custremarks);
                    send.putExtra("tech_signature", str_Techsign);
                    send.putExtra("cust_ack",str_CustAck);
                    startActivity(send);
                }
            }
        });

        img_Pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                alertDialog = new AlertDialog.Builder(context)
                        .setTitle("Are you sure to pause this job ?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialogInterface, int i) {
                                str_job_status = "Job Paused";
                               // Job_status_update();
                                str_Custname = edt_Custname.getText().toString();
                                str_Custno = edt_Custno.getText().toString();
                                str_Custremarks = edt_Custremarks.getText().toString();
                                createLocalValueCall();
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

    private void retrive_LocalValue() {

        APIInterface apiInterface =  RetrofitClient.getClient().create((APIInterface.class));
        Call<Retrive_LocalValueResponse> call = apiInterface.retriveLocalValueCallLR(RestUtils.getContentType(),localRequest());
        Log.w(TAG,"Retrive Local Value url  :%s"+" "+ call.request().url().toString());

        call.enqueue(new Callback<Retrive_LocalValueResponse>() {
            @Override
            public void onResponse(Call<Retrive_LocalValueResponse> call, Response<Retrive_LocalValueResponse> response) {

                Log.e(TAG,"Retrive Response" + new Gson().toJson(response.body()));

                if (response.body() != null){

                    message = response.body().getMessage();

                    if (response.body().getCode() == 200){

                        if (response.body().getData() != null){
                            Log.e("msg",message);

                            str_Custname= response.body().getData().getCustomerName();
                            str_Custno= response.body().getData().getCustomerNo();
                            str_Custremarks = response.body().getData().getRemarks();


                            edt_Custname.setText(str_Custname);
                            edt_Custno.setText(str_Custno);
                            edt_Custremarks.setText(str_Custremarks);

                        }
                    }else{
                        Toasty.warning(getApplicationContext(),""+message,Toasty.LENGTH_LONG).show();
                    }
                }

            }

            @Override
            public void onFailure(Call<Retrive_LocalValueResponse> call, Throwable t) {

                Log.e("On Failure", "--->" + t.getMessage());
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private Job_status_updateRequest localRequest() {

        Job_status_updateRequest custom = new Job_status_updateRequest();
        custom.setUser_mobile_no(se_user_mobile_no);
        custom.setJobId(job_id);
        custom.setSMU_SCQH_QUOTENO(str_Quoteno);
        //  custom.setSMU_SCH_SERTYPE(sertype);
        Log.w(TAG,"Request Data "+ new Gson().toJson(custom));
        return custom;
    }


    private void createLocalValueCall() {

        APIInterface apiInterface =  RetrofitClient.getClient().create((APIInterface.class));
        Call<SuccessResponse> call = apiInterface.createLocalValueCallLR(RestUtils.getContentType(),createLocalRequest());
        Log.w(TAG,"Create Local Value url  :%s"+" "+ call.request().url().toString());

        call.enqueue(new Callback<SuccessResponse>() {
            @Override
            public void onResponse(Call<SuccessResponse> call, Response<SuccessResponse> response) {

                Log.w(TAG,"SignupResponse" + new Gson().toJson(response.body()));

                if (response.body() != null) {
                    message = response.body().getMessage();

                    if (response.body().getCode() == 200){

                        if(response.body().getData() != null){

                            Log.d("msg",message);

                            Intent send = new Intent(context, ServicesActivity.class);
                            startActivity(send);
                        }

                    }else{


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

    private LRService_SubmitRequest createLocalRequest() {
        LRService_SubmitRequest request = new LRService_SubmitRequest();
        request.setJobId(job_id);
        request.setUserId(se_user_mobile_no);
        request.setServiceType(service_type);
        request.setCustomerName(str_Custname);
        request.setCustomerNo(str_Custno);
        request.setRemarks(str_Custremarks);
        request.setTechSignature("-");
        request.setCustomerAcknowledgement("-");
        request.setSMU_SCQH_QUOTENO(str_Quoteno);
        request.set_id(se_id);
        Log.w(TAG," Local Request"+ new Gson().toJson(request));
        return request;
    }

    private void Job_status_update() {

        APIInterface apiInterface = RetrofitClient.getClient().create(APIInterface.class);
        Call<Job_status_updateResponse> call = apiInterface.job_status_updateLRResponseCall(com.triton.johnson_tap_app.utils.RestUtils.getContentType(), job_status_updateRequest());
        Log.w(VolleyLog.TAG,"Response url  :%s"+" "+ call.request().url().toString());

        call.enqueue(new Callback<Job_status_updateResponse>() {
            @SuppressLint("LogNotTimber")
            @Override
            public void onResponse(@NonNull Call<Job_status_updateResponse> call, @NonNull Response<Job_status_updateResponse> response) {

                Log.w(VolleyLog.TAG,"Response" + new Gson().toJson(response.body()));
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
        custom.setJob_id(job_id);
        custom.setStatus(str_job_status);
        custom.setSMU_SCQH_QUOTENO(str_Quoteno);
        Log.w(VolleyLog.TAG,"Request "+ new Gson().toJson(custom));
        return custom;
    }

    private void addCutomerData() {

        Log.e("Hi Nish","Adding");

        str_Custname = edt_Custname.getText().toString();
        str_Custno = edt_Custno.getText().toString();
        str_Custremarks = edt_Custremarks.getText().toString();

        if (str_Custname.equals("")){
            edt_Custname.setError("Enter Customer Name");
        }else if(str_Custno.equals("")){
            edt_Custno.setError("Enter Customer Number");
        }else{
             CommonUtil.dbUtil.addCustomer(job_id,service_title,str_Custname,str_Custno,str_Custremarks);
//            Intent send = new Intent(context, BreakdownMR_Activity.class);
//            send.putExtra("status", status);
//            startActivity(send);
             Cursor cur = CommonUtil.dbUtil.getCustmer(job_id,service_title);
             Log.e("CUSTOMER",""+cur.getCount());
        }


    }

    private void getCustomer(String job_id, String service_title) {

        Cursor cur = CommonUtil.dbUtil.getCustmer(job_id,service_title);

        Log.e("CUSTOMER",""+cur.getCount());

        if (cur.getCount()>0 && cur.moveToFirst()){

            do {
                @SuppressLint("Range")
                String customer_name = cur.getString(cur.getColumnIndex(DbHelper.CUSTOMER_NAME));
                @SuppressLint("Range")
                String customer_no = cur.getString(cur.getColumnIndex(DbHelper.CUSTOMER_NUMBER));
                @SuppressLint("Range")
                String customer_remarks = cur.getString(cur.getColumnIndex(DbHelper.CUSTOMER_REMARKS));

                edt_Custname.setText(customer_name);
                edt_Custno.setText(customer_no);
                edt_Custremarks.setText(customer_remarks);

            }while (cur.moveToNext());
        }
    }

    @Override
    public void onBackPressed() {
//        Intent send = new Intent(context, LR_Details_Activity.class);
//        // send.putExtra("service_title",service_title);
//        send.putExtra("job_id",job_id);
//        send.putExtra("status" , status);
//        send.putExtra("C_name" , str_Custname);
//        send.putExtra("C_no" , str_Custno);
//        send.putExtra("C_remarks" , str_Custremarks);
//        send.putExtra("tech_signature", str_Techsign);
//        send.putExtra("cust_ack",str_CustAck);
//        Log.e("Hi",str_Custname);
//        Log.e("Hi",str_Custno);
//        Log.e("HI",str_Custremarks);
       // startActivity(send);

        alertDialog = new AlertDialog.Builder(context)
                .setTitle("Are you sure to Pause this job ?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        addCutomerData();
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
