package com.example.hyx.appyiji.ReClass;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by hyx on 2018/11/21.
 */

public class MyDatebaseHelper extends SQLiteOpenHelper {
    private  static  final String CREATE_INRECORD="create table inRecord("
            +"id integer primary key autoincrement,"
            +"image integer,"
            +"content text,"
            +"data text,"
            +"cost text)";
    private  static  final String CREATE_OUTRECORD="create table outRecord("
            +"id integer primary key autoincrement,"
            +"image integer,"
            +"content text,"
            +"data text,"
            +"cost text)";
    private Context mContext;

    public MyDatebaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext=context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_INRECORD);
        sqLiteDatabase.execSQL(CREATE_OUTRECORD);
        Toast.makeText(mContext,"create record ok",Toast.LENGTH_LONG);
        Log.e("aaaa","创建表");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
