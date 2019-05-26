package cn.edu.swu;
import javax.servlet.http.*;
import javax.servlet.ServletException;
import javax.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginServlet3 extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req,HttpServletResponse resp)
	throws ServletException,IOException{
		String username = req.getParameter("form-username");
		String passwd2  = req.getParameter("form-password");
		
		Connection connet = null;
		PreparedStatement state = null;
		ResultSet resultSet = null;
		
		PrintWriter out = resp.getWriter();

		//1. 获取请求参数: CHECK_CODE_PARAM_NAME
		String paramCode = req.getParameter("CHECK_CODE_PARAM_NAME");
		
		//2. 获取 session 中的 CHECK_CODE_KEY 属性值
		String sessionCode = (String)req.getSession().getAttribute("CHECK_CODE_KEY");
		
		System.out.println(paramCode);
		System.out.println(sessionCode); 
		
		//3. 比对. 看是否一致, 若一致说明验证码正确, 若不一致, 说明验证码错误
		if(!(paramCode != null && paramCode.equals(sessionCode))){
			req.getSession().setAttribute("message", "验证码不一致!");
			resp.sendRedirect(req.getContextPath() + "/index.jsp");
			return;
		}	
	
		try{
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/first_work?useSSL=false";
			String user = "root";
			String password = "am9712";
			connet = DriverManager.getConnection(url,user,password);
			String sql = "select count(id) from user1 where name = ? AND passwd = ?";

			state = connet.prepareStatement(sql);
			state.setString(1,username);
			state.setString(2,passwd2);

			resultSet = state.executeQuery();		

			if(resultSet.next()){
				int count = resultSet.getInt(1);
				if(count>0){
			req.getRequestDispatcher("chance.jsp").forward(req,resp);
			//resp.sendRedirect("http://120.78.138.231:8080/demo");
				}
				else{
					out.print("sorry " + username);
				}
			}
	
		}catch(Exception e){

			e.printStackTrace();
		}finally{
			try{
				if(resultSet != null){
					resultSet.close();	
				}
			}catch(SQLException e){ e.printStackTrace();}

			try{
                                if(state != null){
                                        state.close();
                                }
                        }catch(SQLException e){ e.printStackTrace();}

			try{
                                if(connet != null){
                                        connet.close();
                                }
                        }catch(SQLException e){ e.printStackTrace();}


		}

		
	}


}
