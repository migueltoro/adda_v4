package us.lsi.p4.ej_2;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import us.lsi.graphs.alg.AStar;
import us.lsi.graphs.virtual.EGraph;

public class TestAStar {
	
	public static void main(String[] args) {

	// Set up
	Locale.setDefault(Locale.of("en", "US"));

	for (Integer id_fichero = 1; id_fichero < 3; id_fichero++) {

		DatosSubconjuntos.iniDatos("ficheros/p4/subconjuntos" + id_fichero + ".txt");
		System.out.println("\n\n>\tResultados para el test " + id_fichero + "\n");
//		DatosSubconjuntos.toConsole();

		// V�rtices clave

		SubconjuntosVertex start = SubconjuntosVertexI.initial();

		// Grafo
		
		EGraph<SubconjuntosVertex, SubconjuntosEdge> graph = 
				EGraph.virtual(start)
				.edgeWeight(x-> x.weight())
				.heuristic(SubconjuntosHeuristic::heuristic)
				.build();

		System.out.println("\n\n#### Ej3 Algoritmo Astar ####");
		
		AStar<SubconjuntosVertex, SubconjuntosEdge,?> aStar = AStar.ofGreedy(graph);
		
		List<Integer> gp_as = aStar.search().get().getEdgeList().stream().map(x -> x.action())
				.collect(Collectors.toList()); // getEdgeList();
		SolucionSubconjuntos s_as = SolucionSubconjuntos.of(gp_as);
		System.out.println(s_as);
	}

	}
}
