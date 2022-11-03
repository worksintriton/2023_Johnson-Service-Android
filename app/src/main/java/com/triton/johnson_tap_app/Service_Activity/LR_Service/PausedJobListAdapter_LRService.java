package com.triton.johnson_tap_app.Service_Activity.LR_Service;

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
import com.triton.johnson_tap_app.responsepojo.Pasused_ListResponse;

import java.util.List;

public class PausedJobListAdapter_LRService extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context context;
    String status,service_title;
    List<Pasused_ListResponse.DataBean> dataBeanList;
    PetBreedTypeSelectListener petBreedTypeSelectListener;
    Pasused_ListResponse.DataBean currentItem;
    SharedPreferences sharedPreferences;
    public PausedJobListAdapter_LRService(Context applicationContext, List<Pasused_ListResponse.DataBean> databeanList, String status) {

        this.context = applicationContext;
        this.dataBeanList = databeanList;
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


        initLayoutOne((PausedJobListAdapter_LRService.ViewHolderOne) holder, position);
    }

    private void initLayoutOne(ViewHolderOne holder, int position) {

        currentItem = dataBeanList.get(position);

        //  if(currentItem.getJob_id() != null){
        holder.job_id.setText(currentItem.getJob_id());
        //  }

        holder.cv_Jobid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String s = dataBeanList.get(position).getJob_id();
                String Quoteno = dataBeanList.get(position).getSMU_SCQH_QUOTENO();
//                String compno = breedTypedataBeanList.get(position).getSMU_SCH_COMPNO();
//                String sertype = breedTypedataBeanList.get(position).getSMU_SCH_SERTYPE();

                // Log.e("A",""+compno);
                //  Log.e("A",""+sertype);
                Log.e("A",""+s);
                Log.e("A","" +Quoteno);


                SharedPreferences.Editor editor = sharedPreferences.edit();
                //   editor.putString("compno",compno);
                //  editor.putString("sertype", sertype);
                editor.putString("quoteno", Quoteno);
                editor.apply();

                Intent n_act = new Intent(context, LR_Details_Activity.class);
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
        return dataBeanList.size();
    }

    private class ViewHolderOne extends RecyclerView.ViewHolder {

        TextView job_id;
        CardView cv_Jobid;
        public ViewHolderOne(View view) {

            super(view);
            job_id = view.findViewById(R.id.txt_jobid);
            cv_Jobid = view.findViewById(R.id.cv_jobid);
        }
    }
}
