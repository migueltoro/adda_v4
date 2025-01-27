package us.lsi.p2;

import java.util.Set;
import java.util.function.Predicate;

import org.jgrapht.Graph;
import org.jgrapht.alg.connectivity.ConnectivityInspector;
import org.jgrapht.alg.interfaces.SpanningTreeAlgorithm.SpanningTree;
import org.jgrapht.alg.spanning.KruskalMinimumSpanningTree;
import org.jgrapht.alg.vertexcover.GreedyVCImpl;

import us.lsi.colors.GraphColors;
import us.lsi.colors.GraphColors.Color;
import us.lsi.graphs.views.SubGraphView;


public class Ejemplo6 {
	
	// Apartado a)
	public static Set<String> apartadoA (Graph<String,Pasillo> gf) {
		GreedyVCImpl<String,Pasillo> algA = new GreedyVCImpl<>(gf);
		Set<String> camaras = algA.getVertexCover();		
		return camaras;
	}
		
	
	// Apartado b) y c)
	public static void apartadoB (Graph<String,Pasillo> gf, String file) {
		
		//Apartado b)
		Set<String> camaras = apartadoA(gf);
		Predicate<String> pc = c -> camaras.contains(c);
		Graph<String,Pasillo> sgf = SubGraphView.of(gf, pc);
		
		ConnectivityInspector<String,Pasillo> algB1 = new ConnectivityInspector<>(sgf);
		Integer n = algB1.connectedSets().size();
		System.out.println("Numero de equipos necesarios: "+n);
		
		KruskalMinimumSpanningTree<String,Pasillo> algB2 = new KruskalMinimumSpanningTree<>(sgf);
		SpanningTree<Pasillo> tree = algB2.getSpanningTree();
		System.out.println(String.format("Metros de cable necesarios: %.1f", tree.getWeight()));
		
		// Apartado c)
		GraphColors.toDot(gf, "ficheros_generados/p2/ejemplo6/" + file + ".gv", c->"", v->"", 
				v -> GraphColors.colorIf(Color.red, Color.blank, camaras.contains(v)),
				e -> GraphColors.colorIf(Color.green, Color.blank, tree.getEdges().contains(e)));
	}
	
}
