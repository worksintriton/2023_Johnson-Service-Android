package com.triton.johnson_tap_app.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.triton.johnson_tap_app.R;
import com.triton.johnson_tap_app.responsepojo.UserTypeListResponse;
import com.triton.johnson_tap_app.UserTypeSelectListener;

import java.util.List;


public class UserTypesListAdapter extends  RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private  String TAG = "UserTypesListAdapter";
    private Context context;
    private UserTypeSelectListener userTypeSelectListener;



   private List<UserTypeListResponse.DataBean.UsertypedataBean> usertypedataBeanList;
    UserTypeListResponse.DataBean.UsertypedataBean currentItem;

    private int userTypeValue;



    public UserTypesListAdapter(Context context, List<UserTypeListResponse.DataBean.UsertypedataBean> usertypedataBeanList, UserTypeSelectListener userTypeSelectListener, int userTypeValue) {
        this.usertypedataBeanList = usertypedataBeanList;
        this.context = context;
        this.userTypeSelectListener = userTypeSelectListener;
        this.userTypeValue = userTypeValue;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_usertypes_cardview, parent, false);
        return new ViewHolderOne(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        initLayoutOne((ViewHolderOne) holder, position);


    }

    @SuppressLint("LogNotTimber")
    private void initLayoutOne(ViewHolderOne holder, final int position) {
        currentItem = usertypedataBeanList.get(position);
      //  Log.w(TAG,"userTypeValue : "+userTypeValue);

        if(currentItem.getUser_type_title() != null){
            holder.txt_usertypes.setText(currentItem.getUser_type_title());

        }
        if (currentItem.getUser_type_img() != null && !currentItem.getUser_type_img().isEmpty()) {
            Glide.with(context)
                        .load(currentItem.getUser_type_img())
                        .into(holder.img_userimages);

            }
        else{
//                Glide.with(context)
//                        .load(APIClient.PROFILE_IMAGE_URL)
//                        .into(holder.img_userimages);

            }

        holder.ll_usertypes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String data = "";
                usertypedataBeanList.get(position).setSelected(true);
                holder.chx_usertypes.setChecked(true);
                notifyDataSetChanged();

                data = data + "\n" + usertypedataBeanList.get(position).getUser_type_title();
                Log.d("ssss", data);

//                if(usertypedataBeanList.get(position).getUser_type_title() != null && usertypedataBeanList.get(position).getUser_type_value() != 0){
//                    userTypeSelectListener.userTypeSelectListener(usertypedataBeanList.get(position).getUser_type_title(),usertypedataBeanList.get(position).getUser_type_value());
//
//                }
            }
        });
//        if (usertypedataBeanList.get(position).isSelected()) {
//            Log.w(TAG, "IF isSelected--->");
//            holder.ll_usertypes.setBackgroundResource(R.drawable.ic_account_circle_white_24dp);
//            holder.chx_usertypes.setVisibility(View.VISIBLE);
//            holder.chx_usertypes.setChecked(true);
//            Log.d("ssss",usertypedataBeanList.get(position).getUser_type_title());
//            return;
//
//        }
//        else {
//            Log.w(TAG, "ELSE isSelected--->");
//
//            holder.ll_usertypes.setBackgroundResource(R.drawable.user_bgm_trans);
//            holder.chx_usertypes.setVisibility(View.INVISIBLE);
//            holder.chx_usertypes.setChecked(false);
//
//        }



    }
    @Override
    public int getItemCount() {
        return usertypedataBeanList.size();
    }


    @Override
    public int getItemViewType(int position) {
        return position;
    }

    static class ViewHolderOne extends RecyclerView.ViewHolder {
        public TextView txt_usertypes;
        public ImageView img_userimages;
        public LinearLayout ll_usertypes;
        public CheckBox chx_usertypes;



        public ViewHolderOne(View itemView) {
            super(itemView);

            txt_usertypes = itemView.findViewById(R.id.txt_usertypes);
            img_userimages = itemView.findViewById(R.id.img_userimages);
            ll_usertypes = itemView.findViewById(R.id.ll_usertypes);
            chx_usertypes = itemView.findViewById(R.id.chx_usertypes);
            chx_usertypes.setClickable(false);

        }
    }

}
