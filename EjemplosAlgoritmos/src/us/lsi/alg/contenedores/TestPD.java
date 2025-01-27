package us.lsi.alg.contenedores;

import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

import org.jgrapht.GraphPath;

import us.lsi.graphs.alg.PDR;
import us.lsi.graphs.virtual.EGraph;
import us.lsi.graphs.virtual.EGraph.Type;
import us.lsi.path.EGraphPath.PathType;

public class TestPD {

	public static void main(String[] args) {

		// Set up
		Locale.setDefault(Locale.of("en", "US"));

		for (Integer id_fichero = 1; id_fichero < 2; id_fichero++) {

			DatosContenedores.iniDatos("ficheros/contenedores/contenedores"+ id_fichero +".txt");
			System.out.println("\n\n>\tResultados para el test " + id_fichero + "\n");
//			DatosSubconjuntos.toConsole();
			// V�rtices clave

			VertexContenedoresI start = VertexContenedoresI.initial();

			// Grafo
			
			EGraph<VertexContenedores, EdgeContenedores> graph = 
					EGraph.virtual(start)
					.pathType(PathType.Last)
					.type(Type.Max)
					.vertexWeight(x -> (double)x.contenedoresCompletos().size())
					.heuristic(ContenedoresHeuristic::heuristic)
					.build();
			
			
			System.out.println("\n\n#### PI-7 Ej3 Algoritmo PD ####");
			
			GraphPath<VertexContenedores, EdgeContenedores> max = ContenedoresHeuristic.caminoVoraz(graph,50);
			GraphPath<VertexContenedores, EdgeContenedores> max2 = ContenedoresHeuristic.caminoVoraz(graph,50);
			
			System.out.println("Solucion Voraz ="+max.getWeight());	
			System.out.println("Solucion Voraz 2 ="+max2.getWeight());
			System.out.println("Heuristica ="+ContenedoresHeuristic.heuristic(start,x->x.goal(), null));


			// Algoritmo PD
			PDR<VertexContenedores, EdgeContenedores, ?> pdr = 
					PDR.ofGreedy(graph);


			Optional<GraphPath<VertexContenedores, EdgeContenedores>> gp = pdr.search();
			SolucionContenedores s_pdr;
			if (gp.isPresent()) {
				List<Integer> gp_pdr = gp.get().getEdgeList().stream().map(x -> x.action())
						.collect(Collectors.toList());
				s_pdr = SolucionContenedores.of(gp_pdr);
				System.out.println(s_pdr);
				
			} else {
				System.out.println("Solucion no encontrada!!!");
			}

		}
	}

}

