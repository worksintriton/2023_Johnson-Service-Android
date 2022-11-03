package com.triton.johnson_tap_app.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.triton.johnson_tap_app.requestpojo.PetAppointmentCreateRequest;
import com.triton.johnson_tap_app.R;
import com.triton.johnson_tap_app.api.RetrofitClient;
import java.util.List;

public class PetCurrentImageListAdapter extends RecyclerView.Adapter<PetCurrentImageListAdapter.AddImageListHolder> {
    private String TAG = "PetCurrentImageListAdapter";
    Context context;
    List<PetAppointmentCreateRequest.PetImgBean> pet_imgList;
    View view;

    public PetCurrentImageListAdapter(Context context, List<PetAppointmentCreateRequest.PetImgBean> pet_imgList) {
        this.context = context;
        this.pet_imgList = pet_imgList;
    }

    @NonNull
    @Override
    public AddImageListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_images_upload, parent, false);

        return new AddImageListHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull AddImageListHolder holder, final int position) {
        final PetAppointmentCreateRequest.PetImgBean petImgBean = pet_imgList.get(position);

        Log.w(TAG,"ImagePic : "+petImgBean.getPet_img());



        if (petImgBean.getPet_img()!= null) {
            Glide.with(context)
                    .load(petImgBean.getPet_img())
                    .into(holder.certificate_pics_1);
        }
        else{
            Glide.with(context)
                    .load(RetrofitClient.BANNER_IMAGE_URL)
                    .into(holder.certificate_pics_1);

        }

        holder.removeImg.setOnClickListener(view -> {
            pet_imgList.remove(position);
            notifyDataSetChanged();
        });

    }

    @Override
    public int getItemCount() {
        return pet_imgList.size();
    }

    public static class AddImageListHolder extends RecyclerView.ViewHolder {
        ImageView removeImg,certificate_pics_1;
        public AddImageListHolder(View itemView) {
            super(itemView);
            certificate_pics_1 = itemView.findViewById(R.id.certificate_pics_1);
            removeImg = itemView.findViewById(R.id.close);
        }
    }
}