package us.lsi.alg.tsp;

import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

import org.jgrapht.Graph;
import org.jgrapht.GraphPath;

import us.lsi.graphs.SimpleEdge;
import us.lsi.graphs.alg.SA;
import us.lsi.graphs.virtual.EGraph;
import us.lsi.path.GraphPath2;
import us.lsi.streams.Stream2;

public class TestSimulatedAnnealingInteger {
	
	
	
	public static void main(String[] args) {
		Locale.setDefault(Locale.of("en", "US"));
		
		SA.numPorIntento = 100;
		SA.numMismaTemperatura = 1;
		SA.temperaturaInicial = 100000;
		SA.alfa = 0.95;
		
		Graph<Integer,SimpleEdge<Integer>> graph = AuxiliaryTsp.generate(100);
		
		TravelVertexInteger.graph = graph;
		
		System.out.println(graph);
//		System.out.println(graph.edgeSet());
		List<Integer> camino = graph.vertexSet().stream().collect(Collectors.toList());
		camino.add(0);
		Collections.shuffle(camino.subList(1,camino.size()-2));
//		System.out.println(graph.getEdgeWeight(graph.getEdge(0,1)));
		GraphPath<Integer,SimpleEdge<Integer>> path = GraphPath2.ofVertices(graph,camino);
		System.out.println(path.getWeight());
		TravelVertexInteger e1 = TravelVertexInteger.of(camino);
		System.out.println(e1);
		
		EGraph<TravelVertexInteger,TravelEdgeInteger> graph2 = 
				EGraph.virtual(e1).vertexWeight(v->v.weight()).build();
		SA<TravelVertexInteger, TravelEdgeInteger> m = 
				SA.simulatedAnnealing(graph2,e1,e->e.weight());
		
//		Optional<TravelVertexInteger> vr = m.search();
		Optional<TravelVertexInteger> vr = 
				Stream2.findLast(m.stream().peek(v->System.out.println(String.format("%d === %.2f",m.i,v.weight()))));
//		System.out.println(GraphPaths.of(graph,v.camino()).getWeight());
		System.out.println(vr.get());
		System.out.println(m.bestWeight);
		System.out.println(m.bestVertex);
	}

}
