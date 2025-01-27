package us.lsi.graphs.virtual;

import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.graph.DirectedMultigraph;
import org.jgrapht.graph.GraphWalk;

import us.lsi.common.TriFunction;
import us.lsi.path.EGraphPath;
import us.lsi.path.EGraphPath.PathType;

public interface EGraph<V, E> extends Graph<V, E> {
	
	public static <G extends Graph<V,E>, V,E> EGraphBuilder<V, E> 
		ofGraph(G graph){
		return new EGraphBuilderGraph<G,V,E>(graph);
	}
	
	public static <G extends Graph<V, E>, V, E> EGraphBuilder<V, E> 
		ofGraph(G graph, V startVertex, Predicate<V> goal) {
		return new EGraphBuilderGraph<G, V, E>(graph, startVertex, goal);
	}
	
	public static <G extends Graph<V,E>, V,E> EGraphBuilder<V, E>
		ofGraph(G graph,V startVertex,Predicate<V> goal,PathType pathType,Type type){
		return new EGraphBuilderGraph<G,V,E>(graph,startVertex,goal,pathType,type);
	}
	
	public static <V extends VirtualVertex<V,E,?>, E extends SimpleEdgeAction<V,?>> EGraphBuilder<V, E> 
		virtual(){
		return new EGraphBuilderVirtual<V,E>();
	}
	
	public static <V extends VirtualVertex<V, E, ?>, E extends SimpleEdgeAction<V, ?>> EGraphBuilder<V, E>
		virtual(V startVertex) {
		return new EGraphBuilderVirtual<V, E>(startVertex);
	}
	
//	public static <V extends VirtualVertex<V,E,?>, E extends SimpleEdgeAction<V,?>> EGraphBuilder<V, E>
//		virtual(V startVertex,PathType pathType,Type type){
//		return new EGraphBuilderVirtual<V,E>(startVertex,pathType,type);
//	}
	
	public static <V, E> EGraphBuilderVirtualG<V, E> 
		virtualG(Graph<V, E> graph, V startVertex,PathType pathType, Type type) {
		return new EGraphBuilderVirtualG<V, E>(graph, startVertex,pathType, type);
	}
	
	public static <V, E> EGraphBuilderVirtualMG<V, E> 
			virtualMG(DirectedMultigraph<V, E> graph, V startVertex, Predicate<V> goal,PathType pathType, Type type) {
		return new EGraphBuilderVirtualMG<V, E>(graph, startVertex, goal, pathType, type);
	}

	double getVertexPassWeight(V vertex, E edgeIn, E edgeOut);

	double getVertexWeight(V vertex);

	List<E> edgesListOf(V v);

	EGraphPath<V, E> initialPath();

	V oppositeVertex(E edge, V v);

	V startVertex();

	Predicate<V> goal();

	V endVertex();
	
	Predicate<V> goalHasSolution();
	
	Function<V, E> greedyEdge();

	PathType pathType();
	
	TriFunction<V, Predicate<V>, V, Double> heuristic(); 
	
	public static enum Type{Min,Max,All,One}
	
	Type type();
	
	Integer solutionNumber();
		
	public default Double add(V vertexActual, Double acumulateValue, E edgeOut, E edgeIn) {
		return this.initialPath().add(vertexActual, acumulateValue, edgeOut, edgeIn);
	}

	public default Double boundedValue(V vertexActual, Double acumulateValue, E edgeOut,
			TriFunction<V, Predicate<V>, V, Double> heuristic) {
		return this.initialPath().boundedValue(vertexActual, acumulateValue, edgeOut, this.goal(), this.endVertex(),
				heuristic);
	}

	public default Double estimatedWeightToEnd(V vertexActual, Double acumulateValue) {
		return this.initialPath().estimatedWeightToEnd(vertexActual, acumulateValue, this.goal(), this.endVertex(),
				this.heuristic());
	}

	public default Double goalSolutionValue(V vertexActual) {
		return pathType().equals(PathType.Sum) ? 0. : getVertexWeight(vertexActual);
	}

	public default Double fromNeighbordSolutionValue(V vertexActual, Double weight, E edgeOut, E edgeIn) {
		 Double r = pathType().equals(PathType.Sum) ? initialPath().add(vertexActual, weight, edgeOut, edgeIn) : weight;
		 return r;
	}
	
	public static <V,E> GraphPath<V,E> pathG(GraphPath<VirtualVertexG<V,E>,VirtualEdgeG<V,E>> p, Graph<V,E> graph) {
		return new GraphWalk<V, E>(graph, 
				p.getStartVertex().vertex(), 
				p.getEndVertex().vertex(), 
				p.getVertexList().stream().map(v->v.vertex()).toList(), 
				p.getEdgeList().stream().map(e->e.action()).toList(),
				p.getWeight());
	}
	
	public static <V,E> GraphPath<V,E> pathMG(GraphPath<VirtualVertexMG<V,E>,VirtualEdgeMG<V,E>> p, Graph<V,E> graph) {
		return new GraphWalk<V, E>(graph, 
				p.getStartVertex().vertex(), 
				p.getEndVertex().vertex(), 
				p.getVertexList().stream().map(v->v.vertex()).toList(), 
				p.getEdgeList().stream().map(e->e.action()).toList(),
				p.getWeight());
	}

}
