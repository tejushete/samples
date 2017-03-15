package com.example.logintofacebook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

public class MainActivity extends AppCompatActivity {
    LoginButton loginButton;
    TextView tvLogin;
    CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_main);
        loginButton=(LoginButton)findViewById(R.id.facebook_login_btn);
        tvLogin=(TextView)findViewById(R.id.tvlogin);
        callbackManager=CallbackManager.Factory.create();
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                tvLogin.setText("Login Succes \n"+
                loginResult.getAccessToken().getUserId()+"\n"+ loginResult.getAccessToken().getToken());
            }

            @Override
            public void onCancel() {
                tvLogin.setText("Login Cancelled");
            }

            @Override
            public void onError(FacebookException error) {
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode,resultCode,data);
    }
}