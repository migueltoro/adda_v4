package us.lsi.p2;


import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;

import us.lsi.common.Files2;
import us.lsi.graphs.Graphs2;

public class TestEjemplo5 {
	
	public static void main(String[] args) {
		testsEjemplo5("Comensales");
	}
	
	public static void testsEjemplo5(String file) {
		
		
		Graph<String, DefaultEdge> g = 
			Graphs2.simpleGraph(String::new, DefaultEdge::new, false);
			
			Files2.streamFromFile("ficheros/p2/"+file+".txt").forEach(linea -> {
				String[] v = linea.split(",");
				g.addVertex(v[0]);
				g.addVertex(v[1]);
				g.addEdge(v[0], v[1]);
			});
			
			
		Ejemplo5.todosLosApartados(g, file);
	}
	



}
