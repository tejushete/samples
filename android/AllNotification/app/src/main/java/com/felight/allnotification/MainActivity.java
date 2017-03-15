package com.felight.allnotification;

import android.app.NotificationManager;
import android.content.Context;
import android.media.MediaPlayer;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.view.View;
import android.widget.Button;

import static com.felight.allnotification.R.raw.song;

public class MainActivity extends AppCompatActivity {

    private Button ShowNoti;
    private Button PlaySong;
    private Button Vibrate;
    private MediaPlayer mediaplayer;
    private Vibrator v;
    private boolean state = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

     //v=(Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE);
        ShowNoti = (Button)findViewById(R.id.btnShowNoti);
        PlaySong = (Button)findViewById(R.id.btnPlaySong);
       Vibrate = (Button) findViewById(R.id.btnVibrate);
        ShowNoti.setOnClickListener(new View.OnClickListener() {
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
        PlaySong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(state == true){
                    mediaplayer.stop();
                    mediaplayer.release();
                    state = false;
                }else{
                    mediaplayer = MediaPlayer.create(MainActivity.this, song);
                    mediaplayer.start();
                    state = true;
                }
            }


        });
        Vibrate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               v=(Vibrator)getSystemService(MainActivity.this.VIBRATOR_SERVICE);
               v.vibrate(1000);
            }
        });

    }

    @Override
    protected void onPause() {
        super.onPause();
        if(state == true) {
            mediaplayer.stop();
            mediaplayer.release();
            state = false;
        }
    }




}



