package com.triton.johnson_tap_app.activity;



import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.triton.johnson_tap_app.materialeditext.MaterialEditText;
import com.triton.johnson_tap_app.materialspinner.MaterialSpinner;
import com.triton.johnson_tap_app.session.SessionManager;
import com.triton.johnson_tap_app.R;

public class LoginActivity extends AppCompatActivity {

    private String TAG ="LoginActivity";

    MaterialEditText employeeMaterialEditText, userNameMaterialEditText, passwordMaterialEditText;

    MaterialSpinner mainMaterialSpinner;

    LinearLayout forgotLinearLayout, loginMainLinearLayout;

    RequestQueue requestQueue;

    TextView mainReasonCustomFontTextView;

    Button loginButton;

    String networkStatus = "", stationId = "";
    String status , message = "", user_level = "", station_code = "", station_name = "", empid = "", name = "", username = "", mobile;

    Dialog  dialog;

    SessionManager sessionManager;
    private String role = "";

    private String userid;
    private String token;
    private static final int REQUEST_CHECK_SETTINGS_GPS = 0x1;
    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Log.w(TAG,"onCreate-->");

//        sessionManager = new SessionManager(getApplicationContext());
//
//
//        userNameMaterialEditText =  findViewById(R.id.user_name);
//        passwordMaterialEditText =  findViewById(R.id.password);
//        loginMainLinearLayout =  findViewById(R.id.loginMainLinearLayout);
//
//        userNameMaterialEditText.setTransformationMethod(new NumericKeyBoardTransformationMethod());
//
//
//        loginButton = findViewById(R.id.loginnnn_button);
//
//        userNameMaterialEditText.setOnTouchListener((view, motionEvent) -> {
//
//            userNameMaterialEditText.setFocusableInTouchMode(true);
//            passwordMaterialEditText.setFocusableInTouchMode(true);
//            return false;
//        });
//
//        passwordMaterialEditText.setOnTouchListener((view, motionEvent) -> {
//
//            userNameMaterialEditText.setFocusableInTouchMode(true);
//            passwordMaterialEditText.setFocusableInTouchMode(true);
//            return false;
//        });
//
//
//
//        // check whether internet is on or not
//        networkStatus = ConnectionDetector.getConnectivityStatusString(getApplicationContext());
//        if (networkStatus.equalsIgnoreCase("Not connected to Internet")) {
//
//            Snackbar snackbar = Snackbar
//                    .make(loginMainLinearLayout, "No internet connection!", Snackbar.LENGTH_LONG)
//                    .setAction("RETRY", view -> {
//
//                        Intent intent = new Intent(Settings.ACTION_SETTINGS);
//                        startActivity(intent);
//                    });
//
//            snackbar.setActionTextColor(Color.RED);
//
//            // Changing action button text color
//            View sbView = snackbar.getView();
//            TextView textView = sbView.findViewById(R.id.snackbar_text);
//            textView.setTextColor(Color.YELLOW);
//            snackbar.show();
//        }
//
//        loginButton.setOnClickListener(view -> {
//           /* Intent intent = new Intent(CMRLLogin.this, CmrlLoginDashboardActivity.class);
//            startActivity(intent);
//            overridePendingTransition(R.anim.new_right, R.anim.new_left);
//*/
//            networkStatus = ConnectionDetector.getConnectivityStatusString(getApplicationContext());
//            if (networkStatus.equalsIgnoreCase("Not connected to Internet")) {
//                Snackbar snackbar = Snackbar
//                        .make(loginMainLinearLayout, "No internet connection!", Snackbar.LENGTH_LONG)
//                        .setAction("RETRY", view1 -> {
//
//                            Intent intent = new Intent(Settings.ACTION_SETTINGS);
//                            startActivity(intent);
//                        });
//
//                snackbar.setActionTextColor(Color.RED);
//
//                // Changing action button text color
//                View sbView = snackbar.getView();
//                TextView textView = sbView.findViewById(R.id.snackbar_text);
//                textView.setTextColor(Color.YELLOW);
//                snackbar.show();
//            }
//            else {
//                if (Objects.requireNonNull(userNameMaterialEditText.getText()).toString().trim().equalsIgnoreCase("")) {
//                    Toasty.warning(getApplicationContext(),"Enter Phone Number",Toasty.LENGTH_LONG).show();
//
//                } else if (Objects.requireNonNull(passwordMaterialEditText.getText()).toString().trim().equalsIgnoreCase("")) {
//                    Toasty.warning(getApplicationContext(),"Enter Password",Toasty.LENGTH_LONG).show();
//                } else {
//
//                    LoginResponseCall();
//                }
//            }
//
//
//        });
//
//        //StationListUrl();
//    }
//
//    // default back button action
//    public void onBackPressed() {
//
//      /*  Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//        startActivity(intent);
//        overridePendingTransition(R.anim.new_right, R.anim.new_left);*/
//
//        //super.onBackPressed();
//    }
//
//    protected void onResume() {
//
//        super.onResume();
//        networkStatus = ConnectionDetector.getConnectivityStatusString(getApplicationContext());
//        if (networkStatus.equalsIgnoreCase("Not connected to Internet")) {
//
//            Snackbar snackbar = Snackbar
//                    .make(loginMainLinearLayout, "No internet connection!", Snackbar.LENGTH_LONG)
//                    .setAction("RETRY", view -> {
//
//                        Intent intent = new Intent(Settings.ACTION_SETTINGS);
//                        startActivity(intent);
//                    });
//
//            snackbar.setActionTextColor(Color.RED);
//
//// Changing action button text color
//            View sbView = snackbar.getView();
//            TextView textView = sbView.findViewById(R.id.snackbar_text);
//            textView.setTextColor(Color.YELLOW);
//            snackbar.show();
//
//
//        } else {
//
//            //StationListUrl();
//        }
//    }
//
//    @SuppressLint("LogNotTimber")
//    private void LoginResponseCall() {
//        dialog = new Dialog(LoginActivity.this, R.style.NewProgressDialog);
//        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//        dialog.setContentView(R.layout.progroess_popup);
//        dialog.show();
//
//        APIInterface apiInterface = RetrofitClient.getClient().create(APIInterface.class);
//        Call<LoginResponse> call = apiInterface.LoginResponseCall(RestUtils.getContentType(), loginRequest());
//        Log.w(TAG,"SignupResponse url  :%s"+" "+ call.request().url().toString());
//
//        call.enqueue(new Callback<LoginResponse>() {
//            @SuppressLint("LogNotTimber")
//            @Override
//            public void onResponse(@NonNull Call<LoginResponse> call, @NonNull Response<LoginResponse> response) {
//
//                Log.w(TAG,"SignupResponse" + new Gson().toJson(response.body()));
//                if (response.body() != null) {
//                    message = response.body().getMessage();
//                    if (200 == response.body().getCode()) {
//                        if(response.body().getData() != null){
//                            userid = response.body().getData().get_id();
//                            sessionManager.createSessionLogin(
//                                    response.body().getData().get_id(),
//                                    String.valueOf(response.body().getData().getUser_id()),
//                                    String.valueOf(response.body().getData().getUser_again_code()),
//                                    response.body().getData().getUser_name(),
//                                    response.body().getData().getUser_email_id(),
//                                    response.body().getData().getUser_designation(),
//                                    response.body().getData().getUser_token(),
//                                    response.body().getData().getUser_status(),
//                                    String.valueOf(response.body().getData().getUser_type()),
//                                    response.body().getData().getUser_role());
//                            attendanceCreateRequestCall(response.body().getData().get_id(), response.body().getData().getUser_name());
//
//                            status = response.body().getData().getUser_status();
//
//                        }
//
//
//                    } else {
//                        dialog.dismiss();
//                        Toasty.warning(getApplicationContext(),""+message,Toasty.LENGTH_LONG).show();
//
//                    }
//                }
//
//
//            }
//
//            @Override
//            public void onFailure(@NonNull Call<LoginResponse> call,@NonNull Throwable t) {
//                dialog.dismiss();
//                Log.e("OTP", "--->" + t.getMessage());
//                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        });
//
//    }
//    private LoginRequest1 loginRequest() {
//
//        /**
//         * user_id : 12345
//         * user_password : 12345
//         * last_login_time : 20-10-2021 11:00 AM
//         */
//
//        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm aa", Locale.getDefault());
//        String currentDateandTime = sdf.format(new Date());
//
//        LoginRequest loginRequest = new LoginRequest();
//        loginRequest.setUser_id(userNameMaterialEditText.getText().toString().trim());
//        loginRequest.setUser_password(passwordMaterialEditText.getText().toString());
//        loginRequest.setLast_login_time(currentDateandTime);
//        Log.w(TAG,"loginRequest "+ new Gson().toJson(loginRequest));
//        return loginRequest;
//    }
//
//
//
//    @SuppressLint("LogNotTimber")
//    private void attendanceCreateRequestCall(String user_id, String user_name) {
//        dialog = new Dialog(LoginActivity.this, R.style.NewProgressDialog);
//        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//        dialog.setContentView(R.layout.progroess_popup);
//        dialog.show();
//
//        APIInterface apiInterface = RetrofitClient.getClient().create(APIInterface.class);
//        Call<SuccessResponse> call = apiInterface.attendanceCreateRequestCall(RestUtils.getContentType(), attendanceCreateRequest(user_id,user_name));
//        Log.w(TAG,"attendanceCreateRequest url  :%s"+" "+ call.request().url().toString());
//
//        call.enqueue(new Callback<SuccessResponse>() {
//            @SuppressLint("LogNotTimber")
//            @Override
//            public void onResponse(@NonNull Call<SuccessResponse> call, @NonNull Response<SuccessResponse> response) {
//
//                Log.w(TAG,"attendanceCreateRequest" + new Gson().toJson(response.body()));
//                if (response.body() != null) {
//                    message = response.body().getMessage();
//                    if (200 == response.body().getCode()) {
//
//                        startActivity(new Intent(LoginActivity.this, Dashbaord_MainActivity.class));
//                    }
//
//
////                        if(status.equals("0")){
////
////                            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(LoginActivity.this);
////                            alertDialogBuilder.setMessage("Allow");
////                            alertDialogBuilder.setPositiveButton("yes",
////                                    new DialogInterface.OnClickListener() {
////                                        @Override
////                                        public void onClick(DialogInterface arg0, int arg1) {
////                                            startActivity(new Intent(LoginActivity.this, Dashbaord_MainActivity.class));
////                                        }
////                                    });
////
//////                                alertDialogBuilder.setNegativeButton("No",new DialogInterface.OnClickListener() {
//////                                    Override
//////                                    public void onClick(DialogInterface dialog, int which) {
//////                                        finish();
//////                                    }
//////                                });
////
////                            AlertDialog alertDialog = alertDialogBuilder.create();
////                            alertDialog.show();
////                        }
////
////                        else {
////                            Toast.makeText(LoginActivity.this,"Status 1",Toast.LENGTH_LONG).show();
////                        }
////
////                    } else {
////                        dialog.dismiss();
////                        Toasty.warning(getApplicationContext(),""+message,Toasty.LENGTH_LONG).show();
////
////                    }
//                }
//
//
//            }
//
//            @Override
//            public void onFailure(@NonNull Call<SuccessResponse> call,@NonNull Throwable t) {
//                dialog.dismiss();
//                Log.e("SuccessResponse flr", "--->" + t.getMessage());
//            }
//        });
//
//    }
//    private AttendanceCreateRequest attendanceCreateRequest(String user_id, String user_name) {
//
//         /*{
//            "user_id": "6209f7cb967cef205b87110c",
//            "attendance_name": "Mohammed imthiyas",
//            "attendance_start_date": "23-10-2022",
//            "attendance_start_date_time": "23-10-2022 11:00 AM",
//            "attendance_start_lat": 18.009090,
//            "attendance_start_long": 79.990090,
//            "attendance_created_at": "23-10-2022 11:00 AM"
//}*/
//
//        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm aa", Locale.getDefault());
//        String currentDateandTime = sdf.format(new Date());
//
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
//        String currentDate = simpleDateFormat.format(new Date());
//
//        AttendanceCreateRequest attendanceCreateRequest = new AttendanceCreateRequest();
//        attendanceCreateRequest.setUser_id(user_id);
//        attendanceCreateRequest.setAttendance_name(user_name);
//        attendanceCreateRequest.setAttendance_start_date(currentDate);
//        attendanceCreateRequest.setAttendance_start_date_time(currentDateandTime);
//        attendanceCreateRequest.setAttendance_created_at(currentDateandTime);
//        Log.w(TAG,"attendanceCreateRequest "+ new Gson().toJson(attendanceCreateRequest));
//        return attendanceCreateRequest;
//    }
//
//    @SuppressLint("MissingSuperCall")
//    public void onRequestPermissionsResult(int requestCode, @org.jetbrains.annotations.NotNull String @org.jetbrains.annotations.NotNull [] permissions, @org.jetbrains.annotations.NotNull int @org.jetbrains.annotations.NotNull [] grantResults) {
//        if (requestCode == MY_PERMISSIONS_REQUEST_LOCATION) {
//            if (grantResults.length > 0
//                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                if (ContextCompat.checkSelfPermission(getApplicationContext(),
//                        Manifest.permission.ACCESS_FINE_LOCATION)
//                        == PackageManager.PERMISSION_GRANTED) {
//                }
//            } else {
//                Toast.makeText(getApplicationContext(), "permission denied", Toast.LENGTH_LONG).show();
//            }
//        }
    }

}