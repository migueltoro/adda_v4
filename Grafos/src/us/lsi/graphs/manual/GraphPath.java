package us.lsi.graphs.manual;

import java.util.ArrayList;
import java.util.List;

public class GraphPath<V,E> {
	
	public static <V, E> GraphPath<V, E> of(List<V> vertices, List<E> edges, Double weight) {
		return new GraphPath<V, E>(new ArrayList<>(vertices), new ArrayList<>(edges), weight);
	}

	private List<V> vertices;
	private List<E> edges;
	private Double weight;
	
	private GraphPath(List<V> vertices, List<E> edges, Double weight) {
		this.vertices = vertices;
		this.edges = edges;
		this.weight = weight;
	}
	
	public V endVertex() {
		return this.vertices.get(this.vertices.size()-1);
	}

	public V startVertex() {
		return this.vertices.get(0);
	}
	
	public Double weight() {
		return this.weight;
	}
	
	public List<V> vertices() {
		return vertices;
	}
	
	public List<E> edges() {
		return edges;
	}
	
	public GraphPath<V, E> copy() {
		return GraphPath.of(this.vertices, this.edges, this.weight);
	}
}
