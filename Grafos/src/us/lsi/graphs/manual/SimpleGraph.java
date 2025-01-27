package us.lsi.graphs.manual;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

import us.lsi.common.Pair;

public class SimpleGraph<V, E> implements RealGraph<V, E>{

	private Set<V> vertexSet;
	private Set<E> edgeSet;
	private Map<Pair<V, V>, E> edgesDict;
	private Map<V, Set<V>> neighbors;
	private Map<V, Set<V>> predecessors;
	private Map<E, V> sources;
	private Map<E, V> targets;
	private VirtualGraph.GraphType graphType;
	private Function<E, Double> weight;
	private VirtualGraph.TraverseType traverseType;

	public SimpleGraph(VirtualGraph.GraphType graphType, Function<E, Double> weight, VirtualGraph.TraverseType traverseType) {
		super();
		this.vertexSet = new HashSet<>();
		this.edgeSet = new HashSet<>();
		this.edgesDict = new HashMap<>();
		this.neighbors = new HashMap<>();
		this.predecessors = new HashMap<>();
		this.sources = new HashMap<>();
		this.targets = new HashMap<>();
		this.graphType = graphType;
		this.weight = weight;
		this.traverseType = traverseType;
	}

	private void addNeighbors(V source, V target) {
		if (this.neighbors.containsKey(source))
			this.neighbors.get(source).add(target);
		else {
			Set<V> st = new HashSet<V>();
			st.add(target);
			this.neighbors.put(source, st);
		}
	}

	private void addPredecessors(V source, V target) {
		if (this.predecessors.containsKey(target))
			this.predecessors.get(target).add(source);
		else {
			Set<V> st = new HashSet<V>();
			st.add(source);
			this.predecessors.put(target, st);
		}
	}

	public Boolean addEdge(V source, V target, E e) {
		assert this.vertexSet.contains(source) : String.format("Vertice %s no esta en el grafo", source);
		assert this.vertexSet.contains(target) : String.format("Vertice %s no esta en el grafo", target);
		assert source != target : String.format("No se pueden añadir bucles %s - %s", source, target);
		if(this.edgesDict.containsKey(Pair.of(source, target))) return false;
		this.edgeSet.add(e);
		this.edgesDict.put(Pair.of(source, target), e);
		this.addNeighbors(source, target);
		this.sources.put(e, source);
		this.targets.put(e, target);
		if (this.graphType == VirtualGraph.GraphType.UNDIRECTED) {
			this.edgesDict.put(Pair.of(target, source), e);
			this.addNeighbors(target, source);
		}
		if (this.graphType == VirtualGraph.GraphType.DIRECTED) {
			this.addPredecessors(target, source);
		}
		return true;
	}

	public Boolean addVertex(V vertex) {
		if (this.vertexSet.contains(vertex)) {
			return false;
		} else {
			this.vertexSet.add(vertex);
			return true;
		}
	}

	public V edgeSource(E e) {
		return this.sources.get(e);
	}

	public V edgeTarget(E e) {
		return this.targets.get(e);
	}

	public E edge(V source, V target) {
		return this.edgesDict.get(Pair.of(source, target));
	}

	public Set<V> predecessors(V vertex) {
		return this.predecessors.getOrDefault(vertex,Set.of());
	}
	
	public Set<V> neighbors(V vertex) {
		return this.neighbors.getOrDefault(vertex,Set.of());
	}

	public Set<V> successors(V vertex) {
		if(this.traverseType.equals(VirtualGraph.TraverseType.FORWARD)) 
            return this.neighbors(vertex);
        else 
            return this.predecessors(vertex);		    
	}

	public Double edgeWeight(V source, V target) {
		E e = this.edge(source, target);
		return this.weight.apply(e);
	}

	public Set<V> vertexSet() {
		return this.vertexSet;
	}

	public Set<E> edgeSet() {
		return this.edgeSet;
	}

	public Boolean containsEdge(V source, V target) {
		return this.edgesDict.containsKey(Pair.of(source, target));
	}

	public VirtualGraph.GraphType graphType() {
		return this.graphType;
	}

	public VirtualGraph.TraverseType traverseType() {
		return this.traverseType;
	}

	public Function<E, Double> weight() {
		return this.weight;
	}

	public SimpleGraph<V, E> inverseGraph() {
		if (this.graphType == VirtualGraph.GraphType.DIRECTED) {
			return this;
		}
		SimpleGraph<V, E> g = new SimpleGraph<>(this.graphType, this.weight, this.traverseType);
		for (V v : this.vertexSet()) {
			g.addVertex(v);
		}
		for (E e : this.edgeSet()) {
			V source = this.edgeSource(e);
			V target = this.edgeTarget(e);
			g.addEdge(target, source, e);
		}
		return g;
	}

	public SimpleGraph<V, E> subgraph(Set<V> vertices) {
		SimpleGraph<V, E> g = new SimpleGraph<>(this.graphType, this.weight, this.traverseType);
		for (V v : vertices) {
			g.addVertex(v);
		}
		for (E e : this.edgeSet()) {
			V s = this.edgeSource(e);
			V t = this.edgeTarget(e);
			if (vertices.contains(s) && vertices.contains(t)) {
				g.addEdge(s, t, e);
			}
		}
		return g;
	}
	
	@Override
	public String toString() {
		return String.format("Grafo %s %s\n%s\n%s", this.graphType, this.traverseType, this.vertexSet, this.edgeSet);
	}

	@Override
	public void removeVertex(V vertex) {
		
		
	}

	@Override
	public void removeEdge(V source, V target) {
		// TODO Auto-generated method stub
		
	}

}
