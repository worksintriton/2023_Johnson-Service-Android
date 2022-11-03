package com.triton.johnson_tap_app.Service_Activity.PreventiveMRApproval;

import static com.android.volley.VolleyLog.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.triton.johnson_tap_app.R;
import com.triton.johnson_tap_app.Service_Activity.BreakdownMRApprovel.CustomerDetailsBreakdownMR_Activity;
import com.triton.johnson_tap_app.Service_Activity.BreakdownMRApprovel.JobDetails_BreakdownMRActivity;
import com.triton.johnson_tap_app.Service_Activity.BreakdownMRApprovel.PausedServicesBreakdownMR_Activity;
import com.triton.johnson_tap_app.api.APIInterface;
import com.triton.johnson_tap_app.api.RetrofitClient;
import com.triton.johnson_tap_app.requestpojo.Custom_detailsRequest;
import com.triton.johnson_tap_app.responsepojo.Custom_detailsResponse;
import com.triton.johnson_tap_app.utils.RestUtils;

import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;

public class CustomerDetailsPreventiveMR_Activity extends AppCompatActivity {

    ImageView iv_back;
    Button btn_continue;
    Dialog dialog;
    Dialog alertdialog;
    String se_user_mobile_no, se_user_name, se_id,check_id, service_title,str_job_id,message,str_address1,str_address2,str_address3,str_pin,str_contract_type,str_contract_status,str_jobid,str_custom_name,str_bd_no,str_bd_date,str_breakdaown_type;
    TextView address1,address2,address3,pin,contract_type,contract_status,job_id,customer_name,bd_number,bd_date,breakdown_type;
    String compno, sertype, status;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_customer_details_preventive_mr);

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
        //breakdown_type = (TextView) findViewById(R.id.breakdown_type);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        se_id = sharedPreferences.getString("_id", "default value");
        se_user_mobile_no = sharedPreferences.getString("user_mobile_no", "default value");
        se_user_name = sharedPreferences.getString("user_name", "default value");
        service_title = sharedPreferences.getString("service_title", "default value");
        str_job_id = sharedPreferences.getString("job_id", "default value");
        Log.e("Name",""+ service_title);
        Log.e("Job ID",""+ str_job_id);
        compno = sharedPreferences.getString("compno","123");
        sertype = sharedPreferences.getString("sertype","123");

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
           // str_job_id = extras.getString("job_id");
            status =extras.getString("status");
            Log.e("Status" , ""+ status);
        }
       // Log.d("loggggg", job_id +" "+ service_title +" "+ se_user_mobile_no);
      //  Log.e("Status" , ""+ status);
        Custom_details();


        if (status.equals("pause")){

            Log.e("Inside", "Paused Job");

            iv_back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent send = new Intent(CustomerDetailsPreventiveMR_Activity.this, PausedServicesPreventiveMR_Activity.class);
                    send.putExtra("status", status);
                    send.putExtra("service_title",service_title);
                    startActivity(send);

                }
            });
        }
        else {

            Log.e("Inside", "New Job ");

            iv_back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent send = new Intent(CustomerDetailsPreventiveMR_Activity.this, JobDetails_PreventiveMRActivity.class);
                    send.putExtra("service_title",service_title);
                    send.putExtra("status", status);
                    startActivity(send);

                }
            });

        }




        btn_continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent send = new Intent(CustomerDetailsPreventiveMR_Activity.this, StartJob_PreventiveMR_Activity.class);
                send.putExtra("job_id",str_job_id);
                send.putExtra("status", status);
                startActivity(send);

            }
        });

    }

    private void Custom_details() {

        APIInterface apiInterface = RetrofitClient.getClient().create(APIInterface.class);
        Call<Custom_detailsResponse> call = apiInterface.Customer_detailsResponsePrventiveMRCall(RestUtils.getContentType(), custom_detailsRequest());
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
                            //breakdown_type.setText(str_breakdaown_type);
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

        if (status.equals("pause")){

            Intent send = new Intent(CustomerDetailsPreventiveMR_Activity.this, PausedServicesPreventiveMR_Activity.class);
            send.putExtra("service_title",service_title);
            send.putExtra("status", status);
            startActivity(send);
        }else {

            Intent send = new Intent(CustomerDetailsPreventiveMR_Activity.this, JobDetails_PreventiveMRActivity.class);
            send.putExtra("service_title",service_title);
            send.putExtra("status", status);
            startActivity(send);

        }

    }
}