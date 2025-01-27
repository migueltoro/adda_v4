package us.lsi.alg.multiconjuntos;

import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

import org.jgrapht.GraphPath;

import us.lsi.colors.GraphColors;
import us.lsi.colors.GraphColors.Color;
import us.lsi.graphs.alg.PDR;
import us.lsi.graphs.alg.GreedyOnGraph;
import us.lsi.graphs.virtual.EGraph;
import us.lsi.graphs.virtual.EGraph.Type;
import us.lsi.path.EGraphPath.PathType;

public class TestPD {

	public static void main(String[] args) {

		// Set up
		Locale.setDefault(Locale.of("en", "US"));
		for (Integer id_fichero = 0; id_fichero < 7; id_fichero++) {

			DatosMulticonjunto.iniDatos("ficheros/multiconjuntos.txt", id_fichero);
			System.out.println("\n\n>\tResultados para el test " + id_fichero + "\n");

			// V�rtices clave

			MulticonjuntoVertex start = MulticonjuntoVertexI.start();

			// Grafo


			System.out.println("\n\n#### Algoritmo PD ####");

			// Algoritmo PD
			
			EGraph<MulticonjuntoVertex, MulticonjuntoEdge> graph =
					EGraph.virtual(start)
					.pathType(PathType.Sum)
					.type(Type.Min)
					.edgeWeight(x -> x.weight())
					.heuristic(MulticonjuntoHeuristic::heuristic)
					.build();
			
			
			GreedyOnGraph<MulticonjuntoVertex, MulticonjuntoEdge> rr = 
					GreedyOnGraph.of(graph);
			
			GraphPath<MulticonjuntoVertex, MulticonjuntoEdge> r = rr.path();
			
			System.out.println("Voraz = "+r.getWeight()+"  == "+SolucionMulticonjunto.of(r));
			
			PDR<MulticonjuntoVertex, MulticonjuntoEdge, ?> pdr = PDR
					.of(graph, null,null, null, true);

			if (rr.isSolution(r)) {
				pdr = PDR.of(graph, null,r.getWeight(), r, true);
			}
			
			
			Optional<GraphPath<MulticonjuntoVertex, MulticonjuntoEdge>> gp = pdr.search();
			
			SolucionMulticonjunto s_pdr = null;
			
			if (gp.isPresent()) {
				System.out.println(gp.get().getEdgeList().stream().map(x -> x.action()).collect(Collectors.toList()));
				List<Integer> gp_pdr = gp.get().getEdgeList().stream().map(x -> x.action()).collect(Collectors.toList()); // getEdgeList();

				s_pdr = SolucionMulticonjunto.of(gp_pdr);

			} 
			
			
			System.out.println(s_pdr);
			
			
			GraphColors.toDot(pdr.outGraph, "ficheros/multiconjuntosPDRGraph.gv", 
					v -> String.format("(%d,%d)", v.indice(), v.sumaRestante()),
					e -> e.action().toString(), 
					v -> GraphColors.colorIf(Color.red, v.goal()),
					e -> GraphColors.colorIf(Color.red, gp.isPresent()?gp.get().getEdgeList().contains(e):false));
		}
	}
}

