package com.triton.johnson_tap_app.NetworkUtility;

import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.triton.johnson_tap_app.R;

public class ConnectionReceiver extends BroadcastReceiver {

    Context mContext;

    @Override
    public void onReceive(Context context, Intent intent) {


        if (isConnected(context)){

            Toast.makeText(context,"Wifi/Network Connected",Toast.LENGTH_LONG).show();
        }
        else {

            showDialog(context);
        }


    }

    private void showDialog(Context context){

            AlertDialog.Builder mBuilder = new AlertDialog.Builder(context);
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View mView = inflater.inflate(R.layout.dialog_nointernet, null);
            Button btn_Retry = mView.findViewById(R.id.btn_retry);


            mBuilder.setView(mView);
            final Dialog dialog= mBuilder.create();
            dialog.show();
            dialog.setCanceledOnTouchOutside(false);

            btn_Retry.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (isConnected(context)){
                        dialog.dismiss();
                    }
                    else{

                        Toast.makeText(context,"Please Turn On Internet",Toast.LENGTH_SHORT).show();
                    }

                }
            });
    }

    public boolean isConnected(Context context){

        try{
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo info = cm.getActiveNetworkInfo();
            return (info != null && info.isConnected());

        }catch (NullPointerException e){
            e.printStackTrace();
            return false;
        }
    }
}
