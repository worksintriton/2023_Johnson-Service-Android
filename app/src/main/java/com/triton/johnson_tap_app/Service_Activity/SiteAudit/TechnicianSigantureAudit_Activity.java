package com.triton.johnson_tap_app.Service_Activity.SiteAudit;

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
import com.github.gcacace.signaturepad.views.SignaturePad;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
import com.triton.johnson_tap_app.Db.CommonUtil;
import com.triton.johnson_tap_app.Db.DbHelper;
import com.triton.johnson_tap_app.Db.DbUtil;
import com.triton.johnson_tap_app.GetFieldListResponse;
import com.triton.johnson_tap_app.R;
import com.triton.johnson_tap_app.RestUtils;
import com.triton.johnson_tap_app.Service_Activity.BreakdownMRApprovel.TechnicianSignature_BreakdownMR_Activity;
import com.triton.johnson_tap_app.Service_Activity.Breakdown_Services.Customer_AcknowledgementActivity;
import com.triton.johnson_tap_app.Service_Activity.ServicesActivity;
import com.triton.johnson_tap_app.api.APIInterface;
import com.triton.johnson_tap_app.api.RetrofitClient;
import com.triton.johnson_tap_app.requestpojo.AuditRequest;
import com.triton.johnson_tap_app.requestpojo.Job_statusRequest;
import com.triton.johnson_tap_app.requestpojo.Job_status_updateRequest;
import com.triton.johnson_tap_app.requestpojo.ServiceUserdetailsRequestResponse;
import com.triton.johnson_tap_app.responsepojo.FileUploadResponse;
import com.triton.johnson_tap_app.responsepojo.Job_statusResponse;
import com.triton.johnson_tap_app.responsepojo.Job_status_updateResponse;
import com.triton.johnson_tap_app.responsepojo.SuccessResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
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

public class TechnicianSigantureAudit_Activity extends AppCompatActivity {


    String myactivity = "AUDIT";
    SharedPreferences sharedPreferences;
    Context context;
    String job_id,status,se_id,se_user_mobile_no,se_user_name,str_job_status,service_title,message;
    SignaturePad signaturePad;
    Button saveButton,clearButton,btnSelection,btn_prev;
    ImageView img_Siganture,iv_back,iv_pause;
    ProgressDialog progressDialog;
    Dialog dialog;
    Bitmap signatureBitmap;
    MultipartBody.Part siganaturePart;
    String userid,value;
    String uploadimagepath = "",signfile;
    String form1_value;
    String form1_name;
    String form1_comments;
    String form1_cat_id;
    String form1_group_id;
    String form_remarks;
    String osacompno;
    String jsonString = "",str_Partid,str_Partname,str_Partno,str_Quantity;
    AlertDialog alertDialog;
    List<AuditRequest.MrDatum> mrData = new ArrayList<>();
    List<GetFieldListResponse.DataBean> FieldData;
    GetFieldListResponse.DataBean dataBean;
    //String checklist = new ArrayList<?>();

    ArrayList<String> mmyvalue;
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

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            status = extras.getString("status");
            Log.e("Status", "" + status);
        }

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        se_id = sharedPreferences.getString("_id", "default value");
        se_user_mobile_no = sharedPreferences.getString("user_mobile_no", "default value");
        se_user_name = sharedPreferences.getString("user_name", "default value");
        job_id =sharedPreferences.getString("jobid","L-1234");
        service_title = sharedPreferences.getString("service_title", "Services");
        osacompno = sharedPreferences.getString("osacompno","ADT2020202020");
        value = sharedPreferences.getString("value","value");
        Log.e("Value", "" + value);

        Log.e("Name", "" + service_title);
        Log.e("Mobile", ""+ se_user_mobile_no);
        Log.e("Jobid",""+ job_id);
        Log.e("osocompno",""+ osacompno);

        form1_value = sharedPreferences.getString("Form1_value","124");
        form1_name = sharedPreferences.getString("Form1_name","124");
        form1_comments = sharedPreferences.getString("Form1_comments","124");
        form1_cat_id = sharedPreferences.getString("Form1_cat_id","124");
        form1_group_id = sharedPreferences.getString("Form1_group_id","124");
        form_remarks = sharedPreferences.getString("Field_remarks","abc");

        Log.e("Value",""+form1_value);
        Log.e("Name",""+form1_name);
        Log.e("Comments",""+form1_comments);
        Log.e("catid",""+form1_cat_id);
        Log.e("groupid",""+form1_group_id);
        Log.e("remarks",""+form_remarks);
        Log.e("osocompno",""+ osacompno);

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


        strRemarks = form_remarks.split(",");
        remarks = new ArrayList<String>(
                Arrays.asList(strRemarks));


        Log.e("Nish",""+mmyvalue);
        Log.e("Nish",""+ remarks);
        Log.e("Remarks Size",""+ remarks.size());

