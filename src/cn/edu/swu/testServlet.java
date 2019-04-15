package cn.edu.swu;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class testServlet extends HttpServlet{
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String servletPathString = req.getServletPath();
		//System.out.println(servletPathString);
		String methodNameString = servletPathString.substring(1);
		methodNameString = methodNameString.substring(0, methodNameString.length()-3);
		//System.out.println(methodNameString);
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
	
	private void query(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("query");
			
	}

}

