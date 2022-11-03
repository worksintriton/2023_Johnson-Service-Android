package com.triton.johnson_tap_app.activity;

import static com.android.volley.VolleyLog.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.triton.johnson_tap_app.requestpojo.Change_PasswordRequest;
import com.triton.johnson_tap_app.responsepojo.Change_PasswordResponse;
import com.triton.johnson_tap_app.R;
import com.triton.johnson_tap_app.api.APIInterface;
import com.triton.johnson_tap_app.api.RetrofitClient;
import com.triton.johnson_tap_app.utils.RestUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Change_PasswordActivity extends AppCompatActivity {

    ImageView iv_back;
    Button submit;
    EditText old_pass,new_pass;
    String s_old_pass, s_new_pass,se_id,se_user_mobile_no,se_user_name,se_user_password,message;
    AlertDialog alertDialog;
    Dialog dialog;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_change_password);

        iv_back = (ImageView) findViewById(R.id.iv_back);
        submit = (Button) findViewById(R.id.submit);
        old_pass = (EditText)findViewById(R.id.old_pass);
        new_pass = (EditText)findViewById(R.id.new_pass);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        se_id = sharedPreferences.getString("_id", "default value");
        se_user_mobile_no = sharedPreferences.getString("user_mobile_no", "default value");
        se_user_name = sharedPreferences.getString("user_name", "default value");
        se_user_password = sharedPreferences.getString("user_password", "default value");

        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent send = new Intent(Change_PasswordActivity.this, Main_Menu_ServicesActivity.class);
                startActivity(send);

            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                s_old_pass = old_pass.getText().toString();
                s_new_pass = new_pass.getText().toString();

                if (s_old_pass.equals("") || s_new_pass.equals("")){

                    alertDialog = new AlertDialog.Builder(Change_PasswordActivity.this)

                            .setMessage("Please Fill the All Values")
                            .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    alertDialog.dismiss();
                                }
                            })
                            .show();
                }
                else{

                   if(s_old_pass.equals(se_user_password)){

                       New_PasswordResponseCall();

                   }
                   else {
                       alertDialog = new AlertDialog.Builder(Change_PasswordActivity.this)

                               .setTitle("Your Old Password Mismatch !!!")
                               .setIcon(R.drawable.ic_warning)
                               .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                   public void onClick(DialogInterface dialogInterface, int i) {
                                       alertDialog.dismiss();
                                   }
                               })
                               .show();

                   }
                }

            }
        });
    }

    private void New_PasswordResponseCall() {
        dialog = new Dialog(Change_PasswordActivity.this, R.style.NewProgressDialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.progroess_popup);
        dialog.show();

        APIInterface apiInterface = RetrofitClient.getClient().create(APIInterface.class);
        Call<Change_PasswordResponse> call = apiInterface.Change_PasswordResponseCall(RestUtils.getContentType(), loginRequest());
        Log.w(TAG,"SignupResponse url  :%s"+" "+ call.request().url().toString());

        call.enqueue(new Callback<Change_PasswordResponse>() {
            @SuppressLint("LogNotTimber")
            @Override
            public void onResponse(@NonNull Call<Change_PasswordResponse> call, @NonNull Response<Change_PasswordResponse> response) {

                Log.w(TAG,"SignupResponse" + new Gson().toJson(response.body()));
                if (response.body() != null) {
                    message = response.body().getMessage();
                    if (200 == response.body().getCode()) {
                      //  userid = response.body().getData().get_id();

                        Log.e("success ","funtion");
                        Intent send = new Intent(Change_PasswordActivity.this, Main_Menu_ServicesActivity.class);
                        startActivity(send);

                        dialog.dismiss();

                    } else {
                        dialog.dismiss();
                        Toasty.warning(getApplicationContext(),""+message,Toasty.LENGTH_LONG).show();

                    }
                }


            }

            @Override
            public void onFailure(@NonNull Call<Change_PasswordResponse> call, @NonNull Throwable t) {
                dialog.dismiss();
                Log.e("OTP", "--->" + t.getMessage());
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
    private Change_PasswordRequest loginRequest() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm aa", Locale.getDefault());
        String currentDateandTime = sdf.format(new Date());

        Change_PasswordRequest loginRequest1 = new Change_PasswordRequest();
        loginRequest1.set_id(se_id);
        loginRequest1.setUser_password(s_new_pass);
        Log.w(TAG,"loginRequest "+ new Gson().toJson(loginRequest1));
        return loginRequest1;
    }
}