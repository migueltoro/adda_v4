package us.lsi.graphs.views;

import java.util.List;

import org.jgrapht.GraphPath;
import org.jgrapht.graph.SimpleDirectedGraph;

public class PathToGraph {
	
	public static <V,E> SimpleDirectedGraph<V,E> addPathToGraph(SimpleDirectedGraph<V,E> g, GraphPath<V,E> gp){
		List<V> vertices = gp.getVertexList();
		List<E> edges = gp.getEdgeList();
		Integer n = edges.size();
		g.addVertex(vertices.get(0));
		for(int i = 0; i<n; i++) {
			if(!g.containsVertex(vertices.get(i+1))) 
				g.addVertex(vertices.get(i+1));
			if(!g.containsEdge(vertices.get(i),vertices.get(i+1))) 
				g.addEdge(vertices.get(i),vertices.get(i+1),edges.get(i));
		}
		return g;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

