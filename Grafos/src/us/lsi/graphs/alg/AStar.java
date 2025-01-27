package us.lsi.graphs.alg;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Stream;

import org.jgrapht.GraphPath;
import org.jgrapht.Graphs;
import org.jgrapht.graph.GraphWalk;
import org.jgrapht.graph.SimpleDirectedGraph;
import org.jheaps.AddressableHeap.Handle;
import org.jheaps.tree.FibonacciHeap;

import us.lsi.colors.GraphColors;
import us.lsi.colors.GraphColors.ArrowHead;
import us.lsi.colors.GraphColors.Color;
import us.lsi.common.Preconditions;
import us.lsi.graphs.Graphs2;
import us.lsi.graphs.virtual.EGraph;
import us.lsi.graphs.virtual.EGraph.Type;
import us.lsi.path.EGraphPath;
import us.lsi.streams.Stream2;


public class AStar<V,E,S> implements Iterator<V>, Iterable<V> {
	
	/**
	 * @param <V> El tipo de los v&eacute;rtices
	 * @param <E> El tipo de las aristas 
	 * @param graph Un grafo
	 * @param heuristic La heur&iacute;stica 
	 * @return Una algoritmo de b&uacute;squeda de AStar
	 */
	public static <V, E, S> AStar<V, E, S> ofGreedy(EGraph<V, E> graph) {
		GreedyOnGraph<V, E> ga = GreedyOnGraph.of(graph);
		Optional<GraphPath<V, E>> gp = ga.search();
		if(gp.isPresent()) return AStar.of(graph,null,gp.get().getWeight(),gp.get());
		else return new AStar<V, E, S>(graph,null,null,null);
	}
	
	public static <V, E, S> AStar<V, E, S> of(EGraph<V, E> graph) {
		return new AStar<V, E, S>(graph,null,null,null);
	}
	
	public static <V, E, S> AStar<V, E, S> of(EGraph<V, E> graph,Double bestValue,GraphPath<V, E> optimalPath) {
		return new AStar<V, E, S>(graph,null,bestValue,optimalPath);
	}
	
	public static <V, E, S> AStar<V, E, S> of(EGraph<V, E> graph,
			Function<GraphPath<V, E>, S> fsolution,Double bestValue,GraphPath<V, E> optimalPath) {
		return new AStar<V, E, S>(graph,fsolution,bestValue,optimalPath);
	}

	private Type type;
	public Comparator<Double> comparator;
	public EGraph<V,E> graph; 
	public Map<V,Handle<Double,Data<V,E>>> tree;
	public FibonacciHeap<Double,Data<V,E>> heap; 
	private Double bestValue = null; //mejor valor estimado
	private GraphPath<V, E> optimalPath = null; //mejor camino estimado
	protected Set<S> solutions;
	protected Function<GraphPath<V,E>,S> fsolution;

	protected AStar(EGraph<V, E> graph, Function<GraphPath<V, E>, S> fsolution, Double bestValue, GraphPath<V, E> optimalPath) {
		super();
		this.graph = graph;
		this.type = this.graph.type();
		this.comparator = switch(this.type) {
		case All -> {
			Preconditions.checkNotNull(fsolution,"Para el caso All fsolution no puede ser null"); 
			this.solutions = new HashSet<>();
			yield null;}
		case Max -> Comparator.reverseOrder();
		case Min -> Comparator.naturalOrder();
		case One -> null;
		};
		this.comparator = this.graph.type().equals(EGraph.Type.Min)?Comparator.naturalOrder():Comparator.reverseOrder();		
		this.tree = new HashMap<>();
		EGraphPath<V, E> ePath = graph.initialPath();
		this.heap = new FibonacciHeap<>(comparator);
		Data<V,E> data = Data.of(graph.startVertex(),null,ePath.getWeight());	
		Double d = this.graph.estimatedWeightToEnd(graph.startVertex(),data.distanceToOrigin);
		Handle<Double, Data<V, E>> h = this.heap.insert(d,data);
		this.tree.put(graph.startVertex(),h);
		this.bestValue = bestValue;
		this.optimalPath = optimalPath;
		this.fsolution = fsolution;
	}
	
	public Boolean closed(V v) {
		return this.tree.get(v).getValue().closed();
	}
	
	public Stream<V> stream() {
		return Stream2.of(this);
	}
	
	public Iterator<V> iterator() {
		return this;
	}
	
	private Boolean forget(Double actualDistance, V v) {
		Double w = graph.estimatedWeightToEnd(v,actualDistance);
		Boolean r = false;
		r = this.bestValue != null && comparator.compare(w,this.bestValue) >= 0;
		if(r) this.tree.remove(v);
		return r;
	}
	
	public boolean hasNext() {
		return !heap.isEmpty(); 
	}

