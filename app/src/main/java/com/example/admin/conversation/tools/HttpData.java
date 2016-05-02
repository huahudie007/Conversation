package com.example.admin.conversation.tools;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by admin on 2016/5/1.
 */
public class HttpData extends AsyncTask<String, Void, String> {
    private HttpGetDataListener listener;
    private String request;


    public HttpData(String url, HttpGetDataListener listener) {
        this.request = url;
        this.listener = listener;
    }

    @Override
    protected String doInBackground(String... params) {
        InputStream in = null;
        OutputStream out = null;
        try {
            URL url = new URL("http://www.tuling123.com/openapi/api");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            conn.setRequestMethod("POST");
            conn.setConnectTimeout(8000);
            conn.setReadTimeout(8000);
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Accept", "application/json");
            out = conn.getOutputStream();
            out.write(request.getBytes("UTF-8"));

            conn.connect();


            out.flush();
            out.close();
            if (conn.getResponseCode() == 200) {
                in = conn.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
                String line = null;
                StringBuffer buffer = new StringBuffer();
                while ((line = bufferedReader.readLine()) != null) {
                    buffer.append(line);
                }
                return buffer.toString();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        listener.getDataUrl(s);
        super.onPostExecute(s);
    }
}
