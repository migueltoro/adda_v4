package us.lsi.alg.productos_y_precios;


import java.util.Locale;
import java.util.Optional;
import java.util.function.Predicate;

import org.jgrapht.GraphPath;

import us.lsi.colors.GraphColors;
import us.lsi.colors.GraphColors.Color;
import us.lsi.graphs.alg.BT;
import us.lsi.graphs.virtual.EGraph;

public class TestBT {

	public static void main(String[] args) {
		Locale.setDefault(Locale.of("en", "US"));

		for (Integer id_fichero = 1; id_fichero < 4; id_fichero++) {

			DatosProductos.iniDatos("ficheros/productos_y_precios/productos" + id_fichero + ".txt");
			System.out.println("\n\n>\tResultados para el test " + id_fichero + "\n");

			ProductosVertex start = ProductosVertex.initial();
			Predicate<ProductosVertex> goal = v->v.goal();
			
			EGraph<ProductosVertex, ProductosEdge> graph = 
					EGraph.virtual(start)
					.edgeWeight(x -> x.weight())
					.heuristic(ProductosHeuristic::heuristic)
					.build();

			GraphPath<ProductosVertex, ProductosEdge> path = 
					ProductosHeuristic.graphPathVoraz(start,goal);
//			List<Integer> la = path.getEdgeList().stream().map(e->e.action()).toList();
			
			System.out.println("\n\n#### Algoritmo BT ####");

			// Algoritmo BT
			BT<ProductosVertex, ProductosEdge,SolucionProductos> bta = 
					BT.of(graph,SolucionProductos::of,path.getWeight(),path,true);
			
//			GraphPath<ProductosVertex, ProductosEdge> gp = bta.optimalPath!=null?bta.optimalPath:path;
			
			Optional<GraphPath<ProductosVertex, ProductosEdge>> gp = bta.search();
			
			System.out.println(SolucionProductos.of(gp.get()));
					
			GraphColors.toDot(bta.outGraph(),"ficheros/productosBTGraph.gv",
					v->v.toGraph(),
					e->e.action().toString(),
					v->GraphColors.colorIf(Color.red,goal.test(v)),
					e->GraphColors.colorIf(Color.red,gp.get().getEdgeList().contains(e))
					);

		}
	}

}

