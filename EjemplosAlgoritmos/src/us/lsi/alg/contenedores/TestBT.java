package us.lsi.alg.contenedores;

import java.util.Locale;
import java.util.Optional;

import org.jgrapht.GraphPath;

import us.lsi.graphs.alg.BT;
import us.lsi.graphs.virtual.EGraph;
import us.lsi.graphs.virtual.EGraph.Type;
import us.lsi.path.EGraphPath.PathType;

public class TestBT {

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

			System.out.println("\n\n#### PI-7 Ej3 Algoritmo BT ####");
			
			GraphPath<VertexContenedores, EdgeContenedores> max = ContenedoresHeuristic.caminoVoraz(graph,50);
			GraphPath<VertexContenedores, EdgeContenedores> max2 = ContenedoresHeuristic.caminoVoraz(graph,50);
			
			System.out.println("Solucion Voraz ="+max.getWeight());
			System.out.println("Solucion Voraz 2 ="+max2.getWeight());
			System.out.println("Heuristica ="+ContenedoresHeuristic.heuristic(start,v->v.goal(), null));
			// Algoritmo BT
			BT<VertexContenedores, EdgeContenedores, SolucionContenedores> bta = 
					BT.ofGreedy(graph);
			

			Optional<GraphPath<VertexContenedores, EdgeContenedores>> gp = bta.search();
			
			System.out.println(SolucionContenedores.of(gp.get()));
		}
	}

}

