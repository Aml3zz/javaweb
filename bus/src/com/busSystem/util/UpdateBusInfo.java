package com.busSystem.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import com.busSystem.core.DBConnection2;

public class UpdateBusInfo {
	private final int pageSize = 10;//每页显示10条记录
	private int count = 0;//计算总记录数
	private int pageCount = 0;//显示总页数
	public void setCount(int count) {
		this.count = count;
	}
	public int getCount() {
		return count;
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	public int getPageCount() {
		return pageCount;
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
	 * 计算总记录数
	 * @param busNum
	 */
	public void countBusNumData(String busNum) {
		DBConnection2 db = DBConnection2.getInstance();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "";
		try {
			if (!busNum.equals("") && busNum != null) {
				busNum = exChange(busNum);//字符串转码
				sql = "select count(*) as max " +
					"from businfo,busst,stinfo where busst.busnum = '"+Integer.parseInt(busNum)+"' and businfo.busnum = busst.busnum and stinfo.stid = busst.stid";
				conn = db.getConnection();
				stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
				rs = stmt.executeQuery(sql);
				if (rs.next() && rs != null) {
					setCount(Integer.parseInt(rs.getString(1)));
					setPageCount(count % pageSize == 0 ? count / pageSize : count /pageSize + 1);
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
	}
	/**
	 * 为更新businfo中的数据提供查询功能显示
	 * @param busnum
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ArrayList queryBusNumData(String busNum,int page) {
		ArrayList arrayList = new ArrayList();
		DBConnection2 db = DBConnection2.getInstance();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "";
		try {
			int m = (page-1)*pageSize;
			int i = 0;
			if (!busNum.equals("") && busNum != null) {
				busNum = exChange(busNum);//字符串转码
				sql = "select businfo.busnum,businfo.ticketnote,businfo.buslevel,businfo.note,busst.storder,stinfo.stname,stinfo.stid " +
					"from businfo,busst,stinfo where busst.busnum = '"+Integer.parseInt(busNum)+"' and " +
							"businfo.busnum = busst.busnum and stinfo.stid = busst.stid order by storder asc";
				conn = db.getConnection();
				stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
				rs = stmt.executeQuery(sql);
				if (rs.first()) {
					rs.absolute(m + 1);
					while (i < pageSize && !rs.isAfterLast() && rs != null) {
						HashMap hashMap = new HashMap();
						hashMap.put("BusNum", ""+rs.getInt("busnum"));
						hashMap.put("TicketNote", rs.getString("ticketnote"));
						hashMap.put("BusLevel", rs.getString("buslevel"));
						hashMap.put("Note", rs.getString("note"));
						hashMap.put("StOrder", rs.getString("storder"));
						hashMap.put("StName", rs.getString("stname"));
						hashMap.put("StId", ""+rs.getInt("stid"));
						arrayList.add(hashMap);
						rs.next();
						i++;
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
		return arrayList;
	}
	
	/**
	 * 更新车次信息
	 * @param busNum
	 * @param beginSt
	 * @param endSt
	 * @param priceNote
	 * @param busLevel
	 * @param priceLevel
	 * @param storder
	 * @return
	 */
	public boolean updateBusInfo (String busNum,String beginSt,String endSt,String priceNote,String busLevel,String priceLevel) {
		boolean success = false;
		QueryData queryData = new QueryData();
		DBConnection2 db = DBConnection2.getInstance();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "";
		int maxId = 0;
		int minId = 0;
		try {
			busNum = exChange(busNum);
			//beginSt = exChange(beginSt);
			//endSt = exChange(endSt);
			priceNote = exChange(priceNote);
			busLevel = exChange(busLevel);
			priceLevel = exChange(priceLevel);
			
			conn = db.getConnection();
			stmt = conn.createStatement();
			sql = "select max(storder) as maxid from busst where busnum = '"+busNum+"'";
			rs = stmt.executeQuery(sql);
			if (rs.next() && rs != null) {
				maxId = rs.getInt(1);
			}
			sql = "select storder from busst where busnum = '"+busNum+"' order by storder asc";
			rs = stmt.executeQuery(sql);
			if (rs.next() && rs != null) {
				minId = rs.getInt(1);
			}
			sql = "update businfo set beginst = '"+exChange(beginSt)+"',endst = '"+exChange(endSt)+"',ticketnote = '"+priceNote+"',buslevel = '"+busLevel+"'" +
					",note = '"+priceLevel+"' where busnum = '"+busNum+"'";
			int flag = stmt.executeUpdate(sql);
			sql = "update busst set stid = '"+queryData.getStidByStname(beginSt)+"' where busnum = '"+busNum+"' and storder = '"+minId+"'";
			int flag2 = stmt.executeUpdate(sql);
			sql = "update busst set stid = '"+queryData.getStidByStname(endSt)+"' where busnum = '"+busNum+"' and storder = '"+maxId+"'";
			int flag3 = stmt.executeUpdate(sql);
			if ((flag + flag2 + flag3) > 2) {
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
	 * 更新各个车次中的站点
	 * @param busnum
	 * @param stid
	 * @return
	 */
	public boolean updateBusst(String busnum,String stid,String storder) {
		boolean success = false;
		DBConnection2 db = DBConnection2.getInstance();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "";
		try {
			if (busnum != null && stid != null && storder != null) {
				if (!busnum.equals("") && !stid.equals("") && !storder.equals("")) {
					QueryData queryData = new QueryData();
					conn = db.getConnection();
					stmt = conn.createStatement();
					int maxStorder = 0;
					sql = "select max(storder) as maxStorder from busst where busnum = '"+busnum+"'";
					rs = stmt.executeQuery(sql);
					if (rs.next() && rs != null) {
						maxStorder = rs.getInt(1);
					}
					if (storder.equals("1")) {
						sql = "update businfo set beginst = '"+queryData.getStnameByStid(stid)+"' where busnum = '"+busnum+"'";
						int flag = stmt.executeUpdate(sql);
						System.out.println("111----"+sql);
						sql = "update busst set stid = '"+stid+"' where busnum = '"+busnum+"' and storder = '"+storder+"'";
						int flag2 = stmt.executeUpdate(sql);
						System.out.println("111----"+sql);
						if ((flag + flag2) > 1) {
							success = true;
						}
					} else if (storder.equals(""+maxStorder)) {
						sql = "update businfo set endst = '"+queryData.getStnameByStid(stid)+"' where busnum = '"+busnum+"'";
						int flag = stmt.executeUpdate(sql);
						System.out.println("222----"+sql);
						sql = "update busst set stid = '"+stid+"' where busnum = '"+busnum+"' and storder = '"+storder+"'";
						int flag2 = stmt.executeUpdate(sql);
						System.out.println("222----"+sql);
						if ((flag + flag2) > 1) {
							success = true;
						}
					} else {
						sql = "update busst set stid = '"+stid+"' where busnum = '"+busnum+"' and storder = '"+storder+"'";
						System.out.println("3333333333"+sql);
						int flag = stmt.executeUpdate(sql);
						if (flag > 0) {
							success = true;
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return success;
	}
}
