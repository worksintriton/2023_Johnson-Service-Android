package com.triton.johnson_tap_app;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.triton.johnson_tap_app.activity.Daily_Collection_DetailsActivity;
import com.triton.johnson_tap_app.responsepojo.BD_DetailsResponse;
import com.triton.johnson_tap_app.responsepojo.RTGS_PopResponse;

import java.util.List;


public class RTGS_Pop_Adapter extends  RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private  String TAG = "PetBreedTypesListAdapter";
    private Context context;
    RTGS_PopResponse.DataBean currentItem;
    private List<RTGS_PopResponse.DataBean> breedTypedataBeanList;
    private static CheckBox lastChecked = null;
    private static int lastCheckedPos = 0;
    String data = "";
    String pop_agent_code,p_chq_no,p_job,p_job1,p_job2,p_job3,p_job4,p_job5,p_job6,p_job7,p_job8,p_job9,p_cus_name,p_cus_name1,p_cus_name2,p_cus_name3,p_cus_name4,p_cus_name5,p_cus_name6,p_cus_name7,p_cus_name8,p_cus_name9,p_f_date,p_f_date1,p_f_date2,p_f_date3,p_f_date4,p_f_date5,p_f_date6,p_f_date7,p_f_date8,p_f_date9,p_t_date,p_t_date1,p_t_date2,p_t_date3,p_t_date4,p_t_date5,p_t_date6,p_t_date7,p_t_date8,p_t_date9,p_pay_amt,p_pay_amt1,p_pay_amt2,p_pay_amt3,p_pay_amt4,p_pay_amt5,p_pay_amt6,p_pay_amt7,p_pay_amt8,p_pay_amt9;

    public RTGS_Pop_Adapter(Context context, List<RTGS_PopResponse.DataBean> breedTypedataBeanList) {
        this.context = context;
        this.breedTypedataBeanList = breedTypedataBeanList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pop_list, parent, false);
        return new ViewHolderOne(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        initLayoutOne((ViewHolderOne) holder, position);


    }

    @SuppressLint("LogNotTimber")
    private void initLayoutOne(ViewHolderOne holder, final int position) {
        currentItem = breedTypedataBeanList.get(position);

        String bsdamt = breedTypedataBeanList.get(position).getFA_BSD_CUSACNM();


        Log.e("Nish",""+bsdamt);

        if(currentItem.getFA_BSD_AMOUNT() != null){
            holder.textView_pop.setText(currentItem.getFA_BSD_UTRNO());
            holder.textView1_pop.setText(currentItem.getFA_BSD_BANKDT());
            holder.textView2_pop.setText(currentItem.getFA_BSD_AMOUNT());
            holder.textView3_pop.setText(currentItem.getFA_BSD_CUSACNM()); ///
            holder.textView4_pop.setText(currentItem.getFA_BSD_IFSCCD());///
            holder.textView5_pop.setText(currentItem.getFA_BSD_BALAMT());
        }


        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        pop_agent_code = sharedPreferences.getString("agent_code", "default value");
        p_chq_no = sharedPreferences.getString("p_chq_no", "default value");
        p_job = sharedPreferences.getString("p_job", "default value");
        p_job1 = sharedPreferences.getString("p_job1", "default value");
        p_job2 = sharedPreferences.getString("p_job2", "default value");
        p_job3 = sharedPreferences.getString("p_job3", "default value");
        p_job4 = sharedPreferences.getString("p_job4", "default value");
        p_job5 = sharedPreferences.getString("p_job5", "default value");
        p_job6 = sharedPreferences.getString("p_job6", "default value");
        p_job7 = sharedPreferences.getString("p_job7", "default value");
        p_job8 = sharedPreferences.getString("p_job8", "default value");
        p_job9 = sharedPreferences.getString("p_job9", "default value");
        p_cus_name = sharedPreferences.getString("p_cust_name", "default value");
        p_cus_name1 = sharedPreferences.getString("p_cust_name1", "default value");
        p_cus_name2 = sharedPreferences.getString("p_cust_name2", "default value");
        p_cus_name3 = sharedPreferences.getString("p_cust_name3", "default value");
        p_cus_name4 = sharedPreferences.getString("p_cust_name4", "default value");
        p_cus_name5 = sharedPreferences.getString("p_cust_name5", "default value");
        p_cus_name6 = sharedPreferences.getString("p_cust_name6", "default value");
        p_cus_name7 = sharedPreferences.getString("p_cust_name7", "default value");
        p_cus_name8 = sharedPreferences.getString("p_cust_name8", "default value");
        p_cus_name9 = sharedPreferences.getString("p_cust_name9", "default value");
        p_f_date = sharedPreferences.getString("p_f_date", "default value");
        p_f_date1 = sharedPreferences.getString("p_f_date1", "default value");
        p_f_date2 = sharedPreferences.getString("p_f_date2", "default value");
        p_f_date3 = sharedPreferences.getString("p_f_date3", "default value");
        p_f_date4 = sharedPreferences.getString("p_f_date4", "default value");
        p_f_date5 = sharedPreferences.getString("p_f_date5", "default value");
        p_f_date6 = sharedPreferences.getString("p_f_date6", "default value");
        p_f_date7 = sharedPreferences.getString("p_f_date7", "default value");
        p_f_date8 = sharedPreferences.getString("p_f_date8", "default value");
        p_f_date9 = sharedPreferences.getString("p_f_date9", "default value");
        p_t_date = sharedPreferences.getString("p_t_date", "default value");
        p_t_date1 = sharedPreferences.getString("p_t_date1", "default value");
        p_t_date2 = sharedPreferences.getString("p_t_date2", "default value");
        p_t_date3 = sharedPreferences.getString("p_t_date3", "default value");
        p_t_date4 = sharedPreferences.getString("p_t_date4", "default value");
        p_t_date5 = sharedPreferences.getString("p_t_date5", "default value");
        p_t_date6 = sharedPreferences.getString("p_t_date6", "default value");
        p_t_date7 = sharedPreferences.getString("p_t_date7", "default value");
        p_t_date8 = sharedPreferences.getString("p_t_date8", "default value");
        p_t_date9 = sharedPreferences.getString("p_t_date9", "default value");
        p_pay_amt = sharedPreferences.getString("p_pay_amt", "default value");
        p_pay_amt1 = sharedPreferences.getString("p_pay_amt1", "default value");
        p_pay_amt2 = sharedPreferences.getString("p_pay_amt2", "default value");
        p_pay_amt3 = sharedPreferences.getString("p_pay_amt3", "default value");
        p_pay_amt4 = sharedPreferences.getString("p_pay_amt4", "default value");
        p_pay_amt5 = sharedPreferences.getString("p_pay_amt5", "default value");
        p_pay_amt6 = sharedPreferences.getString("p_pay_amt6", "default value");
        p_pay_amt7 = sharedPreferences.getString("p_pay_amt7", "default value");
        p_pay_amt8 = sharedPreferences.getString("p_pay_amt8", "default value");
        p_pay_amt9 = sharedPreferences.getString("p_pay_amt9", "default value");

        holder.textView_pop.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                holder.lin.setBackgroundColor(Color.BLUE);

                Intent intent = new Intent(context, Daily_Collection_DetailsActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("urt_no",currentItem.getFA_BSD_UTRNO());
                intent.putExtra("bank_details",currentItem.getFA_BSD_BANKDT());
                intent.putExtra("amt",currentItem.getFA_BSD_AMOUNT());
                intent.putExtra("customer_name",currentItem.getFA_BSD_CUSACNM());
                intent.putExtra("ifsc_code",currentItem.getFA_BSD_IFSCCD());
                intent.putExtra("balance_amt",currentItem.getFA_BSD_BALAMT());
                intent.putExtra("Radio_button","RTGS");
                intent.putExtra("agent_code",pop_agent_code);
                intent.putExtra("p_chq_no",p_chq_no);
                intent.putExtra("p_job",p_job);
                intent.putExtra("p_job1",p_job1);
                intent.putExtra("p_job2",p_job2);
                intent.putExtra("p_job3",p_job3);
                intent.putExtra("p_job4",p_job4);
                intent.putExtra("p_job5",p_job5);
                intent.putExtra("p_job6",p_job6);
                intent.putExtra("p_job7",p_job7);
                intent.putExtra("p_job8",p_job8);
                intent.putExtra("p_job9",p_job9);
                intent.putExtra("p_cust_name",p_cus_name);
                intent.putExtra("p_cust_name1",p_cus_name1);
                intent.putExtra("p_cust_name2",p_cus_name2);
                intent.putExtra("p_cust_name3",p_cus_name3);
                intent.putExtra("p_cust_name4",p_cus_name4);
                intent.putExtra("p_cust_name5",p_cus_name5);
                intent.putExtra("p_cust_name6",p_cus_name6);
                intent.putExtra("p_cust_name7",p_cus_name7);
                intent.putExtra("p_cust_name8",p_cus_name8);
                intent.putExtra("p_cust_name9",p_cus_name9);
                intent.putExtra("p_f_date", p_f_date);
                intent.putExtra("p_f_date1", p_f_date1);
                intent.putExtra("p_f_date2", p_f_date2);
                intent.putExtra("p_f_date3", p_f_date3);
                intent.putExtra("p_f_date4", p_f_date4);
                intent.putExtra("p_f_date5", p_f_date5);
                intent.putExtra("p_f_date6", p_f_date6);
                intent.putExtra("p_f_date7", p_f_date7);
                intent.putExtra("p_f_date8", p_f_date8);
                intent.putExtra("p_f_date9", p_f_date9);
                intent.putExtra("p_t_date", p_t_date);
                intent.putExtra("p_t_date1", p_t_date1);
                intent.putExtra("p_t_date2", p_t_date2);
                intent.putExtra("p_t_date3", p_t_date3);
                intent.putExtra("p_t_date4", p_t_date4);
                intent.putExtra("p_t_date5", p_t_date5);
                intent.putExtra("p_t_date6", p_t_date6);
                intent.putExtra("p_t_date7", p_t_date7);
                intent.putExtra("p_t_date8", p_t_date8);
                intent.putExtra("p_t_date9", p_t_date9);
                intent.putExtra("p_pay_amt", p_pay_amt);
                intent.putExtra("p_pay_amt1", p_pay_amt1);
                intent.putExtra("p_pay_amt2", p_pay_amt2);
                intent.putExtra("p_pay_amt3", p_pay_amt3);
                intent.putExtra("p_pay_amt4", p_pay_amt4);
                intent.putExtra("p_pay_amt5", p_pay_amt5);
                intent.putExtra("p_pay_amt6", p_pay_amt6);
                intent.putExtra("p_pay_amt7", p_pay_amt7);
                intent.putExtra("p_pay_amt8", p_pay_amt8);
                intent.putExtra("p_pay_amt9", p_pay_amt9);
                context.startActivity(intent);
            }
        });
        holder.textView1_pop.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                holder.lin.setBackgroundColor(Color.BLUE);

                Intent intent = new Intent(context,Daily_Collection_DetailsActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("urt_no",currentItem.getFA_BSD_UTRNO());
                intent.putExtra("bank_details",currentItem.getFA_BSD_BANKDT());
                intent.putExtra("amt",currentItem.getFA_BSD_AMOUNT());
                intent.putExtra("customer_name",currentItem.getFA_BSD_CUSACNM());
                intent.putExtra("ifsc_code",currentItem.getFA_BSD_IFSCCD());
                intent.putExtra("balance_amt",currentItem.getFA_BSD_BALAMT());
                intent.putExtra("Radio_button","RTGS");
                intent.putExtra("agent_code",pop_agent_code);
                intent.putExtra("p_chq_no",p_chq_no);
                intent.putExtra("p_job",p_job);
                intent.putExtra("p_job1",p_job1);
                intent.putExtra("p_job2",p_job2);
                intent.putExtra("p_job3",p_job3);
                intent.putExtra("p_job4",p_job4);
                intent.putExtra("p_job5",p_job5);
                intent.putExtra("p_job6",p_job6);
                intent.putExtra("p_job7",p_job7);
                intent.putExtra("p_job8",p_job8);
                intent.putExtra("p_job9",p_job9);
                intent.putExtra("p_cust_name",p_cus_name);
                intent.putExtra("p_cust_name1",p_cus_name1);
                intent.putExtra("p_cust_name2",p_cus_name2);
                intent.putExtra("p_cust_name3",p_cus_name3);
                intent.putExtra("p_cust_name4",p_cus_name4);
                intent.putExtra("p_cust_name5",p_cus_name5);
                intent.putExtra("p_cust_name6",p_cus_name6);
                intent.putExtra("p_cust_name7",p_cus_name7);
                intent.putExtra("p_cust_name8",p_cus_name8);
                intent.putExtra("p_cust_name9",p_cus_name9);
                intent.putExtra("p_f_date", p_f_date);
                intent.putExtra("p_f_date1", p_f_date1);
                intent.putExtra("p_f_date2", p_f_date2);
                intent.putExtra("p_f_date3", p_f_date3);
                intent.putExtra("p_f_date4", p_f_date4);
                intent.putExtra("p_f_date5", p_f_date5);
                intent.putExtra("p_f_date6", p_f_date6);
                intent.putExtra("p_f_date7", p_f_date7);
                intent.putExtra("p_f_date8", p_f_date8);
                intent.putExtra("p_f_date9", p_f_date9);
                intent.putExtra("p_t_date", p_t_date);
                intent.putExtra("p_t_date1", p_t_date1);
                intent.putExtra("p_t_date2", p_t_date2);
                intent.putExtra("p_t_date3", p_t_date3);
                intent.putExtra("p_t_date4", p_t_date4);
                intent.putExtra("p_t_date5", p_t_date5);
                intent.putExtra("p_t_date6", p_t_date6);
                intent.putExtra("p_t_date7", p_t_date7);
                intent.putExtra("p_t_date8", p_t_date8);
                intent.putExtra("p_t_date9", p_t_date9);
                intent.putExtra("p_pay_amt", p_pay_amt);
                intent.putExtra("p_pay_amt1", p_pay_amt1);
                intent.putExtra("p_pay_amt2", p_pay_amt2);
                intent.putExtra("p_pay_amt3", p_pay_amt3);
                intent.putExtra("p_pay_amt4", p_pay_amt4);
                intent.putExtra("p_pay_amt5", p_pay_amt5);
                intent.putExtra("p_pay_amt6", p_pay_amt6);
                intent.putExtra("p_pay_amt7", p_pay_amt7);
                intent.putExtra("p_pay_amt8", p_pay_amt8);
                intent.putExtra("p_pay_amt9", p_pay_amt9);
                context.startActivity(intent);
            }
        });
        holder.textView2_pop.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                holder.lin.setBackgroundColor(Color.BLUE);

                Intent intent = new Intent(context,Daily_Collection_DetailsActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("urt_no",currentItem.getFA_BSD_UTRNO());
                intent.putExtra("bank_details",currentItem.getFA_BSD_BANKDT());
                intent.putExtra("amt",currentItem.getFA_BSD_AMOUNT());
                intent.putExtra("customer_name",currentItem.getFA_BSD_CUSACNM());
                intent.putExtra("ifsc_code",currentItem.getFA_BSD_IFSCCD());
                intent.putExtra("balance_amt",currentItem.getFA_BSD_BALAMT());
                intent.putExtra("Radio_button","RTGS");
                intent.putExtra("agent_code",pop_agent_code);
                intent.putExtra("p_chq_no",p_chq_no);
                intent.putExtra("p_job",p_job);
                intent.putExtra("p_job1",p_job1);
                intent.putExtra("p_job2",p_job2);
                intent.putExtra("p_job3",p_job3);
                intent.putExtra("p_job4",p_job4);
                intent.putExtra("p_job5",p_job5);
                intent.putExtra("p_job6",p_job6);
                intent.putExtra("p_job7",p_job7);
                intent.putExtra("p_job8",p_job8);
                intent.putExtra("p_job9",p_job9);
                intent.putExtra("p_cust_name",p_cus_name);
                intent.putExtra("p_cust_name1",p_cus_name1);
                intent.putExtra("p_cust_name2",p_cus_name2);
                intent.putExtra("p_cust_name3",p_cus_name3);
                intent.putExtra("p_cust_name4",p_cus_name4);
                intent.putExtra("p_cust_name5",p_cus_name5);
                intent.putExtra("p_cust_name6",p_cus_name6);
                intent.putExtra("p_cust_name7",p_cus_name7);
                intent.putExtra("p_cust_name8",p_cus_name8);
                intent.putExtra("p_cust_name9",p_cus_name9);
                intent.putExtra("p_f_date", p_f_date);
                intent.putExtra("p_f_date1", p_f_date1);
                intent.putExtra("p_f_date2", p_f_date2);
                intent.putExtra("p_f_date3", p_f_date3);
                intent.putExtra("p_f_date4", p_f_date4);
                intent.putExtra("p_f_date5", p_f_date5);
                intent.putExtra("p_f_date6", p_f_date6);
                intent.putExtra("p_f_date7", p_f_date7);
                intent.putExtra("p_f_date8", p_f_date8);
                intent.putExtra("p_f_date9", p_f_date9);
                intent.putExtra("p_t_date", p_t_date);
                intent.putExtra("p_t_date1", p_t_date1);
                intent.putExtra("p_t_date2", p_t_date2);
                intent.putExtra("p_t_date3", p_t_date3);
                intent.putExtra("p_t_date4", p_t_date4);
                intent.putExtra("p_t_date5", p_t_date5);
                intent.putExtra("p_t_date6", p_t_date6);
                intent.putExtra("p_t_date7", p_t_date7);
                intent.putExtra("p_t_date8", p_t_date8);
                intent.putExtra("p_t_date9", p_t_date9);
                intent.putExtra("p_pay_amt", p_pay_amt);
                intent.putExtra("p_pay_amt1", p_pay_amt1);
                intent.putExtra("p_pay_amt2", p_pay_amt2);
                intent.putExtra("p_pay_amt3", p_pay_amt3);
                intent.putExtra("p_pay_amt4", p_pay_amt4);
                intent.putExtra("p_pay_amt5", p_pay_amt5);
                intent.putExtra("p_pay_amt6", p_pay_amt6);
                intent.putExtra("p_pay_amt7", p_pay_amt7);
                intent.putExtra("p_pay_amt8", p_pay_amt8);
                intent.putExtra("p_pay_amt9", p_pay_amt9);
                context.startActivity(intent);
            }
        });
        holder.textView3_pop.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                holder.lin.setBackgroundColor(Color.BLUE);

                Intent intent = new Intent(context,Daily_Collection_DetailsActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("urt_no",currentItem.getFA_BSD_UTRNO());
                intent.putExtra("bank_details",currentItem.getFA_BSD_BANKDT());
                intent.putExtra("amt",currentItem.getFA_BSD_AMOUNT());
                intent.putExtra("customer_name",currentItem.getFA_BSD_CUSACNM());
                intent.putExtra("ifsc_code",currentItem.getFA_BSD_IFSCCD());
                intent.putExtra("balance_amt",currentItem.getFA_BSD_BALAMT());
                intent.putExtra("Radio_button","RTGS");
                intent.putExtra("agent_code",pop_agent_code);
                intent.putExtra("p_chq_no",p_chq_no);
                intent.putExtra("p_job",p_job);
                intent.putExtra("p_job1",p_job1);
                intent.putExtra("p_job2",p_job2);
                intent.putExtra("p_job3",p_job3);
                intent.putExtra("p_job4",p_job4);
                intent.putExtra("p_job5",p_job5);
                intent.putExtra("p_job6",p_job6);
                intent.putExtra("p_job7",p_job7);
                intent.putExtra("p_job8",p_job8);
                intent.putExtra("p_job9",p_job9);
                intent.putExtra("p_cust_name",p_cus_name);
                intent.putExtra("p_cust_name1",p_cus_name1);
                intent.putExtra("p_cust_name2",p_cus_name2);
                intent.putExtra("p_cust_name3",p_cus_name3);
                intent.putExtra("p_cust_name4",p_cus_name4);
                intent.putExtra("p_cust_name5",p_cus_name5);
                intent.putExtra("p_cust_name6",p_cus_name6);
                intent.putExtra("p_cust_name7",p_cus_name7);
                intent.putExtra("p_cust_name8",p_cus_name8);
                intent.putExtra("p_cust_name9",p_cus_name9);
                intent.putExtra("p_f_date", p_f_date);
                intent.putExtra("p_f_date1", p_f_date1);
                intent.putExtra("p_f_date2", p_f_date2);
                intent.putExtra("p_f_date3", p_f_date3);
                intent.putExtra("p_f_date4", p_f_date4);
                intent.putExtra("p_f_date5", p_f_date5);
                intent.putExtra("p_f_date6", p_f_date6);
                intent.putExtra("p_f_date7", p_f_date7);
                intent.putExtra("p_f_date8", p_f_date8);
                intent.putExtra("p_f_date9", p_f_date9);
                intent.putExtra("p_t_date", p_t_date);
                intent.putExtra("p_t_date1", p_t_date1);
                intent.putExtra("p_t_date2", p_t_date2);
                intent.putExtra("p_t_date3", p_t_date3);
                intent.putExtra("p_t_date4", p_t_date4);
                intent.putExtra("p_t_date5", p_t_date5);
                intent.putExtra("p_t_date6", p_t_date6);
                intent.putExtra("p_t_date7", p_t_date7);
                intent.putExtra("p_t_date8", p_t_date8);
                intent.putExtra("p_t_date9", p_t_date9);
                intent.putExtra("p_pay_amt", p_pay_amt);
                intent.putExtra("p_pay_amt1", p_pay_amt1);
                intent.putExtra("p_pay_amt2", p_pay_amt2);
                intent.putExtra("p_pay_amt3", p_pay_amt3);
                intent.putExtra("p_pay_amt4", p_pay_amt4);
                intent.putExtra("p_pay_amt5", p_pay_amt5);
                intent.putExtra("p_pay_amt6", p_pay_amt6);
                intent.putExtra("p_pay_amt7", p_pay_amt7);
                intent.putExtra("p_pay_amt8", p_pay_amt8);
                intent.putExtra("p_pay_amt9", p_pay_amt9);
                context.startActivity(intent);
            }
        });
        holder.textView4_pop.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                holder.lin.setBackgroundColor(Color.BLUE);

                Intent intent = new Intent(context,Daily_Collection_DetailsActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("urt_no",currentItem.getFA_BSD_UTRNO());
                intent.putExtra("bank_details",currentItem.getFA_BSD_BANKDT());
                intent.putExtra("amt",currentItem.getFA_BSD_AMOUNT());
                intent.putExtra("customer_name",currentItem.getFA_BSD_CUSACNM());
                intent.putExtra("ifsc_code",currentItem.getFA_BSD_IFSCCD());
                intent.putExtra("balance_amt",currentItem.getFA_BSD_BALAMT());
                intent.putExtra("Radio_button","RTGS");
                intent.putExtra("agent_code",pop_agent_code);
                intent.putExtra("p_chq_no",p_chq_no);
                intent.putExtra("p_job",p_job);
                intent.putExtra("p_job1",p_job1);
                intent.putExtra("p_job2",p_job2);
                intent.putExtra("p_job3",p_job3);
                intent.putExtra("p_job4",p_job4);
                intent.putExtra("p_job5",p_job5);
                intent.putExtra("p_job6",p_job6);
                intent.putExtra("p_job7",p_job7);
                intent.putExtra("p_job8",p_job8);
                intent.putExtra("p_job9",p_job9);
                intent.putExtra("p_cust_name",p_cus_name);
                intent.putExtra("p_cust_name1",p_cus_name1);
                intent.putExtra("p_cust_name2",p_cus_name2);
                intent.putExtra("p_cust_name3",p_cus_name3);
                intent.putExtra("p_cust_name4",p_cus_name4);
                intent.putExtra("p_cust_name5",p_cus_name5);
                intent.putExtra("p_cust_name6",p_cus_name6);
                intent.putExtra("p_cust_name7",p_cus_name7);
                intent.putExtra("p_cust_name8",p_cus_name8);
                intent.putExtra("p_cust_name9",p_cus_name9);
                intent.putExtra("p_f_date", p_f_date);
                intent.putExtra("p_f_date1", p_f_date1);
                intent.putExtra("p_f_date2", p_f_date2);
                intent.putExtra("p_f_date3", p_f_date3);
                intent.putExtra("p_f_date4", p_f_date4);
                intent.putExtra("p_f_date5", p_f_date5);
                intent.putExtra("p_f_date6", p_f_date6);
                intent.putExtra("p_f_date7", p_f_date7);
                intent.putExtra("p_f_date8", p_f_date8);
                intent.putExtra("p_f_date9", p_f_date9);
                intent.putExtra("p_t_date", p_t_date);
                intent.putExtra("p_t_date1", p_t_date1);
                intent.putExtra("p_t_date2", p_t_date2);
                intent.putExtra("p_t_date3", p_t_date3);
                intent.putExtra("p_t_date4", p_t_date4);
                intent.putExtra("p_t_date5", p_t_date5);
                intent.putExtra("p_t_date6", p_t_date6);
                intent.putExtra("p_t_date7", p_t_date7);
                intent.putExtra("p_t_date8", p_t_date8);
                intent.putExtra("p_t_date9", p_t_date9);
                intent.putExtra("p_pay_amt", p_pay_amt);
                intent.putExtra("p_pay_amt1", p_pay_amt1);
                intent.putExtra("p_pay_amt2", p_pay_amt2);
                intent.putExtra("p_pay_amt3", p_pay_amt3);
                intent.putExtra("p_pay_amt4", p_pay_amt4);
                intent.putExtra("p_pay_amt5", p_pay_amt5);
                intent.putExtra("p_pay_amt6", p_pay_amt6);
                intent.putExtra("p_pay_amt7", p_pay_amt7);
                intent.putExtra("p_pay_amt8", p_pay_amt8);
                intent.putExtra("p_pay_amt9", p_pay_amt9);
                context.startActivity(intent);
            }
        });
        holder.textView5_pop.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                holder.lin.setBackgroundColor(Color.BLUE);

                Intent intent = new Intent(context,Daily_Collection_DetailsActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("urt_no",currentItem.getFA_BSD_UTRNO());
                intent.putExtra("bank_details",currentItem.getFA_BSD_BANKDT());
                intent.putExtra("amt",currentItem.getFA_BSD_AMOUNT());
                intent.putExtra("customer_name",currentItem.getFA_BSD_CUSACNM());
                intent.putExtra("ifsc_code",currentItem.getFA_BSD_IFSCCD());
                intent.putExtra("balance_amt",currentItem.getFA_BSD_BALAMT());
                intent.putExtra("Radio_button","RTGS");
                intent.putExtra("agent_code",pop_agent_code);
                intent.putExtra("p_chq_no",p_chq_no);
                intent.putExtra("p_job",p_job);
                intent.putExtra("p_job1",p_job1);
                intent.putExtra("p_job2",p_job2);
                intent.putExtra("p_job3",p_job3);
                intent.putExtra("p_job4",p_job4);
                intent.putExtra("p_job5",p_job5);
                intent.putExtra("p_job6",p_job6);
                intent.putExtra("p_job7",p_job7);
                intent.putExtra("p_job8",p_job8);
                intent.putExtra("p_job9",p_job9);
                intent.putExtra("p_cust_name",p_cus_name);
                intent.putExtra("p_cust_name1",p_cus_name1);
                intent.putExtra("p_cust_name2",p_cus_name2);
                intent.putExtra("p_cust_name3",p_cus_name3);
                intent.putExtra("p_cust_name4",p_cus_name4);
                intent.putExtra("p_cust_name5",p_cus_name5);
                intent.putExtra("p_cust_name6",p_cus_name6);
                intent.putExtra("p_cust_name7",p_cus_name7);
                intent.putExtra("p_cust_name8",p_cus_name8);
                intent.putExtra("p_cust_name9",p_cus_name9);
                intent.putExtra("p_f_date", p_f_date);
                intent.putExtra("p_f_date1", p_f_date1);
                intent.putExtra("p_f_date2", p_f_date2);
                intent.putExtra("p_f_date3", p_f_date3);
                intent.putExtra("p_f_date4", p_f_date4);
                intent.putExtra("p_f_date5", p_f_date5);
                intent.putExtra("p_f_date6", p_f_date6);
                intent.putExtra("p_f_date7", p_f_date7);
                intent.putExtra("p_f_date8", p_f_date8);
                intent.putExtra("p_f_date9", p_f_date9);
                intent.putExtra("p_t_date", p_t_date);
                intent.putExtra("p_t_date1", p_t_date1);
                intent.putExtra("p_t_date2", p_t_date2);
                intent.putExtra("p_t_date3", p_t_date3);
                intent.putExtra("p_t_date4", p_t_date4);
                intent.putExtra("p_t_date5", p_t_date5);
                intent.putExtra("p_t_date6", p_t_date6);
                intent.putExtra("p_t_date7", p_t_date7);
                intent.putExtra("p_t_date8", p_t_date8);
                intent.putExtra("p_t_date9", p_t_date9);
                intent.putExtra("p_pay_amt", p_pay_amt);
                intent.putExtra("p_pay_amt1", p_pay_amt1);
                intent.putExtra("p_pay_amt2", p_pay_amt2);
                intent.putExtra("p_pay_amt3", p_pay_amt3);
                intent.putExtra("p_pay_amt4", p_pay_amt4);
                intent.putExtra("p_pay_amt5", p_pay_amt5);
                intent.putExtra("p_pay_amt6", p_pay_amt6);
                intent.putExtra("p_pay_amt7", p_pay_amt7);
                intent.putExtra("p_pay_amt8", p_pay_amt8);
                intent.putExtra("p_pay_amt9", p_pay_amt9);
                context.startActivity(intent);
            }
        });

    }
    @Override
    public int getItemCount() {
        return breedTypedataBeanList.size();
    }

