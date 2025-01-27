package us.lsi.alg.tsp;

import java.io.PrintWriter;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.interfaces.SpanningTreeAlgorithm;
import org.jgrapht.alg.interfaces.SpanningTreeAlgorithm.SpanningTree;
import org.jgrapht.alg.spanning.KruskalMinimumSpanningTree;
import org.jgrapht.graph.SimpleWeightedGraph;
import org.jgrapht.nio.dot.DOTExporter;

import us.lsi.common.Files2;
import us.lsi.common.IntPair;
import us.lsi.common.List2;
import us.lsi.common.Preconditions;
import us.lsi.grafos.datos.Carretera;
import us.lsi.grafos.datos.Ciudad;
import us.lsi.graphs.Graphs2;
import us.lsi.graphs.GraphsReader;
import us.lsi.graphs.SimpleEdge;
import us.lsi.graphs.alg.DephtSearch;
import us.lsi.graphs.views.CompleteGraphView;
import us.lsi.graphs.views.SubGraphView;
import us.lsi.graphs.virtual.EGraph;
import us.lsi.math.Math2;
import us.lsi.path.GraphPath2;

public class AuxiliaryTsp {
	
	public static Ciudad ciudad(Graph<Ciudad,Carretera> graph, String nombre) {
		return graph.vertexSet().stream().filter(c->c.nombre().equals(nombre)).findFirst().get();
	}
	
	public static SimpleWeightedGraph<Ciudad,Carretera> leeGraph(String fichero) {
		SimpleWeightedGraph<Ciudad,Carretera> graph =  
				GraphsReader.newGraph(fichero,
						Ciudad::ofFormat, 
						Carretera::ofFormat,
						Graphs2::simpleWeightedGraph,
						Carretera::km);
		return graph;
	}
	
	public static Graph<Ciudad,Carretera> completeGraph(Graph<Ciudad,Carretera> graph){
		return CompleteGraphView.of(graph,()->Carretera.of(2000.));
	}
	
	public static SpanningTree<Carretera> spanningTree(Graph<Ciudad, Carretera> graph) {
		SpanningTreeAlgorithm<Carretera> ast = new KruskalMinimumSpanningTree<>(graph);
		SpanningTree<Carretera> r = ast.getSpanningTree();
		return r;
	}
	
	public static Double increment(List<Ciudad> ciudades, IntPair p) {
		Graph<Ciudad,Carretera> graph = TravelVertex.graph;
		Integer i = p.first();
		Integer j = p.second();
		Preconditions.checkArgument(i<j);
		GraphPath<Ciudad,Carretera> path = GraphPath2.ofVertices(graph,ciudades);
		Double w = path.getWeight();
		Integer n = ciudades.size();
		List<Ciudad> ls1 = List2.copy(ciudades.subList(0,i));
		List<Ciudad> ls2 = List2.copy(ciudades.subList(i,j));
		Collections.reverse(ls2);
		List<Ciudad> ls3 = List2.copy(ciudades.subList(j,n));
		ls1.addAll(ls2);
		ls1.addAll(ls3);
		GraphPath<Ciudad,Carretera> path2 = GraphPath2.ofVertices(graph,ls1);	
		return w-path2.getWeight();
	}
	
	public static Double incrementInteger(List<Integer> ciudades, IntPair p) {
		Graph<Integer,SimpleEdge<Integer>> graph = TravelVertexInteger.graph;
		Integer i = p.first();
		Integer j = p.second();
		Preconditions.checkArgument(i<j);
		GraphPath<Integer,SimpleEdge<Integer>> path = GraphPath2.ofVertices(graph,ciudades);
		Double w = path.getWeight();
		Integer n = ciudades.size();
		List<Integer> ls1 = List2.copy(ciudades.subList(0,i));
		List<Integer> ls2 = List2.copy(ciudades.subList(i,j));
		Collections.reverse(ls2);
		List<Integer> ls3 = List2.copy(ciudades.subList(j,n));
		ls1.addAll(ls2);
		ls1.addAll(ls3);
		GraphPath<Integer,SimpleEdge<Integer>> path2 = GraphPath2.ofVertices(graph,ls1);	
		return w-path2.getWeight();
	}
	
