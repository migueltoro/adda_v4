package us.lsi.alg.reinas;

import java.util.Optional;

import org.jgrapht.GraphPath;

import us.lsi.graphs.alg.AStar;
import us.lsi.graphs.virtual.SimpleEdgeAction;
import us.lsi.path.EGraphPath.PathType;
import us.lsi.graphs.virtual.EGraph;
import us.lsi.graphs.virtual.EGraph.Type;

public class TestAStar {
	
	public static void main(String[] args) {
		ReinasVertexI.n = 20;	
		ReinasVertex v1 = ReinasVertex.first();
		
		EGraph<ReinasVertex,SimpleEdgeAction<ReinasVertex,Integer>> graph = 
				EGraph.virtual(v1)
				.pathType(PathType.Last)
				.type(Type.All)
				.vertexWeight(v->v.errores().doubleValue())
				.solutionNumber(2)
				.build();			
		
		AStar<ReinasVertex, SimpleEdgeAction<ReinasVertex,Integer>, ?> ms = 
				AStar.of(graph,SolucionReinas::of,null,null);
		
		Optional<GraphPath<ReinasVertex, SimpleEdgeAction<ReinasVertex, Integer>>> path = ms.search();
		System.out.println(SolucionReinas.of(path.get()));
//		System.out.println(ms.getSolutions().size());
		ms.getSolutions().stream().forEach(s->System.out.println(s));
		
	}
}
