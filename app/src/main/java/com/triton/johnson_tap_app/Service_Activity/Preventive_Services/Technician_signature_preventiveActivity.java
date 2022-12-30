package com.triton.johnson_tap_app.Service_Activity.Preventive_Services;

import static android.content.ContentValues.TAG;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
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

import com.github.gcacace.signaturepad.views.SignaturePad;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
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
import com.triton.johnson_tap_app.responsepojo.FileUploadResponse;
import com.triton.johnson_tap_app.responsepojo.Job_status_updateResponse;
import com.triton.johnson_tap_app.responsepojo.RetriveResponsePR;
import com.triton.johnson_tap_app.responsepojo.SuccessResponse;
import com.triton.johnson_tap_app.utils.ConnectionDetector;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

import es.dmoral.toasty.Toasty;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Technician_signature_preventiveActivity extends AppCompatActivity {

    SignaturePad signaturePad;
    Button saveButton, clearButton;
    private Button btnSelection,btn_prev;
    MultipartBody.Part siganaturePart;
    String userid;
    ImageView image,iv_back,img_Paused,img_Siganture;
    private String uploadimagepath = "";
    String value="",job_id,mr1,mr2,mr3,mr4,mr5,mr6,mr7,mr8,mr9,mr10,value_s,service_title,preventive_check,pm_status,action_req_customer,Form1_value,Form1_name,Form1_comments,str_tech_signature="",str_job_status;
    String Form1_cat_id,Form1_group_id;
    ProgressDialog progressDialog;
    Bitmap signatureBitmap;
    Context context;
    AlertDialog alertDialog;
    SharedPreferences sharedPreferences;
    String se_id,se_user_mobile_no,se_user_name,signfile,status,List,statustype,compno,sertype,s_remark,message;
    String s_mr1 ="", s_mr2 ="",s_mr3 ="",s_mr4 ="",s_mr5 ="",s_mr6 ="",s_mr7 ="",s_mr8 ="",s_mr9 ="",s_mr10 ="",networkStatus="";
    RetriveResponsePR.Data databean ;
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
        setContentView(R.layout.activity_technician_signature_preventive);
        context = this;

        CommonUtil.dbUtil = new DbUtil(context);
        CommonUtil.dbUtil.open();
        CommonUtil.dbHelper = new DbHelper(context);

        signaturePad = (SignaturePad)findViewById(R.id.signaturePad);
        saveButton = (Button)findViewById(R.id.saveButton);
        clearButton = (Button)findViewById(R.id.clearButton);
        btnSelection = (Button) findViewById(R.id.btn_next);
       // btn_prev = (Button) findViewById(R.id.btn_show);
        image = (ImageView)findViewById(R.id.image);
        iv_back = (ImageView) findViewById(R.id.iv_back);
        img_Paused = findViewById(R.id.img_paused);
        img_Siganture = (ImageView)findViewById(R.id.image1);
        txt_Starttime = findViewById(R.id.txt_starttime);
        txt_Jobid = findViewById(R.id.txt_jobid);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        se_id = sharedPreferences.getString("_id", "default value");
        se_user_mobile_no = sharedPreferences.getString("user_mobile_no", "default value");
        se_user_name = sharedPreferences.getString("user_name", "default value");
        job_id = sharedPreferences.getString("job_id", "default value");
        service_title = sharedPreferences.getString("service_title", "default value");
        Log.e("JobID",""+job_id);
        Log.e("Name",""+service_title);

        str_StartTime = sharedPreferences.getString("starttime","");
        str_StartTime = str_StartTime.replaceAll("[^0-9-:]", " ");
        Log.e("Start Time",str_StartTime);
        txt_Jobid.setText("Job ID : " + job_id);
        txt_Starttime.setText("Start Time : " + str_StartTime);

        List = sharedPreferences.getString("List","1");
        Log.e("Month List", "" +List);
        statustype = sharedPreferences.getString("statustype","OD");
        Log.e("ValueB", statustype);
        compno = sharedPreferences.getString("compno","123");
        sertype = sharedPreferences.getString("sertype","123");
        preventive_check = sharedPreferences.getString("PreventiveChecklist","bbb");
        Log.e("Preventive Check",preventive_check);
        pm_status = sharedPreferences.getString("pmstatus","aaa");
        s_remark = sharedPreferences.getString("feedbackremark","000");

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

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
         //   value_s = extras.getString("valuess");
            status = extras.getString("status");
            Log.e("Status",""+status);
        }
        if (extras != null) {
         //   job_id = extras.getString("job_id");
        }

        if (extras != null) {
           // value = extras.getString("value");
        }

        if (extras != null) {
          //  service_title = extras.getString("service_title");
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
           // preventive_check = extras.getString("preventive_check");
        }
        if (extras != null) {
          //  pm_status = extras.getString("pm_status");
        }
        if (extras != null) {
          //  action_req_customer = extras.getString("action_req_customer");
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
//            str_tech_signature = extras.getString("tech_signature");
//            Picasso.get().load(str_tech_signature).into(image);
        }

        networkStatus = ConnectionDetector.getConnectivityStatusString(getApplicationContext());

        Log.e("Network",""+networkStatus);


        if (status.equals("paused")) {

            if (networkStatus.equalsIgnoreCase("Not connected to Internet")) {

               NoInternetDialog();

            }else{

                retrive_LocalValue();
            }



        }else{
            Log.e("Way","New");
            getFeedback();

            getData(job_id,service_title);

            getSign(job_id,service_title);
        }



        saveButton.setEnabled(false);
        clearButton.setEnabled(false);

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

        signaturePad.setOnSignedListener(new SignaturePad.OnSignedListener() {
            @Override
            public void onStartSigning() {

            }

            public void onSigned() {
                saveButton.setEnabled(true);
                clearButton.setEnabled(true);
            }

            public void onClear() {
                saveButton.setEnabled(false);
                clearButton.setEnabled(false);
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                signatureBitmap = signaturePad.getSignatureBitmap();
                Log.w(TAG, "signatureBitmap" + signatureBitmap);
                File file = new File(getFilesDir(), "Technician Signature" + ".jpg");

                OutputStream os;
                try {
                    os = new FileOutputStream(file);
                    if (signatureBitmap != null) {
                        signatureBitmap.compress(Bitmap.CompressFormat.JPEG, 100, os);
                    }
                    os.flush();
                    os.close();
                } catch (Exception e) {
                    Log.e(getClass().getSimpleName(), "Error writing bitmap", e);
                }

                siganaturePart = MultipartBody.Part.createFormData("sampleFile", userid + file.getName(), RequestBody.create(MediaType.parse("image/*"), file));

                networkStatus = ConnectionDetector.getConnectivityStatusString(getApplicationContext());

                Log.e("Network",""+networkStatus);

                if (networkStatus.equalsIgnoreCase("Not connected to Internet")) {

                    NoInternetDialog();

                }else{
                    uploadDigitalSignatureImageRequest(file);


                }

            }
        });

        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signaturePad.clear();
            }
        });

        btnSelection.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Cursor cur =  CommonUtil.dbUtil.getEngSign(job_id,service_title);
                Log.e("ENg Sign", " " + cur.getCount());

                if (signatureBitmap == null && cur.getCount() == 0) {
                    Toast.makeText(context, "Please Drop Signature", Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent send = new Intent(Technician_signature_preventiveActivity.this, Customer_Details_PreventiveActivity.class);
                    send.putExtra("valuess", value_s);
                    send.putExtra("job_id", job_id);
                    send.putExtra("value", value);
                    send.putExtra("service_title", service_title);
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
                    send.putExtra("preventive_check", preventive_check);
                    send.putExtra("pm_status", pm_status);
                    send.putExtra("Tech_signature", uploadimagepath);
                    send.putExtra("action_req_customer", action_req_customer);
                    send.putExtra("Form1_value", Form1_value);
                    send.putExtra("Form1_name", Form1_name);
                    send.putExtra("Form1_comments", Form1_comments);
                    send.putExtra("Form1_cat_id", Form1_cat_id);
                    send.putExtra("Form1_group_id", Form1_group_id);
                    send.putExtra("status",status);
                    startActivity(send);
                }
            }
        });

        iv_back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
