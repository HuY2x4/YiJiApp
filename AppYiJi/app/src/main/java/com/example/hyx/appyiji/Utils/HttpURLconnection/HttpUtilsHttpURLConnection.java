package com.example.hyx.appyiji.Utils.HttpURLconnection;

import android.util.Log;

import com.example.hyx.appyiji.Model.Record;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

public class HttpUtilsHttpURLConnection {

    public static String BASE_URL= "http://120.79.32.211:8080/AppYiJiServer";
    /*
     * urlStr:网址
     * parms：提交数据
     * return:网页源码
     * */
    public static  String getContextByHttp(String urlStr,List<Record> inMap,List<Record> outMap){
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
            Log.d("aaaa", "StringBuilder:9");
            OutputStream outputStream = connection.getOutputStream();
            Log.d("aaaa", "StringBuilder:10");
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
            Log.d("aaaa", "StringBuilder:11");
            String json = getStringFromOutput(inMap,outMap);
            Log.d("aaaa", "getContextByHttp: json:"+json);
            writer.write(json);

            writer.flush();
            writer.close();
            outputStream.close();

            if(connection.getResponseCode() == HttpURLConnection.HTTP_OK){
                Log.d("aaaa", "getResponseCode:"+"HTTP_OK");
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String temp;
                while((temp = reader.readLine()) != null){
                    sb.append(temp);
                }
                Log.d("aaaa", "temp:"+temp);
                reader.close();
            }else{
                Log.d("aaaa", "connection error:"+connection.getResponseCode());
                return "connection error:" + connection.getResponseCode();
            }

            connection.disconnect();
        }catch (Exception e){
            return e.toString();
        }
        return sb.toString();
    }

    /**
     * 将map转换成key1=value1&key2=value2的形式
     * @param
     * @return
     * @throws UnsupportedEncodingException
     */
    private static String getStringFromOutput(List<Record> inMap,List<Record> outMap) throws UnsupportedEncodingException, JSONException {
        /*StringBuilder sb = new StringBuilder();
        boolean isFirst = true;

        for(Map.Entry<String,List<Record>> entry:map.entrySet()){
            if(isFirst)
                isFirst = false;
            else
                sb.append("&");

            sb.append(URLEncoder.encode(entry.getKey(),"UTF-8"));
            sb.append("=");
            sb.append(URLEncoder.encode(entry.getValue(),"UTF-8"));
        }*/
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("inList", inMap);
        jsonObject.put("outList", outMap);
        return jsonObject.toString();
    }
}
