package com.data.structures.graph1;

import java.util.Iterator;
import java.util.ArrayList;
import java.util.TreeMap;
import java.util.Stack;
import java.util.Collection;
import java.util.Collections;
import java.util.TreeSet;
import java.util.HashMap;
import java.util.Vector;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.DriverManager;

import com.busSystem.core.DBConnection2;

public class Graph {
	@SuppressWarnings("rawtypes")
	private TreeMap vertices;
	private int edgeCount;

	@SuppressWarnings("rawtypes")
	public Graph() {
		vertices = new TreeMap();
		edgeCount = 0;
	}

	/**
	 * ��Ҫ����Ķ������TreeMap����
	 * @param vertexLabel
	 */
	@SuppressWarnings("unchecked")
	public void addVertex(Object vertexLabel) {
		vertices.put(vertexLabel,new Vertex(vertexLabel));
	}

	/**
	 * ������㣬�յ�ͱ�
	 * @param begin
	 * @param end
	 * @param edgeWeight
	 */
	public void addEdge(Object begin,Object end,double edgeWeight) {
		Vertex beginVertex = (Vertex) vertices.get(begin);
		Vertex endVertex = (Vertex) vertices.get(end);
		beginVertex.addEdge(endVertex,edgeWeight);
		edgeCount ++;
	}

	/**
	 * ���ö���
	 *
	 */
	public void resetVertices() {
		@SuppressWarnings("rawtypes")
		Collection collection = vertices.values();
		@SuppressWarnings("rawtypes")
		Iterator vertexIterator = collection.iterator();
		while (vertexIterator.hasNext()) {
			Vertex nextVertex = (Vertex) vertexIterator.next();

			nextVertex.unvisit();
			nextVertex.setCost(0);
			nextVertex.setPredecessor(null);
		}
	}

