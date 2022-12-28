package com.triton.johnson_tap_app.Service_Activity.Breakdown_Services;

import static android.view.View.GONE;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.gson.Gson;
import com.triton.johnson_tap_app.R;
import com.triton.johnson_tap_app.api.APIInterface;
import com.triton.johnson_tap_app.api.RetrofitClient;
import com.triton.johnson_tap_app.requestpojo.Job_status_updateRequest;
import com.triton.johnson_tap_app.responsepojo.RetriveLocalValueBRResponse;
import com.triton.johnson_tap_app.utils.RestUtils;

import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Material_RequestActivity extends AppCompatActivity {

    TextView text;
    CardView yes,no;
    String value;
    ImageView iv_back;
    String job_id,feedback_group,feedback_details,bd_dta,feedback_remark,tech_signature="",status;
    SharedPreferences sharedPreferences;
    Context context;
    String se_id,se_user_mobile_no,se_user_name,compno,sertype,message;
    TextView txt_Jobid,txt_Starttime;
    String str_StartTime;

    @SuppressLint("MissingInflatedId")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_material_request);
        context = this;

        text = findViewById(R.id.text);
        yes = findViewById(R.id.card_yes);
        no = findViewById(R.id.card_no);
        iv_back = (ImageView) findViewById(R.id.iv_back);
        txt_Starttime = findViewById(R.id.txt_starttime);
        txt_Jobid = findViewById(R.id.txt_jobid);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        se_id = sharedPreferences.getString("_id", "default value");
        se_user_mobile_no = sharedPreferences.getString("user_mobile_no", "default value");
        se_user_name = sharedPreferences.getString("user_name", "default value");
        compno = sharedPreferences.getString("compno","123");
        sertype = sharedPreferences.getString("sertype","123");
        job_id = sharedPreferences.getString("job_id","123");
        str_StartTime = sharedPreferences.getString("starttime","");
        str_StartTime = str_StartTime.replaceAll("[^0-9-:]", " ");

        Log.e("Start Time",str_StartTime);

        txt_Jobid.setText("Job ID : " + job_id);
        txt_Starttime.setText("Start Time : " + str_StartTime);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
          //  feedback_group = extras.getString("feedback_group");
            status = extras.getString("status");
            Log.e("Status",status);
        }

        if (extras != null) {
            bd_dta = extras.getString("bd_details");
        }

        if (extras != null) {
         //   job_id = extras.getString("job_id");
        }

        if (extras != null) {
            feedback_details = extras.getString("feedback_details");
        }

        if (extras != null) {
            feedback_remark = extras.getString("feedback_remark");
        }

        if (extras != null) {
            tech_signature = extras.getString("tech_signature");
        }

        if (status.equals("paused")){
            retrive_LocalValue();
        }

        Spannable name_Upload = new SpannableString("Raise MR ");
        name_Upload.setSpan(new ForegroundColorSpan(Material_RequestActivity.this.getResources().getColor(R.color.colorAccent)), 0, name_Upload.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        text.setText(name_Upload);
        Spannable name_Upload1 = new SpannableString("*");
        name_Upload1.setSpan(new ForegroundColorSpan(Color.RED), 0, name_Upload1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        text.append(name_Upload1);

        yes.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

              //  Toast.makeText(Material_RequestActivity.this, feedback_details, Toast.LENGTH_LONG).show();

                        Intent send = new Intent( Material_RequestActivity.this, Material_Request_MR_ScreenActivity.class);
                     //   send.putExtra("value","yes");

                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("value", "yes");
                        editor.apply();
                        send.putExtra("feedback_details",feedback_details);
                        send.putExtra("feedback_group",feedback_group);
                        send.putExtra("bd_details",bd_dta);
                        send.putExtra("job_id",job_id);
                        send.putExtra("feedback_remark", feedback_remark);
                        send.putExtra("status",status);
                        startActivity(send);
            }
        });

        no.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent send = new Intent( Material_RequestActivity.this, BD_StatusActivity.class);
               // send.putExtra("value","no");
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("value", "no");
                editor.apply();
                send.putExtra("feedback_details",feedback_details);
                send.putExtra("feedback_group",feedback_group);
                send.putExtra("bd_details",bd_dta);
                send.putExtra("job_id",job_id);
                send.putExtra("feedback_remark", feedback_remark);
                send.putExtra("status",status);
                startActivity(send);
            }
        });

        iv_back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                onBackPressed();

//                    Intent send = new Intent(Material_RequestActivity.this, Feedback_RemarkActivity.class);
//                    send.putExtra("feedback_details", feedback_details);
//                    send.putExtra("feedback_group", feedback_group);
//                    send.putExtra("bd_details", bd_dta);
//                   send.putExtra("job_id", job_id);
//                   send.putExtra("feedback_remark", feedback_remark);
//                    startActivity(send);
            }
        });
    }


    @SuppressLint("LongLogTag")
    private void retrive_LocalValue() {

        APIInterface apiInterface =  RetrofitClient.getClient().create((APIInterface.class));
        Call<RetriveLocalValueBRResponse> call = apiInterface.retriveLocalValueBRCall(RestUtils.getContentType(),localRequest());
        Log.e("Retrive Local Value url  :%s"," "+ call.request().url().toString());

        call.enqueue(new Callback<RetriveLocalValueBRResponse>() {
            @Override
            public void onResponse(Call<RetriveLocalValueBRResponse> call, Response<RetriveLocalValueBRResponse> response) {

                Log.e("Retrive Response","" + new Gson().toJson(response.body()));

                if (response.body() != null){

                    message = response.body().getMessage();

                    if (response.body().getCode() == 200){

                        if (response.body().getData() != null){
                            Log.d("msg",message);

                            String mr_status = response.body().getData().getMr_status();
                            Log.e("mrData",""+mr_status);

                            if (mr_status.equals("yes")){

                                no.setVisibility(GONE);
                            }else{
                                yes.setVisibility(GONE);
                            }

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
        Log.e("Request Data ",""+ new Gson().toJson(custom));
        return custom;
    }

    @Override
    public void onBackPressed() {

        super.onBackPressed();

        Intent intent = new Intent(context,Feedback_RemarkActivity.class);
        intent.putExtra("status",status);
        startActivity(intent);
    }
}