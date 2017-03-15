package com.felight.greetuserapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class GreetUserActivity extends AppCompatActivity {


    private static final String TAG =GreetUserActivity.class.getSimpleName() ;
    private EditText etFirstName;
    private EditText etLastName;
    private Button btnGreetUser;
    private TextView tvResult;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_greet_user);
        etFirstName=(EditText)findViewById(R.id.etFirstName);
        etLastName=(EditText)findViewById(R.id.etLastName);
        btnGreetUser=(Button)findViewById(R.id.btnGreetUser);
        tvResult=(TextView)findViewById(R.id.tvResult);

        btnGreetUser.setOnClickListener(new View.OnClickListener() {




            @Override
            public void onClick(View view) {

                String fName=etFirstName.getText().toString();
                String lName=etLastName.getText().toString();
                tvResult.setText("Greetings"+" "+fName +" " +lName);
                Log.i(TAG,fName+" "+lName);
                Toast.makeText(GreetUserActivity.this,"Greetings"+" "+fName+" "+lName,Toast.LENGTH_SHORT).show();


            }
        });
    }



}
