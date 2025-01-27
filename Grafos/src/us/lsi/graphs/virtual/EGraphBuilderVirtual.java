package us.lsi.graphs.virtual;

import java.util.function.Function;
import java.util.function.Predicate;

import us.lsi.common.TriFunction;
import us.lsi.graphs.virtual.EGraph.Type;
import us.lsi.path.EGraphPath.PathType;

public class EGraphBuilderVirtual<V extends VirtualVertex<V,E,?>, E extends SimpleEdgeAction<V,?>> 
	implements EGraphBuilder<V, E> {
	

	V startVertex = null;	
	Function<E,Double> edgeWeight = e -> e.weight();
	Function<V,Double> vertexWeight = e -> 0.;
	TriFunction<V,E,E,Double> vertexPassWeight= (v,e1,e2)->0.;
	V endVertex = null;	
	PathType pathType = PathType.Sum;
	Function<V,E> greedyEdge = v -> v.edgesListOf().isEmpty()? null : v.edgesListOf().get(0);
	TriFunction<V, Predicate<V>, V, Double> heuristic = (v1,p,v2) -> 0.;
	Type type = Type.Min;
	Integer solutionNumber;
	
	public EGraphBuilderVirtual() {
		super();
	}
		
	public EGraphBuilderVirtual(V startVertex) {
		super();
		this.startVertex = startVertex;
	}
	
	public EGraphBuilderVirtual(V startVertex,PathType pathType,Type type) {
		super();
		this.startVertex = startVertex;
		this.pathType = pathType;
		this.type = type;
	}

	public EGraphBuilderVirtual<V, E> edgeWeight(Function<E, Double> edgeWeight) {
		this.edgeWeight = edgeWeight;
		return this;
	}
	
	public EGraphBuilderVirtual<V, E> vertexWeight(Function<V, Double> vertexWeight) {
		this.vertexWeight = vertexWeight;
		return this;
	}
	public EGraphBuilderVirtual<V, E> vertexPassWeight(TriFunction<V, E, E, Double> vertexPassWeight) {
		this.vertexPassWeight = vertexPassWeight;
		return this;
	}
	public EGraphBuilderVirtual<V, E> startVertex(V startVertex) {
		this.startVertex = startVertex;
		return this;
	}
	public EGraphBuilderVirtual<V, E> endVertex(V endVertex) {
		this.endVertex = endVertex;
		return this;
	}

	public EGraphBuilderVirtual<V, E> pathType(PathType pathType) {
		this.pathType = pathType;
		return this;
	}
	public EGraphBuilderVirtual<V, E> greedyEdge(Function<V, E> greedyEdge) {
		this.greedyEdge = greedyEdge;
		return this;
	}
	public EGraphBuilderVirtual<V, E> heuristic(TriFunction<V, Predicate<V>, V, Double> heuristic) {
		this.heuristic = heuristic;
		return this;
	}
	public EGraphBuilderVirtual<V, E> type(Type type) {
		this.type = type;
		return this;
	}
	@Override
	public EGraphBuilder<V, E> solutionNumber(Integer n) {
		this.solutionNumber = n;
		return this;
	}
	public EGraph<V,E> build() {
		return new ESimpleVirtualGraph<V,E>(this);		
	}

}
