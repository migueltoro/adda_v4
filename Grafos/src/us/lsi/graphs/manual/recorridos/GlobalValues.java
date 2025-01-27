package us.lsi.graphs.manual.recorridos;

import java.util.Comparator;
import java.util.function.BiFunction;
import java.util.function.Predicate;

import us.lsi.graphs.manual.GraphPath;
import us.lsi.graphs.manual.VirtualGraph;

public class GlobalValues<V,E> {
	
	public static <V, E> GlobalValues<V, E> of(Predicate<V> end, VirtualGraph<V, E> graph) {
		return new GlobalValues<V, E>((x,y)->x.compareTo(y),end,graph,null);
	}
	
	public static <V, E> GlobalValues<V, E> of(Predicate<V> end, VirtualGraph<V, E> graph,
			BiFunction<GlobalValues<V, E>,V, Double> heuristica) {
		return new GlobalValues<V, E>((x,y)->x.compareTo(y),end,graph,heuristica);
	}
	
	public static <V, E> GlobalValues<V, E> of(Comparator<Double> cmp, Predicate<V> end, VirtualGraph<V, E> graph,
			BiFunction<GlobalValues<V, E>,V, Double> heuristica) {
		return new GlobalValues<V, E>(cmp,end,graph,heuristica);
	}
	
	public Double bestValue = Double.MAX_VALUE;
	public GraphPath<V,E> bestPath = null;
	public Comparator<Double> cmp = (x,y)->x.compareTo(y);
	public Predicate<V> end;
	public VirtualGraph<V, E> graph;
	public BiFunction<GlobalValues<V, E>,V, Double> heuristica;
	public V endVertex = null;
	
	
	private GlobalValues(Comparator<Double> cmp, Predicate<V> end, VirtualGraph<V, E> graph,
			BiFunction<GlobalValues<V, E>,V, Double> heuristica) {
		this.bestValue = Double.MAX_VALUE;
		this.bestPath = null;
		this.cmp = cmp;
		this.end = end;
		this.graph = graph;
		this.heuristica = heuristica;
	}
	
	public void update(GraphPath<V, E> path, Double acumValue) {
		if (path==null || cmp.compare(acumValue, bestValue) < 0) {
			bestValue = path.weight();
			bestPath = path.copy();
		}
	}
	
	public Boolean filter(V v, Double acumValue, Double value, Double bestValue) {
		Boolean r = false;
		Double weight = acumValue+value+this.heuristica.apply(this,v);
		if(bestValue != null && cmp.compare(weight, bestValue) >= 0 ) r = true;
		return r;
	}	
}

