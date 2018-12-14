package com.example.hyx.appyiji.Activity.MainView.DownAndUp.Thread;

import android.util.Log;

import com.example.hyx.appyiji.Model.Record;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by hyx on 2018/12/10.
 */

public class UpDataThread extends Thread{
    private  String  urlStr;
    private  List<Record> inMap;
    private  List<Record> outMap;
    private  List<Map<String,String>> inMap2;
    private  List<Map<String,String>> outMap2;
    private boolean flag = false;
    private String result;
    public UpDataThread(String urlStr, List<Record> inMap, List<Record> outMap) {
        this.urlStr=urlStr;
        this.inMap=inMap;
        this.outMap=outMap;
    }

    @Override
    public void run() {
        super.run();
        StringBuilder sb = new StringBuilder();
        try{
            URL url = new URL(urlStr);
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod("POST");
            connection.setReadTimeout(5000);
            connection.setConnectTimeout(5000);
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "application/json");//这两句不加后端数据收到每个数据两边为多出%xx
            connection.setRequestProperty("Charset", "UTF-8");
            connection.setInstanceFollowRedirects(true);
            OutputStream outputStream = connection.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
            JSONObject jsonObject = new JSONObject();
            inMap2 = new ArrayList<Map<String,String>>();
            outMap2 = new ArrayList<Map<String,String>>();
            for(Record rec : inMap){
                Map<String,String> map = new HashMap<String,String>();
                map.put("id",Integer.toString(rec.getId()));
                map.put("image",Integer.toString(rec.getImage()));
                map.put("content",rec.getContent());
                map.put("date",rec.getData());
                map.put("cost",rec.getCost());
                Log.d("aaaa", "run: inmap:"+map.toString());
                inMap2.add(map);
            }
            for(Record rec : outMap){
                Map<String,String> map = new HashMap<>();
                map.put("id",Integer.toString(rec.getId()));
                map.put("image",Integer.toString(rec.getImage()));
                map.put("content",rec.getContent());
                map.put("date",rec.getData());
                map.put("cost",rec.getCost());
                Log.d("aaaa", "run: outmap:"+map.toString());
                outMap2.add(map);
            }
            jsonObject.put("inList", inMap2);
            jsonObject.put("outList", outMap2);
            String json = jsonObject.toString();
            Log.d("aaaa", json);
            writer.write(json);
            writer.flush();
            writer.close();
            outputStream.close();

            if(connection.getResponseCode() == HttpURLConnection.HTTP_OK){
                Log.d("aaaa", "run: 4");
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String temp;
                while((temp = reader.readLine()) != null){
                    sb.append(temp);
                }
                //这个等待的过程可以加个等待的圈圈ui
                reader.close();
                result = sb.toString();
                flag=true;

            }else{
                Log.d("aaaa", "connection error:"+connection.getResponseCode());
            }

            connection.disconnect();
        }catch (Exception e){
            Log.e("aaaa", "Exception Exception:"+e.toString());
        }

    }

    public boolean getFlag(){
        return flag;
    }

    public String getResult(){
        return result;
    }

}
