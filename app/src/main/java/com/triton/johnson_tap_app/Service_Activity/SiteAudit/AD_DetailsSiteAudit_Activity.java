package com.triton.johnson_tap_app.Service_Activity.SiteAudit;

import static com.android.volley.VolleyLog.TAG;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.android.volley.VolleyLog;
import com.google.gson.Gson;
import com.triton.johnson_tap_app.Location.GpsTracker;
import com.triton.johnson_tap_app.R;
import com.triton.johnson_tap_app.Service_Activity.PreventiveMRApproval.StartJob_PreventiveMR_Activity;
import com.triton.johnson_tap_app.Service_Activity.ServicesActivity;
import com.triton.johnson_tap_app.api.APIInterface;
import com.triton.johnson_tap_app.api.RetrofitClient;
import com.triton.johnson_tap_app.requestpojo.CheckOutstandingJobRequest;
import com.triton.johnson_tap_app.requestpojo.SkipJobDetailRequest;
import com.triton.johnson_tap_app.requestpojo.UpdateOutstandingJobRequest;
import com.triton.johnson_tap_app.responsepojo.CheckOutstandingJobResponse;
import com.triton.johnson_tap_app.responsepojo.SuccessResponse;
import com.triton.johnson_tap_app.utils.ConnectionDetector;
import com.triton.johnson_tap_app.utils.RestUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AD_DetailsSiteAudit_Activity extends AppCompatActivity {


    ImageView img_Back;
    TextView txt_AdNumber;
    SharedPreferences sharedPreferences;
    Button btn_Next,btn_Prev,btn_Skip;
    Context context;
    Dialog dialog;
    String str_PendingJobid, str_PendingServicename, str_PendingCompno, str_PendingStartTime, str_PendingPauseTime;
    AlertDialog alertDialog;
    String status,se_user_mobile_no, se_user_name, se_id,check_id, service_title,message,jobid,osacompno,str_Remarks,networkStatus ="";
    GpsTracker gpsTracker;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_addetailssiteaudit);
        context = this;

        img_Back = findViewById(R.id.img_back);
        txt_AdNumber = findViewById(R.id.txt_adnumber);
        btn_Prev = findViewById(R.id.btn_prev);
        btn_Next = findViewById(R.id.btn_next);
        btn_Skip = findViewById(R.id.btn_skip);


        try {
            if (ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED ) {
                ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, 101);
            }
        } catch (Exception e){
            e.printStackTrace();
        }

        gpsTracker = new GpsTracker(AD_DetailsSiteAudit_Activity.this);
        if(gpsTracker.canGetLocation()){
            double latitude = gpsTracker.getLatitude();
            double longitude = gpsTracker.getLongitude();
//            tvLatitude.setText(String.valueOf(latitude));
//            tvLongitude.setText(String.valueOf(longitude));
            Log.e("Lat ",latitude + " Long: " + longitude);
        }else{
            gpsTracker.showSettingsAlert();
        }

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        se_id = sharedPreferences.getString("_id", "default value");
        se_user_mobile_no = sharedPreferences.getString("user_mobile_no", "default value");
        se_user_name = sharedPreferences.getString("user_name", "default value");
        service_title = sharedPreferences.getString("service_title", "Services");
        jobid =sharedPreferences.getString("jobid","L-1234");
        osacompno = sharedPreferences.getString("osacompno","ADT2020202020");

        Log.e("Name", "" + service_title);
        Log.e("Mobile", ""+ se_user_mobile_no);
        Log.e("Jobid",""+ jobid);
        Log.e("osocompno",""+ osacompno);

        txt_AdNumber.setText(osacompno);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            //  service_title = extras.getString("service_title");
            status = extras.getString("status");
            //   Log.e("Name",":" + service_title);
            Log.e("Status", "" + status);
        }

        img_Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onBackPressed();
//                Intent send = new Intent(context, JobdetailsSiteaudit_Activity.class);
//                //send.putExtra("service_title",service_title);
//                send.putExtra("status", status);
//                startActivity(send);
            }
        });

        btn_Prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
//                Intent send = new Intent(context, JobdetailsSiteaudit_Activity.class);
//                send.putExtra("status", status);
//                startActivity(send);
            }
        });

        btn_Next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                networkStatus = ConnectionDetector.getConnectivityStatusString(getApplicationContext());

                Log.e("Network",""+networkStatus);
                if (networkStatus.equalsIgnoreCase("Not connected to Internet")) {

                    NoInternetDialog();

                }else{

                    checkOutstandingJobCall();
                }
