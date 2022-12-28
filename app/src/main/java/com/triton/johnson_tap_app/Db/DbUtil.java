package com.triton.johnson_tap_app.Db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.io.File;

public class DbUtil {

    public static SQLiteDatabase db;
    Context context;
    ContentValues values;
    DbHelper dbHelper;

    public DbUtil(Context context) {
        this.context = context;
        values = new ContentValues();
    }

    public SQLiteDatabase open() {
        if (dbHelper == null) {
            dbHelper = new DbHelper(context);
        }
        db = dbHelper.getWritableDatabase();
        return db;
    }

    public void close() {
        if (dbHelper != null) {
            db.close();
        }
    }

    public static final String[] MRList_FIELD = {
            DbHelper.MR_ID,
            DbHelper.PART_NAME,
            DbHelper.PART_NO,
            DbHelper.QUANTITY,
            DbHelper.STATUS,
            DbHelper.MYACTIVITY,
            DbHelper.JOBID
    };

    public static final String[] MRLISTS_FIELD = {
            DbHelper.ID,
            DbHelper.PART_NAME,
            DbHelper.PART_NO,
            DbHelper.QUANTITY,
            DbHelper.STATUS,
            DbHelper.JOBID,
            DbHelper.MYACTIVITY
    };

    public static final String[] P_MRList_FIELD = {
            DbHelper.P_MR_ID,
            DbHelper.P_PART_NAME,
            DbHelper.P_PART_NO,
            DbHelper.P_QUANTITY,
            DbHelper.P_STATUS,
            DbHelper.JOBID
    };

    public static final String[] SIGNFIELD = {
            DbHelper.ID,
            DbHelper.JOBID,
            DbHelper.MYACTIVITY,
            DbHelper.SIGN_FILE,
            DbHelper.SIGN_PATH,
            DbHelper.STATUS
    };

    public static final String[] CUSTACK_FIELD = {
            DbHelper.ID,
            DbHelper.JOBID,
            DbHelper.MYACTIVITY,
            DbHelper.CUSTACK_FILE,
            DbHelper.CUSTACK_PATH,
            DbHelper.STATUS
    };

    public static final String[] PAUSED_BMRList_FIELD = {
            DbHelper.MR_ID,
            DbHelper.MR1,
            DbHelper.MR2,
            DbHelper.MR3,
            DbHelper.MR4,
            DbHelper.MR5,
            DbHelper.MR6,
            DbHelper.MR7,
            DbHelper.MR8,
            DbHelper.MR9,
            DbHelper.MR10,
            DbHelper.JOBID,
            DbHelper.MYACTIVITY
    };

    public static final String[] CUSTOMER_FIELD = {
            DbHelper.JOBID,
            DbHelper.MYACTIVITY,
            DbHelper.ID,
            DbHelper.CUSTOMER_NAME,
            DbHelper.CUSTOMER_NUMBER,
            DbHelper.CUSTOMER_REMARKS,

    };

    public static final String[] CHECK_FIELD = {
            DbHelper.JOBID,
            DbHelper.MYACTIVITY,
            DbHelper.ID,
            DbHelper.MONTH,
            DbHelper.MYNUM,
            DbHelper.CHECKLIST,
            DbHelper.PREVENTIVE_CHECKLIST,
            DbHelper.BD_DETAILS,
            DbHelper.FEEDBACK_GROUP,
            DbHelper.FEEDBACK_DESCRIPTION,
            DbHelper.FEEDBACK_REMARKS,
            DbHelper.STATUS
    };

    public void Preportdelete(String job_id) {
        db.delete(DbHelper.P_MR_TABLE, DbHelper.JOBID + "= '" + job_id + "'", null);

    }
    public void reportdelete(String job_id) {
        db.delete(DbHelper.MR_TABLE, DbHelper.JOBID + "= '" + job_id + "'", null);

    }

