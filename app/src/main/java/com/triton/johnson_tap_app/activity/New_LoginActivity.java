package com.triton.johnson_tap_app.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.triton.johnson_tap_app.R;
import com.triton.johnson_tap_app.api.APIInterface;
import com.triton.johnson_tap_app.api.RetrofitClient;
import com.triton.johnson_tap_app.materialeditext.MaterialEditText;
import com.triton.johnson_tap_app.materialspinner.MaterialSpinner;
import com.triton.johnson_tap_app.requestpojo.LoginRequest1;
import com.triton.johnson_tap_app.responsepojo.LoginResponse1;
import com.triton.johnson_tap_app.session.SessionManager;
import com.triton.johnson_tap_app.utils.ConnectionDetector;
import com.triton.johnson_tap_app.utils.NumericKeyBoardTransformationMethod;
import com.triton.johnson_tap_app.utils.RestUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class New_LoginActivity extends AppCompatActivity {

    private String TAG ="LoginActivity";

    MaterialEditText employeeMaterialEditText, userNameMaterialEditText, passwordMaterialEditText;

    MaterialSpinner mainMaterialSpinner;

    LinearLayout forgotLinearLayout, loginMainLinearLayout;

    RequestQueue requestQueue;
    private SharedPreferences sharedpreferences;
    TextView mainReasonCustomFontTextView;

    Button loginButton;

    String networkStatus = "", stationId = "";
    String status , message = "", user_level = "", station_code = "", station_name = "", empid = "", name = "", username = "", mobile,ID;

    Dialog dialog;

    SessionManager sessionManager;
    private String role = "";

    private String userid;
    private String token;
    private static final int REQUEST_CHECK_SETTINGS_GPS = 0x1;
    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;
    TextView device_id;
    Context context;
    AlertDialog alertDialog;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_login);
        context = this;

        userNameMaterialEditText =  findViewById(R.id.user_name);
        passwordMaterialEditText =  findViewById(R.id.password);
        loginMainLinearLayout =  findViewById(R.id.loginMainLinearLayout);

        userNameMaterialEditText.setTransformationMethod(new NumericKeyBoardTransformationMethod());

        loginButton = findViewById(R.id.loginnnn_button);

        device_id = (TextView)findViewById(R.id.device_id);

        ID = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);
        device_id.setText(ID);
        Log.e("deviceid",ID);

        userNameMaterialEditText.setOnTouchListener((view, motionEvent) -> {

            userNameMaterialEditText.setFocusableInTouchMode(true);
            passwordMaterialEditText.setFocusableInTouchMode(true);
            return false;
        });

        passwordMaterialEditText.setOnTouchListener((view, motionEvent) -> {

            userNameMaterialEditText.setFocusableInTouchMode(true);
            passwordMaterialEditText.setFocusableInTouchMode(true);
            return false;
        });



        // check whether internet is on or not
        networkStatus = ConnectionDetector.getConnectivityStatusString(getApplicationContext());
        if (networkStatus.equalsIgnoreCase("Not connected to Internet")) {

            Snackbar snackbar = Snackbar
                    .make(loginMainLinearLayout, "No internet connection!", Snackbar.LENGTH_LONG)
                    .setAction("RETRY", view -> {

                        Intent intent = new Intent(Settings.ACTION_SETTINGS);
                        startActivity(intent);
                    });

            snackbar.setActionTextColor(Color.RED);

            // Changing action button text color
            View sbView = snackbar.getView();
            TextView textView = sbView.findViewById(R.id.snackbar_text);
            textView.setTextColor(Color.YELLOW);
            snackbar.show();
        }

        loginButton.setOnClickListener(view -> {
           /* Intent intent = new Intent(CMRLLogin.this, CmrlLoginDashboardActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.new_right, R.anim.new_left);
*/
            networkStatus = ConnectionDetector.getConnectivityStatusString(getApplicationContext());
            if (networkStatus.equalsIgnoreCase("Not connected to Internet")) {
                Snackbar snackbar = Snackbar
                        .make(loginMainLinearLayout, "No internet connection!", Snackbar.LENGTH_LONG)
                        .setAction("RETRY", view1 -> {

                            Intent intent = new Intent(Settings.ACTION_SETTINGS);
                            startActivity(intent);
                        });

                snackbar.setActionTextColor(Color.RED);

                // Changing action button text color
                View sbView = snackbar.getView();
                TextView textView = sbView.findViewById(R.id.snackbar_text);
                textView.setTextColor(Color.YELLOW);
                snackbar.show();
            }
            else {
                if (Objects.requireNonNull(userNameMaterialEditText.getText()).toString().trim().equalsIgnoreCase("")) {
                    Toasty.warning(getApplicationContext(),"Enter Phone Number",Toasty.LENGTH_LONG).show();

                } else if (Objects.requireNonNull(passwordMaterialEditText.getText()).toString().trim().equalsIgnoreCase("")) {
                    Toasty.warning(getApplicationContext(),"Enter Password",Toasty.LENGTH_LONG).show();
                } else {

                    LoginResponseCall();
                }
            }


        });
    }

    private void LoginResponseCall() {
        dialog = new Dialog(New_LoginActivity.this, R.style.NewProgressDialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.progroess_popup);
        dialog.show();

        APIInterface apiInterface = RetrofitClient.getClient().create(APIInterface.class);
        Call<LoginResponse1> call = apiInterface.LoginResponseCall(RestUtils.getContentType(), loginRequest());
        Log.w(TAG,"SignupResponse url  :%s"+" "+ call.request().url().toString());

        call.enqueue(new Callback<LoginResponse1>() {
            @SuppressLint("LogNotTimber")
            @Override
            public void onResponse(@NonNull Call<LoginResponse1> call, @NonNull Response<LoginResponse1> response) {

                Log.w(TAG,"SignupResponse" + new Gson().toJson(response.body()));
                if (response.body() != null) {
                    message = response.body().getMessage();
                    if (200 == response.body().getCode()) {

                        userid = response.body().getData().get_id();

                        Log.e("success ","funtion");

                        String emp_Type = response.body().getData().getEmp_type();
                        Log.e("Nishanth",""+emp_Type);
                        String lastLogin = response.body().getData().getLast_login_time();
                        String username = response.body().getData().getUser_name();
                        Log.e("Nishanth",""+username);

                        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(New_LoginActivity.this);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("login_execute","true");
                        editor.putString("_id", response.body().getData().get_id());
                        editor.putString("user_mobile_no", response.body().getData().getUser_mobile_no());
                        editor.putString("user_name", response.body().getData().getUser_name());
                        editor.putString("user_password", response.body().getData().getUser_password());
                        editor.putString("user_type", response.body().getData().getUser_type());
                        editor.putString("emp_type",emp_Type);
                        editor.putString("last_login",lastLogin);
                        editor.apply();

                        Intent home = new Intent(New_LoginActivity.this, Dashbaord_MainActivity.class);
                        startActivity(home);

//                        if(response.body().getData().getUser_type().equals("USER")) {
//
//                            Intent home = new Intent(New_LoginActivity.this, Dashbaord_MainActivity.class);
//                            startActivity(home);
//                        }
//                        else {
//
//                            Intent home = new Intent(New_LoginActivity.this, AgentList_DashboardActivity.class);
//                            startActivity(home);
//                        }

                    } else {
                        dialog.dismiss();
                        Toasty.warning(getApplicationContext(),""+message,Toasty.LENGTH_LONG).show();

                    }
                }


            }

            @Override
            public void onFailure(@NonNull Call<LoginResponse1> call, @NonNull Throwable t) {
                dialog.dismiss();
                Log.e("OTP", "--->" + t.getMessage());
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
    private LoginRequest1 loginRequest() {

        /**
         * user_id : 12345
         * user_password : 12345
         * last_login_time : 20-10-2021 11:00 AM
         */

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm aa", Locale.getDefault());
        String currentDateandTime = sdf.format(new Date());

        LoginRequest1 loginRequest = new LoginRequest1();
        loginRequest.setUser_mobile_no(userNameMaterialEditText.getText().toString().trim());
        loginRequest.setUser_password(passwordMaterialEditText.getText().toString());
        loginRequest.setDevice_id(ID);
        Log.w(TAG,"loginRequest "+ new Gson().toJson(loginRequest));
        return loginRequest;
    }

        public void onBackPressed() {

            alertDialog = new AlertDialog.Builder(context)
                    .setTitle("Are you sure to exit ?")
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