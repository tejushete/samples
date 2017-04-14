package com.example.shopshrey;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.DatatypeConverter;

import com.sun.xml.internal.messaging.saaj.util.Base64;

import jdk.nashorn.internal.parser.JSONParser;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import sun.misc.BASE64Encoder;
import sun.misc.IOUtils;

/**
 * Servlet implementation class test
 */
@WebServlet("/test")
public class test extends HttpServlet {
	private static final long serialVersionUID = 1L;
	List<shopShreyProduct> productsList;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public test() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub

		productsList = new ArrayList<shopShreyProduct>();

		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			Connection conn = DriverManager.getConnection("jdbc:sqlite:F:\\Android/AndroidStudioProjects/ShopShrey/shopShrey.db");
			Statement stat = conn.createStatement();
			ResultSet rs = stat.executeQuery("select * from products;");
			while(rs.next()){
				String name = rs.getString("Name");
				String sellerName = rs.getString("Seller Name");
				String price = rs.getString("Price");
				String rating = rs.getString("Rating");
				String category = rs.getString("Category");
				String subCategory = rs.getString("Subcategory");
				String size = rs.getString("Size");
				int stock = rs.getInt("Stock");
				String description = rs.getString("Description");
				int id = rs.getInt("Id");
				String image = "";
				byte[] img = rs.getBytes("Image");
				String imageBase64 = DatatypeConverter.printBase64Binary(img);
				shopShreyProduct product = new shopShreyProduct(category, subCategory, id, 
						description, size, rating, name, imageBase64, stock, price, sellerName);
				productsList.add(product);
			}
			conn.close();																					rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		String res = "";
		
		String reqCategory = request.getParameter("category");
		String reqSubCategory = request.getParameter("subCategory");
		
		System.out.println("Category:"+reqCategory + ", subCategory:" + reqSubCategory);
		
		JSONObject object = new JSONObject();
		int items = productsList.size();
		JSONArray productArray = new JSONArray();
		int itemsAdded = 0;
		
		for(int i = 0; i<items; i++){
			shopShreyProduct product = productsList.get(i);
			
			if(!(reqCategory.equals(product.getCategory()) && reqSubCategory.equals(product.getSubCategory()))){
				continue;
			}
			JSONObject jsonProduct = new JSONObject();
			jsonProduct.put("name", product.getName());
			jsonProduct.put("id", product.getId());
			String description = product.getDescription();
			jsonProduct.put("description", description);
			jsonProduct.put("rating", product.getRating());
			jsonProduct.put("sellerName", product.getSellerName());
			jsonProduct.put("stock", product.getStock());
			jsonProduct.put("size", product.getSize());
			jsonProduct.put("price", product.getPrice());

			String image = product.getImage();
			jsonProduct.put("image", image);
			jsonProduct.put("cateogory", product.getCategory());
			jsonProduct.put("subCategory", product.getSubCategory());
			productArray.add(itemsAdded, jsonProduct);
			itemsAdded++;
		}

		object.put("products", productArray);
		object.put("totalProducts", itemsAdded);
		res = object.toString();

		response.getWriter().append(res);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
