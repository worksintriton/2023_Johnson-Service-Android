package com.triton.johnson_tap_app.Service_Activity.Preventive_Services;

import static android.content.ContentValues.TAG;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
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
import com.triton.johnson_tap_app.R;
import com.triton.johnson_tap_app.RestUtils;
import com.triton.johnson_tap_app.Service_Activity.ServicesActivity;
import com.triton.johnson_tap_app.api.APIInterface;
import com.triton.johnson_tap_app.api.RetrofitClient;
import com.triton.johnson_tap_app.requestpojo.Job_status_updateRequest;
import com.triton.johnson_tap_app.requestpojo.Preventive_Submit_Request;
import com.triton.johnson_tap_app.responsepojo.Job_status_updateResponse;
import com.triton.johnson_tap_app.responsepojo.RetriveResponsePR;
import com.triton.johnson_tap_app.responsepojo.SuccessResponse;

import java.util.ArrayList;
import java.util.Arrays;

import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Preventive_action_requiredActivity extends AppCompatActivity {

    TextView text,txt;
    private Button btnSelection, btn_prev;
    ImageView iv_back,img_Paused;
    EditText feedback_remark;
    String job_id,value_s,service_title,value,s_remark,mr1,mr2,mr3,mr4,mr5,mr6,mr7,mr8,mr9,mr10,preventive_check,Form1_value,Form1_name,Form1_comments;
    String Form1_cat_id,Form1_group_id,action_req_customer="",status,se_user_mobile_no,se_id,List,statustype,compno,sertype,message,str_job_status;
    AlertDialog alertDialog;
    Context context;
    String s_mr1 ="", s_mr2 ="",s_mr3 ="",s_mr4 ="",s_mr5 ="",s_mr6 ="",s_mr7 ="",s_mr8 ="",s_mr9 ="",s_mr10 ="";
    RetriveResponsePR.Data databean ;

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
        setContentView(R.layout.activity_preventive_action_required);
        context = this;

        text = findViewById(R.id.text);
        txt = findViewById(R.id.txt);
        btnSelection = (Button) findViewById(R.id.btn_next);
      //  btn_prev = (Button) findViewById(R.id.btn_show);
        iv_back = (ImageView) findViewById(R.id.iv_back);
        feedback_remark = (EditText)findViewById(R.id.feedback_remark);
        img_Paused = findViewById(R.id.img_paused);

        Spannable name_Upload = new SpannableString("Action Required By Customer ");
        name_Upload.setSpan(new ForegroundColorSpan(Preventive_action_requiredActivity.this.getResources().getColor(R.color.colorAccent)), 0, name_Upload.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        text.setText(name_Upload);
        Spannable name_Upload1 = new SpannableString("*");
        name_Upload1.setSpan(new ForegroundColorSpan(Color.RED), 0, name_Upload1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        text.append(name_Upload1);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
          //  value_s = extras.getString("valuess");
            status = extras.getString("status");
            Log.e("Status",""+status);
        }
        if (extras != null) {
         //   job_id = extras.getString("job_id");
        }

        if (extras != null) {
         //   value = extras.getString("value");
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
//            preventive_check = extras.getString("preventive_check");
//            Log.e("My Checklist","" + preventive_check);
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
            action_req_customer = extras.getString("action_req_customer");
            feedback_remark.setText(action_req_customer);
        }

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        se_id = sharedPreferences.getString("_id", "default value");
        se_user_mobile_no = sharedPreferences.getString("user_mobile_no", "default value");
        service_title = sharedPreferences.getString("service_title", "default value");
        job_id = sharedPreferences.getString("job_id","L1234");
        List = sharedPreferences.getString("List","1");
        Log.e("Month List", "" +List);
        statustype = sharedPreferences.getString("statustype","OD");
        Log.e("ValueB", statustype);
        compno = sharedPreferences.getString("compno","123");
        sertype = sharedPreferences.getString("sertype","123");
        preventive_check = sharedPreferences.getString("PreventiveChecklist","bbb");
        Log.e("Preventive Check",preventive_check);


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

            getFeedback();

            getData(job_id,service_title);

        }else{

            retriveLocalvalue();
        }

        feedback_remark.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @SuppressLint("SetTextI18n")
            public void afterTextChanged(Editable s) {
                txt.setText(s.toString().length() + "/250");

            }
        });

        img_Paused.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                alertDialog = new AlertDialog.Builder(context)
                        .setTitle("Are you sure to pause this job ?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialogInterface, int i) {

                                s_remark = feedback_remark.getText().toString();
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

                s_remark = feedback_remark.getText().toString();

                if (s_remark.equals("")) {
                    feedback_remark.setError("Please Enter the Feedback Remark");
                }
                else {
                    CommonUtil.dbUtil.addFeedback(job_id,service_title,s_remark,"4");
                    Cursor cur = CommonUtil.dbUtil.getFeedback(job_id,service_title,"4");
                    Log.e("Feedback Count",""+cur.getCount());
                    Intent send = new Intent(Preventive_action_requiredActivity.this, Preventive_StatusActivity.class);
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
                    send.putExtra("preventive_check",preventive_check);
                    send.putExtra("action_req_customer",s_remark);
                    send.putExtra("Form1_value", Form1_value);
                    send.putExtra("Form1_name",Form1_name);
                    send.putExtra("Form1_comments",Form1_comments);
                    send.putExtra("Form1_cat_id",Form1_cat_id);
                    send.putExtra("Form1_group_id",Form1_group_id);
                    send.putExtra("status",status);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("feedbackremark", s_remark);
                    editor.apply();
                    startActivity(send);
                }

            }
        });

        iv_back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
