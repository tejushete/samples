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
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
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
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class FashionFragment extends Fragment implements AdapterView.OnItemClickListener,serverResponse{

   List<shopShreyProduct>productList;
    View fashionFragmentView;
    Button men, women;
    serverResponse context;
    GridView fashionGridView;
    gridAdapter mGridAdapter;




    @Override
    public void dataReceived(String data) {


        Log.d("TAG", "Response received data:"+data);

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
                    setGridViewVisible(fashionFragmentView);
                    mGridAdapter.notifyDataSetChanged();
                }
            });

        }catch (Exception e){

        }
    }


    @Override
    public void onResume() {
        super.onResume();

        getView().setFocusableInTouchMode(true);
        getView().requestFocus();

        NavigationView navigationView;
        navigationView = (NavigationView)(getActivity()).findViewById(R.id.nav_view);
        navigationView.getMenu().findItem(R.id.nav_account).setChecked(false);
        navigationView.getMenu().findItem(R.id.nav_fashion).setChecked(true);
        DrawerLayout drawer = (DrawerLayout) getActivity().findViewById(R.id.drawer_layout);
     //   drawer.openDrawer(GravityCompat.START);

        getView().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                Log.d("<><>", keyCode+" "+ event.getAction());

                if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK) {
                    ShopFragment shopFragment = new ShopFragment();
                    android.support.v4.app.FragmentTransaction fragmentTransaction =
                            getActivity().getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.content_main, shopFragment).commit();
                    NavigationView navigationView = (NavigationView) getActivity().findViewById(R.id.nav_view);

                    navigationView.getMenu().findItem(R.id.nav_fashion).setChecked(false);
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
    public void serverResponseTimedout() {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                LinearLayout llFashionError = (LinearLayout) fashionFragmentView.findViewById(R.id.llFashionError);
                llFashionError.setVisibility(View.VISIBLE);
                LinearLayout llGridViewLoading = (LinearLayout) fashionFragmentView.findViewById(R.id.llFashionGridViewLoading);
                llGridViewLoading.setVisibility(View.INVISIBLE);
            }
        });
    }

    private void setGridViewInvisible(View view){
        LinearLayout llGridView = (LinearLayout)view.findViewById(R.id.llFashionGridView);
        LinearLayout llGridViewLoading = (LinearLayout)view.findViewById(R.id.llFashionGridViewLoading);
        LinearLayout llFashionError = (LinearLayout)fashionFragmentView.findViewById(R.id.llFashionError);

        llFashionError.setVisibility(View.INVISIBLE);

        llGridView.setVisibility(View.INVISIBLE);

        llGridViewLoading.setVisibility(View.VISIBLE);

        ImageView loading = (ImageView)view.findViewById(R.id.ivGridViewLoading);
        Animation rotate = AnimationUtils.loadAnimation(this.getActivity(),R.anim.rotate_animation);
        loading.startAnimation(rotate);
    }


    private void setGridViewVisible(View view){
        LinearLayout llGridView = (LinearLayout)view.findViewById(R.id.llFashionGridView);
        LinearLayout llGridViewLoading = (LinearLayout)view.findViewById(R.id.llFashionGridViewLoading);
        LinearLayout llFashionError = (LinearLayout)fashionFragmentView.findViewById(R.id.llFashionError);

        llFashionError.setVisibility(View.INVISIBLE);

        llGridView.setVisibility(View.VISIBLE);
        llGridViewLoading.setVisibility(View.INVISIBLE);
    }

    public FashionFragment() {
        // Required empty public constructor

    }

    HttpURLConnection urlConnection;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fashionFragmentView = inflater.inflate(R.layout.fragment_fashion, container, false);

        productList = new ArrayList<shopShreyProduct>();

        fashionGridView = (GridView) fashionFragmentView.findViewById(R.id.gdFashion);
        mGridAdapter = new gridAdapter(this.getActivity());
        fashionGridView.setAdapter(mGridAdapter);
        fashionGridView.setOnItemClickListener(this);
        fashionGridView.bringToFront();



        men = (Button)fashionFragmentView.findViewById(R.id.btnMenFashion);
        women = (Button)fashionFragmentView.findViewById(R.id.btnWomenFashion);

        men.bringToFront();
        men.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                men.setTextColor(Color.rgb(255,0,0));
                women.setTextColor(Color.rgb(0,0,0));
                setGridViewInvisible(fashionFragmentView);
                productList.clear();

                serverconnection sc = new serverconnection();
                sc.doGet(shopShreyConstants.menURL, context);
            }
        });
        women.bringToFront();
        women.setTextColor(Color.rgb(255,0,0));
        women.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                women.setTextColor(Color.rgb(255,0,0));
                men.setTextColor(Color.rgb(0,0,0));
                setGridViewInvisible(fashionFragmentView);
                productList.clear();

                serverconnection sc = new serverconnection();
                sc.doGet(shopShreyConstants.womenURL, context);
            }
        });

        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setTitle("Fashion");
        LayoutInflater inflator = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflator.inflate(R.layout.fashion_action_bar, null);
        actionBar.setCustomView(v);

        View cv = actionBar.getCustomView();
        ImageView ivNoti = (ImageView)cv.findViewById(R.id.ivNotiFashionActionBar);
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
        ImageView ivCart =(ImageView)cv.findViewById(R.id.ivCartFashionActionBar);
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

        setGridViewInvisible(fashionFragmentView);
        serverconnection sc = new serverconnection();
        context = this;
        sc.doGet(shopShreyConstants.womenURL, context);
        return fashionFragmentView;
    }





    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        shopShreyProduct product = productList.get(i);
        Intent myIntent = new Intent(getActivity(), ProductDetailsActivity.class);
        myIntent.putExtra("price", product.getPrice());
        myIntent.putExtra("name", product.getName());
        myIntent.putExtra("sellerName", product.getSellerName());
        myIntent.putExtra("rating", product.getRating());
        Log.d("<<>", "stock:"+product.getStock());
        myIntent.putExtra("stock",product.getStock());
        myIntent.putExtra("id", product.getId());
        shopShreyHelper.selectedProductBmp = product.getImage();
        shopShreyHelper.selectedProductDescription = product.getDescription();
        getActivity().startActivity(myIntent);
    }

    private class gridAdapter extends BaseAdapter{

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
        public boolean areAllItemsEnabled() {
            return true;
        }

        @Override
        public boolean isEnabled(int position) {
            return true;
        }

        @Override
        public View getView(int i, View convertView, ViewGroup viewGroup) {
            View grid = convertView;
            LayoutInflater inflater = (LayoutInflater) mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            if (grid == null) {
                grid = new View(mContext);
                grid = inflater.inflate(R.layout.product_grid_view_single, null);
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
