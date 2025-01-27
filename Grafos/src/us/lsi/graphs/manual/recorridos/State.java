package us.lsi.graphs.manual.recorridos;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import us.lsi.graphs.manual.GraphPath;

public class State<V, E> {

	public static <V, E> State<V, E> of(V vertex, Double value) {
		return new State<V, E>(vertex, value);
	}

	private List<V> vertices;
	private Set<V> vertexSet;
	private List<E> edges;
	private List<Double> values;
	private Double accValue;

	private State(V vertex, Double value) {
		super();
		this.vertices = new ArrayList<V>();
		this.vertices.add(vertex);
		this.vertexSet = new HashSet<>();
		this.vertexSet.add(vertex);
		this.edges = new ArrayList<E>();
		this.values = new ArrayList<>();
		this.values.add(value);
		this.accValue = 0.;
	}
	
	public void add(V vertex, E edge, Double value) {
		this.vertices.add(vertex);
		this.vertexSet.add(vertex);
		this.edges.add(edge);
		this.values.add(value);
		this.accValue += value;
	}
	
	public V remove() {
		V vertex = this.vertices.remove(this.vertices.size() - 1);
		this.vertexSet.remove(vertex);
		this.edges.remove(this.edges.size() - 1);
		Double value = this.values.remove(this.values.size() - 1);
		this.accValue -= value;
		return vertex;
	}
	
	public V vertex() {
		return this.vertices.get(this.vertices.size() - 1);
	}
	
	public V startVertex() {
		return this.vertices.get(0);
	}
	
	public Double accValue() {
		return this.accValue;
	}
	
	public List<V> vertices() {
		return vertices;
	}
	
	public Boolean contains(V vertex) {
		return vertexSet.contains(vertex);
	}
	
	public List<E> edges() {
		return edges;
	}
	
	public GraphPath<V, E> path() {
		return GraphPath.of(this.vertices, this.edges, this.accValue());
	}

}

