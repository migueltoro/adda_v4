package us.lsi.alg.tsp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import org.jgrapht.Graph;

import us.lsi.grafos.datos.Carretera;
import us.lsi.grafos.datos.Ciudad;
import us.lsi.graphs.alg.LocalSearch;
import us.lsi.graphs.virtual.EGraph;
import us.lsi.streams.Stream2;

public class TestLocalSearch {
	
	public static void main(String[] args) {
		Locale.setDefault(Locale.of("en", "US"));
		
		Graph<Ciudad,Carretera> graph1 = AuxiliaryTsp.leeGraph("ficheros/andalucia/andalucia.txt");
		Graph<Ciudad,Carretera> graph2 = AuxiliaryTsp.completeGraph(graph1);
		List<Ciudad> camino = new ArrayList<>(graph2.vertexSet().stream().toList());
		
		TravelVertex.graph = graph2;
		
		camino.add(camino.get(0));
		
		Collections.shuffle(camino.subList(1,camino.size()-2));
		
		TravelVertex e1 = TravelVertex.of(camino);
		System.out.println(e1);
		
		EGraph<TravelVertex,TravelEdge> graph = EGraph.virtual(e1).vertexWeight(v->v.weight()).build();
		
		LocalSearch<TravelVertex,TravelEdge> ml = LocalSearch.of(graph,0.,3);
		Optional<TravelVertex> vr = Stream2.findLast(ml.stream().peek(v->System.out.println(v.weight())));
		System.out.println(vr.get());
	}

}
