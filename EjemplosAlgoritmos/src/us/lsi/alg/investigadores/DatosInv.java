package us.lsi.alg.investigadores;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import us.lsi.common.Files2;
import us.lsi.common.IntPair;
import us.lsi.common.List2;
import us.lsi.common.Map2;
import us.lsi.common.String2;


public class DatosInv {

	public static enum VertexType {
		SinIniciar, Iniciado, Acabado
	}

	public static record Investigador(String nombre, Integer diasDisponibles, Integer especialidad) {

		public static Investigador create(String s) {
			String[] v0 = s.split(":");
			String[] v1 = v0[1].split(";");
			String a = v1[0].split("=")[1].trim();
			String b = v1[1].split("=")[1].trim();
			return new Investigador(v0[0].trim(), Integer.parseInt(a), Integer.parseInt(b));
		}

		@Override
		public String toString() {
			return this.nombre + ": " + this.diasDisponibles + "; " + this.especialidad;
		}
	}

	// diasNecesarios[j,k] días necesarios de la especialidad k para el trabajo,
	// dn[j,k]

	public static record Trabajo(String trabajo, Integer calidad, Map<Integer, Integer> diasNecesarios) {
		public static Trabajo create(String s) {
			String[] v0 = s.split("->");
			String[] v1 = v0[1].trim().split(";");
			Integer a = Integer.parseInt(v1[0].split("=")[1].trim());

			Map<Integer, Integer> b = Map2.empty();
			String[] v2 = v1[1].split("=")[1].trim().split(",");
			for (String e : v2) {
				String[] v3 = e.trim().split("[:()]");
				b.put(Integer.parseInt(v3[1].trim()), Integer.parseInt(v3[2].trim()));
			}
			return new Trabajo(v0[0].trim(), a, b);
		}

		@Override
		public String toString() {
			return this.trabajo + ": " + this.calidad + "; " + this.diasNecesarios + "; ";
		}
	}

	public static void line(String s) {
		String2.toConsole(IntStream.range(0, 100).boxed().map(i -> s).collect(Collectors.joining()));
	}

	public static List<Investigador> investigadores;
	public static List<Trabajo> trabajos;
	// numInvestigadores
	public static Integer n;
	// numTrabajos;
	public static Integer m;
	// numEspecialidades
	public static Integer r;
	// p = n*m;
	public static Integer na;
	// Dias de especialidad necesarios para (j,k)
	public static Map<IntPair, Integer> dEn = null;
	// Lista de dias de especialidades necesarios para cada trabajo j
	public static List<List<Integer>> ldEn;
	// Lista de dias disponibles para cada investigador i
	public static List<Integer> ldid;
	

	public static void iniDatos(String fichero) {
		DatosInv.investigadores = List2.empty();
		DatosInv.trabajos = List2.empty();
		for (String s : Files2.linesFromFile(fichero)) {
			if (s.startsWith("//"))
				continue;
			else if (s.startsWith("INV"))
				DatosInv.investigadores.add(Investigador.create(s));
			else
				DatosInv.trabajos.add(Trabajo.create(s));
		}
		// numInvestigadores
		DatosInv.n = DatosInv.investigadores.size();
		;
		// numTrabajos;
		DatosInv.m = DatosInv.trabajos.size();
		// numEspecialidades
		DatosInv.r = DatosInv.investigadores.stream()
				.map(inv->inv.especialidad())
				.collect(Collectors.toSet())
				.size();
		// pna = n*rm;
		DatosInv.na = DatosInv.n * DatosInv.m;
//		Comparator<Trabajo> cmp1 = Comparator.comparing(t->t.calidad());
//		Collections.sort(DatosInv.trabajos,cmp1.reversed());
//		Comparator<Investigador> cmp2 = Comparator.comparing(inv->inv.diasDisponibles());
//		Collections.sort(DatosInv.investigadores,cmp2);
		
		DatosInv.ldEn=getListaDeDiasDeEspecialidadNecesarios();
		DatosInv.ldid =  DatosInv.investigadores.stream().map(inv->inv.diasDisponibles()).toList();
	}


	// devuelve las lista de dias de la especialidad k que son necesarios para completar el
	// trabajo j
	
	public static List<List<Integer>> getListaDeDiasDeEspecialidadNecesarios() {
		Map<Integer,List<Integer>> m = new HashMap<>();
		for (int j = 0; j < DatosInv.m; j++) {
			List<Integer> r = new ArrayList<>();
			for (int k = 0; k < DatosInv.r; k++) {
				Integer d = DatosInv.trabajos.get(j).diasNecesarios().get(k);
				r.add(d);
			} 
			m.put(j, r);
		}
		return IntStream.range(0, DatosInv.m).boxed().map(j->m.get(j)).toList();
	}

	public static Integer totalDiasTrabajo(Integer j) {
		return DatosInv.trabajos.get(j).diasNecesarios().values().stream().reduce(0, (x, y) -> x + y);
	}
	
	public static Boolean esCero(List<Integer> ls) {
		return ls.stream().allMatch(e->e==0);
	}
	
	public static Boolean esMenor(List<Integer> ls1, List<Integer> ls2) {
		return ls1.size() == ls2.size() && IntStream.range(0, ls1.size()).boxed()
				.anyMatch(i->ls1.get(i) < ls2.get(i)) &&
				ls1.size() == ls2.size() && IntStream.range(0, ls1.size()).boxed()
				.allMatch(i->ls1.get(i) <= ls2.get(i));
	}
	
	public static Boolean esMenorOIgual(List<Integer> ls1, List<Integer> ls2) {
		return ls1.size() == ls2.size() && IntStream.range(0, ls1.size()).boxed()
				.allMatch(i->ls1.get(i) <= ls2.get(i));
	}
	
	public static Boolean sonIguales(List<Integer> ls1, List<Integer> ls2) {
		return ls1.size() == ls2.size() && IntStream.range(0, ls1.size()).boxed()
				.allMatch(i->ls1.get(i) == ls2.get(i));
	}
	

	public static void toConsole() {
		String2.toConsole(Arrays.asList(DatosInv.n, DatosInv.m, DatosInv.r, DatosInv.na), "Constantes");
		String2.toConsole(DatosInv.investigadores, "Investigadores");
		String2.toConsole(DatosInv.trabajos, "Trabajos");
		String2.toConsole(String2.line("_",50));
	}

	public static void main(String[] args) {
		DatosInv.iniDatos("ficheros/investigadores/investigadores1.txt");
	}

}
