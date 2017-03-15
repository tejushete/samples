package com.felight.employeedetailslistview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ListView lvEmployees;
    private List emplist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvEmployees=(ListView)findViewById(R.id.lvEmployees);
        initialize();
    }

    @Override
    protected void onStart() {
        super.onStart();
        EmployeeListAdapter adapter =new EmployeeListAdapter(this,emplist);
        lvEmployees.setAdapter(adapter);
    }
    private void initialize(){
        emplist=new ArrayList();
        emplist.add(new Employee(1,"jack",606060));
        emplist.add(new Employee(2,"jack",606060));
        emplist.add(new Employee(3,"jack",606060));
        emplist.add(new Employee(4,"jack",606060));
        emplist.add(new Employee(5,"jack",606060));
        emplist.add(new Employee(6,"jack",606060));
        emplist.add(new Employee(7,"jack",606060));
        emplist.add(new Employee(8,"jack",606060));
        emplist.add(new Employee(9,"jack",606060));
        emplist.add(new Employee(10,"jack",606060));
        emplist.add(new Employee(11,"jack",606060));


    }
}
