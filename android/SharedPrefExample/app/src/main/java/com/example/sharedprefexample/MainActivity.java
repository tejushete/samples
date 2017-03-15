package com.example.sharedprefexample;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    String newVal;
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


         pref = getApplicationContext().getSharedPreferences("TejuPreferences", MODE_PRIVATE);
         editor = pref.edit();

        String val = pref.getString("mykey", null);

        TextView tvSet = (TextView)findViewById(R.id.tvDisplay);
        tvSet.setText(val);

        EditText etGet = (EditText)findViewById(R.id.etGet);
        etGet.setText(val);
        etGet.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                newVal = charSequence.toString();
                editor.putString("mykey", newVal);
                editor.commit();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }
}
