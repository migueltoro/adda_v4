package us.lsi.clases.problemasdelistas;

import static org.junit.Assert.*;

import java.util.Comparator;
import java.util.List;





import org.junit.Test;

import us.lsi.common.*;
import us.lsi.math.Math2;

public class TestProblemasListas {

	@Test
	public void testReordenaMedianteBanderaHolandesa() {
		List<Double> lista = List2.listDoubleAleatoria(50, -20., 20.);
		Comparator<Double> ord = Comparator.naturalOrder();
		Double pivote = lista.get(0);
		IntPair p = ProblemasDeListas.banderaHolandesa(lista, pivote, 0,lista.size(), ord);
		for(int i=0;i<p.first();i++){
			assertTrue(lista.get(i)<pivote);
		}
		for(int i=p.first();i<p.second();i++){
			assertTrue(lista.get(i).equals(pivote));
		}
		for(int i=p.second();i<lista.size();i++){
			assertTrue(lista.get(i)>pivote);
		}
	}

	@Test
	public void testReordenaSobrePivote() {
		List<Double> lista = List2.listDoubleAleatoria(50, -20., 20.);
		Comparator<Double> ord = Comparator.naturalOrder();
		Double pivote = lista.get(0);
		Integer p = ProblemasDeListas.reordenaSobrePivote(lista, pivote, 0,lista.size(), ord);
		for(int i=0;i<p;i++){
			assertTrue(lista.get(i)<pivote);
		}
		for(int i=p;i<lista.size();i++){
			assertTrue(lista.get(i)>=pivote);
		}
	}

	@Test
	public void testOrdenaBase() {
		List<Double> lista = List2.listDoubleAleatoria(50, -20., 20.);
		ProblemasDeListas.ordenaBase(lista,0,lista.size(),Comparator.naturalOrder());
		assertTrue(Comparator2.isOrdered(lista));
	}
	
	@Test
	public void testGetEnteroAleatorio() {
		Integer a = Math2.getEnteroAleatorio(100, 1000);
		Integer b = Math2.getEnteroAleatorio(100, 1000);
		Integer c = Math2.getEnteroAleatorio(a, a+b);
		assertTrue(c>=a);
		assertTrue(c<a+b);
	};

}
