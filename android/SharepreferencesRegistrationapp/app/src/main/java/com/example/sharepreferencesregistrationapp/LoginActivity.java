package com.example.sharepreferencesregistrationapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {
  SharedPreferences pref;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login1);
        login=(Button)findViewById(R.id.btnLogin);

        pref = getApplicationContext().getSharedPreferences("TejuPreferences", MODE_PRIVATE);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email,password;
                email=pref.getString("email","");
                password = pref.getString("password","");
                Boolean isLoginSucces =false;
                EditText etEmail=(EditText)findViewById(R.id.etUn);
                EditText etPwd = (EditText)findViewById(R.id.etPwd);

                if(email.equals(etEmail.getText().toString()) == true &&
                        password.equals(etPwd.getText().toString()) == true){
                    isLoginSucces = true;
                }

                if(isLoginSucces == true){
                    Intent i = new Intent(LoginActivity.this, InfoActivity.class);
                    startActivity(i);
                    finish();
                }
            }
        });

    }
}
