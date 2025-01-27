package us.lsi.alg.mochila;


import java.util.Locale;

import org.jgrapht.GraphPath;
import org.jgrapht.graph.SimpleDirectedGraph;

import us.lsi.colors.GraphColors;
import us.lsi.graphs.Graphs2;
import us.lsi.graphs.alg.GreedyOnGraph;
import us.lsi.graphs.virtual.EGraph;
import us.lsi.graphs.virtual.EGraph.Type;
import us.lsi.mochila.datos.DatosMochila;
import us.lsi.path.EGraphPath.PathType;


public class TestGreadyMochila {
	
	public static void main(String[] args) {
		Locale.setDefault(Locale.of("en", "US"));
		DatosMochila.iniDatos("ficheros/mochila/objetosMochila.txt");
		MochilaVertexI.capacidadInicial = 78;
		MochilaVertex v1 = MochilaVertex.initialVertex();
//		MochilaVertex v2 = MochilaVertex.lastVertex();
//		Predicate<MochilaVertex> goal = v->v.equals(v2);
//		System.out.println(e1);
//		System.out.println(e2);			
		Double r2 = MochilaHeuristic.heuristic1(v1,v->v.goal(),null);
		System.out.println("H1 "+r2);
		r2 = MochilaHeuristic.heuristic2(v1,v->v.goal(),null);
		System.out.println("H2 "+r2);
		
		EGraph<MochilaVertex, MochilaEdge> graph = 
				EGraph.virtual(v1)
				.pathType(PathType.Sum)
				.type(Type.Max)
				.heuristic(MochilaHeuristic::heuristic1)
				.build();
		
		GreedyOnGraph<MochilaVertex, MochilaEdge> gs = GreedyOnGraph.of(graph);
		
		GraphPath<MochilaVertex, MochilaEdge> gp = gs.path();
		
		System.out.println("G "+gp.getWeight());
		
//		Optional<GraphPath<MochilaVertex, MochilaEdge>> r = gs.path();
//		System.out.println("2 "+r.get().getWeight());
//		System.out.println(r.getWeight());
//		Double r3 = MochilaHeuristic.voraz(e1, e->e.equals(e2),e2);
//		System.out.println(r3);
//		GraphPath<MochilaVertex, MochilaEdge> r4 = MochilaHeuristic.greadyPath(e1,e->e.equals(e2));
//		System.out.println(r4);
//		System.out.println(r4.getWeight());
//		EGraph<MochilaVertex,MochilaEdge> graph = Graphs2.simpleVirtualGraph(e1);
//		GreedySearch<MochilaVertex, MochilaEdge> rr = GraphAlg.greedy(graph,
//				MochilaVertex::greedyEdgeHeuristic,
//				e->e.equals(e2));
//		System.out.println(rr.weightToEnd());
		System.out.println("________________");
		System.out.println(gp.getWeight());
		SimpleDirectedGraph<MochilaVertex, MochilaEdge> g = Graphs2.addPathToGraph(gp);
		GraphColors.toDot(g,
				"ficheros/MochilaGreadyPath.gv",
				v->String.format("((%d,%d)",v.index(),v.capacidadRestante()),
				e->e.action().toString());
	}


}
