package cn.edu.swu;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ProcessStep2Servlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//1. 获取请求参数: name, address, cardType, card
		String name = request.getParameter("name");
		String address = request.getParameter("address");
		String payType = request.getParameter("payType");
		String phone = request.getParameter("phone");
		
		//Customer customer = new Customer(name, address, payType, phone);
		Customer customer = new Customer(name,address,payType,phone);	
	
		
		//2. 把请求信息存入到 HttpSession 中
		
		HttpSession session = request.getSession();
		session.setAttribute("customer", customer);
		
		//3. 重定向页面到 confirm.jsp
		response.sendRedirect(request.getContextPath() + "/shoppingcar/confirm.jsp");
	}

}
