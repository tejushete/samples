package com.felight.notification;

import android.app.NotificationManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity{

    private Button showNoti;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        showNoti = (Button)findViewById(R.id.btnShowNoti);
        showNoti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(MainActivity.this);
                mBuilder.setSmallIcon(R.drawable.smallicon);
                mBuilder.setContentTitle("Notification example");
                mBuilder.setContentText("This is a test notification");
                NotificationManager manager = (NotificationManager) getSystemService(MainActivity.this.NOTIFICATION_SERVICE);
                manager.notify(0, mBuilder.build());
            }
        });
    }
}
