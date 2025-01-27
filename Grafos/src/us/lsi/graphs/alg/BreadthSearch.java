package us.lsi.graphs.alg;



import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.jgrapht.Graphs;

import us.lsi.graphs.virtual.EGraph;
import us.lsi.streams.Stream2;

public class BreadthSearch<V,E> implements Iterator<V>, Iterable<V> {
	
	/**
	 * @param <V> El tipo de los v&eacute;rtices
	 * @param <E> El tipo de las aristas
	 * @param g Un grafo 
	 * @param startVertex El v�rtice inicial
	 * @return Una algoritmo de b&uacute;squeda en anchura
	 */
	public static <V, E> BreadthSearch<V, E> of(EGraph<V, E> g, V startVertex) {
		return new BreadthSearch<V, E>(g, startVertex);
	}
	
	private EGraph<V,E> graph;
	private V startVertex;
	public Map<V,E> edgeToOrigin;
	public Queue<V> queue;
	

	BreadthSearch(EGraph<V, E> g, V startVertex) {
		this.graph = g;
		this.startVertex = startVertex;
		this.edgeToOrigin = new HashMap<>();
		this.edgeToOrigin.put(startVertex, null);
		this.queue = new LinkedList<>();
		this.queue.add(startVertex);
	}

	public Stream<V> stream() {
		return Stream2.of(this);
	}
	
	public BreadthSearch<V,E> copy() {
		return BreadthSearch.of(this.graph, this.startVertex);
	}
	
	public Iterator<V> iterator() {
		return this;
	}


	public boolean isSeenVertex(V v) {
		return this.edgeToOrigin.containsKey(v);
	}
	
	public boolean hasNext() {
		return !this.queue.isEmpty();
	}

	@Override
	public V next() {
		V actual = this.queue.remove();
		for(V v:Graphs.neighborListOf(graph, actual)) {
			if(!this.edgeToOrigin.containsKey(v)) {
				this.queue.add(v);
				this.edgeToOrigin.put(v,graph.getEdge(actual, v));
			}
		}
		return actual;
	}

	
	public E getEdgeToOrigin(V v) {
		return this.edgeToOrigin.get(v);
	}

//	public EGraph<V, E> getGraph() {
//		return EGraphI.sum(this.graph,startVertex());
//	}
	
	public V startVertex() {
		return this.startVertex;
	}	
	
	public Double distanceToOrigen(V vertex) {
		Double r = 0.;
		while(!vertex.equals(startVertex)) {		
			E edge = this.getEdgeToOrigin(vertex);
			vertex = Graphs.getOppositeVertex(graph, edge,vertex);
			r = r+1;		
		}
		return r;
	}
	
	public Set<E> edges() {
		return this.edgeToOrigin.values().stream().collect(Collectors.toSet());
	}

}
