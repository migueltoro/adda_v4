package us.lsi.path;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

import org.jgrapht.GraphPath;
import org.jgrapht.Graphs;

import us.lsi.common.List2;
import us.lsi.common.Preconditions;
import us.lsi.common.TriFunction;
import us.lsi.graphs.alg.PDR.Sp;
import us.lsi.graphs.virtual.EGraph;

public class GraphPathLast<V, E> extends GraphPath2<V,E> implements EGraphPath<V,E> {
	

	public static <V, E> GraphPathLast<V, E> ofMap(EGraph<V, E> graph, V vertex, Map<V,Sp<E>> solutions){
		Preconditions.checkArgument(graph.pathType() == PathType.Last, 
				String.format("El tipo del EGraphPath debe ser Last y es %s",graph.pathType()));
		Sp<E> sp = solutions.get(vertex);
		GraphPathLast<V, E> gp = GraphPathLast.ofEdge(graph,sp.edge());
		while(sp.edge() != null) {
			vertex = Graphs.getOppositeVertex(graph,sp.edge(),vertex);
			sp = solutions.get(vertex);
			gp.add(sp.edge());
		}
		return gp;
	}
	
	public static <V, E> GraphPathLast<V, E> ofEdge(EGraph<V, E> graph, E edge) {
		Preconditions.checkArgument(graph.pathType() == PathType.Last, 
				String.format("El tipo del EGraphPath debe ser Last y es %s",graph.pathType()));
		V startVertex = graph.getEdgeSource(edge);
		V endVertex = graph.getEdgeTarget(edge);
		List<V> vertexList =List2.of(startVertex,endVertex);
		List<E> edgeList = List2.of(edge);
		Double weight = graph.getVertexWeight(endVertex);
		return new GraphPathLast<V, E>(graph, vertexList, edgeList, weight);
	}
	
	public static <V, E> GraphPathLast<V, E> ofVertex(EGraph<V, E> graph, V vertex) {
		Preconditions.checkArgument(graph.pathType() == PathType.Last, 
				String.format("El tipo del EGraphPath debe ser Last y es %s",graph.pathType()));
		List<V> vertexList = List2.of(vertex);
		List<E> edgeList = List2.of();
		Double weight = 0.;
	    weight += graph.getVertexWeight(vertex);
		return new GraphPathLast<V, E>(graph, vertexList, edgeList, weight);
	}

	private EGraph<V,E> graph;
	
	protected GraphPathLast(EGraph<V, E> graph, List<V> vertexList, List<E> edgeList, double weight) {
		super(graph, vertexList, edgeList, weight);
		this.graph = graph;
	}

	
	public GraphPathLast<V,E> add(E edge) {
		Preconditions.checkNotNull(edge,"La arista no puede ser null");
		V vertexActual = List2.last(vertexList);
		super.edgeList.add(edge);
		V target = Graphs.getOppositeVertex(graph, edge, vertexActual);
		super.vertexList.add(target);
		super.weight = graph.getVertexWeight(target);	
		return this;
	}
	
	@Override
	public GraphPathLast<V,E> remove() {
		E edge = List2.last(super.edgeList);
		Preconditions.checkNotNull(edge,"La arista no puede ser null");
		V vertexActual = List2.last(super.vertexList);
		Preconditions.checkNotNull(vertexActual,"El v�rtice actual no puede ser null");
		super.edgeList.remove(super.edgeList.size()-1);
		super.vertexList.remove(super.vertexList.size()-1);
		super.weight = graph.getVertexWeight(vertexActual);	
		return this;
	}
	
	@Override
	public Double add(V vertexActual, Double acumulateValue, E edgeOut, E edgeIn) {
		Preconditions.checkNotNull(edgeOut, "La arista no puede ser null");
		V target = Graphs.getOppositeVertex(graph, edgeOut, vertexActual);
		return graph.getVertexWeight(target);
	}
	
	@Override
	public Double add(V vertexActual, Double acumulateValue, Double toEnd,E edgeOut, E edgeIn) {
		return toEnd;
	}
	
	public GraphPathLast<V,E> copy() {
		return new GraphPathLast<V, E>(this.graph, 
				new ArrayList<>(this.vertexList), 
				new ArrayList<>(this.edgeList), 
				this.weight);
	}
	
	public GraphPathLast<V,E> concat(GraphPath<V,E> path) {
		GraphPathLast<V, E> r = this.copy();
		r.getEdgeList().stream().forEach(e->this.add(e));
		return r;
	}

	@Override
	public Double boundedValue(V vertexActual,Double accumulateValue,E edge,Predicate<V> goal, V end, 
			TriFunction<V,Predicate<V>,V,Double> heuristic) {
		Preconditions.checkNotNull(edge,"La arista no puede ser null");
		V target = Graphs.getOppositeVertex(super.graph,edge,vertexActual);	
		return heuristic.apply(target, goal, end);
	}

	@Override
	public Double estimatedWeightToEnd(V vertexActual,Double accumulateValue,Predicate<V> goal, V end, 
			TriFunction<V,Predicate<V>,V,Double> heuristic) {
		return heuristic.apply(vertexActual, goal, end);
	}
	
	@Override
	public E lastEdge() {
		return List2.last(this.edgeList);
	}

	@Override
	public PathType type() {
		return PathType.Last;
	}

	@Override
	public EGraph<V, E> graph() {
		return this.graph;
	}

}

