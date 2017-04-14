package com.example.shopshrey;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

/**
 * Servlet implementation class Account
 */
@WebServlet("/Account")
public class Account extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Account() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		String type = request.getParameter("type");

		System.out.println(type);

		if(type.equals("login")== true){
			String user = request.getParameter("user");
			String password = request.getParameter("pwd");
		}
		else if(type.equals("doesUserExist")== true){
			String user = request.getParameter("user");
			dataBaseHandler dh = new dataBaseHandler();
			int resErrorCode = 0;
			String resErrorMsg = "no";
			boolean ret = true;
			
			if(ret == false){
				resErrorCode = 1;
				resErrorMsg = "User is already exist.";
			}
			String resJson = "";
			JSONObject json = new JSONObject();
			json.put("result", resErrorCode);
			json.put("error", resErrorMsg);
			resJson = json.toString();
			response.getWriter().append(resJson);

		}else if(type.equals("signUp")== true){
			String user = request.getParameter("user");
			String password = request.getParameter("pwd");

			dataBaseHandler dh = new dataBaseHandler();
			int resErrorCode = 0;
			String resErrorMsg = "successfully signed up";
			boolean ret = dh.addAccount(user, password);
			if(ret == false){
				resErrorCode = 1;
				resErrorMsg = "Error in sign up.";
			}
			String resJson = "";
			JSONObject json = new JSONObject();
			json.put("result", resErrorCode);
			json.put("error", resErrorMsg);
			resJson = json.toString();
			response.getWriter().append(resJson);

		}else if(type.equals("resetPwd")== true){
			String user = request.getParameter("user");
			String password = request.getParameter("pwd");
		}
	}
	public void init(ServletConfig config) throws ServletException {

		dataBaseHandler dh = new dataBaseHandler();
		dh.createAccountsTable();
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
