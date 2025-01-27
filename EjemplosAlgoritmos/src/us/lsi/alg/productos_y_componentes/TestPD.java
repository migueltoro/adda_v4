package us.lsi.alg.productos_y_componentes;

import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

import org.jgrapht.GraphPath;


import us.lsi.colors.GraphColors;
import us.lsi.colors.GraphColors.Color;
import us.lsi.graphs.alg.PDR;
import us.lsi.graphs.alg.GreedyOnGraph;
import us.lsi.graphs.virtual.EGraph;
import us.lsi.graphs.virtual.EGraph.Type;
import us.lsi.path.EGraphPath.PathType;

public class TestPD {

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
			
			GraphPath<VertexProductos, EdgeProductos> path = GreedyOnGraph.of(graph,v->v.greedyEdge()).path();

			System.out.println("\n\n#### PI-7 Ej3 Algoritmo PD ####");

			// Algoritmo PD
			PDR<VertexProductos, EdgeProductos,?> pdr = 
					PDR.of(graph, null,path.getWeight(), path, true);
			
			pdr.optimalPath = path;
			Optional<GraphPath<VertexProductos, EdgeProductos>> gp = pdr.search();
			SolucionProductos s_pdr;
			if (gp.isPresent()) {
				List<Integer> gp_pdr = gp.get().getEdgeList().stream().map(x -> x.action())
						.collect(Collectors.toList());
				s_pdr = SolucionProductos.of(gp_pdr);
				System.out.println(s_pdr);
				
			} else {
				System.out.println("Solucion no encontrada!!!");
				//s_pdr = sv;
			}
			
			GraphColors.toDot(pdr.outGraph, "ficheros/productosPDGraph.gv", 
					v -> v.toGraph(),
					e -> e.action().toString(), 
					v -> GraphColors.colorIf(Color.red, v.goal()),
					e -> GraphColors.colorIf(Color.red, gp.get().getEdgeList().contains(e)));

		}
	}

}

