package us.lsi.graphs.virtual;

import org.jgrapht.Graph;

public record VirtualEdgeG<V, E>(VirtualVertexG<V, E> source, VirtualVertexG<V, E> target, E action,Double weight)
		implements SimpleEdgeAction<VirtualVertexG<V, E>, E> {

	public static <V, E> VirtualEdgeG<V, E> of(VirtualVertexG<V, E> c1, VirtualVertexG<V, E> c2, E action,Double weight) {
		return new VirtualEdgeG<V, E>(c1, c2, action, weight);
	}
	
	public static <V, E> VirtualEdgeG<V, E> of(E edge, Graph<V,E> graph) {
		return new VirtualEdgeG<V, E>(
				VirtualVertexG.of(graph.getEdgeSource(edge),graph),
				VirtualVertexG.of(graph.getEdgeTarget(edge),graph),
				edge,
				graph.getEdgeWeight(edge));
	}

}
