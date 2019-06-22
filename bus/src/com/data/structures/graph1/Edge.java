package com.data.structures.graph1;

public class Edge {
	private Vertex vertex;
	private double weight;
	
	public Edge(Vertex endVertex,double edgeWeight) {
		this.vertex = endVertex;
		this.weight = edgeWeight;
	}
	
	public Vertex getEndVertex() {
		return vertex;
	}
	
	public double getWeight() {
		return weight;
	}
	
	public boolean equalsEdge(Object other) {
		Edge otherEdge = (Edge) other;
		return weight == otherEdge.weight;
	}
}
