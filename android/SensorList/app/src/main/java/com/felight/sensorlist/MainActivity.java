package com.felight.sensorlist;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private TextView tvSensors;
    private SensorManager mSensorManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvSensors=(TextView)findViewById(R.id.tvSensors);
        mSensorManager=(SensorManager)getSystemService(Context.SENSOR_SERVICE);
    }

    @Override
    protected void onStart() {
        super.onStart();
        List<Sensor> deviceSensors=mSensorManager.getSensorList(Sensor.TYPE_ALL);
        StringBuilder sensorinfo=new StringBuilder();
        for(Sensor sensor: deviceSensors)
            sensorinfo.append("\n\n"+sensor.getName()+""+sensor.getType()+" "+sensor.getVendor());
        tvSensors.setText(sensorinfo.toString());
    }
}
