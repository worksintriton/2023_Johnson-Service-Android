package com.triton.johnson_tap_app.Service_Activity.Preventive_Services;

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
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.triton.johnson_tap_app.Db.CommonUtil;
import com.triton.johnson_tap_app.Db.DbHelper;
import com.triton.johnson_tap_app.Db.DbUtil;
import com.triton.johnson_tap_app.R;
import com.triton.johnson_tap_app.RestUtils;
import com.triton.johnson_tap_app.Service_Activity.ServicesActivity;
import com.triton.johnson_tap_app.Service_Adapter.Preventive_ChecklistAdapter;
import com.triton.johnson_tap_app.UserTypeSelectListener1;
import com.triton.johnson_tap_app.api.APIInterface;
import com.triton.johnson_tap_app.api.RetrofitClient;
import com.triton.johnson_tap_app.requestpojo.Job_status_updateRequest;
import com.triton.johnson_tap_app.requestpojo.Preventive_ChecklistRequest;
import com.triton.johnson_tap_app.requestpojo.Preventive_Submit_Request;
import com.triton.johnson_tap_app.responsepojo.Job_status_updateResponse;
import com.triton.johnson_tap_app.responsepojo.Preventive_ChecklistResponse;
import com.triton.johnson_tap_app.responsepojo.SuccessResponse;
import com.triton.johnson_tap_app.utils.ConnectionDetector;

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

public class Preventive_checklistActivity extends AppCompatActivity implements UserTypeSelectListener1{

    private Button btnSelection;
    private RecyclerView recyclerView;
    ImageView iv_back,img_Paused;
    List<Preventive_ChecklistResponse.DataBean> breedTypedataBeanList;
    Preventive_ChecklistAdapter activityBasedListAdapter;
    private String PetBreedType = "",hellWrld,str,pre_check;
    String message,se_user_mobile_no,se_user_name, se_id,service_title,str_job_id,data = "",value_s="",job_id,value,mr1,mr2,mr3,mr4,mr5,mr6,mr7,mr8,mr9,mr10,Form1_value,Form1_name,Form1_comments;
    private String Title,petimage,Form1_cat_id,Form1_group_id,jobid,status,str_job_status;
    AlertDialog alertDialog;
    ProgressDialog progressDialog;
    Context context;
    ArrayList<String> mydata = new ArrayList<>();
    SharedPreferences sharedPreferences;
    String s_mr1 ="", s_mr2 ="",s_mr3 ="",s_mr4 ="",s_mr5 ="",s_mr6 ="",s_mr7 ="",s_mr8 ="",s_mr9 ="",s_mr10 ="",List,statustype,compno,sertype,networkStatus="";
    TextView txt_Jobid,txt_Starttime;
    String str_StartTime;
    double Latitude ,Logitude;
    String address = "";

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
        setContentView(R.layout.activity_preventive_checklist);
        context = this;

        CommonUtil.dbUtil = new DbUtil(context);
        CommonUtil.dbUtil.open();
        CommonUtil.dbHelper = new DbHelper(context);

//        CommonUtil.dbUtil.reportDeletePreventiveListDelete();
       // CommonUtil.dbUtil.getPreventiveListDelete();

