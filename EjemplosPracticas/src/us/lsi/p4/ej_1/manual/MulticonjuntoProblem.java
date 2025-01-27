package us.lsi.p4.ej_1.manual;

import java.util.List;
import us.lsi.alg.multiconjuntos.DatosMulticonjunto;
import us.lsi.common.List2;

public record MulticonjuntoProblem(Integer index, Integer remaining) {

	public static MulticonjuntoProblem initial() {
		return of(0, DatosMulticonjunto.SUM);
	}

	public static MulticonjuntoProblem of(Integer i, Integer rest) {
		return new MulticonjuntoProblem(i, rest);
	}
	
	public List<Integer> actions() {
		List<Integer> alternativas = List2.empty();
		if(index < DatosMulticonjunto.NUM_E) {
			Integer value = DatosMulticonjunto.getElemento(index);
			Integer options = remaining / value;
			if(index == DatosMulticonjunto.NUM_E-1) {
				if(remaining % value == 0)
					alternativas = List2.of(remaining / value);
				else
					alternativas = List2.of(0);
			} else {
				alternativas = List2.rangeList(0, options + 1);
			}
		}
		return alternativas;
	}

	public MulticonjuntoProblem neighbor(Integer a) {
		return of(index + 1, remaining - a*DatosMulticonjunto.getElemento(index));
	}

//	// Lo mas optimista: si todavia falta, con el mayor de los que quedan puede ser
//	public Double heuristic() {
//		Double res = 0.;
//		if(remaining>0) {
//			Integer max = IntStream.range(index, DatosMulticonjunto.NUM_E)
//					.map(i -> DatosMulticonjunto.getElemento(i))
//					.filter(e -> e<=remaining).max().orElse(0);
//			if(max>0) {
//				Integer r = remaining()/max;
//				res += remaining()%max==0? r: r+1;				
//			} else  // Penalizacion estilo genetico
//				res += 100;
//		} else if(remaining<0)  // Penalizacion estilo genetico
//			res += 100;
//		
//		return res;
//	}
	
	
	
	public Double heuristic() {
		Double h = 0.;
		if (index < DatosMulticonjunto.NUM_E) {  
			Integer h1 = remaining / DatosMulticonjunto.getElemento(index);
			if (h1*DatosMulticonjunto.getElemento(index) < remaining)
				h1 = h1+1;   // hace falta al menos uno más de los que caben si hay hueco
			h = h1*1.0;
		} 
		return h;
	}


}
