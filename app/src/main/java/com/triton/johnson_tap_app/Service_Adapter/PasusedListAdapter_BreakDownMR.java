package com.triton.johnson_tap_app.Service_Adapter;

import android.annotation.SuppressLint;
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

import com.google.gson.Gson;
import com.triton.johnson_tap_app.PetBreedTypeSelectListener;
import com.triton.johnson_tap_app.R;
import com.triton.johnson_tap_app.Service_Activity.BreakdownMRApprovel.CustomerDetailsBreakdownMR_Activity;
import com.triton.johnson_tap_app.responsepojo.Pasused_ListResponse;

import java.util.List;


public class PasusedListAdapter_BreakDownMR extends  RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private  String TAG = "PetBreedTypesListAdapter";
    private Context context;
    Pasused_ListResponse.DataBean currentItem;
    private List<Pasused_ListResponse.DataBean> breedTypedataBeanList;
    private PetBreedTypeSelectListener petBreedTypeSelectListener;
     String service_title, status;

    public PasusedListAdapter_BreakDownMR(Context context, List<Pasused_ListResponse.DataBean> breedTypedataBeanList, PetBreedTypeSelectListener petBreedTypeSelectListener, String status) {
        this.context = context;
        this.breedTypedataBeanList = breedTypedataBeanList;
       // this.service_title = serviceTitle;
        this.status = status;
        //this.petBreedTypeSelectListener = petBreedTypeSelectListener;
        Log.e("Status", "" + status);
      //  Log.e("Name" , service_title);

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

    @SuppressLint("LogNotTimber")
    private void initLayoutOne(ViewHolderOne holder, final int position) {
        currentItem = breedTypedataBeanList.get(position);

//        Intent intent = new Intent("message_subject_intent");
//        intent.putExtra("cust_name" , currentItem.getCUSNAME());
//        intent.putExtra("cont_no" , currentItem.getCONTNO());
//        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);

        if(currentItem.getJob_id() != null){
            holder.job_id.setText(currentItem.getJob_id());
            holder.paused_time.setText(currentItem.getPaused_time());
            holder.paused_at.setText(currentItem.getPaused_at());

            Log.e("JobId Pause", "" +holder.job_id.getText().toString());

          //  Log.e("JobID", ":" + );
        }

        holder.ll_root.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                breedTypedataBeanList.get(position).getJob_id();

               String s = breedTypedataBeanList.get(position).getJob_id();
               // String pausedtime = breedTypedataBeanList.get(position).getPaused_time();
               // String pausedat  = breedTypedataBeanList.get(position).getPaused_at();

                String compno = breedTypedataBeanList.get(position).getSMU_SCH_COMPNO();
                String sertype = breedTypedataBeanList.get(position).getSMU_SCH_SERTYPE();

                Log.e("JobID", ":" + s );
               // Log.e("pausedtime", ":" + pausedtime );
               // Log.e("pausedat", ":" + pausedat );


                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("job_id",s);
                editor.putString("compno",compno);
                editor.putString("sertype", sertype);
                editor.apply();

              //  Toast.makeText(context, "value" + s, Toast.LENGTH_LONG).show();

                Intent n_act = new Intent(context, CustomerDetailsBreakdownMR_Activity.class);
                n_act.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                n_act.putExtra("job_id", s);
                n_act.putExtra("status", status);
                n_act.putExtra("service_title",service_title);
                context.startActivity(n_act);

            }
       });




    }
    @Override
    public int getItemCount() {
        return breedTypedataBeanList.size();
    }

    public void filterList(List<Pasused_ListResponse.DataBean> breedTypedataBeanListFiltered) {
        breedTypedataBeanList = breedTypedataBeanListFiltered;
        Log.w(TAG,"breedTypedataBeanList : "+new Gson().toJson(breedTypedataBeanList));

        notifyDataSetChanged();
    }




    @Override
    public int getItemViewType(int position) {
        return position;
    }

    static class ViewHolderOne extends RecyclerView.ViewHolder {
        public TextView job_id,paused_time,paused_at;
        public LinearLayout ll_root;

        public ViewHolderOne(View itemView) {
            super(itemView);

            job_id = itemView.findViewById(R.id.text);
            paused_time = itemView.findViewById(R.id.pasused_time);
            paused_at = itemView.findViewById(R.id.pasused_at);
            ll_root = itemView.findViewById(R.id.lin_services_item1);

        }

    }
}
