package us.lsi.graphs.virtual;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import org.jgrapht.Graph;
import org.jgrapht.GraphType;
import org.jgrapht.Graphs;

import us.lsi.common.TriFunction;
import us.lsi.path.EGraphPath;
import us.lsi.path.EGraphPath.PathType;


public class EGraphOfGraph<V,E,G extends Graph<V,E>> implements EGraph<V,E> {
	
	private G graph;
	private Function<E,Double> edgeWeight = null;
	private Function<V,Double> vertexWeight = null;
	private TriFunction<V,E,E,Double> vertexPassWeight= null;
	private EGraphPath<V,E> path;
	private V startVertex;
	private Predicate<V> goal;
	private V endVertex;
	private Predicate<V> goalHasSolution;
	private PathType pathType;
	private Function<V,E> greedyEdge;
	private TriFunction<V, Predicate<V>, V, Double> heuristic;
	private Type type;
	private Integer solutionNumber;

	
	EGraphOfGraph(EGraphBuilderGraph<G,V,E> builder){
		this.graph = builder.graph;
		this.edgeWeight = builder.edgeWeight;
		this.vertexWeight = builder.vertexWeight;
		this.vertexPassWeight = builder.vertexPassWeight;
		this.startVertex = builder.startVertex;
		this.goal = builder.goal;
		this.endVertex = builder.endVertex;
		this.goalHasSolution = builder.goalHasSolution;
		this.pathType = builder.pathType;
		this.greedyEdge = builder.greedyEdge;
		this.heuristic = builder.heuristic;
		this.type = builder.type;
		this.solutionNumber = builder.solutionNumber;
	}
	
	@Override
	public Type type() {
		return type;
	}
	
	@Override
	public Function<V, E> greedyEdge() {
		return this.greedyEdge;
	}

	@Override
	public TriFunction<V, Predicate<V>, V, Double> heuristic() {
		return this.heuristic;
	}

	@Override
	public boolean addEdge(V arg0, V arg1, E arg2) {
		return graph.addEdge(arg0, arg1, arg2);
	}
	@Override
	public E addEdge(V arg0, V arg1) {
		return graph.addEdge(arg0, arg1);
	}
	@Override
	public V addVertex() {
		return graph.addVertex();
	}
	@Override
	public boolean addVertex(V arg0) {
		return graph.addVertex(arg0);
	}
	@Override
	public boolean containsEdge(E arg0) {
		return graph.containsEdge(arg0);
	}
	@Override
	public boolean containsEdge(V arg0, V arg1) {
		return graph.containsEdge(arg0, arg1);
	}
	@Override
	public boolean containsVertex(V arg0) {
		return graph.containsVertex(arg0);
	}
	@Override
	public int degreeOf(V arg0) {
		return graph.degreeOf(arg0);
	}
	@Override
	public Set<E> edgeSet() {
		return graph.edgeSet();
	}
	@Override
	public Set<E> edgesOf(V v) {
		return graph.edgesOf(v);
	}
	@Override
	public Set<E> getAllEdges(V arg0, V arg1) {
		return graph.getAllEdges(arg0, arg1);
	}
	@Override
	public E getEdge(V arg0, V arg1) {
		return graph.getEdge(arg0, arg1);
	}
	@Override
	public V getEdgeSource(E arg0) {
		return graph.getEdgeSource(arg0);
	}
	@Override
	public Supplier<E> getEdgeSupplier() {
		return graph.getEdgeSupplier();
	}
	@Override
	public V getEdgeTarget(E arg0) {
		return graph.getEdgeTarget(arg0);
	}
	@Override
	public GraphType getType() {
		return graph.getType();
	}
	@Override
	public Supplier<V> getVertexSupplier() {
		return graph.getVertexSupplier();
	}
	@Override
	public int inDegreeOf(V arg0) {
		return graph.inDegreeOf(arg0);
	}
	@Override
	public Set<E> incomingEdgesOf(V arg0) {
		return graph.incomingEdgesOf(arg0);
	}
	@Override
	public int outDegreeOf(V arg0) {
		return graph.outDegreeOf(arg0);
	}
	@Override
	public Set<E> outgoingEdgesOf(V arg0) {
		return graph.outgoingEdgesOf(arg0);
	}
	@Override
	public boolean removeAllEdges(Collection<? extends E> arg0) {
		return graph.removeAllEdges(arg0);
	}
	@Override
	public Set<E> removeAllEdges(V arg0, V arg1) {
		return graph.removeAllEdges(arg0, arg1);
	}
	@Override
	public boolean removeAllVertices(Collection<? extends V> arg0) {
		return graph.removeAllVertices(arg0);
	}
	@Override
	public boolean removeEdge(E arg0) {
		return graph.removeEdge(arg0);
	}
	@Override
	public E removeEdge(V arg0, V arg1) {
		return graph.removeEdge(arg0, arg1);
	}
	@Override
	public boolean removeVertex(V arg0) {
		return graph.removeVertex(arg0);
	}
	@Override
	public void setEdgeWeight(E arg0, double arg1) {
		graph.setEdgeWeight(arg0, arg1);
	}
	@Override
	public Set<V> vertexSet() {
		return graph.vertexSet();
	}	
	@Override
	public double getEdgeWeight(E edge) {
		Double r;
		if(edgeWeight != null) r = edgeWeight.apply(edge);
		else r = graph.getEdgeWeight(edge);
		return r;
	}
	
	/**
	 * @param vertex es el v�rtice actual
	 * @return El peso de vertex
	 */
	@Override
	public double getVertexWeight(V vertex) {
		Double r = 0.;
		if(vertexWeight != null) r = vertexWeight.apply(vertex);
		return r;
	}
	/**
	 * @param vertex El v�rtice actual
	 * @param edgeIn Una arista entrante o incidente en el v�rtice actual. Es null en el v�rtice inicial.
	 * @param edgeOut Una arista saliente o incidente en el v�rtice actual. Es null en el v�rtice final.
	 * @return El peso asociado al v�rtice suponiendo las dos aristas dadas. 
	 */
	@Override
	public double getVertexPassWeight(V vertex, E edgeIn, E edgeOut) {
		Double r = 0.;
		if(vertexPassWeight != null) r = vertexPassWeight.apply(vertex,edgeIn,edgeOut);
		return r;
	}
	@Override
	public List<E> edgesListOf(V v) {
		if(graph.getType().isUndirected()) return graph.edgesOf(v).stream().collect(Collectors.toList());
		else return graph.outgoingEdgesOf(v).stream().collect(Collectors.toList());
	}
	@Override
	public EGraphPath<V, E> initialPath() {
		if(this.path == null) {
			this.path = EGraphPath.ofVertex(this,this.startVertex,this.pathType);
		}
		return this.path.copy();
	}
	@Override
	public V startVertex() {
		return startVertex;
	}
	@Override
	public PathType pathType() {
		return pathType;
	}
	@Override
	public Predicate<V> goal() {
		return goal;
	}
	@Override
	public V endVertex() {
		return endVertex;
	}
	
	@Override
	public Predicate<V> goalHasSolution() {
		return goalHasSolution;
	}

	@Override
	public V oppositeVertex(E edge, V v) {
		return Graphs.getOppositeVertex(graph, edge, v);
	}

	@Override
	public Integer solutionNumber() {
		return this.solutionNumber;
	}

}
