package us.lsi.graphs.views;

import java.util.Collection;
import java.util.Set;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import org.jgrapht.Graph;
import org.jgrapht.GraphType;


public class SubGraphView<V, E, G extends Graph<V,E>> implements Graph<V, E> {

	public static <V, E, G extends Graph<V,E>> SubGraphView<V, E, G> of(G graph, Predicate<V> vertices, Predicate<E> edges) {
		return  new SubGraphView<V, E, G>(graph, vertices, edges);
	}
	
	public static <V, E, G extends Graph<V,E>> SubGraphView<V, E, G> of(G graph, Predicate<V> vertices) {
		return  new SubGraphView<V, E, G>(graph, vertices,e->true);
	}
	
	public static <V, E, G extends Graph<V,E>> SubGraphView<V, E, G> ofEdges(G graph, Predicate<E> edges) {
		return  new SubGraphView<V, E, G>(graph, v->true, edges);
	}
	
	private G graph;
	private Predicate<V> vertices;
	private Predicate<E> edges;
	
	private SubGraphView(G graph, Set<V> vertices) {
		super();
		this.graph = graph;
		this.vertices = v->vertices.contains(v);
		this.edges = e->true;
	}

	private SubGraphView(G graph, Predicate<V> vertices, Predicate<E> edges) {
		super();
		this.graph = graph;
		this.vertices = vertices==null?v->true:vertices;
		this.edges = edges==null?e->true:edges;
	}

	public boolean addEdge(V v1, V v2, E e) {
		return graph.addEdge(v1, v2,e);
	}

	public E addEdge(V v1, V v2) {
		return graph.addEdge(v1, v2);
	}

	public V addVertex() {
		return graph.addVertex();
	}

	public boolean addVertex(V v0) {
		return graph.addVertex(v0);
	}

	public boolean containsEdge(E e) {
		return graph.containsEdge(e) && 
				edges.test(e) &&
				vertices.test(this.getEdgeSource(e)) && 
				vertices.test(this.getEdgeTarget(e));
	}

	public boolean containsEdge(V v1, V v2) {
		return graph.containsEdge(v1,v2) && 
				edges.test(graph.getEdge(v1,v2)) &&
				vertices.test(v1) && 
				vertices.test(v2);
	}

	public boolean containsVertex(V v) {
		return graph.containsVertex(v) &&
				vertices.test(v);
	}

	public int degreeOf(V v) {
		return graph.edgesOf(v).size();
	}

	public Set<E> edgeSet() {
		return graph.edgeSet().stream().filter(e->this.containsEdge(e)).collect(Collectors.toSet());
	}

	public Set<E> edgesOf(V v) {
		return graph.edgesOf(v).stream()
				.filter(e->this.containsEdge(e))
				.collect(Collectors.toSet());
	}

	public Set<E> getAllEdges(V v1, V v2) {
		return graph.getAllEdges(v1, v2).stream().filter(e->this.containsEdge(e)).collect(Collectors.toSet());
	}

	public E getEdge(V v1, V v2) {
		return graph.getEdge(v1, v2);
	}

	public V getEdgeSource(E e) {
		return graph.getEdgeSource(e);
	}

	public Supplier<E> getEdgeSupplier() {
		throw new IllegalArgumentException("Metodo no permitido");
	}

	public V getEdgeTarget(E e) {
		return graph.getEdgeTarget(e);
	}

	public double getEdgeWeight(E e) {
		return graph.getEdgeWeight(e);
	}

	public GraphType getType() {
		return graph.getType();
	}

	public Supplier<V> getVertexSupplier() {
		throw new IllegalArgumentException("Metodo no permitido");
	}

	public int inDegreeOf(V v) {
		return this.incomingEdgesOf(v).size();
	}

	public Set<E> incomingEdgesOf(V v) {
		Set<E> edges = graph.incomingEdgesOf(v);
		return edges.stream().filter(e->this.containsEdge(e)).collect(Collectors.toSet());
	}

	public int outDegreeOf(V v) {
		return this.outgoingEdgesOf(v).size();
	}

	public Set<E> outgoingEdgesOf(V v) {
		Set<E> edges = graph.outgoingEdgesOf(v);
		return edges.stream().filter(e->this.containsEdge(e)).collect(Collectors.toSet());
	}

	public boolean removeAllEdges(Collection<? extends E> c) {
		return graph.removeAllEdges(c);
	}

	public Set<E> removeAllEdges(V v1, V v2) {
		return graph.removeAllEdges(v1, v2);
	}

	public boolean removeAllVertices(Collection<? extends V> c) {
		return graph.removeAllVertices(c);
	}

	public boolean removeEdge(E e) {
		return graph.removeEdge(e);
	}

	public E removeEdge(V v1, V v2) {
		return graph.removeEdge(v1, v2);
	}

	public boolean removeVertex(V v) {
		return graph.removeVertex(v);
	}

	public void setEdgeWeight(E e, double w) {
		graph.setEdgeWeight(e, w);
	}

	public Set<V> vertexSet() {
		return graph.vertexSet().stream().filter(v->vertices.test(v)).collect(Collectors.toSet());
	}

	@Override
	public String toString() {
		return String.format("%s === %s",this.vertexSet(),this.edgeSet());
	}
}
