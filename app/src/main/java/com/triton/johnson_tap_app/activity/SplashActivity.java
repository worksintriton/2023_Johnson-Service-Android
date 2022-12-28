package com.triton.johnson_tap_app.activity;


import static android.Manifest.permission.READ_PHONE_NUMBERS;
import static android.Manifest.permission.READ_PHONE_STATE;
import static android.Manifest.permission.READ_SMS;
import static android.icu.text.DisplayContext.LENGTH_SHORT;

import android.Manifest;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.multidex.BuildConfig;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.triton.johnson_tap_app.NetworkUtility.NetworkChangeListener;
import com.triton.johnson_tap_app.responsepojo.GetFetchLatestVersionResponse;
import com.triton.johnson_tap_app.requestpojo.Getlatestversionrequest;
import com.triton.johnson_tap_app.api.APIInterface;
import com.triton.johnson_tap_app.api.RetrofitClient;
import com.triton.johnson_tap_app.session.SessionManager;
import com.triton.johnson_tap_app.R;
import com.triton.johnson_tap_app.utils.ConnectionDetector;

import java.text.SimpleDateFormat;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashActivity extends AppCompatActivity {

    /**
     * Session to check whether user is login or not.
     */
    SessionManager sessionManager;

    // user level
    String user_level;

    int haslocationpermission;
    private SharedPreferences sharedpreferences;
    String TAG = "SplashActivity", ID;
    private String VersionUpdate, VersionUpdate1;
    TextView device_id;
    Context context;
    String networkStatus = "";
    LinearLayout loginMainLinearLayout;

    NetworkChangeListener networkChangeListener = new NetworkChangeListener();

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        context = this;
        SimpleDateFormat currentDate = new SimpleDateFormat("dd.MM.yy");
        Date todayDate = new Date();
        String thisDate = currentDate.format(todayDate);
        TextView txt_version = (TextView) findViewById(R.id.txt_version);
        device_id = (TextView) findViewById(R.id.device_id);
        ID = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);
        device_id.setText(ID);
        Log.e("deviceid",ID);

//        String[] permission = { Manifest.permission.READ_PHONE_NUMBERS};
//        requestPermissions(permission,102);
//        getMobileNumber();

        String versionName = BuildConfig.VERSION_NAME;


        sessionManager = new SessionManager(this);

        networkStatus = ConnectionDetector.getConnectivityStatusString(getApplicationContext());

        Log.e("Network",""+networkStatus);
        if (networkStatus.equalsIgnoreCase("Not connected to Internet")) {

            Toast.makeText(context,"No Internet Connection",Toast.LENGTH_LONG).show();

        }
        else {
            getlatestversion();
        }

//        Intent intent = new Intent(SplashActivity.this, Dashbaord_MainActivity.class);
//        startActivity(intent);
//        overridePendingTransition(R.anim.new_right, R.anim.new_left);
//        finish();


    }


    @SuppressLint("HardwareIds")
    private void getMobileNumber() {
        Log.e("Get Mobile Number","Hi");

        TelephonyManager telephonyManager = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_SMS) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_NUMBERS) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
//        txt_Mobilenumber.setText(telephonyManager.getLine1Number());
        Log.e("Mobile Number",""+telephonyManager.getLine1Number());
    }

    private void getlatestversion() {
        APIInterface apiInterface = RetrofitClient.getClient().create(APIInterface.class);
        Call<GetFetchLatestVersionResponse> call = apiInterface.getlatestversionrequestcall();
        Log.w(TAG, "url  :%s" + call.request().url().toString());
        call.enqueue(new Callback<GetFetchLatestVersionResponse>() {
            @Override
            public void onResponse(Call<GetFetchLatestVersionResponse> call, Response<GetFetchLatestVersionResponse> response) {
                Log.e(TAG, "Submitted_status ---" + new Gson().toJson(response.body()));
                if(response.body() !=null)
                {
                    Log.e(TAG, "Submitted_status ---" +response.body().getMessage());
                    String Submitted_status = response.body().getStatus();
                    Log.e(TAG,"dATARE-000000--"+response.body().getData().getVersion());

                    if(Submitted_status !=null && Submitted_status.equalsIgnoreCase("Success"))
                    {
                        Log.e(TAG,"dATA"+response.body().getData().getVersion());

                        if(response.body().getData().getVersion().equals("25.08.2022.1")){
                            Thread timerThread = new Thread() {
                                @TargetApi(Build.VERSION_CODES.LOLLIPOP)
                                public void run() {
                                    try {
                                        sleep(3000);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    } finally {

                                        Log.w(TAG,"ELSE"+sessionManager.isLoggedIn());


                                        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(SplashActivity.this);
                                        String login=sharedPreferences.getString("login_execute","false");
                                        if(login.equals("true")){
                                            Intent intent = new Intent(SplashActivity.this, Dashbaord_MainActivity.class);
                                            startActivity(intent);
                                            overridePendingTransition(R.anim.new_right, R.anim.new_left);
                                            finish();
                                        }else {
                                            Intent intent = new Intent(SplashActivity.this, New_LoginActivity.class);
                                            startActivity(intent);
                                            overridePendingTransition(R.anim.new_right, R.anim.new_left);
                                            finish();
                                        }

                                    }
                                }
                            };
                            timerThread.start();
                        }
                        else{
                            Log.e(TAG,"dATA-0000--"+response.body().getData().getVersion());
                            String apk_link = response.body().getData().getApk_link();
                            String apk_version = response.body().getData().getVersion();
                            Intent intent = new Intent(SplashActivity.this, DownloadapkfileActivity.class);
                            intent.putExtra("apk_link",apk_link);
                            intent.putExtra("apk_version",apk_version);
                            startActivity(intent);
                        }

                    }else
                    {

                    }
                }
            }

            @Override
            public void onFailure(Call<GetFetchLatestVersionResponse> call, Throwable t) {

                Log.e("Hi","On Failure");
                Log.e(TAG, "Submitted_status 1111---"+t.getLocalizedMessage());
            }
        });
    }

    private Getlatestversionrequest getlatestversionrequest() {
        Getlatestversionrequest getlatestversionrequest = new Getlatestversionrequest();
        getlatestversionrequest.setVersion("");
        return getlatestversionrequest;
    }

    @Override
    protected void onStart() {
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(networkChangeListener,filter);
        super.onStart();
    }

    @Override
    protected void onStop() {
        unregisterReceiver(networkChangeListener);
        super.onStop();
    }
}