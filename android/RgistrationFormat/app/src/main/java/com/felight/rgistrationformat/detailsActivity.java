package com.felight.rgistrationformat;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class detailsActivity extends AppCompatActivity {
    private TextView name, email, mobile, dob, gender;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details);

        name = (TextView)findViewById(R.id.etUserName);
        email = (TextView)findViewById(R.id.etUserEmail);
        mobile = (TextView)findViewById(R.id.etUserMobile);
        dob = (TextView)findViewById(R.id.etUserDOB);
        gender = (TextView)findViewById(R.id.etUserGender);

        Intent intent = getIntent();
        String username = intent.getStringExtra("name");
        String useremail = intent.getStringExtra("email");
        String usermobile = intent.getStringExtra("mobile");
        String usergender = intent.getStringExtra("gender");
        String userdob = intent.getStringExtra("dob");

        name.setText(username);
        email.setText(useremail);
        mobile.setText(usermobile);
        dob.setText(userdob);
        gender.setText(usergender);
    }
}
