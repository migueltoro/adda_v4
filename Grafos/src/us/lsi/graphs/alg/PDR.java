package us.lsi.graphs.alg;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;

import us.lsi.common.Preconditions;
import us.lsi.graphs.Graphs2;
import us.lsi.graphs.virtual.EGraph;
import us.lsi.graphs.virtual.EGraph.Type;
import us.lsi.path.EGraphPath;

import java.util.Optional;
import java.util.Set;

import org.jgrapht.GraphPath;
import org.jgrapht.Graphs;
import org.jgrapht.graph.GraphWalk;
import org.jgrapht.graph.SimpleDirectedGraph;

public class PDR<V, E, S> {
	
	public static <V, E, S> PDR<V, E, S> ofGreedy(EGraph<V, E> graph) {
		GreedyOnGraph<V, E> ga = GreedyOnGraph.of(graph);
		Optional<GraphPath<V, E>> gp = ga.search();
		if(gp.isPresent()) return PDR.of(graph,null,gp.get().getWeight(),gp.get(),false);
		else return PDR.of(graph,null,null,null,false);
	}
	
	public static <V, E, S> PDR<V, E, S> of(EGraph<V, E> graph) {
		return PDR.of(graph,null,null,null,false);
	}
	
	public static <V, E, S> PDR<V, E, S> of(EGraph<V, E> graph, 
			Double bestValue, GraphPath<V, E> optimalPath) {
		return new PDR<V, E, S>(graph,null,bestValue,optimalPath,false);
	}
	
	public static <V, E, S> PDR<V, E, S> of(EGraph<V, E> graph, 
			Function<GraphPath<V, E>, S> fsolution, 
			Double bestValue, GraphPath<V, E> optimalPath, 
			Boolean withGraph) {
		return new PDR<V, E, S>(graph,fsolution,bestValue,optimalPath,withGraph);
	}

	private EGraph<V, E> graph;
	private Double bestValue = null;
	private Comparator<Sp<E>> comparatorEdges;
	private Comparator<Double> comparator;
	public Map<V, Sp<E>> solutionsTree;
	private List<V> actualPath;
	public GraphPath<V, E> optimalPath;
	public Set<S> solutions;
	protected Function<GraphPath<V,E>,S> fsolution;
	public SimpleDirectedGraph<V, E> outGraph;
	private Boolean withGraph = false;
	private Type type;
	private Boolean stop = false;

	PDR(EGraph<V, E> g, Function<GraphPath<V, E>, S> fsolution, Double bestValue, GraphPath<V, E> optimalPath, Boolean withGraph) {
		this.graph = g;
		this.comparatorEdges = this.graph.type() == EGraph.Type.Min?Comparator.naturalOrder():Comparator.reverseOrder();
		this.type = g.type();
		this.comparator = switch(this.type) {
		case All -> {
			Preconditions.checkNotNull(fsolution,"Para el caso All fsolution no puede ser null"); 
			this.solutions = new HashSet<>();
			yield null;}
		case Max -> Comparator.reverseOrder();
		case Min -> Comparator.naturalOrder();
		case One -> null;
		};	
		this.solutionsTree = new HashMap<>();
		this.actualPath = new ArrayList<>();
		this.bestValue = bestValue;
		this.optimalPath = optimalPath;
		this.withGraph = withGraph;
		this.fsolution = fsolution;
		
	}
	
	public Optional<GraphPath<V,E>> optimalPath(){
		return Optional.ofNullable(this.optimalPath);
	}

	private Boolean forget(E edge, V actual,Double accumulateValue,Predicate<V> goal,V end) {
		Boolean r = false;
		if(graph.type().equals(Type.All) || graph.type().equals(Type.One))  return false;
		Double w = this.graph.boundedValue(actual, accumulateValue,edge,(v1,p,v2)->this.newHeuristic(v1,p,v2));
		if(this.bestValue != null) r = comparator.compare(w, this.bestValue) >= 0;
		return r;
	}
	
	private Double newHeuristic(V v1, Predicate<V> p, V v2) {
		Double r;
		if (this.solutionsTree.containsKey(v1) && this.solutionsTree.get(v1) != null)
			r = this.solutionsTree.get(v1).weight();
		else
			r = graph.heuristic().apply(v1, p, v2);
		return r;
	}

