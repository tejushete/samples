package com.felight.splashscreen;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class SplashScrrenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        android.support.v7.app.ActionBar AB=getSupportActionBar();
        AB.hide();
        setContentView(R.layout.activity_splash_scrren);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent =new Intent(getBaseContext(),ActivityNavigator.class);
                startActivity(intent);
                finish();


            }
        } ,3000);
    }
}
