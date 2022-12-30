package com.triton.johnson_tap_app.Service_Activity.SiteAudit;

import static android.content.ContentValues.TAG;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.VolleyLog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.triton.johnson_tap_app.Db.CommonUtil;
import com.triton.johnson_tap_app.Db.DbHelper;
import com.triton.johnson_tap_app.Db.DbUtil;
import com.triton.johnson_tap_app.R;
import com.triton.johnson_tap_app.RestUtils;
import com.triton.johnson_tap_app.Service_Activity.PreventiveMRApproval.PreventiveMRListOne_Activity;
import com.triton.johnson_tap_app.Service_Activity.PreventiveMRApproval.PreventiveMRListtwo_Activity;
import com.triton.johnson_tap_app.Service_Activity.PreventiveMRApproval.TechnicianSignature_PreventiveMRActivity;
import com.triton.johnson_tap_app.Service_Activity.ServicesActivity;
import com.triton.johnson_tap_app.api.APIInterface;
import com.triton.johnson_tap_app.api.RetrofitClient;
import com.triton.johnson_tap_app.interfaces.QuantityListener;
import com.triton.johnson_tap_app.materialeditext.MaterialEditText;
import com.triton.johnson_tap_app.requestpojo.AuditRequest;
import com.triton.johnson_tap_app.requestpojo.Job_status_updateRequest;
import com.triton.johnson_tap_app.responsepojo.Job_status_updateResponse;
import com.triton.johnson_tap_app.responsepojo.SuccessResponse;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AuditMR_Activity extends AppCompatActivity  implements QuantityListener {

    private String TAG ="MRListONE";

    MaterialEditText partnameMaterialEdittext, partnoMaterialEdittext, quantityMaterialEdittext;
    ImageButton imgbtnSearch;
    RecyclerView recyclerView;
    ImageView iv_back,img_clearsearch,iv_pause;
    Button  submitButton, prevButton;
    FloatingActionButton addButton;
    String se_user_mobile_no, se_user_name, se_id,check_id, service_title,job_id,osacompno;
    String strPartname, strPartno,strPartid, strQuantity,status,str_mr1 ="",str_mr2="",str_mr3="",str_mr4="",str_mr5="",str_mr6="",str_mr7="",str_mr8="",str_mr9="",str_mr10="";
    private String PetBreedType = "",str_job_status="",message;
    Context context;
    AlertDialog.Builder builder;
    AlertDialog alertDialog;
    TextView txt_Jobid,txt_Starttime;
    String str_StartTime,str_Partid,str_Partno,str_Quantity,str_Partname,Quantity;
    double Latitude ,Logitude;
    String address = "";

    ArrayList<String> arli_Partname;
    ArrayList<String> arli_Partno;
    ArrayList<String> arli_Partid;
    ArrayList<String> arli_Quantity;

    String form1_value;
    String form1_name;
    String form1_comments;
    String form1_cat_id;
    String form1_group_id;
    String form_remarks;

    ArrayList<String> myFieldValue;
    ArrayList<String> myname;
    ArrayList<String> comments;
    ArrayList<String> catid;
    ArrayList<String> groupid;
    ArrayList<String> remarks;
    ArrayList<String> MyData = new ArrayList<>();

    String[] strValue;
    String[] strName;
    String[] strComments;
    String[] strGroupid;
    String[] strCatid;
    String[] strRemarks;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_breakdown_mrlist_one);

        context = this;
        CommonUtil.dbUtil = new DbUtil(context);
        CommonUtil.dbUtil.open();
        CommonUtil.dbHelper = new DbHelper(context);

        partnameMaterialEdittext = findViewById(R.id.part_name);
        partnoMaterialEdittext = findViewById(R.id.part_no);
        quantityMaterialEdittext = findViewById(R.id.no);
        imgbtnSearch = findViewById(R.id.imgbtn_search);
        iv_back = findViewById(R.id.iv_back);
        addButton = findViewById(R.id.btn_add);
        submitButton = findViewById(R.id.btn_sumbit);
        prevButton = findViewById(R.id.btn_show);
        recyclerView = findViewById(R.id.recyclerView);
        iv_pause = findViewById(R.id.ic_paused);
        txt_Starttime = findViewById(R.id.txt_starttime);
        txt_Jobid = findViewById(R.id.txt_jobid);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        se_id = sharedPreferences.getString("_id", "default value");
        se_user_mobile_no = sharedPreferences.getString("user_mobile_no", "default value");
        se_user_name = sharedPreferences.getString("user_name", "default value");
        service_title = sharedPreferences.getString("service_title", "Services");
        job_id =sharedPreferences.getString("jobid","L-1234");
        osacompno = sharedPreferences.getString("osacompno","ADT2020202020");
        Log.e("Name", "" + service_title);
        Log.e("Mobile", ""+ se_user_mobile_no);
        Log.e("Jobid",""+ job_id);
        Log.e("osocompno",""+ osacompno);

        str_StartTime = sharedPreferences.getString("starttime","");
        str_StartTime = str_StartTime.replaceAll("[^0-9-:]", " ");
        Log.e("Start Time",str_StartTime);
        txt_Jobid.setText("Job ID : " + job_id);
        txt_Starttime.setText("Start Time : " + str_StartTime);

        Latitude = Double.parseDouble(sharedPreferences.getString("lati","0.00000"));
        Logitude = Double.parseDouble(sharedPreferences.getString("long","0.00000"));
        address =sharedPreferences.getString("add","Chennai");
        Log.e("Location",""+Latitude+""+Logitude+""+address);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
