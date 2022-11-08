package com.triton.johnson_tap_app.Service_Activity.PartsReplacementACK;

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
import androidx.annotation.Nullable;
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
import com.triton.johnson_tap_app.Service_Activity.ServicesActivity;
import com.triton.johnson_tap_app.api.APIInterface;
import com.triton.johnson_tap_app.api.RetrofitClient;
import com.triton.johnson_tap_app.requestpojo.ACKService_SubmitRequest;
import com.triton.johnson_tap_app.requestpojo.Job_Details_TextRequest;
import com.triton.johnson_tap_app.requestpojo.Job_statusRequest;
import com.triton.johnson_tap_app.requestpojo.Job_status_updateRequest;
import com.triton.johnson_tap_app.responsepojo.FileUploadResponse;
import com.triton.johnson_tap_app.responsepojo.Job_Details_TextResponse;
import com.triton.johnson_tap_app.responsepojo.Job_statusResponse;
import com.triton.johnson_tap_app.responsepojo.Job_status_updateResponse;
import com.triton.johnson_tap_app.responsepojo.SuccessResponse;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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

public class CustomerAcknowledgement_ACKServiceActivity extends AppCompatActivity {

    SignaturePad signaturePad;
    Button btn_Save,btn_Clear, btn_Prev, btn_Submit;
    ImageView img_Signature,img_Back,img_Pause;
    TextView txt_JobDetails;
    Context context;
    String status,job_id,se_user_mobile_no, se_user_name, se_id,service_title;
    Dialog dialog;
    SharedPreferences sharedPreferences;
    ProgressDialog progressDialog;
    Bitmap signatureBitmap;
    MultipartBody.Part siganaturePart;
    String userid, uploadimagepath = "",str_Techsign,str_CustAck,str_job_status,message,service_type,str_ACKCompno;
    String myactivity = "Parts Replacement ACK",signfile;

