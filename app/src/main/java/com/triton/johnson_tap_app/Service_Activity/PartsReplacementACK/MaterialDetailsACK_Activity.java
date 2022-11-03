package com.triton.johnson_tap_app.Service_Activity.PartsReplacementACK;

import static android.content.ContentValues.TAG;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.VolleyLog;
import com.google.gson.Gson;
import com.triton.johnson_tap_app.R;
import com.triton.johnson_tap_app.RestUtils;
import com.triton.johnson_tap_app.Service_Activity.ServicesActivity;
import com.triton.johnson_tap_app.api.APIInterface;
import com.triton.johnson_tap_app.api.RetrofitClient;
import com.triton.johnson_tap_app.requestpojo.ACKService_SubmitRequest;
import com.triton.johnson_tap_app.requestpojo.Custom_detailsRequest;
import com.triton.johnson_tap_app.requestpojo.Job_status_updateRequest;
import com.triton.johnson_tap_app.responsepojo.Job_status_updateResponse;
import com.triton.johnson_tap_app.responsepojo.Material_DetailsResponseACK;
import com.triton.johnson_tap_app.responsepojo.SuccessResponse;

import java.util.List;

import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MaterialDetailsACK_Activity extends AppCompatActivity {

    Context context;
    String status,job_id,se_user_mobile_no, se_user_name, se_id,check_id, service_title,message,compno,sertype,str_Techsign,str_CustAck,str_ACKCompno;
    TextView txt_Partname,txt_MaterialQty, txt_MaterialID, txt_SeqNumber, txt_no_records;
    Button btn_Prev, btn_Next;
    ImageView img_Back,img_Pause;
    RecyclerView recyclerView;
    SharedPreferences sharedPreferences;
    List<Material_DetailsResponseACK.DataBean> breedTypedataBeanList;
    MaterialDetailsACK_Adapter petBreedTypesListAdapter;
    private String PetBreedType = "",str_job_status,service_type;
    AlertDialog alertDialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_materialdetailsack);
        context = this;

        txt_Partname = findViewById(R.id.txt_partname);
        txt_MaterialQty = findViewById(R.id.txt_materialqty);
        txt_MaterialID = findViewById(R.id.txt_materialid);
        txt_SeqNumber = findViewById(R.id.txt_seqnumber);
        btn_Prev = findViewById(R.id.btn_prev);
        btn_Next = findViewById(R.id.btn_next);
        img_Back = findViewById(R.id.img_back);
        recyclerView  =findViewById(R.id.recyclerView);
        img_Pause = findViewById(R.id.img_paused);
        txt_no_records = findViewById(R.id.txt_no_records);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        se_id = sharedPreferences.getString("_id", "default value");
        se_user_mobile_no = sharedPreferences.getString("user_mobile_no", "default value");
        se_user_name = sharedPreferences.getString("user_name", "default value");
        service_title = sharedPreferences.getString("service_title", "Services");
        str_ACKCompno = sharedPreferences.getString("ackcompno","123");
        service_type = sharedPreferences.getString("service_type","PSM");
        // compno = sharedPreferences.getString("compno","123");
        // sertype = sharedPreferences.getString("sertype","123");

        Log.e("Name", "" + service_title);
        Log.e("Mobile", ""+ se_user_mobile_no);
        Log.e("ACKCompno","" +str_ACKCompno);

        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            //  service_title = extras.getString("service_title");
            status = extras.getString("status");
            //   Log.e("Name",":" + service_title);
            Log.e("Status", "" + status);
            job_id = extras.getString("job_id");
            Log.e("JobID",""+job_id);
            str_Techsign = extras.getString("tech_signature");
            str_CustAck = extras.getString("cust_ack");
        }

        materialDetailscall();

        img_Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent send = new Intent(context, MRDetails_Activity.class);
//                // send.putExtra("service_title",service_title);
//                send.putExtra("job_id",job_id);
//                send.putExtra("status" , status);
//                send.putExtra("tech_signature", str_Techsign);
//                send.putExtra("cust_ack",str_CustAck);
//                startActivity(send);
                onBackPressed();
            }
        });

        btn_Prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent send = new Intent(context, MRDetails_Activity.class);
