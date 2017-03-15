package com.felight.questionslistview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ListView lvQue;
    private List quelist;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvQue = (ListView)findViewById(R.id.lvQue);
        initialize();
    }

    @Override
    protected void onStart() {
        super.onStart();
        QuestionsListAdapter adapter =new QuestionsListAdapter(this,quelist);
        lvQue.setAdapter(adapter);
    }
    private void initialize(){
        quelist=new ArrayList();
        quelist.add(new Qestions(1,"D0 you know Android ?","Yes","No","Only Basic","May be"));
        quelist.add(new Qestions(2,"D0 you know Android ?","Yes","No","Only Basic","May be"));
        quelist.add(new Qestions(3,"D0 you know Android ?","Yes","No","Only Basic","May be"));
        quelist.add(new Qestions(4,"D0 you know Android ?","Yes","No","Only Basic","May be"));
        quelist.add(new Qestions(5,"D0 you know Android ?","Yes","No","Only Basic","May be"));
    }
}
