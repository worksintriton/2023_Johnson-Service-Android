package com.triton.johnson_tap_app.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
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
import com.triton.johnson_tap_app.responsepojo.Pasused_ListResponse;
import com.triton.johnson_tap_app.responsepojo.ViewStatusResponse;

import java.util.List;


public class ViewStatusAdapter extends  RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private  String TAG = "PetBreedTypesListAdapter";
    private Context context;
    ViewStatusResponse.Data currentItem;
    private List<ViewStatusResponse.Data> breedTypedataBeanList;
    private PetBreedTypeSelectListener petBreedTypeSelectListener;

    public ViewStatusAdapter(Context context, List<ViewStatusResponse.Data> breedTypedataBeanList, PetBreedTypeSelectListener petBreedTypeSelectListener ) {
        this.context = context;
        this.breedTypedataBeanList = breedTypedataBeanList;
        this.petBreedTypeSelectListener = petBreedTypeSelectListener;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_status, parent, false);
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

//        if(currentItem.getStatus_title() != null){
//            holder.job_id.setText(currentItem.getStatus_title());

           // Log.d("ssss", String.valueOf(currentItem.getService_listdata()));
//            holder.service_name.setText(currentItem.getS());
//            holder.count.setText(currentItem.getPaused_at());
//        }

//        holder.ll_root.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//
//                breedTypedataBeanList.get(position).getStatus_title();
//
////                String s = breedTypedataBeanList.get(position).getService_name();
////                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
////                SharedPreferences.Editor editor = sharedPreferences.edit();
////                editor.putString("service_title", s);
////                editor.apply();
//
//              //  Toast.makeText(context, "value" + s, Toast.LENGTH_LONG).show();
//
////                Intent n_act = new Intent(context, Breakdown_ServiceActivity.class);
////                n_act.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
////                n_act.putExtra("service_title", s);
////                context.startActivity(n_act);
//            }
//        });


    }
    @Override
    public int getItemCount() {
        return breedTypedataBeanList.size();
    }

    public void filterList(List< ViewStatusResponse.Data> breedTypedataBeanListFiltered) {
        breedTypedataBeanList = breedTypedataBeanListFiltered;
        Log.w(TAG,"breedTypedataBeanList : "+new Gson().toJson(breedTypedataBeanList));

        notifyDataSetChanged();
    }




    @Override
    public int getItemViewType(int position) {
        return position;
    }

    static class ViewHolderOne extends RecyclerView.ViewHolder {
        public TextView job_id,service_name,count;
        public LinearLayout ll_root;

        public ViewHolderOne(View itemView) {
            super(itemView);

            job_id = itemView.findViewById(R.id.text);
//            ll_root = itemView.findViewById(R.id.lin_services_item1);

        }

    }
}
