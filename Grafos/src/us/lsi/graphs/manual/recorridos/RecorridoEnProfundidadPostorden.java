package us.lsi.graphs.manual.recorridos;

import java.util.Optional;

import us.lsi.graphs.manual.VirtualGraph;
import us.lsi.graphs.manual.Pila;

public class RecorridoEnProfundidadPostorden<V,E> extends Recorrido<V,E> {
	
	public static <V, E> RecorridoEnProfundidadPostorden<V, E> of(VirtualGraph<V, E> grafo) {
		return new RecorridoEnProfundidadPostorden<>(grafo);
	}

	protected RecorridoEnProfundidadPostorden(VirtualGraph<V, E> grafo) {
		super(grafo);
	}
	
	public void traverse(V source) {
        V v = source;
        Pila<V> q = Pila.of();
        q.add(v);
        Pila<V> q2 = Pila.of();
        this.tree.put(v, Data.of(Optional.empty(),0.));
        while (!q.isEmpty()) {
            v = q.remove();
            q2.add(v);
            for (V neighbor : this.graph.neighbors(v)) {
                if (!this.tree.containsKey(neighbor)) {
                    q.add(neighbor);                   
                    this.tree.put(neighbor, Data.of(Optional.of(v), this.tree.get(v).weight() + 1));
                }
            }
        }
        while (!q2.isEmpty()) {
            v = q2.remove();
            this.path.add(v);
        }
	}

}
