package us.lsi.alg.monedas;


import us.lsi.graphs.virtual.SimpleEdgeAction;

public record MonedasEdge(MonedasVertex source, MonedasVertex target, Integer action, Double weight) 
           implements SimpleEdgeAction<MonedasVertex,Integer> {

	public static MonedasEdge of(MonedasVertex c1, MonedasVertex c2, Integer action) {
		Double w = (double) (action*DatosMonedas.peso(c1.index()));
		return new MonedasEdge(c1, c2, action, w);
	}

}
