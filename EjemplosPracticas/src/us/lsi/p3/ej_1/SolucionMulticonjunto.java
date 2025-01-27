package us.lsi.p3.ej_1;

import java.util.List;
import us.lsi.common.Multiset;

public class SolucionMulticonjunto {
	
	public static SolucionMulticonjunto of_Range(List<Integer> ls) {
		return new SolucionMulticonjunto(ls);
	}

	private Integer suma, tam;
	private Multiset<Integer> solucion;

	private SolucionMulticonjunto() {
		suma = tam = 0;
		solucion = Multiset.empty();
	}
	private SolucionMulticonjunto(List<Integer> ls) {
		suma = tam  = 0;
		solucion = Multiset.of();
		for(int i=0; i<ls.size(); i++) {
			if(ls.get(i)>0) {				
				Integer e = ls.get(i);
				Integer v = DatosMulticonjunto.getElemento(i);
				tam += e;
				solucion.add(v, e);
				suma += v*e;
			}
		}
	}
	
	public static SolucionMulticonjunto empty() {
		return new SolucionMulticonjunto();
	}

	public void add(Integer e) {
		solucion.add(e, 1);
		tam++;
		suma += e;
	}
	public void add(Integer e, Integer n) {
		solucion.add(e, n);
		tam += n;
		suma += e*n;
	}

	@Override
	public String toString() {
		int error = Math.abs(DatosMulticonjunto.getSuma() - suma);
		String e = error<1? "": String.format("Error = %d", error);
		return String.format("MS = %s; Distintos = %d; Total = %d; %s", solucion, solucion.size(), tam, e);
	}
}

