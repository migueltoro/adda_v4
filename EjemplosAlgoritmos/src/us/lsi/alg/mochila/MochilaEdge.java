package us.lsi.alg.mochila;

import us.lsi.graphs.virtual.SimpleEdgeAction;
import us.lsi.mochila.datos.DatosMochila;

public record MochilaEdge(MochilaVertex source, MochilaVertex target, Integer action, Double weight) 
                   implements SimpleEdgeAction<MochilaVertex,Integer> {
	
	public static MochilaEdge of(MochilaVertex v1, MochilaVertex v2, Integer a) {	
		Double w = a*DatosMochila.getValor(v1.index()).doubleValue();
		return new MochilaEdge(v1,v2, a, w);
	}
	
}

