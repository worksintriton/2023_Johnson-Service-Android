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
import com.triton.johnson_tap_app.responsepojo.Preventive_ChecklistResponse;

import java.util.ArrayList;
import java.util.List;


public class Preventive_ChecklistAdapter extends  RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private  String TAG = "PetBreedTypesListAdapter";
    private Context context;
    Preventive_ChecklistResponse.DataBean currentItem;
    private List<Preventive_ChecklistResponse.DataBean> breedTypedataBeanList;
    private UserTypeSelectListener1 userTypeSelectListener;
    private static CheckBox lastChecked = null;
    private static int lastCheckedPos = 0;
    String data = "",jobid,service_title;
    ArrayList<String> myData = new ArrayList<>();
    boolean isStringExists = false;
    SharedPreferences sharedPreferences;

    public Preventive_ChecklistAdapter(Context applicationcontext, List<Preventive_ChecklistResponse.DataBean> breedTypedataBeanList, UserTypeSelectListener1 userTypeSelectListener, ArrayList<String> mydata) {
        this.context = applicationcontext;
        this.breedTypedataBeanList = breedTypedataBeanList;
        this.userTypeSelectListener = userTypeSelectListener;
        this.myData = mydata;

        CommonUtil.dbUtil = new DbUtil(context);
        CommonUtil.dbUtil.open();
        CommonUtil.dbHelper = new DbHelper(context);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(applicationcontext);
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

        if(currentItem.getCheck_list_value() != null){
            holder.text_title.setText(currentItem.getCheck_list_value());
            holder.chkSelected.setChecked(breedTypedataBeanList.get(position).isSelected());
            holder.chkSelected.setTag(new Integer(position));
        }

//        String mytect = holder.text_title.toString();
//        Log.e("Nishmy",""+mytect);
//
        String mytectsss = holder.text_title.getText().toString();
//        Log.e("Nishmy 12",""+mytectsss);
//
        String mytectss = myData.toString();
//
//        Log.e("Nish",""+mytectss);


//        String str = //the string which you want to compare
//                ArrayList myArray =// my array list
        isStringExists = mytectss.contains(mytectsss);
        Log.e("isChecked",""+isStringExists);

        if(isStringExists){
            Log.e("Nish","inside" + holder.text_title.getText().toString());
            //holder.chkSelected.setSelected(true);
           // breedTypedataBeanList.get(position).setSelected(true);
            holder.chkSelected.setChecked(true);
        }


//        if(myData.contains(mytectss)){
//            Log.e("Nish","inside" +holder.text_title.getText().toString());
//
//            holder.chkSelected.isChecked();
//
//        }

//            if(mytectsss.contentEquals(mytectss)){
//            Log.e("Nish","inside" +holder.text_title.getText().toString());
//            holder.chkSelected.isChecked();
//           // holder.chkSelected.setSelected(true);
//            // holder.chkSelected.setChecked(breedTypedataBeanList.get(position).isSelected());
//            //breedTypedataBeanList.get(position).setSelected(true);
//        } else{
//            Log.e("Nish","outside");
//        }


//        if(position == 0 && breedTypedataBeanList.get(0).isSelected() && holder.chkSelected.isChecked())
//        {
//            lastChecked = holder.chkSelected;
//            lastCheckedPos = 0;
//        }

        holder.chkSelected.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String data = "";
                breedTypedataBeanList.get(position).setSelected(true);
                data = data + "\n" + breedTypedataBeanList.get(position).getCheck_list_value();
                Log.d("ssss", data);

                if (CommonUtil.dbUtil.hasPrevetiveChecked(jobid,service_title,data,"2")){
                    Log.e("Nish","inside");
                    CommonUtil.dbUtil.deleteCheckList(jobid,service_title,data,"2");
                    Cursor cur = CommonUtil.dbUtil.getCheckList(jobid,service_title, "2");
                        Log.e("COunt",""+cur.getCount());
                }else{
                    Log.e("Nish 1","inside");
                    CommonUtil.dbUtil.addCheckList(jobid,service_title,data,"2");
                    Cursor cur = CommonUtil.dbUtil.getCheckList(jobid,service_title,"2");
                    Log.e("COunt",""+cur.getCount());
                }




//                if (!isStringExists){
//                    Log.e("Nish","inside");
//                    CommonUtil.dbUtil.addCheckList(jobid,service_title,data);
//                    Cursor cur = CommonUtil.dbUtil.getCheckList(jobid,service_title);
//                        Log.e("COunt",""+cur.getCount());
//                }


            }
        });

     //  holder.chkSelected.setChecked(currentItem.isSelected());

    }
    @Override
    public int getItemCount() {
        return breedTypedataBeanList.size();
    }

    public void filterList(List<Preventive_ChecklistResponse.DataBean> breedTypedataBeanListFiltered) {
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
