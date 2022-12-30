package com.triton.johnson_tap_app.Service_Activity.Breakdown_Services;

import static android.content.ContentValues.TAG;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.VolleyLog;
import com.google.gson.Gson;
import com.triton.johnson_tap_app.Db.CommonUtil;
import com.triton.johnson_tap_app.Db.DbHelper;
import com.triton.johnson_tap_app.Db.DbUtil;
import com.triton.johnson_tap_app.R;
import com.triton.johnson_tap_app.RestUtils;
import com.triton.johnson_tap_app.Service_Activity.ServicesActivity;
import com.triton.johnson_tap_app.Service_Adapter.Feedback_DetailsAdapter;
import com.triton.johnson_tap_app.UserTypeSelectListener1;
import com.triton.johnson_tap_app.api.APIInterface;
import com.triton.johnson_tap_app.api.RetrofitClient;
import com.triton.johnson_tap_app.requestpojo.Breakdowm_Submit_Request;
import com.triton.johnson_tap_app.requestpojo.Feedback_DetailsRequest;
import com.triton.johnson_tap_app.requestpojo.Job_status_updateRequest;
import com.triton.johnson_tap_app.responsepojo.Feedback_DetailsResponse;
import com.triton.johnson_tap_app.responsepojo.Job_status_updateResponse;
import com.triton.johnson_tap_app.responsepojo.SuccessResponse;
import com.triton.johnson_tap_app.utils.ConnectionDetector;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Feedback_DetailsActivity extends AppCompatActivity implements UserTypeSelectListener1 {

    TextView text,txt_no_records;
    Button btnSelection, btn_prev;
    private RecyclerView recyclerView;
    private EditText etsearch;
    int textlength = 0;
    ImageView iv_back,img_clearsearch,img_Pause;
    String feedback_group ="", message, Title, petimage,str2=null,sstring="",bd_dta,job_id,str_feedback_details="",service_title,feedbackGroup,pre_check,status;
    String se_id,se_user_mobile_no,se_user_name,compno,sertype;
    List<Feedback_DetailsResponse.DataBean> breedTypedataBeanList;
    Feedback_DetailsAdapter activityBasedListAdapter;
    AlertDialog alertDialog;
    SharedPreferences sharedPreferences;
    Context context;
    ArrayList<String> mydata = new ArrayList<>();
    ArrayList<String> outputList = null;
    TextView txt_Jobid,txt_Starttime;
    String str_StartTime,str_job_status="",str_BDDetails="";
    String networkStatus = "";
    double Latitude ,Logitude;
    String address = "";

    @SuppressLint("MissingInflatedId")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_feedback_details);
        context = this;

        CommonUtil.dbUtil = new DbUtil(context);
        CommonUtil.dbUtil.open();
        CommonUtil.dbHelper = new DbHelper(context);

        text = findViewById(R.id.text);
        btn_prev = (Button) findViewById(R.id.btn_show);
        iv_back = (ImageView) findViewById(R.id.iv_back);
        img_clearsearch = (ImageView) findViewById(R.id.img_clearsearch);
        btnSelection = (Button) findViewById(R.id.btn_next);
        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        etsearch = (EditText) findViewById(R.id.edt_search);
        txt_no_records = findViewById(R.id.txt_no_records);
        txt_Starttime = findViewById(R.id.txt_starttime);
        txt_Jobid = findViewById(R.id.txt_jobid);
        img_Pause = findViewById(R.id.ic_paused);


        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        se_id = sharedPreferences.getString("_id", "default value");
        se_user_mobile_no = sharedPreferences.getString("user_mobile_no", "default value");
        se_user_name = sharedPreferences.getString("user_name", "default value");
        job_id = sharedPreferences.getString("job_id", "default value");
        service_title = sharedPreferences.getString("service_title", "default value");
        feedback_group = sharedPreferences.getString("feedbackgroup","FG0003");
        compno = sharedPreferences.getString("compno","123");
        sertype = sharedPreferences.getString("sertype","123");
        String name = sharedPreferences.getString("Hi","");
        Log.e("Hi Nish",""+name);
        Log.e("JobID",""+job_id);
        Log.e("Name",""+service_title);
        Log.e("Feedback Group",feedback_group);

        str_StartTime = sharedPreferences.getString("starttime","");
        str_StartTime = str_StartTime.replaceAll("[^0-9-:]", " ");
        Log.e("Start Time",str_StartTime);
        txt_Jobid.setText("Job ID : " + job_id);
        txt_Starttime.setText("Start Time : " + str_StartTime);

        Latitude = Double.parseDouble(sharedPreferences.getString("lati","0.00000"));
        Logitude = Double.parseDouble(sharedPreferences.getString("long","0.00000"));
        address =sharedPreferences.getString("add","Chennai");
        Log.e("Location",""+Latitude+""+Logitude+""+address);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
