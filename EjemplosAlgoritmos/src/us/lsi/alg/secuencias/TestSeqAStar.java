package us.lsi.alg.secuencias;

import java.util.Locale;

import org.jgrapht.GraphPath;

import us.lsi.common.List2;
import us.lsi.graphs.alg.AStar;
import us.lsi.graphs.virtual.EGraph;

public class TestSeqAStar {

	public static void main(String[] args) {
		
		Locale.setDefault(Locale.of("en", "US"));
		
		SeqVertex.data("cbrrrarreterb", "carretera");
		SeqVertex e1 = SeqVertex.first();
		SeqVertex e2 = SeqVertex.last();
		
		
		EGraph<SeqVertex, SeqEdge> graph =
				EGraph.virtual(e1)
				.edgeWeight(e->e.weight())
				.endVertex(e2)
				.heuristic(SeqHeuristic::heuristic)
				.build();	
		
		AStar<SeqVertex, SeqEdge, ?> ms = AStar.ofGreedy(graph);
		
		GraphPath<SeqVertex, SeqEdge> path = ms.search().orElse(null);
		SeqSolution s = SeqSolution.of(path);
		System.out.println(s);
		System.out.println(List2.last(path.getVertexList()));
	}

}
