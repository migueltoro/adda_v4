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

public class GraphPathSum<V, E> extends GraphPath2<V,E> implements EGraphPath<V,E> {
	
	
	/**
	 * 
	 */
	

	public static <V, E> GraphPathSum<V, E> ofMap(EGraph<V, E> graph, V vertex, Map<V,Sp<E>> solutions){
		Preconditions.checkArgument(graph.pathType() == PathType.Sum, 
				String.format("El tipo del EGraphPath debe ser Sum y es %s",graph.pathType()));
		Sp<E> sp = solutions.get(vertex);
		GraphPathSum<V, E> gp = GraphPathSum.ofEdge(graph,sp.edge());
		while(sp.edge() != null) {
			vertex = Graphs.getOppositeVertex(graph,sp.edge(),vertex);
			sp = solutions.get(vertex);
			gp.add(sp.edge());
		}
		return gp;
	}
	
	public static <V, E> GraphPathSum<V, E> ofEdge(EGraph<V, E> graph, E edge) {
		Preconditions.checkArgument(graph.pathType() == PathType.Sum, 
				String.format("El tipo del EGraphPath debe ser Sum y es %s",graph.pathType()));
		V startVertex = graph.getEdgeSource(edge);
		V endVertex = graph.getEdgeTarget(edge);
		List<V> vertexList = List2.of(startVertex,endVertex);
		List<E> edgeList = List2.of(edge);
		Double weight = graph.getEdgeWeight(edge);
	    weight += graph.getVertexWeight(startVertex);
		weight += graph.getVertexWeight(endVertex);
		return new GraphPathSum<V, E>(graph, vertexList, edgeList, weight);
	}
	
	public static <V, E> GraphPathSum<V, E> ofVertex(EGraph<V, E> graph, V vertex) {
		Preconditions.checkArgument(graph.pathType() == PathType.Sum, 
				String.format("El tipo del EGraphPath debe ser Sum y es %s",graph.pathType()));
		List<V> vertexList = List2.of(vertex);
		List<E> edgeList = List2.of();
		Double weight = 0.;
	    weight += graph.getVertexWeight(vertex);
		return new GraphPathSum<V, E>(graph, vertexList, edgeList, weight);
	}

	protected EGraph<V, E> graph;
	
	protected GraphPathSum(EGraph<V, E> graph, List<V> vertexList, List<E> edgeList, Double weight) {
		super(graph, vertexList, edgeList, weight);
		this.graph = graph;
	}

	@Override
	public GraphPathSum<V,E> add(E edge) {
		Preconditions.checkNotNull(edge,"La arista no puede ser null");
		E lastEdge = this.getEdgeList().isEmpty()?null:List2.last(edgeList);
		V vertexActual = List2.last(vertexList);
		Preconditions.checkNotNull(vertexActual,"El v�rtice actual no puede ser null");
		super.edgeList.add(edge);
		V target = graph.oppositeVertex(edge,vertexActual);
		Preconditions.checkNotNull(target,"El v�rtice destino no puede ser null");
		super.vertexList.add(target);
		super.weight += graph.getEdgeWeight(edge);
		super.weight += graph.getVertexWeight(target);
		if (lastEdge != null) weight += graph.getVertexPassWeight(target, lastEdge, edge);
		return this;
	}
	
	@Override
	public GraphPathSum<V,E> remove() {
		E edge = List2.last(super.edgeList);
		Preconditions.checkNotNull(edge,"La arista no puede ser null");
		V vertexActual = List2.last(super.vertexList);
		Preconditions.checkNotNull(vertexActual,"El v�rtice actual no puede ser null");
		super.edgeList.remove(super.edgeList.size()-1);
		E lastEdge = this.getEdgeList().isEmpty()?null:List2.last(edgeList);
		super.vertexList.remove(super.vertexList.size()-1);
		super.weight -= graph.getEdgeWeight(edge);
		super.weight -= graph.getVertexWeight(vertexActual);
		if (lastEdge != null) weight -= graph.getVertexPassWeight(vertexActual, lastEdge, edge);
		return this;
	}
	
	@Override
	public Double add(V vertexActual, Double acumulateValue, E edgeOut, E edgeIn) {
		Preconditions.checkNotNull(edgeOut, "La arista no puede ser null");
		Double weight = acumulateValue;
		V target = Graphs.getOppositeVertex(graph, edgeOut, vertexActual);
		weight += graph.getEdgeWeight(edgeOut);
		weight += graph.getVertexWeight(target);
		if (edgeIn != null) weight += graph.getVertexPassWeight(vertexActual, edgeIn, edgeOut);
		return weight;
	}
	
	@Override
	public Double add(V vertexActual, Double acumulateValue, Double toEnd,E edgeOut, E edgeIn) {
		Preconditions.checkNotNull(edgeOut, "La arista no puede ser null");
		Double weight = acumulateValue+toEnd;
		if (edgeIn != null) weight += graph.getVertexPassWeight(vertexActual, edgeIn, edgeOut);
		return weight;
	}
	
	public GraphPathSum<V,E> copy() {
		return new GraphPathSum<V, E>(this.graph, 
				new ArrayList<>(this.vertexList), 
				new ArrayList<>(this.edgeList), 
				this.weight);
	}
	
	public GraphPathSum<V,E> concat(GraphPath<V,E> path) {
		GraphPathSum<V, E> r = this.copy();
		r.getEdgeList().stream().forEach(e->this.add(e));
		return r;
	}

	@Override
	public Double boundedValue(V vertexActual,Double acumulateValue, E edge, Predicate<V> goal, V end,  
			TriFunction<V,Predicate<V>,V,Double> heuristic) {
		Double weight = acumulateValue;
		V target = Graphs.getOppositeVertex(graph,edge,vertexActual);
		weight += graph.getEdgeWeight(edge);
		weight += graph.getVertexWeight(target);	
		Double r = weight + heuristic.apply(target, goal, end);
		return r;
	}

	@Override
	public Double estimatedWeightToEnd(V vertexActual,Double acumulateValue,Predicate<V> goal, V end, 
			TriFunction<V,Predicate<V>,V,Double> heuristic) {
		return acumulateValue+heuristic.apply(vertexActual, goal, end);
	}

	@Override
	public E lastEdge() {
		return List2.last(this.edgeList);
	}

	@Override
	public PathType type() {
		return PathType.Sum;
	}
	
	@Override
	public EGraph<V, E> graph() {
		return this.graph;
	}

}
