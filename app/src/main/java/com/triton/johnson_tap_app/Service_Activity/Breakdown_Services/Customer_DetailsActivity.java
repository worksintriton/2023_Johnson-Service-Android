package com.triton.johnson_tap_app.Service_Activity.Breakdown_Services;

import static com.android.volley.VolleyLog.TAG;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.VolleyLog;
import com.google.gson.Gson;
import com.triton.johnson_tap_app.Location.GpsTracker;
import com.triton.johnson_tap_app.R;
import com.triton.johnson_tap_app.Service_Activity.BreakdownMRApprovel.CustomerDetailsBreakdownMR_Activity;
import com.triton.johnson_tap_app.Service_Activity.BreakdownMRApprovel.StartJob_BreakdownMR_Activity;
import com.triton.johnson_tap_app.Service_Activity.LR_Service.StartJob_LRService_Activity;
import com.triton.johnson_tap_app.Service_Activity.ServicesActivity;
import com.triton.johnson_tap_app.api.APIInterface;
import com.triton.johnson_tap_app.api.RetrofitClient;
import com.triton.johnson_tap_app.requestpojo.CheckOutstandingJobRequest;
import com.triton.johnson_tap_app.requestpojo.Custom_detailsRequest;
import com.triton.johnson_tap_app.requestpojo.SkipJobDetailRequest;
import com.triton.johnson_tap_app.requestpojo.UpdateOutstandingJobRequest;
import com.triton.johnson_tap_app.responsepojo.CheckOutstandingJobResponse;
import com.triton.johnson_tap_app.responsepojo.Custom_detailsResponse;
import com.triton.johnson_tap_app.responsepojo.SuccessResponse;
import com.triton.johnson_tap_app.utils.ConnectionDetector;
import com.triton.johnson_tap_app.utils.RestUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Customer_DetailsActivity extends AppCompatActivity {

    ImageView iv_back;
    Button btn_continue,btn_Skip;
    Dialog dialog;
    Dialog alertdialog;
    String se_user_mobile_no, se_user_name, se_id,check_id, service_title,str_job_id,message,str_address1,str_address2,str_address3,str_pin,str_contract_type,str_contract_status,str_jobid,str_custom_name,str_bd_no,str_bd_date,str_breakdaown_type;
    TextView address1,address2,address3,pin,contract_type,contract_status,job_id,customer_name,bd_number,bd_date,breakdown_type;
String compno, sertype,status,str_Remarks,networkStatus="";
    SharedPreferences sharedPreferences;
    AlertDialog alertDialog;
    Context context;
    String str_PendingJobid, str_PendingServicename, str_PendingCompno, str_PendingStartTime, str_PendingPauseTime;
    GpsTracker gpsTracker;

    @SuppressLint("MissingInflatedId")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_customer_details);
        context = this;

        iv_back = (ImageView) findViewById(R.id.iv_back);
        btn_continue = (Button) findViewById(R.id.btn_continue);
        address1 = (TextView) findViewById(R.id.address1);
        address2 = (TextView) findViewById(R.id.address2);
        address3 = (TextView) findViewById(R.id.address3);
        pin = (TextView) findViewById(R.id.pin);
        contract_type = (TextView) findViewById(R.id.contract_type);
        contract_status = (TextView) findViewById(R.id.contract_status);
        job_id = (TextView) findViewById(R.id.job_id);
        customer_name = (TextView) findViewById(R.id.customer_name);
        bd_number = (TextView) findViewById(R.id.bd_number);
        bd_date = (TextView) findViewById(R.id.bd_date);
        breakdown_type = (TextView) findViewById(R.id.breakdown_type);
        btn_Skip = findViewById(R.id.btn_skip);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        se_id = sharedPreferences.getString("_id", "default value");
        se_user_mobile_no = sharedPreferences.getString("user_mobile_no", "default value");
        se_user_name = sharedPreferences.getString("user_name", "default value");
        service_title = sharedPreferences.getString("service_title", "default value");
        str_job_id = sharedPreferences.getString("job_id","123");

        compno = sharedPreferences.getString("compno","123");
        sertype = sharedPreferences.getString("sertype","123");

        Log.e("Name",service_title);
        Log.e("JobID",str_job_id);



        Bundle extras = getIntent().getExtras();
        if (extras != null) {
          // str_job_id = extras.getString("job_id");
            status = extras.getString("status");
            Log.e("Status",status);
        }
      // Log.d("loggggg", job_id +" "+ service_title +" "+ se_user_mobile_no);

        networkStatus = ConnectionDetector.getConnectivityStatusString(getApplicationContext());

        Log.e("Network",""+networkStatus);
        if (networkStatus.equalsIgnoreCase("Not connected to Internet")) {

            Toast.makeText(context,"No Internet Connection",Toast.LENGTH_LONG).show();

        }else{
            Custom_details();
        }



        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onBackPressed();
