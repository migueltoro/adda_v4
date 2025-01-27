package us.lsi.graphs.examples;

import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.graph.SimpleWeightedGraph;

import us.lsi.colors.GraphColors;
import us.lsi.colors.GraphColors.Color;
import us.lsi.colors.GraphColors.Style;
import us.lsi.common.Set2;
import us.lsi.grafos.datos.Carretera;
import us.lsi.grafos.datos.Ciudad;
import us.lsi.graphs.Graphs2;
import us.lsi.graphs.GraphsReader;
import us.lsi.graphs.tour.ShortestTour;

public class Tours {
	
	public static Ciudad ciudad(Graph<Ciudad,Carretera> graph, String nombre) {
		return graph.vertexSet().stream().filter(c->c.nombre().equals(nombre)).findFirst().get();
	}

	public static void main(String[] args) {
		SimpleWeightedGraph<Ciudad, Carretera> graph = GraphsReader.newGraph("ficheros/andalucia.txt", 
				Ciudad::ofFormat,
				Carretera::ofFormat, 
				Graphs2::simpleWeightedGraph,
				Carretera::km);
		
		ShortestTour<Ciudad, Carretera, SimpleWeightedGraph<Ciudad, Carretera>> a = 
				ShortestTour.of(
						graph, 
						Graphs2::simpleWeightedGraph, 
						()->Carretera.of(1000.));
		Set<Ciudad> vertices = Set2.of(ciudad(graph,"Jaen"));
		
		GraphPath<Ciudad, Carretera> r = a.getTour(ciudad(graph,"Sevilla"),ciudad(graph,"Almeria"),vertices);
		System.out.println(r.getVertexList());
		System.out.println(r.getEdgeList());
		
		GraphColors.<Ciudad,Carretera>toDot(graph,"ficheros/andaluciaSpanningTree.gv",
				x->String.format("%s",x.nombre()),
				x->String.format("%.sf",x.km()),
				v->GraphColors.color(Color.black),
				e->GraphColors.styleIf(Style.bold,r.getEdgeList().contains(e)));
		
	}

}