//            service_title = extras.getString("service_title");
            status = extras.getString("status");
            Log.e("Status", "" + status);
            // Log.d("title",service_title);
        }
        if (extras != null) {
            //job_id = extras.getString("job_id");
            strPartname = extras.getString("partname");
            strPartno = extras.getString("partno");
            str_mr1 = extras.getString("mr1");
            str_mr2 = extras.getString("mr2");
            str_mr3 = extras.getString("mr3");
            str_mr4 = extras.getString("mr4");
            str_mr5 = extras.getString("mr5");
            str_mr6 = extras.getString("mr6");
            str_mr7 = extras.getString("mr7");
            str_mr8 = extras.getString("mr8");
            str_mr9 = extras.getString("mr9");
            str_mr10 = extras.getString("mr10");

            partnameMaterialEdittext.setText(strPartname);
            partnoMaterialEdittext.setText(strPartno);
            partnameMaterialEdittext.setFocusableInTouchMode(false);
            partnoMaterialEdittext.setFocusableInTouchMode(false);
            quantityMaterialEdittext.setFocusableInTouchMode(true);

            Log.e("JobId","" + job_id );
        }

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("service_title", service_title);
        editor.apply();

        if (status.equals("pause")){

            Log.e("Inside", "Paused Job");

        }

        else{
            Log.e("Inside", "New Job ");

            iv_pause.setVisibility(View.VISIBLE);

            arli_Partid = new ArrayList<>();
            arli_Partname = new ArrayList<>();
            arli_Partno = new ArrayList<>();
            arli_Quantity = new ArrayList<>();

            getMRList();

            addButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (status.equals("pause")){
//                        createLocaldata();
                    }
                    Intent send = new Intent(context, AuditMRList_Activity.class);
                    send.putExtra("service_title",service_title);
                    send.putExtra("job_id",job_id);
                    send.putExtra("mr1", str_mr1);
                    send.putExtra("mr2", str_mr2);
                    send.putExtra("mr3", str_mr3);
                    send.putExtra("mr4", str_mr4);
                    send.putExtra("mr5", str_mr5);
                    send.putExtra("mr6", str_mr6);
                    send.putExtra("mr7", str_mr7);
                    send.putExtra("mr8", str_mr8);
                    send.putExtra("mr9", str_mr9);
                    send.putExtra("mr10", str_mr10);
                    send.putExtra("status", status);
                    startActivity(send);

                }
            });
        }


        iv_pause.setOnClickListener(new View.OnClickListener() {
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

        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onBackPressed();

            }
        });

        prevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                onBackPressed();
            }
        });
