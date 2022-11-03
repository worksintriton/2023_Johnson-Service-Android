package com.triton.johnson_tap_app.interfaces;

import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.github.gcacace.signaturepad.views.SignaturePad;

public interface GetDigitalSignUploadListener {
    void getDigitalSignUploadListener(LinearLayout llheaderdigitalsignature, ImageView ivdigitalsignature, SignaturePad mSignaturePad, Button mSaveButton, Button mClearButton, int position, String field_length);
}
