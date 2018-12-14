package com.example.hyx.appyiji.Utils.SQLiteDatabase.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.hyx.appyiji.Model.Record;
import com.example.hyx.appyiji.Utils.SQLiteDatabase.DBManager;
import com.example.hyx.appyiji.Utils.SQLiteDatabase.MyDatebaseHelper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by hyx on 2018/12/2.
 */

public class RecordDaoImpl implements RecordDao{
    private Context context;

    @Override
    public boolean addRecord(Context context,int image, String content, String data, String cost, int flag) {
        MyDatebaseHelper myDatebaseHelper= DBManager.getDBHelp(context);
        SQLiteDatabase db =myDatebaseHelper.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("image",image);
        values.put("content",content);
        values.put("data",data);
        if(cost.equals("")){
            cost="0";
        }

        if(flag==DBManager.INRECORD){
            values.put("cost",cost);
            db.insert("inRecord",null,values);
        }
        else if(flag==DBManager.OUTRECORD){
            if(!cost.equals("0")){
                cost="-"+cost;
            }
            values.put("cost",cost);
            db.insert("outRecord",null,values);
        }

        values.clear();
        return true;
    }

    @Override
    public boolean updateRecord(Context context,int id, int image, String content, String data, String cost) {
        return false;
    }

    @Override
    public boolean deleteRecord( String content, String data, String cost, int flag) {
        MyDatebaseHelper myDatebaseHelper= DBManager.getDBHelp(context);
        SQLiteDatabase db =myDatebaseHelper.getWritableDatabase();
        if(flag==DBManager.INRECORD){
            db.delete("inRecord","content=? and data=? and cost=?",new String[]{content,data,cost });
        }
        else{
            db.delete("outRecord","content=? and data=? and cost=?",new String[]{content,data,cost });
        }

        return true;
    }

    @Override
    public Record getRecord(Context context,int id) {
        return null;
    }

    @Override
    public List<Record> getAllRecord(Context context,int flag) {
        this.context=context;
        MyDatebaseHelper myDatebaseHelper= DBManager.getDBHelp(context);
        SQLiteDatabase db =myDatebaseHelper.getWritableDatabase();
        List<Record> list =new ArrayList<Record>();
        Cursor cusor;
        if(flag==DBManager.INRECORD){
            cusor=db.query("inRecord",null,null,null,null,null,null);
        }
        else{
            cusor=db.query("outRecord",null,null,null,null,null,null);
        }

        if(cusor.moveToFirst()){
            do {
                Record itemAdd=new Record(
                        cusor.getInt(cusor.getColumnIndex("id")),
                        cusor.getInt(cusor.getColumnIndex("image")),
                        cusor.getString(cusor.getColumnIndex("content")),
                        cusor.getString(cusor.getColumnIndex("data")),
                        cusor.getString(cusor.getColumnIndex("cost")));
                list.add(itemAdd);
            }while(cusor.moveToNext());
        }
        Collections.reverse(list);

        return list;
    }

    @Override
    public void deleteAllRecord() {
        MyDatebaseHelper myDatebaseHelper= DBManager.getDBHelp(context);
        SQLiteDatabase db =myDatebaseHelper.getWritableDatabase();
        db.delete("inRecord","",new String[]{});
        db.delete("outRecord","",new String[]{});
    }

    @Override
    public void addAllRecord(List<Record> in,List<Record> out) {
        MyDatebaseHelper myDatebaseHelper= DBManager.getDBHelp(context);
        SQLiteDatabase db =myDatebaseHelper.getWritableDatabase();
        ContentValues values=new ContentValues();
        for(Record r : in){
            values.put("image",r.getImage());
            values.put("content",r.getContent());
            values.put("data",r.getData());
            if(r.getCost().equals("")){
                r.setCost("0");
            }
            values.put("cost",r.getCost());
            db.insert("inRecord",null,values);
            values.clear();
        }

        for(Record r : out){
            values.put("image",r.getImage());
            values.put("content",r.getContent());
            values.put("data",r.getData());
            if(!r.getCost().equals("0")){
                r.setCost("-"+r.getCost());
            }
            values.put("cost",r.getCost());
            db.insert("outRecord",null,values);
            values.clear();
        }


    }
}
