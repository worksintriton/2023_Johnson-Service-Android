package com.triton.johnson_tap_app.Service_Activity.PartsReplacementACK;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.triton.johnson_tap_app.PetBreedTypeSelectListener;
import com.triton.johnson_tap_app.R;
import com.triton.johnson_tap_app.responsepojo.Material_DetailsResponseACK;

import java.util.List;

public class MaterialDetailsACK_Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>  {

    Context context;
    String status,service_title;
    List<Material_DetailsResponseACK.DataBean> breedTypedataBeanList;
    PetBreedTypeSelectListener petBreedTypeSelectListener;
    Material_DetailsResponseACK.DataBean currentItem;
    SharedPreferences sharedPreferences;

    public MaterialDetailsACK_Adapter(Context applicationContext, List<Material_DetailsResponseACK.DataBean> breedTypedataBeanList, String status) {

        this.context = applicationContext;
        this.breedTypedataBeanList = breedTypedataBeanList;
        //   this.petBreedTypeSelectListener = petBreedTypeSelectListener;
        this.status = status;

        Log.e("Status", "" + status);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        service_title = sharedPreferences.getString("service_title", "Services");

        Log.e("Name" , " " +service_title);

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_materialdetailsack, parent, false);
        return new ViewHolderOne(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        initLayoutOne((ViewHolderOne) holder, position);
    }

    private void initLayoutOne(ViewHolderOne holder, int position) {

        currentItem = breedTypedataBeanList.get(position);

        holder.txt_Partname.setText(currentItem.getPart_name());
        holder.txt_Quantity.setText(currentItem.getPart_quantity());
        holder.txt_MrId.setText(currentItem.getMr_id());
        holder.txt_MrSeqNumber.setText(currentItem.getMrseq_no());

        String s = breedTypedataBeanList.get(position).getMr_id();

        Log.e("A",""+s);


    }

    @Override
    public int getItemCount() {
        return breedTypedataBeanList.size();
    }

    public class ViewHolderOne extends RecyclerView.ViewHolder {

        TextView txt_Partname, txt_Quantity, txt_MrId, txt_MrSeqNumber;
        public ViewHolderOne(View view) {
            super(view);

            txt_Partname = (TextView) view.findViewById(R.id.txt_partname);
            txt_Quantity = (TextView) view.findViewById(R.id.txt_materialqty);
            txt_MrId = (TextView) view.findViewById(R.id.txt_materialid);
            txt_MrSeqNumber = (TextView) view.findViewById(R.id.txt_seqnumber);
        }
    }
}
