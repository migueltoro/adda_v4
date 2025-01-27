package us.lsi.alg.mochila;

import java.util.List;

import org.jgrapht.GraphPath;

import us.lsi.common.Multiset;
import us.lsi.mochila.datos.DatosMochila;
import us.lsi.mochila.datos.ObjetoMochila;

public record SolucionMochila(Integer valor, Integer peso, Multiset<ObjetoMochila> objetos) {
	
	public static SolucionMochila of(List<Integer> acciones) {
		Multiset<ObjetoMochila> objetos = Multiset.empty();
		Integer valor = 0;
		Integer peso = 0;
		for(int i=0; i< acciones.size();i++) {
			Integer a = acciones.get(i);
			if (a > 0) {
				objetos.add(DatosMochila.getObjeto(i), a);
				valor += a*DatosMochila.getValor(i);
				peso += a*DatosMochila.getPeso(i);
			}
		}
		return new SolucionMochila(valor,peso,objetos);
	}
	
	public static SolucionMochila of(GraphPath<MochilaVertex, MochilaEdge> path) {
		List<MochilaEdge> edges = path.getEdgeList();
		Multiset<ObjetoMochila> objetos = Multiset.empty();
		Integer valor = 0;
		Integer peso = 0;
		for(int i=0; i< edges.size();i++) {
			Integer a = edges.get(i).action();
			if (a > 0) {
				objetos.add(DatosMochila.getObjeto(i), a);
				valor += a*DatosMochila.getValor(i);
				peso += a*DatosMochila.getPeso(i);
			}
		}
		return new SolucionMochila(valor,peso,objetos);
	}
	
	@Override
	public String toString() {
		return String.format("Solucion de valor %d, peso %d y unidades elegidas \n\t%s",this.valor(),this.peso(),this.objetos());
	}

}
