package us.lsi.alg.productos_y_precios;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import org.jgrapht.GraphPath;
import us.lsi.graphs.alg.AStar;
import us.lsi.graphs.virtual.EGraph;

public class TestAS {

	public static void main(String[] args) {
		Locale.setDefault(Locale.of("en", "US"));

		for (Integer id_fichero = 1; id_fichero < 4; id_fichero++) {

			DatosProductos.iniDatos("ficheros/productos_y_precios/productos" + id_fichero + ".txt");
			System.out.println("\n\n>\tResultados para el test " + id_fichero + "\n");
			System.out.println("#### Algoritmo A* ####");

			ProductosVertex start = ProductosVertex.initial();
			
			EGraph<ProductosVertex, ProductosEdge> graph = 
					EGraph.virtual(start)
					.edgeWeight(x -> x.weight())
					.heuristic(ProductosHeuristic::heuristic)
					.build();

			// Algoritmo A*
			AStar<ProductosVertex, ProductosEdge,?> aStar = AStar.ofGreedy(
					graph);
			
			GraphPath<ProductosVertex, ProductosEdge> gp = aStar.search().get();

			List<Integer> gp_as = gp.getEdgeList().stream().map(x -> x.action()).collect(Collectors.toList()); // getEdgeList();

			SolucionProductos s_as = SolucionProductos.of(gp_as);
			System.out.println(s_as);


//			GraphColors.toDot(aStar.outGraph, "ficheros/productosAStarGraph.gv", v -> v.toGraph(),
//					e -> e.action().toString(), v -> GraphColors.colorIf(Color.red, goal.test(v)),
//					e -> GraphColors.colorIf(Color.red, gp.getEdgeList().contains(e)));

		}

	}

}

