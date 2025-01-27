package us.lsi.alg.candidatos;

import java.util.Set;

import us.lsi.common.IntegerSet;
import us.lsi.graphs.virtual.VirtualVertex;

public interface VertexCandidatos extends VirtualVertex<VertexCandidatos,EdgeCandidatos,Boolean> {
	
	Integer index();
	
	IntegerSet seleccion();
	
	Integer pRest();

	Set<Integer> cualidadesCubiertas();

}