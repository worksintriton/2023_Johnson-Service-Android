package com.triton.johnson_tap_app.session;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import com.triton.johnson_tap_app.activity.LoginActivity;

import java.util.HashMap;

public class SessionManager {


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
    public static final String KEY_USERID = "user_id";
    public static final String KEY_USERAGENT = "user_agent_code";
    public static final String KEY_EMAILID = "emailid";
    public static final String KEY_USERNAME = "name";
    public static final String KEY_ROLE = "role";

    public static final String KEY_MOBILE = "mobile";
    public static final String KEEPDEPARTMENTLOGIN = "true";

    public static final String KEY_DESIGNATION = "userdesignation";
    public static final String KEY_TOKEN = "usertoken";
    public static final String KEY_USERSTATUS = "userstatus";
    public static final String KEY_USERTYPE = "usertype";
    public static final String KEY_USERROLE = "userrole";

    private String TAG ="SessionManager";

    @SuppressLint("CommitPrefEdits")
    public SessionManager(Context applicationContext) {

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
        user.put(KEY_USERID, pref.getString(KEY_USERID, null));
        user.put(KEY_USERAGENT, pref.getString(KEY_USERAGENT, null));
        user.put(KEY_ID, pref.getString(KEY_ID, null));
        user.put(KEY_USERNAME, pref.getString(KEY_USERNAME, null));
        user.put(KEY_MOBILE, pref.getString(KEY_MOBILE, null));
        user.put(KEY_ROLE, pref.getString(KEY_ROLE, null));
        user.put(KEY_USERROLE, pref.getString(KEY_USERROLE, null));
        Log.w(TAG,"KEY_ID :"+KEY_ID+" KEY_ROLE : "+KEY_ROLE+" KEY_USERROLE : "+KEY_USERROLE);
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

    public void createSessionLogin(String id1, String s, String id, String userid, String username, String user_email, String designation, String token, String status, String user_type, String user_role, String user_agent_code) {
        editor.putBoolean(IS_LOGIN, true);
        editor.putString(KEY_ID, id);
        editor.putString(KEY_USERID, userid);
        editor.putString(KEY_USERAGENT, user_agent_code);
        editor.putString(KEY_USERNAME, username);
        editor.putString(KEY_EMAILID, user_email);
        editor.putString(KEY_DESIGNATION,designation);
        editor.putString(KEY_TOKEN,token);
        editor.putString(KEY_USERSTATUS,status);
        editor.putString(KEY_USERTYPE,user_type);
        editor.putString(KEY_USERROLE,user_role);
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
