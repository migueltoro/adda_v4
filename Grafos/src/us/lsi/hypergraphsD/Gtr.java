package us.lsi.hypergraphsD;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public record Gtr<V extends HyperVertexD<V, E, A, ?>, E extends HyperEdgeD<V, E, A, ?>, A>(V vertex, A action,
		List<GraphTreeD<V, E, A>> children) implements GraphTreeD<V, E, A> {
	public Boolean isLeaf() {
		return false;
	}

	public E edge() {
		return this.vertex().edge(this.action());
	}

	@Override
	public String toString() {
		String lb = String.format("(%s,%s)", this.vertex(), this.action());
		return lb + this.children().stream().map(g -> g.toString()).collect(Collectors.joining(",", "(", ")"));
	}

	public Set<E> allEdges() {
		Set<E> s = new HashSet<>();
		s.add(this.edge());
		this.children().stream().forEach(t -> s.addAll(t.allEdges()));
		return s;
	}

}
