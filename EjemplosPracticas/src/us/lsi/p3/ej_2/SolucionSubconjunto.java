package us.lsi.p3.ej_2;

import java.util.List;
import java.util.stream.Collectors;

import us.lsi.common.List2;
import us.lsi.p3.ej_2.DatosSubconjunto.Subconjunto;

public class SolucionSubconjunto {
	
	public static SolucionSubconjunto create(List<Integer> ls) {
		return new SolucionSubconjunto(ls);
	}
	
	private Double total;
	private List<Subconjunto> subconjuntos;	

	private SolucionSubconjunto(List<Integer> ls) {
		total = 0.;
		subconjuntos = List2.empty();
		for(int i=0; i<ls.size(); i++) {
			if(ls.get(i)>0) {
				total += DatosSubconjunto.getPeso(i);
				subconjuntos.add(DatosSubconjunto.getSubConjunto(i));
			}
		}
	}

	@Override
	public String toString() {
		String s = subconjuntos.stream().map(e -> "S"+e.id())
		.collect(Collectors.joining(", ", "Conjuntos elegidos: {", "}\n"));
		return String.format("%sCoste Total: %.1f", s, total);	
	}
}

