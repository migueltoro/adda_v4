package us.lsi.alg.tsp;

import us.lsi.graphs.SimpleEdge;
import us.lsi.graphs.alg.LocalSearch;
import us.lsi.graphs.virtual.EGraph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.function.Supplier;

import org.jgrapht.Graph;

public class TestLocalSearchInteger {

	public static void main(String[] args) {
		Locale.setDefault(Locale.of("en", "US"));
		
		Graph<Integer,SimpleEdge<Integer>> graph = AuxiliaryTsp.generate(100);
		TravelVertexInteger.graph = graph;
		TravelVertexInteger.n = 10;
		List<Integer> camino = new ArrayList<>(graph.vertexSet().stream().toList());
		
		camino.add(camino.get(0));
		
		Collections.shuffle(camino.subList(1,camino.size()-2));
		
		TravelVertexInteger e1 = TravelVertexInteger.of(camino);
		
		EGraph<TravelVertexInteger,TravelEdgeInteger> g = 
				EGraph.virtual(e1).vertexWeight(v->v.weight()).build();
		
		Supplier<TravelVertexInteger> sp = () -> {
			Collections.shuffle(camino.subList(1, camino.size() - 2));
			return TravelVertexInteger.of(camino);
		};

		TravelVertexInteger r = LocalSearch.repeat(g, sp, 0.1, 3, 5);

		System.out.println(r.weight()+" = "+r);
	}

}
