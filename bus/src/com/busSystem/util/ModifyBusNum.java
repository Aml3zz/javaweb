package com.busSystem.util;


import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;

import com.busSystem.core.DBConnection2;

@SuppressWarnings("rawtypes")
public class ModifyBusNum {
	@SuppressWarnings("rawtypes")
	private ArrayList busInfoList;
	public void setBusInfoList(ArrayList arrayList) {
		this.busInfoList = arrayList;
	}
	@SuppressWarnings("rawtypes")
	public ArrayList getBusInfoList() {
		return busInfoList;
	}
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
	 * 添加新的数据到busInfo表中
	 * @param busNum
	 * @param beginSt
	 * @param endSt
	 * @param selectPrice
	 * @param selectLevel
	 * @param selectNote
	 * @return
	 */
	public boolean addBusNumInfo(String busNum,String beginSt,String endSt,String selectPrice,String selectLevel,String selectNote) {
		boolean success = false;
		DBConnection2 db = DBConnection2.getInstance();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "";
		int count = 0;
		try {
			
			busNum = exChange(busNum);//字符串转码
			beginSt = exChange(beginSt);//字符串转码
			endSt = exChange(endSt);//字符串转码
			selectPrice = exChange(selectPrice);//字符串转码
			selectLevel = exChange(selectLevel);//字符串转码
			selectNote = exChange(selectNote);//字符串转码
			
			sql = "select * from businfo where busNum = '"+Integer.parseInt(busNum)+"'";
			conn = db.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if (rs.next() && rs != null) {
				count++;
			}
			if (count == 0) {
				sql = "insert into businfo (busNum,BeginSt,EndSt,TicketNote,BusLevel,Note) values " +
						"('"+Integer.parseInt(busNum)+"','"+beginSt+"','"+endSt+"','"+selectPrice+"','"+selectLevel+"','"+selectNote+"')";
				int flag = 0;
				flag = stmt.executeUpdate(sql);
				if (flag > 0) {
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
	 * 根据对应的车号添加站点信息
	 * @param addStid
	 * @param busNum
	 * @param beginStid
	 * @param endStid
	 * @param stCount
	 * @return
	 */
	public boolean addStInfo(String addStid,String busNum,String beginStid,String endStid,String stCount) {
		boolean success = false;
		QueryData queryData = new QueryData();
		DBConnection2 db = DBConnection2.getInstance();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "";
		int maxId = 0;
		try {
			if (!stCount.equals("")) {
				int stCountInt = Integer.parseInt(stCount);
				if (stCountInt == 0) {
					sql = "insert into busst (busnum,stid,storder) values ('"+Integer.parseInt(busNum)+"','"+queryData.getStidByStname(beginStid)+"','"+1+"')";
					conn = db.getConnection();
					stmt = conn.createStatement();
					int flag = stmt.executeUpdate(sql);
					int flag2 = 0; 
					if (queryData.getStidByStname(addStid) > 0) {
						sql = "insert into busst (busnum,stid,storder) values ('"+Integer.parseInt(busNum)+"','"+queryData.getStidByStname(addStid)+"','"+2+"')";
						flag2 = stmt.executeUpdate(sql);
					} else {
						sql = "select max(stid) as maxstid from stinfo";
						rs = stmt.executeQuery(sql);
						if (rs.next() && rs != null) {
							maxId = rs.getInt(1) + 1;
						}
						sql = "insert into stinfo (stid,stname) values ('"+maxId+"','"+exChange(addStid)+"')";
						stmt.executeUpdate(sql);
						sql = "insert into busst (busnum,stid,storder) values ('"+Integer.parseInt(busNum)+"','"+queryData.getStidByStname(addStid)+"','"+2+"')";
						flag2 = stmt.executeUpdate(sql);
					}
					//sql = "insert into busst (busnum,stid,storder) values ('"+Integer.parseInt(busNum)+"','"+queryData.getStidByStname(addStid)+"','"+2+"')";
					//flag2 = stmt.executeUpdate(sql);
					sql = "insert into busst (busnum,stid,storder) values ('"+Integer.parseInt(busNum)+"','"+queryData.getStidByStname(endStid)+"','"+3+"')";
					int flag3 = stmt.executeUpdate(sql);
					if ((flag + flag2 + flag3) > 2) {
						success = true;
					}
				} else {
					sql = "select max(storder) as maxid from busst where busnum = '"+Integer.parseInt(busNum)+"'";
					conn = db.getConnection();
					stmt = conn.createStatement();
					rs = stmt.executeQuery(sql);
					if (rs.next() && rs != null) {
						System.out.println(rs.getInt("maxId"));
						maxId = rs.getInt(1);
						if (maxId == 0) {
							maxId = 1;
						} else {
							maxId = maxId + 1;
						}
					}
					if (queryData.getStidByStname(addStid) > 0) {
						sql = "update busst set storder = '"+maxId+"' where busNum = '"+Integer.parseInt(busNum)+"' and stid = '"+queryData.getStidByStname(endStid)+"'";
						int flag = stmt.executeUpdate(sql);
						sql = "insert into busst (busnum,stid,storder) values ('"+Integer.parseInt(busNum)+"','"+queryData.getStidByStname(addStid)+"','"+(maxId-1)+"')";
						int flag2 = stmt.executeUpdate(sql);
						if ((flag + flag2) > 1) {
							success = true;
						}
					} else {
						sql = "update busst set storder = '"+maxId+"' where busNum = '"+Integer.parseInt(busNum)+"' and stid = '"+queryData.getStidByStname(endStid)+"'";
						int flag = stmt.executeUpdate(sql);
						sql = "select max(stid) as maxstid from stinfo";
						rs = stmt.executeQuery(sql);
						int maxId2 = 0;
						if (rs.next() && rs != null) {
							maxId2 = rs.getInt(1) + 1;
						}
						sql = "insert into stinfo (stid,stname) values ('"+maxId2+"','"+exChange(addStid)+"')";
						stmt.executeUpdate(sql);
						sql = "insert into busst (busnum,stid,storder) values ('"+Integer.parseInt(busNum)+"','"+queryData.getStidByStname(addStid)+"','"+(maxId-1)+"')";
						int flag2 = stmt.executeUpdate(sql);
						if ((flag + flag2) > 1) {
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
	/**
	 * 添加新的数据到message表中
	 * @param userid
	 * @param username
	 * @param messagedate
	 * @param topic
	 * @param email
	 * @param messagetext
	 * @return
	 */
	public boolean addMessage(String username,String topic,String email,String messagetext) {
		boolean success = false;
		DBConnection2 db = DBConnection2.getInstance();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "";
		Date date = new Date();//获取系统时间
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//将系统时间转化为(如：2003-09-12 21:00:00)这种格式
		String strDate = dateFormat.format(date);
		int count = 0;
		int maxId = 0;
		try {
			
			username = exChange(username);//字符串转码
			topic = exChange(topic);//字符串转码
			email = exChange(email);//字符串转码
			messagetext = exChange(messagetext);//字符串转码
			
			sql = "select max(id) as maxid from message";
			conn = db.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if (rs.next() && rs != null) {
				//System.out.println(rs.getInt("maxId"));
				maxId = rs.getInt(1);
				if (maxId == 0) {
					maxId = 1;
				} else {
					maxId = maxId + 1;
				}
			}
			if (count == 0) {
				sql = "insert into message (username,messagedate,topic,email,messagetext,id) values " +
						"('"+username+"','"+strDate+"','"+topic+"','"+email+"','"+messagetext+"','"+maxId+"')";
				int flag = 0;
				flag = stmt.executeUpdate(sql);
				if (flag > 0) {
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
	 * 修改用户信息
	 * @param username
	 * @return
	 */
	public boolean modifyUserInfo(String username,String userpassword,String userage,String address,String email,String question,String answer,String idnum) {
		boolean success = false;
		DBConnection2 db = DBConnection2.getInstance();
		Connection conn = null;
		Statement stmt = null;
		String sql = "";
		StringBuffer bf = new StringBuffer();
		int flag = 0;
		try {
			if (!username.equals("") && username != null) {
				username = exChange(username);//字符串转码
				sql = "update userInfo set";
				if (!userpassword.equals("") && userpassword != null) {
					userpassword = exChange(userpassword);//字符串转码
					sql = sql + " userpassword = '"+userpassword+"'";
					bf.append(sql);
					if (!userage.equals("") && userage != null) {
						userage = exChange(userage);//字符串转码
						bf.append(",userage = '"+Integer.parseInt(userage)+"'");
					}
					if (!address.equals("") && address != null) {
						address = exChange(address);//字符串转码
						bf.append(",address = '"+address+"'");
					}
					if (!email.equals("") && email != null) {
						email = exChange(email);//字符串转码
						bf.append(",email = '"+email+"'");
					}
					if (!question.equals("") && question != null) {
						question = exChange(question);//字符串转码
						bf.append(",question = '"+question+"'");
					}
					if (!answer.equals("") && answer != null) {
						answer = exChange(answer);//字符串转码
						bf.append(",answer = '"+answer+"'");
					}
					if (!idnum.equals("") && idnum != null) {
						idnum = exChange(idnum);//字符串转码
						bf.append(",idnum = '"+idnum+"'");
					}
					bf.append(" where username = '"+username+"'");
					sql = bf.toString();
					conn = db.getConnection();
					stmt = conn.createStatement();
					flag = stmt.executeUpdate(sql);
					if (flag > 0) {
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
}
