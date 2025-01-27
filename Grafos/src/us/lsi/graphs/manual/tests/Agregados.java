package us.lsi.graphs.manual.tests;

import java.util.Comparator;
import java.util.List;

import us.lsi.graphs.manual.Cola;
import us.lsi.graphs.manual.ListaOrdenada;
import us.lsi.graphs.manual.ListaOrdenadaSinRepeticion;
import us.lsi.graphs.manual.Pila;

public class Agregados {

	public static void main(String[] args) {
		List<Integer> ls = List.of(23, 47, 47, 1,2,-3,4,5);
		Pila<Integer> pl = Pila.of();
		pl.addAll(ls);
		System.out.println(pl.removeAll());
		Cola<Integer> cl = Cola.of();
		cl.addAll(ls);
		System.out.println(cl.removeAll());
		ListaOrdenada<Integer> lo = ListaOrdenada.of(Comparator.comparing(x->x));
		lo.addAll(ls);
		System.out.println(lo.removeAll());
		ListaOrdenadaSinRepeticion<Integer> los = ListaOrdenadaSinRepeticion.of(Comparator.comparing(x->x));
		los.addAll(ls);
		System.out.println(los.removeAll());		
	}

}
