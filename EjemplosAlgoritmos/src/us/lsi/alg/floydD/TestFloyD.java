package us.lsi.alg.floydD;

import java.util.Locale;


import org.jgrapht.GraphPath;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleDirectedGraph;
import us.lsi.alg.floyd.DatosFloyd;
import us.lsi.common.String2;
import us.lsi.common.Union;
import us.lsi.graphs.SimpleEdge;
import us.lsi.hypergraphsD.Data;
import us.lsi.hypergraphsD.GraphTreeD;

public class TestFloyD {
	
	

	public static void main(String[] args) {
		Locale.setDefault(Locale.of("en", "US"));
		
		Locale.setDefault(Locale.of("en", "US"));
		
		DatosFloyd.datos();
		
		System.out.println(DatosFloyd.graph);
		System.out.println(DatosFloyd.graphI);
		
		Integer origen = DatosFloyd.graphI.index(v->v.nombre().equals("Sevilla"));
		Integer destino = DatosFloyd.graphI.index(v->v.nombre().equals("Almeria"));
		
		
		FloydVertexD.graph = DatosFloyd.graphI;
		FloydVertexD.n = DatosFloyd.graphI.vertexSet().size();
		FloydVertexD p = FloydVertexD.initial(origen,destino);
		
//		GraphPath<Integer,SimpleEdge<Integer>> gp = p.solution();
		String2.toConsole("21 = "+FloydVertexD.n.toString());
//		String2.toConsole(p.weight().toString());
		String2.toConsole("2 = "+Data.of().memory.toString());
		Data.type = Data.DpType.Min;
		GraphPath<Integer, SimpleEdge<Integer>> gp = p.solution();
		GraphTreeD<FloydVertexD, FloydEdgeD, Boolean> t = p.graphTree();
		
		String2.toConsole("3 = "+gp.getVertexList().stream()
				.map(i->FloydVertexD.graph.vertex(i)).toList().toString());
		String2.toConsole("4 = "+t.toString());
		SimpleDirectedGraph<Union<FloydVertexD,FloydEdgeD>, DefaultEdge> g = 
				Data.graph(Data.<FloydVertexD,FloydEdgeD>of().memoryAll);
		
		Data.toDotHypergraph(g, "ficheros/hiperGraph.gv", p);
		Data.toDotAndOr(g, "ficheros/andOrGraph.gv", p);
	}

}
