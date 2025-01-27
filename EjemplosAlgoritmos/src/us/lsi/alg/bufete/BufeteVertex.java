package us.lsi.alg.bufete;

import java.util.List;

import us.lsi.graphs.virtual.VirtualVertex;

public interface BufeteVertex extends VirtualVertex<BufeteVertex, BufeteEdge, Integer>{
	
	Integer index();
	
	List<Integer> cargas();

	Integer npMax();

	Integer npMin();

	Integer maxCarga();

	List<Integer> cargasDespues(Integer a);

}