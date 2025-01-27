package us.lsi.alg.monedas;


import java.util.function.Predicate;


public class MonedasHeuristica {
	
	public static Double heuristic(MonedasVertex v1, Predicate<MonedasVertex> goal, MonedasVertex v2) {
		return heuristic(v1);
	}
	
	public static Double heuristic(MonedasVertex v1) {
		
		Double r = 0.;
		Double p = 0.;
		Integer index = v1.index();
		Double valorRestante = v1.valorRestante().doubleValue();
		while (valorRestante> 0 && index < DatosMonedas.n) {
			Double a = valorRestante / DatosMonedas.valor(index);
			r = r + a * DatosMonedas.valor(index);
			p = p + a * DatosMonedas.peso(index);
			valorRestante = valorRestante - a * DatosMonedas.valor(index);
			index = index + 1;
		}
		return p;
	}
	

}
