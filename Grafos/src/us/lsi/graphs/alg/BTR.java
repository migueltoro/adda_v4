package us.lsi.graphs.alg;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;


import org.jgrapht.GraphPath;

import us.lsi.common.List2;
import us.lsi.graphs.virtual.EGraph;
import us.lsi.math.Math2;

public class BTR<V,E,S extends Comparable<S>> extends BT<V,E,S> {
	
	public static <V, E, S extends Comparable<S>> BTR<V, E, S> of(
			EGraph<V, E> graph, 
			Function<GraphPath<V, E>, S> solution, 
			Function<V, Integer> size,
			Integer threshold) {
		return new BTR<V, E, S>(graph,solution, size,threshold);
	}
	

	BTR(EGraph<V, E> graph, 
			Function<GraphPath<V, E>, S> solution,
			Function<V,Integer> size,
			Integer threshold) {
		super(graph, solution, null,null, false);
		this.size = size;
		this.threshold = threshold;
	}
		
	protected Function<V,Integer> size;
	public Integer iterations;
	public Integer threshold;
	
	
	@Override
	public Optional<GraphPath<V, E>> search() {
		State<V,E> initialState = StatePath.of(graph,graph.goal(),graph.endVertex());
		this.iterations = 0;
		Math2.initRandom();
		while (!this.stop) {
			this.iterations++;
			search(initialState);
		}
		return this.optimalPath();
	}
	
	@Override
	public void search(State<V, E> state) {
		V actual = state.getActualVertex();
		if (graph.goal().test(actual)) 
			update(state);		
		else {
			List<E> edges = graph.edgesListOf(actual);
			if(size.apply(actual) > this.threshold) edges = List2.randomUnitary(edges);
			for (E edge : edges) {				
				state.forward(edge);
				search(state);
				if(this.stop) return;
				state.back(edge);
			}
		}
	}

	

}
