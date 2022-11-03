package com.triton.johnson_tap_app.interfaces;

import androidx.recyclerview.widget.RecyclerView;

import com.triton.johnson_tap_app.GetFieldListResponse;

import java.util.List;

public interface GetInputFieldListener {
    void getInputFieldListener(RecyclerView rv_liftinputlist, int startItem, String field_length, List<GetFieldListResponse.DataBean.LiftListBean> lift_list);
}
