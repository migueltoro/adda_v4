package us.lsi.alg.colorgraphs;

import us.lsi.graphs.virtual.SimpleEdgeAction;

public record ColorEdge(ColorVertex source, ColorVertex target, Integer action, Double weight) 
             implements SimpleEdgeAction<ColorVertex,Integer> {

	public static ColorEdge of(ColorVertex c1, ColorVertex c2, Integer action) {
		return new ColorEdge(c1, c2, action, 1.);
	}

}
