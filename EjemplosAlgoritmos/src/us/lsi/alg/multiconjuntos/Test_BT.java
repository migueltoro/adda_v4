package us.lsi.alg.multiconjuntos;

import java.util.Locale;
import java.util.Optional;

import org.jgrapht.GraphPath;


import us.lsi.graphs.alg.BT;
import us.lsi.graphs.alg.GreedyOnGraph;
import us.lsi.graphs.virtual.EGraph;
import us.lsi.graphs.virtual.EGraph.Type;
import us.lsi.path.EGraphPath.PathType;

public class Test_BT {

	public static void main(String[] args) {

		// Set up
		Locale.setDefault(Locale.of("en", "US"));

		for (Integer id_fichero = 0; id_fichero < 7; id_fichero++) {

			DatosMulticonjunto.iniDatos("ficheros/multiconjuntos.txt", id_fichero);
			System.out.println("=============");
			System.out.println("\tResultados para el test " + id_fichero + "\n");
			
			DatosMulticonjunto.toConsole();

			// V�rtices clave

			MulticonjuntoVertex start = MulticonjuntoVertexI.start();

			// Grafo

			

			System.out.println("\n#### Algoritmo BT ####");
			
			// Algoritmo BT
			
			EGraph<MulticonjuntoVertex, MulticonjuntoEdge> graph =
					EGraph.virtual(start)
					.pathType(PathType.Sum)
					.type(Type.Min)
					.edgeWeight(x -> x.weight())
					.heuristic(MulticonjuntoHeuristic::heuristic)
					.build();
			
			
			GreedyOnGraph<MulticonjuntoVertex, MulticonjuntoEdge> rr = GreedyOnGraph.of(graph);
			
			GraphPath<MulticonjuntoVertex, MulticonjuntoEdge> r = rr.path();
			
			System.out.println("Voraz = "+r.getWeight()+"  == "+SolucionMulticonjunto.of(r));
			
			BT<MulticonjuntoVertex, MulticonjuntoEdge, SolucionMulticonjunto> bta = 
					BT.of(graph,SolucionMulticonjunto::of, null, null, true);

			if (rr.isSolution(r)) {
				bta = BT.of(graph,SolucionMulticonjunto::of, r.getWeight(), r, true);
			}
			Optional<GraphPath<MulticonjuntoVertex, MulticonjuntoEdge>> gp = bta.search();
			System.out.println(SolucionMulticonjunto.of(gp.get()));
			
//			System.out.println(bta.path.getEdgeList().stream().map(x -> x.action())
//					.collect(Collectors.toList()));
			
			
//			GraphColors.toDot(bta.graph(), "ficheros/multiconjuntosBTGraph.gv", 
//					v -> v.toGraph(),
//					e -> e.action().toString(), 
//					v -> GraphColors.colorIf(Color.red, MulticonjuntoVertex.goal().test(v)),
//					e -> GraphColors.colorIf(Color.red, bta.optimalPath.getEdgeList().contains(e)));

		}
	}

}

