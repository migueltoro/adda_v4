package us.lsi.alg.multiconjuntos;

import us.lsi.graphs.virtual.VirtualVertex;

public interface MulticonjuntoVertex 
	extends VirtualVertex<MulticonjuntoVertex, MulticonjuntoEdge, Integer>{
	
	Integer indice();
	Integer sumaRestante();
	Double accionReal();
	
	public static MulticonjuntoVertex start() {
		return MulticonjuntoVertexI.start();
	}
	
	
}