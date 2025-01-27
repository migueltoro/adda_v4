package us.lsi.hypergraphs;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public record Gtr<V extends VirtualHyperVertex<V, E, A, S>, E extends SimpleHyperEdge<V, E, A>, A, S>(V vertex,
		A action, Double weight, List<GraphTree<V, E, A, S>> children) implements GraphTree<V, E, A, S> {

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
