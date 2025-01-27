package us.lsi.graphs.alg;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.jgrapht.Graphs;

import us.lsi.graphs.virtual.EGraph;
import us.lsi.streams.Stream2;

public class DephtSearch<V, E> implements Iterator<V>, Iterable<V> {
	
	/**
	 * @param <V> El tipo de los v&eacute;rtices
	 * @param <E> El tipo de las aristas
	 * @param g Un grafo 
	 * @param startVertex El v�rtice inicial
	 * @return Una algoritmo de b&uacute;squeda en profundidad en preorden
	 */
	public static <V, E> DephtSearch<V, E> of(EGraph<V, E> g, V startVertex) {
		return new DephtSearch<V, E>(g, startVertex);
	}


	protected Map<V,E> edgeToOrigin;
	public EGraph<V,E> graph;
	protected Stack<V> stack;
	protected V startVertex; 

	DephtSearch(EGraph<V, E> g, V startVertex) {
		this.graph = g;
		this.startVertex = startVertex;
		this.edgeToOrigin = new HashMap<>();
		this.edgeToOrigin.put(startVertex, null);
		this.stack = new Stack<>();
		this.stack.add(startVertex);
	}
	
	public Stream<V> stream() {
		return Stream2.of(this);
	}
	
	public DephtSearch<V, E> copy() {
		return DephtSearch.of(this.graph, this.startVertex);
	}
	
	public Iterator<V> iterator() {
		return this;
	}
	
	public boolean isSeenVertex(V v) {
		return this.edgeToOrigin.containsKey(v);
	}
	
	public boolean hasNext() {
		return !stack.isEmpty();
	}

	@Override
	public V next() {
		V actual = stack.pop();
		for(V v:Graphs.neighborListOf(graph, actual)) {
			if(!this.edgeToOrigin.containsKey(v)) {
				stack.add(v);
				this.edgeToOrigin.put(v,graph.getEdge(actual, v));
			}
		}
		return actual;
	}

	public E getEdgeToOrigin(V v) {
		return this.edgeToOrigin.get(v);
	}
	
	public V startVertex() {
		return this.startVertex;
	}
	
	public Set<E> edges() {
		return this.edgeToOrigin.values().stream().collect(Collectors.toSet());
	}

	

}
