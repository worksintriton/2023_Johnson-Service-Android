package com.triton.johnson_tap_app.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.triton.johnson_tap_app.PetBreedTypeSelectListener;
import com.triton.johnson_tap_app.R;
import com.triton.johnson_tap_app.Service_Activity.LR_Service.JobListAdapter_LRService;
import com.triton.johnson_tap_app.responsepojo.JobListResponse;
import com.triton.johnson_tap_app.responsepojo.NotificationListResponse;

import java.util.List;

public class NotificationListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context context;
    String status,service_title;
    List<NotificationListResponse.NotificationData> notificationDataList;
    PetBreedTypeSelectListener petBreedTypeSelectListener;
    NotificationListResponse.NotificationData currentItem;
    SharedPreferences sharedPreferences;

    public NotificationListAdapter(Notification_Activity notification_activity, List<NotificationListResponse.NotificationData> notificationDataList) {

        this.context = notification_activity;
        this.notificationDataList = notificationDataList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_notificationlist, parent, false);
        return new ViewHolderOne(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        initLayoutOne((ViewHolderOne) holder, position);
    }

    private void initLayoutOne(ViewHolderOne holder, int position) {

        currentItem = notificationDataList.get(position);

        if (currentItem.getNotification_desc()!= null){

            holder.txt_Title.setText(currentItem.getNotification_title());
            holder.txt_JobID.setText(currentItem.getNotification_desc());

            String Date = currentItem.getDate_and_time();
            Log.e("Start Time 1",Date);
            Date = Date.replaceAll("[^0-9-:]", " ");
            Log.e("Start Time",Date);
            holder.txt_Date.setText(Date);
        }
    }

    @Override
    public int getItemCount() {
        return notificationDataList.size();
    }

    private class ViewHolderOne extends RecyclerView.ViewHolder {

        TextView txt_Title , txt_JobID, txt_Date;
        public ViewHolderOne(View view) {
            super(view);
            txt_Title = view.findViewById(R.id.txt_title);
            txt_Date = view.findViewById(R.id.txt_date);
            txt_JobID = view.findViewById(R.id.txt_jobid);
        }
    }
}
