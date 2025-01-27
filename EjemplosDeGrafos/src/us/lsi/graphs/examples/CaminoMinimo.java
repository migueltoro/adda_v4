package us.lsi.graphs.examples;


import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.alg.interfaces.ShortestPathAlgorithm;
import org.jgrapht.graph.SimpleWeightedGraph;

import us.lsi.colors.GraphColors.Style;
import us.lsi.colors.GraphColors.Color;
import us.lsi.colors.GraphColors;
import us.lsi.grafos.datos.Carretera;
import us.lsi.grafos.datos.Ciudad;
import us.lsi.graphs.Graphs2;
import us.lsi.graphs.GraphsReader;

/**
 * Resuelve un problema de camino m�nimo
 * 
 * @author Miguel Toro
 *
 */

public class CaminoMinimo {
	
	public static Ciudad ciudad(Graph<Ciudad,Carretera> graph, String nombre) {
		return graph.vertexSet().stream().filter(c->c.nombre().equals(nombre)).findFirst().get();
	}

	public static void main(String[] args) {
		SimpleWeightedGraph<Ciudad,Carretera> graph =  
				GraphsReader.newGraph("ficheros/andalucia.txt",
						Ciudad::ofFormat, 
						Carretera::ofFormat,
						Graphs2::simpleWeightedGraph,
						Carretera::km);
		System.out.println(graph);
		ShortestPathAlgorithm<Ciudad,Carretera> a = new DijkstraShortestPath<Ciudad,Carretera>(graph);
		Ciudad from = ciudad(graph,"Sevilla");
		Ciudad to = ciudad(graph,"Almeria");
		GraphPath<Ciudad,Carretera> gp =  a.getPath(from,to);
		System.out.println(gp);
		System.out.println(gp.getVertexList());	
		System.out.println(gp.getWeight());
		
		SimpleWeightedGraph<Ciudad,Carretera> graph2 = 
				Graphs2.subGraph(graph, 
						null, 
						e->gp.getEdgeList().contains(e),
						Graphs2::simpleWeightedGraph);
		
		GraphColors.toDot(graph,"ficheros/caminoMinimoAndalucia1.gv",x->x.nombre(),x->x.nombre()+"--"+x.km());
		
		GraphColors.toDot(graph2,"ficheros/caminoMinimoAndalucia2.gv",
				x->x.nombre(),
				x->x.nombre()+"--"+x.km(),
				v->GraphColors.color(Color.black),
				e->GraphColors.style(Style.bold));
		
		
	}

}
