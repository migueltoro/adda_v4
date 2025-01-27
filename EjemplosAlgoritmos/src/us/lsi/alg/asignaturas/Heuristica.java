package us.lsi.alg.asignaturas;

import java.util.function.Predicate;

public class Heuristica {
	
	public static Double heuristic(AsignaturasVertice v1, Predicate<AsignaturasVertice> goal, AsignaturasVertice v2) {
		Integer r = v1.getPeso()+5*(DatosAsignaturas.ND-v1.index());
		return (double)r;
	}

}
