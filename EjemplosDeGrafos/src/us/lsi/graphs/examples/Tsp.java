package us.lsi.graphs.examples;



import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.cycle.HierholzerEulerianCycle;
import org.jgrapht.alg.interfaces.EulerianCycleAlgorithm;
import org.jgrapht.alg.interfaces.HamiltonianCycleAlgorithm;
import org.jgrapht.alg.interfaces.ShortestPathAlgorithm;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.alg.tour.TwoApproxMetricTSP;
import org.jgrapht.graph.SimpleWeightedGraph;

import us.lsi.grafos.datos.Carretera;
import us.lsi.grafos.datos.Ciudad;
import us.lsi.graphs.Graphs2;
import us.lsi.graphs.GraphsReader;

public class Tsp {
	
	public static Ciudad ciudad(Graph<Ciudad,Carretera> graph, String nombre) {
		return graph.vertexSet().stream().filter(c->c.nombre().equals(nombre)).findFirst().get();
	}

	public static void main(String[] args) {
		SimpleWeightedGraph<Ciudad, Carretera> graph = GraphsReader.newGraph("ficheros/andalucia.txt", 
				Ciudad::ofFormat,
				Carretera::ofFormat, 
				Graphs2::simpleWeightedGraph,
				Carretera::km);
		SimpleWeightedGraph<Ciudad, Carretera> graph2 = Graphs2.explicitCompleteGraph(
				graph, 
				1000.,
				Graphs2::simpleWeightedGraph, 
				()->Carretera.of(1000.),
				Carretera::km);
		HamiltonianCycleAlgorithm<Ciudad, Carretera> a = new TwoApproxMetricTSP<>();
		GraphPath<Ciudad, Carretera> r = a.getTour(graph2);
		System.out.println(r.getVertexList());
		System.out.println(r.getEdgeList());
		System.out.println(r.getWeight());
		System.out.println(r.getEdgeList().stream().mapToDouble(x->x.km()).sum());
		System.out.println(r.getEdgeList().stream().mapToDouble(x->graph2.getEdgeWeight(x)).sum());
		System.out.println("==========");
		EulerianCycleAlgorithm<Ciudad, Carretera> a2 = new HierholzerEulerianCycle<>();
		GraphPath<Ciudad, Carretera> r2 = a2.getEulerianCycle(graph);
		System.out.println(r2.getVertexList());
		System.out.println(r2.getEdgeList());
		System.out.println(r2.getWeight());
		System.out.println(r2.getEdgeList().stream().mapToDouble(x->x.km()).sum());
		System.out.println(r2.getEdgeList().stream().mapToDouble(x->graph2.getEdgeWeight(x)).sum());
		ShortestPathAlgorithm<Ciudad, Carretera> dj = new DijkstraShortestPath<Ciudad, Carretera>(graph2);
		System.out.println("==========");
		Ciudad from = ciudad(graph,"Huelva");
		Ciudad to = ciudad(graph,"Almeria");
		GraphPath<Ciudad, Carretera> gp = dj.getPath(from, to);
		System.out.println(gp);
		System.out.println(gp.getVertexList());
		System.out.println(gp.getWeight());
		System.out.println(gp.getEdgeList().stream().mapToDouble(x->x.km()).sum());
		
		
		
//		DOTExporter<Ciudad, Carretera> de = new DOTExporter<Ciudad, Carretera>(new IntegerComponentNameProvider<>(),
//				x -> x.getNombre(), x -> x.getNombre() + "--" + x.getKm(), null,
//				e -> GraphColors.getStyleIf("bold", e, x -> r.getEdgeList().contains(x)));
//		PrintWriter f1 = Files2.getWriter("ficheros/tspAndalucia2.gv");
//		de.exportGraph(graph2, f1);
	}

}
