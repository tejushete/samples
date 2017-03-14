package com.example.shopshrey;


import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;


/**
 * A simple {@link Fragment} subclass.
 */
public class FurnitureFragment extends Fragment implements AdapterView.OnItemClickListener{


    public FurnitureFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_furniture, container, false);
        GridView gridview = (GridView) view.findViewById(R.id.gdFurniture);
        gridview.setAdapter(new FurnitureFragment.gridAdapter(this.getActivity()));
        gridview.setOnItemClickListener(this);

//        ImageView loading = (ImageView)view.findViewById(R.id.ivGridViewLoading);
//        Animation rotate = AnimationUtils.loadAnimation(this.getActivity(),R.anim.rotate_animation);
//        loading.startAnimation(rotate);

        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setTitle("Home and Furniture");
        LayoutInflater inflator = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflator.inflate(R.layout.living_action_bar, null);
        actionBar.setCustomView(v);
        View cv = actionBar.getCustomView();

        ImageView ivNoti = (ImageView)cv.findViewById(R.id.ivNotiLivingActionBar);
        ivNoti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NotificationFragment notificationFragment = new NotificationFragment();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.content_main, notificationFragment).commit();
                NavigationView navigationView = (NavigationView) getActivity().findViewById(R.id.nav_view);
                navigationView.getMenu().findItem(R.id.nav_notifications).setChecked(true);

            }
        });
        ImageView ivCart =(ImageView)cv.findViewById(R.id.ivCartLivingActionBar);
        ivCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CartFragment cartFragment = new CartFragment();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.content_main, cartFragment).commit();
                NavigationView navigationView = (NavigationView) getActivity().findViewById(R.id.nav_view);
                navigationView.getMenu().findItem(R.id.nav_myCart).setChecked(true);

            }
        });

        return view;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }
    private class gridAdapter extends BaseAdapter {

        Context mContext;
        gridAdapter(Context c){
            mContext = c;
        }

        @Override
        public int getCount() {
            return 15;
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
        public View getView(int i, View convertView, ViewGroup viewGroup) {
            View grid;
            LayoutInflater inflater = (LayoutInflater) mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            if (convertView == null) {

                grid = new View(mContext);
                grid = inflater.inflate(R.layout.product_grid_view_single, null);
            } else {
                grid = (View) convertView;
            }
            return grid;
        }
    }
}



