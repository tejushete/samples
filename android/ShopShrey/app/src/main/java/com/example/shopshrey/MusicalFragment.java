package com.example.shopshrey;


import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
public class MusicalFragment extends Fragment implements AdapterView.OnItemClickListener,serverResponse{
    List<shopShreyProduct> productList;
    View MusicalFragmentView;
    serverResponse context;
    GridView fashionGridView;
    gridAdapter mGridAdapter;


    public MusicalFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        MusicalFragmentView= inflater.inflate(R.layout.fragment_musical, container, false);
        productList = new ArrayList<shopShreyProduct>();
        GridView musicalGridView = (GridView) MusicalFragmentView.findViewById(R.id.gdMusical);
        mGridAdapter=new gridAdapter( this.getActivity());
        musicalGridView.setAdapter(mGridAdapter);
        musicalGridView.setOnItemClickListener(this);

        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setTitle("Musical Instrument");
        LayoutInflater inflator = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflator.inflate(R.layout.musical_action_bar, null);
        actionBar.setCustomView(v);
        View cv = actionBar.getCustomView();

        ImageView ivNoti = (ImageView)cv.findViewById(R.id.ivNotiMusicalActionBar);
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
        ImageView ivCart =(ImageView)cv.findViewById(R.id.ivCartMusicalActionBar);
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

        //set musical grid view invisible and show loading view till response from server is recevied
        setGridViewInvisible(MusicalFragmentView);

        context = this;
        serverconnection sc = new serverconnection();
        sc.doGet(shopShreyConstants.musicalURL, context);

        return MusicalFragmentView;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        shopShreyProduct product = productList.get(i);
        Intent myIntent = new Intent(getActivity(),ProductDetailsActivity.class);
        myIntent.putExtra("price",product.getPrice());
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

                    navigationView.getMenu().findItem(R.id.nav_music).setChecked(false);
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

        Log.d("TAG", "data received: "+data);
        try {
            JSONObject allProductJson = new JSONObject(data);
            int totalProducts = allProductJson.getInt("totalProducts");
            JSONArray allProductJsonArray = allProductJson.getJSONArray("products");

            for (int i = 0; i < totalProducts; i++) {
                JSONObject singleProductJson = allProductJsonArray.getJSONObject(i);
                int id = singleProductJson.getInt("id");
                String name = singleProductJson.getString("name");
                String SellarName = singleProductJson.getString("sellerName");
                String price = singleProductJson.getString("price");
                String rating = singleProductJson.getString("rating");
                int stock = singleProductJson.getInt("stock");
                String description = singleProductJson.getString("description");
                String image = singleProductJson.getString("image");
                String size = singleProductJson.getString("size");
                byte[] imageBytes = Base64.decode(image, Base64.DEFAULT);
                Bitmap bmp = BitmapFactory.decodeByteArray(imageBytes,0,imageBytes.length);


                shopShreyProduct product = new shopShreyProduct();
                product.setId(id);
                product.setName(name);
                product.setSellerName(SellarName);
                product.setPrice(price);
                product.setRating(rating);
                product.setStock(stock);
                product.setDescription(description);
                product.setImage(bmp);
                product.setSize(size);
                productList.add(product);
            }

            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    setGridViewVisible(MusicalFragmentView);
                    mGridAdapter.notifyDataSetChanged();
                }
            });

        }catch(Exception e){
            Log.d("TAG", "exception received");
        }

    }

    @Override
    public void serverResponseTimedout() {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                LinearLayout llMusicalError = (LinearLayout) MusicalFragmentView.findViewById(R.id.llMusicalError);
                llMusicalError.setVisibility(View.VISIBLE);
                LinearLayout llGridViewLoading = (LinearLayout) MusicalFragmentView.findViewById(R.id.llMusicalGridViewLoading);
                llGridViewLoading.setVisibility(View.INVISIBLE);
            }
        });
    }

       private void setGridViewInvisible(View view){

           LinearLayout llMusicalGridView = (LinearLayout)view.findViewById(R.id.llMusicalGridView);
           LinearLayout llMusicalGridViewLoading = (LinearLayout)view.findViewById(R.id.llMusicalGridViewLoading);
           LinearLayout llMusicalError = (LinearLayout)MusicalFragmentView.findViewById(R.id.llMusicalError);

           llMusicalError.setVisibility(View.INVISIBLE);
           llMusicalGridView.setVisibility(view.INVISIBLE);
           llMusicalGridViewLoading.setVisibility(view.VISIBLE);

           ImageView ivGridViewLoading = (ImageView)view.findViewById(R.id.ivMusicalGridViewLoading);
           Animation rotate = AnimationUtils.loadAnimation(this.getActivity(),R.anim.rotate_animation);
           ivGridViewLoading.startAnimation(rotate);
       }

    private void setGridViewVisible(View view){

        LinearLayout llMusicalGridView = (LinearLayout)view.findViewById(R.id.llMusicalGridView);
        LinearLayout llMusicalGridViewLoading = (LinearLayout)view.findViewById(R.id.llMusicalGridViewLoading);
        LinearLayout llMusicalError = (LinearLayout)MusicalFragmentView.findViewById(R.id.llMusicalError);

        llMusicalError.setVisibility(View.INVISIBLE);
        llMusicalGridView.setVisibility(view.VISIBLE);
        llMusicalGridViewLoading.setVisibility(view.INVISIBLE);

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












