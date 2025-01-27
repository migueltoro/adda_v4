package us.lsi.p2;

import java.util.List;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.connectivity.ConnectivityInspector;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.SimpleWeightedGraph;

import us.lsi.colors.GraphColors;
import us.lsi.colors.GraphColors.Color;
import us.lsi.colors.GraphColors.Style;
import us.lsi.grafos.datos.Carretera;
import us.lsi.grafos.datos.Ciudad;

public class Ejemplo4 {
	public static Ciudad ciudad(Graph<Ciudad,Carretera> graph, String nombre) {
		return graph.vertexSet().stream().filter(c->c.nombre().equals(nombre)).findFirst().get();
	}
	
	// A) A partir del grafo original, dados dos vértices v1 y v2 de dicho grafo obtener 
	// el camino mínimo para ir de v1 a v2. Muestre el grafo original configurando su apariencia 
	// de forma que se resalte el camino mínimo para ir de v1 a v2
	
	public static void apartadoA(SimpleWeightedGraph<Ciudad, Carretera> gf, String file, String c1, String c2) {
		
		var alg = new DijkstraShortestPath<>(gf);
		Ciudad origen = ciudad(gf,c1);
		Ciudad destino = ciudad(gf,c2);
		GraphPath<Ciudad, Carretera> gp = alg.getPath(origen, destino);
		
		GraphColors.toDot(gf,"ficheros_generados/p2/ejemplo4/" + file + "A.gv",
				x->x.nombre(), x->x.nombre(),
				v->GraphColors.styleIf(Style.bold, gp.getVertexList().contains(v)),
				e->GraphColors.styleIf(Style.bold, gp.getEdgeList().contains(e)));
		
		System.out.println(file + "A.gv generado en " + "ficheros_generados/p2/ejemplo4");
		
	}
	
	
	// B) Calcule las componentes conexas del grafo original. Muestre el grafo original 
	// configurando su apariencia de forma que se coloree cada componente conexa de un color diferente
	
	public static void apartadoB(SimpleWeightedGraph<Ciudad, Carretera> gf, String file) {	
	
		var alg = new ConnectivityInspector<>(gf);
		List<Set<Ciudad>> ls = alg.connectedSets();
		System.out.println("Hay " + ls.size() + " componentes conexas." );
		
		GraphColors.toDot(gf,"ficheros_generados/p2/ejemplo4/" + file + "B.gv",
				x->x.nombre(), x->x.nombre(),
				v->GraphColors.color(asignaColor(v,ls,alg)),
				e->GraphColors.color(asignaColor(gf.getEdgeSource(e), ls, alg)));
		
		System.out.println(file + "B.gv generado en " + "ficheros_generados/ejemplo4");
	}
	
	
	private static Color asignaColor(Ciudad v, List<Set<Ciudad>> ls, 
			ConnectivityInspector<Ciudad, Carretera> alg) {
		Color[] vc = Color.values();
		Set<Ciudad> s = alg.connectedSetOf(v);
		return vc[ls.indexOf(s)];
	}
	


}
