package us.lsi.alg.candidatos;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

import org.jgrapht.GraphPath;

import us.lsi.graphs.alg.AStar;
import us.lsi.graphs.virtual.EGraph;
import us.lsi.graphs.virtual.EGraph.Type;
import us.lsi.path.EGraphPath.PathType;
import us.lsi.colors.GraphColors;
import us.lsi.colors.GraphColors.Color;

public class TestAStart {

	
	public static void main(String[] args) {

		// Set up
		Locale.setDefault(Locale.of("en", "US"));
		
		for (Integer i = 1; i < 2; i++) {

			DatosCandidatos.iniDatos("ficheros/candidatos"+ i +".txt");
			DatosCandidatos.toConsole();
			System.out.println("\n\n>\tResultados para el test " + i + "\n");

			// V�rtices clave

			VertexCandidatos start = VertexCandidatosI.initial();

			// Grafo
			
			//SimpleVirtualGraph.constraintG =  MulticonjuntoVertex.constraint();

			System.out.println("#### Algoritmo A* ####");

			// Algoritmo A*
			EGraph<VertexCandidatos, EdgeCandidatos> graph = EGraph.virtual(start)
					.pathType(PathType.Sum)
					.type(Type.Max)
					.edgeWeight(x -> x.weight())
					.heuristic(CandidatosHeuristic::heuristic)
					.build();
			
			
			AStar<VertexCandidatos, EdgeCandidatos, SolucionCandidatos> aStar = AStar.ofGreedy(graph);
			
			Optional<GraphPath<VertexCandidatos, EdgeCandidatos>> gp = aStar.search();

			if (gp.isPresent()) {
			    SolucionCandidatos s_as = SolucionCandidatos.of(gp.get());
			    System.out.println(s_as);
			} else {
				System.out.println("No se encontro soluci�n");
			}
			
			GraphColors.toDot(aStar.outGraph(), "ficheros/candidatosAStarGraph.gv", 
					v -> v.toString(),
					e -> e.action().toString(), 
					v -> GraphColors.colorIf(
							List.of(!aStar.closed(v),
									v.goal() && (v.goalHasSolution()),
									v.goal()),
						    List.of(Color.green,Color.red,Color.blue)),
					e -> GraphColors.colorIf(Color.red, gp.get().getEdgeList().contains(e)));
		}
	}

}
