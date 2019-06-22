package com.data.structures.graph1;

import java.util.Iterator;
import java.util.ArrayList;

public class Vertex {
	private Object label;
	private ArrayList<Edge> list;
	private boolean visited;//����Ѿ�������Ϊtrue
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
	 * ȡ�ö���ı�ʶ
	 * @return
	 */
	public Object getLabel() {
		return label;
	}
	
	/**
	 * ���ö����Ƿ񱻱��Ϊ�ѷ���
	 * @return
	 */
	public boolean isVisited() {
		return visited;
	}

	/**
	 * ��һ���������Ӹö�����ָ������
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
	 * �ӵ������з��ظö�������еı�
	 * @return
	 */
	public Iterator<Edge> getNeighborIterator() {
		return list.iterator();
	}
	
	/**
	 * ȡ�ü�¼Ϊǰһ������Ķ���
	 * @return
	 */
	public Vertex getPredecessor() {
		return previousVertex;
	}
	
	/**
	 * ���ǰһ�������Ƿ񱻼�¼
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
