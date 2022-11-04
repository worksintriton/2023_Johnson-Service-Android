package com.triton.johnson_tap_app.activity;

import static com.android.volley.VolleyLog.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
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
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.VolleyLog;
import com.google.gson.Gson;
import com.triton.johnson_tap_app.Engineer_Dashboard.AgentList_DashboardActivity;
import com.triton.johnson_tap_app.R;
import com.triton.johnson_tap_app.RestUtils;
import com.triton.johnson_tap_app.api.APIInterface;
import com.triton.johnson_tap_app.api.RetrofitClient;
import com.triton.johnson_tap_app.requestpojo.CreateRequest;
import com.triton.johnson_tap_app.requestpojo.LogoutRequest;
import com.triton.johnson_tap_app.responsepojo.CreateResponse;
import com.triton.johnson_tap_app.responsepojo.SuccessResponse;
import com.triton.johnson_tap_app.session.SessionManager;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Dashbaord_MainActivity extends AppCompatActivity {

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.general)
    Button general;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.services)
    Button services;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.dashboard)
    Button dashboard;
    private SessionManager session;
    private String TAG ="MainActivity";
    private SharedPreferences sharedpreferences;
    LinearLayout logout;
    AlertDialog alertDialog;
    String user_type,message,se_user_mobile_no,emp_Type,currentDate,current,se_id,se_user_name;
    LocationManager locationManager;
    String latitude, longitude, no_of_hours;
    private static final int REQUEST_LOCATION = 1;
    Context context;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_dashbaord_main);
        context = this;

        logout = (LinearLayout) findViewById(R.id.logout);

        ActivityCompat.requestPermissions( this, new String[] {Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            OnGPS();
        } else {
            getLocation();
        }

        ButterKnife.bind(this);
        Log.w(TAG,"Oncreate -->");
        session = new SessionManager(getApplicationContext());

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String login=sharedPreferences.getString("login_execute","false");
        user_type = sharedPreferences.getString("_id", "default value");
        se_user_mobile_no = sharedPreferences.getString("user_mobile_no", "default value");
        emp_Type = sharedPreferences.getString("emp_type","ABCD");
        se_id = sharedPreferences.getString("_id", "default value");
        se_user_name = sharedPreferences.getString("user_name", "default value");
        if(login.equals("true")){
            login="true";
        }else {
            Intent intent=new Intent(this, SplashActivity.class);
            startActivity(intent);
            finish();
        }

        general.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent send = new Intent(Dashbaord_MainActivity.this, MainActivity.class);
                startActivity(send);

//                alertDialog = new AlertDialog.Builder(Dashbaord_MainActivity.this)
//                        .setMessage("You don't have the access")
//                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialogInterface, int i) {
//                                alertDialog.dismiss();
//                            }
//                        })
//                        .show();

            }
        });

        services.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent send = new Intent(Dashbaord_MainActivity.this, Main_Menu_ServicesActivity.class);
                startActivity(send);
            }
        });

        dashboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(emp_Type.equals("Engineer")) {

                    CreateResponseCall();


                }
               else {
                    alertDialog = new AlertDialog.Builder(Dashbaord_MainActivity.this)
                            .setMessage("You don't have the access")
                            .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    alertDialog.dismiss();
                                }
                            })
                            .show();
                }

            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                alertDialog = new AlertDialog.Builder(Dashbaord_MainActivity.this)
                        .setTitle("Logout")
                        .setMessage("Are youe sure do you want to Logout ?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialogInterface, int i) {


                                logoutCall();

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

    private void getLocation() {

        if (ActivityCompat.checkSelfPermission(
                context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);
        } else {
            Location locationGPS = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if (locationGPS != null) {
                double lat = locationGPS.getLatitude();
                double longi = locationGPS.getLongitude();
                latitude = String.valueOf(lat);
                longitude = String.valueOf(longi);
                Log.e("Your Location:", "Latitude: " + latitude + "\n" + "Longitude: " + longitude);
            } else {
                // Toast.makeText(this, "Unable to find location.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void OnGPS() {

        final android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);
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
        final android.app.AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void CreateResponseCall() {

        APIInterface apiInterface = RetrofitClient.getClient().create(APIInterface.class);
        Call<CreateResponse> call = apiInterface.CreateResponseCall(com.triton.johnson_tap_app.utils.RestUtils.getContentType(), createRequest());
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

                          //  Toasty.success(getApplicationContext(),"Add Successfully", Toast.LENGTH_SHORT, true).show();

                            Intent send = new Intent(Dashbaord_MainActivity.this, AgentList_DashboardActivity.class);
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

    private void logoutCall() {

        APIInterface apiInterface = RetrofitClient.getClient().create(APIInterface.class);
        Call<SuccessResponse> call = apiInterface.LogoutCall(RestUtils.getContentType(),logoutRequest());
        Log.w(VolleyLog.TAG,"Logout Response url  :%s"+" "+ call.request().url().toString());

        call.enqueue(new Callback<SuccessResponse>() {
            @Override
            public void onResponse(Call<SuccessResponse> call, Response<SuccessResponse> response) {

                Log.w(VolleyLog.TAG,"Response" + new Gson().toJson(response.body()));

                if (response.body() != null){

                    message = response.body().getMessage();

                    if (response.body().getCode() == 200){

                        if (response.body().getData() != null){

                            Log.e("message",message);

                            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(Dashbaord_MainActivity.this);
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.clear();
                            editor.commit();

                            Toasty.success(getApplicationContext(),"Logout Sucessfully", Toast.LENGTH_SHORT, true).show();
                            Intent send = new Intent(Dashbaord_MainActivity.this, New_LoginActivity.class);
                            startActivity(send);
                        }
                    }
                    else {

                        Toasty.warning(getApplicationContext(),""+message,Toasty.LENGTH_LONG).show();

                    }
                }
            }

            @Override
            public void onFailure(Call<SuccessResponse> call, Throwable t) {
                Log.e("on Failure", "--->" + t.getMessage());
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private LogoutRequest logoutRequest() {

        LogoutRequest data = new LogoutRequest();
        data.setUser_mobile_no(se_user_mobile_no);
        Log.e(TAG," Logout Request"+ new Gson().toJson(data));
        return data;
    }

    @Override
    public void onBackPressed() {
        alertDialog = new AlertDialog.Builder(context)
                .setTitle("Are you sure to exit?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                        finishAffinity();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        alertDialog.dismiss();
                    }
                })
                .show();
    }
}