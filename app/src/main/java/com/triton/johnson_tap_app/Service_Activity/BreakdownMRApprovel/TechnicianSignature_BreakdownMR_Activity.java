package com.triton.johnson_tap_app.Service_Activity.BreakdownMRApprovel;

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
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
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
import com.github.gcacace.signaturepad.views.SignaturePad;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
import com.triton.johnson_tap_app.Db.CommonUtil;
import com.triton.johnson_tap_app.Db.DbHelper;
import com.triton.johnson_tap_app.Db.DbUtil;
import com.triton.johnson_tap_app.R;
import com.triton.johnson_tap_app.RestUtils;
import com.triton.johnson_tap_app.Service_Activity.PreventiveMRApproval.PreventiveMRListOne_Activity;
import com.triton.johnson_tap_app.Service_Activity.PreventiveMRApproval.PreventiveMR_Activity;
import com.triton.johnson_tap_app.Service_Activity.ServicesActivity;
import com.triton.johnson_tap_app.api.APIInterface;
import com.triton.johnson_tap_app.api.RetrofitClient;
import com.triton.johnson_tap_app.requestpojo.Job_Details_TextRequest;
import com.triton.johnson_tap_app.requestpojo.Job_statusRequest;
import com.triton.johnson_tap_app.requestpojo.Job_status_updateRequest;
import com.triton.johnson_tap_app.requestpojo.ServiceUserdetailsRequestResponse;
import com.triton.johnson_tap_app.responsepojo.FileUploadResponse;
import com.triton.johnson_tap_app.responsepojo.Job_Details_TextResponse;
import com.triton.johnson_tap_app.responsepojo.Job_statusResponse;
import com.triton.johnson_tap_app.responsepojo.Job_status_updateResponse;
import com.triton.johnson_tap_app.responsepojo.SuccessResponse;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

public class TechnicianSignature_BreakdownMR_Activity extends AppCompatActivity {

    SignaturePad signaturePad;
    Button saveButton, clearButton;
    private Button btnSelection,btn_prev;
    MultipartBody.Part siganaturePart;
    String userid;
    ImageView img_Siganture,iv_back,iv_pause;
    Bitmap signatureBitmap;
    TextView job_details_text;
    private String uploadimagepath = "";
    String value="",job_id,feedback_group,feedback_details,bd_dta,feedback_remark,mr1,mr2,mr3,mr4,mr5,mr6,mr7,mr8,mr9,mr10,breakdown_servies,str_tech_signature="";
    ProgressDialog progressDialog;
    String se_user_mobile_no, se_user_name, se_id,check_id,service_title,message;
    String str2="",sstring,str_job_status;
    String str_Partid, str_Partno, str_Partname, str_Quantity,status;
    List<ServiceUserdetailsRequestResponse.MrDatum> servicedetailsrequestbean;
    Dialog dialog;
    String compno, sertype;
    String signfile;
    Context context;
    String myactivity = "Breakdown MR", sign,imagepath;
    boolean mysign = false;
    AlertDialog.Builder builder;
    SharedPreferences sharedPreferences;
    AlertDialog alertDialog;

    @SuppressLint("WrongThread")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_techniciansignature_breakdownmr);
        context = this;

        CommonUtil.dbUtil = new DbUtil(context);
        CommonUtil.dbUtil.open();
        CommonUtil.dbHelper = new DbHelper(context);

        signaturePad = (SignaturePad)findViewById(R.id.signaturePad);
        saveButton = (Button)findViewById(R.id.saveButton);
        clearButton = (Button)findViewById(R.id.clearButton);
        btnSelection = (Button) findViewById(R.id.btn_next);
        btn_prev = (Button) findViewById(R.id.btn_show);
        img_Siganture = (ImageView)findViewById(R.id.image1);
        iv_back = (ImageView) findViewById(R.id.iv_back);
        iv_pause = findViewById(R.id.ic_paused);
        img_Siganture.setVisibility(View.VISIBLE);

        //getSign(job_id,myactivity);

         sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        se_id = sharedPreferences.getString("_id", "default value");
        se_user_mobile_no = sharedPreferences.getString("user_mobile_no", "default value");
        se_user_name = sharedPreferences.getString("user_name", "default value");
        compno = sharedPreferences.getString("compno","123");
        sertype = sharedPreferences.getString("sertype","123");

        service_title = sharedPreferences.getString("service_title", "default value");
        job_id = sharedPreferences.getString("job_id", "default value");
        Log.e("Name",""+ service_title);
        Log.e("Job ID",""+ job_id);

       // mysign = sharedPreferences.getBoolean("mysignkey",false);
      //  uploadimagepath = sharedPreferences.getString("ImagePath" , "");

