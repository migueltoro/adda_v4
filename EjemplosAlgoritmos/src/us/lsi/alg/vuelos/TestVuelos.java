package us.lsi.alg.vuelos;

import static us.lsi.colors.GraphColors.all;
import static us.lsi.colors.GraphColors.colorIf;
import static us.lsi.colors.GraphColors.styleIf;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.jgrapht.GraphPath;
import org.jgrapht.graph.DirectedMultigraph;
import org.jgrapht.graph.DirectedWeightedMultigraph;
import org.jgrapht.graph.SimpleDirectedGraph;


import us.lsi.colors.GraphColors;
import us.lsi.colors.GraphColors.Color;
import us.lsi.colors.GraphColors.Style;
import us.lsi.common.LocalDateTime2;
import us.lsi.common.Pair;
import us.lsi.graphs.Graphs2;
import us.lsi.graphs.GraphsReader;
import us.lsi.graphs.alg.AStar;
import us.lsi.graphs.alg.BT;
import us.lsi.graphs.alg.PDR;
import us.lsi.graphs.virtual.EGraph;
import us.lsi.graphs.virtual.EGraph.Type;
import us.lsi.path.EGraphPath.PathType;

public class TestVuelos {
	
	public static DirectedWeightedMultigraph<String,Vuelo> leeGrafo(String fichero) {
		DirectedWeightedMultigraph<String,Vuelo> graph = GraphsReader.newGraph(fichero, 
				s->s[0], 
			    Vuelo::ofFormat,
				Graphs2::directedWeightedMultigraph, 
				v->v.getDuracion().doubleValue());
		return graph;
	}
	
	public static SimpleDirectedGraph<Pair<String, Integer>, Vuelo> toGraph(
			DirectedMultigraph<String, Vuelo> graphIn, String startVertex) {

		SimpleDirectedGraph<Pair<String, Integer>, Vuelo> graphOut = 
				new SimpleDirectedGraph<Pair<String, Integer>, Vuelo>(null, null, false);

		graphOut.addVertex(Pair.of(startVertex, -1));

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
	
	public static List<Vuelo> vuelos(List<Vuelo> vuelos) {
		List<Vuelo> r = new ArrayList<>();
		r.add(vuelos.get(0));
		for (int i=1;i<vuelos.size();i++) {
			Vuelo v=vuelos.get(i);
			Vuelo va=vuelos.get(i-1);
			r.add(Vuelo.of(v.getId(),v.getFrom(),v.getTo(),LocalDateTime2.next(va.getHoraDeLlegada(),v.getHoraDeSalida()),v.getDuracion()));
		}
		return r;
	}
	
	public static Double getVertexPassWeight(Pair<String,Integer> p, Vuelo edgeIn, Vuelo EdgeOut) {
		return Vuelo.getVertexPassWeight(p.first(), edgeIn, EdgeOut);
	}
	

	public static void main(String[] args) {
		Locale.setDefault(Locale.of("en", "US"));
		Vuelo.fecha = LocalDate.now();
		DirectedWeightedMultigraph<String,Vuelo> graph = leeGrafo("ficheros/vuelos2.txt");
		System.out.println(graph);
		System.out.println("__________________");
		SimpleDirectedGraph<Pair<String,Integer>,Vuelo> gs = toGraph(graph,"Sevilla");
		System.out.println(gs);
		String end = "Malaga";
		
		EGraph<Pair<String,Integer>, Vuelo> gv = EGraph.ofGraph(gs,Pair.of("Sevilla",-1),
						v->v.first().equals(end),PathType.Sum,Type.Min)
				.edgeWeight(v->v.getDuracion().doubleValue())
				.vertexPassWeight((v,e1,e2)-> getVertexPassWeight(v,e1,e2))
				.heuristic((p1,v,p2)->0.)
				.build();
		
		System.out.println("AStar");
		AStar<Pair<String,Integer>,Vuelo,GraphPath<Pair<String,Integer>,Vuelo>> ms = AStar.of(gv,p->p,null,null);
		
		GraphPath<Pair<String,Integer>,Vuelo> path = ms.search().orElse(null);
		System.out.printf("Aeropuertos = %s\n",path.getVertexList());
		System.out.printf("Recorrido = %s\n",vuelos(path.getEdgeList()));
		System.out.printf("Tiempo de Recorrido = %.2f\n",path.getWeight());
		System.out.println("__________________");
		System.out.println("Backtracking");
		BT<Pair<String,Integer>,Vuelo,GraphPath<Pair<String,Integer>,Vuelo>> bt = BT.of(gv);
		
		GraphPath<Pair<String,Integer>,Vuelo> btp = bt.search().orElse(null);
		System.out.printf("Aeropuertos = %s\n",btp.getVertexList());
		System.out.printf("Recorrido = %s\n",vuelos(btp.getEdgeList()));
		System.out.printf("Tiempo de Recorrido = %.2f\n",btp.getWeight());
		System.out.println("__________________");
		System.out.println("PDR");
		PDR<Pair<String,Integer>,Vuelo,GraphPath<String,Vuelo>> pd = PDR.of(gv,null,null,null,true);
		
		GraphPath<Pair<String,Integer>,Vuelo> pdp = pd.search().orElse(null);
		System.out.printf("Aeropuertos = %s\n",pdp.getVertexList());
		System.out.printf("Recorrido = %s\n",vuelos(pdp.getEdgeList()));
		System.out.printf("Tiempo de Recorrido = %.2f\n",pdp.getWeight());
		
		Predicate<Pair<String,Integer>> pv = v->pd.optimalPath().get().getVertexList().contains(v);
		Predicate<Vuelo> pe= e->pd.optimalPath().get().getEdgeList().contains(e);
		
		GraphColors.toDot(pd.outGraph,"ficheros/vuelosPDRGraph.gv",
				v->String.format("%s",v),
				e->e.getHoraDeSalida().toLocalTime().toString(),
				v->all(colorIf(Color.red,pv.test(v)),styleIf(Style.bold,pv.test(v))),
				e->all(colorIf(Color.red,pe.test(e)),styleIf(Style.bold,pe.test(e)))
				);
		
		
	}

}
