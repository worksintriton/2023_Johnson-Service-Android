package com.triton.johnson_tap_app;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.triton.johnson_tap_app.interfaces.EditTextValueChangedListener;

import java.util.List;


public class LiftInputTypeListAdapter extends  RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private  String TAG = "LiftInputTypeListAdapter";

    private Context context;

    private String size;
    int startItem;

    EditTextValueChangedListener editTextValueChangedListener;

    public List<GetFieldListResponse.DataBean.LiftListBean> list;

    public LiftInputTypeListAdapter(Context context, String size, int startItem, EditTextValueChangedListener editTextValueChangedListene, List<GetFieldListResponse.DataBean.LiftListBean> list) {
        this.context = context;
        this.size = size;
        this.startItem=startItem;
        this.editTextValueChangedListener=editTextValueChangedListene;
        this.list=list;

        Log.w(TAG,"sIZEEEE---"+list.size());
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_liftinputlist, parent, false);
        return new ViewHolderOne(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        initLayoutOne((ViewHolderOne) holder, position);
        Log.w(TAG,"INS---");

    }

    @SuppressLint({"SetTextI18n", "LogNotTimber"})
    private void initLayoutOne(ViewHolderOne holder, final int position) {
        Log.w(TAG,"INS---");

        Log.w(TAG,"sizeeeeeee -->"+size);

       if(list.get(position).getLeft()!=null &&!list.get(position).getLeft().equals("")){
//
//            //holder.edt_inputValue.setText(list.get(position).getLeft());
//
//
       }
        if(list.get(position).getTitle() != null && !list.get(position).getTitle().isEmpty()){
            Log.w(TAG,"INS---");
            holder.txt_title.setVisibility(View.VISIBLE);
            holder.txt_title.setText(list.get(position).getTitle());
        }else{
            holder.txt_title.setVisibility(View.GONE);
        }






        holder.edt_inputValue.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

/*
                editTextValueChangedListener.editTextValueListener(startItem,charSequence.toString(),size,position);
*/
            }

            @Override
            public void afterTextChanged(Editable editable) {
                Log.w(TAG,"afterTextChanged liftlist-->"+new Gson().toJson(list));

                editTextValueChangedListener.editTextValueListener(startItem,editable.toString(),size,position,list);

            }
        });


    }



//    @Override
//    public int getItemCount(int size) {
//        return size;
//    }


    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }



    static class ViewHolderOne extends RecyclerView.ViewHolder {

        public EditText edt_inputValue;
        public TextView txt_title;


        CardView cv_root;

        public ViewHolderOne(View itemView) {
            super(itemView);

            edt_inputValue = itemView.findViewById(R.id.edt_inputValue);
            txt_title = itemView.findViewById(R.id.txt_title);
            txt_title.setVisibility(View.GONE);

            cv_root = itemView.findViewById(R.id.cv_root);



        }




    }




}
