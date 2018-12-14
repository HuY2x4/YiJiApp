package com.example.hyx.appyiji.Utils.SQLiteDatabase;

import android.content.Context;

/**
 * Created by hyx on 2018/12/2.
 */

public class DBManager {
    public static final int OUTRECORD=0;
    public static final int INRECORD=1;
    public static MyDatebaseHelper getDBHelp(Context context){
        MyDatebaseHelper database=new MyDatebaseHelper(context,"YiJiDate.db",null,1);
        return database;
    }
}
