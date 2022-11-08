package com.triton.johnson_tap_app.Service_Activity.SiteAudit;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.triton.johnson_tap_app.Db.CommonUtil;
import com.triton.johnson_tap_app.Db.DbHelper;
import com.triton.johnson_tap_app.Db.DbUtil;
import com.triton.johnson_tap_app.R;
import com.triton.johnson_tap_app.Service_Activity.BreakdownMRApprovel.BreakdownMRListtwo_Activity;
import com.triton.johnson_tap_app.Service_Activity.BreakdownMRApprovel.BreakdownMR_Activity;
import com.triton.johnson_tap_app.Service_Activity.BreakdownMRApprovel.MRForms_BreakdownMRActivity;
import com.triton.johnson_tap_app.Service_Activity.BreakdownMRApprovel.TechnicianSignature_BreakdownMR_Activity;
import com.triton.johnson_tap_app.Service_Activity.ServicesActivity;
import com.triton.johnson_tap_app.Service_Adapter.BreakdownMRListOne_Adapter;
import com.triton.johnson_tap_app.materialeditext.MaterialEditText;

import java.util.ArrayList;

public class AuditMR_Activity extends AppCompatActivity {

    private String TAG ="MRListONE";

    MaterialEditText partnameMaterialEdittext, partnoMaterialEdittext, quantityMaterialEdittext;
    ImageButton imgbtnSearch;
    RecyclerView recyclerView;
    ImageView iv_back,img_clearsearch,iv_pause;
    Button addButton, submitButton, prevButton;
    String se_user_mobile_no, se_user_name, se_id,check_id, service_title,job_id,osacompno;
    String strPartname, strPartno,strPartid, strQuantity,status,str_mr1 ="",str_mr2="",str_mr3="",str_mr4="",str_mr5="",str_mr6="",str_mr7="",str_mr8="",str_mr9="",str_mr10="";
    private String PetBreedType = "";
    Context context;
    AlertDialog.Builder builder;
    AlertDialog alertDialog;

