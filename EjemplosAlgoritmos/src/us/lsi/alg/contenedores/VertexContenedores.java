package us.lsi.alg.contenedores;

import java.util.List;
import java.util.Set;

import us.lsi.graphs.virtual.VirtualVertex;

public interface VertexContenedores extends VirtualVertex<VertexContenedores,EdgeContenedores,Integer> {
	
	Integer index();
	
	List<Integer> capRest();

	Set<Integer> contenedoresCompletos();

}