        btnSelection = (Button) findViewById(R.id.btn_next);
        iv_back = (ImageView) findViewById(R.id.iv_back);
        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        img_Paused = (ImageView) findViewById(R.id.img_paused);
        txt_Starttime = findViewById(R.id.txt_starttime);
        txt_Jobid = findViewById(R.id.txt_jobid);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
           // value_s = extras.getString("valuess");
            status = extras.getString("status");
            Log.e("Status",""+status);
        }
        if (extras != null) {
//            job_id = extras.getString("job_id");
//            Log.e("Job ID", ""+ job_id);
        }

        if (extras != null) {
        //    value = extras.getString("value");
        }

        if (extras != null) {
         //   service_title = extras.getString("service_title");
        }

        if (extras != null) {
            mr1 = extras.getString("mr1");
        }
        if (extras != null) {
            mr2 = extras.getString("mr2");
        }
        if (extras != null) {
            mr3 = extras.getString("mr3");
        }
        if (extras != null) {
            mr4 = extras.getString("mr4");
        }
        if (extras != null) {
            mr5 = extras.getString("mr5");
        }
        if (extras != null) {
            mr6 = extras.getString("mr6");
        }
        if (extras != null) {
            mr7 = extras.getString("mr7");
        }
        if (extras != null) {
            mr8 = extras.getString("mr8");
        }
        if (extras != null) {
            mr9 = extras.getString("mr9");
        }
        if (extras != null) {
            mr10 = extras.getString("mr10");
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

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        se_id = sharedPreferences.getString("_id", "default value");
        se_user_mobile_no = sharedPreferences.getString("user_mobile_no", "default value");
        se_user_name = sharedPreferences.getString("user_name", "default value");
        List = sharedPreferences.getString("List","1");
       // service_title = sharedPreferences.getString("service_title", "default value");
        jobid = sharedPreferences.getString("job_id", "default value");
        service_title = sharedPreferences.getString("service_title", "default value");
        Log.e("JobID",""+jobid);
        Log.e("Name",""+service_title);
        Log.e("Month List", "" +List);
        statustype = sharedPreferences.getString("statustype","OD");
        Log.e("ValueB", statustype);
        value_s = sharedPreferences.getString("value","default value");
        Log.e("ValueA",value_s);
        compno = sharedPreferences.getString("compno","123");
        sertype = sharedPreferences.getString("sertype","123");

        str_StartTime = sharedPreferences.getString("starttime","");
        str_StartTime = str_StartTime.replaceAll("[^0-9-:]", " ");
        Log.e("Start Time",str_StartTime);
        txt_Jobid.setText("Job ID : " + jobid);
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

//        CommonUtil.dbUtil.reportDeletePreventiveListDelete(job_id,service_title);
//        Cursor cur = CommonUtil.dbUtil.getCheckList(jobid,service_title);
//        Log.e("Checklist Delete",""+cur.getCount());


       // if(breedTypedataBeanList.size() <0){



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

        networkStatus = ConnectionDetector.getConnectivityStatusString(getApplicationContext());

        Log.e("Network",""+networkStatus);
        if (networkStatus.equalsIgnoreCase("Not connected to Internet")) {

            Toast.makeText(context,"No Internet Connection",Toast.LENGTH_LONG).show();

        }
        else{
            jobFindResponseCall();
        }

        getData(jobid,service_title);

        img_Paused.setOnClickListener(new View.OnClickListener() {
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
                                getPreventiveCheck();
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



        btnSelection.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
//                for (int i = 0; i < breedTypedataBeanList.size(); i++) {
//                    Preventive_ChecklistResponse.DataBean singleStudent = breedTypedataBeanList.get(i);
//                    if (singleStudent.isSelected()) {
//                        data = data + "," + breedTypedataBeanList.get(i).getCheck_list_value().toString();
//
//                        Log.e("Nish Data",""+ data);
//
//                        hellWrld = data.replace("null","");
//                        str = hellWrld;
//                        str = str.substring(1);
//
//                        Log.e("Nish Data1",""+ str);
//                       // Toasty.warning(getApplicationContext(), "" + str, Toasty.LENGTH_LONG).show();
////                        CommonUtil.dbUtil.addCheckList(jobid,service_title,data);
////                        Cursor cur = CommonUtil.dbUtil.getCheckList(jobid,service_title);
////                        Log.e("COunt",""+cur.getCount());
//                    }
//                }
//
//
//
//                ArrayList<String> list= new ArrayList<String>(Arrays.asList(str.split(",")));
//                System.out.println(list);
                getPreventiveCheck();

                Cursor cur = CommonUtil.dbUtil.getCheckList(jobid,service_title, "2");
                Log.e("Checklist get",""+cur.getCount());

//                if(mydata.equals("")){
               if(cur.getCount() ==0) {

                    alertDialog = new AlertDialog.Builder(Preventive_checklistActivity.this)
                            .setTitle("Please Selected Value")
                            .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    alertDialog.dismiss();
                                }
                            })
                            .show();
                }
                else {

                    Intent send = new Intent(Preventive_checklistActivity.this, Preventive_action_requiredActivity.class);
                    send.putExtra("valuess",value_s);
                    send.putExtra("job_id",job_id);
                    send.putExtra("value",value);
                    send.putExtra("service_title",service_title);
                    send.putExtra("mr1", mr1);
                    send.putExtra("mr2", mr2);
                    send.putExtra("mr3", mr3);
                    send.putExtra("mr4", mr4);
                    send.putExtra("mr5", mr5);
                    send.putExtra("mr6", mr6);
                    send.putExtra("mr7", mr7);
                    send.putExtra("mr8", mr8);
                    send.putExtra("mr9", mr9);
                    send.putExtra("mr10", mr10);
                    send.putExtra("preventive_check",pre_check);
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
//                if(value_s.equals("yes")){
//                    Intent send = new Intent(Preventive_checklistActivity.this, Material_Request_MR_Screen_PreventiveActivity.class);
//                    send.putExtra("valuess","yes");
//                    send.putExtra("job_id",job_id);
//                    send.putExtra("value",value);
//                    send.putExtra("service_title",service_title);
//                    send.putExtra("mr1", mr1);
//                    send.putExtra("mr2", mr2);
//                    send.putExtra("mr3", mr3);
//                    send.putExtra("mr4", mr4);
//                    send.putExtra("mr5", mr5);
//                    send.putExtra("mr6", mr6);
//                    send.putExtra("mr7", mr7);
//                    send.putExtra("mr8", mr8);
//                    send.putExtra("mr9", mr9);
//                    send.putExtra("mr10", mr10);
//                    send.putExtra("Form1_value", Form1_value);
//                    send.putExtra("Form1_name",Form1_name);
//                    send.putExtra("Form1_comments",Form1_comments);
//                    send.putExtra("Form1_cat_id",Form1_cat_id);
//                    send.putExtra("Form1_group_id",Form1_group_id);
//                    startActivity(send);
//                }
//                else {
//                    Intent send = new Intent(Preventive_checklistActivity.this, Material_Request_PreventiveActivity.class);
//                    send.putExtra("valuess","no");
//                    send.putExtra("job_id",job_id);
//                    send.putExtra("value",value);
//                    send.putExtra("service_title",service_title);
//                    send.putExtra("mr1", mr1);
//                    send.putExtra("mr2", mr2);
//                    send.putExtra("mr3", mr3);
//                    send.putExtra("mr4", mr4);
//                    send.putExtra("mr5", mr5);
//                    send.putExtra("mr6", mr6);
//                    send.putExtra("mr7", mr7);
//                    send.putExtra("mr8", mr8);
//                    send.putExtra("mr9", mr9);
//                    send.putExtra("mr10", mr10);
//                    send.putExtra("Form1_value", Form1_value);
//                    send.putExtra("Form1_name",Form1_name);
//                    send.putExtra("Form1_comments",Form1_comments);
//                    send.putExtra("Form1_cat_id",Form1_cat_id);
//                    send.putExtra("Form1_group_id",Form1_group_id);
//                    startActivity(send);
//                }
                onBackPressed();
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
        custom.setJob_id(str_job_id);
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
            }while (cur.moveToNext());
        }
        cur.close();
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

        Log.e( "before ", pre_check);
        pre_check  = pre_check.replaceAll("\n", "").replaceAll("","");
        Log.e( "after ", pre_check);

        Preventive_Submit_Request localRequest = new Preventive_Submit_Request();
        localRequest.setJob_date(List);
        localRequest.setJob_id(jobid);
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
        localRequest.setPreventive_check(pre_check);
        localRequest.setAction_req_customer("-");
        localRequest.setPm_status("-");
        localRequest.setTech_signature("-");
        localRequest.setCustomer_name("-");
        localRequest.setCustomer_number("-");
        localRequest.setCustomer_signature("-");
        localRequest.setUser_mobile_no(se_user_mobile_no);
        localRequest.setSMU_SCH_COMPNO(compno);
        localRequest.setSMU_SCH_SERTYPE(sertype);
        Log.e("CompNo",""+compno);
        Log.e("SertYpe", ""+sertype);
        Log.e("JobID",""+jobid);

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


    private void getPreventiveCheck() {

        Cursor cur = CommonUtil.dbUtil.getCheckList(jobid,service_title, "2");
        Log.e("Checklist get",""+cur.getCount());
        mydata = new ArrayList<>();
        if(cur.getCount() >0 && cur.moveToFirst()){

            do{
                @SuppressLint("Range")
                String abc = cur.getString(cur.getColumnIndex(DbHelper.PREVENTIVE_CHECKLIST));
                Log.e("Datas",""+abc);
                mydata.add(abc);
            }while (cur.moveToNext());

        } else{
            Log.e("Datasss",""+cur);

        }

        ArrayList<String> outputList = new ArrayList<String>();
        for (String item: mydata) {
//            outputList.add("\""+item+"\"");
            outputList.add(""+item+"");
        }
        pre_check = String.valueOf(outputList);
        //   pre_check = pre_check.replaceAll("\\[", "").replaceAll("\\]","");
        //  System.out.println("EEEEEEEEEEE"+ddd);

        Log.e("Preventive Checklist", String.valueOf(outputList));
        Log.e("Preventive Checklist", pre_check);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("PreventiveChecklist", pre_check);
        editor.apply();

    }

    private void jobFindResponseCall() {
        APIInterface apiInterface = RetrofitClient.getClient().create(APIInterface.class);
        Call<Preventive_ChecklistResponse> call = apiInterface.Preventive_ChecklistResponseCall(RestUtils.getContentType(), serviceRequest());
        Log.w(TAG, "Jobno Find Response url  :%s" + " " + call.request().url().toString());
        call.enqueue(new Callback<Preventive_ChecklistResponse>() {
            @SuppressLint("LogNotTimber")
            @Override
            public void onResponse(@NonNull Call<Preventive_ChecklistResponse> call, @NonNull Response<Preventive_ChecklistResponse> response) {
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
            public void onFailure(@NonNull Call<Preventive_ChecklistResponse> call, @NonNull Throwable t) {
                Log.e("Jobno Find ", "--->" + t.getMessage());
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private Preventive_ChecklistRequest serviceRequest() {
        Preventive_ChecklistRequest service = new Preventive_ChecklistRequest();
        service.setJob_id(jobid);
        Log.w(TAG, "Jobno Find Request " + new Gson().toJson(service));
        return service;
    }

    private void setView(List<Preventive_ChecklistResponse.DataBean> dataBeanList) {

      Cursor cur = CommonUtil.dbUtil.getCheckList(jobid,service_title, "2");
        Log.e("Checklist",""+cur.getCount());
      if(cur.getCount() >0 && cur.moveToFirst()){

          do{
              @SuppressLint("Range")
              String abc = cur.getString(cur.getColumnIndex(DbHelper.PREVENTIVE_CHECKLIST));
              Log.e("Datas",""+abc);
              mydata.add(abc);
          }while (cur.moveToNext());

      }

        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        activityBasedListAdapter = new Preventive_ChecklistAdapter(getApplicationContext(), (ArrayList<Preventive_ChecklistResponse.DataBean>) dataBeanList,this,mydata);
        recyclerView.setAdapter(activityBasedListAdapter);
    }

    public void userTypeSelectListener1(String usertype, String usertypevalue) {
        Title = usertype;

        Log.w(TAG,"myPetsSelectListener : "+ "petList" +new Gson().toJson(breedTypedataBeanList));

            if(breedTypedataBeanList != null && breedTypedataBeanList.size()>0) {
            for (int i = 0; i < breedTypedataBeanList.size(); i++) {
                if (breedTypedataBeanList.get(i).getCheck_list_value().equalsIgnoreCase(breedTypedataBeanList.get(i).getCheck_list_value())) {
                    petimage = breedTypedataBeanList.get(i).getCheck_list_value();
                }
                Log.w(TAG, "myPetsSelectListener : " + "petimage" + petimage);

            }
        }
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();

        if (value_s.equals("yes")){
            Intent send = new Intent(Preventive_checklistActivity.this, Material_Request_MR_Screen_PreventiveActivity.class);
            send.putExtra("status",status);
            startActivity(send);
        }else{
            Intent send = new Intent(Preventive_checklistActivity.this, Material_Request_PreventiveActivity.class);
            send.putExtra("status",status);
            startActivity(send);
        }
    }
}