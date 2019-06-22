package com.busSystem.util;

import java.util.ArrayList;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import com.busSystem.core.DBConnection2;

public class DeleteBusInfo {
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
	 * 删除businfo中的数据
	 * @param busnum
	 * @return
	 */
	public boolean deleteBusInfo(String busNum) {
		boolean success = false;
		DBConnection2 db = DBConnection2.getInstance();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "";
		int flag = 0;
		int count = 0;
		try {
			busNum = exChange(busNum);//字符串转码
			sql = "select * from busst where busnum = '"+Integer.parseInt(busNum)+"'";
			conn = db.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next() && rs != null) {
				count++;
			}
			if (count == 0) {
				sql = "delete from businfo where busnum = '"+Integer.parseInt(busNum)+"'";
				flag = stmt.executeUpdate(sql);
				if (flag > 0) {
					success = true;
				}
			} else {
				sql = "delete from busst where busnum = '"+Integer.parseInt(busNum)+"'";
				flag = stmt.executeUpdate(sql);
				sql = "delete from businfo where busnum = '"+Integer.parseInt(busNum)+"'";
				flag = flag + stmt.executeUpdate(sql);
				if (flag > 1) {
					success = true;
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
		return success;
	}
	
	/**
	 * 删除留言信息
	 * @param id
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public boolean deleteMessage(String id) {
		boolean success = false;
		DBConnection2 db = DBConnection2.getInstance();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "";
		int flag = 0;
		try {
			if (!id.equals("")) {
				id = exChange(id);//字符串转码
				conn = db.getConnection();
				stmt = conn.createStatement();
				sql = "select max(id) as maxId from message";
				int maxId = 0;
				rs = stmt.executeQuery(sql);
				if (rs.next() && rs != null) {
					maxId = rs.getInt(1);
				}
				if (id.equals(""+maxId)) {
					sql = "delete from message where id = '"+Integer.parseInt(id)+"'";
					flag = stmt.executeUpdate(sql);
					if (flag > 0) {
						success = true;
					}
				} else {
					sql = "select id from message where id > '"+Integer.parseInt(id)+"'";
					ArrayList arrayList = new ArrayList();
					rs = stmt.executeQuery(sql);
					while (rs.next() && rs != null) {
						arrayList.add(""+rs.getInt(1));
					}
					sql = "delete from message where id = '"+Integer.parseInt(id)+"'";
					flag = stmt.executeUpdate(sql);
					int flag2 = 0;
					if (!arrayList.isEmpty() && arrayList.size() > 0) {
						for (int i = 0;i < arrayList.size();i++) {
							sql = "update message set id = '"+(Integer.parseInt(id)+i)+"' where id = '"+arrayList.get(i)+"'";
							flag2 = stmt.executeUpdate(sql);
						}
					}
					if ((flag + flag2)> 1) {
						success = true;
					}
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
		return success;
	}
	/**
	 * 删除用户信息
	 * @param username
	 * @return
	 */	
	public boolean deleteUserInfo(String username){
		boolean success = false;
		DBConnection2 db = DBConnection2.getInstance();
		Connection conn = null;
		Statement stmt = null;
		String sql = "";
		int flag = 0;
		try {	
			conn = db.getConnection();
			stmt = conn.createStatement();			
			sql = "delete from userinfo where username='"+exChange(username)+"'" ;
			flag = stmt.executeUpdate(sql);					
			if (flag > 0) {
						success = true;						
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
		return success;
	}
	/**
	 * 删除相应的站点
	 * @param busNum
	 * @param stOrder
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "resource" })
	public boolean deleteBusSt(String busNum,String stOrder) {
		boolean success = false;
		DBConnection2 db = DBConnection2.getInstance();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "";
		try {
			if (busNum != null && stOrder != null) {
				if (!busNum.equals("") && !stOrder.equals("")) {
					QueryData queryData = new QueryData();
					@SuppressWarnings("rawtypes")
					ArrayList arrayList = new ArrayList();
					conn = db.getConnection();
					stmt = conn.createStatement();
					sql = "select max(storder) as maxstorder from busst where busnum = '"+busNum+"'";
					rs = stmt.executeQuery(sql);
					int maxStOrder = 0;
					if (rs.next() && rs != null) {
						maxStOrder = rs.getInt(1);
					}
					sql = "select count(*) as maxnum from busst where busnum = '"+busNum+"' and storder > '"+stOrder+"'";
					int count = 0;
					rs = stmt.executeQuery(sql);
					if (rs.next() && rs != null) {
						count = rs.getInt(1);
					}
					//System.out.println("maxStOrder==="+maxStOrder);
					//System.out.println("count==="+count);
					
					sql = "delete from busst where busnum = '"+busNum+"' and storder = '"+stOrder+"'";
					int flag = stmt.executeUpdate(sql);
					
					if (stOrder.equals("1")) {
						sql = "select stid from busst where busnum = '"+busNum+"' and storder > '"+stOrder+"' order by storder asc";
						rs = stmt.executeQuery(sql);
						while (rs.next() && rs != null) {
							arrayList.add(""+rs.getInt(1));
						}
						int flag2 = 0;
						if (!arrayList.isEmpty() && arrayList.size() > 0) {
							for (int i = 0;i < arrayList.size();i++) {
								sql = "update busst set storder = '"+(Integer.parseInt(stOrder)+i)+"' where busnum = '"+busNum+"' and stid = '"+arrayList.get(i)+"'";
								flag2 = flag2 + stmt.executeUpdate(sql);
								if (i == 0) {
									sql = "update businfo set beginst = '"+queryData.getStnameByStid(""+arrayList.get(i))+"' where busnum = '"+busNum+"'";
									stmt.executeUpdate(sql);
								}
							}
						}
						if ((flag + flag2) > count) {
							success = true;
						}
					} else if (stOrder.equals(new String(""+maxStOrder)) && Integer.parseInt(stOrder) > 2) {
						sql = "select stid from busst where busnum = '"+busNum+"' and storder = '"+(Integer.parseInt(stOrder)-1)+"' order by storder asc";
						rs = stmt.executeQuery(sql);
						int stid = 0;
						while (rs.next() && rs != null) {
							stid = rs.getInt(1);
						}
						int flag2 = 0;
						sql = "update businfo set endst = '"+queryData.getStnameByStid(""+stid)+"' where busnum = '"+busNum+"'";
						flag2 = stmt.executeUpdate(sql);
						if ((flag + flag2) > count) {
							success = true;
						}
					} else {
						sql = "select stid from busst where busnum = '"+busNum+"' and storder > '"+stOrder+"' order by storder asc";
						rs = stmt.executeQuery(sql);
						while (rs.next() && rs != null) {
							arrayList.add(""+rs.getInt(1));
						}
						int flag2 = 0;
						if (!arrayList.isEmpty() && arrayList.size() > 0) {
							for (int i = 0;i < arrayList.size();i++) {
								sql = "update busst set storder = '"+(Integer.parseInt(stOrder)+i)+"' where busnum = '"+busNum+"' and stid = '"+arrayList.get(i)+"'";
								flag2 = flag2 + stmt.executeUpdate(sql);
							}
						}
						if ((flag + flag2) > count) {
							success = true;
						}
					}
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
		return success;
	}
}
