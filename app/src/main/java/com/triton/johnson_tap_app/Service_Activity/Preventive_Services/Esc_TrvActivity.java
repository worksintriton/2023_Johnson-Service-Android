package com.triton.johnson_tap_app.Service_Activity.Preventive_Services;

import static android.content.ContentValues.TAG;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.triton.johnson_tap_app.Adapter.CardViewDataAdapter;
import com.triton.johnson_tap_app.Adapter.MyListAdapter;
import com.triton.johnson_tap_app.MyListData;
import com.triton.johnson_tap_app.R;
import com.triton.johnson_tap_app.RestUtils;
import com.triton.johnson_tap_app.Service_Adapter.BD_DetailsAdapter;
import com.triton.johnson_tap_app.UserTypeSelectListener1;
import com.triton.johnson_tap_app.api.APIInterface;
import com.triton.johnson_tap_app.api.RetrofitClient;
import com.triton.johnson_tap_app.requestpojo.BD_DetailsRequest;
import com.triton.johnson_tap_app.responsepojo.BD_DetailsResponse;

import java.util.ArrayList;
import java.util.List;

import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Esc_TrvActivity extends AppCompatActivity implements UserTypeSelectListener1 {

    private Button btnSelection;
    private RecyclerView recyclerView;
    private CardViewDataAdapter adapter;
    ImageView iv_back,ic_paused;
    List<BD_DetailsResponse.DataBean> breedTypedataBeanList;
    BD_DetailsAdapter activityBasedListAdapter;
    private String PetBreedType = "";
    String message,se_user_mobile_no, se_user_name, se_id,check_id, service_title,str_job_id,data;
    private String Title,petimage;
    AlertDialog alertDialog;
    ProgressDialog progressDialog;
    ArrayList<String> myData = new ArrayList<>();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_esc_trv);

        btnSelection = (Button) findViewById(R.id.btn_next);
        iv_back = (ImageView) findViewById(R.id.iv_back);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

//        Bundle extras = getIntent().getExtras();
//        if (extras != null) {
//            str_job_id = extras.getString("job_id");
//        }

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        se_id = sharedPreferences.getString("_id", "default value");
        se_user_mobile_no = sharedPreferences.getString("user_mobile_no", "default value");
        se_user_name = sharedPreferences.getString("user_name", "default value");
        service_title = sharedPreferences.getString("service_title", "default value");

        MyListData[] myListData = new MyListData[] {
                new MyListData("MONTHLY"),
                new MyListData("QUARTERLY"),
                new MyListData("HALF YEARLY"),
                new MyListData("YEARLY")
        };

        MyListAdapter adapter = new MyListAdapter(myListData,this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        // jobFindResponseCall("L-F3183");
       // jobFindResponseCall(str_job_id);

        btnSelection.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
//                for (int i = 0; i < breedTypedataBeanList.size(); i++) {
//                    BD_DetailsResponse.DataBean singleStudent = breedTypedataBeanList.get(i);
//                    if (singleStudent.isSelected() == true) {
//                        data = breedTypedataBeanList.get(i).getTitle().toString();
//                    }
//                }
//
//                if(data.equals("")){
//
//                    alertDialog = new AlertDialog.Builder(Esc_TrvActivity.this)
//                            .setTitle("Please Selected Value")
//                            .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
//                                public void onClick(DialogInterface dialogInterface, int i) {
//                                    alertDialog.dismiss();
//                                }
//                            })
//                            .show();
//                }
//                else {
//
////                    Intent send = new Intent(Esc_TrvActivity.this, Feedback_GroupActivity.class);
////                    send.putExtra("bd_details",data);
////                    send.putExtra("job_id",str_job_id);
////                    startActivity(send);
   //             }

                Intent send = new Intent(Esc_TrvActivity.this, Quarterly_Top_PitActivity.class);
                startActivity(send);
            }
        });


        iv_back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

//                alertDialog = new AlertDialog.Builder(Esc_TrvActivity.this)
//                        .setTitle("Are you sure close job ?")
//                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialogInterface, int i) {
//                                Intent send = new Intent(Esc_TrvActivity.this, Job_Details_PreventiveActivity.class);
//                                startActivity(send);
//                            }
//                        })
//                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialogInterface, int i) {
//                                alertDialog.dismiss();
//                            }
//                        })
//                        .show();

                onBackPressed();
            }
        });
    }

    private void jobFindResponseCall(String job_no) {
        APIInterface apiInterface = RetrofitClient.getClient().create(APIInterface.class);
        Call<BD_DetailsResponse> call = apiInterface.BD_DetailsResponseCall(RestUtils.getContentType(), serviceRequest(job_no));
        Log.w(TAG, "Jobno Find Response url  :%s" + " " + call.request().url().toString());
        call.enqueue(new Callback<BD_DetailsResponse>() {
            @SuppressLint("LogNotTimber")
            @Override
            public void onResponse(@NonNull Call<BD_DetailsResponse> call, @NonNull Response<BD_DetailsResponse> response) {
                Log.w(TAG, "Jobno Find Response" + new Gson().toJson(response.body()));

                if (response.body() != null) {

                    message = response.body().getMessage();
                    Log.d("message", message);

                    if (200 == response.body().getCode()) {
                        if (response.body().getData() != null) {
                            breedTypedataBeanList = response.body().getData();

                            setView(breedTypedataBeanList);
                            Log.d("dataaaaa", String.valueOf(breedTypedataBeanList));

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
            public void onFailure(@NonNull Call<BD_DetailsResponse> call, @NonNull Throwable t) {
                Log.e("Jobno Find ", "--->" + t.getMessage());
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private BD_DetailsRequest serviceRequest(String job_no) {
        BD_DetailsRequest service = new BD_DetailsRequest();
        service.setJob_id(job_no);
        Log.w(TAG, "Jobno Find Request " + new Gson().toJson(service));
        return service;
    }

    private void setView(List<BD_DetailsResponse.DataBean> dataBeanList) {
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
      //  activityBasedListAdapter = new BD_DetailsAdapter(getApplicationContext(), dataBeanList,this);
        recyclerView.setAdapter(activityBasedListAdapter);
    }

    public void userTypeSelectListener1(String usertype, String usertypevalue) {
        Title = usertype;

        Log.w(TAG,"myPetsSelectListener : "+ "petList" +new Gson().toJson(breedTypedataBeanList));

        if(breedTypedataBeanList != null && breedTypedataBeanList.size()>0) {
            for (int i = 0; i < breedTypedataBeanList.size(); i++) {
                if (breedTypedataBeanList.get(i).getTitle().equalsIgnoreCase(breedTypedataBeanList.get(i).getTitle())) {
                    petimage = breedTypedataBeanList.get(i).getTitle();
                }
                Log.w(TAG, "myPetsSelectListener : " + "petimage" + petimage);

            }
        }
    }

    @Override
    public void onBackPressed() {
        alertDialog = new AlertDialog.Builder(Esc_TrvActivity.this)
                .setTitle("Are you sure close job ?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent send = new Intent(Esc_TrvActivity.this, Job_Details_PreventiveActivity.class);
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