package com.triton.johnson_tap_app;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.triton.johnson_tap_app.Adapter.AddEducAdapter;
import com.triton.johnson_tap_app.requestpojo.DocUploadRequest;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import es.dmoral.toasty.Toasty;

public class TestActivity extends AppCompatActivity {

    TextView s,s1,n,n1;
    Button btn;
    String str_s,str_s1,str_n,str_n1;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rcyrl_added_edu)
    RecyclerView recylerView_added_education;
    List<DocUploadRequest.EducationDetailsBean> educationDetailsBeans = new ArrayList<>();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        s = (TextView) findViewById(R.id.s);
        s1 = (TextView) findViewById(R.id.s1);
        n = (TextView) findViewById(R.id.n);
        n1 = (TextView) findViewById(R.id.n1);
        btn = (Button) findViewById(R.id.btn);

        recylerView_added_education.setHasFixedSize(true);
        recylerView_added_education.setNestedScrollingEnabled(false);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        recylerView_added_education.setLayoutManager(layoutManager);

        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                str_s = "1";
                str_s1 = "Reva";
                str_n = "2";
                str_n1 = "Revathy";

                insertEdu(str_s, str_s1, str_n, str_n1);
            }
        });
    }

        private void insertEdu(String job_no, String job_name, String job_no1, String job_name1) {
            Log.w(TAG,"educationDetailsBeans size: "+educationDetailsBeans.size());

                Toasty.success(getApplicationContext(),"Eductation added successfully",Toast.LENGTH_SHORT).show();
                educationDetailsBeans.add(new DocUploadRequest.EducationDetailsBean(job_no,job_name));
                Log.w(TAG,"educationDetailsBeans : "+new Gson().toJson(educationDetailsBeans));
                if(educationDetailsBeans != null && educationDetailsBeans.size()>0){
                    recylerView_added_education.setVisibility(View.VISIBLE);
                    recylerView_added_education.setHasFixedSize(true);
                    recylerView_added_education.setNestedScrollingEnabled(false);
                    setViewEducation();
                }else{
                    recylerView_added_education.setVisibility(View.GONE);

                }

            }

        private void setViewEducation() {
            recylerView_added_education.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
            recylerView_added_education.setItemAnimator(new DefaultItemAnimator());
            AddEducAdapter addEducAdapter = new AddEducAdapter(getApplicationContext(), educationDetailsBeans);
            recylerView_added_education.setAdapter(addEducAdapter);
        }
}