package com.example.shopshrey;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;



public class serverconnection {

    public void doGet(String Url, final serverResponse context){

        final HttpURLConnection urlConnection;
        try {
            URL url = new URL(Url);
            urlConnection = (HttpURLConnection) url.openConnection();

            urlConnection.setRequestMethod("GET");
            urlConnection.addRequestProperty("Accept", "application/json");
            urlConnection.addRequestProperty("Content-type", "application/json");
            urlConnection.setDoOutput(true);

            Runnable myrunnable = new Runnable() {
                @Override
                public void run() {

                    int res = 0;
                    try {
                        res = urlConnection.getResponseCode();
                    } catch (java.net.ConnectException e){
                        Log.d("TAG", "internet not available");
                       context.serverResponseTimedout();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    String response = "";
                    if (res == HttpURLConnection.HTTP_OK) {
                        BufferedReader br = null;
                        try {
                            br = new BufferedReader(new InputStreamReader((urlConnection.getInputStream())));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        StringBuilder sb = new StringBuilder();
                        String output;
                        try {
                            while ((output = br.readLine()) != null) {
                                sb.append(output);
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        response = sb.toString();
                        context.dataReceived(response);
                    }
                }
            };

            Thread t = new Thread(myrunnable);
            t.start();

        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

