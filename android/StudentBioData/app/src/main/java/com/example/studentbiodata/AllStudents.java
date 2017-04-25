package com.example.studentbiodata;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class AllStudents extends AppCompatActivity {

    ArrayList<String> studentsList;

    private void updateNames(){

        int i = 0;
        for(String name: studentsList){
            name = (i+1)+") " + name;
            studentsList.set(i, name);
            i++;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.total_students);

        studentsList = new ArrayList<String >();

        sharedData.studentsInfoList = sharedData.mDataBaseHandler.getStudentDetails();
        for(int i = 0; i<sharedData.studentsInfoList.size(); i++){
            student s = sharedData.studentsInfoList.get(i);
            studentsList.add(s.getName());
        }

        updateNames();
        ArrayAdapter<String> Adapter =new ArrayAdapter<String>(this,R.layout.single_student, studentsList);

        ListView lv=(ListView)findViewById(R.id.lvStudents);

        lv.setAdapter(Adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.d("TAG", studentsList.get(i));
                Intent intent = new Intent(AllStudents.this, DetailsActivity.class);
                Bundle mBundle = new Bundle();
                mBundle.putInt("index", i);
                intent.putExtras(mBundle);
                startActivity(intent);
            }
        });
    }

}
