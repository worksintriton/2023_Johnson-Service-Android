package com.triton.johnson_tap_app.Service_Activity.Breakdown_Services;

import static android.content.ContentValues.TAG;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
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
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.triton.johnson_tap_app.Db.CommonUtil;
import com.triton.johnson_tap_app.Db.DbHelper;
import com.triton.johnson_tap_app.Db.DbUtil;
import com.triton.johnson_tap_app.R;
import com.triton.johnson_tap_app.RestUtils;
import com.triton.johnson_tap_app.Service_Adapter.Feedback_GroupAdapter;
import com.triton.johnson_tap_app.api.APIInterface;
import com.triton.johnson_tap_app.api.RetrofitClient;
import com.triton.johnson_tap_app.requestpojo.Feedback_GroupRequest;
import com.triton.johnson_tap_app.responsepojo.Feedback_GroupResponse;

import java.util.ArrayList;
import java.util.List;

import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Feedback_GroupActivity extends AppCompatActivity {

    TextView text,txt_no_records;
    Button btnSelection, btn_prev;
    ImageView iv_back,img_clearsearch;
    private RecyclerView recyclerView;
    List<Feedback_GroupResponse.DataBean> dataBeanList;
    Feedback_GroupAdapter activityBasedListAdapter;
    String Title, Codes,petimage,bd_dta,job_id,message,search_string,service_title, pre_check ="",status;
    int textlength = 0;
    EditText etsearch;
    AlertDialog alertDialog;
    Context context;
    SharedPreferences sharedPreferences;
    ArrayList<String> mydata = new ArrayList<>();
    ArrayList<String> outputList = null;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_feedback_group);
        context = this;

        CommonUtil.dbUtil = new DbUtil(context);
        CommonUtil.dbUtil.open();
        CommonUtil.dbHelper = new DbHelper(context);



        text = findViewById(R.id.text);
        txt_no_records = findViewById(R.id.txt_no_records);
        btnSelection = (Button) findViewById(R.id.btn_next);
        btn_prev = (Button) findViewById(R.id.btn_show);
        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        iv_back = (ImageView) findViewById(R.id.iv_back);
        img_clearsearch = (ImageView) findViewById(R.id.img_clearsearch);
        etsearch = (EditText) findViewById(R.id.edt_search);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        job_id = sharedPreferences.getString("job_id", "default value");
        service_title = sharedPreferences.getString("service_title", "default value");
        Log.e("JobID",""+job_id);
        Log.e("Name",""+service_title);

       // CommonUtil.dbUtil.reportDeletePreventiveListDelete(job_id,service_title);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            bd_dta = extras.getString("bd_details");
            status = extras.getString("status");
            Log.e("Status",status);
        }

        if (extras != null) {
          //  job_id = extras.getString("job_id");
        }


        Spannable name_Upload = new SpannableString("Description ");
        name_Upload.setSpan(new ForegroundColorSpan(Feedback_GroupActivity.this.getResources().getColor(R.color.colorAccent)), 0, name_Upload.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        text.setText(name_Upload);
        Spannable name_Upload1 = new SpannableString("*");
        name_Upload1.setSpan(new ForegroundColorSpan(Color.RED), 0, name_Upload1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        text.append(name_Upload1);

        btn_prev.setBackgroundResource(R.drawable.blue_button_background_with_radius);
        btn_prev.setTextColor(getResources().getColor(R.color.white));
        btn_prev.setEnabled(true);


        if (status.equals("new")){


        }
        else{


        }

      //  jobFindResponseCall("L-Q1234");
        jobFindResponseCall(job_id);

        btnSelection.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
//                String data = "";
//                for (int i = 0; i < dataBeanList.size(); i++) {
//                    Feedback_GroupResponse.DataBean singleStudent = dataBeanList.get(i);
//                    if (singleStudent.isSelected() == true) {
//                        data = data + "," + dataBeanList.get(i).getCodes().toString();
//                    }
//                }

                Cursor cur = CommonUtil.dbUtil.getFeedbackgroup(job_id,service_title, "2");
                Log.e("FeedBack Group get",""+cur.getCount());
                mydata = new ArrayList<>();
                if(cur.getCount() >0 && cur.moveToFirst()){

                    do{
                        @SuppressLint("Range")
                        String abc = cur.getString(cur.getColumnIndex(DbHelper.FEEDBACK_GROUP));
                        Log.e("Datas",""+abc);
                        mydata.add(abc);
                    }while (cur.moveToNext());

                } else{
                    Log.e("Datasss",""+cur);

                }

                ArrayList<String> outputList = new ArrayList<String>();
                for (String item: mydata) {
                    //outputList.add("\""+item+"\"");
                    outputList.add(""+item+"");
                   // outputList.remove("null");
                }

                pre_check = String.valueOf(outputList);
//                   pre_check = pre_check.replaceAll("\\[", "").replaceAll("\\]","");
//                  System.out.println("EEEEEEEEEEE"+ddd);

                Log.e("FEEDBACK GROUP", String.valueOf(mydata));
                // Log.e("FEEDBACK GROUP", String.valueOf(outputList));
                Log.e("FEEDBACK GROUP 1", pre_check);

               // sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("feedbackgroup", pre_check);
                Log.e("FeedBack Group", pre_check);
                editor.putString("Hi","Nishanth");
                Log.e("FeedBack Group","dsfds");
                editor.apply();

              //  if(data.equals("")){
                if(cur.getCount() ==0) {

                    alertDialog = new AlertDialog.Builder(Feedback_GroupActivity.this)
                            .setTitle("Please Selected Value")
                            .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    alertDialog.dismiss();
                                }
                            })
                            .show();
                }
                else {
                Intent send = new Intent(Feedback_GroupActivity.this, Feedback_DetailsActivity.class);
                send.putExtra("feedback_group", pre_check);
                send.putExtra("bd_details",bd_dta);
                send.putExtra("job_id",job_id);
                send.putExtra("status",status);
                startActivity(send);
                }

            }
        });

        btn_prev.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                onBackPressed();

