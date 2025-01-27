package us.lsi.flowgraph.examples;

import java.io.IOException;
import java.util.Locale;

import org.jgrapht.Graph;
import org.jgrapht.graph.SimpleWeightedGraph;

import us.lsi.colors.GraphColors;
import us.lsi.grafos.datos.Carretera;
import us.lsi.grafos.datos.Ciudad;
import us.lsi.graphs.GraphData;
import us.lsi.graphs.Graphs2;
import us.lsi.graphs.GraphsReader;
import us.lsi.graphs.SimpleEdge;
import us.lsi.graphs.views.IntegerVertexGraphView;
import us.lsi.gurobi.GurobiLp;
import us.lsi.gurobi.GurobiSolution;
import us.lsi.solve.AuxGrammar;

public class Tsp {
	
	public static Ciudad ciudad(Graph<Ciudad,Carretera> graph, String nombre) {
		return graph.vertexSet().stream().filter(c->c.nombre().equals(nombre)).findFirst().get();
	}
	
	public static void tsp() throws IOException {	
		
		SimpleWeightedGraph<Ciudad,Carretera> graph =  
				GraphsReader.newGraph("ficheros/andalucia.txt",
						Ciudad::ofFormat, 
						Carretera::ofFormat,
						Graphs2::simpleWeightedGraph,
						Carretera::km);	
		Graph<Integer,SimpleEdge<Integer>> graph3 = IntegerVertexGraphView.of(graph);
		GraphData.graph = graph3;
		GraphData.n = graph3.vertexSet().size();
		AuxGrammar.generate(GraphData.class,"models/tsp.lsi","ficheros/tsp.lp");
		GurobiSolution solution = GurobiLp.gurobi("ficheros/tsp.lp");
		Locale.setDefault(Locale.of("en", "US"));
		System.out.println(solution.toString((s,d)->d>0.));		
	}
	
	public static void shortestPath() throws IOException {	
		Locale.setDefault(Locale.of("en", "US"));
		SimpleWeightedGraph<Ciudad,Carretera> graph =  
				GraphsReader.newGraph("ficheros/andalucia.txt",
						Ciudad::ofFormat, 
						Carretera::ofFormat,
						Graphs2::simpleWeightedGraph,
						Carretera::km);
		System.out.println(graph);	
		IntegerVertexGraphView<Ciudad,Carretera> graph3 = IntegerVertexGraphView.of(graph);
		GraphColors.toDot(graph3,"ficheros/andaluciaIndex.gv",v->v.toString(),e->e.toString());
		System.out.println(graph3);	
		System.out.println("============   "+graph3.containsEdge(4, 6));
		System.out.println("============   "+graph3.containsEdge(6, 4));
		GraphData.graph = graph3;
		GraphData.n = graph3.vertexSet().size();
		Ciudad origin = ciudad(graph,"Sevilla");
		Ciudad target = ciudad(graph,"Almeria");
		GraphData.origin= graph3.index(origin);
		GraphData.target= graph3.index(target);
		AuxGrammar.generate(GraphData.class,"models/shortest_path.lsi","ficheros/shortest_path.lp");
		GurobiSolution solution = GurobiLp.gurobi("ficheros/shortest_path.lp");
		System.out.println(solution.toString((s,d)->s.startsWith("x_0") || d > 0.));		
	}


	public static void main(String[] args) throws IOException {
		tsp();
	}

}
