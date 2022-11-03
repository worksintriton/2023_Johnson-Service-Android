package com.triton.johnson_tap_app.Service_Activity.Breakdown_Services;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.triton.johnson_tap_app.Db.CommonUtil;
import com.triton.johnson_tap_app.Db.DbHelper;
import com.triton.johnson_tap_app.Db.DbUtil;
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

public class Customer_Details_BreakdownActivity extends AppCompatActivity {

    private Button btnSelection,btn_prev;
    TextView txt_cust_name, txt_cust_no,txt;
    ImageView iv_back;
    String value="",job_id,feedback_group,feedback_details,bd_dta,feedback_remark,mr1,mr2,mr3,mr4,mr5,mr6,mr7,mr8,mr9,mr10,breakdown_servies,tech_signature,str_cust_name="",str_cust_no="",customer_acknowledgement="";
    EditText et_cust_name,et_cust_no;
    String  animal2 ="";
    SharedPreferences sharedPreferences;
    String s_cust_name,s_cust_no,service_title,status,se_id,message,se_user_mobile_no,se_user_name,compno,sertype;
    Context context;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_customer_details_breakdown);
        context = this;

        CommonUtil.dbUtil = new DbUtil(context);
        CommonUtil.dbUtil.open();
        CommonUtil.dbHelper = new DbHelper(context);

        btnSelection = (Button) findViewById(R.id.btn_next);
        txt_cust_name = (TextView) findViewById(R.id.txt_cust_name);
        txt_cust_no = (TextView) findViewById(R.id.txt_cust_no);
        txt = (TextView) findViewById(R.id.txt);
        btn_prev = (Button) findViewById(R.id.btn_show);
        iv_back = (ImageView) findViewById(R.id.iv_back);
        et_cust_name = (EditText)findViewById(R.id.et_cust_name);
        et_cust_no = (EditText)findViewById(R.id.et_cust_no);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        job_id = sharedPreferences.getString("job_id", "default value");
        service_title = sharedPreferences.getString("service_title", "default value");
        se_id = sharedPreferences.getString("_id", "default value");
        se_user_mobile_no = sharedPreferences.getString("user_mobile_no", "default value");
        se_user_name = sharedPreferences.getString("user_name", "default value");
        compno = sharedPreferences.getString("compno","123");
        sertype = sharedPreferences.getString("sertype","123");
        Log.e("JobID",""+job_id);
        Log.e("Name",""+service_title);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
          //  value = extras.getString("value");
            status = extras.getString("status");
            Log.e("Status",status);
        }
        if (extras != null) {
            feedback_group = extras.getString("feedback_group");
        }

        if (extras != null) {
            bd_dta = extras.getString("bd_details");
        }

        if (extras != null) {
          //  job_id = extras.getString("job_id");
        }

        if (extras != null) {
            feedback_details = extras.getString("feedback_details");
        }

        if (extras != null) {
            feedback_remark = extras.getString("feedback_remark");
        }

        if (extras != null) {
            mr1 = extras.getString("mr1");
        }
        if (extras != null) {
            mr2 = extras.getString("mr2");
        }
        if (extras != null) {
            mr3 = extras.getString("mr3");
        }
        if (extras != null) {
            mr4 = extras.getString("mr4");
        }
        if (extras != null) {
            mr5 = extras.getString("mr5");
        }
        if (extras != null) {
            mr6 = extras.getString("mr6");
        }
        if (extras != null) {
            mr7 = extras.getString("mr7");
        }
        if (extras != null) {
            mr8 = extras.getString("mr8");
        }
        if (extras != null) {
            mr9 = extras.getString("mr9");
        }
        if (extras != null) {
            mr10 = extras.getString("mr10");
        }
        if (extras != null) {
            breakdown_servies = extras.getString("breakdown_service");
            Log.e("A",breakdown_servies);
        }
        if (extras != null) {
            tech_signature = extras.getString("tech_signature");
        }
        if (extras != null) {
//            str_cust_name = extras.getString("customer_name");
//            et_cust_name.setText(str_cust_name);
        }
        if (extras != null) {
//            str_cust_no = extras.getString("customer_number");
//            et_cust_no.setText(str_cust_no);
        }

        if (extras != null) {
            customer_acknowledgement = extras.getString("customer_acknowledgement");
        }


        Spannable name_Upload = new SpannableString("Customer Name ");
        name_Upload.setSpan(new ForegroundColorSpan(Customer_Details_BreakdownActivity.this.getResources().getColor(R.color.colorAccent)), 0, name_Upload.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        txt_cust_name.setText(name_Upload);
        Spannable name_Upload1 = new SpannableString("*");
        name_Upload1.setSpan(new ForegroundColorSpan(Color.RED), 0, name_Upload1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        txt_cust_name.append(name_Upload1);

        Spannable no = new SpannableString("Customer Number ");
        no.setSpan(new ForegroundColorSpan(Customer_Details_BreakdownActivity.this.getResources().getColor(R.color.colorAccent)), 0, name_Upload.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        txt_cust_no.setText(no);
        Spannable no1 = new SpannableString("*");
        no1.setSpan(new ForegroundColorSpan(Color.RED), 0, name_Upload1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        txt_cust_no.append(no1);

        btn_prev.setBackgroundResource(R.drawable.blue_button_background_with_radius);
        btn_prev.setTextColor(getResources().getColor(R.color.white));
        btn_prev.setEnabled(true);

        if (status.equals("paused")){
            retrive_LocalValue();
        }
        else{
            getCustomer(job_id,service_title);
        }


        btnSelection.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                addCutomerData();



//                String s_cust_name = et_cust_name.getText().toString();
//                String s_cust_no = et_cust_no.getText().toString();
//
//                if (s_cust_name.equals("")) {
//                    et_cust_name.setError("Please Enter the Customer Name");
//                }
//               else if (s_cust_no.equals("")) {
//                    et_cust_no.setError("Please Enter the Customer Number");
//                }
//                else {
//                    Intent send = new Intent(Customer_Details_BreakdownActivity.this, Customer_AcknowledgementActivity.class);
//                    send.putExtra("value", value);
//                    send.putExtra("feedback_details", feedback_details);
//                    send.putExtra("feedback_group", feedback_group);
//                    send.putExtra("bd_details", bd_dta);
//                    send.putExtra("job_id", job_id);
//                    send.putExtra("feedback_remark", feedback_remark);
//                    send.putExtra("mr1", mr1);
//                    send.putExtra("mr2", mr2);
//                    send.putExtra("mr3", mr3);
//                    send.putExtra("mr4", mr4);
//                    send.putExtra("mr5", mr5);
//                    send.putExtra("mr6", mr6);
//                    send.putExtra("mr7", mr7);
//                    send.putExtra("mr8", mr8);
//                    send.putExtra("mr9", mr9);
//                    send.putExtra("mr10", mr10);
//                    send.putExtra("breakdown_service", breakdown_servies);
//                    send.putExtra("tech_signature", tech_signature);
//                    send.putExtra("customer_name", s_cust_name);
//                    send.putExtra("customer_number", s_cust_no);
//                    send.putExtra("customer_acknowledgement", customer_acknowledgement);
//                    send.putExtra("status",status);
//                    startActivity(send);
              //  }
            }
        });

        btn_prev.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                onBackPressed();
//                Intent send = new Intent( Customer_Details_BreakdownActivity.this, Technician_signatureActivity.class);
//                send.putExtra("value",value);
//                send.putExtra("feedback_details",feedback_details);
//                send.putExtra("feedback_group",feedback_group);
//                send.putExtra("bd_details",bd_dta);
//                send.putExtra("job_id",job_id);
//                send.putExtra("feedback_remark", feedback_remark);
//                send.putExtra("mr1", mr1);
//                send.putExtra("mr2", mr2);
//                send.putExtra("mr3", mr3);
//                send.putExtra("mr4", mr4);
//                send.putExtra("mr5", mr5);
//                send.putExtra("mr6", mr6);
//                send.putExtra("mr7", mr7);
//                send.putExtra("mr8", mr8);
//                send.putExtra("mr9", mr9);
//                send.putExtra("mr10", mr10);
//                send.putExtra("breakdown_service", breakdown_servies);
//                send.putExtra("tech_signature", tech_signature);
//                startActivity(send);
            }
        });

        iv_back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                onBackPressed();