    AlertDialog alertDialog;

    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_customeracknowledgement_lrservice);
        context = this;

        CommonUtil.dbUtil = new DbUtil(context);
        CommonUtil.dbUtil.open();
        CommonUtil.dbHelper = new DbHelper(context);

        signaturePad = findViewById(R.id.signaturePad);
        btn_Save = findViewById(R.id.btn_save);
        btn_Clear = findViewById(R.id.btn_clear);
        btn_Prev = findViewById(R.id.btn_prev);
        btn_Submit = findViewById(R.id.btn_submit);
        img_Back = findViewById(R.id.img_back);
        img_Pause = findViewById(R.id.img_paused);
        img_Signature = findViewById(R.id.image1);
        txt_JobDetails = findViewById(R.id.txt_jobdetails);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            //  service_title = extras.getString("service_title");
            status = extras.getString("status");
            //   Log.e("Name",":" + service_title);
            Log.e("Status", "" + status);
            job_id = extras.getString("job_id");
            Log.e("jobID", "" + job_id);
            str_Techsign = extras.getString("tech_signature");
            str_CustAck = extras.getString("cust_ack");
            // Picasso.get().load(str_CustAck).into((Target) signaturePad);
        }


        sharedPreferences =  PreferenceManager.getDefaultSharedPreferences(this);
        se_id = sharedPreferences.getString("_id", "default value");
        se_user_mobile_no = sharedPreferences.getString("user_mobile_no", "default value");
        se_user_name = sharedPreferences.getString("user_name", "default value");
        service_title = sharedPreferences.getString("service_title", "Services");
        service_type = sharedPreferences.getString("service_type","PSM");
        str_ACKCompno = sharedPreferences.getString("ackcompno","123");

        Log.e("name",""+service_title);
        Log.e("Mobile", ""+ se_user_mobile_no);
        Log.e("Type", ""+ service_type);
        Log.e("ACKCompno","" +str_ACKCompno);


        Job_status();

        getSign(job_id,myactivity);


        job_details_in_text();

        btn_Save.setEnabled(false);
        btn_Clear.setEnabled(false);

        signaturePad.setOnSignedListener(new SignaturePad.OnSignedListener() {
            @Override
            public void onStartSigning() {

            }

            @Override
            public void onSigned() {
                btn_Save.setEnabled(true);
                btn_Clear.setEnabled(true);
            }

            @Override
            public void onClear() {
                btn_Save.setEnabled(false);
                btn_Clear.setEnabled(false);
            }
        });


        btn_Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                progressDialog = new ProgressDialog(context);
                progressDialog.setMessage("Please Wait Image Upload ...");
                progressDialog.setCancelable(false);
                progressDialog.show();

                signatureBitmap = signaturePad.getSignatureBitmap();
                Log.w(TAG, "signatureBitmap" + signatureBitmap);
                File file = new File(getFilesDir(), "Technician Signature" + ".jpg");
                Log.e("Nish",""+file);

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

                siganaturePart = MultipartBody.Part.createFormData("sampleFile", userid + file.getName(), RequestBody.create(MediaType.parse("image/*"), file));

                uploadDigitalSignatureImageRequest(file);

                long delayInMillis = 15000;
                Timer timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        progressDialog.dismiss();
                    }
                }, delayInMillis);
               // Toast.makeText(context,"Signature Saved",Toast.LENGTH_SHORT).show();
            }
        });

        btn_Clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signaturePad.clear();
            }
        });

        img_Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent send = new Intent(context, TechnicianSignature_ACKServiceActivity.class);
                // send.putExtra("service_title",service_title);
                send.putExtra("status" , status);
                send.putExtra("status" , status);
                send.putExtra("job_id",job_id);
                send.putExtra("tech_signature", str_Techsign);
                send.putExtra("cust_ack", uploadimagepath);
                startActivity(send);
            }
        });

        btn_Prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent send = new Intent(context, TechnicianSignature_ACKServiceActivity.class);
                // send.putExtra("service_title",service_title);
                send.putExtra("status" , status);
                send.putExtra("status" , status);
                send.putExtra("job_id",job_id);
                send.putExtra("tech_signature", str_Techsign);
                send.putExtra("cust_ack", uploadimagepath);
                startActivity(send);
            }
        });

        btn_Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (signatureBitmap == null) {
                    Toast.makeText(context, "Please Drop Signature", Toast.LENGTH_SHORT).show();
                }
                else {

                    submitAddResponseCall();
                      }
                }
        });

    }

    private void Job_status() {

        APIInterface apiInterface = RetrofitClient.getClient().create(APIInterface.class);
        Call<Job_statusResponse> call = apiInterface.CheckworkStatusACKCall(com.triton.johnson_tap_app.utils.RestUtils.getContentType(), job_statusRequest());
        Log.w(VolleyLog.TAG,"SignupResponse url  :%s"+" "+ call.request().url().toString());

        call.enqueue(new Callback<Job_statusResponse>() {

            @SuppressLint("LogNotTimber")
            @Override
            public void onResponse(@NonNull Call<Job_statusResponse> call, @NonNull retrofit2.Response<Job_statusResponse> response) {

                Log.e("Hi","OnResponse");

                Log.w(VolleyLog.TAG,"Job Status Response" + new Gson().toJson(response.body()));
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

        AlertDialog.Builder mBuilder = new AlertDialog.Builder(context);
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
        custom.setSMU_ACK_COMPNO(str_ACKCompno);
        Log.w(VolleyLog.TAG,"loginRequest "+ new Gson().toJson(custom));
        return custom;
    }

    @SuppressLint("Range")
    private void getSign(String job_id, String myactivity) {

        Log.e("Sign", "Hi");
        Log.e("Nish",""+job_id);
        Log.e("Nish",""+myactivity);
        Cursor cur =  CommonUtil.dbUtil.getCustAck(job_id,myactivity);
        Log.e("ENg Sign", " " + cur.getCount());

        if (cur.getCount()>0 && cur.moveToFirst()){

            do{
                signfile = cur.getString(cur.getColumnIndex(DbHelper.CUSTACK_FILE));
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

    private void submitAddResponseCall() {

        dialog = new Dialog(context, R.style.NewProgressDialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.progroess_popup);
        dialog.show();


        APIInterface apiInterface = RetrofitClient.getClient().create(APIInterface.class);
        Call<SuccessResponse> call = apiInterface.submitAddACKResponseCall(RestUtils.getContentType(),submitACKRequest());
        Log.w(TAG,"url  :%s"+" "+ call.request().url().toString());

        call.enqueue(new Callback<SuccessResponse>() {
            @SuppressLint("LogNotTimber")
            @Override
            public void onResponse(@NotNull Call<SuccessResponse> call, @NotNull Response<SuccessResponse> response) {

                Log.w(TAG, " Submit Response" + new Gson().toJson(response.body()));
                Log.w(TAG,"url  :%s"+" "+ call.request().url().toString());

                if (response.body() != null) {
                    dialog.dismiss();
                    if(response.body().getCode() == 200){
                        dialog.dismiss();
                        Log.w(TAG,"url  :%s"+" "+ call.request().url().toString());

                        Log.w(TAG,"dddd %s"+" "+ response.body().getData().toString());


                        Toasty.success(context, "Added Successfully", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                        CommonUtil.dbUtil.deleteSign(job_id,myactivity);

                        CommonUtil.dbUtil.deleteCustAck(job_id,myactivity);
                        Intent send = new Intent( context, ServicesActivity.class);
                        startActivity(send);

                    }else{
                        //  showErrorLoading(response.body().getMessage());
                        dialog.dismiss();
                    }

                }
            }

            @Override
            public void onFailure(Call<SuccessResponse> call, Throwable t) {
                Log.w(TAG,"AddLocationResponseflr"+t.getMessage());
                dialog.dismiss();
            }
        });

    }

    private ACKService_SubmitRequest submitACKRequest() {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm aa", Locale.getDefault());
        String currentDateandTime = sdf.format(new Date());

        ACKService_SubmitRequest request = new ACKService_SubmitRequest();
        request.setJobId(job_id);
        request.setUserId(se_user_mobile_no);
        request.setServiceType(service_type);
        request.setTechSignature(str_Techsign);
        request.setCustomerAcknowledgement(uploadimagepath);
        request.setSMU_ACK_COMPNO(str_ACKCompno);
        request.set_id(se_id);
        Log.w(TAG," Submit Request"+ new Gson().toJson(request));
        return request;

    }

    private void uploadDigitalSignatureImageRequest(File file) {
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

                            Toast.makeText(context, "Signature Saved", Toast.LENGTH_SHORT).show();
                            img_Signature.setVisibility(View.GONE);
                            CommonUtil.dbUtil.addCustAck(job_id,myactivity,uploadimagepath,file);
                            Log.e("a" , "" + job_id);
                            Log.e("b", "" + myactivity);
                            Log.e("c" , "" + uploadimagepath);
                            Log.e("d" , "" + file);
                            progressDialog.dismiss();

                        }
                    }
                    else{

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

    private void job_details_in_text() {

        APIInterface apiInterface = RetrofitClient.getClient().create(APIInterface.class);
        Call<Job_Details_TextResponse> call = apiInterface.jobdetailsACK(RestUtils.getContentType(), custom_detailsRequest());
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

                            String jobdetails = response.body().getData().getText_value();

                            Log.e("Hi" , "" + jobdetails);

                            txt_JobDetails.setText(jobdetails);
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
        custom.setSMU_ACK_COMPNO(str_ACKCompno);
        Log.w(VolleyLog.TAG,"loginRequest "+ new Gson().toJson(custom));
        return custom;
    }

    private void createLocalValueStopCall() {

        APIInterface apiInterface =  RetrofitClient.getClient().create((APIInterface.class));
        Call<SuccessResponse> call = apiInterface.createLocalValueCallACK(com.triton.johnson_tap_app.utils.RestUtils.getContentType(),createLocalStopRequest());
        Log.w(VolleyLog.TAG,"Create Local Value url  :%s"+" "+ call.request().url().toString());

        call.enqueue(new Callback<SuccessResponse>() {
            @Override
            public void onResponse(Call<SuccessResponse> call, Response<SuccessResponse> response) {

                Log.e("Hi","OnSucess");

                Log.w(VolleyLog.TAG,"Local Response" + new Gson().toJson(response.body()));

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

            }
        });

    }

    private ACKService_SubmitRequest createLocalStopRequest() {
        ACKService_SubmitRequest request = new ACKService_SubmitRequest();
        request.setJobId(job_id);
        request.setUserId(se_user_mobile_no);
        request.setServiceType(service_type);
        request.setTechSignature(str_Techsign);
        request.setCustomerAcknowledgement(uploadimagepath);
        request.setSMU_ACK_COMPNO(str_ACKCompno);
        request.set_id(se_id);
        // local.setMrData(null);
        Log.w(VolleyLog.TAG,"Local Request "+ new Gson().toJson(request));
        return request;
    }

    private void createLocalValueCall() {

        APIInterface apiInterface =  RetrofitClient.getClient().create((APIInterface.class));
        Call<SuccessResponse> call = apiInterface.createLocalValueCallACK(com.triton.johnson_tap_app.utils.RestUtils.getContentType(),createLocalRequest());
        Log.w(VolleyLog.TAG,"Create Local Value url  :%s"+" "+ call.request().url().toString());

        call.enqueue(new Callback<SuccessResponse>() {
            @Override
            public void onResponse(Call<SuccessResponse> call, Response<SuccessResponse> response) {

                Log.e("Hi","OnSucess");

                Log.w(VolleyLog.TAG,"Local Response" + new Gson().toJson(response.body()));

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

            }
        });
    }

    private ACKService_SubmitRequest createLocalRequest() {
        ACKService_SubmitRequest request = new ACKService_SubmitRequest();
        request.setJobId(job_id);
        request.setUserId(se_user_mobile_no);
        request.setServiceType(service_type);
        request.setTechSignature(str_Techsign);
        request.setCustomerAcknowledgement(uploadimagepath);
        request.setSMU_ACK_COMPNO(str_ACKCompno);
        request.set_id(se_id);
        // local.setMrData(null);
        Log.w(VolleyLog.TAG,"Local Request "+ new Gson().toJson(request));
        return request;
    }

    private void Job_status_update() {

        APIInterface apiInterface = RetrofitClient.getClient().create(APIInterface.class);
        Call<Job_status_updateResponse> call = apiInterface.job_status_updateACKResponseCall(com.triton.johnson_tap_app.utils.RestUtils.getContentType(), job_status_updateRequest());
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
        custom.setSMU_ACK_COMPNO(str_ACKCompno);
        Log.w(VolleyLog.TAG,"Request "+ new Gson().toJson(custom));
        return custom;
    }

    public void onBackPressed() {
        Intent send = new Intent(context, TechnicianSignature_ACKServiceActivity.class);
        // send.putExtra("service_title",service_title);
        send.putExtra("status" , status);
        send.putExtra("status" , status);
        send.putExtra("job_id",job_id);
        send.putExtra("tech_signature", str_Techsign);
        send.putExtra("cust_ack", uploadimagepath);
        startActivity(send);
    }
}
