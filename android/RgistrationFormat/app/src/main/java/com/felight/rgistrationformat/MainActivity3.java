package com.felight.rgistrationformat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import static com.felight.rgistrationformat.R.id.etEmailAdd;
import static com.felight.rgistrationformat.R.id.etMobile;
import static com.felight.rgistrationformat.R.id.etName;

public class MainActivity3 extends AppCompatActivity {
    private TextView tvFullName, tvMobileNo, tvEmail, tvGender, tvDob;

    private EditText etName, etMobile, etpw, etRpw;
    private EditText etEmailAdd;
    private RadioButton rbFemale, rbMale, rbOther;
    private Button btRegister;
    // int phone2;
    private String name, mobile, email, password, reenterpw, gender;
    private int year, month, day;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        tvFullName = (TextView) findViewById(R.id.tvFullName);
        tvMobileNo = (TextView) findViewById(R.id.tvMobileNo);
        tvEmail = (TextView) findViewById(R.id.tvEmail);
        tvGender = (TextView) findViewById(R.id.tvGender);
        tvDob = (TextView) findViewById(R.id.tvDob);
        etName = (EditText) findViewById(R.id.etName);
        etMobile = (EditText) findViewById(R.id.etMobile);
        etEmailAdd = (EditText) findViewById(R.id.etEmailAdd);
        etpw = (EditText) findViewById(R.id.etPw) ;
        etRpw =(EditText) findViewById(R.id.etRpw) ;
        rbFemale = (RadioButton) findViewById(R.id.rbFemale);
        rbMale = (RadioButton) findViewById(R.id.rbMale);
        rbOther = (RadioButton) findViewById(R.id.rbOther);
        btRegister = (Button) findViewById(R.id.btRegister);
        btRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register();

            }
        });
        DatePicker datePicker = (DatePicker) findViewById(R.id.datePicker1);
        day = datePicker.getDayOfMonth();
        month = datePicker.getMonth() + 1;
        year = datePicker.getYear();


        rbFemale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gender = "female";
                rbMale.setChecked(false);
                rbOther.setChecked(false);
            }
        });

        rbMale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gender="male";
                rbFemale.setChecked(false);
                rbOther.setChecked(false);
            }
        });

        rbOther.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gender = "other";

                rbFemale.setChecked(false);
                rbMale.setChecked(false);

            }
        });
    }




    public void register() {

        boolean areParamValid = true;
        initialise();
        if (!validateMobile(etMobile.getText().toString())) {
            areParamValid = false;
        }

        if(name.isEmpty()){
            areParamValid = false;
            etName.setError("empty name");
        }

        if(!validateEmail(email)){
            areParamValid = false;
        }

        if(!validatePassword(password, reenterpw)){
            areParamValid = false;
        }

        if(areParamValid == false){
            Toast.makeText(MainActivity3.this, "Enter valid params", Toast.LENGTH_LONG).show();
            return;
        }

        Intent intent = new Intent(MainActivity3.this, detailsActivity.class);
        intent.putExtra("name", name);
        intent.putExtra("email", email);
        intent.putExtra("mobile", mobile);
        intent.putExtra("dob", day+"/"+month+"/"+year);
        intent.putExtra("gender", gender);
        startActivity(intent);

    }

    private boolean validateMobile(String phone2) {
        boolean check = true;
        if(phone2.isEmpty()){
            check = false;
            etMobile.setError("Mobile is empty.");
            final Animation shake= AnimationUtils.loadAnimation(this,R.anim.shake);
            etMobile.startAnimation(shake);
            return check;
        }
        if (phone2.length() < 6 || phone2.length() > 13) {
            check = false;
            etMobile.setError("Not a valid mobile number");
          //  final Animation shake= AnimationUtils.loadAnimation(this,R.anim.shake);
           // etMobile.setOnClickListener(new View.OnClickListener() {
             //   @Override
               // public void onClick(View view) {
                 //   etMobile.startAnimation(shake);

                //}
         //   });

        }
        return check;
    }

    private boolean validateEmail(String email){
        boolean check = true;

        if(email.isEmpty()){
            check = false;
            etEmailAdd.setError("Email id is empty.");
            return check;
        }

        if(!email.contains("@") && !email.contains(".com")){
            check = false;
            etEmailAdd.setError("Email id is not valid.");
            return check;
        }

        return check;
    }

    private boolean validatePassword(String pw, String secondPw){

        boolean check = true;

        if(password.isEmpty()){
            check = false;
            etpw.setError("password is empty");
        }
        if(reenterpw.isEmpty()){
            check = false;
            etRpw.setError("password is empty");
            return check;
        }
        if(!password.equals(reenterpw)){
            check = false;
            etpw.setError("Password fields don't match");
            etRpw.setError("Password fields don't match");
            return check;
        }

        return check;
    }

    public void initialise() {
        name = etName.getText().toString();
        mobile = etMobile.getText().toString();
        email = etEmailAdd.getText().toString();
        password = etpw.getText().toString();
        reenterpw = etRpw.getText().toString();
    }
}
















































