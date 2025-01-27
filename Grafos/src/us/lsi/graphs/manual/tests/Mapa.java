package us.lsi.graphs.manual.tests;


import us.lsi.graphs.manual.SimpleGraph;
import us.lsi.graphs.manual.VirtualGraph;
import us.lsi.grafos.datos.Ciudad;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import us.lsi.common.Files2;

import us.lsi.grafos.datos.Carretera;

public class Mapa extends SimpleGraph<Ciudad,Carretera>{
	
	public static Mapa of(VirtualGraph.GraphType graphType,VirtualGraph.TraverseType traverseType) {
        return new Mapa(graphType,traverseType);
    }

	private Map<String,Ciudad> ciudadesId;
	
	private Mapa(VirtualGraph.GraphType graphType,VirtualGraph.TraverseType traverseType) {
		super(graphType,x->x.km(),traverseType);
        this.ciudadesId = new HashMap<>();
	}
	
	public static Mapa parse(String f1,String f2,VirtualGraph.GraphType graphType,VirtualGraph.TraverseType traverseType) {
        Mapa m = Mapa.of(graphType,traverseType);
        Set<Ciudad> ciudades = new HashSet<>();
        for(String linea:Files2.linesFromFile(f1)) {
        	String[] partes = linea.split(",");
        	Ciudad c = Ciudad.of(partes[0],Integer.parseInt(partes[1]));
        	ciudades.add(c);
        }
        for(Ciudad c:ciudades) {
            assert !m.ciudadesId.containsKey(c.nombre()):String.format("Ciudad %s repetida",c.nombre());
            m.ciudadesId.put(c.nombre(), c);
            m.addVertex(c);
        }
        for(String linea:Files2.linesFromFile(f2)) {
            String[] partes = linea.split(",");
            Ciudad sc = m.ciudad(partes[0]);
            Ciudad tg = m.ciudad(partes[1]);
            Carretera cr = Carretera.of(Double.parseDouble(partes[3]),partes[2]);
            m.addEdge(sc, tg, cr);
        }
        return m;
    }
	
	public Ciudad ciudad(String nombre) {
		return this.ciudadesId.get(nombre);
	}
	
	public static void main(String[] args) {
		Mapa m = Mapa.parse("ficheros/ciudades_2.txt", 
				"ficheros/carreteras_2.txt", 
				VirtualGraph.GraphType.UNDIRECTED,
				VirtualGraph.TraverseType.FORWARD);
		System.out.println(m);
		System.out.println(m.successors(m.ciudad("VNFLN")));
	}
}
