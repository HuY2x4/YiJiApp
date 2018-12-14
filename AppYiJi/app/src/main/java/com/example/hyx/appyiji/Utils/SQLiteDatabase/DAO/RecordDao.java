package com.example.hyx.appyiji.Utils.SQLiteDatabase.DAO;

import android.content.Context;

import com.example.hyx.appyiji.Model.Record;

import java.util.List;

/**
 * Created by hyx on 2018/12/2.
 */

public interface RecordDao {
    public boolean addRecord(Context context, int image, String content, String data, String cost, int flag);

    public boolean updateRecord(Context context,int id,int image,String content,String data,String cost);

    public boolean deleteRecord(String content,String data,String cost,int flag);

    public Record getRecord(Context context,int id);

    public List<Record> getAllRecord(Context context,int flag);

    public void deleteAllRecord();

    public void addAllRecord(List<Record> in,List<Record> out);


}
