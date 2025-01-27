package us.lsi.alg.asignaturas;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import us.lsi.common.Files2;
import us.lsi.common.List2;

public class DatosAsignaturas {
	public static List<List<Integer>>mejoras; 
	public static Integer ND = 10;
	public static Integer NA = 4;
	public static void iniciarDatos(String fichero) {
		mejoras = new ArrayList<>();
		List<String>filas = Files2.linesFromFile(fichero);
		mejoras.add(List2.of(0,0,0,0));
		for(String fila : filas) {
			List<Integer>mejorasFila = Arrays
					.stream(fila.split(","))
					.map(n->Integer.valueOf(n))
					.collect(Collectors.toList());
			mejoras.add(mejorasFila);
		}
	}
	public static Integer getMejora(Integer numDias, Integer asignatura) {
		return mejoras.get(numDias).get(asignatura);
	}

}

