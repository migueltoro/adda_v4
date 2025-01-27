package us.lsi.graphs.virtual;

import java.util.function.Function;
import java.util.function.Predicate;

import org.jgrapht.Graph;

import us.lsi.common.List2;
import us.lsi.common.Preconditions;
import us.lsi.common.TriFunction;
import us.lsi.graphs.virtual.EGraph.Type;
import us.lsi.path.EGraphPath.PathType;

public class EGraphBuilderGraph<G extends Graph<V,E>, V, E> implements EGraphBuilder<V, E> {

	G graph;
	V startVertex = null;
	Predicate<V> goal = null;	
	Function<E,Double> edgeWeight = e -> this.graph.getEdgeWeight(e);
	Function<V,Double> vertexWeight = e -> 0.;
	TriFunction<V,E,E,Double> vertexPassWeight= (v,e1,e2)->0.;
	V endVertex = null;	
	Predicate<V> goalHasSolution = v->true;
	PathType pathType = PathType.Sum;
	Function<V,E> greedyEdge = v -> this.graph.edgesOf(v).isEmpty() ? null : 
		List2.randomUnitary(this.graph.edgesOf(v)).get(0);
	TriFunction<V, Predicate<V>, V, Double> heuristic = (v1,p,v2) -> 0.;
	Type type = Type.Min;
	Integer solutionNumber = 1;
	
	public EGraphBuilderGraph(G graph) {
		super();
		Preconditions.checkArgument(graph.getType().isSimple(),"El grafo debe ser simple");
		this.graph = graph;
	}
	
	public EGraphBuilderGraph(G graph,V startVertex,Predicate<V> goal) {
		super();
		this.graph = graph;
		this.startVertex = startVertex;
		this.goal = goal;
	}
	
	public EGraphBuilderGraph(G graph,V startVertex,Predicate<V> goal,PathType pathType,Type type) {
		super();
		this.graph = graph;
		this.startVertex = startVertex;
		this.goal = goal;
		this.pathType = pathType;
		this.type = type;
	}
	
	
	@Override
	public EGraphBuilder<V, E> edgeWeight(Function<E, Double> edgeWeight) {
		this.edgeWeight = edgeWeight;
		return this;
	}
	
	@Override
	public EGraphBuilder<V, E> vertexWeight(Function<V, Double> vertexWeight) {
		this.vertexWeight = vertexWeight;
		return this;
	}
	@Override
	public EGraphBuilder<V, E> vertexPassWeight(TriFunction<V, E, E, Double> vertexPassWeight) {
		this.vertexPassWeight = vertexPassWeight;
		return this;
	}
	@Override
	public EGraphBuilder<V, E> startVertex(V startVertex) {
		this.startVertex = startVertex;
		return this;
	}
	
	@Override
	public EGraphBuilder<V, E> endVertex(V endVertex) {
		this.endVertex = endVertex;
		return this;
	}
	
	@Override
	public EGraphBuilder<V, E> pathType(PathType pathType) {
		this.pathType = pathType;
		return this;
	}
	
	@Override
	public EGraphBuilder<V, E> heuristic(TriFunction<V, Predicate<V>, V, Double> heuristic) {
		this.heuristic = heuristic;
		return this;
	}
	@Override
	public EGraphBuilder<V, E> type(Type type) {
		this.type = type;
		return this;
	}
	
	@Override
	public EGraphBuilder<V, E> solutionNumber(Integer n) {
		this.solutionNumber = n;
		return this;
	}
	
	@Override
	public EGraph<V,E> build() {
		return new EGraphOfGraph<>(this);
	}

}