    public int reportDeleteMonthList(String job_id, String service_title) {

        return db.delete(DbHelper.CHECK_TABLE,DbHelper.JOBID + "= '" + job_id + "'" + "AND " + DbHelper.MYACTIVITY + "= '" + service_title + "'",null) ;
    }

    public int reportDeletePreventiveList(String job_id, String service_title) {

        return db.delete(DbHelper.CHECK_TABLE,DbHelper.JOBID + "= '" + job_id + "'" + "AND " + DbHelper.MYACTIVITY + "= '" + service_title + "'",null) ;

    }

    public int deleteSign(String job_id, String myactivity) {
        return db.delete(DbHelper.SIGN_TABLE,DbHelper.JOBID + "= '" + job_id + "'" + "AND " + DbHelper.MYACTIVITY + "= '" + myactivity + "'",null) ;
    }

    public int deleteBreakdownMR(String job_id, String myactivity) {
        return db.delete(DbHelper.PAUSED_BMRlIST_TABLE,DbHelper.JOBID + "= '" + job_id + "'" + "AND " + DbHelper.MYACTIVITY + "= '" + myactivity + "'",null);
    }

    public int deleteCustAck(String job_id, String myactivity) {
        return db.delete(DbHelper.CUSTACK_TABLE,DbHelper.JOBID + "= '" + job_id + "'" + "AND " + DbHelper.MYACTIVITY + "= '" + myactivity + "'",null) ;
    }



    public long addMR(String strPartname, String strPartno, String strQuantity, String job_id) {
        values.clear();
        values.put(DbHelper.PART_NAME,strPartname);
        values.put(DbHelper.PART_NO,strPartno);
        values.put(DbHelper.QUANTITY,strQuantity);
        values.put(DbHelper.JOBID,job_id);
        return db.insert(DbHelper.MR_TABLE,null,values);
    }

    public Cursor getMR(String job_id) {
        return db.query(DbHelper.MR_TABLE,MRList_FIELD,DbHelper.JOBID + "= '" + job_id + "'",null,null,null,null);
    }


    public int deleteMR(String toString) {
         return db.delete(DbHelper.MR_TABLE,DbHelper.MR_ID + "= '" + toString + "'",null);
    }



    public long addPMR(String strPartname, String strPartno, String strQuantity, String job_id) {
        values.clear();
        values.put(DbHelper.P_PART_NAME,strPartname);
        values.put(DbHelper.P_PART_NO,strPartno);
        values.put(DbHelper.P_QUANTITY,strQuantity);
        values.put(DbHelper.JOBID,job_id);
        return db.insert(DbHelper.P_MR_TABLE,null,values);
    }

    public Cursor getPMR(String job_id) {
        return db.query(DbHelper.P_MR_TABLE,P_MRList_FIELD,DbHelper.JOBID + "= '" + job_id + "'",null,null,null,null);
    }


    public long addBreakdownMRList(String s_mr1, String s_mr2, String s_mr3, String s_mr4,
                                   String s_mr5, String s_mr6, String s_mr7, String s_mr8,
                                   String s_mr9, String s_mr10, String job_id, String myactivity) {

        values.clear();
        values.put(DbHelper.MR1,s_mr1);
        values.put(DbHelper.MR2,s_mr2);
        values.put(DbHelper.MR3,s_mr3);
        values.put(DbHelper.MR4,s_mr4);
        values.put(DbHelper.MR5,s_mr5);
        values.put(DbHelper.MR6,s_mr6);
        values.put(DbHelper.MR7,s_mr7);
        values.put(DbHelper.MR8,s_mr8);
        values.put(DbHelper.MR9,s_mr9);
        values.put(DbHelper.MR10,s_mr10);
        values.put(DbHelper.JOBID,job_id);
        values.put(DbHelper.MYACTIVITY,myactivity);
        return db.insert(DbHelper.PAUSED_BMRlIST_TABLE,null,values);

    }

    public Cursor getBreakdownMrList() {

        return db.query(DbHelper.PAUSED_BMRlIST_TABLE,PAUSED_BMRList_FIELD,null,null,null,null,null);
    }

