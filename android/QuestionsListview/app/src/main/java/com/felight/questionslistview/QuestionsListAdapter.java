package com.felight.questionslistview;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Tejashree on 15-Feb-17.
 */

public class QuestionsListAdapter extends BaseAdapter {

    private Activity activity;



    private List quelist;
    private LayoutInflater inflater;
    public QuestionsListAdapter(Activity activity , List quelist){
        this.activity=activity;
        this.quelist=quelist;
        inflater=activity.getLayoutInflater();
    }
    @Override
    public int getCount() {
        return quelist.size();
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
        Qestions que =(Qestions) quelist.get(i);
        view=inflater.inflate(R.layout.questions_row,viewGroup,false);
        TextView tvR=(TextView)view.findViewById(R.id.tvR);
        RadioButton rb1=(RadioButton)view.findViewById(R.id.rb1);
        RadioButton rb2=(RadioButton)view.findViewById(R.id.rb2);
        RadioButton rb3=(RadioButton)view.findViewById(R.id.rb3);
        RadioButton rb4=(RadioButton)view.findViewById(R.id.rb4);

        tvR.setText(que.getQuestion());
        rb1.setText(que.getOption1());
        rb2.setText(que.getOption2());
        rb3.setText(que.getOption3());
        rb4.setText(que.getOption4());




        return view;
    }
}

