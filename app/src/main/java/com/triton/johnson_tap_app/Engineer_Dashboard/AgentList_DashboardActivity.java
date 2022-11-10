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
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.triton.johnson_tap_app.R;
import com.triton.johnson_tap_app.RestUtils;
import com.triton.johnson_tap_app.activity.Main_Menu_ServicesActivity;
import com.triton.johnson_tap_app.activity.New_LoginActivity;
import com.triton.johnson_tap_app.api.APIInterface;
import com.triton.johnson_tap_app.api.RetrofitClient;
import com.triton.johnson_tap_app.requestpojo.Agent_new_screenRequest;
import com.triton.johnson_tap_app.requestpojo.CheckAttenRequest;
import com.triton.johnson_tap_app.requestpojo.LogoutRequest;
import com.triton.johnson_tap_app.responsepojo.Agent_new_screenResponse;
import com.triton.johnson_tap_app.responsepojo.CheckAttenResponse;
import com.triton.johnson_tap_app.responsepojo.LogoutResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AgentList_DashboardActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private EditText etsearch;
    TextView txt_last_login;
    String se_id,se_user_mobile_no,se_user_name,message,lastLogin;
    List<Agent_new_screenResponse.DataBean> dataBeanList;
    AgentList_DashboardAdapter activityBasedListAdapter;
    TextView text,txt_no_records,txt_Lastlogin,txt_Welcome;
    ImageView iv_back,img_clearsearch;
    LinearLayout logout;
    AlertDialog alertDialog;
    SwipeRefreshLayout swipeRefreshLayout;
    Context context;
    Spinner spinner;
    ArrayList<String> students;
    String latitude, longitude, no_of_hours;
    String endDateandTime, currentDateandTime,currentDate,str_spinner,current,start,check_id;
    long elapsedHours;
    long elapsedMinutes;
    JSONArray result;
    RelativeLayout rel_Job;

    @SuppressLint({"ClickableViewAccessibility", "MissingInflatedId"})
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_agent_list_new_screen);
        context = this;

        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        txt_no_records = findViewById(R.id.txt_no_records);
        img_clearsearch = (ImageView) findViewById(R.id.img_clearsearch);
        etsearch = (EditText) findViewById(R.id.edt_search);
        logout = (LinearLayout) findViewById(R.id.logout);
        txt_Lastlogin = findViewById(R.id.txt_lastlogin);
        txt_Welcome = findViewById(R.id.txt_welcome);
        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);
        rel_Job = findViewById(R.id.rel_job);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        se_id = sharedPreferences.getString("_id", "default value");
        se_user_mobile_no = sharedPreferences.getString("user_mobile_no", "default value");
        se_user_name = sharedPreferences.getString("user_name", "default value");
        lastLogin = sharedPreferences.getString("last_login","2020-10-28 05:36:33.000");
//        lastLogin = lastLogin.substring(0,20);
        lastLogin = lastLogin.substring(0,lastLogin.length() -5);
        lastLogin = lastLogin.replaceAll("[^0-9-:]", " ");

        txt_Lastlogin.setText("Last Login : " + lastLogin);
        txt_Welcome.setText("Welcome "+ se_user_name);


        CheckAttendanceResponseCall();
      //  jobFindResponseCall("98765432111");
        jobFindResponseCall(se_user_mobile_no);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(false);
                jobFindResponseCall(se_user_mobile_no);
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
                  //  jobFindResponseCall(se_user_mobile_no);
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
                jobFindResponseCall(se_user_mobile_no);
                img_clearsearch.setVisibility(View.INVISIBLE);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                android.app.AlertDialog.Builder mBuilder = new android.app.AlertDialog.Builder(context);
                View mView = getLayoutInflater().inflate(R.layout.dialog_logout, null);
                spinner = (Spinner) mView.findViewById(R.id.spinner);
                Button yes = (Button) mView.findViewById(R.id.btn_yes);
                Button no = (Button) mView.findViewById(R.id.btn_no);

                students = new ArrayList<String>();

                mBuilder.setView(mView);
                final android.app.AlertDialog dialog = mBuilder.create();
                dialog.show();

                getData();


                // retrieveJSON();

                yes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        str_spinner = spinner.getSelectedItem().toString();

                        if(str_spinner.equals("Select Logout Reason")){

                            Toast.makeText(context, "Please Selected Logout Reason", Toast.LENGTH_SHORT).show();

                        }
                        else {

                            SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy hh:mm aa", Locale.getDefault());
                            endDateandTime = sdf1.format(new Date());

                            //       Toast.makeText(Main_Menu_ServicesActivity.this, "current :" + start, Toast.LENGTH_SHORT).show();

                            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm aa");


                            try {
                                Date date1 = simpleDateFormat.parse(start);
                                Date date2 = simpleDateFormat.parse(endDateandTime);

                                printDifference(date1, date2);

                            } catch (ParseException e) {
                                e.printStackTrace();
                            }

                            logoutResponseCall(check_id, endDateandTime, str_spinner,latitude,longitude,no_of_hours);
                            dialog.dismiss();

                        }

                    }
                });

                no.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