    ArrayList<String> arli_Partname;
    ArrayList<String> arli_Partno;
    ArrayList<String> arli_Partid;
    ArrayList<String> arli_Quantity;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_breakdown_mrlist_one);

        context = this;
        CommonUtil.dbUtil = new DbUtil(context);
        CommonUtil.dbUtil.open();
        CommonUtil.dbHelper = new DbHelper(context);

        partnameMaterialEdittext = findViewById(R.id.part_name);
        partnoMaterialEdittext = findViewById(R.id.part_no);
        quantityMaterialEdittext = findViewById(R.id.no);
        imgbtnSearch = findViewById(R.id.imgbtn_search);
        iv_back = findViewById(R.id.iv_back);
        addButton = findViewById(R.id.btn_add);
        submitButton = findViewById(R.id.btn_sumbit);
        prevButton = findViewById(R.id.btn_show);
        recyclerView = findViewById(R.id.recyclerView);
        iv_pause = findViewById(R.id.ic_paused);


        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        se_id = sharedPreferences.getString("_id", "default value");
        se_user_mobile_no = sharedPreferences.getString("user_mobile_no", "default value");
        se_user_name = sharedPreferences.getString("user_name", "default value");
        service_title = sharedPreferences.getString("service_title", "Services");
        job_id =sharedPreferences.getString("jobid","L-1234");
        osacompno = sharedPreferences.getString("osacompno","ADT2020202020");

        Log.e("Name", "" + service_title);
        Log.e("Mobile", ""+ se_user_mobile_no);
        Log.e("Jobid",""+ job_id);
        Log.e("osocompno",""+ osacompno);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            service_title = extras.getString("service_title");
            status = extras.getString("status");
            Log.e("Status", "" + status);
            // Log.d("title",service_title);
        }
        if (extras != null) {
            //job_id = extras.getString("job_id");
            strPartname = extras.getString("partname");
            strPartno = extras.getString("partno");
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

            partnameMaterialEdittext.setText(strPartname);
            partnoMaterialEdittext.setText(strPartno);
            partnameMaterialEdittext.setFocusableInTouchMode(false);
            partnoMaterialEdittext.setFocusableInTouchMode(false);
            quantityMaterialEdittext.setFocusableInTouchMode(true);

            Log.e("JobId","" + job_id );
        }

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("service_title", service_title);
        editor.apply();

        if (status.equals("pause")){

            Log.e("Inside", "Paused Job");

            addMrData(job_id);


            addButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Cursor cursor = CommonUtil.dbUtil.getMR(job_id);

                    Log.e("MR Count",""+ cursor.getCount());

                    String strQuantity = quantityMaterialEdittext.getText().toString();



                    Log.e("MR List Data","" + strQuantity + " " + strPartname + " " + strPartno );

                    if (strQuantity.equals("")){
                        quantityMaterialEdittext.setError("Please Enter the Quantity");
                    } else if(strPartname.equals("")){
                        partnameMaterialEdittext.setError("Please Select the Part Name");
                    }else if(strPartno.equals("")){
                        partnoMaterialEdittext.setError("Please Select the Part Name");
                    }
                    else {

                        Cursor cur = CommonUtil.dbUtil.getMR(job_id);

                        Log.e("Count", ""+ cur.getCount());

                        if (cur.getCount()>9){
                            Toast.makeText(AuditMR_Activity.this,"Only 10 Items to be Addes", Toast.LENGTH_SHORT).show();

                        }
                        else {
                            CommonUtil.dbUtil.addMR(strPartname, strPartno, strQuantity,job_id);
                            Toast.makeText(AuditMR_Activity.this,"Successfully Added", Toast.LENGTH_SHORT).show();
                            addMrData(job_id);
                            partnameMaterialEdittext.setText("");
                            partnoMaterialEdittext.setText("");
                            quantityMaterialEdittext.setText("");
                        }

//                    Intent send = new Intent(BreakdownMRListOne_Activity.this, BreakdownMRListOne_Activity.class);
//                    startActivity(send);

                    }

                }
            });

            iv_pause.setVisibility(View.GONE);

            iv_pause.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {



                    builder = new AlertDialog.Builder(AuditMR_Activity.this);

                    builder.setMessage("Are You sure want to pause this job ?")
                            .setCancelable(false)
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {

                                    Intent send = new Intent(AuditMR_Activity.this, ServicesActivity.class);
                                    send.putExtra("status", status);
                                    send.putExtra("service_title",service_title);
                                    send.putExtra("job_id",job_id);
                                    startActivity(send);

                                }
                            })
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            });

                    AlertDialog alert = builder.create();
                    //Setting the title manually
                    alert.show();

                }
            });
        }
        else{
            Log.e("Inside", "New Job ");

            addMrData(job_id);

            addButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Cursor cursor = CommonUtil.dbUtil.getMR(job_id);

                    Log.e("MR Count",""+ cursor.getCount());

                    String strQuantity = quantityMaterialEdittext.getText().toString();
                    strPartname = partnameMaterialEdittext.getText().toString();
                    strPartno  = partnoMaterialEdittext.getText().toString();


                    Log.e("MR List Data","" + strQuantity + " " + strPartname + " " + strPartno );

                    if (strQuantity.equals("")){
                        quantityMaterialEdittext.setError("Please Enter the Quantity");
                    } else if(strPartname.equals("")){
                        partnameMaterialEdittext.setError("Please Select the Part Name");
                    }else if(strPartno.equals("")){
                        partnoMaterialEdittext.setError("Please Select the Part Name");
                    }
                    else {

                        Cursor cur = CommonUtil.dbUtil.getMR(job_id);

                        Log.e("Count", ""+ cur.getCount());

                        if (cur.getCount()>9){
                            Toast.makeText(AuditMR_Activity.this,"Only 10 Items to be Addes", Toast.LENGTH_SHORT).show();

                        }
                        else {
                            CommonUtil.dbUtil.addMR(strPartname, strPartno, strQuantity,job_id);
                            Toast.makeText(AuditMR_Activity.this,"Successfully Added", Toast.LENGTH_SHORT).show();
                            addMrData(job_id);
                            partnameMaterialEdittext.setText("");
                            partnoMaterialEdittext.setText("");
                            quantityMaterialEdittext.setText("");
                        }

//                    Intent send = new Intent(BreakdownMRListOne_Activity.this, BreakdownMRListOne_Activity.class);
//                    startActivity(send);

                    }

                }
            });
        }

        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onBackPressed();

//                Intent send = new Intent(AuditMR_Activity.this, MRForms_BreakdownMRActivity.class);
//                send.putExtra("service_title",service_title);
//                send.putExtra("job_id",job_id);
//                send.putExtra("mr1", str_mr1);
//                send.putExtra("mr2", str_mr2);
//                send.putExtra("mr3", str_mr3);
//                send.putExtra("mr4", str_mr4);
//                send.putExtra("mr5", str_mr5);
//                send.putExtra("mr6", str_mr6);
//                send.putExtra("mr7", str_mr7);
//                send.putExtra("mr8", str_mr8);
//                send.putExtra("mr9", str_mr9);
//                send.putExtra("mr10", str_mr10);
//                send.putExtra("status", status);
//                startActivity(send);

            }
        });

        prevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                onBackPressed();
