package us.lsi.graphs.manual.recorridos;

import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import us.lsi.graphs.manual.VirtualGraph;
import us.lsi.graphs.manual.heaps.Heap;

public class AStar<V,E> extends Recorrido<V,E> {
	
	private Function<V,Double> heuristic;
	private Predicate<V> predicate;
	
	public static <V, E> AStar<V, E> of(VirtualGraph<V, E> grafo, 
			Function<V, Double> heuristic,
			Predicate<V> predicate) {
		return new AStar<>(grafo, heuristic, predicate);
	}
	
	protected AStar(VirtualGraph<V, E> grafo, 
			Function<V,Double> heuristic, 
			Predicate<V> predicate) {
		super(grafo);
		this.heuristic = heuristic;
		this.predicate = predicate;
	}
	
	private Double estimatedWeightToEnd(V v, Double w) {
		return w + this.heuristic.apply(v);
	}
	
	public void traverse(V source) {
        V v = source;
        Heap<V,Double> q = Heap.of();
        q.add(v,0.);
        this.tree.put(v, Data.of(Optional.empty(),0.));
        while (!q.isEmpty()) {
            v = q.remove();           
            this.path.add(v);
            if (this.predicate.test(v)) break;
            Double w = this.tree.get(v).weight();
            for (V neighbor : this.graph.neighbors(v)) {
                Double nbw = w + this.graph.edgeWeight(v, neighbor); 
                Double ewe = this.estimatedWeightToEnd(neighbor,nbw);
                if (!this.tree.containsKey(neighbor)) {
                    this.tree.put(neighbor, Data.of(Optional.of(v), nbw));
                    q.add(neighbor,ewe);                    
                } else {
                    if (nbw < this.tree.get(neighbor).weight()) {
                        this.tree.put(neighbor, Data.of(Optional.of(v), nbw));
                        q.decrease(neighbor, ewe);
                    }
                }
            }
        }
    }

}
