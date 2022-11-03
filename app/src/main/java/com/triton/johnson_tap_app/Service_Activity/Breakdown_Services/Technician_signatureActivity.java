package com.triton.johnson_tap_app.Service_Activity.Breakdown_Services;

import static android.content.ContentValues.TAG;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.ContentInfo;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.github.gcacace.signaturepad.views.SignaturePad;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
import com.triton.johnson_tap_app.Db.CommonUtil;
import com.triton.johnson_tap_app.Db.DbHelper;
import com.triton.johnson_tap_app.Db.DbUtil;
import com.triton.johnson_tap_app.R;
import com.triton.johnson_tap_app.api.APIInterface;
import com.triton.johnson_tap_app.api.RetrofitClient;
import com.triton.johnson_tap_app.requestpojo.Job_status_updateRequest;
import com.triton.johnson_tap_app.responsepojo.FileUploadResponse;
import com.triton.johnson_tap_app.responsepojo.RetriveLocalValueBRResponse;
import com.triton.johnson_tap_app.utils.RestUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Timer;
import java.util.TimerTask;

import es.dmoral.toasty.Toasty;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Technician_signatureActivity extends AppCompatActivity {

    SignaturePad signaturePad;
    Button saveButton, clearButton;
    private Button btnSelection,btn_prev;
    MultipartBody.Part siganaturePart;
    String userid;
    ImageView image,iv_back,img_Siganture;
    private String uploadimagepath = "";
    String value="",job_id,feedback_group,feedback_details,bd_dta,feedback_remark,mr1,mr2,mr3,mr4,mr5,mr6,mr7,mr8,mr9,mr10,breakdown_servies,str_tech_signature="",message;
    ProgressDialog progressDialog;
    Bitmap signatureBitmap;
    SharedPreferences sharedPreferences;
    String se_id,se_user_mobile_no,se_user_name,service_title,signfile,status,compno,sertype;
    Context context;

    @SuppressLint("MissingInflatedId")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_technician_signature);
        context = this;

        CommonUtil.dbUtil = new DbUtil(context);
        CommonUtil.dbUtil.open();
        CommonUtil.dbHelper = new DbHelper(context);

        signaturePad = (SignaturePad)findViewById(R.id.signaturePad);
        saveButton = (Button)findViewById(R.id.saveButton);
        clearButton = (Button)findViewById(R.id.clearButton);
        btnSelection = (Button) findViewById(R.id.btn_next);
        btn_prev = (Button) findViewById(R.id.btn_show);
        image = (ImageView)findViewById(R.id.image);
        img_Siganture = (ImageView)findViewById(R.id.img_sign);
        iv_back = (ImageView) findViewById(R.id.iv_back);

        btn_prev.setBackgroundResource(R.drawable.blue_button_background_with_radius);
        btn_prev.setTextColor(getResources().getColor(R.color.white));
        btn_prev.setEnabled(true);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        se_id = sharedPreferences.getString("_id", "default value");
        se_user_mobile_no = sharedPreferences.getString("user_mobile_no", "default value");
        se_user_name = sharedPreferences.getString("user_name", "default value");
        job_id = sharedPreferences.getString("job_id", "default value");
        service_title = sharedPreferences.getString("service_title", "default value");
        compno = sharedPreferences.getString("compno","123");
        sertype = sharedPreferences.getString("sertype","123");
        uploadimagepath = sharedPreferences.getString("tech_sign","-");
        Log.e("JobID",""+job_id);
        Log.e("Name",""+service_title);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            value = extras.getString("value");
            status = extras.getString("status");
            Log.e("Status",status);
        }
        if (extras != null) {
          //  feedback_group = extras.getString("feedback_group");
        }

        if (extras != null) {
          //  bd_dta = extras.getString("bd_details");
        }

        if (extras != null) {
         //   job_id = extras.getString("job_id");
        }

        if (extras != null) {
          //  feedback_details = extras.getString("feedback_details");
        }

        if (extras != null) {
          //  feedback_remark = extras.getString("feedback_remark");
        }

        if (extras != null) {
          //  mr1 = extras.getString("mr1");
        }
        if (extras != null) {
           // mr2 = extras.getString("mr2");
        }
        if (extras != null) {
           // mr3 = extras.getString("mr3");
        }
        if (extras != null) {
          //  mr4 = extras.getString("mr4");
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
            Log.e("A",breakdown_servies);
        }
        if (extras != null) {
//            str_tech_signature = extras.getString("tech_signature");
//            Picasso.get().load(str_tech_signature).into(image);
        }

        if (status.equals("paused")) {
            retrive_LocalValue();

         //   Picasso.get().load(uploadimagepath).into(img_Siganture);

        }else{
            Log.e("Way","New");
            getSign(job_id, service_title);
        }

        saveButton.setEnabled(false);
        clearButton.setEnabled(false);

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

                progressDialog = new ProgressDialog(Technician_signatureActivity.this);
                progressDialog.setMessage("Please Wait Image Upload ...");
                progressDialog.setCancelable(false);
                progressDialog.show();

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

                if (signatureBitmap == null && uploadimagepath == null) {
                    Toast.makeText(Technician_signatureActivity.this, "Please Drop Signature", Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent send = new Intent(Technician_signatureActivity.this, Customer_Details_BreakdownActivity.class);
                send.putExtra("value", value);
                send.putExtra("feedback_details", feedback_details);
                send.putExtra("feedback_group", feedback_group);
                send.putExtra("bd_details", bd_dta);
                send.putExtra("job_id", job_id);
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
                send.putExtra("breakdown_service", breakdown_servies);
                send.putExtra("tech_signature", uploadimagepath);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("tech_sign", uploadimagepath);
                    editor.apply();
                    Log.e("Tech Sign",""+uploadimagepath);
                send.putExtra("status",status);
                startActivity(send);
            }
            }
        });

        btn_prev.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                onBackPressed();
