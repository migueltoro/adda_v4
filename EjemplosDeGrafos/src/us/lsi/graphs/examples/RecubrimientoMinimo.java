package us.lsi.graphs.examples;

import java.util.Map;

import org.jgrapht.graph.SimpleWeightedGraph;

import us.lsi.colors.GraphColors;
import us.lsi.colors.GraphColors.Color;
import us.lsi.colors.GraphColors.Style;
import us.lsi.common.Map2;
import us.lsi.grafos.datos.Carretera;
import us.lsi.grafos.datos.Ciudad;
import us.lsi.graphs.Graphs2;
import us.lsi.graphs.GraphsReader;


import org.jgrapht.alg.interfaces.SpanningTreeAlgorithm;
import org.jgrapht.alg.interfaces.SpanningTreeAlgorithm.SpanningTree;
import org.jgrapht.alg.interfaces.VertexCoverAlgorithm;
import org.jgrapht.alg.interfaces.VertexCoverAlgorithm.VertexCover;
import org.jgrapht.alg.spanning.KruskalMinimumSpanningTree;
import org.jgrapht.alg.vertexcover.RecursiveExactVCImpl;

/**
 * Calcula varios recubrimiento de un grafo
 * 
 * @author Miguel Toro
 *
 */
public class RecubrimientoMinimo {
	
	public static void main(String[] args) {
		
		SimpleWeightedGraph<Ciudad,Carretera> graph =  
				GraphsReader.newGraph("ficheros/andalucia.txt",
						Ciudad::ofFormat, 
						Carretera::ofFormat,
						Graphs2::simpleWeightedGraph,
						Carretera::km);
		
		SpanningTreeAlgorithm<Carretera> ast = new KruskalMinimumSpanningTree<>(graph);
		SpanningTree<Carretera> r = ast.getSpanningTree();
		System.out.println(r.getEdges());	
		
		Map<Ciudad,Double> habitantes = Map2.of(x->1./x.habitantes());
		VertexCoverAlgorithm<Ciudad> vc = new RecursiveExactVCImpl<>(graph,habitantes);
		VertexCover<Ciudad> r2 = vc.getVertexCover();
		System.out.println(r2.toString());
		
		
		GraphColors.<Ciudad,Carretera>toDot(graph,
				"ficheros/andaluciaSpanningTree.gv",
				x->String.format("%s",x.nombre()),
				x->String.format("%.2f",x.km()),
				v->GraphColors.color(Color.black),
				e->GraphColors.styleIf(Style.bold,r.getEdges().contains(e)));
		
		GraphColors.<Ciudad,Carretera>toDot(graph,"ficheros/andaluciaVertexCover.gv",
				x->String.format("%s",x.nombre()),
				x->String.format("%.2f",x.km()),
				v->GraphColors.colorIf(Color.green,Color.blue,r2.contains(v)),
				e->GraphColors.style(Style.solid));
		
	}

	
	
}
