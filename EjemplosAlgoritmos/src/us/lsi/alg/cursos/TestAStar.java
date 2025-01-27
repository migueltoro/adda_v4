package us.lsi.alg.cursos;

import java.util.Locale;

import org.jgrapht.GraphPath;

import us.lsi.graphs.alg.AStar;
import us.lsi.graphs.alg.GreedyOnGraph;
import us.lsi.graphs.virtual.EGraph;
import us.lsi.graphs.virtual.EGraph.Type;
import us.lsi.path.EGraphPath.PathType;

public class TestAStar {

	public static void main(String[] args) {
		Locale.setDefault(Locale.of("en", "US"));
		
		DatosCursos.iniDatos("ficheros/cursos/cursos3.txt");
		
		EGraph<CursosVertex, CursosEdge> graph = 
				EGraph.virtual(CursosVertex.first())
				.pathType(PathType.Sum)
				.type(Type.Min)
				.heuristic(CursosHeuristic::heuristic)
				.build();
		
		GreedyOnGraph<CursosVertex, CursosEdge> ms = GreedyOnGraph.of(graph);
		GraphPath<CursosVertex, CursosEdge> path = ms.path();
		
		System.out.println(SolucionCursos.of(path));
		
		System.out.println("_________________________________");
		
		AStar<CursosVertex, CursosEdge,SolucionCursos> msa = AStar.of(graph,path.getWeight(),path);
		
		GraphPath<CursosVertex, CursosEdge> gp = msa.search().get();
		SolucionCursos s = SolucionCursos.of(gp);
		System.out.println(s);
		
		
	}

}
