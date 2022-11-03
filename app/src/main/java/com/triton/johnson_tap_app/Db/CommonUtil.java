package com.triton.johnson_tap_app.Db;

import android.content.Context;
import android.content.SharedPreferences;

public class CommonUtil {

    public static final String MyPREFERENCES = "Preference";
    public static SharedPreferences pref;
    public static Context context;
    public static DbUtil dbUtil;
    public static DbHelper dbHelper;


    public CommonUtil(Context context) {
        CommonUtil.context = context;
        pref = CommonUtil.context.getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
    }

    // STATUS....
    // BD DETAILS - 1
    // FEEDBACK GROUP -2
    // FEEDBACK DESCRIPTION -3
    // FEEDBACK REMARK - 4

    // MONTH LIST & ESCTRV - 1
    // PREVENTIVE CHECKLIST - 2
    // Recycler Spinner - 3
}
