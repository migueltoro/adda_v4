package us.lsi.alg.monedas;

import java.util.Collections;
import java.util.Comparator;
import java.util.Locale;

import org.jgrapht.GraphPath;

import us.lsi.graphs.alg.GreedyOnGraph;
import us.lsi.graphs.virtual.EGraph;
import us.lsi.graphs.virtual.EGraph.Type;
import us.lsi.path.EGraphPath.PathType;

public class TestGreedy {

	public static void main(String[] args) {
		Locale.setDefault(Locale.of("en", "US"));
		DatosMonedas.datosIniciales("ficheros/monedas/monedas2.txt");
		System.out.println(DatosMonedas.monedas);
		System.out.println(DatosMonedas.monedas.size());

//		System.out.println("1 = "+e1);
//		System.out.println("2 = "+e2);
		
//		System.out.println("3 = "+e1);
//		System.out.println("4 = "+e2);
		
		for (int i = 0; i < 5; i++) {
			
			Collections.sort(DatosMonedas.monedas, Comparator.comparing(m ->-m.pesoUnitario()));
//			System.out.println(DatosMonedas.valorInicial + "--------");
			MonedasVertexI e1 = MonedasVertexI.first(400+i);
			//		MonedaVertex e2 = MonedaVertex.last();
			EGraph<MonedasVertex, MonedasEdge> graph = EGraph.virtual(e1)
					.pathType(PathType.Sum)
					.type(Type.Max)
					.heuristic(MonedasHeuristica::heuristic)
					.build();
		
			GreedyOnGraph<MonedasVertex, MonedasEdge> rr = GreedyOnGraph.of(graph);
			GraphPath<MonedasVertex, MonedasEdge> path = rr.path();
			System.out.println(String.format("%s,%s,%.2f,%.2f,",400+i,rr.isSolution(path),
					path.getWeight(),
					MonedasHeuristica.heuristic(e1, v->v.goal(), null)));
//			System.out.println(String.format("%s",path.getEdgeList().stream().map(e->e.action()).toList()));
//			System.out.println(String.format("%s",SolucionMonedas.of(path.getEdgeList().stream().map(e->e.action()).toList())));
			Collections.sort(DatosMonedas.monedas, Comparator.comparing(m -> m.pesoUnitario()));
			//		System.out.println(Moneda.monedas);
			e1 = MonedasVertexI.first(400+i);
			graph = EGraph.virtual(e1)
					.pathType(PathType.Sum)
					.type(Type.Min)
					.heuristic(MonedasHeuristica::heuristic)
					.build();	
			rr = GreedyOnGraph.of(graph, MonedasVertex::greedyEdge);
			path = rr.path();
			System.out.println(String.format("%s,%s,%.2f,%.2f",400+i,rr.isSolution(path),path.getWeight(),
					MonedasHeuristica.heuristic(e1, v->v.goal(), null)));
		}
	}

}
