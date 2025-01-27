package us.lsi.graphs.virtual;

import org.jgrapht.graph.DirectedMultigraph;

public record VirtualEdgeMG<V, E>(VirtualVertexMG<V, E> source, VirtualVertexMG<V, E> target, E action, Double weight)
		implements SimpleEdgeAction<VirtualVertexMG<V, E>, E> {

	public static <V, E> VirtualEdgeMG<V, E> of(VirtualVertexMG<V, E> c1, VirtualVertexMG<V, E> c2, E action, Double weight) {
		return new VirtualEdgeMG<V, E>(c1, c2, action, weight);
	}
	
	public static <V, E> VirtualEdgeMG<V, E> of(E edge, DirectedMultigraph<V,E> graph) {
		return new VirtualEdgeMG<V, E>(
				VirtualVertexMG.of(graph.getEdgeSource(edge),edge,graph),
				VirtualVertexMG.of(graph.getEdgeTarget(edge),edge,graph),
				edge,
				graph.getEdgeWeight(edge));
	}

}
