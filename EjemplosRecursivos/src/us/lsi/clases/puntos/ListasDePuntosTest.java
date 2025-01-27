package us.lsi.clases.puntos;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Set;

import org.junit.Test;

import us.lsi.geometria.ParDePuntos;
import us.lsi.geometria.Punto2D;

public class ListasDePuntosTest {

	@Test
	public void testParMasCercano() {
		List<Punto2D> lista = ListasDePuntos.getListaPuntosAleatoria(10000);
		ParDePuntos p1 = ListasDePuntos.parMasCercano(lista);
		ParDePuntos p2 = ListasDePuntos.parMasCercanoBase(0, lista.size(), lista);
		if(!p1.distancia().equals(p2.distancia())){
			System.out.println(p1+","+p2);
		}
		assertTrue(p1.distancia().equals(p2.distancia()));
	}
	
	
	@Test
	public void testPuntosMaximales() {
		List<Punto2D> lista = ListasDePuntos.getListaPuntosAleatoria(10000);
		Set<Punto2D> r1 = ListasDePuntos.puntosMaximales(lista);
		Set<Punto2D> r2 = ListasDePuntos.puntosMaximalesBase(0, lista.size(), lista);
		assertTrue(r1.equals(r2));
	}
	
}
