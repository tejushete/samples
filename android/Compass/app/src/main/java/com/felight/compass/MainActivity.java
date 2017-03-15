package com.felight.compass;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import static com.felight.compass.R.id.image;

public class MainActivity extends AppCompatActivity implements SensorEventListener{



    private ImageView ivCompasImg;
    private TextView tvCompass;
    private float currentDegree=0f;
    private SensorManager mSensorManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ivCompasImg=(ImageView)findViewById(R.id.ivCompasImg);
        tvCompass=(TextView)findViewById(R.id.tvCompass);
        mSensorManager=(SensorManager)getSystemService(SENSOR_SERVICE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(this,mSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION),SensorManager.SENSOR_DELAY_GAME);

    }

    @Override
    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);


    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {


        float degree = Math.round(sensorEvent.values[0]);
        tvCompass.setText("Heading: " + Float.toString(degree) + " degrees");



        RotateAnimation ra = new RotateAnimation(

                currentDegree,
                        -degree,

                Animation.RELATIVE_TO_SELF, 0.5f,

                Animation.RELATIVE_TO_SELF,

                0.5f);

        ra.setDuration(210);
        ra.setFillAfter(true);

        ivCompasImg.startAnimation(ra);

        currentDegree = -degree;

    }

    @Override

    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

}





