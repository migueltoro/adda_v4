package us.lsi.graphs.examples;

import java.util.List;
import java.util.stream.Stream;

import org.jgrapht.Graph;
import org.jgrapht.traverse.BreadthFirstIterator;
import org.jgrapht.traverse.DepthFirstIterator;

import us.lsi.colors.GraphColors;
import us.lsi.common.String2;
import us.lsi.grafos.datos.Carretera;
import us.lsi.grafos.datos.Ciudad;
import us.lsi.graphs.Graphs2;
import us.lsi.graphs.GraphsReader;
import us.lsi.streams.Stream2;

/**
 * Calcula varios recorridos sobre un grafo
 * 
 * @author Miguel Toro
 *
 */
public class Recorridos {
	
	public static Ciudad ciudad(Graph<Ciudad,Carretera> graph, String nombre) {
		return graph.vertexSet().stream().filter(c->c.nombre().equals(nombre)).findFirst().get();
	}

	public static void main(String[] args) {

		Graph<Ciudad,Carretera> graph =  
				GraphsReader.newGraph("ficheros/andalucia.txt",
						Ciudad::ofFormat, 
						Carretera::ofFormat,
						Graphs2::simpleWeightedGraph,
						Carretera::km);
		
		Ciudad c = ciudad(graph,"Sevilla");
		Ciudad c2 = ciudad(graph,"Almeria");
		
		DepthFirstIterator<Ciudad, Carretera> rp = new DepthFirstIterator<>(graph,c);
		Stream<Ciudad> sp = Stream2.ofIterator(rp);
		List<Ciudad> lp = sp.toList();
		
		String2.toConsole("%s", lp);
		
		GraphColors.<Ciudad,Carretera>toDot(graph,"ficheros/andalucia.gv",x->x.nombre(),x->x.nombre()+"--"+x.km());
		
		BreadthFirstIterator<Ciudad, Carretera> ra = new BreadthFirstIterator<>(graph,c);
		Stream<Ciudad> sa = Stream2.ofIterator(ra);
		Integer pa = ra.getDepth(c2);
		Ciudad pva = ra.getParent(c2);
		Carretera sta = ra.getSpanningTreeEdge(c2);
		List<Ciudad> la = sa.toList();
		
		String2.toConsole("%s", la);
		
		GraphColors.<Ciudad,Carretera>toDot(graph,"ficheros/andalucia.gv",x->x.nombre(),x->x.nombre()+"--"+x.km());
		
	}

}