//                // send.putExtra("service_title",service_title);
//                send.putExtra("job_id",job_id);
//                send.putExtra("status" , status);
//                send.putExtra("tech_signature", str_Techsign);
//                send.putExtra("cust_ack",str_CustAck);
//                startActivity(send);
                onBackPressed();
            }
        });

        btn_Next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent send = new Intent(context, TechnicianSignature_ACKServiceActivity.class);
                // send.putExtra("service_title",service_title);
                send.putExtra("job_id",job_id);
                send.putExtra("status" , status);
                send.putExtra("tech_signature", str_Techsign);
                send.putExtra("cust_ack",str_CustAck);
                startActivity(send);
            }
        });

        img_Pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                alertDialog = new AlertDialog.Builder(context)
                        .setTitle("Are you sure to pause this job ?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialogInterface, int i) {
                                str_job_status = "Job Paused";
                                Job_status_update();
                               // createLocalValueCall();
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

    private void createLocalValueCall() {

        APIInterface apiInterface =  RetrofitClient.getClient().create((APIInterface.class));
        Call<SuccessResponse> call = apiInterface.createLocalValueCallACK(com.triton.johnson_tap_app.utils.RestUtils.getContentType(),createLocalRequest());
        Log.w(VolleyLog.TAG,"Create Local Value url  :%s"+" "+ call.request().url().toString());
        call.enqueue(new Callback<SuccessResponse>() {
            @Override
            public void onResponse(Call<SuccessResponse> call, Response<SuccessResponse> response) {

                Log.e("Hi","OnSucess");

                Log.w(VolleyLog.TAG,"Create Local Response" + new Gson().toJson(response.body()));

                if (response.body() != null) {
                    message = response.body().getMessage();

                    if (response.body().getCode() == 200){

                        if(response.body().getData() != null){

                            Log.d("msg",message);

                            Intent send = new Intent(context, ServicesActivity.class);
                            startActivity(send);
                        }

                    } else{

                        Toasty.warning(getApplicationContext(),""+message,Toasty.LENGTH_LONG).show();
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


    private ACKService_SubmitRequest createLocalRequest() {

        ACKService_SubmitRequest request = new ACKService_SubmitRequest();
        request.set_id(se_id);
        request.setUserId(se_user_mobile_no);
        request.setJobId(job_id);
        request.setServiceType(service_type);
        request.setSMU_ACK_COMPNO(str_ACKCompno);
        request.setTechSignature("-");
        request.setCustomerAcknowledgement("-");
        Log.w(VolleyLog.TAG,"Local Request "+ new Gson().toJson(request));
        return null;
    }

    private void Job_status_update() {

        APIInterface apiInterface = RetrofitClient.getClient().create(APIInterface.class);
        Call<Job_status_updateResponse> call = apiInterface.job_status_updateACKResponseCall(com.triton.johnson_tap_app.utils.RestUtils.getContentType(), job_status_updateRequest());
        Log.w(VolleyLog.TAG,"Response url  :%s"+" "+ call.request().url().toString());

        call.enqueue(new Callback<Job_status_updateResponse>() {
            @SuppressLint("LogNotTimber")
            @Override
            public void onResponse(@NonNull Call<Job_status_updateResponse> call, @NonNull Response<Job_status_updateResponse> response) {

                Log.w(VolleyLog.TAG,"Response" + new Gson().toJson(response.body()));
                if (response.body() != null) {
                    message = response.body().getMessage();

                    if (200 == response.body().getCode()) {
                        if(response.body().getData() != null){

                            Log.d("msg",message);

                            Intent send = new Intent(context, ServicesActivity.class);
                            startActivity(send);
                        }


                    } else {
                        Toasty.warning(getApplicationContext(),""+message,Toasty.LENGTH_LONG).show();

                    }
                }


            }

            @Override
            public void onFailure(@NonNull Call<Job_status_updateResponse> call, @NonNull Throwable t) {
                Log.e("OTP", "--->" + t.getMessage());
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private Job_status_updateRequest job_status_updateRequest() {
        Job_status_updateRequest custom = new Job_status_updateRequest();
        custom.setUser_mobile_no(se_user_mobile_no);
        custom.setService_name(service_title);
        custom.setJob_id(job_id);
        custom.setStatus(str_job_status);
        custom.setSMU_ACK_COMPNO(str_ACKCompno);
        Log.w(VolleyLog.TAG,"Request "+ new Gson().toJson(custom));
        return custom;
    }

    private void materialDetailscall() {
        APIInterface apiInterface = RetrofitClient.getClient().create(APIInterface.class);
        Call<Material_DetailsResponseACK> call = apiInterface.MaterialResponseCall(RestUtils.getContentType(),materialrequest());
        Log.w(TAG, "Material Details Response url  :%s" + " " + call.request().url().toString());

        call.enqueue(new Callback<Material_DetailsResponseACK>() {
            @Override
            public void onResponse(Call<Material_DetailsResponseACK> call, Response<Material_DetailsResponseACK> response) {

                Log.e(VolleyLog.TAG,"Material Response  "  + new Gson().toJson(response.body()));

                if (response.body() != null) {

                    message = response.body().getMessage();
                    Log.d("message", message);

                    if (200 == response.body().getCode()) {
                        if (response.body().getData() != null) {
                            breedTypedataBeanList = response.body().getData();

                            if (breedTypedataBeanList.size() == 0){

                                recyclerView.setVisibility(View.GONE);
                                txt_no_records.setVisibility(View.VISIBLE);
                                txt_no_records.setText("No Records Found");

                            }

                            setBreedTypeView(breedTypedataBeanList);
                            Log.d("dataaaaa", String.valueOf(breedTypedataBeanList));

                        }

                    } else if (400 == response.body().getCode()) {
                        if (response.body().getMessage() != null && response.body().getMessage().equalsIgnoreCase("There is already a user registered with this email id. Please add new email id")) {

                            recyclerView.setVisibility(View.GONE);
                            txt_no_records.setVisibility(View.VISIBLE);
                            txt_no_records.setText("Error 404 Found");
                        }
                    } else {

                        recyclerView.setVisibility(View.GONE);
                        txt_no_records.setVisibility(View.VISIBLE);
                        txt_no_records.setText("Error 404 Found");
                        Toasty.warning(getApplicationContext(), "" + response.body().getMessage(), Toasty.LENGTH_LONG).show();
                    }
                }
            }

            @SuppressLint("LongLogTag")
            @Override
            public void onFailure(Call<Material_DetailsResponseACK> call, Throwable t) {
                Log.e("Material Details On Failure ", "--->" + t.getMessage());
                recyclerView.setVisibility(View.GONE);
                txt_no_records.setVisibility(View.VISIBLE);
                txt_no_records.setText("Something Went Wrong.. Try Again Later");
               // Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setBreedTypeView(List<Material_DetailsResponseACK.DataBean> breedTypedataBeanList) {

        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        petBreedTypesListAdapter = new MaterialDetailsACK_Adapter(getApplicationContext(), breedTypedataBeanList,status);
        recyclerView.setAdapter(petBreedTypesListAdapter);
    }

    private Custom_detailsRequest materialrequest() {
        Custom_detailsRequest custom = new Custom_detailsRequest();
        custom.setJob_id(job_id);
        custom.setUser_mobile_no(se_user_mobile_no);
        custom.setService_name(service_title);
        custom.setSMU_ACK_COMPNO(str_ACKCompno);
        Log.w(VolleyLog.TAG,"MR Details Request "+ new Gson().toJson(custom));
        return custom;
    }

    public void petBreedTypeSelectListener(String petbreedtitle, String petbreedid) {
        PetBreedType = petbreedtitle;
    }

    @Override
    public void onBackPressed() {
//        Intent send = new Intent(context, MRDetails_Activity.class);
//        // send.putExtra("service_title",service_title);
//        send.putExtra("job_id",job_id);
//        send.putExtra("status" , status);
//        send.putExtra("tech_signature", str_Techsign);
//        send.putExtra("cust_ack",str_CustAck);
//        startActivity(send);

        alertDialog = new AlertDialog.Builder(context)
                .setTitle("Are you sure to close this job ?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent send = new Intent(context, ServicesActivity.class);
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
}
