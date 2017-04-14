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
public class FurnitureFragment extends Fragment implements AdapterView.OnItemClickListener,serverResponse{
    List<shopShreyProduct>productList;
    Button homeDecor,kitchenAndDining,homeFurnishing,toolsAndHardware,homeClean;
    serverResponse context;
    View furnitureFragmentView;
    GridView furnitureGridView;
    gridAdapter mGridAdapter;


    public FurnitureFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        furnitureFragmentView = inflater.inflate(R.layout.fragment_furniture, container, false);
        furnitureGridView = (GridView) furnitureFragmentView.findViewById(R.id.gdFurniture);
        mGridAdapter = new gridAdapter(this.getActivity());
        productList = new ArrayList<shopShreyProduct>();
        furnitureGridView.setAdapter(mGridAdapter);
        furnitureGridView.setOnItemClickListener(this);
        context = this;

        homeDecor = (Button)furnitureFragmentView.findViewById(R.id.btnHomeDecor);
        homeDecor.bringToFront();

        homeDecor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                homeDecor.setTextColor(Color.rgb(255,0,0));
                homeClean.setTextColor(Color.rgb(0,0,0));
                kitchenAndDining.setTextColor(Color.rgb(0,0,0));
                homeFurnishing.setTextColor(Color.rgb(0,0,0));
                toolsAndHardware.setTextColor(Color.rgb(0,0,0));
                setGridViewInvisible(furnitureFragmentView);
                productList.clear();
                serverconnection sc = new serverconnection();
                sc.doGet(shopShreyConstants.homeDecorURL,context);

            }
        });

        homeClean = (Button)furnitureFragmentView.findViewById(R.id.btnHomeClean);
        homeClean.bringToFront();

        homeClean.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                homeClean.setTextColor(Color.rgb(255,0,0));
                homeDecor.setTextColor(Color.rgb(0,0,0));
                kitchenAndDining.setTextColor(Color.rgb(0,0,0));
                homeFurnishing.setTextColor(Color.rgb(0,0,0));
                toolsAndHardware.setTextColor(Color.rgb(0,0,0));
                setGridViewInvisible(furnitureFragmentView);
                productList.clear();
                serverconnection sc = new serverconnection();
                sc.doGet(shopShreyConstants.homeCleanURL,context);

            }
        });

        homeFurnishing = (Button)furnitureFragmentView.findViewById(R.id.btnfurniture);
        homeFurnishing.bringToFront();

        homeFurnishing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                homeFurnishing.setTextColor(Color.rgb(255,0,0));
                homeClean.setTextColor(Color.rgb(0,0,0));
                kitchenAndDining.setTextColor(Color.rgb(0,0,0));
                homeDecor.setTextColor(Color.rgb(0,0,0));
                toolsAndHardware.setTextColor(Color.rgb(0,0,0));
                setGridViewInvisible(furnitureFragmentView);
                productList.clear();
                serverconnection sc = new serverconnection();
                sc.doGet(shopShreyConstants.homeFurnishingURL,context);

            }
        });


        kitchenAndDining = (Button)furnitureFragmentView.findViewById(R.id.btnKitchen);
        kitchenAndDining.bringToFront();

        kitchenAndDining.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                kitchenAndDining.setTextColor(Color.rgb(255,0,0));
                homeClean.setTextColor(Color.rgb(0,0,0));
                homeDecor.setTextColor(Color.rgb(0,0,0));
                homeFurnishing.setTextColor(Color.rgb(0,0,0));
                toolsAndHardware.setTextColor(Color.rgb(0,0,0));
                setGridViewInvisible(furnitureFragmentView);
                productList.clear();
                serverconnection sc = new serverconnection();
                sc.doGet(shopShreyConstants.kitchenAndDiningURL,context);

            }
        });

        toolsAndHardware = (Button)furnitureFragmentView.findViewById(R.id.btnTools);
        toolsAndHardware.bringToFront();

        toolsAndHardware.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toolsAndHardware.setTextColor(Color.rgb(255,0,0));
                homeClean.setTextColor(Color.rgb(0,0,0));
                kitchenAndDining.setTextColor(Color.rgb(0,0,0));
                homeFurnishing.setTextColor(Color.rgb(0,0,0));
                homeDecor.setTextColor(Color.rgb(0,0,0));
                setGridViewInvisible(furnitureFragmentView);
                productList.clear();
                serverconnection sc = new serverconnection();
                sc.doGet(shopShreyConstants.toolsAndHardwareURL,context);

            }
        });






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

        homeDecor.setTextColor(Color.rgb(255,0,0));
        homeClean.setTextColor(Color.rgb(0,0,0));
        kitchenAndDining.setTextColor(Color.rgb(0,0,0));
        homeFurnishing.setTextColor(Color.rgb(0,0,0));
        toolsAndHardware.setTextColor(Color.rgb(0,0,0));
        setGridViewInvisible(furnitureFragmentView);
        productList.clear();
        serverconnection sc = new serverconnection();
        sc.doGet(shopShreyConstants.homeDecorURL,context);

        return furnitureFragmentView;
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

                    navigationView.getMenu().findItem(R.id.nav_home).setChecked(false);
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
                    setGridViewVisible(furnitureFragmentView);
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
                LinearLayout llFurnitureError = (LinearLayout) furnitureFragmentView.findViewById(R.id.llFurnitureError);

                llFurnitureError.setVisibility(View.VISIBLE);
                LinearLayout llGridViewLoading = (LinearLayout) furnitureFragmentView.findViewById(R.id.llFurnitureGridViewLoading);
                llGridViewLoading.setVisibility(View.INVISIBLE);
            }
        });

    }
    private void setGridViewInvisible(View view){
        LinearLayout llGridView = (LinearLayout)view.findViewById(R.id.llFurnitureGridView);
        LinearLayout llGridViewLoading = (LinearLayout)view.findViewById(R.id.llFurnitureGridViewLoading);
        LinearLayout llFurnitureError = (LinearLayout)furnitureFragmentView.findViewById(R.id.llFurnitureError);

        llFurnitureError.setVisibility(View.INVISIBLE);
        llGridView.setVisibility(View.INVISIBLE);
        llGridViewLoading.setVisibility(View.VISIBLE);

        ImageView loading = (ImageView)view.findViewById(R.id.ivGridViewLoading);
        Animation rotate = AnimationUtils.loadAnimation(this.getActivity(),R.anim.rotate_animation);
        loading.startAnimation(rotate);
    }


    private void setGridViewVisible(View view){
        LinearLayout llGridView = (LinearLayout)view.findViewById(R.id.llFurnitureGridView);
        LinearLayout llGridViewLoading = (LinearLayout)view.findViewById(R.id.llFurnitureGridViewLoading);
        LinearLayout llFurnitureError = (LinearLayout)furnitureFragmentView.findViewById(R.id.llFurnitureError);

        llFurnitureError.setVisibility(View.INVISIBLE);
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




