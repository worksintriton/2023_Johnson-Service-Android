package com.triton.johnson_tap_app.Service_Activity.Breakdown_Services;

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
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.triton.johnson_tap_app.Db.CommonUtil;
import com.triton.johnson_tap_app.Db.DbHelper;
import com.triton.johnson_tap_app.R;
import com.triton.johnson_tap_app.Service_Activity.PreventiveMRApproval.PreventiveMR_Activity;
import com.triton.johnson_tap_app.api.APIInterface;
import com.triton.johnson_tap_app.api.RetrofitClient;
import com.triton.johnson_tap_app.requestpojo.Job_status_updateRequest;
import com.triton.johnson_tap_app.responsepojo.RetriveLocalValueBRResponse;
import com.triton.johnson_tap_app.utils.RestUtils;

import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Material_Request_MR_ScreenActivity extends AppCompatActivity {

    private Button btnSelection,btn_prev;
    String value,job_id,feedback_group,feedback_details,bd_dta,feedback_remark,str_mr1 ="",str_mr2="",str_mr3="",str_mr4="",str_mr5="",str_mr6="",str_mr7="",str_mr8="",str_mr9="",str_mr10="";
    ImageView iv_back;
    EditText mr1;
    EditText mr2;
    EditText mr3;
    EditText mr4;
    EditText mr5;
    EditText mr6;
    EditText mr7;
    EditText mr8;
    EditText mr9;
    EditText mr10;
    String contract_status,service_title;
    SharedPreferences sharedPreferences;
    AlertDialog alertDialog;
    Context context;
    String TAG = "MATERIAL MR SCREEN",message,se_id,se_user_mobile_no,se_user_name,compno,sertype;
    String s_mr1 ="", s_mr2 ="",s_mr3 ="",s_mr4 ="",s_mr5 ="",s_mr6 ="",s_mr7 ="",s_mr8 ="",s_mr9 ="",s_mr10 ="",status,Value;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_material_request_mr_screen);
        context = this;

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

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
         //   value = extras.getString("value");
          //  Log.e("Value",""+value);
            status = extras.getString("status");
            Log.e("Status",status);
        }

        if (extras != null) {
          //  feedback_group = extras.getString("feedback_group");
        }

        if (extras != null) {
         //   bd_dta = extras.getString("bd_details");
        }

        if (extras != null) {
          //  job_id = extras.getString("job_id");
        }

        if (extras != null) {
            //feedback_details = extras.getString("feedback_details");
        }
        if (extras != null) {
          //  feedback_remark = extras.getString("feedback_remark");
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
//        service_title = sharedPreferences.getString("service_title","Preventive Maintenance");
//        Log.e("Name",""+service_title);
        contract_status = sharedPreferences.getString("contract_status", "default value");
        Log.e("Contract",""+contract_status);
        se_id = sharedPreferences.getString("_id", "default value");
        se_user_mobile_no = sharedPreferences.getString("user_mobile_no", "default value");
        se_user_name = sharedPreferences.getString("user_name", "default value");
        service_title = sharedPreferences.getString("service_title", "default value");
        job_id = sharedPreferences.getString("job_id","L1234");
        Value = sharedPreferences.getString("value","default value");
        compno = sharedPreferences.getString("compno","123");
        sertype = sharedPreferences.getString("sertype","123");
        Log.e("Name",service_title);
        Log.e("JobID",job_id);
        Log.e("Value",Value);


        if (contract_status.equals("GRACE")){

            alertDialog = new AlertDialog.Builder(context)
                    .setTitle("Job is under Grace Period")
                    .setMessage("While raising MR\n" +
                            "Job is under Grace period, check with your Engineer for MR")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialogInterface, int i) {
                            alertDialog.dismiss();
//                                                Intent send = new Intent(context, ServicesActivity.class);
//                                                startActivity(send);
                        }
                    })
//                                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
//                                            public void onClick(DialogInterface dialogInterface, int i) {
//                                                alertDialog.dismiss();
//                                            }
//                                        })
                    .show();
        }

        if (status.equals("paused")){
            retrive_LocalValue();
        }
        else{
            getData(job_id,service_title);
        }

        btn_prev.setBackgroundResource(R.drawable.blue_button_background_with_radius);
        btn_prev.setTextColor(getResources().getColor(R.color.white));
        btn_prev.setEnabled(true);

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
                    Intent send = new Intent(Material_Request_MR_ScreenActivity.this, BD_StatusActivity.class);
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
                    send.putExtra("status",status);
                    startActivity(send);
                }

            }
        });

        btn_prev.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                onBackPressed();

//                Intent send = new Intent( Material_Request_MR_ScreenActivity.this, Material_RequestActivity.class);
//                send.putExtra("value","yes");
//                send.putExtra("feedback_details",feedback_details);
//                send.putExtra("feedback_group",feedback_group);
//                send.putExtra("bd_details",bd_dta);
//                send.putExtra("job_id",job_id);
//                send.putExtra("feedback_remark", feedback_remark);
//                startActivity(send);
            }
        });

        iv_back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                onBackPressed();

//                Intent send = new Intent( Material_Request_MR_ScreenActivity.this, Material_RequestActivity.class);
//                send.putExtra("value","yes");
//                send.putExtra("feedback_details",feedback_details);
//                send.putExtra("feedback_group",feedback_group);
//                send.putExtra("bd_details",bd_dta);
//                send.putExtra("job_id",job_id);
//                send.putExtra("feedback_remark", feedback_remark);
//                startActivity(send);
            }
        });

    }

    private void retrive_LocalValue() {

        APIInterface apiInterface =  RetrofitClient.getClient().create((APIInterface.class));
        Call<RetriveLocalValueBRResponse> call = apiInterface.retriveLocalValueBRCall(RestUtils.getContentType(),localRequest());
        Log.w(TAG,"Retrive Local Value url  :%s"+" "+ call.request().url().toString());

        call.enqueue(new Callback<RetriveLocalValueBRResponse>() {
            @Override
            public void onResponse(Call<RetriveLocalValueBRResponse> call, Response<RetriveLocalValueBRResponse> response) {

                Log.e(TAG,"Retrive Response" + new Gson().toJson(response.body()));

                if (response.body() != null){

                    message = response.body().getMessage();

                    if (response.body().getCode() == 200){

                        if (response.body().getData() != null){
                            Log.d("msg",message);

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
                            String techsign = response.body().getData().getTech_signature();
                            Log.e("Sign",""+techsign);

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

                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString("tech_sign", techsign);
                            editor.apply();



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
        custom.setJob_id(job_id);
        custom.setSMU_SCH_COMPNO(compno);
        //  custom.setSMU_SCH_SERTYPE(sertype);
        Log.w(TAG,"Request Data "+ new Gson().toJson(custom));
        return custom;
    }

    private void getData(String job_id, String service_title) {

        Log.e("JobId",""+job_id);
        Log.e("Activity",""+service_title);


        Cursor cur = CommonUtil.dbUtil.getBreakdownMrList(job_id,service_title);

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

   //    super.onBackPressed();
        Intent intent = new Intent(context,Material_RequestActivity.class);
        intent.putExtra("status",status);
        startActivity(intent);
    }
}