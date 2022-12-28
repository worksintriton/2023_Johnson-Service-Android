package com.triton.johnson_tap_app.Service_Activity.Preventive_Services;

import static android.content.ContentValues.TAG;
import androidx.annotation.NonNull;
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
import android.graphics.Bitmap;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.VolleyLog;
import com.bumptech.glide.Glide;
import com.github.gcacace.signaturepad.views.SignaturePad;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
import com.triton.johnson_tap_app.Db.CommonUtil;
import com.triton.johnson_tap_app.Db.DbHelper;
import com.triton.johnson_tap_app.Db.DbUtil;
import com.triton.johnson_tap_app.GetFieldListResponse;
import com.triton.johnson_tap_app.R;
import com.triton.johnson_tap_app.RestUtils;
import com.triton.johnson_tap_app.Service_Activity.Breakdown_Services.Customer_AcknowledgementActivity;
import com.triton.johnson_tap_app.Service_Activity.ServicesActivity;
import com.triton.johnson_tap_app.api.APIInterface;
import com.triton.johnson_tap_app.api.RetrofitClient;
import com.triton.johnson_tap_app.requestpojo.Job_Details_TextRequest;
import com.triton.johnson_tap_app.requestpojo.Job_statusRequest;
import com.triton.johnson_tap_app.requestpojo.Job_status_updateRequest;
import com.triton.johnson_tap_app.requestpojo.Preventive_Submit_Request;
import com.triton.johnson_tap_app.responsepojo.Breakdown_submitrResponse;
import com.triton.johnson_tap_app.responsepojo.Feedback_DetailsResponse;
import com.triton.johnson_tap_app.responsepojo.FileUploadResponse;
import com.triton.johnson_tap_app.responsepojo.Job_Details_TextResponse;
import com.triton.johnson_tap_app.responsepojo.Job_statusResponse;
import com.triton.johnson_tap_app.responsepojo.Job_status_updateResponse;
import com.triton.johnson_tap_app.responsepojo.RetriveResponsePR;
import com.triton.johnson_tap_app.responsepojo.SubmitPreventiveResponse;
import com.triton.johnson_tap_app.responsepojo.SuccessResponse;
import com.triton.johnson_tap_app.utils.ConnectionDetector;

import org.jetbrains.annotations.NotNull;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

