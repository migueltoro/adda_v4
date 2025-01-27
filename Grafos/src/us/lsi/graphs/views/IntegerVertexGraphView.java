package us.lsi.graphs.views;


import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.jgrapht.Graph;
import org.jgrapht.GraphType;

import us.lsi.graphs.SimpleEdge;

public class IntegerVertexGraphView<V,E> implements Graph<Integer,SimpleEdge<Integer>>{
	
	public static <V, E> IntegerVertexGraphView<V, E> of(Graph<V, E> graph) {
		return new IntegerVertexGraphView<V, E>(graph);
	}

	private final Map<V,Integer> mapIndex;
	private final List<V> vertices;
	public final Integer n;
	private Graph<V,E> graph;
	
	private IntegerVertexGraphView(Graph<V, E> graph) {
		super();
		this.graph = graph;
		this.vertices = graph.vertexSet().stream().collect(Collectors.toList());
		this.mapIndex = IntStream.range(0,this.vertices.size()).boxed()
				.collect(Collectors.toMap(x->this.vertices.get(x),x->x));
		this.n = vertices.size();
	}

	@Override
	public SimpleEdge<Integer> addEdge(Integer arg0, Integer arg1) {
		throw new IllegalArgumentException("Metodo no permitido");
	}

	@Override
	public boolean addEdge(Integer arg0, Integer arg1, SimpleEdge<Integer> arg2) {
		throw new IllegalArgumentException("Metodo no permitido");
	}

	@Override
	public Integer addVertex() {
		throw new IllegalArgumentException("Metodo no permitido");
	}

	@Override
	public boolean addVertex(Integer arg0) {
		throw new IllegalArgumentException("Metodo no permitido");
	}

	@Override
	public boolean containsEdge(SimpleEdge<Integer> e) {
		throw new IllegalArgumentException("Metodo no permitido");
	}

	@Override
	public boolean containsEdge(Integer v1, Integer v2) {
		return graph.containsEdge(vertices.get(v1), vertices.get(v2));
	}

	@Override
	public boolean containsVertex(Integer v) {
		return v>=0 && v<vertices.size();
	}

	@Override
	public int degreeOf(Integer v) {
		return graph.degreeOf(vertices.get(v));
	}

	@Override
	public Set<SimpleEdge<Integer>> edgeSet() {
		return graph.edgeSet().stream()
				.map(e ->SimpleEdge.of(mapIndex.get(graph.getEdgeSource(e)),mapIndex.get(graph.getEdgeTarget(e)), graph.getEdgeWeight(e)))
				.collect(Collectors.toSet());
	}

	@Override
	public Set<SimpleEdge<Integer>> edgesOf(Integer v) {
		return graph.edgesOf(vertices.get(v)).stream()
				.map(e ->SimpleEdge.of(mapIndex.get(graph.getEdgeSource(e)),mapIndex.get(graph.getEdgeTarget(e)), graph.getEdgeWeight(e)))
				.collect(Collectors.toSet());
	}

	@Override
	public Set<SimpleEdge<Integer>> getAllEdges(Integer v1, Integer v2) {
		throw new IllegalArgumentException("Metodo no permitido");
	}

	@Override
	public SimpleEdge<Integer> getEdge(Integer v1, Integer v2) {
		E edge = graph.getEdge(vertices.get(v1), vertices.get(v2));
		Double w = graph.getEdgeWeight(edge);
		return SimpleEdge.of(v1,v2, w);
	}

	@Override
	public Integer getEdgeSource(SimpleEdge<Integer> e) {
		return e.source();
	}

	@Override
	public Supplier<SimpleEdge<Integer>> getEdgeSupplier() {
		throw new IllegalArgumentException("Metodo no permitido");
	}

	@Override
	public Integer getEdgeTarget(SimpleEdge<Integer> e) {
		return e.target();
	}

	@Override
	public double getEdgeWeight(SimpleEdge<Integer> e) {
		return e.weight();
	}
	
	public double getEdgeWeight(int i, int j) {
		E e = graph.getEdge(vertices.get(i), vertices.get(j));
		return graph.getEdgeWeight(e);
	}

	public V getVertex(int v) {
		return vertices.get(v);
	}
	
	public Integer getIndex(V v) {
		return mapIndex.get(v);
	}
	
	@Override
	public GraphType getType() {
		return graph.getType();
	}

	@Override
	public Supplier<Integer> getVertexSupplier() {
		throw new IllegalArgumentException("Metodo no permitido");
	}

	@Override
	public int inDegreeOf(Integer v) {
		return graph.inDegreeOf(vertices.get(v));
	}

	@Override
	public Set<SimpleEdge<Integer>> incomingEdgesOf(Integer v) {
		return graph.incomingEdgesOf(vertices.get(v))
				.stream()
				.map(e ->SimpleEdge.of(mapIndex.get(graph.getEdgeSource(e)),mapIndex.get(graph.getEdgeTarget(e)), graph.getEdgeWeight(e)))
				.collect(Collectors.toSet());
	}

	@Override
	public int outDegreeOf(Integer v) {
		return graph.outDegreeOf(vertices.get(v));
	}

	@Override
	public Set<SimpleEdge<Integer>> outgoingEdgesOf(Integer v) {
		return graph.outgoingEdgesOf(vertices.get(v))
				.stream()
				.map(e ->SimpleEdge.of(mapIndex.get(graph.getEdgeSource(e)),mapIndex.get(graph.getEdgeTarget(e)), graph.getEdgeWeight(e)))
				.collect(Collectors.toSet());
	}

	@Override
	public boolean removeAllEdges(Collection<? extends SimpleEdge<Integer>> arg0) {
		throw new IllegalArgumentException("Metodo no permitido");
	}

	@Override
	public Set<SimpleEdge<Integer>> removeAllEdges(Integer arg0, Integer arg1) {
		throw new IllegalArgumentException("Metodo no permitido");
	}

	@Override
	public boolean removeAllVertices(Collection<? extends Integer> arg0) {
		throw new IllegalArgumentException("Metodo no permitido");
	}

	@Override
	public boolean removeEdge(SimpleEdge<Integer> arg0) {
		throw new IllegalArgumentException("Metodo no permitido");
	}

	@Override
	public SimpleEdge<Integer> removeEdge(Integer arg0, Integer arg1) {
		throw new IllegalArgumentException("Metodo no permitido");
	}

	@Override
	public boolean removeVertex(Integer arg0) {
		throw new IllegalArgumentException("Metodo no permitido");
	}

	@Override
	public void setEdgeWeight(SimpleEdge<Integer> e, double w) {
		throw new IllegalArgumentException("Metodo no permitido");
	}

	@Override
	public Set<Integer> vertexSet() {
		return graph.vertexSet().stream()
				.map(v->this.getIndex(v))
				.collect(Collectors.toSet());
	}

	@Override
	public String toString() {
		return String.format("(%s,%s)",this.vertexSet(),this.edgeSet());
	}	
	
	public Integer index(V v) {
		return this.mapIndex.get(v);
	}
	
	public Integer index(Predicate<V> p) {
		return this.mapIndex.get(this.vertex(p));
	}
	
	public V vertex(Integer i) {
		return this.vertices.get(i);
	}
	
	public V vertex(Predicate<V> p) {
		return this.vertices.stream().filter(p).findFirst().get();
	}

}

