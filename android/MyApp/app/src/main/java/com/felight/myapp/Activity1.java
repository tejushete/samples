package com.felight.myapp;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.jar.Attributes;

public class Activity1 extends AppCompatActivity {
    private static final int NAME_REQUEST_CODE=100;
    private Button btnGetName;
    private TextView tvName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_1_layout);
        btnGetName=(Button)findViewById(R.id.btnGetName);
        tvName=(TextView)findViewById(R.id.tvName);
    }

    @Override
    protected void onStart() {
        super.onStart();
        btnGetName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getBaseContext(), Activity2.class);
                startActivityForResult(intent, NAME_REQUEST_CODE);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if(requestCode==NAME_REQUEST_CODE && resultCode==RESULT_OK){
            String name=intent.getStringExtra(Activity2.NAME);
            tvName.setText(name);
        }
        super.onActivityResult(requestCode, resultCode, intent);
        Log.i("teju",""+requestCode);
    }
}