package us.lsi.alg.contenedores;

import java.util.List;
import java.util.Locale;
import java.util.function.Predicate;
import java.util.stream.IntStream;

public class TestVertexContenedores {

	public static void main(String[] args) {
		Locale.setDefault(Locale.of("en", "US"));

		for (Integer i = 1; i < 2; i++) {

			DatosContenedores.iniDatos("ficheros/contenedores/contenedores" + i + ".txt");
			DatosContenedores.toConsole();
			System.out.println("\n\n>\tResultados para el test " + i + "\n");

			// V�rtices clave

			VertexContenedoresI start = VertexContenedoresI.initial();
			Predicate<VertexContenedoresI> goal = v->v.goal();

			List<Integer> r = IntStream.range(0, DatosContenedores.getNumContenedores() + 1).boxed()
					.filter(j -> !start.contenedoresCompletos().contains(j))
					.filter(j -> DatosContenedores.getPuedeUbicarse(start.index(), j))
//	        		.filter(j->DatosContenedores.getTamElemento(start.index()) <= start.capRest().get(j))
					.toList();

			System.out.println(start);
			System.out.println(start.contenedoresCompletos());
			System.out.println(r);
			System.out.println(DatosContenedores.getTamElemento(start.index()));
			for (int j = 0; j < DatosContenedores.getNumContenedores() + 1; j++) {
				System.out.println(j + " = " + start.capRest().get(j));
			}
			VertexContenedoresI sc = start.neighbor(0);
			System.out.println(sc);
			System.out.println(sc.contenedoresCompletos());
			System.out.println(r);
			System.out.println(DatosContenedores.getTamElemento(sc.index()));
			for (int j = 0; j < DatosContenedores.getNumContenedores() + 1; j++) {
				System.out.println(j + " = " + sc.capRest().get(j));
			}
			System.out.println(goal.test(start));
		}
	}

}
