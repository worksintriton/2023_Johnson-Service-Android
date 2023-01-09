package com.triton.johnson_tap_app.Service_Activity.Preventive_Services;

import android.annotation.SuppressLint;
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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.triton.johnson_tap_app.Db.CommonUtil;
import com.triton.johnson_tap_app.Db.DbHelper;
import com.triton.johnson_tap_app.Db.DbUtil;
import com.triton.johnson_tap_app.R;
import com.triton.johnson_tap_app.RestUtils;
import com.triton.johnson_tap_app.Service_Activity.PreventiveMRApproval.MRForms_PreventiveMRActivity;
import com.triton.johnson_tap_app.Service_Activity.PreventiveMRApproval.PreventiveMR_Activity;
import com.triton.johnson_tap_app.Service_Activity.ServicesActivity;
import com.triton.johnson_tap_app.api.APIInterface;
import com.triton.johnson_tap_app.api.RetrofitClient;
import com.triton.johnson_tap_app.requestpojo.Job_status_updateRequest;
import com.triton.johnson_tap_app.requestpojo.Preventive_Submit_Request;
import com.triton.johnson_tap_app.responsepojo.Job_status_updateResponse;
import com.triton.johnson_tap_app.responsepojo.RetriveLocalValueBRResponse;
import com.triton.johnson_tap_app.responsepojo.RetriveResponsePR;
import com.triton.johnson_tap_app.responsepojo.ServiceUserdetailsResponse;
import com.triton.johnson_tap_app.responsepojo.SuccessResponse;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Material_Request_MR_Screen_PreventiveActivity extends AppCompatActivity {

    private Button btnSelection,btn_prev;
    String valuess="",job_id,Form1_value,value,service_title,Form1_name,Form1_comments,Form1_cat_id,Form1_group_id,str_mr1,str_mr2,str_mr3,str_mr4,str_mr5,str_mr6,str_mr7,str_mr8,str_mr9,str_mr10;
    ImageView iv_back,img_Pause;
    EditText mr1,mr2,mr3,mr4,mr5,mr6,mr7,mr8,mr9,mr10;
    Context context;
    AlertDialog alertDialog;
    String s_mr1 ="", s_mr2 ="",s_mr3 ="",s_mr4 ="",s_mr5 ="",s_mr6 ="",s_mr7 ="",s_mr8 ="",s_mr9 ="",s_mr10 ="",contract_status,status;
    SharedPreferences sharedPreferences;
    String TAG = "MATERIAL REQUEST",value_s,List="",compno,sertype,se_id,se_user_mobile_no,se_user_name,statustype="",message,str_job_status;
    RetriveResponsePR.Data databean ;
    List<RetriveResponsePR.FieldValueDatum> databeanlist;
    double Latitude ,Logitude;
    String address = "";
    int PageNumber = 4;
    List<RetriveResponsePR.FieldValueDatum> servicedetailsbean;

    TextView txt_Jobid,txt_Starttime;
    String str_StartTime;

    String form1_value;
    String form1_name;
    String form1_comments;
    String form1_cat_id;
    String form1_group_id;

    ArrayList<String> mmyvalue;
    ArrayList<String> myname;
    ArrayList<String> comments;
    ArrayList<String> catid;
    ArrayList<String> groupid;

    String[] strValue;
    String[] strName;
    String[] strComments;
    String[] strGroupid;
    String[] strCatid;

    @SuppressLint("MissingInflatedId")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_material_request_mr_screen_preventive);
        context = this;

        CommonUtil.dbUtil = new DbUtil(context);
        CommonUtil.dbUtil.open();
        CommonUtil.dbHelper = new DbHelper(context);

        btnSelection = (Button) findViewById(R.id.btn_next);
       // btn_prev = (Button) findViewById(R.id.btn_show);
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
        img_Pause = findViewById(R.id.img_paused);
        txt_Starttime = findViewById(R.id.txt_starttime);
        txt_Jobid = findViewById(R.id.txt_jobid);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
           // valuess = extras.getString("valuess");
            status = extras.getString("status");
            Log.e("Status",""+status);
        }
        if (extras != null) {
          //  job_id = extras.getString("job_id");
        }

        if (extras != null) {
         //   value = extras.getString("value");
        }

        if (extras != null) {
         //   service_title = extras.getString("service_title");
        }

        if (extras != null) {
            Form1_value = extras.getString("Form1_value");
        }

        if (extras != null) {
            Form1_name = extras.getString("Form1_name");
        }
        if (extras != null) {
            Form1_comments = extras.getString("Form1_comments");
        }

        if (extras != null) {
            Form1_cat_id = extras.getString("Form1_cat_id");
        }

        if (extras != null) {
            Form1_group_id = extras.getString("Form1_group_id");
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

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        contract_status = sharedPreferences.getString("contract_status", "default value");
        Log.e("Contract",""+contract_status);
        service_title = sharedPreferences.getString("service_title", "default value");
        job_id = sharedPreferences.getString("job_id","L1234");
        value_s = sharedPreferences.getString("value","default value");
        Log.e("Name",service_title);
        Log.e("JobID",job_id);
        Log.e("ValueA",value_s);
        List = sharedPreferences.getString("List","1");
        compno = sharedPreferences.getString("compno","123");
        sertype = sharedPreferences.getString("sertype","123");
        se_id = sharedPreferences.getString("_id", "default value");
        se_user_mobile_no = sharedPreferences.getString("user_mobile_no", "default value");
        se_user_name = sharedPreferences.getString("user_name", "default value");
        statustype = sharedPreferences.getString("statustype","OD");
        Log.e("ValueB", statustype);
        Log.e("Month List", "" +List);

        str_StartTime = sharedPreferences.getString("starttime","");
        str_StartTime = str_StartTime.replaceAll("[^0-9-:]", " ");
        Log.e("Start Time",str_StartTime);
        txt_Jobid.setText("Job ID : " + job_id);
        txt_Starttime.setText("Start Time : " + str_StartTime);

        Latitude = Double.parseDouble(sharedPreferences.getString("lati","0.00000"));
        Logitude = Double.parseDouble(sharedPreferences.getString("long","0.00000"));
        address =sharedPreferences.getString("add","Chennai");
        Log.e("Location",""+Latitude+""+Logitude+""+address);

        form1_value = sharedPreferences.getString("Form1_value","124");
        form1_name = sharedPreferences.getString("Form1_name","124");
        form1_comments = sharedPreferences.getString("Form1_comments","124");
        form1_cat_id = sharedPreferences.getString("Form1_cat_id","124");
        form1_group_id = sharedPreferences.getString("Form1_group_id","124");
        Log.e("Value",""+form1_value);
        Log.e("Name",""+form1_name);
        Log.e("Comments",""+form1_comments);
        Log.e("catid",""+form1_cat_id);
        Log.e("groupid",""+form1_group_id);

        if (contract_status.equals("GRACE")){

            alertDialog = new AlertDialog.Builder(context)
                    .setTitle("Job is under Grace Period")
                    .setMessage("While raising MR\n" +
                            "Job is under Grace period, check with your Engineer for MR")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialogInterface, int i) {
                            alertDialog.dismiss();
                        }
                    })

                    .show();
        }


        strValue = form1_value.split(",");
        mmyvalue = new ArrayList<String>(
                Arrays.asList(strValue));

        strName = form1_name.split(",");
        myname = new ArrayList<String>(
                Arrays.asList(strName));

        strComments = form1_comments.split(",");
        comments = new ArrayList<String>(
                Arrays.asList(strComments));

        strGroupid = form1_group_id.split(",");
        groupid = new ArrayList<String>(
                Arrays.asList(strGroupid));

        strCatid = form1_cat_id.split(",");
        catid = new ArrayList<String>(
                Arrays.asList(strCatid));

        Log.e("Nish",""+mmyvalue);

        if (status.equals("new")){

            getData(job_id,service_title);
        }else{

            retriveLocalvalue();
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
                    Intent send = new Intent(Material_Request_MR_Screen_PreventiveActivity.this, Preventive_checklistActivity.class);
                    send.putExtra("valuess",valuess);
                    send.putExtra("job_id",job_id);
                    send.putExtra("value",value);
                    send.putExtra("service_title",service_title);
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
                    send.putExtra("Form1_value", Form1_value);
                    send.putExtra("Form1_name",Form1_name);
                    send.putExtra("Form1_comments",Form1_comments);
                    send.putExtra("Form1_cat_id",Form1_cat_id);
                    send.putExtra("Form1_group_id",Form1_group_id);
                    send.putExtra("status",status);
                    startActivity(send);
                }

            }
        });

        iv_back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

