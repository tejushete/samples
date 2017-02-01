package com.felight.myapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Activity2 extends AppCompatActivity {
    public static final String NAME="name";
    private EditText etName;
    public void submit(View view){
        String name=etName.getText().toString();
        Intent intent=new Intent();
        intent.putExtra(NAME,name);
        setResult(RESULT_OK,intent);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2_layout);
        etName=(EditText)findViewById(R.id.etName);
    }
}
