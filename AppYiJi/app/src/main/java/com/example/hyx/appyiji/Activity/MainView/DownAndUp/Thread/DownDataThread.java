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
import java.util.List;

/**
 * Created by hyx on 2018/12/10.
 */

public class DownDataThread extends Thread{
    private  String  urlStr;

    private boolean flag = false;
    private String result;
    public DownDataThread(String urlStr) {
        this.urlStr=urlStr;

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
            connection.setInstanceFollowRedirects(true);
            OutputStream outputStream = connection.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
            JSONObject jsonObject = new JSONObject();

            String json = jsonObject.toString();
            writer.write(json);
            writer.flush();
            writer.close();
            outputStream.close();

            if(connection.getResponseCode() == HttpURLConnection.HTTP_OK){
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String temp;
                while((temp = reader.readLine()) != null){
                    sb.append(temp);
                }
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