//                if(uploadimagepath.equals("")) {
//                    Intent send = new Intent(Technician_signatureActivity.this, BD_StatusActivity.class);
//                    send.putExtra("value", value);
//                    send.putExtra("feedback_details", feedback_details);
//                    send.putExtra("feedback_group", feedback_group);
//                    send.putExtra("bd_details", bd_dta);
//                    send.putExtra("job_id", job_id);
//                    send.putExtra("feedback_remark", feedback_remark);
//                    startActivity(send);
//                }
//                else {
//                    Intent send = new Intent(Technician_signatureActivity.this, BD_StatusActivity.class);
//                    send.putExtra("value", value);
//                    send.putExtra("feedback_details", feedback_details);
//                    send.putExtra("feedback_group", feedback_group);
//                    send.putExtra("bd_details", bd_dta);
//                    send.putExtra("job_id", job_id);
//                    send.putExtra("feedback_remark", feedback_remark);
//                    send.putExtra("tech_signature", uploadimagepath);
//                    startActivity(send);
//                }
            }
        });

        iv_back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                onBackPressed();
//                if(uploadimagepath.equals("")) {
//                    Intent send = new Intent(Technician_signatureActivity.this, BD_StatusActivity.class);
//                    send.putExtra("value", value);
//                    send.putExtra("feedback_details", feedback_details);
//                    send.putExtra("feedback_group", feedback_group);
//                    send.putExtra("bd_details", bd_dta);
//                    send.putExtra("job_id", job_id);
//                    send.putExtra("feedback_remark", feedback_remark);
//                    startActivity(send);
//                }
//                else {
//                    Intent send = new Intent(Technician_signatureActivity.this, BD_StatusActivity.class);
//                    send.putExtra("value", value);
//                    send.putExtra("feedback_details", feedback_details);
//                    send.putExtra("feedback_group", feedback_group);
//                    send.putExtra("bd_details", bd_dta);
//                    send.putExtra("job_id", job_id);
//                    send.putExtra("feedback_remark", feedback_remark);
//                    send.putExtra("tech_signature", uploadimagepath);
//                    startActivity(send);
//                }
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

                            uploadimagepath = response.body().getData().getTech_signature();
                            Picasso.get().load(uploadimagepath).into(img_Siganture);
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

    @SuppressLint("Range")
    private void getSign(String job_id, String service_title) {
        Log.e("Sign", "Hi");
        Log.e("Nish",""+job_id);
        Log.e("Nish",""+service_title);
        Cursor cur =  CommonUtil.dbUtil.getEngSign(job_id,service_title);
        Cursor curs =  CommonUtil.dbUtil.getEngSign();
        Log.e("ENg Sign", " " + cur.getCount());

        if (cur.getCount()>0 && cur.moveToLast()){

          //  do{
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
        //    }while (cur.moveToNext());


        }
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

                        Log.d("image",uploadimagepath);
                        if (uploadimagepath != null) {

                         //   Picasso.get().load(uploadimagepath).into(image);

                            Toast.makeText(Technician_signatureActivity.this, "Signature Saved", Toast.LENGTH_SHORT).show();
                            img_Siganture.setVisibility(View.GONE);
                            CommonUtil.dbUtil.addEngSign(job_id,service_title,uploadimagepath,file,"0");
                            Log.e("a" , "" + job_id);
                            Log.e("b", "" + service_title);
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

    @Override
    public void onBackPressed() {

//       super.onBackPressed();
        Intent intent = new Intent(context,BD_StatusActivity.class);
        intent.putExtra("status",status);
        intent.putExtra("breakdown_service", breakdown_servies);
        startActivity(intent);
    }
}