package us.lsi.alg.mochila;

import org.jgrapht.GraphPath;

import us.lsi.graphs.virtual.VirtualVertex;

public interface MochilaVertex extends VirtualVertex<MochilaVertex, MochilaEdge, Integer> {

	public Integer capacidadInicial();
	public Integer index();
	public Integer capacidadRestante();
	
	public static SolucionMochila getSolucion(GraphPath<MochilaVertex, MochilaEdge> path) {
		return MochilaVertexI.getSolucion(path);
	}
	public static MochilaVertex initialVertex() {
		return MochilaVertexI.initialVertex();
	}
	public static MochilaVertex of(Integer i, Integer capacidadInicial) {
		return MochilaVertexI.of(i, capacidadInicial);
	}
	
	public static Double heuristicAction(Integer index, Double cr) {
		return MochilaVertexI.heuristicAction(index, cr);
	}
}