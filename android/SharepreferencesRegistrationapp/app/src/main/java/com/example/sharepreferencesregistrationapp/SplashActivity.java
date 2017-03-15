package com.example.sharepreferencesregistrationapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;


public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        android.support.v7.app.ActionBar AB=getSupportActionBar();
        AB.hide();
        setContentView(R.layout.activity_splash);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                SharedPreferences pref = getApplicationContext().getSharedPreferences("TejuPreferences", MODE_PRIVATE);
                Boolean isRegistered = pref.getBoolean("isRegistered", false);
                Log.d("TAG", ""+isRegistered);
                Intent intent;

                if(isRegistered == false){
                    intent =new Intent(getBaseContext(),MainActivity.class);
                }else{
                        intent = new Intent(getBaseContext(), LoginActivity.class);

                }
                startActivity(intent);
                finish();


            }
        } ,0);
    }
}