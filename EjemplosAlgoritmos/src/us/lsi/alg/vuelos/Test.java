package us.lsi.alg.vuelos;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import org.jgrapht.graph.DirectedWeightedMultigraph;

import us.lsi.graphs.Graphs2;
import us.lsi.graphs.GraphsReader;
import us.lsi.graphs.virtual.EGraph;
import us.lsi.graphs.virtual.EGraph.Type;
import us.lsi.path.EGraphPath.PathType;

public class Test {
	
	public static DirectedWeightedMultigraph<String,Vuelo> leeGrafo(String fichero) {
		DirectedWeightedMultigraph<String,Vuelo> graph = GraphsReader.newGraph(fichero, 
				s->s[0], 
			    Vuelo::ofFormat,
				Graphs2::directedWeightedMultigraph, 
				v->v.getDuracion().doubleValue());
		return graph;
	}
	
	public static List<Vuelo> vuelos(List<Vuelo> vuelos) {
		List<Vuelo> r = new ArrayList<>();
		Vuelo v0 = vuelos.get(0);
		LocalDateTime day = v0.getHoraDeLlegada();
		for (Vuelo v : vuelos) {
			LocalDateTime hs = v.getHoraDeSalida();
			LocalDateTime hl = v.getHoraDeLlegada();
			if (hs.isBefore(day)) {
				hs = hs.plusDays(1L);
				hl = hl.plusDays(1L);
			}
			r.add(Vuelo.of(v.getFrom(),v.getTo(),hs,v.getDuracion()));
		}
		return r;
	}

	public static void main(String[] args) {
		Locale.setDefault(Locale.of("en", "US"));
		Vuelo.fecha = LocalDate.now();
		DirectedWeightedMultigraph<String,Vuelo> graph = leeGrafo("ficheros/vuelos2.txt");
		System.out.println(graph);
		String end = "Malaga";
		EGraph<String, Vuelo> g = EGraph.ofGraph(graph,"Sevilla",v->v.equals(end),PathType.Sum,Type.Min)
				.edgeWeight(v->v.getDuracion().doubleValue())
				.vertexPassWeight((v,e1,e2)-> Vuelo.getVertexPassWeight(v,e1,e2))
				.heuristic((p1,v,p2)->0.)
				.build();
		System.out.println(g.estimatedWeightToEnd(g.startVertex(), 0.));
		
		
		System.out.println(graph.outgoingEdgesOf("Sevilla")
				.stream().map(e->e.toString()).collect(Collectors.joining("\n")));
	}

}
