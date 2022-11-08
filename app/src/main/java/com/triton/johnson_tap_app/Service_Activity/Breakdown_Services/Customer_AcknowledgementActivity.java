package com.triton.johnson_tap_app.Service_Activity.Breakdown_Services;

import static android.content.ContentValues.TAG;

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
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.VolleyLog;
import com.bumptech.glide.Glide;
import com.github.gcacace.signaturepad.views.SignaturePad;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
import com.triton.johnson_tap_app.Db.CommonUtil;
import com.triton.johnson_tap_app.Db.DbHelper;
import com.triton.johnson_tap_app.Db.DbUtil;
import com.triton.johnson_tap_app.R;
import com.triton.johnson_tap_app.RestUtils;
import com.triton.johnson_tap_app.Service_Activity.PreventiveMRApproval.TechnicianSignature_PreventiveMRActivity;
import com.triton.johnson_tap_app.Service_Activity.ServicesActivity;
import com.triton.johnson_tap_app.api.APIInterface;
import com.triton.johnson_tap_app.api.RetrofitClient;
import com.triton.johnson_tap_app.requestpojo.Breakdowm_Submit_Request;
import com.triton.johnson_tap_app.requestpojo.Feedback_DetailsRequest;
import com.triton.johnson_tap_app.requestpojo.Job_Details_TextRequest;
import com.triton.johnson_tap_app.requestpojo.Job_statusRequest;
import com.triton.johnson_tap_app.requestpojo.Job_status_updateRequest;
import com.triton.johnson_tap_app.responsepojo.Breakdown_submitrResponse;
import com.triton.johnson_tap_app.responsepojo.Feedback_DetailsResponse;
import com.triton.johnson_tap_app.responsepojo.FileUploadResponse;
import com.triton.johnson_tap_app.responsepojo.Job_Details_TextResponse;
import com.triton.johnson_tap_app.responsepojo.Job_statusResponse;
import com.triton.johnson_tap_app.responsepojo.Job_status_updateResponse;
import com.triton.johnson_tap_app.responsepojo.RetriveLocalValueBRResponse;
import com.triton.johnson_tap_app.responsepojo.SubmitBreakdownResponseee;
import com.triton.johnson_tap_app.responsepojo.SuccessResponse;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

public class Customer_AcknowledgementActivity extends AppCompatActivity {