//                Intent send = new Intent(context, StartJob_AuditActivity.class);
//                send.putExtra("status", status);
//                startActivity(send);
            }
        });

        btn_Skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                skipAlert();

            }
        });

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

    @SuppressLint("MissingInflatedId")
    private void skipAlert() {

        AlertDialog.Builder mBuilder = new AlertDialog.Builder(context);
        View mView = getLayoutInflater().inflate(R.layout.remarks_popup, null);

        EditText edt_Remarks = mView.findViewById(R.id.edt_remarks);
        Button btn_Submit = mView.findViewById(R.id.btn_submit);

        btn_Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                str_Remarks = edt_Remarks.getText().toString();

                if(str_Remarks.length()>0){

                    skipJobDetailsCall();

                }
                else{

                    edt_Remarks.setError("Please Enter Remarks");
                }
            }
        });

        mBuilder.setView(mView);
        final AlertDialog dialog = mBuilder.create();
        dialog.show();
        dialog.setCanceledOnTouchOutside(false);
    }

    private void skipJobDetailsCall() {

        dialog = new Dialog(context, R.style.NewProgressDialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.progroess_popup);
        dialog.show();

        APIInterface apiInterface = RetrofitClient.getClient().create(APIInterface.class);
        Call<SuccessResponse> call = apiInterface.SkipJobDetails(RestUtils.getContentType(), skipJobRequest());
        Log.w(TAG,"Skip Job Details url  :%s"+" "+ call.request().url().toString());

        call.enqueue(new Callback<SuccessResponse>() {
            @Override
            public void onResponse(Call<SuccessResponse> call, Response<SuccessResponse> response) {

                Log.w(VolleyLog.TAG,"Skip Job Details Response" + new Gson().toJson(response.body()));

                dialog.dismiss();

                if (response.body() != null){

                    message = response.body().getMessage();
                    Log.e("Message",""+message);

                    if (response.body().getCode() == 200){

                        Intent send = new Intent(context, ServicesActivity.class);
                        startActivity(send);
                    }
                    else {

                        dialog.dismiss();
                    }
                }
                else{

                    dialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<SuccessResponse> call, Throwable t) {
                dialog.dismiss();
                Log.e("OTP", "--->" + t.getMessage());
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private SkipJobDetailRequest skipJobRequest() {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm a");
        String skiptime = sdf.format(new Date());
        Log.e("Skip Time",""+ skiptime);

        SkipJobDetailRequest skipJobDetailRequest = new SkipJobDetailRequest();
        skipJobDetailRequest.setKey_no(osacompno);
        skipJobDetailRequest.setService_type(service_title);
        skipJobDetailRequest.setRemarks(str_Remarks);
        skipJobDetailRequest.setStatus("Completed");
        skipJobDetailRequest.setUser_mobile_number(se_user_mobile_no);
        skipJobDetailRequest.setTime(skiptime);
        Log.w(TAG,"Job Skip Request "+ new Gson().toJson(skipJobDetailRequest));
        return skipJobDetailRequest;
    }

    private void checkOutstandingJobCall() {

        dialog = new Dialog(context, R.style.NewProgressDialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.progroess_popup);
        dialog.show();

        APIInterface apiInterface = RetrofitClient.getClient().create(APIInterface.class);
        Call<CheckOutstandingJobResponse> call = apiInterface.CheckOutstandingJobCall(RestUtils.getContentType(), checkOustandingjob());
        Log.w(TAG,"Check Outstanding Job url  :%s"+" "+ call.request().url().toString());

        call.enqueue(new Callback<CheckOutstandingJobResponse>() {
            @Override
            public void onResponse(Call<CheckOutstandingJobResponse> call, Response<CheckOutstandingJobResponse> response) {

                Log.w(TAG," Check Outstanding Response" + new Gson().toJson(response.body()));

                dialog.dismiss();

                if (response.body() != null){

                    if (response.body().getCode() == 200){

                        message = response.body().getMessage();

                        Log.e("Message",""+message);

                        if (message.equals("Pending Job")){

                            str_PendingJobid = response.body().getData().getJob_no();
                            str_PendingServicename = response.body().getData().getService_type();
                            str_PendingCompno= response.body().getData().getComp_no();
                            str_PendingStartTime = response.body().getData().getStart_time();
                            str_PendingStartTime = str_PendingStartTime.replaceAll("[^0-9-:]", " ");

                            Log.e("Pending JobID",""+ str_PendingJobid);
                            Log.e("Pending Servicename",""+ str_PendingServicename);
                            Log.e("Pending Compno",""+ str_PendingCompno);
                            Log.e("Pending StartTime",""+ str_PendingStartTime);

                            showPopup();

                        }
                        else{

                            getMYLocation();

                        }


                    }
                    else{

                        dialog.dismiss();
                    }


                }
                else{

                    dialog.dismiss();
                }

            }

            @Override
            public void onFailure(Call<CheckOutstandingJobResponse> call, Throwable t) {
                Log.e("OTP", "--->" + t.getMessage());
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void getMYLocation() {

        gpsTracker = new GpsTracker(context);
        if(gpsTracker.canGetLocation()){
            double latitude = gpsTracker.getLatitude();
            double longitude = gpsTracker.getLongitude();
            Log.e("Lat ",latitude + " Long: " + latitude);

            Intent send = new Intent(context, StartJob_AuditActivity.class);
            send.putExtra("status", status);
            startActivity(send);

        }else{
            gpsTracker.showSettingsAlert();
        }
    }

    private void showPopup() {

        AlertDialog.Builder mBuilder = new AlertDialog.Builder(context);
        View mView = getLayoutInflater().inflate(R.layout.popup_outstandingjob, null);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        str_PendingPauseTime = sdf.format(new Date());
        Log.e("Pause Time",""+ str_PendingPauseTime);

        TextView txt_JobId,txt_Servicename,txt_Compno, txt_Starttime, txt_Pausetime;
        ImageView img_Pause;

        txt_JobId = mView.findViewById(R.id.txt_jobid);
        txt_Servicename = mView.findViewById(R.id.txt_servicetype);
        txt_Compno = mView.findViewById(R.id.txt_compno);
        txt_Starttime = mView.findViewById(R.id.txt_starttime);
        txt_Pausetime = mView.findViewById(R.id.txt_pausetime);
        img_Pause = mView.findViewById(R.id.img_pause);

        txt_JobId.setText(str_PendingJobid);
        txt_Servicename.setText(str_PendingServicename);
        txt_Compno.setText(str_PendingCompno);
        txt_Starttime.setText(str_PendingStartTime);
        txt_Pausetime.setText(str_PendingPauseTime);

        mBuilder.setView(mView);
        alertDialog= mBuilder.create();
        alertDialog.show();
        alertDialog.setCanceledOnTouchOutside(true);

        img_Pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                updateOutstandingJobCall();

                //  alertDialog.dismiss();
            }
        });

    }

    private CheckOutstandingJobRequest checkOustandingjob() {

        CheckOutstandingJobRequest checkOutstandingJobRequest = new CheckOutstandingJobRequest();
        checkOutstandingJobRequest.setUser_mobile_no(se_user_mobile_no);
        Log.w(TAG,"Check OutStanding Request "+ new Gson().toJson(checkOutstandingJobRequest));
        return checkOutstandingJobRequest;
    }

    private void updateOutstandingJobCall() {

        dialog = new Dialog(context, R.style.NewProgressDialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.progroess_popup);
        dialog.show();

        APIInterface apiInterface = RetrofitClient.getClient().create(APIInterface.class);
        Call<SuccessResponse> call = apiInterface.UpdateOutstandingJobCall(RestUtils.getContentType(), updateOutstandingjob());
        Log.w(TAG,"Update Outstanding Job url  :%s"+" "+ call.request().url().toString());

        call.enqueue(new Callback<SuccessResponse>() {
            @Override
            public void onResponse(Call<SuccessResponse> call, Response<SuccessResponse> response) {

                Log.w(TAG," Update Outstanding Response" + new Gson().toJson(response.body()));

                dialog.dismiss();

                if (response.body() != null) {

                    if (response.body().getCode() == 200){

                        alertDialog.dismiss();
                    }
                    else{

                        dialog.dismiss();
                    }

                }
                else{

                    dialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<SuccessResponse> call, Throwable t) {

                dialog.dismiss();
                Log.e("OTP", "--->" + t.getMessage());
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private UpdateOutstandingJobRequest updateOutstandingjob() {
        UpdateOutstandingJobRequest updateOutstandingJobRequest =new UpdateOutstandingJobRequest();
        updateOutstandingJobRequest.setJob_no(str_PendingJobid);
        updateOutstandingJobRequest.setService_type(str_PendingServicename);
        updateOutstandingJobRequest.setComp_no(str_PendingCompno);
        updateOutstandingJobRequest.setUser_mobile_no(se_user_mobile_no);
        updateOutstandingJobRequest.setPause_time(str_PendingPauseTime);
        Log.w(TAG,"Update OutstandingJob Request "+ new Gson().toJson(updateOutstandingJobRequest));
        return updateOutstandingJobRequest;
    }

    @Override
    public void onBackPressed() {

        super.onBackPressed();
//        Intent send = new Intent(context, JobdetailsSiteaudit_Activity.class);
//        //send.putExtra("service_title",service_title);
//        send.putExtra("status", status);
//        startActivity(send);
    }
}
