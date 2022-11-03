package com.triton.johnson_tap_app.Engineer_Dashboard;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.triton.johnson_tap_app.R;
import com.triton.johnson_tap_app.RestUtils;
import com.triton.johnson_tap_app.activity.New_LoginActivity;
import com.triton.johnson_tap_app.api.APIInterface;
import com.triton.johnson_tap_app.api.RetrofitClient;
import com.triton.johnson_tap_app.requestpojo.GetCustomer_Engineer_Request;
import com.triton.johnson_tap_app.responsepojo.CustomerDetails_EngineerResponse;

import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CustomerDetails_DashboardActivity extends AppCompatActivity {

   TextView txttitle_JobId,txt_Welcome,txt_LastLogin,txt_Jobid,txt_Customername,txt_Addressone,txt_Addresstwo,txt_Addressthree,txt_Pincode,txt_ContractType,txt_BDnumber,txt_Date,txt_Mechanicname;
   String str_TitleJobID,str_Keyvalue,agentName,agentNumber,service_Title;
    LinearLayout logout;
    AlertDialog alertDialog;
    SharedPreferences sharedPreferences;
    Context context;
    String lastLogin, str_Jobid,str_Customername,str_Addressone,str_Addresstwo,str_Addressthree,str_Pincode,str_Contracttype,str_BDNumber,str_Date,str_Mechanicname,message;
    CustomerDetails_EngineerResponse.DataBean dataBeanList;
    @SuppressLint("MissingInflatedId")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_customer_details_new_screen);
        context = this;

        txttitle_JobId = (TextView) findViewById(R.id.title_job);
        logout = (LinearLayout) findViewById(R.id.logout);
        txt_Welcome = findViewById(R.id.txt_welcome);
        txt_LastLogin = findViewById(R.id.txt_lastlogin);

        txt_Jobid = findViewById(R.id.job_id);
        txt_Customername = findViewById(R.id.customer_name);
        txt_Addressone = findViewById(R.id.address1);
        txt_Addresstwo = findViewById(R.id.address2);
        txt_Addressthree = findViewById(R.id.address3);
        txt_Pincode = findViewById(R.id.pin);
        txt_ContractType = findViewById(R.id.contract_type);
        txt_BDnumber = findViewById(R.id.bd_number);
        txt_Date = findViewById(R.id.bd_date);
        txt_Mechanicname = findViewById(R.id.txt_mechname);


        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        str_TitleJobID = sharedPreferences.getString("jobno","L-1234");
        str_Keyvalue = sharedPreferences.getString("keyvalue","TN123456789");
        txttitle_JobId.setText(str_TitleJobID);
        Log.e("Key Value",""+str_Keyvalue);
        agentName = sharedPreferences.getString("agent_name","name");
        agentNumber = sharedPreferences.getString("agent_number","123456789");
        service_Title = sharedPreferences.getString("service_title","ServiceName");
        Log.e("Agent Number",""+agentNumber);
        Log.e("Service Name",""+service_Title);
        lastLogin = sharedPreferences.getString("last_login","2020-10-28 05:36:33.000 ");
       // lastLogin = lastLogin.substring(0,20);
        lastLogin = lastLogin.substring(0,lastLogin.length() -5);
        lastLogin = lastLogin.replaceAll("[^0-9-:]", " ");

        txt_Welcome.setText("Welcome " +agentName);
        txt_LastLogin.setText("Last Login : " + lastLogin);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
//            title = extras.getString("title_job");
//            title_job.setText(title);
        }

        CustomerDetailsCall();

        logout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                alertDialog = new AlertDialog.Builder(CustomerDetails_DashboardActivity.this)
                        .setTitle("Logout")
                        .setMessage("Are youe sure do you want to Logout ?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialogInterface, int i) {

                                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(CustomerDetails_DashboardActivity.this);
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.clear();
                                editor.commit();

                                Toasty.success(getApplicationContext(),"Logout Sucessfully", Toast.LENGTH_SHORT, true).show();
                                Intent send = new Intent(CustomerDetails_DashboardActivity.this, New_LoginActivity.class);
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

    private void CustomerDetailsCall() {

        APIInterface apiInterface = RetrofitClient.getClient().create(APIInterface.class);
        Call<CustomerDetails_EngineerResponse> call = apiInterface.getCustomerEnginnerCall(RestUtils.getContentType(),getCustomerRequest());
        Log.e(TAG, "Customer Details Response url  :%s" + " " + call.request().url().toString());

        call.enqueue(new Callback<CustomerDetails_EngineerResponse>() {
            @Override
            public void onResponse(Call<CustomerDetails_EngineerResponse> call, Response<CustomerDetails_EngineerResponse> response) {

                Log.e(TAG, "Customer Response" + new Gson().toJson(response.body()));

                if (response.body() != null) {

                    message = response.body().getMessage();
                    Log.d("message", message);

                    if (200 == response.body().getCode()) {
                        if (response.body().getData() != null) {

                            dataBeanList = response.body().getData();

                            Log.d("dataaaaa", String.valueOf(dataBeanList));

                            str_Jobid = response.body().getData().getJob_id();
                            str_Customername = response.body().getData().getCustomer_name();
                            str_Addressone = response.body().getData().getAddress_one();
                            str_Addresstwo = response.body().getData().getAddress_two();
                            str_Addressthree = response.body().getData().getAddress_three();
                            str_Pincode = response.body().getData().getPin_code();
                            str_Contracttype = response.body().getData().getContract_type();
                            str_BDNumber = response.body().getData().getNumber();
                            str_Date = response.body().getData().getData_and_time();
                            str_Mechanicname=response.body().getData().getMech_name();

                            txt_Jobid.setText(str_Jobid);
                            txt_Customername.setText(str_Customername);
                            txt_Addressone.setText(str_Addressone);
                            txt_Addresstwo.setText(str_Addresstwo);
                            txt_Addressthree.setText(str_Addressthree);
                            txt_Pincode.setText(str_Pincode);
                            txt_ContractType.setText(str_Contracttype);
                            txt_BDnumber.setText(str_BDNumber);
                            txt_Date.setText(str_Date);
                            txt_Mechanicname.setText(str_Mechanicname);


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
            public void onFailure(Call<CustomerDetails_EngineerResponse> call, Throwable t) {
                Log.e("On Failure ", "--->" + t.getMessage());
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private GetCustomer_Engineer_Request getCustomerRequest() {

        GetCustomer_Engineer_Request customerRequest = new GetCustomer_Engineer_Request();
        customerRequest.setEmp_no(agentNumber);
        customerRequest.setJob_id(str_TitleJobID);
        customerRequest.setService_name(service_Title);
        customerRequest.setKey_value(str_Keyvalue);
        Log.e(TAG, "Get Customer Request " + new Gson().toJson(customerRequest));
        return customerRequest;
    }
}