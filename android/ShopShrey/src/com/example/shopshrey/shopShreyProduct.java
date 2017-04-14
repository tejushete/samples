package com.example.shopshrey;

public class shopShreyProduct {
	String mCategory;
	String mSubCategory;
	int mId;
	String mDescription_json;
	String mSize_json;
	String mRating;
	String mName;
	String mImage;
	String mPrice;
	int mStock;
	String mSellerName;
	
	public shopShreyProduct(){}
	
	public shopShreyProduct(String category, String subCategory, int id, String descr, String size, String rating, String name, String image,
			int stock, String price, String sellerName) {
		// TODO Auto-generated constructor stub
		mCategory = category;
		mSubCategory = subCategory;
		mId = id;
		mDescription_json = descr;
		mSize_json = size;
		mRating = rating;
		mName = name;
		mImage = image;
		mPrice = price;
		mStock = stock;
		mSellerName = sellerName;
	}
	
	public String getCategory(){
		return mCategory;
	}

	public String getSubCategory(){
		return mSubCategory;
	}

	public int getId(){
		return mId;
	}

	public String getDescription(){
		return mDescription_json;
	}

	public String getSize(){
		return mSize_json;
	}

	public String getRating(){
		return mRating;
	}
	
	public String getName(){
		return mName;
	}
	
	public String getImage(){
		return mImage;
	}
	
	public String getPrice(){
		return mPrice;
	}
	
	public String getSellerName(){
		return mSellerName;
	}
	
	public int getStock(){
		return mStock;
	}
	
}
