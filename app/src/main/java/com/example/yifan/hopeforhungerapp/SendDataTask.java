package com.example.yifan.hopeforhungerapp;

import android.os.AsyncTask;
import android.util.Log;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Yifan on 10/2/2016.
 */

public class SendDataTask extends AsyncTask<String, Void, Void> {
    @Override
    protected Void doInBackground(String... params) {
        HashMap<String, String> map = new HashMap<>();
        map.put("key", params[0]);
        sendData(map);

        return null;
    }

    public void sendData(Map<String, String> paramsMap) {
        String result = "";
        final String urlStr = "http://localhost:8080/";
        URL serverUrl = null;
        try {
            serverUrl = new URL(urlStr);
            Log.d("URL PROBLEM", serverUrl.toString());
        } catch (MalformedURLException e) {
            Log.e("AppUtil", "URL Connection Error: "
                    + urlStr);
            result = urlStr;
        }
        StringBuilder postBody = new StringBuilder();
        Iterator<Map.Entry<String, String>> iterator = paramsMap.entrySet()
                .iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, String> param = iterator.next();
            postBody.append(param.getKey()).append('=')
                    .append(param.getValue());
            if (iterator.hasNext()) {
                postBody.append('&');
            }
        }
        String body = postBody.toString();
        byte[] bytes = body.getBytes();
        HttpURLConnection httpCon = null;
        try {
            httpCon = (HttpURLConnection) serverUrl.openConnection();
            httpCon.setDoOutput(true);
            httpCon.setUseCaches(false);
            httpCon.setFixedLengthStreamingMode(bytes.length);
            httpCon.setRequestMethod("GET");
            httpCon.setRequestProperty("Content-Type",
                    "application/x-www-form-urlencoded;charset=UTF-8");
            OutputStream out = httpCon.getOutputStream();
            out.write(bytes);
            out.close();

            int status = httpCon.getResponseCode();
            if (status == 200) {
                result = "Success";
            } else {
                result = "Post Failure." + " Status: " + status;
            }
        } catch (Exception e) {
            Log.d("SOME ERROR", e.toString());
            httpCon.disconnect();
        }
        // return null;
    }
}


