package com.busSystem.util;

/**
 * ������������Ҫ����ɶ�������Ϣ��ѯ�����������β�ѯ��վ���ѯ��վ����վ��֮�����·����ѯ��
 * �û�������Ϣ��ѯ����ʾ��ҳ��ѯ�ȵ�...
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;

import com.busSystem.core.DBConnection2;
import com.data.structures.graph1.*;

public class QueryData {
	@SuppressWarnings("rawtypes")
	private ArrayList arrayListBusNum;
	private final int pageSize = 10;//ÿҳ��ʾ10����¼
	private int count = 0;//�����ܼ�¼��
	private int pageCount = 0;//��ʾ��ҳ��
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
	
	@SuppressWarnings("rawtypes")
	public void setArrayListBusNum(ArrayList arrayList) {
		this.arrayListBusNum = arrayList;
	}
	
	@SuppressWarnings("rawtypes")
	public ArrayList getArrayListBusNum() {
		return arrayListBusNum;
	}
	
	public QueryData() {
		
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
	 * ��ѯ��busnum�����ŷ��ص�������
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ArrayList queryBusNum() {
		ArrayList arrayList = new ArrayList();
		DBConnection2 db = DBConnection2.getInstance();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "";
		try {
			sql = "select * from businfo";
			conn = db.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next() && rs != null) {
				arrayList.add(""+rs.getInt("busnum"));
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
	 * ��ѯ��stinfo��zվ�����Ʒ��ص�������
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ArrayList querySt() {
		ArrayList arrayList = new ArrayList();
		DBConnection2 db = DBConnection2.getInstance();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "";
		try {
			sql = "select * from stinfo";
			conn = db.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next() && rs != null) {
				HashMap hashMap = new HashMap();
				hashMap.put("stid", ""+rs.getInt("stid"));
				hashMap.put("stname", rs.getString("stname"));
				arrayList.add(hashMap);
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
	 * ����businfo��¼��������ҳ����
	 * @param busNum
	 */
	public void countBusInfo(String busNum) {
		DBConnection2 db = DBConnection2.getInstance();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "";
		try {
			busNum = exChange(busNum);//�ַ���ת��
			if (busNum.equals("") || busNum == null) {
				sql = "select count(*) from businfo";
			} else {
				busNum = exChange(busNum);//�ַ���ת��
				sql = "select count(*) from businfo where busnum = '"+Integer.parseInt(busNum)+"'";
			}
			conn = db.getConnection();
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			rs = stmt.executeQuery(sql);
			if (rs.next() && rs != null) {
				setCount(Integer.parseInt(rs.getString(1)));
				setPageCount(count % pageSize == 0 ? count / pageSize : count /pageSize + 1);
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
	 * ��ҳ��ʾ��ѯbusinfo�е�����
	 * @param busNum
	 * @param page
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ArrayList statisticsByBusInfo(String busNum,int page) {
		ArrayList arrayList = new ArrayList();
		DBConnection2 db = DBConnection2.getInstance();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "";
		try {
			int m = (page-1)*pageSize; //�����α��λ��
			int i = 0;
			busNum = exChange(busNum);//�ַ���ת��
			if (busNum.equals("") || busNum == null) {
				sql = "select * from businfo";
			} else {
				busNum = exChange(busNum);//�ַ���ת��
				sql = "select * from businfo where busnum = '"+Integer.parseInt(busNum)+"'";
			}
			conn = db.getConnection();
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			rs = stmt.executeQuery(sql);
			if (rs.first()) {
				//rs.setFetchSize(50);
				rs.absolute(m + 1); //���α�ŵ�ָ��λ���ټ�1��Ҳ�ǿ�ʼ�ĵ�һ����¼����ţ�
				while (i < pageSize && !rs.isAfterLast() && rs != null) {
					HashMap hashMap = new HashMap();
					hashMap.put("BusNum",""+rs.getInt("busnum"));
					hashMap.put("BeginSt",rs.getString("beginst"));
					hashMap.put("EndSt",rs.getString("endst"));
					hashMap.put("TicketNote",rs.getString("ticketnote"));
					hashMap.put("BusLevel",rs.getString("buslevel"));
					hashMap.put("Note",rs.getString("note"));
					arrayList.add(hashMap);
					rs.next();
					i++;
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
	 * ����message��¼��������ҳ����
	 * @param username
	 */
	public void countMessage(String username) {
		DBConnection2 db = DBConnection2.getInstance();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "";
		try {
			username = exChange(username);//�ַ���ת��
			if (username == null || username.equals("")) {
				sql = "select count(*) from message";
			} else {
				username = exChange(username);//�ַ���ת��
				sql = "select count(*) from message where username = '"+username+"'";
			}
			conn = db.getConnection();
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			rs = stmt.executeQuery(sql);
			if (rs.next() && rs != null) {
				setCount(Integer.parseInt(rs.getString(1)));
				setPageCount(count % pageSize == 0 ? count / pageSize : count /pageSize + 1);
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
	 * ����userInfo
	 * ��¼��������ҳ����
	 */
	public void countUserInfo() {
		DBConnection2 db = DBConnection2.getInstance();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "";
		try {			
				sql = "select count(*) from userinfo";			
			conn = db.getConnection();
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			rs = stmt.executeQuery(sql);
			if (rs.next() && rs != null) {
				setCount(Integer.parseInt(rs.getString(1)));
				setPageCount(count % pageSize == 0 ? count / pageSize : count /pageSize + 1);
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
	 * ��ҳ��ʾ��ѯmessage�е�����
	 * @param username
	 * @param page
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ArrayList statisticsByMeassage(String username,int page) {
		ArrayList arrayList = new ArrayList();
		DBConnection2 db = DBConnection2.getInstance();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "";
		try {
			int m = (page-1)*pageSize;
			int i = 0;
			username = exChange(username);//�ַ���ת��
			if (username == null || username.equals("")) {
				sql = "select * from message";
			} else {
				username = exChange(username);//�ַ���ת��
				sql = "select * from message where username = '"+username+"'";
				System.out.println(sql);
			}
			conn = db.getConnection();
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			rs = stmt.executeQuery(sql);
			if (rs.first()) {
				//rs.setFetchSize(50);
				rs.absolute(m + 1);
				while (i < pageSize && !rs.isAfterLast() && rs != null) {
					HashMap hashMap = new HashMap();
					hashMap.put("username",rs.getString("username"));
					hashMap.put("messagedate",rs.getString("messagedate"));
					hashMap.put("topic",rs.getString("topic"));
					hashMap.put("email",rs.getString("email"));
					hashMap.put("messagetext",rs.getString("messagetext"));
					hashMap.put("id",""+rs.getInt("id"));
					arrayList.add(hashMap);
					rs.next();
					i++;
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
	 * ��ҳ��ʾ��ѯuserInfo�е�����
	 * @param page
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ArrayList statisticsByUserInfo(int page) {
		ArrayList arrayList = new ArrayList();
		DBConnection2 db = DBConnection2.getInstance();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "";
		try {			
			int m = (page-1)*pageSize;
			int i = 0;
				sql = "select * from userinfo";							
			conn = db.getConnection();
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			rs = stmt.executeQuery(sql);
			if (rs.first()) {
				//rs.setFetchSize(50);
				rs.absolute(m + 1);
				while (i < pageSize && !rs.isAfterLast() && rs != null) {
					HashMap hashMap = new HashMap();
					hashMap.put("username",rs.getString("username"));
					System.out.println("----------------->"+hashMap.get("username"));
					hashMap.put("userpassword",rs.getString("userpassword"));
					hashMap.put("userage",rs.getString("userage"));
					hashMap.put("address",rs.getString("address"));
					hashMap.put("email",rs.getString("email"));
					hashMap.put("idnum",""+rs.getInt("idnum"));
					arrayList.add(hashMap);
					rs.next();
					i++;
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
	 * ���β�ѯ
	 * @param busNum
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ArrayList queryBusNumDetail(String busNum) {
		ArrayList arrayList = new ArrayList();
		DBConnection2 db = DBConnection2.getInstance();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "";
		try {
			if (busNum.equals("") || busNum == null) {
				sql = "select * from businfo";
			} else {
				busNum = exChange(busNum);//�ַ���ת��
				sql = "select * from businfo where busnum = '"+Integer.parseInt(busNum)+"'";
			}
			conn = db.getConnection();
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			rs = stmt.executeQuery(sql);
			while (rs.next() && rs != null) {
				HashMap hashMap = new HashMap();
				hashMap.put("BusNum",""+rs.getInt("busnum"));
				hashMap.put("BeginSt",rs.getString("beginst"));
				hashMap.put("EndSt",rs.getString("endst"));
				hashMap.put("TicketNote",rs.getString("ticketnote"));
				hashMap.put("BusLevel",rs.getString("buslevel"));
				hashMap.put("Note",rs.getString("note"));
				arrayList.add(hashMap);
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
	 * ���ݳ��Ų�ѯվ������
	 * @param busNum
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ArrayList queryStCount(String busNum) {
		ArrayList arrayList = new ArrayList();
		DBConnection2 db = DBConnection2.getInstance();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "";
		try {
			if (!busNum.equals("")) {
				busNum = exChange(busNum);//�ַ���ת��
				sql = "select max(storder) as stCount from busst where busnum = '"+Integer.parseInt(busNum)+"'";
				conn = db.getConnection();
				stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
				rs = stmt.executeQuery(sql);
				if (rs.next() && rs != null) {
					arrayList.add(""+rs.getInt("stCount"));
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
	 * ���ݳ��Ų�ѯ���վ���յ�վ�����Ų���Ϊ��
	 * @param busNum
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ArrayList queryBusNumDetail2(String busNum) {
		ArrayList arrayList = new ArrayList();
		DBConnection2 db = DBConnection2.getInstance();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "";
		try {
			if (!busNum.equals("")) {
				busNum = exChange(busNum);//�ַ���ת��
				sql = "select * from businfo where busnum = '"+Integer.parseInt(busNum)+"'";
				conn = db.getConnection();
				stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
				rs = stmt.executeQuery(sql);
				while (rs.next() && rs != null) {
					HashMap hashMap = new HashMap();
					hashMap.put("BusNum",""+rs.getInt("busnum"));
					hashMap.put("BeginSt",rs.getString("beginst"));
					hashMap.put("EndSt",rs.getString("endst"));
					hashMap.put("TicketNote",rs.getString("ticketnote"));
					hashMap.put("BusLevel",rs.getString("buslevel"));
					hashMap.put("Note",rs.getString("note"));
					arrayList.add(hashMap);
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
	 * վ���ѯ
	 * @param stname
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ArrayList queryStInfo(String stname) {
		ArrayList arrayList = new ArrayList();
		DBConnection2 db = DBConnection2.getInstance();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "";
		try {
			stname = exChange(stname);//�ַ���ת��
			conn = db.getConnection();
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			sql = "select businfo.busnum,businfo.BeginSt,businfo.EndSt,businfo.TicketNote,stinfo.stname from businfo,stinfo,busst where stname like '%"+stname+"%' and stinfo.stid = busst.stid and busst.busnum = businfo.busnum";
			rs = stmt.executeQuery(sql);
			while (rs.next() && rs != null) {
				HashMap hashMap = new HashMap();
				hashMap.put("BusNum",""+rs.getInt("busnum"));
				hashMap.put("BeginSt",rs.getString("BeginSt"));
				hashMap.put("EndSt",rs.getString("EndSt"));
				hashMap.put("TicketNote",rs.getString("TicketNote"));
				hashMap.put("StName",rs.getString("stname"));
				arrayList.add(hashMap);
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
	 * ��ȡ��վ��֮������·��
	 * @param beginSt
	 * @param endSt
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ArrayList queryShortestPath(String beginSt,String endSt) {
		ArrayList arrayList = new ArrayList();
		ArrayList listBusNum = new ArrayList();
		DBConnection2 db = DBConnection2.getInstance();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "";
		int beginStId = 0;
		int endStId = 0;
		try {
			conn = db.getConnection();
			stmt = conn.createStatement();
			beginSt = exChange(beginSt);
			endSt = exChange(endSt);
			sql = "select * from stinfo where stname = '"+beginSt+"'";
			rs = stmt.executeQuery(sql);
			if (rs.next() && rs != null) {
				beginStId = rs.getInt("stid");
			}
			sql = "select * from stinfo where stname = '"+endSt+"'";
			rs = stmt.executeQuery(sql);
			if (rs.next() && rs != null) {
				endStId = rs.getInt("stid");
			}
			
			Graph graph = new Graph();
			graph.createGraphByDataBase(graph);
			Stack stack = new Stack();//�������·��(������վ�㳵��)
			//stack = graph.getShortestPath2("68", "49");
			stack = graph.getShortestPath2(""+beginStId, ""+endStId);
			
			Stack stack2 = new Stack();//�������·��(����վ�㳵��)
			stack2 = graph.getShortestPath2(""+beginStId, ""+endStId);
			
			if (stack2.size() != 1) {//����������վ������,��������
				while (!stack2.isEmpty()) {
					//System.out.print(""+((Vertex)stack2.pop()).getLabel()+" ");
					listBusNum.add(""+((Vertex)stack2.pop()).getLabel());//��վ������ȡ�����ŵ��б���
				}
				setArrayListBusNum(graph.exChangeStIdToBusNum(listBusNum));//��վ������(վ���)ת��Ϊ��Ӧ�ĳ��κ�
			}
			
			if (stack.size() != 1) {
				while (!stack.isEmpty()) {
					//System.out.print(((Vertex)stack.pop()).getLabel() + "   ");
					//System.out.print(graph.exchangeStId(""+((Vertex)stack.pop()).getLabel()) + "   ");
					arrayList.add("" + graph.exchangeStId(""+((Vertex)stack.pop()).getLabel()));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {//ǿ��ִ��,�ͷ�
			try {
				if (stmt != null) {
					stmt.close();//
				}
				db.freeConnection(conn);//�ر����ݿ�����
			} catch (Exception e) {
				e.printStackTrace();//��ӡ��ջ�쳣
			}
		}
		return arrayList;//����õ����·���Ž������б�arraylist��,������
	}
	
	/**
	 * ���վ���Ƿ�����
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ArrayList checkSt(String stname) {
		ArrayList arrayList = new ArrayList();
		DBConnection2 db = DBConnection2.getInstance();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "";
		try {
			stname = exChange(stname);//�ַ���ת��
			if (stname != null) {
				stname = exChange(stname);
				if (!stname.equals("")) {
					sql = "select * from stinfo where stname like '%"+stname+"%'";
				} else {
					sql = "select * from stinfo";
				}
			}
			//sql = "select * from stinfo where stname like '%"+stname+"%'";
			//sql = "select * from stinfo";
			conn = db.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next() && rs != null) {
				HashMap hashMap = new HashMap();
				hashMap.put("stid", ""+rs.getInt("stid"));
				hashMap.put("stname", rs.getString("stname"));
				arrayList.add(hashMap);
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
	 * ����û����Ƿ����
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ArrayList checkUserName(String username){
	ArrayList arrayList=new ArrayList();
	DBConnection2 db=DBConnection2.getInstance();
	Connection conn=null;
	Statement stmt=null;
	ResultSet rs=null;
	String sql="";
	try{
		if (!username.equals("")) {
			username = exChange(username);//�ַ���ת��
			sql = "select * from userinfo where username like '%"+username+"%'";
			conn = db.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next() && rs != null) {
				HashMap hashMap = new HashMap();
				hashMap.put("username", rs.getString("username"));
				arrayList.add(hashMap);
				}
		}
	 }catch (Exception e) {
		e.printStackTrace();
	}
	  finally {
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
	 * ��ѯ�û�������Ϣ
	 * @param username
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ArrayList queryUserInfo(String username) {
		ArrayList list = new ArrayList();
		DBConnection2 db=DBConnection2.getInstance();
		Connection conn=null;
		Statement stmt=null;
		ResultSet rs=null;
		String sql="";
		try {
			if (!username.equals("") && username != null) {
				username = exChange(username);//�ַ���ת��
				sql = "select * from userinfo where username = '"+username+"'";
				conn = db.getConnection();
				stmt = conn.createStatement();
				rs = stmt.executeQuery(sql);
				while (rs.next() && rs != null) {
					HashMap hashMap = new HashMap();
					hashMap.put("username", rs.getString("username"));
					
					hashMap.put("userpassword", rs.getString("userpassword"));
					
					hashMap.put("userage", ""+rs.getInt("userage"));
					hashMap.put("address", rs.getString("address"));
					hashMap.put("email", rs.getString("email"));
					hashMap.put("idnum", rs.getString("idnum"));
					
					list.add(hashMap);
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
		return list;
	}
	
	/**
	 * ��ѯ��message�е�����
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ArrayList queryMessage(String username){
		ArrayList arrayList=new ArrayList();
		DBConnection2 db=DBConnection2.getInstance();
		Connection conn=null;
		Statement stmt=null;
		ResultSet rs=null;
		String sql="";
		try{
			if (!username.equals("")) {
				username = exChange(username);//�ַ���ת��
				sql = "select * from message where username = '"+username+"'";
			} else {
				sql = "select * from message";
			}
			conn = db.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next() && rs != null) {
				HashMap hashMap = new HashMap();
				hashMap.put("username", rs.getString("username"));
				hashMap.put("messagedate",rs.getString("messagedate"));
				hashMap.put("topic", rs.getString("topic"));
				hashMap.put("email", rs.getString("email"));
				hashMap.put("messagetext", rs.getString("messagetext"));
				hashMap.put("id", ""+rs.getInt("id"));
				arrayList.add(hashMap);
			}
		 }catch (Exception e) {
			e.printStackTrace();
		}
		  finally {
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
	 * ����վ������һ��һ��ѯվ��ID��
	 * @param stname
	 * @return
	 */
	public int getStidByStname(String stname) {
		int stid = 0;
		DBConnection2 db=DBConnection2.getInstance();
		Connection conn=null;
		Statement stmt=null;
		ResultSet rs=null;
		String sql="";
		try {
			if (stname != null && !stname.equals("")) {
				stname = exChange(stname);//�ַ���ת��
				sql = "select stid from stinfo where stname = '"+stname+"'";
				conn = db.getConnection();
				stmt = conn.createStatement();
				rs = stmt.executeQuery(sql);
				if (rs.next() && rs != null) {
					stid = rs.getInt(1);
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
		return stid;
	}
	
	/**
	 * ����վ��ID��һ��һ��ѯվ��ID��վ������
	 * @param stid
	 * @return
	 */
	public String getStnameByStid(String stid) {
		String stname = "";
		DBConnection2 db=DBConnection2.getInstance();
		Connection conn=null;
		Statement stmt=null;
		ResultSet rs=null;
		String sql="";
		try {
			if (stid != null && !stid.equals("")) {
				stid = exChange(stid);//�ַ���ת��
				sql = "select stname from stinfo where stid = '"+stid+"'";
				conn = db.getConnection();
				stmt = conn.createStatement();
				rs = stmt.executeQuery(sql);
				if (rs.next() && rs != null) {
					stname = rs.getString(1);
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
		return stname;
	}
}
	

	