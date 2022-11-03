package com.triton.johnson_tap_app.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.triton.johnson_tap_app.responsepojo.FilterPageInfoResponse;
import com.triton.johnson_tap_app.PriceRangeSelectedListener;
import com.triton.johnson_tap_app.R;

import java.util.List;


public class PriceRangeAdapter extends  RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private  String TAG = "PriceRangeAdapter";
    private Context context;
    private PriceRangeSelectedListener priceRangeSelectedListener;
    private int selectedPosition =-1;
    List<FilterPageInfoResponse.PriceRangeBean> price_range;

    public PriceRangeAdapter(Context context,  List<FilterPageInfoResponse.PriceRangeBean> price_range,PriceRangeSelectedListener priceRangeSelectedListener) {
        this.context = context;
        this.price_range = price_range;
        this.priceRangeSelectedListener = priceRangeSelectedListener;

    }

    public void filterList(List<FilterPageInfoResponse.PriceRangeBean> filterllist)
    {
        price_range = filterllist;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_pricerange_cardview, parent, false);
        return new ViewHolderOne(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        initLayoutOne((ViewHolderOne) holder, position);


    }

    private void initLayoutOne(ViewHolderOne holder, final int position) {

        // currentItem = dataBeanList.get(position);
        for (int i = 0; i < price_range.size(); i++) {
            holder.txt_value.setText(price_range.get(position).getDisplay_text());

        }

//        holder.txt_value.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                priceRangeSelectedListener.priceRangeSelectedListener(price_range.get(position).getCount_value_start(),(price_range.get(position).getCount_value_end()));
//                selectedPosition = position;
//                notifyDataSetChanged();
//
//
//            }
//        });
//        if(selectedPosition==position){
//            holder.txt_value.setBackgroundResource(R.drawable.button_blue_rounded_corner_without_stroke);
//            holder.txt_value.setTextColor(ContextCompat.getColor(context, R.color.white));
//        } else{
//            holder.txt_value.setBackgroundResource(R.drawable.button_white_rounded_corner_without_stroke);
//            holder.txt_value.setTextColor(ContextCompat.getColor(context, R.color.black));
//
//        }
    }
    @Override
    public int getItemCount() {
        return price_range.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    class ViewHolderOne extends RecyclerView.ViewHolder {
        public TextView txt_value;

        public ViewHolderOne(View itemView) {
            super(itemView);

            txt_value = itemView.findViewById(R.id.txt_value);
        }
    }
}