	/**
	 * ���ù�����ȱ����㷨�������·��
	 * @param begin
	 * @param end
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Stack getShortestPath2(Object begin,Object end) {
		this.resetVertices();
		boolean done = false;
		ArrayList vertexQueue = new ArrayList();
		Vertex beginVertex = (Vertex) vertices.get(begin);
		Vertex endVertex = (Vertex) vertices.get(end);
		beginVertex.visit();
		vertexQueue.add(beginVertex);

		while (!done && !vertexQueue.isEmpty()) {
			Vertex frontVertex =  (Vertex) vertexQueue.get(0);
			vertexQueue.remove(0);
			Iterator edges = frontVertex.getNeighborIterator();
			while (!done && edges.hasNext()) {
				Edge edgeToNextNeighbor = (Edge) edges.next();
				Vertex nextNeighbor = edgeToNextNeighbor.getEndVertex();
				if (!nextNeighbor.isVisited()) {
					nextNeighbor.visit();
					nextNeighbor.setCost(1 + frontVertex.getCost());
					nextNeighbor.setPredecessor(frontVertex);
					//System.out.println(nextNeighbor.getLabel()+"ǰ����"+nextNeighbor.getPredecessor().getLabel());
					vertexQueue.add(nextNeighbor);
				}
				if (nextNeighbor.equals(endVertex)) {
					done = true;
				}
			}
		}
		Stack path = new Stack();

		path.push(endVertex);
		while (endVertex.getPredecessor() != null) {
			endVertex = endVertex.getPredecessor();
			path.push(endVertex);
		}
		return path;
	}

    /**
     * ͨ����ȡ���ݿ��е�����������ͼ
     * @param graph
     */
    @SuppressWarnings("unchecked")
	public void createGraphByDataBase(Graph graph) {
    	Connection conn = null;
    	Statement stmt = null;
    	ResultSet rs = null;
    	DBConnection2 db = DBConnection2.getInstance();
    	@SuppressWarnings("rawtypes")
		ArrayList listBusNum = new ArrayList();
    	@SuppressWarnings("rawtypes")
		ArrayList listStID = new ArrayList();
    	String sql = "";
    	try {
    		conn = db.getConnection();
    		stmt = conn.createStatement();
    		listBusNum.add("1");
    		listBusNum.add("2");
    		listBusNum.add("3");
    		listBusNum.add("4");
    		listBusNum.add("5");
    		sql = "select * from stinfo";
    		rs = stmt.executeQuery(sql);
    		while (rs.next() && rs != null) {
    			graph.addVertex(""+rs.getInt("stid"));
    		}
    		for (int i = 0;i < listBusNum.size();i++) {
    			sql = "select * from busst where busnum = '"+listBusNum.get(i)+"' order by storder asc";
    			rs = stmt.executeQuery(sql);
    			@SuppressWarnings("rawtypes")
				Vector vector = new Vector();
    			while (rs.next() && rs != null) {
    				vector.addElement(""+rs.getInt("stid"));
    			}
    			listStID.add(vector);
    		}
    		createEdgeByDataBase(listStID,graph);//ͨ����ȡ���ݿ���վ�����(stid)�����ݹ���ͼ�ı�
    		//System.out.println("ͼ�еĶ������:"+graph.getNumberOfVertices());
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
    			System.out.println("���ݿ�ر�ʧ�ܣ�");
    		}
    	}
    }

    /**
     * ͨ����ȡ���ݿ��е����ݹ���ͼ�ı�
     * @param list
     * @param graph
     */
    @SuppressWarnings("rawtypes")
	public void createEdgeByDataBase(ArrayList list,Graph graph) {
    	for (int i = 0;i < list.size();i++) {
    		@SuppressWarnings("rawtypes")
			Vector vector = (Vector) list.get(i);
    		if (!vector.isEmpty()) {
    			for (int j = 0;j < vector.size();j++) {
    				if (j < (vector.size() -1)) {
    					graph.addEdge(vector.get(j), vector.get(j + 1), 0);//���������(�������Ǿ����,���ǽ�����˫���)
    					graph.addEdge(vector.get(j + 1), vector.get(j), 0);
    				}
    			}
    		}
    	}
    }
    
    /**
     * ��վ���ת��Ϊվ������
     * @param stid
     * @return
     */
    public String exchangeStId(String stid) {
    	Connection conn = null;
    	Statement stmt = null;
    	ResultSet rs = null;
    	DBConnection2 db = DBConnection2.getInstance();
    	String sql = "";
    	String stname = "";
    	try {
    		conn = db.getConnection();
    		stmt = conn.createStatement();
    		sql = "select * from stinfo where stid = '"+stid+"'";
    		rs = stmt.executeQuery(sql);
    		if (rs.next() && rs != null) {
    			stname = rs.getString("stname");
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
    
    /**
     * ��վ���ת��Ϊ���κ�
     * @return
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
	public ArrayList exChangeStIdToBusNum(ArrayList list) {
    	ArrayList arrayList = new ArrayList();
    	Connection conn = null;
    	Statement stmt = null;
    	ResultSet rs = null;
    	DBConnection2 db = DBConnection2.getInstance();
    	String sql = "";
    	try {
    		conn = db.getConnection();
    		stmt = conn.createStatement();
    		if (!list.isEmpty()) {
    			for (int i = 0;i < list.size();i++) {
    				sql = "select busnum from busst,stinfo where stinfo.stid = busst.stid and stinfo.stid = '"+list.get(i)+"'";
    				rs = stmt.executeQuery(sql);
    				Vector vector = new Vector();//�����洢���κŵ�����vector
    				while (rs.next() && rs != null) {
    	    			vector.add(""+rs.getInt("busnum"));
    	    		}
    				arrayList.add(vector);
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
    
	@SuppressWarnings("rawtypes")
	public static void main(String args[]) throws Exception {
		Graph graph = new Graph();

		graph.createGraphByDataBase(graph);
        
		Stack stack = new Stack();
		stack = graph.getShortestPath2("68", "49");//�����õ�վ������

		while (!stack.isEmpty()) {
			//System.out.print(((Vertex)stack.pop()).getLabel() + "   ");
			System.out.print(graph.exchangeStId(""+((Vertex)stack.pop()).getLabel()) + "   ");
		}
		
	}
}
