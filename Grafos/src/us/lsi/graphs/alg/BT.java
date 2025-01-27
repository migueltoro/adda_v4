package us.lsi.graphs.alg;


import java.util.ArrayList;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.jgrapht.GraphPath;
import org.jgrapht.Graphs;
import org.jgrapht.graph.SimpleDirectedGraph;

import us.lsi.common.List2;
import us.lsi.common.Preconditions;
import us.lsi.graphs.Graphs2;
import us.lsi.graphs.virtual.EGraph;
import us.lsi.graphs.virtual.EGraph.Type;
import us.lsi.path.EGraphPath;

public class BT<V,E,S> {

	public static <V, E, S> BT<V, E, S> ofGreedy(
			EGraph<V, E> graph) {
		GreedyOnGraph<V, E> ga = GreedyOnGraph.of(graph);
		Optional<GraphPath<V, E>> gp = ga.search();
		if(gp.isPresent()) return BT.of(graph,null,gp.get().getWeight(),gp.get(),false);
		else return BT.of(graph, null, null, null, false);
	}
	
	public static <V, E, S> BT<V, E, S> of(
			EGraph<V, E> graph,
			Function<GraphPath<V, E>, S> fsolution) {
		return BT.of(graph, fsolution, null, null, false);
	}
	
	public static <V, E, S> BT<V, E, S> of(
			EGraph<V, E> graph) {
		return BT.of(graph, null, null, null, false);
	}
	
	public static <V, E, S> BT<V, E, S> of(
			EGraph<V, E> graph,
			Double bestValue,
			GraphPath<V,E> optimalPath) {
		return new BT<V, E, S>(graph,null,bestValue,optimalPath,false);
	}
	
	public static <V, E, S> BT<V, E, S> of(
			EGraph<V, E> graph,
			Function<GraphPath<V, E>, S> fsolution,
			Double bestValue,
			GraphPath<V,E> optimalPath,
			Boolean withGraph) {
		return new BT<V, E, S>(graph,fsolution,bestValue,optimalPath,withGraph);
	}
	
	private Comparator<Double> comparator = Comparator.naturalOrder();
	
	private Type type;
	public EGraph<V,E> graph;
	public Double bestValue;
	public GraphPath<V,E> optimalPath;
	public Set<S> solutions;
	protected Function<GraphPath<V,E>,S> fsolution;
	private SimpleDirectedGraph<V,E> outGraph;
	private Boolean withGraph = false;
	protected Boolean stop = false;
	
	BT(EGraph<V, E> graph,Function<GraphPath<V, E>, S> fsolution, 
			Double bestValue,GraphPath<V,E> optimalPath, Boolean withGraph) {
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
		this.fsolution = fsolution;
		this.bestValue = bestValue;
		this.optimalPath = optimalPath;
		this.withGraph = withGraph;
	}
	
	protected Boolean forget(State<V,E> state, E edge) {
		Boolean r = false;
		if(graph.type().equals(Type.All) || graph.type().equals(Type.One))  return false;
		Double w = state.getGraph().boundedValue(state.getActualVertex(),state.getAccumulateValue(),
				edge,graph.heuristic());
		if(this.bestValue != null) r = comparator.compare(w,this.bestValue) >= 0;
		return r;
	}
	
	protected void update(State<V, E> state) {
		if (graph.goalHasSolution().test(state.getActualVertex())) {
			switch(this.type) {
			case All: 
				this.optimalPath = state.getPath();
				S s = fsolution.apply(state.getPath());
				this.solutions.add(s);
				if (this.solutions.size() >= this.graph.solutionNumber()) this.stop = true;
				break;
			case One:
				this.bestValue = state.getAccumulateValue();
				this.optimalPath = state.getPath();
				this.stop = true;
				break;
			case Min:
			case Max:
				if (this.bestValue == null || this.comparator.compare(state.getAccumulateValue(), this.bestValue) < 0) {
					this.bestValue = state.getAccumulateValue();
					this.optimalPath = state.getPath();
				}
			}
		}
	}
	
	private void initialGraph() {
		if (this.withGraph) this.outGraph = Graphs2.simpleDirectedGraph();
	}
	
