package com.triton.johnson_tap_app.Service_Activity.LR_Service;

import static com.android.volley.VolleyLog.TAG;

import android.content.Context;
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

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.triton.johnson_tap_app.R;
import com.triton.johnson_tap_app.api.APIInterface;
import com.triton.johnson_tap_app.api.RetrofitClient;
import com.triton.johnson_tap_app.requestpojo.Custom_detailsRequest;
import com.triton.johnson_tap_app.responsepojo.LR_DetailsResponse;
import com.triton.johnson_tap_app.utils.RestUtils;

import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LR_Details_Activity extends AppCompatActivity {

    TextView txt_LrNo,txt_LrData,txt_QuoteNo,txt_Customername,txt_Adone,txt_Adtwo,txt_Adthree,txt_Adfour,txt_Pin, txt_Mobileno,txt_Servicetype,txt_Mechname;
    String  str_Lrno,str_LrData,str_QuoteNo,str_Customername,str_Adone,str_Adtwo,str_Adthree,str_Adfour,str_Pin,str_MobileNo,str_Servicetype,str_Mechname;
    Context context;
    String status,job_id,se_user_mobile_no, se_user_name, se_id,check_id, service_title,message,compno,sertype,str_Techsign,str_CustAck;
    String str_Custname, str_Custno, str_Custremarks,str_Quoteno;
    Button btn_Continue;
    ImageView img_Back;
    SharedPreferences sharedPreferences;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_lrdetails);
        context = this;

        txt_LrNo = findViewById(R.id.txt_lrno);
        txt_LrData = findViewById(R.id.txt_lrdate);
        txt_QuoteNo = findViewById(R.id.txt_quotenumber);
        txt_Customername = findViewById(R.id.txt_customername);
        txt_Adone = findViewById(R.id.address1);
        txt_Adtwo = findViewById(R.id.address2);
        txt_Adthree = findViewById(R.id.address3);
        txt_Adfour = findViewById(R.id.address4);
        txt_Pin = findViewById(R.id.pin);
        txt_Mobileno = findViewById(R.id.txt_mobileno);
        txt_Servicetype = findViewById(R.id.txt_servicetype);
        txt_Mechname = findViewById(R.id.txt_mechname);
        btn_Continue = findViewById(R.id.btn_continue);
        img_Back = findViewById(R.id.img_back);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            //  service_title = extras.getString("service_title");
            status = extras.getString("status");
            //   Log.e("Name",":" + service_title);
            Log.e("Status", "" + status);
            job_id = extras.getString("job_id");
            Log.e("JobID",""+job_id);
            str_Custname = extras.getString("C_name");
            str_Custno = extras.getString("C_no");
            str_Custremarks = extras.getString("C_remarks");
            str_Techsign = extras.getString("tech_signature");
            str_CustAck = extras.getString("cust_ack");
