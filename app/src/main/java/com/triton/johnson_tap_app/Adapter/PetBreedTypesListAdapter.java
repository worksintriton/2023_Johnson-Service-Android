package com.triton.johnson_tap_app.Adapter;

import static android.content.Context.MODE_PRIVATE;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.triton.johnson_tap_app.responsepojo.BreedTypeResponse1;
import com.triton.johnson_tap_app.PetBreedTypeSelectListener;
import com.triton.johnson_tap_app.R;

import java.util.List;


public class PetBreedTypesListAdapter extends  RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private  String TAG = "PetBreedTypesListAdapter";
    private Context context;
    BreedTypeResponse1.DataBean currentItem;
    private List<BreedTypeResponse1.DataBean> breedTypedataBeanList;
    private PetBreedTypeSelectListener petBreedTypeSelectListener;

    public PetBreedTypesListAdapter(Context context, List<BreedTypeResponse1.DataBean> breedTypedataBeanList, PetBreedTypeSelectListener petBreedTypeSelectListener ) {
        this.context = context;
        this.breedTypedataBeanList = breedTypedataBeanList;
        this.petBreedTypeSelectListener = petBreedTypeSelectListener;


    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_breedtype_cardview, parent, false);
        return new ViewHolderOne(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        initLayoutOne((ViewHolderOne) holder, position);


    }

    @SuppressLint("LogNotTimber")
    private void initLayoutOne(ViewHolderOne holder, final int position) {
        currentItem = breedTypedataBeanList.get(position);

        if(currentItem.getEMPNO() != null){
            holder.txt_breedtype.setText(currentItem.getEMPNAME() + "," + currentItem.getEMPNO());

        }

        holder.txt_breedtype.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                breedTypedataBeanList.get(position).getEMPNAME();

                String s = breedTypedataBeanList.get(position).getEMPNAME() + "," + breedTypedataBeanList.get(position).getEMPNO();

                Toast.makeText(context, "value" + s, Toast.LENGTH_LONG).show();

                SharedPreferences sharedPref = context.getSharedPreferences("myKey", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString("value", s);
                editor.apply();


            }
        });

    }
    @Override
    public int getItemCount() {
        return breedTypedataBeanList.size();
    }

 /*   public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    breedTypedataBeanListFiltered = breedTypedataBeanList;
                } else {
                    List<BreedTypeResponse.DataBean> filteredList = new ArrayList<>();
                    for (BreedTypeResponse.DataBean row : breedTypedataBeanList) {

                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (row.getPet_breed().toLowerCase().contains(charString.toLowerCase()) || row.getPet_breed().contains(charSequence)) {
                            filteredList.add(row);
                        }
                    }

                    breedTypedataBeanListFiltered = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = breedTypedataBeanListFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                breedTypedataBeanListFiltered = (ArrayList<BreedTypeResponse.DataBean>) filterResults.values;

                // refresh the list with filtered data
                notifyDataSetChanged();
            }
        };
    }*/

    public void filterList(List<BreedTypeResponse1.DataBean> breedTypedataBeanListFiltered) {
        breedTypedataBeanList = breedTypedataBeanListFiltered;
        Log.w(TAG,"breedTypedataBeanList : "+new Gson().toJson(breedTypedataBeanList));

        notifyDataSetChanged();
    }




    @Override
    public int getItemViewType(int position) {
        return position;
    }

    static class ViewHolderOne extends RecyclerView.ViewHolder {
        public TextView txt_breedtype;
        public LinearLayout ll_root;

        public ViewHolderOne(View itemView) {
            super(itemView);

            txt_breedtype = itemView.findViewById(R.id.txt_breedtype);
            ll_root = itemView.findViewById(R.id.ll_root);


        }

    }
}
