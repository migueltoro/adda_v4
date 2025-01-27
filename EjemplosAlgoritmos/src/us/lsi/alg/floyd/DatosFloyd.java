package us.lsi.alg.floyd;

import org.jgrapht.Graph;
import org.jgrapht.graph.SimpleWeightedGraph;

import us.lsi.grafos.datos.Carretera;
import us.lsi.grafos.datos.Ciudad;
import us.lsi.graphs.Graphs2;
import us.lsi.graphs.GraphsReader;
import us.lsi.graphs.views.IntegerVertexGraphView;

public class DatosFloyd {
	
	public static Ciudad ciudad(Graph<Ciudad,Carretera> graph, String nombre) {
		return graph.vertexSet().stream().filter(c->c.nombre().equals(nombre)).findFirst().get();
	}
	
	public static SimpleWeightedGraph<Ciudad, Carretera> leeDatos(String fichero) {
		SimpleWeightedGraph<Ciudad, Carretera> graph = GraphsReader.newGraph(fichero, 
				Ciudad::ofFormat, 
				Carretera::ofFormat,
				Graphs2::simpleWeightedGraph, 
				Carretera::km);
		return graph;
	}
	
	public static SimpleWeightedGraph<Ciudad, Carretera> graph;
	public static IntegerVertexGraphView<Ciudad,Carretera> graphI;
	public static Integer n;
	
	public static void datos() {
		DatosFloyd.graph = leeDatos("ficheros/andalucia/andalucia.txt");
		DatosFloyd.graphI = IntegerVertexGraphView.of(graph);
		DatosFloyd.n = DatosFloyd.graphI.vertexSet().size();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
