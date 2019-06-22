package com.busSystem.core;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@SuppressWarnings({ "serial", "unused" })
public class UserLoginServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public UserLoginServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		login(request,response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		login(request,response);
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occure
	 */
	public void init() throws ServletException {
		// Put your code here
	}
	
	private void login(HttpServletRequest request,HttpServletResponse response) {
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		HttpSession session = request.getSession(true);
		session.removeAttribute("userName");
		session.removeAttribute("userPassword");
		DBConnection2 db = DBConnection2.getInstance();
		String sql = "";
		String username = request.getParameter("name");
		username = exChange(username);//×Ö·û´®ÂÒÂë×ª»»		
		String password = request.getParameter("password");
		//System.out.println(password);
		password = exChange(password);//×Ö·û´®ÂÒÂë×ª»»
		int count = 0;
		try {
			sql = "select * from userinfo where username = '"+username+"' and userpassword = '"+password+"'";
			//System.out.println(sql);
			conn = db.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next() && rs != null) {
				count++;
			}
			
			if (count > 0) {
				session.setAttribute("userName", username);
				session.setAttribute("userPassword", password);
				response.sendRedirect("query.jsp");
			} else {
				response.sendRedirect("errorUserLogin.jsp");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
				db.freeConnection(conn);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	private String exChange(String str) {
		try {
			str = new String(str.getBytes("ISO8859_1"),"UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return str;
	}
}
