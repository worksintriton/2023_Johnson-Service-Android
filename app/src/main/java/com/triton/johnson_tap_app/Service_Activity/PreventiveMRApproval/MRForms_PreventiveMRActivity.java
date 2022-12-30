package com.triton.johnson_tap_app.Service_Activity.PreventiveMRApproval;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

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
import com.triton.johnson_tap_app.requestpojo.ServiceUserdetailsRequest;
import com.triton.johnson_tap_app.responsepojo.ServiceUserdetailsResponse;
import com.triton.johnson_tap_app.utils.ConnectionDetector;
import com.triton.johnson_tap_app.utils.RestUtils;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MRForms_PreventiveMRActivity extends AppCompatActivity {

    private Button btnSelection,btn_prev,btn_Ok;
    String value,job_id,feedback_group,feedback_details,bd_dta,feedback_remark,str_mr1 ="",str_mr2="",str_mr3="",str_mr4="",str_mr5="",str_mr6="",str_mr7="",str_mr8="",str_mr9="",str_mr10="",tech_signature="";
    ImageView iv_back,iv_pause;
    EditText mr1,mr2,mr3,mr4,mr5,mr6,mr7,mr8,mr9,mr10;
    String se_user_mobile_no, se_user_name, se_id,check_id,service_title,status;
    private String TAG ="MRForms_PreventiveMRActivity";
    List<ServiceUserdetailsResponse.Datum> servicedetailsbean;
    Context context;
    String myactivity = "Preventive MR", compno, sertype;
    String s_mr1 ="", s_mr2 ="",s_mr3 ="",s_mr4 ="",s_mr5 ="",s_mr6 ="",s_mr7 ="",s_mr8 ="",s_mr9 ="",s_mr10 ="",networkStatus="";
    AlertDialog.Builder builder;
    AlertDialog alertDialog;
    TextView txt_Jobid,txt_Starttime;
    String str_StartTime;
    ProgressDialog dialog;
    ScrollView line_MR;
    RelativeLayout rel_Pop;
    AlertDialog mDialog;

    @SuppressLint("MissingInflatedId")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_mrforms_preventive_mractivity);
        context = this;

        CommonUtil.dbUtil = new DbUtil(context);
        CommonUtil.dbUtil.open();
        CommonUtil.dbHelper = new DbHelper(context);

        btnSelection = (Button) findViewById(R.id.btn_next);
        btn_prev = (Button) findViewById(R.id.btn_show);
        iv_back = (ImageView) findViewById(R.id.iv_back);
        mr1 = (EditText) findViewById(R.id.mr1);
        mr2 = (EditText) findViewById(R.id.mr2);
        mr3 = (EditText) findViewById(R.id.mr3);
        mr4 = (EditText) findViewById(R.id.mr4);
        mr5 = (EditText) findViewById(R.id.mr5);
        mr6 = (EditText) findViewById(R.id.mr6);
        mr7 = (EditText) findViewById(R.id.mr7);
        mr8 = (EditText) findViewById(R.id.mr8);
        mr9 = (EditText) findViewById(R.id.mr9);
        mr10 = (EditText) findViewById(R.id.mr10);
        iv_pause = findViewById(R.id.ic_paused);
        txt_Starttime = findViewById(R.id.txt_starttime);
        txt_Jobid = findViewById(R.id.txt_jobid);
        line_MR = findViewById(R.id.line_mr);
        rel_Pop = findViewById(R.id.rel_pop);
        btn_Ok = findViewById(R.id.btn_ok);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        se_id = sharedPreferences.getString("_id", "default value");
        se_user_mobile_no = sharedPreferences.getString("user_mobile_no", "default value");
        se_user_name = sharedPreferences.getString("user_name", "default value");
        compno = sharedPreferences.getString("compno","123");
        sertype = sharedPreferences.getString("sertype","123");
        service_title = sharedPreferences.getString("service_title", "default value");
        job_id = sharedPreferences.getString("job_id", "default value");
        Log.e("Name",""+ service_title);
        Log.e("Job ID",""+ job_id);

        str_StartTime = sharedPreferences.getString("starttime","");
        str_StartTime = str_StartTime.replaceAll("[^0-9-:]", " ");
        Log.e("Start Time",str_StartTime);
        txt_Jobid.setText("Job ID : " + job_id);
        txt_Starttime.setText("Start Time : " + str_StartTime);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            value = extras.getString("value");
            status = extras.getString("status");
            Log.e("Status", "" + status);
        }

        if (extras != null) {
            feedback_group = extras.getString("feedback_group");
        }

        if (extras != null) {
            bd_dta = extras.getString("bd_details");
        }

        if (extras != null) {
           // job_id = extras.getString("job_id");
        }

        if (extras != null) {
            feedback_details = extras.getString("feedback_details");
        }
        if (extras != null) {
            feedback_remark = extras.getString("feedback_remark");
        }
        if (extras != null) {
            str_mr1 = extras.getString("mr1");
            mr1.setText(str_mr1);
        }
        if (extras != null) {
            str_mr2 = extras.getString("mr2");
            mr2.setText(str_mr2);
        }
        if (extras != null) {
            str_mr3 = extras.getString("mr3");
            mr3.setText(str_mr3);
        }
        if (extras != null) {
            str_mr4 = extras.getString("mr4");
            mr4.setText(str_mr4);
        }
        if (extras != null) {
            str_mr5 = extras.getString("mr5");
            mr5.setText(str_mr5);
        }
        if (extras != null) {
            str_mr6 = extras.getString("mr6");
            mr6.setText(str_mr6);
        }
        if (extras != null) {
            str_mr7 = extras.getString("mr7");
            mr7.setText(str_mr7);
        }
        if (extras != null) {
            str_mr8 = extras.getString("mr8");
            mr8.setText(str_mr8);
        }
        if (extras != null) {
            str_mr9 = extras.getString("mr9");
            mr9.setText(str_mr9);
        }
        if (extras != null) {
            str_mr10 = extras.getString("mr10");
            mr10.setText(str_mr10);
        }

        if (extras != null) {
            tech_signature = extras.getString("tech_signature");
        }

        btn_prev.setBackgroundResource(R.drawable.blue_button_background_with_radius);
        btn_prev.setTextColor(getResources().getColor(R.color.white));
        btn_prev.setEnabled(true);
        // calling API

        if (status.equals("pause")) {
            Log.e("Inside", "Paused Job");

            networkStatus = ConnectionDetector.getConnectivityStatusString(getApplicationContext());

            Log.e("Network",""+networkStatus);
            if (networkStatus.equalsIgnoreCase("Not connected to Internet")) {

                NoInternetDialog();

            }else {

                Callservice_userdetails();
            }

            iv_pause.setVisibility(View.INVISIBLE);

            getData(job_id,myactivity);

            iv_pause.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    builder = new AlertDialog.Builder(MRForms_PreventiveMRActivity.this);

                    builder.setMessage("Are You sure want to pause this job ?")
                            .setCancelable(false)
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {

                                    addData();

                                }
                            })
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            });

                    AlertDialog alert = builder.create();
                    //Setting the title manually
                    alert.show();
                }
            });

            btnSelection.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {

                    addData();

                    String s_mr1 = mr1.getText().toString();
                    String s_mr2 = mr2.getText().toString();
                    String s_mr3 = mr3.getText().toString();
                    String s_mr4 = mr4.getText().toString();
                    String s_mr5 = mr5.getText().toString();
                    String s_mr6 = mr6.getText().toString();
                    String s_mr7 = mr7.getText().toString();
                    String s_mr8 = mr8.getText().toString();
                    String s_mr9 = mr9.getText().toString();
                    String s_mr10 = mr10.getText().toString();

                    if (s_mr1.equals("")) {
                        mr1.setError("Please Enter the MR1");
                    }
                    else {
                        Intent send = new Intent(MRForms_PreventiveMRActivity.this, PreventiveMRListOne_Activity.class);
                        send.putExtra("value", value);
                        send.putExtra("feedback_details", feedback_details);
                        send.putExtra("feedback_group", feedback_group);
                        send.putExtra("bd_details", bd_dta);
                        send.putExtra("job_id", job_id);
                        send.putExtra("feedback_remark", feedback_remark);
                        send.putExtra("mr1", s_mr1);
                        send.putExtra("mr2", s_mr2);
                        send.putExtra("mr3", s_mr3);
                        send.putExtra("mr4", s_mr4);
                        send.putExtra("mr5", s_mr5);
                        send.putExtra("mr6", s_mr6);
                        send.putExtra("mr7", s_mr7);
                        send.putExtra("mr8", s_mr8);
                        send.putExtra("mr9", s_mr9);
                        send.putExtra("mr10", s_mr10);
                        send.putExtra("status", status);
                        startActivity(send);
                    }

                }
            });
        }
        else{
            Log.e("Inside", "New Job ");

            Cursor cur = CommonUtil.dbUtil.getBreakdownMrList(job_id,myactivity);

            Log.e("MRNewLIST" ,"" + cur.getCount());

            if (cur.getCount()==0){
                Log.e("way", "API");

                networkStatus = ConnectionDetector.getConnectivityStatusString(getApplicationContext());

                Log.e("Network",""+networkStatus);
                if (networkStatus.equalsIgnoreCase("Not connected to Internet")) {

                  NoInternetDialog();

                }else {

                    Callservice_userdetails();
                }

            }else {
                Log.e("way","db");
                getData(job_id,myactivity);
            }

            btnSelection.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {

                    addData();

                    String s_mr1 = mr1.getText().toString();
                    String s_mr2 = mr2.getText().toString();
                    String s_mr3 = mr3.getText().toString();
                    String s_mr4 = mr4.getText().toString();
                    String s_mr5 = mr5.getText().toString();
                    String s_mr6 = mr6.getText().toString();
                    String s_mr7 = mr7.getText().toString();
                    String s_mr8 = mr8.getText().toString();
                    String s_mr9 = mr9.getText().toString();
                    String s_mr10 = mr10.getText().toString();

                    if (s_mr1.equals("")) {
                        mr1.setError("Please Enter the MR1");
                    }
                    else {
                        Intent send = new Intent(MRForms_PreventiveMRActivity.this, PreventiveMRListOne_Activity.class);
                        send.putExtra("value", value);
                        send.putExtra("feedback_details", feedback_details);
                        send.putExtra("feedback_group", feedback_group);
                        send.putExtra("bd_details", bd_dta);
                        send.putExtra("job_id", job_id);
                        send.putExtra("feedback_remark", feedback_remark);
                        send.putExtra("mr1", s_mr1);
                        send.putExtra("mr2", s_mr2);
                        send.putExtra("mr3", s_mr3);
                        send.putExtra("mr4", s_mr4);
                        send.putExtra("mr5", s_mr5);
                        send.putExtra("mr6", s_mr6);
                        send.putExtra("mr7", s_mr7);
                        send.putExtra("mr8", s_mr8);
                        send.putExtra("mr9", s_mr9);
                        send.putExtra("mr10", s_mr10);
                        send.putExtra("status", status);
                        startActivity(send);
                    }

                }
            });
        }

        btn_Ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(getIntent());
            }
        });

        btn_prev.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

