package com.triton.johnson_tap_app.Service_Activity.Preventive_Services;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.triton.johnson_tap_app.R;
import com.triton.johnson_tap_app.Service_Activity.InputValueFormListActivity;

public class Quarterly_Top_PitActivity extends AppCompatActivity {

    TextView text;
    CardView yes,no,na;
    String value;
    ImageView iv_back;
    String job_id,feedback_group,feedback_details,bd_dta,feedback_remark;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_quarterly_top_pit);

        text = findViewById(R.id.text);
        yes = findViewById(R.id.card_yes);
        no = findViewById(R.id.card_no);
        na = findViewById(R.id.card_na);
        iv_back = (ImageView) findViewById(R.id.iv_back);

        Spannable name_Upload = new SpannableString("Check tightening of drive unit base mounting bolts ");
        name_Upload.setSpan(new ForegroundColorSpan(Quarterly_Top_PitActivity.this.getResources().getColor(R.color.colorAccent)), 0, name_Upload.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        text.setText(name_Upload);
        Spannable name_Upload1 = new SpannableString("*");
        name_Upload1.setSpan(new ForegroundColorSpan(Color.RED), 0, name_Upload1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        text.append(name_Upload1);

        yes.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent send = new Intent( Quarterly_Top_PitActivity.this,Recycler_SpinnerActivity.class);
                startActivity(send);
            }
        });

        no.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent send = new Intent( Quarterly_Top_PitActivity.this, Recycler_SpinnerActivity.class);
                startActivity(send);
            }
        });

        na.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent send = new Intent( Quarterly_Top_PitActivity.this, InputValueFormListActivity.class);
                startActivity(send);
            }
        });

        iv_back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent send = new Intent(Quarterly_Top_PitActivity.this, Esc_TrvActivity.class);
                startActivity(send);
            }
        });
    }
}