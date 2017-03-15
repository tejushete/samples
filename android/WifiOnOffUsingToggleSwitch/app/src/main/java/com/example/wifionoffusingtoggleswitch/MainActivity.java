package com.example.wifionoffusingtoggleswitch;

import android.content.Context;
import android.net.wifi.WifiManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {
    private ToggleButton tbWifi;
    private WifiManager wifiManager;
    private TextView tvWifi;
    Boolean isChecked=false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tbWifi=(ToggleButton)findViewById(R.id.tbWifi);
        tvWifi=(TextView)findViewById(R.id.tvWifi);
        wifiManager=(WifiManager)getSystemService(Context.WIFI_SERVICE);

        if(wifiManager.isWifiEnabled()){
            tvWifi.setText("Wifi is cerrently On");
            tbWifi.setChecked(true);
            isChecked = true;
        }else{
            tvWifi.setText("Wifi is cerrently Off");
            tbWifi.setChecked(false);
            isChecked = false;
        }
        tbWifi.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                if(isChecked == false){
                    wifiManager.setWifiEnabled(true);
                    tvWifi.setText("Wifi is cerrently On");
                    isChecked = true;
                }else{
                    wifiManager.setWifiEnabled(false);
                    tvWifi.setText("Wifi is cerrently Off");
                    isChecked = false;
                }
            }
        });


    }
}
