package com.example.shopshrey;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Size;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class ProductDetailsActivity extends AppCompatActivity {
    List<shopShreyProduct> productList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("");
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        final String name = intent.getStringExtra("name");
        final String sellerName = intent.getStringExtra("sellerName");
        final String rating = intent.getStringExtra("rating");
        final String price = intent.getStringExtra("price");
        final int stock = intent.getIntExtra("stock",0);
        final int id = intent.getIntExtra("id", 0);

      final Bitmap bmp = shopShreyHelper.selectedProductBmp;
        final String description = shopShreyHelper.selectedProductDescription;

        TextView tvName = (TextView)findViewById(R.id.tvProductDetailsName);
        TextView tvRating = (TextView)findViewById(R.id.tvProductDetailsRating);
        TextView tvPrice = (TextView)findViewById(R.id.tvProductDetailsPrice);

        ImageView ivProduct = (ImageView)findViewById(R.id.ivProductDetailsProduct);
        TextView tvDescription = (TextView)findViewById(R.id.tvProductDetailsDescripion);


        tvName.setText(name);
        tvRating.setText(rating);


        tvPrice.setText(price);
        ivProduct.setImageBitmap(bmp);

        final Button btnAddToCart = (Button)findViewById(R.id.btnProductDetailsAddToCart);
        Button btnByeNow = (Button)findViewById(R.id.btnProductDetailsBuyNow);

        if (stock > 0 ) {
            TextView  tvStock=(TextView)findViewById(R.id.tvInStock);
            tvStock.setText("In Stock");
            tvStock.setTextColor(Color.rgb(0,255,0));
        }else
        {
            TextView  tvStock=(TextView)findViewById(R.id.tvInStock);
            tvStock.setText("Out of Stock");
            tvStock.setTextColor(Color.rgb(255,0, 0));
            btnAddToCart.setEnabled(false);
            btnByeNow.setEnabled(false);
        }

        Log.d("TAG", description);

        try {
            JSONObject jsonObject = new JSONObject(description);
            JSONArray jsonArray = jsonObject.getJSONArray("description");
            String desc = "";
            Log.d("TAG", "<><>"+jsonArray.toString()+" length:"+jsonArray.length());
            for(int i = 0; i<jsonArray.length(); i++){
                Log.d("TAG", i+"<><>"+desc);
                desc += "• "+jsonArray.getString(i)+"\n";
            }
            tvDescription.setText(desc);
        }catch (Exception e){
            e.printStackTrace();
        }

        btnAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(shopShreyHelper.cartList == null){
                    shopShreyHelper.cartList = new ArrayList<shopShreyProduct>();
                }
                  Toast.makeText(ProductDetailsActivity.this,"Added To Cart",Toast.LENGTH_SHORT).show();

                Button btn = (Button)view;
                btn.setEnabled(false);

                //please check if already added to cart by adding global boolean variable
                // create shopShreyProduct and all details to the product
                //then add it to the list

                shopShreyProduct product = new shopShreyProduct();
                product.setName(name);
                product.setSize("");
                product.setRating(rating);
                product.setDescription(description);
                product.setPrice(price);
                product.setSellerName(sellerName);
                product.setImage(bmp);
                product.setId(id);

                shopShreyHelper.mDataBaseHandler.addShopShreyProduct(product, "cart");

                Log.d("<><>", "product added");
            }
        });

        btnByeNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }
}