package com.triton.johnson_tap_app.Service_Activity.Preventive_Services;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
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
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.triton.johnson_tap_app.Db.CommonUtil;
import com.triton.johnson_tap_app.Db.DbHelper;
import com.triton.johnson_tap_app.Db.DbUtil;
import com.triton.johnson_tap_app.R;
import com.triton.johnson_tap_app.RestUtils;
import com.triton.johnson_tap_app.Service_Activity.ServicesActivity;
import com.triton.johnson_tap_app.api.APIInterface;
import com.triton.johnson_tap_app.api.RetrofitClient;
import com.triton.johnson_tap_app.requestpojo.Job_status_updateRequest;
import com.triton.johnson_tap_app.requestpojo.Preventive_Submit_Request;
import com.triton.johnson_tap_app.responsepojo.Job_status_updateResponse;
import com.triton.johnson_tap_app.responsepojo.SuccessResponse;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ESCTRV extends AppCompatActivity implements JobDateListener {

    ImageView iv_Back,img_Pause;
    RecyclerView recyclerView;
    Button btn_Next;
    String str_job_id,service_title,value,se_id,se_user_mobile_no,compno,sertype,pre_check,status,message,str_job_status;
    Context context;
    ArrayList<String> arli_Month = new ArrayList<String>();
    ArrayList<String> arli_ID = new ArrayList<String>();
    ESCTRV_adapter petBreedTypesListAdapter;
    AlertDialog alertDialog;
    ArrayList<String> mydata = new ArrayList<>();
    String statustype,TAG = "ESCTRV";
    SharedPreferences sharedPreferences;
    List FieldData = Collections.singletonList("-");
    TextView txt_Jobid,txt_Starttime;
    String str_StartTime;
    int PageNumber = 1;

    @SuppressLint({"MissingInflatedId", "SetTextI18n"})
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_esc_trv);
        context = this;

        CommonUtil.dbUtil = new DbUtil(context);
        CommonUtil.dbUtil.open();
        CommonUtil.dbHelper = new DbHelper(context);

       // CommonUtil.dbUtil.reportDeletePreventiveListDelete();
//        CommonUtil.dbUtil.getPreventiveListDelete();

        recyclerView = findViewById(R.id.recyclerView);
        iv_Back = findViewById(R.id.iv_back);
        img_Pause = findViewById(R.id.img_paused);
        btn_Next = findViewById(R.id.btn_next);
        txt_Starttime = findViewById(R.id.txt_starttime);
        txt_Jobid = findViewById(R.id.txt_jobid);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        se_id = sharedPreferences.getString("_id", "default value");
        se_user_mobile_no = sharedPreferences.getString("user_mobile_no", "default value");
        service_title = sharedPreferences.getString("service_title", "default value");
        str_job_id = sharedPreferences.getString("job_id","L1234");
        statustype = sharedPreferences.getString("statustype","OD");
        Log.e("Value", statustype);

        str_StartTime = sharedPreferences.getString("starttime","");
        str_StartTime = str_StartTime.replaceAll("[^0-9-:]", " ");
        Log.e("Start Time",str_StartTime);
        txt_Jobid.setText("Job ID : " + str_job_id);
        txt_Starttime.setText("Start Time : " + str_StartTime);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
           // str_job_id = extras.getString("job_id");
         //   service_title = extras.getString("service_title");
           // value = extras.getString("value");
            status = extras.getString("status");
            Log.e("Status",""+status);
        }

//        CommonUtil.dbUtil.reportDeletePreventiveListDelete(str_job_id,service_title);
//        Cursor curs = CommonUtil.dbUtil.getCheckList(str_job_id,service_title);
//        Log.e("Checklist Delete",""+curs.getCount());


        arli_Month.add("MONTHLY");
        arli_Month.add("QUARTERLY");
        arli_Month.add("HALF YEARLY");
        arli_Month.add("ANNUALLY");

        arli_ID.add("1");
        arli_ID.add("2");
        arli_ID.add("3");
        arli_ID.add("4");



        Cursor cur = CommonUtil.dbUtil.getMonthlist(str_job_id,service_title, "1");
        Log.e("Checklist",""+cur.getCount());
        if(cur.getCount() >0 && cur.moveToFirst()){

            do{
                @SuppressLint("Range")
                String abc = cur.getString(cur.getColumnIndex(DbHelper.MONTH));
                Log.e("Datas",""+abc);
                mydata.add(abc);
            }while (cur.moveToNext());

        }

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        petBreedTypesListAdapter = new ESCTRV_adapter(arli_Month,this, (JobDateListener) this,mydata,arli_ID);
        recyclerView.setAdapter(petBreedTypesListAdapter);


        btn_Next.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                getPreventivecheck();

                Cursor cur = CommonUtil.dbUtil.getMonthlist(str_job_id,service_title, "1");
                Log.e("Checklist",""+cur.getCount());

                if (cur.getCount() == 0){
                    alertDialog = new AlertDialog.Builder(context)
                            .setTitle("Please Select Value")
                            .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    alertDialog.dismiss();
                                }
                            })
                            .show();
                }
                else{

                    Intent intent = new Intent(context, Recycler_SpinnerActivity.class);
//                Bundle args = new Bundle();
//                args.putSerializable("ARRAYLIST",(Serializable)arrayList);
//                intent.putExtra("BUNDLE",args);
                    intent.putExtra("job_id",str_job_id);
                    intent.putExtra("value",value);
                    intent.putExtra("service_title",service_title);
                    intent.putExtra("way","ESC");
                    intent.putExtra("status",status);
                    startActivity(intent);
                }

            }
        });

        iv_Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(context, Start_Job_Text_PreventiveActivity.class);