//                if(uploadimagepath.equals("")) {
//                    Intent send = new Intent(Technician_signature_preventiveActivity.this, Preventive_StatusActivity.class);
//                    send.putExtra("valuess", value_s);
//                    send.putExtra("job_id", job_id);
//                    send.putExtra("value", value);
//                    send.putExtra("service_title", service_title);
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
//                    send.putExtra("preventive_check", preventive_check);
//                    send.putExtra("pm_status", pm_status);
//                    send.putExtra("action_req_customer", action_req_customer);
//                    send.putExtra("Form1_value", Form1_value);
//                    send.putExtra("Form1_name", Form1_name);
//                    send.putExtra("Form1_comments", Form1_comments);
//                    send.putExtra("Form1_cat_id", Form1_cat_id);
//                    send.putExtra("Form1_group_id", Form1_group_id);
//                    startActivity(send);
//                }
//                else {
//                    Intent send = new Intent(Technician_signature_preventiveActivity.this, Preventive_StatusActivity.class);
//                    send.putExtra("valuess", value_s);
//                    send.putExtra("job_id", job_id);
//                    send.putExtra("value", value);
//                    send.putExtra("service_title", service_title);
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
//                    send.putExtra("preventive_check", preventive_check);
//                    send.putExtra("pm_status", pm_status);
//                    send.putExtra("action_req_customer", action_req_customer);
//                    send.putExtra("Form1_value", Form1_value);
//                    send.putExtra("Form1_name", Form1_name);
//                    send.putExtra("Form1_comments", Form1_comments);
//                    send.putExtra("Form1_cat_id", Form1_cat_id);
//                    send.putExtra("Form1_group_id", Form1_group_id);
//                    send.putExtra("tech_signature", uploadimagepath);
//                    startActivity(send);
//                }

                onBackPressed();
            }
        });
    }

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

    @SuppressLint("LongLogTag")
    private void retrive_LocalValue() {

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

                            uploadimagepath = response.body().getData().getTech_signature();
                            Picasso.get().load(uploadimagepath).into(img_Siganture);


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
        custom.setJOB_START_LONG(Logitude);
        custom.setJOB_START_LAT(Latitude);
        custom.setJOB_LOCATION(address);
        Log.e("CompNo",""+compno);
        Log.e("SertYpe", ""+sertype);
        Log.w(TAG,"Job Status Update Request "+ new Gson().toJson(custom));
        return custom;
    }

    @SuppressLint("Range")
    private void getFeedback() {

        Cursor cur = CommonUtil.dbUtil.getFeedback(job_id,service_title,"4");

        Log.e("GET FEEDBACK ",""+cur.getCount());

        if (cur.getCount()>0 && cur.moveToLast()){


            s_remark= cur.getString(cur.getColumnIndex(DbHelper.FEEDBACK_REMARKS));
            Log.e("Remarks",""+s_remark);
//            feedback_remark.setText(s_remark);

        }
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
        localRequest.setPm_status(pm_status);
        localRequest.setTech_signature(uploadimagepath);
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

    @SuppressLint("Range")
    private void getSign(String job_id, String myactivity) {

        Log.e("Sign", "Hi");
        Log.e("Nish",""+job_id);
        Log.e("Nish",""+myactivity);
        Cursor cur =  CommonUtil.dbUtil.getEngSign(job_id,myactivity);
        Cursor curs =  CommonUtil.dbUtil.getEngSign();
        Log.e("ENg Sign", " " + cur.getCount());

        if (cur.getCount()>0 && cur.moveToFirst()){

            do{
                signfile = cur.getString(cur.getColumnIndex(DbHelper.SIGN_FILE));
                String jon = cur.getString(cur.getColumnIndex(DbHelper.JOBID));
                String ss = cur.getString(cur.getColumnIndex(DbHelper.MYACTIVITY));
                uploadimagepath = cur.getString(cur.getColumnIndex(DbHelper.SIGN_PATH));

                Log.e("job" , ""+jon);
                Log.e("act" , ""+ss);
                Log.e("path" , ""+uploadimagepath);

                Picasso.get().load(uploadimagepath).into(img_Siganture);


                ///  BitmapDrawable drawable = (BitmapDrawable) img_Siganture.getDrawable();
                //  Bitmap bitmap = drawable.getBitmap();
                ///  bitmap = Bitmap.createScaledBitmap(bitmap, 70, 70, true);
                //  photo.setImageBitmap(bitmap)
                // Bitmap image = ((BitmapDrawable)img_Siganture.getDrawable()).getBitmap();


                //  File file = new File(signfile);
                //  String filePath = file.getPath();
                //   Bitmap bitmap = BitmapFactory.decodeFile(filePath);
                // img_Siganture.setImageBitmap(bitmap);
                // signaturePad.setSignatureBitmap(bitmap);
                //  signaturePad.setSignatureBitmap(bitmap);
                // signatureBitmap = signaturePad.getSignatureBitmap();
            }while (cur.moveToNext());


        }
    }

    private void uploadDigitalSignatureImageRequest(File file) {

        progressDialog = new ProgressDialog(Technician_signature_preventiveActivity.this);
        progressDialog.setMessage("Please Wait Image Upload ...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        APIInterface apiInterface = RetrofitClient.getImageClient().create(APIInterface.class);
        Call<FileUploadResponse> call = apiInterface.getImageStroeResponse(siganaturePart);
        Log.w(TAG, "url  :%s" + call.request().url().toString());

        long delayInMillis = 1000;
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                progressDialog.dismiss();
            }
        }, delayInMillis);

        call.enqueue(new Callback<FileUploadResponse>() {
            @SuppressLint("LogNotTimber")
            @Override
            public void onResponse(@NonNull Call<FileUploadResponse> call, @NonNull Response<FileUploadResponse> response) {

                if (response.body() != null) {
                    if (200 == response.body().getCode()) {
                        Log.w(TAG, "Profpic" + "--->" + new Gson().toJson(response.body()));

                        uploadimagepath = response.body().getData();

                        Log.d("image",uploadimagepath);
                        if (uploadimagepath != null) {

                            //   Picasso.get().load(uploadimagepath).into(image);

                            Toast.makeText(Technician_signature_preventiveActivity.this, "Signature Saved", Toast.LENGTH_SHORT).show();

                            img_Siganture.setVisibility(View.GONE);
                            CommonUtil.dbUtil.addEngSign(job_id,service_title,uploadimagepath,file,"0");
                            Log.e("a" , "" + job_id);
                            Log.e("b", "" + service_title);
                            Log.e("c" , "" + uploadimagepath);
                            Log.e("d" , "" + file);
                          //  progressDialog.dismiss();
//                            progressDialog.dismiss();

                        }
                    }
                    else{

                        long delayInMillis = 1000;
                        Timer timer = new Timer();
                        timer.schedule(new TimerTask() {
                            @Override
                            public void run() {
                                progressDialog.dismiss();
                            }
                        }, delayInMillis);
                    }

                }

            }

            @SuppressLint("LogNotTimber")
            @Override
            public void onFailure(@NonNull Call<FileUploadResponse> call, @NonNull Throwable t) {
                Log.w(TAG, "ServerUrlImagePath" + "On failure working" + t.getMessage());
            }
        });
    }

    @Override
    public void onBackPressed() {
       // super.onBackPressed();

        Intent send = new Intent( Technician_signature_preventiveActivity.this, Preventive_StatusActivity.class);
        send.putExtra("status",status);
        startActivity(send);
    }
}