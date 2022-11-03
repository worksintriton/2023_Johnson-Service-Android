package com.triton.johnson_tap_app.Service_Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.triton.johnson_tap_app.Db.CommonUtil;
import com.triton.johnson_tap_app.Db.DbHelper;
import com.triton.johnson_tap_app.Db.DbUtil;
import com.triton.johnson_tap_app.R;
import com.triton.johnson_tap_app.UserTypeSelectListener1;
import com.triton.johnson_tap_app.responsepojo.Feedback_DetailsResponse;

import java.util.ArrayList;
import java.util.List;


public class Feedback_DetailsAdapter extends  RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private  String TAG = "PetBreedTypesListAdapter";
    private Context context;
    Feedback_DetailsResponse.DataBean currentItem;
    private List<Feedback_DetailsResponse.DataBean> breedTypedataBeanList;
    private UserTypeSelectListener1 userTypeSelectListener;
    SharedPreferences sharedPreferences;
    String jobid,service_title;
    ArrayList<String> myData = new ArrayList<>();
    boolean isStringExists = false;

    public Feedback_DetailsAdapter(Context thiscontext, List<Feedback_DetailsResponse.DataBean> breedTypedataBeanList, UserTypeSelectListener1 userTypeSelectListener, ArrayList<String> mydata) {
        this.context = thiscontext;
        this.breedTypedataBeanList = breedTypedataBeanList;
        this.userTypeSelectListener = userTypeSelectListener;
        this.myData = mydata;


        CommonUtil.dbUtil = new DbUtil(context);
        CommonUtil.dbUtil.open();
        CommonUtil.dbHelper = new DbHelper(context);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        jobid = sharedPreferences.getString("job_id", "default value");
        service_title = sharedPreferences.getString("service_title", "default value");

        Log.e("JobID",""+jobid);
        Log.e("Name",""+service_title);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bd_card, parent, false);
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

        if(currentItem.getTitle() != null){
            holder.text_title.setText(currentItem.getTitle());
        }

      //  holder.chkSelected.setChecked(breedTypedataBeanList.get(position).isSelected());

       // holder.chkSelected.setTag(breedTypedataBeanList.get(position));


        String feedback = holder.text_title.getText().toString();
        String myfeedback = myData.toString();

        Log.e("Data Get 1",""+myfeedback);

        isStringExists = myfeedback.contains(feedback);
        Log.e("isChecked",""+isStringExists);

        if(isStringExists){
            Log.e("Nish","inside" + holder.text_title.getText().toString());
            //holder.chkSelected.setSelected(true);
            // breedTypedataBeanList.get(position).setSelected(true);
            holder.chkSelected.setChecked(true);
        }

        holder.chkSelected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String data = "";
                breedTypedataBeanList.get(position).setSelected(true);
                data = data + "\n" + breedTypedataBeanList.get(position).getTitle();
                Log.d("ssss", data);

                if (CommonUtil.dbUtil.hasFeedbackDesc(jobid,service_title,data,"3")){
                    Log.e("Nish","inside");
                    Log.e("Hi Nish","Had Data");
                    CommonUtil.dbUtil.deleteFeedbackDesc(jobid,service_title,data,"3");
                    Cursor cur = CommonUtil.dbUtil.getFeedbackDesc(jobid,service_title, "3");
                    Log.e("COunt",""+cur.getCount());
                }else{
                    Log.e("Nish 1","inside");
                    CommonUtil.dbUtil.addFeedbackDesc(jobid,service_title,data,"3");
                    Cursor cur = CommonUtil.dbUtil.getFeedbackDesc(jobid,service_title,"3");
                    Log.e("COunt",""+cur.getCount());
                    Log.e("Hi Nish","No Data");
                }
            }
        });

    }
    @Override
    public int getItemCount() {
        return breedTypedataBeanList.size();
    }

    public void filterList(List<Feedback_DetailsResponse.DataBean> breedTypedataBeanListFiltered) {
        breedTypedataBeanList = breedTypedataBeanListFiltered;
        Log.w(TAG,"breedTypedataBeanList : "+new Gson().toJson(breedTypedataBeanList));

        notifyDataSetChanged();
    }




    @Override
    public int getItemViewType(int position) {
        return position;
    }

    static class ViewHolderOne extends RecyclerView.ViewHolder {
        public TextView text_title,last_used_time,upload_count,pending_count,failure_count,paused_count,jobs_count;
        public LinearLayout ll_root;
        public CheckBox chkSelected;

        public ViewHolderOne(View itemView) {
            super(itemView);

            text_title = itemView.findViewById(R.id.tvName);
            chkSelected = (CheckBox) itemView.findViewById(R.id.chkSelected);
        }

    }
}
