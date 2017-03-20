package com.freeman.httpurlconnection.httpurlconnection;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by Freeman on 10.01.2017.
 */


// Singletone
public class HttpProvider {
    private static final String BASE_URL = "http://jsonplaceholder.typicode.com";
    public static final String BASE_URL_HOME_TASK = "https://telranstudentsproject.appspot.com/_ah/api/contactsApi/v1";
    private static HttpProvider provider;

    private HttpProvider() {
    }
     public static HttpProvider getProvider(){
         if (provider == null)
             provider = new HttpProvider();
         return provider;
     }
    public String get(String link) throws IOException {
        String result = "";
        URL url = new URL(BASE_URL + link);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setReadTimeout(15000);
        connection.setConnectTimeout(15000);
        connection.setRequestProperty("Content-Type", "application/json");
        connection.connect();

        if (connection.getResponseCode() < 400) {
            InputStreamReader isr = new InputStreamReader(connection.getInputStream());
            BufferedReader br = new BufferedReader(isr);
            String line = "";
            while ((line = br.readLine()) != null) {
                result += line;
            }
            br.close();
        }else {
            InputStreamReader eisr = new InputStreamReader(connection.getErrorStream());
            BufferedReader ebr = new BufferedReader(eisr);
            String eLine = "";
            while ((eLine = ebr.readLine()) != null){
                result += eLine;
            }
            ebr.close();
        }

        return result;
    }

    public String post(String link, String data) throws IOException {
        String result = "";
        URL url = new URL(BASE_URL_HOME_TASK + link);
        HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setReadTimeout(15000);
        connection.setConnectTimeout(15000);
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setDoInput(true);
        connection.setDoOutput(true);

        OutputStream os = connection.getOutputStream();
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
        bw.write(data);
        bw.flush();
        bw.close();
        os.close();

        //Answer
        BufferedReader br;
        String line = "";
        if (connection.getResponseCode() < 400){
            br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        }else {
            br = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
        }
        while ((line = br.readLine()) != null){
            result += line;
        }
        br.close();
        return result;
    }
}
