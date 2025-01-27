package us.lsi.graphs.manual.tests;


import us.lsi.graphs.manual.VirtualGraph;
import us.lsi.graphs.manual.recorridos.RecorridoEnAnchura;
import us.lsi.graphs.manual.recorridos.RecorridoEnProfundidadPostorden;
import us.lsi.graphs.manual.recorridos.RecorridoEnProfundidadPreorden;
import us.lsi.graphs.manual.recorridos.RecorridoOrdenado;
import us.lsi.graphs.manual.recorridos.RecorridoTopologico;
import us.lsi.grafos.datos.Carretera;
import us.lsi.grafos.datos.Ciudad;

public class TestRecorridos {


	public static void main(String[] args) {
		Mapa m = Mapa.parse("ficheros/ciudades.txt", 
				"ficheros/carreteras.txt", 
				VirtualGraph.GraphType.UNDIRECTED,
				VirtualGraph.TraverseType.FORWARD);
		System.out.println(m);
		System.out.println(m.successors(m.ciudad("VNFLN")));
		Ciudad c1 = m.ciudad("Sevilla");
		Ciudad c2 = m.ciudad("Algeciras");
		RecorridoEnAnchura<Ciudad,Carretera> ra = RecorridoEnAnchura.of(m);
		ra.traverseAll();
		System.out.println(ra.groups().size());
		System.out.println(ra.groups().keySet());
		System.out.println("Anchura = "+ra.path());
		System.out.println(ra.pathExist(c1, c2));
		RecorridoEnProfundidadPreorden<Ciudad,Carretera> rpp = RecorridoEnProfundidadPreorden.of(m);
		rpp.traverseAll();
		System.out.println(rpp.groups().size());
		System.out.println(rpp.groups().keySet());
		System.out.println("Preorden = "+rpp.path());
		System.out.println(rpp.pathExist(c1, c2));
		RecorridoEnProfundidadPostorden<Ciudad,Carretera> rpo = RecorridoEnProfundidadPostorden.of(m);
		rpo.traverseAll();
		System.out.println(rpo.groups().size());
		System.out.println(rpo.groups().keySet());
		System.out.println("Postorden = "+rpo.path());
		System.out.println(rpo.pathExist(c1, c2));
		RecorridoTopologico<Ciudad,Carretera> rt = RecorridoTopologico.of(m);
		rt.traverseAll();
		System.out.println(rt.groups().size());
		System.out.println(rt.groups().keySet());
		System.out.println("Topologico = "+rpo.path());
		System.out.println(rpo.pathExist(c1, c2));
		RecorridoOrdenado<Ciudad,Carretera> ro = RecorridoOrdenado.of(m);
		ro.traverse(c1);
		System.out.println(ro.pathExist(c1, c2));
		System.out.println("Ordenado = "+ro.path());
		System.out.println(ro.pathFromOrigin(c2));
			
	}

}
