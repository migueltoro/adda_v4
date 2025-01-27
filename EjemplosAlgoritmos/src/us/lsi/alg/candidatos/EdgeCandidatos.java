package us.lsi.alg.candidatos;

import us.lsi.graphs.virtual.SimpleEdgeAction;

public record EdgeCandidatos(VertexCandidatos source, VertexCandidatos target, Boolean action, Double weight) 
	implements SimpleEdgeAction<VertexCandidatos,Boolean> {

	public static EdgeCandidatos of(VertexCandidatos v1, VertexCandidatos v2, Boolean a) {
		double peso = a?DatosCandidatos.getValoracion(v1.index()):0;
		return new EdgeCandidatos(v1, v2, a, peso);
	}
	
}
