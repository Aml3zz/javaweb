package cn.edu.swu.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.swu.mvcapp.dao.CriteriaCustomer;
import cn.edu.swu.mvcapp.dao.CustomerDAO;
import cn.edu.swu.mvcapp.dao.impl.CustomerDAOJdbcImpl;
import cn.edu.swu.mvcapp.domain.Customer;



/**
 * Servlet implementation class CustomerServlet
 */
public class CustomerServlet extends HttpServlet {
	
	private CustomerDAO customerDAO = new CustomerDAOJdbcImpl();
	
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
/*
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String methodString = request.getParameter("method");
		
		switch (methodString) {
			case "add": add(request,response); break;
			case "query": query(request,response); break;
			case "delete": delete(request,response); break;
			default:
				break;
		}
	}

*/


	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String servletPathString = req.getServletPath();
		//System.out.println(servletPathString);
		String methodNameString = servletPathString.substring(1);
		methodNameString = methodNameString.substring(0, methodNameString.length()-3);
		System.out.println(methodNameString);
		try {
			//利用反射获取methodname对应的方法
			Method method = getClass().getDeclaredMethod(methodNameString, HttpServletRequest.class,HttpServletResponse.class);
			
			//利用反射调用对应的方法：调用edit或add
			method.invoke(this, req,resp);
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	
	
	private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//System.out.println("delete");
		String idString = request.getParameter("id");
		int id = 0;
		
		//防止idstring不能转为int类型，若不能转，则id=0，无法进行任何的删除操作。
		try {
			id = Integer.parseInt(idString);
			customerDAO.delete(id);
		} catch (Exception e) {
				
		}
		
		response.sendRedirect("query.do");
	}

	private void query(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//System.out.println("query");
			String nameString = request.getParameter("name");
			String phoneString = request.getParameter("phone");
			String addrString = request.getParameter("address");
			
			CriteriaCustomer cc = new CriteriaCustomer(nameString, addrString, phoneString);
		
			List<Customer> customers = customerDAO.getForListWithCriteriaCustomer(cc);
			request.setAttribute("customers",customers);
			request.getRequestDispatcher("/index.jsp").forward(request, response);
	
	}
	
	private void update(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException  {
		System.out.println("update");
		//1.获取表单参数: id ,name ,address ,phone , oldname
		String idString = request.getParameter("id");
		String nameString = request.getParameter("name");
		String address = request.getParameter("address");
		String phoneString = request.getParameter("phone");
		String oldName = request.getParameter("oldName");
		//2.校验name 是否已经被占用；
		
		//2.1比较name和oldname是否相同，若相同说明name可用
		//2.1若不相同，则调用customerDAO的getCountwithname获取name在数据库中是否存在
		if(!oldName.equalsIgnoreCase(nameString)) {
			long count = customerDAO.getCountWithName(nameString);
			//2.2若返回值大于0，则响应updatecustomer.jsp:
			if(count > 0) {
				//2.2.2在newcustomer.jsp显示一个错误消息：用户名被占用，请重新选择
				//在request中放入一个属性message：用户名被占用，请重新选择
				//在页面上通过request.getAttribute("message")的方式来显示
				request.setAttribute("message","用户名" +  nameString + "已经被占用，请重新选择！");
				
				//2.2.2在updatecustomer.jsp的表单值可以回显value="<%= request.getParameter("name") == null ? "": request.getParameter("name")%>"
				//address,phone显示提交表单的新值，而name 显示oldname,而不是新提交的name
				
				//2.2.3结束方法：return
					request.getRequestDispatcher("/updatecustomer.jsp").forward(request, response);
					return;
			}
		}

		//3.若验证通过把表单参数封装为一个Customer对象customer
		Customer customer = new Customer(nameString,address,phoneString);
		customer.setId(Integer.parseInt(idString));
		
		//4.调用customerDAO的save方法
		customerDAO.update(customer);
		
		//5.重定向到query.do页面
		response.sendRedirect("query.do");
	}
	

	private void edit(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		System.out.println("edit");
		String forwardPath = "/error.jsp";
		//1.获取请求参数id
		String idString = request.getParameter("id");
		
		//2.调用CustomerDAO 的 customerDAO.get(id) 获取和ID对应的Customer对象customer
		try {
			Customer customer = customerDAO.get(Integer.parseInt(idString));
			if(customer != null) {
				forwardPath = "/updatecustomer.jsp";
				//3.将customer放入request中
				request.setAttribute("customer", customer);
			}
		} catch (NumberFormatException e) {
			
		}
		
		//4.响应updatecustomer.jsp 页面；转发
		request.getRequestDispatcher(forwardPath).forward(request, response);
	}


	private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//System.out.println("add");
		//获取表单参数
		String nameString = request.getParameter("name");
		String addString = request.getParameter("address");
		String phoneString = request.getParameter("phone");
		
		//检验name是否已经被占用
		//调用customerDAO的getCountwithname获取name在数据库中是否存在
		long count = customerDAO.getCountWithName(nameString);
				
		
		//若返回值大于0，则响应newcustomer.jsp:
		if(count > 0) {		
		//要求在newcustomer.jsp显示一个错误消息：用户名被占用，请重新选择
		//在request中放入一个属性message：用户名被占用，请重新选择
		//在页面上通过request.getAttribute的方式来显示
		request.setAttribute("message", "用户名"+ nameString +"被占用，请重新选择");
		
		//newcustomer.jsp的表单值可以回显value="<%= request.getParameter("name") == null ? "": request.getParameter("name")%>"
		
		request.getRequestDispatcher("/newcustomer.jsp").forward(request, response);
		
		return ;
		}
		
		//把表单参数封装为一个customer对象
		Customer customer = new Customer(nameString,addString,phoneString);
		
		//调用customerDAO的save方法
		customerDAO.save(customer);
		
		//重定向到success页面,避免表单的重复提交问题 
		response.sendRedirect("success.jsp");
	}
	
}

