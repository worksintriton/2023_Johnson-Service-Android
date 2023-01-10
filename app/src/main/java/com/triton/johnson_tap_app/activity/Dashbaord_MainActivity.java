package com.triton.johnson_tap_app.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.android.volley.VolleyLog;
import com.bumptech.glide.Glide;
import com.github.chrisbanes.photoview.PhotoView;
import com.google.gson.Gson;
import com.triton.johnson_tap_app.Engineer_Dashboard.AgentList_DashboardActivity;
import com.triton.johnson_tap_app.NetworkUtility.ConnectionReceiver;
import com.triton.johnson_tap_app.NetworkUtility.NetworkChangeListener;
import com.triton.johnson_tap_app.R;
import com.triton.johnson_tap_app.RestUtils;
import com.triton.johnson_tap_app.api.APIInterface;
import com.triton.johnson_tap_app.api.RetrofitClient;
import com.triton.johnson_tap_app.requestpojo.CreateRequest;
import com.triton.johnson_tap_app.requestpojo.LogoutRequest;
import com.triton.johnson_tap_app.responsepojo.CreateResponse;
import com.triton.johnson_tap_app.responsepojo.GetPopUpImageRequest;
import com.triton.johnson_tap_app.responsepojo.GetPopupImageResponse;
import com.triton.johnson_tap_app.responsepojo.SuccessResponse;
import com.triton.johnson_tap_app.responsepojo.UpdatePopImageRequest;
import com.triton.johnson_tap_app.session.SessionManager;
import com.triton.johnson_tap_app.utils.ConnectionDetector;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

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
    @BindView(R.id.attendance)
    Button attendance;
    private SessionManager session;
    private String TAG ="MainActivity";
    private SharedPreferences sharedpreferences;
    LinearLayout logout;
    AlertDialog alertDialog;
    String user_type,message,se_user_mobile_no,emp_Type,currentDate,current,se_id,se_user_name,networkStatus="";
    LocationManager locationManager;
    String latitude, longitude, no_of_hours;
    private static final int REQUEST_LOCATION = 1;
    Context context;
    List<GetPopupImageResponse.Data> databeanList;
    ConstraintLayout con_PopImage;
    RelativeLayout rel_Main;
    Button btn_Submit;
    PhotoView img_Pop;
    //Zoom Image
    private ScaleGestureDetector scaleGestureDetector;
    private float mScaleFactor = 1.5f;
    NetworkChangeListener networkChangeListener = new NetworkChangeListener();
    BroadcastReceiver broadcastReceiver;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_dashbaord_main);
        context = this;

        logout = (LinearLayout) findViewById(R.id.logout);
        rel_Main = findViewById(R.id.rel_main);
        con_PopImage = findViewById(R.id.rel_popimage);
        btn_Submit = findViewById(R.id.btn_submit);
        img_Pop = findViewById(R.id.img_pop);
        scaleGestureDetector = new ScaleGestureDetector(this, new ScaleListener());

        ActivityCompat.requestPermissions( this, new String[] {Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {

            Log.e("Location","off");

            OnGPS();
        } else {

            Log.e("Location","On");
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

        Log.i(TAG, "onCreate: emp_Type --> " + emp_Type);

        if (emp_Type.equalsIgnoreCase("engineer")) {
            attendance.setVisibility(View.VISIBLE);
        } else {
            attendance.setVisibility(View.GONE);
        }

        if(login.equals("true")){
            login="true";
        }else {
            Intent intent=new Intent(this, SplashActivity.class);
            startActivity(intent);
            finish();
        }

   //     checkConnection();

//        broadcastReceiver = new ConnectionReceiver();
//        registerNetworkBroadcast();

        networkStatus = ConnectionDetector.getConnectivityStatusString(getApplicationContext());

        Log.e("Network",""+networkStatus);
        if (networkStatus.equalsIgnoreCase("Not connected to Internet")) {

//            Toast.makeText(context,"No Internet Connection",Toast.LENGTH_LONG).show();

            NoInternetDialog();

        }else {

            getPopupImage();
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

        attendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent send = new Intent(Dashbaord_MainActivity.this, DailyAttendanceActivity.class);
                startActivity(send);

            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                alertDialog = new AlertDialog.Builder(Dashbaord_MainActivity.this)
                        .setTitle("Logout")
                        .setMessage("Are youe sure do you want to Logout ?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialogInterface, int i) {


                             //   logoutCall();

                                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(Dashbaord_MainActivity.this);
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.clear();
                                editor.commit();

                                Toasty.success(getApplicationContext(),"Logout Sucessfully", Toast.LENGTH_SHORT, true).show();
                                Intent send = new Intent(Dashbaord_MainActivity.this, New_LoginActivity.class);
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
    public void NoInternetDialog() {

        android.app.AlertDialog.Builder mBuilder = new android.app.AlertDialog.Builder(context);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View mView = inflater.inflate(R.layout.dialog_nointernet, null);
        Button btn_Retry = mView.findViewById(R.id.btn_retry);


        mBuilder.setView(mView);
        final Dialog dialog= mBuilder.create();
        dialog.show();
        dialog.setCanceledOnTouchOutside(false);

        btn_Retry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();
                finish();
                startActivity(getIntent());

            }
        });
    }

    private void getPopupImage() {

        APIInterface apiInterface = RetrofitClient.getClient().create(APIInterface.class);
        Call<GetPopupImageResponse> call = apiInterface.getPopUpImage(com.triton.johnson_tap_app.utils.RestUtils.getContentType(), getpopupImageRequest());
        Log.w(TAG,"Get PopUp Image url  :%s"+" "+ call.request().url().toString());

        call.enqueue(new Callback<GetPopupImageResponse>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(Call<GetPopupImageResponse> call, Response<GetPopupImageResponse> response) {

                Log.w(TAG,"Get PopUp Image Response" + new Gson().toJson(response.body()));

                if (response.body()!= null){

                    message = response.body().getMessage();
                    Log.e("Message",""+message);
                    Log.e("Code",""+response.body().getCode());
                    if (response.body().getCode() == 200){

                        Log.e("Message",""+response.body().getData().getImage_path());

                        if (response.body().getData().getImage_path() != null){

                            String Image = response.body().getData().getImage_path();
                            String _id = response.body().getData().get_id();

//                            rel_Main.setVisibility(View.GONE);
//                            con_PopImage.setVisibility(View.VISIBLE);

                            AlertDialog.Builder mBuilder = new AlertDialog.Builder(context);
                            View mView = getLayoutInflater().inflate(R.layout.remarks_popup, null);

                            EditText edt_Remarks = mView.findViewById(R.id.edt_remarks);
                            Button btn_Submit = mView.findViewById(R.id.btn_submit);
                            btn_Submit.setText("Ok I have Readed");
                            edt_Remarks.setVisibility(View.GONE);
                            @SuppressLint({"MissingInflatedId", "LocalSuppress"})
                            PhotoView img_Pop = mView.findViewById(R.id.img_pop);
                            img_Pop.setVisibility(View.VISIBLE);
                            mBuilder.setView(mView);
                            alertDialog= mBuilder.create();
                            alertDialog.show();
                            alertDialog.setCanceledOnTouchOutside(false);


                            Glide.with(context)
                                    .load(Image)
                                    .into(img_Pop);

                            btn_Submit.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {

                                    updatePopupImage(_id);
                                }
                            });
                        }

                    }
                }
            }

            @Override
            public void onFailure(Call<GetPopupImageResponse> call, Throwable t) {
                Log.e("On Failure", "--->" + t.getMessage());
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void updatePopupImage(String _id) {

        APIInterface apiInterface = RetrofitClient.getClient().create(APIInterface.class);
        Call<SuccessResponse> call = apiInterface.updatePopUpImage(com.triton.johnson_tap_app.utils.RestUtils.getContentType(), updatepopupimagerequest(_id));
        Log.w(TAG,"Update PopUp Image url  :%s"+" "+ call.request().url().toString());

        call.enqueue(new Callback<SuccessResponse>() {
            @Override
            public void onResponse(Call<SuccessResponse> call, Response<SuccessResponse> response) {

                Log.w(TAG,"Update PopUp Image Response" + new Gson().toJson(response.body()));

                if (response.body()!= null) {

                    message = response.body().getMessage();
                    Log.e("Message", "" + message);

                    if (response.body().getCode() == 200) {

                        alertDialog.dismiss();
//                        rel_Main.setVisibility(View.VISIBLE);
//                        con_PopImage.setVisibility(View.GONE);

                    }
                }
            }

            @Override
            public void onFailure(Call<SuccessResponse> call, Throwable t) {
                Log.e("On Failure", "--->" + t.getMessage());
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private UpdatePopImageRequest updatepopupimagerequest(String id) {
        UpdatePopImageRequest updatePopImageRequest = new UpdatePopImageRequest();
        updatePopImageRequest.set__id(id);
        updatePopImageRequest.setStatus("Viewed");
        Log.w(TAG,"Update PopUp Image Request "+ new Gson().toJson(updatePopImageRequest));
        return updatePopImageRequest;

    }


    private GetPopUpImageRequest getpopupImageRequest() {

        GetPopUpImageRequest getPopUpImageRequest = new GetPopUpImageRequest();
        getPopUpImageRequest.setUser_mobile_no(se_user_mobile_no);
        Log.w(TAG,"Get PopUp Image Request "+ new Gson().toJson(getPopUpImageRequest));
        return getPopUpImageRequest;
    }

    private void getLocation() {

        Log.e("Nish","Hi 1");

        if (ActivityCompat.checkSelfPermission(
                context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            Log.e("Nish","Hi 2");
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);

        } else {
            Log.e("Nish","Hi 3");
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

    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {
        scaleGestureDetector.onTouchEvent(motionEvent);
        return true;
    }

    private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {
        @Override
        public boolean onScale(ScaleGestureDetector scaleGestureDetector) {
            mScaleFactor *= scaleGestureDetector.getScaleFactor();
            mScaleFactor = Math.max(0.1f, Math.min(mScaleFactor, 10.0f));
            img_Pop.setScaleX(mScaleFactor);
            img_Pop.setScaleY(mScaleFactor);
            return true;
        }
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

    protected void registerNetworkBroadcast(){

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
            registerReceiver(broadcastReceiver,new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));


        }else{

        }

    }

    protected  void unRegisterNetwork(){

        try{
            unregisterReceiver(broadcastReceiver);

        }catch (IllegalArgumentException e){
            e.printStackTrace();
        }
    }

}