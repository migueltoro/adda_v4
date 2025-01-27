package us.lsi.graphs;

import java.util.Map;

import org.jgrapht.Graph;


public class GraphData {
	
	public static Graph<Integer,SimpleEdge<Integer>> graph;
	public static Map<Integer,Double> vertexWeight;
	public static Integer n;
	public static Integer m;
	public static Integer origin;
	public static Integer target;
	
	public static Boolean containsEdge(Integer i, Integer j) {
		return graph.containsEdge(i,j);
	}
	
	public static Double edgeWeight(Integer i, Integer j) {
		return graph.getEdge(i,j).weight();
	}
	
	public static Boolean containsVertex(Integer i) {
		return graph.containsVertex(i);
	}
	
	public static Double vertexWeight(Integer i) {
		return vertexWeight.get(i);
	}
	
	public static Integer getN() {
		return n;
	}
	
	public static Integer getM() {
		return m;
	}
	
	public static Integer origin() {
		return origin;
	}
	
	public static Integer target() {
		return target;
	}



}
