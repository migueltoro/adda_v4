package us.lsi.alg.candidatos;

import java.util.function.Predicate;



public class CandidatosHeuristic {
	public static Double heuristic(VertexCandidatos v1, Predicate<VertexCandidatos> goal,
			VertexCandidatos  v2) {
		return (double)(DatosCandidatos.getNumCandidatos()-v1.index() ) * 5.0;
	}
	
}
