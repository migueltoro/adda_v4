package us.lsi.alg.secuencias;

import us.lsi.graphs.virtual.SimpleEdgeAction;


public record SeqEdge(SeqVertex source, SeqVertex target, SeqAction action, Double weight) 
          implements SimpleEdgeAction<SeqVertex,SeqAction> {

	public static Double weight(SeqAction action) {
		return switch(action) {
		case a -> 1.;
		case c -> 1.;
		case e -> 1.;
		case m -> 0.;
		};
	}
	
	public static SeqEdge of(SeqVertex c1, SeqVertex c2, SeqAction action) {
		Double w = SeqEdge.weight(action);
		return new SeqEdge(c1, c2, action, w);
	}

}
