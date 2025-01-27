package us.lsi.alg.tsp;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import org.jgrapht.Graph;
import us.lsi.grafos.datos.Carretera;
import us.lsi.grafos.datos.Ciudad;

public class TestCamino {
	
	public static Ciudad ciudad(Graph<Ciudad,Carretera> graph, String nombre) {
		return graph.vertexSet().stream().filter(c->c.nombre().equals(nombre)).findFirst().get();
	}

	public static void main(String[] args) {
		Locale.setDefault(Locale.of("en", "US"));
		Graph<Ciudad,Carretera> graph1 = AuxiliaryTsp.leeGraph("ficheros/andalucia/andalucia.txt");
		Graph<Ciudad,Carretera> graph2 = AuxiliaryTsp.completeGraph(graph1);

		
		TravelVertex.graph = graph2;
		List<Ciudad> camino = Arrays.asList(ciudad(graph2,"Sevilla"),
				ciudad(graph1,"Huelva"),
				ciudad(graph1,"Cordoba"),
				ciudad(graph1,"Almeria"),
				ciudad(graph1,"Malaga"),
				ciudad(graph1,"Algeciras"),
				ciudad(graph1,"Granada"),
				ciudad(graph2,"Sevilla"));
		Integer n = camino.size();
		
		Collections.shuffle(camino.subList(1,n-1));
		System.out.println(camino.subList(1,n-1));
		
		TravelVertex v1 = TravelVertex.of(camino);
		
//		EGraph<TravelVertex,TravelEdge> graph = 
//				EGraph.virtual(v1,null).vertexWeight(v->v.weight()).build();
		
		Double error = 0.1;
		Double r;
		do {
			System.out.println(v1);
			Double w = v1.weight();
			v1 = v1.neighbor(v1.actions().get(0));
			Double nw = v1.weight();
			r = Math.abs(w-nw);			
		}while(r > error);
	}

}
