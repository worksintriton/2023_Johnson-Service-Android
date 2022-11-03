package com.triton.johnson_tap_app.session;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import com.triton.johnson_tap_app.activity.LoginActivity;

import java.util.HashMap;

public class SessionManager1 {


    private SharedPreferences pref;
    // Editor for Shared preferences
    private SharedPreferences.Editor editor;

    // Context
    private Context context;
    // Sharedpref file name
    private static final String PREF_NAME = "AndroidHivePref";
    // All Shared Preferences Keys
    private static final String IS_LOGIN = "IsLoggedIn";


    // User name (make variable public to access from outside)
    public static final String KEY_MESSAGE = "message";
    public static final String KEY_USER_LEVEL = "userlevel";
    public static final String KEY_STATION_CODE = "stationcode";
    public static final String KEY_STATION_NAME = "stationname";

    public static final String KEY_ID = "_id";
    public static final String KEY_USER_MOBILE_NO = "user_mobile_no";
    public static final String KEY_USER_PASSWORD = "user_password";
    public static final String KEY_USER_PER_MOB = "user_per_mob";
    public static final String KEY_USERNAME = "user_name";
    public static final String KEY_USER_EMAIL = "user_email";

    public static final String KEY_USER_INTRODUCED_BY = "user_introduced_by";
    public static final String KEEPDEPARTMENTLOGIN = "true";

    public static final String KEY_USER_LOCATION = "user_location";
    public static final String KEY_USER_MOB_MODEL = "user_mob_model";
    public static final String KEY_USER_MOB_OS = "user_mob_os";
    public static final String KEY_USER_MOB_MAKE = "user_mob_make";
    public static final String KEY_DEVICE_NO = "device_no";

    public static final String KEY_ORGANISATION_NAME = "organisation_name";
    public static final String KEY_STATUS = "status";
    public static final String KEY_MOBILE_ISSUE_DATE = "mobile_issue_date";
    public static final String KEY_DOUCMENTS = "Documents";
    public static final String KEY_DELETE_STATUS = "delete_status";

    public static final String KEY_LAST_LOGIN_TIME = "last_login_time";
    public static final String KEY_LAST_LOGOUT_TIME = "last_logout_time";
    public static final String KEY_USER_TOKEN = "user_token";

    private String TAG ="SessionManager";

    @SuppressLint("CommitPrefEdits")
    public SessionManager1(Context applicationContext) {

        // TODO Auto-generated constructor stub

        this.context = applicationContext;
        // Shared pref mode
        int PRIVATE_MODE = 0;
        pref = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();

    }

    public HashMap<String, String> getUserDetails() {
        // TODO Auto-generated method stub

        HashMap<String, String> user = new HashMap<>();

        user.put(KEY_MESSAGE, pref.getString(KEY_MESSAGE, null));
        user.put(KEY_USER_LEVEL, pref.getString(KEY_USER_LEVEL, null));
        user.put(KEY_STATION_CODE, pref.getString(KEY_STATION_CODE, null));
        user.put(KEY_STATION_NAME, pref.getString(KEY_STATION_NAME, null));


        user.put(KEY_USER_MOBILE_NO, pref.getString(KEY_USER_MOBILE_NO, null));
        user.put(KEY_USER_PASSWORD, pref.getString(KEY_USER_PASSWORD, null));
        user.put(KEY_ID, pref.getString(KEY_ID, null));
        user.put(KEY_USERNAME, pref.getString(KEY_USERNAME, null));
        user.put(KEY_USER_PER_MOB, pref.getString(KEY_USER_PER_MOB, null));
        user.put(KEY_USER_EMAIL, pref.getString(KEY_USER_EMAIL, null));
        user.put(KEY_USER_INTRODUCED_BY, pref.getString(KEY_USER_INTRODUCED_BY, null));
        Log.w(TAG,"KEY_ID :"+KEY_ID+" KEY_MOBILE_NO : "+KEY_USER_MOBILE_NO+" KEY_USER_PASSWORD : "+KEY_USER_PASSWORD);
        return user;

    }

    public boolean checkLogin() {
        // TODO Auto-generated method stub
        if (!this.isLoggedIn()) {

            // user is not logged in redirect him to Login Activity
            Intent i = new Intent(context, LoginActivity.class);
            // Closing all the Activities
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            // Add new Flag to start new Activity
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            // Staring Login Activity
            context.startActivity(i);

            return true;
        }
        return false;
    }

    public void logoutUser() {
        // Clearing all data from Shared Preferences
        editor.clear();
        editor.commit();

        Intent i = new Intent(context, LoginActivity.class);
        // Closing all the Activities
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        // Add new Flag to start new Activity
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        // Staring Login Activity
        context.startActivity(i);
        Log.w(TAG,"logoutUser : ");

    }

    public boolean isLoggedIn() {
        // TODO Auto-generated method stub
        return pref.getBoolean(IS_LOGIN, false);
    }

/*    public void createLoginSession(String message, String user_level, String station_code, String role, String station_name, String empid, String name, String username, String mobile) {

        editor.putBoolean(IS_LOGIN, true);
        editor.putString(KEY_MESSAGE, message);
        editor.putString(KEY_USER_LEVEL, user_level);
        editor.putString(KEY_STATION_CODE, station_code);
        Log.w(TAG,"KEY_STATION_CODE Editor :"+KEY_STATION_CODE);
        editor.putString(KEY_STATION_NAME, station_name);
        editor.putString(KEY_ROLE, role);
        editor.putString(KEY_EMPID, empid);
        editor.putString(KEY_NAME, name);
        editor.putString(KEY_USERNMAE, username);
        editor.putString(KEY_MOBILE, mobile);
        editor.commit();

    }*/

    public void createSessionLogin(String _id) {
        editor.putBoolean(IS_LOGIN, true);
        editor.putString(KEY_ID, _id);
//        editor.putString(KEY_USER_MOBILE_NO, user_mobile_no);
//        editor.putString(KEY_USER_PASSWORD, user_password);
//        editor.putString(KEY_USERNAME, user_name);
//        editor.putString(KEY_USER_PER_MOB, user_per_mob);
//        editor.putString(KEY_USER_EMAIL,user_email);
//        editor.putString(KEY_USER_INTRODUCED_BY,user_introduced_by);
//        editor.putString(KEY_USER_LOCATION,user_location);
//        editor.putString(KEY_USER_MOB_MODEL,user_mob_model);
//        editor.putString(KEY_USER_MOB_OS,user_mob_os);
        editor.commit();

    }



    public void createDepartmentLoginSession() {

        editor.putBoolean(KEEPDEPARTMENTLOGIN, true);

        editor.commit();

    }

    public void setIsLogin(boolean isLoogedIn){
        editor.putBoolean(IS_LOGIN,isLoogedIn);
        editor.apply();
    }


}
