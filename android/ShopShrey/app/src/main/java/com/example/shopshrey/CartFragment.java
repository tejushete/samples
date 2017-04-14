package com.example.shopshrey;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import static android.R.attr.id;

/**
 * A simple {@link Fragment} subclass.
 */
public class CartFragment extends Fragment implements AdapterView.OnItemClickListener {
    ListView listView;
    ListViewCustomAdapter mAdapter;
    List<shopShreyProduct> productList;

    View mCartFragmentView;
    int mCount;
    long mFinalPrice = 0;

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

    public void getFinalPrice(){

        mFinalPrice = 0;
        for(int i = 0; i<shopShreyHelper.cartList.size(); i++){
            shopShreyProduct product = shopShreyHelper.cartList.get(i);
            int q = product.getQuantity();
            int costPer1 = Integer.valueOf(product.getPrice());

            mFinalPrice += (q * costPer1);
        }

        TextView tvFinalPrice = (TextView)mCartFragmentView.findViewById(R.id.tvFinalCartPrice);
        tvFinalPrice.setText(""+mFinalPrice);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cart, container, false);

        mCartFragmentView = view;
        ListView listView = (ListView)view.findViewById(R.id.lvMain);
        if (getActivity()!=null) {
            mAdapter = new CartFragment.ListViewCustomAdapter(this.getActivity());
            listView.setAdapter(mAdapter);
        }

        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setTitle("My Cart");
        LayoutInflater inflator = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflator.inflate(R.layout.cart_action_bar, null);
        actionBar.setCustomView(v);

        shopShreyHelper.cartList = shopShreyHelper.mDataBaseHandler.getAllProducts("cart");
        mCount = shopShreyHelper.cartList == null ? 0: shopShreyHelper.cartList.size();

        if(mCount>0){
            setCartViewVisible(view);
            getFinalPrice();
        }else {
            setNoCartViewVisible(view);
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
                    NavigationView navigationView = (NavigationView) getActivity().findViewById(R.id.nav_view);

                    navigationView.getMenu().findItem(R.id.nav_myCart).setChecked(false);
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

        Spinner spQuantity;
        @Override
        public View getView(int i, View convertView, ViewGroup viewGroup) {
            View list = convertView;
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            shopShreyProduct product = null;
            if (list == null) {
                list = new View(mContext);
                list = inflater.inflate(R.layout.main, null);
            }

            TextView tvProductName = (TextView)list.findViewById(R.id.tvCartProductName);
            TextView tvSellerName = (TextView)list.findViewById(R.id.tvCartSellarName) ;
            TextView tvProductPrice = (TextView)list.findViewById(R.id.tvCartProductPrice);
            TextView tvProductRating = (TextView)list.findViewById(R.id.tvCartProductRating);

            product = shopShreyHelper.cartList.get(i);
            spQuantity = (Spinner)list.findViewById(R.id.spQuantity);
            spQuantity.setSelection(product.getQuantity()-1);
            spQuantity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    View parentRow = (View) view.getParent().getParent().getParent();
                    ListView listView = (ListView) parentRow.getParent().getParent();
                    int iindex = listView.getPositionForView(parentRow);
                   shopShreyProduct product = shopShreyHelper.cartList.get(iindex);
                    product.setQuantity(i+1);
                    shopShreyHelper.mDataBaseHandler.updateQuantity(product.getId(),"cart",product.getQuantity());
                    Log.d("<><>", "product updated");
                    //update quantity in database also with id and cartOrWishList sw primary key
                    mAdapter.notifyDataSetChanged();
                }



                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });



            tvProductName.setText(product.getName());
            tvProductPrice.setText((Integer.valueOf(product.getPrice())*product.getQuantity())+" INR");
            tvProductRating.setText("Rating: "+product.getRating());
            tvSellerName.setText("Seller: "+product.getSellerName());

            Bitmap bmp = product.getImage();
            ImageView iv = (ImageView)list.findViewById(R.id.ivCartImage);
            iv.setImageBitmap(bmp);

            Button btnRemove = (Button)list.findViewById(R.id.btnCartItemRemove);
            btnRemove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    View parentRow = (View) view.getParent().getParent();
                    ListView listView = (ListView) parentRow.getParent();
                    int index = listView.getPositionForView(parentRow);
                    shopShreyProduct product = shopShreyHelper.cartList.get(index);
                    shopShreyHelper.mDataBaseHandler.deleteShopShreyProduct(product.getId(),"cart");
                    shopShreyHelper.cartList.remove(index);
                    mCount = shopShreyHelper.cartList.size();
                    Log.d("<><>", "product deleted");
                    //delete from database
                    //if mCount is zero make listview invisible and show empty cart view
                    mAdapter.notifyDataSetChanged();
                }
            });

            Button btnMoveToWishList = (Button)list.findViewById(R.id.btnMoveToWishList);
            btnMoveToWishList.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(shopShreyHelper.wishList == null){
                        shopShreyHelper.wishList = new ArrayList<shopShreyProduct>();
                    }
                    Toast.makeText(getActivity(), "Moved product to wish list", Toast.LENGTH_SHORT);

                    Button btn = (Button)view;
                    btn.setEnabled(false);

                    View parentRow = (View) view.getParent().getParent();
                    ListView listView = (ListView) parentRow.getParent();
                    int index = listView.getPositionForView(parentRow);
                    shopShreyProduct product = shopShreyHelper.cartList.get(index);
                    shopShreyHelper.mDataBaseHandler.addShopShreyProduct(product, "wish");
                }
            });

            getFinalPrice();
            return list;
        }
    }
}
