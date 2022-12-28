package com.triton.johnson_tap_app.Service_Activity.SiteAudit;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.triton.johnson_tap_app.Db.CommonUtil;
import com.triton.johnson_tap_app.Db.DbHelper;
import com.triton.johnson_tap_app.Db.DbUtil;
import com.triton.johnson_tap_app.R;
import com.triton.johnson_tap_app.Service_Activity.BreakdownMRApprovel.BreakdownMRListOne_Activity;
import com.triton.johnson_tap_app.interfaces.QuantityListener;

import java.util.ArrayList;
import java.util.Objects;

public class AuditMR_Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context context;
    ArrayList<String> arliPartid = new ArrayList<>();
    ArrayList<String> arliPartname = new ArrayList<>();
    ArrayList<String> arliPartno = new ArrayList<>();
    ArrayList<String> arliQuantity= new ArrayList<>();
    AlertDialog.Builder builder;
    QuantityListener getQuantityListner;
    String service_title,job_id;


    public AuditMR_Adapter(ArrayList<String> arli_partid, ArrayList<String> arli_partname, ArrayList<String> arli_partno, ArrayList<String> arli_Quantity, Context context, AuditMR_Activity getQuantityListner) {

        this.context = context;
        this.arliPartid = arli_partid;
        this.arliPartname = arli_partname;
        this.arliPartno =arli_partno;
        this.arliQuantity = arli_Quantity;
        this.getQuantityListner =  getQuantityListner;

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        service_title = sharedPreferences.getString("service_title", "default value");
        job_id = sharedPreferences.getString("jobid", "default value");
        Log.e("Name",""+ service_title);
        Log.e("Job ID",""+ job_id);

        CommonUtil.dbUtil = new DbUtil(context);
        CommonUtil.dbUtil.open();
        CommonUtil.dbHelper = new DbHelper(context);

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

 //       holder.delete.setVisibility(View.INVISIBLE);

        holder.txt_Partname.setText(arliPartname.get(position));
        holder.txt_Partno.setText(arliPartno.get(position));
        if (arliQuantity.get(position).equals("0") || arliQuantity.get(position).isEmpty()){

            holder.edt_Qty.setVisibility(View.VISIBLE);
            holder.edt_Qty.setText(arliQuantity.get(position));
        }
        else{
            holder.txt_Quantity.setVisibility(View.VISIBLE);
            holder.txt_Quantity.setText(arliQuantity.get(position));
        }

        holder.txt_Partid.setText(arliPartid.get(position));

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                builder = new AlertDialog.Builder(context);

                builder.setMessage("Are You sure want to delete this Item ?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                CommonUtil.dbUtil.deleteMRPartno(holder.txt_Partid.getText().toString());
                                if (context instanceof AuditMR_Activity) {
                                    ((AuditMR_Activity)context).getMRList();
                                }
//                                arliPartid.remove(position);
//                                notifyDataSetChanged();
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

        holder.edt_Qty.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String S = arliPartid.get(position);
                String partno = arliPartno.get(position);
                String partname = arliPartname.get(position);

                Log.e("On Quantity Update",""+S +" "+ partno +" "+ partname);

                getQuantityListner.onQuantityChange(holder.edt_Qty,s.toString(), Integer.parseInt(S));

                Log.e("On Quantity Update",""+holder.edt_Qty.getText().toString() +" "+ service_title +" "+ job_id);
                CommonUtil.dbUtil.UpdateMRList(partno,partname,"3",holder.edt_Qty.getText().toString(),S,service_title,job_id);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

//        holder.delete.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                builder = new AlertDialog.Builder(context);
//
//                //builder.setMessage("Delete Alert") .setTitle("Delete");
//
//                builder.setMessage("Are You sure want to delete this Item ?")
//                        .setCancelable(false)
//                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog, int id) {
//                                CommonUtil.dbUtil.deleteMR(holder.partid.getText().toString());
//                                //Intent send = new Intent(context, BreakdownMRListOne_Activity.class);
//                                //context.startActivity(send);
//                                arliPartid.remove(position);
//                                notifyDataSetChanged();
//
//                            }
//                        })
//                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog, int id) {
//                                dialog.cancel();
//                            }
//                        });
//
//                AlertDialog alert = builder.create();
//                //Setting the title manually
//                alert.show();
//
//
//            }
//        });
    }

    @Override
    public int getItemCount() {

//        int limit = 10;
//
//        if (arliPartid.size()>10){
//            return limit;
//        }else{
//            return arliPartid.size();
//        }
        return arliPartno.size();

    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public class ViewHolderOne extends RecyclerView.ViewHolder {

        public LinearLayout lin_job_item;
        public TextView txt_Partno, txt_Partname, txt_Quantity, txt_Partid;
        ImageView delete;
        EditText edt_Qty;

        public ViewHolderOne(View itemview) {
            super(itemview);

            lin_job_item = itemView.findViewById(R.id.lin_job_item);
            txt_Partname = itemView.findViewById(R.id.partname);
            txt_Partno = itemView.findViewById(R.id.partno);
            txt_Quantity = itemview.findViewById(R.id.quantity);
            txt_Partid = itemview.findViewById(R.id.part_id);
            delete = itemview.findViewById(R.id.delete);
            edt_Qty = itemview.findViewById(R.id.no);
        }
    }
}
