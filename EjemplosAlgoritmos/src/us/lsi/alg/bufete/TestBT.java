package us.lsi.alg.bufete;

import java.util.Locale;
import java.util.Optional;

import org.jgrapht.GraphPath;

import us.lsi.graphs.alg.BT;
import us.lsi.graphs.alg.GreedyOnGraph;
import us.lsi.graphs.virtual.EGraph;
import us.lsi.graphs.virtual.EGraph.Type;
import us.lsi.path.EGraphPath.PathType;

public class TestBT {

	public static void main(String[] args) {
		Locale.setDefault(Locale.of("en", "US"));

		for (Integer id_fichero = 1; id_fichero < 4; id_fichero++) {

			DatosBufete.iniDatos("ficheros/bufete/bufete" + id_fichero + ".txt");
//			DatosBufete.toConsole();

			System.out.println("\n\n>\tResultados para el test " + id_fichero + "\n");

			BufeteVertex start = BufeteVertexI.initialVertex();

			/**
			 * IMPORTANTE. En este tipo se usa el tipo "Last".
			 * 
			 * 
			 */
			
			EGraph<BufeteVertex, BufeteEdge> graph = EGraph.virtual(start)
					.pathType(PathType.Last)
					.type(Type.Min)
					.vertexWeight(v -> (double) v.maxCarga())
					.heuristic(Heuristica::heuristic)
					.build();
			
			System.out.println("\n\n#### Algoritmo BT ####");
			
			GreedyOnGraph<BufeteVertex, BufeteEdge> rr = GreedyOnGraph.of(graph);
			
			GraphPath<BufeteVertex, BufeteEdge> path = rr.path();
			Double bv = path.getWeight();
			System.out.println(bv);
			
			// Algoritmo BT
			BT<BufeteVertex, BufeteEdge, SolucionBufete> bta = BT.of(graph, 
					SolucionBufete::of, 
					path.getWeight(),
					path,
					false);

			
			Optional<GraphPath<BufeteVertex, BufeteEdge>> gp = bta.search();

			SolucionBufete mejorSolucion = SolucionBufete.of(gp.get());
			System.out.println("maxCarga = "+mejorSolucion.maxCarga());
			System.out.println(mejorSolucion);
		}
	}
}

