package us.lsi.p3.ej_3;

import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.Collectors;

import us.lsi.common.List2;

public class SolucionAlumnos {
	
	public static SolucionAlumnos of(List<Integer> ls) {
		return new SolucionAlumnos(ls);
	}
	public static SolucionAlumnos empty() {
		return new SolucionAlumnos();
	}

	private double af_tot;
	private SortedMap<Integer,List<String>> solucion;
	
	private SolucionAlumnos(List<Integer> ls) {
		af_tot = 0;
		solucion = new TreeMap<>();
		for(int n=0; n<ls.size(); n++) {
			if(ls.get(n)>0) {
				int i = n%DatosAlumnos.getNumAlumnos();
				int j = n/DatosAlumnos.getNumAlumnos();
				af_tot += DatosAlumnos.getAfinidad(i,j);
				if(solucion.containsKey(j))
					solucion.get(j).add(DatosAlumnos.getAlumno(i).nombre());
				else
					solucion.put(j, List2.of(DatosAlumnos.getAlumno(i).nombre()));
			}
		}
	}
	
	private SolucionAlumnos() {
		af_tot = 0;
		solucion = new TreeMap<>();
	}
	public void add(int i, int j) {
		af_tot += DatosAlumnos.getAfinidad(i,j);
		if(solucion.containsKey(j))
			solucion.get(j).add(DatosAlumnos.getAlumno(i).nombre());
		else
			solucion.put(j, List2.of(DatosAlumnos.getAlumno(i).nombre()));		
	}
	
	@Override
	public String toString() {
		double med = af_tot/DatosAlumnos.getNumAlumnos();
		String s = String.format("\nAfinidad total: %.1f; A. med: %.1f", af_tot, med);
		return solucion.entrySet().stream().map(e -> "Grupo "+(e.getKey()+1)+": "+e.getValue())
		.collect(Collectors.joining("\n", "Reparto obtenido:\n", s));
	}
}

