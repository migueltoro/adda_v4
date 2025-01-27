package us.lsi.alg.typ;

import java.util.function.Predicate;

public class Heuristica {
	
	public static Double heuristic(TyPVertex v1, Predicate<TyPVertex> p, TyPVertex v2) {
		return v1.maxCarga();
	}
}
