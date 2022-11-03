package com.triton.johnson_tap_app.Service_Activity;

import static com.android.volley.VolleyLog.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.triton.johnson_tap_app.activity.Main_Menu_ServicesActivity;
import com.triton.johnson_tap_app.R;
import com.triton.johnson_tap_app.api.APIInterface;
import com.triton.johnson_tap_app.api.RetrofitClient;
import com.triton.johnson_tap_app.requestpojo.AgentRequest;
import com.triton.johnson_tap_app.responsepojo.AgentResponse;
import com.triton.johnson_tap_app.utils.RestUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Agent_ProfileActivity extends AppCompatActivity {

    ImageView iv_back;
    Button submit;
    TextView agent_name, agent_id, agent_location, completed_jobs, pending_jobs, invalid_jobs, services_mapped;
    String s_agent_code, s_agent_name, s_agent_status;
    AlertDialog alertDialog;
    Dialog dialog;
    String status , message = "";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_agent_profile);

        iv_back = (ImageView) findViewById(R.id.iv_back);
        agent_name = (TextView) findViewById(R.id.agent_name);
        agent_id = (TextView) findViewById(R.id.agent_id);
        agent_location = (TextView) findViewById(R.id.agent_location);
        completed_jobs = (TextView) findViewById(R.id.completed_jobs);
        pending_jobs = (TextView) findViewById(R.id.pending_jobs);
        invalid_jobs = (TextView) findViewById(R.id.invalid_jobs);
        services_mapped = (TextView) findViewById(R.id.services_mapped);

        //   LoginResponseCall();


        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent send = new Intent(Agent_ProfileActivity.this, Main_Menu_ServicesActivity.class);
                startActivity(send);

            }
        });

        AgentResponseCall();
    }

    private void AgentResponseCall() {

        APIInterface apiInterface = RetrofitClient.getClient().create(APIInterface.class);
        Call<AgentResponse> call = apiInterface.AgentResponseCall(RestUtils.getContentType(), loginRequest());
        Log.w(TAG,"SignupResponse url  :%s"+" "+ call.request().url().toString());

        call.enqueue(new Callback<AgentResponse>() {
            @SuppressLint("LogNotTimber")
            @Override
            public void onResponse(@NonNull Call<AgentResponse> call, @NonNull Response<AgentResponse> response) {

                Log.w(TAG,"SignupResponse" + new Gson().toJson(response.body()));
                if (response.body() != null) {
                    message = response.body().getMessage();
                    if (200 == response.body().getCode()) {
                        if(response.body().getData() != null){

                         //     Log.d("inform",response.body().getData().getUser_name());
                           agent_name.setText(response.body().getData().getUser_name());
                            agent_id.setText(response.body().getData().get_id());
                            agent_location.setText(response.body().getData().getUser_location());

                        }


                    } else {
                        Toasty.warning(getApplicationContext(),""+message,Toasty.LENGTH_LONG).show();

                    }
                }


            }

            @Override
            public void onFailure(@NonNull Call<AgentResponse> call, @NonNull Throwable t) {
                Log.e("OTP", "--->" + t.getMessage());
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
    private AgentRequest loginRequest() {

        /**
         * user_id : 12345
         * user_password : 12345
         * last_login_time : 20-10-2021 11:00 AM
         */

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm aa", Locale.getDefault());
        String currentDateandTime = sdf.format(new Date());

        AgentRequest loginRequest = new AgentRequest();
        loginRequest.setUser_mobile_no("98765432101");
        Log.w(TAG,"loginRequest "+ new Gson().toJson(loginRequest));
        return loginRequest;
    }

}