package com.triton.johnson_tap_app.Db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "johnson.db";
    public static final int DATABASE_VERSION = 2;


    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(MRLIST_QUERY);
        db.execSQL(MRFORM_QUERY);
        db.execSQL(P_MRFORM_QUERY);
        db.execSQL(PAUSED_BMRLIST_QUERY);
        db.execSQL(SIGN_QUERY);
        db.execSQL(CUSTACK_QUERY);
        db.execSQL(CUSTOMER_QUERY);
        db.execSQL(CHECK_QUERY);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + MR_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + P_MR_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + SIGN_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + CUSTACK_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + PAUSED_BMRlIST_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + CUSTOMER_TABLE);
        onCreate(db);
    }

    public static  final String MRLIST_TABLE = "mrlist_table";
    public static final String MR_ID = "mr_id";
    public static  final String MR_TABLE = "mr_table";
    public static  final String PART_NAME = "part_name";
    public static final String PART_NO = "part_no";
    public static final String QUANTITY = "quantity";
    public static final String STATUS = "status";

    public static final String P_MR_ID = "P_mr_id";
    public static  final String P_MR_TABLE = "P_mr_table";
    public static  final String P_PART_NAME = "part_name";
    public static final String P_PART_NO = "paP_rt_no";
    public static final String P_QUANTITY = "P_quantity";
    public static final String P_STATUS = "P_status";

    public static final String JOBID = "jobid";
    public  static final String JOBSTATUS = "jobstatus";
    public static final String MYACTIVITY = "myactivity";

    public static final String ID = "id";
    public static final String SIGN_TABLE = "sign_table";
    public static final String SIGN_FILE = "signature";
    public static  final  String SIGN_PATH = "sign_path";

    public static final String CUSTACK_TABLE = "custack_table";
    public static final String CUSTACK_FILE = "custack_signature";
    public static  final  String CUSTACK_PATH = "custack_signpath";

    public static  final String PAUSED_BMRlIST_TABLE = "P_mrlist_table";
    public static  final String MR1 = "mr1";
    public static final String MR2 = "mr2";
    public static final String MR3 = "mr3";
    public static final String MR4 = "mr4";
    public static final String MR5 = "mr5";
    public static final String MR6 = "mr6";
    public static final String MR7 = "mr7";
    public static final String MR8 = "mr8";
    public static final String MR9 = "mr9";
    public static final String MR10 = "mr10";

    public static final String CUSTOMER_TABLE = "customer_table";
    public static final String CUSTOMER_NAME = "customer_name";
    public static final String CUSTOMER_NUMBER = "customer_number";
    public static final String CUSTOMER_REMARKS = "customer_remarks";

    public static final String CHECK_TABLE = "checktable";
    public static final String MONTH = "month";
    public static final String CHECKLIST = "checklist";
    public static final String PREVENTIVE_CHECKLIST = "preventive_checklist";
    public static final String MYNUM = "mynum";
    public static final String BD_DETAILS = "bddetails";
    public static final String FEEDBACK_GROUP = "feedback_group";
    public static final String FEEDBACK_DESCRIPTION = "feedback_description";
    public static final String FEEDBACK_REMARKS = "feedbackremarks";



    public static final String CHECK_QUERY = "CREATE TABLE "
            + CHECK_TABLE + " (" + ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, " + JOBID
            + " TEXT , " + MONTH
            + " TEXT , " + CHECKLIST
            + " TEXT , " + PREVENTIVE_CHECKLIST
            + " TEXT , " + BD_DETAILS
            + " TEXT , " + FEEDBACK_GROUP
            + " TEXT , " + FEEDBACK_DESCRIPTION
            + " TEXT , " + STATUS
            + " TEXT , " + MYNUM
            + " TEXT , " + FEEDBACK_REMARKS
            + " TEXT , " + MYACTIVITY
            + " TEXT );";


    public static final String SIGN_QUERY = "CREATE TABLE "
            + SIGN_TABLE + " (" + ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, " + JOBID
            + " TEXT , " + MYACTIVITY
            + " TEXT , " + SIGN_FILE
            + " TEXT , " + SIGN_PATH
            + " TEXT , " + STATUS
            + " TEXT );";

    public static final String CUSTACK_QUERY = "CREATE TABLE "
            + CUSTACK_TABLE + " (" + ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, " + JOBID
            + " TEXT , " + MYACTIVITY
            + " TEXT , " + CUSTACK_FILE
            + " TEXT , " + CUSTACK_PATH
            + " TEXT , " + STATUS
            + " TEXT );";

    public static final String MRFORM_QUERY = "CREATE TABLE "
            + MR_TABLE + " (" + MR_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, " + PART_NAME
            + " TEXT , " + PART_NO
            + " TEXT , " + QUANTITY
            + " TEXT , " + STATUS
            + " TEXT , " + JOBID
            + " TEXT );";

    public static final String P_MRFORM_QUERY = "CREATE TABLE "
            + P_MR_TABLE + " (" + P_MR_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, " + P_PART_NAME
            + " TEXT , " + P_PART_NO
            + " TEXT , " + P_QUANTITY
            + " TEXT , " + P_STATUS
            + " TEXT , " + JOBID
            + " TEXT );";

    public static  final String MRLIST_QUERY = "CREATE TABLE "
            + MRLIST_TABLE + " (" + ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, " + PART_NAME
            + " TEXT , " + PART_NO
            + " TEXT , " + QUANTITY
            + " TEXT , " + STATUS
            + " TEXT , " + JOBID
            + " TEXT , " + MYACTIVITY
            + " TEXT );";

    public static final String PAUSED_BMRLIST_QUERY = "CREATE TABLE "
            + PAUSED_BMRlIST_TABLE + " (" + MR_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, " + MR1
            + " TEXT , " + MR2
            + " TEXT , " + MR3
            + " TEXT , " + MR4
            + " TEXT , " + MR5
            + " TEXT , " + MR6
            + " TEXT , " + MR7
            + " TEXT , " + MR8
            + " TEXT , " + MR9
            + " TEXT , " + MR10
            + " TEXT , " + JOBID
            + " TEXT , " + MYACTIVITY
            + " TEXT );";

    public static final String CUSTOMER_QUERY = "CREATE TABLE "
            + CUSTOMER_TABLE + " (" + ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, " + CUSTOMER_NAME
            + " TEXT , " + CUSTOMER_NUMBER
            + " TEXT , " + CUSTOMER_REMARKS
            + " TEXT , " + MYACTIVITY
            + " TEXT , " + JOBID
            + " TEXT );";
}
