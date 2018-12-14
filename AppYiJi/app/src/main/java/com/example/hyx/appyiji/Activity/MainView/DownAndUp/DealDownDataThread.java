package com.example.hyx.appyiji.Activity.MainView.DownAndUp;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import com.example.hyx.appyiji.Activity.MainView.DownAndUp.Thread.DownDataThread;
import com.example.hyx.appyiji.Activity.MainView.DownAndUp.Thread.UpDataThread;
import com.example.hyx.appyiji.Model.Record;
import com.example.hyx.appyiji.Utils.HttpURLconnection.HttpUtilsHttpURLConnection;
import com.example.hyx.appyiji.Utils.SQLiteDatabase.DAO.RecordDao;
import com.example.hyx.appyiji.Utils.SQLiteDatabase.DAO.RecordDaoImpl;
import com.example.hyx.appyiji.Utils.SQLiteDatabase.DBManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hyx on 2018/12/8.
 */

public class DealDownDataThread extends Thread{
    private static Context context;
    private RecordDao recordDao;

    public DealDownDataThread(Context context){
        this.context=context;
    }

    public void run() {
        String url= HttpUtilsHttpURLConnection.BASE_URL+"/downloadRecordByUser";
        String result="{\"success\":\"false\"}";

        recordDao = new RecordDaoImpl();

        List<Record> inList = recordDao.getAllRecord(context, DBManager.INRECORD);
        List<Record> outList = recordDao.getAllRecord(context, DBManager.OUTRECORD);
        Log.d("aaaa", "run: "+inList+outList);
        DownDataThread downDataThread = new DownDataThread(url);
        downDataThread.run();
        for (int i = 0;i < 8;i++){
            try {
                Thread.sleep(500);
                if(downDataThread.getFlag()){
                    result = downDataThread.getResult();
                    Log.d("aaaa", "run: getResult:"+result);
                    break;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Log.d("aaaa", "run:result: "+result);
        Message msg = new Message();
        msg.what=0x12;
        Bundle data=new Bundle();
        data.putString("Bigresult",result);
        msg.setData(data);
        //现在的问题就是将HttpUtilsHttpURLConnection设为线程，因为连接网络不能再主线程执行

        hander.sendMessage(msg);
    }

    Handler hander = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if (msg.what==0x12){
                Bundle data = msg.getData();
                String key = data.getString("Bigresult");
                //得到json返回的json
                //   Toast.makeText(Denglu.this,key,Toast.LENGTH_LONG).show();
                try {
                    JSONObject json= new JSONObject(key);
                    String result = (String) json.get("success");
                    /*解析json数据String类型*/
                    List<Record> recIn = new ArrayList<Record>();
                    List<Record> recOut = new ArrayList<Record>();
                    JSONObject  dataJson = json.getJSONObject("data");
                    JSONArray inList = dataJson.getJSONArray("inList");
                    JSONArray outList = dataJson.getJSONArray("outList");
                    for (int i = 0; i < inList.length(); i++) {
                        Record rec = new Record();
                        JSONObject value = inList.getJSONObject(i);
                        rec.setId(Integer.parseInt(value.getString("id")));
                        rec.setImage(Integer.parseInt(value.getString("image")));
                        rec.setContent(value.getString("content"));
                        rec.setCost(value.getString("cost"));
                        rec.setData(value.getString("date"));
                        recIn.add(rec);
                    }
                    for (int i = 0; i < outList.length(); i++) {
                        Record rec = new Record();
                        JSONObject value = inList.getJSONObject(i);
                        rec.setId(Integer.parseInt(value.getString("id")));
                        rec.setImage(Integer.parseInt(value.getString("image")));
                        rec.setContent(value.getString("content"));
                        rec.setCost(value.getString("cost"));
                        rec.setData(value.getString("date"));
                        recOut.add(rec);
                    }
                    recordDao.deleteAllRecord();
                    recordDao.addAllRecord(recIn,recOut);
                    Log.d("aaaa", "hander: "+result);
                    if(result.equals("true")){
                        Toast.makeText(context,"恢复成功",Toast.LENGTH_LONG).show();
                    }
                    else{
                        Toast.makeText(context,"辣鸡服务器出错了",Toast.LENGTH_LONG).show();
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    };
}
