package us.lsi.graphs.examples;

import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.color.GreedyColoring;
import org.jgrapht.alg.cycle.CycleDetector;
import org.jgrapht.alg.cycle.HierholzerEulerianCycle;
import org.jgrapht.alg.interfaces.EulerianCycleAlgorithm;
import org.jgrapht.alg.interfaces.VertexColoringAlgorithm;
import org.jgrapht.alg.interfaces.VertexColoringAlgorithm.Coloring;
import org.jgrapht.alg.interfaces.SpanningTreeAlgorithm.SpanningTree;
import org.jgrapht.alg.interfaces.VertexCoverAlgorithm;
import org.jgrapht.alg.interfaces.VertexCoverAlgorithm.VertexCover;
import org.jgrapht.alg.shortestpath.FloydWarshallShortestPaths;
import org.jgrapht.alg.spanning.KruskalMinimumSpanningTree;
import org.jgrapht.alg.vertexcover.RecursiveExactVCImpl;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DirectedAcyclicGraph;
import org.jgrapht.graph.SimpleDirectedGraph;
import org.jgrapht.traverse.BreadthFirstIterator;

import us.lsi.colors.GraphColors;
import us.lsi.common.String2;
import us.lsi.grafos.datos.Carretera;
import us.lsi.grafos.datos.Ciudad;
import us.lsi.graphs.Graphs2;
import us.lsi.graphs.GraphsReader;
import us.lsi.graphs.SimpleEdge;
import us.lsi.streams.Stream2;

public class VariosEjemplos {
	
	public static Graph<Ciudad, Carretera> leeFichero(String file) {
		return GraphsReader.newGraph(file, 
				Ciudad::ofFormat, 
				Carretera::ofFormat, 
				Graphs2::simpleWeightedGraph,
				Carretera::km);
	}
	
	public static Graph<String,String> leeEuleriano(String file) {
		return GraphsReader.newGraph(file, 
				s->s[0], 
				s->s[2], 
				Graphs2::simpleGraph);
	}
	
	public static DirectedAcyclicGraph<String,String> leeAsignaturas(String file) {
		return GraphsReader.newGraph(file, 
				s->s[0], 
				s->s[2], 
				()->new DirectedAcyclicGraph<String,String>(String.class));
	}
	
	public static Graph<String,DefaultEdge> leeRelaciones(String file) {
		return GraphsReader.newGraph(file, 
				s->s[0], 
				s->new DefaultEdge(), 
				Graphs2::simpleGraph);
	}
	
	
	public static <V,E> Set<GraphPath<V,E>> distanciasMinimas(Graph<V,E> g){
		List<V> vertices = g.vertexSet().stream().toList();
		FloydWarshallShortestPaths<V,E>  a =new FloydWarshallShortestPaths<>(g);
		Set<GraphPath<V,E>> s = new HashSet<>();
		for(int i = 0; i<vertices.size();i++) {
			for(int j=i+1; j<vertices.size();j++) {
				s.add(a.getPath(vertices.get(i),vertices.get(j)));
			}
		}
		return s;
	}
	
	public static <V,E> SpanningTree<E> recubrimientoMinimo(Graph<V,E> g){
		KruskalMinimumSpanningTree<V,E> a = new KruskalMinimumSpanningTree<>(g);
		return a.getSpanningTree();
	}
	
	public static <V,E> VertexCover<V> recubrimientoVertices(Graph<V,E> g){
		VertexCoverAlgorithm<V> a = new RecursiveExactVCImpl<>(g);
		return a.getVertexCover();
	}
	
	public static <V,E> Boolean esEuleriano(Graph<V,E> g){
		return g.vertexSet().stream().allMatch(v->g.degreeOf(v)%2==0);
	}
	
	public static <V,E> GraphPath<V,E> caminoEuleriano(Graph<V,E> g){
		EulerianCycleAlgorithm<V,E> a = new HierholzerEulerianCycle<V,E>();
		return a.getEulerianCycle(g);
	}
	
	public static Set<String> asignaturasPosibles(DirectedAcyclicGraph<String,String> g, List<String> as){
		Set<String> r = new HashSet<>();
		as.stream()
			.flatMap(a->g.outgoingEdgesOf(a).stream().map(t->g.getEdgeTarget(t)))
			.filter(a->!as.contains(a))
			.forEach(y->r.add(y));
		return r;
	}
	
