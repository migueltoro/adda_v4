package us.lsi.alg.colorgraphs;

import java.util.Locale;

import us.lsi.graphs.alg.GreedyOnGraph;
import us.lsi.graphs.virtual.EGraph;
import us.lsi.graphs.virtual.EGraph.Type;
import us.lsi.path.EGraphPath.PathType;

public class TestGreedyColor {


	
	public static void main(String[] args) {
		
		Locale.setDefault(Locale.of("en", "US"));
		
		DatosColor.data(9,"ficheros/andalucia.txt");
			
		ColorVertex e1 = ColorVertex.first();
		
		EGraph<ColorVertex, ColorEdge> graph = 
				EGraph.virtual(e1)
				.pathType(PathType.Last)
				.type(Type.Min)
				.vertexWeight(v->v.nc().doubleValue())
				.build();
		
		Integer m2 = GreedyOnGraph.of(graph,v->v.greedyEdge()).last().get().nc();
		System.out.println("Voraz = "+m2);
	}

}
