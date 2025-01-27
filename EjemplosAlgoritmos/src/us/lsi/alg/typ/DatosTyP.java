package us.lsi.alg.typ;

import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import us.lsi.common.Files2;

public class DatosTyP {
	
	public static record Tarea(Integer id, Integer duracion) {
		private static Integer nId = 0;
		public static Tarea parse(String s) {
			Integer id = nId;
			nId++;
			return new Tarea(id,(int)Double.parseDouble(s));
		}
		public String toString() {
			return String.format("(%d,%d)",id,duracion);
		}
	}
	
	public static Integer numeroDeProcesadores;
	public static Integer numeroDeTareas;
	public static List<Tarea> tareas;
	public static TyPVertex inicial;
	public static Integer n;
	public static Integer m;
	
	public static void datos(String fichero, Integer np) {
		DatosTyP.tareas = Files2.streamFromFile(fichero).map(s -> Tarea.parse(s))
				.sorted(Comparator.comparing(Tarea::duracion).reversed())
				.collect(Collectors.toList());
		DatosTyP.n = tareas.size();
		DatosTyP.numeroDeProcesadores = np;
		DatosTyP.numeroDeTareas = DatosTyP.tareas.size();
		DatosTyP.n = numeroDeTareas;
		DatosTyP.m = np;
	}
	
	public static Tarea tarea(Integer index) {
		return DatosTyP.tareas.get(index);
	}
	
	public static Integer duracion(Integer index) {
		return DatosTyP.tareas.get(index).duracion();
	}
	
	public static void toConsole() {
		System.out.println(tareas);
		System.out.println(String.format("numTareas = %d, numProcesadores = %d",n,m));
	}

	public static void main(String[] args) {
		Locale.setDefault(Locale.of("en", "US"));
		DatosTyP.datos("ficheros/tareas.txt",5);
		toConsole();
	}

}