	public static <V, E> Map<V, Integer> coloreado(Graph<V, E> g) {
		VertexColoringAlgorithm<V> vca = new GreedyColoring<V, E>(g);
		Coloring<V> vc = vca.getColoring();
		return vc.getColors();
	}
	
	public static void test1() {
		Graph<Ciudad,Carretera> grafo = leeFichero("data/andalucia.txt");
		Set<GraphPath<Ciudad,Carretera>> paths = distanciasMinimas(grafo);
		paths.stream().forEach(p->String2.toConsole("%.2f, %s == %s == %s",p.getWeight(),p.getStartVertex(),p.getEndVertex(),p));
	}
	
	public static void test2() {
		Graph<Ciudad,Carretera> grafo = leeFichero("data/andalucia.txt");
		SpanningTree<Carretera> r = recubrimientoMinimo(grafo);
		String2.toConsole("%.2f",r.getWeight());
		r.getEdges().stream().forEach(e->String2.toConsole("%s",e));
	}
	
	public static void test3() {
		Graph<Ciudad,Carretera> grafo = leeFichero("data/andalucia.txt");
		VertexCover<Ciudad> vc = recubrimientoVertices(grafo);
		vc.stream().forEach(e->String2.toConsole("%s",e));
	}
	
	public static void test4() {
		Graph<String,String> g = leeEuleriano("ficheros/euleriano2.txt");
		String2.toConsole("%s",g);
		g.vertexSet().stream().forEach(v->String2.toConsole("%s,%s",v,g.degreeOf(v)));
		GraphPath<String,String> gp = caminoEuleriano(g);
		String2.toConsole("%s",gp.getEdgeList());
	}
	
	public static void test5() {
		DirectedAcyclicGraph<String,String> g = leeAsignaturas("ficheros/asignaturas.txt");
		String2.toConsole("%s",g);
		Set<String> r = asignaturasPosibles(g, List.of("a7","a8"));
		String2.toConsole("%s",r);
	}
	
	public static void test6() {
		Graph<String,DefaultEdge> g = leeRelaciones("ficheros/relaciones.txt");
		String2.toConsole("%s",g);
		Map<String, Integer> m = coloreado(g);
		String2.toConsole("%s",m);
		Map<Integer, List<String>> r = m.keySet().stream().collect(Collectors.groupingBy(s->m.get(s)));
		String2.toConsole("%s",r);
	}
	
	public static <V,E> List<V> verticesPost(SimpleDirectedGraph<V,E> graph, V v){
		return Stream2.ofIterator(new BreadthFirstIterator<>(graph,v)).toList();
	}
	
	public static <V,E> List<V> verticesPre(SimpleDirectedGraph<V,E> graph, V v){
		SimpleDirectedGraph<V,E> graph2 = Graphs2.inversedDirectedGraph(graph);	
		return Stream2.ofIterator(new BreadthFirstIterator<>(graph2,v)).toList();
	}
	
	public static void test7() {
		SimpleDirectedGraph<Integer,SimpleEdge<Integer>> graph = GraphsReader.newGraph("data/topologico.txt", 
				format->Integer.parseInt(format[0]), 
				format->SimpleEdge.of(Integer.parseInt(format[0]),Integer.parseInt(format[1])), 
				Graphs2::simpleDirectedGraph
				);
		CycleDetector<Integer, SimpleEdge<Integer>> cd = new CycleDetector<>(graph);
		Boolean c = cd.detectCycles();
		String2.toConsole("%s", c);
		SimpleDirectedGraph<Integer,SimpleEdge<Integer>> graph2 = Graphs2.inversedDirectedGraph(graph);	
		GraphColors.toDot(graph,
				"ficheros/topologico.gv",
				x->x.toString(),
				e->""
				);
		GraphColors.toDot(graph2,
				"ficheros/topologico2.gv",
				x->x.toString(),
				e->""
				);
		List<Integer> verticesPost = verticesPost(graph,7);
		List<Integer> verticesPre = verticesPre(graph,10);
		String2.toConsole("%s",verticesPost);
		String2.toConsole("%s",verticesPre);
	}

	public static void main(String[] args) {
		Locale.setDefault(Locale.of("en", "US"));
		test7();
	}

}
