package us.lsi.alg.candidatos;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import us.lsi.common.Files2;
import us.lsi.common.List2;
import us.lsi.common.Set2;
import us.lsi.common.String2;

public class DatosCandidatos {
	
	public static record Candidato(String nombre, List<String> cualidades, Double sueldo, Integer valoracion, List<String> incompatibilidades) {
		public static Candidato create(String s) {
			String[] v0 = s.split(":");
			String[] v1 = v0[1].split(";");
			List<String> a = List2.parse(v1[0], ", ", x->x);
			Double b = Double.parseDouble(v1[1].trim());
			Integer c = Integer.parseInt(v1[2].trim());
			List<String> d = List2.parse(v1[3], ", ", x->x);
			return new Candidato(v0[0].trim(), a, b, c, d);
		}	
		@Override
		public String toString() {		
			return String.format("%s: %s; %.1f; %d; %s", nombre, cualidades, sueldo, valoracion, incompatibilidades);
		}		
	}

	private static Integer presupuestoMax;
	private static List<String> cualidades;
	private static List<Candidato> candidatos;
	
	public static void iniDatos(String fichero) {
		List<String> ls = Files2.linesFromFile(fichero);
		String cad = ls.remove(0).split(": ")[1];
		cualidades = List2.parse(cad, ", ", x->x);
		
		presupuestoMax = Integer.parseInt(ls.remove(0).split(": ")[1]);
		
		candidatos = List2.empty();
		ls.forEach(s -> candidatos.add(Candidato.create(s)));
		toConsole();
	}
	
	public static Integer getNumCandidatos() {
		return candidatos.size();
	}
	public static Integer getNumCualidades() {
		return cualidades.size();
	}
	public static Integer getPresupuestoMax() {
		return presupuestoMax;
	}
	public static Integer getValoracion(Integer i) {
		return candidatos.get(i).valoracion();
	}
	public static Double getSueldoMin(Integer i) {
		return candidatos.get(i).sueldo();
	}	
	public static Integer getTieneCualidad(Integer i, Integer j) {
		String s = cualidades.get(j);
		return candidatos.get(i).cualidades().contains(s)? 1: 0;
	}
	public static Boolean getSonIncompatibles(Integer i, Integer j) {
		String s = candidatos.get(j).nombre();
		return candidatos.get(i).incompatibilidades().contains(s);
	}
	
	public static Candidato getCandidato(Integer i) {
		return candidatos.get(i);
	}
	
	public static Set<String> getCualidades() {
		return Set2.copy(cualidades);
	}
	// Nueva funcion
	public static List<String> getCualidadesList() {
		return List2.copy(cualidades);
	}
	public static Set<Integer> getCualidadesSet(Integer i) {
		Set<Integer> s = Set2.empty();
		for (int j=0;j<DatosCandidatos.getNumCualidades();j++) {
			if (DatosCandidatos.getTieneCualidad(i, j)>0) {
				s.add(j);
			}
		}
		return s;
	}
	
	public static void toConsole() {
		String2.toConsole("Presupuesto Max.: %d", presupuestoMax);
		String2.toConsole(cualidades,"Cualidades");
		String2.toConsole(candidatos,"Candidatos");
		String2.toConsole(String2.linea());
	}
	
	// Test de la lectura del fichero
	public static void main(String[] args) throws IOException {
		iniDatos("ficheros/PI5Ej2DatosEntrada1.txt");
	}	
	
}
