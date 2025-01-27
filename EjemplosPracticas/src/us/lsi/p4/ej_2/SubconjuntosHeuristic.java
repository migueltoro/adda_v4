package us.lsi.p4.ej_2;


import java.util.function.Predicate;
import java.util.stream.IntStream;

public class SubconjuntosHeuristic {

	public static Double heuristic(SubconjuntosVertex v1, Predicate<SubconjuntosVertex> goal, SubconjuntosVertex v2) {
		return heuristic1(v1, DatosSubconjuntos.NUM_SC);
	}
	

	public static Double heuristic0(SubconjuntosVertex vertice, Integer lastIndex) {
		return 0.;
	}

	public static Double heuristic1(SubconjuntosVertex vertice, Integer lastIndex) {
		if (vertice.remaining().isEmpty())  return 0.;
		else return IntStream.range(vertice.index(),lastIndex)
				.mapToDouble(i->DatosSubconjuntos.peso(i))
				.min()
				.getAsDouble();
	}

}


