package com.felight.musicplayer;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String[] mysongs={"Hindi Songs","Marathi Songs","English Songs"};

        ArrayAdapter<String> Adapter =new ArrayAdapter<String>(this,R.layout.songelement,mysongs);

        ListView lv=(ListView)findViewById(R.id.lvSongsList);

        lv.setAdapter(Adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TextView textView =(TextView)view;
                String msg= "You clicked position"+""+i+"Ths song is"+ textView.getText().toString();
                Toast.makeText(MainActivity.this,msg,Toast.LENGTH_LONG).show();
            }
        });
    }

}
