package com.triton.johnson_tap_app.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Dialog;
import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.triton.johnson_tap_app.R;

import java.util.Objects;

public class DownloadapkfileActivity extends AppCompatActivity {

    private String TAG ="DownloadapkfileActivity";
    Button btn_download;
    ProgressDialog progressDialog;
    Dialog submittedSuccessfulalertdialog;

    DownloadManager manager;
    String apk_link,apk_version;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_downloadapkfile);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            apk_link = extras.getString("apk_link");

            Log.w(TAG,"activity_id -->"+apk_link);

        }

        if (extras != null) {
            apk_version = extras.getString("apk_version");

            Log.w(TAG,"activity_version -->"+apk_version);
        }
        //checkPermission();
       // requestPermission();
        isStoragePermissionGranted();

        btn_download = findViewById(R.id.btn_download);
        btn_download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                DownloadManager downloadManager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
//                Uri uri= Uri.parse("http://smart.johnsonliftsltd.com:3000/api/uploads/tab_13_05_2022_1.apk");
//                DownloadManager.Request request = new DownloadManager.Request(uri);
//                request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI |
//                        DownloadManager.Request.NETWORK_MOBILE);
//                request.setTitle("Jlsmart APK");
//                request.allowScanningByMediaScanner();
//                request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
//                request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS,"JlsmartDownload");
//                //  request.setMimeType("*/*");
//                downloadManager.enqueue(request);

//                DownloadManager downloadManager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
//                Uri uri= Uri.parse(apk_link);
//                DownloadManager.Request request = new DownloadManager.Request(uri);
//                request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI | DownloadManager.Request.NETWORK_MOBILE);
//                request.setTitle("JlsmartTab APK-" + apk_version);
//                request.setDescription("JlsmartTab download using DownloadManager.");
//
//                request.allowScanningByMediaScanner();
//                request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
//                request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS,"JlsmartTab-" + apk_version);
//              //  request.setMimeType("*/*");
//                downloadManager.enqueue(request);

                progressDialog = new ProgressDialog(DownloadapkfileActivity.this);
                progressDialog.setIndeterminate(true);
                progressDialog.setMessage("Jlsmart APK Download Please Wait...");
                progressDialog.setCanceledOnTouchOutside(false);
                progressDialog.setCancelable(false);
                progressDialog.show();

                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        showSubmittedSuccessful();

                        progressDialog.dismiss();

                    }
                }, 15000);


                String urlString = apk_link;
                Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse(urlString));
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setPackage("com.android.chrome");
                try {
                    startActivity(intent);
                } catch (ActivityNotFoundException ex) {
                    // Chrome browser presumably not installed so allow user to choose instead
                    intent.setPackage(null);
                    startActivity(intent);
                }


            }
        });
    }

    private void showSubmittedSuccessful() {

        Log.w(TAG, "showSubmittedSuccessful -->+");
        submittedSuccessfulalertdialog = new Dialog(DownloadapkfileActivity.this);
        submittedSuccessfulalertdialog.setCancelable(false);
        submittedSuccessfulalertdialog.setContentView(R.layout.pop);
        Button btn_goback = submittedSuccessfulalertdialog.findViewById(R.id.btn_goback);
        btn_goback.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                submittedSuccessfulalertdialog.dismiss();
                overridePendingTransition(R.anim.new_right, R.anim.new_left);
                submittedSuccessfulalertdialog.dismiss();

            }
        });
        Objects.requireNonNull(submittedSuccessfulalertdialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        submittedSuccessfulalertdialog.show();
    }

    //    private boolean checkPermission() {
//        int result = ContextCompat.checkSelfPermission(getApplicationContext(), WRITE_EXTERNAL_STORAGE);
//        int result1 = ContextCompat.checkSelfPermission(getApplicationContext(), READ_EXTERNAL_STORAGE);
//
//        return result == PackageManager.PERMISSION_GRANTED && result1 == PackageManager.PERMISSION_GRANTED;
//    }
//    private void requestPermission() {
//        ActivityCompat.requestPermissions(this, new String[]{WRITE_EXTERNAL_STORAGE, READ_EXTERNAL_STORAGE}, 200);
//    }
    public  boolean isStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                Log.v(TAG,"Permission is granted");
                return true;
            } else {

                Log.v(TAG,"Permission is revoked");
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                return false;
            }
        }
        else { //permission is automatically granted on sdk<23 upon installation
            Log.v(TAG,"Permission is granted");
            return true;
        }
    }
}