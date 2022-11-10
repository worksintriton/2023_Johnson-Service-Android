package com.triton.johnson_tap_app.Service_Activity.SiteAudit;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.triton.johnson_tap_app.GetFieldListResponse;
import com.triton.johnson_tap_app.R;
import com.triton.johnson_tap_app.ReAdapter;
import com.triton.johnson_tap_app.RestUtils;
import com.triton.johnson_tap_app.Service_Activity.PreventiveMRApproval.TechnicianSignature_PreventiveMRActivity;
import com.triton.johnson_tap_app.Service_Activity.Preventive_Services.Job_Details_PreventiveActivity;
import com.triton.johnson_tap_app.Service_Activity.Preventive_Services.Material_Request_PreventiveActivity;
import com.triton.johnson_tap_app.Service_Activity.Preventive_Services.Quarterly_Top_PitActivity;
import com.triton.johnson_tap_app.api.APIInterface;
import com.triton.johnson_tap_app.api.RetrofitClient;
import com.triton.johnson_tap_app.interfaces.GetNumberListener;
import com.triton.johnson_tap_app.interfaces.GetSpinnerListener;
import com.triton.johnson_tap_app.interfaces.GetStringListener;
import com.triton.johnson_tap_app.interfaces.GetStringRemarksListener;
import com.triton.johnson_tap_app.requestpojo.GetFieldListRequest;
import com.triton.johnson_tap_app.utils.ConnectionDetector;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AuditChecklist extends AppCompatActivity implements GetSpinnerListener, GetStringListener, GetNumberListener, GetStringRemarksListener {

    private RecyclerView recyclerView;
    private  String TAG = "Audit Checklist Adapter";
    List<GetFieldListResponse.DataBean> dataBeanList;
    AuditAdapter activityBasedListAdapter;
    private int currentPage = 0;
    String Data_values;
    Button btn_prev;
    Button btn_next;
    Button btn_success;
    Button btn_complete;
    Button btn_pending;
    Button btn_clear;
    public int TOTAL_NUM_ITEMS;
    public int ITEMS_PER_PAGE = 6;
    public int ITEMS_REMAINING;
    public int LAST_PAGE = TOTAL_NUM_ITEMS / ITEMS_PER_PAGE;
    String Result1,material_value;
    private int totalPages;
    Dialog dialog;
    String networkStatus = "";
    String string_value, message, service_id, activity_id, job_id, group_id, status, job_detail_no,osacompno,se_user_mobile_no;
    String s1,_id,value,service_title,data,data1,data2,data3,data4,data5,field_value,field_name,field_comments,field_cat_id,field_group_id,field_remarks,str,str1,str2,str3,str4,str5,service_type;
    private Dialog alertDialog;
    ImageView iv_back;
    LinearLayout footerView;
    String List;
    SharedPreferences sharedPreferences;
    Context context;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_recycler_spinner);
        context = this;

        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        btn_prev = (Button) findViewById(R.id.btn_prev);
        btn_next = (Button) findViewById(R.id.btn_next);
        btn_success = (Button) findViewById(R.id.btn_success);
        btn_complete = (Button) findViewById(R.id.btn_complete);
        btn_pending = (Button) findViewById(R.id.btn_pending);
        btn_clear = (Button) findViewById(R.id.btn_clear);
        iv_back = (ImageView) findViewById(R.id.iv_back);
        footerView = (LinearLayout) findViewById(R.id.footerView);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            job_id = extras.getString("job_id");
        }

        if (extras != null) {
            value = extras.getString("value");
        }

        if (extras != null) {
            service_title = extras.getString("service_title");
            status = extras.getString("status");
            Log.e("Status", "" + status);
//            service_type = extras.getString("service_type");
//            Log.e("Service Type", "" + service_type);
        }

      sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        job_id =sharedPreferences.getString("jobid","L-1234");
//        List = sharedPreferences.getString("List","1");
        se_user_mobile_no = sharedPreferences.getString("user_mobile_no", "default value");
        service_title = sharedPreferences.getString("service_title", "Services");
        osacompno = sharedPreferences.getString("osacompno","ADT2020202020");
        service_type = sharedPreferences.getString("service_type","value");
        Log.e("Service Type", "" + service_type);

        Log.e("Name", "" + service_title);
        Log.e("Mobile", ""+ se_user_mobile_no);
        Log.e("Jobid", "" +job_id);
        Log.e("osocompno",""+ osacompno);


