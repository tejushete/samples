package com.felight.sensorvalues;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    private SensorManager mSensorManager;
    private Sensor sensor;
    private TextView tvResult;
    private static final int SHAKE_THRESHOLD=800;
    private long lastUpdate;
    private float x;
    private float y;
    private float z;
    private float last_x;
    private float last_y;
    private float last_z;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvResult=(TextView)findViewById(R.id.tvResult);
        mSensorManager=(SensorManager)getSystemService(Context.SENSOR_SERVICE);
        sensor=mSensorManager.getDefaultSensor(sensor.TYPE_ACCELEROMETER);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        float x = sensorEvent.values[0];
        float y = sensorEvent.values[1];
        float z = sensorEvent.values[2];
        tvResult.setText("X:" + x + "\nY:" + y + "\nZ:" + z);
     /*long curTime = System.currentTimeMillis();
        if ((curTime - lastUpdate) > 100) {
            long difTime = (curTime - lastUpdate);
            lastUpdate = curTime;
            x = sensorEvent.values[0];
            y = sensorEvent.values[1];
            z = sensorEvent.values[2];

            float speed = Math.abs(x + y + z - last_x - last_y - last_z) / difTime * 10000;
            if (speed > SHAKE_THRESHOLD) {
                Log.d("sensor", "shake detected w/speed:" + speed);
                tvResult.setText("shake detected w/ speed" + speed);
            }
            last_x = x;
            last_y = y;
            last_z = z;

        }*/
    }
    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(this,sensor,SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }
}
