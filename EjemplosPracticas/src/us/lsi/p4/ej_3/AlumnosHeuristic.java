package us.lsi.p4.ej_3;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.IntStream;

public class AlumnosHeuristic {
	// Lo mas optimista: los alumnos que quedan estaran en el mejor grupo posible con hueco
	public static Double heuristic(AlumnosVertex v1, Predicate<AlumnosVertex> goal, AlumnosVertex v2) {
		return IntStream.range(v1.index(), DatosAlumnos.getNumAlumnos())
		         .mapToDouble(i -> mejorOpcion(i, v1.remaining())).sum();
	}

	private static Double mejorOpcion(Integer i, List<Integer> remaining) {
		return IntStream.range(0, DatosAlumnos.getNumGrupos())
				.filter(j -> remaining.get(j)>0)
				.mapToDouble(j -> DatosAlumnos.getAfinidad(i, j)).max().orElse(0);
	}	
}
