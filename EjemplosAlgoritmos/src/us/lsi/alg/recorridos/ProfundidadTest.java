package us.lsi.alg.recorridos;



import org.jgrapht.Graph;
import org.jgrapht.graph.SimpleWeightedGraph;

import us.lsi.grafos.datos.Carretera;
import us.lsi.grafos.datos.Ciudad;
import us.lsi.graphs.Graphs2;
import us.lsi.graphs.GraphsReader;
import us.lsi.graphs.alg.DephtPostSearch;
import us.lsi.graphs.alg.DephtSearch;
import us.lsi.graphs.virtual.EGraph;

public class ProfundidadTest {
	
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
		
		graph.addVertex(Ciudad.of("Londres",2000000));
		
		System.out.println(graph);
		
		EGraph<Ciudad,Carretera> g = EGraph.ofGraph(graph,ciudad(graph,"Sevilla"),null).build();
		
		
		DephtSearch<Ciudad, Carretera> ra = DephtSearch.of(g,ciudad(graph,"Sevilla"));
		
//		GraphPath<Ciudad, Carretera> carreteras = ra.pathTo(Ciudad.ofName("Almeria")).get();
		
		ra.stream().forEach(c->System.out.println(c));;
		System.out.println("_________");
		DephtPostSearch<Ciudad, Carretera> ra2 = DephtPostSearch.of(g,ciudad(graph,"Sevilla"));
		ra2.stream().forEach(c->System.out.println(c));;

	}
}