    SignaturePad signaturePad;
    Button saveButton, clearButton;
     Button btn_success, btn_prev;
    ImageView iv_back;
    MultipartBody.Part siganaturePart;
    String userid;
    ImageView image;
    String str2="",sstring;
    private String uploadimagepath = "";
    private List<Breakdown_submitrResponse.DataBean.Feedback_detailsBean> defaultLocationList ;
    List<Feedback_DetailsResponse.DataBean> pet_imgList = new ArrayList();
    String value="",job_id,feedback_group,Str_feedback_details,bd_dta,feedback_remark,mr1,mr2,mr3,mr4,mr5,mr6,mr7,mr8,mr9,mr10,breakdown_servies,tech_signature,customer_name,customer_no,str_customer_acknowledgement="";
    String se_user_mobile_no, se_user_name, se_id,check_id,service_title;
    String str_job_status,message;
    ProgressDialog progressDialog;
    TextView job_details_text;
    Bitmap signatureBitmap;
    Dialog dialog;
    String compno, sertype, str_BDDetails,status,s_mr1,s_mr2,s_mr3,s_mr4,s_mr5,s_mr6,s_mr7,s_mr8,s_mr9,s_mr10;
    ArrayList<String> list = new ArrayList<>();
    Context context;
    AlertDialog alertDialog;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_customer_acknowledgement);
        context = this;

        CommonUtil.dbUtil = new DbUtil(context);
        CommonUtil.dbUtil.open();
        CommonUtil.dbHelper = new DbHelper(context);

        signaturePad = (SignaturePad)findViewById(R.id.signaturePad);
        saveButton = (Button)findViewById(R.id.saveButton);
        clearButton = (Button)findViewById(R.id.clearButton);
        btn_success = (Button) findViewById(R.id.btn_success);
        btn_prev = (Button) findViewById(R.id.btn_show);
        iv_back = (ImageView) findViewById(R.id.iv_back);
       image = (ImageView)findViewById(R.id.image);
        job_details_text = (TextView) findViewById(R.id.job_details_text);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        se_id = sharedPreferences.getString("_id", "default value");
        se_user_mobile_no = sharedPreferences.getString("user_mobile_no", "default value");
        se_user_name = sharedPreferences.getString("user_name", "default value");
        job_id = sharedPreferences.getString("job_id", "default value");
        service_title = sharedPreferences.getString("service_title", "default value");
        Str_feedback_details = sharedPreferences.getString("feedbackdetails","FG0003");
        feedback_group = sharedPreferences.getString("feedbackgroup","FG0003");
       // String name = sharedPreferences.getString("Hi","");
        value = sharedPreferences.getString("value","default value");
        Log.e("Value",value);
    //    Str_feedback_details = Str_feedback_details.replaceAll("\\[", "").replaceAll("\\]","");
       // Log.e("Hi Nish",""+name);
        Log.e("JobID",""+job_id);
        Log.e("Name",""+service_title);
        Log.e("Feedback DEtails",Str_feedback_details);
        Log.e("Feedback Group",feedback_group);


        compno = sharedPreferences.getString("compno","123");
        sertype = sharedPreferences.getString("sertype","123");
        feedback_remark = sharedPreferences.getString("feedback_remark","hi");
        tech_signature = sharedPreferences.getString("tech_sign","defalut");
      //  breakdown_servies = sharedPreferences.getString("ServiceName","");
        Log.e("Feedback Remarks",feedback_remark);
     //   Log.e("breakdown servies ",breakdown_servies);
        Log.e("tech_signature",tech_signature);
//

       // arLi_FeedbackDetails = Str_feedback_details
    //    list = (ArrayList<String>) Arrays.asList(Str_feedback_details);
     //   Log.e("List", String.valueOf(list));

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
          //  value = extras.getString("value");
            status = extras.getString("status");
            Log.e("Status",status);

            if (status.equals("new")){
                breakdown_servies = extras.getString("breakdown_service");
                Log.e("A",breakdown_servies);
            }

        }



        Job_status();

        job_details_in_text();

        if (status.equals("new")){
            getBdDetails();

            getCustomer(job_id,service_title);

            getData(job_id,service_title);
        }else{

            retrive_LocalValue();
        }

//        str2 =  feedback_details.substring(1, feedback_details.length());
//        String[] sArr = str2.split(",");
//        List<String> sList = Arrays.asList(sArr);
//        sstring = String.valueOf(sList);

        btn_prev.setBackgroundResource(R.drawable.blue_button_background_with_radius);
        btn_prev.setTextColor(getResources().getColor(R.color.white));
        btn_prev.setEnabled(true);


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

                progressDialog = new ProgressDialog(Customer_AcknowledgementActivity.this);
                progressDialog.setMessage("Please Wait Image Upload ...");
                progressDialog.setCancelable(false);
                progressDialog.show();

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

                uploadDigitalSignatureImageRequest();

                long delayInMillis = 15000;
                Timer timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        progressDialog.dismiss();
                    }
                }, delayInMillis);
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
                    Toast.makeText(Customer_AcknowledgementActivity.this, "Please Drop Signature", Toast.LENGTH_SHORT).show();
                }
                else {

                    locationAddResponseCall();
                }

            }
        });

        btn_prev.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                onBackPressed();