//        List = sharedPreferences.getString("List","1");
        Log.e("Jobid", "" +job_id);

//        String[] namesList = form1_value.split(",");
//        for (int i=0; i<=20; i++){
//
//        }

       // alert();

        jobStatuscall();

        getSign(job_id,myactivity);


        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });


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

                progressDialog = new ProgressDialog(context);
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


            }
        });

        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signaturePad.clear();
            }
        });

        btn_prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                onBackPressed();
            }
        });

        btnSelection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (signatureBitmap == null) {
                    Toast.makeText(context, "Please Drop Signature", Toast.LENGTH_SHORT).show();
                }else{
                    serviceUserDetailsRequestResponse();
                }

            }
        });
    }

    private void jobStatuscall() {

        APIInterface apiInterface = RetrofitClient.getClient().create(APIInterface.class);
        Call<Job_statusResponse> call = apiInterface.CheckworkAuditStatusCall(RestUtils.getContentType(), job_statusRequest());
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
                               // createLocalValueCall();
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
        custom.setOM_OSA_COMPNO(osacompno);
        Log.w(TAG,"loginRequest "+ new Gson().toJson(custom));
        return custom;
    }

    private void serviceUserDetailsRequestResponse() {
        dialog = new Dialog(context, R.style.NewProgressDialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.progroess_popup);
        dialog.show();
        APIInterface apiInterface = RetrofitClient.getClient().create(APIInterface.class);
        Call<SuccessResponse> call = apiInterface.AuditSubmiCall(RestUtils.getContentType(),submitRequest());
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

                        Toasty.success(context, "Added Successfully", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                        Intent send = new Intent( context, ServicesActivity.class);
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

    private AuditRequest submitRequest() {

        AuditRequest auditRequest = new AuditRequest();
        auditRequest.setJobId(job_id);
        auditRequest.setOmOsaCompno(osacompno);
        auditRequest.setCustomerSignature(uploadimagepath);
        auditRequest.setUserMobileNo(se_user_mobile_no);

        List<AuditRequest.MrDatum> mrData = new ArrayList<>();

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

                AuditRequest.MrDatum mrDatum = new AuditRequest.MrDatum();
                mrDatum.setTitle("Mr"+mynum);
                if(mynum==1) {
                   // mrDatum.setValue(mr1);
                    mrDatum.setPartno(str_Partno);
                    mrDatum.setReq(str_Quantity);
                } else if (mynum==2) {
                   // mrDatum.setValue(mr2);
                    mrDatum.setPartno(str_Partno);
                    mrDatum.setReq(str_Quantity);
                } else if (mynum==3) {
                   // mrDatum.setValue(mr3);
                    mrDatum.setPartno(str_Partno);
                    mrDatum.setReq(str_Quantity);
                } else if (mynum==4) {

                  //  mrDatum.setValue(mr4);
                    mrDatum.setPartno(str_Partno);
                    mrDatum.setReq(str_Quantity);
                } else if (mynum==5) {

                   // mrDatum.setValue(mr5);
                    mrDatum.setPartno(str_Partno);
                    mrDatum.setReq(str_Quantity);
                } else if (mynum==6) {

                   // mrDatum.setValue(mr6);
                    mrDatum.setPartno(str_Partno);
                    mrDatum.setReq(str_Quantity);
                } else if (mynum==7) {

                   // mrDatum.setValue(mr7);
                    mrDatum.setPartno(str_Partno);
                    mrDatum.setReq(str_Quantity);
                } else if (mynum==8) {

                   // mrDatum.setValue(mr8);
                    mrDatum.setPartno(str_Partno);
                    mrDatum.setReq(str_Quantity);
                } else if (mynum==9) {

                   // mrDatum.setValue(mr9);
                    mrDatum.setPartno(str_Partno);
                    mrDatum.setReq(str_Quantity);
                } else if (mynum==10) {

                    //mrDatum.setValue(mr10);
                    mrDatum.setPartno(str_Partno);
                    mrDatum.setReq(str_Quantity);
                }

            //    Log.e("Nish",""+mynum + mrDatum.getTitle() +" :"+ mrDatum.getValue());
                mrData.add(mrDatum);

                i++;


            }while (cursor.moveToNext());
        }
        auditRequest.setMrData(mrData);

      List<AuditRequest.FieldValueDatum> fielddata = new ArrayList<>();



        for(int j =0; j <mmyvalue.size(); j++){
            AuditRequest.FieldValueDatum myfiled = new AuditRequest.FieldValueDatum();

            myfiled.setFieldValue(mmyvalue.get(j));
            myfiled.setFieldName(myname.get(j));
            myfiled.setFieldComments(comments.get(j));
            myfiled.setFieldCatId(catid.get(j));
            myfiled.setFieldGroupId(groupid.get(j));
            myfiled.setFieldRemarks(remarks.get(j));
//            if (remarks.size()>0){
//                myfiled.setFieldRemarks(remarks.get(j));
//            }
//            myfiled.setFieldRemarks("-");

//            Log.e("Nish",""+mmyvalue.get(j) + comments.get(j));
            fielddata.add(myfiled);

        }

        Log.e("Nish",""+ fielddata.size());
        Log.e(TAG," Audit Request field"+ new Gson().toJson(fielddata));

//        Log.e(TAG," Audit Request"+ new Gson().toJson(auditRequest));
        auditRequest.setFieldValueData(fielddata);

//        JSONObject obj = null;
//        JSONArray jsonArray = new JSONArray();
//       for(int j =0; j <mmyvalue.size(); j++){
//
//            Log.e("Value","" +mmyvalue);
//            Log.e("I",""+j);
//
////            myfiled.setFieldValue(mmyvalue.get(j));
////
//          //  myfiled.setFieldName(myname.get(j));
//         //   Log.e("Name",""+myname);
//           myfiled.setFieldComments(comments.get(j));
//            Log.e("Name",""+comments);
////            myfiled.setFieldCatId(catid.get(j));
////
////            myfiled.setFieldGroupId(groupid.get(j));
////
////          //  MyData.add(String.valueOf(myfiled));
////
////            jsonArray.put(myfiled);
//
//            obj = new JSONObject();
//            try {
//                obj.put(String.valueOf(mmyvalue), mmyvalue.get(i));
////                obj.put("name", name[i]);
////                obj.put("year", year[i]);
////                obj.put("curriculum", curriculum[i]);
////                obj.put("birthday", birthday[i]);
//
//
//            } catch (JSONException e) {
//
//                e.printStackTrace();
//            }
//            jsonArray.put(obj);
//          //  Log.e("My Data",""+obj);
//
//        }
        // he is in meeting
//        fielddata.add(myfiled);

     //   auditRequest.setFieldValueData(fielddata);
//        Log.e("Nish",""+ mmyvalue.get(j));
//        Log.e("Nish",""+ myname.get(j));
//        Log.e("Nish",""+ comments.get(j));
//        Log.e("Nish",""+ catid.get(j));
//        Log.e("Nish",""+ groupid.get(j));

        Log.e(TAG," Audit Request"+ new Gson().toJson(auditRequest));
        return auditRequest;
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

                            Toast.makeText(context, "Signature Saved", Toast.LENGTH_SHORT).show();
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



    private void Job_status_update() {

        APIInterface apiInterface = RetrofitClient.getClient().create(APIInterface.class);
        Call<Job_status_updateResponse> call = apiInterface.job_status_updateAuditResponseCall(com.triton.johnson_tap_app.utils.RestUtils.getContentType(), job_status_updateRequest());
        Log.w(VolleyLog.TAG,"SignupResponse url  :%s"+" "+ call.request().url().toString());

        call.enqueue(new Callback<Job_status_updateResponse>() {
            @SuppressLint("LogNotTimber")
            @Override
            public void onResponse(@NonNull Call<Job_status_updateResponse> call, @NonNull Response<Job_status_updateResponse> response) {

                Log.w(VolleyLog.TAG,"SignupResponse" + new Gson().toJson(response.body()));
                if (response.body() != null) {

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
        Log.w(VolleyLog.TAG,"loginRequest "+ new Gson().toJson(custom));
        return custom;
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();

        if (value.equals("no")){

            Intent send = new Intent(context, MaterialRequest_AuditActivity.class);
            send.putExtra("status", status);
            startActivity(send);
        }
        else{
            Intent send = new Intent(context, AuditMR_Activity.class);
            send.putExtra("status", status);
            startActivity(send);
        }


    }
}
