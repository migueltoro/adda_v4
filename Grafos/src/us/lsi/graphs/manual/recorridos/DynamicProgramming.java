package us.lsi.graphs.manual.recorridos;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Predicate;
import us.lsi.graphs.manual.GraphPath;
import us.lsi.graphs.manual.VirtualGraph;

public class DynamicProgramming<V, E> {
	

	public static <V,E> GraphPath<V,E> path(Map<V,Sp<E>> memory,V start,VirtualGraph<V, E> graph) {
		List<V> vertices = new ArrayList<>();
		List<E> edges = new ArrayList<>();
		V actual = start;
		Double weight = memory.get(actual).weight();
		while (memory.containsKey(actual)) {
			Sp<E> sp = memory.get(actual);
			vertices.add(actual);
			if (sp.edge() == null) break;
			edges.add(sp.edge());
			actual = graph.otherVertex(actual, sp.edge());
		}		
		return GraphPath.of(vertices,edges,weight);
	}
	
	public static record Sp<E>(E edge, Double weight) implements Comparable<Sp<E>>{
		
		public static Comparator<Double> comp = null;
		
		public static <E> Sp<E> of(E edge, Double weight) {
			return new Sp<E>(edge, weight);
		}

		@Override
		public int compareTo(Sp<E> other) {
			return comp.compare(this.weight(), other.weight());
		}	
	}
	
	public static <V, E> GraphPath<V, E> dynamicProgrammingR(V start, Predicate<V> end, VirtualGraph<V, E> graph,
			BiFunction<GlobalValues<V, E>,V, Double> heuristica) {
		return dynamicProgrammingR(start, end, graph, heuristica, (x,y)->x.compareTo(y));
	}
	
	public static <V, E> GraphPath<V, E> dynamicProgrammingR(V start, Predicate<V> end, 
			VirtualGraph<V, E> graph,
			BiFunction<GlobalValues<V, E>,V, Double> heuristica, Comparator<Double> cmp) {
		Sp.comp = cmp;
		State<V, E> state = State.of(start, 0.);
		GlobalValues<V, E> global = GlobalValues.of(cmp,end,graph,heuristica);
		Map<V,Sp<E>> memory = new HashMap<>();;
		dynamicProgrammingR(state,global,memory);
		return path(memory,start,graph);
	}

	private static <V, E> Sp<E> dynamicProgrammingR(State<V, E> state, GlobalValues<V, E> global,Map<V, Sp<E>> memory) {
		V actualVertex = state.vertex();
		Sp<E> r = null;
		if (global.end.test(actualVertex)) {
			global.update(state.path(), state.accValue());
			r = Sp.of(null, 0.);
			memory.put(actualVertex, r);
		} else if (memory.containsKey(actualVertex)) {
			r = memory.get(actualVertex);
		} else {
			List<Sp<E>> spChildren = new ArrayList<>();
			for (V v : global.graph.neighbors(state.vertex())) {
				E edge = global.graph.edge(actualVertex, v);
				Double edgeWeight = global.graph.edgeWeight(actualVertex, v);
				if (state.contains(v) || global.filter(v, state.accValue(), edgeWeight, global.bestValue)) continue;
				state.add(v, edge, edgeWeight);
				Sp<E> spc = dynamicProgrammingR(state, global, memory);
				state.remove();
				if (spc != null) {
					Sp<E> sp = Sp.of(edge, spc.weight() + edgeWeight);
					spChildren.add(sp);
				}
			}
			if (!spChildren.isEmpty()) {
				r = spChildren.stream().min(Comparator.naturalOrder()).get();
				memory.put(actualVertex, r);
			}
		}
		return r;
	}

}
