package com.triton.johnson_tap_app.Engineer_Dashboard;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.triton.johnson_tap_app.R;
import com.triton.johnson_tap_app.RestUtils;
import com.triton.johnson_tap_app.activity.New_LoginActivity;
import com.triton.johnson_tap_app.api.APIInterface;
import com.triton.johnson_tap_app.api.RetrofitClient;
import com.triton.johnson_tap_app.requestpojo.Joblist_new_screenRequest;
import com.triton.johnson_tap_app.responsepojo.Joblist_new_screenResponse;

import java.util.ArrayList;
import java.util.List;

import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class JobList_DashboardActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private EditText etsearch;
    TextView txt_Lastlogin,txt_Welcome,txt_ServiceTitle;
    String se_id,se_user_mobile_no,se_user_name,message,title,agentName,agentNumber,service_Title,lastLogin;
    List<Joblist_new_screenResponse.DataBean> dataBeanList;
    JobList_DashboardAdapter activityBasedListAdapter;
    TextView text,txt_no_records,service_title;
    ImageView iv_back,img_clearsearch;
    LinearLayout logout;
    AlertDialog alertDialog;
    SwipeRefreshLayout swipeRefreshLayout;

    @SuppressLint("SetTextI18n")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_job_list_new_screen);


        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        txt_no_records = findViewById(R.id.txt_no_records);
        img_clearsearch = (ImageView) findViewById(R.id.img_clearsearch);
        etsearch = (EditText) findViewById(R.id.edt_search);
        service_title = (TextView) findViewById(R.id.service_title);
        logout = (LinearLayout) findViewById(R.id.logout);
        txt_Lastlogin = findViewById(R.id.txt_lastlogin);
        txt_Welcome = findViewById(R.id.txt_welcome);
        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);


        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        se_id = sharedPreferences.getString("_id", "default value");
        se_user_mobile_no = sharedPreferences.getString("user_mobile_no", "default value");
        se_user_name = sharedPreferences.getString("user_name", "default value");
        agentName = sharedPreferences.getString("agent_name","name");
        agentNumber = sharedPreferences.getString("agent_number","123456789");
        service_Title = sharedPreferences.getString("service_title","ServiceName");
        lastLogin = sharedPreferences.getString("last_login","2020-10-28 05:36:33.000 ");
      //  lastLogin = lastLogin.substring(0,20);
        lastLogin = lastLogin.substring(0,lastLogin.length() -5);
        lastLogin = lastLogin.replaceAll("[^0-9-:]", " ");

//        StringBuffer sb= new StringBuffer(lastLogin);
//        sb.deleteCharAt(sb.length()-4);


        Log.e("Agent Number",""+agentNumber);
        Log.e("Service Name",""+service_Title);
        service_title.setText(service_Title);
        txt_Welcome.setText("Welcome " +agentName);
        txt_Lastlogin.setText("Last Login : " + lastLogin);


        Bundle extras = getIntent().getExtras();
        if (extras != null) {
//            title = extras.getString("service_title");
//            service_title.setText(title);
        }

        //jobFindResponseCall("8976322100","LR SERVICE");
        jobFindResponseCall();

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(false);
                jobFindResponseCall();
            }
        });
        etsearch.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {

                String Searchvalue = etsearch.getText().toString();

                recyclerView.setVisibility(View.VISIBLE);
                txt_no_records.setVisibility(View.GONE);

                filter(Searchvalue);
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String Searchvalue = etsearch.getText().toString();

                if(Searchvalue.equals("")){
                    recyclerView.setVisibility(View.VISIBLE);
                    jobFindResponseCall();
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
                jobFindResponseCall();
                img_clearsearch.setVisibility(View.INVISIBLE);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                alertDialog = new AlertDialog.Builder(JobList_DashboardActivity.this)
                        .setTitle("Logout")
                        .setMessage("Are youe sure do you want to Logout ?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialogInterface, int i) {

                                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(JobList_DashboardActivity.this);
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.clear();
                                editor.commit();

                                Toasty.success(getApplicationContext(),"Logout Sucessfully", Toast.LENGTH_SHORT, true).show();
                                Intent send = new Intent(JobList_DashboardActivity.this, New_LoginActivity.class);
                                startActivity(send);
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialogInterface, int i) {
                                alertDialog.dismiss();
                            }
                        })
                        .show();
            }
        });


    }

    private void filter(String s) {
        List<Joblist_new_screenResponse.DataBean> filteredlist = new ArrayList<>();
        for(Joblist_new_screenResponse.DataBean item : dataBeanList)
        {
            if(item.getJob_no().toLowerCase().contains(s.toLowerCase()))
            {
                Log.w(TAG,"filter----"+item.getJob_no().toLowerCase().contains(s.toLowerCase()));
                filteredlist.add(item);

            }
        }
        if(filteredlist.isEmpty())
        {
           // Toast.makeText(this,"No Data Found ... ",Toast.LENGTH_SHORT).show();
            recyclerView.setVisibility(View.GONE);
            txt_no_records.setVisibility(View.VISIBLE);
            txt_no_records.setText("No Data Found");
        }else
        {
            activityBasedListAdapter.filterList(filteredlist);
        }

    }


    private void jobFindResponseCall() {
        APIInterface apiInterface = RetrofitClient.getClient().create(APIInterface.class);
        Call<Joblist_new_screenResponse> call = apiInterface.Job_list_new_screenResponseCall(RestUtils.getContentType(), serviceRequest());
        Log.w(TAG, "Jobno Find Response url  :%s" + " " + call.request().url().toString());
        call.enqueue(new Callback<Joblist_new_screenResponse>() {
            @SuppressLint("LogNotTimber")
            @Override
            public void onResponse(@NonNull Call<Joblist_new_screenResponse> call, @NonNull Response<Joblist_new_screenResponse> response) {
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
            public void onFailure(@NonNull Call<Joblist_new_screenResponse> call, @NonNull Throwable t) {
                Log.e("Jobno Find ", "--->" + t.getMessage());
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private Joblist_new_screenRequest serviceRequest() {
        Joblist_new_screenRequest service = new Joblist_new_screenRequest();
        service.setUser_mobile_no(agentNumber);
        service.setService_name(service_Title);
        Log.w(TAG, "Jobno Find Request " + new Gson().toJson(service));
        return service;
    }

    private void setView(List<Joblist_new_screenResponse.DataBean> dataBeanList) {

        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        activityBasedListAdapter = new JobList_DashboardAdapter(getApplicationContext(), dataBeanList);
        recyclerView.setAdapter(activityBasedListAdapter);
    }
}