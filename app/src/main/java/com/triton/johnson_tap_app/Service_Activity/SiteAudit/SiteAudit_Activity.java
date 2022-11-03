package com.triton.johnson_tap_app.Service_Activity.SiteAudit;

import static android.content.ContentValues.TAG;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.gson.Gson;
import com.triton.johnson_tap_app.R;
import com.triton.johnson_tap_app.RestUtils;
import com.triton.johnson_tap_app.Service_Activity.ServicesActivity;
import com.triton.johnson_tap_app.api.APIInterface;
import com.triton.johnson_tap_app.api.RetrofitClient;
import com.triton.johnson_tap_app.requestpojo.Count_pasusedRequest;
import com.triton.johnson_tap_app.responsepojo.Count_pasusedResponse;

import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;

public class SiteAudit_Activity extends AppCompatActivity {


    ImageView img_back;
    CardView cv_new_job, cv_pasused_job;
    TextView pasused_count,title_name;
    Context context;
    SharedPreferences sharedPreferences;
    String se_user_mobile_no, se_user_name, se_id,check_id, service_title,message,paused_count;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_siteaudit);
        context = this;

        img_back = (ImageView) findViewById(R.id.img_back);
        cv_new_job = (CardView) findViewById(R.id.cv_new_job);
        cv_pasused_job = (CardView) findViewById(R.id.cv_paused_job);
        pasused_count = (TextView) findViewById(R.id.paused_count);
        title_name = (TextView)findViewById(R.id.title_name);

        sharedPreferences =  PreferenceManager.getDefaultSharedPreferences(this);
        se_id = sharedPreferences.getString("_id", "default value");
        se_user_mobile_no = sharedPreferences.getString("user_mobile_no", "default value");
        se_user_name = sharedPreferences.getString("user_name", "default value");
        service_title = sharedPreferences.getString("service_title", "Services");

        Log.e("name",""+service_title);
        Log.e("Mobile", ""+ se_user_mobile_no);

        Count_paused();

        cv_new_job.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, JobdetailsSiteaudit_Activity.class);
                intent.putExtra("status" , "new");
                startActivity(intent);
            }
        });

        cv_pasused_job.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Intent intent = new Intent(context,JobDetails_LRService_Activity.class);
//                intent.putExtra("status" , "pause");
//                startActivity(intent);
            }
        });

        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onBackPressed();
//                Intent send = new Intent(context, ServicesActivity.class);
//                startActivity(send);
            }
        });
    }

    private void Count_paused() {

        APIInterface apiInterface = RetrofitClient.getClient().create(APIInterface.class);
        Call<Count_pasusedResponse> call = apiInterface.Count_AuditstatuscountCall(RestUtils.getContentType(), count_pasuedRequest());
        Log.w(TAG,"SignupResponse url  :%s"+" "+ call.request().url().toString());

        call.enqueue(new Callback<Count_pasusedResponse>() {
            @SuppressLint("LogNotTimber")
            @Override
            public void onResponse(@NonNull Call<Count_pasusedResponse> call, @NonNull retrofit2.Response<Count_pasusedResponse> response) {

                Log.w(TAG,"Job RESPONSE" + new Gson().toJson(response.body()));
                if (response.body() != null) {
                    message = response.body().getMessage();

                    if (200 == response.body().getCode()) {
                        if(response.body().getData() != null){

                            paused_count = response.body().getData().getPaused_count();
                            Log.e("Count",""+paused_count);
                            pasused_count.setText("(" +paused_count +")");
                           // pasused_count.setText( paused_count);
                        }


                    } else {
                        Toasty.warning(getApplicationContext(),""+message,Toasty.LENGTH_LONG).show();

                    }
                }


            }

            @Override
            public void onFailure(@NonNull Call<Count_pasusedResponse> call, @NonNull Throwable t) {
                Log.e("OTP", "--->" + t.getMessage());
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private Count_pasusedRequest count_pasuedRequest() {
        Count_pasusedRequest count = new Count_pasusedRequest();
        count.setUser_mobile_no(se_user_mobile_no);
        count.setService_name(service_title);
        Log.w(TAG,"loginRequest "+ new Gson().toJson(count));
        return count;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
//        Intent send = new Intent(context, ServicesActivity.class);
//        startActivity(send);
    }
}
