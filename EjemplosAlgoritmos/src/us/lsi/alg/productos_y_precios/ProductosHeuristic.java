package us.lsi.alg.productos_y_precios;


import java.util.List;
import java.util.Locale;
import java.util.function.Predicate;
import java.util.stream.IntStream;

import org.jgrapht.GraphPath;

import us.lsi.graphs.alg.GreedyOnGraph;
import us.lsi.graphs.virtual.EGraph;


public class ProductosHeuristic {

	public static Double heuristic(ProductosVertex v1, Predicate<ProductosVertex> goal, ProductosVertex v2) {
		return heuristic2(v1, DatosProductos.NUM_PRODUCTOS);
	}

	public static Double heuristic(ProductosVertex vertice, Integer lastIndex) {
		return 0.;
	}
	
	
	public static GraphPath<ProductosVertex, ProductosEdge> graphPathVoraz(ProductosVertex start,
			Predicate<ProductosVertex> goal) {
		EGraph<ProductosVertex, ProductosEdge> graph = 
				EGraph.virtual(start)
				.edgeWeight(x -> x.weight())
				.heuristic(ProductosHeuristic::heuristic)
				.build();
		GreedyOnGraph<ProductosVertex, ProductosEdge> rr = 
				GreedyOnGraph.of(graph, ProductosVertex::greedyEdge);
		GraphPath<ProductosVertex, ProductosEdge> p = rr.path();
		return p;
	}
	
	public static Double heuristic2(ProductosVertex vertice, Integer lastIndex) {		
		if (vertice.funcionalidades_restantes().isEmpty()) {
			return 0.;
		} else {
			return IntStream.range(vertice.indice(),lastIndex)
					.mapToDouble(i->DatosProductos.producto(i).precio())
					.min()
					.getAsDouble();
		}
	}


	

	public static void main(String[] args) {
		Locale.setDefault(Locale.of("en", "US"));

		for (Integer id_fichero = 1; id_fichero < 4; id_fichero++) {

			DatosProductos.iniDatos("ficheros/productos" + id_fichero + ".txt");
			System.out.println("\n\n>\tResultados para el test " + id_fichero + "\n");
			System.out.println("#### Voraz ####");
//			DatosProductos.toConsole();
		

			ProductosVertex start = ProductosVertex.initial();
//			Predicate<ProductosVertex> finalVertex = v -> ProductosVertex.goal(v);
			GraphPath<ProductosVertex,ProductosEdge> path = 
					ProductosHeuristic.graphPathVoraz(start,v->v.goal());
			List<Integer> la = path.getEdgeList().stream().map(e->e.action()).toList();
			System.out.println(SolucionProductos.of(la).precioTotal());
			System.out.println(heuristic2(start,DatosProductos.NUM_PRODUCTOS));
		}
	}

}

