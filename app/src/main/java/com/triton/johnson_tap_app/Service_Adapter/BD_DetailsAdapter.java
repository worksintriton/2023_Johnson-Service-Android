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
import com.triton.johnson_tap_app.responsepojo.BD_DetailsResponse;

import java.util.ArrayList;
import java.util.List;


public class BD_DetailsAdapter extends  RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private  String TAG = "PetBreedTypesListAdapter";
    private Context context;
    BD_DetailsResponse.DataBean currentItem;
    private List<BD_DetailsResponse.DataBean> breedTypedataBeanList;
    private UserTypeSelectListener1 userTypeSelectListener;
    private static CheckBox lastChecked = null;
    private static int lastCheckedPos = 0;
    String data = "";
    String myjobdate,jobid,service_title ;
    Boolean isStringExists;
    SharedPreferences sharedPreferences;
    ArrayList<String> myData = new ArrayList<>();
    String abc,status,str_BdDetails;


    public BD_DetailsAdapter(Context context, List<BD_DetailsResponse.DataBean> breedTypedataBeanList, UserTypeSelectListener1 userTypeSelectListener, String mystatus, String str_BDDetails) {
        this.context = context;
        this.breedTypedataBeanList = breedTypedataBeanList;
        this.userTypeSelectListener = userTypeSelectListener;
        this.status = mystatus;
        this.str_BdDetails = str_BDDetails;
      //  this.myData = mydata;

        CommonUtil.dbUtil = new DbUtil(context);
        CommonUtil.dbUtil.open();
        CommonUtil.dbHelper = new DbHelper(context);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        jobid = sharedPreferences.getString("job_id", "default value");
        service_title = sharedPreferences.getString("service_title", "default value");

        Log.e("JobID",""+jobid);
        Log.e("Name",""+service_title);
        Log.e("Status",""+status);
        Log.e("My BD",""+str_BdDetails);
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

    @SuppressLint({"LogNotTimber", "Range"})
    private void initLayoutOne(ViewHolderOne holder, final int position) {
        currentItem = breedTypedataBeanList.get(position);

//        Intent intent = new Intent("message_subject_intent");
//        intent.putExtra("cust_name" , currentItem.getCUSNAME());
//        intent.putExtra("cont_no" , currentItem.getCONTNO());
//        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);

        if(currentItem.getTitle() != null){
            holder.text_title.setText(currentItem.getTitle());
            holder.chkSelected.setChecked(breedTypedataBeanList.get(position).isSelected());
            holder.chkSelected.setTag(new Integer(position));
        }

//        Cursor curs = CommonUtil.dbUtil.getBDdetails(jobid,service_title, "1");
//        Log.e("BD Count",""+curs.getCount());
//
//        if (curs.getCount()>0 && curs.moveToLast()){
//
//            abc = curs.getString(curs.getColumnIndex(DbHelper.BD_DETAILS));
//            Log.e("BD Data Get",""+abc);
//
//            String feedback = holder.text_title.getText().toString();
//            Log.e("BD DETAILS",""+feedback);
//
//            isStringExists = abc.contains(feedback);
//            Log.e("isChecked",""+isStringExists);
//
//            if(isStringExists){
//                Log.e("Nish","inside" + holder.text_title.getText().toString());
//                //holder.chkSelected.setSelected(true);
//                // breedTypedataBeanList.get(position).setSelected(true);
//                holder.chkSelected.setChecked(true);
//                lastChecked =holder.chkSelected;
//            }
//        }
        String bddetails = holder.text_title.getText().toString();

        isStringExists = bddetails.equals(str_BdDetails);

        if(isStringExists){
            Log.e("Nish","inside" + holder.text_title.getText().toString());
            //holder.chkSelected.setSelected(true);
            // breedTypedataBeanList.get(position).setSelected(true);
            holder.chkSelected.setChecked(true);
            lastChecked =holder.chkSelected;
        }


        if(position == 0 && breedTypedataBeanList.get(0).isSelected() && holder.chkSelected.isChecked())
        {
            lastChecked = holder.chkSelected;
            lastCheckedPos = 0;
        }

  //      String BdData = holder.text_title.getText().toString();
//        Log.e("Nishmy 12",""+mytectsss);
//
  //      String myBdData = myData.toString();
//
//        Log.e("Nish",""+mytectss);

//        isStringExists = myData.contains(BdData);
  //      Log.e("isChecked",""+isStringExists);

//        if(isStringExists){
//            Log.e("Nish","inside" + holder.text_title.getText().toString());
//            //holder.chkSelected.setSelected(true);
//            // breedTypedataBeanList.get(position).setSelected(true);
//            holder.chkSelected.setChecked(true);
//        }

        holder.chkSelected.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {

                CheckBox cb = (CheckBox)v;
                int clickedPos = ((Integer)cb.getTag()).intValue();

                if(cb.isChecked()) {

                    Log.e("hi ","Nish 1");

                    if(lastChecked != null) {
                        Log.e("hi ","Nish 2");
                        lastChecked.setChecked(false);
                        breedTypedataBeanList.get(lastCheckedPos).setSelected(false);
                        breedTypedataBeanList.get(position).setSelected(true);
                        data = breedTypedataBeanList.get(position).getTitle();
                        Log.e("My BD Data", data);
                        CommonUtil.dbUtil.addBDDetails(jobid,service_title,data,"1");
                        Cursor curs = CommonUtil.dbUtil.getBDdetails(jobid,service_title, "1");
                        Log.e("BD",""+curs.getCount());
                    }
                    else{
                        breedTypedataBeanList.get(lastCheckedPos).setSelected(false);
                        breedTypedataBeanList.get(position).setSelected(true);
                        data = breedTypedataBeanList.get(position).getTitle();
                        Log.e("My BD Data 1", data);

                        CommonUtil.dbUtil.addBDDetails(jobid,service_title,data,"1");
                        Cursor curs = CommonUtil.dbUtil.getBDdetails(jobid,service_title, "1");
                        Log.e("BD",""+curs.getCount());
                    }


                    lastChecked = cb;
                    lastCheckedPos = clickedPos;
                }

                else{
                    Log.e("hi ","Nish 3");
                    lastChecked = null;
                    breedTypedataBeanList.get(clickedPos).setSelected(cb.isChecked());
                    CommonUtil.dbUtil.deleteBDdetails(jobid,service_title,"1");
//                    CommonUtil.dbUtil.addBDDetails(jobid,service_title,data,"1");
                    Cursor curs = CommonUtil.dbUtil.getBDdetails(jobid,service_title, "1");
                    Log.e("COunt Add",""+curs.getCount());
                    holder.chkSelected.setChecked(false);

                }

            }
        });

     //  holder.chkSelected.setChecked(currentItem.isSelected());
//        String data = holder.text_title.getText().toString();
//        Log.e("My BD Data",""+data);

    }
    @Override
    public int getItemCount() {
        return breedTypedataBeanList.size();
    }

    public void filterList(List<BD_DetailsResponse.DataBean> breedTypedataBeanListFiltered) {
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