//
//        imgbtnSearch.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                Intent send = new Intent(AuditMR_Activity.this, AuditMRList_Activity.class);
//                send.putExtra("status", status);
//                startActivity(send);
//
//            }
//        });

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (status.equals("pause")){
                    Log.e("Hi Nish","Pause JOB");
//                    createLocaldata();
                }

                getMRList();

                if (arli_Partno.size() == 0){

                    alertDialog = new AlertDialog.Builder(context)
                            //.setTitle("Please Login to Continue!")
                            .setMessage("Please add MR")
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    alertDialog.dismiss();
                                }
                            })
                            .show();
                }
                else if(arli_Quantity.contains("0")){

                    alertDialog = new AlertDialog.Builder(context)
                            //.setTitle("Please Login to Continue!")
                            .setMessage("Please add MR Quantity")
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    alertDialog.dismiss();
                                }
                            })
                            .show();
                }

                else{
//                    createLocaldata();
                    Intent send = new Intent(context, TechnicianSigantureAudit_Activity.class);
                    send.putExtra("service_title",service_title);
                    send.putExtra("job_id",job_id);
                    send.putExtra("mr1", str_mr1);
                    send.putExtra("mr2", str_mr2);
                    send.putExtra("mr3", str_mr3);
                    send.putExtra("mr4", str_mr4);
                    send.putExtra("mr5", str_mr5);
                    send.putExtra("mr6", str_mr6);
                    send.putExtra("mr7", str_mr7);
                    send.putExtra("mr8", str_mr8);
                    send.putExtra("mr9", str_mr9);
                    send.putExtra("mr10", str_mr10);
                    send.putExtra("status", status);
                    startActivity(send);
                }

            }
        });
    }

    private void Job_status_update() {

        APIInterface apiInterface = RetrofitClient.getClient().create(APIInterface.class);
        Call<Job_status_updateResponse> call = apiInterface.job_status_updateAuditResponseCall(com.triton.johnson_tap_app.utils.RestUtils.getContentType(), job_status_updateRequest());
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
        custom.setOM_OSA_COMPNO(osacompno);
        custom.setJOB_START_LONG(Logitude);
        custom.setJOB_START_LAT(Latitude);
        custom.setJOB_LOCATION(address);
        Log.w(VolleyLog.TAG,"Request "+ new Gson().toJson(custom));
        return custom;
    }

    private void createLocalValueCall() {

        APIInterface apiInterface = RetrofitClient.getClient().create(APIInterface.class);
        Call<SuccessResponse> call = apiInterface.createLocalValueCallAudit(RestUtils.getContentType(),createLocalRequest());
        Log.w(TAG, " Check Local Value Form Call url  :%s" + " " + call.request().url().toString());

        call.enqueue(new Callback<SuccessResponse>() {
            @Override
            public void onResponse(Call<SuccessResponse> call, Response<SuccessResponse> response) {

                Log.w(TAG, "Check Local Value Form Response" + new Gson().toJson(response.body()));

                if (response.body() != null) {
                    message = response.body().getMessage();

                    if (response.body().getCode() == 200){

                        if(response.body().getData() != null){

                            Log.d("msg",message);

//                            Intent send = new Intent(context, ServicesActivity.class);
//                            startActivity(send);
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

    private AuditRequest createLocalRequest() {

        AuditRequest localreq = new AuditRequest();
        localreq.setJobId(job_id);
        localreq.setCustomerSignature("");
        localreq.setOmOsaCompno(osacompno);
        localreq.setUserMobileNo(se_user_mobile_no);

        List<AuditRequest.MrDatum> mrData = new ArrayList<>();

        Cursor cur = CommonUtil.dbUtil.getMRList(job_id,"3",service_title);
        Log.e("List Count",""+cur.getCount());

        if (cur.getCount()>0 &&cur.moveToFirst()) {

            do {
                str_Partid = cur.getString(cur.getColumnIndexOrThrow(DbHelper.ID));
                str_Partname = cur.getString(cur.getColumnIndexOrThrow(DbHelper.PART_NAME));
                str_Partno = cur.getString(cur.getColumnIndexOrThrow(DbHelper.PART_NO));
                str_Quantity = cur.getString(cur.getColumnIndexOrThrow(DbHelper.QUANTITY));
                arli_Partname.add(str_Partname);
                arli_Partno.add(str_Partno);
                arli_Quantity.add(str_Quantity);
                Log.e("Part List No",""+arli_Partno);
                Log.e("Part List Name",""+arli_Partname);
                Log.e("Part List Qty",""+arli_Quantity);

            }while (cur.moveToNext());

        }

        for (int i = 0; i<arli_Partno.size();i++){
            int mynum = i+1;

            AuditRequest.MrDatum mrDatum = new AuditRequest.MrDatum();

            mrDatum.setValue("MR "+mynum);
            mrDatum.setPartno(arli_Partno.get(i));
            mrDatum.setPartname(arli_Partname.get(i));
            mrDatum.setReq(arli_Quantity.get(i));

            mrData.add(mrDatum);

        }
        Log.e("Nish MR Data",""+ mrData.size());
        Log.e(TAG,"Request field"+ new Gson().toJson(mrData));
        localreq.setMrData(mrData);

        List<AuditRequest.FieldValueDatum> fielddata = new ArrayList<>();

        for(int j =0; j <myFieldValue.size(); j++){
            AuditRequest.FieldValueDatum myfiled = new AuditRequest.FieldValueDatum();

            myfiled.setFieldValue(myFieldValue.get(j));
            myfiled.setFieldName(myname.get(j));
            myfiled.setFieldComments(comments.get(j));
            myfiled.setFieldCatId(catid.get(j));
            myfiled.setFieldGroupId(groupid.get(j));
            myfiled.setFieldRemarks(remarks.get(j));
            fielddata.add(myfiled);

        }
        Log.e(TAG,"Request field"+ new Gson().toJson(fielddata));
        localreq.setFieldValueData(fielddata);

        Log.w(VolleyLog.TAG,"Request "+ new Gson().toJson(localreq));
        return localreq;
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

    public void getMRList() {

        arli_Partid = new ArrayList<>();
        arli_Partname = new ArrayList<>();
        arli_Partno = new ArrayList<>();
        arli_Quantity = new ArrayList<>();

        Cursor cur = CommonUtil.dbUtil.getMRList(job_id,"3",service_title);
        Log.e("List Count",""+cur.getCount());


        if (cur.getCount()>0 &&cur.moveToFirst()){

            do {
                str_Partid = cur.getString(cur.getColumnIndexOrThrow(DbHelper.ID));
                str_Partname= cur.getString(cur.getColumnIndexOrThrow(DbHelper.PART_NAME));
                str_Partno= cur.getString(cur.getColumnIndexOrThrow(DbHelper.PART_NO));
                str_Quantity = cur.getString(cur.getColumnIndexOrThrow(DbHelper.QUANTITY));
                Log.e("Quantity",""+str_Quantity);
                arli_Partid.add(str_Partid);
                arli_Partname.add(str_Partname);
                arli_Partno.add(str_Partno);
                arli_Quantity.add(str_Quantity);
            } while (cur.moveToNext());
        }
        cur.close();

        Log.e("Nish",""+arli_Partid.size());
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        AuditMR_Adapter adapter = new AuditMR_Adapter(arli_Partid,arli_Partname,arli_Partno, arli_Quantity,context,this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onBackPressed() {

       // super.onBackPressed();

        if (status.equals("pause")){
            Log.e("Hi Nish","Pause JOB");
            createLocaldata();
        }
        Intent send = new Intent(context, MaterialRequest_AuditActivity.class);
        send.putExtra("status", status);
        startActivity(send);

    }

    public void petBreedTypeSelectListener(String petbreedtitle, String petbreedid) {
        PetBreedType = petbreedtitle;
    }

    public void onQuantityChange(EditText edt_Qty, String s, int position) {

        Quantity = s;
        Log.e("Remarks 123",""+ Quantity +" "+position);

    }

    private void createLocaldata() {

        Log.e("Nish","Create Local Data");

        APIInterface apiInterface =  RetrofitClient.getClient().create((APIInterface.class));
        Call<SuccessResponse> call = apiInterface.createLocalValueCallAudit(com.triton.johnson_tap_app.utils.RestUtils.getContentType(),createLocalRequest());
        Log.w(TAG,"Create Local Pause Value url  :%s"+" "+ call.request().url().toString());

        call.enqueue(new Callback<SuccessResponse>() {
            @Override
            public void onResponse(Call<SuccessResponse> call, Response<SuccessResponse> response) {

                Log.w(TAG,"SignupResponse" + new Gson().toJson(response.body()));

                if (response.body() != null) {
                    message = response.body().getMessage();

                    if (response.body().getCode() == 200){

                        if(response.body().getData() != null){

                            Log.d("msg",message);

//                            Intent send = new Intent(BreakdownMRListOne_Activity.this, ServicesActivity.class);
//                            startActivity(send);
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
}