//                Intent send = new Intent( Material_Request_MR_Screen_PreventiveActivity.this, Material_Request_PreventiveActivity.class);
//                send.putExtra("valuess",valuess);
//                send.putExtra("job_id",job_id);
//                send.putExtra("value",value);
//                send.putExtra("service_title",service_title);
//                send.putExtra("Form1_value", Form1_value);
//                send.putExtra("Form1_name",Form1_name);
//                send.putExtra("Form1_comments",Form1_comments);
//                send.putExtra("Form1_cat_id",Form1_cat_id);
//                send.putExtra("Form1_group_id",Form1_group_id);
//                startActivity(send);
                onBackPressed();
            }
        });

        img_Pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DateFormat df = new SimpleDateFormat("EEE, d MMM yyyy, HH:mm");
                String date = df.format(Calendar.getInstance().getTime());

                alertDialog = new AlertDialog.Builder(context)
                        .setTitle("Are you sure to pause this job ?")
                        .setMessage(date)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialogInterface, int i) {
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
                                Toast.makeText(context,"Lat : " + Latitude + "Long : " + Logitude + "Add : " + address,Toast.LENGTH_LONG).show();
                                str_job_status = "Job Paused";
                                Job_status_update();
                                createLocalValue();

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

    private void Job_status_update() {

        APIInterface apiInterface = RetrofitClient.getClient().create(APIInterface.class);
        Call<Job_status_updateResponse> call = apiInterface.job_status_update_PreventiveResponseCall(com.triton.johnson_tap_app.utils.RestUtils.getContentType(), job_status_updateRequest());
        Log.w(TAG,"SignupResponse url  :%s"+" "+ call.request().url().toString());

        call.enqueue(new Callback<Job_status_updateResponse>() {
            @SuppressLint("LogNotTimber")
            @Override
            public void onResponse(@NonNull Call<Job_status_updateResponse> call, @NonNull retrofit2.Response<Job_status_updateResponse> response) {

                Log.w(TAG,"Job Status Update Response" + new Gson().toJson(response.body()));
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
        custom.setSMU_SCH_COMPNO(compno);
        custom.setSMU_SCH_SERTYPE(sertype);
        custom.setJOB_START_LONG(Logitude);
        custom.setJOB_START_LAT(Latitude);
        custom.setJOB_LOCATION(address);
        Log.e("CompNo",""+compno);
        Log.e("SertYpe", ""+sertype);
        Log.w(TAG,"Job Status Update Request "+ new Gson().toJson(custom));
        return custom;
    }

    @SuppressLint("LongLogTag")
    private void retriveLocalvalue() {

        APIInterface apiInterface =  RetrofitClient.getClient().create((APIInterface.class));
        Call<RetriveResponsePR> call = apiInterface.retriveLocalValuePRCall(com.triton.johnson_tap_app.utils.RestUtils.getContentType(),localRequest());
        Log.e("Retrive Local Value url  :%s"," "+ call.request().url().toString());

        call.enqueue(new Callback<RetriveResponsePR>() {
            @Override
            public void onResponse(Call<RetriveResponsePR> call, Response<RetriveResponsePR> response) {

                Log.e("Retrive Response","" + new Gson().toJson(response.body()));

                if (response.body() != null){

                    message = response.body().getMessage();
                    Log.d("message", message);

                    if (response.body().getCode() == 200){

                        if (response.body().getData() != null){

                            databean = response.body().getData();

                            Log.e("Data", String.valueOf(databean));

                            List = response.body().getData().getJob_date();
                            Log.e("Month List",List);
                            statustype = response.body().getData().getJob_status_type();
                            Log.e("Status Type",statustype);
                            value_s = response.body().getData().getMr_status();
                            s_mr1 = response.body().getData().getMr_1();
                            s_mr2 = response.body().getData().getMr_2();
                            s_mr3 = response.body().getData().getMr_3();
                            s_mr4 = response.body().getData().getMr_4();
                            s_mr5 = response.body().getData().getMr_5();
                            s_mr6 = response.body().getData().getMr_6();
                            s_mr7 = response.body().getData().getMr_7();
                            s_mr8 = response.body().getData().getMr_8();
                            s_mr9 = response.body().getData().getMr_9();
                            s_mr10 = response.body().getData().getMr_10();

                            Log.e("MR 1" , "" +s_mr1);
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

                            servicedetailsbean = response.body().getData().getField_value_data();

                            if (servicedetailsbean.isEmpty()){

                            }
                            else{
                                Log.e("Check List", "" + servicedetailsbean.size());

                                for(int i=0;i<servicedetailsbean.size();i++){

                                    Form1_cat_id = servicedetailsbean.get(i).getField_cat_id();
                                    Form1_group_id = servicedetailsbean.get(i).getField_group_id();
                                    form1_comments = servicedetailsbean.get(i).getField_comments();
                                    form1_name = servicedetailsbean.get(i).getField_name();
                                    form1_value = servicedetailsbean.get(i).getField_value();
                                    Log.e("A", "" + Form1_cat_id);
                                    Log.e("B", "" + Form1_group_id);
                                    Log.e("c", "" + form1_comments);
                                    Log.e("d", "" + form1_name);
                                    Log.e("e", "" + form1_value);
                                }
                            }

                        }

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
        Job_status_updateRequest custom = new Job_status_updateRequest();
        custom.setUser_mobile_no(se_user_mobile_no);
        custom.setJob_id(job_id);
        custom.setSMU_SCH_COMPNO(compno);
        Log.e("Request Data ",""+ new Gson().toJson(custom));
        return custom;
    }

    private void createLocalValue() {

        APIInterface apiInterface = RetrofitClient.getClient().create(APIInterface.class);
        Call<SuccessResponse> call = apiInterface.createLocalValueCallPM(RestUtils.getContentType(),createLocalRequest());
        Log.w(TAG, " Create local Data Call url  :%s" + " " + call.request().url().toString());

        call.enqueue(new Callback<SuccessResponse>() {
            @Override
            public void onResponse(Call<SuccessResponse> call, Response<SuccessResponse> response) {

                Log.w(TAG, "Create Local Response" + new Gson().toJson(response.body()));

                if (response.body() != null) {
                    message = response.body().getMessage();

                    if (200 == response.body().getCode()) {
                        if(response.body().getData() != null){

                            Log.d("msg",message);
                            Intent send = new Intent(context, ServicesActivity.class);
                            startActivity(send);

                        }


                    } else {
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

    private Preventive_Submit_Request createLocalRequest() {
        Preventive_Submit_Request localRequest = new Preventive_Submit_Request();
        localRequest.setJob_date(List);
        localRequest.setJob_id(job_id);
        localRequest.setJob_status_type(statustype);
        localRequest.setMr_status(value_s);
        localRequest.setMr_1(s_mr1);
        localRequest.setMr_2(s_mr2);
        localRequest.setMr_3(s_mr3);
        localRequest.setMr_4(s_mr4);
        localRequest.setMr_5(s_mr5);
        localRequest.setMr_6(s_mr6);
        localRequest.setMr_7(s_mr7);
        localRequest.setMr_8(s_mr8);
        localRequest.setMr_9(s_mr9);
        localRequest.setMr_10(s_mr10);
        localRequest.setPreventive_check("-");
        localRequest.setAction_req_customer("-");
        localRequest.setPm_status("-");
        localRequest.setTech_signature("-");
        localRequest.setCustomer_name("-");
        localRequest.setCustomer_number("-");
        localRequest.setCustomer_signature("-");
        localRequest.setUser_mobile_no(se_user_mobile_no);
        localRequest.setSMU_SCH_COMPNO(compno);
        localRequest.setSMU_SCH_SERTYPE(sertype);
        localRequest.setPage_number(PageNumber);
        Log.e("CompNo",""+compno);
        Log.e("SertYpe", ""+sertype);
        Log.e("JobID",""+job_id);

        java.util.List<Preventive_Submit_Request.Field_valueDatum> fielddata = new ArrayList<>();

        for(int j =0; j <mmyvalue.size(); j++){
            Preventive_Submit_Request.Field_valueDatum myfiled = new Preventive_Submit_Request.Field_valueDatum();

            myfiled.setField_value(mmyvalue.get(j));
            myfiled.setField_name(myname.get(j));
            myfiled.setField_comments(comments.get(j));
            myfiled.setField_cat_id(catid.get(j));
            myfiled.setField_group_id(groupid.get(j));

//            Log.e("Nish",""+mmyvalue.get(j) + comments.get(j));
            fielddata.add(myfiled);

        }
        localRequest.setField_valueData(fielddata);

        Log.w(TAG, " locationAddRequest" + new Gson().toJson(localRequest));
        return  localRequest;

    }

    private void getData(String job_id, String service_title) {

        Log.e("JobId",""+job_id);
        Log.e("Activity",""+service_title);

        Cursor cur = CommonUtil.dbUtil.getBreakdownMrList(job_id,service_title);

        Log.e("MRLIST" ,"" + cur.getCount());

        if (cur.getCount()>0 && cur.moveToFirst()){

            do{
                 s_mr1 = cur.getString(cur.getColumnIndexOrThrow(DbHelper.MR1));
                 s_mr2 = cur.getString(cur.getColumnIndexOrThrow(DbHelper.MR2));
                 s_mr3 = cur.getString(cur.getColumnIndexOrThrow(DbHelper.MR3));
                 s_mr4 = cur.getString(cur.getColumnIndexOrThrow(DbHelper.MR4));
                 s_mr5 = cur.getString(cur.getColumnIndexOrThrow(DbHelper.MR5));
                 s_mr6 = cur.getString(cur.getColumnIndexOrThrow(DbHelper.MR6));
                 s_mr7 = cur.getString(cur.getColumnIndexOrThrow(DbHelper.MR7));
                 s_mr8 = cur.getString(cur.getColumnIndexOrThrow(DbHelper.MR8));
                 s_mr9 = cur.getString(cur.getColumnIndexOrThrow(DbHelper.MR9));
                 s_mr10 = cur.getString(cur.getColumnIndexOrThrow(DbHelper.MR10));
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
                    s_mr8,s_mr9,s_mr10,job_id, service_title);
            Intent send = new Intent(context, PreventiveMR_Activity.class);
           // send.putExtra("status", status);
            startActivity(send);
            Cursor c = CommonUtil.dbUtil.getBreakdownMrList();
            Log.e("MRLIST" ,"" + c.getCount());
        }

    }

    @Override
    public void onBackPressed() {
      //  super.onBackPressed();

        Intent send = new Intent( Material_Request_MR_Screen_PreventiveActivity.this, Material_Request_PreventiveActivity.class);
        send.putExtra("status",status);
        startActivity(send);

    }
}