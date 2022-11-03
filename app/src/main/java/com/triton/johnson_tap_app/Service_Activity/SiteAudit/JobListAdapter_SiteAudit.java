package com.triton.johnson_tap_app.Service_Activity.SiteAudit;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.triton.johnson_tap_app.PetBreedTypeSelectListener;
import com.triton.johnson_tap_app.R;
import com.triton.johnson_tap_app.responsepojo.JobListResponse;

import java.util.ArrayList;
import java.util.List;

public class JobListAdapter_SiteAudit extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context context;
    String status,service_title;
    List<JobListResponse.DataBean> breedTypedataBeanList;
    PetBreedTypeSelectListener petBreedTypeSelectListener;
    JobListResponse.DataBean currentItem;
    SharedPreferences sharedPreferences;
    private ArrayList<String> arliJobid;
    private ArrayList<String> arliCustname;
    private ArrayList<String> arliAuditdate;

    public JobListAdapter_SiteAudit(Context applicationContext, List<JobListResponse.DataBean> breedTypedataBeanList, String status) {

        this.context = applicationContext;
        this.breedTypedataBeanList = breedTypedataBeanList;
        //   this.petBreedTypeSelectListener = petBreedTypeSelectListener;
        this.status = status;
//        this.arliJobid = arli_jobid;
//        this.arliCustname = arli_custname;
//        this.arliAuditdate = arli_auditdate;

        Log.e("Status", "" + status);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        service_title = sharedPreferences.getString("service_title", "Services");

        Log.e("Name" , " " +service_title);


    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_jobdetails_siteaudit, parent, false);
        return new ViewHolderOne(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        initLayoutOne((ViewHolderOne) holder, position);

    }

    private void initLayoutOne(ViewHolderOne holder, int position) {

//         String jobid = arliJobid.get(position);
//          Log.e("data" , "" + jobid);
//          holder.txt_JobID.setText(jobid);
//        String custname = arliCustname.get(position);
//        Log.e("data" , "" + custname);
//        holder.txt_Custname.setText(custname);
//        String auditdate = arliAuditdate.get(position);
//        Log.e("data" , "" + auditdate);
//        holder.txt_Auditdate.setText(auditdate);

        currentItem = breedTypedataBeanList.get(position);

        holder.txt_JobID.setText(currentItem.getJob_id());
        holder.txt_Custname.setText(currentItem.getName());
        holder.txt_Auditdate.setText(currentItem.getDate());


        holder.cv_Jobid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String jobid = breedTypedataBeanList.get(position).getJob_id();
                String osa_compno = breedTypedataBeanList.get(position).getOM_OSA_COMPNO();
                Log.e("A",""+jobid);
                Log.e("osacompno",""+osa_compno);

                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("osacompno", osa_compno);
                editor.putString("jobid",jobid);
                editor.apply();

                Intent intent = new Intent(context,AD_DetailsSiteAudit_Activity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("status", status);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return breedTypedataBeanList.size();
    }

    public void filterrList(List<JobListResponse.DataBean> filterlist) {
        breedTypedataBeanList = filterlist;
        notifyDataSetChanged();

    }

    private class ViewHolderOne extends RecyclerView.ViewHolder {

        TextView txt_JobID,txt_Custname, txt_Auditdate;
        CardView cv_Jobid;
        public ViewHolderOne(View view) {
            super(view);

            txt_JobID = view.findViewById(R.id.txt_jobid);
            txt_Custname = view.findViewById(R.id.txt_custname);
            txt_Auditdate = view.findViewById(R.id.txt_auditdate);
            cv_Jobid = view.findViewById(R.id.cv_job);
        }
    }
}
