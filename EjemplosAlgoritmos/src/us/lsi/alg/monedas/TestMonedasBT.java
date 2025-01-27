package us.lsi.alg.monedas;

import java.util.Collections;
import java.util.Comparator;
import java.util.Locale;
import java.util.Optional;


import org.jgrapht.GraphPath;
import org.jgrapht.graph.SimpleDirectedGraph;

import us.lsi.colors.GraphColors;
import us.lsi.colors.GraphColors.Color;
import us.lsi.graphs.alg.BT;
import us.lsi.graphs.alg.GreedyOnGraph;
import us.lsi.graphs.virtual.EGraph;
import us.lsi.graphs.virtual.EGraph.Type;
import us.lsi.path.EGraphPath.PathType;

public class TestMonedasBT {

	public static void main(String[] args) {
		Locale.setDefault(Locale.of("en", "US"));
		DatosMonedas.datosIniciales("ficheros/monedas/monedas2.txt");
		Integer valorInicial = 400;

		MonedasVertex e1 = MonedasVertexI.first(valorInicial);
		
		EGraph<MonedasVertex, MonedasEdge> graph = 
				EGraph.virtual(e1)
				.pathType(PathType.Sum)
				.type(Type.Max)
				.heuristic(MonedasHeuristica::heuristic)
				.build();

		GreedyOnGraph<MonedasVertex, MonedasEdge> rr = GreedyOnGraph.of(graph);

		GraphPath<MonedasVertex, MonedasEdge> path1 = rr.path();

		BT<MonedasVertex, MonedasEdge, SolucionMonedas> ms1;

		if (rr.isSolution(path1)) {
			System.out.println("Hay solucion voraz 1"+path1.getWeight());
			ms1 = BT.of(graph,SolucionMonedas::of,path1.getWeight(),path1,true);
		} else {
			ms1 = BT.of(graph,SolucionMonedas::of,null,null,true);
		}
		
		ms1.search();

		Optional<GraphPath<MonedasVertex, MonedasEdge>> ps = ms1.optimalPath();

		if (ps.isPresent()) {
			SolucionMonedas s = SolucionMonedas.of(ps.get());
			System.out.println("2 = " + s.toString());
			SimpleDirectedGraph<MonedasVertex, MonedasEdge> g = ms1.outGraph();
			GraphColors.toDot(g, "ficheros/MonedasBTGraph1.gv",
					v -> String.format("(%d,%d)", v.index(), v.valorRestante()), e -> e.action().toString(),
					v -> GraphColors.colorIf(Color.red, v.goal()),
					e -> GraphColors.color(Color.black));
		} else
			System.out.println("2 = No hay solucion");

		Collections.sort(DatosMonedas.monedas, Comparator.comparing(m -> m.pesoUnitario()));

		MonedasVertex e3 = MonedasVertexI.first(valorInicial);

		graph = EGraph.virtual(e3)
				.pathType(PathType.Sum)
				.type(Type.Min)
				.heuristic(MonedasHeuristica::heuristic)
				.build();

		rr = GreedyOnGraph.of(graph, MonedasVertex::greedyEdge);

		GraphPath<MonedasVertex, MonedasEdge> path2 = rr.path();

		if (rr.isSolution(path1)) {
			System.out.println("Hay solucion voraz 1"+path1.getWeight());
			ms1 = BT.of(graph,SolucionMonedas::of,path2.getWeight(),path2,true);
		} else {
			ms1 = BT.of(graph,SolucionMonedas::of,null,null,true);
		}
		
		Optional<GraphPath<MonedasVertex, MonedasEdge>> gp = ms1.search();

		if (gp.isPresent()) {
			System.out.println("4 = " + SolucionMonedas.of(gp.get()).toString());
		} else
			System.out.println("4 = No hay solucion");

	}
}
