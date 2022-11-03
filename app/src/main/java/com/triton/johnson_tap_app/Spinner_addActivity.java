package com.triton.johnson_tap_app;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.triton.johnson_tap_app.Adapter.PetBreedTypesListAdapter;
import com.triton.johnson_tap_app.api.APIInterface;
import com.triton.johnson_tap_app.api.RetrofitClient;
import com.triton.johnson_tap_app.requestpojo.BreedTypeRequest1;
import com.triton.johnson_tap_app.responsepojo.BreedTypeResponse1;

import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Spinner_addActivity extends AppCompatActivity implements PetBreedTypeSelectListener {

    private List<BreedTypeResponse1.DataBean> breedTypedataBeanList;
    PetBreedTypesListAdapter petBreedTypesListAdapter;
    private String PetBreedType = "";
    @SuppressLint("NonConstantResourceId")
    LinearLayout ll_pettypeandbreed;
    TextView Seleted;
    RecyclerView rv_breedtype;
    LinearLayout ll_pettype,ll_breedtype;
    String value = "";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner_add);

        ll_pettypeandbreed = (LinearLayout) findViewById(R.id.ll_pettypeandbreed);
        Seleted = (TextView) findViewById(R.id.selected);

        SharedPreferences sharedPreferences = getSharedPreferences("myKey", MODE_PRIVATE);
        value = sharedPreferences.getString("value","");
        Seleted.setText(value);

      //  Toast.makeText(Spinner_addActivity.this, "valueeeee" + value, Toast.LENGTH_LONG).show();

        ll_pettypeandbreed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {

                    Dialog dialog = new Dialog(Spinner_addActivity.this);
                    dialog.setContentView(R.layout.alert_pettype_layout);
                    dialog.setCanceledOnTouchOutside(false);

                    ll_breedtype = dialog.findViewById(R.id.ll_breedtype);
                    rv_breedtype = dialog.findViewById(R.id.rv_breedtype);

                    breedTypeResponseByPetIdCall("L-0112");

                    Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    dialog.show();

                } catch (WindowManager.BadTokenException e) {
                    e.printStackTrace();
                }
            }
        });

    }


    private void breedTypeResponseByPetIdCall(String petTypeId) {
            APIInterface apiInterface = RetrofitClient.getClient().create(APIInterface.class);
            Call<BreedTypeResponse1> call = apiInterface.breedTypeResponseByPetIdCall(RestUtils.getContentType(),breedTypeRequest(petTypeId));
            Log.w(TAG,"url  :%s"+ call.request().url().toString());

            call.enqueue(new Callback<BreedTypeResponse1>() {
                @SuppressLint("SetTextI18n")
                @Override
                public void onResponse(@NonNull Call<BreedTypeResponse1> call, @NonNull Response<BreedTypeResponse1> response) {
                    Log.w(TAG, "BreedTypeResponse" + "--->" + new Gson().toJson(response.body()));

                    if (response.body() != null) {
                        if (200 == response.body().getCode()) {
                            if(response.body().getData() != null) {
                                breedTypedataBeanList = response.body().getData();
                                Log.w(TAG, "BreedTypeResponse11111" + "--->" +breedTypedataBeanList.toString());
                                Log.w(TAG, "dataBeanList Size : " + breedTypedataBeanList.size());

                            }
                            if(breedTypedataBeanList != null && breedTypedataBeanList.size()>0){
                                setBreedTypeView(breedTypedataBeanList);
                            }else{
                                rv_breedtype.setVisibility(View.GONE);
                            }

                        }

                    }

                }
                public void onFailure(@NonNull Call<BreedTypeResponse1> call, @NonNull Throwable t) {
                    Log.w(TAG,"BreedTypeResponse flr"+"--->" + t.getMessage());
                }
            });

        }
        private BreedTypeRequest1 breedTypeRequest(String petTypeId) {
            BreedTypeRequest1 breedTypeRequest = new BreedTypeRequest1();
            breedTypeRequest.setJob_id(petTypeId);
            Log.w(TAG,"breedTypeRequest"+ "--->" + new Gson().toJson(breedTypeRequest));
            return breedTypeRequest;
        }

    private void setBreedTypeView(List<BreedTypeResponse1.DataBean> breedTypedataBeanList) {
        rv_breedtype.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        rv_breedtype.setItemAnimator(new DefaultItemAnimator());
        petBreedTypesListAdapter = new PetBreedTypesListAdapter(getApplicationContext(), breedTypedataBeanList,this);
        rv_breedtype.setAdapter(petBreedTypesListAdapter);

        Log.w(TAG,"per : "+ petBreedTypesListAdapter);
    }

    @Override
    public void petBreedTypeSelectListener(String petbreedtitle, String petbreedid) {
        PetBreedType = petbreedtitle;
        Log.w(TAG,"petBreedTypeSelectListener : "+"petbreedtitle : "+petbreedtitle+"petbreedid : "+petbreedid);
     //   txt_petandbreedtype.setText(petType+", "+petbreedtitle);

    }
}