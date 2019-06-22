package com.busSystem.core;

import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Connection;

public class Register {
	/**
	 * �ַ�ת��
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
	 * �û�ע��
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
				username = exChange(username);//�ַ���ת��
				conn = db.getConnection();//�������Դ
				stmt = conn.createStatement();//��ö����ʼ��
				sql = "select * from userinfo where username = '"+username+"'";
				rs = stmt.executeQuery(sql); //ִ�в�ѯSQL���
				while (rs.next() && rs != null) {
					count++;
				}
				if (count == 0) {
					sql = "insert into userinfo (username,userpassword,userage,address,email,idnum) " +
							"values ('"+username+"','"+userpassword+"','"+userage+"','"+address+"','"+email+"','"+idnum+"')";
					success = stmt.executeUpdate(sql);
					if (success > 0) {
						info = "�û�ע��ɹ���";
					} else {
						info = "�û�ע�᲻�ɹ���";
					}
				} else {
					info = "�Բ��𣬴��û��Ѿ���ע�ᣡ";
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
