package us.lsi.alg.monedas;

import java.util.Collections;
import java.util.Comparator;
import java.util.Locale;
import java.util.Optional;

import org.jgrapht.GraphPath;
import org.jgrapht.graph.SimpleDirectedGraph;

import us.lsi.colors.GraphColors;
import us.lsi.colors.GraphColors.Color;
import us.lsi.graphs.alg.AStar;
import us.lsi.graphs.alg.GreedyOnGraph;
import us.lsi.graphs.virtual.EGraph;
import us.lsi.graphs.virtual.EGraph.Type;
import us.lsi.path.EGraphPath.PathType;


public class TestMonedasAStar {

	public static void main(String[] args) {
		Locale.setDefault(Locale.of("en", "US"));
		DatosMonedas.datosIniciales("ficheros/monedas/monedas2.txt");
		Integer valorInicial = 400;
		MonedasVertex e1 = MonedasVertex.first(valorInicial);
		
		EGraph<MonedasVertex, MonedasEdge> graph = 
				EGraph.virtual(e1)
				.pathType(PathType.Sum)
				.type(Type.Max)
				.heuristic(MonedasHeuristica::heuristic)
				.build();	
		
		GreedyOnGraph<MonedasVertex, MonedasEdge> rr = GreedyOnGraph.of(graph);

		GraphPath<MonedasVertex, MonedasEdge> path1 = rr.path();
		
		AStar<MonedasVertex, MonedasEdge, SolucionMonedas> ms;
		
//		ms.stream().limit(100).forEach(v->System.out.printf("%s,actions = %s\n",v,v.actions()));
		
		if (rr.isSolution(path1)) {
			System.out.println("Hay solucion voraz 1 = " +path1.getWeight());
			ms = AStar.ofGreedy(graph);
		} else {
			ms = AStar.of(graph);
		}
		
		Optional<GraphPath<MonedasVertex, MonedasEdge>> path = ms.search();
		
		SolucionMonedas s;
		if (path.isPresent()) {
			s = SolucionMonedas.of(path.get());
			System.out.println(s);
			System.out.println(ms.tree.keySet().size());
			SimpleDirectedGraph<MonedasVertex, MonedasEdge> g = ms.outGraph();
			GraphColors.toDot(g,"ficheros/MonedasAstarGraph1.gv",
					v->String.format("(%d,%d)",v.index(),v.valorRestante()),
					e->e.action().toString(),
					v->GraphColors.colorIf(Color.red,v.goal()),
					e->GraphColors.color(Color.black)
					);
		} else {
			System.out.println("No hay solucion");
		}
		
		
		System.out.println("_________________________________");
		
		Collections.sort(DatosMonedas.monedas, Comparator.comparing(m -> m.pesoUnitario()));
		
		MonedasVertex e3 = MonedasVertexI.first(valorInicial);
//		MonedaVertex e4 = MonedaVertex.last();
		
		

		graph = EGraph.virtual(e3)
				.pathType(PathType.Sum)
				.type(Type.Min)
				.heuristic(MonedasHeuristica::heuristic)
				.build();	
		
		rr = GreedyOnGraph.of(graph, MonedasVertex::greedyEdge);

		GraphPath<MonedasVertex, MonedasEdge> path2 = rr.path();
	    
	    if (rr.isSolution(path2)) {
			System.out.println("Hay solucion voraz 2 = "+path2.getWeight());
			ms = AStar.ofGreedy(graph);
		} else {
			ms = AStar.of(graph);
		}
		
	    path = ms.search();
		
	    if (path.isPresent()) {
			s = SolucionMonedas.of(path.get());
			System.out.println(s);
			System.out.println(ms.tree.keySet().size());
			SimpleDirectedGraph<MonedasVertex, MonedasEdge> g = ms.outGraph();
			GraphColors.toDot(g,"ficheros/MonedasAstarGraph2.gv",
					v->String.format("(%d,%d)",v.index(),v.valorRestante()),
					e->e.action().toString(),
					v->GraphColors.colorIf(Color.red,v.goal()),
					e->GraphColors.color(Color.black)
					);
		} else {			
			System.out.println("No hay solucion");
		}
		
	}

}
