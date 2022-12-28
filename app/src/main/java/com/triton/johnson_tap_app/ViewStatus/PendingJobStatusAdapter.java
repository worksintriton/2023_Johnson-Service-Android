package com.triton.johnson_tap_app.ViewStatus;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.triton.johnson_tap_app.R;
import com.triton.johnson_tap_app.responsepojo.ViewStatusResponse;

import java.util.ArrayList;
import java.util.List;

public class PendingJobStatusAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context context;
    ViewStatusResponse.Data.PendingData currentItem;
    List<ViewStatusResponse.Data.PendingData > breedTypedataBeanList;
    String str_Jobno,str_StartTime,str_EndTime;
    ArrayList<String> arrayListJobID = new ArrayList<>();
    ArrayList<String> arrayListSTime = new ArrayList<>();
    ArrayList<String> arrayListETime = new ArrayList<>();
    SharedPreferences sharedPreferences;

    public PendingJobStatusAdapter(Job_StatusActivity job_statusActivity, List<ViewStatusResponse.Data.PendingData> pendingDataList) {

        this.context = job_statusActivity;
        this.breedTypedataBeanList = pendingDataList;

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_jobsstatus, parent, false);
        return new ViewHolderOne(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        initLayoutOne((ViewHolderOne) holder, position);
    }

    @SuppressLint("SetTextI18n")
    private void initLayoutOne(ViewHolderOne holder, int position) {

        currentItem = breedTypedataBeanList.get(position);

        Log.e("Service Name" ,"" +currentItem.getService_name());

        holder.txt_Servicename.setText(currentItem.getService_name());
        holder.txt_Counts.setText("(" + currentItem.getCount() + ")");

        holder.rel_Service.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                arrayListJobID.clear();
                arrayListSTime.clear();
                arrayListETime.clear();

                String servicename = breedTypedataBeanList.get(position).getService_name();
                Log.e("On Click Service Name" ,"" +servicename);
                Integer count = breedTypedataBeanList.get(position).getCount();

                Log.e("Size Data" ,"" + breedTypedataBeanList.get(position).getData().size());

                for (int i = 0; i < breedTypedataBeanList.get(position).getData().size(); i++) {

                    str_Jobno = breedTypedataBeanList.get(position).getData().get(i).getJob_id();
                    str_StartTime = breedTypedataBeanList.get(position).getData().get(i).getStart_time();
                    str_EndTime = breedTypedataBeanList.get(position).getData().get(i).getEnd_time();
                    arrayListJobID.add(str_Jobno);
                    arrayListSTime.add(str_StartTime);
                    arrayListETime.add(str_EndTime);
                    Log.e("JOB LIST", "" + arrayListJobID);
                }

                Intent n_act = new Intent(context, JobStatusList_Activity.class);
                n_act.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("Job_ID", String.valueOf(arrayListJobID));
                editor.putString("StartTime", String.valueOf(arrayListSTime));
                editor.putString("EndTime", String.valueOf(arrayListETime));
                editor.putInt("Count",count);
                editor.apply();
                context.startActivity(n_act);
            }
        });
    }

    @Override
    public int getItemCount() {
        return breedTypedataBeanList.size();
    }

    private class ViewHolderOne extends RecyclerView.ViewHolder {
        TextView txt_Servicename,txt_Counts;
        RelativeLayout rel_Service;
        public ViewHolderOne(View view) {
            super(view);
            txt_Servicename = view.findViewById(R.id.txt_servicename);
            txt_Counts = view.findViewById(R.id.txt_count);
            rel_Service = view.findViewById(R.id.rel_service);
        }
    }
}