//        if(mysign){
//            mysign = sharedPreferences.getBoolean("mysignkey",false);
//            sign = sharedPreferences.getString("MySign"," ").trim();
//            File file = new File(sign);
//
//            String filePath = file.getPath();
//            Bitmap bitmap = BitmapFactory.decodeFile(filePath);
//           // img_Siganture.setImageBitmap(bitmap);
//
//            signaturePad.setSignatureBitmap(bitmap);
//
//            Log.e("My File", "" + file);
//
//
//        }

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
         //   job_id = extras.getString("job_id");
        }

        if (extras != null) {
            feedback_details = extras.getString("feedback_details");
        }

        if (extras != null) {
            feedback_remark = extras.getString("feedback_remark");
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
            breakdown_servies = extras.getString("breakdown_service");
        }
        if (extras != null) {
            str_tech_signature = extras.getString("tech_signature");
            Picasso.get().load(str_tech_signature).into(img_Siganture);


        }

        Job_status();

        getSign(job_id,myactivity);


        btn_prev.setBackgroundResource(R.drawable.blue_button_background_with_radius);
        btn_prev.setTextColor(getResources().getColor(R.color.white));
        btn_prev.setEnabled(true);

        if (status.equals("pause")){
            Log.e("Inside", "Paused Job");

            iv_pause.setVisibility(View.INVISIBLE);

            iv_pause.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {



                    builder = new AlertDialog.Builder(TechnicianSignature_BreakdownMR_Activity.this);

                    builder.setMessage("Are You sure want to pause this job ?")
                            .setCancelable(false)
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {

                                    Intent send = new Intent(TechnicianSignature_BreakdownMR_Activity.this, BreakdownMR_Activity.class);
                                    send.putExtra("status", status);
                                    send.putExtra("service_title",service_title);
                                    send.putExtra("job_id",job_id);
                                    startActivity(send);

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
        }else{
            Log.e("Inside", "New Job ");
        }

        saveButton.setEnabled(false);
        clearButton.setEnabled(false);

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

                progressDialog = new ProgressDialog(TechnicianSignature_BreakdownMR_Activity.this);
                progressDialog.setMessage("Please Wait Image Upload ...");
                progressDialog.setCancelable(false);
                progressDialog.show();

                signatureBitmap = signaturePad.getSignatureBitmap();
                Log.w(TAG, "signatureBitmap" + signatureBitmap);
                File file = new File(getFilesDir(), "Technician Signature" + ".jpg");

                // Save the file in the local db

                Log.e("Nish",""+file);

//                SharedPreferences.Editor editor = sharedPreferences.edit();
//                editor.putString("MySign" ,""+file );
//                editor.putBoolean( "mysignkey" , true);
//                editor.apply();

                OutputStream os;
                try {
                    os = new FileOutputStream(file);
                    if (signatureBitmap != null) {
                        signatureBitmap.compress(Bitmap.CompressFormat.JPEG, 100, os);


                        // image.setImageBitmap(signatureBitmap);
                    }
                    os.flush();
                    os.close();
                } catch (Exception e) {
                    Log.e(getClass().getSimpleName(), "Error writing bitmap", e);
                }

                siganaturePart = MultipartBody.Part.createFormData("sampleFile", userid + file.getName(),RequestBody.create(MediaType.parse("image/*"), file));

                uploadDigitalSignatureImageRequest(file);

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

        btnSelection.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

//                Intent send = new Intent( TechnicianSignature_BreakdownMR_Activity.this, ServicesActivity.class);
//                send.putExtra("value",value);
//                send.putExtra("feedback_details",feedback_details);
//                send.putExtra("feedback_group",feedback_group);
//                send.putExtra("bd_details",bd_dta);
//                send.putExtra("job_id",job_id);
//                send.putExtra("feedback_remark", feedback_remark);
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
//                send.putExtra("breakdown_service", breakdown_servies);
//                send.putExtra("tech_signature", uploadimagepath);
//                startActivity(send);




//                progressDialog = new ProgressDialog(TechnicianSignature_BreakdownMR_Activity.this);
//                progressDialog.setMessage("Please Wait ...");
//                progressDialog.setCancelable(false);
//                progressDialog.show();

//                SharedPreferences.Editor editor = sharedPreferences.edit();
//                editor.putBoolean( "mysignkey" , false);
//                editor.apply();

                serviceUserDetailsRequestResponse();

            }
        });

        btn_prev.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent send = new Intent( TechnicianSignature_BreakdownMR_Activity.this, BreakdownMRListOne_Activity.class);
                send.putExtra("value",value);
                send.putExtra("feedback_details",feedback_details);
                send.putExtra("feedback_group",feedback_group);
                send.putExtra("bd_details",bd_dta);
                send.putExtra("job_id",job_id);
                send.putExtra("feedback_remark", feedback_remark);
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
                send.putExtra("status", status);
                startActivity(send);
            }
        });

        iv_back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent send = new Intent( TechnicianSignature_BreakdownMR_Activity.this, BreakdownMRListOne_Activity.class);
                send.putExtra("value",value);
                send.putExtra("feedback_details",feedback_details);
                send.putExtra("feedback_group",feedback_group);
                send.putExtra("bd_details",bd_dta);
                send.putExtra("job_id",job_id);
                send.putExtra("feedback_remark", feedback_remark);
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
                send.putExtra("status", status);
                startActivity(send);
            }
        });
    }

    private void Job_status() {

        APIInterface apiInterface = RetrofitClient.getClient().create(APIInterface.class);
        Call<Job_statusResponse> call = apiInterface.CheckworkStatusCall(RestUtils.getContentType(), job_statusRequest());
        Log.w(TAG,"SignupResponse url  :%s"+" "+ call.request().url().toString());

        call.enqueue(new Callback<Job_statusResponse>() {
            @SuppressLint("LogNotTimber")
            @Override
            public void onResponse(@NonNull Call<Job_statusResponse> call, @NonNull retrofit2.Response<Job_statusResponse> response) {

                Log.w(TAG,"SignupResponse" + new Gson().toJson(response.body()));
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

            @Override
            public void onFailure(@NonNull Call<Job_statusResponse> call, @NonNull Throwable t) {
                Log.e("OTP", "--->" + t.getMessage());
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void alert() {

        AlertDialog.Builder mBuilder = new AlertDialog.Builder(TechnicianSignature_BreakdownMR_Activity.this);
        View mView = getLayoutInflater().inflate(R.layout.startjob_popup_layout, null);

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

        ll_pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                str_job_status = "Job Paused";

                alertDialog = new AlertDialog.Builder(context)
                        .setTitle("Are you sure to pause this job ?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Job_status_update();
                                createLocalValueCall();
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
                createLocalValueStopCall();
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
        Log.w(TAG,"loginRequest "+ new Gson().toJson(custom));
        return custom;
    }

    private void createLocalValueStopCall() {

        APIInterface apiInterface =  RetrofitClient.getClient().create((APIInterface.class));
        Call<SuccessResponse> call = apiInterface.createLocalValueCallBMR(com.triton.johnson_tap_app.utils.RestUtils.getContentType(),createstopLocalRequest());
        Log.w(VolleyLog.TAG,"Create Local Value url  :%s"+" "+ call.request().url().toString());

        call.enqueue(new Callback<SuccessResponse>() {
            @Override
            public void onResponse(Call<SuccessResponse> call, Response<SuccessResponse> response) {

                Log.w(VolleyLog.TAG,"SignupResponse" + new Gson().toJson(response.body()));

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

    private ServiceUserdetailsRequestResponse createstopLocalRequest() {

        ServiceUserdetailsRequestResponse local = new ServiceUserdetailsRequestResponse();
        local.setJobId(job_id);
        local.setUserMobileNo(se_user_mobile_no);
        local.setSMU_SCH_COMPNO(compno);
        local.setSMU_SCH_SERTYPE(sertype);
        local.setEngSignature("-");
        // local.setMrData(null);

        List<ServiceUserdetailsRequestResponse.MrDatum> mrData = new ArrayList<>();

        Cursor cursor = CommonUtil.dbUtil.getMR(job_id);

        Log.e("MR Count",""+ cursor.getCount());


        int i =0;
        if (cursor.getCount()>0 &&cursor.moveToFirst()) {

            do {
                str_Partid = cursor.getString(cursor.getColumnIndexOrThrow(DbHelper.MR_ID));
                str_Partname = cursor.getString(cursor.getColumnIndexOrThrow(DbHelper.PART_NAME));
                str_Partno = cursor.getString(cursor.getColumnIndexOrThrow(DbHelper.PART_NO));
                str_Quantity = cursor.getString(cursor.getColumnIndexOrThrow(DbHelper.QUANTITY));

                int mynum = i+1;

                ServiceUserdetailsRequestResponse.MrDatum mrDatum = new ServiceUserdetailsRequestResponse.MrDatum();
                mrDatum.setTitle("Mr"+mynum);
                if(mynum==1) {
                    //   mrDatum.setValue(mr1);
                    mrDatum.setPartno(str_Partno);
                    mrDatum.setPartname(str_Partname);
                    mrDatum.setReq(str_Quantity);
                } else if (mynum==2) {
                    // mrDatum.setValue(mr2);
                    mrDatum.setPartno(str_Partno);
                    mrDatum.setPartname(str_Partname);
                    mrDatum.setReq(str_Quantity);
                } else if (mynum==3) {

                    //   mrDatum.setValue(mr3);
                    mrDatum.setPartno(str_Partno);
                    mrDatum.setPartname(str_Partname);
                    mrDatum.setReq(str_Quantity);
                } else if (mynum==4) {

                    //   mrDatum.setValue(mr4);
                    mrDatum.setPartno(str_Partno);
                    mrDatum.setPartname(str_Partname);
                    mrDatum.setReq(str_Quantity);
                } else if (mynum==5) {

                    //   mrDatum.setValue(mr5);
                    mrDatum.setPartno(str_Partno);
                    mrDatum.setPartname(str_Partname);
                    mrDatum.setReq(str_Quantity);
                } else if (mynum==6) {

                    //  mrDatum.setValue(mr6);
                    mrDatum.setPartno(str_Partno);
                    mrDatum.setPartname(str_Partname);
                    mrDatum.setReq(str_Quantity);
                } else if (mynum==7) {

                    //  mrDatum.setValue(mr7);
                    mrDatum.setPartno(str_Partno);
                    mrDatum.setPartname(str_Partname);
                    mrDatum.setReq(str_Quantity);
                } else if (mynum==8) {

                    // mrDatum.setValue(mr8);
                    mrDatum.setPartno(str_Partno);
                    mrDatum.setPartname(str_Partname);
                    mrDatum.setReq(str_Quantity);
                } else if (mynum==9) {

                    //  mrDatum.setValue(mr9);
                    mrDatum.setPartno(str_Partno);
                    mrDatum.setPartname(str_Partname);
                    mrDatum.setReq(str_Quantity);
                } else if (mynum==10) {

                    //  mrDatum.setValue(mr10);
                    mrDatum.setPartno(str_Partno);
                    mrDatum.setPartname(str_Partname);
                    mrDatum.setReq(str_Quantity);
                }

                Log.e("Nish",""+mynum + mrDatum.getTitle() +" :"+ mrDatum.getValue());
                mrData.add(mrDatum);

                i++;

            }while (cursor.moveToNext());
        }

        local.setMrData(mrData);

        Log.w(VolleyLog.TAG,"Local Request "+ new Gson().toJson(local));
        return local;

    }

    private void createLocalValueCall() {
        APIInterface apiInterface =  RetrofitClient.getClient().create((APIInterface.class));
        Call<SuccessResponse> call = apiInterface.createLocalValueCallBMR(com.triton.johnson_tap_app.utils.RestUtils.getContentType(),createLocalRequest());
        Log.w(VolleyLog.TAG,"Create Local Value url  :%s"+" "+ call.request().url().toString());

        call.enqueue(new Callback<SuccessResponse>() {
            @Override
            public void onResponse(Call<SuccessResponse> call, Response<SuccessResponse> response) {

                Log.w(VolleyLog.TAG,"SignupResponse" + new Gson().toJson(response.body()));

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

    private ServiceUserdetailsRequestResponse createLocalRequest() {
        ServiceUserdetailsRequestResponse local = new ServiceUserdetailsRequestResponse();
        local.setJobId(job_id);
        local.setUserMobileNo(se_user_mobile_no);
        local.setSMU_SCH_COMPNO(compno);
        local.setSMU_SCH_SERTYPE(sertype);
        local.setEngSignature("-");
       // local.setMrData(null);

        List<ServiceUserdetailsRequestResponse.MrDatum> mrData = new ArrayList<>();

        Cursor cursor = CommonUtil.dbUtil.getMR(job_id);

        Log.e("MR Count",""+ cursor.getCount());


        int i =0;
        if (cursor.getCount()>0 &&cursor.moveToFirst()) {

            do {
                str_Partid = cursor.getString(cursor.getColumnIndexOrThrow(DbHelper.MR_ID));
                str_Partname = cursor.getString(cursor.getColumnIndexOrThrow(DbHelper.PART_NAME));
                str_Partno = cursor.getString(cursor.getColumnIndexOrThrow(DbHelper.PART_NO));
                str_Quantity = cursor.getString(cursor.getColumnIndexOrThrow(DbHelper.QUANTITY));

                int mynum = i+1;

                ServiceUserdetailsRequestResponse.MrDatum mrDatum = new ServiceUserdetailsRequestResponse.MrDatum();
                mrDatum.setTitle("Mr"+mynum);
                if(mynum==1) {
                    //   mrDatum.setValue(mr1);
                    mrDatum.setPartno(str_Partno);
                    mrDatum.setPartname(str_Partname);
                    mrDatum.setReq(str_Quantity);
                } else if (mynum==2) {
                    // mrDatum.setValue(mr2);
                    mrDatum.setPartno(str_Partno);
                    mrDatum.setPartname(str_Partname);
                    mrDatum.setReq(str_Quantity);
                } else if (mynum==3) {

                    //   mrDatum.setValue(mr3);
                    mrDatum.setPartno(str_Partno);
                    mrDatum.setPartname(str_Partname);
                    mrDatum.setReq(str_Quantity);
                } else if (mynum==4) {

                    //   mrDatum.setValue(mr4);
                    mrDatum.setPartno(str_Partno);
                    mrDatum.setPartname(str_Partname);
                    mrDatum.setReq(str_Quantity);
                } else if (mynum==5) {

                    //   mrDatum.setValue(mr5);
                    mrDatum.setPartno(str_Partno);
                    mrDatum.setPartname(str_Partname);
                    mrDatum.setReq(str_Quantity);
                } else if (mynum==6) {

                    //  mrDatum.setValue(mr6);
                    mrDatum.setPartno(str_Partno);
                    mrDatum.setPartname(str_Partname);
                    mrDatum.setReq(str_Quantity);
                } else if (mynum==7) {

                    //  mrDatum.setValue(mr7);
                    mrDatum.setPartno(str_Partno);
                    mrDatum.setPartname(str_Partname);
                    mrDatum.setReq(str_Quantity);
                } else if (mynum==8) {

                    // mrDatum.setValue(mr8);
                    mrDatum.setPartno(str_Partno);
                    mrDatum.setPartname(str_Partname);
                    mrDatum.setReq(str_Quantity);
                } else if (mynum==9) {

                    //  mrDatum.setValue(mr9);
                    mrDatum.setPartno(str_Partno);
                    mrDatum.setPartname(str_Partname);
                    mrDatum.setReq(str_Quantity);
                } else if (mynum==10) {

                    //  mrDatum.setValue(mr10);
                    mrDatum.setPartno(str_Partno);
                    mrDatum.setPartname(str_Partname);
                    mrDatum.setReq(str_Quantity);
                }

                Log.e("Nish",""+mynum + mrDatum.getTitle() +" :"+ mrDatum.getValue());
                mrData.add(mrDatum);

                i++;

            }while (cursor.moveToNext());
        }

        local.setMrData(mrData);

        Log.w(VolleyLog.TAG,"Local Request "+ new Gson().toJson(local));
        return local;
    }

    @SuppressLint("Range")
    public void getSign(String job_id, String myactivity) {
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

                Picasso.get().load(uploadimagepath).into(img_Siganture);


                Log.e("job" , ""+jon);
                Log.e("act" , ""+ss);

              ///  BitmapDrawable drawable = (BitmapDrawable) img_Siganture.getDrawable();
              //  Bitmap bitmap = drawable.getBitmap();
              ///  bitmap = Bitmap.createScaledBitmap(bitmap, 70, 70, true);
              //  photo.setImageBitmap(bitmap)
               // Bitmap image = ((BitmapDrawable)img_Siganture.getDrawable()).getBitmap();

               // File file = new File(signfile);
//                Bitmap bitmap = BitmapFactory.decodeFile(filePath);
                // img_Siganture.setImageBitmap(bitmap);
                //signaturePad.setSignatureBitmap(bitmap);
                // signatureBitmap = signaturePad.getSignatureBitmap();
            }while (cur.moveToNext());


        }
    }


    private void Job_status_update() {
        dialog = new Dialog(TechnicianSignature_BreakdownMR_Activity.this, R.style.NewProgressDialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.progroess_popup);
        dialog.show();

        APIInterface apiInterface = RetrofitClient.getClient().create(APIInterface.class);
        Call<Job_status_updateResponse> call = apiInterface.BreakdownMrJobWorkStatusResponseCall(com.triton.johnson_tap_app.utils.RestUtils.getContentType(), job_status_updateRequest());
        Log.w(VolleyLog.TAG,"SignupResponse url  :%s"+" "+ call.request().url().toString());

        call.enqueue(new Callback<Job_status_updateResponse>() {
            @SuppressLint("LogNotTimber")
            @Override
            public void onResponse(@NonNull Call<Job_status_updateResponse> call, @NonNull Response<Job_status_updateResponse> response) {

                Log.w(VolleyLog.TAG,"SignupResponse" + new Gson().toJson(response.body()));
                if (response.body() != null) {
                    dialog.dismiss();
                    message = response.body().getMessage();

                    if (200 == response.body().getCode()) {
                        dialog.dismiss();
                        if(response.body().getData() != null){

                            Log.d("msg",message);
                            dialog.dismiss();
                        }


                    } else {
                        Toasty.warning(getApplicationContext(),""+message,Toasty.LENGTH_LONG).show();
                        dialog.dismiss();
                    }
                }


            }

            @Override
            public void onFailure(@NonNull Call<Job_status_updateResponse> call, @NonNull Throwable t) {
                Log.e("OTP", "--->" + t.getMessage());
                dialog.dismiss();
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

    private void serviceUserDetailsRequestResponse() {
        dialog = new Dialog(TechnicianSignature_BreakdownMR_Activity.this, R.style.NewProgressDialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.progroess_popup);
        dialog.show();
        APIInterface apiInterface = RetrofitClient.getClient().create(APIInterface.class);
        Call<SuccessResponse> call = apiInterface.ServiceUserdetailsRequestsubmitMRListCall(RestUtils.getContentType(),submitRequest());
        Log.w(TAG,"url  :%s"+" "+ call.request().url().toString());

        call.enqueue(new Callback<SuccessResponse>() {
            @Override
            public void onResponse(Call<SuccessResponse> call, Response<SuccessResponse> response) {


                Log.w(TAG, "UserDetailsRequestResponse" + new Gson().toJson(response.body()));


                if (response.body() != null) {
                    dialog.dismiss();
                    String message = response.body().getMessage();
                    if(response.body().getCode() == 200){
                        dialog.dismiss();
//                        servicedetailsrequestbean = response.body().getData();

                        Log.w(TAG,"url  :%s"+" "+ call.request().url().toString());
                        CommonUtil.dbUtil.reportdelete(job_id);

                        CommonUtil.dbUtil.deleteSign(job_id,myactivity);

                        CommonUtil.dbUtil.deleteBreakdownMR(job_id,myactivity);

                        Toasty.success(TechnicianSignature_BreakdownMR_Activity.this, "Added Successfully", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                        Intent send = new Intent( TechnicianSignature_BreakdownMR_Activity.this,ServicesActivity.class);
                        startActivity(send);

                    }else{
//                          showErrorLoading(response.body().getMessage());
                        dialog.dismiss();
                    }

                }
            }

            @Override
            public void onFailure(Call<SuccessResponse> call, Throwable t) {
                dialog.dismiss();
            }
        });

    }

    private ServiceUserdetailsRequestResponse submitRequest() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm aa", Locale.getDefault());
        String currentDateandTime = sdf.format(new Date());

        ServiceUserdetailsRequestResponse submitRequest = new ServiceUserdetailsRequestResponse();

        submitRequest.setJobId(job_id);
        submitRequest.setUserMobileNo(se_user_mobile_no);
        submitRequest.setEngSignature(uploadimagepath);
        submitRequest.setSMU_SCH_COMPNO(compno);
        submitRequest.setSMU_SCH_SERTYPE(sertype);
        Log.e("CompNo",""+compno);
        Log.e("SertYpe", ""+sertype);

        List<ServiceUserdetailsRequestResponse.MrDatum> mrData = new ArrayList<>();


        Cursor cursor = CommonUtil.dbUtil.getMR(job_id);

        Log.e("MR Count",""+ cursor.getCount());

        int i =0;
        if (cursor.getCount()>0 &&cursor.moveToFirst()) {

            do {
                str_Partid = cursor.getString(cursor.getColumnIndexOrThrow(DbHelper.MR_ID));
                str_Partname = cursor.getString(cursor.getColumnIndexOrThrow(DbHelper.PART_NAME));
                str_Partno = cursor.getString(cursor.getColumnIndexOrThrow(DbHelper.PART_NO));
                str_Quantity = cursor.getString(cursor.getColumnIndexOrThrow(DbHelper.QUANTITY));

                int mynum = i+1;

                ServiceUserdetailsRequestResponse.MrDatum mrDatum = new ServiceUserdetailsRequestResponse.MrDatum();
                mrDatum.setTitle("Mr"+mynum);
                if(mynum==1) {
                    mrDatum.setValue(mr1);
                    mrDatum.setPartno(str_Partno);
                    mrDatum.setReq(str_Quantity);
                } else if (mynum==2) {
                    mrDatum.setValue(mr2);
                    mrDatum.setPartno(str_Partno);
                    mrDatum.setReq(str_Quantity);
                } else if (mynum==3) {

                    mrDatum.setValue(mr3);
                    mrDatum.setPartno(str_Partno);
                    mrDatum.setReq(str_Quantity);
                } else if (mynum==4) {

                    mrDatum.setValue(mr4);
                    mrDatum.setPartno(str_Partno);
                    mrDatum.setReq(str_Quantity);
                } else if (mynum==5) {

                    mrDatum.setValue(mr5);
                    mrDatum.setPartno(str_Partno);
                    mrDatum.setReq(str_Quantity);
                } else if (mynum==6) {

                    mrDatum.setValue(mr6);
                    mrDatum.setPartno(str_Partno);
                    mrDatum.setReq(str_Quantity);
                } else if (mynum==7) {

                    mrDatum.setValue(mr7);
                    mrDatum.setPartno(str_Partno);
                    mrDatum.setReq(str_Quantity);
                } else if (mynum==8) {

                    mrDatum.setValue(mr8);
                    mrDatum.setPartno(str_Partno);
                    mrDatum.setReq(str_Quantity);
                } else if (mynum==9) {

                    mrDatum.setValue(mr9);
                    mrDatum.setPartno(str_Partno);
                    mrDatum.setReq(str_Quantity);
                } else if (mynum==10) {

                    mrDatum.setValue(mr10);
                    mrDatum.setPartno(str_Partno);
                    mrDatum.setReq(str_Quantity);
                }

                Log.e("Nish",""+mynum + mrDatum.getTitle() +" :"+ mrDatum.getValue());
                mrData.add(mrDatum);

                i++;


            }while (cursor.moveToNext());
        }

        submitRequest.setMrData(mrData);
        Log.w(TAG," serviceUserDetailsRequestResponse"+ job_id);
        Log.w(TAG," serviceUserDetailsRequestResponse"+ se_user_mobile_no);
        Log.w(TAG," serviceUserDetailsRequestResponse"+ uploadimagepath);

        Log.w(TAG," serviceUserDetailsRequestResponse"+ new Gson().toJson(submitRequest));
        return submitRequest;

    }

    private void  uploadDigitalSignatureImageRequest(File file) {

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

//                        SharedPreferences.Editor editor = sharedPreferences.edit();
//                        editor.putString("ImagePath" ,""+uploadimagepath );
//                        editor.apply();

                        Log.d("image",uploadimagepath);

                      //  image.setImageURI(Uri.parse(uploadimagepath));
                        if (uploadimagepath != null) {

                            //   Picasso.get().load(uploadimagepath).into(image);

                            Toast.makeText(TechnicianSignature_BreakdownMR_Activity.this, "Signature Saved", Toast.LENGTH_SHORT).show();
                            img_Siganture.setVisibility(View.GONE);
                            CommonUtil.dbUtil.addEngSign(job_id,myactivity,uploadimagepath,file,"0");
                            Log.e("a" , "" + job_id);
                            Log.e("b", "" + myactivity);
                            Log.e("c" , "" + uploadimagepath);
                            Log.e("d" , "" + file);
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

    @Override
    public void onBackPressed() {
        Intent send = new Intent( TechnicianSignature_BreakdownMR_Activity.this, BreakdownMRListOne_Activity.class);
        send.putExtra("value",value);
        send.putExtra("feedback_details",feedback_details);
        send.putExtra("feedback_group",feedback_group);
        send.putExtra("bd_details",bd_dta);
        send.putExtra("job_id",job_id);
        send.putExtra("feedback_remark", feedback_remark);
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
        send.putExtra("status", status);
        startActivity(send);
    }
}