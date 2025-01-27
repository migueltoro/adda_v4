package us.lsi.p4.ej_2;

import us.lsi.graphs.virtual.SimpleEdgeAction;

public record SubconjuntosEdge(SubconjuntosVertex source, SubconjuntosVertex target, Integer action, Double weight) 
			implements SimpleEdgeAction<SubconjuntosVertex,Integer> {

	public static SubconjuntosEdge of(SubconjuntosVertexI c1, SubconjuntosVertex c2, Integer action) {
		Double w = (double) DatosSubconjuntos.peso(c1.index())*action;
		return new SubconjuntosEdge(c1, c2, action, w);
	}
}