    public Cursor getBreakdownMrList(String job_id, String myactivity) {
        return db.query(DbHelper.PAUSED_BMRlIST_TABLE,PAUSED_BMRList_FIELD,
                dbHelper.JOBID + "= '" + job_id + "'" + "AND " + DbHelper.MYACTIVITY + "= '" + myactivity + "'", null,null,null,null );
    }

    public long addEngSign(String job_id, String myactivity, String uploadimagepath, File file, String s) {

        values.clear();
        values.put(DbHelper.JOBID,job_id);
        values.put(DbHelper.MYACTIVITY,myactivity);
        values.put(DbHelper.SIGN_PATH,uploadimagepath);
        values.put(DbHelper.SIGN_FILE, String.valueOf(file));
        values.put(DbHelper.STATUS,s);
        return db.insert(DbHelper.SIGN_TABLE,null,values) ;

    }

    public Cursor getEngSign(String job_id, String myactivity) {
        return db.query(DbHelper.SIGN_TABLE,SIGNFIELD,DbHelper.JOBID + "= '" + job_id + "'" + "AND " +
                DbHelper.MYACTIVITY + "= '" + myactivity + "'",null,null,null,null);
    }

    public int updatesign(String job_id, String myactivity, String s) {
        values.clear();
        values.put(DbHelper.JOBID,job_id);
        values.put(DbHelper.MYACTIVITY,myactivity);
        values.put(DbHelper.STATUS,s);
        return db.update(DbHelper.SIGN_TABLE,values,DbHelper.JOBID + "= '" + job_id + "'" +
                "AND " + DbHelper.MYACTIVITY + "' " + myactivity + "'",null);
    }

    public Cursor getEngSign() {
        return db.query(DbHelper.SIGN_TABLE,SIGNFIELD,null,null,null,null,null);
    }


    public long addCustAck(String job_id, String myactivity, String uploadimagepath, File file) {
        values.clear();
        values.put(DbHelper.JOBID,job_id);
        values.put(DbHelper.MYACTIVITY,myactivity);
        values.put(DbHelper.CUSTACK_PATH,uploadimagepath);
        values.put(DbHelper.CUSTACK_FILE, String.valueOf(file));
        return db.insert(DbHelper.CUSTACK_TABLE,null,values) ;
    }

    public Cursor getCustAck(String job_id, String myactivity) {
        return db.query(DbHelper.CUSTACK_TABLE,CUSTACK_FIELD,DbHelper.JOBID + "= '" + job_id + "'" + "AND " +
                DbHelper.MYACTIVITY + "= '" + myactivity + "'",null,null,null,null);
    }


    public boolean hasMR(String strPartname, String job_id,String partno, String quantity) {

        String abc = DbHelper.PART_NAME + "='" + strPartname + "'" + "AND " + DbHelper.JOBID + "= '" + job_id + "'"
                + "AND " + DbHelper.PART_NO + "= '" + partno + "'" + "AND " + DbHelper.QUANTITY+ "= '" + quantity + "'";
        Cursor cursor = db.rawQuery("SELECT * FROM " + DbHelper.MR_TABLE + " WHERE " + abc, null);
        if(cursor.getCount() <= 0){
            cursor.close();
            return false;
        }
        cursor.close();
        return true;

    }


    public boolean hasPMR(String strPartname, String job_id, String strPartno, String strQuantity) {

        String abc = DbHelper.P_PART_NAME + "='" + strPartname + "'" + "AND " + DbHelper.JOBID + "= '" + job_id + "'"
                + "AND " + DbHelper.P_PART_NO + "= '" + strPartno + "'" + "AND " + DbHelper.P_QUANTITY+ "= '" + strQuantity + "'";
        Cursor cursor = db.rawQuery("SELECT * FROM " + DbHelper.P_MR_TABLE + " WHERE " + abc, null);
        if(cursor.getCount() <= 0){
            cursor.close();
            return false;
        }
        cursor.close();
        return true;
    }

