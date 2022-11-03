package com.triton.johnson_tap_app.activity;

import static com.android.volley.VolleyLog.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.triton.johnson_tap_app.R;
import com.triton.johnson_tap_app.Service_Activity.Agent_ProfileActivity;
import com.triton.johnson_tap_app.Service_Activity.Breakdown_Services.Job_StatusActivity;
import com.triton.johnson_tap_app.Service_Activity.ServicesActivity;
import com.triton.johnson_tap_app.api.APIInterface;
import com.triton.johnson_tap_app.api.RetrofitClient;
import com.triton.johnson_tap_app.requestpojo.CheckAttenRequest;
import com.triton.johnson_tap_app.requestpojo.CountRequest;
import com.triton.johnson_tap_app.requestpojo.CreateRequest;
import com.triton.johnson_tap_app.requestpojo.LogoutRequest;
import com.triton.johnson_tap_app.responsepojo.CheckAttenResponse;
import com.triton.johnson_tap_app.responsepojo.CountResponse;
import com.triton.johnson_tap_app.responsepojo.CreateResponse;
import com.triton.johnson_tap_app.responsepojo.LogoutResponse;
import com.triton.johnson_tap_app.utils.RestUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;

public class Main_Menu_ServicesActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

   LinearLayout menu_service, menu_view_status, menu_change_password, menu_agent_profile;
   ImageView iv_back,profile_gray,profile_green, profile_red;
    AlertDialog alertDialog1;
    String str_value,message;
    TextView spinner_txt,txt_service_count,txt_view_count;
    LinearLayout logout,ll_Menu;
    Spinner spinner;
    private final String URLstring = "http://smart.johnsonliftsltd.com:3000/api/service_userdetails/logout_reason";
    private ArrayList<String> names = new ArrayList<String>();
    Dialog dialog;
    private static final int REQUEST_LOCATION = 1;
    LocationManager locationManager;
    String latitude, longitude, no_of_hours;
    String endDateandTime, currentDateandTime,currentDate,str_spinner,current,start;
    long elapsedHours,elapsedMinutes;
    String se_user_mobile_no, se_user_name, se_id,check_id, view_count, service_count;
    private ArrayList<String> students;
    private JSONArray result;
    AlertDialog alertDialog;
    Context context;
    SharedPreferences sharedPreferences;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main_menu_services);
        context = this;

        menu_service = (LinearLayout) findViewById(R.id.menu_service);
        menu_view_status = (LinearLayout) findViewById(R.id.menu_view_status);
        menu_change_password = (LinearLayout) findViewById(R.id.menu_change_password);
        menu_agent_profile = (LinearLayout) findViewById(R.id.menu_agent_profile);
        iv_back = (ImageView) findViewById(R.id.iv_back);
        profile_gray = (ImageView) findViewById(R.id.profile_gray);
        profile_green = (ImageView) findViewById(R.id.profile_green);
        profile_red = (ImageView) findViewById(R.id.profile_red);
        logout = (LinearLayout) findViewById(R.id.logout);
        txt_service_count = (TextView) findViewById(R.id.txt_service_count);
        txt_view_count = (TextView) findViewById(R.id.txt_view_count);
        ll_Menu = findViewById(R.id.ll_menu);

       // ll_Menu.setEnabled(false);


        ActivityCompat.requestPermissions( this, new String[] {Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            OnGPS();
        } else {
            getLocation();
        }

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        se_id = sharedPreferences.getString("_id", "default value");
        se_user_mobile_no = sharedPreferences.getString("user_mobile_no", "default value");
        se_user_name = sharedPreferences.getString("user_name", "default value");

        Log.w(TAG, "userrole  : " + se_id + se_user_mobile_no);

        Count();

        CheckAttendanceResponseCall();

        iv_back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent send = new Intent(Main_Menu_ServicesActivity.this, Dashbaord_MainActivity.class);
                startActivity(send);

            }
        });

        profile_red.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Main_Menu_ServicesActivity.this);
                alertDialogBuilder.setMessage("Your Profile Login?");
                        alertDialogBuilder.setPositiveButton("yes",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface arg0, int arg1) {
                                        CreateResponseCall();
                                    }
                                });

                alertDialogBuilder.setNegativeButton("No",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        });

        menu_service.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.e("Message",""+message);

                if(message.equals("Not Present")){

                    alertDialog = new AlertDialog.Builder(context)
                            //.setTitle("Please Login to Continue!")
                            .setMessage("Please Login to Continue!")
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    alertDialog.dismiss();
                                }
                            })
                            .show();

                }
                else{

                    Intent send = new Intent(Main_Menu_ServicesActivity.this, ServicesActivity.class);
                    startActivity(send);
                }


            }
        });

        menu_view_status.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(message.equals("Not Present")){

                    alertDialog = new AlertDialog.Builder(context)
                            //.setTitle("Please Login to Continue!")
                            .setMessage("Please Login to Continue!")
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    alertDialog.dismiss();
                                }
                            })
                            .show();

                }
                else{
                    Intent send = new Intent(Main_Menu_ServicesActivity.this, Job_StatusActivity.class);
                    startActivity(send);
                }



            }
        });

        menu_change_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(message.equals("Not Present")){

                    alertDialog = new AlertDialog.Builder(context)
                            //.setTitle("Please Login to Continue!")
                            .setMessage("Please Login to Continue!")
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    alertDialog.dismiss();
                                }
                            })
                            .show();

                }
                else{
                    Intent send = new Intent(Main_Menu_ServicesActivity.this, Change_PasswordActivity.class);
                    startActivity(send);
                }



            }
        });

        menu_agent_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(message.equals("Not Present")){

                    alertDialog = new AlertDialog.Builder(context)
                            //.setTitle("Please Login to Continue!")
                            .setMessage("Please Login to Continue!")
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    alertDialog.dismiss();
                                }
                            })
                            .show();

                }
                else{
                    Intent send = new Intent(Main_Menu_ServicesActivity.this, Agent_ProfileActivity.class);
                    startActivity(send);
                }

            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder mBuilder = new AlertDialog.Builder(Main_Menu_ServicesActivity.this);
                View mView = getLayoutInflater().inflate(R.layout.dialog_logout, null);
                spinner = (Spinner) mView.findViewById(R.id.spinner);
                Button yes = (Button) mView.findViewById(R.id.btn_yes);
                Button no = (Button) mView.findViewById(R.id.btn_no);

                students = new ArrayList<String>();

                mBuilder.setView(mView);
                final AlertDialog dialog = mBuilder.create();
                dialog.show();

                getData();


               // retrieveJSON();

                yes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        str_spinner = spinner.getSelectedItem().toString();

                       if(str_spinner.equals("Select Logout Reason")){

                           Toast.makeText(Main_Menu_ServicesActivity.this, "Please Selected Logout Reason", Toast.LENGTH_SHORT).show();

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

    private void CheckAttendanceResponseCall() {

        APIInterface apiInterface = RetrofitClient.getClient().create(APIInterface.class);
        Call<CheckAttenResponse> call = apiInterface.CheckAttenResponseCall(RestUtils.getContentType(), checkattRequest());
        Log.w(TAG,"AttendanceResponse url  :%s"+" "+ call.request().url().toString());

        call.enqueue(new Callback<CheckAttenResponse>() {
            @SuppressLint("LogNotTimber")
            @Override
            public void onResponse(@NonNull Call<CheckAttenResponse> call, @NonNull retrofit2.Response<CheckAttenResponse> response) {

                Log.e(TAG,"Attendance Response" + new Gson().toJson(response.body()));
                if (response.body() != null) {
                    message = response.body().getMessage();
                    start = response.body().getData().getAtt_start_time();
                    check_id = response.body().getData().get_id();

                    if(message.equals("Not Present")){
                        profile_gray.setVisibility(View.GONE);
                        profile_green.setVisibility(View.GONE);
                        profile_red.setVisibility(View.VISIBLE);
                        logout.setVisibility(View.INVISIBLE);

//                        menu_service.setEnabled(false);
//                        menu_view_status.setEnabled(false);
//                        menu_agent_profile.setEnabled(false);
//                        menu_change_password.setEnabled(false);

                    }
                    else if(message.equals("Present")){
                        logout.setVisibility(View.VISIBLE);
                        profile_gray.setVisibility(View.GONE);
                        profile_red.setVisibility(View.GONE);
                        profile_green.setVisibility(View.VISIBLE);
                    }
                    else {
                        profile_gray.setVisibility(View.GONE);
                        profile_green.setVisibility(View.GONE);
                        profile_red.setVisibility(View.VISIBLE);
                        logout.setVisibility(View.INVISIBLE);
                        Toast.makeText(Main_Menu_ServicesActivity.this, "You have already logout. ", Toast.LENGTH_SHORT).show();
                    }


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
        Log.e(TAG,"Attendance Request "+ new Gson().toJson(checkRequest));
        return checkRequest;
    }

    private void CreateResponseCall() {

        APIInterface apiInterface = RetrofitClient.getClient().create(APIInterface.class);
        Call<CreateResponse> call = apiInterface.CreateResponseCall(RestUtils.getContentType(), createRequest());
        Log.w(TAG,"SignupResponse url  :%s"+" "+ call.request().url().toString());

        call.enqueue(new Callback<CreateResponse>() {
            @SuppressLint("LogNotTimber")
            @Override
            public void onResponse(@NonNull Call<CreateResponse> call, @NonNull retrofit2.Response<CreateResponse> response) {

                Log.w(TAG,"SignupResponse" + new Gson().toJson(response.body()));
                if (response.body() != null) {
                    message = response.body().getMessage();

                    if (200 == response.body().getCode()) {
                        if(response.body().getData() != null){

                            Toasty.success(getApplicationContext(),"Add Successfully", Toast.LENGTH_SHORT, true).show();
                            Intent send = new Intent(Main_Menu_ServicesActivity.this, Dashbaord_MainActivity.class);
                            startActivity(send);
                        }


                    } else {
                        Toasty.warning(getApplicationContext(),""+message,Toasty.LENGTH_LONG).show();

                    }
                }


            }

            @Override
            public void onFailure(@NonNull Call<CreateResponse> call, @NonNull Throwable t) {
                Log.e("OTP", "--->" + t.getMessage());
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private CreateRequest createRequest() {

        /**
         * user_id : 12345
         * user_password : 12345
         * last_login_time : 20-10-2021 11:00 AM
         */


        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
        currentDate = sdf.format(new Date());

        SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy hh:mm aa", Locale.getDefault());
        current = sdf1.format(new Date());

        CreateRequest creaRequest = new CreateRequest();
        creaRequest.setUser_mobile_no(se_user_mobile_no);
        creaRequest.setUser_name(se_user_name);
        creaRequest.setAtt_date(currentDate);
        creaRequest.setAtt_start_time(current);
        creaRequest.setAtt_status("Present");
        creaRequest.setAtt_start_lat(latitude);
        creaRequest.setAtt_start_long(longitude);

        Log.w(TAG,"loginCreateRequest "+ new Gson().toJson(creaRequest));
        return creaRequest;
    }

    private void logoutResponseCall(String id, String att_end_time, String att_reason, String att_end_lat, String att_end_long, String att_no_of_hrs) {
       APIInterface apiInterface = RetrofitClient.getClient().create(APIInterface.class);
        Call<LogoutResponse> call = apiInterface.logoutResponseCall(RestUtils.getContentType(), addReviewRequest(id,att_end_time,att_reason,att_end_lat,att_end_long, att_no_of_hrs));
        Log.w(TAG,"addReviewResponseCall url  :%s"+" "+ call.request().url().toString());


        call.enqueue(new Callback<LogoutResponse>() {
            @Override
            public void onResponse(@NonNull Call<LogoutResponse> call, @NonNull retrofit2.Response<LogoutResponse> response) {

                Log.w(TAG,"AddReviewResponse"+ "--->" + new Gson().toJson(response.body()));

                Log.w(TAG,"Response"+ "--->" + "_id : " + id + "," + "att_end_time : " + att_end_time + ","+ "att_reason : " + att_reason + "," + "att_end_lat : " + att_end_lat + "," + "att_end_long : " + att_end_long + ","+ "att_no_of_hrs : " + att_no_of_hrs);

                if (response.body() != null) {
                    if(response.body().getCode() == 200){

                        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(Main_Menu_ServicesActivity.this);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.clear();
                        editor.commit();

                        Toasty.success(getApplicationContext(),"Logout Sucessfully", Toast.LENGTH_SHORT, true).show();
                        Intent send = new Intent(Main_Menu_ServicesActivity.this, New_LoginActivity.class);
                        startActivity(send);
                    }
                    else{
                    }
                }


            }

            @SuppressLint("LongLogTag")
            @Override
            public void onFailure(@NonNull Call<LogoutResponse> call, @NonNull Throwable t) {

                Log.w(TAG,"AddReviewResponse flr"+"--->" + t.getMessage());
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
        Log.w(TAG,"addReviewRequest" + new Gson().toJson(addReviewRequest));
        return addReviewRequest;
    }

    private void Count() {

        APIInterface apiInterface = RetrofitClient.getClient().create(APIInterface.class);
        Call<CountResponse> call = apiInterface.CountResponseCall(RestUtils.getContentType(), countRequest());
        Log.w(TAG,"SignupResponse url  :%s"+" "+ call.request().url().toString());

        call.enqueue(new Callback<CountResponse>() {
            @SuppressLint("LogNotTimber")
            @Override
            public void onResponse(@NonNull Call<CountResponse> call, @NonNull retrofit2.Response<CountResponse> response) {

                Log.w(TAG,"SignupResponse" + new Gson().toJson(response.body()));
                if (response.body() != null) {
                    message = response.body().getMessage();

                    if (200 == response.body().getCode()) {
                        if(response.body().getData() != null){

                            view_count = response.body().getData().getView_status();
                            service_count = response.body().getData().getServices_count();
                            txt_service_count.setText(service_count);
                            txt_view_count.setText(view_count);
                           // Log.d("count", service_count + "," + view_count);

                        }


                    } else {
//                        dialog.dismiss();
                        Toasty.warning(getApplicationContext(),""+message,Toasty.LENGTH_LONG).show();

                    }
                }


            }

            @Override
            public void onFailure(@NonNull Call<CountResponse> call, @NonNull Throwable t) {
                Log.e("OTP", "--->" + t.getMessage());
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
    private CountRequest countRequest() {

        /**
         * user_id : 12345
         * user_password : 12345
         * last_login_time : 20-10-2021 11:00 AM
         */

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
        currentDateandTime = sdf.format(new Date());

        CountRequest count = new CountRequest();
        count.setUser_mobile_no(se_user_mobile_no);
        Log.w(TAG,"loginRequest "+ new Gson().toJson(count));
        return count;
    }


    private void OnGPS() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Enable GPS").setCancelable(false).setPositiveButton("Yes", new  DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
            }
        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        final AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
    private void getLocation() {
        if (ActivityCompat.checkSelfPermission(
                Main_Menu_ServicesActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                Main_Menu_ServicesActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);
        } else {
            Location locationGPS = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if (locationGPS != null) {
                double lat = locationGPS.getLatitude();
                double longi = locationGPS.getLongitude();
                latitude = String.valueOf(lat);
                longitude = String.valueOf(longi);
                Log.d("Your Location:", "Latitude: " + latitude + "\n" + "Longitude: " + longitude);
            } else {
               // Toast.makeText(this, "Unable to find location.", Toast.LENGTH_SHORT).show();
            }
        }

    }

    private void getData(){
        //Creating a string request
        StringRequest stringRequest = new StringRequest("http://smart.johnsonliftsltd.com:3000/api/service_userdetails/logout_reason",
                new Response.Listener<String>() {
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
                new Response.ErrorListener() {
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

        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(Main_Menu_ServicesActivity.this,R.layout.spinner_item, students);
         spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
        spinner.setAdapter(spinnerArrayAdapter);
       // spinner.setAdapter(new ArrayAdapter<String>(Main_Menu_ServicesActivity.this, R.layout.spinner_item, students));
    }

    public void onItemSelected(AdapterView parent, View view, int position, long id) {
        String item = parent.getItemAtPosition(position).toString();
     //  Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
    }

    public void onNothingSelected(AdapterView arg0) {

    }

    @Override
    public void onBackPressed() {
        Intent send = new Intent(Main_Menu_ServicesActivity.this, Dashbaord_MainActivity.class);
        startActivity(send);
    }
}