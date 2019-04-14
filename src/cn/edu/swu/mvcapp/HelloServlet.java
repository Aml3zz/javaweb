package cn.edu.swu;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Properties;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class HelloServlet implements Servlet{

	@Override
	public void destroy() {
		System.out.println("destroy");
	}

	@Override
	public ServletConfig getServletConfig() {
		System.out.println("getServletConfig");
		return null;
	}

	@Override
	public String getServletInfo() {
		System.out.println("getServletInfo");
		return null;
	}
	
	@Override
	public void init(ServletConfig servletConfig) throws ServletException {
		System.out.println("init");
		String name = servletConfig.getInitParameter("user");
		System.out.println("user: " + name);
		String pass = servletConfig.getInitParameter("password");
		System.out.println("passwd :" + pass);
		
		Enumeration<String> names = servletConfig.getInitParameterNames();
		while(names.hasMoreElements()){
			String some = names.nextElement();
			String val = servletConfig.getInitParameter(some);
			System.out.println("name :"+ some + "what :" + val);
		}
	}
	
	@Override
	public void service(ServletRequest arg0, ServletResponse arg1)
			throws ServletException, IOException {
		System.out.println("service");
	}

	public HelloServlet() {
		System.out.println("HelloServlet's constructor");
	}

}

