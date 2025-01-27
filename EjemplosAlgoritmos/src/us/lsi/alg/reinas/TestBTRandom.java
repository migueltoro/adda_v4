package us.lsi.alg.reinas;


import java.util.Optional;

import org.jgrapht.GraphPath;

import us.lsi.graphs.alg.BTR;
import us.lsi.graphs.virtual.SimpleEdgeAction;
import us.lsi.graphs.virtual.EGraph.Type;
import us.lsi.path.EGraphPath.PathType;
import us.lsi.graphs.virtual.EGraph;


public class TestBTRandom {

	public static void main(String[] args) {
			ReinasVertexI.n = 110;
//			BTR.threshold = 15;
			ReinasVertex e1 = ReinasVertex.first();
			
			EGraph<ReinasVertex,SimpleEdgeAction<ReinasVertex,Integer>> graph = 
					EGraph.virtual(e1)
					.pathType(PathType.Last)
					.type(Type.All)
					.solutionNumber(2)
					.vertexWeight(v->v.errores().doubleValue())
					.build();		
			
			BTR<ReinasVertex,SimpleEdgeAction<ReinasVertex,Integer>, SolucionReinas> ms = 
					BTR.of(
					graph, 
					SolucionReinas::of, 
					v->ReinasVertexI.n-v.index(),
					15);	
			
			long startTime = System.nanoTime();
			Optional<GraphPath<ReinasVertex, SimpleEdgeAction<ReinasVertex, Integer>>> gp = ms.search();
			System.out.println("Iteraciones = "+ms.iterations);
			long endTime = System.nanoTime() - startTime;
			System.out.println("1 = "+endTime);
			System.out.println(SolucionReinas.of(gp.get()));
			System.out.println("______");
			ms.getSolutions().stream().forEach(s->System.out.println(s));
	}

}
