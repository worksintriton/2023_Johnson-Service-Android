package com.triton.johnson_tap_app.Service_Activity.Preventive_Services;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.triton.johnson_tap_app.PetBreedTypeSelectListener;
import com.triton.johnson_tap_app.R;
import com.triton.johnson_tap_app.Service_Activity.Breakdown_Services.Customer_DetailsActivity;
import com.triton.johnson_tap_app.Service_Adapter.PasusedListAdapter;
import com.triton.johnson_tap_app.responsepojo.Pasused_ListResponse;

import java.util.List;

public class PausedListAdapter_Preventive extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private  String TAG = "PetBreedTypesListAdapter";
    Context context;
    Pasused_ListResponse.DataBean currentItem;
    private List<Pasused_ListResponse.DataBean> dataBeanList;
    private PetBreedTypeSelectListener petBreedTypeSelectListener;
    String status;

    public PausedListAdapter_Preventive(Context applicationContext, List<Pasused_ListResponse.DataBean> mdataBeanList, Job_Details_PreventiveActivity job_details_preventiveActivity, String mystatus) {

        this.context = applicationContext;
        this.dataBeanList = mdataBeanList;
        this.petBreedTypeSelectListener = petBreedTypeSelectListener;
        this.status = mystatus;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.services_item2, parent, false);
        return new ViewHolderOne(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        initLayoutOne((ViewHolderOne) holder, position);
    }

    private void initLayoutOne(ViewHolderOne holder, int position) {

      //  if(currentItem.getJob_id() != null){
//            holder.job_id.setText(currentItem.getJob_id());
//            holder.paused_time.setText(currentItem.getPaused_time());
//            holder.paused_at.setText(currentItem.getPaused_at());


      //  }
        String jobid = dataBeanList.get(position).getJob_id();
        String paused_time = dataBeanList.get(position).getPaused_time();
        String paused_at = dataBeanList.get(position).getPaused_at();
        Log.e("A",""+jobid);
        Log.e("A",""+paused_time);
        Log.e("A",""+paused_at);
        holder.job_id.setText(jobid);
        holder.paused_time.setText(paused_time);
        holder.paused_at.setText(paused_at);

        holder.ll_root.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                dataBeanList.get(position).getJob_id();

//                String s = breedTypedataBeanList.get(position).getService_name();
//                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
//                SharedPreferences.Editor editor = sharedPreferences.edit();
//                editor.putString("service_title", s);
//                editor.apply();

                //  Toast.makeText(context, "value" + s, Toast.LENGTH_LONG).show();

                String jobid = dataBeanList.get(position).getJob_id();
                String compno = dataBeanList.get(position).getSMU_SCH_COMPNO();
                String sertype = dataBeanList.get(position).getSMU_SCH_SERTYPE();

                Log.e("A",""+compno);
                Log.e("A",""+sertype);


                Intent n_act = new Intent(context, Customer_Details_preActivity.class);
                n_act.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                n_act.putExtra("status",status);
                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                //  editor.putString("service_title", service_title);
                editor.putString("compno",compno);
                editor.putString("sertype", sertype);
                editor.putString("job_id",jobid);
                editor.apply();
                context.startActivity(n_act);
            }
        });


    }

    @Override
    public int getItemCount() {
        return dataBeanList.size();
    }

    private class ViewHolderOne extends RecyclerView.ViewHolder {

        public TextView job_id,paused_time,paused_at;
        public LinearLayout ll_root;
        public ViewHolderOne(View view) {
            super(view);
            job_id = itemView.findViewById(R.id.text);
            paused_time = itemView.findViewById(R.id.pasused_time);
            paused_at = itemView.findViewById(R.id.pasused_at);
            ll_root = itemView.findViewById(R.id.lin_services_item1);
        }
    }
}
