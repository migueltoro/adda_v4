package us.lsi.alg.puzzle;

import us.lsi.graphs.virtual.SimpleEdgeAction;

public record EdgePuzzle(VertexPuzzle source, VertexPuzzle target, ActionPuzzle action, Double weight)
         implements SimpleEdgeAction<VertexPuzzle,ActionPuzzle> {
	
	public static EdgePuzzle of(VertexPuzzle v1, VertexPuzzle v2, ActionPuzzle a) {		
		return new EdgePuzzle(v1, v2, a, 1.);
	}
	
}


