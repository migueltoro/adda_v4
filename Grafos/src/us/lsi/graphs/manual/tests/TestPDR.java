package us.lsi.graphs.manual.tests;

import us.lsi.grafos.datos.Carretera;
import us.lsi.grafos.datos.Ciudad;
import us.lsi.graphs.manual.GraphPath;
import us.lsi.graphs.manual.VirtualGraph;
import us.lsi.graphs.manual.recorridos.DynamicProgramming;

public class TestPDR {
	
	public static void main(String[] args) {
		Mapa m = Mapa.parse("ficheros/ciudades.txt", 
				"ficheros/carreteras.txt", 
				VirtualGraph.GraphType.UNDIRECTED,
				VirtualGraph.TraverseType.FORWARD);
		System.out.println(m);
		Ciudad c1 = m.ciudad("Sevilla");
		Ciudad c2 = m.ciudad("Malaga");
		GraphPath<Ciudad, Carretera> pbt = 
				DynamicProgramming.dynamicProgrammingR(c1,x->x.equals(c2),m,(x,y)->0.);
		System.out.println("Weight = "+pbt.weight());
		System.out.println("MinPath "+pbt.vertices());
	}

}
