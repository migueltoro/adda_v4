package us.lsi.p2;

import java.util.Locale;

import org.jgrapht.Graph;

import us.lsi.graphs.Graphs2;
import us.lsi.graphs.GraphsReader;

public class TestEjemplo6 {
	
	public static void main(String[] args) {
		Locale.setDefault(Locale.of("en", "US"));
		testEjemplo6("Supermercado1");
		testEjemplo6("Supermercado2");
		testEjemplo6("Supermercado3");
		
	}
	
	public static void testEjemplo6(String file) {
		
		Graph<String, Pasillo> grafo = 
				GraphsReader.newGraph("ficheros/p2/" + file + ".txt",
			v -> v[0], Pasillo::ofFormat, 
			Graphs2::simpleWeightedGraph, 
			Pasillo::mts);
		
		System.out.println("\nArchivo " + file + ".txt \n" + "Datos de entrada: " + grafo);
		
		System.out.println("Apartado A):");
		System.out.println("Las camaras deben colocarse en los siguientes "+
				Ejemplo6.apartadoA(grafo).size()+" cruces:");
		Ejemplo6.apartadoA(grafo).forEach(c->System.out.println("\t- Cruce "+c)); 
		
		System.out.println("Apartado B):");
		Ejemplo6.apartadoB(grafo, file);
		
		System.out.println("Apartado C):");
		System.out.println(file + "C.gv generado en " + "ficheros_generados/p2/ejemplo6");
		
		System.out.println(grafo.edgesOf("C07"));
	}



}
