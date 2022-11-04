package com.triton.johnson_tap_app.activity;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.triton.johnson_tap_app.R;
import com.triton.johnson_tap_app.RTGS_Pop_Adapter;
import com.triton.johnson_tap_app.RestUtils;
import com.triton.johnson_tap_app.Service_Adapter.BD_DetailsAdapter;
import com.triton.johnson_tap_app.UserTypeSelectListener1;
import com.triton.johnson_tap_app.api.APIInterface;
import com.triton.johnson_tap_app.api.RetrofitClient;
import com.triton.johnson_tap_app.responsepojo.Feedback_GroupResponse;
import com.triton.johnson_tap_app.responsepojo.RTGS_PopResponse;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RTGS_PopActivity extends AppCompatActivity{

//    private RetrofitAdapter retrofitAdapter;
    private RecyclerView recyclerView;
    ImageView iv_back;
    List<RTGS_PopResponse.DataBean> breedTypedataBeanList;
    RTGS_Pop_Adapter activityBasedListAdapter;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.edt_search)
    EditText edt_search;
    String petimage;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.img_clearsearch)
    ImageView img_clearsearch;
    TextView text,txt_no_records;

    private String search_string ="",agent_code;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_rtgs_pop);

        ButterKnife.bind(this);
        txt_no_records = findViewById(R.id.txt_no_records);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            agent_code = extras.getString("agent_code");
        }

    //    Toast.makeText(RTGS_PopActivity.this, "N" + agent_code, Toast.LENGTH_SHORT).show();

        iv_back = (ImageView) findViewById(R.id.iv_back);
        recyclerView = findViewById(R.id.recycler1);

       // fetchJSON();

        jobFindResponseCall();

        iv_back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent send = new Intent(RTGS_PopActivity.this, Daily_Collection_DetailsActivity.class);
                send.putExtra("Radio_button","RTGS");
                send.putExtra("agent_code",agent_code);
                startActivity(send);
            }
        });

        edt_search.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {


                String Searchvalue = edt_search.getText().toString();

                Log.e("Search",""+Searchvalue);

                recyclerView.setVisibility(View.VISIBLE);
                txt_no_records.setVisibility(View.GONE);

                filter(Searchvalue);

            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {

                String Searchvalue = edt_search.getText().toString();

                Log.e("Search",""+Searchvalue);

                if(Searchvalue.equals("")){

                    Log.e("Search 1",""+Searchvalue);

                    recyclerView.setVisibility(View.VISIBLE);
                //   jobFindResponseCall();
                    img_clearsearch.setVisibility(View.INVISIBLE);
                }
                else {
                    Log.e("Search 2",""+Searchvalue);
                    //   Log.w(TAG,"Search Value---"+Searchvalue);
                    filter(Searchvalue);
                }
            }
        });

        img_clearsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edt_search.setText("");
                recyclerView.setVisibility(View.VISIBLE);
                jobFindResponseCall();
                img_clearsearch.setVisibility(View.INVISIBLE);
            }
        });

    }

    private void filter(String s) {
        List<RTGS_PopResponse.DataBean> filteredlist = new ArrayList<>();
        for(RTGS_PopResponse.DataBean item : breedTypedataBeanList)
        {
           // if(item.getFA_BSD_UTRNO().toLowerCase().contains(s.toLowerCase()) ||
            // item.getFA_BSD_BANKDT().toLowerCase().contains(s.toLowerCase()) ||
            // item.getFA_BSD_AMOUNT().toLowerCase().contains(s.toLowerCase()) ||
            // item.getFA_BSD_CUSACNM().toLowerCase().contains(s.toLowerCase()) || error
            // item.getFA_BSD_BALAMT().toLowerCase().contains(s.toLowerCase()))
            if(item.getFA_BSD_UTRNO().toString().contains(s.toString()))
            {
                Log.e("A","" + item.getFA_BSD_UTRNO().toString());
//                Log.e("A","" + item.getFA_BSD_BANKDT().toString());
//                Log.e("A","" + item.getFA_BSD_AMOUNT().toString());
//                Log.e("A","" + item.getFA_BSD_CUSACNM().toString());
//                Log.e("A","" + item.getFA_BSD_BALAMT().toString());

                Log.w(TAG,"filter----"+item.getFA_BSD_UTRNO().toLowerCase().contains(s.toLowerCase()));
                filteredlist.add(item);

            }
        }
        if(filteredlist.isEmpty())
        {
            Toast.makeText(this,"No Data Found ... ",Toast.LENGTH_SHORT).show();
            recyclerView.setVisibility(View.GONE);
            txt_no_records.setVisibility(View.VISIBLE);
            txt_no_records.setText("No Data Found");
        }else
        {
            activityBasedListAdapter.filterList(filteredlist);
        }

    }

    private void jobFindResponseCall() {
        APIInterface apiInterface = RetrofitClient.getClient().create(APIInterface.class);
        Call<RTGS_PopResponse> call = apiInterface.filterPageInfoResponseCall(RestUtils.getContentType());
        Log.w(TAG, "Jobno Find Response url  :%s" + " " + call.request().url().toString());
        call.enqueue(new Callback<RTGS_PopResponse>() {
            @SuppressLint("LogNotTimber")
            @Override
            public void onResponse(@NonNull Call<RTGS_PopResponse> call, @NonNull Response<RTGS_PopResponse> response) {
                Log.w(TAG, "Filter Page Info Response" + new Gson().toJson(response.body()));

                if (response.body() != null) {

                   String message = response.body().getMessage();
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
            public void onFailure(@NonNull Call<RTGS_PopResponse> call, @NonNull Throwable t) {
                Log.e("Jobno Find ", "--->" + t.getMessage());
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void setView(List<RTGS_PopResponse.DataBean> dataBeanList) {
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        activityBasedListAdapter = new  RTGS_Pop_Adapter(getApplicationContext(), dataBeanList);
        recyclerView.setAdapter(activityBasedListAdapter);
    }

    }

