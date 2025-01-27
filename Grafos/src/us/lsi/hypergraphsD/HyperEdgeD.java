package us.lsi.hypergraphsD;

import java.util.List;

public interface HyperEdgeD<V extends HyperVertexD<V, E, A, S>, 
		E extends HyperEdgeD<V,E,A,S>, A, S> {
	V source();
	A action();
	Double weight(List<Double> weights);
	S solution(List<S> solutions);
	E me();
	
	default Double weight() {
		if(this.sp() == null) return null;
		else return this.sp().weight();
	}
	default List<V> targets() {
		return this.source().neighbors(this.action());
	}
	default Boolean hasSolution() {
		return this.targets().stream().allMatch(v->v.hasSolution());
	}
	default Sp<E> sp() {
		Sp<E> r = null;
		if(this.hasSolution()) {
			Double weight = this.weight(this.targets().stream().map(v -> v.weight()).toList());
			r = Sp.of(weight, me());
		}
		return r;
	}
	default S solution() {
		return solution(this.targets().stream().map(v->v.solution()).toList());
	}
}
