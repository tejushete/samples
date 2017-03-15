package com.example.googlemap;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

/**
 * Created by Tejashree on 02-Mar-17.
 */

public class DetailsActivity extends AppCompatActivity {
    HttpURLConnection urlConnection;
    String address, name, rating, placeid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details_layout);

        Intent intent = getIntent();
        double lati, longi;
        lati = intent.getDoubleExtra("lati", 10.78);
        longi = intent.getDoubleExtra("longi", 23.90);

        TextView tvLatiLongi = (TextView) findViewById(R.id.tvLatLong);
        tvLatiLongi.setText("" + lati + " ," + longi);
        String placeURL = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=" + lati + "," + longi + "&radius=5&key=AIzaSyCPSpp71zG_UHFURffw5k0Qc_06Q7NETmQ";

        try {
            URL url = new URL(placeURL);
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


                        //add json code here
                        try {
                            JSONObject json = new JSONObject(response);
                            JSONArray array = json.getJSONArray("results");
                            int length = array.length();
                            String place_id = "";
                            for(int i = 0; i<length; i++){
                                place_id = array.getJSONObject(i).getString("place_id");
                            }

                            Log.d("TAG", place_id);
                            String placeIdUrl = "https://maps.googleapis.com/maps/api/place/details/json?key=AIzaSyCPSpp71zG_UHFURffw5k0Qc_06Q7NETmQ&placeid="+place_id;
                            URL url = new URL(placeIdUrl);
                            urlConnection = (HttpURLConnection) url.openConnection();
                            urlConnection.setRequestMethod("GET");
                            urlConnection.addRequestProperty("Accept", "application/json");
                            urlConnection.addRequestProperty("Content-type", "application/json");
                            urlConnection.setDoOutput(true);

                            res = urlConnection.getResponseCode();
                            if(res == HttpURLConnection.HTTP_OK){
                                BufferedReader br1 = null;
                                try {
                                    br1 = new BufferedReader(new InputStreamReader((urlConnection.getInputStream())));
                                } catch (IOException e) {
                                    e.printStackTrace();
                               }
                                StringBuilder sb1 = new StringBuilder();
                                output="";

                                while ((output = br1.readLine()) != null)
                                    sb1.append(output);

                                output = sb1.toString();
                                Log.d("TAG1", output);

                                JSONObject jsson = new JSONObject(output);
                                JSONObject result = jsson.getJSONObject("result");
                                Log.d("TAG2", output);
                                address = result.getString("formatted_address");
                                Log.d("TAG4", address);
                                name = result.getString("name");
                                Log.d("TAG5", name);
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        TextView tvAddress = (TextView)findViewById(R.id.tvPlaceNameAddress);
                                        tvAddress.setText(name + ", " + address);

                                        TextView tvRating = (TextView)findViewById(R.id.tvRating);
                                        tvRating.setText("Rating : 0");
                                    }
                                });
                                rating = ""+result.getDouble("rating");

                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        TextView tvRating = (TextView)findViewById(R.id.tvRating);
                                        tvRating.setText(rating);
                                    }
                                });
                                Log.d("TAG6", address+" ,"+name+" ,"+rating);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        } catch (ProtocolException e) {
                            e.printStackTrace();
                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }

                }
            };
            Thread t = new Thread(myrunnable);
            t.start();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}