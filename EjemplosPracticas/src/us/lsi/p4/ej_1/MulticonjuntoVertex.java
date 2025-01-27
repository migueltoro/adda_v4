package us.lsi.p4.ej_1;

import us.lsi.graphs.virtual.VirtualVertex;

public interface MulticonjuntoVertex extends
	VirtualVertex<MulticonjuntoVertex, MulticonjuntoEdge, Integer> {

	Integer indice();
	Integer sumaRestante();
	Double accionReal();

	public static MulticonjuntoVertex start() {
		return  new MulticonjuntoVertexI(0, DatosMulticonjunto.SUM);
	}
}