package com.triton.johnson_tap_app.Service_Activity.Breakdown_Services;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.triton.johnson_tap_app.Db.CommonUtil;
import com.triton.johnson_tap_app.Db.DbHelper;
import com.triton.johnson_tap_app.R;
import com.triton.johnson_tap_app.api.APIInterface;
import com.triton.johnson_tap_app.api.RetrofitClient;
import com.triton.johnson_tap_app.requestpojo.Job_status_updateRequest;
import com.triton.johnson_tap_app.responsepojo.RetriveLocalValueBRResponse;
import com.triton.johnson_tap_app.responsepojo.Retrive_LocalValueResponse;
import com.triton.johnson_tap_app.utils.RestUtils;

import java.util.List;

import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Feedback_RemarkActivity extends AppCompatActivity {

    TextView text,txt;
    private Button btnSelection, btn_prev;
    ImageView iv_back;
    EditText feedback_remark;
    String s_feedback_remark;
    String job_id,feedback_group,feedback_details,bd_dta,str_feedback_remark="",status;
    String TAG = "FEEDBACK REMARK";
    String se_id,se_user_mobile_no,se_user_name,compno,sertype,message,service_title;
    List<RetriveLocalValueBRResponse.Data> databean;
    Context context;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_feedback_remark);
        context = this;

        text = findViewById(R.id.text);
        txt = findViewById(R.id.txt);
        btnSelection = (Button) findViewById(R.id.btn_next);
        btn_prev = (Button) findViewById(R.id.btn_show);
        iv_back = (ImageView) findViewById(R.id.iv_back);
        feedback_remark = (EditText)findViewById(R.id.feedback_remark);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
        //    feedback_group = extras.getString("feedback_group");
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
            str_feedback_remark = extras.getString("feedback_remark");
            feedback_remark.setText(str_feedback_remark);
        }

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        se_id = sharedPreferences.getString("_id", "default value");
        se_user_mobile_no = sharedPreferences.getString("user_mobile_no", "default value");
        se_user_name = sharedPreferences.getString("user_name", "default value");
        service_title = sharedPreferences.getString("service_title", "Services");
        compno = sharedPreferences.getString("compno","123");
        sertype = sharedPreferences.getString("sertype","123");
        job_id = sharedPreferences.getString("job_id", "default value");


        Spannable name_Upload = new SpannableString("Feedback Remark ");
        name_Upload.setSpan(new ForegroundColorSpan(Feedback_RemarkActivity.this.getResources().getColor(R.color.colorAccent)), 0, name_Upload.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        text.setText(name_Upload);
        Spannable name_Upload1 = new SpannableString("*");
        name_Upload1.setSpan(new ForegroundColorSpan(Color.RED), 0, name_Upload1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        text.append(name_Upload1);

        btn_prev.setBackgroundResource(R.drawable.blue_button_background_with_radius);
        btn_prev.setTextColor(getResources().getColor(R.color.white));
        btn_prev.setEnabled(true);

        if (status.equals("paused")){

            retrive_LocalValue();

        }else{
            getFeedback();
        }

        feedback_remark.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            public void afterTextChanged(Editable s) {
                txt.setText(s.toString().length() + "/250");

            }
        });

        btnSelection.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                s_feedback_remark = feedback_remark.getText().toString();

                if (s_feedback_remark.equals("")) {
                    feedback_remark.setError("Please Enter the Feedback Remark");
                }
                else {
                    CommonUtil.dbUtil.addFeedback(job_id,service_title,s_feedback_remark,"4");
                    Cursor cur = CommonUtil.dbUtil.getFeedback(job_id,service_title,"4");
                    Log.e("Feedback Count",""+cur.getCount());
                    Intent send = new Intent(Feedback_RemarkActivity.this, Material_RequestActivity.class);
                    send.putExtra("status",status);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("feedback_remark", s_feedback_remark);
                    editor.apply();
                    startActivity(send);
                }

            }
        });

        btn_prev.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                onBackPressed();

//                Intent send = new Intent( Feedback_RemarkActivity.this, Feedback_DetailsActivity.class);
//                send.putExtra("feedback_details",feedback_details);
//                send.putExtra("feedback_group",feedback_group);
//                send.putExtra("bd_details",bd_dta);
//                send.putExtra("job_id",job_id);
//                startActivity(send);
            }
        });

        iv_back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                onBackPressed();

//                Intent send = new Intent( Feedback_RemarkActivity.this, Feedback_DetailsActivity.class);
//                send.putExtra("feedback_details",feedback_details);
//                send.putExtra("feedback_group",feedback_group);
//                send.putExtra("bd_details",bd_dta);
//                send.putExtra("job_id",job_id);
//                startActivity(send);
            }
        });

    }

    private void getFeedback() {

        Cursor cur = CommonUtil.dbUtil.getFeedback(job_id,service_title,"4");

        Log.e("GET FEEDBACK ",""+cur.getCount());

        if (cur.getCount()>0 && cur.moveToLast()){

            @SuppressLint("Range")
            String feedback_remarks = cur.getString(cur.getColumnIndex(DbHelper.FEEDBACK_REMARKS));
            Log.e("Remarks",""+feedback_remarks);
            feedback_remark.setText(feedback_remarks);

        }

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

                            s_feedback_remark = response.body().getData().getFeedback_remark_text();
                            Log.e("Remarks",""+s_feedback_remark);
                            feedback_remark.setText(s_feedback_remark);

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


    @Override
    public void onBackPressed() {

       // super.onBackPressed();

        Intent intent = new Intent(context,Feedback_DetailsActivity.class);
        intent.putExtra("status",status);
        startActivity(intent);
    }
}