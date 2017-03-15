package com.felight.listviewdemo;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ListView lvColorsList;
    ArrayList<String> colorsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] mycolors = {"pink", "blue", "green", "purple", "orange", "red", "black", "white",};
        /*colorsList = new ArrayList<String>();
        colorsList.add("pink");
        colorsList.add("blue");
        colorsList.add("green");
        colorsList.add("purple");
        colorsList.add("orange");
        colorsList.add("red");
        colorsList.add("black");
        */

        ArrayAdapter<String> Adapter = new ArrayAdapter<String>(this, R.layout.colorelements, mycolors);

        ListView lv = (ListView) findViewById(R.id.lvColorsList);

        lv.setAdapter(Adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TextView textView =(TextView)view;
                String msg= "You clicked position"+" "+i+" "+"The color is"+" "+ textView.getText().toString();
                Toast.makeText(MainActivity.this,msg,Toast.LENGTH_LONG).show();
            }
        });
    }

}
