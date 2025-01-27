package us.lsi.graphs.views;

import java.util.Collection;
import java.util.Set;
import java.util.function.Supplier;

import org.jgrapht.Graph;
import org.jgrapht.GraphType;

public class CompleteGraphView<V, E> implements Graph<V, E> {

	private Graph<V, E> graph;
	private Supplier<E> edgeWeightFactory;
	public Double weight = 2000.;
	
	public static <V, E> CompleteGraphView<V, E> of(Graph<V, E> graph, Supplier<E> edgeWeightFactory) {
		return new CompleteGraphView<V, E>(graph, 
				edgeWeightFactory);
	}
	
	private CompleteGraphView(Graph<V, E> graph, Supplier<E> edgeWeightFactory) {
		super();
		this.graph = graph;
		this.edgeWeightFactory = edgeWeightFactory;
	}
	
	public void addEdges() {
		Set<V> vertices = graph.vertexSet();
		for (V v0 : vertices) {
			for (V v1 : vertices) {
				if (!v0.equals(v1)) {
					if (graph.containsEdge(v0, v1)) continue;
					E edge = this.edgeWeightFactory.get();
					graph.addEdge(v0, v1, edge);
					graph.setEdgeWeight(edge, weight);
				}
			}
		}
	}

	public boolean addEdge(V arg0, V arg1, E arg2) {
		return graph.addEdge(arg0, arg1, arg2);
	}

	public E addEdge(V arg0, V arg1) {
		return graph.addEdge(arg0, arg1);
	}

	public V addVertex() {
		return graph.addVertex();
	}

	public boolean addVertex(V arg0) {
		return graph.addVertex(arg0);
	}

	public boolean containsEdge(E arg0) {
		return graph.containsEdge(arg0);
	}

	public boolean containsEdge(V arg0, V arg1) {
		return graph.containsEdge(arg0, arg1);
	}

	public boolean containsVertex(V arg0) {
		return graph.containsVertex(arg0);
	}

	public int degreeOf(V arg0) {
		return graph.degreeOf(arg0);
	}

	public Set<E> edgeSet() {
		return graph.edgeSet();
	}

	public Set<E> edgesOf(V arg0) {
		return graph.edgesOf(arg0);
	}

	public Set<E> getAllEdges(V arg0, V arg1) {
		return graph.getAllEdges(arg0, arg1);
	}

	public E getEdge(V v0, V v1) {
		E edge = null;
		if(v0.equals(v1)) return null;
		if (graph.containsEdge(v0, v1))
			edge = graph.getEdge(v0, v1);
		else {
			edge = this.edgeWeightFactory.get();
			graph.addEdge(v0, v1, edge);
			graph.setEdgeWeight(edge, weight);
		}
		return edge;
	}

	public V getEdgeSource(E arg0) {
		return graph.getEdgeSource(arg0);
	}

	public Supplier<E> getEdgeSupplier() {
		return graph.getEdgeSupplier();
	}

	public V getEdgeTarget(E arg0) {
		return graph.getEdgeTarget(arg0);
	}

	public double getEdgeWeight(E edge) {
		return graph.getEdgeWeight(edge);
	}

	public GraphType getType() {
		return graph.getType();
	}

	public Supplier<V> getVertexSupplier() {
		return graph.getVertexSupplier();
	}

	public int inDegreeOf(V arg0) {
		return graph.inDegreeOf(arg0);
	}

	public Set<E> incomingEdgesOf(V arg0) {
		return graph.incomingEdgesOf(arg0);
	}

	public int outDegreeOf(V arg0) {
		return graph.outDegreeOf(arg0);
	}

	public Set<E> outgoingEdgesOf(V arg0) {
		return graph.outgoingEdgesOf(arg0);
	}

	public boolean removeAllEdges(Collection<? extends E> arg0) {
		return graph.removeAllEdges(arg0);
	}

	public Set<E> removeAllEdges(V arg0, V arg1) {
		return graph.removeAllEdges(arg0, arg1);
	}

	public boolean removeAllVertices(Collection<? extends V> arg0) {
		return graph.removeAllVertices(arg0);
	}

	public boolean removeEdge(E arg0) {
		return graph.removeEdge(arg0);
	}

	public E removeEdge(V arg0, V arg1) {
		return graph.removeEdge(arg0, arg1);
	}

	public boolean removeVertex(V arg0) {
		return graph.removeVertex(arg0);
	}

	public void setEdgeWeight(E arg0, double arg1) {
		graph.setEdgeWeight(arg0, arg1);
	}

	public void setEdgeWeight(V sourceVertex, V targetVertex, double weight) {
		graph.setEdgeWeight(sourceVertex, targetVertex, weight);
	}

	public Set<V> vertexSet() {
		return graph.vertexSet();
	}

	
}
