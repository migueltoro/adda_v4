package us.lsi.alg.typ;


import java.util.Locale;
import java.util.Optional;

import org.jgrapht.GraphPath;

import us.lsi.colors.GraphColors;
import us.lsi.colors.GraphColors.Color;
import us.lsi.graphs.alg.AStar;
import us.lsi.graphs.alg.GreedyOnGraph;
import us.lsi.graphs.virtual.SimpleEdgeAction;
import us.lsi.path.EGraphPath.PathType;
import us.lsi.graphs.virtual.EGraph;
import us.lsi.graphs.virtual.EGraph.Type;

public class TestAStarTyP {

	public static void main(String[] args) {
		Locale.setDefault(Locale.of("en", "US"));
		DatosTyP.datos("ficheros/tareas.txt",5);
		TyPVertex e1 = TyPVertex.first();
		
		
		EGraph<TyPVertex,SimpleEdgeAction<TyPVertex,Integer>> graph = 
				EGraph.virtual(e1)
				.pathType(PathType.Last)
				.type(Type.Min)
				.vertexWeight(v->v.maxCarga())
				.heuristic(Heuristica::heuristic)
				.build();	
		
		GreedyOnGraph<TyPVertex, SimpleEdgeAction<TyPVertex,Integer>> rr = GreedyOnGraph.of(graph);
		
		GraphPath<TyPVertex, SimpleEdgeAction<TyPVertex, Integer>> pp = rr.path();
		Double bv = pp.getWeight();
		System.out.println(bv);
		
		
		AStar<TyPVertex,SimpleEdgeAction<TyPVertex, Integer>,SolucionTyP> ms = AStar.ofGreedy(graph);
		
//		ms.stream().forEach(v->System.out.println(v));
		
		Optional<GraphPath<TyPVertex, SimpleEdgeAction<TyPVertex, Integer>>> path = ms.search();
//		List<MochilaEdge> edges = path.getEdgeList();
//		System.out.println(path);
		SolucionTyP s = SolucionTyP.of(path.get());
		System.out.println(s);
		
		GraphColors.toDot(ms.outGraph(),"ficheros/TyPAStar.gv",
				v->String.format("(%d,%.1f)",v.index(),v.maxCarga()),
				e->e.action().toString(),
				v->GraphColors.colorIf(Color.red,v.goal()),
				e->GraphColors.colorIf(Color.red,path.get().getEdgeList().contains(e))
				);
	}

}
