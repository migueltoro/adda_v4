package us.lsi.graphs.examples;

import org.jgrapht.Graph;
import org.jgrapht.graph.SimpleWeightedGraph;

import us.lsi.grafos.datos.Carretera;
import us.lsi.grafos.datos.Ciudad;
import us.lsi.graphs.GraphsReader;
import us.lsi.graphs.Graphs2;

public class TransformGraphs {

	public static void main(String[] args) {
		SimpleWeightedGraph<Ciudad, Carretera> graph = GraphsReader.newGraph("ficheros/andalucia.txt", 
				Ciudad::ofFormat,
				Carretera::ofFormat, 
				Graphs2::simpleWeightedGraph,
				Carretera::km);
		System.out.println(graph.vertexSet());
		System.out.println(graph.edgeSet());
		Graph<Ciudad, Carretera> graph2 = Graphs2.toDirectedWeightedGraph(graph,(Carretera x)->Carretera.of(x.km(),x.nombre()));
		System.out.println("_______________");
		System.out.println(graph2.vertexSet());
		System.out.println(graph2.edgeSet());
	}

}
