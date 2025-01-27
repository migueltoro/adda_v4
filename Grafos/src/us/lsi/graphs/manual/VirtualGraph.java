package us.lsi.graphs.manual;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public interface VirtualGraph<V, E> {	
    enum GraphType {
		UNDIRECTED, DIRECTED
	}
	enum TraverseType {
		FORWARD, BACK
	}
	Set<V> neighbors(V vertex);
    Double edgeWeight(V sourceVertex, V targetVertex);
    E edge(V sourceVertex, V targetVertex);
    Set<V> vertexSet();
    
    public default List<V> closestVertices(V vertex) {
		return this.neighbors(vertex).stream()
				.sorted(Comparator.comparing(n -> this.edgeWeight(vertex, n)))
				.toList();
	}
    
    public default V otherVertex(V vertex, E edge) {
		return this.neighbors(vertex).stream().filter(x->this.edge(vertex, x).equals(edge)).findFirst().get();
	}
    
    public default Set<E> edgesOf(V vertex) {
		return this.neighbors(vertex).stream().map(x->this.edge(vertex, x)).collect(Collectors.toSet());
	}
}