//                Intent send = new Intent(AuditMR_Activity.this, MRForms_BreakdownMRActivity.class);
//                send.putExtra("service_title",service_title);
//                send.putExtra("job_id",job_id);
//                send.putExtra("mr1", str_mr1);
//                send.putExtra("mr2", str_mr2);
//                send.putExtra("mr3", str_mr3);
//                send.putExtra("mr4", str_mr4);
//                send.putExtra("mr5", str_mr5);
//                send.putExtra("mr6", str_mr6);
//                send.putExtra("mr7", str_mr7);
//                send.putExtra("mr8", str_mr8);
//                send.putExtra("mr9", str_mr9);
//                send.putExtra("mr10", str_mr10);
//                send.putExtra("status", status);
//                startActivity(send);
            }
        });

        imgbtnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent send = new Intent(AuditMR_Activity.this, AuditMRList_Activity.class);
//                send.putExtra("service_title",service_title);
//                send.putExtra("job_id",job_id);
//                send.putExtra("mr1", str_mr1);
//                send.putExtra("mr2", str_mr2);
//                send.putExtra("mr3", str_mr3);
//                send.putExtra("mr4", str_mr4);
//                send.putExtra("mr5", str_mr5);
//                send.putExtra("mr6", str_mr6);
//                send.putExtra("mr7", str_mr7);
//                send.putExtra("mr8", str_mr8);
//                send.putExtra("mr9", str_mr9);
//                send.putExtra("mr10", str_mr10);
               send.putExtra("status", status);
                startActivity(send);

            }
        });



        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Cursor cursor = CommonUtil.dbUtil.getMR(job_id);

                Log.e("MR Count",""+ cursor.getCount());

                if(cursor.getCount()>0) {
                    Intent send = new Intent(AuditMR_Activity.this, TechnicianSigantureAudit_Activity.class);
                    //send.putExtra("service_title",service_title);
//                send.putExtra("job_id",job_id);
//                send.putExtra("mr1", str_mr1);
//                send.putExtra("mr2", str_mr2);
//                send.putExtra("mr3", str_mr3);
//                send.putExtra("mr4", str_mr4);
//                send.putExtra("mr5", str_mr5);
//                send.putExtra("mr6", str_mr6);
//                send.putExtra("mr7", str_mr7);
//                send.putExtra("mr8", str_mr8);
//                send.putExtra("mr9", str_mr9);
//                send.putExtra("mr10", str_mr10);
                    send.putExtra("status", status);
                    startActivity(send);
                }
                else{
                    alertDialog = new AlertDialog.Builder(context)
                            //.setTitle("Please Login to Continue!")
                            .setMessage("Please add MR")
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    alertDialog.dismiss();
                                }
                            })
                            .show();
                }

            }
        });



    }

    @Override
    public void onBackPressed() {

       // super.onBackPressed();

        Intent send = new Intent(context, MaterialRequest_AuditActivity.class);
        send.putExtra("status", status);
        startActivity(send);

    }

    private void addMrData(String job_id) {

//        ArrayList<String> arli_Partid = new ArrayList<>();
//        ArrayList<String> arli_Partname = new ArrayList<>();
//        ArrayList<String> arli_Partno =  new ArrayList<>();
//        ArrayList<String> arli_Quantity =  new ArrayList<>();

//        arli_Partid.clear();
//        arli_Partname.clear();
//        arli_Partno.clear();
//        arli_Quantity.clear();

        arli_Partid = new ArrayList<>();
        arli_Partname = new ArrayList<>();
        arli_Partno = new ArrayList<>();
        arli_Quantity = new ArrayList<>();


        Cursor cursor = CommonUtil.dbUtil.getMR(job_id);

        Log.e("MR Count",""+ cursor.getCount());

        if (cursor.getCount()>0 &&cursor.moveToFirst()){

            do {
                String str_Partid = cursor.getString(cursor.getColumnIndexOrThrow(DbHelper.MR_ID));
                String str_Partname = cursor.getString(cursor.getColumnIndexOrThrow(DbHelper.PART_NAME));
                String str_Partno = cursor.getString(cursor.getColumnIndexOrThrow(DbHelper.PART_NO));
                String str_Quantity = cursor.getString(cursor.getColumnIndexOrThrow(DbHelper.QUANTITY));

                arli_Partid.add(str_Partid);
                arli_Partname.add(str_Partname);
                arli_Partno.add(str_Partno);
                arli_Quantity.add(str_Quantity);
            } while (cursor.moveToNext());
        }
        cursor.close();

        Log.e("Nish",""+arli_Partid.size());
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        AuditMR_Adapter adapter = new AuditMR_Adapter(arli_Partid,arli_Partname,arli_Partno, arli_Quantity,context);
        recyclerView.setAdapter(adapter);


    }

    public void petBreedTypeSelectListener(String petbreedtitle, String petbreedid) {
        PetBreedType = petbreedtitle;
    }
}