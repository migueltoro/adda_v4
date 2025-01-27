package us.lsi.alg.pack;

import java.util.Locale;

import org.jgrapht.GraphPath;

import us.lsi.graphs.alg.GreedyOnGraph;
import us.lsi.graphs.virtual.EGraph;
import us.lsi.graphs.virtual.EGraph.Type;
import us.lsi.path.EGraphPath.PathType;

public class TestHeuristica {

	public static void main(String[] args) {
		Locale.setDefault(Locale.of("en", "US"));
		Data.data("ficheros/pack/pack1.txt");
		PackVertex e1 = PackVertex.first();
		
		EGraph<PackVertex,PackEdge> graph = 
				EGraph.virtual(e1)
				.pathType(PathType.Sum)
				.type(Type.Min)
				.vertexWeight(v->(double)v.nc())
				.edgeWeight(e->e.weight())
				.heuristic(Heuristica::heuristic)
				.build();		
		
		GreedyOnGraph<PackVertex,PackEdge> rr = GreedyOnGraph.of(graph);
	
		GraphPath<PackVertex, PackEdge> p = rr.path();
		SolucionPack sp = SolucionPack.of(p);
		System.out.println(sp);
		System.out.println("Vo = "+sp.nc());
		System.out.println("He = "+Heuristica.heuristic(e1, v->v.goal(), null));
	}

}