	@Override
	public V next() {
		Handle<Double, Data<V, E>> hActual = heap.deleteMin();
		Data<V, E> dActual = hActual.getValue();
		V vertexActual = dActual.vertex;
		Double actualDistance = dActual.distanceToOrigin;
		E edgeToOrigen = dActual.edge;
		if(forget(actualDistance,  vertexActual)) return null;
		for (E backEdge : graph.edgesListOf(vertexActual)) {
			V v = Graphs.getOppositeVertex(graph,backEdge,vertexActual);
			Double newDistanceToOrigin = graph.add(v,actualDistance,backEdge,edgeToOrigen);
			Double newDistanceToEnd =  graph.estimatedWeightToEnd(v,newDistanceToOrigin);
			if (!tree.containsKey(v)) {
				Data<V, E> dv = Data.of(v, backEdge, newDistanceToOrigin);
				Handle<Double, Data<V, E>> hv = heap.insert(newDistanceToEnd, dv);
				tree.put(v, hv);
			} else if (comparator.compare(newDistanceToOrigin,tree.get(v).getValue().distanceToOrigin()) < 0) {
				Data<V, E> dv = Data.of(v, backEdge, newDistanceToOrigin);
				Handle<Double, Data<V, E>> hv = tree.get(v);
				hv.setValue(dv);
				hv.decreaseKey(newDistanceToEnd);
			}
		}
		hActual.setValue(Data.toTrue(dActual));
		tree.put(vertexActual, hActual);
		return vertexActual;
	}

	public E getEdgeToOrigin(V v) {
		return tree.get(v).getValue().edge;
	}

	public EGraph<V, E> getGraph() {
		return this.graph;
	}
	
	public Optional<GraphPath<V, E>> path(V startVertex, V last) {
		return this.path(startVertex,Optional.of(last));
	}
	
	public Optional<GraphPath<V, E>> path(V startVertex, Optional<V> last) {
		if (!last.isPresent() || !graph.goalHasSolution().test(last.get())) return Optional.empty();
		V endVertex = last.get();
		V v = endVertex;
		if (!tree.containsKey(v)) return Optional.empty();
		Handle<Double, Data<V, E>> hav = this.tree.get(v);
		Data<V, E> dav = hav.getValue();
		Double weight = dav.distanceToOrigin;
		E edge = dav.edge;
		List<E> edges = new ArrayList<>();
		while (edge != null) {
			edges.add(edge);
			v = Graphs.getOppositeVertex(graph, edge, v);
			edge = this.getEdgeToOrigin(v);
		}
		Collections.reverse(edges);
		List<V> vertices = new ArrayList<>();
		v = startVertex;
		vertices.add(v);
		for (E e : edges) {
			v = Graphs.getOppositeVertex(graph, e, v);
			vertices.add(v);
		}
		GraphPath<V, E> gp = new GraphWalk<>(graph, startVertex, endVertex, vertices, edges, weight);
		return Optional.of(gp);
	}

	public Optional<GraphPath<V, E>> search() {
		V startVertex = graph.startVertex();
		EGraphPath<V, E> ePath = graph.initialPath();
		Optional<GraphPath<V, E>> r = Optional.empty();
		if (graph.goal().test(startVertex))
			return Optional.of(ePath);
		switch (this.graph.type()) {
		case All:
			List<V> goals = this.stream()
				.filter(v -> v != null)
				.filter(graph.goal().and(graph.goalHasSolution()))
				.limit(this.graph.solutionNumber())
				.toList();
			for (V v : goals) {
				Optional<GraphPath<V, E>> p = this.path(startVertex, v);
				r = p;
				this.solutions.add(this.fsolution.apply(p.get()));
			}
			break;
		case Max:
		case Min:
		case One:
			Optional<V> last = this.stream().filter(v -> v != null).filter(graph.goal().and(graph.goalHasSolution()))
					.findFirst();
			if (last.isPresent())
				r = path(startVertex, last);
			else
				r = Optional.ofNullable(this.optimalPath);
		}
		return r;
	}
	
	public Set<S> getSolutions() {
		return this.solutions;
	}
	
	public SimpleDirectedGraph<V,E> outGraph(){
		SimpleDirectedGraph<V,E> g = Graphs2.simpleDirectedGraph();
		for(V v:tree.keySet()) {
			g.addVertex(v);
		}
		for(V v:tree.keySet()) {
			E e = tree.get(v).getValue().edge();
			if (e != null) {
				V source = graph.getEdgeSource(e);
				V target = graph.getEdgeTarget(e);
				g.addEdge(source, target, e);
			}
		}
		return g;
	}
	
	
	public static <V,E> void toDot(SimpleDirectedGraph<V,E> g, GraphPath<V,E> gp, String file){
		List<V> vertices = gp.getVertexList();
		List<E> edges = gp.getEdgeList();
		GraphColors.toDot(g, file, 
				x -> x.toString(),
				x -> x.toString(),
				x -> GraphColors.colorIf(Color.red, vertices.contains(x)),
				e -> GraphColors.all(GraphColors.arrowHead(ArrowHead.none),
						GraphColors.colorIf(Color.red, edges.contains(e))));
	}
	

	public static record Data<V, E> (V vertex, E edge, Double distanceToOrigin, Boolean closed) {
		
		public static <V, E> Data<V, E> of(V vertex, E edge, Double distance) {
			return new Data<>(vertex, edge, distance,false);
		}

		public static <V, E> Data<V, E> toTrue(Data<V, E> d) {
			return new Data<>(d.vertex, d.edge, d.distanceToOrigin,true);
		}

	}
	
}
