package com.example.shopshrey;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class dataBaseHandler {

	public void createAccountsTable(){
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			Connection conn = DriverManager.getConnection("jdbc:sqlite:F:\\Android/AndroidStudioProjects/ShopShrey/shopShrey.db");
			Statement stat = conn.createStatement();
			String sql = "CREATE TABLE ShopShreyAccounts" +
					"(id INTEGER AUTO_INCREMENT," +
					" MobileNo VARCHAR(255), " + 
					" Password VARCHAR(255)" + 
					")"; 

			stat.executeUpdate(sql);
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public boolean addAccount(String mobileNo, String password){
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {
			Connection conn = DriverManager.getConnection("jdbc:sqlite:F:\\Android/AndroidStudioProjects/ShopShrey/shopShrey.db");
			Statement stat = conn.createStatement();
			String insertQuery = "INSERT INTO ShopShreyAccounts (MobileNo, Password)VALUES (\'"+mobileNo+"\', \'"+password+"\')";
			stat.executeUpdate(insertQuery);
			
		}
		catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean doesUserExist(String mobileNo){
		boolean ret = false;
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {
			Connection conn = DriverManager.getConnection("jdbc:sqlite:F:\\Android/AndroidStudioProjects/ShopShrey/shopShrey.db");
			Statement stat = conn.createStatement();
			
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ret;
	}

	public String getPassword(String mobileNo){
		String pwd = "";
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			Connection conn = DriverManager.getConnection("jdbc:sqlite:F:\\Android/AndroidStudioProjects/ShopShrey/shopShrey.db");
			Statement stat = conn.createStatement();
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pwd;
	}

	public void createOrdersTable(){
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			Connection conn = DriverManager.getConnection("jdbc:sqlite:F:\\Android/AndroidStudioProjects/ShopShrey/shopShrey.db");
			Statement stat = conn.createStatement();
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public List<shopShreyProduct> getOrderDetails(){
		List <shopShreyProduct> products = new ArrayList<shopShreyProduct>();
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			Connection conn = DriverManager.getConnection("jdbc:sqlite:F:\\Android/AndroidStudioProjects/ShopShrey/shopShrey.db");
			Statement stat = conn.createStatement();
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return products;
	}

	public boolean addPurchasedProductToTheList(shopShreyProduct product){
		boolean ret = false;
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			Connection conn = DriverManager.getConnection("jdbc:sqlite:F:\\Android/AndroidStudioProjects/ShopShrey/shopShrey.db");
			Statement stat = conn.createStatement();
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ret;
	}

}
