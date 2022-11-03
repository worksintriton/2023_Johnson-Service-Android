package com.triton.johnson_tap_app.Engineer_Dashboard;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.triton.johnson_tap_app.R;
import com.triton.johnson_tap_app.RestUtils;
import com.triton.johnson_tap_app.api.APIInterface;
import com.triton.johnson_tap_app.api.RetrofitClient;
import com.triton.johnson_tap_app.requestpojo.Agent_new_screenRequest;
import com.triton.johnson_tap_app.responsepojo.Agent_pop_statusResponse;

import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;

public class Agent_Pop_New_ScreenActivity extends AppCompatActivity {

   TextView txt_last_login,txt_last_job,txt_pending_today,txt_pending_total,txt_completed_today,txt_completed_monthly;
   String message,se_id,se_user_mobile_no,se_user_name,title;;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agent_pop_new_screen);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        se_id = sharedPreferences.getString("_id", "default value");
        se_user_mobile_no = sharedPreferences.getString("user_mobile_no", "default value");
        se_user_name = sharedPreferences.getString("user_name", "default value");

        Custom_details();

        AlertDialog.Builder mBuilder = new AlertDialog.Builder(Agent_Pop_New_ScreenActivity.this);
        View mView = getLayoutInflater().inflate(R.layout.dialog_agent, null);
        txt_last_login = (TextView) mView.findViewById(R.id.txt_last_login);
        txt_last_job = (TextView) mView.findViewById(R.id.txt_last_job);
        txt_pending_today = (TextView) mView.findViewById(R.id.txt_pending_today);
        txt_pending_total = (TextView) mView.findViewById(R.id.txt_pending_total);
        txt_completed_today = (TextView) mView.findViewById(R.id.txt_completed_today);
        txt_completed_monthly = (TextView) mView.findViewById(R.id.txt_completed_monthly);
        Button yes = (Button) mView.findViewById(R.id.btn_yes);

        mBuilder.setView(mView);
        final AlertDialog dialog = mBuilder.create();
        dialog.show();
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);

        yes.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent send = new Intent(Agent_Pop_New_ScreenActivity.this, AgentList_DashboardActivity.class);
                startActivity(send);
            //    dialog.dismiss();
            }
        });

    }

    private void Custom_details() {

        APIInterface apiInterface = RetrofitClient.getClient().create(APIInterface.class);
        Call<Agent_pop_statusResponse> call = apiInterface.Agent_pop_statusResponseCall(RestUtils.getContentType(), serviceRequest());
        Log.w(TAG,"SignupResponse url  :%s"+" "+ call.request().url().toString());

        call.enqueue(new Callback<Agent_pop_statusResponse>() {
            @SuppressLint("LogNotTimber")
            @Override
            public void onResponse(@NonNull Call<Agent_pop_statusResponse> call, @NonNull retrofit2.Response<Agent_pop_statusResponse> response) {

                Log.w(TAG,"SignupResponse" + new Gson().toJson(response.body()));
                if (response.body() != null) {
                    message = response.body().getMessage();

                    if (200 == response.body().getCode()) {
                        if(response.body().getData() != null){

                            String last_login = response.body().getData().getLast_login();
                            String last_job_act = response.body().getData().getLast_job_act();
                            String pending_job_today = response.body().getData().getPending_job_today();
                            String pending_job_total = response.body().getData().getPending_job_total();
                            String completed_job_today = response.body().getData().getCompleted_job_today();
                            String completed_job_monthly = response.body().getData().getCompleted_job_monthly();

                            txt_last_login.setText(last_login);
                            txt_last_job.setText(last_job_act);
                            txt_pending_today.setText(pending_job_today);
                            txt_pending_total.setText(pending_job_total);
                            txt_completed_today.setText(completed_job_today);
                            txt_completed_monthly.setText(completed_job_monthly);

                            Log.d("ssss",last_login);

                        }


                    } else {
                        Toasty.warning(getApplicationContext(),""+message,Toasty.LENGTH_LONG).show();

                    }
                }


            }

            @Override
            public void onFailure(@NonNull Call<Agent_pop_statusResponse> call, @NonNull Throwable t) {
                Log.e("OTP", "--->" + t.getMessage());
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
    private Agent_new_screenRequest serviceRequest() {
        Agent_new_screenRequest service = new Agent_new_screenRequest();
        service.setUser_mobile_no(se_user_mobile_no);
        Log.w(ContentValues.TAG, "Jobno Find Request " + new Gson().toJson(service));
        return service;
    }
}