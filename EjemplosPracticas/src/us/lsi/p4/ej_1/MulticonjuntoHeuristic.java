package us.lsi.p4.ej_1;

import java.util.function.Predicate;


public class MulticonjuntoHeuristic {

	public static Double heuristic(MulticonjuntoVertex v1, Predicate<MulticonjuntoVertex> goal, MulticonjuntoVertex v2) {
		Double h = 0.;
		if (v1.indice() < DatosMulticonjunto.NUM_E) {  
			Integer h1 = v1.sumaRestante() / DatosMulticonjunto.getElemento(v1.indice());
			if (h1*DatosMulticonjunto.getElemento(v1.indice()) < v1.sumaRestante())
				h1 = h1+1;   // hace falta al menos uno más de los que caben si hay hueco
			h = h1*1.0;
		} 
		return h;
	}


}