//                if(uploadimagepath.equals("")) {
//                    Intent send = new Intent(Customer_AcknowledgementActivity.this, Customer_Details_BreakdownActivity.class);
//                    send.putExtra("value", value);
//                    send.putExtra("feedback_details", feedback_details);
//                    send.putExtra("feedback_group", feedback_group);
//                    send.putExtra("bd_details", bd_dta);
//                    send.putExtra("job_id", job_id);
//                    send.putExtra("feedback_remark", feedback_remark);
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
//                    send.putExtra("breakdown_service", breakdown_servies);
//                    send.putExtra("tech_signature", tech_signature);
//                    send.putExtra("customer_name", customer_name);
//                    send.putExtra("customer_number", customer_no);
//                    startActivity(send);
//                }
//                else {
//                    Intent send = new Intent(Customer_AcknowledgementActivity.this, Customer_Details_BreakdownActivity.class);
//                    send.putExtra("value", value);
//                    send.putExtra("feedback_details", feedback_details);
//                    send.putExtra("feedback_group", feedback_group);
//                    send.putExtra("bd_details", bd_dta);
//                    send.putExtra("job_id", job_id);
//                    send.putExtra("feedback_remark", feedback_remark);
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
//                    send.putExtra("breakdown_service", breakdown_servies);
//                    send.putExtra("tech_signature", tech_signature);
//                    send.putExtra("customer_name", customer_name);
//                    send.putExtra("customer_number", customer_no);
//                    send.putExtra("customer_acknowledgement", uploadimagepath);
//                    startActivity(send);
//                }
            }
        });

        iv_back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                onBackPressed();
