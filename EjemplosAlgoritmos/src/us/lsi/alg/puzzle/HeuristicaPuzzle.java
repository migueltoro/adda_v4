package us.lsi.alg.puzzle;

import java.util.function.Predicate;
import java.util.stream.IntStream;

import us.lsi.common.IntPair;



public class HeuristicaPuzzle {

	
	public static Double heuristica(VertexPuzzle v1, Predicate<VertexPuzzle> goal, VertexPuzzle end) {
		return (double)v1.getNumDiferentes(end);
	}
	
	public static Integer manhattan(IntPair p1, IntPair p2) {
		return Math.abs(p1.first()-p2.first())+ Math.abs(p1.second()-p2.second());
	}
	
	public static Double heuristicaManhattan(VertexPuzzle v1, Predicate<VertexPuzzle> goal, VertexPuzzle end) {	
		return (double) IntStream.range(1,9)
				.map(i->manhattan(v1.positions().get(i),end.positions().get(i)))
				.sum();
	}
	

}
