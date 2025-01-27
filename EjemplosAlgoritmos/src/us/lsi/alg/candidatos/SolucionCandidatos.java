package us.lsi.alg.candidatos;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.jgrapht.GraphPath;

import us.lsi.alg.candidatos.DatosCandidatos.Candidato;

public class SolucionCandidatos {
	
	public static SolucionCandidatos of(GraphPath<VertexCandidatos, EdgeCandidatos> path) {

		/*
		List<Integer> gp_as = gp.getEdgeList().stream().map(x -> x.action())
				.collect(Collectors.toList()); // getEdgeList();
		*/
		
		List<Boolean> alternativas = new ArrayList<>();
		
		for (EdgeCandidatos alternativa : path.getEdgeList()) {
			alternativas.add(alternativa.action());
		}

		return SolucionCandidatos.of(alternativas);
	}		
	
	public static SolucionCandidatos of(List<Boolean> ls) {
		return new SolucionCandidatos(ls);
	}

	private List<Candidato> solucion;

	// ls[i]=1 => se ha seleccionado al candidato i-esimo
	private SolucionCandidatos(List<Boolean> ls) {
		solucion = IntStream.range(0, ls.size())
				            .filter(i->ls.get(i))
		                    .mapToObj(i->DatosCandidatos.getCandidato(i))
		                    .toList();
	}
	
	@Override
	public String toString() {
		Double val = solucion.stream().mapToDouble(x-> x.valoracion()).sum();
		Double sueldos = solucion.stream().mapToDouble(x-> x.sueldo()).sum();
		return solucion.stream().map(Candidato::toString)
		.collect(Collectors.joining("\n", "Candidatos Seleccionados:\n", "\n")) 
		+ "Sueldos totales: " + sueldos + "\n"
		+ "Valores totales: " + val + "\n";
	}

}
