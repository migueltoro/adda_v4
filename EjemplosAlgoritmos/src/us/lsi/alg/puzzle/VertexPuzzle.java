package us.lsi.alg.puzzle;

import java.util.Map;

import us.lsi.common.IntPair;
import us.lsi.graphs.virtual.VirtualVertex;

public interface VertexPuzzle extends VirtualVertex<VertexPuzzle, EdgePuzzle, ActionPuzzle>{
	
	public static Integer numFilas = 3;
	public static Integer n = numFilas;
	
	public static VertexPuzzle of(Integer... d){
		return VertexPuzzleI.of(d);
	}

	IntPair blackPosition();
	
	Integer get(Integer f, Integer c);

	Integer get(IntPair p);
	
	Integer numFilas();
	
	Boolean isSolvable();
	
	Boolean isSolvable(VertexPuzzle other);

	Integer getNumDiferentes(VertexPuzzle end);

	Map<Integer,IntPair> positions();
	
	VertexPuzzleI swap(IntPair np);
	
	Integer getInvCount();

}