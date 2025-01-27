package us.lsi.alg.contenedores;

import java.util.function.Predicate;

import org.jgrapht.GraphPath;

import us.lsi.graphs.alg.GreedyOnGraph;
import us.lsi.graphs.virtual.EGraph;

public class ContenedoresHeuristic {

	public static Double heuristic(VertexContenedores v1, Predicate<VertexContenedores> goal,
			VertexContenedores  v2) {
		//return (double)(DatosContenedores.getNumContenedores()-v1.index()+1);
		//return (double)DatosContenedores.getNumContenedores()-v1.contenedoresCompletos().size();
		return v1.contenedoresCompletos().size()+ 
				Math.min((double)DatosContenedores.getNumContenedores()-v1.contenedoresCompletos().size(),
				(double)DatosContenedores.getNumElementos()-v1.index());
	}
	
	public static GraphPath<VertexContenedores, EdgeContenedores> caminoVoraz(EGraph<VertexContenedores, EdgeContenedores> graph, Integer nv) {
		Integer max = 0;
		GraphPath<VertexContenedores, EdgeContenedores> gp = null;
		for (int i = 0; i < nv; i++) {
			GreedyOnGraph<VertexContenedores, EdgeContenedores> ga = GreedyOnGraph.random(graph);
			GraphPath<VertexContenedores, EdgeContenedores> path = ga.path();
			Integer m = path.getEndVertex().contenedoresCompletos().size();
			if (m > max) max = m;
			gp = path;
		}
		return gp;
	}
	
	public static GraphPath<VertexContenedores, EdgeContenedores> caminoVoraz2(
			EGraph<VertexContenedores, EdgeContenedores> graph) {
			GreedyOnGraph<VertexContenedores, EdgeContenedores> ga = GreedyOnGraph.of(graph,
					v -> v.edge(v.greedyAction()));
			GraphPath<VertexContenedores, EdgeContenedores> path = ga.path();
//			Integer m = path.getEndVertex().contenedoresCompletos().size();
		return path;
	}
}
