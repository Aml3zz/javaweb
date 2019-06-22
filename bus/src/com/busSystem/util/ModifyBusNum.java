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
	 * ����µ����ݵ�busInfo����
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
			
			busNum = exChange(busNum);//�ַ���ת��
			beginSt = exChange(beginSt);//�ַ���ת��
			endSt = exChange(endSt);//�ַ���ת��
			selectPrice = exChange(selectPrice);//�ַ���ת��
			selectLevel = exChange(selectLevel);//�ַ���ת��
			selectNote = exChange(selectNote);//�ַ���ת��
			
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
	 * ���ݶ�Ӧ�ĳ������վ����Ϣ
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
	 * ����µ����ݵ�message����
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
		Date date = new Date();//��ȡϵͳʱ��
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//��ϵͳʱ��ת��Ϊ(�磺2003-09-12 21:00:00)���ָ�ʽ
		String strDate = dateFormat.format(date);
		int count = 0;
		int maxId = 0;
		try {
			
			username = exChange(username);//�ַ���ת��
			topic = exChange(topic);//�ַ���ת��
			email = exChange(email);//�ַ���ת��
			messagetext = exChange(messagetext);//�ַ���ת��
			
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
	 * �޸��û���Ϣ
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
				username = exChange(username);//�ַ���ת��
				sql = "update userInfo set";
				if (!userpassword.equals("") && userpassword != null) {
					userpassword = exChange(userpassword);//�ַ���ת��
					sql = sql + " userpassword = '"+userpassword+"'";
					bf.append(sql);
					if (!userage.equals("") && userage != null) {
						userage = exChange(userage);//�ַ���ת��
						bf.append(",userage = '"+Integer.parseInt(userage)+"'");
					}
					if (!address.equals("") && address != null) {
						address = exChange(address);//�ַ���ת��
						bf.append(",address = '"+address+"'");
					}
					if (!email.equals("") && email != null) {
						email = exChange(email);//�ַ���ת��
						bf.append(",email = '"+email+"'");
					}
					if (!question.equals("") && question != null) {
						question = exChange(question);//�ַ���ת��
						bf.append(",question = '"+question+"'");
					}
					if (!answer.equals("") && answer != null) {
						answer = exChange(answer);//�ַ���ת��
						bf.append(",answer = '"+answer+"'");
					}
					if (!idnum.equals("") && idnum != null) {
						idnum = exChange(idnum);//�ַ���ת��
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
