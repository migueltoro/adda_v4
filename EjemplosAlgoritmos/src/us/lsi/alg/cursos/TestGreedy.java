package us.lsi.alg.cursos;

import java.util.Locale;

import org.jgrapht.GraphPath;

import us.lsi.graphs.alg.GreedyOnGraph;
import us.lsi.graphs.virtual.EGraph;
import us.lsi.graphs.virtual.EGraph.Type;
import us.lsi.path.EGraphPath.PathType;

public class TestGreedy {

	public static void main(String[] args) {
		Locale.setDefault(Locale.of("en", "US"));
		
		DatosCursos.iniDatos("ficheros/cursos/cursos3.txt");
		
		EGraph<CursosVertex, CursosEdge> graph = 
				EGraph.virtual(CursosVertexI.first())
				.pathType(PathType.Sum)
				.type(Type.Min)
				.heuristic(CursosHeuristic::heuristic)
				.build();
		
		System.out.println(CursosVertexI.first());
		System.out.println("==================");
		GreedyOnGraph<CursosVertex, CursosEdge> ms = GreedyOnGraph.of(graph);
		GraphPath<CursosVertex, CursosEdge> path = ms.path();
		
		System.out.println(SolucionCursos.of(path));

	}

}
