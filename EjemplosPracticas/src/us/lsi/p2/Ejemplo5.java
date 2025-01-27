package us.lsi.p2;

import java.util.Map;

import org.jgrapht.Graph;
import org.jgrapht.alg.color.GreedyColoring;
import org.jgrapht.alg.interfaces.VertexColoringAlgorithm.Coloring;
import org.jgrapht.graph.DefaultEdge;

import us.lsi.colors.GraphColors;
import us.lsi.colors.GraphColors.Style;

public class Ejemplo5 {

	
	public static void todosLosApartados (Graph<String, DefaultEdge> gf, String file) {
		//var alg = new LargestDegreeFirstColoring<>(gf)
		var alg = new GreedyColoring<>(gf); 
		Coloring<String> solucion = alg.getColoring();
		System.out.println("Mesas necesarias: "+solucion.getNumberColors());
		
		System.out.println("Composicion de las mesas");
		var mesas = solucion.getColorClasses();
		for(int i=0; i<mesas.size(); i++) {
			System.out.println("Mesa numero "+(i+1)+": "+mesas.get(i));
		}
		
		Map<String, Integer> map = solucion.getColors(); 
		
		GraphColors.toDot(gf, "ficheros_generados/p2/ejemplo5/" + file + ".gv", 
				v->v.toString(), 
				e->"",
				v -> GraphColors.color(map.get(v)),
				e -> GraphColors.style(Style.solid));
		
		System.out.println(file + "C.gv generado en " + "ficheros_generados/p2/ejemplo5");
		
		 
	}
			
	

}
