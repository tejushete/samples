package com.example.slidingmenu.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.slidingmenu.R;

/**
 * Created by Tejashree on 01-Mar-17.
 */

public class Fragment2 extends Fragment {
    public Fragment2(){

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment2,container,false);
        return  rootView;
    }
}
