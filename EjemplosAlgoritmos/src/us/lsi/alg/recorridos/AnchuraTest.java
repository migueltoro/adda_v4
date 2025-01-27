package us.lsi.alg.recorridos;

import java.util.Optional;

import org.jgrapht.Graph;
import org.jgrapht.graph.SimpleWeightedGraph;

import us.lsi.grafos.datos.Carretera;
import us.lsi.grafos.datos.Ciudad;
import us.lsi.graphs.Graphs2;
import us.lsi.graphs.GraphsReader;
import us.lsi.graphs.alg.BreadthSearch;
import us.lsi.graphs.virtual.EGraph;

public class AnchuraTest {
	
	public static Ciudad ciudad(Graph<Ciudad,Carretera> graph, String nombre) {
		return graph.vertexSet().stream().filter(c->c.nombre().equals(nombre)).findFirst().get();
	}

	public static void main(String[] args) {
		
		SimpleWeightedGraph<Ciudad,Carretera> graph =  
				GraphsReader.newGraph("ficheros/andalucia/andalucia.txt",
						Ciudad::ofFormat, 
						Carretera::ofFormat,
						Graphs2::simpleWeightedGraph,
						Carretera::km);
		
		graph.addVertex(Ciudad.of("Londres",20000000));
		
		System.out.println(graph);
		System.out.println(graph.edgeSet());
		
		EGraph<Ciudad,Carretera> g = EGraph.ofGraph(graph,ciudad(graph,"Sevilla"),null).build();
		
		BreadthSearch<Ciudad, Carretera> ra = BreadthSearch.of(g,ciudad(graph,"Sevilla"));
		
//		GraphPath<Ciudad, Carretera> carreteras = ra.pathTo(Ciudad.ofName("Almeria")).get();
		
		Optional<Ciudad> ciudad = ra.stream().filter(v->v.equals(ciudad(graph,"Londres"))).findFirst();
		
		System.out.println(ciudad.isPresent());

	}
}
