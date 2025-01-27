package us.lsi.hypergraphs;

import java.util.List;
import java.util.Set;

public record Gtb<V extends VirtualHyperVertex<V, E, A, S>, E extends SimpleHyperEdge<V, E, A>, A, S>(V vertex,
		Double weight) implements GraphTree<V, E, A, S> {
	public Boolean isLeaf() {
		return true;
	}

	public List<GraphTree<V, E, A, S>> children() {
		return List.of();
	}

	@Override
	public String toString() {
		return String.format("(%s)", this.vertex());
	}

	public Set<E> allEdges() {
		return Set.of();
	}
}
