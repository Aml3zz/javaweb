package com.busSystem.core;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * 使用单列模式(DBConnection2定义为私有类型)来连接数据库
 * @author Administrator
 *
 */
public class DBConnection2 {
	private static DBConnection2 db = null;
	private String username = "root";
	private String password = "am9712";
	private String url = "jdbc:mysql://localhost:3306/bus?characterEncoding=UTF-8";
	private DBConnection2() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("数据连接失败!");
		}
	}
	synchronized public static DBConnection2 getInstance(){
		if (db == null) {
			db = new DBConnection2();
		}
		return db;
	}
	public Connection getConnection() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url,username,password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	public void freeConnection(Connection conn) {
		try {
			if (conn != null) {
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("数据库关闭失败!");
		}
	}
}
