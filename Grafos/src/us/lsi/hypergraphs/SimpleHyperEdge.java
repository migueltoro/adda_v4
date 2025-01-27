package us.lsi.hypergraphs;

import java.util.List;

public interface SimpleHyperEdge<V,E,A> {
	V source();
	List<V> targets();
	A action();
	Double weight(List<Double> solutions);
}