//                Intent send = new Intent( Preventive_action_requiredActivity.this, Preventive_checklistActivity.class);
//                send.putExtra("valuess",value_s);
//                send.putExtra("job_id",job_id);
//                send.putExtra("value",value);
//                send.putExtra("service_title",service_title);
//                send.putExtra("mr1", mr1);
//                send.putExtra("mr2", mr2);
//                send.putExtra("mr3", mr3);
//                send.putExtra("mr4", mr4);
//                send.putExtra("mr5", mr5);
//                send.putExtra("mr6", mr6);
//                send.putExtra("mr7", mr7);
//                send.putExtra("mr8", mr8);
//                send.putExtra("mr9", mr9);
//                send.putExtra("mr10", mr10);
//                send.putExtra("preventive_check",preventive_check);
//                send.putExtra("Form1_value", Form1_value);
//                send.putExtra("Form1_name",Form1_name);
//                send.putExtra("Form1_comments",Form1_comments);
//                send.putExtra("Form1_cat_id",Form1_cat_id);
//                send.putExtra("Form1_group_id",Form1_group_id);
//                startActivity(send);
                onBackPressed();
            }
        });
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

                            s_remark = response.body().getData().getAction_req_customer();
                            feedback_remark.setText(s_remark);

                          //  List = response.body().getData().getJob_date();

                          //  Log.e("Job Date","",List);
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

        Log.e( "before ", preventive_check);
        preventive_check  = preventive_check.replaceAll("\n", "").replaceAll("","");
        Log.e( "after ", preventive_check);

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
        localRequest.setPreventive_check(preventive_check);
        localRequest.setAction_req_customer(s_remark);
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

    @SuppressLint("Range")
    private void getFeedback() {

        Cursor cur = CommonUtil.dbUtil.getFeedback(job_id,service_title,"4");

        Log.e("GET FEEDBACK ",""+cur.getCount());

        if (cur.getCount()>0 && cur.moveToLast()){


            s_remark= cur.getString(cur.getColumnIndex(DbHelper.FEEDBACK_REMARKS));
            Log.e("Remarks",""+s_remark);
            feedback_remark.setText(s_remark);

        }

    }

    @Override
    public void onBackPressed() {
       // super.onBackPressed();
        Intent send = new Intent( Preventive_action_requiredActivity.this, Preventive_checklistActivity.class);
        send.putExtra("status",status);
        startActivity(send);
    }
}