package us.lsi.alg.investigadores;


import us.lsi.graphs.virtual.SimpleEdgeAction;

public record InvEdge(InvVertex source, InvVertex target, Integer action, Double weight)
		implements SimpleEdgeAction<InvVertex,Integer> {

	public static InvEdge of(InvVertex v1, InvVertex v2, Integer a) {
		return new InvEdge(v1, v2, a, 1.);
	}
}
