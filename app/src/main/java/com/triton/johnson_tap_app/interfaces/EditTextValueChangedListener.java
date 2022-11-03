package com.triton.johnson_tap_app.interfaces;

import com.triton.johnson_tap_app.GetFieldListResponse;

import java.util.List;

public interface EditTextValueChangedListener {
    void editTextValueListener(int startItem, String s, String size, int position, List<GetFieldListResponse.DataBean.LiftListBean> list);
}
