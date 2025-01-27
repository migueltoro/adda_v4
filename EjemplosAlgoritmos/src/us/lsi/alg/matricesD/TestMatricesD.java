package us.lsi.alg.matricesD;

import java.util.Locale;

import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleDirectedGraph;

import us.lsi.alg.matrices.DatosMatrices;
import us.lsi.common.String2;
import us.lsi.common.Union;
import us.lsi.hypergraphsD.Data;
import us.lsi.hypergraphsD.GraphTreeD;


public class TestMatricesD {

		public static void main(String[] args) {
			Locale.setDefault(Locale.of("en", "US"));
			DatosMatrices.leeFichero("ficheros/matrices/matrices.txt",Data.DpType.Min);
			Data.type = Data.DpType.Min;
			MatrixVertexD p = MatrixVertexD.initial();
			String s = p.solution();
			String2.toConsole(s);
			GraphTreeD<MatrixVertexD, MatrixEdgeD, Integer> t = p.graphTree();
			String2.toConsole(t.toString());
			String2.toConsole(t.children().toString());
			String2.toConsole(t.allVertices().toString());
			String2.toConsole(t.allEdges().toString());
			
			SimpleDirectedGraph<Union<MatrixVertexD, MatrixEdgeD>, DefaultEdge> g = 
					Data.graph(Data.<MatrixVertexD, MatrixEdgeD>of().memoryAll);
			
			Data.toDotHypergraph(g, "ficheros/hiperMatrix.gv", p);
			Data.toDotAndOr(g, "ficheros/andOrMatrix.gv", p);
		}

}
