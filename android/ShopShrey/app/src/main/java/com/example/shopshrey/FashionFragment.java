package com.example.shopshrey;


import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;


/**
 * A simple {@link Fragment} subclass.
 */
public class FashionFragment extends Fragment implements AdapterView.OnItemClickListener{

    boolean isMenSelected = false;

    public FashionFragment() {
        // Required empty public constructor

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fashion, container, false);
        ImageView gridViewLoading =(ImageView)view.findViewById(R.id.ivGridViewLoading);


        GridView gridview = (GridView) view.findViewById(R.id.gdFashion);
        gridview.setAdapter(new gridAdapter(this.getActivity()));
        gridview.setOnItemClickListener(this);

//        ImageView loading = (ImageView)view.findViewById(R.id.ivGridViewLoading);
//        Animation rotate = AnimationUtils.loadAnimation(this.getActivity(),R.anim.rotate_animation);
//        loading.startAnimation(rotate);

     //   gridViewLoading.setOnClickListener(new View.OnClickListener() {




        gridViewLoading.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("TAG", "<><>");

            }
        });

        Button men = (Button)view.findViewById(R.id.btnMenFashion);
        men.bringToFront();
        men.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("TAG", "<><>");
            }
        });
        Button women = (Button)view.findViewById(R.id.btnWomenFashion);
        women.bringToFront();
        women.setTextColor(Color.rgb(255,0,0));
        women.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("TAG", "<><>");
            }
        });
        return view;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent myIntent = new Intent(getActivity(), ProductDetailsActivity.class);
        getActivity().startActivity(myIntent);


        Log.d("<><>", "<><>"+i);
    }
    private class gridAdapter extends BaseAdapter{

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
