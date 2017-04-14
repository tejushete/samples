package com.example.shopshrey;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;



public class AccountActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_account);
        //NavigationView navigationView = (NavigationView)findViewById(R.id.nav_view);
        //navigationView.setEnabled(false);

        ActionBar actionBar = ((AppCompatActivity) this).getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true);

        actionBar.setTitle("ShopShrey");
        LayoutInflater inflator = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflator.inflate(R.layout.account_action_bar, null);
        actionBar.setCustomView(v);
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.rgb(35, 51, 138)));
        actionBar.setElevation(0);

        TextView cancel = (TextView)findViewById(R.id.tvAccountCancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        Button signup = (Button)findViewById(R.id.btnAccountSignUp);
        Button newuser = (Button)findViewById(R.id.btnNewUser);
        newuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AccountActivity.this,SignUpActivity.class);
                startActivity(intent);
            }
        });
    }


}
