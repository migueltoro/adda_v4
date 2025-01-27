package us.lsi.alg.contenedores;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.jgrapht.GraphPath;

import us.lsi.alg.contenedores.DatosContenedores.Contenedor;
import us.lsi.alg.contenedores.DatosContenedores.Elemento;
import us.lsi.common.List2;
import us.lsi.common.Map2;

public class SolucionContenedores {
	
	public static SolucionContenedores of(GraphPath<VertexContenedores,EdgeContenedores> gp) {
		List<Integer> gp_as = gp.getEdgeList().stream().map(x -> x.action())
			.collect(Collectors.toList()); // getEdgeList();
		return SolucionContenedores.of(gp_as);
	}
	
	
	public static SolucionContenedores of(List<Integer> ls) {
		return new SolucionContenedores(ls);
	}
	
	private Map<Contenedor,List<Elemento>> solucion;
	
	private SolucionContenedores(List<Integer> ls) {
		solucion = Map2.empty();
		for(int elemPos=0; elemPos<ls.size(); elemPos++) {
			Integer contPos = ls.get(elemPos);
			if (contPos == DatosContenedores.getNumContenedores()) { continue;}
			Contenedor cont = DatosContenedores.getContenedor(contPos);
			if(solucion.containsKey(cont)) {
				solucion.get(cont).add(DatosContenedores.getElemento(elemPos));
			} else {
				solucion.put(cont, List2.of(DatosContenedores.getElemento(elemPos)));
			}
		}		
	}	
	
	@Override
	public String toString() {
		return solucion.entrySet().stream().map(e->e.getKey()+": "+e.getValue())
		.collect(Collectors.joining("\n", "Reparto obtenido:\n", "\n"))
		+ "Numero de contenedores " + solucion.entrySet().size() + "\n";
	}

}
