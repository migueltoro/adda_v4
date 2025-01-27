package us.lsi.pli.vertexcover;

import java.io.IOException;
import java.util.Set;
import java.util.stream.Collectors;

import org.jgrapht.graph.SimpleWeightedGraph;

import us.lsi.colors.GraphColors;
import us.lsi.colors.GraphColors.Color;
import us.lsi.colors.GraphColors.Style;
import us.lsi.grafos.datos.Carretera;
import us.lsi.grafos.datos.Ciudad;
import us.lsi.graphs.GraphData;
import us.lsi.graphs.Graphs2;
import us.lsi.graphs.GraphsReader;
import us.lsi.graphs.views.IntegerVertexGraphView;
import us.lsi.gurobi.GurobiLp;
import us.lsi.gurobi.GurobiSolution;
import us.lsi.solve.AuxGrammar;

public class VertexEdgeCoverPLI {
	
	public static SimpleWeightedGraph<Ciudad,Carretera> graph;
	public static IntegerVertexGraphView<Ciudad, Carretera> g;
	
	public static void leeDatos(String file) {
		graph =  GraphsReader.newGraph(file,
						Ciudad::ofFormat, 
						Carretera::ofFormat,
						Graphs2::simpleWeightedGraph,
						Carretera::km);
		g = IntegerVertexGraphView.of(graph);
		GraphData.n = g.vertexSet().size();
		GraphData.vertexWeight = g.vertexSet().stream()
				.collect(Collectors.toMap(x->x,x->1./g.vertex(x).habitantes()));
		GraphData.graph = g;
	}
	
	public static Ciudad ciudad(String s) {
		String[] partes = s.split("_");
		Ciudad c = g.getVertex(Integer.parseInt(partes[1]));
		return c;
	}
	
	public static void vertex_cover_model() throws IOException {
		VertexEdgeCoverPLI.leeDatos("data/andalucia.txt");
		System.out.println(GraphData.graph);
		AuxGrammar.generate(GraphData.class,"modelos/vertex_cover.lsi","ficheros/vertex_cover.lp");
		GurobiSolution solution = GurobiLp.gurobi("ficheros/vertex_cover.lp");
		System.out.println(solution.toString((s,d)->d>0.));
		Set<Ciudad> ciudades = solution.values.keySet().stream()
				.filter(s->solution.values.get(s)>0)
				.map(s->ciudad(s))
				.collect(Collectors.toSet());
		GraphColors.toDot(graph,
				"ficheros/vertex_cover.gv", 
				v->v.nombre(), 
				e->e.nombre(), 
				v->GraphColors.colorIf(Color.red,Color.black,ciudades.contains(v)),
				e->GraphColors.style(Style.solid));
	}
	
	public static Carretera carretera(String s) {
		String[] partes = s.split("_");
		Ciudad c1 = g.getVertex(Integer.parseInt(partes[1]));
		Ciudad c2 = g.getVertex(Integer.parseInt(partes[2]));
		Carretera c = graph.getEdge(c1,c2);
		return c;
	}
	
	public static void edge_cover_model() throws IOException {
		VertexEdgeCoverPLI.leeDatos("data/andalucia.txt");
		System.out.println(GraphData.graph);
		AuxGrammar.generate(GraphData.class,"modelos/edge_cover.lsi","ficheros/edge_cover.lp");
		GurobiSolution solution = GurobiLp.gurobi("ficheros/edge_cover.lp");
		System.out.println(solution.toString((s,d)->d>0.));
		Set<Carretera> carreteras = solution.values.keySet().stream()
				.filter(s->solution.values.get(s)>0)
				.map(s->carretera(s))
				.collect(Collectors.toSet());
		GraphColors.toDot(graph,
				"ficheros/edge_cover.gv", 
				v->v.nombre(), 
				e->e.nombre(), 
				v->GraphColors.color(Color.black),
				e->GraphColors.colorIf(Color.red,Color.black,carreteras.contains(e)));
		
	}
	
	public static void main(String[] args) throws IOException {
		vertex_cover_model();
	}

}
