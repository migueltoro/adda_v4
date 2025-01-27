package us.lsi.graphs.examples;

import java.util.List;
import java.util.Locale;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.alg.connectivity.ConnectivityInspector;

import us.lsi.common.String2;
import us.lsi.grafos.datos.Carretera;
import us.lsi.grafos.datos.Ciudad;
import us.lsi.graphs.Graphs2;
import us.lsi.graphs.GraphsReader;

public class Componentes {
	
	public static Ciudad ciudad(Graph<Ciudad,Carretera> graph, String nombre) {
		return graph.vertexSet().stream().filter(c->c.nombre().equals(nombre)).findFirst().get();
	}
	
	
	public static void main(String[] args) {
		Locale.setDefault(Locale.of("en", "US"));

		Graph<Ciudad,Carretera> graph =  
				GraphsReader.newGraph("ficheros/andalucia.txt",
						Ciudad::ofFormat, 
						Carretera::ofFormat,
						Graphs2::simpleWeightedGraph,
						Carretera::km);		
		
		
		Ciudad start = ciudad(graph,"Sevilla");
		Ciudad end = ciudad(graph,"Almeria");
		
		ConnectivityInspector<Ciudad, Carretera> a = new ConnectivityInspector<>(graph);
		Boolean r1 = a.isConnected();
		Boolean r2 = a.pathExists(start,end);
		List<Set<Ciudad>> r3 = a.connectedSets();
		Set<Ciudad> r4 = a.connectedSetOf(start);
		String2.toConsole("%s,%s",r1,r2);
		String2.toConsole("%s",r3);
		String2.toConsole("%s",r4);
		
	}

}
