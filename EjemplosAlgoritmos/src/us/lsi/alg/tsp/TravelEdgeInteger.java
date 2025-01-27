package us.lsi.alg.tsp;

import us.lsi.common.IntPair;
import us.lsi.graphs.virtual.SimpleEdgeAction;

public record TravelEdgeInteger(TravelVertexInteger source, TravelVertexInteger target, IntPair action, Double weight) 
            implements SimpleEdgeAction<TravelVertexInteger,IntPair> {

	public static TravelEdgeInteger of(TravelVertexInteger c1, TravelVertexInteger c2, IntPair action) {
		Double w = c2.weight()-c1.weight();
		return new TravelEdgeInteger(c1, c2, action, w);
	}

}
