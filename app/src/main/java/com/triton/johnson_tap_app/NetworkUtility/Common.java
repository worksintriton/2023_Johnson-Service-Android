package com.triton.johnson_tap_app.NetworkUtility;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.widget.Toast;

import java.net.NetworkInterface;

public class Common {

    public static boolean isConnectedtoInternet(Context context){

        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(context.CONNECTIVITY_SERVICE);

        NetworkInfo wifi=connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);


        NetworkInfo  network=connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

        if (connectivityManager != null){

            NetworkInfo[] info = connectivityManager.getAllNetworkInfo();
            if(info != null){

                for (int i = 0; i<info.length;i++){
                    if (info[i].getState() == NetworkInfo.State.CONNECTED);
                    return true;
                }
            }
        }

        return false;
    }
}
