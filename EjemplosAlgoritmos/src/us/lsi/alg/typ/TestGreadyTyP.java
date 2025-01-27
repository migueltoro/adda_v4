package us.lsi.alg.typ;

import java.util.Locale;

import org.jgrapht.GraphPath;

import us.lsi.graphs.alg.GreedyOnGraph;
import us.lsi.graphs.virtual.SimpleEdgeAction;
import us.lsi.graphs.virtual.EGraph.Type;
import us.lsi.path.EGraphPath.PathType;
import us.lsi.graphs.virtual.EGraph;

public class TestGreadyTyP {
	
	

	public static void main(String[] args) {
		Locale.setDefault(Locale.of("en", "US"));
		DatosTyP.datos("ficheros/tareas.txt",5);
		TyPVertex e1 = TyPVertex.first();
//		TyPVertex e2 = TyPVertex.last();
		
		EGraph<TyPVertex,SimpleEdgeAction<TyPVertex,Integer>> graph = 
				EGraph.virtual(e1)
				.pathType(PathType.Last)
				.type(Type.Min)
				.vertexWeight(v->v.maxCarga())
				.heuristic(Heuristica::heuristic)
				.build();	
		
		GreedyOnGraph<TyPVertex, SimpleEdgeAction<TyPVertex, Integer>> ms = GreedyOnGraph.of(graph);	
		
		GraphPath<TyPVertex, SimpleEdgeAction<TyPVertex, Integer>> path = ms.path();
		
		System.out.println(SolucionTyP.of(path));
		
		System.out.println(path.getWeight());
	}

}
