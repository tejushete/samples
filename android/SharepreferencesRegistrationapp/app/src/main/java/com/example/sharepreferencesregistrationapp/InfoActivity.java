package com.example.sharepreferencesregistrationapp;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class InfoActivity extends AppCompatActivity {
    private TextView name, email, mobile, dob, gender;
    SharedPreferences pref;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        pref = getApplicationContext().getSharedPreferences("TejuPreferences", MODE_PRIVATE);

        name = (TextView) findViewById(R.id.etUserName);
        email = (TextView) findViewById(R.id.etUserEmail);
        mobile = (TextView) findViewById(R.id.etUserMobile);
        dob = (TextView) findViewById(R.id.etUserDOB);
        gender = (TextView) findViewById(R.id.etUserGender);

        String username = pref.getString("name", "");
        String useremail = pref.getString("email", "");
        String usermobile = pref.getString("mobile", "");
        String usergender = pref.getString("gender", "");
        if(usergender.equals("0")) usergender = "female";
        if(usergender.equals("1")) usergender = "male";
        if(usergender.equals("2")) usergender = "other";
        String userdob = pref.getString("dobDate", "")+"/" + pref.getString("dobMonth", "")+"/" +pref.getString("dobYear", "");

        name.setText(username);
        email.setText(useremail);
        mobile.setText(usermobile);
        dob.setText(userdob);
        gender.setText(usergender);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menuedit, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = new Intent(getBaseContext(), MainActivity.class);
        startActivity(intent);
        return super.onOptionsItemSelected(item);
    }
}