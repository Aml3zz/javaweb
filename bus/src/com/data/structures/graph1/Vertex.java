package com.data.structures.graph1;

import java.util.Iterator;
import java.util.ArrayList;

public class Vertex {
	private Object label;
	private ArrayList<Edge> list;
	private boolean visited;//如果已经访问则为true
	private Vertex previousVertex;
	private double cost;
	
	public Vertex(Object vertexLabel) {
		label = vertexLabel;
		list = new ArrayList<Edge>();
		visited = false;
		previousVertex = null;
		cost = 0;
	}
	
	/**
	 * 取得顶点的标识
	 * @return
	 */
	public Object getLabel() {
		return label;
	}
	
	/**
	 * 检查该顶点是否被标记为已访问
	 * @return
	 */
	public boolean isVisited() {
		return visited;
	}

	/**
	 * 用一个条边连接该顶点与指定顶点
	 * @param endVertex
	 * @param edgeWeight
	 */
	public void addEdge(Vertex endVertex,double edgeWeight) {
		list.add(new Edge(endVertex,edgeWeight));
		endVertex.setPredecessor(this);
	}
	
	void setPredecessor(Vertex vertex) {
		this.previousVertex = vertex;
	}

	/**
	 * 从迭代器中返回该顶点的所有的边
	 * @return
	 */
	public Iterator<Edge> getNeighborIterator() {
		return list.iterator();
	}
	
	/**
	 * 取得记录为前一个顶点的顶点
	 * @return
	 */
	public Vertex getPredecessor() {
		return previousVertex;
	}
	
	/**
	 * 检查前一个顶点是否被记录
	 * @return
	 */
	public boolean hasPredecessor() {
		return previousVertex.isVisited();
	}
	
	public void setCost(double newCost) {
		this.cost = newCost;
	}
	
	public double getCost() {
		return this.cost;
	}
/*
	public void visit() {
		isVisited();		
	}*/

	public void visit() {
		// TODO Auto-generated method stub
		visited = true;
		
	}

	public void unvisit() {
		// TODO Auto-generated method stub
		visited = false;
	}
}