//    public void filterList(List<RTGS_PopResponse.DataBean> breedTypedataBeanListFiltered) {
//        breedTypedataBeanList = breedTypedataBeanListFiltered;
//        Log.w(TAG,"breedTypedataBeanList : "+new Gson().toJson(breedTypedataBeanList));
//
//        notifyDataSetChanged();
//    }




    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public void filterList(List<RTGS_PopResponse.DataBean> filteredlist) {

        breedTypedataBeanList = filteredlist;
        Log.w(TAG,"breedTypedataBeanList : "+new Gson().toJson(breedTypedataBeanList));
        notifyDataSetChanged();
    }

    static class ViewHolderOne extends RecyclerView.ViewHolder {
        TextView textView_pop, textView1_pop, textView2_pop, textView3_pop, textView4_pop, textView5_pop;
        ImageView iv;
        LinearLayout lin;

        public ViewHolderOne(View itemView) {
            super(itemView);

            textView_pop = (TextView) itemView.findViewById(R.id.textView_pop);
            textView1_pop = (TextView) itemView.findViewById(R.id.textView1_pop);
            textView2_pop = (TextView) itemView.findViewById(R.id.textView2_pop);
            textView3_pop = (TextView) itemView.findViewById(R.id.textView3_pop);
            textView4_pop = (TextView) itemView.findViewById(R.id.textView4_pop);
            textView5_pop = (TextView) itemView.findViewById(R.id.textView5_pop);
            lin = (LinearLayout) itemView.findViewById(R.id.lin);
        }

    }
}
