package cn.edu.swu;


import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ProcessStep1Servlet extends HttpServlet {
	
	
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 获取选中的图书的信息
		String [] books = request.getParameterValues("book");
		//2. 把图书信息放入到 HttpSession 中
		request.getSession().setAttribute("books", books);
		
		//2. 重定向页面到 shoppingcart/step-2.jsp
		System.out.println(request.getContextPath() + "/shoppingcar/step-2.jsp");
		response.sendRedirect(request.getContextPath() + "/shoppingcar/step-2.jsp");
	}

}
