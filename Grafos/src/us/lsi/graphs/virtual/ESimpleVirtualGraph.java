package us.lsi.graphs.virtual;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import org.jgrapht.GraphType;
import org.jgrapht.graph.DefaultGraphType;
import us.lsi.common.TriFunction;
import us.lsi.path.EGraphPath;
import us.lsi.path.EGraphPath.PathType;

/**
 * <p> Implementaci�n de un grafo virtual simple 
 * Asumimos que los v�rtices son subtipo de VirtualVertex &lt; V,E &gt;
 * Asumimos que las aristas son subtipos de SimpleEdge &lt; V &gt; 
 * </p>
 * 
 * <p> El grafo es inmutable por lo que no est�n permitadas las operaci�n de modificaci�n. Tampoco
 * est�n permitidas las operaciones de consulta de todos los v�rtices o todas las aristas.
 *  Si se invocan alguna de ellas se disparar� 
 * la excepci�n UnsupportedOperationException </p>
 * 
 * @see us.lsi.graphs.virtual.VirtualVertex
 * 
 * 
 * 
 * @author Miguel Toro
 *
 * @param <V> El tipo de los v�rtices
 * @param <E> El tipo de las aristas
 * 
 */
public class ESimpleVirtualGraph<V extends VirtualVertex<V,E,?>, E extends SimpleEdgeAction<V,?>> 
		implements EGraph<V,E> {
	

	private Set<V> vertexSet;
	private Function<E,Double> edgeWeight = null;
	private Function<V,Double> vertexWeight = null;
	private TriFunction<V,E,E,Double> vertexPassWeight= null;	
	private EGraphPath<V,E> path;
	private V startVertex;
	private V endVertex;	
	private PathType pathType;
	private TriFunction<V, Predicate<V>, V, Double> heuristic;
	private Type type;
	private Integer solutionNumber;
	
	
	ESimpleVirtualGraph(EGraphBuilderVirtual<V,E> builder) {
		this.edgeWeight =  builder.edgeWeight;
		this.vertexWeight = builder.vertexWeight;
		this.vertexPassWeight = builder.vertexPassWeight;
		this.startVertex = builder.startVertex;
		this.vertexSet = new HashSet<V>();
		this.vertexSet.add(this.startVertex);
		this.pathType = builder.pathType;
		this.endVertex = builder.endVertex;
		this.path = EGraphPath.ofVertex(this,this.startVertex,this.pathType);
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
		return v->v.greedyEdge();
	}

	@Override
	public TriFunction<V, Predicate<V>, V, Double> heuristic() {
		return this.heuristic;
	}

	@Override
	public boolean containsEdge(E e) {
		return e.source().isNeighbor(e.target());
	}

	@Override
	public boolean containsEdge(V v1, V v2) {
		return v1.isNeighbor(v2);
	}
	@Override
	public boolean containsVertex(V v) {
		return v.isValid();
	}
	@Override
	public V getEdgeSource(E e) {
		return e.source();
	}

	@Override
	public V getEdgeTarget(E e) {
		return e.target();
	}
	
	/**
	 * @param edge es una arista
	 * @return El peso de edge
	 */
	@Override
	public double getEdgeWeight(E edge) {
		Double r;
		if(edgeWeight != null) r = edgeWeight.apply(edge);
		else r = edge.weight();
		return r;
	}
	
	/**
	 * @param vertex es el v�rtice actual
	 * @return El peso de vertex
	 */
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
	public double getVertexPassWeight(V vertex, E edgeIn, E edgeOut) {
		Double r = 0.;
		if(vertexPassWeight != null) r = vertexPassWeight.apply(vertex,edgeIn,edgeOut);
		return r;
	}
	
	
	@Override
	public E getEdge(V v1, V v2) {
		return v1.getEdgeToVertex(v2);
	}	
	@Override
	public Set<E> getAllEdges(V v1, V v2) {
		Set<E> s = new HashSet<>();
		if (v1.isNeighbor(v2)) s = Set.of(this.getEdge(v1, v2));
		return s;
	}	
	
	/** 
	 * @return Conjunto de v�rtices del grafo que se han hecho expl�citos en el constructor.
	 */
	@Override
	public Set<V> vertexSet(){
		return this.vertexSet;
	}
	
	@Override
	public Set<E> edgesOf(V v) {
		return v.edgesOf();
	}
	
	@Override
	public List<E> edgesListOf(V v) {
		if(v.goal()) return List.of();
		return v.edgesListOf();
	}
	
	public V oppositeVertex(E edge, V v) {
		return edge.otherVertex(v);
	}
	
	@Override
	public int degreeOf(V v) {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public int outDegreeOf(V v) {
		return v.edgesListOf().size();
	}

	@Override
	public Set<E> outgoingEdgesOf(V v) {
		return v.edgesListOf().stream().collect(Collectors.toSet());
	}
	
	@Override
	public void setEdgeWeight(E edge, double weight) {
		throw new UnsupportedOperationException();	
	}	

	/**
	 * @throw UnsupportedOperationException
	 */
	@Override
	public int inDegreeOf(V v) {
		throw new UnsupportedOperationException();
	}

	/**
	 * @throw UnsupportedOperationException
	 */
	@Override
	public Set<E> incomingEdgesOf(V v) {
		throw new UnsupportedOperationException();
	}

	
	/**
	 * @throw UnsupportedOperationException
	 */
	@Override
	public GraphType getType() {
		return new DefaultGraphType.Builder().directed().allowMultipleEdges(false).build();
	}	
	
	/**
	 * @throw UnsupportedOperationException
	 */
	@Override
	public Set<E> edgeSet() {
		throw new UnsupportedOperationException();
	}
	
	/**
	 * @throw UnsupportedOperationException
	 */
	@Override
	public E addEdge(V arg0, V arg1) {
		throw new UnsupportedOperationException();
	}

	
	/**
	 * @throw UnsupportedOperationException
	 */
	@Override
	public boolean addEdge(V arg0, V arg1, E arg2) {
		throw new UnsupportedOperationException();
	}


	/**
	 * @throw UnsupportedOperationException
	 */
	@Override
	public boolean addVertex(V arg0) {
		throw new UnsupportedOperationException();
	}

	/**
	 * @throw UnsupportedOperationException
	 */
	@Override
	public boolean removeAllEdges(Collection<? extends E> arg0) {
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @throw UnsupportedOperationException
	 */
	@Override
	public Set<E> removeAllEdges(V arg0, V arg1) {
		throw new UnsupportedOperationException();
	}

	/**
	 * @throw UnsupportedOperationException
	 */
	@Override
	public boolean removeAllVertices(Collection<? extends V> arg0) {
		throw new UnsupportedOperationException();
	}

	/**
	 * @throw UnsupportedOperationException
	 */
	@Override
	public boolean removeEdge(E arg0) {
		throw new UnsupportedOperationException();
	}

	/**
	 * @throw UnsupportedOperationException
	 */
	@Override
	public E removeEdge(V arg0, V arg1) {
		throw new UnsupportedOperationException();
	}

	/**
	 * @throw UnsupportedOperationException
	 */
	@Override
	public boolean removeVertex(V arg0) {
		throw new UnsupportedOperationException();
	}

	@Override
	public V addVertex() {
		throw new UnsupportedOperationException();
	}

	@Override
	public Supplier<E> getEdgeSupplier() {
		throw new UnsupportedOperationException();
	}

	@Override
	public Supplier<V> getVertexSupplier() {
		throw new UnsupportedOperationException();
	}

	@Override
	public EGraphPath<V, E> initialPath() {
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
		return v->v.goal();
	}
	@Override
	public V endVertex() {
		return endVertex;
	}		
	@Override
	public Predicate<V> goalHasSolution() {
		return v->v.goalHasSolution();
	}
	@Override
	public Integer solutionNumber() {
		return this.solutionNumber;
	}

}
