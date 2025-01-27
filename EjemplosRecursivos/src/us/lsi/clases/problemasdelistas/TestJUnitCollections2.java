package us.lsi.clases.problemasdelistas;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import us.lsi.clases.problemasdelistas.ProblemasDeListas.SubSecuencia;
import us.lsi.common.Comparator2;
import us.lsi.common.List2;
import us.lsi.math.Math2;

public class TestJUnitCollections2 {

	@Test
	public void testBinarySearchListOfEE() {
		List<Double> ls = List2.empty();
		for(int i = 0; i<200; i++){
			ls.add(Math2.getDoubleAleatorio(0., 1000.));
		}
		ProblemasDeListas.sort(ls);		
		assertTrue(ProblemasDeListas.binarySearch(ls, ls.get(20))==20);
	}

	@Test
	public void testSortListOfE() {
		List<Double> ls = List2.empty();
		for(int i = 0; i<1000; i++){
			ls.add(Math2.getDoubleAleatorio(0., 1000.));
		}
		ProblemasDeListas.sort(ls);
		assertTrue(Comparator2.isOrdered(ls));
	}

	@Test
	public void testMergeSortListOfE() {
		List<Double> ls = List2.empty();
		for(int i = 0; i<1000; i++){
			ls.add(Math2.getDoubleAleatorio(0., 1000.));
		}
		ProblemasDeListas.mergeSort(ls);
		assertTrue(Comparator2.isOrdered(ls));
	}
	
	@Test
	public void testGetKesimoListOfEInt() {
		List<Double> ls = List2.empty();	
		for(int i = 0; i<200; i++){
			ls.add(Math2.getDoubleAleatorio(0., 1000.));
		}
		int k = Math2.getEnteroAleatorio(0, 200);
		Double r = ProblemasDeListas.getKesimo(ls, k);
		ProblemasDeListas.sort(ls);
		assertTrue(r==ls.get(k));
	}

	@Test
	public void testGetSubSecuenciaMaxima() {
		List<Double> ls = List2.empty();
		int a = Math2.getEnteroAleatorio(0, 198);
		int b = Math2.getEnteroAleatorio(a, 200);
		for(int i = 0; i<a; i++){
			ls.add(Math2.getDoubleAleatorio(-1000., 0.));
		}
		for(int i = a; i<b; i++){
			ls.add(Math2.getDoubleAleatorio(0., 1000.));
		}
		for(int i = b; i<200; i++){
			ls.add(Math2.getDoubleAleatorio(-1000., 0.));
		}
		SubSecuencia s = ProblemasDeListas.getSubSecuenciaMaxima(ls);
		assertTrue(s.from()==a && s.to()==b);
	}

}
