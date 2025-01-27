package us.lsi.alg.cursos;

import java.util.List;
import java.util.Locale;
import java.util.Set;

import us.lsi.common.Files2;
import us.lsi.common.List2;
import us.lsi.common.Set2;
import us.lsi.common.String2;
import us.lsi.common.IntegerSet;

public class DatosCursos {
	
	public static record Curso(Integer id, IntegerSet tematicas, Double precio, Integer centro) {
		public static int cont = 0;
		public static Curso parse(String s) {
			String[] v = s.split(":");
			return new Curso(cont++, 
				IntegerSet.parse(v[0].trim(), "{,}"),
				Double.parseDouble(v[1].trim()), 
				Integer.parseInt(v[2].trim()));
		}
		
		@Override
		public String toString() {
			return String.format("S%d = %s; Coste = %.1f; Centro = %s", id, tematicas, precio, centro);
		}	
	}

	public static IntegerSet tematicas;
	public static List<Curso> cursos;
	public static Integer maxCentros;
	//Numero de cursos
	public static Integer n;
	//Numero de centros
	public static Integer nc;
	//Numero de tematicas
	public static Integer m;
	

	public static void iniDatos(String fichero) {
		
		DatosCursos.cursos = List2.empty();
		DatosCursos.tematicas = IntegerSet.empty();
		
		List<String> ls = Files2.linesFromFile(fichero);
		
		DatosCursos.maxCentros = Integer.parseInt(ls.remove(0).split("=")[1].trim());
		
		for(String linea: ls) {
			Curso s = Curso.parse(linea);
			DatosCursos.cursos.add(s);
			DatosCursos.tematicas.addAll(s.tematicas());
		}
//		DatosCursos.universo =  DatosCursos.tematicas; // DatosCursos.tematicas.stream().toList();
		DatosCursos.n = DatosCursos.cursos.size();
		DatosCursos.nc = getNumCentros();
		DatosCursos.m = DatosCursos.tematicas.size();
		toConsole();
	}
	
	
	public static Double getCoste(Integer i) {
		return DatosCursos.cursos.get(i).precio();
	}
	public static Integer imparteTematica(Integer i, Integer j) {
		return DatosCursos.cursos.get(i).tematicas().contains(j)? 1: 0;
	}
	
	public static Curso getCurso(int i){
		return DatosCursos.cursos.get(i);
	}
	public static IntegerSet getTematicasDelCurso(int i){
		return DatosCursos.cursos.get(i).tematicas();
	}
	
	public static Integer getCentro(int i) {
		return DatosCursos.cursos.get(i).centro();
	}
	
	public static Boolean esImpartidoEn(Integer i, Integer j) {
		return DatosCursos.cursos.get(i).centro() == j;
	}
	
	public static Integer getNumCentros() {
		Set<Integer> centros = Set2.empty();
		for (int i=0; i<DatosCursos.n;i++) {
			centros.add(DatosCursos.getCentro(i));
		}
		return centros.size();
	}
	
	public static Integer getMaxCentros() {
		return DatosCursos.maxCentros;
	}
	
	public static IntegerSet getTematicas() {
		return DatosCursos.tematicas;
	}

	public static void toConsole() {
		String2.toConsole("Tematicas: %s", DatosCursos.tematicas);
		String2.toConsole(cursos,"Cursos");
		String2.toConsole("Numero maximo de centros en los que se puede matricular: %d", maxCentros);
		String2.toConsole(String2.line("_", 50));		
	}
	
	// Test de la lectura del fichero
	public static void main(String[] args) {
		Locale.setDefault(Locale.of("en", "US"));
		DatosCursos.iniDatos("ficheros/cursos/cursos3.txt");
	}

}
