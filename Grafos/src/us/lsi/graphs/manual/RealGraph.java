package us.lsi.graphs.manual;

import java.util.Set;

public interface RealGraph<V, E> extends VirtualGraph<V, E> {
	
	Set<V> predecessors(V vertex);
	
	Set<V> successors(V vertex);
	
	Boolean addVertex(V vertex);

	Boolean addEdge(V source, V target, E edge);

	void removeVertex(V vertex);

	void removeEdge(V source, V target);

}
