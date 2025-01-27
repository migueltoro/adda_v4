package us.lsi.alg.investigadores;


import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.IntStream;


public class InvHeuristic {
	// Lo mas optimista: completar todos los trabajos que se puedan completar
	public static Double heuristic(InvVertex v1, Predicate<InvVertex> goal, InvVertex  v2) {
		if(v1.index() == DatosInv.na) return 0.;
		return IntStream.range(0, DatosInv.m).boxed()
				.filter(j->v1.esTrabajoTerminable(j) || v1.esTrabajoAcabado(j) )
				.mapToDouble(j->DatosInv.trabajos.get(j).calidad())
				.sum();
	}
	
	public static  InvVertex greedy(InvVertex start) {
		List<Integer> alternativas = new ArrayList<>();
		InvVertex v = start;
		for(int i = 0; i<DatosInv.na;i++) {
//			System.out.println(v);
			Integer a = v.greedyEdge().action();
			alternativas.add(a);
			v = v.neighbor(a);
		}
		return v;
	}
	
}