import es.dmoral.toasty.Toasty;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Customer_Acknowledgement_PreventiveActivity extends AppCompatActivity {

    SignaturePad signaturePad;
    Button saveButton, clearButton;
    Button btn_success, btn_prev;
    ImageView iv_back;
    MultipartBody.Part siganaturePart;
    String userid;
    ImageView image,img_Signature;
    String str2="",sstring;
    private String uploadimagepath = "";
    private List<Breakdown_submitrResponse.DataBean.Feedback_detailsBean> defaultLocationList ;
    List<Feedback_DetailsResponse.DataBean> pet_imgList = new ArrayList();
    String statustype ="",job_id,mr1,mr2,mr3,mr4,mr5,mr6,mr7,mr8,mr9,mr10,tech_signature,customer_name,customer_no,value_s,preventive_check,pm_status,action_req_customer,Form1_value,Form1_name,Form1_comments;
    String se_user_mobile_no, se_user_name, se_id,check_id,service_title,Form1_cat_id,Form1_group_id;
    String str_job_status,message,str_customer_acknowledgement,signfile,custfile,status;
    ProgressDialog progressDialog;
    TextView job_details_text;
    Bitmap signatureBitmap;
    Dialog dialog;
    String compno, sertype,List,s_cust_name,s_cust_no;
    Context context;
    SharedPreferences sharedPreferences;
    AlertDialog alertDialog;
    String s_mr1 ="", s_mr2 ="",s_mr3 ="",s_mr4 ="",s_mr5 ="",s_mr6 ="",s_mr7 ="",s_mr8 ="",s_mr9 ="",s_mr10 ="",networkStatus="";
    TextView txt_Jobid,txt_Starttime;
    String str_StartTime;
    ArrayList<String> mydata = new ArrayList<>();

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
        setContentView(R.layout.activity_customer_acknowledgement_preventive);
        context = this;

        CommonUtil.dbUtil = new DbUtil(context);
        CommonUtil.dbUtil.open();
        CommonUtil.dbHelper = new DbHelper(context);

        signaturePad = (SignaturePad)findViewById(R.id.signaturePad);
        saveButton = (Button)findViewById(R.id.saveButton);
        clearButton = (Button)findViewById(R.id.clearButton);
        btn_success = (Button) findViewById(R.id.btn_success);
       // btn_prev = (Button) findViewById(R.id.btn_show);
        iv_back = (ImageView) findViewById(R.id.iv_back);
        image = (ImageView)findViewById(R.id.image);
        job_details_text = (TextView) findViewById(R.id.job_details_text);
        img_Signature = findViewById(R.id.image1);
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
        List = sharedPreferences.getString("List","1");
        Log.e("Month List", "" +List);

        str_StartTime = sharedPreferences.getString("starttime","");
        str_StartTime = str_StartTime.replaceAll("[^0-9-:]", " ");
        Log.e("Start Time",str_StartTime);
        txt_Jobid.setText("Job ID : " + job_id);
        txt_Starttime.setText("Start Time : " + str_StartTime);

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

        compno = sharedPreferences.getString("compno","123");
        sertype = sharedPreferences.getString("sertype","123");
        value_s = sharedPreferences.getString("value","default value");
        statustype = sharedPreferences.getString("statustype","OD");
        action_req_customer = sharedPreferences.getString("feedbackremark","000");
        pm_status = sharedPreferences.getString("pmstatus","aaa");
        preventive_check = sharedPreferences.getString("PreventiveChecklist","bbb");

        Log.e("Name",service_title);
        Log.e("JobID",job_id);
        Log.e("Type", statustype);
        Log.e("Value",value_s);
        Log.e("Remark",action_req_customer);
        Log.e("Preventive Check",preventive_check);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
          //  value_s = extras.getString("valuess");
            status = extras.getString("status");
            Log.e("Status",""+status);
        }
        if (extras != null) {
          //  job_id = extras.getString("job_id");
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
          //  preventive_check = extras.getString("preventive_check");
        }
        if (extras != null) {
        //    pm_status = extras.getString("pm_status");
        }
        if (extras != null) {
           // tech_signature = extras.getString("Tech_signature");
        }
        if (extras != null) {
            customer_name = extras.getString("customer_name");
        }
        if (extras != null) {
            customer_no = extras.getString("customer_no");
        }
        if (extras != null) {
           // action_req_customer = extras.getString("action_req_customer");
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
            str_customer_acknowledgement = extras.getString("customer_acknowledgement");
            Picasso.get().load(str_customer_acknowledgement).into(image);
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

        networkStatus = ConnectionDetector.getConnectivityStatusString(getApplicationContext());

        Log.e("Network",""+networkStatus);
        if (networkStatus.equalsIgnoreCase("Not connected to Internet")) {

            NoInternetDialog();

        }
        else{

            Job_status();

            job_details_in_text();

        }

        getPreventiveCheck();


        if (status.equals("new")){

            getData(job_id,service_title);
            getCustomer(job_id,service_title);
            getCustAck(job_id,service_title);
            getFeedback();
            getSign(job_id,service_title);
        }else{

            if (networkStatus != "Not connected to Internet"){
                retrive_LocalValue();
            }
            else{

                NoInternetDialog();

            }
        }
        getSample();


        //disable both buttons at start
        saveButton.setEnabled(false);
        clearButton.setEnabled(false);

        //change screen orientation to landscape mode
        //  setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        signaturePad.setOnSignedListener(new SignaturePad.OnSignedListener() {
            @Override
            public void onStartSigning() {

            }

            @Override
            public void onSigned() {
                saveButton.setEnabled(true);
                clearButton.setEnabled(true);
            }

            @Override
            public void onClear() {
                saveButton.setEnabled(false);
                clearButton.setEnabled(false);
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                signatureBitmap = signaturePad.getSignatureBitmap();
                Log.w(TAG, "signatureBitmap" + signatureBitmap);
                File file = new File(getFilesDir(), "Acknowledgment_Signature" + ".jpg");

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

        btn_success.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if (signatureBitmap == null) {
                    Toast.makeText(Customer_Acknowledgement_PreventiveActivity.this, "Please Drop Signature", Toast.LENGTH_SHORT).show();
                }
                else {

                    networkStatus = ConnectionDetector.getConnectivityStatusString(getApplicationContext());

                    Log.e("Network",""+networkStatus);
                    if (networkStatus.equalsIgnoreCase("Not connected to Internet")) {

                        NoInternetDialog();

                    }
                    else{

                        locationAddResponseCall();
                    }

                }

            }
        });

        iv_back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

