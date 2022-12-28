package com.triton.johnson_tap_app.Service_Activity.Preventive_Services;

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
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.triton.johnson_tap_app.Db.CommonUtil;
import com.triton.johnson_tap_app.Db.DbHelper;
import com.triton.johnson_tap_app.Db.DbUtil;
import com.triton.johnson_tap_app.R;

import java.util.ArrayList;

public class ESCTRV_adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<String> arliMonth;
    private ArrayList<String> arliID;
    Context context;
    ArrayList<String> arliJobDate = new ArrayList<>();
    ArrayList<String> myData = new ArrayList<>();
    JobDateListener jobDateListener;
    SharedPreferences sharedPreferences;
    String jobid,service_title;
    Boolean isStringExists;

    public ESCTRV_adapter(ArrayList<String> arliMonth, Context context, JobDateListener jobDateListener, ArrayList<String> mydata, ArrayList<String> arli_ID) {
        this.arliMonth = arliMonth;
        this.arliID = arli_ID;
        this.context = context;
        this.jobDateListener = jobDateListener;
        this.myData = mydata;

        CommonUtil.dbUtil = new DbUtil(context);
        CommonUtil.dbUtil.open();
        CommonUtil.dbHelper = new DbHelper(context);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        jobid = sharedPreferences.getString("job_id", "default value");
        service_title = sharedPreferences.getString("service_title", "default value");
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.feedback_card, parent, false);
        return new ESCTRV_adapter.ViewHolderOne(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        initLayoutOne((ESCTRV_adapter.ViewHolderOne) holder, position);
    }

    private void initLayoutOne(ESCTRV_adapter.ViewHolderOne holder, int position) {

        if (arliMonth != null && arliMonth.size()>0){

            holder.name.setText(arliMonth.get(position));
            holder.id.setText(arliID.get(position));

            String myMonth = holder.name.getText().toString();
           // Log.e("Nishmy 12",""+myMonth);
//
            String myDate = myData.toString();
            Log.e("Nishmy 12",""+myDate);

            isStringExists = myDate.contains(myMonth);
            Log.e("isChecked",""+isStringExists);


            if(isStringExists){
                Log.e("Nish","inside" + holder.name.getText().toString());
                holder.chx_usertypes.setChecked(true);
            }

            holder.chx_usertypes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    String data = holder.name.getText().toString();
                    String id = holder.id.getText().toString();

                    Log.e("Data",""+data);

//                    data = data.replace("ANUALLY","YEARLY");
//                    if (holder.chx_usertypes.isChecked()){
//
//                        arliJobDate.add(arliMonth.get(position));
//                    }
//                    else {
//                        arliJobDate.remove(arliMonth.get(position));
//                    }
//                    jobDateListener.onMonthchange(arliJobDate);
//
//                    Log.e("LIST",""+arliJobDate);
//
//                    ArrayList<String> list = arliJobDate;
//
//                    //  String list = String.join(" ",arliJobDate);
//
//                    Log.e("List 1",""+list);
//                    SharedPreferences.Editor editor = sharedPreferences.edit();
//                    editor.putString("List", String.valueOf(list));
//                    editor.apply();

                    if (CommonUtil.dbUtil.hasMonthList(jobid,service_title,data, "1")){
                        Log.e("Nish","inside");
                        Log.e("Data Get",""+ data);
                        CommonUtil.dbUtil.deleteMonthList(jobid,service_title,data, "1");
                        Log.e("Data Delete",""+ data);
                        Cursor cur = CommonUtil.dbUtil.getMonthlist(jobid,service_title, "1");
                        Log.e("COunt",""+cur.getCount());
                    }
                    else{

                        Log.e("Nish 1","inside");
                        CommonUtil.dbUtil.addMonthList(jobid,service_title,data, "1");
                        Log.e("Data Add",""+ data);
                        Cursor cur = CommonUtil.dbUtil.getMonthlist(jobid,service_title, "1");
                        Log.e("COunt",""+cur.getCount());
                    }
                }

            });
        }
//        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//        editor.putString("List", String.valueOf(arliJobDate));
//        editor.apply();
    }

    @Override
    public int getItemCount() {
        return arliMonth.size();
    }

    public class ViewHolderOne extends RecyclerView.ViewHolder {

        TextView name,code,id;
        CardView cv_root;
        LinearLayout ll_usertypes;
        CheckBox chx_usertypes;
        public ViewHolderOne(View view) {
            super(view);
            name = view.findViewById(R.id.tvName);
            code = view.findViewById(R.id.codes);
            id = view.findViewById(R.id.tvID);
            ll_usertypes = view.findViewById(R.id.ll_usertypes);
            chx_usertypes = view.findViewById(R.id.chkSelected);

            id.setVisibility(View.GONE);
        }
    }
}
