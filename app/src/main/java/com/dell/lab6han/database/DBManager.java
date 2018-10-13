package com.dell.lab6han.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBManager extends SQLiteOpenHelper {

    public static final String DBNHANVIEN="bangnhanvien";
    public static final String TB_NhanVIEN="nhanvien";
    public static final String MA="manv";
    public static final String TENNV="tennhanvien";
    public static final String LUONGNV="luongnv";

    private String SQLQuery = "CREATE TABLE " + TB_NhanVIEN + " ( " +
            MA + " TEXT primary key, " +
            TENNV + " TEXT, " +
            LUONGNV + " TEXT)";



    public DBManager(Context context) {
        super(context, DBNHANVIEN, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQLQuery);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
