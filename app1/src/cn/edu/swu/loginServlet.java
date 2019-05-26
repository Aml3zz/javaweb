package cn.edu.swu;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Map;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.ServletContext;

public class loginServlet implements Servlet{

	@Override
	public void destroy() {
		
	}

	@Override
	public ServletConfig getServletConfig() {
		return null;
	}

	@Override
	public String getServletInfo() {
		return null;
	}

	private ServletConfig arg;
	@Override
	public void init(ServletConfig arg) throws ServletException {
		this.arg = arg;
	}

	@Override
	public void service(ServletRequest req,ServletResponse resp)
			throws ServletException,IOException{
		System.out.println("request coming ---.>");
		//System.out.println(req);

		String name = req.getParameter("username");
		String pass = req.getParameter("password");
		System.out.println(name + "," + pass);
	
		ServletContext servcont = arg.getServletContext();
		String xname = servcont.getInitParameter("user");
		String xpass = servcont.getInitParameter("password");

		//just output only one interest
		String intersting = req.getParameter("interest");
		System.out.println("the first interesting :" + intersting);

		//output all the interest
		String[] inters = req.getParameterValues("interest");
		for(String inter : inters){
			System.out.println("interest :" + inter);
		}

		//use enumeration output name passwd and interesting
	/*	Enumeration<String>names = req.getParameterNames();
		while(names.hasMoreElements()){
			String ele = names.nextElement();
			String val = req.getParameter(ele);

			System.out.println("enumeration:: name is :" + ele + "value ::" + val);
		}
		
	*/
		Map<String,String[]> map = req.getParameterMap();
		for(Map.Entry<String,String[]> entry:map.entrySet()){
			System.out.println("map method: " + entry.getKey() + " : " + Arrays.asList(entry.getValue()));
		}
	
	//output the request URI
	/*	HttpServletRequest hreq = (HttpServletRequest)req;
		String reqURI = hreq.getRequestURI();
		System.out.println(reqURI);
	*/

		PrintWriter out = resp.getWriter();
		if(xname.equals(name) && xpass.equals(pass)){
			out.print("hello " + name);
		}
		else
			out.print("sorry " + name);
	}
	
}