//                Intent send = new Intent(Feedback_GroupActivity.this, BD_DetailsActivity.class);
//                send.putExtra("bd_details",bd_dta);
//                send.putExtra("job_id",job_id);
//                startActivity(send);
            }
        });

        iv_back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                onBackPressed();
//
//                Intent send = new Intent(Feedback_GroupActivity.this, BD_DetailsActivity.class);
//                send.putExtra("bd_details",bd_dta);
//                send.putExtra("job_id",job_id);
//                startActivity(send);
            }
        });

        etsearch.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String Searchvalue = etsearch.getText().toString();

                if(Searchvalue.equals("")){
                    recyclerView.setVisibility(View.VISIBLE);
                    jobFindResponseCall(job_id);
                    img_clearsearch.setVisibility(View.INVISIBLE);
                }
                else {
                    //   Log.w(TAG,"Search Value---"+Searchvalue);
                    filter(Searchvalue);
                }
            }
        });

        img_clearsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                etsearch.setText("");
                recyclerView.setVisibility(View.VISIBLE);
                jobFindResponseCall(job_id);
                img_clearsearch.setVisibility(View.INVISIBLE);
            }
        });


    }

    private void filter(String s) {
        List<Feedback_GroupResponse.DataBean> filteredlist = new ArrayList<>();
        for(Feedback_GroupResponse.DataBean item : dataBeanList)
        {
            if(item.getTitle().toLowerCase().contains(s.toLowerCase()))
            {
                Log.w(TAG,"filter----"+item.getTitle().toLowerCase().contains(s.toLowerCase()));
                filteredlist.add(item);

            }
        }
        if(filteredlist.isEmpty())
        {
            Toast.makeText(this,"No Data Found ... ",Toast.LENGTH_SHORT).show();
            recyclerView.setVisibility(View.GONE);
            txt_no_records.setVisibility(View.VISIBLE);
            txt_no_records.setText("No Data Found");
        }else
        {
            activityBasedListAdapter.filterList(filteredlist);
        }

    }

    private void jobFindResponseCall(String job_no) {
        APIInterface apiInterface = RetrofitClient.getClient().create(APIInterface.class);
        Call<Feedback_GroupResponse> call = apiInterface.feedback_groupResponseCall(RestUtils.getContentType(), serviceRequest(job_no));
        Log.w(TAG, "Jobno Find Response url  :%s" + " " + call.request().url().toString());
        call.enqueue(new Callback<Feedback_GroupResponse>() {
            @SuppressLint("LogNotTimber")
            @Override
            public void onResponse(@NonNull Call<Feedback_GroupResponse> call, @NonNull Response<Feedback_GroupResponse> response) {
                Log.w(TAG, "Jobno Find Response" + new Gson().toJson(response.body()));

                if (response.body() != null) {

                    message = response.body().getMessage();
                    Log.d("message", message);

                    if (200 == response.body().getCode()) {
                        if (response.body().getData() != null) {
                           dataBeanList = response.body().getData();

                            setView(dataBeanList);
                            Log.d("dataaaaa", String.valueOf(dataBeanList));

                        }

                    } else if (400 == response.body().getCode()) {
                        if (response.body().getMessage() != null && response.body().getMessage().equalsIgnoreCase("There is already a user registered with this email id. Please add new email id")) {

                        }
                    } else {

                        Toasty.warning(getApplicationContext(), "" + response.body().getMessage(), Toasty.LENGTH_LONG).show();
                    }
                }

            }

            @Override
            public void onFailure(@NonNull Call<Feedback_GroupResponse> call, @NonNull Throwable t) {
                Log.e("Jobno Find ", "--->" + t.getMessage());
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private Feedback_GroupRequest serviceRequest(String job_no) {
        Feedback_GroupRequest service = new Feedback_GroupRequest();
        service.setJob_id(job_no);
        Log.w(TAG, "Jobno Find Request " + new Gson().toJson(service));
        return service;
    }

    private void setView(List<Feedback_GroupResponse.DataBean> dataBeanList) {

        Cursor cur = CommonUtil.dbUtil.getFeedbackgroup(job_id, service_title, "2");
        Log.e("Checklist get Data", "" + cur.getCount());

        if (cur.getCount() > 0 && cur.moveToFirst()) {

            do {
                @SuppressLint("Range")
                String abc = cur.getString(cur.getColumnIndex(DbHelper.FEEDBACK_GROUP));
                Log.e("Data Get", "" + abc);
                mydata.add(abc);
//                outputList = new ArrayList<String>();
//                for (String item : mydata) {
//                    //outputList.add("\""+item+"\"");
//                    outputList.add("" + item + "");
//                    outputList.remove("null");
//                }

            } while (cur.moveToNext());

        }


        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        activityBasedListAdapter = new Feedback_GroupAdapter(getApplicationContext(), dataBeanList, mydata);
        recyclerView.setAdapter(activityBasedListAdapter);
    }

    @Override
    public void onBackPressed() {

       // super.onBackPressed();
        Intent intent = new Intent(context,BD_DetailsActivity.class);
        intent.putExtra("status",status);
        startActivity(intent);

    }
}