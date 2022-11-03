package com.triton.johnson_tap_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.triton.johnson_tap_app.Adapter.UserTypesListAdapter;
import com.triton.johnson_tap_app.api.APIInterface;
import com.triton.johnson_tap_app.api.RetrofitClient;
import com.triton.johnson_tap_app.responsepojo.UserTypeListResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Sample_testActivity extends AppCompatActivity implements UserTypeSelectListener{

    private static final String TAG = "ChooseUserTypeActivity";

    RecyclerView rv_usertype;
    TextView tv_norecords;
    Button btn_change;
    String petimage;
    List<UserTypeListResponse.DataBean.UsertypedataBean> usertypedataBeanList;
    private String UserType;
    private int UserTypeValue = 4;
    private String firstname,lastname,useremail,referralcode,userphone;
    private String verified;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample_test);

        tv_norecords = (TextView)findViewById(R.id.tv_norecords);
        rv_usertype =(RecyclerView)findViewById(R.id.rv_usertype) ;
        btn_change = (Button)findViewById(R.id.btn_change);

        userTypeListResponseCall(UserTypeValue);

        btn_change.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String data = "";
                for (int i = 0; i < usertypedataBeanList.size(); i++) {
                    UserTypeListResponse.DataBean.UsertypedataBean singleStudent =usertypedataBeanList.get(i);
                    if (singleStudent.isSelected() == true) {
                        data = data + "\n" + usertypedataBeanList.get(i).getUser_type_title().toString();
                    }
                    }
                //  Toast.makeText(Sample_testActivity.this, "Selected Students: \n" + data, Toast.LENGTH_LONG).show();
            }
        });

    }

    @SuppressLint("LogNotTimber")
    public void userTypeListResponseCall(int userTypeValue){

        APIInterface apiInterface = RetrofitClient.getClient().create(APIInterface.class);
        Call<UserTypeListResponse> call = apiInterface.userTypeListResponseCall(RestUtils.getContentType());
        Log.w(TAG,"url  :%s"+ call.request().url().toString());

        call.enqueue(new Callback<UserTypeListResponse>() {
            @SuppressLint("LogNotTimber")
            @Override
            public void onResponse(@NonNull Call<UserTypeListResponse> call, @NonNull Response<UserTypeListResponse> response) {

                if (response.body() != null) {
                    if (200 == response.body().getCode()) {
                        Log.w(TAG, "UserTypeListResponse" + new Gson().toJson(response.body()));

                        if (response.body().getData().getUsertypedata() != null) {
                            usertypedataBeanList = response.body().getData().getUsertypedata();
                        }


                        if (usertypedataBeanList != null && usertypedataBeanList.size() > 0) {
                            setView(userTypeValue);
                        }
                    }

                }
            }
            public void onFailure(@NonNull Call<UserTypeListResponse> call,@NonNull  Throwable t) {
                Log.w(TAG,"UserTypeListResponse flr"+t.getMessage());
            }
        });

    }

    @SuppressLint("LogNotTimber")
    private void setView(int userTypeValue) {
        for(int i=0; i<usertypedataBeanList.size();i++){
            if(userTypeValue == usertypedataBeanList.get(i).getUser_type_value()){
                usertypedataBeanList.get(i).setSelected(true);
                break;
            }
        }
        Log.w(TAG, "setView : "+userTypeValue);
        rv_usertype.setLayoutManager(new GridLayoutManager(this, 2));
        rv_usertype.setItemAnimator(new DefaultItemAnimator());
        UserTypesListAdapter userTypesListAdapter = new UserTypesListAdapter(getApplicationContext(), usertypedataBeanList,this,userTypeValue);
        rv_usertype.setAdapter(userTypesListAdapter);
    }


    @SuppressLint("LogNotTimber")
    @Override
    public void userTypeSelectListener(String usertype, int usertypevalue) {
        UserType = usertype;
        UserTypeValue = usertypevalue;
      //  Log.w(TAG,"userTypeSelectListener : "+" usertype : "+usertype+" usertypevalue : "+usertypevalue);

        Log.w(TAG,"myPetsSelectListener : "+ "petList" +new Gson().toJson(usertypedataBeanList));

        if(usertypedataBeanList != null && usertypedataBeanList.size()>0) {
            for (int i = 0; i < usertypedataBeanList.size(); i++) {
                if (usertype.equalsIgnoreCase(usertypedataBeanList.get(i).get_id())) {
                    petimage = usertypedataBeanList.get(i).getUser_type_title();
                }
                Log.w(TAG, "myPetsSelectListener : " + "petimage" + petimage);

            }
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

}