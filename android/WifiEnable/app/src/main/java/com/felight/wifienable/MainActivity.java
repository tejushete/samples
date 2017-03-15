package com.felight.wifienable;
import android.content.Context;
import android.net.wifi.WifiManager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        WifiManager wifiManager = (WifiManager)getSystemService(Context.WIFI_SERVICE);
        wifiManager.setWifiEnabled(true);
    }
}
