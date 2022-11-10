package com.triton.johnson_tap_app.Engineer_Dashboard;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import com.triton.johnson_tap_app.requestpojo.Agent_new_screenRequest;
import com.triton.johnson_tap_app.responsepojo.Service_list_new_screenResponse;

import java.util.ArrayList;
import java.util.List;

import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ServicesList_DashboardActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private EditText etsearch;
    TextView txt_last_login,txt_Lastlogin;
    String se_id,se_user_mobile_no,se_user_name,message,agentName,agentNumber,lastLogin;
    List<Service_list_new_screenResponse.DataBean> dataBeanList;
   ServicesList_DashboardAdapter activityBasedListAdapter;
    TextView text,txt_no_records, title_Welcome;
    ImageView iv_back,img_clearsearch;
    String title;
    LinearLayout logout;
    AlertDialog alertDialog;

    @SuppressLint({"MissingInflatedId", "SetTextI18n"})
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_services_list_new_screen);

        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        txt_no_records = findViewById(R.id.txt_no_records);
        img_clearsearch = (ImageView) findViewById(R.id.img_clearsearch);
        etsearch = (EditText) findViewById(R.id.edt_search);
        title_Welcome = (TextView) findViewById(R.id.txt_welcome);
        logout = (LinearLayout) findViewById(R.id.logout);
        txt_Lastlogin = findViewById(R.id.txt_lastlogin);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        se_id = sharedPreferences.getString("_id", "default value");
        se_user_mobile_no = sharedPreferences.getString("user_mobile_no", "default value");
        se_user_name = sharedPreferences.getString("user_name", "default value");
        agentName = sharedPreferences.getString("agent_name","name");
        agentNumber = sharedPreferences.getString("agent_number","123456789");
        Log.e("Agent Number",""+agentNumber);
        lastLogin = sharedPreferences.getString("last_login","2020-10-28 05:36:33.000 ");
       // lastLogin = lastLogin.substring(0,20);
        lastLogin = lastLogin.substring(0,lastLogin.length() -5);
        lastLogin = lastLogin.replaceAll("[^0-9-:]", " ");

        title_Welcome.setText("Welcome " +agentName);
        txt_Lastlogin.setText("Last Login : " + lastLogin);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
//            title = extras.getString("title");
//            title_name.setText(title);
        }

                jobFindResponseCall();
       // jobFindResponseCall("8976322100");

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
                  //  jobFindResponseCall();
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

                alertDialog = new AlertDialog.Builder(ServicesList_DashboardActivity.this)
                        .setTitle("Logout")
                        .setMessage("Are youe sure do you want to Logout ?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialogInterface, int i) {

                                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(ServicesList_DashboardActivity.this);
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.clear();
                                editor.commit();

                                Toasty.success(getApplicationContext(),"Logout Sucessfully", Toast.LENGTH_SHORT, true).show();
                                Intent send = new Intent(ServicesList_DashboardActivity.this, New_LoginActivity.class);
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
        List<Service_list_new_screenResponse.DataBean> filteredlist = new ArrayList<>();
        for(Service_list_new_screenResponse.DataBean item : dataBeanList)
        {
            if(item.getService_name().toLowerCase().contains(s.toLowerCase()))
            {
                Log.w(TAG,"filter----"+item.getService_name().toLowerCase().contains(s.toLowerCase()));
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
        Call<Service_list_new_screenResponse> call = apiInterface.Services_list_new_screenResponseCall(RestUtils.getContentType(), serviceRequest());
        Log.w(TAG, "Jobno Find Response url  :%s" + " " + call.request().url().toString());
        call.enqueue(new Callback<Service_list_new_screenResponse>() {
            @SuppressLint("LogNotTimber")
            @Override
            public void onResponse(@NonNull Call<Service_list_new_screenResponse> call, @NonNull Response<Service_list_new_screenResponse> response) {
                Log.w(TAG, "Jobno Find Response" + new Gson().toJson(response.body()));

                if (response.body() != null) {

                    message = response.body().getMessage();
                    Log.d("message", message);

                    if (200 == response.body().getCode()) {
                        if (response.body().getData() != null) {
                            dataBeanList = response.body().getData();

                            if (dataBeanList.size() == 0){

                                recyclerView.setVisibility(View.GONE);
                                txt_no_records.setVisibility(View.VISIBLE);
                                txt_no_records.setText("No Records Found");
                                etsearch.setEnabled(false);

                            }

                            setView(dataBeanList);
                            Log.d("dataaaaa", String.valueOf(dataBeanList));

                        }

                    } else if (400 == response.body().getCode()) {
                        if (response.body().getMessage() != null && response.body().getMessage().equalsIgnoreCase("There is already a user registered with this email id. Please add new email id")) {

                            recyclerView.setVisibility(View.GONE);
                            txt_no_records.setVisibility(View.VISIBLE);
                            txt_no_records.setText("Error 404 Found..!");
                            etsearch.setEnabled(false);
                        }
                    } else {

                        Toasty.warning(getApplicationContext(), "" + response.body().getMessage(), Toasty.LENGTH_LONG).show();
                        recyclerView.setVisibility(View.GONE);
                        txt_no_records.setVisibility(View.VISIBLE);
                        txt_no_records.setText("Error 404 Found..!");
                        etsearch.setEnabled(false);
                    }
                }

            }

            @Override
            public void onFailure(@NonNull Call<Service_list_new_screenResponse> call, @NonNull Throwable t) {
                Log.e("Jobno Find ", "--->" + t.getMessage());
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                recyclerView.setVisibility(View.GONE);
                txt_no_records.setVisibility(View.VISIBLE);
                txt_no_records.setText("Something went wrong..! Try agin");
                etsearch.setEnabled(false);
            }
        });

    }

    private Agent_new_screenRequest serviceRequest() {
        Agent_new_screenRequest service = new Agent_new_screenRequest();
        service.setUser_mobile_no(se_user_mobile_no);
        Log.w(TAG, "Jobno Find Request " + new Gson().toJson(service));
        return service;
    }

    private void setView(List<Service_list_new_screenResponse.DataBean> dataBeanList) {

        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        activityBasedListAdapter = new ServicesList_DashboardAdapter(getApplicationContext(), dataBeanList);
        recyclerView.setAdapter(activityBasedListAdapter);
    }
}