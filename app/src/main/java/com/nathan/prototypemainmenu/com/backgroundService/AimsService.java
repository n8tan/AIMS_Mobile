package com.nathan.prototypemainmenu.com.backgroundService;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by Nathan on 28/05/2017.
 */

public class AimsService extends IntentService {

    public String accounts;
    public String items;
    public String reports;
    public String key;

    public AimsService() {
        this(AimsService.class.getName());
    }

    public AimsService(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        //Checks incoming intent...
        //Determine what should we do.
        //Do necessary processing
        String action = intent.getStringExtra("action");

        switch (action) {

            case "rf":
                reports = makeServiceCall("http://10.0.17.127:8080/aims/mobile/reports.html","getReports");
                break;

            case "accounts":
                accounts = makeServiceCall("http://10.0.17.127:8080/aims/mobile/accounts.html","getAccounts");
                break;

            case "password":

                break;

            case "items":
                items = makeServiceCall("http://10.0.17.127:8080/aims/mobile/items.html","getItems");
                break;

            case "all":
                reports = makeServiceCall("http://10.0.17.127:8080/aims/mobile/reports.html","getReports");
                accounts = makeServiceCall("http://10.0.17.127:8080/aims/mobile/accounts.html","getAccounts");
                items = makeServiceCall("http://10.0.17.127:8080/aims/mobile/items.html","getItems");
        }

    }

    public String makeServiceCall(String reqUrl,String action) {
        String response = null;
        try {
            URL url = new URL(reqUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            if(action.equals("login")){
                String charset = "UTF-8";
                String s = "user=" + URLEncoder.encode("bryce27923@gmail.com", charset);
                s += "&password=" + URLEncoder.encode("2792327923", charset);
                conn.setFixedLengthStreamingMode(s.getBytes().length);
                PrintWriter out = new PrintWriter(conn.getOutputStream());
                out.print(s);
                out.close();
            }
            if(action.equals("getItems") || action.equals("getAccounts") ||action.equals("getReports")){
                String s = "key=" + key;
                conn.setFixedLengthStreamingMode(s.getBytes().length);
                PrintWriter out = new PrintWriter(conn.getOutputStream());
                out.print(s);
                out.close();
            }
            InputStream in = new BufferedInputStream(conn.getInputStream());
            response = convertStreamToString(in);
        } catch (MalformedURLException e) {
            System.err.println(e.getMessage());
        } catch (ProtocolException e) {
            System.err.println(e.getMessage());
        } catch (IOException e) {
            System.err.println(e.getMessage());
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return response;
    }

    private String convertStreamToString(InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String line;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line).append('\n');
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }
}
