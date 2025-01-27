package us.lsi.graphs.manual.recorridos;

import java.util.Collections;

import us.lsi.graphs.manual.VirtualGraph;

public class RecorridoTopologico<V,E> extends RecorridoEnProfundidadPostorden<V,E> {
	
	public static <V, E> RecorridoTopologico<V, E> of(VirtualGraph<V, E> grafo) {
		return new RecorridoTopologico<>(grafo);
	}

	protected RecorridoTopologico(VirtualGraph<V, E> grafo) {
		super(grafo);
	}

	public void traverse(V source) {
		super.traverse(source);
		Collections.reverse(this.path);
	}

}