	private void addGraph(V v, E edge) {
		if(withGraph) {
			V v2 = Graphs.getOppositeVertex(graph,edge,v);
			if(!this.outGraph.containsVertex(v)) this.outGraph.addVertex(v);
			if(!this.outGraph.containsVertex(v2)) this.outGraph.addVertex(v2);
			if(!this.outGraph.containsEdge(edge)) this.outGraph.addEdge(v, v2, edge);
		}
	}
	
	public SimpleDirectedGraph<V,E> outGraph() {
		return this.outGraph;
	}
	
	public Optional<GraphPath<V, E>> search() {	
		initialGraph();
		State<V,E> initialState = StatePath.of(graph,graph.goal(),graph.endVertex());
		search(initialState);
		return this.optimalPath();
	}
	
	public void search(State<V, E> state) {
		V actual = state.getActualVertex();
		if (graph.goal().test(actual)) {
			this.update(state);
		} else {
			for (E edge : graph.edgesListOf(actual)) {
				if (this.forget(state, edge) || this.stop) continue;
				state.forward(edge);
				search(state);
				addGraph(actual, edge);
				state.back(edge);
			}
		}
	}

	public Set<S> getSolutions(){
		if(this.solutions == null) return Set.of(this.fsolution.apply(this.optimalPath));
		return this.solutions;
	}
	
	public Optional<GraphPath<V, E>> optimalPath(){
		return Optional.ofNullable(this.optimalPath);		
	}
	
	public String toStringSolutions() {
		return this.solutions.stream().sorted().map(e->e.toString()).collect(Collectors.joining("\n"));
	}

	public interface State<V, E> {
		void forward(E edge);
		void back(E edge);
		Double getAccumulateValue();
		EGraphPath<V, E> getPath();
		EGraph<V, E> getGraph();
		V getActualVertex();
	}
	
	
	public static class StatePath<V,E> implements State<V, E> {
		private V actualVertex;
		private EGraphPath<V, E> path;
		private EGraph<V,E> graph;
		private List<E> edges;
		private List<Double> weights;
		private Double accumulateValue;
		
		public static <V,E> State<V, E> of(EGraph<V,E> graph, Predicate<V> goal, V end){
			return new StatePath<>(graph,goal,end);
		}		
		
		public StatePath(EGraph<V,E> graph, Predicate<V> goal, V end) {
			super();
			this.actualVertex = graph.startVertex();
			this.graph = graph;
			this.path = graph.initialPath();
			this.edges = new ArrayList<>();
			this.weights = new ArrayList<>();
			this.accumulateValue = this.path.getWeight();
		}		
	
		@Override
		public void forward(E edge) {
			E lastEdge = edges.isEmpty()?null:List2.last(edges);
			this.accumulateValue = this.graph.add(this.actualVertex,this.accumulateValue,edge,lastEdge);
			this.actualVertex = Graphs.getOppositeVertex(graph,edge,this.actualVertex);
			this.edges.add(edge);
			this.weights.add(this.accumulateValue);
		}
		
		@Override
		public void back(E edge) {
			this.actualVertex = Graphs.getOppositeVertex(graph,edge,this.actualVertex);	
			this.edges.remove(this.edges.size()-1);
			this.weights.remove(this.weights.size()-1);
			this.accumulateValue = !this.weights.isEmpty()? List2.last(this.weights): graph.initialPath().getWeight();
		}
		
		@Override
		public Double getAccumulateValue() {
			return this.accumulateValue;
		}	
	
		@Override
		public EGraph<V, E> getGraph() {
			return graph;
		}

		@Override
		public EGraphPath<V, E> getPath() {				
			EGraphPath<V,E> ePath = graph.initialPath();
			for(E e:this.edges) {
				ePath.add(e);
			}
			return ePath;
		}
					
		@Override
		public V getActualVertex() {
			return actualVertex;
		}
		
		@Override
		public String toString() {
			return String.format("%s,\n%.2f,\n%s",
					this.actualVertex,this.getAccumulateValue(),
					this.getPath().getEdgeList().stream().map(e->e.toString()).collect(Collectors.joining(",","{","}")));
		}	
	}	
}
