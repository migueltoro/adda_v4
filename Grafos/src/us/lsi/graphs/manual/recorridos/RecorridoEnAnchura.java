package us.lsi.graphs.manual.recorridos;

import java.util.Optional;

import us.lsi.graphs.manual.Cola;
import us.lsi.graphs.manual.VirtualGraph;

public class RecorridoEnAnchura<V,E> extends Recorrido<V,E> {
	
	public static <V, E> RecorridoEnAnchura<V, E> of(VirtualGraph<V, E> grafo) {
		return new RecorridoEnAnchura<>(grafo);
	}
	
	protected RecorridoEnAnchura(VirtualGraph<V, E> grafo) {
		super(grafo);
	}

	public void traverse(V source) {
        V v = source;
        Cola<V> q = Cola.of();
        q.add(v);
        this.tree.put(v, Data.of(Optional.empty(),0.));
        while (!q.isEmpty()) {
            v = q.remove();
            this.path.add(v);
            for (V neighbor : this.graph.neighbors(v)) {
                if (!this.tree.containsKey(neighbor)) {
                    q.add(neighbor);                   
                    this.tree.put(neighbor, Data.of(Optional.of(v), this.tree.get(v).weight() + 1));
                }
            }
        }
    }

}