    public long addCustomer(String job_id, String service_title, String str_custname, String str_custno, String str_custremarks) {
        values.clear();
        values.put(DbHelper.JOBID,job_id);
        values.put(DbHelper.MYACTIVITY,service_title);
        values.put(DbHelper.CUSTOMER_NAME,str_custname);
        values.put(DbHelper.CUSTOMER_NUMBER,str_custno);
        values.put(DbHelper.CUSTOMER_REMARKS,str_custremarks);
        return db.insert(DbHelper.CUSTOMER_TABLE,null,values);
    }

    public Cursor getCustmer(String job_id, String service_title) {
        return db.query(DbHelper.CUSTOMER_TABLE,CUSTOMER_FIELD,DbHelper.JOBID + "= '" + job_id + "'" + "AND " +
                DbHelper.MYACTIVITY + "= '" + service_title + "'",null,null,null,null);
    }

    public long addCheckList(String jobid, String service_title, String data, String s) {

        values.clear();
        values.put(DbHelper.JOBID,jobid);
        values.put(DbHelper.MYACTIVITY,service_title);
        values.put(DbHelper.PREVENTIVE_CHECKLIST,data);
        values.put(DbHelper.STATUS,s);
        return db.insert(DbHelper.CHECK_TABLE,null,values);
    }

    public Cursor getCheckList(String jobid, String service_title, String s) {
        return db.query(DbHelper.CHECK_TABLE,CHECK_FIELD,DbHelper.JOBID + "= '" + jobid + "'"
                        + "AND " + DbHelper.MYACTIVITY + "= '" + service_title + "'"
                        + "AND " + DbHelper.STATUS + "= '" + s + "'"
                ,null,null,null,null);
    }

    public boolean hasPrevetiveChecked(String jobid, String service_title, String data, String s) {

        String abc = DbHelper.JOBID + "= '" + jobid + "'" + "AND " + DbHelper.MYACTIVITY + "= '" + service_title + "'"
                + "AND " + DbHelper.PREVENTIVE_CHECKLIST + "= '" + data + "'"
                + "AND " + DbHelper.STATUS + "= '" + s + "'";
        Cursor cursor = db.rawQuery("SELECT * FROM " + DbHelper.CHECK_TABLE + " WHERE " + abc , null);
        if (cursor.getCount()<= 0 ){
            cursor.close();
            return false;
        }
        cursor.close();
        return  true;
    }

    public int deleteCheckList(String jobid, String service_title, String data, String s) {

        return db.delete(DbHelper.CHECK_TABLE,DbHelper.JOBID + "= '" + jobid + "'"
                + "AND " + DbHelper.MYACTIVITY + "= '" + service_title + "'"
                + "AND " + DbHelper.PREVENTIVE_CHECKLIST + "= '" + data + "'"
                + "AND " + DbHelper.STATUS + "= '" + s + "'",null) ;
    }

    public long addMonthList(String jobid, String service_title, String monthlist, String s) {

        values.clear();
        values.put(DbHelper.JOBID,jobid);
        values.put(DbHelper.MYACTIVITY,service_title);
        values.put(DbHelper.MONTH,monthlist);
        values.put(DbHelper.STATUS,s);
        return db.insert(DbHelper.CHECK_TABLE,null,values);
    }

    public Cursor getMonthlist(String jobid, String service_title, String s) {
        return db.query(DbHelper.CHECK_TABLE,CHECK_FIELD,DbHelper.JOBID + "= '" + jobid + "'" + "AND " +
                DbHelper.MYACTIVITY + "= '" + service_title + "'"
                + "AND " + DbHelper.STATUS + "= '" + s + "'",null,null,null,null);
    }

    public boolean hasMonthList(String jobid, String service_title, String data, String s) {
        String abc = DbHelper.JOBID + "= '" + jobid + "'" + "AND " + DbHelper.MYACTIVITY + "= '" + service_title + "'"
                + "AND " + DbHelper.MONTH+ "= '" + data + "'"
                + "AND " + DbHelper.STATUS + "= '" + s + "'";
        Cursor cursor = db.rawQuery("SELECT * FROM " + DbHelper.CHECK_TABLE + " WHERE " + abc , null);
        if (cursor.getCount()<= 0 ){
            cursor.close();
            return false;
        }
        cursor.close();
        return  true;
    }

