package com.nictbills.app.activities.fastag.utils;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class MyDatabase extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "mydatabase.db";
    public static final String TABLE_NAME = "items";
    public static final String ICOL_1 = "ID";
    public static final String ICOL_2 = "SID";
    public static final String ICOL_3 = "service_title";
    public static final String ICOL_4 = "service_price";
    public static final String ICOL_5 = "service_subcat_id";
    public static final String ICOL_6 = "service_sdesc";
    public static final String ICOL_7 = "service_childcat_id";
    public static final String ICOL_8 = "service_cat_id";
    public static final String ICOL_9 = "service_discount";
    public static final String ICOL_10 = "service_ttken";
    public static final String ICOL_11 = "service_mqty";
    public static final String ICOL_12 = "service_qty";
    SessionManager sessionManager;

    public MyDatabase(Context context) {
        super(context, DATABASE_NAME, null, 1);
        sessionManager = new SessionManager(context);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, SID TEXT , service_title TEXT ,service_price Double , service_subcat_id TEXT , service_sdesc TEXT, service_childcat_id TEXT , service_cat_id TEXT, service_discount Double , service_ttken TEXT , service_mqty TEXT , service_qty int )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }


    private int getID(String sid) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.query(TABLE_NAME, new String[]{"SID"}, "SID =? ", new String[]{sid}, null, null, null, null);
        if (c.moveToFirst()) //if the row exist then return the id
            return c.getInt(c.getColumnIndex("SID"));
        return -1;
    }

    public int getCard(String aid) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.query(TABLE_NAME, new String[]{"service_qty"}, "SID =? ", new String[]{aid}, null, null, null, null);
        if (c.moveToFirst()) { //if the row exist then return the id
            return c.getInt(c.getColumnIndex("service_qty"));
        } else {
            return -1;
        }
    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME, null);
        return res;
    }



    public void deleteCard(String cid) {
        SQLiteDatabase db = this.getWritableDatabase();
        Integer a = db.delete(TABLE_NAME, "service_cat_id = ? ", new String[]{cid});
    }


}