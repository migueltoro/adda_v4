package us.lsi.alg.monedas;


import java.util.List;

import org.jgrapht.GraphPath;
import us.lsi.common.Multiset;

public record SolucionMonedas(Multiset<Moneda> monedas, Integer peso, Integer valor) 
               implements Comparable<SolucionMonedas> {
	
	public static SolucionMonedas of(GraphPath<MonedasVertex,MonedasEdge> path) {	
		Multiset<Moneda> monedas = Multiset.empty();
		Integer peso = 0;
		Integer valor = 0;
		for (MonedasEdge e : path.getEdgeList()) {
			Integer i = e.source().index();
			Moneda m = DatosMonedas.get(i);
			Integer p = DatosMonedas.peso(i);
			Integer v = DatosMonedas.valor(i);
			monedas.add(m, e.action());
			peso = peso + e.action() * p;
			valor = valor + e.action() * v;
		}
		return new SolucionMonedas(monedas, peso,valor);
	}
	
	public static SolucionMonedas of(List<Integer> unidades) {	
		Multiset<Moneda> monedas = Multiset.empty();
		Integer peso = 0;
		Integer valor = 0;
		for (int i =0; i<unidades.size();i++) {
			Moneda m = DatosMonedas.get(i);
			Integer p = DatosMonedas.peso(i);
			Integer v = DatosMonedas.valor(i);
			monedas.add(m, unidades.get(i));
			peso = peso + unidades.get(i) * p;
			valor = valor + unidades.get(i) * v;
		}
		return new SolucionMonedas(monedas, peso,valor);
	}

	@Override
	public int compareTo(SolucionMonedas other) {
		return this.peso.compareTo(other.peso);
	}

}
