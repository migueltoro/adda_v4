package us.lsi.alg.colorgraphs;

import java.util.List;

import org.jgrapht.Graph;
import org.jgrapht.graph.SimpleWeightedGraph;

import us.lsi.common.List2;
import us.lsi.grafos.datos.Carretera;
import us.lsi.grafos.datos.Ciudad;
import us.lsi.graphs.Graphs2;
import us.lsi.graphs.GraphsReader;
import us.lsi.graphs.SimpleEdge;
import us.lsi.graphs.views.IntegerVertexGraphView;

public class DatosColor {
	
	public static Integer m; // n�mero maximo de colores, obtenido previamente mediante un camino voraz
	public static Integer n; // n�mero de v�rtices
	public static Graph<Integer,SimpleEdge<Integer>> graph;
	public static IntegerVertexGraphView<Ciudad, Carretera> g2;
	public static List<Integer> colors;
	
	public static SimpleWeightedGraph<Ciudad, Carretera> leeGrafo(String fichero) {
		SimpleWeightedGraph<Ciudad, Carretera> graph = GraphsReader.newGraph(fichero, 
				Ciudad::ofFormat, 
				Carretera::ofFormat,
				Graphs2::simpleWeightedGraph, 
				Carretera::km);
		return graph;
	}
	
	public static void data(Integer m, String file) {
		
		SimpleWeightedGraph<Ciudad, Carretera> g0 = leeGrafo(file);		
//		System.out.println(g0);		
		g2 = IntegerVertexGraphView.of(g0);	
//		Integer n = g2.vertexSet().size();	
		
		DatosColor.m = m;; 
		DatosColor.n = g2.vertexSet().size(); 
		DatosColor.graph = g2;
		DatosColor.colors = List2.rangeList(0, DatosColor.m);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
