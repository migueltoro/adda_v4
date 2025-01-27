package us.lsi.graphs.manual.recorridos;

import java.util.function.Predicate;

import us.lsi.common.TriFunction;
import us.lsi.graphs.manual.GraphPath;
import us.lsi.graphs.manual.VirtualGraph;

public class Greedy {
	
	public static <V, E> GraphPath<V, E> greedy(V start, Predicate<V> end, 
			VirtualGraph<V, E> graph, TriFunction<State<V, E>,GlobalValues<V,E>,V, V> greedyVertex) {
		State<V, E> state = State.of(start, 0.);
		GlobalValues<V, E> global = GlobalValues.of(end, graph);
		greedy(state, global, greedyVertex);
		return global.bestPath;
	}

	private static <V, E> void greedy(State<V, E> state, GlobalValues<V, E> global,
			TriFunction<State<V, E>,GlobalValues<V,E>,V, V> greedyVertex) {
		V actualVertex = state.vertex();		
		while(!global.end.test(actualVertex) || global.graph.neighbors(state.vertex()).isEmpty()) {
			V v = greedyVertex.apply(state,global,actualVertex); 
			E edge = global.graph.edge(actualVertex, v);
			Double value = global.graph.edgeWeight(actualVertex, v);
			state.add(v, edge, value);
			actualVertex  = v;
		} 
		if(global.end.test(actualVertex)) global.update(state.path(),state.accValue());
		else global.update(null, null);
	}
}
