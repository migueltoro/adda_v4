package us.lsi.alg.investigadores;

import org.jgrapht.GraphPath;
import us.lsi.graphs.alg.AStar;
import us.lsi.graphs.alg.GreedyOnGraph;
import us.lsi.graphs.virtual.EGraph;
import us.lsi.graphs.virtual.EGraph.Type;
import us.lsi.path.EGraphPath.PathType;


public class TestAStar {

	public static void main(String[] args) {
			DatosInv.iniDatos("ficheros/investigadores/inv1.txt");
			DatosInv.toConsole();
			
			EGraph<InvVertex, InvEdge> graph = EGraph.virtual(InvVertexI.first())
						.pathType(PathType.Last)
						.type(Type.Max)
						.vertexWeight(v->v.fo().doubleValue())
						.heuristic(InvHeuristic::heuristic).build();
			
			GreedyOnGraph<InvVertex, InvEdge> gd = GreedyOnGraph.of(graph);
			GraphPath<InvVertex, InvEdge> pgd = gd.path();
			System.out.println(pgd.getEndVertex());
			
			AStar<InvVertex, InvEdge,SolucionInv> ms = AStar.of(graph,pgd.getWeight(),pgd);
			
			GraphPath<InvVertex,InvEdge> path = ms.search().get();
			SolucionInv s = SolucionInv.of(path);
			System.out.println(s);
	}

}
