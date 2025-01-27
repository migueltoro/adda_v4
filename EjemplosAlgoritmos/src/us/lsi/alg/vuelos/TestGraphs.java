package us.lsi.alg.vuelos;

import java.time.LocalDate;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

import org.jgrapht.graph.DirectedWeightedMultigraph;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;

import us.lsi.colors.GraphColors;
import us.lsi.common.Pair;
import us.lsi.common.String2;
import us.lsi.graphs.Graphs2;
import us.lsi.graphs.GraphsReader;

public class TestGraphs {
	
	public static DirectedWeightedMultigraph<String,Vuelo> leeGrafo(String fichero) {
		DirectedWeightedMultigraph<String,Vuelo> graph = GraphsReader.newGraph(fichero, 
				s->s[0], 
			    Vuelo::ofFormat,
				Graphs2::directedWeightedMultigraph, 
				v->v.getDuracion().doubleValue());
		return graph;
	}
	
	public static SimpleDirectedWeightedGraph<Pair<String, Integer>, Vuelo> toGraph(
			DirectedWeightedMultigraph<String, Vuelo> graphIn, String startVertex) {

		SimpleDirectedWeightedGraph<Pair<String, Integer>, Vuelo> graphOut = 
				new SimpleDirectedWeightedGraph<Pair<String, Integer>, Vuelo>(null, null);

		graphOut.addVertex(Pair.of(startVertex, -1));
		System.out.println(graphOut.edgeSet());
		for (Vuelo v : graphIn.edgeSet()) {
			graphOut.addVertex(Pair.of(v.getTo(), v.getId()));			
		}
		
		Map<String,List<Pair<String, Integer>>> m = graphOut.vertexSet().stream()
				.collect(Collectors.groupingBy(p->p.first()));
		
		for (Vuelo v : graphIn.edgeSet()) {
			for (Pair<String, Integer> p : m.get(v.getFrom())) {
				graphOut.addEdge(p, Pair.of(v.getTo(), v.getId()), v.copy());
			}
		}
		return graphOut;
	}

	
	public static void main(String[] args) {
		Locale.setDefault(Locale.of("en", "US"));
		Vuelo.fecha = LocalDate.now();
		DirectedWeightedMultigraph<String,Vuelo> graph = leeGrafo("ficheros/vuelos2.txt");
		System.out.println(graph);
		System.out.println(graph.edgeSet().size());
		System.out.println("__________________");
		SimpleDirectedWeightedGraph<Pair<String,Integer>,Vuelo> gs = toGraph(graph,"Sevilla");
		System.out.println(gs);
		System.out.println(gs.edgeSet().size());
//		String end = "Malaga";
		String v0 = "Barcelona";
		System.out.println("__________________");
		System.out.println(gs.vertexSet().stream().filter(p->p.first().equals(v0)).collect(Collectors.toSet()));
		System.out.println("__________________");
		System.out.println(String2.format(graph.outgoingEdgesOf(v0),"Multigrafo"));
		System.out.println(String2.format(gs.outgoingEdgesOf(Pair.of(v0,10)),"Grafo"));
		System.out.println(String2.format(gs.edgesOf(Pair.of(v0,10)),"Grafo"));
		System.out.println("__________________");
		System.out.println(Pair.of(v0,10).equals(Pair.of(v0,10)));
		System.out.println(gs.containsVertex(Pair.of(v0,10)));
		GraphColors.toDot(gs, "ficheros/vuelosSimpleGraph.gv", 
				v -> v.toString(), 
				e -> String.format("%s", e.getId()));
		GraphColors.toDot(graph, "ficheros/vuelosMultiGraph.gv", 
				v -> v.toString(), 
				e -> String.format("%s", e.getId()));
	}

}