//                Intent send = new Intent( MRForms_PreventiveMRActivity.this, StartJob_PreventiveMR_Activity.class);
//                send.putExtra("value","yes");
//                send.putExtra("feedback_details",feedback_details);
//                send.putExtra("feedback_group",feedback_group);
//                send.putExtra("bd_details",bd_dta);
//                send.putExtra("job_id",job_id);
//                send.putExtra("status", status);
//                send.putExtra("feedback_remark", feedback_remark);
//                startActivity(send);
                onBackPressed();
            }
        });

        iv_back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

//                Intent send = new Intent( MRForms_PreventiveMRActivity.this, StartJob_PreventiveMR_Activity.class);
//                send.putExtra("value","yes");
//                send.putExtra("feedback_details",feedback_details);
//                send.putExtra("feedback_group",feedback_group);
//                send.putExtra("bd_details",bd_dta);
//                send.putExtra("job_id",job_id);
//                send.putExtra("feedback_remark", feedback_remark);
//                send.putExtra("status", status);
//                startActivity(send);
                onBackPressed();
            }
        });

    }

    private void getData(String job_id, String myactivity) {

        Log.e("JobId",""+job_id);
        Log.e("Activity",""+myactivity);


        Cursor cur = CommonUtil.dbUtil.getBreakdownMrList(job_id,myactivity);

        Log.e("MRLIST" ,"" + cur.getCount());

        if (cur.getCount()>0 && cur.moveToFirst()){

            do{
                String s_mr1 = cur.getString(cur.getColumnIndexOrThrow(DbHelper.MR1));
                String s_mr2 = cur.getString(cur.getColumnIndexOrThrow(DbHelper.MR2));
                String s_mr3 = cur.getString(cur.getColumnIndexOrThrow(DbHelper.MR3));
                String s_mr4 = cur.getString(cur.getColumnIndexOrThrow(DbHelper.MR4));
                String s_mr5 = cur.getString(cur.getColumnIndexOrThrow(DbHelper.MR5));
                String s_mr6 = cur.getString(cur.getColumnIndexOrThrow(DbHelper.MR6));
                String s_mr7 = cur.getString(cur.getColumnIndexOrThrow(DbHelper.MR7));
                String s_mr8 = cur.getString(cur.getColumnIndexOrThrow(DbHelper.MR8));
                String s_mr9 = cur.getString(cur.getColumnIndexOrThrow(DbHelper.MR9));
                String s_mr10 = cur.getString(cur.getColumnIndexOrThrow(DbHelper.MR10));
                mr1.setText(s_mr1);
                mr2.setText(s_mr2);
                mr3.setText(s_mr3);
                mr4.setText(s_mr4);
                mr5.setText(s_mr5);
                mr6.setText(s_mr6);
                mr7.setText(s_mr7);
                mr8.setText(s_mr8);
                mr9.setText(s_mr9);
                mr10.setText(s_mr10);
            }while (cur.moveToNext());
        }
        cur.close();

    }

    private void addData() {

        s_mr1 = mr1.getText().toString();
        s_mr2 = mr2.getText().toString();
        s_mr3 = mr3.getText().toString();
        s_mr4 = mr4.getText().toString();
        s_mr5 = mr5.getText().toString();
        s_mr6 = mr6.getText().toString();
        s_mr7 = mr7.getText().toString();
        s_mr8 = mr8.getText().toString();
        s_mr9 = mr9.getText().toString();
        s_mr10 = mr10.getText().toString();


        if (s_mr1.equals("")) {
            mr1.setError("Please Enter the MR1");
        }
        else{
            CommonUtil.dbUtil.addBreakdownMRList(s_mr1,s_mr2,s_mr3,s_mr4,s_mr5,s_mr6,s_mr7,
                    s_mr8,s_mr9,s_mr10,job_id, myactivity);
            Intent send = new Intent(MRForms_PreventiveMRActivity.this, PreventiveMR_Activity.class);
            send.putExtra("status", status);
            startActivity(send);
            Cursor c = CommonUtil.dbUtil.getBreakdownMrList();
            Log.e("MRLIST" ,"" + c.getCount());
        }

    }

    public void Callservice_userdetails() {

//        dialog = new ProgressDialog(context, R.style.NewProgressDialog);
//        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//        dialog.setContentView(R.layout.progroess_popup);
//        dialog.show();

        APIInterface apiInterface = RetrofitClient.getClient().create(APIInterface.class);
        Call<ServiceUserdetailsResponse> call = apiInterface.ServiceUserdetailsPrventiveMRCall(RestUtils.getContentType(), serviceUserdetailsRequest());
        Log.w(TAG,"Callservice_userdetails url  :%s"+" "+ call.request().url().toString());

        Log.e("Hi","Nish");
//
//        long delayInMillis = 5000;
//        Timer timer = new Timer();
//        timer.schedule(new TimerTask() {
//            @Override
//            public void run() {
//                dialog.dismiss();
////                ErrorAlert();
//            }
//        }, delayInMillis);

        call.enqueue(new Callback<ServiceUserdetailsResponse>() {

            @Override
            public void onResponse(Call<ServiceUserdetailsResponse> call, Response<ServiceUserdetailsResponse> response) {

                Log.w(TAG,"Callservice_userdetails" + new Gson().toJson(response.body()));
                if (response.body() != null) {
                    String message = response.body().getMessage();
                    if (200 == response.body().getCode()) {
                        if(response.body().getData() != null){

                            rel_Pop.setVisibility(View.GONE);
                            line_MR.setVisibility(View.VISIBLE);
                            btnSelection.setVisibility(View.VISIBLE);

                            servicedetailsbean = response.body().getData();
                            int i =0;
                            for( i=0; i<servicedetailsbean.size(); i ++) {

                                String title = servicedetailsbean.get(i). getTitle();
                                String value = servicedetailsbean.get(i).getValue();
                                if(title.equals("Mr1")){
                                    mr1.setText(value);
                                } else if(title.equals("Mr2")){
                                    mr2.setText(value);
                                }else if(title.equals("Mr3")){
                                   mr3.setText(value);
                                }
                                else if(title.equals("Mr4")){
                                   mr4.setText(value);
                                }
                                else if(title.equals("Mr5")){
                                   mr5.setText(value);
                                }
                                else if(title.equals("Mr6")){
                                   mr6.setText(value);
                                }
                                else if(title.equals("Mr7")){
                                   mr7.setText(value);
                                }
                                else if(title.equals("Mr8")){
                                   mr8.setText(value);
                                }
                                else if(title.equals("Mr9")){
                                   mr9.setText(value);
                                }
                                else if(title.equals("Mr10")){
                                   mr10.setText(value);
                                }


                                Log.e("Nishanth MR", "" + title + value);

                            }
                        }
                        else{
                            //ErrorAlert();
                        }

                    } else {
                       // ErrorAlert();
                        Toasty.warning(getApplicationContext(),""+message,Toasty.LENGTH_LONG).show();

                    }
                }

                else{
                 //   ErrorAlert();
                }

            }

            @Override
            public void onFailure(Call<ServiceUserdetailsResponse> call, Throwable t) {

                Log.e("On Failure", "--->" + t.getMessage());
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
              //  ErrorAlert();
            }
        });

    }


    private ServiceUserdetailsRequest serviceUserdetailsRequest() {
        ServiceUserdetailsRequest serviceUserdetailsRequest = new ServiceUserdetailsRequest();
        serviceUserdetailsRequest.setJobId(job_id);
        serviceUserdetailsRequest.setUserMobileNo(se_user_mobile_no);
        serviceUserdetailsRequest.setSMU_SCH_COMPNO(compno);
        serviceUserdetailsRequest.setSMU_SCH_SERTYPE(sertype);
        Log.w(TAG, "serviceUserdetailsRequest " + new Gson().toJson(serviceUserdetailsRequest));
        return serviceUserdetailsRequest;
    }

    @Override
    public void onBackPressed() {
//        Intent send = new Intent( MRForms_PreventiveMRActivity.this, StartJob_PreventiveMR_Activity.class);
//        send.putExtra("value","yes");
//        send.putExtra("feedback_details",feedback_details);
//        send.putExtra("feedback_group",feedback_group);
//        send.putExtra("bd_details",bd_dta);
//        send.putExtra("job_id",job_id);
//        send.putExtra("feedback_remark", feedback_remark);
//        send.putExtra("status", status);
//        startActivity(send);

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

    @SuppressLint("MissingInflatedId")
    private void ErrorAlert() {

        AlertDialog.Builder mBuilder = new AlertDialog.Builder(context);
        View mView = getLayoutInflater().inflate(R.layout.popup_tryagain, null);

        TextView txt_Message = mView.findViewById(R.id.txt_message);
        Button btn_Ok = mView.findViewById(R.id.btn_ok);

        txt_Message.setText("Please Try Again Later");


        mBuilder.setView(mView);
        final AlertDialog mDialog = mBuilder.create();
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
}