//                Intent send = new Intent( Customer_Details_BreakdownActivity.this, Technician_signatureActivity.class);
//                send.putExtra("value",value);
//                send.putExtra("feedback_details",feedback_details);
//                send.putExtra("feedback_group",feedback_group);
//                send.putExtra("bd_details",bd_dta);
//                send.putExtra("job_id",job_id);
//                send.putExtra("feedback_remark", feedback_remark);
//                send.putExtra("mr1", mr1);
//                send.putExtra("mr2", mr2);
//                send.putExtra("mr3", mr3);
//                send.putExtra("mr4", mr4);
//                send.putExtra("mr5", mr5);
//                send.putExtra("mr6", mr6);
//                send.putExtra("mr7", mr7);
//                send.putExtra("mr8", mr8);
//                send.putExtra("mr9", mr9);
//                send.putExtra("mr10", mr10);
//                send.putExtra("breakdown_service", breakdown_servies);
//                send.putExtra("tech_signature", tech_signature);
//                startActivity(send);
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


                            s_cust_name = response.body().getData().getCustomer_name();
                            s_cust_no = response.body().getData().getCustomer_number();
                            Log.e("Nish Cname",""+ s_cust_name);
                            Log.e("Nish CNo",""+ s_cust_no);
                            et_cust_name.setText(s_cust_name);
                            et_cust_no.setText(s_cust_no);

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

    private void getCustomer(String job_id, String service_title) {

        Cursor cur = CommonUtil.dbUtil.getCustmer(job_id,service_title);

        Log.e("GET CUSTOMER ",""+cur.getCount());

        if (cur.getCount()>0 && cur.moveToFirst()){

            do {
                @SuppressLint("Range")
                String customer_name = cur.getString(cur.getColumnIndex(DbHelper.CUSTOMER_NAME));
                @SuppressLint("Range")
                String customer_no = cur.getString(cur.getColumnIndex(DbHelper.CUSTOMER_NUMBER));
            //    @SuppressLint("Range")
               // String customer_remarks = cur.getString(cur.getColumnIndex(DbHelper.CUSTOMER_REMARKS));

                et_cust_name.setText(customer_name);
                et_cust_no.setText(customer_no);
                // edt_Custremarks.setText(customer_remarks);

            }while (cur.moveToNext());
        }
    }

    private void addCutomerData() {

        Log.e("Hi Nish","Adding");

        s_cust_name = et_cust_name.getText().toString();
        s_cust_no = et_cust_no.getText().toString();
        String str_Custremarks = "-";

        if (s_cust_name.equals("")) {
            et_cust_name.setError("Please Enter the Customer Name");
        }
        else if (s_cust_no.equals("")) {
            et_cust_no.setError("Please Enter the Customer Number");
        }else{
            CommonUtil.dbUtil.addCustomer(job_id,service_title,s_cust_name,s_cust_no,"-");
            Log.e("JobID",""+job_id);
            Log.e("Name",""+service_title);
            Cursor cur = CommonUtil.dbUtil.getCustmer(job_id,service_title);
            Log.e("CUSTOMER",""+cur.getCount());
            Intent intent = new Intent(context,Customer_AcknowledgementActivity.class);
            intent.putExtra("status",status);
            intent.putExtra("breakdown_service", breakdown_servies);
            startActivity(intent);
        }
    }

    @Override
    public void onBackPressed() {

      //  super.onBackPressed();

        Intent intent = new Intent(context,Technician_signatureActivity.class);
        intent.putExtra("status",status);
        intent.putExtra("breakdown_service", breakdown_servies);
        startActivity(intent);
    }
}