package us.lsi.alg.secuencias;

import java.util.function.Predicate;
import java.util.stream.IntStream;

public class SeqHeuristic {
	
	public static Character getChar(String s, Integer i) {
		if(i<s.length()) return s.charAt(i);
		else return null;
	}
	
	public static Double heuristic(SeqVertex v1, Predicate<SeqVertex> p, SeqVertex v2) {
		return (double) IntStream.range(v1.index(),Math.max(v1.n(),SeqVertex.n2))
				.filter(i->SeqHeuristic.getChar(v1.s(),i)!=SeqHeuristic.getChar(v2.s(),i))
				.count();
	}

}
