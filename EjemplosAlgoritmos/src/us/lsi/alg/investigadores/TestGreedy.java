package us.lsi.alg.investigadores;

import org.jgrapht.GraphPath;

import us.lsi.graphs.alg.GreedyOnGraph;
import us.lsi.graphs.virtual.EGraph;
import us.lsi.graphs.virtual.EGraph.Type;
import us.lsi.path.EGraphPath.PathType;

public class TestGreedy {

	public static void main(String[] args) {
		DatosInv.iniDatos("ficheros/investigadores/inv1.txt");
		DatosInv.toConsole();
		
		EGraph<InvVertex, InvEdge> graph = EGraph.virtual(InvVertexI.first())
				.pathType(PathType.Last)
				.type(Type.Max)
				.vertexWeight(v->v.fo().doubleValue())
				.heuristic(InvHeuristic::heuristic).build();
	
		System.out.println(InvVertex.first());
		System.out.println("==================");
		GreedyOnGraph<InvVertex, InvEdge> ms = GreedyOnGraph.of(graph);
		GraphPath<InvVertex, InvEdge> path = ms.path();
//		System.out.println(path.getEdgeList().stream().map(e->e.action()).toList());
		
		System.out.println(path.getEndVertex());
		System.out.println("==================");
//		System.out.println(InvVertex.first());
		InvVertex v = InvHeuristic.greedy(InvVertexI.first());
//		SolucionInv s = SolucionInv.of(path);
		System.out.println(v);
		System.out.println("==================");
		System.out.println(InvHeuristic.heuristic(InvVertexI.first(),x->x.goal(),null));
	}

}