//                intent.putExtra("job_id",str_job_id);
//                intent.putExtra("value",value);
//                intent.putExtra("service_title",service_title);
//                startActivity(intent);
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
                                getPreventivecheck();
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
        custom.setJob_id(str_job_id);
        custom.setStatus(str_job_status);
        custom.setSMU_SCH_COMPNO(compno);
        custom.setSMU_SCH_SERTYPE(sertype);
        Log.e("CompNo",""+compno);
        Log.e("SertYpe", ""+sertype);
        Log.w(TAG,"Job Status Update Request "+ new Gson().toJson(custom));
        return custom;
    }

    private void createLocalValue() {

        APIInterface apiInterface = RetrofitClient.getClient().create(APIInterface.class);
        Call<SuccessResponse> call = apiInterface.createLocalValueCallPM(RestUtils.getContentType(),createLocalRequest());
        Log.w(TAG, "Create local Data Call url  :%s" + " " + call.request().url().toString());

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

            }
        });

    }

    private Preventive_Submit_Request createLocalRequest() {

        FieldData = new ArrayList();
        Preventive_Submit_Request localRequest = new Preventive_Submit_Request();
        localRequest.setJob_date(String.valueOf(mydata));
        localRequest.setJob_id(str_job_id);
        localRequest.setJob_status_type(statustype);
        localRequest.setMr_status("-");
        localRequest.setMr_1("-");
        localRequest.setMr_2("-");
        localRequest.setMr_3("-");
        localRequest.setMr_4("-");
        localRequest.setMr_5("-");
        localRequest.setMr_6("-");
        localRequest.setMr_7("-");
        localRequest.setMr_8("-");
        localRequest.setMr_9("-");
        localRequest.setMr_10("-");
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
        Log.e("CompNo",""+compno);
        Log.e("SertYpe", ""+sertype);
        Log.e("JobID",""+str_job_id);
        // Log.e("Nish List",""+dataBeanList.size());
        localRequest.setField_valueData(FieldData);

        Log.w(TAG, "Create Local Request" + new Gson().toJson(localRequest));
        return  localRequest;
    }

    private void getPreventivecheck() {

        Cursor cur = CommonUtil.dbUtil.getMonthlist(str_job_id,service_title, "1");
        Log.e("Checklist",""+cur.getCount());
        mydata = new ArrayList<>();
        if(cur.getCount() >0 && cur.moveToFirst()){

            do{
                @SuppressLint("Range")
                String abc = cur.getString(cur.getColumnIndex(DbHelper.MONTH));
                Log.e("Datas",""+abc);
                mydata.add(abc);
            }while (cur.moveToNext());

        } else {

            Log.e("Datasss",""+cur);

        }

        ArrayList<String> outputList = new ArrayList<String>();
        for (String item: mydata) {
            //outputList.add("\""+item+"\"");
            outputList.add(""+item+"");
        }
        pre_check = String.valueOf(outputList);
        pre_check = pre_check.replace("ANNUALLY","YEARLY");
        //   pre_check = pre_check.replaceAll("\\[", "").replaceAll("\\]","");
        //  System.out.println("EEEEEEEEEEE"+ddd);

        Log.e("Month List", pre_check);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("List", pre_check);
        editor.apply();
    }

    @Override
    public void onBackPressed() {
//        Intent intent = new Intent(context, Start_Job_Text_PreventiveActivity.class);
//        intent.putExtra("job_id",str_job_id);
//        intent.putExtra("value",value);
//        intent.putExtra("service_title",service_title);
//        startActivity(intent);

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

    @Override
    public void onMonthchange(ArrayList<String> arrayList) {
        //Toast.makeText(context, arrayList.toString(), Toast.LENGTH_SHORT).show();
    }
}
