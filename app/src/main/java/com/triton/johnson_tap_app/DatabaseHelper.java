package com.triton.johnson_tap_app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "StoreSpinnerData.db";
    public static final String TABLE_NAME = "spinner_table";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "storeroomname";
    public static final String COL_3 = "storename";
    public static final String COL_4 = "sname";
    public static final String COL_5 = "roomname";
    public static final String COL_6 = "strname";
    public static final String COL_7 = "liftpit";
    public static final String COL_8 = "liftshaft";
    public static final String COL_9 = "lftshaft";
    public static final String COL_10 = "liftsht";
    public static final String COL_11 = "lftsht";
    public static final String COL_12 = "ls";
    public static final String COL_13 = "lfts";
    public static final String COL_14 = "lft";
    public static final String COL_15 = "shaft";
    public static final String COL_16 = "sft";
    public static final String COL_17 = "lft";
    public static final String COL_18 = "lst";
    public static final String COL_19 = "machineroom";
    public static final String COL_20 = "mroom";
    public static final String COL_21 = "machiner";
    public static final String COL_22 = "machinrm";
    public static final String COL_23 = "mchneroom";
    public static final String COL_24 = "mhinerm";
    public static final String COL_25 = "machinr";
    public static final String COL_26 = "mrm";
    public static final String COL_27 = "minroom";
    public static final String COL_28 = "minrm";
    public static final String COL_29 = "mainrm";
    public static final String COL_30 = "loadhook";
    public static final String COL_31 = "otherrequirements";
    public static final String COL_32 = "orequirements";
    public static final String COL_33 = "otherrmts";
    public static final String COL_34 = "ormts";
    public static final String COL_35 = "orements";
    public static final String COL_36 = "otherr";
    public static final String COL_37 = "otherequirmts";
    public static final String COL_38 = "othrrmts";
    public static final String COL_39 = "otrrmts";
    public static final String COL_40 = "orrmts";
    public static final String COL_41 = "othrrqmts";
    public static final String COL_42 = "othrrmtqs";
    public static final String COL_43 = "observation";
    public static final String COL_44 = "totalscore";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "create table " + DATABASE_NAME + "(ID integer primary key, storeroomname text,storename text" +
                        ",sname text,roomname text,strname text,liftpit text,liftshaft text,lftshaft text," +
                        "liftsht text,lftsht text,ls text,lfts text,lft text,shaft text,sft text,lft text," +
                        "lst text,machineroom text,mroom text,machiner text,machinrm text,mchneroom text," +
                        "mhinerm text,machinr text,mrm text,minroom text,minrm text,mainrm text," +
                        "loadhook text,otherrequirements text," +
                        "orequirements text,otherrmts text,ormts text,orements text," +
                        "otherr text,otherequirmts text,othrrmts text,otrrmts text," +
                        "orrmts text,othrrqmts text,othrrmtqs text,observation text,totalscore number)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DATABASE_NAME);
        onCreate(db);
    }
    public boolean insertData(String storeroomname ) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, storeroomname);

        long result = db.insert(TABLE_NAME,null ,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }
    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME,null);
        return res;
    }
    public boolean updateData(String id,String storeroomname, String storename,String sname,String roomname,
                              String strname,String liftpit,String liftshaft,String lftshaft,String liftsht,
                              String lftsht ,String ls,String lfts ,String shaft ,String sft  ,String lft
            ,String lst,String machineroom,String mroom,String machiner,String machinrm,String mchneroom ,
                              String mainrm,String mhinerm,String machinr,String mrm,String minroom,String minrm
            ,String loadhook ,String otherrequirements,String orequirements ,String otherrmts,String ormts
            ,String orements,String otherr,String otherequirmts,String othrrmts,String otrrmts,String orrmts,
                              String othrrqmts,String othrrmtqs,String observation,int totalscore) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,id);
        contentValues.put(COL_2, storeroomname);
        contentValues.put(COL_3, storename);
        contentValues.put(COL_4, sname);
        contentValues.put(COL_5, roomname);
        contentValues.put(COL_6, strname);
        contentValues.put(COL_7, liftpit);
        contentValues.put(COL_8, liftshaft);
        contentValues.put(COL_9, lftshaft);
        contentValues.put(COL_10, liftsht);
        contentValues.put(COL_11, lftsht);
        contentValues.put(COL_12, ls);
        contentValues.put(COL_13, lfts);
        contentValues.put(COL_14, lft);
        contentValues.put(COL_15, shaft);
        contentValues.put(COL_16, sft);
        contentValues.put(COL_17, lft);
        contentValues.put(COL_18, lst);
        contentValues.put(COL_19, machineroom);
        contentValues.put(COL_20, mroom);
        contentValues.put(COL_21, machiner);
        contentValues.put(COL_22, machinrm);
        contentValues.put(COL_23, mchneroom);
        contentValues.put(COL_24, mhinerm);
        contentValues.put(COL_25, machinr);
        contentValues.put(COL_26, mrm);
        contentValues.put(COL_27, minroom);
        contentValues.put(COL_28, minrm);
        contentValues.put(COL_29, mainrm);
        contentValues.put(COL_30, loadhook);
        contentValues.put(COL_31, otherrequirements);
        contentValues.put(COL_32, orequirements);
        contentValues.put(COL_33, otherrmts);
        contentValues.put(COL_34, ormts);
        contentValues.put(COL_35, orements);
        contentValues.put(COL_36, otherr);
        contentValues.put(COL_37, otherequirmts);
        contentValues.put(COL_38, othrrmts);
        contentValues.put(COL_39, otrrmts);
        contentValues.put(COL_40, orrmts);
        contentValues.put(COL_41, othrrqmts);
        contentValues.put(COL_42, othrrmtqs);
        contentValues.put(COL_43, observation);
        contentValues.put(COL_44, totalscore);
        db.update(TABLE_NAME, contentValues, "ID = ?",new String[] { id });
        return true;
    }

    public Integer deleteData (String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "ID = ?",new String[] {id});
    }

}