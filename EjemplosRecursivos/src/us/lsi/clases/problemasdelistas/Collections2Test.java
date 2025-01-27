package us.lsi.clases.problemasdelistas;

import static org.junit.Assert.assertTrue;

import java.util.Collections;
import java.util.List;

import org.junit.Test;

import java.util.Comparator;

import us.lsi.clases.problemasdelistas.ProblemasDeListas.SubSecuencia;
import us.lsi.common.Comparator2;
import us.lsi.common.List2;
import us.lsi.math.Math2;


public class Collections2Test {

	@Test
	public void testBinarySearchList() {
		List<Double> lista = List2.listDoubleAleatoria(50, -20., 20.);
		Double key = lista.get(4);
		ProblemasDeListas.sort(lista);
		int r1 = ProblemasDeListas.binarySearch(lista, key);
		int r2 = Collections.binarySearch(lista, key);
		System.out.println(r1+","+r2);
		assertTrue(r1==r2);
	}
	
	@Test
	public void testSortList() {
		List<Double> lista = List2.listDoubleAleatoria(50, -20., 20.);
		ProblemasDeListas.sort(lista);
		assertTrue(Comparator2.isOrdered(lista));
	}

	@Test
	public void testSortListComparator() {
		List<Double> lista = List2.listDoubleAleatoria(50, -20., 20.);
		Comparator<Double> ord = Comparator.<Double>reverseOrder();
		ProblemasDeListas.sort(lista,ord);
		assertTrue(Comparator2.isOrdered(lista,ord));
	}
	
	@Test
	public void testEscogeKesimo() {
		List<Double> lista = List2.listDoubleAleatoria(50, -20., 20.);
		Integer k = Math2.getEnteroAleatorio(0,lista.size());
		Double r = ProblemasDeListas.getKesimo(lista,k);
		ProblemasDeListas.sort(lista);
		assertTrue(lista.get(k).equals(r));
	}

	@Test
	public void testEscogeKesimoComparator() {
		List<Double> lista = List2.listDoubleAleatoria(50, -20., 20.);
		Comparator<Double> ord = Comparator.reverseOrder();
		Integer k = Math2.getEnteroAleatorio(0,lista.size());
		Double r = ProblemasDeListas.getKesimo(lista,k,ord);
		ProblemasDeListas.sort(lista,ord);
		assertTrue(lista.get(k).equals(r));
	}
	
	@Test
	public void testSubSecuencia() {
		Double[] r = {1., -2., 11., -4., 13., -5., 2., 3.};
		List<Double> lista = List2.empty();
		for(Double e:r){
			lista.add(e);
		}
		SubSecuencia s = ProblemasDeListas.getSubSecuenciaMaxima(lista);
		assertTrue(SubSecuencia.of(2,5,lista).equals(s));
	}
}
