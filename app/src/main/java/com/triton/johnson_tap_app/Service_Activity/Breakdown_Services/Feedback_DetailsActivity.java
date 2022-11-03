package com.triton.johnson_tap_app.Service_Activity.Breakdown_Services;

import static android.content.ContentValues.TAG;

import android.annotation.SuppressLint;
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

import com.google.gson.Gson;
import com.triton.johnson_tap_app.Db.CommonUtil;
import com.triton.johnson_tap_app.Db.DbHelper;
import com.triton.johnson_tap_app.Db.DbUtil;
import com.triton.johnson_tap_app.R;
import com.triton.johnson_tap_app.RestUtils;
import com.triton.johnson_tap_app.Service_Adapter.Feedback_DetailsAdapter;
import com.triton.johnson_tap_app.UserTypeSelectListener1;
import com.triton.johnson_tap_app.api.APIInterface;
import com.triton.johnson_tap_app.api.RetrofitClient;
import com.triton.johnson_tap_app.requestpojo.Feedback_DetailsRequest;
import com.triton.johnson_tap_app.responsepojo.Feedback_DetailsResponse;

import java.util.ArrayList;
import java.util.List;

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
    ImageView iv_back,img_clearsearch;
    String feedback_group ="", message, Title, petimage,str2=null,sstring="",bd_dta,job_id,str_feedback_details,service_title,feedbackGroup,pre_check,status;
    List<Feedback_DetailsResponse.DataBean> breedTypedataBeanList;
    Feedback_DetailsAdapter activityBasedListAdapter;
    AlertDialog alertDialog;
    SharedPreferences sharedPreferences;
    Context context;
    ArrayList<String> mydata = new ArrayList<>();
    ArrayList<String> outputList = null;

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

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        job_id = sharedPreferences.getString("job_id", "default value");
        service_title = sharedPreferences.getString("service_title", "default value");
        feedback_group = sharedPreferences.getString("feedbackgroup","FG0003");
        String name = sharedPreferences.getString("Hi","");
        Log.e("Hi Nish",""+name);
        Log.e("JobID",""+job_id);
        Log.e("Name",""+service_title);
        Log.e("Feedback Group",feedback_group);

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


      // getFeedbackgroup();

        jobFindResponseCall();

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

                Cursor cur = CommonUtil.dbUtil.getFeedbackDesc(job_id,service_title, "3");
                Log.e("FeedBack DEsc get",""+cur.getCount());
                mydata = new ArrayList<>();
                if(cur.getCount() >0 && cur.moveToFirst()){

                    do{
                        @SuppressLint("Range")
                        String abc = cur.getString(cur.getColumnIndex(DbHelper.FEEDBACK_DESCRIPTION));
                        Log.e("Datas",""+abc);
                        mydata.add(abc);
                    }while (cur.moveToNext());

                } else{
                    Log.e("Datasss",""+cur);

                }

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
                if(cur.getCount() ==0) {
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
            Toast.makeText(this,"No Data Found ... ",Toast.LENGTH_SHORT).show();
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

    private void setView(List<Feedback_DetailsResponse.DataBean> dataBeanList) {



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

        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        activityBasedListAdapter = new Feedback_DetailsAdapter(getApplicationContext(), dataBeanList, this,mydata);
        recyclerView.setAdapter(activityBasedListAdapter);
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