    public int deleteMonthList(String jobid, String service_title, String data, String s) {

        return db.delete(DbHelper.CHECK_TABLE,DbHelper.JOBID + "= '" + jobid + "'"
                + "AND " + DbHelper.MYACTIVITY + "= '" + service_title + "'"
                + "AND " + DbHelper.MONTH + "= '" + data + "'"
                + "AND " + DbHelper.STATUS + "= '" + s + "'",null) ;
    }

    public int reportDeletePreventiveListDelete(String job_id, String service_title) {

        return db.delete(DbHelper.CHECK_TABLE,DbHelper.JOBID + "= '" + job_id + "'"
                + "AND " + DbHelper.MYACTIVITY + "= '" + service_title + "'",null);
    }

    public Cursor getPreventiveListDelete() {
        return db.query(DbHelper.CHECK_TABLE,CHECK_FIELD,null,null,null,null,null) ;
    }

    public long addFeedbackgroup(String jobid, String service_title, String data, String s) {

        values.clear();
        values.put(DbHelper.JOBID,jobid);
        values.put(DbHelper.MYACTIVITY,service_title);
        values.put(DbHelper.FEEDBACK_GROUP,data);
        values.put(DbHelper.STATUS,s);
        return db.insert(DbHelper.CHECK_TABLE,null,values);
    }

    public Cursor getFeedbackgroup(String jobid, String service_title, String s) {

        return db.query(DbHelper.CHECK_TABLE,CHECK_FIELD,DbHelper.JOBID + "= '" + jobid + "'"
                + "AND " + DbHelper.MYACTIVITY + "= '" + service_title + "'"
                + "AND " + DbHelper.STATUS + "= '" + s + "'",null,null,null,null);
    }

    public int deleteFeedbackgroup(String jobid, String service_title, String data, String s) {

        return db.delete(DbHelper.CHECK_TABLE,DbHelper.JOBID + "= '" + jobid + "'"
                + "AND " + DbHelper.MYACTIVITY + "= '" + service_title + "'"
                + "AND " + DbHelper.FEEDBACK_GROUP + "= '" + data + "'"
                + "AND " + DbHelper.STATUS + "= '" + s + "'",null) ;
    }

    public boolean hasFeedbackgroup(String jobid, String service_title, String data, String s) {
        String abc = DbHelper.JOBID + "= '" + jobid + "'" + "AND " + DbHelper.MYACTIVITY + "= '" + service_title + "'"
                + "AND " + DbHelper.FEEDBACK_GROUP+ "= '" + data + "'"
                +  "AND " + DbHelper.STATUS+ "= '" + s + "'";
        Cursor cursor = db.rawQuery("SELECT * FROM " + DbHelper.CHECK_TABLE + " WHERE " + abc , null);
        if (cursor.getCount()<= 0 ){
            cursor.close();
            return false;
        }
        cursor.close();
        return  true;
    }

    public long addFeedbackDesc(String jobid, String service_title, String data, String s) {
        values.clear();
        values.put(DbHelper.JOBID,jobid);
        values.put(DbHelper.MYACTIVITY,service_title);
        values.put(DbHelper.FEEDBACK_DESCRIPTION,data);
        values.put(DbHelper.STATUS,s);
        return db.insert(DbHelper.CHECK_TABLE,null,values);
    }

    public Cursor getFeedbackDesc(String jobid, String service_title, String s) {
        return db.query(DbHelper.CHECK_TABLE,CHECK_FIELD,DbHelper.JOBID + "= '" + jobid + "'"
                + "AND " + DbHelper.MYACTIVITY + "= '" + service_title + "'"
                + "AND " + DbHelper.STATUS + "= '" + s + "'"
                ,null,null,null,null);
    }

