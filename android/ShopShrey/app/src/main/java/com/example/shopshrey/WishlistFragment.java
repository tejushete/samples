package com.example.shopshrey;


import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class WishlistFragment extends Fragment implements AdapterView.OnItemClickListener {
    ListView listView;
    ListViewCustomAdapter mAdapter;
    List<shopShreyProduct> productList;

    View mWishlistFragmentView;
    int mCount;


    public WishlistFragment() {
        // Required empty public constructor
    }

    public void setNoWishlistViewVisible(View view) {
        LinearLayout emptyWishlist = (LinearLayout) view.findViewById(R.id.llNoWishList);
        LinearLayout wishlist = (LinearLayout) view.findViewById(R.id.llWishListViewHolder);

        emptyWishlist.setVisibility(View.VISIBLE);
        wishlist.setVisibility(View.INVISIBLE);
    }

    public void setWishlistViewVisible(View view) {
        LinearLayout emptyWishlist = (LinearLayout) view.findViewById(R.id.llNoWishList);
        LinearLayout wishlist = (LinearLayout) view.findViewById(R.id.llWishListViewHolder);

        emptyWishlist.setVisibility(View.INVISIBLE);
        wishlist.setVisibility(View.VISIBLE);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_wishlist, container, false);
        mWishlistFragmentView = view;
        final ListView listView = (ListView) view.findViewById(R.id.lvWish);
        if (getActivity() != null) {
            mAdapter = new WishlistFragment.ListViewCustomAdapter(this.getActivity());
            listView.setAdapter(mAdapter);
        }

        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setTitle("My Wishlist");
        LayoutInflater inflator = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflator.inflate(R.layout.wishlist_action_bar, null);
        actionBar.setCustomView(v);
        View cv = actionBar.getCustomView();

        ImageView ivCart = (ImageView) cv.findViewById(R.id.ivCartWishlistActionBar);
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


        Button shopNow = (Button) view.findViewById(R.id.btnShopNow);
        shopNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShopFragment shopFragment = new ShopFragment();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.content_main, shopFragment).commit();
                NavigationView navigationView = (NavigationView) getActivity().findViewById(R.id.nav_view);

                navigationView.getMenu().findItem(R.id.nav_myWish).setChecked(false);
                ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
                actionBar.setDisplayShowCustomEnabled(true);
                actionBar.setTitle("ShopShrey");


            }
        });
        Button btnClearAll = (Button) view.findViewById(R.id.btnClearAll);
        btnClearAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("<><>", ""+shopShreyHelper.wishList.size());
                int size = shopShreyHelper.wishList.size();
                for(int i = 0; i<size; i++){
                    Log.d("<><>", "removing:"+i);
                    shopShreyHelper.mDataBaseHandler.deleteShopShreyProduct(shopShreyHelper.wishList.get(0).getId(),"wish");
                    shopShreyHelper.wishList.remove(0);
                    mAdapter.notifyDataSetChanged();
                }
                mCount = shopShreyHelper.wishList == null ? 0: shopShreyHelper.wishList.size();
                if(mCount>0){
                    setWishlistViewVisible(mWishlistFragmentView);
                }else {
                    setNoWishlistViewVisible(mWishlistFragmentView);
                }
            }
        });

        shopShreyHelper.wishList = shopShreyHelper.mDataBaseHandler.getAllProducts("wish");
        mCount = shopShreyHelper.wishList == null ? 0: shopShreyHelper.wishList.size();

        if(mCount>0){
            setWishlistViewVisible(view);
        }else {
            setNoWishlistViewVisible(view);
        }


        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        getView().setFocusableInTouchMode(true);
        getView().requestFocus();
        getView().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK) {


                    ShopFragment shopFragment = new ShopFragment();
                    android.support.v4.app.FragmentTransaction fragmentTransaction =
                            getActivity().getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.content_main, shopFragment).commit();

                    // handle back button's click listener
                    Toast.makeText(getActivity(), "Back press", Toast.LENGTH_SHORT).show();

                    NavigationView navigationView = (NavigationView) getActivity().findViewById(R.id.nav_view);

                    navigationView.getMenu().findItem(R.id.nav_myWish).setChecked(false);
                    ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
                    actionBar.setDisplayShowCustomEnabled(true);
                    actionBar.setTitle("ShopShrey");

                    return true;
                }
                return false;
            }
        });

    }



    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }

    public class ListViewCustomAdapter extends BaseAdapter {
        Context mContext;

        ListViewCustomAdapter(Context c) {
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
            View list = convertView;
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            shopShreyProduct product = null;
            if (list == null) {
                list = new View(mContext);
                list = inflater.inflate(R.layout.wish, null);
            }

            TextView tvWishProductName = (TextView) list.findViewById(R.id.tvWishProductName);
            TextView tvWishProductPrice = (TextView) list.findViewById(R.id.tvWishProductPrice);
            TextView tvWishProductRating = (TextView) list.findViewById(R.id.tvWishProductRating);

            product = shopShreyHelper.wishList.get(i);

           tvWishProductName.setText(product.getName());
            tvWishProductPrice.setText(product.getPrice());
           tvWishProductRating.setText("Rating: " + product.getRating());

            Bitmap bmp = product.getImage();
            ImageView iv = (ImageView) list.findViewById(R.id.ivWishImage);
            iv.setImageBitmap(bmp);

            ImageButton ibWishListDelete = (ImageButton)list.findViewById(R.id.ibWishListDelete);
            ibWishListDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    View parentRow = (View) view.getParent().getParent();
                    ListView listView = (ListView) parentRow.getParent();
                    int index = listView.getPositionForView(parentRow);
                    shopShreyProduct product = shopShreyHelper.wishList.get(index);
                    shopShreyHelper.mDataBaseHandler.deleteShopShreyProduct(product.getId(),"wish");
                    shopShreyHelper.wishList.remove(index);
                    mCount = shopShreyHelper.wishList.size();
                    Log.d("<><>", "product deleted");
                    //delete from database
                    //if mCount is zero make listview invisible and show empty cart view
                    mAdapter.notifyDataSetChanged();

                }
            });
            return list;

        }


    }
}