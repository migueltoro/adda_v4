package us.lsi.alg.robots;


import java.util.Locale;
import java.util.Optional;

import org.jgrapht.GraphPath;

import us.lsi.graphs.alg.BT;
import us.lsi.graphs.virtual.EGraph;
import us.lsi.graphs.virtual.EGraph.Type;
import us.lsi.path.EGraphPath.PathType;

public class TestBT {

	public static void main(String[] args) {
		Locale.setDefault(Locale.of("en", "US"));
		RobotVertex v0 = RobotVertex.of();	
		
		EGraph<RobotVertex, RobotEdge> graph = 
				EGraph.virtual(v0)
				.pathType(PathType.Sum)
				.type(Type.Max)
				.edgeWeight(e->e.weight())
				.heuristic((v1,p,v2)->3.*(RobotVertex.N-v1.t()))
				.solutionNumber(4)
				.build();

		
		BT<RobotVertex, RobotEdge,RobotSolution> ms = BT.of(graph,RobotSolution::of,null, null,true);	
		
		Long t0 = System.nanoTime();
		Optional<GraphPath<RobotVertex,RobotEdge>> path = ms.search();
		Long t1 = System.nanoTime();
		System.out.println(t1-t0);
		System.out.println(RobotSolution.of(path.get()));
		System.out.println(ms.bestValue);
		
		

//		GraphColors.toDot(ms.outGraph(),"ficheros/RobotsBT.gv",
//				v->String.format("%s",v.toString()),
//				e->e.action().toString(),
//				v->GraphColors.colorIf(Color.red,v.goal()),
//				e->GraphColors.colorIf(Color.red,path.get().getEdgeList().contains(e))
//				);
	}

}
