package us.lsi.alg.reinas;


import java.util.Optional;

import org.jgrapht.GraphPath;

import us.lsi.graphs.alg.BT;
import us.lsi.graphs.virtual.SimpleEdgeAction;
import us.lsi.graphs.virtual.EGraph.Type;
import us.lsi.path.EGraphPath.PathType;
import us.lsi.graphs.virtual.EGraph;

public class TestBT {

	public static void main(String[] args) {
		ReinasVertexI.n = 10;
		ReinasVertex e1 = ReinasVertex.first();
		
		EGraph<ReinasVertex,SimpleEdgeAction<ReinasVertex,Integer>> graph = 
				EGraph.virtual(e1)
				.pathType(PathType.Last)
				.type(Type.All)
				.vertexWeight(v->v.errores().doubleValue())
				.solutionNumber(1000)
				.build();

		BT<ReinasVertex,SimpleEdgeAction<ReinasVertex,Integer>, SolucionReinas> ms = 
				BT.of(graph, 
				SolucionReinas::of,null,null,false);

		Optional<GraphPath<ReinasVertex, SimpleEdgeAction<ReinasVertex, Integer>>> gp = ms.search();
		System.out.println(SolucionReinas.of(gp.get()));
//		ms.getSolutions().stream().forEach(s->System.out.println(s));
		System.out.println(ms.getSolutions().size());
	}
}
