package com.felight.myapp;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by Tejashree on 1/18/2017.
 */

public class NewActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_activity_layout);
    }
}
