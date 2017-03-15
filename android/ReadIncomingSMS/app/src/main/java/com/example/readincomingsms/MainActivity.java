package com.example.readincomingsms;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pref =  getApplicationContext().getSharedPreferences("myPrefs",
                Context.MODE_PRIVATE);
        pref.registerOnSharedPreferenceChangeListener(new SharedPreferences.OnSharedPreferenceChangeListener() {
            @Override
            public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {
                String msgFrom = pref.getString("from",null);
                String body=pref.getString("body",null);
                TextView tvResult = (TextView)findViewById(R.id.tvResult);
                tvResult.setText("From:"+msgFrom+";body:"+body);
            }
        });
    }
}
