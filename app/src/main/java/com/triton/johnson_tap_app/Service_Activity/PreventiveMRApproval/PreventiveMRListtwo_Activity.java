package com.triton.johnson_tap_app.Service_Activity.PreventiveMRApproval;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.triton.johnson_tap_app.Service_Adapter.PreventiveMRListTwo_Adapter;
import com.triton.johnson_tap_app.R;
import com.triton.johnson_tap_app.RestUtils;
import com.triton.johnson_tap_app.api.APIInterface;
import com.triton.johnson_tap_app.api.RetrofitClient;
import com.triton.johnson_tap_app.requestpojo.Fetch_MrList_Request;
import com.triton.johnson_tap_app.responsepojo.Fetch_MrList_Response;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PreventiveMRListtwo_Activity extends AppCompatActivity {

    private String TAG ="MRListTWO";

    EditText edtsearch;
    TextView txt_no_records;
    ProgressDialog progressDialog;
    ImageView iv_back,img_clearsearch;
    RecyclerView recyclerView;
    PreventiveMRListTwo_Adapter MrListtwoAdapter;
    private String PetBreedType = "";
    String se_user_mobile_no, se_user_name, se_id,check_id, service_title,job_id,message,status,compno, sertype;
    private List<Fetch_MrList_Response.Datum> breedTypedataBeanList;
    String str_mr1 ="",str_mr2="",str_mr3="",str_mr4="",str_mr5="",str_mr6="",str_mr7="",str_mr8="",str_mr9="",str_mr10="";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_preventive_mrlisttwo);

        edtsearch = findViewById(R.id.edt_search);
        txt_no_records = findViewById(R.id.txt_no_records);
        iv_back = findViewById(R.id.iv_back);
        img_clearsearch = findViewById(R.id.img_clearsearch);
        recyclerView = findViewById(R.id.recyclerView);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        se_id = sharedPreferences.getString("_id", "default value");
        se_user_mobile_no = sharedPreferences.getString("user_mobile_no", "default value");
        se_user_name = sharedPreferences.getString("user_name", "default value");

        compno = sharedPreferences.getString("compno","123");
        sertype = sharedPreferences.getString("sertype","123");

        service_title = sharedPreferences.getString("service_title", "default value");
        job_id = sharedPreferences.getString("job_id", "default value");
        Log.e("Name",""+ service_title);
        Log.e("Job ID",""+ job_id);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
           // service_title = extras.getString("service_title");
            status = extras.getString("status");
            Log.e("Status", "" + status);
            // Log.d("title",service_title);
        }
        if (extras != null) {
            //job_id = extras.getString("job_id");
            str_mr1 = extras.getString("mr1");
            str_mr2 = extras.getString("mr2");
            str_mr3 = extras.getString("mr3");
            str_mr4 = extras.getString("mr4");
            str_mr5 = extras.getString("mr5");
            str_mr6 = extras.getString("mr6");
            str_mr7 = extras.getString("mr7");
            str_mr8 = extras.getString("mr8");
            str_mr9 = extras.getString("mr9");
            str_mr10 = extras.getString("mr10");
        }


        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("service_title", service_title);
        editor.apply();


        fetchmrlistcall();


        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent send = new Intent(PreventiveMRListtwo_Activity.this, PreventiveMRListOne_Activity.class);
                send.putExtra("service_title",service_title);
                send.putExtra("job_id",job_id);
                send.putExtra("mr1", str_mr1);
                send.putExtra("mr2", str_mr2);
                send.putExtra("mr3", str_mr3);
                send.putExtra("mr4", str_mr4);
                send.putExtra("mr5", str_mr5);
                send.putExtra("mr6", str_mr6);
                send.putExtra("mr7", str_mr7);
                send.putExtra("mr8", str_mr8);
                send.putExtra("mr9", str_mr9);
                send.putExtra("mr10", str_mr10);
                send.putExtra("status", status);
                startActivity(send);
            }
        });

        edtsearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                String Search = edtsearch.getText().toString();

                if(Search.equals("")){
                    recyclerView.setVisibility(View.VISIBLE);
                    img_clearsearch.setVisibility(View.INVISIBLE);
                } else {

                    filter(Search);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

                String Search = edtsearch.getText().toString();

                recyclerView.setVisibility(View.VISIBLE);
                txt_no_records.setVisibility(View.GONE);

                filter(Search);

            }
        });

    }

    @SuppressLint("SetTextI18n")
    private void filter(String search) {
        List<Fetch_MrList_Response.Datum> filterlist = new ArrayList<>();
        for (Fetch_MrList_Response.Datum item :breedTypedataBeanList){
            if(item.getPartname().toLowerCase().contains(search.toLowerCase()))
            {
                Log.w(TAG,"filter----"+item.getPartname().toLowerCase().contains(search.toLowerCase()));
                filterlist.add(item);

            }
        }

        if(filterlist.isEmpty())
        {
            Toast.makeText(this,"No Data Found ... ",Toast.LENGTH_SHORT).show();
            recyclerView.setVisibility(View.GONE);
            txt_no_records.setVisibility(View.VISIBLE);
            txt_no_records.setText("No Data Found");
        }else
        {
            MrListtwoAdapter.filterrList(filterlist);
        }
    }

    private void fetchmrlistcall() {

        progressDialog = new ProgressDialog(PreventiveMRListtwo_Activity.this);
        progressDialog.setMessage("Please Wait..");
        progressDialog.setCancelable(false);
        progressDialog.show();

        APIInterface apiInterface = RetrofitClient.getClient().create(APIInterface.class);
        Call<Fetch_MrList_Response> call = apiInterface.FetchMrListBreakdownMRCall(RestUtils.getContentType(),fetch_mrList_request());

        Log.w(TAG,"mrlist url  :%s"+" "+ call.request().url().toString());

        call.enqueue(new Callback<Fetch_MrList_Response>() {
            @Override
            public void onResponse(Call<Fetch_MrList_Response> call, Response<Fetch_MrList_Response> response) {
                Log.w(TAG, "MRList Response" + new Gson().toJson(response.body()));

                progressDialog.dismiss();
                if (response.body() != null){
                    message = response.body().getMessage();
                    Log.d("message", message);

                    if (200 == response.body().getCode()){
                        if (response.body().getData() != null) {
                            breedTypedataBeanList = response.body().getData();

                            if (breedTypedataBeanList.size() == 0){

                                recyclerView.setVisibility(View.GONE);
                                txt_no_records.setVisibility(View.VISIBLE);
                                txt_no_records.setText("No Records Found");
                                edtsearch.setEnabled(false);

                            }

                            setBreedTypeView(breedTypedataBeanList);
                            Log.d("dataaaaa", String.valueOf(breedTypedataBeanList));
                        }
                    }
                }else {
                    recyclerView.setVisibility(View.GONE);
                    txt_no_records.setVisibility(View.VISIBLE);
                    txt_no_records.setText("Error 404 Found");
                    edtsearch.setEnabled(false);
                }
            }

            @Override
            public void onFailure(Call<Fetch_MrList_Response> call, Throwable t) {
                Log.e("Mrlist ", "--->" + t.getMessage());
             //   Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                recyclerView.setVisibility(View.GONE);
                txt_no_records.setVisibility(View.VISIBLE);
                txt_no_records.setText("Something Went Wrong.. Try Again Later");
                edtsearch.setEnabled(false);
            }
        });
    }

    private void setBreedTypeView(List<Fetch_MrList_Response.Datum> breedTypedataBeanList) {

        Log.e("Nish",""+breedTypedataBeanList.size());
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        MrListtwoAdapter = new PreventiveMRListTwo_Adapter(getApplicationContext(),breedTypedataBeanList,this, service_title,job_id,
                str_mr1,str_mr2,str_mr3,str_mr4,str_mr5,str_mr6,str_mr7,str_mr8,str_mr9,str_mr10,status );
        recyclerView.setAdapter(MrListtwoAdapter);
//        MrListoneAdapter = new BreakdownMRListTwo_Adapter(getApplicationContext(), breedTypedataBeanList,this);
//        recyclerView.setAdapter(MrListoneAdapter);
    }

    private Fetch_MrList_Request fetch_mrList_request() {
        Fetch_MrList_Request mrlistrequest = new Fetch_MrList_Request();
        mrlistrequest.setJobId(job_id);
        mrlistrequest.setUserMobileNo(se_user_mobile_no);
        mrlistrequest.setSMU_SCH_COMPNO(compno);
        mrlistrequest.setSMU_SCH_SERTYPE(sertype);
        Log.w(TAG, "mrlistrequest " + new Gson().toJson(mrlistrequest));
        return mrlistrequest;
    }

    public void petBreedTypeSelectListener(String petbreedtitle, String petbreedid) {
        PetBreedType = petbreedtitle;
    }

    @Override
    public void onBackPressed() {
        Intent send = new Intent(PreventiveMRListtwo_Activity.this, PreventiveMRListOne_Activity.class);
        send.putExtra("service_title",service_title);
        send.putExtra("job_id",job_id);
        send.putExtra("mr1", str_mr1);
        send.putExtra("mr2", str_mr2);
        send.putExtra("mr3", str_mr3);
        send.putExtra("mr4", str_mr4);
        send.putExtra("mr5", str_mr5);
        send.putExtra("mr6", str_mr6);
        send.putExtra("mr7", str_mr7);
        send.putExtra("mr8", str_mr8);
        send.putExtra("mr9", str_mr9);
        send.putExtra("mr10", str_mr10);
        send.putExtra("status", status);
        startActivity(send);
    }
}