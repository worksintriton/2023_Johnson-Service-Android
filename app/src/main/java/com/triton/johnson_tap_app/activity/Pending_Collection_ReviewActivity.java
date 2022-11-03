package com.triton.johnson_tap_app.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;

import com.triton.johnson_tap_app.DrawableClickListener;
import com.triton.johnson_tap_app.R;

import java.util.Calendar;

public class Pending_Collection_ReviewActivity extends AppCompatActivity {

    ImageView iv_back;
    EditText rev_date,rev_date1,rev_date2,rev_date3,rev_date4,exp_date,exp_date1,exp_date2,exp_date3,exp_date4,handling_date,old_exp_date;
    EditText custo_name,mgr_remark,agent,record_cnt,ho_remarks,address,old_tnt,ord_ser_brcd;
    EditText job_no,job_no1,job_no2,job_no3,job_no4,os_amt,os_amt1,os_amt2,os_amt3,os_amt4,col_amt,col_amt1,col_amt2,col_amt3,col_amt4,bal_os,bal_os1,bal_os2,bal_os3,bal_os4,target,target1,target2,target3,target4,remarks,remarks1,remarks2,remarks3,remarks4,support,support1,support2,support3,support4,elaps_day,elaps_day1,elaps_day2,elaps_day3,elaps_day4;
    DatePickerDialog datepicker;
    Button sumbit;
    String s_rev_date,s_rev_date1,s_rev_date2,s_rev_date3,s_rev_date4,s_exp_date,s_exp_date1,s_exp_date2,s_exp_date3,s_exp_date4,s_handling_date,s_old_exp_date,s_custo_name,s_mgr_remark,s_agent,s_record_cnt,s_ho_remarks,s_address,s_old_tnt,s_ord_ser_brcd;
    String s_job_no,s_job_no1,s_job_no2,s_job_no3,s_job_no4,s_os_amt,s_os_amt1,s_os_amt2,s_os_amt3,s_os_amt4,s_col_amt,s_col_amt1,s_col_amt2,s_col_amt3,s_col_amt4,s_bal_os,s_bal_os1,s_bal_os2,s_bal_os3,s_bal_os4,s_target,s_target1,s_target2,s_target3,s_target4,s_remarks,s_remarks1,s_remarks2,s_remarks3,s_remarks4,s_support,s_support1,s_support2,s_support3,s_support4,s_elaps_day,s_elaps_day1,s_elaps_day2,s_elaps_day3,s_elaps_day4;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_pending_collection_review);

        rev_date = (EditText)findViewById(R.id.revw_date);
        rev_date1 = (EditText)findViewById(R.id.revw_date1);
        rev_date2 = (EditText)findViewById(R.id.revw_date2);
        rev_date3 = (EditText)findViewById(R.id.revw_date3);
        rev_date4 = (EditText)findViewById(R.id.revw_date4);
        exp_date = (EditText)findViewById(R.id.exp_date);
        exp_date1 = (EditText)findViewById(R.id.exp_date1);
        exp_date2 = (EditText)findViewById(R.id.exp_date2);
        exp_date3 = (EditText)findViewById(R.id.exp_date3);
        exp_date4 = (EditText)findViewById(R.id.exp_date4);
        job_no = (EditText)findViewById(R.id.job_no);
        job_no1 = (EditText)findViewById(R.id.job_no1);
        job_no2 = (EditText)findViewById(R.id.job_no2);
        job_no3 = (EditText)findViewById(R.id.job_no3);
        job_no4 = (EditText)findViewById(R.id.job_no4);
        os_amt = (EditText)findViewById(R.id.os_amt);
        os_amt1 = (EditText)findViewById(R.id.os_amt1);
        os_amt2 = (EditText)findViewById(R.id.os_amt2);
        os_amt3 = (EditText)findViewById(R.id.os_amt3);
        os_amt4 = (EditText)findViewById(R.id.os_amt4);
        col_amt = (EditText)findViewById(R.id.col_amt);
        col_amt1 = (EditText)findViewById(R.id.col_amt1);
        col_amt2 = (EditText)findViewById(R.id.col_amt2);
        col_amt3 = (EditText)findViewById(R.id.col_amt3);
        col_amt4 = (EditText)findViewById(R.id.col_amt4);
        bal_os = (EditText)findViewById(R.id.bal_os);
        bal_os1 = (EditText)findViewById(R.id.bal_os1);
        bal_os2 = (EditText)findViewById(R.id.bal_os2);
        bal_os3 = (EditText)findViewById(R.id.bal_os3);
        bal_os4 = (EditText)findViewById(R.id.bal_os4);
        target = (EditText)findViewById(R.id.target);
        target1 = (EditText)findViewById(R.id.target1);
        target2 = (EditText)findViewById(R.id.target2);
        target3 = (EditText)findViewById(R.id.target3);
        target4 = (EditText)findViewById(R.id.target4);
        remarks = (EditText)findViewById(R.id.remarks);
        remarks1 = (EditText)findViewById(R.id.remarks1);
        remarks2 = (EditText)findViewById(R.id.remarks2);
        remarks3 = (EditText)findViewById(R.id.remarks3);
        remarks4 = (EditText)findViewById(R.id.remarks4);
        support = (EditText)findViewById(R.id.support);
        support1 = (EditText)findViewById(R.id.support1);
        support2 = (EditText)findViewById(R.id.support2);
        support3 = (EditText)findViewById(R.id.support3);
        support4 = (EditText)findViewById(R.id.support4);
        elaps_day = (EditText)findViewById(R.id.elaps_day);
        elaps_day1 = (EditText)findViewById(R.id.elaps_day1);
        elaps_day2 = (EditText)findViewById(R.id.elaps_day2);
        elaps_day3 = (EditText)findViewById(R.id.elaps_day3);
        elaps_day4 = (EditText)findViewById(R.id.elaps_day4);

        handling_date = (EditText)findViewById(R.id.handling_date);
        old_exp_date = (EditText)findViewById(R.id.old_exp_date);
        custo_name = (EditText)findViewById(R.id.customer_name);
        mgr_remark = (EditText)findViewById(R.id.mgr_remarks);
        agent = (EditText)findViewById(R.id.agent);
        record_cnt = (EditText)findViewById(R.id.record_cnt);
        ho_remarks = (EditText)findViewById(R.id.ho_remarks);
        address = (EditText)findViewById(R.id.address);
        old_tnt = (EditText)findViewById(R.id.old_tnt);
        ord_ser_brcd = (EditText)findViewById(R.id.ord_ser_brcd);
        sumbit = (Button) findViewById(R.id.submit);
        iv_back = (ImageView) findViewById(R.id.iv_back);

        iv_back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent send = new Intent(Pending_Collection_ReviewActivity.this, MainActivity.class);
                startActivity(send);
            }
        });

        rev_date.setOnTouchListener(new DrawableClickListener.RightDrawableClickListener(rev_date) {
            public boolean onDrawableClick() {
                rev_date.setCursorVisible(false);
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                datepicker = new DatePickerDialog(Pending_Collection_ReviewActivity.this,
                        new DatePickerDialog.OnDateSetListener() {

                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                rev_date.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, year, month, day);
                datepicker.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
                datepicker.show();
                return true;
            }
        });

        rev_date1.setOnTouchListener(new DrawableClickListener.RightDrawableClickListener(rev_date1) {
            public boolean onDrawableClick() {
                rev_date1.setCursorVisible(false);
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                datepicker = new DatePickerDialog(Pending_Collection_ReviewActivity.this,
                        new DatePickerDialog.OnDateSetListener() {

                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                rev_date1.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, year, month, day);
                datepicker.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
                datepicker.show();
                return true;
            }
        });

        rev_date2.setOnTouchListener(new DrawableClickListener.RightDrawableClickListener(rev_date2) {
            public boolean onDrawableClick() {
                rev_date2.setCursorVisible(false);
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                datepicker = new DatePickerDialog(Pending_Collection_ReviewActivity.this,
                        new DatePickerDialog.OnDateSetListener() {

                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                rev_date2.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, year, month, day);
                datepicker.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
                datepicker.show();
                return true;
            }
        });

        rev_date3.setOnTouchListener(new DrawableClickListener.RightDrawableClickListener(rev_date3) {
            public boolean onDrawableClick() {
                rev_date3.setCursorVisible(false);
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                datepicker = new DatePickerDialog(Pending_Collection_ReviewActivity.this,
                        new DatePickerDialog.OnDateSetListener() {

                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                rev_date3.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, year, month, day);
                datepicker.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
                datepicker.show();
                return true;
            }
        });

        rev_date4.setOnTouchListener(new DrawableClickListener.RightDrawableClickListener(rev_date4) {
            public boolean onDrawableClick() {
                rev_date4.setCursorVisible(false);
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                datepicker = new DatePickerDialog(Pending_Collection_ReviewActivity.this,
                        new DatePickerDialog.OnDateSetListener() {

                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                rev_date4.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, year, month, day);
                datepicker.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
                datepicker.show();
                return true;
            }
        });

        exp_date.setOnTouchListener(new DrawableClickListener.RightDrawableClickListener(exp_date) {
            public boolean onDrawableClick() {
                exp_date.setCursorVisible(false);
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                datepicker = new DatePickerDialog(Pending_Collection_ReviewActivity.this,
                        new DatePickerDialog.OnDateSetListener() {

                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                exp_date.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, year, month, day);
                datepicker.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
                datepicker.show();
                return true;
            }
        });

        exp_date1.setOnTouchListener(new DrawableClickListener.RightDrawableClickListener(exp_date1) {
            public boolean onDrawableClick() {
                exp_date1.setCursorVisible(false);
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                datepicker = new DatePickerDialog(Pending_Collection_ReviewActivity.this,
                        new DatePickerDialog.OnDateSetListener() {

                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                exp_date1.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, year, month, day);
                datepicker.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
                datepicker.show();
                return true;
            }
        });

        exp_date2.setOnTouchListener(new DrawableClickListener.RightDrawableClickListener(exp_date2) {
            public boolean onDrawableClick() {
                exp_date2.setCursorVisible(false);
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                datepicker = new DatePickerDialog(Pending_Collection_ReviewActivity.this,
                        new DatePickerDialog.OnDateSetListener() {

                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                exp_date2.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, year, month, day);
                datepicker.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
                datepicker.show();
                return true;
            }
        });

        exp_date3.setOnTouchListener(new DrawableClickListener.RightDrawableClickListener(exp_date3) {
            public boolean onDrawableClick() {
                exp_date3.setCursorVisible(false);
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                datepicker = new DatePickerDialog(Pending_Collection_ReviewActivity.this,
                        new DatePickerDialog.OnDateSetListener() {

                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                exp_date3.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, year, month, day);
                datepicker.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
                datepicker.show();
                return true;
            }
        });

        exp_date4.setOnTouchListener(new DrawableClickListener.RightDrawableClickListener(exp_date4) {
            public boolean onDrawableClick() {
                exp_date4.setCursorVisible(false);
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                datepicker = new DatePickerDialog(Pending_Collection_ReviewActivity.this,
                        new DatePickerDialog.OnDateSetListener() {

                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                exp_date4.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, year, month, day);
                datepicker.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
                datepicker.show();
                return true;
            }
        });

        handling_date.setOnTouchListener(new DrawableClickListener.RightDrawableClickListener(handling_date) {
            public boolean onDrawableClick() {
                handling_date.setCursorVisible(false);
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                datepicker = new DatePickerDialog(Pending_Collection_ReviewActivity.this,
                        new DatePickerDialog.OnDateSetListener() {

                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                handling_date.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, year, month, day);
                datepicker.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
                datepicker.show();
                return true;
            }
        });

        old_exp_date.setOnTouchListener(new DrawableClickListener.RightDrawableClickListener(old_exp_date) {
            public boolean onDrawableClick() {
                old_exp_date.setCursorVisible(false);
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                datepicker = new DatePickerDialog(Pending_Collection_ReviewActivity.this,
                        new DatePickerDialog.OnDateSetListener() {

                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                old_exp_date.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, year, month, day);
                datepicker.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
                datepicker.show();
                return true;
            }
        });

        sumbit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                s_rev_date = rev_date.getText().toString();
                s_rev_date1 = rev_date1.getText().toString();
                s_rev_date2 = rev_date2.getText().toString();
                s_rev_date3 = rev_date3.getText().toString();
                s_rev_date4 = rev_date4.getText().toString();
                s_exp_date = exp_date.getText().toString();
                s_exp_date1 = exp_date1.getText().toString();
                s_exp_date2 = exp_date2.getText().toString();
                s_exp_date3 = exp_date3.getText().toString();
                s_exp_date4 = exp_date4.getText().toString();
                s_job_no = job_no.getText().toString();
                s_job_no1 = job_no1.getText().toString();
                s_job_no2 = job_no2.getText().toString();
                s_job_no3 = job_no3.getText().toString();
                s_job_no4 = job_no4.getText().toString();
                s_os_amt = os_amt.getText().toString();
                s_os_amt1 = os_amt1.getText().toString();
                s_os_amt2 = os_amt2.getText().toString();
                s_os_amt3 = os_amt3.getText().toString();
                s_os_amt4 = os_amt4.getText().toString();
                s_col_amt = col_amt.getText().toString();
                s_col_amt1 = col_amt1.getText().toString();
                s_col_amt2 = col_amt2.getText().toString();
                s_col_amt3 = col_amt3.getText().toString();
                s_col_amt4 = col_amt4.getText().toString();
                s_bal_os = bal_os.getText().toString();
                s_bal_os1 = bal_os1.getText().toString();
                s_bal_os2 = bal_os2.getText().toString();
                s_bal_os3 = bal_os3.getText().toString();
                s_bal_os4 = bal_os4.getText().toString();
                s_target = target.getText().toString();
                s_target1 = target1.getText().toString();
                s_target2 = target2.getText().toString();
                s_target3 = target3.getText().toString();
                s_target4 = target4.getText().toString();
                s_remarks = remarks.getText().toString();
                s_remarks1 = remarks1.getText().toString();
                s_remarks2 = remarks2.getText().toString();
                s_remarks3 = remarks3.getText().toString();
                s_remarks4 = remarks4.getText().toString();
                s_support = support.getText().toString();
                s_support1 = support1.getText().toString();
                s_support2 = support2.getText().toString();
                s_support3 = support3.getText().toString();
                s_support4 = support4.getText().toString();
                s_elaps_day = elaps_day.getText().toString();
                s_elaps_day1 = elaps_day1.getText().toString();
                s_elaps_day2 = elaps_day2.getText().toString();
                s_elaps_day3 = elaps_day3.getText().toString();
                s_elaps_day4 = elaps_day4.getText().toString();

                s_handling_date = handling_date.getText().toString();
                s_old_exp_date = old_exp_date.getText().toString();
                s_custo_name = handling_date.getText().toString();
                s_mgr_remark = mgr_remark.getText().toString();
                s_agent = agent.getText().toString();
                s_record_cnt = record_cnt.getText().toString();
                s_ho_remarks = ho_remarks.getText().toString();
                s_address = address.getText().toString();
                s_old_tnt = old_tnt.getText().toString();
                s_ord_ser_brcd = ord_ser_brcd.getText().toString();

            }
        });

    }
}