//                if(uploadimagepath.equals("")) {
//                    Intent send = new Intent(Customer_AcknowledgementActivity.this, Customer_Details_BreakdownActivity.class);
//                    send.putExtra("feedback_details", feedback_details);
//                    send.putExtra("value", value);
//                    send.putExtra("feedback_group", feedback_group);
//                    send.putExtra("bd_details", bd_dta);
//                    send.putExtra("job_id", job_id);
//                    send.putExtra("feedback_remark", feedback_remark);
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
//                    send.putExtra("breakdown_service", breakdown_servies);
//                    send.putExtra("tech_signature", tech_signature);
//                    send.putExtra("customer_name", customer_name);
//                    send.putExtra("customer_number", customer_no);
//                    startActivity(send);
//                }
//                else {
//                    Intent send = new Intent(Customer_AcknowledgementActivity.this, Customer_Details_BreakdownActivity.class);
//                    send.putExtra("value", value);
//                    send.putExtra("feedback_details", feedback_details);
//                    send.putExtra("feedback_group", feedback_group);
//                    send.putExtra("bd_details", bd_dta);
//                    send.putExtra("job_id", job_id);
//                    send.putExtra("feedback_remark", feedback_remark);
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
//                    send.putExtra("breakdown_service", breakdown_servies);
//                    send.putExtra("tech_signature", tech_signature);
//                    send.putExtra("customer_name", customer_name);
//                    send.putExtra("customer_number", customer_no);
//                    send.putExtra("customer_acknowledgement", uploadimagepath);
//                    startActivity(send);
//                }
            }
        });
    }

    private void Job_status() {

        APIInterface apiInterface = RetrofitClient.getClient().create(APIInterface.class);
        Call<Job_statusResponse> call = apiInterface.job_statusResponseCall(com.triton.johnson_tap_app.utils.RestUtils.getContentType(), job_statusRequest());
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

        AlertDialog.Builder mBuilder = new AlertDialog.Builder(Customer_AcknowledgementActivity.this);
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
                                createLocalvalue();
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
        Call<RetriveLocalValueBRResponse> call = apiInterface.retriveLocalValueBRCall(com.triton.johnson_tap_app.utils.RestUtils.getContentType(),localRequest());
        Log.e("Retrive Local Value url  :%s"," "+ call.request().url().toString());

        call.enqueue(new Callback<RetriveLocalValueBRResponse>() {
            @Override
            public void onResponse(Call<RetriveLocalValueBRResponse> call, Response<RetriveLocalValueBRResponse> response) {
                Log.e("Retrive Response","" + new Gson().toJson(response.body()));
                if (response.body() != null){

                    message = response.body().getMessage();

                    if (response.body().getCode() == 200){

                        if (response.body().getData() != null){
                            Log.d("msg",message);

                            customer_name= response.body().getData().getCustomer_name();
                            customer_no = response.body().getData().getCustomer_number();
                            tech_signature = response.body().getData().getTech_signature();
                            value = response.body().getData().getMr_status();
                            mr1 = response.body().getData().getMr_1();
                            mr2 = response.body().getData().getMr_2();
                            mr3 = response.body().getData().getMr_3();
                            mr4 = response.body().getData().getMr_4();
                            mr5 = response.body().getData().getMr_5();
                            mr6 = response.body().getData().getMr_6();
                            mr7 = response.body().getData().getMr_7();
                            mr8 = response.body().getData().getMr_8();
                            mr9 = response.body().getData().getMr_9();
                            mr10 = response.body().getData().getMr_10();
                            feedback_remark = response.body().getData().getFeedback_remark_text();
                            str_BDDetails = response.body().getData().getBd_details();
                            Str_feedback_details = response.body().getData().getFeedback_details();
                            feedback_group = response.body().getData().getCode_list();
                            breakdown_servies = response.body().getData().getBreakdown_service();

                            Log.e("Nish Cname",""+ customer_name);
                            Log.e("Nish CNo",""+ customer_no);
                            Log.e("Nish Feedback Details",""+ Str_feedback_details);
                            Log.e("Nish Feedback Group",""+ feedback_group);
                            Log.e("Nish BD Data",""+ str_BDDetails);


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
            Log.e("Request Data ",""+ new Gson().toJson(custom));
            return custom;

    }

    @SuppressLint("Range")
    private void getCustomer(String job_id, String service_title) {

        Cursor cur = CommonUtil.dbUtil.getCustmer(job_id,service_title);

        Log.e("GET CUSTOMER ",""+cur.getCount());

        if (cur.getCount()>0 && cur.moveToLast()){

                    customer_name = cur.getString(cur.getColumnIndex(DbHelper.CUSTOMER_NAME));
             customer_no = cur.getString(cur.getColumnIndex(DbHelper.CUSTOMER_NUMBER));
        }
    }

    private void getData(String job_id, String service_title) {

        Log.e("JobId",""+job_id);
        Log.e("Activity",""+service_title);

        Cursor cur = CommonUtil.dbUtil.getBreakdownMrList(job_id,service_title);

        Log.e("MRLIST" ,"" + cur.getCount());

        if (cur.getCount()>0 && cur.moveToLast()){

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
        }
    }

    private void createLocalvalue() {

        APIInterface apiInterface = RetrofitClient.getClient().create(APIInterface.class);
        Call<SuccessResponse> call = apiInterface.createLocalvalueBD(com.triton.johnson_tap_app.utils.RestUtils.getContentType(), createLocalRequest());
        Log.w(VolleyLog.TAG,"Create Local Value Response url  :%s"+" "+ call.request().url().toString());

        call.enqueue(new Callback<SuccessResponse>() {
            @Override
            public void onResponse(Call<SuccessResponse> call, Response<SuccessResponse> response) {

                Log.w(VolleyLog.TAG,"Create Local Value Response" + "" + new Gson().toJson(response.body()));

                if (response.body() != null) {
                    message = response.body().getMessage();

                    if (response.body().getCode() == 200){

                        if(response.body().getData() != null){

                            Log.d("msg",message);


                            Intent send = new Intent(context, ServicesActivity.class);
                            startActivity(send);
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

    private Breakdowm_Submit_Request createLocalRequest() {

        Log.e( "before ", feedback_group);
        feedback_group  = feedback_group.replaceAll("\n", "").replaceAll("","");
        Log.e( "after ", feedback_group);

        Log.e( "before 1 ", Str_feedback_details);
        Str_feedback_details  = Str_feedback_details.replaceAll("\n", "").replaceAll("","");
        Log.e( "after 1 ", Str_feedback_details);

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm aa", Locale.getDefault());
        String currentDateandTime = sdf.format(new Date());

        Breakdowm_Submit_Request submitDailyRequest = new Breakdowm_Submit_Request();
        submitDailyRequest.setBd_details(str_BDDetails);
        //submitDailyRequest.setFeedback_details(sstring);
        submitDailyRequest.setFeedback_details(Str_feedback_details);
        submitDailyRequest.setCode_list(feedback_group);
        submitDailyRequest.setFeedback_remark_text(feedback_remark);
        submitDailyRequest.setMr_status(value);
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
        submitDailyRequest.setBreakdown_service(breakdown_servies);
        submitDailyRequest.setTech_signature(tech_signature);
        submitDailyRequest.setCustomer_name(customer_name);
        submitDailyRequest.setCustomer_number(customer_no);
        submitDailyRequest.setCustomer_acknowledgemnet(uploadimagepath);
        submitDailyRequest.setDate_of_submission(currentDateandTime);
        submitDailyRequest.setUser_mobile_no(se_user_mobile_no);
        submitDailyRequest.setJob_id(job_id);
        submitDailyRequest.setSMU_SCH_COMPNO(compno);
        submitDailyRequest.setSMU_SCH_SERTYPE(sertype);
        Log.e("CompNo",""+compno);
        Log.e("SertYpe", ""+sertype);
        Log.e("Feedback Details",""+ Str_feedback_details);
        Log.e("BD Details",""+ str_BDDetails);
        Log.w(TAG," Create Local Value Request"+ new Gson().toJson(submitDailyRequest));
        return submitDailyRequest;
    }

    @SuppressLint("Range")
    private void getBdDetails() {

        Cursor cur = CommonUtil.dbUtil.getBDdetails(job_id,service_title, "1");
        Log.e("BD Count",""+cur.getCount());

        if (cur.moveToLast()){

            str_BDDetails = cur.getString(cur.getColumnIndex(DbHelper.BD_DETAILS));
            Log.e("BD Data Get",""+ str_BDDetails);
        }
    }

    private void uploadDigitalSignatureImageRequest() {

        APIInterface apiInterface = RetrofitClient.getImageClient().create(APIInterface.class);
        Call<FileUploadResponse> call = apiInterface.getImageStroeResponse(siganaturePart);
        Log.w(TAG, "url  :%s" + call.request().url().toString());

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

                        Toast.makeText(Customer_AcknowledgementActivity.this, "Signature Saved", Toast.LENGTH_SHORT).show();

                        if (uploadimagepath != null) {
                            Glide.with(Customer_AcknowledgementActivity.this)
                                    .load(uploadimagepath)
                                    .into(image);

                            progressDialog.dismiss();

                        }
                    }else{

                        long delayInMillis = 15000;
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
        dialog = new Dialog(Customer_AcknowledgementActivity.this, R.style.NewProgressDialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.progroess_popup);
        dialog.show();
        APIInterface apiInterface = RetrofitClient.getClient().create(APIInterface.class);
        Call<SubmitBreakdownResponseee> call = apiInterface.submitAddResponseCall(RestUtils.getContentType(),submitDailyRequest());
        Log.w(TAG,"url  :%s"+" "+ call.request().url().toString());

        call.enqueue(new Callback<SubmitBreakdownResponseee>() {
            @SuppressLint("LogNotTimber")
            @Override
            public void onResponse(@NotNull Call<SubmitBreakdownResponseee> call, @NotNull Response<SubmitBreakdownResponseee> response) {

                Log.w(TAG, "AddLocationResponse" + new Gson().toJson(response.body()));
                Log.w(TAG,"url  :%s"+" "+ call.request().url().toString());

                if (response.body() != null) {
                    dialog.dismiss();
                    if(response.body().getCode() == 200){
                        dialog.dismiss();
                        Log.w(TAG,"url  :%s"+" "+ call.request().url().toString());

                        Log.w(TAG,"dddd %s"+" "+ response.body().getData().toString());

                        Toasty.success(Customer_AcknowledgementActivity.this, "Added Successfully", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();

                        CommonUtil.dbUtil.reportDeletePreventiveListDelete(job_id,service_title);

                        CommonUtil.dbUtil.deleteSign(job_id,service_title);

                        CommonUtil.dbUtil.deleteCustAck(job_id,service_title);

                        CommonUtil.dbUtil.deleteBreakdownMR(job_id,service_title);


                        Intent send = new Intent( Customer_AcknowledgementActivity.this, ServicesActivity.class);
                        startActivity(send);

                    }else{
                        //  showErrorLoading(response.body().getMessage());
                        dialog.dismiss();
                    }

                }
            }

            @Override
            public void onFailure(@NotNull Call<SubmitBreakdownResponseee> call, @NotNull Throwable t) {
                Log.w(TAG,"AddLocationResponseflr"+t.getMessage());
                dialog.dismiss();
            }
        });

    }

    private Breakdowm_Submit_Request submitDailyRequest() {

        Log.e( "before ", Str_feedback_details);
        Str_feedback_details  = Str_feedback_details.replaceAll("\n", "").replaceAll("","");
        Log.e( "after ", Str_feedback_details);

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm aa", Locale.getDefault());
        String currentDateandTime = sdf.format(new Date());

        Breakdowm_Submit_Request submitDailyRequest = new Breakdowm_Submit_Request();
        submitDailyRequest.setBd_details(str_BDDetails);
        //submitDailyRequest.setFeedback_details(sstring);
        submitDailyRequest.setFeedback_details(Str_feedback_details);
       // submitDailyRequest.setCode_list(feedback_group);
        submitDailyRequest.setFeedback_remark_text(feedback_remark);
        submitDailyRequest.setMr_status(value);
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
        submitDailyRequest.setBreakdown_service(breakdown_servies);
        submitDailyRequest.setTech_signature(tech_signature);
        submitDailyRequest.setCustomer_name(customer_name);
        submitDailyRequest.setCustomer_number(customer_no);
        submitDailyRequest.setCustomer_acknowledgemnet(uploadimagepath);
        submitDailyRequest.setDate_of_submission(currentDateandTime);
        submitDailyRequest.setUser_mobile_no(se_user_mobile_no);
        submitDailyRequest.setJob_id(job_id);
        submitDailyRequest.setSMU_SCH_COMPNO(compno);
        submitDailyRequest.setSMU_SCH_SERTYPE(sertype);
        Log.e("CompNo",""+compno);
        Log.e("SertYpe", ""+sertype);
        Log.e("Feedback Details",""+ Str_feedback_details);
        Log.e("BD Details",""+ str_BDDetails);
        Log.w(TAG," locationAddRequest"+ new Gson().toJson(submitDailyRequest));
        return submitDailyRequest;
    }

    private void Job_status_update() {

        APIInterface apiInterface = RetrofitClient.getClient().create(APIInterface.class);
        Call<Job_status_updateResponse> call = apiInterface.job_status_updateResponseCall(com.triton.johnson_tap_app.utils.RestUtils.getContentType(), job_status_updateRequest());
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
        Call<Job_Details_TextResponse> call = apiInterface.Job_Details_TextResponseCall(RestUtils.getContentType(), custom_detailsRequest());
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
        Log.e("JobID",""+job_id);
        Log.w(VolleyLog.TAG,"loginRequest "+ new Gson().toJson(custom));
        return custom;
    }

    @Override
    public void onBackPressed() {
   // super.onBackPressed();

        if(status.equals("new")){
            Intent intent = new Intent(context,Customer_Details_BreakdownActivity.class);
            intent.putExtra("status",status);
            intent.putExtra("breakdown_service", breakdown_servies);
            startActivity(intent);
        }else{
            Intent intent = new Intent(context,Customer_Details_BreakdownActivity.class);
            intent.putExtra("status",status);
            intent.putExtra("breakdown_service", breakdown_servies);
            startActivity(intent);
        }

    }
}