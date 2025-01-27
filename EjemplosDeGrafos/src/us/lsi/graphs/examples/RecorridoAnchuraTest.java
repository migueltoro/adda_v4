package us.lsi.graphs.examples;

import java.util.Locale;
import java.util.Set;

import org.jgrapht.Graph;

import us.lsi.colors.GraphColors;
import us.lsi.grafos.datos.Carretera;
import us.lsi.grafos.datos.Ciudad;
import us.lsi.graphs.Graphs2;
import us.lsi.graphs.GraphsReader;
import us.lsi.graphs.alg.BreadthSearch;
import us.lsi.graphs.virtual.EGraph;
import us.lsi.streams.Stream2;

public class RecorridoAnchuraTest {
	
	public static Ciudad ciudad(Graph<Ciudad,Carretera> graph, String nombre) {
		return graph.vertexSet().stream().filter(c->c.nombre().equals(nombre)).findFirst().get();
	}
	
	public static void main(String[] args) {
		Locale.setDefault(Locale.of("en", "US"));
		Graph<Ciudad,Carretera> graph =  
				GraphsReader.newGraph("data/andalucia.txt",
						Ciudad::ofFormat, 
						Carretera::ofFormat,
						Graphs2::simpleWeightedGraph,
						Carretera::km);
		
		graph.addVertex(Ciudad.of("Londres",2000000));
		
		EGraph<Ciudad,Carretera> g = 
				EGraph.ofGraph(graph,ciudad(graph,"Sevilla"),null).edgeWeight(e->e.km()).build();
		
		BreadthSearch<Ciudad, Carretera> ra = BreadthSearch.of(g,ciudad(graph,"Sevilla"));
		Stream2.findLast(ra.stream());
		Set<Carretera> carreteras = ra.edges();
		
		GraphColors.toDot(graph,"ficheros/andalucia.gv",x->x.nombre(),x->x.nombre()+"--"+x.km());
		
		
		System.out.println(carreteras);
		

	}


}
