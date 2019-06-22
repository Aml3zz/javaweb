package com.busSystem.core;

import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Connection;

public class Register {
	/**
	 * 字符转码
	 * @param str
	 * @return
	 */
	private String exChange(String str) {
		try {
			str = new String(str.getBytes("ISO8859_1"),"UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return str;
	}
	/**
	 * 用户注册
	 * @param username
	 * @param userpassword
	 * @param userage
	 * @param address
	 * @param email
	 * @param idnum
	 * @return
	 */
	public String getRegisterInfo(String username,String userpassword,String userage,String address,String email,String idnum) {
		String sql = "";
		String info = "";
		DBConnection2 db = DBConnection2.getInstance();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		int count = 0;
		int success = 0;
		try {
			if (!userpassword.equals("")) {
				userpassword = exChange(userpassword);
			}
			if (!address.equals("")) {
				address = exChange(address);
			}
			if (!email.equals("")) {
				email = exChange(email);
			}
			if (!idnum.equals("")) {
				idnum = exChange(idnum);
			}
			if (!username.equals("")) {
				username = exChange(username);//字符串转码
				conn = db.getConnection();//获得数据源
				stmt = conn.createStatement();//获得对象初始化
				sql = "select * from userinfo where username = '"+username+"'";
				rs = stmt.executeQuery(sql); //执行查询SQL语句
				while (rs.next() && rs != null) {
					count++;
				}
				if (count == 0) {
					sql = "insert into userinfo (username,userpassword,userage,address,email,idnum) " +
							"values ('"+username+"','"+userpassword+"','"+userage+"','"+address+"','"+email+"','"+idnum+"')";
					success = stmt.executeUpdate(sql);
					if (success > 0) {
						info = "用户注册成功！";
					} else {
						info = "用户注册不成功！";
					}
				} else {
					info = "对不起，此用户已经被注册！";
				}
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
		return info;
	}
}