//        Intent intent = getIntent();
//        Bundle args = intent.getBundleExtra("BUNDLE");
//        ArrayList<String > object = (ArrayList<String>) args.getSerializable("ARRAYLIST");


     //   Log.e("List New", "" +List);

        jobFindResponseCall();
        networkStatus = ConnectionDetector.getConnectivityStatusString(getApplicationContext());

        iv_back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                onBackPressed();
//                    Intent send = new Intent(AuditChecklist.this, Quarterly_Top_PitActivity.class);
//                    startActivity(send);
            }
        });


        btn_next.setOnClickListener(new View.OnClickListener() {
            @SuppressLint({"ResourceAsColor", "SetTextI18n"})
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {

                boolean flag = true;


                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        recyclerView.smoothScrollToPosition(0);
                    }
                }, 50);

                //Log.w(TAG, "btnnext currentPage : " + currentPage);
                int currentpagesize = currentPage;
                //Log.w(TAG, "btnnext totalPages  : " + totalPages+" TOTAL_NUM_ITEMS : "+TOTAL_NUM_ITEMS+" currentpagesize : "+currentpagesize);
                List<GetFieldListResponse.DataBean> dataBeanListS = new ArrayList<>();
                int startItem = currentPage * ITEMS_PER_PAGE;
                //Log.w(TAG, "btnnext startItem : "  + startItem);

                String Result = "";
                if (currentPage == 0) {
                    for (int i = 0; i < 6; i++) {

                        Result = dataBeanList.get(i).getField_value().toString();

                        Log.e("Resuktt", Result);


                        if (dataBeanList.get(i).getField_value().isEmpty() || dataBeanList.get(i).getField_value().equalsIgnoreCase("Select")) {
                            if (dataBeanList.get(i).getField_type() != null && dataBeanList.get(i).getField_type().equalsIgnoreCase("Lift")) {
                                dataBeanList.get(i).setField_value("LIFT");
                            }/*else if(dataBeanList.get(i).getField_type() !=  null && dataBeanList.get(i).getField_type().equalsIgnoreCase("File upload")){
                                dataBeanList.get(i).setField_value("File upload");
                            }*/
                            flag = false;
                        }
                    }


                    //joinInspectionCreateRequestCall();

                } else {
                    int enditem = (currentPage + 1) * ITEMS_PER_PAGE;
                    Log.w(TAG, "currentPage else currentPage : " + currentPage + " startItem : " + startItem + " enditem : " + enditem + " ITEMS_PER_PAGE : " + ITEMS_PER_PAGE);

                    Log.w(TAG, "btnnext enditem : " + enditem);
                    for (int i = startItem; i < enditem; i++) {
                        Result1 = dataBeanList.get(i).getField_value().toString();
                        Log.e("Resuktt", Result1);
                        Log.w(TAG, "loop fieldvalue : " + dataBeanList.get(i).getField_value() + " i : " + i);
                        if (dataBeanList.get(i).getField_value().isEmpty() || dataBeanList.get(i).getField_value().equalsIgnoreCase("Select")) {
                            if (dataBeanList.get(i).getField_type() != null && dataBeanList.get(i).getField_type().equalsIgnoreCase("Lift")) {
                                Log.w(TAG, "index---- : " + i + " endvaleue " + (enditem - 1));
                                dataBeanList.get(i).setField_value("LIFT");
                            }/*else if(dataBeanList.get(i).getField_type() !=  null && dataBeanList.get(i).getField_type().equalsIgnoreCase("File upload")){
                                dataBeanList.get(i).setField_value("File upload");
                            }*/
                            flag = false;
                        }
                        Log.w(TAG, "index : " + i + " endvaleue " + (enditem - 1));

//                        if (Objects.equals(Result1, "Select")){
//
//                            showErrorLoading();
//                        }



                    }


                }
                //Log.w(TAG, "btnnext flag : " + flag);

                if (flag) {
                    currentPage += 1;
                    startItem = currentPage * ITEMS_PER_PAGE;
                    Log.w(TAG, "currentPage flag : " + currentPage + " startItem : " + startItem + " ITEMS_PER_PAGE : " + ITEMS_PER_PAGE);
                }

                int condition = 0;

                ITEMS_REMAINING = ITEMS_REMAINING - ITEMS_PER_PAGE;

                Log.w(TAG, "btnnext ITEMS_REMAINING : " + ITEMS_REMAINING);
                Log.w(TAG, "btnnext TOTAL_NUM_ITEMS : " + TOTAL_NUM_ITEMS);

                double LAST_PAGE = ((double) TOTAL_NUM_ITEMS / ITEMS_PER_PAGE);

                Log.w(TAG, "btnnext LAST_PAGE : " + LAST_PAGE + " currentPage : " + currentPage);

                if (currentPage == LAST_PAGE - 1) {
                    Log.w(TAG, "btnnext if condition----->");
                    Log.w(TAG, "btnnext if ITEMS_REMAINING----->" + ITEMS_REMAINING);

                    if (ITEMS_REMAINING == 0) {
                        condition = startItem + ITEMS_PER_PAGE;
                        Log.w(TAG, "btnnext if condition----->" + condition);
                    } else {
                        condition = startItem + ITEMS_REMAINING;
                        Log.w(TAG, "btnnext if else condition----->" + condition);

                    }
                    Log.w(TAG, "btnnext startItem----->" + startItem + " condition -->" + condition);


                    for (int i = startItem; i < dataBeanList.size(); i++) {
                        dataBeanListS.add(dataBeanList.get(i));


                    }


                    setView(dataBeanListS, ITEMS_PER_PAGE, TOTAL_NUM_ITEMS);
                    Log.w(TAG, "btnnext  setView  ITEMS_PER_PAGE : " + ITEMS_PER_PAGE + " TOTAL_NUM_ITEMS : " + TOTAL_NUM_ITEMS + " dataBeanListS : " + new Gson().toJson(dataBeanListS));

                    // enableDisableButtons();
                    // rv.setAdapter(new MyAdapter(MainActivity.this, p.generatePage(currentPage)));
                    btn_next.setEnabled(false);

                    btn_prev.setBackgroundResource(R.drawable.blue_button_background_with_radius);
                    btn_prev.setTextColor(getResources().getColor(R.color.white));
                    btn_prev.setEnabled(true);
                    btn_next.setVisibility(View.GONE);
                    btn_success.setVisibility(View.VISIBLE);

                } else {
                    if (flag) {
                        Log.w(TAG, "btnnext else condition----->");
                        condition = startItem + ITEMS_PER_PAGE;
                        Log.w(TAG, "btnnext  else startItem : " + startItem + " condition : " + condition + "size : " + dataBeanList.size());

                        for (int i = startItem; i < dataBeanList.size(); i++) {
                            Log.w(TAG, "dataBeanList.get(i) : " + dataBeanList.get(i));
                            dataBeanListS.add(dataBeanList.get(i));

                        }

                        Log.w(TAG, "btnnext else dataBeanList" + new Gson().toJson(dataBeanListS));
                        setView(dataBeanListS, ITEMS_PER_PAGE, TOTAL_NUM_ITEMS);
                        Log.w(TAG, "btnnext else setView " + " ITEMS_PER_PAGE : " + ITEMS_PER_PAGE + " TOTAL_NUM_ITEMS : " + TOTAL_NUM_ITEMS + " dataBeanListS :  " + new Gson().toJson(dataBeanListS));
                        toggleButtons();
                    } else {

                        showErrorLoading();
/*
                        Toast toast = Toast.makeText(getApplicationContext(), "please enter all required data", Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.CENTER, 0, 0);
                        if(toast.getView() != null){
                            toast.getView().setBackgroundTintList(ColorStateList.valueOf(R.color.warning));
                            toast.show();
                        }
                        toast.show();*/


                    }

                }


            }
        });

        btn_prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn_complete.setVisibility(View.GONE);
                btn_pending.setVisibility(View.GONE);

                recyclerView.scrollToPosition(0);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        recyclerView.smoothScrollToPosition(0);
                    }
                }, 50);
                currentPage -= 1;
                List<GetFieldListResponse.DataBean> dataBeanListS = new ArrayList<>();
                int startItem = currentPage * ITEMS_PER_PAGE;
                int condition;
                int ITEMS_REMAINING = TOTAL_NUM_ITEMS % ITEMS_PER_PAGE;
                int LAST_PAGE = TOTAL_NUM_ITEMS / ITEMS_PER_PAGE;
                if (currentPage == 0 || (currentPage >= 1 && currentPage <= totalPages)) {
                    condition = startItem + ITEMS_PER_PAGE;
                } else {
                    condition = startItem + ITEMS_REMAINING;
                }
                for (int i = startItem; i < condition; i++) {
                    Log.w(TAG, "generatePage: dataBeanList" + new Gson().toJson(dataBeanList.get(i)));

                    dataBeanListS.add(dataBeanList.get(i));

                }


                Log.w(TAG, "btnprev dataBeanList" + new Gson().toJson(dataBeanList));

                Log.w(TAG, "btnprev  setView " + " ITEMS_PER_PAGE : " + ITEMS_PER_PAGE + " TOTAL_NUM_ITEMS : " + TOTAL_NUM_ITEMS + " dataBeanListS : " + new Gson().toJson(dataBeanListS));

                setView(dataBeanListS, ITEMS_PER_PAGE, TOTAL_NUM_ITEMS);
                // rv.setAdapter(new MyAdapter(MainActivity.this, p.generatePage(currentPage)));

                toggleButtons();
            }
        });

        btn_success.setOnClickListener(new View.OnClickListener() {

            @SuppressLint({"NewApi", "ResourceAsColor"})
            @Override
            public void onClick(View v) {
                Log.w(TAG, "inside");


                if (networkStatus.equalsIgnoreCase("Not connected to Internet")) {

                    Toasty.warning(getApplicationContext(), "No Internet", Toasty.LENGTH_LONG).show();

                } else {

                    boolean flag = true;
                    for (int i = 0; i < dataBeanList.size(); i++) {
                        Log.w(TAG, "loop fieldvalue : " + dataBeanList.get(i).getField_value() + " i : " + i);
                        if (dataBeanList.get(i).getField_value().isEmpty() || dataBeanList.get(i).getField_value().equalsIgnoreCase("Select Value")) {
                            if (dataBeanList.get(i).getField_type() != null && dataBeanList.get(i).getField_type().equalsIgnoreCase("Lift")) {
                                dataBeanList.get(i).setField_value("LIFT");
                            }/*else if(dataBeanList.get(i).getField_type() !=  null && dataBeanList.get(i).getField_type().equalsIgnoreCase("File upload")){
                                dataBeanList.get(i).setField_value("File upload");
                            }*/
                            flag = false;
                        }


                    }

                    Log.w(TAG, "flag " + flag);

                    if (flag) {

                        for (int i = 0; i < dataBeanList.size(); i++) {

                            data = data + "," + dataBeanList.get(i).getField_value().toString();
                            data1 = data1 + "," + dataBeanList.get(i).getField_name().toString();
                            data2 = data2 + "," + dataBeanList.get(i).getField_comments().toString();
                            data3 = data3 + "," + dataBeanList.get(i).getCat_id().toString();
                            data4 = data4 + "," + dataBeanList.get(i).getGroup_id().toString();
                            data5 = data5 + "," + dataBeanList.get(i).getField_remarks().toString();

                            field_value = data.replace("null","");
                            field_name = data1.replace("null","");
                            field_comments = data2.replace("null","");
                            field_cat_id = data3.replace("null","");
                            field_group_id = data4.replace("null","");
                            field_group_id = data4.replace("null","");
                            field_remarks = data5.replace("null","");

                            str = field_value;
                            str1 = field_name;
                            str2 = field_comments;
                            str3 = field_cat_id;
                            str4 = field_group_id;
                            str5 = field_remarks;

                            str = str.substring(1);
                            str1 = str1.substring(1);
                            str2 = str2.substring(1);
                            str3 = str3.substring(1);
                            str4 = str4.substring(1);
                            str5 = str5.substring(1);

                            Log.e("Form1_comments",str2);
                            Log.e("Remarks",str5);

                          //  sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString("Form1_value",str);
                            editor.putString("Form1_name",str1);
                            editor.putString("Form1_comments",str2);
                            editor.putString("Form1_cat_id",str3);
                            editor.putString("Form1_group_id",str4);
                            editor.putString("Field_remarks",str5);
                            editor.apply();


                            dialog = new Dialog(context, R.style.NewProgressDialog);
                            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                            dialog.setContentView(R.layout.progroess_popup);
                            dialog.show();

                            Intent send = new Intent(AuditChecklist.this, MaterialRequest_AuditActivity.class);
                            send.putExtra("job_id",job_id);
//                            send.putExtra("value",value);
//                            send.putExtra("service_title",service_title);
//                            send.putExtra("Form1_value",str);
//                            send.putExtra("Form1_name",str1);
//                            send.putExtra("Form1_comments",str2);
//                            send.putExtra("Form1_cat_id",str3);
//                            send.putExtra("Form1_group_id",str4);
                            send.putExtra("status", status);
                            startActivity(send);
                            dialog.dismiss();
                        }

                    } else {

                        Toast toast = Toast.makeText(getApplicationContext(), "please enter all required data", Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.CENTER, 0, 0);
                        // toast.getView().setBackgroundTintList(ColorStateList.valueOf(R.color.warning));
                        toast.show();
                    }


                }

            }
        });
    }

    private void toggleButtons() {
        if (currentPage == 0) {
            btn_next.setBackgroundResource(R.drawable.blue_button_background_with_radius);
            btn_next.setTextColor(getResources().getColor(R.color.white));
            btn_success.setVisibility(View.GONE);
            btn_next.setVisibility(View.VISIBLE);
            btn_next.setEnabled(true);
            btn_prev.setEnabled(false);
            btn_prev.setBackgroundResource(R.drawable.edit_background_with_border);
            btn_prev.setTextColor(getResources().getColor(R.color.colorPrimary));
        }
        else if (currentPage == totalPages) {
            btn_prev.setBackgroundResource(R.drawable.blue_button_background_with_radius);
            btn_prev.setTextColor(getResources().getColor(R.color.white));
            btn_prev.setEnabled(true);
            btn_next.setVisibility(View.GONE);
            btn_success.setVisibility(View.VISIBLE);
        }
        else if (currentPage >= 1 && currentPage <= totalPages) {
            btn_next.setBackgroundResource(R.drawable.blue_button_background_with_radius);
            btn_next.setTextColor(getResources().getColor(R.color.white));
            btn_prev.setEnabled(true);
            btn_next.setEnabled(true);
            btn_success.setVisibility(View.GONE);
            btn_next.setVisibility(View.VISIBLE);
            btn_prev.setBackgroundResource(R.drawable.blue_button_background_with_radius);
            btn_prev.setTextColor(getResources().getColor(R.color.white));
        }
    }

    private void jobFindResponseCall() {
        APIInterface apiInterface = RetrofitClient.getClient().create(APIInterface.class);
        Call<GetFieldListResponse> call = apiInterface.Preventive_ChecklistAuditResponseCall(RestUtils.getContentType(), getFieldListRequest());
        Log.w(TAG, "Jobno Find Response url  :%s" + " " + call.request().url().toString());
        call.enqueue(new Callback<GetFieldListResponse>() {
            @SuppressLint("LogNotTimber")
            @Override
            public void onResponse(@NonNull Call<GetFieldListResponse> call, @NonNull Response<GetFieldListResponse> response) {
                Log.w(TAG, "Jobno Find Response" + new Gson().toJson(response.body()));

                if (response.body() != null) {

                  String  message = response.body().getMessage();
                    Log.d("message", message);

                    if (200 == response.body().getCode()) {
                        if (response.body().getData() != null) {
                            dataBeanList = response.body().getData();

                            footerView.setVisibility(View.VISIBLE);
                            if (dataBeanList.size() < 6 || dataBeanList.size() == 6) {
                                btn_prev.setVisibility(View.INVISIBLE);
                                btn_next.setVisibility(View.GONE);
                                btn_success.setVisibility(View.VISIBLE);
                            }

                            totalPages = dataBeanList.size() / 6;
                            TOTAL_NUM_ITEMS = dataBeanList.size();
                            Log.w(TAG, "totalPages  : " + totalPages + " TOTAL_NUM_ITEMS : " + TOTAL_NUM_ITEMS);

                            ITEMS_REMAINING = TOTAL_NUM_ITEMS - ITEMS_PER_PAGE;

                            Log.w(TAG, " getfieldListResponseCall  setView  ITEMS_PER_PAGE : " + ITEMS_PER_PAGE + " TOTAL_NUM_ITEMS : " + TOTAL_NUM_ITEMS + " dataBeanList : " + new Gson().toJson(dataBeanList));

                            setView(dataBeanList, ITEMS_PER_PAGE, TOTAL_NUM_ITEMS);

//                            if (dataBeanList != null && dataBeanList.size() > 0) {
//                                footerView.setVisibility(View.VISIBLE);
//                                if (dataBeanList.size() < 6 || dataBeanList.size() == 6) {
//                                    btn_prev.setVisibility(View.INVISIBLE);
//                                    btn_next.setVisibility(View.GONE);
//                                    btn_success.setVisibility(View.VISIBLE);
//                                }
//
//                                totalPages = dataBeanList.size() / 6;
//                                TOTAL_NUM_ITEMS = dataBeanList.size();
//                                Log.w(TAG, "totalPages  : " + totalPages + " TOTAL_NUM_ITEMS : " + TOTAL_NUM_ITEMS);
//
//                                ITEMS_REMAINING = TOTAL_NUM_ITEMS - ITEMS_PER_PAGE;
//
//                                Log.w(TAG, " getfieldListResponseCall  setView  ITEMS_PER_PAGE : " + ITEMS_PER_PAGE + " TOTAL_NUM_ITEMS : " + TOTAL_NUM_ITEMS + " dataBeanList : " + new Gson().toJson(dataBeanList));
//
//
//                                setView(dataBeanList, ITEMS_PER_PAGE, TOTAL_NUM_ITEMS);
//
//                               // txt_no_records.setVisibility(View.GONE);
//                            } else {
//                                footerView.setVisibility(View.GONE);
////                                txt_no_records.setVisibility(View.VISIBLE);
////                                txt_no_records.setText("No Input Fields Available");
//                            }

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
            public void onFailure(@NonNull Call<GetFieldListResponse> call, @NonNull Throwable t) {
                Log.e("Jobno Find ", "--->" + t.getMessage());
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

        private GetFieldListRequest getFieldListRequest() {
        GetFieldListRequest getFieldListRequest = new GetFieldListRequest();
//        getFieldListRequest.setJob_id(job_id);
//        getFieldListRequest.setJob_status_type(value);
//        getFieldListRequest.setJob_date(List);
            getFieldListRequest.setJob_id(job_id);
            getFieldListRequest.setService_type(service_type);
        Log.w(TAG, "GetFieldListRequest " + new Gson().toJson(getFieldListRequest));
        return getFieldListRequest;
    }

    private void setView(List<GetFieldListResponse.DataBean> dataBeanList, int ITEMS_PER_PAGE, int TOTAL_NUM_ITEMS) {

        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        activityBasedListAdapter = new AuditAdapter(getApplicationContext(), dataBeanList, ITEMS_PER_PAGE, TOTAL_NUM_ITEMS,currentPage,this,this,this,this);
        recyclerView.setAdapter(activityBasedListAdapter);
    }

    @Override
    public void getSpinnerListener(Spinner spr_dropdown, int positions, String sprvalue, String field_length) {
        dataBeanList.get(positions).setField_value(sprvalue);
       // Toast.makeText(context, sprvalue.toString(), Toast.LENGTH_SHORT).show();
    }

    public void getStringListener(EditText edt_string, String s, int position, String field_length) {
        dataBeanList.get(position).setField_value(s);

    }

    public void getNumberListener(EditText edt_number, String s, int position, String field_length) {
        Log.w(TAG, "getNumberListener : " + "s : " + s + " position : " + position + " field_length : " + field_length);
        dataBeanList.get(position).setField_value(s);
    }


    public void showErrorLoading() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("please enter all required data");
        alertDialogBuilder.setPositiveButton("ok",
                (arg0, arg1) -> hideLoading());
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    public void hideLoading() {
        try {
            alertDialog.dismiss();
        } catch (Exception ignored) {

        }

    }

    @Override
    public void getStringRemarksListener(EditText edt_Remarks, String s, int position, String field_length) {
        Log.e("Remarks",""+ s);
        dataBeanList.get(position).setField_remarks(s);

         //Toast.makeText(context, s.toString(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {
       // super.onBackPressed();

        Intent send = new Intent(context, Checklist_AuditActivity.class);
        send.putExtra("status", status);
        startActivity(send);
    }
}