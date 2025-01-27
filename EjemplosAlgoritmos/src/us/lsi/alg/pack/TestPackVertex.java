package us.lsi.alg.pack;

import java.util.Arrays;
import java.util.Locale;

import us.lsi.graphs.virtual.EGraph;
import us.lsi.graphs.virtual.EGraph.Type;
import us.lsi.path.EGraphPath.PathType;

public class TestPackVertex {

	public static void main(String[] args) {
		Locale.setDefault(Locale.of("en", "US"));
		Data.data("ficheros/pack/pack.txt");
		PackVertex e1 = PackVertex.first();
//		Predicate<PackVertex> goal  = PackVertex.goal;
		
		EGraph<PackVertex,PackEdge> graph = 
				EGraph.virtual(e1)
				.pathType(PathType.Sum)
				.type(Type.Min)
				.vertexWeight(v->(double)v.nc())
				.edgeWeight(e->e.weight())
				.heuristic(Heuristica::heuristic)
				.build();		
		
		System.out.println(graph.startVertex());
		System.out.println(graph.startVertex().actions());
		PackVertex v0 = graph.startVertex();
		PackVertex v1 = v0.neighbor(0);
		PackVertex v2 = v1.neighbor(0);
		PackVertex v3 = v2.neighbor(0);
		System.out.println(Arrays.asList(v0,v1,v2,v3));
		System.out.println(graph.getEdgeSource(graph.startVertex().greedyEdge()));
		System.out.println(graph.getEdgeTarget(graph.startVertex().greedyEdge()));
		System.out.println(graph.startVertex().greedyEdge());

	}

}
