package com.triton.johnson_tap_app.responsepojo;

import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.cardview.widget.CardView;

public interface GetFileUploadListener {
    void getFileUploadListener(LinearLayout ll_file_upload, int position, ImageView img_file_upload, ImageView img_close, String field_length, CardView cv_image);
}