	public static List<Ciudad> neighbor(List<Ciudad> ciudades, IntPair p) {
		Integer i = p.first();
		Integer j = p.second();
		Preconditions.checkArgument(i<j);
		Integer n = ciudades.size();
		List<Ciudad> ls1 = List2.copy(ciudades.subList(0,i));
		List<Ciudad> ls2 = List2.copy(ciudades.subList(i,j));
		Collections.reverse(ls2);
		List<Ciudad> ls3 = List2.copy(ciudades.subList(j,n));
		ls1.addAll(ls2);
		ls1.addAll(ls3);
		return ls1;
	}
	
	public static List<Integer> neighborInteger(List<Integer> ciudades, IntPair p) {
//		Graph<Integer,SimpleEdge<Integer>> graph = TravelVertexInteger.graph;
		Integer i = p.first();
		Integer j = p.second();
		Preconditions.checkArgument(i<j);
		Integer n = ciudades.size();
		List<Integer> ls1 = List2.copy(ciudades.subList(0,i));
		List<Integer> ls2 = List2.copy(ciudades.subList(i,j));
		Collections.reverse(ls2);
		List<Integer> ls3 = List2.copy(ciudades.subList(j,n));
		ls1.addAll(ls2);
		ls1.addAll(ls3);
		return ls1;
	}
	
	public static Graph<Integer,SimpleEdge<Integer>> generate(Integer n){
		Graph<Integer,SimpleEdge<Integer>> graph = Graphs2.simpleGraph(null,null,true);
		IntStream.range(0,n).boxed()
			.filter(v->!graph.containsVertex(v))
			.forEach(v->graph.addVertex(v));
		List<Integer> vertices = graph.vertexSet().stream().collect(Collectors.toList());
		for(int i=0;i<vertices.size();i++) {
			for(int j=i+1;j<vertices.size();j++) {
				Double w = Math2.getDoubleAleatorio(0.,500.);
				SimpleEdge<Integer> e = SimpleEdge.of(vertices.get(i),vertices.get(j),w);
				graph.addEdge(vertices.get(i),vertices.get(j),e);
				graph.setEdgeWeight(e,w);
			}
		}
		return graph;
	}
	
	public static List<Integer> solution(){
		return List.of(41, 57, 58, 30, 14, 80, 32, 86, 96, 56, 0, 76, 81, 75, 62, 73, 34, 65, 98, 91, 
				70, 15, 44, 36, 74, 24, 47, 33, 22, 39, 90, 59, 89, 83, 23, 52, 60, 16, 17, 29, 8, 54, 40, 37, 77, 
				46, 95, 43, 72, 21, 67, 63, 19, 26, 50, 27, 88, 35, 42, 25, 18, 94, 61, 69, 28, 13, 11, 31, 64, 1, 84, 
				92, 5, 6, 7, 
				87, 38, 66, 12, 9, 45, 97, 78, 4, 3, 68, 79, 93, 53, 2, 85, 55, 51, 82, 48, 49, 20, 71, 10, 99, 0);
	}
		
	public static void main(String[] args) {
		Graph<Ciudad,Carretera> graph1 = leeGraph("ficheros/andalucia/andalucia.txt");
		Graph<Ciudad,Carretera> graph2 = completeGraph(graph1);
		SpanningTree<Carretera> tree = spanningTree(graph2);
		Graph<Ciudad,Carretera> graph3 = SubGraphView.of(graph2,
				v->graph1.vertexSet().contains(v),
				e->tree.getEdges().contains(e));
//		System.out.println(graph3);
		EGraph<Ciudad,Carretera> g4 = EGraph.ofGraph(graph3).build();
		DephtSearch<Ciudad,Carretera> ms = DephtSearch.of(g4,ciudad(graph1,"Sevilla"));
		List<Ciudad> camino = ms.stream().collect(Collectors.toList());
//		camino.add(ciudad(graph1,"Sevilla"));
		GraphPath<Ciudad,Carretera> path = GraphPath2.ofVertices(graph2,camino);
		System.out.println(path.getWeight());
		Integer n = camino.size();
		TravelVertex.graph = graph2;
		for (int i = 0; i < n; i++) {
			for (int j = i+1; j < n; j++) {
				Double w = increment(camino, IntPair.of(i, j));
				System.out.println(String.format("%d,%d,%.2f",i,j,w));
			}
		}
		
		DOTExporter<Ciudad,Carretera> de = new DOTExporter<Ciudad,Carretera>();
		de.setVertexIdProvider(v->v.nombre());
		de.setEdgeIdProvider(e->String.format("%.2f",e.km()));
		
		PrintWriter f1 = Files2.getWriter("ficheros/tourAndalucia1.gv");
		de.exportGraph(graph1, f1);
	}
	
	
}
