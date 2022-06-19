package com.example.mykost;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/*Pembuatan Database dengan Kosan*/
public class DataHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "Kosan.db";
    private static int DATABASE_VERSION = 1;
    public DataHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {

        /*Query Create Table data*/
        String sql = "create table data(no integer primary key, nama text null, fasilitas text null, alamat text null, kontak text null, harga text null);";
        Log.d("Data", "onCreate: " + sql);
        db.execSQL(sql);

    }
    @Override
    public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
    }
}