    public boolean hasFeedbackDesc(String jobid, String service_title, String data, String s) {
        String abc = DbHelper.JOBID + "= '" + jobid + "'" + "AND " + DbHelper.MYACTIVITY + "= '" + service_title + "'"
                + "AND " + DbHelper.FEEDBACK_DESCRIPTION+ "= '" + data + "'"
                + "AND " + DbHelper.STATUS+ "= '" + s + "'";
        Cursor cursor = db.rawQuery("SELECT * FROM " + DbHelper.CHECK_TABLE + " WHERE " + abc , null);
        if (cursor.getCount()<= 0 ){
            cursor.close();
            return false;
        }
        cursor.close();
        return  true;
    }

    public int deleteFeedbackDesc(String jobid, String service_title, String data, String s) {

        return db.delete(DbHelper.CHECK_TABLE,DbHelper.JOBID + "= '" + jobid + "'"
                + "AND " + DbHelper.MYACTIVITY + "= '" + service_title + "'"
                + "AND " + DbHelper.FEEDBACK_DESCRIPTION + "= '" + data + "'"
                + "AND " + DbHelper.STATUS + "= '" + s + "'",null) ;
    }


    public int deleteFeedbackDescTable(String jobid, String service_title, String s) {

        return db.delete(DbHelper.CHECK_TABLE,DbHelper.JOBID + "= '" + jobid + "'"
                + "AND " + DbHelper.MYACTIVITY + "= '" + service_title + "'"
                + "AND " + DbHelper.STATUS + "= '" + s + "'",null) ;
    }

    public long addBDDetails(String str_job_id, String service_title, String data, String s) {

        values.clear();
        values.put(DbHelper.JOBID,str_job_id);
        values.put(DbHelper.MYACTIVITY,service_title);
        values.put(DbHelper.BD_DETAILS,data);
        values.put(DbHelper.STATUS,s);
        return db.insert(DbHelper.CHECK_TABLE,null,values);
    }

    public int deleteBDdetails(String str_job_id, String service_title, String s) {

        return db.delete(DbHelper.CHECK_TABLE,DbHelper.JOBID + "= '" + str_job_id + "'"
                + "AND " + DbHelper.MYACTIVITY + "= '" + service_title + "'"
                + "AND " + DbHelper.STATUS + "= '" + s + "'",null) ;
    }

    public Cursor getBDdetails(String str_job_id, String service_title, String s) {

        return db.query(DbHelper.CHECK_TABLE,CHECK_FIELD,DbHelper.JOBID + "= '" + str_job_id + "'"
                        + "AND " + DbHelper.MYACTIVITY + "= '" + service_title + "'"
                        + "AND " + DbHelper.STATUS + "= '" + s + "'"
                ,null,null,null,null);
    }

    public long addSpinnerChecklist(String job_id, String service_title, String result, String s) {

        values.clear();
        values.put(DbHelper.JOBID,job_id);
        values.put(DbHelper.MYACTIVITY,service_title);
        values.put(DbHelper.CHECKLIST,result);
        values.put(DbHelper.STATUS,s);
        return db.insert(DbHelper.CHECK_TABLE,null,values);
    }

    public Cursor getSpinnerChecklist(String job_id, String service_title, String s) {
        return db.query(DbHelper.CHECK_TABLE,CHECK_FIELD,DbHelper.JOBID + "= '" + job_id + "'"
                        + "AND " + DbHelper.MYACTIVITY + "= '" + service_title + "'"
                        + "AND " + DbHelper.STATUS + "= '" + s + "'"
                ,null,null,null,null);
    }

    public long addFeedback(String job_id, String service_title, String s_feedback_remark, String s) {

        values.clear();
        values.put(DbHelper.JOBID,job_id);
        values.put(DbHelper.MYACTIVITY,service_title);
        values.put(DbHelper.FEEDBACK_REMARKS,s_feedback_remark);
        values.put(DbHelper.STATUS,s);
        return db.insert(DbHelper.CHECK_TABLE,null,values);
    }