//            feedback_group = extras.getString("feedback_group");
//            Log.e("Feedback Group 1",""+feedbackGroup);
            status = extras.getString("status");
            Log.e("Status",status);
        }

        if (extras != null) {
            bd_dta = extras.getString("bd_details");
        }

        if (extras != null) {
           // job_id = extras.getString("job_id");
        }

      //  CommonUtil.dbUtil.reportDeletePreventiveListDelete(job_id,service_title);


//            str2 = feedback_group.substring(1, feedback_group.length());
//            String[] sArr = str2.split(",");
//            List<String> sList = Arrays.asList(sArr);
//            sstring = String.valueOf(sList);

        Spannable name_Upload = new SpannableString("Feedback Description ");
        name_Upload.setSpan(new ForegroundColorSpan(Feedback_DetailsActivity.this.getResources().getColor(R.color.colorAccent)), 0, name_Upload.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        text.setText(name_Upload);
        Spannable name_Upload1 = new SpannableString("*");
        name_Upload1.setSpan(new ForegroundColorSpan(Color.RED), 0, name_Upload1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        text.append(name_Upload1);

        btn_prev.setBackgroundResource(R.drawable.blue_button_background_with_radius);
        btn_prev.setTextColor(getResources().getColor(R.color.white));
        btn_prev.setEnabled(true);
        etsearch = (EditText) findViewById(R.id.edt_search);

        networkStatus = ConnectionDetector.getConnectivityStatusString(getApplicationContext());

        Log.e("Network",""+networkStatus);
        if (networkStatus.equalsIgnoreCase("Not connected to Internet")) {

           NoInternetDialog();

        }
        else{

            jobFindResponseCall();

        }

        getBDDetails();
        getFeedbackGroup();

        if (status.equals("paused")){

            if (!Objects.equals(networkStatus, "Not connected to Internet")){

//                retrive_LocalValue();
            }
            else{
                NoInternetDialog();
            }

        }

        btnSelection.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
//                String data = "";
//                for (int i = 0; i < breedTypedataBeanList.size(); i++) {
//                    Feedback_DetailsResponse.DataBean singleStudent = breedTypedataBeanList.get(i);
//                    if (singleStudent.isSelected() == true) {
//
//                        data = data + "," + breedTypedataBeanList.get(i).getCodes();
//                    }
//
//               }
                getFeedBackDesc();

                ArrayList<String> outputList = new ArrayList<String>();
                for (String item: mydata) {
                    //outputList.add("\""+item+"\"");
                    outputList.add(""+item+"");
                    outputList.remove("null");
                }
                pre_check = String.valueOf(outputList);
//                   pre_check = pre_check.replaceAll("\\[", "").replaceAll("\\]","");
//                  System.out.println("EEEEEEEEEEE"+ddd);

              //  Log.e("FEEDBACK GROUP", String.valueOf(mydata));
                Log.e("FeedBack Group",""+pre_check);

                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("feedbackdetails", pre_check);
                Log.e("FeedBack Desc", pre_check);
                editor.putString("Hi","Nishanth");
                Log.e("FeedBack Desc","dsfds");
                editor.apply();

             //   if(data.equals("")){
                if(mydata.isEmpty()) {
                    alertDialog = new AlertDialog.Builder(Feedback_DetailsActivity.this)
                            .setTitle("Please Selected Value")
                            .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    alertDialog.dismiss();
                                }
                            })
                            .show();
                }
                else {

                    Intent send = new Intent( Feedback_DetailsActivity.this, Feedback_RemarkActivity.class);
//                    send.putExtra("feedback_details",data);
                    send.putExtra("feedback_group",feedback_group);
                    send.putExtra("bd_details",bd_dta);
                    send.putExtra("job_id",job_id);
                    send.putExtra("status",status);
                    startActivity(send);
                }
            }
        });

        btn_prev.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                onBackPressed();
