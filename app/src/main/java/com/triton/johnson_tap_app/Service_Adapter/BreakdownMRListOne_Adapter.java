package com.triton.johnson_tap_app.Service_Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.triton.johnson_tap_app.Db.CommonUtil;
import com.triton.johnson_tap_app.R;
import com.triton.johnson_tap_app.Service_Activity.BreakdownMRApprovel.BreakdownMRListOne_Activity;

import java.util.ArrayList;

public class BreakdownMRListOne_Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context context;
    ArrayList<String> arliPartid = new ArrayList<>();
    ArrayList<String> arliPartname = new ArrayList<>();
    ArrayList<String> arliPartno = new ArrayList<>();
    ArrayList<String> arliQuantity= new ArrayList<>();
    AlertDialog.Builder builder;


    public BreakdownMRListOne_Adapter(ArrayList<String> arli_partid, ArrayList<String> arli_partname, ArrayList<String> arli_partno, ArrayList<String> arli_Quantity, Context context) {

        this.context = context;
        this.arliPartid = arli_partid;
        this.arliPartname = arli_partname;
        this.arliPartno =arli_partno;
        this.arliQuantity = arli_Quantity;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_breakdownmrlistone, parent, false);
        return new ViewHolderOne(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        initLayoutOne((ViewHolderOne) holder, position);
    }

    private void initLayoutOne(ViewHolderOne holder, int position) {

        String S = arliPartid.get(position);

        holder.partname.setText(arliPartname.get(position));
        holder.partno.setText(arliPartno.get(position));
        holder.quantity.setText(arliQuantity.get(position));
        holder.partid.setText(arliPartid.get(position));

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                builder = new AlertDialog.Builder(context);

                //builder.setMessage("Delete Alert") .setTitle("Delete");

                builder.setMessage("Are You sure want to delete this Item ?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                CommonUtil.dbUtil.deleteMR(holder.partid.getText().toString());
                                //Intent send = new Intent(context, BreakdownMRListOne_Activity.class);
                                //context.startActivity(send);
                                arliPartid.remove(position);
                                notifyDataSetChanged();

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

    @Override
    public int getItemCount() {

        int limit = 10;

        if (arliPartid.size()>10){
            return limit;
        }else{
            return arliPartid.size();
        }

    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public class ViewHolderOne extends RecyclerView.ViewHolder {

        public LinearLayout lin_job_item;
        public TextView partno,partname,quantity,partid;
        ImageView delete;

        public ViewHolderOne(View itemview) {
            super(itemview);

            lin_job_item = itemView.findViewById(R.id.lin_job_item);
            partname = itemView.findViewById(R.id.partname);
            partno = itemView.findViewById(R.id.partno);
            quantity = itemview.findViewById(R.id.quantity);
            partid = itemview.findViewById(R.id.part_id);
            delete = itemview.findViewById(R.id.delete);
        }
    }
}