//                if(uploadimagepath.equals("")) {
//                    Intent send = new Intent(Customer_Acknowledgement_PreventiveActivity.this, Customer_Details_PreventiveActivity.class);
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
//                    send.putExtra("Tech_signature", tech_signature);
//                    send.putExtra("action_req_customer", action_req_customer);
//                    send.putExtra("Form1_value", Form1_value);
//                    send.putExtra("Form1_name", Form1_name);
//                    send.putExtra("Form1_comments", Form1_comments);
//                    send.putExtra("Form1_cat_id", Form1_cat_id);
//                    send.putExtra("Form1_group_id", Form1_group_id);
//                    send.putExtra("customer_no",customer_no);
//                    send.putExtra("customer_name",customer_name);
//                    startActivity(send);
//                }
//                else {
//                    Intent send = new Intent(Customer_Acknowledgement_PreventiveActivity.this, Customer_Details_PreventiveActivity.class);
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
//                    send.putExtra("Tech_signature", tech_signature);
//                    send.putExtra("action_req_customer", action_req_customer);
//                    send.putExtra("Form1_value", Form1_value);
//                    send.putExtra("Form1_name", Form1_name);
//                    send.putExtra("Form1_comments", Form1_comments);
//                    send.putExtra("Form1_cat_id", Form1_cat_id);
//                    send.putExtra("Form1_group_id", Form1_group_id);
//                    send.putExtra("customer_acknowledgement", uploadimagepath);
//                    send.putExtra("customer_no",customer_no);
//                    send.putExtra("customer_name",customer_name);
//                    startActivity(send);
//                }
                onBackPressed();
            }
        });
    }

    private void getPreventiveCheck() {

        Cursor cur = CommonUtil.dbUtil.getCheckList(job_id,service_title, "2");
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
        preventive_check = String.valueOf(outputList);
        Log.e("Preventive Checklist DB", preventive_check);
    }

    private Preventive_Submit_Request getSample() {

        Log.e( "before ", preventive_check);

        preventive_check  = preventive_check.replaceAll("\n", "").replaceAll("","");
        Log.e( "after ", preventive_check);

        Preventive_Submit_Request submitDailyRequest = new Preventive_Submit_Request();
        submitDailyRequest.setJob_status_type(statustype);
        submitDailyRequest.setMr_status(value_s);
        submitDailyRequest.setMr_1(s_mr1);
        submitDailyRequest.setMr_2(s_mr2);
        submitDailyRequest.setMr_3(s_mr3);
        submitDailyRequest.setMr_4(s_mr4);
        submitDailyRequest.setMr_5(s_mr5);
        submitDailyRequest.setMr_6(s_mr6);
        submitDailyRequest.setMr_7(s_mr7);
        submitDailyRequest.setMr_8(s_mr8);
        submitDailyRequest.setMr_9(s_mr9);
        submitDailyRequest.setMr_10(s_mr10);
        submitDailyRequest.setPreventive_check(preventive_check);
        submitDailyRequest.setAction_req_customer(action_req_customer);
        submitDailyRequest.setPm_status(pm_status);
        submitDailyRequest.setTech_signature(tech_signature);
        submitDailyRequest.setCustomer_name(customer_name);
        submitDailyRequest.setCustomer_number(customer_no);
        submitDailyRequest.setCustomer_signature("uploadimagepath");
        submitDailyRequest.setUser_mobile_no(se_user_mobile_no);
        submitDailyRequest.setJob_id(job_id);
        submitDailyRequest.setSMU_SCH_COMPNO(compno);
        submitDailyRequest.setSMU_SCH_SERTYPE(sertype);
        Log.e("CompNo",""+compno);
        Log.e("SertYpe", ""+sertype);
        Log.e("JobID",""+job_id);

        Log.w(TAG, " locationAddRequest" + new Gson().toJson(submitDailyRequest));
        return submitDailyRequest;
    }

    private void Job_status() {

        APIInterface apiInterface = RetrofitClient.getClient().create(APIInterface.class);
        Call<Job_statusResponse> call = apiInterface.job_status_PreventiveResponseCall(com.triton.johnson_tap_app.utils.RestUtils.getContentType(), job_statusRequest());
        Log.w(VolleyLog.TAG,"SignupResponse url  :%s"+" "+ call.request().url().toString());

        call.enqueue(new Callback<Job_statusResponse>() {
            @SuppressLint("LogNotTimber")
            @Override
            public void onResponse(@NonNull Call<Job_statusResponse> call, @NonNull retrofit2.Response<Job_statusResponse> response) {

                Log.w(VolleyLog.TAG,"SignupResponse" + new Gson().toJson(response.body()));
                if (response.body() != null) {
                    message = response.body().getMessage();

                    if (200 == response.body().getCode()) {
                        if(response.body().getData() != null){

                            Log.d("msg",message);

                            if (Objects.equals(message, "Job Started") || Objects.equals(message, "Job Paused") || Objects.equals(message, "Job Resume")){

                                Log.e("Hi","inside");

                                alert();
                            }
                            else{
                                Log.e("Hi","outside");
                            }


                        }


                    } else {
                        Toasty.warning(getApplicationContext(),""+message,Toasty.LENGTH_LONG).show();

                    }
                }
            }

            public void onFailure(@NonNull Call<Job_statusResponse> call, @NonNull Throwable t) {
                Log.e("OTP", "--->" + t.getMessage());
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void alert() {

        AlertDialog.Builder mBuilder = new AlertDialog.Builder(Customer_Acknowledgement_PreventiveActivity.this);
        View mView = getLayoutInflater().inflate(R.layout.startjob_popup_layout, null);

        DateFormat df = new SimpleDateFormat("EEE, d MMM yyyy, HH:mm");
        String date = df.format(Calendar.getInstance().getTime());
        TextView txt_DateTime = mView.findViewById(R.id.txt_datetime);
        txt_DateTime.setText(date);

        TextView txt_jobstatus = mView.findViewById(R.id.txt_jobstatus);
        TextView txt_job_content = mView.findViewById(R.id.txt_job_content);
        LinearLayout ll_start = mView.findViewById(R.id.ll_start);
        LinearLayout ll_pause = mView.findViewById(R.id.ll_pause);
        LinearLayout ll_stop = mView.findViewById(R.id.ll_stop);
        LinearLayout ll_resume = mView.findViewById(R.id.ll_resume);
        ImageView img_close = mView.findViewById(R.id.img_close);
        Button btn_back = mView.findViewById(R.id.btn_back);
        btn_back.setVisibility(View.GONE);
        txt_jobstatus.setVisibility(View.GONE);
        ll_resume.setVisibility(View.GONE);
        ll_start.setVisibility(View.GONE);

        if (Objects.equals(message, "Job Stopped")){

            ll_stop.setVisibility(View.GONE);
        }

        mBuilder.setView(mView);
        final AlertDialog dialog = mBuilder.create();
        dialog.show();
        dialog.setCanceledOnTouchOutside(false);

        ll_pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                str_job_status = "Job Paused";

                alertDialog = new AlertDialog.Builder(context)
                        .setTitle("Are you sure to pause this job ?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Job_status_update();
                                createLocalValue();
                                dialog.dismiss();
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

        ll_stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                str_job_status = "Job Stopped";
                Job_status_update();
                //  createLocalValueStopCall();
                dialog.dismiss();
            }
        });



    }

    private Job_statusRequest job_statusRequest() {

        Job_statusRequest custom = new Job_statusRequest();
        custom.setUser_mobile_no(se_user_mobile_no);
        custom.setService_name(service_title);
        custom.setJob_id(job_id);
        custom.setSMU_SCH_COMPNO(compno);
        custom.setSMU_SCH_SERTYPE(sertype);
        Log.e("CompNo",""+compno);
        Log.e("SertYpe", ""+sertype);
        Log.w(VolleyLog.TAG,"loginRequest "+ new Gson().toJson(custom));
        return custom;
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

    @SuppressLint("Range")
    private void getSign(String job_id, String service_title) {

        Log.e("Sign", "Hi");
        Log.e("Nish",""+job_id);
        Log.e("Nish",""+service_title);
        Cursor cur =  CommonUtil.dbUtil.getEngSign(job_id,service_title);
        Cursor curs =  CommonUtil.dbUtil.getEngSign();
        Log.e("ENg Sign", " " + cur.getCount());

        if (cur.getCount()>0 && cur.moveToFirst()){

            do{
                signfile = cur.getString(cur.getColumnIndex(DbHelper.SIGN_FILE));
                String jon = cur.getString(cur.getColumnIndex(DbHelper.JOBID));
                String ss = cur.getString(cur.getColumnIndex(DbHelper.MYACTIVITY));
                tech_signature = cur.getString(cur.getColumnIndex(DbHelper.SIGN_PATH));

                Log.e("job" , ""+jon);
                Log.e("act" , ""+ss);
                Log.e("path" , ""+uploadimagepath);

             //   Picasso.get().load(uploadimagepath).into(img_Siganture);

            }while (cur.moveToNext());


        }
    }

    @SuppressLint("Range")
    private void getFeedback() {

        Cursor cur = CommonUtil.dbUtil.getFeedback(job_id,service_title,"4");

        Log.e("GET FEEDBACK ",""+cur.getCount());

        if (cur.getCount()>0 && cur.moveToLast()){


            action_req_customer= cur.getString(cur.getColumnIndex(DbHelper.FEEDBACK_REMARKS));
            Log.e("Remarks",""+action_req_customer);
//            feedback_remark.setText(s_remark);

        }
    }

    @SuppressLint("Range")
    private void getCustomer(String job_id, String service_title) {

        Cursor cur = CommonUtil.dbUtil.getCustmer(job_id,service_title);

        Log.e("GET CUSTOMER ",""+cur.getCount());

        if (cur.getCount()>0 && cur.moveToFirst()){

            do {

                s_cust_name = cur.getString(cur.getColumnIndex(DbHelper.CUSTOMER_NAME));

                s_cust_no = cur.getString(cur.getColumnIndex(DbHelper.CUSTOMER_NUMBER));


            }while (cur.moveToNext());
        }

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
        localRequest.setAction_req_customer(action_req_customer);
        localRequest.setPm_status(pm_status);
        localRequest.setTech_signature(tech_signature);
        localRequest.setCustomer_name(s_cust_name);
        localRequest.setCustomer_number(s_cust_no);
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

        Log.w(TAG, "Create Local Request" + new Gson().toJson(localRequest));
        return  localRequest;
    }

    @SuppressLint("Range")
    private void getCustAck(String job_id, String service_title) {

        Log.e("Sign", "Hi");
        Log.e("Nish",""+job_id);
        Log.e("Nish",""+service_title);
        Cursor cur =  CommonUtil.dbUtil.getCustAck(job_id,service_title);
        Cursor curs =  CommonUtil.dbUtil.getEngSign();
        Log.e("ENg Sign", " " + cur.getCount());

        if (cur.getCount()>0 && cur.moveToFirst()){

            do{
                custfile = cur.getString(cur.getColumnIndex(DbHelper.CUSTACK_FILE));
                String jon = cur.getString(cur.getColumnIndex(DbHelper.JOBID));
                String ss = cur.getString(cur.getColumnIndex(DbHelper.MYACTIVITY));
                uploadimagepath = cur.getString(cur.getColumnIndex(DbHelper.CUSTACK_PATH));

                Log.e("job" , ""+jon);
                Log.e("act" , ""+ss);
                Log.e("path" , ""+uploadimagepath);

                Picasso.get().load(uploadimagepath).into(img_Signature);


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

        progressDialog = new ProgressDialog(Customer_Acknowledgement_PreventiveActivity.this);
        progressDialog.setMessage("Please Wait Image Upload ...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        APIInterface apiInterface = RetrofitClient.getImageClient().create(APIInterface.class);
        Call<FileUploadResponse> call = apiInterface.getImageStroeResponse(siganaturePart);
        Log.w(TAG, "url  :%s" + call.request().url().toString());

        long delayInMillis = 10000;
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

                        image.setVisibility(View.INVISIBLE);

                        Log.d("image", uploadimagepath);

                        Toast.makeText(Customer_Acknowledgement_PreventiveActivity.this, "Signature Saved", Toast.LENGTH_SHORT).show();

                        if (uploadimagepath != null) {
                            Glide.with(Customer_Acknowledgement_PreventiveActivity.this)
                                    .load(uploadimagepath)
                                    .into(image);

                            Toast.makeText(context, "Signature Saved", Toast.LENGTH_SHORT).show();
                            img_Signature.setVisibility(View.GONE);
                            CommonUtil.dbUtil.addCustAck(job_id,service_title,uploadimagepath,file);
                            Log.e("a" , "" + job_id);
                            Log.e("b", "" + service_title);
                            Log.e("c" , "" + uploadimagepath);
                            Log.e("d" , "" + file);
                            progressDialog.dismiss();

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

    public void locationAddResponseCall(){
        dialog = new Dialog(Customer_Acknowledgement_PreventiveActivity.this, R.style.NewProgressDialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.progroess_popup);
        dialog.show();
        APIInterface apiInterface = RetrofitClient.getClient().create(APIInterface.class);
        Call<SubmitPreventiveResponse> call = apiInterface.submitAddPreResponseCall(RestUtils.getContentType(),submitDailyRequest());
        Log.w(TAG,"url  :%s"+" "+ call.request().url().toString());

        call.enqueue(new Callback<SubmitPreventiveResponse>() {
            @SuppressLint("LogNotTimber")
            @Override
            public void onResponse(@NotNull Call<SubmitPreventiveResponse> call, @NotNull Response<SubmitPreventiveResponse> response) {

                Log.w(TAG, "AddLocationResponse" + new Gson().toJson(response.body()));
                Log.w(TAG,"url  :%s"+" "+ call.request().url().toString());

                if (response.body() != null) {
                    dialog.dismiss();
                    message = response.body().getMessage();
                    Log.e("Message",""+message);
                    if(response.body().getCode() == 200){
                        dialog.dismiss();
                        Log.w(TAG,"url  :%s"+" "+ call.request().url().toString());

                        Log.w(TAG,"dddd %s"+" "+ response.body().getData().toString());

                        Toasty.success(Customer_Acknowledgement_PreventiveActivity.this, "Added Successfully", Toast.LENGTH_SHORT).show();

//                        CommonUtil.dbUtil.reportDeleteMonthList(job_id,service_title);
//                        Cursor cur =  CommonUtil.dbUtil.getMonthlist(job_id,service_title);
//                        Log.e("Month List Final",""+cur.getCount());
//
//                        CommonUtil.dbUtil.reportDeletePreventiveList(job_id,service_title);
//                        Cursor curs =  CommonUtil.dbUtil.getCheckList(job_id,service_title);
//                        Log.e("Month List Final",""+curs.getCount());

                        CommonUtil.dbUtil.deleteSign(job_id,service_title);

                        CommonUtil.dbUtil.deleteCustAck(job_id,service_title);

//                        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
//                        SharedPreferences.Editor editor = sharedPreferences.edit();
//                        editor.clear();
//                        editor.commit();

                        Intent send = new Intent( Customer_Acknowledgement_PreventiveActivity.this, ServicesActivity.class);
                        startActivity(send);
                        dialog.dismiss();

                    }else{
                        //  showErrorLoading(response.body().getMessage());
                        dialog.dismiss();
                        showErrorAlert(message);
                    }

                }
            }

            @Override
            public void onFailure(@NotNull Call<SubmitPreventiveResponse> call, @NotNull Throwable t) {
                Log.w(TAG,"AddLocationResponseflr"+t.getMessage());
                dialog.dismiss();
            }
        });

    }

    private void showErrorAlert(String message) {

        AlertDialog.Builder mBuilder = new AlertDialog.Builder(context);
        View mView = getLayoutInflater().inflate(R.layout.remarks_popup, null);

        EditText edt_Remarks = mView.findViewById(R.id.edt_remarks);
        Button btn_Submit = mView.findViewById(R.id.btn_submit);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        TextView txt_Message = mView.findViewById(R.id.txt_message);
        btn_Submit.setText("OK");
        edt_Remarks.setVisibility(View.GONE);
        txt_Message.setVisibility(View.VISIBLE);

        mBuilder.setView(mView);
        alertDialog= mBuilder.create();
        alertDialog.show();
        alertDialog.setCanceledOnTouchOutside(false);

        txt_Message.setText(message);

        btn_Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                alertDialog.dismiss();
            }
        });
    }

    private Preventive_Submit_Request submitDailyRequest() {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm aa", Locale.getDefault());
        String currentDateandTime = sdf.format(new Date());

        Preventive_Submit_Request submitDailyRequest = new Preventive_Submit_Request();
        submitDailyRequest.setJob_status_type(statustype);
        submitDailyRequest.setMr_status(value_s);
        submitDailyRequest.setMr_1(s_mr1);
        submitDailyRequest.setMr_2(s_mr2);
        submitDailyRequest.setMr_3(s_mr3);
        submitDailyRequest.setMr_4(s_mr4);
        submitDailyRequest.setMr_5(s_mr5);
        submitDailyRequest.setMr_6(s_mr6);
        submitDailyRequest.setMr_7(s_mr7);
        submitDailyRequest.setMr_8(s_mr8);
        submitDailyRequest.setMr_9(s_mr9);
        submitDailyRequest.setMr_10(s_mr10);
        submitDailyRequest.setPreventive_check(preventive_check);
        submitDailyRequest.setAction_req_customer(action_req_customer);
        submitDailyRequest.setPm_status(pm_status);
        submitDailyRequest.setTech_signature(tech_signature);
        submitDailyRequest.setCustomer_name(customer_name);
        submitDailyRequest.setCustomer_number(customer_no);
        submitDailyRequest.setCustomer_signature(uploadimagepath);
        submitDailyRequest.setUser_mobile_no(se_user_mobile_no);
        submitDailyRequest.setJob_id(job_id);
        submitDailyRequest.setSMU_SCH_COMPNO(compno);
        submitDailyRequest.setSMU_SCH_SERTYPE(sertype);
        Log.e("CompNo",""+compno);
        Log.e("SertYpe", ""+sertype);
        Log.e("JobID",""+job_id);

        List<Preventive_Submit_Request.Field_valueDatum> fielddata = new ArrayList<>();

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

        Log.e("Nish",""+ fielddata.size());
        Log.e(TAG," Audit Request field"+ new Gson().toJson(fielddata));

//        Log.e(TAG," Audit Request"+ new Gson().toJson(auditRequest));
        submitDailyRequest.setField_valueData(fielddata);


            Log.w(TAG, " locationAddRequest" + new Gson().toJson(submitDailyRequest));
            return submitDailyRequest;
        }

    private GetFieldListResponse getFieldListResponse() {

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm aa", Locale.getDefault());
        String currentDateandTime = sdf.format(new Date());

        GetFieldListResponse getFieldListResponse = new GetFieldListResponse();
        getFieldListResponse.setCustomer_name("Reva");
        return getFieldListResponse;
    }


    private void Job_status_update() {

        APIInterface apiInterface = RetrofitClient.getClient().create(APIInterface.class);
        Call<Job_status_updateResponse> call = apiInterface.job_status_update_PreventiveResponseCall(com.triton.johnson_tap_app.utils.RestUtils.getContentType(), job_status_updateRequest());
        Log.w(VolleyLog.TAG,"SignupResponse url  :%s"+" "+ call.request().url().toString());

        call.enqueue(new Callback<Job_status_updateResponse>() {
            @SuppressLint("LogNotTimber")
            @Override
            public void onResponse(@NonNull Call<Job_status_updateResponse> call, @NonNull Response<Job_status_updateResponse> response) {

                Log.w(VolleyLog.TAG,"SignupResponse" + new Gson().toJson(response.body()));
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
        Log.w(VolleyLog.TAG,"loginRequest "+ new Gson().toJson(custom));
        return custom;
    }

    private void job_details_in_text() {

        APIInterface apiInterface = RetrofitClient.getClient().create(APIInterface.class);
        Call<Job_Details_TextResponse> call = apiInterface.Job_DetailsTextPreventiveResponseCall(RestUtils.getContentType(), custom_detailsRequest());
        Log.w(VolleyLog.TAG,"SignupResponse url  :%s"+" "+ call.request().url().toString());

        call.enqueue(new Callback<Job_Details_TextResponse>() {
            @SuppressLint("LogNotTimber")
            @Override
            public void onResponse(@NonNull Call<Job_Details_TextResponse> call, @NonNull Response<Job_Details_TextResponse> response) {

                Log.w(VolleyLog.TAG,"SignupResponse" + new Gson().toJson(response.body()));
                if (response.body() != null) {
                    message = response.body().getMessage();

                    if (200 == response.body().getCode()) {
                        if(response.body().getData() != null){

                            String str_address1 = response.body().getData().getText_value();

                            job_details_text.setText(str_address1);
                        }


                    } else {
                        Toasty.warning(getApplicationContext(),""+message,Toasty.LENGTH_LONG).show();

                    }
                }

            }

            @Override
            public void onFailure(@NonNull Call<Job_Details_TextResponse> call, @NonNull Throwable t) {
                Log.e("OTP", "--->" + t.getMessage());
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
    private Job_Details_TextRequest custom_detailsRequest() {

        Job_Details_TextRequest custom = new Job_Details_TextRequest();
        custom.setJob_id(job_id);
        custom.setSMU_SCH_COMPNO(compno);
        custom.setSMU_SCH_SERTYPE(sertype);
        Log.e("CompNo",""+compno);
        Log.e("SertYpe", ""+sertype);
        Log.w(VolleyLog.TAG,"loginRequest "+ new Gson().toJson(custom));
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

    @Override
    public void onBackPressed() {
       // super.onBackPressed();

        Intent send = new Intent( Customer_Acknowledgement_PreventiveActivity.this, Customer_Details_PreventiveActivity.class);
        send.putExtra("status",status);
        startActivity(send);
    }
}