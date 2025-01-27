package us.lsi.graphs.alg;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;
import java.util.stream.Stream;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;

import us.lsi.graphs.virtual.EGraph;
import us.lsi.streams.Stream2;

public class TopologicalSearch<V, E> implements Iterator<V>, Iterable<V> {
	
	/**
	 * @param <V> El tipo de los v&eacute;rtices
	 * @param <E> El tipo de las aristas
	 * @param g Un grafo 
	 * @param startVertex El v�rtice inicial
	 * @return Una algoritmo de b&uacute;squeda en orden topologico
	 */
	public static <V, E> TopologicalSearch<V, E> topological(EGraph<V, E> g, V startVertex) {
		return new TopologicalSearch<V, E>(g, startVertex);
	}

	
	protected Map<V,E> edgeToOrigin;
	public EGraph<V,E> graph;
	protected Stack<V> stackPre;
	protected Queue<V> queue;
	protected V startVertex; 
	protected Integer n;
	protected Integer i;
	public Graph<V,E> outGraph;
	public Boolean withGraph = false;


	TopologicalSearch(EGraph<V, E> g, V startVertex) {
		this.graph = g;
		this.startVertex = startVertex;
		this.edgeToOrigin = new HashMap<>();
		this.edgeToOrigin.put(startVertex, null);
		this.stackPre = new Stack<>();
		this.stackPre.add(startVertex);
		this.queue = new LinkedList<>();
		this.preorder();
//		this.n = this.queue.size();
//		this.i = 0;
	}
	
	public Stream<V> stream() {
		if(this.withGraph) outGraph = new SimpleDirectedWeightedGraph<>(null,null);
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
	
	public boolean hasNextP() {
		return !stackPre.isEmpty();
	}

	public V nextP() {
		V actual = stackPre.pop();
		if(this.withGraph) outGraph.addVertex(actual);
		this.queue.add(actual);
		for(V v:Graphs.neighborListOf(graph, actual)) {
			if(!this.edgeToOrigin.containsKey(v)) {
				stackPre.add(v);
				this.edgeToOrigin.put(v,graph.getEdge(actual, v));
				if(this.withGraph) {
					outGraph.addVertex(v);
					outGraph.addEdge(actual,v,graph.getEdge(actual,v));
				}
			}
		}
		return actual;
	}
	
	private void preorder() {
		while(this.hasNextP()) {
			this.nextP();
		}
	}
	
	@Override
	public boolean hasNext() {
		return !this.queue.isEmpty();
	}

	@Override
	public V next() {
		return this.queue.remove();
	}	

	public E getEdgeToOrigin(V v) {
		return this.edgeToOrigin.get(v);
	}
	
	public V startVertex() {
		return this.startVertex;
	}
	

}
