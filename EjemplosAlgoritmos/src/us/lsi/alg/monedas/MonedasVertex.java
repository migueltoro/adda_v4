package us.lsi.alg.monedas;

import us.lsi.graphs.virtual.VirtualVertex;

public interface MonedasVertex extends VirtualVertex<MonedasVertex, MonedasEdge, Integer>{
	
	Integer index();
	Integer valorRestante();
	
	public static MonedasVertex first(Integer valorInicial) {
		return MonedasVertexI.first(valorInicial);
	}

}