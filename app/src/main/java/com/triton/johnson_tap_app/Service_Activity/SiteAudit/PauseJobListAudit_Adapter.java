package com.triton.johnson_tap_app.Service_Activity.SiteAudit;

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
import com.triton.johnson_tap_app.Service_Adapter.PasusedListAdapter;
import com.triton.johnson_tap_app.responsepojo.PauseJobListAuditResponse;

import java.util.List;

public class PauseJobListAudit_Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private  String TAG = "PetBreedTypesListAdapter",service_title ;
    private Context context;
    SharedPreferences sharedPreferences;
    PauseJobListAuditResponse.PauseData currentItem;
    private List<PauseJobListAuditResponse.PauseData> breedTypedataBeanList;
    private PetBreedTypeSelectListener petBreedTypeSelectListener;
    String status;

    public PauseJobListAudit_Adapter(Context applicationContext, List<PauseJobListAuditResponse.PauseData> databeanList, String mystatus) {

        this.context = applicationContext;
        this.breedTypedataBeanList = databeanList;
        this.petBreedTypeSelectListener = petBreedTypeSelectListener;
        this.status = mystatus;

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        service_title = sharedPreferences.getString("service_title", "Services");

        Log.e("Name" , " " +service_title);
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

        currentItem = breedTypedataBeanList.get(position);

        if(currentItem.getJob_id() != null){
            holder.job_id.setText(currentItem.getJob_id());
            holder.paused_time.setText(currentItem.getDate());
            holder.paused_at.setText("Site Audit");
        }

        holder.ll_root.setOnClickListener(new View.OnClickListener() {
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