//            Log.e("A", "" + str_Custname);
//            Log.e("A", "" + str_Custno);
//            Log.e("A", "" + str_Custremarks);
        }

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        se_id = sharedPreferences.getString("_id", "default value");
        se_user_mobile_no = sharedPreferences.getString("user_mobile_no", "default value");
        se_user_name = sharedPreferences.getString("user_name", "default value");
        service_title = sharedPreferences.getString("service_title", "Services");
        str_Quoteno = sharedPreferences.getString("quoteno","123");
       // compno = sharedPreferences.getString("compno","123");
       // sertype = sharedPreferences.getString("sertype","123");

        Log.e("Name", "" + service_title);
        Log.e("Mobile", ""+ se_user_mobile_no);
        Log.e("QuoteNO", ""+ str_Quoteno);
       // Log.e("Compno", "" + compno);
       // Log.e("sertype", ""+ sertype);

        Custom_details();

        img_Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent send = new Intent(context, JobDetails_LRService_Activity.class);
                // send.putExtra("service_title",service_title);
                send.putExtra("status" , status);
                startActivity(send);
            }
        });

        btn_Continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent send = new Intent(context, StartJob_LRService_Activity.class);
                // send.putExtra("service_title",service_title);
                send.putExtra("job_id",job_id);
                send.putExtra("status" , status);
                send.putExtra("C_name" , str_Custname);
                send.putExtra("C_no" , str_Custno);
                send.putExtra("C_remarks" , str_Custremarks);
                send.putExtra("tech_signature", str_Techsign);
                send.putExtra("cust_ack",str_CustAck);
                startActivity(send);
            }
        });

    }

    private void Custom_details() {

        APIInterface apiInterface = RetrofitClient.getClient().create(APIInterface.class);
        Call<LR_DetailsResponse> call = apiInterface.LRDetailsResponseCall(RestUtils.getContentType(),custom_detailsRequest());
        Log.w("LR Details Response URL" ,"" + call.request().url().toString());
        call.enqueue(new Callback<LR_DetailsResponse>() {
            @Override
            public void onResponse(Call<LR_DetailsResponse> call, Response<LR_DetailsResponse> response) {

                Log.e(TAG,"LR Details Response  "  + new Gson().toJson(response.body()));
                if (response.body() != null) {
                    message = response.body().getMessage();

                    if (response.body().getCode() == 200){
                        if (response.body().getData() != null){

                            str_Lrno = response.body().getData().getLr_no();
                            str_LrData = response.body().getData().getLr_date();
                            str_QuoteNo = response.body().getData().getQuote_no();
                            str_Customername = response.body().getData().getCustomer_name();
                            str_Adone = response.body().getData().getAddress1();
                            str_Adtwo = response.body().getData().getAddress2();
                            str_Adthree = response.body().getData().getAddress3();
                            str_Adfour = response.body().getData().getAddress4();
                            str_Pin = response.body().getData().getPin();
                            str_MobileNo = response.body().getData().getMobile_no();
                            str_Servicetype = response.body().getData().getService_type();
                            str_Mechname = response.body().getData().getMechanic_name();

                            txt_LrNo.setText(str_Lrno);
                            txt_LrData.setText(str_LrData);
                            txt_QuoteNo.setText(str_QuoteNo);
                            txt_Customername.setText(str_Customername);
                            txt_Adone.setText(str_Adone);
                            txt_Adtwo.setText(str_Adtwo);
                            txt_Adthree.setText(str_Adthree);
                            txt_Adtwo.setText(str_Adfour);
                            txt_Pin.setText(str_Pin);
                            txt_Mobileno.setText(str_MobileNo);
                            txt_Servicetype.setText(str_Servicetype);
                            txt_Mechname.setText(str_Mechname);

                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString("service_type",str_Servicetype);
                            editor.apply();

                        }
                    }else {
                        Toasty.warning(getApplicationContext(),""+message,Toasty.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<LR_DetailsResponse> call, Throwable t) {
                Log.e("LR Details on Failure", "--->" + t.getMessage());
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }


    private Custom_detailsRequest custom_detailsRequest() {

        Custom_detailsRequest custom = new Custom_detailsRequest();
        custom.setJob_id(job_id);
        custom.setUser_mobile_no(se_user_mobile_no);
        custom.setService_name(service_title);
        custom.setSMU_SCQH_QUOTENO(str_Quoteno);
//        custom.setSMU_SCH_COMPNO(compno);
//        custom.setSMU_SCH_SERTYPE(sertype);
//        Log.e("CompNo",""+compno);
//        Log.e("SertYpe", ""+sertype);
        Log.w(TAG,"LR Details Request "+ new Gson().toJson(custom));
        return custom;
    }

    @Override
    public void onBackPressed() {
        Intent send = new Intent(context, JobDetails_LRService_Activity.class);
        // send.putExtra("service_title",service_title);
        send.putExtra("status" , status);
        startActivity(send);
    }
}
