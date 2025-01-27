package us.lsi.alg.typ;

import java.util.Locale;
import java.util.Optional;

import org.jgrapht.GraphPath;

import us.lsi.graphs.alg.BT;
import us.lsi.graphs.alg.GreedyOnGraph;
import us.lsi.graphs.virtual.SimpleEdgeAction;
import us.lsi.graphs.virtual.EGraph.Type;
import us.lsi.path.EGraphPath.PathType;
import us.lsi.graphs.virtual.EGraph;

public class TestBTTyP {

	
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
		
		GraphPath<TyPVertex, SimpleEdgeAction<TyPVertex, Integer>> path = rr.path();
		Double bv = path.getWeight();
		System.out.println(bv);
		
		BT<TyPVertex, SimpleEdgeAction<TyPVertex, Integer>, SolucionTyP> ms = BT.ofGreedy(
						graph);		
		
		Optional<GraphPath<TyPVertex, SimpleEdgeAction<TyPVertex, Integer>>> gp = ms.search();

		System.out.println(SolucionTyP.of(gp.get()));
		
//		GraphPath<TyPVertex, SimpleEdgeAction<TyPVertex, Integer>> sp = ms.optimalPath().get();
//		GraphColors.toDot(ms.outGraph(),"ficheros/TyPBT.gv",
//				v->String.format("(%d,%.1f)",v.index(),v.maxCarga()),
//				e->e.action().toString(),
//				v->GraphColors.colorIf(Color.red,v.goal()),
//				e->GraphColors.colorIf(Color.red,sp.getEdgeList().contains(e))
//				);

	}

}