//                Intent send = new Intent(Feedback_DetailsActivity.this, Feedback_GroupActivity.class);
//                send.putExtra("feedback_group",feedback_group);
//                send.putExtra("bd_details",bd_dta);
//                send.putExtra("job_id",job_id);
             //   startActivity(send);
            }
        });

        iv_back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                onBackPressed();

//                Intent send = new Intent(Feedback_DetailsActivity.this, Feedback_GroupActivity.class);
//                send.putExtra("feedback_group",feedback_group);
//                send.putExtra("bd_details",bd_dta);
//                send.putExtra("job_id",job_id);
//                startActivity(send);
            }
        });

        etsearch.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
                String Searchvalue = etsearch.getText().toString();

                recyclerView.setVisibility(View.VISIBLE);
                txt_no_records.setVisibility(View.GONE);

                filter(Searchvalue);
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {

                String Searchvalue = etsearch.getText().toString();

                if(Searchvalue.equals("")){
                    recyclerView.setVisibility(View.VISIBLE);
                    img_clearsearch.setVisibility(View.INVISIBLE);
                  //  jobFindResponseCall(sstring);

                }
                else {
                    //   Log.w(TAG,"Search Value---"+Searchvalue);
                    filter(Searchvalue);
                }
            }
        });

        img_clearsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                etsearch.setText("");
                recyclerView.setVisibility(View.VISIBLE);
               // jobFindResponseCall(sstring);
                img_clearsearch.setVisibility(View.INVISIBLE);
            }
        });

        img_Pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DateFormat df = new SimpleDateFormat("EEE, d MMM yyyy, HH:mm");
                String date = df.format(Calendar.getInstance().getTime());

                alertDialog = new AlertDialog.Builder(context)
                        .setTitle("Are you sure to pause this job ?")
                        .setMessage(date)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(context,"Lat : " + Latitude + "Long : " + Logitude + "Add : " + address,Toast.LENGTH_LONG).show();
                                str_job_status = "Job Paused";
                                Job_status_update();
                                createLocalvalue();

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

    @SuppressLint("Range")
    private void getBDDetails() {

        Cursor curs = CommonUtil.dbUtil.getBDdetails(job_id,service_title, "1");
        Log.e("BD Count",""+curs.getCount());

        if (curs.getCount()>0 && curs.moveToLast()){

            str_BDDetails = curs.getString(curs.getColumnIndex(DbHelper.BD_DETAILS));
            Log.e("BD Data Get",""+str_BDDetails);
        }


    }

    private void getFeedbackGroup() {

        mydata = new ArrayList<>();

        Cursor cur = CommonUtil.dbUtil.getFeedbackgroup(job_id, service_title, "2");
        Log.e("Checklist get Data", "" + cur.getCount());

        if (cur.getCount() > 0 && cur.moveToFirst()) {

            do {
                @SuppressLint("Range")
                String abc = cur.getString(cur.getColumnIndex(DbHelper.FEEDBACK_GROUP));
                Log.e("Data Get", "" + abc);
                mydata.add(abc);

            } while (cur.moveToNext());

        }

        feedback_group = String.valueOf(mydata);
        Log.e("FeedBack Group",""+ feedback_group);
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

    private void filter(String s) {
        List<Feedback_DetailsResponse.DataBean> filteredlist = new ArrayList<>();
        for(Feedback_DetailsResponse.DataBean item : breedTypedataBeanList)
        {
            if(item.getTitle().toLowerCase().contains(s.toLowerCase()))
            {
                Log.w(TAG,"filter----"+item.getTitle().toLowerCase().contains(s.toLowerCase()));
                filteredlist.add(item);
            }
        }
        if(filteredlist.isEmpty())
        {
//            Toast.makeText(this,"No Data Found ... ",Toast.LENGTH_SHORT).show();
            recyclerView.setVisibility(View.GONE);
            txt_no_records.setVisibility(View.VISIBLE);
        }
        else
        {
            activityBasedListAdapter.filterList(filteredlist);
        }

    }

    private void jobFindResponseCall() {
        APIInterface apiInterface = RetrofitClient.getClient().create(APIInterface.class);
        Call<Feedback_DetailsResponse> call = apiInterface.Feedback_DetailsResponseCall(RestUtils.getContentType(), serviceRequest());
        Log.w(TAG, "Jobno Find Response url  :%s" + " " + call.request().url().toString());
        call.enqueue(new Callback<Feedback_DetailsResponse>() {
            @SuppressLint("LogNotTimber")
            @Override
            public void onResponse(@NonNull Call<Feedback_DetailsResponse> call, @NonNull Response<Feedback_DetailsResponse> response) {
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
            public void onFailure(@NonNull Call<Feedback_DetailsResponse> call, @NonNull Throwable t) {
                Log.e("On Failure ", "--->" + t.getMessage());
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private Feedback_DetailsRequest serviceRequest() {
        Log.e( "before ", feedback_group);

        feedback_group  = feedback_group.replaceAll("\n", "").replaceAll("","");
        Log.e( "after ", feedback_group);

        Feedback_DetailsRequest service = new Feedback_DetailsRequest();
        service.setCode_list(feedback_group);
        Log.e( "Group2 ", ""+ new Gson().toJson(service));

        Log.w(TAG, "Jobno Find Request " + new Gson().toJson(service));
        return service;
    }

    private void Job_status_update() {

        APIInterface apiInterface = RetrofitClient.getClient().create(APIInterface.class);
        Call<Job_status_updateResponse> call = apiInterface.job_status_updateResponseCall(com.triton.johnson_tap_app.utils.RestUtils.getContentType(), job_status_updateRequest());
        Log.w(VolleyLog.TAG,"SignupResponse url  :%s"+" "+ call.request().url().toString());

        call.enqueue(new Callback<Job_status_updateResponse>() {
            @SuppressLint("LogNotTimber")
            @Override
            public void onResponse(@NonNull Call<Job_status_updateResponse> call, @NonNull Response<Job_status_updateResponse> response) {

                Log.w(VolleyLog.TAG,"SignupResponse" + new Gson().toJson(response.body()));
                if (response.body() != null) {

                    message = response.body().getMessage();

                    if (200 == response.body().getCode()) {
                        if(response.body().getData() != null){

                            Log.d("msg",message);
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
        custom.setSMU_SCH_COMPNO(compno);
        custom.setSMU_SCH_SERTYPE(sertype);
        custom.setJOB_START_LONG(Logitude);
        custom.setJOB_START_LAT(Latitude);
        custom.setJOB_LOCATION(address);
        Log.e("CompNo",""+compno);
        Log.e("SertYpe", ""+sertype);
        Log.w(VolleyLog.TAG,"loginRequest "+ new Gson().toJson(custom));
        return custom;
    }

    private void createLocalvalue() {

        APIInterface apiInterface = RetrofitClient.getClient().create(APIInterface.class);
        Call<SuccessResponse> call = apiInterface.createLocalvalueBD(com.triton.johnson_tap_app.utils.RestUtils.getContentType(), createLocalRequest());
        Log.w(VolleyLog.TAG,"Create Local Value Response url  :%s"+" "+ call.request().url().toString());

        call.enqueue(new Callback<SuccessResponse>() {
            @Override
            public void onResponse(Call<SuccessResponse> call, Response<SuccessResponse> response) {

                Log.w(VolleyLog.TAG,"Create Local Value Response" + "" + new Gson().toJson(response.body()));

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

    private Breakdowm_Submit_Request createLocalRequest() {

        getFeedBackDesc();

        str_feedback_details  = str_feedback_details.replaceAll("\n", "").replaceAll("","");
        Log.e( "after ", str_feedback_details);

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm aa", Locale.getDefault());
        String currentDateandTime = sdf.format(new Date());

        Breakdowm_Submit_Request submitDailyRequest = new Breakdowm_Submit_Request();
        submitDailyRequest.setBd_details(str_BDDetails);
        //submitDailyRequest.setFeedback_details(sstring);
        submitDailyRequest.setFeedback_details(str_feedback_details);
        submitDailyRequest.setCode_list(feedback_group);
        submitDailyRequest.setFeedback_remark_text("");
        submitDailyRequest.setMr_status("");
        submitDailyRequest.setMr_1("");
        submitDailyRequest.setMr_2("");
        submitDailyRequest.setMr_3("");
        submitDailyRequest.setMr_4("");
        submitDailyRequest.setMr_5("");
        submitDailyRequest.setMr_6("");
        submitDailyRequest.setMr_7("");
        submitDailyRequest.setMr_8("");
        submitDailyRequest.setMr_9("");
        submitDailyRequest.setMr_10("");
        submitDailyRequest.setBreakdown_service("");
        submitDailyRequest.setTech_signature("");
        submitDailyRequest.setCustomer_name("");
        submitDailyRequest.setCustomer_number("");
        submitDailyRequest.setCustomer_acknowledgemnet("");
        submitDailyRequest.setDate_of_submission(currentDateandTime);
        submitDailyRequest.setUser_mobile_no(se_user_mobile_no);
        submitDailyRequest.setJob_id(job_id);
        submitDailyRequest.setSMU_SCH_COMPNO(compno);
        submitDailyRequest.setSMU_SCH_SERTYPE(sertype);
        Log.e("CompNo",""+compno);
        Log.e("SertYpe", ""+sertype);
        Log.w(TAG," Create Local Value Request"+ new Gson().toJson(submitDailyRequest));
        return submitDailyRequest;
    }

    private void setView(List<Feedback_DetailsResponse.DataBean> dataBeanList) {

        getFeedBackDesc();

        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        activityBasedListAdapter = new Feedback_DetailsAdapter(getApplicationContext(), dataBeanList, this,mydata);
        recyclerView.setAdapter(activityBasedListAdapter);
    }

    private void getFeedBackDesc() {

        Cursor cur = CommonUtil.dbUtil.getFeedbackDesc(job_id,service_title, "3");
        Log.e("Feedback Desc get Data",""+cur.getCount());
        mydata = new ArrayList<>();
        if(cur.getCount() >0 && cur.moveToFirst()){

            do{
                @SuppressLint("Range")
                String abc = cur.getString(cur.getColumnIndex(DbHelper.FEEDBACK_DESCRIPTION));
                Log.e("Data Get",""+abc);
                mydata.add(abc);
//                outputList = new ArrayList<String>();
//                for (String item : mydata) {
//                    //outputList.add("\""+item+"\"");
//                    outputList.add("" + item + "");
//                    outputList.remove("null");
//                }
            }while (cur.moveToNext());

        }
        str_feedback_details = String.valueOf(mydata);
    }

    public void userTypeSelectListener1(String usertype, String usertypevalue) {
        Title = usertype;

        Log.w(TAG, "myPetsSelectListener : " + "petList" + new Gson().toJson(breedTypedataBeanList));

        if (breedTypedataBeanList != null && breedTypedataBeanList.size() > 0) {
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
      //  super.onBackPressed();
        Intent intent = new Intent(context,Feedback_GroupActivity.class);
        intent.putExtra("status",status);
        startActivity(intent);

    }
}