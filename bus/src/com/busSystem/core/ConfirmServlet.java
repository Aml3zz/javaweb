package com.busSystem.core;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@SuppressWarnings("serial")
public class ConfirmServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public ConfirmServlet() {
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
		HttpSession session = request.getSession(true);//如果用户session为空，重新分配一个新的session给用户
		DBConnection2 db = DBConnection2.getInstance();
		String adminname = request.getParameter("adminname") == null ? "" : request.getParameter("adminname");
		adminname = exChange(adminname);//字符串乱码处理
		String password = request.getParameter("password") == null ? "" : request.getParameter("password");
		password = exChange(password);//字符串乱码处理
		String sql = "";
		String rand = (String) session.getAttribute("syscode");
		String input = request.getParameter("vaild");
		int n = 0;
		try {
			conn = db.getConnection();
			stmt = conn.createStatement();
			sql = "select * from administrator where adminiID = '"+adminname+"' and adminipassword = '"+password+"'";
			rs = stmt.executeQuery(sql);
			if (rs.next() && rs != null) {
				n++;
			}
			if (n > 0 && input.equals(rand)) {
				session.setAttribute("adminname", adminname);
				session.setAttribute("adminpassword", password);
				session.removeAttribute("rand");
				response.sendRedirect("loginSuccess.jsp");
			} else {
				response.sendRedirect("error.jsp");
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