    public Cursor getFeedback(String job_id, String service_title, String s) {

        return db.query(DbHelper.CHECK_TABLE,CHECK_FIELD,DbHelper.JOBID + "= '" + job_id + "'"
                        + "AND " + DbHelper.MYACTIVITY + "= '" + service_title + "'"
                        + "AND " + DbHelper.STATUS + "= '" + s + "'"
                ,null,null,null,null);
    }
 /////// For MR LISTS PART NAME and PART NO

    public long addMRList(String partno, String partName, String s, String job_id, String service_title, String quantity) {
        values.clear();
        values.put(DbHelper.PART_NO,partno);
        values.put(DbHelper.PART_NAME,partName);
        values.put(DbHelper.JOBID,job_id);
        values.put(DbHelper.STATUS,s);
        values.put(DbHelper.MYACTIVITY,service_title);
        values.put(DbHelper.QUANTITY,quantity);
        return db.insert(DbHelper.MRLIST_TABLE,null,values);

    }

    public Cursor getMRList(String job_id, String s, String service_title) {
        return db.query(DbHelper.MRLIST_TABLE,MRLISTS_FIELD,DbHelper.JOBID + "= '" + job_id + "'"
                        + "AND " + DbHelper.STATUS + "= '" + s + "'"
                        + "AND " + DbHelper.MYACTIVITY + "= '" + service_title + "'"
                ,null,null,null,null);
    }

    public int deleteMRList(String partno, String partName, String s, String job_id, String service_title) {

        return db.delete(DbHelper.MRLIST_TABLE,DbHelper.PART_NO + "= '" + partno + "'"
                + "AND " + DbHelper.PART_NAME + "= '" + partName + "'"
                        + "AND " + DbHelper.STATUS + "= '" + s + "'"
                        + "AND " + DbHelper.JOBID + "= '" + job_id + "'"
                + "AND " + DbHelper.MYACTIVITY + "= '" + service_title + "'"
               ,null) ;
    }

    public int deleteMRPartno(String toString) {
        return db.delete(DbHelper.MRLIST_TABLE,DbHelper.ID + "= '" + toString + "'",null);
    }

    public boolean hasMRList(String partno, String partName, String s, String job_id, String service_title) {

        String abc = DbHelper.PART_NO + "= '" + partno + "'" + "AND " + DbHelper.PART_NAME + "= '" + partName + "'"
                + "AND " + DbHelper.JOBID+ "= '" + job_id + "'"
                + "AND " + DbHelper.MYACTIVITY + "= '" + service_title + "'"
                + "AND " + DbHelper.STATUS+ "= '" + s + "'";
        Cursor cursor = db.rawQuery("SELECT * FROM " + DbHelper.MRLIST_TABLE + " WHERE " + abc , null);
        if (cursor.getCount()<= 0 ){
            cursor.close();
            return false;
        }
        cursor.close();
        return  true;
    }

    public int UpdateMRList(String partno, String partname, String status, String qty, String id, String service_title, String job_id) {
        values.clear();
        values.put(DbHelper.PART_NO,partno);
        values.put(DbHelper.PART_NAME,partname);
        values.put(DbHelper.STATUS, status);
        values.put(DbHelper.QUANTITY,qty);
        values.put(DbHelper.ID,id);
        values.put(DbHelper.MYACTIVITY,service_title);
        values.put(DbHelper.JOBID,job_id);
        return db.update(DbHelper.MRLIST_TABLE,values,
                DbHelper.ID + " = ' " + id + " ' ",null);
    }

    public int deleteMRTable(String job_id, String s, String service_title) {

        return db.delete(DbHelper.MRLIST_TABLE,  DbHelper.STATUS + "= '" + s + "'"
                        + "AND " + DbHelper.JOBID + "= '" + job_id + "'"
                        + "AND " + DbHelper.MYACTIVITY + "= '" + service_title + "'"
                ,null) ;
    }


}
