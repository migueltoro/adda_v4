package us.lsi.p2;

import java.util.function.Predicate;

import org.jgrapht.Graph;

import us.lsi.colors.GraphColors;
import us.lsi.colors.GraphColors.Color;
import us.lsi.grafos.datos.Carretera;
import us.lsi.grafos.datos.Ciudad;
import us.lsi.graphs.views.SubGraphView;

public class Ejemplo3 {

	// Crea vista del grafo
	public static void crearVista(String file, Graph<Ciudad,Carretera> g, Predicate<Ciudad> pv, 
			Predicate<Carretera> pa, String nombreVista) {
		
		Graph<Ciudad, Carretera> vista = SubGraphView.of(g, pv, pa);

		GraphColors.toDot(vista,"ficheros_generados/p2/ejemplo3/" + file + nombreVista + ".gv",
				x->x.nombre(), x->x.nombre(),
				v->GraphColors.colorIf(Color.red, vista.edgesOf(v).size() > 1),
				e->GraphColors.color(Color.black));
		
		System.out.println(file + nombreVista + ".gv generado en " + "ficheros_generados/p2/ejemplo3");
	}
	

}
