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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;


/**
 * A simple {@link Fragment} subclass.
 */
public class CartFragment extends Fragment implements AdapterView.OnItemClickListener {
    ListView listView;
    ListViewCustomAdapter adapter;
    int mCount;

    public CartFragment() {
        // Required empty public constructor
    }

    public void setNoCartViewVisible(View view){
        LinearLayout emptycart = (LinearLayout)view.findViewById(R.id.llNoCart);
        LinearLayout cartlist = (LinearLayout)view.findViewById(R.id.llListViewHolder);
        LinearLayout totalCart = (LinearLayout)view.findViewById(R.id.llTotalPriceCart);

        emptycart.setVisibility(View.VISIBLE);
        cartlist.setVisibility(View.INVISIBLE);
        totalCart.setVisibility(View.INVISIBLE);
    }

    public void setCartViewVisible(View view){
        LinearLayout emptycart = (LinearLayout)view.findViewById(R.id.llNoCart);
        LinearLayout cartlist = (LinearLayout)view.findViewById(R.id.llListViewHolder);
        LinearLayout totalCart = (LinearLayout)view.findViewById(R.id.llTotalPriceCart);

        emptycart.setVisibility(View.INVISIBLE);
        cartlist.setVisibility(View.VISIBLE);
        totalCart.setVisibility(View.VISIBLE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cart, container, false);

        ListView listView = (ListView)view.findViewById(R.id.lvMain);
        if (getActivity()!=null) {
            adapter = new ListViewCustomAdapter(this.getActivity());
            listView.setAdapter(adapter);
        }

        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setTitle("My Cart");
        LayoutInflater inflator = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflator.inflate(R.layout.cart_action_bar, null);
        actionBar.setCustomView(v);

        mCount = 5;

        if(mCount>0){
            setCartViewVisible(view);
        }else {
            setNoCartViewVisible(view);
        }

        return view;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }
    public  class ListViewCustomAdapter extends BaseAdapter{
        Context mContext;
        ListViewCustomAdapter(Context c){
            mContext = c;
        }
        @Override
        public int getCount() {
            return mCount;
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
            View list =null;
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            if (convertView == null) {
                list = new View(mContext);
                convertView = inflater.inflate(R.layout.main, null);
                convertView.setTag(list);
            } else {
               list = (View) convertView.getTag();
            }


            return convertView;
        }
    }
}
