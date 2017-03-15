package com.example.sharepreferencesregistrationapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.content.IntentCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private TextView tvFullName, tvMobileNo, tvEmail, tvGender, tvDob;
    private EditText etName, etMobile, etpw, etRpw;
    private EditText etEmailAdd;
    private RadioButton rbFemale, rbMale, rbOther;
    private Button btRegister;
    // int phone2;
    private String name, mobile, email, password, reenterpw, gender;
    private int year, month, day;


    SharedPreferences pref;
    String newVal;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pref = getApplicationContext().getSharedPreferences("TejuPreferences", MODE_PRIVATE);
        editor = pref.edit();

        tvFullName = (TextView) findViewById(R.id.tvFullName);
        tvMobileNo = (TextView) findViewById(R.id.tvMobileNo);
        tvEmail = (TextView) findViewById(R.id.tvEmail);
        tvGender = (TextView) findViewById(R.id.tvGender);
        tvDob = (TextView) findViewById(R.id.tvDob);
        etName = (EditText) findViewById(R.id.etName);
        etMobile = (EditText) findViewById(R.id.etMobile);
        etEmailAdd = (EditText) findViewById(R.id.etEmailAdd);
        rbFemale = (RadioButton) findViewById(R.id.rbFemale);
        rbMale = (RadioButton) findViewById(R.id.rbMale);
        rbOther = (RadioButton) findViewById(R.id.rbOther);
        btRegister = (Button) findViewById(R.id.btRegister);

        Boolean isRegistered = pref.getBoolean("isRegistered", false);
        if(isRegistered == true){
            btRegister.setText("Save");
        }
        btRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    register();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        String val = pref.getString("name", null);
        etName.setText(val);
        etName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                newVal = charSequence.toString();
                editor.putString("name", newVal);
                editor.commit();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        String Day = pref.getString("dobDate", null);
        String Month = pref.getString("dobMonth", null);
        String Year = pref.getString("dobYear", null);

        Calendar cal = Calendar.getInstance();

        day = cal.get(Calendar.DATE);
        month = cal.get(Calendar.MONTH);
        year = cal.get(Calendar.YEAR);

        if(Day != null){
            day = Integer.parseInt(Day);
        }

        if(Month != null){
            month = Integer.parseInt(Month)-1;
        }
        if(Year != null){
            year = Integer.parseInt(Year);
        }

        DatePicker datePicker = (DatePicker) findViewById(R.id.datePicker1);
        datePicker.init(year, month, day, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker datePicker, int i, int i1, int i2) {
                day = datePicker.getDayOfMonth();
                month = datePicker.getMonth() + 1;
                year = datePicker.getYear();

                editor.putString("dobDate", ""+day);
                editor.putString("dobMonth", ""+month);
                editor.putString("dobYear", ""+year);
                editor.commit();
            }
        });

        //Hint use this code for Password
//        ViewGroup parent = (ViewGroup) etMobile.getParent();
//        if (parent != null) {
//            parent.removeView(etMobile);
//        }
        String val1 = pref.getString("mobile", null);
        etMobile.setText(val1);
        etMobile.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                newVal = charSequence.toString();
                editor.putString("mobile", newVal);
                editor.commit();

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        String val2 = pref.getString("email",null);
        etEmailAdd.setText(val2);
        etEmailAdd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                newVal=charSequence.toString();
                editor.putString("email",newVal);
                editor.commit();

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });



        String rb = pref.getString("gender",null);
        if(rb != null && rb.equals("0")){
            rbMale.setChecked(false);
            rbOther.setChecked(false);
            rbFemale.setChecked(true);
        }else if(rb != null && rb.equals("1")){
            rbMale.setChecked(true);
            rbOther.setChecked(false);
            rbFemale.setChecked(false);
        }else if(rb!= null && rb.equals("2")){
            rbMale.setChecked(false);
            rbOther.setChecked(true);
            rbFemale.setChecked(false);
        }

        rbFemale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gender = "female";
                rbMale.setChecked(false);
                rbOther.setChecked(false);
                editor.putString("gender","0");
                editor.commit();
            }
        });



        rbMale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gender="male";
                rbFemale.setChecked(false);
                rbOther.setChecked(false);
                editor.putString("gender","1");
                editor.commit();
            }
        });

        rbOther.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gender = "other";
                rbFemale.setChecked(false);
                rbMale.setChecked(false);
                editor.putString("gender","2");
                editor.commit();
            }
        });
    }

    public void PasswordGenerator(){
        char[] chars = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            char c = chars[random.nextInt(chars.length)];
            sb.append(c);
        }
        password = sb.toString();
        editor.putString("password",password);
        editor.commit();
    }

    public void register() throws Exception {

        Boolean isRegisteredAlready = pref.getBoolean("isRegistered", false);
        if(isRegisteredAlready == true) {
            Intent i = new Intent(MainActivity.this, InfoActivity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(i);
            finish();
            return;
        }

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

        if(areParamValid == false){
            Toast.makeText(MainActivity.this, "Enter valid params", Toast.LENGTH_LONG).show();
            return;
        }

          final Intent intent = new Intent(MainActivity.this, LoginActivity.class);
//        intent.putExtra("name", name);
//        intent.putExtra("email", email);
//        intent.putExtra("mobile", mobile);
//        intent.putExtra("dob", day+"/"+month+"/"+year);
//        intent.putExtra("gender", gender);
        //create password and email it to user
        //launch login activity

        editor.putBoolean("isRegistered", true);
        editor.commit();
        Log.d("TAG", ""+pref.getBoolean("isRegistered", false));
        new Thread(new Runnable() {

            @Override
            public void run() {
                Log.d("TAG", "sending email");
                PasswordGenerator();
                GMailSender gm = new GMailSender("kiran.d.nalawade@gmail.com", "srib@3621");
                try {
                    gm.sendMail("Teju App Registration Password", "your new password is " + password, "kiran.d.nalawade@gmail.com",
                            email, null);
                    Intent i = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(intent);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

        //finish();
    }

    private boolean validateMobile(String phone2) {
        boolean check = true;
        if(phone2.isEmpty()){
            check = false;
            etMobile.setError("Mobile is empty.");
            return check;
        }
        if (phone2.length() < 6 || phone2.length() > 13) {
            check = false;
            etMobile.setError("Not a valid mobile number");

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
    }
}