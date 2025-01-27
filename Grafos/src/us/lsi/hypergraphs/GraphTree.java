package us.lsi.hypergraphs;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import us.lsi.graphs.alg.PD.Sp;

public sealed interface GraphTree<V extends VirtualHyperVertex<V, E, A, S>, E extends SimpleHyperEdge<V, E, A>, A, S> 
	permits Gtb, Gtr {

	public static <V extends VirtualHyperVertex<V, E, A, S>, E extends SimpleHyperEdge<V, E, A>, A, S> 
		GraphTree<V, E, A, S> tb(V v, Double weight) {
		return new Gtb<V, E, A, S>(v, weight);
	}

	public static <V extends VirtualHyperVertex<V, E, A, S>, E extends SimpleHyperEdge<V, E, A>, A, S> 
		GraphTree<V, E, A, S> tr(V v, A a, Double weight, List<GraphTree<V, E, A,S>> children) {
		return new Gtr<V, E, A, S>(v, a, weight, children);
	}

	public static <V extends VirtualHyperVertex<V, E, A, S>, E extends SimpleHyperEdge<V, E, A>, A, S> 
		GraphTree<V, E, A, S> optimalTree(
			V v, Map<V, Sp<E>> tree) {
		if (v.isBaseCase()) {
			return new Gtb<V, E, A, S>(v, tree.get(v).weight());
		} else {
			A a = tree.get(v).edge().action();
			List<GraphTree<V, E, A, S>> children = v.neighbors(a).stream().map(x -> GraphTree.optimalTree(x, tree))
					.toList();
			return new Gtr<V, E, A, S>(v, a, tree.get(v).weight(), children);
		}
	}

	public V vertex();

	public Double weight();

	public Boolean isLeaf();

	public List<GraphTree<V, E, A, S>> children();

	public Set<E> allEdges();

	public default Set<V> vertices() {
		Set<V> s = new HashSet<>();
		s.add(this.vertex());
		this.children().stream().forEach(t->s.addAll(t.vertices()));
		return s;
	}

	public default S solution() {
		return switch(this) {
		case Gtb<V,E,A,S> t -> t.vertex().baseCaseSolution();
		case Gtr<V,E,A,S> t -> t.vertex().solution(t.children().stream().<S>map(x->x.solution()).toList());
		};
	}

}
