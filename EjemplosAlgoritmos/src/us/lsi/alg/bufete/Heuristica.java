package us.lsi.alg.bufete;

import java.util.function.Predicate;

public class Heuristica {
	
	public static Double heuristic(BufeteVertex v1, Predicate<BufeteVertex> p, BufeteVertex v2) {
		return (double)v1.maxCarga();
	}

}
