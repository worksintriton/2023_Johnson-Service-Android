package com.triton.johnson_tap_app.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.triton.johnson_tap_app.MyListData1;
import com.triton.johnson_tap_app.R;
import com.triton.johnson_tap_app.Service_Activity.Breakdown_Services.Customer_DetailsActivity;

public class MyListAdapter1 extends RecyclerView.Adapter<MyListAdapter1.ViewHolder>{

    private MyListData1[] listdata;
    private Context context;

    // RecyclerView recyclerView;
    public MyListAdapter1(MyListData1[] listdata, Context context) {
        this.listdata = listdata;
        this.context = context;
    }
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.services_item1, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final MyListData1 myListData = listdata[position];
        holder.textView.setText(listdata[position].getId());
        holder.textView1.setText(listdata[position].getName());

        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent send = new Intent(context, Customer_DetailsActivity.class);
                context.startActivity(send);
            }
        });

        holder.lin_job_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent send = new Intent(context, Customer_DetailsActivity.class);
                context.startActivity(send);
            }
        });

        holder.cv_job_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent send = new Intent(context, Customer_DetailsActivity.class);
                context.startActivity(send);
            }
        });
    }


    @Override
    public int getItemCount() {
        return listdata.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textView, textView1;
        LinearLayout lin_job_item;
        CardView cv_job_item;

        public ViewHolder(View itemView) {
            super(itemView);
            this.textView = (TextView) itemView.findViewById(R.id.text);
            this.textView1 = (TextView) itemView.findViewById(R.id.text1);
            this.lin_job_item = (LinearLayout) itemView.findViewById(R.id.lin_job_item);
            this.cv_job_item = (CardView) itemView.findViewById(R.id.cv_job_item);

        }
    }
}
