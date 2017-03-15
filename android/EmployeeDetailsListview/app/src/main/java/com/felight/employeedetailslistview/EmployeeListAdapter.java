package com.felight.employeedetailslistview;

import android.app.Activity;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Tejashree on 15-Feb-17.
 */

public class EmployeeListAdapter extends BaseAdapter {
    private Activity activity;



    private List empList;
    private LayoutInflater inflater;
    public EmployeeListAdapter(Activity activity ,List empList){
        this.activity=activity;
        this.empList=empList;
        inflater=activity.getLayoutInflater();
    }
    @Override
    public int getCount() {
        return empList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Employee emp =(Employee)empList.get(i);
        view=inflater.inflate(R.layout.employee_row,viewGroup,false);
        ImageView ivEmp=(ImageView)view.findViewById(R.id.ivEmp);
        TextView tvEmployeeName=(TextView)view.findViewById(R.id.tvEmployeeName);
        TextView tvEmployeeSalary=(TextView)view.findViewById(R.id.tvEmployeeSalary);
        tvEmployeeName.setText(emp.getName());
        tvEmployeeSalary.setText(""+emp.getSalary());



        return view;
    }
}
