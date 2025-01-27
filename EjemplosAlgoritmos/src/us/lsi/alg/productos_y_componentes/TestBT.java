package us.lsi.alg.productos_y_componentes;

import java.util.Locale;
import java.util.Optional;

import org.jgrapht.GraphPath;

import us.lsi.colors.GraphColors;
import us.lsi.colors.GraphColors.Color;
import us.lsi.graphs.alg.BT;
import us.lsi.graphs.alg.GreedyOnGraph;
import us.lsi.graphs.virtual.EGraph;
import us.lsi.graphs.virtual.EGraph.Type;
import us.lsi.path.EGraphPath.PathType;

public class TestBT {

	public static void main(String[] args) {

		// Set up
		Locale.setDefault(Locale.of("en", "US"));

		for (Integer id_fichero = 0; id_fichero < 2; id_fichero++) {

			DatosProductos.iniDatos("ficheros/productos_y_componentes/productoscomp"+ id_fichero +".txt");
			System.out.println("\n\n>\tResultados para el test " + id_fichero + "\n");
//			DatosSubconjuntos.toConsole();
			

			// V�rtices clave

			VertexProductos start = VertexProductos.initial();

			// Grafo
			
			EGraph<VertexProductos, EdgeProductos> graph = 
					EGraph.virtual(start)
					.pathType(PathType.Sum)
					.type(Type.Max)
					.edgeWeight(x -> x.weight())
					.heuristic(ProductosHeuristic::heuristic)
					.build();
			
			GraphPath<VertexProductos, EdgeProductos> gp = GreedyOnGraph.of(graph,v->v.greedyEdge()).path();

			System.out.println("\n\n#### PI-7 Ej3 Algoritmo BT ####");

			// Algoritmo BT
			BT<VertexProductos, EdgeProductos, SolucionProductos> bta = 
				BT.of(graph, 
					SolucionProductos::of, 
					gp.getWeight(),gp,true);

			Optional<GraphPath<VertexProductos, EdgeProductos>> gps = bta.search();
			
			System.out.println(SolucionProductos.of(gps.get()));

			GraphColors.toDot(bta.outGraph(), "ficheros/productosPDGraph.gv", 
					v -> v.toGraph(),
					e -> e.action().toString(), 
					v -> GraphColors.colorIf(Color.red,v.goal()),
					e -> GraphColors.colorIf(Color.red, bta.optimalPath.getEdgeList().contains(e)));

			
		}
	}

}

