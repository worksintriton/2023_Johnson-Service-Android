package com.triton.johnson_tap_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;

import com.triton.johnson_tap_app.Adapter.PriceRangeAdapter;
import com.triton.johnson_tap_app.responsepojo.FilterPageInfoResponse;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchActivity extends AppCompatActivity {

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rv_pricerange)
    RecyclerView rv_pricerange;
    String FA_BSD_BANKDT, FA_BSD_AMOUNT;
    String s;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.edt_search)
    EditText edt_search;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.img_clearsearch)
    ImageView img_clearsearch;

    List<FilterPageInfoResponse.PriceRangeBean> price_range;
    PriceRangeAdapter priceRangeAdapter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        ButterKnife.bind(this);

//        filterPageInfoResponseCall();
//
//        edt_search.setOnEditorActionListener(new TextView.OnEditorActionListener() {
//            @Override
//            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
//                img_clearsearch.setVisibility(View.VISIBLE);
//                String Searchvalue = edt_search.getText().toString();
//                Log.w(TAG,"Search Value---"+Searchvalue);
//                filter(Searchvalue);
//                return false;
//            }
//        });
//    }

//    public void filterPageInfoResponseCall(){
//        APIInterface apiInterface = RetrofitClient.getClient().create(APIInterface.class);
//        Call<FilterPageInfoResponse> call = apiInterface.filterPageInfoResponseCall(RestUtils.getContentType());
//        Log.w(TAG,"url  :%s"+ call.request().url().toString());
//        call.enqueue(new Callback<FilterPageInfoResponse>() {
//            @SuppressLint("SetTextI18n")
//            @Override
//            public void onResponse(@NonNull Call<FilterPageInfoResponse> call, @NonNull Response<FilterPageInfoResponse> response) {
//                if (response.body() != null) {
//                    if (200 == response.body().getCode()) {
//                        Log.w(TAG, "FilterPageInfoResponse" + new Gson().toJson(response.body()));
//
//                        if(response.body().getPrice_range() != null && response.body().getPrice_range().size()>0){
//                            setViewPriceRange(response.body().getPrice_range());
//                        }
//                    }
//                }
//
//
//
//
//            }
//
//            @Override
//            public void onFailure(@NonNull Call<FilterPageInfoResponse> call, @NonNull  Throwable t) {
//                Log.w(TAG,"filterPageInfoResponseCall flr"+t.getMessage());
//            }
//        });
//
//    }
//
//    private void setViewPriceRange(List<FilterPageInfoResponse.PriceRangeBean> price_range) {
//        rv_pricerange.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false));
//        rv_pricerange.setItemAnimator(new DefaultItemAnimator());
//        priceRangeAdapter = new PriceRangeAdapter(getApplicationContext(), price_range,this);
//        rv_pricerange.setAdapter(priceRangeAdapter);
//    }
//
//    private void filter(String s) {
//        List<FilterPageInfoResponse.PriceRangeBean> filteredlist = new ArrayList<>();
//
//        Log.d("ss",price_range.toString());
//
//        for(FilterPageInfoResponse.PriceRangeBean item : price_range)
//        {
//            if(item.getDisplay_text().toLowerCase().contains(s.toLowerCase()))
//            {
//                Log.w(TAG,"filter----"+item.getDisplay_text().toLowerCase().contains(s.toLowerCase()));
//                filteredlist.add(item);
//            }
//        }
//        if(filteredlist.isEmpty())
//        {
//            Toast.makeText(this,"No Data Found ... ",Toast.LENGTH_SHORT).show();
//            rv_pricerange.setVisibility(View.GONE);
//            //txt_no_records.setVisibility(View.VISIBLE);
//            //  txt_no_records.setText("No Jobs Available");
//
//        }else
//        {
//            priceRangeAdapter.filterList(filteredlist);
//        }
//
//    }
//
////    public void priceRangeSelectedListener(String fa_bsd_bankdt, String fa_bsd_amount) {
////        Log.w(TAG," priceRangeSelectedListener :"+" count_value_start : "+fa_bsd_bankdt+" count_value_end :"+fa_bsd_amount);
////       FA_BSD_BANKDT  = fa_bsd_bankdt;
////       FA_BSD_AMOUNT = fa_bsd_amount;
////    }
//
//    public void priceRangeSelectedListener(int count_value_start, int count_value_end) {
//        Log.w(TAG," priceRangeSelectedListener :"+" count_value_start : "+count_value_start+" count_value_end :"+count_value_end);
////        Count_value_end = count_value_start;
////        Count_value_start = count_value_end;

    }
}