//                        profile_gray.setVisibility(View.VISIBLE);
//                        profile_green.setVisibility(View.GONE);
                        //   Toast.makeText(Main_Menu_ServicesActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();

                    }
                });
            }
        });

    }

    private void logoutResponseCall(String id, String att_end_time, String att_reason, String att_end_lat, String att_end_long, String att_no_of_hrs) {
        APIInterface apiInterface = RetrofitClient.getClient().create(APIInterface.class);
        Call<LogoutResponse> call = apiInterface.logoutResponseCall(com.triton.johnson_tap_app.utils.RestUtils.getContentType(), addReviewRequest(id,att_end_time,att_reason,att_end_lat,att_end_long, att_no_of_hrs));
        Log.w(VolleyLog.TAG,"addReviewResponseCall url  :%s"+" "+ call.request().url().toString());


        call.enqueue(new Callback<LogoutResponse>() {
            @Override
            public void onResponse(@NonNull Call<LogoutResponse> call, @NonNull retrofit2.Response<LogoutResponse> response) {

                Log.w(VolleyLog.TAG,"AddReviewResponse"+ "--->" + new Gson().toJson(response.body()));

                Log.w(VolleyLog.TAG,"Response"+ "--->" + "_id : " + id + "," + "att_end_time : " + att_end_time + ","+ "att_reason : " + att_reason + "," + "att_end_lat : " + att_end_lat + "," + "att_end_long : " + att_end_long + ","+ "att_no_of_hrs : " + att_no_of_hrs);

                if (response.body() != null) {
                    if(response.body().getCode() == 200){

                        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.clear();
                        editor.commit();

                        Toasty.success(getApplicationContext(),"Logout Sucessfully", Toast.LENGTH_SHORT, true).show();
                        Intent send = new Intent(context, New_LoginActivity.class);
                        startActivity(send);
                    }
                    else{
                    }
                }


            }

            @SuppressLint("LongLogTag")
            @Override
            public void onFailure(@NonNull Call<LogoutResponse> call, @NonNull Throwable t) {

                Log.w(VolleyLog.TAG,"AddReviewResponse flr"+"--->" + t.getMessage());
            }
        });

    }

    private LogoutRequest addReviewRequest(String id, String att_end_time, String att_reason, String att_end_lat, String att_end_long, String att_no_hrs) {

        LogoutRequest addReviewRequest = new LogoutRequest();
        addReviewRequest.set_id(id);
        addReviewRequest.setAtt_end_time(att_end_time);
        addReviewRequest.setAtt_reason(att_reason);
        addReviewRequest.setAtt_end_lat(att_end_lat);
        addReviewRequest.setAtt_end_long(att_end_long);
        addReviewRequest.setAtt_no_of_hrs(att_no_hrs);
        addReviewRequest.setUser_mobile_no(se_user_mobile_no);
        Log.w(VolleyLog.TAG,"addReviewRequest" + new Gson().toJson(addReviewRequest));
        return addReviewRequest;
    }

    private void CheckAttendanceResponseCall() {

        APIInterface apiInterface = RetrofitClient.getClient().create(APIInterface.class);
        Call<CheckAttenResponse> call = apiInterface.CheckAttenResponseCall(com.triton.johnson_tap_app.utils.RestUtils.getContentType(), checkattRequest());
        Log.w(VolleyLog.TAG,"AttendanceResponse url  :%s"+" "+ call.request().url().toString());

        call.enqueue(new Callback<CheckAttenResponse>() {
            @SuppressLint("LogNotTimber")
            @Override
            public void onResponse(@NonNull Call<CheckAttenResponse> call, @NonNull retrofit2.Response<CheckAttenResponse> response) {

                Log.e(VolleyLog.TAG,"Attendance Response" + new Gson().toJson(response.body()));
                if (response.body() != null) {
                    message = response.body().getMessage();
                    start = response.body().getData().getAtt_start_time();
                    check_id = response.body().getData().get_id();

//                    if(message.equals("Not Present")){
//                        profile_gray.setVisibility(View.GONE);
//                        profile_green.setVisibility(View.GONE);
//                        profile_red.setVisibility(View.VISIBLE);
//                        logout.setVisibility(View.INVISIBLE);
//
////                        menu_service.setEnabled(false);
////                        menu_view_status.setEnabled(false);
////                        menu_agent_profile.setEnabled(false);
////                        menu_change_password.setEnabled(false);
//
//                    }
//                    else if(message.equals("Present")){
//                        logout.setVisibility(View.VISIBLE);
//                        profile_gray.setVisibility(View.GONE);
//                        profile_red.setVisibility(View.GONE);
//                        profile_green.setVisibility(View.VISIBLE);
//                    }
//                    else {
//                        profile_gray.setVisibility(View.GONE);
//                        profile_green.setVisibility(View.GONE);
//                        profile_red.setVisibility(View.VISIBLE);
//                        logout.setVisibility(View.INVISIBLE);
//                        Toast.makeText(Main_Menu_ServicesActivity.this, "You have already logout. ", Toast.LENGTH_SHORT).show();
//                    }


                    if (200 == response.body().getCode()) {
                        if(response.body().getData() != null){


                        }


                    } else {
//                        dialog.dismiss();
                        Toasty.warning(getApplicationContext(),""+message,Toasty.LENGTH_LONG).show();

                    }
                }


            }

            @Override
            public void onFailure(@NonNull Call<CheckAttenResponse> call, @NonNull Throwable t) {
                Log.e("OTP", "--->" + t.getMessage());
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
    private CheckAttenRequest checkattRequest() {

        /**
         * user_id : 12345
         * user_password : 12345
         * last_login_time : 20-10-2021 11:00 AM
         */

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
        currentDateandTime = sdf.format(new Date());

        CheckAttenRequest checkRequest = new CheckAttenRequest();
        checkRequest.setUser_mobile_no(se_user_mobile_no);
        checkRequest.setAtt_date(currentDateandTime);
        Log.e(VolleyLog.TAG,"Attendance Request "+ new Gson().toJson(checkRequest));
        return checkRequest;
    }

    private void getData(){
        //Creating a string request
        StringRequest stringRequest = new StringRequest("http://smart.johnsonliftsltd.com:3000/api/service_userdetails/logout_reason",
                new com.android.volley.Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        JSONObject j = null;
                        try {
                            j = new JSONObject(response);

                            result = j.getJSONArray("Data");
                            getStudents(result);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new com.android.volley.Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void getStudents(JSONArray j){
        students.add("Select Logout Reason");
        for(int i=0;i<j.length();i++){
            try {
                JSONObject json = j.getJSONObject(i);
                students.add(json.getString("logout_reason"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(context,R.layout.spinner_item, students);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
        spinner.setAdapter(spinnerArrayAdapter);
        // spinner.setAdapter(new ArrayAdapter<String>(Main_Menu_ServicesActivity.this, R.layout.spinner_item, students));
    }

    public void printDifference(Date startDate, Date endDate) {
        long different = endDate.getTime() - startDate.getTime();

        long secondsInMilli = 1000;
        long minutesInMilli = secondsInMilli * 60;
        long hoursInMilli = minutesInMilli * 60;
        long daysInMilli = hoursInMilli * 24;

        long elapsedDays = different / daysInMilli;
        different = different % daysInMilli;

        elapsedHours = different / hoursInMilli;
        different = different % hoursInMilli;

        elapsedMinutes = different / minutesInMilli;

        no_of_hours = elapsedHours + "." + elapsedMinutes;

        Log.d("time", elapsedHours + "." + elapsedMinutes);
    }

    private void filter(String s) {
        List<Agent_new_screenResponse.DataBean> filteredlist = new ArrayList<>();
        for(Agent_new_screenResponse.DataBean item : dataBeanList)
        {
            if(item.getUser_name().toLowerCase().contains(s.toLowerCase()))
            {
                Log.w(TAG,"filter----"+item.getUser_name().toLowerCase().contains(s.toLowerCase()));
                filteredlist.add(item);

            }
        }
        if(filteredlist.isEmpty())
        {
          //  Toast.makeText(this,"No Data Found ... ",Toast.LENGTH_SHORT).show();
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
        Call<Agent_new_screenResponse> call = apiInterface.Agent_new_screenResponseCall(RestUtils.getContentType(), serviceRequest(job_no));
        Log.w(TAG, "Jobno Find Response url  :%s" + " " + call.request().url().toString());
        call.enqueue(new Callback<Agent_new_screenResponse>() {
            @SuppressLint("LogNotTimber")
            @Override
            public void onResponse(@NonNull Call<Agent_new_screenResponse> call, @NonNull Response<Agent_new_screenResponse> response) {
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
            public void onFailure(@NonNull Call<Agent_new_screenResponse> call, @NonNull Throwable t) {
                Log.e("Jobno Find ", "--->" + t.getMessage());
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                recyclerView.setVisibility(View.GONE);
                txt_no_records.setVisibility(View.VISIBLE);
                txt_no_records.setText("Something went wrong..! Try agin");
                etsearch.setEnabled(false);
            }
        });

    }

    private Agent_new_screenRequest serviceRequest(String job_no) {
        Agent_new_screenRequest service = new Agent_new_screenRequest();
        service.setUser_mobile_no(job_no);
        Log.w(TAG, "Jobno Find Request " + new Gson().toJson(service));
        return service;
    }

    private void setView(List<Agent_new_screenResponse.DataBean> dataBeanList) {

        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        activityBasedListAdapter = new AgentList_DashboardAdapter(getApplicationContext(), dataBeanList);
        recyclerView.setAdapter(activityBasedListAdapter);
    }

}