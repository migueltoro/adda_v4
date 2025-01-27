package us.lsi.graphs.manual.tests;

import us.lsi.grafos.datos.Carretera;
import us.lsi.grafos.datos.Ciudad;
import us.lsi.graphs.manual.VirtualGraph;
import us.lsi.graphs.manual.recorridos.AStar;

public class TestAStar {

	public static void main(String[] args) {
		Mapa m = Mapa.parse("ficheros/ciudades.txt", 
				"ficheros/carreteras.txt", 
				VirtualGraph.GraphType.UNDIRECTED,
				VirtualGraph.TraverseType.FORWARD);
		System.out.println(m);
		System.out.println(m.successors(m.ciudad("VNFLN")));
		Ciudad c1 = m.ciudad("Sevilla");
		Ciudad c2 = m.ciudad("Malaga");
		AStar<Ciudad,Carretera> roa = AStar.of(m,x->0.,x->x.equals(c2));
		roa.traverse(c1);
		System.out.println("AStar = "+roa.path());
		System.out.println(roa.pathFromOrigin(c2));
		System.out.println(roa.pathWeight(c2));	

	}

}
