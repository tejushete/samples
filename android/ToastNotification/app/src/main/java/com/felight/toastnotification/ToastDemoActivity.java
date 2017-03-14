package com.felight.toastnotification;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.Toast;

public class ToastDemoActivity extends AppCompatActivity{
    private Button button1,button2,button3,button4,button5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.toast_demo_layout);

        button1=(Button)findViewById(R.id.button1);
        button2=(Button)findViewById(R.id.button2);
        button3=(Button)findViewById(R.id.button3);
        button4=(Button)findViewById(R.id.button4);
        button5=(Button)findViewById(R.id.button5);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast toast = Toast.makeText(ToastDemoActivity.this,"selected  forums",Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.TOP| Gravity.LEFT,0,0);
                toast.show();
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast toast = Toast.makeText(ToastDemoActivity.this,"selected  forums",Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.TOP| Gravity.RIGHT,0,0);
                toast.show();
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast toast = Toast.makeText(ToastDemoActivity.this,"selected  forums",Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER,0,0);
                toast.show();
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast toast = Toast.makeText(ToastDemoActivity.this,"selected  forums",Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.BOTTOM | Gravity.LEFT,0,0);
                toast.show();
            }
        });
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast toast = Toast.makeText(ToastDemoActivity.this,"selected  forums",Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.BOTTOM| Gravity.RIGHT,0,0);
                toast.show();
            }
        });
    }
}
