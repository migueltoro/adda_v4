package us.lsi.alg.productos_y_componentes;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import org.jgrapht.GraphPath;

import us.lsi.colors.GraphColors;
import us.lsi.colors.GraphColors.Color;
import us.lsi.graphs.alg.AStar;
import us.lsi.graphs.virtual.EGraph;
import us.lsi.graphs.virtual.EGraph.Type;
import us.lsi.path.EGraphPath.PathType;

public class TestAStart {

	public static void main(String[] args) {

		// Set up
		Locale.setDefault(Locale.of("en", "US"));
		
		for (Integer i = 0; i < 2; i++) {

			DatosProductos.iniDatos("ficheros/productos_y_componentes/productoscomp"+ i +".txt");
			System.out.println("\n\n>\tResultados para el test " + i + "\n");

			// V�rtices clave

			VertexProductos start = VertexProductos.initial();

			// Grafo

			System.out.println("#### Algoritmo A* ####");

			// Algoritmo A*
			
			EGraph<VertexProductos, EdgeProductos> graph = 
					EGraph.virtual(start)
					.pathType(PathType.Sum)
					.type(Type.Max)
					.edgeWeight(x -> x.weight())
					.heuristic(ProductosHeuristic::heuristic)
					.build();
			
			
			AStar<VertexProductos, EdgeProductos, ?> aStar = AStar.ofGreedy(graph);
			
			GraphPath<VertexProductos, EdgeProductos> gp = aStar.search().get();
			
			List<Integer> gp_as = gp.getEdgeList().stream().map(x -> x.action())
					.collect(Collectors.toList()); // getEdgeList();
				
			SolucionProductos s_as = SolucionProductos.of(gp);
			
			
			System.out.println(s_as);
			System.out.println(gp_as);

			GraphColors.toDot(aStar.outGraph(), "ficheros/productosAStarGraph.gv", 
					v -> v.toGraph(),
					e -> e.action().toString(), 
					v -> GraphColors.colorIf(Color.red, v.goal()),
					e -> GraphColors.colorIf(Color.red, gp.getEdgeList().contains(e)));
		}
	}

}
