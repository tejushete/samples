package com.example.shopshrey;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import static android.R.attr.id;




public class DataBaseHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "ShopShreyDatabase";
    private static final String TABLE_NAME = "ShopShreyProduct";

    private static final String KEY_NAME = "name";
    private static final String KEY_SellarName ="sellarName";
    private static final String KEY_PRICE = "price";
    private static final String KEY_RATING = "rating";
    private static final String KEY_CATEGORY = "category";
    private static final String KEY_SUBCATEGORY = "subCategory";
    private static final String KEY_IMAGE = "image";
    private static final String KEY_DESCRIPTION = "description";
    private static final String KEY_ID = "id";
    private static final String KEY_STOCK = "stock";
    private static final String KEY_QUANTITY= "quantity";
    private static final String KEY_CARTorWISHLIST= "cartOrWishList";

    public DataBaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + KEY_ID + " INTEGER," + KEY_NAME + " TEXT," + KEY_CATEGORY+" TEXT," + KEY_CARTorWISHLIST +" TEXT," +
                 KEY_DESCRIPTION+" TEXT," + KEY_PRICE +" TEXT," + KEY_QUANTITY + " INTEGER," + KEY_SUBCATEGORY + " TEXT," +
                KEY_SellarName+" TEXT," + KEY_RATING + " TEXT," + KEY_IMAGE +" BLOB," +
                KEY_STOCK+" TEXT, " +
                "PRIMARY KEY("+KEY_ID+", "+KEY_CARTorWISHLIST+")"+");";

        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    public void addShopShreyProduct(shopShreyProduct product, String cartOrWishList){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ID, product.getId());
        values.put(KEY_NAME, product.getName());
        values.put(KEY_CATEGORY, product.getCategory());
        values.put(KEY_CARTorWISHLIST, cartOrWishList);
        values.put(KEY_DESCRIPTION, product.getDescription());
        values.put(KEY_PRICE, product.getPrice());
        values.put(KEY_QUANTITY, product.getQuantity());
        values.put(KEY_SUBCATEGORY, product.getSubCategory());
        values.put(KEY_SellarName, product.getSellerName());
        values.put(KEY_RATING, product.getRating());

        Bitmap bmp = product.getImage();
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG, 100, bos);
        byte[] bArray = bos.toByteArray();
        values.put(KEY_IMAGE, bArray);

        values.put(KEY_STOCK, product.getStock());

        Log.d("<><>", product.getId()+", "+cartOrWishList);
        Log.d("<><>", ""+db.insert(TABLE_NAME, null, values));

        Log.d("<><>", "entry added");
        db.close();
    }

    public shopShreyProduct getShopShreyProduct(int id, String cartOrWishList){
        shopShreyProduct product = null;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_NAME, new String[] { KEY_ID,
                KEY_NAME, KEY_CATEGORY, KEY_DESCRIPTION, KEY_PRICE, KEY_QUANTITY, KEY_SUBCATEGORY, KEY_SellarName,
                        KEY_RATING, KEY_STOCK}, KEY_ID + "=? and "+KEY_CARTorWISHLIST+ "=?",
                new String[] { String.valueOf(id), cartOrWishList }, null, null, null, null);

        if(cursor != null){
            cursor.moveToFirst();
        }

        product = new shopShreyProduct();
        product.setId(id);
        product.setName(cursor.getString(1));
        product.setCategory(cursor.getString(2));
        product.setDescription(cursor.getString(4));
        product.setPrice(cursor.getString(5));
        product.setQuantity(cursor.getInt(6));
        product.setSubCategory(cursor.getString(7));
        product.setSellerName(cursor.getString(8));
        product.setRating(cursor.getString(9));

        byte[] bArray;
        bArray = cursor.getBlob(10);
        Bitmap bmp = BitmapFactory.decodeByteArray(bArray, 0 , bArray.length);
        product.setImage(bmp);

        product.setStock(cursor.getInt(11));
        return product;
    }

    public List<shopShreyProduct>getAllProducts(String cartOrWishList){
        List<shopShreyProduct> productList = new ArrayList<shopShreyProduct>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_NAME + " WHERE "+KEY_CARTorWISHLIST + "= '"+cartOrWishList+"'";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                shopShreyProduct product = new shopShreyProduct();
                product.setId(cursor.getInt(0));
                product.setName(cursor.getString(1));
                product.setCategory(cursor.getString(2));
                product.setDescription(cursor.getString(4));
                product.setPrice(cursor.getString(5));
                Log.d("<><>", product.getDescription());
                Log.d("<><>", product.getPrice());
                product.setQuantity(cursor.getInt(6));
                product.setSubCategory(cursor.getString(7));
                product.setSellerName(cursor.getString(8));
                product.setRating(cursor.getString(9));
                product.setStock(cursor.getInt(11));

                byte[] bArray;
                bArray = cursor.getBlob(10);
                Bitmap bmp = BitmapFactory.decodeByteArray(bArray, 0 , bArray.length);
                product.setImage(bmp);

                productList.add(product);
            } while (cursor.moveToNext());
        }

        db.close();
        // return contact list
        return productList;
    }

    public boolean deleteShopShreyProduct(int id , String cartOrWishlist){

        Boolean ret = true;
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_NAME, KEY_ID + " = ? and "+ KEY_CARTorWISHLIST + " = ?", new String[]{String.valueOf(id), cartOrWishlist});
        db.close();

        return  ret;
    }

    public void updateQuantity(int id , String cartOrWishlist, int quantity){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_QUANTITY , quantity);
        Log.d("<><>", ""+id);
        db.update(TABLE_NAME, values,KEY_ID + " = ? and "+ KEY_CARTorWISHLIST + " = ?", new String[]{String.valueOf(id), cartOrWishlist});
        db.close();




    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

        // Create tables again
        onCreate(db);
    }

}