package com.triton.johnson_tap_app.Service_Activity.PartsReplacementACK;

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

public class JobListAdapter_ACKService  extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<String> arliJobid;
    Context context;
    String status,service_title;
    List<JobListResponse.DataBean> breedTypedataBeanList;
    PetBreedTypeSelectListener petBreedTypeSelectListener;
    JobListResponse.DataBean currentItem;
    SharedPreferences sharedPreferences;

    public JobListAdapter_ACKService(Context applicationContext, List<JobListResponse.DataBean> breedTypedataBeanList, String status) {

        this.context = applicationContext;
        this.breedTypedataBeanList = breedTypedataBeanList;
        //   this.petBreedTypeSelectListener = petBreedTypeSelectListener;
        this.status = status;

        Log.e("Status", "" + status);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        service_title = sharedPreferences.getString("service_title", "Services");

        Log.e("Name" , " " +service_title);

    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_jobdetails_lrservice, parent, false);
        return new ViewHolderOne(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        initLayoutOne((ViewHolderOne) holder, position);
    }

    private void initLayoutOne(ViewHolderOne holder, int position) {

//        String title = arliJobid.get(position);
//        Log.e("data" , "" + title);
//
//        holder.job_id.setText(title);

        currentItem = breedTypedataBeanList.get(position);


        holder.job_id.setText(currentItem.getJob_id());

        holder.cv_Jobid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String s = breedTypedataBeanList.get(position).getJob_id();
                String ACKCompno = breedTypedataBeanList.get(position).getSMU_ACK_COMPNO();

                Log.e("A",""+s);
                Log.e("ackcompno",""+ACKCompno);

                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("ackcompno", ACKCompno);
                editor.putString("job_id",s);
                editor.apply();

                Intent n_act = new Intent(context, MRDetails_Activity.class);
                n_act.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                n_act.putExtra("job_id", s);
                n_act.putExtra("status", status);
                //  n_act.putExtra("service_title",service_title);
                context.startActivity(n_act);
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

    public class ViewHolderOne extends RecyclerView.ViewHolder {

        TextView job_id;
        CardView cv_Jobid;
        public ViewHolderOne(View view) {
            super(view);

            job_id = view.findViewById(R.id.txt_jobid);
            cv_Jobid = view.findViewById(R.id.cv_jobid);
        }
    }
}
