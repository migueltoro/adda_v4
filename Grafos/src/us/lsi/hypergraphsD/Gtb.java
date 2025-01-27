package us.lsi.hypergraphsD;

import java.util.List;
import java.util.Set;

public record Gtb<V extends HyperVertexD<V, E, A, ?>, E extends HyperEdgeD<V, E, A, ?>, A>(V vertex)
		implements GraphTreeD<V, E, A> {
	public Boolean isLeaf() {
		return true;
	}

	public List<GraphTreeD<V, E, A>> children() {
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