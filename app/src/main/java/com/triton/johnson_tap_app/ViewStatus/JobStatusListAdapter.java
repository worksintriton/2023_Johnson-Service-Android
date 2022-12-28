package com.triton.johnson_tap_app.ViewStatus;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.triton.johnson_tap_app.R;

import java.util.ArrayList;

public class JobStatusListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    ArrayList<String> Ar_JobID = new ArrayList<>();
    ArrayList<String> Ar_StartTime = new ArrayList<>();
    ArrayList<String> Ar_EndTime = new ArrayList<>();

    public JobStatusListAdapter(JobStatusList_Activity completedJobList_activity, ArrayList<String> ar_JobID, ArrayList<String> ar_StartTime, ArrayList<String> ar_EndTime) {
        this.context = completedJobList_activity;
        this.Ar_JobID = ar_JobID;
        this.Ar_StartTime = ar_StartTime;
        this.Ar_EndTime = ar_EndTime;

        Log.e("Job No",""+Ar_JobID);

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_jobstatuslist, parent, false);
        return new ViewHolderOne(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        initLayoutOne((ViewHolderOne) holder, position);

    }

    private void initLayoutOne(ViewHolderOne holder, int position) {

        holder.txt_JobId.setText(Ar_JobID.get(position));
        holder.txt_StartTime.setText(Ar_StartTime.get(position));
        holder.txt_EndTime.setText(Ar_EndTime.get(position));
    }

    @Override
    public int getItemCount() {
        return Ar_JobID.size();
    }

    private class ViewHolderOne extends RecyclerView.ViewHolder {

        TextView txt_JobId,txt_StartTime, txt_EndTime;
        public ViewHolderOne(View view) {
            super(view);
            txt_JobId = view.findViewById(R.id.txt_jobid);
            txt_StartTime = view.findViewById(R.id.txt_starttime);
            txt_EndTime = view.findViewById(R.id.txt_endtime);
        }
    }
}
