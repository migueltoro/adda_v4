package us.lsi.hypergraphsD;


import java.util.Comparator;
import java.util.List;

public interface HyperVertexD<V extends HyperVertexD<V,E,A,S>, E extends HyperEdgeD<V,E,A,S>,A,S> {
	
	List<A> actions();
	Boolean isBaseCase();
	Double baseCaseWeight();
	S baseCaseSolution();
	V me();
	List<V> neighbors(A a);
	E edge(A a);
	
	public static <V,E> Data<V,E> data() {
		return Data.<V,E>of();
	}	
	public default Boolean isSolved() {
		return Data.<V,E>of().solved(this.me());
	}
	public default Sp<E> spData() {
		return Data.<V,E>of().sp(this.me());
	}
	public default void setSpData(Sp<E> sp) {
		Data.<V,E>of().setSp(this.me(),sp);
	}
	public default void setAllSpData(Sp<E> sp) {
		Data.<V,E>of().setAllSp(this.me(),sp);
	}
	public default Comparator<Sp<E>> orderData() {
		return Data.<V,E>of().order();
	}
	
	public default Double weight() {
		if(this.sp() != null)
			return this.sp().weight();
		else 
			return null;
	}
	
	public default Sp<E> sp() {
		Sp<E> r = null;
		if (this.isSolved())
			r = this.spData();
		else {
			if (this.isBaseCase()) {
				Double br = baseCaseWeight();
				if (br != null) r = Sp.of(br,null);
			} else {
				r = this.edgesOf().stream()
						.map(e->e.sp())
						.peek(e->this.setAllSpData(e))
						.filter(s -> s != null)	
						.min(this.orderData())
						.orElse(null);
			}
			this.setSpData(r);
		}
		return r;
	}

	default Boolean hasSolution() {
		return this.sp() != null;
	}
	
	default public S solution() {
		if (this.isBaseCase())
			return this.baseCaseSolution();
		else 
			return  this.sp().edge().solution();
	}

	/**
	 * Este m&eacute;todo podr&iacute;a ser sobrescrito en la clase que refine al
	 * tipo
	 * 
	 * @return El conjunto de las aristas hacia los vecinos
	 */
	default public List<E> edgesOf() {
		return this.actions().stream().map(a -> this.edge(a)).toList();
	}
	
	default public List<V> targets(A a) {
		return this.edge(a).targets();
	}

	default GraphTreeD<V, E, A> graphTree() {
		return GraphTreeD.optimalTree(this.me());
	}
}
