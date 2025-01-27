package us.lsi.tiposrecursivos.ast;

import java.util.HashMap;
import java.util.Map;

import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleDirectedGraph;
import org.jgrapht.nio.Attribute;

import us.lsi.colors.GraphColors;
import us.lsi.colors.GraphColors.Color;
import us.lsi.colors.GraphColors.Shape;

public interface Vertex {
	
	void toGraph(SimpleDirectedGraph<Vertex, DefaultEdge> graph);
	
	default SimpleDirectedGraph<Vertex, DefaultEdge> toGraph() {
		SimpleDirectedGraph<Vertex, DefaultEdge> graph = 
				new SimpleDirectedGraph<>(null,()->new DefaultEdge(), false);
		this.toGraph(graph);
		return graph;
	}
	
	String label();
	
	default Map<String,Attribute> styleAndShape() {
		return Map.of();
	}

	public static Map<String,Attribute> circle(Color c) {
		Map<String,Attribute> m1 = new HashMap<>(GraphColors.shape(Shape.doublecircle));
		Map<String,Attribute> m2 = GraphColors.color(c);
		m1.putAll(m2);
		return m1;
	}
	
	public static Vertex of(String label,Map<String,Attribute> style) {
		return VertexI.of(label,style);
	}
	
	
	public static record VertexI(Integer id,String label, Map<String,Attribute> styleAndShape) implements Vertex {
		public static Integer n = 0;
		public static Vertex of(String label,Map<String,Attribute> styleAndShape) {
			Vertex v = new VertexI(n,label,styleAndShape);
			VertexI.n = VertexI.n+1;
			return v;
		}
		public void toGraph(SimpleDirectedGraph<Vertex, DefaultEdge> graph) {};	
	}
}
