package com.example.shopshrey;


import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.KeyEvent;
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
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class StationaryFragment extends Fragment implements AdapterView.OnItemClickListener,serverResponse{

    List<shopShreyProduct>productList;
    Button books,others;
    serverResponse context;
    View stationaryFragmentView;
    GridView stationaryGridView;
    gridAdapter mGridAdapter;


    public StationaryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        stationaryFragmentView = inflater.inflate(R.layout.fragment_stationary, container, false);
        stationaryGridView = (GridView) stationaryFragmentView.findViewById(R.id.gdStationary);
        mGridAdapter = new gridAdapter(this.getActivity());
        productList = new ArrayList<shopShreyProduct>();
        stationaryGridView.setAdapter(mGridAdapter);
        stationaryGridView.setOnItemClickListener(this);
        context = this;

        books = (Button)stationaryFragmentView.findViewById(R.id.btnBooks);
        books.bringToFront();

        books.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                books.setTextColor(Color.rgb(255,0,0));
                others.setTextColor(Color.rgb(0,0,0));

                setGridViewInvisible(stationaryFragmentView);
                productList.clear();
                serverconnection sc = new serverconnection();
                sc.doGet(shopShreyConstants.booksURL,context);

            }
        });

        others = (Button)stationaryFragmentView.findViewById(R.id.btnOther);
        others.bringToFront();

        others.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                others.setTextColor(Color.rgb(255,0,0));
                books.setTextColor(Color.rgb(0,0,0));

                setGridViewInvisible(stationaryFragmentView);
                productList.clear();
                serverconnection sc = new serverconnection();
                sc.doGet(shopShreyConstants.otherURL,context);

            }
        });







        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setTitle("Stationary");
        LayoutInflater inflator = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflator.inflate(R.layout.stationary_action_bar, null);
        actionBar.setCustomView(v);
        View cv = actionBar.getCustomView();

        ImageView ivNoti = (ImageView)cv.findViewById(R.id.ivNotiStationaryActionBar);
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
        ImageView ivCart =(ImageView)cv.findViewById(R.id.ivCartStationaryActionBar);
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

        books.setTextColor(Color.rgb(255,0,0));
        others.setTextColor(Color.rgb(0,0,0));

        setGridViewInvisible(stationaryFragmentView);
        productList.clear();
        serverconnection sc = new serverconnection();
        sc.doGet(shopShreyConstants.booksURL,context);

        return stationaryFragmentView;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        shopShreyProduct product = productList.get(i);
        Intent myIntent = new Intent(getActivity(), ProductDetailsActivity.class);
        myIntent.putExtra("price", product.getPrice());
        myIntent.putExtra("name", product.getName());
        myIntent.putExtra("sellerName", product.getSellerName());
        myIntent.putExtra("rating", product.getRating());
        myIntent.putExtra("stock",product.getStock());
        shopShreyHelper.selectedProductBmp = product.getImage();
        shopShreyHelper.selectedProductDescription = product.getDescription();
        getActivity().startActivity(myIntent);


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
                    NavigationView navigationView = (NavigationView) getActivity().findViewById(R.id.nav_view);

                    navigationView.getMenu().findItem(R.id.nav_books).setChecked(false);
                    ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
                    actionBar.setDisplayShowCustomEnabled(true);
                    actionBar.setTitle("ShopShrey");
                    // handle back button's click listener

                    return true;
                }
                return false;
            }
        });

    }



    @Override
    public void dataReceived(String data) {
        try {
            JSONObject allProductJson = new JSONObject(data);
            int totalProducts = allProductJson.getInt("totalProducts");
            Log.d("TAG", totalProducts+"");
            JSONArray allProductJsonArray = allProductJson.getJSONArray("products");
            for (int i = 0; i < totalProducts; i++) {
                JSONObject singleProductJson = allProductJsonArray.getJSONObject(i);
                int id = singleProductJson.getInt("id");
                String name = singleProductJson.getString("name");
                String sellerName= singleProductJson.getString("sellerName");
                String price= singleProductJson.getString("price");
                String rating = singleProductJson.getString("rating");
                int stock = singleProductJson.getInt("stock");
                String description = singleProductJson.getString("description");
                String image = singleProductJson.getString("image");
                String size = singleProductJson.getString("size");

                byte[] imageBytes = Base64.decode(image, Base64.DEFAULT);
                Bitmap bmp = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);

                shopShreyProduct product = new shopShreyProduct();
                product.setId(id);
                product.setStock(stock);
                product.setName(name);
                product.setSellerName(sellerName);
                product.setSize(size);
                product.setDescription(description);
                product.setPrice(price);
                product.setRating(rating);
                product.setImage(bmp);
                productList.add(product);
            }

            Log.d("TAG", "product list populated");
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Log.d("TAG", "running on ui thread");
                    setGridViewVisible(stationaryFragmentView);
                    mGridAdapter.notifyDataSetChanged();
                }
            });

        }catch (Exception e){

        }

    }







    @Override
    public void serverResponseTimedout() {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {

                LinearLayout llStationaryError = (LinearLayout) stationaryFragmentView.findViewById(R.id.llStationaryError);

                llStationaryError.setVisibility(View.VISIBLE);
                LinearLayout llGridViewLoading = (LinearLayout) stationaryFragmentView.findViewById(R.id.llStationaryGridViewLoading);
                llGridViewLoading.setVisibility(View.INVISIBLE);
            }
        });
    }

    private void setGridViewInvisible(View view){
        LinearLayout llGridView = (LinearLayout)view.findViewById(R.id.llStationaryGridView);
        LinearLayout llGridViewLoading = (LinearLayout)view.findViewById(R.id.llStationaryGridViewLoading);
        LinearLayout llStationaryError= (LinearLayout)stationaryFragmentView.findViewById(R.id.llStationaryError);

        llStationaryError.setVisibility(View.INVISIBLE);
        llGridView.setVisibility(View.INVISIBLE);
        llGridViewLoading.setVisibility(View.VISIBLE);

        ImageView loading = (ImageView)view.findViewById(R.id.ivGridViewLoading);
        Animation rotate = AnimationUtils.loadAnimation(this.getActivity(),R.anim.rotate_animation);
        loading.startAnimation(rotate);
    }


    private void setGridViewVisible(View view){
        LinearLayout llGridView = (LinearLayout)view.findViewById(R.id.llStationaryGridView);
        LinearLayout llGridViewLoading = (LinearLayout)view.findViewById(R.id.llStationaryGridViewLoading);
        LinearLayout llStationaryError= (LinearLayout)stationaryFragmentView.findViewById(R.id.llStationaryError);

        llStationaryError.setVisibility(View.INVISIBLE);
        llGridView.setVisibility(View.VISIBLE);
        llGridViewLoading.setVisibility(View.INVISIBLE);
    }



    private class gridAdapter extends BaseAdapter {

        Context mContext;
        gridAdapter(Context c){
            mContext = c;
        }

        @Override
        public int getCount() {
            return productList.size();
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
            TextView tvProductName = (TextView)grid.findViewById(R.id.tvProductName);
            TextView tvProductPrice = (TextView)grid.findViewById(R.id.tvProductPrice);
            TextView tvProductRating = (TextView)grid.findViewById(R.id.tvProductRating);

            shopShreyProduct product = productList.get(i);
            tvProductName.setText(product.getName());
            tvProductPrice.setText(product.getPrice()+" INR");
            tvProductRating.setText("Rating: "+product.getRating());

            Bitmap bmp = product.getImage();
            ImageView iv = (ImageView)grid.findViewById(R.id.ivProductSingle);
            iv.setImageBitmap(bmp);

            return grid;
        }
    }
}








