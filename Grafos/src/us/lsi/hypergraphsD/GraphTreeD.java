package us.lsi.hypergraphsD;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public sealed interface GraphTreeD<V extends HyperVertexD<V, E, A, ?>, E extends HyperEdgeD<V, E, A,?>, A> 
		permits Gtb,Gtr{

	public static <V extends HyperVertexD<V, E, A, ?>, E extends HyperEdgeD<V, E, A, ?>, A> 
		GraphTreeD<V, E, A> tb(V v) {
		return new Gtb<V, E, A>(v);
	}
	
	public static <V extends HyperVertexD<V, E, A, ?>, E extends HyperEdgeD<V, E, A, ?>, A> 
		GraphTreeD<V, E, A> tr(V v,A a,List<GraphTreeD<V,E,A>> children) {
		return new Gtr<V, E, A>(v,a,children);
	}
	
	public static <V extends HyperVertexD<V, E, A, ?>, E extends HyperEdgeD<V, E, A, ?>, A, S> 
		GraphTreeD<V, E, A> optimalTree(V v) {
		if (v.isBaseCase()) {
			return new Gtb<V, E, A>(v);
		} else {
			A a = v.sp().edge().action();
			List<GraphTreeD<V,E,A>> children = v.neighbors(a).stream()
					.map(g->GraphTreeD.optimalTree(g))
					.toList();					
			return new Gtr<V, E, A>(v,a,children);
		}
	}
	
	public V vertex();

	public Boolean isLeaf();

	public default Double weight() {
		return this.vertex().weight();
	}

	public List<GraphTreeD<V, E, A>> children(); 
	
	public default Set<V> allVertices() {
		Set<V> s = new HashSet<>();
		s.add(this.vertex());
		this.children().stream().forEach(t->s.addAll(t.allVertices()));
		return s;
	}

	public Set<E> allEdges();
}