//                Intent send = new Intent(Customer_DetailsActivity.this, Job_DetailsActivity.class);
//                send.putExtra("service_title",service_title);
//                startActivity(send);

            }
        });

        btn_continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                networkStatus = ConnectionDetector.getConnectivityStatusString(getApplicationContext());

                Log.e("Network",""+networkStatus);
                if (networkStatus.equalsIgnoreCase("Not connected to Internet")) {

                    Toast.makeText(context,"No Internet Connection",Toast.LENGTH_LONG).show();

                }else{
                    checkOutstandingJobCall();
                }


//                Intent send = new Intent(Customer_DetailsActivity.this,Start_Job_TextActivity.class);
//                //send.putExtra("job_id",str_job_id);
//                send.putExtra("status",status);
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
        skipJobDetailRequest.setKey_no(compno);
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

            Intent send = new Intent(Customer_DetailsActivity.this,Start_Job_TextActivity.class);
            //send.putExtra("job_id",str_job_id);
            send.putExtra("status",status);
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

    private void Custom_details() {

        APIInterface apiInterface = RetrofitClient.getClient().create(APIInterface.class);
        Call<Custom_detailsResponse> call = apiInterface.Custom_detailsResponseCall(RestUtils.getContentType(), custom_detailsRequest());
        Log.w(TAG,"SignupResponse url  :%s"+" "+ call.request().url().toString());

        call.enqueue(new Callback<Custom_detailsResponse>() {
            @SuppressLint("LogNotTimber")
            @Override
            public void onResponse(@NonNull Call<Custom_detailsResponse> call, @NonNull retrofit2.Response<Custom_detailsResponse> response) {

                Log.w(TAG,"SignupResponse" + new Gson().toJson(response.body()));
                if (response.body() != null) {
                    message = response.body().getMessage();

                    if (200 == response.body().getCode()) {
                        if(response.body().getData() != null){

                            str_address1 = response.body().getData().getAddress1();
                            str_address2 = response.body().getData().getAddress2();
                            str_address3 = response.body().getData().getAddress3();
                            str_pin = response.body().getData().getPin();
                            str_contract_type = response.body().getData().getContract_type();
                            str_contract_status = response.body().getData().getContract_status();
                            str_jobid = response.body().getData().getJob_id();
                            str_custom_name = response.body().getData().getCustomer_name();
                            str_bd_no = response.body().getData().getBd_number();
                            str_bd_date = response.body().getData().getBd_date();
                            str_breakdaown_type = response.body().getData().getBreakdown_type();

                            address1.setText(str_address1);
                            address2.setText(str_address2);
                            address3.setText(str_address3);
                            pin.setText(str_pin);
                            contract_type.setText(str_contract_type);
                            contract_status.setText(str_contract_status);
                            job_id.setText(str_job_id);
                            customer_name.setText(str_custom_name);
                            bd_number.setText(str_bd_no);
                            bd_date.setText(str_bd_date);
                            breakdown_type.setText(str_breakdaown_type);


                            Log.e("Contract",""+ str_contract_status);

                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString("contract_status", str_contract_status);
                            editor.apply();

                            if (str_contract_status.equals("GRACE")){

                                alertDialog = new AlertDialog.Builder(context)
                                        .setTitle("This job is under grace service")
                                        .setMessage("Grace period MR. Please follow up for approval")
                                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                alertDialog.dismiss();
//                                                Intent send = new Intent(context, ServicesActivity.class);
//                                                startActivity(send);
                                            }
                                        })
//                                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
//                                            public void onClick(DialogInterface dialogInterface, int i) {
//                                                alertDialog.dismiss();
//                                            }
//                                        })
                                        .show();
                            }

                        }

                    } else {
                        Toasty.warning(getApplicationContext(),""+message,Toasty.LENGTH_LONG).show();

                    }
                }


            }

            @Override
            public void onFailure(@NonNull Call<Custom_detailsResponse> call, @NonNull Throwable t) {
                Log.e("OTP", "--->" + t.getMessage());
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
    private Custom_detailsRequest custom_detailsRequest() {

        Custom_detailsRequest custom = new Custom_detailsRequest();
        custom.setUser_mobile_no(se_user_mobile_no);
        custom.setService_name(service_title);
        custom.setJob_id(str_job_id);
        custom.setSMU_SCH_COMPNO(compno);
        custom.setSMU_SCH_SERTYPE(sertype);
        Log.e("CompNo",""+compno);
        Log.e("SertYpe", ""+sertype);
        Log.w(TAG,"loginRequest "+ new Gson().toJson(custom));
        return custom;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}