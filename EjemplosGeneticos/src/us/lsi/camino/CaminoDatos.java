package us.lsi.camino;

import java.util.function.Predicate;
import java.util.stream.IntStream;

import org.jgrapht.graph.SimpleWeightedGraph;


import us.lsi.grafos.datos.Carretera;
import us.lsi.grafos.datos.Ciudad;
import us.lsi.graphs.Graphs2;
import us.lsi.graphs.GraphsReader;
import us.lsi.graphs.views.IntegerVertexGraphView;

public class CaminoDatos {
	
	public static IntegerVertexGraphView<Ciudad,Carretera> graph;
	public static Integer n;
	
	public static Predicate<Ciudad> pv;
	public static Predicate<Double> pe;
	public static Integer or;
	public static Integer de;
	public static Boolean sh = false;
	
	public static void iniGrafo(String fichero, Predicate<Ciudad> pv, Predicate<Double> pe, 
			String origen, String destino) {	
		
		SimpleWeightedGraph<Ciudad,Carretera> g =  
				GraphsReader.newGraph(fichero,
						Ciudad::ofFormat, 
						Carretera::ofFormat,
						Graphs2::simpleWeightedGraph,
						Carretera::km);
		
		CaminoDatos.pv = pv;
		CaminoDatos.pe = pe;		
		graph = IntegerVertexGraphView.of(g);
		n = graph.vertexSet().size();
		
		CaminoDatos.or = IntStream.range(0, n).boxed()
				.filter(i->graph.vertex(i).nombre().equals(origen))
				.findFirst().get();
		CaminoDatos.de = IntStream.range(0, n).boxed()
				.filter(i->graph.vertex(i).nombre().equals(destino))
				.findFirst().get();
	}


}
