package com.triton.johnson_tap_app.Service_Activity.SiteAudit;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.triton.johnson_tap_app.Db.CommonUtil;
import com.triton.johnson_tap_app.Db.DbHelper;
import com.triton.johnson_tap_app.R;
import com.triton.johnson_tap_app.RestUtils;
import com.triton.johnson_tap_app.Service_Activity.BreakdownMRApprovel.BreakdownMRListOne_Activity;
import com.triton.johnson_tap_app.Service_Activity.Breakdown_Services.Technician_signatureActivity;
import com.triton.johnson_tap_app.Service_Adapter.BreakdownMRListTwo_Adapter;
import com.triton.johnson_tap_app.api.APIInterface;
import com.triton.johnson_tap_app.api.RetrofitClient;
import com.triton.johnson_tap_app.requestpojo.Fetch_MrList_Request;
import com.triton.johnson_tap_app.responsepojo.Fetch_MrList_Response;
import com.triton.johnson_tap_app.utils.ConnectionDetector;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AuditMRList_Activity extends AppCompatActivity {

    private String TAG ="MRListTWO";
    Context context;
    EditText edtsearch;
    TextView txt_no_records;
    ImageView iv_back,img_clearsearch;
    RecyclerView recyclerView;
    AuditMRList_Adapter MrListtwoAdapter;
    private String PetBreedType = "";
    String se_user_mobile_no, se_user_name, se_id,check_id, service_title,job_id,message,status,compno, sertype;
    private List<Fetch_MrList_Response.Datum> breedTypedataBeanList;
    String str_mr1 ="",str_mr2="",str_mr3="",str_mr4="",str_mr5="",str_mr6="",str_mr7="",str_mr8="",str_mr9="",str_mr10="",networkStatus="";
    ProgressDialog progressDialog;
    TextView txt_Jobid,txt_Starttime;
    String str_StartTime;
    Button btn_Next;
    ArrayList<String> Ar_PartNo = new ArrayList<>();
    ArrayList<String> Ar_PartName = new ArrayList<>();
    ArrayList<String> Ar_PartQuantity = new ArrayList<>();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_breakdown_mrlisttwo);
        context = this;

        edtsearch = findViewById(R.id.edt_search);
        txt_no_records = findViewById(R.id.txt_no_records);
        iv_back = findViewById(R.id.iv_back);
        img_clearsearch = findViewById(R.id.img_clearsearch);
        recyclerView = findViewById(R.id.recyclerView);
        txt_Starttime = findViewById(R.id.txt_starttime);
        txt_Jobid = findViewById(R.id.txt_jobid);
        btn_Next = findViewById(R.id.btn_next);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        se_id = sharedPreferences.getString("_id", "default value");
        se_user_mobile_no = sharedPreferences.getString("user_mobile_no", "default value");
        se_user_name = sharedPreferences.getString("user_name", "default value");
        job_id =sharedPreferences.getString("jobid","L-1234");
        Log.e("JobID",""+job_id);

        str_StartTime = sharedPreferences.getString("starttime","");
        str_StartTime = str_StartTime.replaceAll("[^0-9-:]", " ");
        Log.e("Start Time",str_StartTime);
        txt_Jobid.setText("Job ID : " + job_id);
        txt_Starttime.setText("Start Time : " + str_StartTime);

      //  compno = sharedPreferences.getString("compno","123");
       // sertype = sharedPreferences.getString("sertype","123");

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            service_title = extras.getString("service_title");
            status = extras.getString("status");
            Log.e("Status", "" + status);
            // Log.d("title",service_title);
        }
        if (extras != null) {
          //  job_id = extras.getString("job_id");
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

        networkStatus = ConnectionDetector.getConnectivityStatusString(getApplicationContext());

        Log.e("Network",""+networkStatus);
        if (networkStatus.equalsIgnoreCase("Not connected to Internet")) {

            NoInternetDialog();

        }else {

            fetchmrlistcall();
        }


        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onBackPressed();
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

        btn_Next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onBackPressed();
            }
        });

    }

    public void NoInternetDialog() {

        AlertDialog.Builder mBuilder = new AlertDialog.Builder(context);
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

        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Please Wait..");
        progressDialog.setCancelable(false);
        progressDialog.show();

        APIInterface apiInterface = RetrofitClient.getClient().create(APIInterface.class);
        Call<Fetch_MrList_Response> call = apiInterface.FetchMrListAuditCall(RestUtils.getContentType(),fetch_mrList_request());

        Log.w(TAG,"mrlist url  :%s"+" "+ call.request().url().toString());

        call.enqueue(new Callback<Fetch_MrList_Response>() {
            @Override
            public void onResponse(Call<Fetch_MrList_Response> call, Response<Fetch_MrList_Response> response) {
                Log.w(TAG, "MRList Response" + new Gson().toJson(response.body()));

                progressDialog.dismiss();

                long delayInMillis = 15000;
                Timer timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        progressDialog.dismiss();
                    }
                }, delayInMillis);
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
                    }else if (400 == response.body().getCode()) {

                        progressDialog.dismiss();
                        if (response.body().getMessage() != null && response.body().getMessage().equalsIgnoreCase("There is already a user registered with this email id. Please add new email id")) {

                            recyclerView.setVisibility(View.GONE);
                            txt_no_records.setVisibility(View.VISIBLE);
                            txt_no_records.setText("Error 404 Found");
                            edtsearch.setEnabled(false);
                        }
                    } else {

                        progressDialog.dismiss();
                        recyclerView.setVisibility(View.GONE);
                        txt_no_records.setVisibility(View.VISIBLE);
                        txt_no_records.setText("Error 404 Found");
                        edtsearch.setEnabled(false);
                        // Toasty.warning(getApplicationContext(), "" + response.body().getMessage(), Toasty.LENGTH_LONG).show();
                    }
                }
            }

            //One of the best for Testing and the account and shirt and let me see the account of tra and shirt ends in which the best for Testing using the one third of the account and the gnkjff ygllk ghhhj

            @Override
            public void onFailure(Call<Fetch_MrList_Response> call, Throwable t) {

                progressDialog.dismiss();
                Log.e("Mrlist ", "--->" + t.getMessage());
                recyclerView.setVisibility(View.GONE);
                txt_no_records.setVisibility(View.VISIBLE);
                txt_no_records.setText("Something Went Wrong.. Try Again Later");
                edtsearch.setEnabled(false);
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setBreedTypeView(List<Fetch_MrList_Response.Datum> breedTypedataBeanList) {

        getMRList();

        Log.e("Nish",""+breedTypedataBeanList.size());
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        MrListtwoAdapter = new AuditMRList_Adapter(getApplicationContext(),breedTypedataBeanList,this, service_title,job_id,
                str_mr1,str_mr2,str_mr3,str_mr4,str_mr5,str_mr6,str_mr7,str_mr8,str_mr9,str_mr10,status,Ar_PartNo,Ar_PartName,Ar_PartQuantity);
        recyclerView.setAdapter(MrListtwoAdapter);
//        MrListoneAdapter = new BreakdownMRListTwo_Adapter(getApplicationContext(), breedTypedataBeanList,this);
//        recyclerView.setAdapter(MrListoneAdapter);
    }

    private void getMRList() {

        Ar_PartNo = new ArrayList<>();
        Ar_PartName = new ArrayList<>();
        Ar_PartQuantity = new ArrayList<>();

        Cursor cur = CommonUtil.dbUtil.getMRList(job_id,"3",service_title);
        Log.e("List Count",""+cur.getCount());

        if (cur.getCount() > 0 && cur.moveToFirst()) {

            do {
                @SuppressLint("Range")
                String partno = cur.getString(cur.getColumnIndex(DbHelper.PART_NO));
                @SuppressLint("Range")
                String partname = cur.getString(cur.getColumnIndex(DbHelper.PART_NAME));
                @SuppressLint("Range")
                String Quantity = cur.getString(cur.getColumnIndex(DbHelper.QUANTITY));
                Log.e("Part NO", "" + partno);
                Ar_PartNo.add(partno);
                Ar_PartName.add(partname);
                Ar_PartQuantity.add(Quantity);

            } while (cur.moveToNext());

        }
    }

    private Fetch_MrList_Request fetch_mrList_request() {
        Fetch_MrList_Request mrlistrequest = new Fetch_MrList_Request();
        mrlistrequest.setJobId(job_id);
        mrlistrequest.setUserMobileNo(se_user_mobile_no);
       // mrlistrequest.setSMU_SCH_COMPNO(compno);
       // mrlistrequest.setSMU_SCH_SERTYPE(sertype);
        Log.w(TAG, "mrlistrequest " + new Gson().toJson(mrlistrequest));
        return mrlistrequest;
    }

    public void petBreedTypeSelectListener(String petbreedtitle, String petbreedid) {
        PetBreedType = petbreedtitle;
    }

    @Override
    public void onBackPressed() {

      //  super.onBackPressed();
        Intent send = new Intent(context, AuditMR_Activity.class);
        send.putExtra("status", status);
        startActivity(send);

    }
}