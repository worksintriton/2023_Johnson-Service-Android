package com.triton.johnson_tap_app.Service_Activity.SiteAudit;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.triton.johnson_tap_app.Db.CommonUtil;
import com.triton.johnson_tap_app.Db.DbHelper;
import com.triton.johnson_tap_app.Db.DbUtil;
import com.triton.johnson_tap_app.R;
import com.triton.johnson_tap_app.responsepojo.Fetch_MrList_Response;

import java.util.ArrayList;
import java.util.List;

public class AuditMRList_Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private  String TAG = "PetBreedTypesListAdapter";
    private Context context;
    Fetch_MrList_Response.Datum currentItem;
    private List<Fetch_MrList_Response.Datum> breedTypedataBeanList;
    String service_title;
    AlertDialog.Builder builder;
    String service_tittle, job_id, str_mr1, str_mr2,str_mr3,str_mr4, str_mr5, str_mr6,str_mr7,str_mr8,str_mr9,str_mr10,status;
    ArrayList<String> Ar_PartNo = new ArrayList<>();
    ArrayList<String> Ar_PartName = new ArrayList<>();
    ArrayList<String> Ar_PartQuantity = new ArrayList<>();
    boolean isStringExists = false;
    String Partno = "",PartName = "";

    public AuditMRList_Adapter(Context applicationContext, List<Fetch_MrList_Response.Datum> breedTypedataBeanList, AuditMRList_Activity breakdownMrListtwo_activity,
                               String service_title, String job_id, String str_mr1, String str_mr2, String str_mr3, String str_mr4, String str_mr5, String str_mr6, String str_mr7, String str_mr8, String str_mr9, String str_mr10, String status, ArrayList<String> ar_PartNo, ArrayList<String> ar_PartName, ArrayList<String> ar_PartQuantity) {

        this.context = applicationContext;
        this.breedTypedataBeanList = breedTypedataBeanList;
        this.service_tittle = service_title;
        this.job_id = job_id;
        this.str_mr1 = str_mr1;
        this.str_mr2 = str_mr2;
        this.str_mr3 = str_mr3;
        this.str_mr4 = str_mr4;
        this.str_mr5 = str_mr5;
        this.str_mr6 = str_mr6;
        this.str_mr7 = str_mr7;
        this.str_mr8 = str_mr8;
        this.str_mr9 = str_mr9;
        this.str_mr10 = str_mr10;
        this.status = status;
        this.Ar_PartNo = ar_PartNo;
        this.Ar_PartName = ar_PartName;
        this.Ar_PartQuantity = ar_PartQuantity;
        Log.e("Status", "" + status);
    //this.petBreedTypeSelectListener = petBreedTypeSelectListener;

        CommonUtil.dbUtil = new DbUtil(context);
        CommonUtil.dbUtil.open();
        CommonUtil.dbHelper = new DbHelper(context);
    }

    public void filterrList(List<Fetch_MrList_Response.Datum> filterllist)
    {
        breedTypedataBeanList = filterllist;
        notifyDataSetChanged();

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_breakdownmrlisttwo, parent, false);
        return new ViewHolderOne(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        initLayoutOne((ViewHolderOne) holder, position);
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        service_title = sharedPreferences.getString("service_title", "default value");
        job_id = sharedPreferences.getString("jobid", "default value");
        Log.e("Name",""+ service_title);
        Log.e("Job ID",""+ job_id);
    }

//    private void initLayoutOne(ViewHolderOne holder, int position) {
//        currentItem = breedTypedataBeanList.get(position);
//
//        if (currentItem.getPartname() != null){
//            holder.partno.setText(currentItem.getPartno());
//            holder.partname.setText(currentItem.getPartname());
//            Log.e("dataaaaa","Error");
//        }
//    }

    private void initLayoutOne(ViewHolderOne holder, int position) {

        currentItem = breedTypedataBeanList.get(position);
       try{
           if (currentItem.getPartno() != null){
               holder.partno.setText(currentItem.getPartno());
               holder.partname.setText(currentItem.getPartname());
           }
       } catch (NullPointerException e){
           e.printStackTrace();
       }

        // String qty = Ar_PartQuantity.get(position);
        String partno = holder.partno.getText().toString();
        String myPartno = Ar_PartNo.toString();
        String partname = holder.partname.getText().toString();
        String myPartname = Ar_PartName.toString();

//        isStringExists = myPartno.contains(partno);
        isStringExists =  myPartno.contains(partno);
//        isStringExists = Boolean.parseBoolean(myPartname.concat(partname));
        Log.e("isChecked",""+isStringExists);

        if(isStringExists){
//            Log.e("Nish","inside" + holder.partno.getText().toString());
            Log.e("Nish","inside" + holder.partname.getText().toString());
            Log.e("Nish","inside");
            holder.chkSelected.setChecked(true);

        }else{

            holder.chkSelected.setChecked(false);
        }

        holder.lin_job_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                breedTypedataBeanList.get(position).getPartno();
                String str_no = breedTypedataBeanList.get(position).getPartno();
                String str_name = breedTypedataBeanList.get(position).getPartname();

//                Intent intent = new Intent(context, AuditMR_Activity.class);
//                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                intent.putExtra("partname", str_name);
//                intent.putExtra("partno", str_no);
//                intent.putExtra("service_title",service_title);
//                intent.putExtra("status", status);
//                context.startActivity(intent);
            }
        });

       holder.chkSelected.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               breedTypedataBeanList.get(position).setSelected(true);

               Partno= breedTypedataBeanList.get(position).getPartno();
               PartName = breedTypedataBeanList.get(position).getPartname();
               PartName = PartName.replace("'","*");
               Log.e("Part No",""+Partno);
               Log.e("Part Name",""+PartName);

               if (CommonUtil.dbUtil.hasMRList(Partno,PartName,"3",job_id,service_title)){
                   Log.e("Hi Nish","Had Data");
                   CommonUtil.dbUtil.deleteMRList(Partno,PartName,"3",job_id,service_title);
                   Cursor cur = CommonUtil.dbUtil.getMRList(job_id,"3",service_title);
                   Log.e("List Count",""+cur.getCount());
               }
               else{
                   CommonUtil.dbUtil.addMRList(Partno,PartName,"3",job_id,service_title,"0");
                   Cursor cur = CommonUtil.dbUtil.getMRList(job_id,"3",service_title);
                   Log.e("List Count",""+cur.getCount());
               }
           }
       });

    }

    @Override
    public int getItemCount() {
        return breedTypedataBeanList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    static  class ViewHolderOne extends RecyclerView.ViewHolder{

        public LinearLayout lin_job_item,line_Qty;
        public TextView partno,partname,id;
        public CheckBox chkSelected;
        public EditText No;

        public ViewHolderOne(View itemView) {
            super(itemView);

            lin_job_item = itemView.findViewById(R.id.lin_job_item);
            partname = itemView.findViewById(R.id.partname);
            partno = itemView.findViewById(R.id.partno);
            chkSelected = itemView.findViewById(R.id.chkSelected);
            line_Qty = itemView.findViewById(R.id.line_qty);
            No = itemView.findViewById(R.id.no);
            id = itemView.findViewById(R.id.part_id);

        }

    }
}
