package us.lsi.p4.ej_2;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.jgrapht.GraphPath;

public record SolucionSubconjuntos(Double peso, Set<String> conjuntos, Boolean cubre) 
           implements Comparable<SolucionSubconjuntos>{
	
	public static SolucionSubconjuntos of(GraphPath<SubconjuntosVertex, SubconjuntosEdge> path) {
		List<Integer> la = path.getEdgeList().stream().map(e->e.action()).toList();
		return SolucionSubconjuntos.of(la);
	}

	public static SolucionSubconjuntos of(List<Integer> ls) {
		Set<String> cs = new HashSet<>();
		Double ps = 0.;
		Set<Integer> s = new HashSet<>();
		for (int i = 0; i < ls.size(); i++) {
			if (ls.get(i) == 1) {
				cs.add(DatosSubconjuntos.nombre(i));
				ps += DatosSubconjuntos.peso(i);
				s.addAll(DatosSubconjuntos.conjunto(i));
			}
		}
		Boolean c = s.equals(DatosSubconjuntos.universo());
		return new SolucionSubconjuntos(ps,cs,c);
	}

	@Override
	public int compareTo(SolucionSubconjuntos other) {
		return this.peso().compareTo(other.peso());
	}
}
