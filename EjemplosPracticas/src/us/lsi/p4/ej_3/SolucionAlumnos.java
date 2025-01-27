package us.lsi.p4.ej_3;

import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.Collectors;

import org.jgrapht.GraphPath;

import us.lsi.common.List2;

public class SolucionAlumnos {
	
	public static SolucionAlumnos of(List<Integer> ls) {
		return new SolucionAlumnos(ls);
	}
	
	// Ahora en la PI5
	public static SolucionAlumnos of(GraphPath<AlumnosVertex, AlumnosEdge> path) {
		List<Integer> ls = path.getEdgeList().stream().map(e -> e.action()).toList();
		SolucionAlumnos res = of(ls);
		res.path = ls;
		return res;
	}

	private double af_tot;
	private SortedMap<Integer,List<String>> solucion;
	
	// Ahora en la PI5
	private List<Integer> path;
	
	private SolucionAlumnos(List<Integer> ls) {
		af_tot = 0;
		solucion = new TreeMap<>();
		for(int i=0; i<ls.size(); i++) {
			Integer j = ls.get(i);
			af_tot += DatosAlumnos.getAfinidad(i, j);
			if(solucion.containsKey(j))
				solucion.get(j).add(DatosAlumnos.getAlumno(i).nombre());
			else
				solucion.put(j, List2.of(DatosAlumnos.getAlumno(i).nombre()));
		}
	}
	
	@Override
	public String toString() {
		String s = String.format("\nAfinidad total: %.1f", af_tot);
		String res = solucion.entrySet().stream().map(e -> "Grupo "+(e.getKey()+1)+": "+e.getValue())
		.collect(Collectors.joining("\n", "Reparto obtenido:\n", s));
		return path==null? res: String.format("%s\nPath de la solucion: %s", res, path);		
	}
}

