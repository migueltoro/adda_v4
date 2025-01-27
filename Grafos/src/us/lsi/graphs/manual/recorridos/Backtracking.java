package us.lsi.graphs.manual.recorridos;


import java.util.Comparator;
import java.util.function.BiFunction;
import java.util.function.Predicate;

import us.lsi.graphs.manual.GraphPath;
import us.lsi.graphs.manual.VirtualGraph;

public class Backtracking {
	
	public static <V, E> GraphPath<V, E> backtracking(V start, Predicate<V> end, VirtualGraph<V, E> graph,
			BiFunction<GlobalValues<V, E>,V, Double> heuristica) {
		return backtracking(start, end, graph, heuristica, (x,y)->x.compareTo(y));
	}
	
	public static <V, E> GraphPath<V, E> backtracking(V start, Predicate<V> end, VirtualGraph<V, E> graph,
			BiFunction<GlobalValues<V, E>,V, Double> heuristica, Comparator<Double> cmp) {
		State<V, E> state = State.of(start, 0.);
		GlobalValues<V, E> global = GlobalValues.of(cmp,end,graph,heuristica);
		backtracking(state, global);
		return global.bestPath;
	}

	private static <V, E> void backtracking(State<V, E> state, GlobalValues<V, E> global) {
		V actualVertex = state.vertex();		
		if (global.end.test(actualVertex)) {
			global.update(state.path(),state.accValue());			
		} 
		else for (V v : global.graph.neighbors(state.vertex())) {
			E edge = global.graph.edge(actualVertex, v);
			Double value = global.graph.edgeWeight(actualVertex, v);
			if (state.contains(v) || global.filter(v, state.accValue(), value, global.bestValue)) continue;
			state.add(v, edge, value);
			backtracking(state, global);
			state.remove();
		}
	}

}
