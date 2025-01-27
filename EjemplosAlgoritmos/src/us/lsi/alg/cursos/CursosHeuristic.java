package us.lsi.alg.cursos;

import java.util.function.Predicate;
import java.util.stream.IntStream;

public class CursosHeuristic {
	// Lo mas optimista: si todavia quedan por completar tematicas, el coste del
	// curso de menor coste de entre os que tengan
	// intersección con las temáticas restantes.
	public static Double heuristic(CursosVertex v1, Predicate<CursosVertex> goal, CursosVertex v2) {
		if (v1.remaining().isEmpty())
			return 0.;
		return IntStream.range(v1.index(), DatosCursos.n)
				.filter(i -> !v1.remaining().intersection(DatosCursos.getTematicasDelCurso(i)).isEmpty())
				.mapToDouble(i -> DatosCursos.getCoste(i)).min().orElse(0.);
	}
}