	protected void update(V actual, Double accumulateValue) {
		if (graph.goalHasSolution().test(actual)) {
			switch(this.type) {
			case All:
				this.optimalPath = pathToOrigin(actual,accumulateValue);
				S s = fsolution.apply(this.optimalPath);
				this.solutions.add(s);
				if (this.solutions.size() >= this.graph.solutionNumber()) this.stop = true;
				break;
			case One:
				this.bestValue = accumulateValue;
				this.optimalPath = pathToOrigin(actual,accumulateValue);
				this.stop = true;
				break;
			case Min:
			case Max:
				if (this.bestValue == null || this.comparator.compare(accumulateValue, this.bestValue) < 0) {
					this.bestValue = accumulateValue;
				}
			}
		}
	}
	
	protected void updateMem(V actual,Double accumulateValue, Double toEnd, E edgeOut, E edgeIn) {
		Double weight = this.graph.initialPath().add(actual, accumulateValue, toEnd, edgeOut, edgeIn);
		if (this.bestValue == null || this.comparator.compare(weight, this.bestValue) < 0) {
			this.bestValue = accumulateValue;
		}
	}
	
	public Set<S> getSolutions(){
		return this.solutions;
	}
	
	public void iniciaGraph() {
		if(this.withGraph) outGraph = Graphs2.simpleDirectedGraph();
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
		iniciaGraph();
		this.solutionsTree = new HashMap<>();
		Sp<E> r = search(graph.startVertex(),0., null);	
		if(r == null && this.optimalPath !=null) return Optional.of(this.optimalPath);
		return pathFrom(graph.startVertex());
	}
	
	private Sp<E> search(V actual, Double accumulateValue, E edgeToOrigin) {
		this.actualPath.add(actual);
		Sp<E> r = null;
		if(this.solutionsTree.containsKey(actual)) {
			r = this.solutionsTree.get(actual);
			Double toEnd = r.weight();
			updateMem(actual,accumulateValue, toEnd, r.edge(), edgeToOrigin);
		} else if (graph.goal().test(actual)) {
			if (graph.goalHasSolution().test(actual)) {
				r = Sp.of(graph.goalSolutionValue(actual), null);
				this.solutionsTree.put(actual, r);
				update(actual,accumulateValue);			
			} else {
				r = null;
				this.solutionsTree.put(actual, r);
			}			
		} else {
			List<Sp<E>> rs = new ArrayList<>();	
			for (E edge : graph.edgesListOf(actual)) {					
				if (this.forget(edge,actual,accumulateValue,graph.goal(),graph.endVertex()) || this.stop) continue;
				V v = Graphs.getOppositeVertex(graph,edge,actual);
				Double ac = this.graph.add(actual,accumulateValue,edge,edgeToOrigin); 
				Sp<E> s = search(v,ac,edge);
				if (s!=null) {
					Double spv = this.graph.fromNeighbordSolutionValue(actual,s.weight,edge,edgeToOrigin);	
					Sp<E> sp = Sp.of(spv,edge);
					rs.add(sp);
				}
				addGraph(actual, edge);
			}
			if (!rs.isEmpty()) {
				r = rs.stream().filter(s -> s != null).min(this.comparatorEdges).orElse(null);
				this.solutionsTree.put(actual, r);
			}
		}
		this.actualPath.remove(actual);
		return r;
	}
	
	private GraphPath<V, E> pathToOrigin(V vertex,Double accumulateValue) {
		return new GraphWalk<>(this.graph,this.actualPath,accumulateValue);
	}

	private Optional<GraphPath<V, E>> pathFrom(V vertex) {	
		if(this.solutionsTree.get(vertex) == null) return Optional.empty();
		E edge = this.solutionsTree.get(vertex).edge;	
		EGraphPath<V,E> ePath = EGraphPath.ofVertex(this.graph,vertex,this.graph.pathType());
		while(edge != null) {
			ePath.add(edge);
			vertex = Graphs.getOppositeVertex(graph,edge,vertex);
			edge = this.solutionsTree.get(vertex).edge;	
		}
		this.optimalPath = ePath;
		return Optional.of(ePath);
	}
	
	public record Sp<E>(Double weight, E edge) implements Comparable<Sp<E>> {
		
		public static <E> Sp<E> of(Double weight,E edge) {
			return new Sp<>(weight,edge);
		}

		@Override
		public int compareTo(Sp<E> sp) {
			return this.weight.compareTo(sp.weight);
		}

	}
}
